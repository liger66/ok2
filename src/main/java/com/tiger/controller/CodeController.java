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
import com.tiger.vo.MGroupCd;
import com.tiger.vo.Tuser;

@Controller
public class CodeController {
	private CodeService  codeService;
	
	public void setCodeService (CodeService codeService) {
		this.codeService = codeService;
	}	
	
	@ResponseBody
	@RequestMapping(value="/code/checkcode", method=RequestMethod.POST)
	public String main(@RequestParam(required=false) String gubunCd) {
		String errorYN = codeService.checkCode(gubunCd);
		
		return errorYN;
	}
	
	@RequestMapping(value="/code/insertGb", method=RequestMethod.POST)
	public String insertGb(@RequestParam(required=false) String gubunCd, 
						   @RequestParam(required=false) String gubunNm) {
		gubunCd = gubunCd.toUpperCase();	
		codeService.insert(gubunCd, gubunNm);
		
		return "redirect:/code/gbList";
	}
	
	@RequestMapping(value="/code/gbList", method=RequestMethod.GET)
	public String gbList(@RequestParam(required=false) String gubunCd, 
			   			 @RequestParam(required=false) String gubunNm, Model model) {
		CodeGb codeGb = new CodeGb();
		codeGb.setGubunCd(gubunCd);
		codeGb.setGubunNm(gubunNm);
		
		List<CodeGb>  codeGbList = codeService.selectCodeGbList(codeGb);
		model.addAttribute("codeGbList", codeGbList);
				
		System.out.println("list length : " + codeGbList.size());
		
		return "/code.jsp";
	}
	
	@ResponseBody
	@RequestMapping(value="/code/lGroupList", method=RequestMethod.POST)
	public List<LGroupCd> lGroupList(@RequestParam(required=false) String gubunCd, Model model) {
		
		List<LGroupCd>  lGroupCdList = codeService.selectLGroupCdList(gubunCd);
		
		model.addAttribute("lGroupCdList", lGroupCdList);
		
		return lGroupCdList;		
	}
	
	@ResponseBody
	@RequestMapping(value="/code/mGroupList", method=RequestMethod.POST)
	public List<MGroupCd> mGroupList(@RequestParam(required=false) String gubunCd,
			@RequestParam(required=false) String lGroupCd, Model model) {
		
		List<MGroupCd>  mGroupCdList = codeService.selectMGroupCdList(gubunCd, lGroupCd);
		
		model.addAttribute("mGroupCdList", mGroupCdList);
		
		return mGroupCdList;		
	}
	
	@ResponseBody
	@RequestMapping(value="/code/insertLGroup", method=RequestMethod.POST)
	public String insertLGroup(@RequestParam(required=false) String gubunCd, 
							   @RequestParam(required=false) String lGroupCd,
							   @RequestParam(required=false) String lGroupNm,
							   @RequestParam(required=false) String lOrderBy,
							   HttpServletRequest request, HttpServletResponse response) {
		
		Tuser tuser = (Tuser) request.getSession().getAttribute("tuser");
		if (tuser == null) {
			System.out.println("no login ----------->");
			return "L";
		}
		String userCd = tuser.getUserCd();
		lGroupCd = lGroupCd.toUpperCase();
		
		LGroupCd  lGroup = new LGroupCd();
		
		lGroup.setGubunCd(gubunCd);
		lGroup.setlGroupCd(lGroupCd);
		lGroup.setlGroupNm(lGroupNm);
		lGroup.setOrderBy(lOrderBy);
		lGroup.setInUser(userCd);
		lGroup.setUseYN("Y");
		lGroup.setMemo("");
		
		LGroupCd  lGroupOne = codeService.selectLGroupOne(lGroup);
		
		if (lGroupOne == null) {
			codeService.insert(lGroup);
			return "N";
		} else {
			System.out.println("not null : " + lGroupOne.getGubunCd() + " -- " + lGroupOne.getlGroupCd());
			return "Y";
		}		
	}
	
	@ResponseBody
	@RequestMapping(value="/code/insertMGroup", method=RequestMethod.POST)
	public String insertMGroup(@RequestParam(required=false) String gubunCd, 
							   @RequestParam(required=false) String lGroupCd,
							   @RequestParam(required=false) String mGroupCd,
							   @RequestParam(required=false) String mGroupNm,
							   @RequestParam(required=false) String mOrderBy,
							   HttpServletRequest request, HttpServletResponse response) {
		
		Tuser tuser = (Tuser) request.getSession().getAttribute("tuser");
		if (tuser == null) {
			System.out.println("no login ----------->");
			return "L";
		}
		String userCd = tuser.getUserCd();
		mGroupCd = mGroupCd.toUpperCase();
		
		MGroupCd  mGroup = new MGroupCd();
		
		mGroup.setGubunCd(gubunCd);
		mGroup.setlGroupCd(lGroupCd);
		mGroup.setmGroupCd(mGroupCd);
		mGroup.setmGroupNm(mGroupNm);
		mGroup.setOrderBy(mOrderBy);
		mGroup.setInUser(userCd);
		mGroup.setUseYN("Y");
		mGroup.setMemo("");
		
		MGroupCd  mGroupOne = codeService.selectMGroupOne(mGroup);
		
		if (mGroupOne == null) {
			System.out.println("---- null --> insert");
			codeService.insert(mGroup);
			return "redirect:/code/gbList";
		} else {
			System.out.println("not null : " + mGroupOne.getGubunCd() + " -- " + mGroupOne.getlGroupCd());
			return "Y";
		}
		
	}
}
