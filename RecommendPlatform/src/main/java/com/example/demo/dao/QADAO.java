package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.mapper.QAMapper;
import com.example.demo.vo.QAVO;

@Repository
public class QADAO {
	@Autowired
	private QAMapper qamapper;
	
	public boolean insertQA(QAVO vo) {
		return qamapper.insertQA(vo);
	};
	
}
