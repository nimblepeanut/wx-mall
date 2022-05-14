package com.qxf.mall.service;

import java.math.BigDecimal;
import java.util.List;

import com.qxf.mall.entity.Cart;

/**
 * 	购物车业务层接口
 * @author dell
 *
 */
public interface CartService {

	/**
	 * 	加入购物车
	 * @param cart
	 * @return
	 */
	Integer add(Cart cart);

	/**
	 * 	购物车，
	 * 	根据用户id查
	 * @param userId
	 * @return
	 */
	List<Cart> all(Integer userId);

	/**
	 * 	付款，
	 * 	根据用户id
	 * @param userId
	 * @return
	 */
	Integer payment(Integer userId);


	
	
	
	
	
	
	
	
	
	
	
	
	

}
