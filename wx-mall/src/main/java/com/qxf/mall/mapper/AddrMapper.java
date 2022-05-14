package com.qxf.mall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.qxf.mall.entity.Addr;

/**
 * 	地址数据库访问层
 * @author dell
 *
 */
public interface AddrMapper {
	
	/**
	 * 	地址列表，
	 * 	根据用户id和地名查
	 * @param userId
	 * @return
	 */
	@Select({
		"select ",
		"id, consignee, phone, address, name ",
		"from addr ",
		"where user_id = #{userId}",
		"and name like '%${name}%' "
	})
	List<Addr> all(@Param("userId") Integer userId, @Param("name") String name);

	/**
	 * 	添加地址
	 * @param address
	 * @return
	 */
	@Insert({
		"insert into addr ",
		"(consignee, phone, address, name, user_id)",
		"values ",
		"(#{consignee}, #{phone}, #{address}, #{name}, #{userId})"
	})
	Integer add(Addr addr);


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
