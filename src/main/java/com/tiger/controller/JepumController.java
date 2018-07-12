package com.tiger.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tiger.service.CodeService;
import com.tiger.service.JepumService;
import com.tiger.vo.Jepum;
import com.tiger.vo.LGroupCd;

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
	
	@RequestMapping(value="/jepum/mnew")
	public String mnew(@ModelAttribute Jepum jepum, Model model) throws ParseException {
		model.addAttribute("mnew", true);
		List<LGroupCd> lGroupCdList = codeService.selectLGroupCdList("JEPUMG");		
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
				
		Calendar cal = Calendar.getInstance();
		
		cal.add(Calendar.MONTH, 2);
		
		String yy = String.valueOf(cal.get(Calendar.YEAR));
		String mm = String.valueOf(cal.get(Calendar.MONTH));
		String dd = String.valueOf(cal.get(Calendar.DATE));		
		
		if (mm.length() == 1) { mm = "0" + mm; }
				
		String ipgo = yy + "-" + mm + "-" + dd;		
		jepum.setGiIpgoDt(ipgo);
		
		cal.add(Calendar.DATE, 7);
		yy = String.valueOf(cal.get(Calendar.YEAR));
		mm = String.valueOf(cal.get(Calendar.MONTH));
		dd = String.valueOf(cal.get(Calendar.DATE));
		
		String pan = yy + "-" + mm + "-" + dd;
		jepum.setGiPanDt(pan);
		
		//jepum.setGiPanDt(cal.get(Calendar.YEAR)+"-"+cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.DATE));
		
		return "/jepum.jsp";
	}
}
