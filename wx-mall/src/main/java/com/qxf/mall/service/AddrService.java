package com.qxf.mall.service;

import java.util.List;

import com.qxf.mall.entity.Addr;

/**
 * 	地址业务层接口
 * @author dell
 *
 */
public interface AddrService {
	
	/**
	 * 	地址列表，
	 * 	根据用户id和地名查
	 * @param userId
	 * @param name 
	 * @return
	 */
	List<Addr> all(Integer userId, String name);

	/**
	 * 	添加地址
	 * @param address
	 * @return
	 */
	Integer add(Addr addr);



}
