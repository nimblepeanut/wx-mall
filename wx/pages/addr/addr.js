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
      addrList: [],

      // 防抖-定时器
      TimeId: -1,

      // 用户id
      userId: "",

      // 商品id
      goodsId: "",

      // 地址id
      addrId: "",


   },

   /**
    * 生命周期函数--监听页面加载
    */
   onLoad(options) {

      // 用户id
      this.data.userId = getApp().globalData.user.id;

      // 商品id
      this.data.goodsId = Number(options.goodsId);
      // console.log(this.data.goodsId);

      // 使用本地缓存 开始

      // 获取本地储存中的数据 商品
      const LocalAddrList = wx.getStorageSync('localAddrList')

      // 判断本地储存中有没有旧数据 开始

      if (!LocalAddrList) {

         // 所属分类的商品
         this.getAddrLIst();

      }

      // 判断本地储存中有没有旧数据 结束

      // 判断是否过期 开始

      // 持续时间
      var DurationAddrList = Date.now() - LocalAddrList.time;

      // 单位：毫秒
      // 3分钟过期
      if (DurationAddrList > 1000 * 1 * 60 * 3) {

         // 重新发送请求
         this.getAddrLIst();

      } else {

         // console.log(LocalAddrList.data);

         // 赋值
         this.setData({

            // 填充商品的内容
            addrList: LocalAddrList.data,

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

      // 用户id
      const userId = getApp().globalData.user.id

      // 网络请求 开始
      wx.request({

         url: 'http://www.qxf.world/addr',

         method: "GET",

         data: {
            name: that.data.value,
            userId
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
                  addrList: res.data.data
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
         getAddrLIst()
   },
   // 点击-取消按钮 结束

   // 获取商品列表 开始

   getAddrLIst() {

      // loading 提示框，
      //    开启加载中的图标
      wx.showLoading({
         title: '加载中',
         mask: true
      });

      // !!一定要有这个!!
      var that = this;

      // 用户id
      const userId = getApp().globalData.user.id

      // 网络请求 开始
      wx.request({

         url: 'http://www.qxf.world/addr',

         method: "GET",

         // 该用户的收货地址
         data: {
            userId
         },

         // 成功回调 开始
         // res是response的缩写，用来接收服务端返回的数据
         success: function (res) {

            // code=0成功
            //         =-1失败，错误信息为msg

            // 判断-成功 开始
            if (res.data.code == 0) {

               // 给分类列表赋值 categoryList
               that.setData({
                  addrList: res.data.data
               })

               // !!一定要有这个!!
               // var that = this;

               // 把接口里的数据存入本地缓存： localAddrList
               //    当前时间：Date.now()
               //    that.data.addrList
               wx.setStorageSync('localAddrList', { time: Date.now(), data: that.data.addrList });

               // console.log(that.data.addrList);

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

   // 添加购物车 开始
   addCart(options) {

      // console.log(options.currentTarget.dataset);

      // 获取绑定的地址id
      this.data.addrId = options.currentTarget.dataset.addrid;

      // console.log(this.data);

      // 如果有商品id就跳转
      if (this.data.goodsId > 0) {

         // 放到全局变量中
         // var app = getApp();
         // app.globalData.cartList = this.data.cart;

         // 网络请求-加到购物车 开始

         wx.request({

            url: 'http://www.qxf.world/cart',

            method: "POST",

            // 请求头
            // 在get请求中可有可无，但post必须加
            // header: {
            //    // 默认值
            //    'content-type': 'application/x-www-form-urlencoded'
            // },

            header: {
               'Content-Type': 'application/json'
            },

            // 传递给服务器
            data: {

               // 用户id
               userId: this.data.userId,

               // 商品id
               goodsId: this.data.goodsId,

               // 地址id
               addrId: this.data.addrId

            },

            // 成功回调
            // res是response的缩写，用来接收服务端返回的数据
            success: function (res) {

               // code=1成功
               //         =-1失败，错误信息为msg
               // console.log(res.data);

               var addr = res.data;

               // console.log(cart);

               // 判断-成功 开始
               // 这里是1，代表被影响的行数
               if (addr.code == 1) {

                  // 弹窗 开始
                  wx.showToast({
                     // 图表
                     icon: 'success',
                     // 标题
                     title: '添加成功',

                     // 跳转到购物车 开始

                     success: function () {

                        // 延迟800毫秒 开始

                        setTimeout(function () {

                           // 跳转到购物车，
                           // 是tabBar页面，必须用 switchTab !!!!!!!
                           wx.switchTab({
                              url: '/pages/cart/cart'
                           })

                        }, 800)

                        // 延迟800毫秒 结束

                     }

                     // 跳转到登录页面 结束

                  })
                  // 弹窗 结束

               }
               // 判断-成功 结束

               // 判断-失败 开始
               if (addr.code == -1) {

                  // 弹窗 开始
                  wx.showToast({
                     // 图表
                     icon: 'error',
                     // 标题
                     title: addr.msg,
                  })
                  // 弹窗 结束

               }
               // 判断-失败 结束

            }

         })

         // 网络请求-加到购物车 结束

      }
      // 添加购物车 结束

   },


   // 添加购物车







})