package com.qxf.mall.service;

import java.math.BigDecimal;

import com.qxf.mall.entity.User;

public interface UserService {
	
	/**
	 * 登录
	 */
	User login(String nameuser, String password) throws Exception;

	/**
	 * 	注册
	 * @param username
	 * @param password
	 * @param headPortrait
	 * @return
	 * @throws Exception 
	 */
	Integer register(String username, String password, String headPortrait) throws Exception;

	/**
	 * 	充值，
	 * 	根据用户id
	 * @param userId
	 * @param money
	 * @return
	 */
	User recharge(Integer userId, BigDecimal money);

	/**
	 * 	根据用户id查，
	 * 	清空购物车后的重查用户信息
	 * @param id
	 * @return
	 */
	User seleById(Integer id);
	
	
	
	
	
	
	
	
	
	

}
