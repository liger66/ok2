package com.tiger.controller;

import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tiger.service.CodeService;
import com.tiger.service.JepumService;
import com.tiger.vo.CodeGb;
import com.tiger.vo.Jepum;
import com.tiger.vo.LGroupCd;
import com.tiger.vo.Pumjong;
import com.tiger.vo.Tuser;

@Controller
public class JepumController {
	private JepumService  jepumService;	
	public void setJepumService (JepumService jepumService) {
		this.jepumService = jepumService;
	}
	
	private CodeService  codeService;
	public void setCodeService (CodeService codeService) {
		this.codeService = codeService;
	}
	
	@RequestMapping("/jepum")
	public String jepum(Model model, HttpServletRequest request) {
		Tuser tUser = (Tuser) request.getSession().getAttribute("tuser");
		if (tUser == null) {
			System.out.println("no login ----------->");
			return "L";
		}
		
		Model mod = jepumService.getSerchHead(model);
		model = mod;
		
		model.addAttribute("tUser", tUser);
		model.addAttribute("jepum", new Jepum());
		
		return "/jepum.jsp";
	}
	
	@RequestMapping(value="/jepum/mnew")
	public String mnew(@ModelAttribute Jepum jepum, Model model) throws ParseException {
		model.addAttribute("mnew", true);
		
		Model mod = jepumService.getMainHead(model);
		model = mod;
		
		Jepum jep = jepumService.getMainHead2(jepum);
		
		jepum = jep;
				
		return "/jepum.jsp";
	}
	
	@RequestMapping(value="/jepum/change", method=RequestMethod.POST )
	public String change(@ModelAttribute Jepum jepum, Model model) throws ParseException {
		System.out.println("in change -----------------");
		jepum.setJepum(jepum.getJepum().toUpperCase());
		
		Map<String, Object>  resultMap = new HashMap<>();
		resultMap = jepumService.jepumChange(jepum);
		
		model.addAttribute("brandNm", resultMap.get("brandNm"));
		model.addAttribute("giYYNm", resultMap.get("giYYNm"));
		model.addAttribute("seasonNm", resultMap.get("seasonNm"));
		model.addAttribute("pumNm", resultMap.get("pumNm"));	
		System.out.println("before return change -----------------");	
		return  "/jepum.jsp";
	}
	
	// , produces="application/json; charset=utf-8"
	@ResponseBody
	@RequestMapping(value="/jepum/checkData", method=RequestMethod.POST, produces="text/plain; charset=utf-8" )
	public String checkData(@ModelAttribute Jepum jepum) {
		System.out.println("in  check data -----------");
		jepum.setJepum(jepum.getJepum().toUpperCase());
		
		Map<String, Object>  resultMap = new HashMap<>();
		resultMap = jepumService.checkInData(jepum);
		
		String msg = "OK";
		if (resultMap.get("errorYN") == "Y") {
			msg = (String) resultMap.get("msg");
		}
		
		return msg;
	}
	
	@RequestMapping(value="/jepum/insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute Jepum jepum, Model model, HttpServletRequest request) {
		System.out.println("insert in : " + jepum.getJepum());
		
		Tuser tuser = (Tuser) request.getSession().getAttribute("tuser");
		if (tuser == null) {
			System.out.println("no login ----------->");
		}
		String userCd = tuser.getUserCd();
		String jepumCd = jepum.getJepum().toUpperCase();
		
		jepum.setInUser(userCd);
		jepum.setJepum(jepumCd);
		model.addAttribute("mnew", false);
		
		Map<String, Object>  resultMap = new HashMap<>();
		
		resultMap = jepumService.checkInData(jepum);
		
		if (resultMap.get("errorYN") == "Y") {
			String msg = (String) resultMap.get("msg");
			System.out.println("error msg : " + msg);
			return "/jepum.jsp";
		}
		
		model.addAttribute("brandNm", resultMap.get("brandNm"));
		model.addAttribute("giYYNm", resultMap.get("giYYNm"));
		model.addAttribute("seasonNm", resultMap.get("seasonNm"));
		model.addAttribute("pumNm", resultMap.get("pumNm"));		
		model.addAttribute("hJepum", jepum.getJepum());
		
		jepum = (Jepum) resultMap.get("jepum");
		
		jepumService.insert(jepum);
		
		Model mod = jepumService.getSerchHead(model);
		model = mod;
		
		return "/jepum.jsp";
				
		//return "redirect:/jepum/list";
	}
	
	@RequestMapping(value="/jepum/list", method=RequestMethod.GET)
	public String jpList(@ModelAttribute Jepum jepum,  Model model,
						 @RequestParam(required=false) String sBrand, 
			   			 @RequestParam(required=false) String sGiYY,
			   			 @RequestParam(required=false) String sSeason,
			   			 @RequestParam(required=false) String sPumjong) {
		System.out.println("brand : " + sBrand);
		System.out.println("giyy  : " + sGiYY);
		System.out.println("season : " + sSeason);
		System.out.println("pumjong : " + sPumjong);
		
		Jepum jep = new Jepum();
		jep.setBrand(sBrand);
		jep.setGiYY(sGiYY);
		jep.setSeason(sSeason);
		jep.setPum(sPumjong);
		
		List<Jepum>  jepumList = jepumService.selectList(jep);		
			
		model.addAttribute("sJepumList", jepumList);
		
		Model mod = jepumService.getSerchHead(model);
		model = mod;
		
		model.addAttribute("jepum", new Jepum());
				
		System.out.println("list length : " + jepumList.size());
		
		return "/jepum.jsp";
	}
	
	@ResponseBody
	@RequestMapping(value="/jepum/selectJepum", method=RequestMethod.POST)
	public Jepum selectJepum(@ModelAttribute Jepum jepum,
						 	  @RequestParam(required=false) String sJepum) {
		System.out.println("sjepum : " + sJepum);
		
		Jepum jep = new Jepum();
		jep.setJepum(sJepum);
		
		jepum = jepumService.selectOne(jep);
		
		return jepum;
	}	
}
