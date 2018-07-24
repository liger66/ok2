package com.tiger.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
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
import com.tiger.vo.Jepum;
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
		
		model = jepumService.getSerchHead(model);		
		model.addAttribute("tUser", tUser);
		model.addAttribute("jepum", new Jepum());
		
		return "/jepum.jsp";
	}
	
	@RequestMapping(value="/jepum/mnew")
	public String mnew(@ModelAttribute Jepum jepum, Model model) throws ParseException {
		model.addAttribute("mnew", true);
		
		model = jepumService.getMainHead(model);		
		jepum = jepumService.getMainHead2(jepum);
				
		return "/jepum.jsp";
	}
	
	@RequestMapping(value="/jepum/change", method=RequestMethod.POST )
	@ResponseBody
	public Map<String, Object> change(@ModelAttribute Jepum jepum, Model model) throws ParseException {
		jepum.setJepum(jepum.getJepum().toUpperCase());
		
		Map<String, Object>  resultMap = new HashMap<>();
		resultMap = jepumService.jepumChange(jepum);
		
		return  resultMap;
	}
	
	// , produces="application/json; charset=utf-8"
	@ResponseBody
	@RequestMapping(value="/jepum/checkData", method=RequestMethod.POST, produces="text/plain; charset=utf-8" )
	public String checkData(@ModelAttribute Jepum jepum) {
		
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
		
		model = jepumService.getSerchHead(model);
		
		//return "/jepum.jsp";				
		//return "redirect:/jepum/list";
		return String.format("redirect:/jepum/list?sBrand=%s&sGiYY=%s&sSeason=%s&sPumjong=%s",
							jepum.getBrand(), jepum.getGiYY(), jepum.getSeason(), jepum.getPum());
	}
	
	@RequestMapping(value="/jepum/list", method=RequestMethod.GET)
	public String jpList(@ModelAttribute Jepum jepum,  Model model,
						 @RequestParam(required=false) String sBrand, 
			   			 @RequestParam(required=false) String sGiYY,
			   			 @RequestParam(required=false) String sSeason,
			   			 @RequestParam(required=false) String sPumjong) {
		
		Jepum jep = new Jepum();
		jep.setBrand(sBrand);
		jep.setGiYY(sGiYY);
		jep.setSeason(sSeason);
		jep.setPum(sPumjong);
		
		List<Jepum>  jepumList = jepumService.selectList(jep);		
			
		model.addAttribute("sJepumList", jepumList);
		
		model = jepumService.getSerchHead(model);		
		model.addAttribute("jepum", new Jepum());
				
		System.out.println("list length : " + jepumList.size());
		
		return "/jepum.jsp";
	}
	
	@ResponseBody
	@RequestMapping(value="/jepum/selectJepum", method=RequestMethod.POST)
	public Map<String, Object> selectJepum(@ModelAttribute Jepum jepum,
						 	  @RequestParam(required=false) String sJepum) {
		System.out.println("jepum in sjepum : " + sJepum);
		
		Map<String, Object>  resultMap = new HashMap<>();
		
		Jepum jep = new Jepum();
		jep.setJepum(sJepum);
		
		jepum = jepumService.selectOne(jep);
		
		resultMap = (Map<String, Object>) jepumService.getJepumDetl(jepum);
		
		//String dt = jepum.getGiIpgoDt().format("yyyy-mm-dd");
		//System.out.println("---------------- " + dt);
		//jepum.setGiIpgoDt(jepum.getGiIpgoDt().format("yyyy-mm-dd"));
		
		//SimpleDateFormat  dt =  new SimpleDateFormat("yyyy-mm-dd");
		//jepum.setGiIpgoDt(dt.format(jepum.getGiIpgoDt()) );
		
		String dt = jepum.getGiIpgoDt().substring(0, 10);
		jepum.setGiIpgoDt(dt);
		dt = jepum.getGiPanDt().substring(0, 10);
		jepum.setGiPanDt(dt);
		
		resultMap.put("jepum", jepum);
		
		return resultMap;
	}
}
