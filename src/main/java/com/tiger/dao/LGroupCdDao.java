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
		System.out.println("dao insert------------- : " + lGroupCd.getlGroupCd());
		session.insert("lGroupCd.insert", lGroupCd);
	}

	public LGroupCd selectOne(LGroupCd lGroup) {
		System.out.println("dao selectOne ------------- : " + lGroup.getlGroupCd());
		LGroupCd lGroupCd = session.selectOne("lGroupCd.selectOne", lGroup);
		return lGroupCd;
	}
}
