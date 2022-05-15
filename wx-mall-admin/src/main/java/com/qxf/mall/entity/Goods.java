package com.qxf.mall.entity;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Goods {
	
	// id
	private Integer id;
	
	// categoryId
	private Integer categoryId;

    // 分类类
    // 关联商品所属分类：一对一关系
	private Category category;
	
	// 商品名
	private String name;
	
	// 价格
	private BigDecimal price;
	
	// 库存
	private Integer count;
	
	// 图片类
	// 一对多
	// 	注意，一对多查询不需要goodsPictureId，用商品id就行
	private List<GoodsPicture> goodsPicture;
	
	// 介绍
	private String introduce;
	
	// 推荐
	// 	0：不推荐
	//	1：推荐
	private Integer recommend;
	
	// 状态
	// 	0：删除
	//	1：上架
	private Integer state;
	
	// 购物车id
	private Integer cartId;

}
