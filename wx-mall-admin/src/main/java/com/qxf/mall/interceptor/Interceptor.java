package com.qxf.mall.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.qxf.mall.entity.Admin;

@Component
public class Interceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 验证用户是否登录
		HttpSession session = request.getSession();
		
		Admin admin = (Admin) session.getAttribute("admin");
		
		if (admin == null) {
			
			// 用户没有登录，跳转到登录页面
			response.sendRedirect("/login");
			
			// 拦截
			return false;
			
		} else {
			
			return true;
			
		}
		
	}
	
}
