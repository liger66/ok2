package com.tiger.service;

import com.tiger.dao.CodeGbDao;
import com.tiger.dao.TuserDao;

public class CodeServiceImpl implements CodeService {
	private CodeGbDao codeGbDao;
	
	public void setTuserDao(CodeGbDao codeGbDao) {
		this.codeGbDao = codeGbDao;
	}

	@Override
	public void insert(String gbCode, String gbName) {
		
		
	}

}
