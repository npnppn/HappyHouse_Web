package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.MemberDto;

public interface LoginService {

	public MemberDto login(String userid, String userpwd) throws Exception;
	
}
