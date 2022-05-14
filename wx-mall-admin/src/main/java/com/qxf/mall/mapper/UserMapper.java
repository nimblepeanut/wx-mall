package com.qxf.mall.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.qxf.mall.entity.User;

/**
 * 	用户的数据操作层
 * @author dell
 *
 */
public interface UserMapper {

	/**
	 * 综合查询
	 * @param page
	 * @param name
	 * @return
	 */
	@Select({
		"select id, username, head_portrait, DATE_FORMAT(regtime, '%Y年%m月%d日') as regtime ",
		"from user where username like '%${username}%' "
	})
	@Results({
		@Result(column = "head_portrait", property = "headPortrait")
	})
	List<User> select(String username);

	/**
	 * 用户注册时间的统计
	 * @return
	 */
	@Select({
		"select ",
		"DATE_FORMAT(regtime , '%Y-%m-%d') as regtime, count(id) as `count` ",
		"from user GROUP BY DATE_FORMAT(regtime, '%Y-%m-%d') ",
		"order by DATE_FORMAT(regtime, '%Y-%m-%d') "
	})
	List<Map<String, Object>> selectTime();

	/**
	 * 一段时间内，用户注册时间的统计
	 * @param start
	 * @param end
	 * @return
	 */
	@Select({
		"select ",
		"DATE_FORMAT(regtime , '%Y-%m-%d') as regtime, count(id) as `count` ",
		"from user ",
		"where regtime >= #{start} and regtime <= #{end} ",
//		"where regtime >= \"2022-05-01\" and regtime <= \"2022-06-01\" ",
		"GROUP BY DATE_FORMAT(regtime, '%Y-%m-%d') ",
		"order by DATE_FORMAT(regtime, '%Y-%m-%d') "
	})
	List<Map<String, Object>> selectStart(@Param("start") String start, @Param("end") String end);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
