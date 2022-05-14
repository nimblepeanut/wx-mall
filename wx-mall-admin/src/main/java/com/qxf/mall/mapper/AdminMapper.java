package com.qxf.mall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qxf.mall.entity.Admin;

/**
 *	管理员数据访问层
 * @author dell
 *
 */
public interface AdminMapper {

	/**
	 * 	登录，根据用户名查询
	 * @param username
	 * @return
	 */
	@Select("select * from admin where username=#{username}")
	@Results({
		@Result(column = "head_portrait", property = "headPortrait")
	})
	Admin selectByUsername(String username);

	/**
	 * 通用查询
	 * @param name
	 * @return
	 */
	@Select({
		"select id, username, password, head_portrait ",
		"from admin where username like '%${username}%'"
	})
	@Results({
		@Result(column = "head_portrait", property = "headPortrait")
	})
	List<Admin> selectName(String name);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@Delete({
		"delete from admin where id = #{id}"
	})
	int delete(int id);

	/**
	 * 修改
	 * @param admin
	 * @return
	 */
	@Update({
		"update admin ",
		"set ",
		"username=#{username} , password=#{password}, head_portrait=#{headPortrait} ",
		"where id=#{id}"
	})
	int update(Admin admin);
	
	/**
	 * 添加
	 * @param admin
	 * @return
	 */
	@Insert({
		"insert into admin ",
		"(username, password, head_portrait) ",
		"values ",
		"(#{username}, #{password}, #{headPortrait})"
	})
	int insert(Admin admin);

}
