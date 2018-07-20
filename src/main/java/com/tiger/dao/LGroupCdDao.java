package com.tiger.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tiger.vo.LGroupCd;

public class LGroupCdDao {
	private SqlSession session;
	
	public void setSession (SqlSession session) {
		this.session = session;
	}

	public List<LGroupCd> selectList(String gubunCd) {
		
		List<LGroupCd>  lGroupCdList = session.selectList("lGroupCd.selectList", gubunCd);
		
		return lGroupCdList;
	}

	public void insert(LGroupCd lGroupCd) {
		session.insert("lGroupCd.insert", lGroupCd);
	}

	public LGroupCd selectOne(LGroupCd lGroup) {
		LGroupCd lGroupCd = session.selectOne("lGroupCd.selectOne", lGroup);
		return lGroupCd;
	}

	public List<LGroupCd> selectListOne(LGroupCd lgCd) {
		List<LGroupCd>  lGroupCdList = session.selectList("lGroupCd.selectOne", lgCd);
		return lGroupCdList;
	}
}
