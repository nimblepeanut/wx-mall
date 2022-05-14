package com.qxf.mall.service;

import com.qxf.mall.entity.GoodsPicture;

/**
 * 	商品图片业务层接口
 * @author dell
 *
 */
public interface GoodsPictureService {
	
	/**
	 * 	添加商品图片
	 * @param goodsPicture
	 * @return
	 */
	int add(GoodsPicture goodsPicture);

}
