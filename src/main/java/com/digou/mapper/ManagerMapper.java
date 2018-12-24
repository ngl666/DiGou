package com.digou.mapper;

import com.digou.entity.SellerUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface ManagerMapper {
	
	ArrayList<SellerUser> sellerPassInfo();
	ArrayList<SellerUser> sellerBlackInfo();
	void sellerBlackCancel(int id);
	void sellerUpdateApprove(int id);
	void sellerUpdateReject(int id);
//	int insertUser(SellerUser user);
//	SellerUser findUserById(int id);
//	void modifyUser(SellerUser user);
//	public CUser findCUserByID(int userID);
//	public void updateCUser(CUser account);
//	public int insertCUser(CUser account);
	
}
