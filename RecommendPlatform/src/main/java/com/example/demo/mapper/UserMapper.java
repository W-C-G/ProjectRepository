package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.UserVO;

@Mapper
public interface UserMapper {
	
	@Insert("INSERT INTO user VALUES(NULL, #{id}, #{pwd}, #{uname}, #{phone}, #{email})")
	boolean insertUser(UserVO vo);
	
	@Insert("INSERT INTO user VALUES(NULL, #{id}, #{pwd}, #{uname}, #{phone}, #{email})")
	@Options(useGeneratedKeys = true, keyProperty = "uno")
	int insertAndGetNum(UserVO vo);
	
	@Select("SELECT * FROM user WHERE num = #{num}")
	UserVO getUserByNum(int num);
	
	@Select("SELECT * FROM user WHERE id = #{id}")
	UserVO getUserById(String id);
	
	@Select("SELECT id, pwd FROM user")
	List<UserVO> getUserIdandPwd();
	
	@Select("SELECT id, uno FROM user")
	List<UserVO> getUserIdandUno();
}
