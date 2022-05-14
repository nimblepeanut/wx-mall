package com.qxf.mall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.qxf.mall.entity.Cart;

/**
 * 	购物车
 * @author dell
 *
 */
public interface CartMapper {

	/**
	 * 	加入购物车
	 * @param cart
	 * @return
	 */
	@Insert({
		"insert into cart ",
		"(user_id, goods_id, addr_id )",
		"values ",
		"(#{userId}, #{goodsId}, #{addrId} )"
	})
	Integer add(Cart cart);

	/**
	 * 	购物车，
	 * 	根据用户id查
	 * 
	 * 	state = 0 代表当前未支付的
	 * @param userId
	 * @return
	 */
	@Select({
		"select ",
		"id, goods_id, count",
		"from cart",
		"where state = 0", 
		"and user_id = #{userId} ",
	})
	@Results({

		// 对应商品
		@Result(column = "goods_id", property = "goods",
			one = @One(select = "com.qxf.mall.mapper.GoodsMapper.selectById")
		)
		
	})
	List<Cart> all(Integer userId);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
