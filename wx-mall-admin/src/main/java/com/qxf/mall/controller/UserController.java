package com.qxf.mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.qxf.mall.entity.Result;
import com.qxf.mall.entity.User;
import com.qxf.mall.service.UserService;

/**
 * 	用户控制层
 * @author dell
 *
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 *  综合查询
	 * @param page
	 * @param limit
	 * @param name
	 * @return
	 */
	@GetMapping
	public Result get(Integer page, Integer limit, String username) {
		
		PageInfo list = userService.select(page, limit, username);
		
		// 判断是否查询成功
		if (list == null) {
			return Result.error("查询失败！");
		} else {
			return Result.success(list);
		}
		
	}
	
	/**
	 * 用户注册时间的统计
	 * @return
	 */
	@GetMapping("/echarts")
	public Result get() {
		
		List list = userService.selectTime();
		
		// 判断是否查询成功
		if (list == null) {
			return Result.error("查询失败！");
		} else {
			return Result.success(list);
		}
		
	}
	
	/**
	 * 统计一段时间内，用户注册时间
	 * @return
	 */
	@GetMapping("/echartsStart")
	public Result get(String start, String end) {
		
		List list = userService.selectStart(start, end);
		
		// 判断是否查询成功
		if (list == null) {
			return Result.error("查询失败！");
		} else {
			return Result.success(list);
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
