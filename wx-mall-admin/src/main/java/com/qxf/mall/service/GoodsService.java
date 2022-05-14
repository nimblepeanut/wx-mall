package com.qxf.mall.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.qxf.mall.entity.Goods;

/**
 * 	商品业务层接口
 * @author dell
 *
 */
public interface GoodsService {

	/**
	 * 通用查询
	 * @param page
	 * @param limit
	 * @param name
	 * @return
	 */
	PageInfo select(Integer page, Integer limit, Integer categoryId, String name);
	
	/**
	 * 添加
	 * @param goods
	 * @return
	 */
	int add(Goods goods);

	/**
	 * 	软删除
	 * @param goods
	 * @return
	 */
	int editState(Integer id);
	
	/**
	 *	 修改
	 * @param goods
	 * @return
	 */
	int edit(Goods goods);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
