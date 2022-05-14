package com.qxf.mall.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qxf.mall.entity.Admin;
import com.qxf.mall.service.AdminService;

/**
 * 管理员登录控制层
 * 
 * @author dell
 *
 */
@Controller
@RequestMapping("/login")
public class AdminLoginController {

	// 注入Service层接口
	@Autowired
	private AdminService adminService;

	/**
	 * 管理员登录页面
	 * 
	 * @return
	 */
	@GetMapping
	public String login() {
		return "/login";
	}

	/**
	 * 管理员登录的操作
	 */
	@PostMapping
	public String login(Admin admin, String verCode, HttpSession session, Model model) {

		// 登录失败就抛异常
		try {

			// 检验验证码 开始

			// 正确的验证码

			// 从session中获取生成的验证码：正确的验证码
			String correctVerCode = (String) session.getAttribute("correctVerCode");

			if (verCode == null) {
				throw new Exception("未输入验证码！");
			}

			if (!verCode.toUpperCase().equals(correctVerCode)) {
				throw new Exception("验证码错误！");
			}

			// 检验验证码 结束

			// 调用业务层的查询方法login
			Admin loginAdmin = adminService.login(admin);

			// 登录成功
			// 保存登录信息到session
			// 主页和拦截器要用
			session.setAttribute("admin", loginAdmin);

			// 跳转到主页
			return "redirect:/";

		} catch (Exception e) {

			e.printStackTrace();

			// 把错误信息带回登录页面
			model.addAttribute("error", e.getMessage());

			return "login";

		}

	}

	/**
	 * 退出登录
	 * 
	 * @return
	 */
	@GetMapping("/out")
	public String out(HttpSession session) {

		// 清空session
		session.invalidate();

		// 跳转
		return "redirect:/login";

	}

}
