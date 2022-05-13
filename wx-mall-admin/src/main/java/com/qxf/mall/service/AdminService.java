package com.qxf.mall.service;

import com.github.pagehelper.PageInfo;
import com.qxf.mall.entity.Admin;

/**
 *	管理员业务层接口
 * @author dell
 *
 */
public interface AdminService {

	/**
	 *	登录
	 * @param admin
	 * @return
	 */
	Admin login(Admin admin) throws Exception;

	/**
	 * 通用查询
	 * @param page
	 * @param limit
	 * @param name
	 * @return
	 */
	PageInfo selectName(Integer page, Integer limit, String name);

	/**
	 * 	删除
	 * @param id
	 * @return
	 */
	int remove(int id);

	/**
	 * 修改
	 * @param admin
	 * @return
	 */
	int edit(Admin admin);

	/**
	 * 添加
	 * @param admin
	 * @return
	 */
	int add(Admin admin);

}
