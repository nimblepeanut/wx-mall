package com.qxf.mall.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.qxf.mall.util.VerCodeUtil;

/**
 * 	验证码控制层
 * @author dell
 *
 */
@Controller
public class VercodeController {
	
	@GetMapping("/verCode")
	private void verCode(HttpServletResponse response, 
			HttpSession session) {
		
		// 生成验证码的图片，通过Response返回
		// 接收验证码的字符串
		String correctVerCode = VerCodeUtil.createVerCode(response);
		
		// 将验证码字符串保存到session中
		session.setAttribute("correctVerCode", correctVerCode);
		
	}

}
