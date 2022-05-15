package com.qxf.mall.service.Impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.qxf.mall.entity.Cart;
import com.qxf.mall.mapper.CartMapper;
import com.qxf.mall.mapper.GoodsMapper;
import com.qxf.mall.mapper.UserMapper;
import com.qxf.mall.service.CartService;

/**
 * 	购物车业务层实现类
 * @author dell
 *
 */
@Service
public class CartServiceImpl implements CartService {
	
	// 购物车数据库访层
	@Autowired
	private CartMapper cartMapper;
	
	// 用户数据库访层
	@Autowired
	private UserMapper userMapper;
	
	// 商品数据库访层
	@Autowired
	private GoodsMapper goodsMapper;

	/**
	 * 	加入购物车
	 * 
	 * 	查询商品是否重复，
	 * 	根据用户id、商品id查
	 * 
	 * 	重复的话添加购物车里的商品数量
	 */
	@Override
	public Integer add(Cart cart) {
		
		// 查询商品
		Integer num = cartMapper.selectByUserIdGoodsId(cart.getUserId(), cart.getGoodsId());
		
//		System.out.println("-------------------------\n\n");
//		System.out.println(cart);
//		System.out.println("-------------------------\n\n");
//		System.out.println(num);
//		System.out.println("-------------------------\n\n");
		
		/**
		 * 	呵、呵呵，null，
		 * 	这是我觉得最有意思的地方了，
		 * 	num == null 必须是第一个if里的，
		 * 	有点东西，这不和equals判断是否相等一样的问题吗
		 */
		// 是否重复
		if (num == null) {
			// 不重复，
			// 加入购物车
			return cartMapper.add(cart);

		} else {
			// 重复，
			// 修改商品数量
			return cartMapper.updateByUserIdGoodsId(cart.getUserId(), cart.getGoodsId());
		}
		
	}

	/**
	 * 	购物车，
	 * 	根据用户id查
	 */
	@Override
	public List<Cart> all(Integer userId) {
		return cartMapper.all(userId);
	}

	/**
	 * 	付款
	 * 
	 * 	为防止超买超卖并且涉及到金钱，
	 * 	数据库事物的隔离级别调到串行化，
	 * 	使用表级共享锁：sql语句执行一句就加一个锁，
	 * 	报错就回滚
	 */
	@Override
	@Transactional(
		isolation = Isolation.SERIALIZABLE, 
		rollbackFor = Exception.class
	)
	public Integer payment(Integer userId) {

		// 购物车
		List<Cart> cartList = cartMapper.all(userId);
		
		// 总金额,初始值为0
		BigDecimal totalPrice = new BigDecimal("0");
		
		// 遍历修改每个商品的数量
		for (Cart cart : cartList) {
			
			// 减少对应商品的库存	 
			goodsMapper.updateCount(cart.getGoods().getId(), cart.getCount());
			
			// 注意：BigDecimal不能用 "*"、"+"等运算，必须用方法
			
			// multiply：乘，
			// 需要用 new BigDecimal() 方法把数量从Integer转为BigDecimal
			BigDecimal newNum = cart.getGoods().getPrice().multiply(new BigDecimal(cart.getCount()));
			
			// add：加
			totalPrice = totalPrice.add(newNum);
	
		}
		
		// 修改用户余额
		Integer userMoney = userMapper.updateMoney(userId, totalPrice);
		
		// 清空购物车，
		// 修改状态 state = 1
		Integer emptyCart = cartMapper.updateState(userId);
		
		// 判断是否成功
		if (userMoney > 0 && emptyCart > 0) {
			return 1;
		} else {
			return 0;
		}
		
	}


	
	
	
	
	
	
	
	
	

}
