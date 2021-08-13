package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDAO;
import com.example.demo.vo.UserVO;

@Repository
public class UserService {
	@Autowired
	private UserDAO dao;
	
	public String loginSVC(String id, String pwd) {
		List<UserVO> list = dao.getUserIdandPwd();
		for(int i=0; i<list.size();i++) {
			if(list.get(i).getId().equals(id) && list.get(i).getPwd().equals(pwd)) {
				return list.get(i).getId();
			}
		}
		return null;
	}
	public int loginSVC2(String id) {
		List<UserVO> list = dao.getUserIdandUno();
		for(int i=0; i<list.size();i++) {
			if(list.get(i).getId().equals(id)) {
				return list.get(i).getUno();
			}
		}
		return 0;
	}
}
