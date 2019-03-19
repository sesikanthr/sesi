package com.cisco.cdr.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author dedadhic
 *
 */
@Controller
public class CDRDashboardController {
	
	@RequestMapping(value = "/")
	public String index() {
		return "index.html";
	}	
}
