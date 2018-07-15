package com.tiger.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tiger.vo.Pumjong;

public class PumjongDao {
	private SqlSession session;	
	public void setSession (SqlSession session) {
		this.session = session;
	}
	
	public List<Pumjong> selectList() {
		
		List<Pumjong> pumjongList = session.selectList("pumjong.selectList");		
		return pumjongList;	
	}

	public Pumjong selectOne(Pumjong pum) {
		Pumjong pumjong = session.selectOne("pumjong.selectOne", pum);
		return pumjong;
	}	
}
