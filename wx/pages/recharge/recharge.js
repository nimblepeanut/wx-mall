// pages/recharge/recharge.js
Page({

   /**
    * 页面的初始数据
    */
   data: {

      // 用户名
      username: '',

      // 用户
      user: ''

   },

   /**
    * 生命周期函数--监听页面加载
    */
   onLoad(options) {

      // 获取全局变量中的用户名
      let username = getApp().globalData.user.username

      // 赋值给data
      this.setData({
         username
      })

   },

   // 充值 开始
   recharge: function (options) {

      console.log(options.detail.value.rechargeMoney);

      // 判断金额大于0 开始
      if (options.detail.value.rechargeMoney <= 0) {

         // 弹窗 开始
         wx.showToast({
            // 图表
            icon: 'error',
            // 标题
            title: '请输入正确的金额',
         })
         // 弹窗 结束

         return;

      } else {

         // 网络请求 开始
         wx.request({

            url: 'http://localhost:5000/user',

            method: "PUT",

            // 请求头
            // 在get请求中可有可无，但PUT必须加
            header: {
               // 默认值
               'content-type': 'application/x-www-form-urlencoded'
            },

            // 传递给服务器
            data: {
               userId: getApp().globalData.user.id,
               money: Number(Number(getApp().globalData.user.money)+Number(options.detail.value.rechargeMoney))
            },

            // 成功回调
            // res是response的缩写，用来接收服务端返回的数据
            success: function (res) {

               // code=0成功
               //         =-1失败，错误信息为msg
               console.log(res.data);

               // 用户信息都在 res.data.data 中
               // console.log(res.data.data);

               // 用户登录信息
               var user = res.data;

               //  console.log(user.data.username);

               // 判断-成功 开始
               if (user.code == 0) {

                  // 将用户信息添加到app.js全局变量中
                  getApp().globalData.user = user.data;

                  console.log(getApp().globalData.user);

                  // 弹窗 开始
                  wx.showToast({
                     // 图表
                     icon: 'success',
                     // 标题
                     title: '就当你充值了',

                     // 跳转到tabber页面-首页 开始

                     success: function () {

                        // 延迟800毫秒 开始

                        setTimeout(function () {

                           wx.switchTab({
                              url: '/pages/personal/personal',
                           })

                        }, 800)

                        // 延迟800毫秒 结束

                     }

                     // 跳转到tabber页面-首页 结束

                  });
                  // 弹窗 结束

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
         // 网络请求 结束

      }
      // 判断金额大于0 结束

   }

   // 充值 结束




})