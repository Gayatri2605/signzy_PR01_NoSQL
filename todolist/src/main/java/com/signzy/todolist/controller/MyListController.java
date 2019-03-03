package com.signzy.todolist.controller;

import java.util.List;

import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.signzy.todolist.MyListModel;
import com.signzy.todolist.MyListService;

/**
 * MyListController connects the front and back end.
 * 
 * @author Gayatri
 *
 */
@Controller
@RequestMapping("/mainList")
public class MyListController {

	private static Logger log = Logger.getLogger(MyListController.class);

	@Resource(name = "myListService")
	private MyListService myListService;

	/**
	 * getTaskList method displays the initial task list.
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getTaskList(Model model) {
		log.debug("Request to fetch all task from the mongo database");
		List user_list = myListService.getAll();
		model.addAttribute("taskLists", user_list);
		return "welcome";
	}

	/**
	 * addUser method opens the add new task page.
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addTask(Model model) {
		log.debug("Request to open the add new task page");
		model.addAttribute("taskAttr", new MyListModel());
		return "form";
	}

	/**
	 * editTask method opens the edit task page.
	 * 
	 * @param title
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editTask(@RequestParam(value = "title", required = true) String title, Model model) {
		log.debug("Request to open the edit task page");
		model.addAttribute("taskAttr", myListService.findUserId(title));
		return "form";
	}

	/**
	 * deleteTask method delets the specified task with task title.
	 * 
	 * @param title
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteTask(@RequestParam(value = "title", required = true) String title, Model model) {
		myListService.delete(title);
		return "redirect:list";
	}

	/**
	 * saveTask method adds a new task or updating an existing task.
	 * 
	 * @param myListModel
	 * @return String
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveTask(@ModelAttribute("taskAttr") MyListModel myListModel) {
		boolean redirect = false;
		if (myListModel.getMode() == 1)
			redirect = myListService.edit(myListModel);
		else
			redirect = myListService.add(myListModel);
		if (redirect)
			return "redirect:list";
		else
			return "redirect:error";
	}

	/**
	 * getError method displays the error page.
	 * 
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String getError(Model model) {
		log.debug("Request to fetch all task from the mongo database");
		return "error";
	}
}
