package main.resources.job.listing.com.job.listing;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/main")
public class InitialController {

	@Resource(name = "detailService")
	private DetailService detailService;

	@RequestMapping(value = "/jobs", method = RequestMethod.GET)
	public String getTaskList(Model model) {
		List jobList = detailService.getAll();
		model.addAttribute("jobList", jobList);
		return "jobs";
	}

	@RequestMapping(value = "/jobs/search", method = RequestMethod.GET)
	public String editTask(@RequestParam(value = "title", required = true) String jobTitle, @RequestParam(value = "location", required = true)String location,
			Model model) {
		model.addAttribute("jobList", detailService.findJob(jobTitle,location));
		return "jobs";
	}

	@RequestMapping(value = "/jobs/sync", method = RequestMethod.POST)
	public String syncJobs() {
		detailService.add();
		return "main";
	}
	
	public String index() {
		return "main";
	}

}
