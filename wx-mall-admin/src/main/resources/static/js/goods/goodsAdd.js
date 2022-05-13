/**
 * 添加页面js
 */

/**
 * 添加表单
 * @returns
 */

layui.use(['table', 'layer', 'form', 'upload', 'element'], function(){
	
	var table = layui.table,
	form = layui.form,
	upload = layui.upload, 
	element = layui.element,
	layer = layui.layer,
	$ = layui.$;

	// 分类下拉框 开始
	
	// 渲染分类
	if ($("select[name='categoryId']")[0]){
		loadCategory();
	}
	
	// 加载分类
	function loadCategory(cur) {
		
		$.ajax({
			
			url: '/api/category', 
			data: {categoryId: 0},
			type: 'get',
			dataType: 'json',
			success: function(data) {
				
				var html = '<option value="">请选择分类</option>';
				
				$.each(data.data, function(index, item) {
					
					if (item.id == cur) {
						html += '<option value="'+item.id+'" selected>'+item.name+'</option>';
					} else {
						html += '<option value="'+item.id+'" >'+item.name+'</option>';
					}
					
				});
				
				$("select[name='categoryId']").html(html);

				form.render();
					
			},
		
			error: function(){
				layer.msg('请求失败');
			}
			
		});
		
	}
	
	// 分类下拉框 结束

	// 重新渲染表单
	form.render();
	
	// 是否是添加表单
//	if ($("#goodsAdd-form")[0]){
		
	// 表单提交 开始
	form.on('submit(goodsAdd)', function(data) {

		// debugger;

		// ajax异步请求 开始
		$.ajax({

			url:'/api/goods',

			type: 'post',

			// 先转换类型，再规定发送类型，最后规定返回类型

			// 参数格式：JSON格式的字符串
			data: JSON.stringify(data.field),

			// 传入的参数类型
			contentType: 'application/json; charset=utf-8',

			// 服务器返回的参数类型
			dataType: 'json',

			success: function(data) {
				
				if (data.code == 0) {
					//layer.msg('添加成功！');
					// 到分类列表
					// open('/html/goods/goodsList.html');
					
					// 因为下面用的父元素所以，要用这个
					parent.layer.msg('修改成功');
					
					// 调用父元素
					parent.layer.closeAll('iframe');
					
					
					
				} else {
					layer.msg(data.msg);
				}

			},
			error : function() {
				layer.msg('请求失败！');
			}

		// ajax异步请求 结束
		});

		// 阻止表单提交
		return false;

		// 表单提交 结束
	});
	
	
	
	
	
//	}
	
	
	
	
	
	
	
	
	
	

});
