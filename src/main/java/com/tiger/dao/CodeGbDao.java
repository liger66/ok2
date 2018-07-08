package com.tiger.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tiger.vo.CodeGb;

public class CodeGbDao {
	private SqlSession session;
	
	public void setSession (SqlSession session) {
		this.session = session;
	}

	public CodeGb selectOne(String gbCode) {
		CodeGb codeGb = session.selectOne("codeGb.selectOne", gbCode);
		return codeGb;
	}

	public void insert(CodeGb codeGb) {
		session.insert("codeGb.insert",codeGb);		
	}

	public List<CodeGb> selectList() {
		List<CodeGb> codeGbList = session.selectList("codeGb.selectList");
		return codeGbList;
	}	
}
