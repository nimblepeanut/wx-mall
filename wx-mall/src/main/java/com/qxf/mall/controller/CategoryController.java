package com.qxf.mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qxf.mall.entity.Category;
import com.qxf.mall.entity.Result;
import com.qxf.mall.service.CategoryService;

/**
 * 	分类控制层
 * @author dell
 *
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	/**
	 *	查分类里的推荐商品
	 * @return
	 */
	@GetMapping("/recommend")
	public Result selectByRecommend() {
		
		List<Category> list = categoryService.selectByRecommend();
		
		// 判断查询成功
		if (list == null) {
			return Result.error("查询失败！");
		} else {
			return Result.success(list);
		}
		
	}
	
	/**
	 * 	查所有分类
	 * @return
	 */
	@GetMapping("/all")
	public Result selectAll() {
		
		List<Category> list = categoryService.selectAll();
		
		// 判断查询成功
		if (list == null) {
			return Result.error("查询失败！");
		} else {
			return Result.success(list);
		}
		
	}
		

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
