package com.qxf.mall.entity;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
	
	// id
	private Integer id;
	
	// 用户名
	private String username;
		
	// 密码
	private String password;
	
	// 余额
	private BigDecimal money;
	
	// 头像
	private String headPortrait;
	
	// 注册时间
	private String regtime;
	
	// 购物车id
	private Integer cartId;
	
	
	
	
	
	
	
	
	
	

}
