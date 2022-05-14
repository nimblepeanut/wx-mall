// pages/login/login.js
Page({

   /**
    * 页面的初始数据
    */
   data: {

   },

   //   登录 开始

   login: function (options) {

      // 网络请求
      wx.request({

         url: 'http://localhost:5000/user',

         method: "GET",

         // 传递给服务器
         data: {
            username: options.detail.value.username,
            password: options.detail.value.password
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
               var app = getApp();
               app.globalData.user = user.data;

               // console.log(app.globalData.use);

               // 弹窗 开始
               wx.showToast({
                  // 图表
                  icon: 'success',
                  // 标题
                  title: '您好，' + user.data.username,

                  // 跳转到tabber页面-首页 开始

                  success: function () {

                     // 延迟800毫秒 开始

                     setTimeout(function () {

                        wx.switchTab({
                           url: '../index/index',
                        })

                     }, 800)

                     // 延迟800毫秒 结束

                  }

                  // 跳转到tabber页面-首页 结束

               })
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

   }

   // 登录 结束



})