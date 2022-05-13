package com.qxf.mall.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 向客户端返回JSON格式的一个包装类
 * @author bobzyh
 *
 */
@Getter
@Setter
public class Result {
	
	// 状态码
	private Integer code;	
	
	// 成功/失败信息
	private String msg;	
	
	// 数据
	private Object data;	
	
	/**
	 * 成功调用 👇
	 */
	public static Result success() {
		return success(0, null, null);
	}
	
	public static Result success(Integer code) {
		return success(code, null, null);
	}
	
	public static Result success(String msg) {
		return success(0, msg, null);
	}
	
	public static Result success(Object data) {
		return success(0, null, data);
	}
	
	public static Result success(Integer code, String msg, Object data) {
		Result result = new Result();
		result.setCode(code);
		result.setMsg(msg);
		result.setData(data);
		
		return result;
	}
	
	/**
	 * 失败时的回调 👇
	 */
	public static Result error() {
		return error(-1, null);
	}
	
	public static Result error(Integer code) {
		return error(code, null);
	}
	
	public static Result error(String msg) {
		return error(-1, msg);
	}
	
	public static Result error(Integer code, String msg) {
		Result result = new Result();
		result.setCode(code);
		result.setMsg(msg);
		
		return result;
	}
}
