package com.tiger.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tiger.service.CodeService;
import com.tiger.service.TuserService;
import com.tiger.vo.LGroupCd;
import com.tiger.vo.Tuser;

@Controller
public class MainController {
	private TuserService  tuserservice;	
	public void setTuserService (TuserService tuserService) {
		this.tuserservice = tuserService;
	}
	
	private CodeService  codeService;	
	public void setCodeService (CodeService codeService) {
		this.codeService = codeService;
	}
	
	@RequestMapping("/main")
	public String main() {
		return "/main.jsp";
	}
	
	@RequestMapping("/code")
	public String code() {
		return "/code.jsp";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "/login.jsp";
	}
	
	@RequestMapping("/jepum")
	public String jepum(Model model, HttpServletRequest request) {
		Tuser tUser = (Tuser) request.getSession().getAttribute("tuser");
		if (tUser == null) {
			System.out.println("no login ----------->");
			return "L";
		}
		model.addAttribute("tUser", tUser);
		
		List<LGroupCd> lGroupCdList = codeService.selectLGroupCdList("BRAND");	
		model.addAttribute("sBrandList", lGroupCdList);
		
		lGroupCdList = codeService.selectLGroupCdList("GIYY");		
		model.addAttribute("sGiYYList", lGroupCdList);
		
		
		lGroupCdList = codeService.selectLGroupCdList("SEASON");		
		model.addAttribute("sSeasonList", lGroupCdList);
		
		lGroupCdList = codeService.selectPumjongList();		
		model.addAttribute("sSeason", lGroupCdList);
		
		lGroupCdList = codeService.selectLGroupCdList("JEPUMG");		
		model.addAttribute("jepumGbList", lGroupCdList);
		
		lGroupCdList = codeService.selectLGroupCdList("GIG");		
		model.addAttribute("giGbList", lGroupCdList);
		
		lGroupCdList = codeService.selectLGroupCdList("MAJING");		
		model.addAttribute("majinGbList", lGroupCdList);
		
		lGroupCdList = codeService.selectLGroupCdList("SOJEG");		
		model.addAttribute("sojeGbList", lGroupCdList);
		
		lGroupCdList = codeService.selectLGroupCdList("PRICEG");		
		model.addAttribute("priceGbList", lGroupCdList);
		
		lGroupCdList = codeService.selectLGroupCdList("SIZEG");		
		model.addAttribute("sizGrList", lGroupCdList);
		
		lGroupCdList = codeService.selectLGroupCdList("SENGH");		
		model.addAttribute("sengHtList", lGroupCdList);
		
		return "/jepum.jsp";
	}
	
	@RequestMapping("/login/in")
	public String loginin(@RequestParam(required=false) String id, 
						  @RequestParam(required=false) String pass, HttpServletRequest request) {
		
		Map<String, Object>  resultMap = new HashMap<>();
		resultMap = tuserservice.selectOne(id, pass);
		
		if (resultMap.get("errorYN").equals("Y") ) {
			request.setAttribute("errorYN", "Y");
			request.setAttribute("msg", resultMap.get("msg"));
			return "redirect:/login";
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
}
