package com.tiger.service;

import java.util.Map;

import com.tiger.vo.Tuser;

public interface TuserService {
	Map<String, Object> selectOne (String id, String pass);
	
}
