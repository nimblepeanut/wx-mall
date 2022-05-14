package com.qxf.mall.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qxf.mall.entity.Goods;
import com.qxf.mall.mapper.GoodsMapper;
import com.qxf.mall.service.GoodsService;

/**
 * 商品业务层实现类
 * 
 * @author dell
 *
 */
@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsMapper goodsMapper;

	/**
	 * 查询， 首页轮播图-推荐商品
	 */
	@Override
	public List<Goods> selectByRecommend() {
		return goodsMapper.selectByRecommend();
	}

	/**
	 * 查询全部商品
	 */
	@Override
	public List<Goods> selectAll() {
		return goodsMapper.selectAll();
	}

	/**
	 * 	查询，
	 * 	根据id
	 */
	@Override
	public Goods selectById(Integer id) {
		return goodsMapper.selectById(id);
	}
	
	/**
	 * 	查询，
	 * 	根据名称
	 */
	@Override
	public List<Goods> selectByName(String name) {
		return goodsMapper.selectByName(name);
	}

}
