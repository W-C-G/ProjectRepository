package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.ReviewVO;

@Mapper
public interface ReviewMapper {
	@Select("SELECT * FROM review")
	List<ReviewVO> getReviewList();
	
	@Select("SELECT * FROM review WHERE res_name LIKE #{text}")
	List<ReviewVO> getListByName(String text);
	
	@Select("SELECT * FROM review WHERE uno = #{uno} ORDER BY res_score DESC")
	List<ReviewVO> getListByUno(int uno);
	
}
