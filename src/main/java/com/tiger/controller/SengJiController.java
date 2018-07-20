package com.tiger.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiger.service.CodeService;
import com.tiger.service.JepumService;
import com.tiger.service.SengJiService;
import com.tiger.vo.Jepum;
import com.tiger.vo.Tuser;

@Controller
public class SengJiController {
	private SengJiService  sengJiService;
	public void setSengJiService (SengJiService sengJiService) {
		this.sengJiService = sengJiService;
	}
	
	private JepumService  jepumService;	
	public void setJepumService (JepumService jepumService) {
		this.jepumService = jepumService;
	}
	
	private CodeService  codeService;
	public void setCodeService (CodeService codeService) {
		this.codeService = codeService;
	}
	
	@RequestMapping("/sengJi")
	public String jepum(Model model, HttpServletRequest request) {
		Tuser tUser = (Tuser) request.getSession().getAttribute("tuser");
		if (tUser == null) {
			System.out.println("no login ----------->");
			return "L";
		}
		
		model.addAttribute("tUser", tUser);
		
		return "/sengJi.jsp";
	}
}
