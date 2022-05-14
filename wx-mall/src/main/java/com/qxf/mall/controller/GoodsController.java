package com.qxf.mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qxf.mall.entity.Goods;
import com.qxf.mall.entity.Result;
import com.qxf.mall.service.GoodsService;

/**
 * 商品控制层
 * 
 * @author dell
 *
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

	@Autowired
	private GoodsService goodsService;

	/**
	 * 	查询， 
	 * 	首页轮播图-推荐商品
	 * 
	 * @return
	 */
	@GetMapping("/selectByRecommend")
	public Result selectByRecommend() {

		List<Goods> list = goodsService.selectByRecommend();

		// 判断查询成功
		if (list == null) {
			return Result.error("查询失败！");
		} else {
			return Result.success(list);
		}

	}

	/**
	 * 	查询全部商品
	 * @return
	 */
	@GetMapping("/all")
	public Result selectAll() {

		List<Goods> list = goodsService.selectAll();

		// 判断查询成功
		if (list == null) {
			return Result.error("查询失败！");
		} else {
			return Result.success(list);
		}

	}

	/**
	 * 	查询，
	 * 	根据id
	 * @return
	 */
	@GetMapping("/selectById")
	public Result selectById(Integer id) {

		Goods goods = goodsService.selectById(id);

		// 判断查询成功
		if (goods == null) {
			return Result.error("查询失败！");
		} else {
			return Result.success(goods);
		}

	}
	
	/**
	 * 	查询，
	 * 	根据名称
	 */
	@GetMapping("/selectByName")
	public Result selectByName(String name) {

		List<Goods> list = goodsService.selectByName(name);

		// 判断查询成功
		if (list == null) {
			return Result.error("查询失败！");
		} else {
			return Result.success(list);
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
