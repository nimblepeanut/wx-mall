package com.qxf.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qxf.mall.entity.GoodsPicture;
import com.qxf.mall.entity.Result;
import com.qxf.mall.service.GoodsPictureService;

@RestController
@RequestMapping("/api/goodsPicture")
public class GoodsPictureController {
	
	@Autowired
	private GoodsPictureService goodsPictureService;
	
	@PostMapping
	public Result add(@RequestBody GoodsPicture goodsPicture) {
		
		// 调用业务层添加方法
		int add = goodsPictureService.add(goodsPicture);
		
		// 成功判断
		if (add > 0) {
			return Result.success("添加成功！");
		} else {
			return Result.error("添加失败！");
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
