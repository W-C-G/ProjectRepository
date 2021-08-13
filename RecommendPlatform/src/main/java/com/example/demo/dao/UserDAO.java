package com.example.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.mapper.UserMapper;
import com.example.demo.vo.UserVO;

@Repository
//외부 I/O 처리하는 경우: @Repository
public class UserDAO {
	@Autowired
	private UserMapper userMapper;
	
	public boolean insertUser(UserVO vo) {
		return userMapper.insertUser(vo);
	}
	
	public int insertAndGetNum(UserVO vo) {
		return userMapper.insertAndGetNum(vo);
	}

	public UserVO getUserByNum(int num) {
		return userMapper.getUserByNum(num);
	}
	
	public UserVO getUserById(String id) {
		return userMapper.getUserById(id);
	}
	
	public List<UserVO> getUserIdandPwd(){
		return userMapper.getUserIdandPwd();
	}
	
	public List<UserVO> getUserIdandUno(){
		return userMapper.getUserIdandUno();
	}
	
}
