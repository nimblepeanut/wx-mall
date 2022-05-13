
// 表格
layui.use(['table', 'layer', 'form', 'upload', 'element'], function(){
	
	var table = layui.table,
	form = layui.form,
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
					
				})
				
				$("select[name='categoryId']").html(html);

				form.render();
					
			},
		
			error: function(){
				layer.msg('请求失败');
			}
			
		});
		
	}
	
	// 分类下拉框 结束
	
	// 渲染表格
	table.render({
		
		// 指定原始表格元素选择器
		elem: '#goodsList',
		
		// 数据接口
		url: '/api/goods',
		
		// 请求方式
		type:'get',
		
		// 开启分页
		page: true,

		// 设置表头
		cols: [[
			{field:'id', title:'ID', width:50},
//			{field:'goods', width:200, title:'分类', templet: function(d){ return d.category.name } },
			{field:'goods', width:120, title:'分类', templet: function(d){ return (d.category==null)?'暂无分类':d.category.name } },
			{field:'name', width:150, title:'商品名称'},
			{field:'price', title:'价格', width:80 },
			{field:'count', title:'数量', width:80 },
			{field:'goodsPicture', title:'图片', templet: '#goodsPicture'},
			{field:'introduce', title:'介绍'},
			{field:'recommend', width:110 , title:'主页推荐', templet: function(d){ return (d.recommend)?'推荐':'不推荐' } },
			{field:'state', width:70 , title:'状态', templet: function(d){ return ['下架', '上架'][d.state]; } },																							
			{title:'操作', width:290, toolbar:'#operation'}
		]],
		
	});

	// 行工具的响应 开始
	table.on('tool(goodsList)', function(obj) {
		
		// 下架 开始
		
		if (obj.event == 'delete') {
			
			layer.confirm('确定要下架吗？', function(index) {
				                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
				layer.close(index);
				
				// ajax提交下架
				$.ajax({
					
					url: '/api/goods/',
					data: {id: obj.data.id},
					// 请求方式
					type: 'delete',
					dataType: 'json',
					
					// 成功回调
					success: function(data) {
						
						// 判断是否下架成功
						if(data.code == 0) {
							
							layer.msg("下架成功！");
							
							// 重载表格
							table.reload('goodsList');
							
							
						} else {
							
							// 下架失败
							layer.msg(data.msg);
							
						}
						
					},
					
					// 失败回调
					error: function() {
						
						layer.msg("请求失败！");
						
					}
					
				});
				
				
			});
			
		// 下架 结束
			
		// 修改 开始
			
		} else if(obj.event == 'edit') {
			
			// 当前要编辑的数据，保存到sessionStorage中
			sessionStorage.setItem('goodsEdit', JSON.stringify(obj.data));
			
			// 弹出层
			layer.open({
	
				// layer提供了5种层类型type
				// 0 信息框 默认
				// 1 页面层
				// 2 iframe层 相当于又开了一个页面
				// 3 加载层
				// 4 tips层
				type: 2,
				title: ['修改商品信息', 'font-size:20px; color: #009688;'],
				// 宽高
				area:['650px', '680px'],
				// 内容，每个层都不一样，看layui文档
				content: "/html/goods/goodsEdit.html",
				// 图标
				icon: 1,
				// 点×关闭
				// 层销毁后触发的回调，无论是确认还是取消，只要层被销毁了，end都会执行，不携带任何参数
				end: function() {
					// 当弹出层关闭时，重载表格
					table.reload('goodsList');
				}	
				
			});
			
		// 修改 结束
			
		// 添加图片 开始
			
		} else if(obj.event == 'pictureAdd') {
			
			// 当前要编辑的数据，保存到sessionStorage中
			sessionStorage.setItem('goodsPictureAdd', JSON.stringify(obj.data));
			
			// 弹出层
			layer.open({
	
				// layer提供了5种层类型type
				// 0 信息框 默认
				// 1 页面层
				// 2 iframe层 相当于又开了一个页面
				// 3 加载层
				// 4 tips层
				type: 2,
				title: ['添加图片', 'font-size:20px; color: #009688;'],
				// 宽高
				area:['800px', '350px'],
				// 内容，每个层都不一样，看layui文档
				content: "/html/goods/goodsPictureAdd.html",
				// 图标
				icon: 1,
				// 点×关闭
				// 层销毁后触发的回调，无论是确认还是取消，只要层被销毁了，end都会执行，不携带任何参数
				end: function() {
					// 当弹出层关闭时，重载表格
					table.reload('goodsList');
				}	
				
			});
			
			// 添加图片 结束
			
		}
		
	});
	
	// 行工具的响应 结束
	
	// 搜索 开始
	
	form.on('submit(search)', function(data){
		
		// 重载（刷新）表格
		table.reload('goodsList', {
			
			// 查询条件
			where: data.field,
			
			page: {curr: 1},
			
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
			
			title : [ '添加商品', 'font-size:24px; color: #488CB6;' ],
			
			// 宽高
			area : [ '650px', '680px' ],
			
			// 内容，每个层都不一样，看layui文档
			content : "/html/goods/goodsAdd.html",
			
			// 图标
			icon : 1,
			
			// 点×关闭
			// 层销毁后触发的回调，无论是确认还是取消，只要层被销毁了，end都会执行，不携带任何参数
			end : function() {
				
				// 当弹出层关闭时，重载表格
				table.reload('goodsList');
				
			}

		});

	});

	// 添加 结束

});

























