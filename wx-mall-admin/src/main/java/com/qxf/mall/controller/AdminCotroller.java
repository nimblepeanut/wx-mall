package com.qxf.mall.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.qxf.mall.entity.Admin;
import com.qxf.mall.entity.Result;
import com.qxf.mall.service.AdminService;

/**
 * 管理员控制层
 * @author dell
 *
 */
@RestController
@RequestMapping("/api/admin")
public class AdminCotroller {
	
	@Autowired
	private AdminService adminService;
	
	/**
	 * 通用查询
	 * 1. name为null则全部查询
	 * 2. name通过 where: data.field 传值后则进行模糊查询
	 * @param page
	 * @param limit
	 * @param name
	 * @return
	 */
	@GetMapping
	public Result get(Integer page, Integer limit, String name) {
		
		PageInfo list = adminService.selectName(page, limit, name);
		
		// 判断是否查询成功
		if (list == null) {
			return Result.error("查询失败");
		} else {
			return Result.success(list);
		}
		
	}

	/**
	 * 根据id删除
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping
	public Result remove(int id) {
		
		int remove = adminService.remove(id);
		
		if (remove == 0) {
			return Result.error("删除失败！");
		} else {
			return Result.success("删除成功！");
		}
		
	}
	
	/**
	 * 	修改
	 * @return
	 */
	@PutMapping
	public Result edit(@RequestBody Admin admin) {
		
		// 调用业务层修改方法
		int edit = adminService.edit(admin);
		
		// 判断是否修改成功
		if (edit > 0) {
			return Result.success(admin);
		} else {
			return Result.error("修改失败");
		}
		
	}
	
	/**
	 * 	添加
	 * @param admin
	 * @return
	 */
	@PostMapping
	public Result add(@RequestBody Admin admin) {
		
		// 调用业务层添加方法
		int add = adminService.add(admin);
		
		if(add > 0) {
			return Result.success(admin);
		} else {
			return Result.error("添加失败！");
		}
		
	}
	

}























