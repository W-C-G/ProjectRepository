package com.example.demo.dao;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.vo.FileVO;

@Repository
public class FileDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Transactional
	public boolean uploadFile(String fileName, long fileSize, String filedate, int uno) {
		String sql = "INSERT INTO file(filename, filesize, uno) VALUES(?, ?, ?);";
		int rows = jdbcTemplate.update(sql, fileName, fileSize, uno);		
		if(rows != 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Transactional
	public List<FileVO> getFileList(int uno) {
		String sql = "SELECT fileNum, fileName, fileSize, uno FROM file where uno = " + uno;	
		List<FileVO> list = jdbcTemplate.query(sql, (rs, i)->new FileVO(rs.getInt(1), rs.getString(2), rs.getLong(3), rs.getInt(4)));
		
		return list;
	}
}
