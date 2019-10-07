package com.sample.forumcreation.controller;



import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sample.forumcreation.ForumCollectorService;
import com.sample.forumcreation.model.CommentModel;
import com.sample.forumcreation.model.UserDetailModel;


/**
 * ForumController connects the front and back end.
 * 
 * @author Gayatri
 *
 */
@Controller
@RequestMapping("/forumCreator")
public class ForumController {

	private static Logger log = Logger.getLogger(ForumController.class);

	@Resource(name = "forumCollectorService")
	ForumCollectorService forumCollectorService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getIndexDate (Model model) {
		log.debug("Index Welcome page");
		return "welcome";
	}
	
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String registerTask(UserDetailModel userDetailModel,Model model) {
		log.debug("login place"+userDetailModel.getEmail()+" "+userDetailModel.getName()+" "+userDetailModel.getPass());
		boolean resultTask = forumCollectorService.registerUser(userDetailModel);
		if(resultTask)
			model.addAttribute("registerResult", "Successfully registered, Login again using the credentials");
		else
			model.addAttribute("registerResult", "Not registered, please try again");
		return "welcome";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginTask(UserDetailModel userDetailModel,Model model) {
		log.debug("login place"+" "+userDetailModel.getName()+" "+userDetailModel.getPass());
		boolean resultTask = forumCollectorService.authenticateUser(userDetailModel);
		if (resultTask) {
			List<CommentModel> commentModelList = forumCollectorService.getCommentDetails();
			model.addAttribute("commentList", commentModelList);
			model.addAttribute("name", userDetailModel.getName());
			model.addAttribute("userId", userDetailModel.getUserId());
			return "forum_profile";
		}
		else {
			model.addAttribute("registerResult", "Login credential wrong, try again");
			return "welcome";
		}
	}
	
	@RequestMapping(value = "/addComment", method = RequestMethod.POST)
	public String addComment(CommentModel commentModel, Model model) {
		forumCollectorService.addComment(commentModel);
		List<CommentModel> commentModelList = forumCollectorService.getCommentDetails();
		model.addAttribute("commentList", commentModelList);
		model.addAttribute("name", commentModel.getName());
		model.addAttribute("email", commentModel.getUserId());
		return "forum_profile";
	}
	
	@RequestMapping(value = "/postReply", method = RequestMethod.POST)
	public String postReply(CommentModel commentModel, String reply, Model model) {
		forumCollectorService.addReply(commentModel, reply);
		List<CommentModel> commentModelList = forumCollectorService.getCommentDetails();
		model.addAttribute("commentList", commentModelList);
		model.addAttribute("name", commentModel.getName());
		model.addAttribute("email", commentModel.getUserId());
		return "forum_profile";
	}

}
