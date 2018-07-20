package com.tiger.dao;

import org.apache.ibatis.session.SqlSession;

public class JepumSizDao {
	private SqlSession session;	
	public void setSession (SqlSession session) {
		this.session = session;
	}
}
