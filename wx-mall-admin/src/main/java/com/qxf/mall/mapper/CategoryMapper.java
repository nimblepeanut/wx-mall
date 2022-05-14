package com.qxf.mall.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.qxf.mall.entity.Category;

/**
 * 商品分类数据访问层
 * @author dell
 *
 */
public interface CategoryMapper {
	
	/**
	 * 商品下拉框，全查
	 * @return
	 */
	@Select("select id, name, picture from category")
	List<Category> selectAll();

	/**
	 * 通用查询：全查+名称模糊查询
	 * @param start
	 * @param limit
	 * @param name
	 * @return
	 */
	@Select("select id, name, picture from category where name like '%${name}%'")
	List<Category> selectName(String name);
	
	/**
	 * 	根据id查询，商品一对一查分类id和名称,
	 * 		tm千万别忘了还有id，修改商品时分类下拉框的选中事件要用
	 * @param start
	 * @param limit
	 * @param name
	 * @return
	 */
	@Select("select id, name from category where id = #{id}")
	Category selectById(Integer id);

	/**
	 *	根据id删除商品分类
	 * @param id
	 * @return
	 */
	@Delete("delete from category where id = #{id}")
	int delete(int id);

	/**
	 * 修改
	 * @param category
	 * @return
	 */
	@Update("update category set name=#{name} , picture=#{picture} where id=#{id}")
	int update(Category category);

	/**
	 * 	添加
	 * @param category
	 * @return
	 */
	@Insert({
		"insert into category (name, picture)",
		"values (#{name}, #{picture})"
	})
	int insert(Category category);

	/**
	 * 	每个分类的商品数量
	 * @return
	 */
	@Select({
		"select category.name,count(goods.category_id) as count ",
		"from category,goods where category.id=goods.category_id ",
		"GROUP BY goods.category_id"
	})
	List<Map<String, Object>> selectGoodsCount();



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
