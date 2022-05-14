package com.qxf.mall.service;

import java.util.List;

import com.qxf.mall.entity.Category;

/**
 * 	分类业务层接口
 * @author dell
 *
 */
public interface CategoryService {
	
	/**
	 * 	查分类里的推荐商品
	 * @return
	 */
	List<Category> selectByRecommend();

	/**
	 * 	查所有分类
	 * @return
	 */
	List<Category> selectAll();
	
}
