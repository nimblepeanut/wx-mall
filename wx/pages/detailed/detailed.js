// pages/detailed/detailed.js
Page({

   /**
    * 页面的初始数据
    */
   data: {

      // 商品
      goods: {},

      // 商品是否被收藏
      isCollect: false

   },

   // 生命周期函数--监听页面加载 开始
   onLoad: function (options) {

      console.log(options.goodsId);

      //  根据id查商品 开始

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

         url: 'http://localhost:5000/goods/selectById',

         method: "GET",

         data: {
            id: options.goodsId
         },

         // 成功回调 开始
         // res是response的缩写，用来接收服务端返回的数据
         success: function (res) {

            // console.log(res.data.data);

            // code=0成功
            //         =-1失败，错误信息为msg

            // 判断-成功 开始
            if (res.data.code == 0) {

               // 给分类列表赋值 categoryList
               that.setData({
                  goods: res.data.data
               })

               console.log(that.data.goods);

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

      // 根据id查商品 结束

   },
   // 生命周期函数--监听页面加载 结束

   // 点击图片-放大预览 开始
   handlePrevewImage(e) {

      // 图片列表
      const urls = this.data.goods.goodsPicture.map(v => v.picture);
      // console.log(urls);

      // 现在的图片
      const current = e.currentTarget.dataset.url;

      // 放大预览图片
      wx.previewImage({
         // 现在的图片
         current,
         // 图片列表
         urls
      })

   },
   // 点击图片-放大预览 结束

   // 点击-商品收藏图标 开始
   handleCollect() {

      var that = this;

      var collect = that.data.isCollect

      if (collect == false) {

         // 收藏 开始

         wx.showToast({
            title: '收藏成功',
            icon: 'success',
            mask: true
         });

         // 修改data中的属性  isCollect
         that.setData({
            isCollect: true
         })

         // 收藏 结束

      } else {

         // 取消收藏 开始

         wx.showToast({
            title: '取消收藏',
            icon: 'success',
            mask: true
         });

         // 修改data中的属性  isCollect
         that.setData({
            isCollect: false
         })

         // 取消收藏 结束

      }

   },
   // 点击-商品收藏图标 结束








})