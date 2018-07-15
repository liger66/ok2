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
	
	@RequestMapping(value="/jepum/insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute Jepum jepum, BindingResult result, Model model, HttpServletRequest request) {
		Tuser tuser = (Tuser) request.getSession().getAttribute("tuser");
		if (tuser == null) {
			System.out.println("no login ----------->");
			FieldError error = new FieldError("dup", "jepum", "로그인 후 이용 가능 합니다.");
			result.addError(error);
		}
		String userCd = tuser.getUserCd();
		String jepumCd = jepum.getJepum().toUpperCase();
		
		jepum.setInUser(userCd);
		jepum.setJepum(jepumCd);
		model.addAttribute("mnew", false);
		
		Map<String, Object>  resultMap = new HashMap<>();
		
		resultMap = jepumService.checkInData(jepum);	
		
		if(resultMap.get("errorYN") == "Y") {
			String msg = (String) resultMap.get("msg");
			FieldError error = new FieldError("dup", "jepum", msg);
			result.addError(error);
			System.out.println("error msg : " + msg);
			return "/jepum.jsp";
		}
		
		if(result.hasErrors()) {
			model.addAttribute("errors",result.getAllErrors());
			System.out.println("error count : " + result.getErrorCount());
			//return "/jepum.jsp";
		}
		
		model.addAttribute("brandNm", resultMap.get("brandNm"));
		model.addAttribute("giYYNm", resultMap.get("giYYNm"));
		model.addAttribute("seasonNm", resultMap.get("seasonNm"));
		model.addAttribute("pumNm", resultMap.get("pumNm"));		
		model.addAttribute("hJepum", jepum.getJepum());
		
		jepum = (Jepum) resultMap.get("jepum");
		System.out.println("insert jepum : " + jepum);
		
		jepumService.insert(jepum);
		
		return "/jepum.jsp";
				
		//return "redirect:/jepum/list";
	}
	
	@RequestMapping(value="/jepum/list", method=RequestMethod.POST)
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
