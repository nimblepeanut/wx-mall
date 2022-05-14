// pages/login/login.js
Page({

   /**
    * 页面的初始数据
    */
   data: {

   },

   /**
    * 生命周期函数--监听页面加载
    */
   login: function (options) {

      var value = options.detail.value;

      if (value.password != value.confirmPassword) {

         // 弹窗 开始
         wx.showToast({

            icon: 'error',

            title: '两次密码不一致！',
         })
         // 弹窗 结束

      } else {

         // 网络请求 开始
         wx.request({

            url: 'http://localhost:5000/user',

            method: "POST",

            // 请求头
            // 在get请求中可有可无，但post必须加
            header: {
               // 默认值
               'content-type': 'application/x-www-form-urlencoded'
            },

            // 传递给服务器
            data: {
               // 用户名
               username: options.detail.value.username,
               // 密码
               password: options.detail.value.password,
               // 头像
               headPortrait: options.detail.value.headPortrait
            },

            // 成功回调
            // res是response的缩写，用来接收服务端返回的数据
            success: function (res) {

               // code=0成功
               //         =-1失败，错误信息为msg
               // console.log(res.data);

               // 用户注册信息
               var user = res.data;

               console.log(user);

               // 判断-成功 开始
               // 这里是1，代表被影响的行数
               if (user.code == 1) {

                  // 弹窗 开始
                  wx.showToast({
                     // 图表
                     icon: 'success',
                     // 标题
                     title: '注册成功',

                     // 跳转到登录页面 开始

                     success: function () {

                        // 延迟800毫秒 开始

                        setTimeout(function () {

                           // tabBat页
                           // wx.switchTab({

                           //非 tabBar页
                           wx.navigateTo({
                              url: '/pages/login/login',
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

   }

})