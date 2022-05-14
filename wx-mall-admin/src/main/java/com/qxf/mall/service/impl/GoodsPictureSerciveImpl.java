package com.qxf.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qxf.mall.entity.GoodsPicture;
import com.qxf.mall.mapper.GoodsPictureMapper;
import com.qxf.mall.service.GoodsPictureService;

/**
 * 	商品图片业务层实现类
 * @author dell
 *
 */
@Service
public class GoodsPictureSerciveImpl implements GoodsPictureService {
	
	@Autowired
	private GoodsPictureMapper goodsPictureMapper;
	
	/**
	 * 	添加商品图片
	 */
	@Override
	public int add(GoodsPicture goodsPicture) {
		return goodsPictureMapper.Insert(goodsPicture);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
