package com.qxf.mall.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qxf.mall.entity.Cart;
import com.qxf.mall.mapper.CartMapper;
import com.qxf.mall.service.CartService;

/**
 * 	购物车业务层实现类
 * @author dell
 *
 */
@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartMapper cartMapper;

	/**
	 * 	加入购物车
	 */
	@Override
	public Integer add(Cart cart) {
		return cartMapper.add(cart);
	}

	/**
	 * 	购物车，
	 * 	根据用户id查
	 */
	@Override
	public List<Cart> all(Integer userId) {
		return cartMapper.all(userId);
	}
	
	
	
	
	
	
	
	
	

}
