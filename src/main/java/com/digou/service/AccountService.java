package com.digou.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.digou.entity.*;
import com.digou.mapper.*;
import com.digou.common.*;

@ComponentScan({"com.digou.mapper"})
@Service("accountService")
public class AccountService implements AccountIService {
	
    @Resource
    private AccountMapper accountMapper;
    
    @Override
	public Map<String, Object> logup(HttpServletResponse response, String username, String password) {
    	CUser account = accountMapper.findCUser(username);
    	if (account != null) {
    		//用户名已存在
    		return ResponseCommon.wrappedResponse(null, 102, null);
		}
    	CUser newAccount = new CUser();
    	newAccount.username = username;
    	newAccount.password = password;
    	newAccount.isOnline = true;
    	int result = accountMapper.insertCUser(newAccount);
    	if (result != 1) {
			return ResponseCommon.wrappedResponse(null, 106, null);
		}
    	int userID = newAccount.userID;
    	Map<String, Object> data = new HashMap<String, Object>();
    	data.put("userID", userID);
    	return ResponseCommon.wrappedResponse(data, 101, null);
    }
	
    @Override
	public Map<String, Object> loginCheck(HttpServletResponse response, String username, String password) {
		CUser account = accountMapper.findCUser(username);
		
		AccountCheckEnum code;
		if(account == null) {
			code = AccountCheckEnum.USERNAME_ERROR;
		} else if(!account.password.equals(password)) {
			code = AccountCheckEnum.PASSWORD_ERROR;
		} else if (account.isOnline) {
			code = AccountCheckEnum.IS_ONLINE;
		} else {
			account.isOnline = true;
			code = AccountCheckEnum.SUCCESS;
		}
		accountMapper.updateCUser(account);
		
		this.addCookie(response, username);
		
		ArrayList<String> conditionArr = new ArrayList<String>();
		conditionArr.add("nickname");
		conditionArr.add("userID");
		Map<String, Object> data = ResponseCommon.filter(account, conditionArr);
		return ResponseCommon.wrappedResponse(data, code.value(), null);
//		Map<String, Object> data = new HashMap<String, Object>();
//		data.put("nickname", account.nickname);
//		data.put("userID", account.userID);
//		Map<String, Object> resp = new HashMap<String, Object>();
//		resp.put("code", code.value());
//		resp.put("data", data);
//		return resp;
	}
    
    private void addCookie(HttpServletResponse response, String username) {
    	Cookie nameCookie = new Cookie("username",username);
    	nameCookie.setMaxAge(3600);
    	nameCookie.setPath("/");
    	Cookie timeCookie = new Cookie("lastAccessTime", System.currentTimeMillis()+"");
    	timeCookie.setMaxAge(3600);
    	timeCookie.setPath("/");
        response.addCookie(nameCookie);
        response.addCookie(timeCookie);
	}
}
