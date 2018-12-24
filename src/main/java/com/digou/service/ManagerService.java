package com.digou.service;

import com.digou.common.ResponseCommon;
import com.digou.entity.SellerUser;
import com.digou.mapper.ManagerMapper;
import com.digou.mapper.SellerAccountMapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.Id;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@ComponentScan({"com.digou.mapper"})
@Service("managerService")
public class ManagerService {
	
    @Resource
    private ManagerMapper managerMapper;

	public Map<String, Object> sellerPassInfo(HttpServletResponse response) {
        ArrayList<SellerUser> sellerUsers = managerMapper.sellerPassInfo();
        Map<String, Object> data = new HashMap<>();
        data.put("array", sellerUsers);
        return ResponseCommon.wrappedResponse(data, 101, null);
    }

	public Map<String, Object> sellerBlackInfo(HttpServletResponse response) {
        ArrayList<SellerUser> sellerUsers = managerMapper.sellerBlackInfo();
        Map<String, Object> data = new HashMap<>();
        data.put("array", sellerUsers);
        return ResponseCommon.wrappedResponse(data, 101, null);
    }

	public Map<String, Object> sellerBlackCancel(HttpServletResponse response, int id) {
        managerMapper.sellerBlackCancel(id);
        return ResponseCommon.wrappedResponse(null, 101, null);
    }

	public Map<String, Object> sellerWhiteInfo(HttpServletResponse response) {
        ArrayList<SellerUser> sellerUsers = managerMapper.sellerPassInfo();
        Map<String, Object> data = new HashMap<>();
        data.put("array", sellerUsers);
        return ResponseCommon.wrappedResponse(data, 101, null);
    }

	public Map<String, Object> sellerPassApprove(HttpServletResponse response, int id) {
        managerMapper.sellerUpdateApprove(id);
        return ResponseCommon.wrappedResponse(null, 101, null);
    }

	public Map<String, Object> sellerPassReject(HttpServletResponse response, int id) {
        managerMapper.sellerUpdateReject(id);
        return ResponseCommon.wrappedResponse(null, 101, null);
    }
/*
	public Map<String, Object> info_get(HttpServletResponse response, int id) {
		//检查
		SellerUser sellerUser = sellerAccountMapper.findUserById(id);
    	if (sellerUser == null) {
    		return ResponseCommon.wrappedResponse(null, 102, null);
		} else {
			Map<String, Object> data = new HashMap<>();
			data.put("data", sellerUser);
			return ResponseCommon.wrappedResponse(data, 101, data);
		}
    }

	public Map<String, Object> info_modify(HttpServletResponse response, int id,String url,String telephone,String shopName,String major,String description,String nickname) {
		//插入
		SellerUser user = new SellerUser(url, telephone, shopName, nickname, description, major, "");
		user.setId(id);
		sellerAccountMapper.modifyUser(user);
		return ResponseCommon.wrappedResponse(null, 101, null);
	}

	public Map<String, Object> login(HttpServletResponse response, String telephone, String password) {
		SellerUser user = sellerAccountMapper.findUser(telephone);
		if (user == null || !user.getPassword().equals(password)) {
			return ResponseCommon.wrappedResponse(null, 102, null);
		}
		Map<String, Object> data = new HashMap<>();
		data.put("data", user);
		return ResponseCommon.wrappedResponse(data, 101, null);
	}*/
}
