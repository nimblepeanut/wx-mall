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
		elem : '#userList',

		// 数据接口
		url : '/api/user',

		// 请求方式
		type : 'get',

		// 开启分页
		page : true,

		// 设置表头
		cols : [[
			{field : 'id',title:'ID', width:70}, 
			{field : 'username',title : '用户名', width:180}, 
			{field : 'headPortrait', title : '用户头像', width:180, templet:'#headPortrait'}, 
			{field : 'regtime',title:'注册时间', width:200},
			{title : '密码、余额', toolbar : '#prohibit'}, 
			{title : '操作',toolbar : '#operation', width:200},
		]],
		
		// 别忘了加这个
		// 否则会报 LAY_TABLE_INDEX：Layui数据表格数据加载异常
		parseData: function(d){
			
//			 console.log(d.data.list);
			
			return {
				code: d.code,
				msg: d.msg,
				count: d.data.total,
				data: d.data.list,
			};
		}

	});

	// 搜索 开始
	form.on('submit(search)', function(data) {

		// 重载（刷新）表格
		table.reload('userList', {

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

});











