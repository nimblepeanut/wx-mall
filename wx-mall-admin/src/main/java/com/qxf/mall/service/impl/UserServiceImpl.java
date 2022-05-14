package com.qxf.mall.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qxf.mall.entity.User;
import com.qxf.mall.mapper.UserMapper;
import com.qxf.mall.service.UserService;

/**
 * 	用户业务层接口
 * @author dell
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 	综合查询
	 */
	@Override
	public PageInfo select(Integer page, Integer limit, String username) {
		
		if (page == null) {
			page = 1;
		}
		
		if (limit == null) {
			limit = 10;
		}
		
		// 开启分页
		PageHelper.startPage(page, limit);
		List list = userMapper.select(username);
		PageInfo pageInfo = new PageInfo<>(list);
		
		return pageInfo;
	}

	/**
	 * 用户注册时间的统计
	 */
	@Override
	public List selectTime() {
		return userMapper.selectTime();
	}

	/**
	 * 统计一段时间内，用户注册时间
	 */
	@Override
	public List selectStart(String start, String end) {
		return userMapper.selectStart(start, end);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
