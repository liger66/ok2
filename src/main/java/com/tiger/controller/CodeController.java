package com.tiger.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tiger.service.CodeService;
import com.tiger.service.TuserService;

@Controller
public class CodeController {
	private CodeService  codeService;
	
	public void setTuserService (CodeService codeService) {
		this.codeService = codeService;
	}
	
	@RequestMapping("/main")
	public String main() {
		return "/main.jsp";
	}
	
	@RequestMapping("/code/insertGb")
	public String loginin(@RequestParam(required=false) String gbCode, 
						  @RequestParam(required=false) String gbName, HttpServletRequest request) {
		
		codeService.insert(gbCode, gbName);
				
		System.out.println("bb----------------------------");
		//request.getSession().setAttribute("tuser", resultMap.get("tuser"));
		
		return "/main.jsp";
	}
}
