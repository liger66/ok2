package com.tiger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@RequestMapping("/hello")
	public String hello() {
		return "/hello.jsp";
	}
	
	@RequestMapping("/hello2")
	public String hello2() {
		return "/hello2.jsp";
	}
	
	@RequestMapping("/hello3")
	public String hello3() {
		return "/hello3.jsp";
	}
	
	@RequestMapping("/menu1")
	public String menu1() {
		return "/menu1.jsp";
	}
	
	@RequestMapping("/menu2")
	public String menu2() {
		return "/menu2.jsp";
	}
}
