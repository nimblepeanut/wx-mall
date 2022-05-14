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

import com.qxf.mall.entity.Goods;

/**
 * 	商品数据访问层
 * @author dell
 *
 */
public interface GoodsMapper {

	/**
	 * 	通用查询
	 * @param start
	 * @param limit
	 * @param name
	 * @return
	 */
	@Select({
		
		"<script>",
		
			"select ",
			"id, category_id, name, price, count, introduce, recommend, state ", 
			"from goods",
			
			"<where>",
			
				"<if test='categoryId != null'>",
					"category_id = #{categoryId}",
				"</if>",
				
				"<if test='name != null'>", 
					"and name like '%${name}%'",
				"</if>", 
				
			"</where>",
			
			"limit #{start}, #{lt}",
			
		"</script>"
			
//		"select ", 
//		"id, category_id, name, price, count, introduce, recommend, state",
//		"from goods where name like '%${name}%' limit #{start}, #{lt}"
	})
	@Results({
		
		// 因为要用到两次id，所以这里要写一次
		@Result(column = "id", property = "id"),
		
		// 根据id查询，商品一对一查分类id和名称
		@Result(column = "category_id", property = "category", 
			one = @One(select = "com.qxf.mall.mapper.CategoryMapper.selectById")
		),
		
		// 根据id查询，商品一对多查图片
		@Result(column = "id", property = "goodsPicture", 
			many = @Many(select = "com.qxf.mall.mapper.GoodsPictureMapper.selectById")
		)
	
	})
	List<Goods> select(@Param("start") Integer start, @Param("lt") Integer limit, @Param("categoryId") Integer categoryId, @Param("name") String name);
	
	/**
	 * 添加
	 * @param goods
	 * @return
	 */
	@Insert({
		"insert into goods (category_id, name, price, count, introduce, recommend, state)",
		"values (#{categoryId}, #{name}, #{price}, #{count}, #{introduce}, #{recommend}, #{state}) "
	})
	int Insert(Goods goods);
	
	/**
	 * 	软删除，本质是修改状态
	 * @param goods
	 * @return
	 */
	@Update("update goods set state = 0 where id=#{id}")
	int updateState(Integer id);

	/**
	 * 	修改
	 * @param goods
	 * @return
	 */
	@Update({
		"update goods set", 
		"category_id=#{categoryId}, name=#{name}, price=#{price}, count=#{count}, introduce=#{introduce}, recommend=#{recommend}, state=#{state}", 
		"where id=#{id}"
	})
	int update(Goods goods);
																																							

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
