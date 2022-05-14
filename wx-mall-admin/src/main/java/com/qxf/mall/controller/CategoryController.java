package com.qxf.mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qxf.mall.entity.Category;
import com.qxf.mall.entity.Result;
import com.qxf.mall.service.CategoryService;

/**
 * 商品分类控制层
 * 
 * @author dell
 *
 */
// 返回json格式数据
@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

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
		
		List<Category> list = categoryService.selectName(page, limit, name);
		
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
		
		int remove = categoryService.remove(id);
		
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
	public Result edit(@RequestBody Category category) {
		
		// 调用业务层修改方法
		int edit = categoryService.edit(category);
		
		// 判断是否修改成功
		if (edit > 0) {
			return Result.success(category);
		} else {
			return Result.error("修改失败");
		}
		
	}
	
	/**
	 * 	添加
	 * @param category
	 * @return
	 */
	@PostMapping
	public Result add(@RequestBody Category category) {
		
		// 调用业务层添加方法
		int add = categoryService.add(category);
		
		if(add > 0) {
			return Result.success(category);
		} else {
			return Result.error("添加失败！");
		}
		
	}
	
	/**
	 * 	分类的可视化图表，每个分类的商品数量
	 * @return
	 */
	@GetMapping("/echarts")
	public Result echarts() {
		
		List list = categoryService.count();
		
		return Result.success(list);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
