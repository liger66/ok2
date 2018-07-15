package com.tiger.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.tiger.vo.Jepum;

public interface JepumService {

	Map<String, Object> checkInData(Jepum jepum);

	void insert(Jepum jepum);

	List<Jepum> selectList(Jepum jep);

	Model getSerchHead(Model model);

	Model getMainHead(Model model);

	Jepum getMainHead2(Jepum jepum);

	Jepum selectOne(Jepum jep);
	
}
