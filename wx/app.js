// app.js
App({



   onLaunch() {

      // 背景音乐
      this.setAudioPlay()

      // 展示本地存储能力
      const logs = wx.getStorageSync('logs') || []
      logs.unshift(Date.now())
      wx.setStorageSync('logs', logs)

      // 登录
      wx.login({
         success: res => {
            // 发送 res.code 到后台换取 openId, sessionKey, unionId
         }
      })
   },
   
   globalData: {

      userInfo: null,

      // 用户信息
      user: {},

      // 购物车列表
      cartList: [
         {
            // 地址id
            addrId: null,
             // 商品id
            goodsId: null,
             // 用户id
            userId: null,
         }
      ]

   },

   // 背景音乐 秋日私语

   setAudioPlay() {
      const adctx = wx.createInnerAudioContext()
      adctx.autoplay = true
      adctx.loop = true
      adctx.src = 'https://link.jscdn.cn/1drv/aHR0cHM6Ly8xZHJ2Lm1zL3UvcyFBdkRxZENJYnJidnRoRGFpbTRURnExWDhTTmZvP2U9NEplMU1l.mp3'
      adctx.onPlay(() => {
         console.log('开始播放')
         adctx.play()
      })
   }









})
