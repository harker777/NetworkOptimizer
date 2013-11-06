/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.networker.controller;

import com.epam.networker.db.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author harker777
 */
@Controller
public class ConnectionController {

	@Autowired
	private ConnectionService connectionService;

	@RequestMapping(value = "/connections")
	public String list(Model uiModel) {
		uiModel.addAttribute("connections", connectionService.findAll());
		return "connections/list";
	}
}
