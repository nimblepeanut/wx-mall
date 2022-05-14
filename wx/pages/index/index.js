Page({

   /**
    * 页面的初始数据
    */
   data: {

      // 推荐列表
      recommendList: [],

      List: [

         {
           name: "商品列表",
           image_url: "/pages//",
           image_src: "/icons/商品.png"
         },
   
         {
           name: "往期订单",
           image_url: "/pages//",
           image_src: "/icons/订单.png"
         },
   
         {
           name: "余额充值",
           image_url: "/pages/recharge/recharge",
           image_src: "/icons/充值.png"
         },
   
         {
           name: "热销商品",
           image_url: "/pages//",
           image_src: "/icons/热销.png"
         }
   
       ],

      // 分类列表
      categoryList: []

   },

   /**
    * 生命周期函数--监听页面加载
    */
   onLoad: function (options) {

      // !!一定要有这个!!
      var that = this;

      // 推荐-轮播图 开始

      wx.request({

         url: 'http://localhost:5000/goods/selectByRecommend',

         method: "GET",

         // 成功回调 开始
         // res是response的缩写，用来接收服务端返回的数据
         success: function (res) {

            // code=0成功
            //         =-1失败，错误信息为msg

            // 判断-成功 开始
            if (res.data.code == 0) {

               // 给商品推荐列表赋值 recommendList
               that.setData({
                  recommendList: res.data.data
               })

               // console.log(that.data.recommendList);

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

         }
         // 成功回调 结束

      }),

      // 推荐-轮播图 结束

      // 分类 开始

      wx.request({

         url: 'http://localhost:5000/category/recommend',

         method: "GET",

         // 成功回调 开始
         // res是response的缩写，用来接收服务端返回的数据
         success: function (res) {

            // code=0成功
            //         =-1失败，错误信息为msg

            // 判断-成功 开始
            if (res.data.code == 0) {

               // 将分类列表赋值给 categoryList
               that.setData({
                  categoryList: res.data.data
               })

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

         }
         // 成功回调 结束

      })

      // 分类 结束




























   },









   /**
    * 生命周期函数--监听页面初次渲染完成
    */
   onReady: function () {

   },

   /**
    * 生命周期函数--监听页面显示
    */
   onShow: function () {

   },

   /**
    * 生命周期函数--监听页面隐藏
    */
   onHide: function () {

   },

   /**
    * 生命周期函数--监听页面卸载
    */
   onUnload: function () {

   },

   /**
    * 页面相关事件处理函数--监听用户下拉动作
    */
   onPullDownRefresh: function () {

   },

   /**
    * 页面上拉触底事件的处理函数
    */
   onReachBottom: function () {

   },

   /**
    * 用户点击右上角分享
    */
   onShareAppMessage: function () {

   }
})