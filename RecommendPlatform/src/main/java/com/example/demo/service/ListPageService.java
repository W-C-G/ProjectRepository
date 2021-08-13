package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.mapper.QAMapper;
import com.example.demo.mapper.ReviewMapper;
import com.example.demo.vo.QAVO;
import com.example.demo.vo.ReviewVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Repository
public class ListPageService {
	@Autowired
	private ReviewMapper rmapper;
	
	@Autowired
	private QAMapper qamapper;
	
	public PageInfo<ReviewVO> getListPage(int pageNum, int pageSize){
		PageHelper.startPage(pageNum, pageSize);
		// pageInfo 자료구조에서 mapper 인터페이스를 통해 얻은 결과를 파라미터로 넣어줘야 한다.
		PageInfo<ReviewVO> pageInfo = new PageInfo<>(rmapper.getReviewList());
		return pageInfo;
	}
	
	public PageInfo<ReviewVO> searchListPageByName(int pageNum, int pageSize, String text){
		PageHelper.startPage(pageNum, pageSize);
		PageInfo<ReviewVO> pageInfo = new PageInfo<>(rmapper.getListByName(text));
		return pageInfo;
	}
	
	public PageInfo<ReviewVO> getMyListPage(int pageNum, int pageSize, int uno){
		PageHelper.startPage(pageNum, pageSize);
		// pageInfo 자료구조에서 mapper 인터페이스를 통해 얻은 결과를 파라미터로 넣어줘야 한다.
		PageInfo<ReviewVO> pageInfo = new PageInfo<>(rmapper.getListByUno(uno));
		return pageInfo;
	}
	
	public PageInfo<QAVO> getQAListPage(int pageNum, int pageSize){
		PageHelper.startPage(pageNum, pageSize);
		// pageInfo 자료구조에서 mapper 인터페이스를 통해 얻은 결과를 파라미터로 넣어줘야 한다.
		PageInfo<QAVO> pageInfo = new PageInfo<>(qamapper.getQAList());
		return pageInfo;
	}
}
