package com.tiger.service;

import java.util.HashMap;
import java.util.Map;

import com.tiger.dao.TuserDao;
import com.tiger.vo.Tuser;

public class TuserServiceImpl implements TuserService {
	private TuserDao tuserDao;
	
	public void setTuserDao(TuserDao tuserDao) {
		this.tuserDao = tuserDao;
	}

	@Override
	public Map<String, Object> selectOne(String id, String pass) {
		System.out.println("cc----------------------------");
		Tuser tuser = tuserDao.selectOne(id);
		
		Map<String, Object>  resultMap = new HashMap<>();
		
		if (tuser == null ) {
			System.out.println("11----------------------------");
			resultMap.put("errorYN", "Y");
			resultMap.put("msg", "존재하지 않는 사용자 입니다.");
			return resultMap;
		}
		
		if (!tuser.getPass().equals(pass) ) {
			System.out.println("12----------------------------");
			resultMap.put("errorYN", "Y");
			resultMap.put("msg", "패스워드가 일치하지 않습니다.");
			return resultMap;
		}
		
		if (tuser.getOutYN().equals("Y") ) {
			System.out.println("13----------------------------");
			resultMap.put("errorYN", "Y");
			resultMap.put("msg", "유효하지 않은 사용자입니다.(퇴사)");
			return resultMap;
		}
		System.out.println("14----------------------------");
		resultMap.put("errorYN", "N");
		resultMap.put("tuser", tuser);
		
		return resultMap;
	}
	
}
