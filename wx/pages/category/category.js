// pages/category/category.js
Page({

   /**
    * 页面的初始数据
    */
   data: {

      // 分类列表
      categoryList: [],

      // 当前点击的索引，
      // 目前为不选中的状态
      currentIndex: -1,

      // 商品列表
      goodsList: [],

      // 右侧内容的滚动条距离顶部的距离
      scrollTop: 0,

   },

   // 生命周期函数--监听页面加载

   onLoad: function (options) {

      // 使用本地缓存 开始

      // 获取本地储存中的数据 分类
      const LocalCategoryList = wx.getStorageSync('localCategoryList')

      // 获取本地储存中的数据 商品
      const LocalGoodsList = wx.getStorageSync('localGoodsList')

      // 判断本地储存中有没有旧数据 开始
      if (!LocalCategoryList || !LocalGoodsList) {

         // 所属分类的商品
         this.getCategory();

         // !!一定要有这个!!
         var that = this;

         // 没点击时全显示
         if (that.data.currentIndex == -1) {

            // 所有的商品
            this.getGoodsAll();

         }

      }

      // 判断本地储存中有没有旧数据 结束

      // 判断是否过期 开始

      // 持续时间
      var DurationCategoryList = Date.now() - LocalCategoryList.time;
      var DurationGoodsList = Date.now() - LocalGoodsList.time;

      // 单位：毫秒
      // 3分钟过期
      if (DurationCategoryList > 1000 * 1 * 60 * 3 || DurationGoodsList > 1000 * 1 * 60 * 3) {

         // 重新发送请求
         this.getCategory();
         this.getGoodsAll();

      } else {

         // console.log(LocalCategoryList.data);
         // console.log(LocalGoodsList.data);

         // 赋值
         this.setData({

            // 填充分类的内容
            categoryList: LocalCategoryList.data,
            // 填充商品的内容
            goodsList: LocalGoodsList.data,

            // 重新设置scroll-view距离顶部为0
            scrollTop: 0,

         });

      }

      // 判断是否过期 结束

      // 使用本地缓存 结束

   },

   // 生命周期函数--监听页面加载

   // 获取分类数据 开始
   getCategory() {

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

         url: 'http://www.qxf.world/category/all',

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
                  categoryList: res.data.data
               }),

                  // 把接口里的数据存入本地缓存： localCategoryList
                  //    当前时间：Date.now()
                  //    that.data.categoryList
                  wx.setStorageSync('localCategoryList', { time: Date.now(), data: that.data.categoryList });

               // console.log(that.data.categoryList);

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
   // 获取分类数据 结束

   // 没点击时全显示 开始

   getGoodsAll() {

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

   // 没点击时全显示 结束

   // 分类的点击事件 开始

   clickCategory(e) {

      // console.log(e);

      const { index } = e.currentTarget.dataset;

      // 给当前点击的商品列表赋值 goodsList
      let goodsList = this.data.categoryList[index].goods;

      // 赋值
      this.setData({

         // 给当前点击的索引赋值 currentIndex
         currentIndex: index,

         // 填充商品列表的内容
         goodsList,

         // 重新设置scroll-view距离顶部为0
         scrollTop: 0,

      });

      console.log(goodsList);

   }

   // 分类的点击事件 结束

})