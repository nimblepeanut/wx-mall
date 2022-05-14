package com.qxf.mall.service;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.qxf.mall.entity.User;

/**
 * 	用户的业务层接口
 * @author dell
 *
 */
public interface UserService {

	/**
	 * 综合查询
	 * @param page
	 * @param limit
	 * @param name
	 * @return
	 */
	PageInfo select(Integer page, Integer limit, String username);
	
	/**
	 * 用户注册时间的统计
	 * @return
	 */
	List selectTime();

	/**
	 * 统计一段时间内，用户注册时间
	 * @param start
	 * @param end
	 * @return
	 */
	List selectStart(String start, String end);
	
	

}
