package com.qxf.mall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.qxf.mall.entity.Category;

public interface CategoryMapper {
	
	/**
	 * 	商品关联查询，
	 * 	根据id
	 * @param id
	 * @return
	 */
	@Select({
		"select id, name ",
		"from category ",
		"where id = #{id}"
	})
	Category selectById(Integer id);
	
	/**
	 * 	查，
	 * 	对应分类+推荐的商品
	 * @return
	 */
	@Select({
		"select ",
		"id, name, picture ",
		"from category "
	})
	@Results({
		
		// 原有的id
		@Result(column = "id", property = "id"),
		
		// 对应分类的商品
		@Result(column = "id", property = "goods",
			many = @Many(select = "com.qxf.mall.mapper.GoodsMapper.selectByCategoryIdRecommend")
		),
		
	})
	List<Category> selectByRecommend();
	
	/**
	 * 	查所有分类
	 * @return
	 */
	@Select({
		"select ",
		"id, name, picture ",
		"from category "
	})
	@Results({
		
		// 原有的id
		@Result(column = "id", property = "id"),
		
		// 对应分类的商品
		@Result(column = "id", property = "goods",
			many = @Many(select = "com.qxf.mall.mapper.GoodsMapper.selectByCategoryId")
		),
		
	})
	List<Category> selectAll();

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
