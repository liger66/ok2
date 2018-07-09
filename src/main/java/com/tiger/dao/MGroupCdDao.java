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
	public void insert(MGroupCd mGroupCd) {		
		session.insert("mGroupCd.insert", mGroupCd);
	}

	public List<MGroupCd> selectList(MGroupCd mGroupCd) {
		List<MGroupCd>  mGroupList = session.selectList("mGroupCd.selectList", mGroupCd);
		return mGroupList;
	}
	
	public MGroupCd selectOne(MGroupCd mGroup) {
		MGroupCd mGroupCd = session.selectOne("mGroupCd.selectOne", mGroup);
		return mGroupCd;
	}
}
