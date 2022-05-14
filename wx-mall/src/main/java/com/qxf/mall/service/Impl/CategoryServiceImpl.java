package com.qxf.mall.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qxf.mall.entity.Category;
import com.qxf.mall.mapper.CategoryMapper;
import com.qxf.mall.service.CategoryService;

/**
 * 	分类业务层实现类
 * @author dell
 *
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapper categoryMapper;
	
	/**
	 * 	查分类里的推荐商品
	 */
	@Override
	public List<Category> selectByRecommend() {
		return categoryMapper.selectByRecommend();
	}
	
	/**
	 * 	查所有分类
	 */
	@Override
	public List<Category> selectAll() {
		return categoryMapper.selectAll();
	}

}
