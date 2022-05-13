package com.qxf.mall.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 	商品图片
 * @author 秦晓枫
 *
 */
@Getter
@Setter
public class GoodsPicture {
	
	// id
	private Integer id;
	
	// 图片
	private String picture;
	
	// 商品id
	private Integer goodsId;
	
}
