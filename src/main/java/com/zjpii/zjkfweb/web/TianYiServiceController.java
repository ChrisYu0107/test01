package com.zjpii.zjkfweb.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
@RequestMapping("/tianYiService")
public class TianYiServiceController {

	@RequestMapping("/showIndexPage")
	public String showIndexPage(HttpServletRequest request) {
		
		return "tianyiservice/html/index";
	}
	
	
	@RequestMapping("/showZzpzPage")
	public String showZzpzPage(HttpServletRequest request) {
		
		return "tianyiservice/html/zzpz";
	}
	
	@RequestMapping("/getFormdate")
	public String getFormdate(HttpServletRequest request) {
		System.out.println("2");
		return "tianyiservice/html/zzpz";
	}
}
