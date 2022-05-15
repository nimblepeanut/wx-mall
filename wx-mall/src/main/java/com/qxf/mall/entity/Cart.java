package com.qxf.mall.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 	购物车
 * @author 秦晓枫
 *
 */

@Getter
@Setter
public class Cart {

	// id
	private Integer id;
	
	// 用户
	private User user;
	// 用户id
	private Integer userId;
	
	// 商品
	private Goods goods;
	// 商品id
	private Integer goodsId;
	
	// 地址
	private Addr addr;
	// 地址id
	private Integer addrId;
	
	// 商品数量
	private Integer count;
	
	// 状态
	// 0：未支付
	// 1：支付
	private Integer state;

	@Override
	public String toString() {
		return "Cart [id=" + id + ", user=" + user + ", userId=" + userId + ", goods=" + goods + ", goodsId=" + goodsId
				+ ", addr=" + addr + ", addrId=" + addrId + ", count=" + count + ", state=" + state + "]";
	}
	
	
	
	
	
	
	
	
	
}
