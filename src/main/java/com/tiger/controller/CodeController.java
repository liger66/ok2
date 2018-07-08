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
import com.tiger.service.TuserService;
import com.tiger.vo.CodeGb;
import com.tiger.vo.LGroupCd;
import com.tiger.vo.Tuser;

@Controller
public class CodeController {
	private CodeService  codeService;
	
	public void setCodeService (CodeService codeService) {
		this.codeService = codeService;
	}	
	
	@ResponseBody
	@RequestMapping(value="/code/checkcode", method=RequestMethod.POST)
	public String main(@RequestParam(required=false) String gbCode) {
		String errorYN = codeService.checkCode(gbCode);
		
		return errorYN;
	}
	
	@RequestMapping(value="/code/insertGb", method=RequestMethod.POST)
	public String insertGb(@RequestParam(required=false) String gbCode, 
						  @RequestParam(required=false) String gbName) {
		codeService.insert(gbCode, gbName);
		
		return "redirect:/code/gbList";
	}
	
	@RequestMapping(value="/code/gbList")
	public String gbList(Model model) {
		
		List<CodeGb>  codeGbList = codeService.selectCodeGbList();
		model.addAttribute("codeGbList", codeGbList);
				
		System.out.println("list length : " + codeGbList.size());
		
		return "/code.jsp";
	}
	
	@ResponseBody
	@RequestMapping(value="/code/lGroupList", method=RequestMethod.POST)
	public List<LGroupCd> lGroupList(@RequestParam(required=false) String gubuncd, Model model) {
		
		List<LGroupCd>  lGroupCdList = codeService.selectLGroupCdList(gubuncd);
		
		model.addAttribute("lGroupCdList", lGroupCdList);
				
		System.out.println("lGroupCdList size : " + lGroupCdList.size());
		
		return lGroupCdList;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/code/insertLGroup", method=RequestMethod.POST)
	public String insertLGroup(@RequestParam(required=false) String gubuncd, 
							   @RequestParam(required=false) String lGroupCd,
							   @RequestParam(required=false) String lGroupNm,
							   @RequestParam(required=false) String lOrderBy,
							   HttpServletRequest request, HttpServletResponse response) {
		System.out.println("insertLGroup  --------- in ");
		Tuser tuser = (Tuser) request.getSession().getAttribute("tuser");		
		String userCd = tuser.getUserCd();
		
		LGroupCd  lGroup = new LGroupCd();
		
		lGroup.setGubunCd(gubuncd);
		lGroup.setlGroupCd(lGroupCd);
		lGroup.setlGroupNm(lGroupNm);
		lGroup.setOrderBy(lOrderBy);
		lGroup.setInUser(userCd);
		lGroup.setUseYN("Y");
		lGroup.setMemo("");
		
		LGroupCd  lGroupOne = codeService.selectLGroupOne(lGroup);
		
		if (lGroupOne == null) {
			System.out.println("---------------------------------- null");
			codeService.insert(lGroup);
			return "redirect:/code/gbList";
		} else {
			System.out.println("not null : " + lGroupOne.getGubunCd() + " -- " + lGroupOne.getlGroupCd());
			return "Y";
		}
		
	}
}
