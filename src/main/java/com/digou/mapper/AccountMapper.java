package com.digou.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.digou.entity.*;

@Mapper
public interface AccountMapper {
	
	public CUser findCUser(String username);
	public void updateCUser(CUser account);
}
