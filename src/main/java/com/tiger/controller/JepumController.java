package com.tiger.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiger.service.CodeService;
import com.tiger.service.JepumService;
import com.tiger.service.TuserService;
import com.tiger.vo.CodeGb;
import com.tiger.vo.LGroupCd;
import com.tiger.vo.MGroupCd;
import com.tiger.vo.Tuser;

@Controller
public class JepumController {
	private JepumService  jepumService;
	
	public void setJepumService (JepumService jepumService) {
		this.jepumService = jepumService;
	}	
	
	@ResponseBody
	@RequestMapping(value="/code/checkcode", method=RequestMethod.POST)
	public String main(@RequestParam(required=false) String gubunCd) {
		//String errorYN = jepumService.checkCode(gubunCd);
		
		return "";
	}
	
	@RequestMapping(value="/code/insertGb", method=RequestMethod.POST)
	public String insertGb(@RequestParam(required=false) String gubunCd, 
						   @RequestParam(required=false) String gubunNm) {
		gubunCd = gubunCd.toUpperCase();	
		//codeService.insert(gubunCd, gubunNm);
		
		return "redirect:/code/gbList";
	}
}
