package com.qxf.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qxf.mall.entity.Goods;
import com.qxf.mall.mapper.GoodsMapper;
import com.qxf.mall.service.GoodsService;

/**
 * 	商品业务层实现类
 * @author dell
 *
 */
@Service
public class GoodsServiceImpl implements GoodsService {
	
	@Autowired
	private GoodsMapper goodsMapper;

	/**
	 * 通用查询
	 */
	@Override
	public PageInfo select(Integer page, Integer limit, Integer categoryId, String name) {
		
		if (page == null) {
			page = 1;
		}
		
		if (limit == null) {
			limit = 10;
		}
		
		// 开启分页
		PageHelper.startPage(page, limit);
		List list = goodsMapper.select(categoryId, name);
		PageInfo pageInfo = new PageInfo<>(list);
		
		return pageInfo;
		
	}
	
	/**
	 * 添加
	 */
	@Override
	public int add(Goods goods) {
		return goodsMapper.Insert(goods);
	}

	/**
	 * 	软删除
	 */
	@Override
	public int editState(Integer id) {
		return goodsMapper.updateState(id);
	}
	
	/**
	 * 	修改
	 */
	@Override
	public int edit(Goods goods) {
		return goodsMapper.update(goods);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
