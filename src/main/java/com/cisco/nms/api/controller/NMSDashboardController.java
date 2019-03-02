package com.cisco.nms.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author dedadhic
 *
 */
@Controller
public class NMSDashboardController {
	
	@RequestMapping(value = "/")
	public String index() {
		return "index.html";
	}	
}
