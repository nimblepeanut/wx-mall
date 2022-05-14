package com.qxf.mall.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.qxf.mall.interceptor.Interceptor;

/**
 * 	拦截器的配置
 * @author dell
 *
 */
// 配置类注解
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Autowired
	private Interceptor interceptor;

	/**
	 * 	配置拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		// 将之前写的用户拦截器加载起来
		registry.addInterceptor(interceptor)
			.addPathPatterns("/**")
			// 排除登录页面login
			.excludePathPatterns("/login", "/css/**", "/layui/**", "/verCode");
		
	}
	
	
}
