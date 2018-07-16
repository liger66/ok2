package com.tiger.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.tiger.dao.JepumDao;
import com.tiger.dao.LGroupCdDao;
import com.tiger.dao.PumjongDao;
import com.tiger.vo.Jepum;
import com.tiger.vo.LGroupCd;
import com.tiger.vo.Pumjong;

public class JepumServiceImpl implements JepumService {	
	private LGroupCdDao lGroupCdDao;
	public void setLGroupCdDao(LGroupCdDao lGroupCdDao) {
		this.lGroupCdDao = lGroupCdDao;
	}
	
	private PumjongDao pumjongDao;	
	public void setPumjongDao(PumjongDao pumjongDao) {
		this.pumjongDao = pumjongDao;
	}
	
	private JepumDao jepumDao;	
	public void setJepumDao(JepumDao jepumDao) {
		this.jepumDao = jepumDao;
	}
	
	private CodeService  codeService;
	public void setCodeService (CodeService codeService) {
		this.codeService = codeService;
	}

	@Override
	public Map<String, Object> checkInData(Jepum jepum) {
		Map<String, Object>  resultMap = new HashMap<>();
		resultMap.put("errorYN", "Y");
		
		String iJepum = jepum.getJepum();
		System.out.println("jepum : " + iJepum);
		
		String iBrand = iJepum.substring(0,1);
		String iGiYY = iJepum.substring(1,2);
		String iSeason = iJepum.substring(2,3);
		String iPum = iJepum.substring(3,4);
		String iSeq = iJepum.substring(4,7);
		
		Jepum jep = new Jepum();
		jep.setJepum(iJepum);		
		Jepum jepum2 = jepumDao.selectOne(jep);
		
		if (jepum2 != null) {					
			resultMap.put("msg", "제품코드가 이미 존재 합니다.");
			System.out.println("error  jepum------------ ");
			return resultMap;
		}	
		
		LGroupCd lGroup = new LGroupCd();
		lGroup.setGubunCd("BRAND");
		lGroup.setlGroupCd(iBrand);		
		LGroupCd lGroupCd = lGroupCdDao.selectOne(lGroup);		
		
		if (lGroupCd == null) {
			resultMap.put("msg", "제품코드 처음 1자리는 브랜드코드를 입력 합니다.");	
			System.out.println("error  brand------------ ");
			return resultMap;
		} else { resultMap.put("brandNm", lGroupCd.getlGroupNm());	}
		
		lGroup.setGubunCd("GIYY");
		lGroup.setlGroupCd(iGiYY);		
		lGroupCd = lGroupCdDao.selectOne(lGroup);
		
		if (lGroupCd == null) {
			resultMap.put("msg", "제품코드 2번째 자리는 기획년도를 입력 합니다.");
			System.out.println("error  giyy------------ ");
			return resultMap;
		} else { resultMap.put("giYYNm", lGroupCd.getlGroupNm());	}
		
		lGroup.setGubunCd("SEASON");
		lGroup.setlGroupCd(iSeason);		
		lGroupCd = lGroupCdDao.selectOne(lGroup);		
		
		if (lGroupCd == null) {
			resultMap.put("msg", "제품코드 3번째 자리는  시즌코드를 입력 합니다.");
			System.out.println("error  season------------ ");
			return resultMap;
		}	else { resultMap.put("seasonNm", lGroupCd.getlGroupNm());	}
		
		Pumjong pum = new Pumjong();
		pum.setPumCd(iPum);		
		Pumjong pumjong = pumjongDao.selectOne(pum);
		
		if (pumjong == null) {					
			resultMap.put("msg", "제품코드 4번째 자리는  품종코드를 입력 합니다.");
			System.out.println("error  pumjong------------ ");
			return resultMap;
		} 	else { resultMap.put("pumNm", pumjong.getPumNm());	}
		/*
		if (iSeq < "000" || iSeq > "999") {
			resultMap.put("errorYN", "Y");
			resultMap.put("msg", "제품코드 마지막 3 자리는 숫자를 입력 합니다.");
			return resultMap;
		}	*/
		/*
		if (jepum.getGiIpgoDt() < jepum.getGiPanDt()) {
			resultMap.put("errorYN", "Y");
			resultMap.put("msg", "기획 판매일자와 입고일자를 확인 하세요.");
			return resultMap;
		} */
		
		// 미 입력 원가를 zero 셋팅
		int won = jepum.getGiWonjaje() + jepum.getGiBujaje() + jepum.getGiImbong();
		resultMap.put("giWon", won);
		
		if (won == 0) {
			resultMap.put("msg", "원가 항목에 입력 하세요.");
			System.out.println("error  won------------ ");
			return resultMap;
		}
		if (jepum.getGiSobi() == 0) {
			resultMap.put("msg", "소비자가를 입력 하세요.");
			System.out.println("error  sobi------------ ");
			return resultMap;
		}
		
		jepum.setBrand(iBrand);
		jepum.setGiYY(iGiYY);
		jepum.setSeason(iSeason);
		jepum.setPum(iPum);
		jepum.setWonjaje(jepum.getGiWonjaje());
		jepum.setBujaje(jepum.getGiBujaje());
		jepum.setImbong(jepum.getGiImbong());
		jepum.setSobi(jepum.getGiSobi());
		jepum.setFixDt("15-01-01");
		jepum.setfPanDt("15-01-01");
		jepum.setlPanDt("15-01-01");
		jepum.setfIpgoDt("15-01-01");
		jepum.setlIpgoDt("15-01-01");
		jepum.setImage("N");
		jepum.setWon(won);
		jepum.setGiQty(0);
		
		resultMap.put("errorYN", "N");
		resultMap.put("jepum", jepum);	

		return resultMap;
	}

