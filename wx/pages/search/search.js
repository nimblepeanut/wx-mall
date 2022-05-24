// pages/search/search.js
Page({

   /**
    * 页面的初始数据
    */
   data: {

      // 取消按钮
      isFocus: false,

      // 输入的值
      value: null,

      // 输入框的值
      inpValue: "",

      // 商品列表
      goodsList: [],

      // 防抖-定时器
      TimeId: -1,

   },

   /**
    * 生命周期函数--监听页面加载
    */
   onLoad(options) {

      // 使用本地缓存 开始

      // 获取本地储存中的数据 商品
      const LocalGoodsList = wx.getStorageSync('localGoodsList')

      // 判断本地储存中有没有旧数据 开始

      if (!LocalGoodsList) {

         // 所属分类的商品
         this.getGoodsLIst();

      }

      // 判断本地储存中有没有旧数据 结束

      // 判断是否过期 开始

      // 持续时间
      var DurationGoodsList = Date.now() - LocalGoodsList.time;

      // 单位：毫秒
      // 3分钟过期
      if (DurationGoodsList > 1000 * 1 * 60 * 3) {

         // 重新发送请求
         this.getGoodsLIst();

      } else {

         // console.log(LocalGoodsList.data);

         // 赋值
         this.setData({

            // 填充商品的内容
            goodsList: LocalGoodsList.data,

         });

      }

      // 判断是否过期 结束

      // 使用本地缓存 结束

   },

   // 输入框的值改变就会触发的事件 开始
   handleInput(e) {

      // 显示取消按钮
      this.setData({
         isFocus: true,
         value: e.detail.value
      })

      // 防抖 开始

      // 准备发送请求获取数据
      clearTimeout(this.TimeId);

      // 主体 TimeId 方法
      this.TimeId = setTimeout(() => {

         // 根据名称查
         this.getSelectByName();

      }, 1000);

      // 防抖 结束

   },
   // 输入框的值改变就会触发的事件 结束

   // 根据名称查 开始
   getSelectByName() {

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

         url: 'http://www.qxf.world/goods/selectByName',

         method: "GET",

         data: {
            name: that.data.value
         },

         // 成功回调 开始
         // res是response的缩写，用来接收服务端返回的数据
         success: function (res) {

            // code=0成功
            //         =-1失败，错误信息为msg

            // 判断-成功 开始
            if (res.data.code == 0) {

               console.log(res.data.data);

               // 给分类列表赋值 categoryList
               that.setData({
                  goodsList: res.data.data
               })

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

   },
   // 根据名称查 结束

   // 点击-取消按钮 开始
   handleCancel() {
      this.setData({
         inpValue: "",
         isFocus: false,
      }),
         // 重新给商品列表赋值
         getGoodsLIst()
   },
   // 点击-取消按钮 结束

   // 获取商品列表 开始

   getGoodsLIst() {

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

         url: 'http://www.qxf.world/goods/all',

         method: "GET",

         // 成功回调 开始
         // res是response的缩写，用来接收服务端返回的数据
         success: function (res) {

            // code=0成功
            //         =-1失败，错误信息为msg

            // 判断-成功 开始
            if (res.data.code == 0) {

               // 给分类列表赋值 categoryList
               that.setData({
                  goodsList: res.data.data
               })

               // !!一定要有这个!!
               // var that = this;

               // 把接口里的数据存入本地缓存： localGoodsList
               //    当前时间：Date.now()
               //    that.data.goodsList
               wx.setStorageSync('localGoodsList', { time: Date.now(), data: that.data.goodsList });

               // console.log(that.data.goodsList);

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

   },

   // 获取商品列表 结束   










})