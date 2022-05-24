// pages/cart/cart.js
Page({

   /**
    * 页面的初始数据
    */
   data: {

      // 用户id
      userId: "",

      // 购物车列表
      cartList: [],

      // 总数
      totalNum: 0,

      // 总价
      totalPrice: 0,

   },

   /**
    * 生命周期函数--监听页面加载
    */
   onLoad: function (options) {

      // 购物车的内容
      this.cartAll()

      this.total()

   },
   // 生命周期函数--监听页面加载

   onShow() {

      // 购物车的内容
      this.cartAll();

   },

   // 总数 开始
   total() {

      // !!一定要有这个!!
      var that = this;


      // 总数
      let totalNum = 0;
      // 总价
      let totalPrice = 0;

      let { cartList } = that.data;

      // console.log(cartList);

      // 计算求值
      cartList.forEach(v => {

         // console.log(v.count);

         // 总数
         totalNum += v.count;
         // 总价
         totalPrice += v.count * v.goods.price;

      })

      console.log(totalNum);
      console.log(totalPrice);

      // 给data赋值
      that.setData({
         totalNum,
         totalPrice
      })

   },
   // 总数 结束

   // 购物车的内容 开始
   cartAll() {

      // 用户id
      const userId = getApp().globalData.user.id;

      //   根据用户id查购物车 开始

      // loading 提示框，
      //    开启加载中的图标
      wx.showLoading({
         title: '加载中',
         mask: true
      });

      // !!一定要有这个!!
      var that = this;

      // 网络请求 开始
      wx.request({

         url: 'http://www.qxf.world/cart',

         method: "GET",

         data: {
            userId
         },

         // 成功回调 开始
         success: function (res) {

            // console.log(res.data.data);

            // code=0成功
            //         =-1失败，错误信息为msg

            // 判断-成功 开始
            if (res.data.code == 0) {

               // 给购物车列表赋值 cartList
               that.setData({
                  cartList: res.data.data
               })

               // 计算总数和价格
               that.total();

            }
            // 判断-成功 结束

            // 判断-失败 开始
            if (res.data.code == -1) {

               // 弹窗 开始
               wx.showToast({
                  // 图表
                  icon: 'error',
                  // 标题
                  title: res.data.msg,
               })
               // 弹窗 结束

            }
            // 判断-失败 结束

         },
         // 成功回调 结束

         // 请求完成，不管是否成功
         complete: function () {
            // 隐藏 loading 提示框
            wx.hideLoading();
         }

      })
      // 网络请求 结束


      // 根据用户id查购物车 结束

   },
   // 购物车的内容 结束

   // 加减商品数量 开始
   handEditNum(e) {

      // 获取传递过来的参数：操作和商品id
      const { operation, id } = e.currentTarget.dataset;
      // console.log(operation, id);

      // 获取购物车数组
      let { cartList } = this.data;

      console.log(cartList);

      // 找到需要修改的商品的索引
      const index = cartList.findIndex(v => v.id == id);

      // 修改数量
      cartList[index].count += operation;

      // console.log(cartList[index].goods);

      // 数量判断 开始
      if (cartList[index].count > 0 && cartList[index].count <= cartList[index].goods.count) {

         // !!一定要有这个!!
         var that = this;

         // 设置回data中
         that.setData({
            cartList
         })

         // 计算总数和价格
         that.total();

      } else if (cartList[index].count < 1) {

         // 弹窗 开始
         wx.showToast({
            // 图表
            icon: 'error',
            // 标题
            title: '不能少于1件',
         })
         // 弹窗 结束

         return;

      } else if (cartList[index].count > cartList[index].goods.count) {

         // 弹窗 开始
         wx.showToast({
            // 图表
            icon: 'error',
            // 标题
            title: '不能大于库存',
         })
         // 弹窗 结束

         return;

      }

      // 数量判断 结束

   },
   // 加减商品数量 结束

   // 结算 开始
   handlePay() {

      //   清空购物车 开始

      // loading 提示框，
      //    开启加载中的图标
      wx.showLoading({
         title: '加载中',
         mask: true
      });

      // 网络请求 开始
      wx.request({

         url: 'http://www.qxf.world/cart',

         method: "PUT",

         // 请求头
         // 在get请求中可有可无
         header: {
            // 默认值
            'content-type': 'application/x-www-form-urlencoded'
         },

         // header: {
         //    'Content-Type': 'application/json'
         // },

         data: {
            // 用户id
            userId: getApp().globalData.user.id
         },

         // 成功回调 开始
         success: function (res) {

            // console.log(res.data.data);

            // code=0成功
            //         =-1失败，错误信息为msg

            // 判断-成功 开始
            if (res.data.code == 0) {

               // 重查购物车
               this.cartAll();

               // 重查用户
               this.userId();

               // 弹窗 开始
               wx.showToast({
                  // 图表
                  icon: 'success',
                  // 标题
                  title: 付款成功,
               })
               // 弹窗 结束

            }
            // 判断-成功 结束

            // 判断-失败 开始
            if (res.data.code == -1) {

               // 弹窗 开始
               wx.showToast({
                  // 图表
                  icon: 'error',
                  // 标题
                  title: res.data.msg,
               })
               // 弹窗 结束

            }
            // 判断-失败 结束

         },
         // 成功回调 结束

         // 请求完成，不管是否成功
         complete: function () {
            // 隐藏 loading 提示框
            wx.hideLoading();
         }

      })
      // 网络请求 结束


      // 清空购物车 结束

   },
   // 结算 结束


   //   登录 开始

   userId: function () {

      // 网络请求
      wx.request({

         url: 'http://www.qxf.world/user/seleById',

         method: "GET",

         // 传递给服务器
         data: {
            id: getApp().globalData.user.id,
         },

         // 成功回调
         // res是response的缩写，用来接收服务端返回的数据
         success: function (res) {

            // code=0成功
            //         =-1失败，错误信息为msg

            // 用户登录信息
            var user = res.data;

            // 判断-成功 开始
            if (user.code == 0) {

               // 将用户信息添加到app.js全局变量中
               getApp().globalData.user = user.data;

            }
            // 判断-成功 结束

            // 判断-失败 开始
            if (user.code == -1) {

               // 弹窗 开始
               wx.showToast({
                  // 图表
                  icon: 'error',
                  // 标题
                  title: user.msg,
               })
               // 弹窗 结束

            }
            // 判断-失败 结束

         }

      })

   }

   // 登录 结束


















})