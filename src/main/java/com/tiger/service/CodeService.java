package com.tiger.service;

import java.util.List;

import com.tiger.vo.CodeGb;
import com.tiger.vo.LGroupCd;

public interface CodeService {
	void insert(String gbCode, String gbName);
	void insert(LGroupCd lGroup);

	String checkCode(String gbCode);

	List<CodeGb> selectCodeGbList();

	List<LGroupCd> selectLGroupCdList(String gbCode);

	LGroupCd selectLGroupOne(LGroupCd lGroup);
	
}
