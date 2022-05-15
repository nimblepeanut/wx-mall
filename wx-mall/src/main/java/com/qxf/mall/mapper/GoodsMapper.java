package com.qxf.mall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qxf.mall.entity.Goods;

/**
 * 	商品数据库连接层
 * 	所有的商品状态必须是上架 state = 1
 * @author dell
 *
 */
public interface GoodsMapper {

	/**
	 * 	查询，
	 * 	首页轮播图-推荐商品
	 * @return
	 */
	@Select({
		"select ",
		"id, category_id, name, price, count, ",
		"introduce, recommend, state ",
		"from goods",
		"where state = 1",
		"and recommend = 1"
	})
	@Results({
		
		// 用到两次id，这次对应id
		@Result(column = "id", property = "id"),
		
		// 分类信息
		@Result(column = "category_id", property = "category", 
			one = @One(select = "com.qxf.mall.mapper.CategoryMapper.selectById")
		),
		
		// 图片列表，一对多
		@Result(column = "id", property = "goodsPicture",
			many = @Many(select = "com.qxf.mall.mapper.GoodsPictureMapper.selectById")
		)
		
	})
	List<Goods> selectByRecommend();
	
	/**
	 * 	查询，
	 * 	对应分类+推荐的商品
	 * @param categoryId
	 * @return
	 */
	@Select({
		"select ",
		"id, category_id, name, price, count, ",
		"introduce, recommend, state ",
		"from goods",
		"where state = 1 ",
		"and recommend = 1 ",
		"and category_id = #{categoryId} "
	})
	@Results({
		
		// 用到两次id，这次对应id
		@Result(column = "id", property = "id"),
		
		// 图片列表，一对多
		@Result(column = "id", property = "goodsPicture",
			many = @Many(select = "com.qxf.mall.mapper.GoodsPictureMapper.selectById")
		)
		
	})
	List<Goods> selectByCategoryIdRecommend(Integer categoryId);
	
	/**
	 * 	查询，
	 * 	对应分类
	 * @param categoryId
	 * @return
	 */
	@Select({
		"select ",
		"id, category_id, name, price, count, ",
		"introduce, recommend, state ",
		"from goods",
		"where state = 1 ",
		"and category_id = #{categoryId} "
	})
	@Results({
		
		// 用到两次id，这次对应id
		@Result(column = "id", property = "id"),
		
		// 分类信息
		@Result(column = "category_id", property = "category", 
			one = @One(select = "com.qxf.mall.mapper.CategoryMapper.selectById")
		),
		
		// 图片列表，一对多
		@Result(column = "id", property = "goodsPicture",
			many = @Many(select = "com.qxf.mall.mapper.GoodsPictureMapper.selectById")
		)
		
	})
	List<Goods> selectByCategoryId(Integer categoryId);

	/**
	 * 	查询全部商品
	 * @param categoryId
	 * @return
	 */
	@Select({
		"select ",
		"id, category_id, name, price, count, ",
		"introduce, recommend, state ",
		"from goods",
		"where state = 1 "
	})
	@Results({
		
		// 用到两次id，这次对应id
		@Result(column = "id", property = "id"),
		
		// 分类信息
		@Result(column = "category_id", property = "category", 
			one = @One(select = "com.qxf.mall.mapper.CategoryMapper.selectById")
		),
		
		// 图片列表，一对多
		@Result(column = "id", property = "goodsPicture",
			many = @Many(select = "com.qxf.mall.mapper.GoodsPictureMapper.selectById")
		)
		
	})
	List<Goods> selectAll();

	/**
	 * 	查询，
	 * 	根据id
	 * @param id
	 * @return
	 */
	@Select({
		"select ",
		"id, name, price, count, ",
		"introduce, recommend, state ",
		"from goods",
		"where id = #{id} ",
	})
	@Results({
		
		// 用到两次id，这次对应id
		@Result(column = "id", property = "id"),
		
		// 图片列表，一对多
		@Result(column = "id", property = "goodsPicture",
			many = @Many(select = "com.qxf.mall.mapper.GoodsPictureMapper.selectById")
		)
		
	})
	Goods selectById(Integer id);

	/**
	 * 	查询，
	 * 	根据名称
	 * @param name
	 * @return
	 */
	@Select({
		"select ",
		"id, name, price, count, ",
		"introduce, recommend, state ",
		"from goods",
		"where name like '%${name}%' "
	})
	@Results({
		
		// 用到两次id，这次对应id
		@Result(column = "id", property = "id"),
		
		// 图片列表，一对多
		@Result(column = "id", property = "goodsPicture",
			many = @Many(select = "com.qxf.mall.mapper.GoodsPictureMapper.selectById")
		)
		
	})
	List<Goods> selectByName(String name);

	/**
	 * 	付款，
	 * 	根据商品修改库存
	 * @param count
	 * @param integer 
	 */
	@Update({
		"update goods ",
		"set ",
		"count = count - #{count} ",
		"where id = #{id} ",
	})
	void updateCount(@Param("id") Integer id, @Param("count") Integer count);

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
