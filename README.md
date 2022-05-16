# 项目简介
项目使用前后台分离的设计方式、RESTful的设计风格开发。<br>
后台采用Layui框架搭建页面，Ajax 异步请求、Thymeleaf 模板引擎搭配使用。<br>
前台为微信小程序
# 后台管理系统
## 1. 登录
![请添加图片描述](https://img-blog.csdnimg.cn/746786c90b5943f0b23e2c3d98fc300b.png)
## 2. 主页
![请添加图片描述](https://img-blog.csdnimg.cn/70699bcd3f5c4fb0b094b45ce51b1b4e.png)
## 3. 管理员账号
![请添加图片描述](https://img-blog.csdnimg.cn/163ef63040ba4393947126b7254bd507.png)
## 4. 用户列表
![请添加图片描述](https://img-blog.csdnimg.cn/d3151ffff8e047e2a0ab01d0fbc1bab9.png)
## 5. 用户注册
![请添加图片描述](https://img-blog.csdnimg.cn/c98a4fa0fa8547d4872b441b43fb34f3.png)
![请添加图片描述](https://img-blog.csdnimg.cn/3d59fccd88d04f2b96980168ec0c5f24.png)
## 6. 商品列表
![请添加图片描述](https://img-blog.csdnimg.cn/3869b675a8a4422daebde0f205a4815b.png)

## 7. 商品分页和查询
![请添加图片描述](https://img-blog.csdnimg.cn/29c49331b9b1427dad817a809d42d6fb.png)

## 8. 添加商品
![请添加图片描述](https://img-blog.csdnimg.cn/1f7c1df55baf4bb8a197a2eb7f9b1dbb.png)

## 9. 修改商品
![请添加图片描述](https://img-blog.csdnimg.cn/8da2faae8c504ca884c5a590b643a433.png)

## 10. 分类列表
![请添加图片描述](https://img-blog.csdnimg.cn/21efeb3376e04f188a0500ba1ea15a9f.png)

## 11. 分类信息统计
![请添加图片描述](https://img-blog.csdnimg.cn/24e6a5149e4c4f1f8a9236736c1bc116.png)
![请添加图片描述](https://img-blog.csdnimg.cn/bfc09258907548029f2b8ff86a7a741c.png)
# 前台微信小程序
## 1. 登录
![请添加图片描述](https://pic.imgdb.cn/item/6281ac9509475431298bc665.png)
## 2. 注册
![请添加图片描述](https://pic.imgdb.cn/item/6281ac9609475431298bc671.png)
## 3. 首页
![请添加图片描述](https://img-blog.csdnimg.cn/83fc888b211c47baa2146802cbef931e.png)
![请添加图片描述](https://img-blog.csdnimg.cn/2baf392c25b9481b8a8f9dd338d2b7da.png)

## 4. 搜索中心
![请添加图片描述](https://img-blog.csdnimg.cn/580d6197a2d3426ea000911e4cf3f9ed.png)

## 5. 商品分类
![请添加图片描述](https://img-blog.csdnimg.cn/eb45e23d61074781a924e42f75c79927.png)
![请添加图片描述](https://img-blog.csdnimg.cn/02770aa6facf41d685c37d54db14d706.png)

## 6. 我的账号
![请添加图片描述](https://img-blog.csdnimg.cn/a517e47b80444cfbbde9ca5daddb8529.png)
## 7. 充值中心
![请添加图片描述](https://img-blog.csdnimg.cn/8ead93e383774ba78aa565378e662f89.png)
![请添加图片描述](https://img-blog.csdnimg.cn/fb0d1e9e11b3461a8ef2c0050356b853.png)
## 8. 商品详情
![请添加图片描述](https://img-blog.csdnimg.cn/bc864c1b22cc4f4d8f9130d8fd2d7b56.png)
![请添加图片描述](https://img-blog.csdnimg.cn/ff98413835ba4d3fbd7259e9f6aed48d.png)

## 9. 收获地址
![请添加图片描述](https://img-blog.csdnimg.cn/4d4ef400d8274ff99f1deab50ad3f8f0.png)

![请添加图片描述](https://img-blog.csdnimg.cn/184ea625af3f4a9a8b0b840a05f9c30e.png)
![请添加图片描述](https://img-blog.csdnimg.cn/75e7950727ac45c4abae1f06f8b542c0.png)
![请添加图片描述](https://img-blog.csdnimg.cn/9423534ed8de471aa3750d0f5c77e35c.png)

## 10. 购物车
![请添加图片描述](https://img-blog.csdnimg.cn/d94f02e74cb74532a05fe0d45378732d.png)
![请添加图片描述](https://img-blog.csdnimg.cn/cb0e8eacee664531b863bb3774ad6506.png)
![请添加图片描述](https://img-blog.csdnimg.cn/c41ec3584c754a5783e31d88aa98deff.png)
```java
	/**
	 * 	付款
	 * 
	 * 	为防止超买超卖并且涉及到金钱，
	 * 	数据库事物的隔离级别调到串行化，
	 * 	使用表级共享锁：sql语句执行一句就加一个锁，
	 * 	报错就回滚
	 */
	@Override
	@Transactional(
		isolation = Isolation.SERIALIZABLE, 
		rollbackFor = Exception.class
	)
	public Integer payment(Integer userId) {

		// 购物车
		List<Cart> cartList = cartMapper.all(userId);
	
		// 总金额,初始值为0
		BigDecimal totalPrice = new BigDecimal("0");
		
		// 遍历修改每个商品的数量
		for (Cart cart : cartList) {
			// 减少对应商品的库存	 
			goodsMapper.updateCount(cart.getGoods().getId(), cart.getCount());
			
			// 注意：BigDecimal不能用 "*"、"+"等运算，必须用方法
			// multiply：乘，
			// 需要用 new BigDecimal() 方法把数量从Integer转为BigDecimal
			BigDecimal newNum = cart.getGoods().getPrice().multiply(new BigDecimal(cart.getCount()));
			// add：加
			totalPrice = totalPrice.add(newNum);
		}
		
		// 修改用户余额
		Integer userMoney = userMapper.updateMoney(userId, totalPrice);
		
		// 清空购物车，
		// 修改状态 state = 1
		Integer emptyCart = cartMapper.updateState(userId);
		
		// 判断是否成功
		if (userMoney > 0 && emptyCart > 0) {
			return 1;
		} else {
			return 0;
		}
		
	}
```
