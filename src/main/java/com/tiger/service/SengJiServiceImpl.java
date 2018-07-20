package com.tiger.service;

import com.tiger.dao.JepumColorDao;
import com.tiger.dao.JepumDao;
import com.tiger.dao.JepumSizDao;
import com.tiger.dao.LGroupCdDao;
import com.tiger.dao.PumjongDao;
import com.tiger.dao.SengJisiDao;

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
}
