package com.qxf.mall.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.qxf.mall.entity.Category;

/**
 * 商品分类业务层接口
 * @author dell
 *
 */
public interface CategoryService {
	
	/**
	 * 通用查询
	 * @param page
	 * @param limit
	 * @param name
	 * @return
	 */
	PageInfo selectName(Integer page, Integer limit, String name);
	
	/**
	 * 	根据id删除商品分类
	 * @param id
	 * @return
	 */
	int remove(int id);

	/**
	 * 修改
	 * @param category
	 * @return
	 */
	int edit(Category category);
	
	/**
	 * 	添加
	 * @param category
	 * @return
	 */
	int add(Category category);

	/**
	 * 	每个分类的商品数量
	 * @return
	 */
	List count();
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
