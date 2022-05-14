package com.qxf.mall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.qxf.mall.entity.GoodsPicture;

public interface GoodsPictureMapper {
	
	/**
	 * 	商品关联查询，
	 * 	根据id
	 * @param id
	 * @return
	 */
	@Select({
		"select picture ",
		"from goods_picture ",
		"where goods_id = #{id}"
	})
	List<GoodsPicture> selectById(Integer id);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
