package com.qxf.mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qxf.mall.entity.Addr;
import com.qxf.mall.entity.Cart;
import com.qxf.mall.entity.Goods;
import com.qxf.mall.entity.Result;
import com.qxf.mall.service.CartService;

/**
 * 	购物车控制层
 * @author dell
 *
 */
@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	/**
	 * 	加入购物车
	 * @param cart
	 * @return
	 */
	@PostMapping
	public Result add(@RequestBody Cart cart) {
		
		Integer add = cartService.add(cart);
		
		// 影响行数
		if (add == 1) {
			return Result.success(add);
		} else {
			return Result.error("添加失败！");
		}
		
	}
	
	/**
	 * 	购物车，
	 * 	根据用户id查
	 * @param userId
	 * @return
	 */
	@GetMapping
	public Result all(Integer userId) {

		List<Cart> list = cartService.all(userId);

		// 判断查询成功
		if (list == null) {
			return Result.error("查询失败！");
		} else {
			return Result.success(list);
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
