package com.qxf.mall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qxf.mall.entity.Cart;

/**
 * 	购物车数据库访层
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
	 * 	查询商品是否重复，
	 * 	根据用户id、商品id查
	 * 
	 * @param userId
	 * @param goodsId
	 * @return
	 */
	@Select({
		"select ",
		"id, user_id, goods_id, addr_id, count",
		"from cart",
		"where state = 0", 
		"and user_id = #{userId} ",
		"and goods_id = #{goodsId} ",
	})
	@Results({
		@Result(column = "user_id", property = "userId"),
		@Result(column = "goods_id", property = "goodsId"),
		@Result(column = "addr_id", property = "addrId")
	})
	Integer selectByUserIdGoodsId(@Param("userId") Integer userId, @Param("goodsId") Integer goodsId);

	/**
	 * 	商品重复，修改数量+1
	 * @param userId
	 * @param goodsId
	 * @return
	 */
	@Update({
		"update cart ",
		"set ",
		"count = count + 1 ",
		"where state = 0", 
		"and user_id = #{userId} ",
		"and goods_id = #{goodsId} ",
	})
	Integer updateByUserIdGoodsId(@Param("userId") Integer userId, @Param("goodsId") Integer goodsId);
	
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
		"id, user_id, goods_id, addr_id, count",
		"from cart",
		"where state = 0", 
		"and user_id = #{userId} ",
	})
	@Results({

		// 对应商品
		@Result(column = "goods_id", property = "goods",
			one = @One(select = "com.qxf.mall.mapper.GoodsMapper.selectById")
		),
		
//		@Result(column = "user_id", property = "userId"),
		@Result(column = "goods_id", property = "goodsId"),
		@Result(column = "addr_id", property = "addrId")

	})
	List<Cart> all(Integer userId);

	/**
	 * 	清空购物车，
	 * 	修改状态 state = 1
	 * @param userId
	 * @return
	 */
	@Update({
		"update cart ",
		"set ",
		"state = 1 ",
		"where user_id = #{userId} ",
	})
	Integer updateState(Integer userId);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
