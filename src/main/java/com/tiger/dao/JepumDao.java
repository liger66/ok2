package com.tiger.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tiger.vo.Jepum;

public class JepumDao {
	private SqlSession session;	
	public void setSession (SqlSession session) {
		this.session = session;
	}
	
	public List<Jepum> selectList(Jepum jep) {
		List<Jepum> jepumList = session.selectList("jepum.selectList", jep);
		return jepumList;
	}

	public Jepum selectOne(Jepum jep) {
		Jepum jepum = session.selectOne("jepum.selectOne", jep);
		return jepum;
	}

	public void insert(Jepum jepum) {
		session.insert("jepum.insert", jepum);		
	}	
}
