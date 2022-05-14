package com.qxf.mall.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qxf.mall.entity.Addr;
import com.qxf.mall.entity.Result;
import com.qxf.mall.entity.User;
import com.qxf.mall.service.UserService;

/**
 * 用户控制层
 * 
 * @author dell
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 用户登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@GetMapping
	public Result login(String username, String password) {

		try {
			// 验证登录信息
			User loginUser = userService.login(username, password);
			return Result.success(loginUser);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error(e.getMessage());
		}

	}

	/**
	 * 注册
	 * 
	 * @param username
	 * @param password
	 * @param headPortrait
	 * @return
	 */
	@PostMapping
	public Result register(String username, String password, String headPortrait) {

		try {
			// 验证注册信息
			Integer register = userService.register(username, password, headPortrait);
			if (register > 0) {
				return Result.success(register);
			} else {
				return Result.error("注册失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error(e.getMessage());
		}

	}

	/**
	 * 	充值，
	 * 	根据用户id
	 * @param userId
	 * @param money
	 * @return
	 */
	@PutMapping
	public Result recharge(Integer userId, BigDecimal money) {

		// 业务层
		User user = userService.recharge(userId, money);

		// 结果
		if (user != null) {
			return Result.success(user);
		} else {
			return Result.error("充值失败！");
		}

	}
	
	/**
	 * 	根据用户id查，
	 * 	清空购物车后的重查用户信息
	 * @param username
	 * @param password
	 * @return
	 */
	@GetMapping("/seleById")
	public Result seleById(Integer id) {

		try {
			// 验证登录信息
			User user = userService.seleById(id);
			return Result.success(id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error(e.getMessage());
		}

	}
	
	
	

}
