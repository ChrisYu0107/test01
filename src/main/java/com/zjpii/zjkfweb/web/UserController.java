package com.zjpii.zjkfweb.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zjpii.zjkfweb.entity.TUser;
import com.zjpii.zjkfweb.service.UserService;




@Controller
@RequestMapping("/user")
public class UserController {
	
	
	

	@Autowired
	private UserService userService;

	@RequestMapping("/showUser/{id}")
	public String showUser(@PathVariable Integer id, HttpServletRequest request) {
		TUser u = userService.getEntity(id);
		request.setAttribute("user", u);
		return "showUser";
	}
	
	@ModelAttribute("user")
	public TUser getUser(){
		TUser user = new TUser();
		user.setName("11");
		return user;
	}

}
