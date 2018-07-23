package com.tiger.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tiger.dao.JepumColorDao;
import com.tiger.dao.JepumDao;
import com.tiger.dao.JepumSizDao;
import com.tiger.dao.LGroupCdDao;
import com.tiger.dao.PumjongDao;
import com.tiger.dao.SengJisiDao;
import com.tiger.vo.Jepum;
import com.tiger.vo.MGroupCd;

public class SengJiServiceImpl  implements SengJiService {
	private LGroupCdDao lGroupCdDao;
	public void setLGroupCdDao(LGroupCdDao lGroupCdDao) {
		this.lGroupCdDao = lGroupCdDao;
	}
	
	private JepumDao jepumDao;	
	public void setJepumDao(JepumDao jepumDao) {
		this.jepumDao = jepumDao;
	}
	
	private JepumColorDao jepumColorDao;	
	public void setJepumColorDao(JepumColorDao jepumColorDao) {
		this.jepumColorDao = jepumColorDao;
	}
	
	private JepumSizDao jepumSizDao;	
	public void setJepumSizDao(JepumSizDao jepumSizDao) {
		this.jepumSizDao = jepumSizDao;
	}
	
	private SengJisiDao sengJisiDao;	
	public void setSengJisiDao(SengJisiDao sengJisiDao) {
		this.sengJisiDao = sengJisiDao;
	}
	
	private CodeService  codeService;
	public void setCodeService (CodeService codeService) {
		this.codeService = codeService;
	}
	
	private JepumService  jepumService;
	public void setJepumService (JepumService sengJiService) {
		this.jepumService = jepumService;
	}
	@Override
	public Map<String, Object> jepumIn(String jep) {
		Map<String, Object>  resultMap = new HashMap<>();
		Jepum jepum = new Jepum();
		jepum.setJepum(jep);
		
		Jepum  jepum2 = jepumDao.selectOne(jepum);
		if (jepum2 == null) {
			resultMap.put("errorYN", "Y");			
			resultMap.put("msg", "제품코드 정보가 앖습니다.");	
			return resultMap;
		}
		resultMap = jepumService.getJepumDetl(jepum2);
		resultMap.put("jepum", jepum2);
		
		List<MGroupCd> mGroupCdList =  codeService.selectMGroupCdList("SIZ", jepum2.getSizGroup());
		resultMap.put("sizList", mGroupCdList);
		resultMap.put("errorYN", "N");
		
		return resultMap;
	}
}
