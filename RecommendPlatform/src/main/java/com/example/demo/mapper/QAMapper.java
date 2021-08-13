package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.QAVO;

@Mapper
public interface QAMapper {
	@Insert("INSERT INTO qa_bbs VALUES(NULL, #{title}, #{id}, #{wdate}, #{contents}, NULL)")
	boolean insertQA(QAVO vo);
	
	@Select("SELECT * FROM qa_bbs")
	List<QAVO> getQAList();
}
