// 表格
layui.use(['table', 'layer', 'form', 'upload', 'element'], function(){
	
	var table = layui.table,
	form = layui.form,
	upload = layui.upload, 
	element = layui.element,
	layer = layui.layer,
	$ = layui.$;

	// 渲染表格
	table.render({

		// 指定原始表格元素选择器
		elem : '#categoryList',

		// 数据接口
		url : '/api/category',

		// 请求方式
		type : 'get',

		// 开启分页
		page : true,

		// 设置表头
		cols : [[
			// 不行，id也会换
			// {field : 'id',title:'ID',templet:'#titleTpl'}, 
			{field : 'id',title:'ID'}, 
			{field : 'name',title : '分类名称'}, 
			{field : 'picture',title : '分类图片',templet:'#pic'}, 
			{title : '操作',toolbar : '#operation'} 
		]],

	});

	// 行工具的响应 开始
	table.on('tool(categoryList)', function(obj) {

		// 删除 开始

		if (obj.event == 'delete') {

			layer.confirm('确定要删除吗？', function(index) {

				layer.close(index);

				// ajax提交删除
				$.ajax({

					url : '/api/category',
					data : {
						id : obj.data.id
					},
					// 请求方式
					type : 'delete',
					dataType : 'json',

					// 成功回调
					success : function(data) {

						// 判断是否删除成功
						if (data.code == 0) {

							layer.msg("删除成功！");

							// 重载表格
							table.reload('categoryList');

						} else {

							// 删除失败
							layer.msg(data.msg);

						}

					},

					// 失败回调
					error : function() {

						layer.msg("请求失败！");

					}

				});

			});

			// 删除 结束

			// 修改 开始

		} else if (obj.event == 'edit') {

			// 当前要编辑的数据，保存到sessionStorage中
			sessionStorage.setItem('categoryEdit', JSON.stringify(obj.data));

			// 弹出层
			layer.open({

				// layer提供了5种层类型type
				// 0 信息框 默认
				// 1 页面层
				// 2 iframe层 相当于又开了一个页面
				// 3 加载层
				// 4 tips层
				type : 2,
				title : [ '修改商品分类', 'font-size:20px; color: #009688;' ],
				// 宽高
				area : [ '750px', '280px' ],
				// 内容，每个层都不一样，看layui文档
				content : "/html/category/categoryEdit.html",
				// 图标
				icon : 1,
				// 点×关闭
				// 层销毁后触发的回调，无论是确认还是取消，只要层被销毁了，end都会执行，不携带任何参数
				end : function() {
					// 当弹出层关闭时，重载表格
					table.reload('categoryList');
				}

			});

			// 修改 结束

		}

	});

	// 行工具的响应 结束

	// 搜索 开始

	// if($("#categorySearch")[0]) {

	form.on('submit(search)', function(data) {

		// 重载（刷新）表格
		table.reload('categoryList', {

			// 查询条件
			where : data.field,

			page : {
				curr : 1
			},

		});

		// 阻止表单自动提交
		return false;

	});

	// 搜索 结束

	// 添加 开始

	form.on('submit(add)', function(data) {

		// 弹出层
		layer.open({

			type : 2,
			title : [ '添加商品分类', 'font-size:24px; color: #488CB6;' ],
			// 宽高
			area : [ '800px', '350px' ],
			// 内容，每个层都不一样，看layui文档
			content : "/html/category/categoryAdd.html",
			// 图标
			icon : 1,
			// 点×关闭
			// 层销毁后触发的回调，无论是确认还是取消，只要层被销毁了，end都会执行，不携带任何参数
			end : function() {
				// 当弹出层关闭时，重载表格
				table.reload('categoryList');
			}

		});

	});

	// 添加 结束

});
