package com.qxf.mall.service;

import java.util.List;

import com.qxf.mall.entity.Goods;

/**
 * 商品业务层接口
 * 
 * @author dell
 *
 */
public interface GoodsService {

	/**
	 * 查询， 首页轮播图-推荐商品
	 * 
	 * @return
	 */
	List<Goods> selectByRecommend();

	/**
	 * 查询全部商品
	 * 
	 * @return
	 */
	List<Goods> selectAll();

	/**
	 * 查询， 根据id
	 * 
	 * @param id
	 * @return
	 */
	Goods selectById(Integer id);

	/**
	 * 	查询， 
	 * 	根据名称
	 * 
	 * @param name
	 * @return
	 */
	List<Goods> selectByName(String name);

}
