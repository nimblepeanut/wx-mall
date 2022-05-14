// pages/personal/personal.js
Page({

   /**
    * 页面的初始数据
    */
   data: {

      // 头像
      headPortrait: null,

      // 用户名
      username: null,

      // 余额
      money: null,

   },

   /**
    * 生命周期函数--监听页面加载
    */
   onLoad: function (options) {

      // 保存在全局变量中的用户信息
      var appUser = getApp().globalData.user;

      console.log(appUser);

      // 赋值
      this.setData({
         // 头像
         headPortrait: appUser.headPortrait,
         // 用户名
         username: appUser.username,
         // 余额
         money: appUser.money,
      })

   },


})




