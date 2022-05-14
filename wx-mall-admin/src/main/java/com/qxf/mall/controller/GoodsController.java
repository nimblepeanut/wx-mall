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

import com.github.pagehelper.PageInfo;
import com.qxf.mall.entity.Goods;
import com.qxf.mall.entity.Result;
import com.qxf.mall.service.GoodsService;

/**
 * 	商品控制层
 * @author dell
 *
 */
@RestController
@RequestMapping("/api/goods")
public class GoodsController {
	
	@Autowired
	private GoodsService goodsService;
	
	/**
	 * 	查询
	 * @param page
	 * @param limit
	 * @param name
	 * @return
	 */
	@GetMapping
	public Result get(Integer page, Integer limit, Integer categoryId, String name) {
		
		PageInfo list = goodsService.select(page, limit, categoryId, name);
		
		// 判断是否查询成功
		if (list == null) {
			return Result.error("查询失败！");
		} else {
			return Result.success(list);
		}
		
	}
	
	/**
	 * 	软删除，本质是修改状态为下架
	 * @param goods
	 * @return
	 */
	@DeleteMapping
	public Result editState(Integer id) {
		
		int editState = goodsService.editState(id);
		
		// 判断是否修改成功
		if (editState > 0) {
			return Result.success("下架成功！");
		} else {
			return Result.error("下架失败！");
		}
		
	}
	
	/**
	 * 	修改
	 * @param goods
	 * @return
	 */
	@PutMapping
	public Result edit(@RequestBody Goods goods) {
		
		// 业务层
		int edit = goodsService.edit(goods);
		
		if (edit > 0) {
			return Result.success("修改成功！");
		} else {
			return Result.error("修改失败！");
		}
		
	}
	
	/**
	 * 添加
	 * @param goods
	 * @return
	 */
	@PostMapping
	public Result add(@RequestBody Goods goods) {
		
		// 业务层
		int add = goodsService.add(goods);
		
		if (add > 0) {
			return Result.success(goods);
		} else {
			return Result.error("添加失败！");
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
