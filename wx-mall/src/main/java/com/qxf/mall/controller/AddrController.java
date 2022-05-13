package com.qxf.mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qxf.mall.entity.Addr;
import com.qxf.mall.entity.Goods;
import com.qxf.mall.entity.Result;
import com.qxf.mall.service.AddrService;

/**
 * 	地址控制层
 * @author dell
 *
 */
@RestController
@RequestMapping("/addr")
public class AddrController {

	@Autowired
	private AddrService addrService;
	
	/**
	 * 	地址列表，
	 * 	根据用户id和地名查
	 * @return
	 */
	@GetMapping
	public Result all(Integer userId, String name) {

		List<Addr> list = addrService.all(userId, name);

		// 判断查询成功
		if (list == null) {
			return Result.error("查询失败！");
		} else {
			return Result.success(list);
		}

	}
	
	/**
	 * 	添加地址
	 * @param address
	 * @return
	 */
	@PostMapping
	public Result add(@RequestBody Addr addr) {
		
		Integer add = addrService.add(addr);
		
		// 影响行数
		if (add == 1) {
			return Result.success(add);
		} else {
			return Result.error("添加失败！");
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
