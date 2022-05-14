// pages/addressAdd/addressAdd.js
Page({

   /**
    * 页面的初始数据
    */
   data: {

      // 收货人
      consignee: '',

      // 手机号码
      phone: '',

      // 所在地区
      address: '',

      // 详细地址
      name: '',

      // 用户id
      userId: ''

   },

   // 选择收货地址 开始
   getLocation: function () {

      var that = this;

      // 打开地图选择位置 开始
      wx.chooseLocation({

         success: function (res) {

            // 获取值
            // 所在地区
            var address = res.address
            // 详细地址
            var name = res.name

            // 赋值
            that.setData({
               address,
               name
            })

            // 弹窗 开始
            wx.showToast({
               // 图表
               icon: 'success',
               // 标题
               title: '选择成功',
            })
            // 弹窗 结束

         }

      })
      // 打开地图选择位置 结束

   },
   // 选择收货地址 结束

   // 添加-收货地址 开始
   addrAdd: function (options) {

      const userId = getApp().globalData.user.id

      console.log(userId)

      console.log(options.detail.value);

      // 网络请求 开始
      wx.request({

         url: 'http://localhost:5000/addr',

         method: "POST",

         // 请求头
         // 在get请求中可有可无，但post必须加
         // header: {
         //    // 默认值
         //    'content-type': 'application/x-www-form-urlencoded'
         // },

         // md，还得用json的格式啊，
         // csdn千篇一律和抄袭的文章太多，误导人
         header: {
            'Content-Type': 'application/json'
         },

         // 传递给服务器
         data: {

            // 收货人
            consignee: options.detail.value.consignee,
            // 手机号码
            phone: options.detail.value.phone,
            // 所在地区
            address: options.detail.value.address,
            // 详细地址
            name: options.detail.value.name,
            // 用户id
            userId

         },

         // 成功回调
         // res是response的缩写，用来接收服务端返回的数据
         success: function (res) {

            // code=1成功
            //         =-1失败，错误信息为msg
            // console.log(res.data);

            // 用户注册信息
            var addr = res.data;

            console.log(addr);

            // 判断-成功 开始
            // 这里是1，代表被影响的行数
            if (addr.code == 1) {

               // 弹窗 开始
               wx.showToast({
                  // 图表
                  icon: 'success',
                  // 标题
                  title: '添加成功',

                  // 跳转到登录页面 开始

                  success: function () {

                     // 延迟800毫秒 开始

                     setTimeout(function () {

                        // tabBat页
                        // wx.switchTab({

                        //非 tabBar页
                        wx.navigateTo({
                           url: '/pages/addr/addr',
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

      // 网络请求 结束

   }
   // 添加-收货地址 结束


























})