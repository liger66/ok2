package com.tiger.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.tiger.vo.CodeGb;

public class CodeGbDao {
	private SqlSession session;
	
	public void setSession (SqlSession session) {
		this.session = session;
	}

	public CodeGb selectOne(String gubunCd) {
		CodeGb codeGb = session.selectOne("codeGb.selectOne", gubunCd);
		return codeGb;
	}

	public void insert(CodeGb codeGb) {
		session.insert("codeGb.insert", codeGb);
	}

	public List<CodeGb> selectList(CodeGb codeGb) {
		List<CodeGb> codeGbList = session.selectList("codeGb.selectList", codeGb);
		return codeGbList;
	}	
}
