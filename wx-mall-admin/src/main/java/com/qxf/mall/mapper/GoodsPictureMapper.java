package com.qxf.mall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.qxf.mall.entity.GoodsPicture;

public interface GoodsPictureMapper {
	
	/**
	 * 	根据id查询，商品一对多查图片
	 * @param id
	 * @return
	 */
	@Select("select picture from goods_picture where goods_id = #{id} ")
	List<GoodsPicture> selectById(Integer id);
	
	/**
	 * 	添加商品图片
	 * @param goodsPicture
	 * @return
	 */
	@Insert({
		"insert into goods_picture (picture, goods_id)",
		"values (#{picture}, #{goodsId})"
	})
	int Insert(GoodsPicture goodsPicture);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
