/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.networker.controller;

import com.epam.networker.db.entities.Task;
import com.epam.networker.db.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Iaroslav_Mazai
 */
@Controller
public class TaskController {

	@Autowired
	TaskService taskService;

	@RequestMapping(value = "/tasks")
	public String list(Model uiModel) {
		uiModel.addAttribute("tasks", taskService.findAll());
		return "tasks/list";
	}

	@RequestMapping(value = "/tasks/{id}")
	public String details(@PathVariable Integer id, Model uiModel) {
		Task task = taskService.findById(id);
		uiModel.addAttribute("task", task);
		return "tasks/details";
	}
}
