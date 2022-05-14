package com.qxf.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qxf.mall.entity.Category;
import com.qxf.mall.mapper.CategoryMapper;
import com.qxf.mall.service.CategoryService;

/**
 * 商品分类业务层实现类
 * @author dell
 *
 */
@Service
public class CategoryServiceImpl implements CategoryService {
	
	// 注入 CategoryMapper
	@Autowired
	private CategoryMapper categoryMapper;
	
	/**
	 * 通用查询
	 */
	@Override
	public PageInfo selectName(Integer page, Integer limit, String name) {
		
		if (page == null) {
			page = 1;
		}
		
		if (limit == null) {
			limit = 10;
		}
		
		// 开启分页
		PageHelper.startPage(page, limit);
		List list = categoryMapper.selectName(name);
		PageInfo pageInfo = new PageInfo<>(list);
		
		return pageInfo;
		
	}
	
	/**
	 * 	根据id删除商品分类
	 */
	@Override
	public int remove(int id) {
		
		return categoryMapper.delete(id);
		
	}

	/**
	 * 修改
	 */
	@Override
	public int edit(Category category) {
		
		// 调用数据访问层修改方法
		return categoryMapper.update(category);
		
	}
	
	/**
	 * 	添加
	 */
	@Override
	public int add(Category category) {
		return categoryMapper.insert(category);
	}

	/**
	 * 	每个分类的商品数量
	 */
	@Override
	public List count() {
		return categoryMapper.selectGoodsCount();
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
