package com.qxf.mall.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 购物车
 * @author 秦晓枫
 *
 */

@Getter
@Setter
public class Cart {

	// id
	private Integer id;
	
	// 用户id
	private Integer userId;
	
	// 商品id
	private Integer goodsId;
	
	
}
