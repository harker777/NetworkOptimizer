package com.epam.networker.controller;

import com.epam.networker.db.entities.Solution;
import com.epam.networker.db.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author harker777
 */
@Controller
public class SolutionController {

	@Autowired
	private SolutionService solutionService;

	@RequestMapping(value = "/solutions/{id}")
	public String details(@PathVariable Integer id, Model uiModel) {
		Solution foundSolution = solutionService.findById(id);
		uiModel.addAttribute("solution", foundSolution);
		return "solutions/details";
	}
}
