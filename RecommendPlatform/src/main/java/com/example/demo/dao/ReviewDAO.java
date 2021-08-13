package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.vo.ReviewVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Repository
public class ReviewDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Transactional
	public List<ReviewVO> getTopTenReviewList(){
		String sql = "SELECT * FROM review ORDER BY res_score DESC LIMIT 10";
		List<ReviewVO> list = jdbcTemplate.query(sql,  (rs, i) -> new ReviewVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8)));
		return list;
	}
	
	@Transactional
	public List<ReviewVO> getAllList(){
		String sql = "SELECT * FROM review";
		List<ReviewVO> list = jdbcTemplate.query(sql,  (rs, i) -> new ReviewVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8)));
		return list;
	}
	
	@Transactional
	public List<ReviewVO> getListByName(String text){
		String sql = "SELECT * FROM review WHERE res_name LIKE '%"+text+" %'";
		List<ReviewVO> list = jdbcTemplate.query(sql,  (rs, i) -> new ReviewVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8)));
		return list;
	}
}
