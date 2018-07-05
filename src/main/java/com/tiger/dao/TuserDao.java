package com.tiger.dao;

import org.apache.ibatis.session.SqlSession;

import com.tiger.vo.Tuser;

public class TuserDao {
	private SqlSession session;
	
	public void setSession (SqlSession session) {
		this.session = session;
	}

	public Tuser selectOne(String id) {	
		
		Tuser tuser = session.selectOne("tuser.selectOne", id);
		
		return tuser;
	}
	
}
