package com.tiger.service;

import java.util.List;

import com.tiger.dao.CodeGbDao;
import com.tiger.dao.LGroupCdDao;
import com.tiger.dao.MGroupCdDao;
import com.tiger.dao.PumjongDao;
import com.tiger.dao.TuserDao;
import com.tiger.vo.CodeGb;
import com.tiger.vo.LGroupCd;
import com.tiger.vo.MGroupCd;
import com.tiger.vo.Pumjong;

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
	
	private PumjongDao pumjongDao;	
	public void setPumjongDao(PumjongDao pumjongDao) {
		this.pumjongDao = pumjongDao;
	}

	@Override
	public void insert(String gubunCd, String gubunNm) {
		CodeGb codeGb = new CodeGb();		
		codeGb.setGubunCd(gubunCd);
		codeGb.setGubunNm(gubunNm);
		
		codeGbDao.insert(codeGb);	
	}

	@Override
	public String checkCode(String gubunCd) {
		CodeGb  codeGb = codeGbDao.selectOne (gubunCd);		
		String errorYN = "N";		
		if (codeGb != null) {
			errorYN = "Y";
		}
		return errorYN;
	}

	@Override
	public List<CodeGb> selectCodeGbList(CodeGb codeGb) {
		List<CodeGb> codeGbList = codeGbDao.selectList(codeGb);
		return codeGbList;
	}

	@Override
	public List<LGroupCd> selectLGroupCdList(String gubunCd) {
		
		List<LGroupCd> lGroupCdList = lGroupCdDao.selectList(gubunCd);
		
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

	@Override
	public List<MGroupCd> selectMGroupCdList(String gubunCd, String lGroupCd) {
		MGroupCd mGroupCd = new MGroupCd();
		
		mGroupCd.setGubunCd(gubunCd);
		mGroupCd.setlGroupCd(lGroupCd);
		
		List<MGroupCd> mGroupCdList = mGroupCdDao.selectList(mGroupCd);
		
		if (mGroupCdList.size() == 0) {
			mGroupCd.setmGroupCd("XXX");
			mGroupCd.setmGroupNm("No Data ... ^^");
			mGroupCd.setOrderBy("00");
			
			mGroupCdList.add(mGroupCd);
		}
		
		return mGroupCdList;
	}

	@Override
	public MGroupCd selectMGroupOne(MGroupCd mGroup) {
		MGroupCd mGroupCd = mGroupCdDao.selectOne(mGroup);
		return mGroupCd;
	}

	@Override
	public void insert(MGroupCd mGroup) {
		mGroupCdDao.insert(mGroup);		
	}

	@Override
	public List<Pumjong> selectPumjongList() {
		List<Pumjong> pumjongList = pumjongDao.selectList();
		return pumjongList;
	}

}
