package com.tiger.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@ResponseBody
	@RequestMapping(value="/sengJi/jepumIn", method=RequestMethod.POST, produces="text/plain; charset=utf-8" )
	public Map<String, Object> checkData(@RequestParam(required=false) String jepum ) {
		System.out.println("in  jepumin -----------");
		jepum = jepum.toUpperCase();
		
		Map<String, Object>  resultMap = new HashMap<>();
		resultMap = sengJiService.jepumIn(jepum);
		
		
		return resultMap;
	}
}
