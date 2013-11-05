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

/**
 *
 * @author harker777
 */
@RequestMapping("/connections")
@Controller
public class ConnectionController {

	@Autowired
	private ConnectionService connectionService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model uiModel) {
		uiModel.addAttribute("collections", connectionService.findAll());
		return "connections/list";
	}
}
