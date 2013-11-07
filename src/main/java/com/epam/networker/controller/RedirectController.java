/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.networker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author harker777
 */
@Controller
public class RedirectController {

	private final String DEFAULT_ROUTE = "/uploadForm";

	@RequestMapping(value = "/")
	public String redirectToDefaultRoute() {
		return "redirect:" + DEFAULT_ROUTE;
	}
}
