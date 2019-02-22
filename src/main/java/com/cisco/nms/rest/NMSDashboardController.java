package com.cisco.nms.rest;

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
