package com.qxf.mall.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qxf.mall.entity.Addr;
import com.qxf.mall.mapper.AddrMapper;
import com.qxf.mall.service.AddrService;

/**
 * 	地址业务层实现类
 * @author dell
 *
 */
@Service
public class AddrServiceImpl implements AddrService {

	@Autowired
	private AddrMapper addrMapper;
	
	/**
	 * 	地址列表，
	 * 	根据用户id和地名查
	 */
	@Override
	public List<Addr> all(Integer userId, String name) {
		return addrMapper.all(userId, name);
	}
	
	/**
	 * 	添加地址
	 */
	@Override
	public Integer add(Addr addr) {
		return addrMapper.add(addr);
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
