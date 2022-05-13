package com.qxf.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qxf.mall.entity.Admin;
import com.qxf.mall.entity.Category;
import com.qxf.mall.mapper.AdminMapper;
import com.qxf.mall.service.AdminService;
import com.qxf.mall.util.MD5Util;

/**
 *	管理员业务层实现类
 * @author dell
 *
 */
@Service
public class AdminServiceImpl implements AdminService {

	// 注入Mapper层
	@Autowired
	private AdminMapper adminMapper;
	
	/**
	 *	登录
	 */
	@Override
	public Admin login(Admin admin) throws Exception {

		// 1．验证账号和密码格式是否正确
		//	账号和密码长度大于3小于16
		
		// 账号
		if (admin.getUsername().length() < 3) {
			throw new Exception("账号长度小于3位！");
		}
		
		if (admin.getUsername().length() > 16) {
			throw new Exception("账号长度大于16位！");
		}
		
		// 密码
		if (admin.getPassword().length() < 3) {
			throw new Exception("密码长度小于3位！");
		}
		
		if (admin.getPassword().length() > 16) {
			throw new Exception("密码长度大于16位！");
		}
		
		// 2. 验证账号是否存在
		// sAdmin是数据库中查到的
		Admin sAdmin = adminMapper.selectByUsername(admin.getUsername());
		
		if (sAdmin == null) {
			throw new Exception("用户名不存在！");
		}
		
		// 3. 密码是否正确
		// 因为管理员密码在存入数据库时进行了MD5加密，
		// 		所以登录时输入过来的密码也要MD5加密
		if (! sAdmin.getPassword().equals(MD5Util.getMD5(admin.getPassword()))) {
			throw new Exception("密码错误！");
		}
		
		// 4. 返回结果
		return sAdmin;
		
	}
	
	/**
	 * 通用查询
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PageInfo selectName(Integer page, Integer limit, String name) {
		
		if (page == null) {
			page = 1;
		}
		
		if (limit == null) {
			limit = 10;
		}
		
		// 开启分页
		PageHelper.startPage(page, limit);
		List list = adminMapper.selectName(name);
		PageInfo pageInfo = new PageInfo<>(list);
		
		return pageInfo;
	}

	/**
	 * 删除
	 */
	@Override
	public int remove(int id) {
		return adminMapper.delete(id);
	}

	/**
	 * 修改
	 */
	@Override
	public int edit(Admin admin) {
		return adminMapper.update(admin);
	}
	
	/**
	 * 添加
	 */
	@Override
	public int add(Admin admin) {
		
		// 将密码改为MD5加密的方式
		admin.setPassword(MD5Util.getMD5(admin.getPassword()));
		
		return adminMapper.insert(admin);
	}

}
