package com.tiger.service;

import java.util.List;

import com.tiger.vo.CodeGb;
import com.tiger.vo.LGroupCd;
import com.tiger.vo.MGroupCd;
import com.tiger.vo.Pumjong;

public interface CodeService {
	void insert(String gubunCd, String gubunNm);
	void insert(LGroupCd lGroup);
	void insert(MGroupCd mGroup);

	String checkCode(String gubunCd);

	List<CodeGb> selectCodeGbList(CodeGb codeGb);

	List<LGroupCd> selectLGroupCdList(String gubunCd);

	LGroupCd selectLGroupOne(LGroupCd lGroup);
	
	List<MGroupCd> selectMGroupCdList(String gubunCd, String lGroupCd);
	
	MGroupCd selectMGroupOne(MGroupCd mGroup);
	
	List<Pumjong> selectPumjongList();
	Pumjong selectPumjongOne(Pumjong pum);
}
