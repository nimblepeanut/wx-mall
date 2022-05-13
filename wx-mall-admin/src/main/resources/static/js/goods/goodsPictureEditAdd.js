
/**
 * 	添加图片页面
 * @returns
 */

/**
 * 右侧显示图片
 */
var onblurfun = function() {
	document.getElementById("img").src=document.getElementById("picture").value;
}

/**
 * 表单
 * @returns
 */
layui.use(['table', 'layer', 'form', 'upload', 'element'], function(){
	
	var table = layui.table,
	form = layui.form,
	upload = layui.upload, 
	element = layui.element,
	layer = layui.layer,
	$ = layui.$;

/**
 * 修改页面
 */

	
	// 会话 开始
	var goodsPictureAdd = sessionStorage.getItem('goodsPictureAdd');

	goodsPictureAdd = eval("("+goodsPictureAdd+")");
	
	// 填充表单数据
	$("input[name='goodsId']").val(goodsPictureAdd.id);

	//删除会话里保存的数据
	sessionStorage.removeItem('goodsPictureAdd');


form.on('submit(goodsPictureAdd)', function(data) {



	// 会话 结束
	
	// ajax异步请求
	$.ajax({
		
		url:'/api/goodsPicture',
		
		// 请求方式
		type:'post',
		
		// 参数格式：JSON格式的字符串
		data: JSON.stringify(data.field),
		
		// 发送到服务器数据的类型（传参类型）
		contentType: 'application/json; charset=utf-8',
		
		// 服务器返回的数据类型
		dataType: 'json',
		
		success: function(data) {
			
			if(data.code == 0) {
				
				// 这是在子元素中弹出的
				// layer.msg('修改成功');
				
				// 因为下面用的父元素所以，要用这个
				parent.layer.msg('添加成功');
				
				// 调用父元素
				parent.layer.closeAll('iframe');
				
			} else {
				layer.msg(data.msg);
			}
			
		},
		
		error: function() {
			layer.msg('请求失败，请换一个符合的图片链接！');
		}
		
	});
	
//	为事件处理函数返回false,可以防止默认的事件行为
//		例如：默认情况下点击一个元素，
//			页面会跳转到该元素href属性指定的页
	// 这里取消表单的提交，让它发put请求
	return false;
	
	});

});



















