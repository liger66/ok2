package com.tiger.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tiger.vo.LGroupCd;
import com.tiger.vo.MGroupCd;

public class MGroupCdDao {
private SqlSession session;
	
	public void setSession (SqlSession session) {
		this.session = session;
	}

	public List<MGroupCd> selectList(String gbCode, String lGroupCd) {
		List<MGroupCd>  mGroupList = session.selectList("mGroupCd.selectList", gbCode);
		return mGroupList;
	}

	public void insert(MGroupCd mGroupCd) {
		session.insert("mGroupCd.insert",mGroupCd);
	}
}
