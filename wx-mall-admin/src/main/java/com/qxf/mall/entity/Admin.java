package com.qxf.mall.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 管理员
 * @author 秦晓枫
 *
 */

@Getter
@Setter
public class Admin {
	
	// id
	private Integer id;
	
	// 用户名
	private String username;
	
	// 密码
	private String password;
	
	// 头像
	private String headPortrait;

}
