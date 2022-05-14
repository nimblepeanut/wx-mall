

/**
 * 添加页面js
 */

var onblurfun = function() {
	document.getElementById("img").src=document.getElementById("picture").value;
}

/**
 * 添加表单
 * @returns
 */
layui.use([ 'form' ], function() {

	var form = layui.form, 
	$ = layui.$;

	// 重新渲染表单
	form.render();

	// 表单提交 开始
	form.on('submit(categoryAdd)', function(data) {

		// debugger;

		// ajax异步请求 开始
		$.ajax({

			url : '/api/category',

			type : 'post',

			// 先转换类型，再规定发送类型，最后规定返回类型

			// 参数格式：JSON格式的字符串
			data : JSON.stringify(data.field),

			// 传入的参数类型
			contentType : 'application/json; charset=utf-8',

			// 服务器返回的参数类型
			dataType : 'json',

			success : function(data) {

				if (data.code == 0) {
					layer.msg('添加成功！');
					// 到分类列表
					// open('/html/category/categoryList.html');
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

});
