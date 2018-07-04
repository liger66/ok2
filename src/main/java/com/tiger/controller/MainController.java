package com.tiger.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tiger.service.TuserService;

@Controller
public class MainController {
	private TuserService  tuserservice;
	
	public void setTuserService (TuserService tuserService) {
		this.tuserservice = tuserService;
	}
	
	@RequestMapping("/login")
	public String login() {
		return "/login.jsp";
	}
	
	@RequestMapping("/loginin")
	public String loginin(@RequestParam(required=false) String id, 
						  @RequestParam(required=false) String pass, HttpServletRequest request) {
		
		Map<String, Object>  resultMap = new HashMap<>();
		System.out.println("aa----------------------------------");
		resultMap = tuserservice.selectOne(id, pass);
		System.out.println("bb----------------------------------");
		if (resultMap.get("errorYN").equals("N") ) {
			return "/login.jsp";
		}
		request.getSession().setAttribute("tuser", resultMap.get("tuser"));
		
		return "/main.jsp";
	}
	
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