	@Override
	public void insert(Jepum jepum) {
		jepumDao.insert(jepum);		
	}

	@Override
	public List<Jepum> selectList(Jepum jep) {
		List<Jepum>  jepumList = jepumDao.selectList(jep);
		return jepumList;
	}

	@Override
	public Model getSerchHead(Model model) {	
		
		List<LGroupCd> lGroupCdList = codeService.selectLGroupCdList("BRAND");	
		model.addAttribute("sBrandList", lGroupCdList);
		
		lGroupCdList = codeService.selectLGroupCdList("GIYY");
		model.addAttribute("sGiYYList", lGroupCdList);		
		
		lGroupCdList = codeService.selectLGroupCdList("SEASON");		
		model.addAttribute("sSeasonList", lGroupCdList);
		
		List<Pumjong> pumjongList = codeService.selectPumjongList();	
		model.addAttribute("sPumjongList", pumjongList);
		
		return model;
	}

	@Override
	public Model getMainHead(Model model) {
		
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
		
		return model;
	}

	@Override
	public Jepum getMainHead2(Jepum jepum) {
		Calendar cal = Calendar.getInstance();
		
		cal.add(Calendar.MONTH, 2);
		
		String yy = String.valueOf(cal.get(Calendar.YEAR));
		String mm = String.valueOf(cal.get(Calendar.MONTH));
		String dd = String.valueOf(cal.get(Calendar.DATE));		
		
		if (mm.length() == 1) { mm = "0" + mm; }
		if (dd.length() == 1) { dd = "0" + dd; }
				
		String ipgo = yy + "-" + mm + "-" + dd;	
		jepum.setGiIpgoDt(ipgo);
		
		cal.add(Calendar.DATE, 7);
		yy = String.valueOf(cal.get(Calendar.YEAR));
		mm = String.valueOf(cal.get(Calendar.MONTH));
		dd = String.valueOf(cal.get(Calendar.DATE));
		
		if (mm.length() == 1) { mm = "0" + mm; }
		if (dd.length() == 1) { dd = "0" + dd; }
		
		String pan = yy + "-" + mm + "-" + dd;
		jepum.setGiPanDt(pan);
		
		jepum.setGiBujaje(0);
		jepum.setGiWonjaje(0);
		jepum.setGiImbong(0);
		jepum.setGiSobi(0);
		
		return jepum;
	}

	@Override
	public Jepum selectOne(Jepum jep) {
		Jepum jepum = jepumDao.selectOne(jep);
		return jepum;
	}
	
}
