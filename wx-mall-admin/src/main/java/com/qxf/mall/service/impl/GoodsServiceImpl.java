package com.qxf.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<Goods> select(Integer page, Integer limit, Integer categoryId, String name) {
		
		// 先判断page有没有值，没有给默认值1
		if (page == null) {
			page = 1;
		}
		
		// 起点
		Integer start = (page - 1) * limit;
		
		return goodsMapper.select(start, limit, categoryId, name);
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
