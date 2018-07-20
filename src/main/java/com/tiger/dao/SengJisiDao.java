package com.tiger.dao;

import org.apache.ibatis.session.SqlSession;

public class SengJisiDao {
	private SqlSession session;	
	public void setSession (SqlSession session) {
		this.session = session;
	}
}
