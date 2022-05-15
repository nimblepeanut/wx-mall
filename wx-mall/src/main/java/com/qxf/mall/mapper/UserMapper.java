package com.qxf.mall.mapper;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qxf.mall.entity.User;

public interface UserMapper {
	
	/**
	 * 	用户登录
	 * @param username
	 * @return
	 */
	@Select("select * from user where username=#{username}")
	@Results({
		@Result(column = "head_portrait", property = "headPortrait")
	})
	User selectLogin(String username);

	/**
	 *	 注册
	 * @param username
	 * @param password
	 * @param headPortrait
	 * @return
	 */
	@Insert({
		"insert into user ",
		"(username, password, head_portrait) ",
		"values ",
		"(#{username}, #{password}, #{headPortrait}) ",
	})
	Integer register(@Param("username") String username, @Param("password") String password, @Param("headPortrait") String headPortrait);

	/**
	 * 	充值，
	 * 	根据用户id
	 * @param userId
	 * @param money
	 * @return
	 */
	@Update({
		"update user ",
		"set ",
		"money = #{money} ",
		"where id = #{userId}"
	})
	Integer recharge(@Param("userId") Integer userId, @Param("money") BigDecimal money);

	/**
	 * 	根据id查用户
	 * @param username
	 * @return
	 */
	@Select("select * from user where id=#{userId}")
	@Results({
		@Result(column = "head_portrait", property = "headPortrait")
	})
	User selectById(Integer userId);

	/**
	 * 	付款，
	 * 	根据用户id查
	 * @param userId
	 * @param totalPrice
	 * @return
	 */
	@Update({
		"update user ",
		"set ",
		"money = money - #{totalPrice} ",
		"where id = #{userId}"
	})
	Integer updateMoney(@Param("userId") Integer userId, @Param("totalPrice")  BigDecimal totalPrice);
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
