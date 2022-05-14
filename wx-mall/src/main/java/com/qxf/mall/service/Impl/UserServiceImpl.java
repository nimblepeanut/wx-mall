package com.qxf.mall.service.Impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.qxf.mall.entity.User;
import com.qxf.mall.mapper.UserMapper;
import com.qxf.mall.service.UserService;
import com.qxf.mall.util.MD5Util;

/**
 * 用户业务层实现类
 * 
 * @author dell
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	/**
	 * 登录
	 * 
	 * @throws Exception
	 */
	@Override
	public User login(String username, String password) throws Exception {

		// 1．验证账号和密码格式是否正确
		// 账号和密码长度大于3小于16

		// 账号
		if (username.length() < 3) {
			throw new Exception("账号长度小于3位！");
		}

		if (username.length() > 16) {
			throw new Exception("账号长度大于16位！");
		}

		// 密码
		if (password.length() < 3) {
			throw new Exception("密码长度小于3位！");
		}

		if (password.length() > 16) {
			throw new Exception("密码长度大于16位！");
		}

		// 2. 验证账号是否存在
		// sUser是数据库中查到的
		User sUser = userMapper.selectLogin(username);

		if (sUser == null) {
			throw new Exception("用户名不存在！");
		}

		// 3. 密码是否正确
		// 因为管理员密码在存入数据库时进行了MD5加密，
		// 所以登录时输入过来的密码也要MD5加密
		if (!sUser.getPassword().equals(MD5Util.getMD5(password))) {
			throw new Exception("密码错误！");
		}

		// 4. 返回结果
		return sUser;

	}

	/**
	 * 注册
	 * 
	 * @throws Exception
	 */
	@Override
	public Integer register(String username, String password, String headPortrait) throws Exception {

		// 1．验证账号和密码格式是否正确
		// 账号和密码长度大于3小于16

		// 账号
		if (username.length() < 3) {
			throw new Exception("账号长度小于3位！");
		}

		if (username.length() > 16) {
			throw new Exception("账号长度大于16位！");
		}

		// 密码
		if (password.length() < 3) {
			throw new Exception("密码长度小于3位！");
		}

		if (password.length() > 16) {
			throw new Exception("密码长度大于16位！");
		}

		// 2. 密码进行MD5加密
		password = MD5Util.getMD5(password);

		// 3. 返回结果
		return userMapper.register(username, password, headPortrait);
	}

	/**
	 * 	充值， 根据用户id
	 * 
	 * 	隔离级别：串行化
	 */
	@Override
	// 数据库事物的隔离级别调到串行化，
	// 使用表级共享锁：sql语句执行一句就加一个锁，
	// 报错就回滚
	@Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class)
	public User recharge(Integer userId, BigDecimal money) {

		//修改余额
		userMapper.recharge(userId, money);
		
		// 重新查询用户
		return userMapper.selectById(userId);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
