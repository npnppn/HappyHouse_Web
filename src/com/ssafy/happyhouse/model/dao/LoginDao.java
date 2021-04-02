package com.ssafy.happyhouse.model.dao;

import java.sql.SQLException;

import com.ssafy.happyhouse.model.MemberDto;

public interface LoginDao {

	public MemberDto login(String userid, String userpwd) throws SQLException;
	
}
