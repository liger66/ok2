package com.tiger.service;

import java.util.List;

import com.tiger.dao.CodeGbDao;
import com.tiger.dao.LGroupCdDao;
import com.tiger.dao.MGroupCdDao;
import com.tiger.dao.TuserDao;
import com.tiger.vo.CodeGb;
import com.tiger.vo.LGroupCd;

public class CodeServiceImpl implements CodeService {
	private CodeGbDao codeGbDao;	
	public void setCodeGbDao(CodeGbDao codeGbDao) {
		this.codeGbDao = codeGbDao;
	}
	
	private LGroupCdDao lGroupCdDao;	
	public void setLGroupCdDao(LGroupCdDao lGroupCdDao) {
		this.lGroupCdDao = lGroupCdDao;
	}
	
	private MGroupCdDao mGroupCdDao;	
	public void setMGroupCdDao(MGroupCdDao mGroupCdDao) {
		this.mGroupCdDao = mGroupCdDao;
	}

	@Override
	public void insert(String gbCode, String gbName) {
		CodeGb codeGb = new CodeGb();		
		codeGb.setGubunCd(gbCode);
		codeGb.setGubunNm(gbName);
		
		codeGbDao.insert(codeGb);		
	}

	@Override
	public String checkCode(String gubuncd) {
		CodeGb  codeGb = codeGbDao.selectOne (gubuncd);		
		String errorYN = "N";		
		if (codeGb != null) {
			errorYN = "Y";
		}
		return errorYN;
	}

	@Override
	public List<CodeGb> selectCodeGbList() {
		List<CodeGb> codeGbList = codeGbDao.selectList();
		return codeGbList;
	}

	@Override
	public List<LGroupCd> selectLGroupCdList(String gubuncd) {
		
		List<LGroupCd> lGroupCdList = lGroupCdDao.selectList(gubuncd);
		
		return lGroupCdList;
	}

	@Override
	public LGroupCd selectLGroupOne(LGroupCd lGroup) {
		LGroupCd lGroupCd = lGroupCdDao.selectOne(lGroup);
		return lGroupCd;
	}

	@Override
	public void insert(LGroupCd lGroup) {
		lGroupCdDao.insert(lGroup);
		
	}

}
