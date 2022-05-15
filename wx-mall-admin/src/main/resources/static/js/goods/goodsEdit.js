
/**
 * 修改页面
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
 * 从会话中取数据
 */
var goodsEdit = sessionStorage.getItem('goodsEdit');

goodsEdit = eval("("+goodsEdit+")");

// 填充表单数据
$("input[name='id']").val(goodsEdit.id);

// 调用分类下拉框方法，传次商品的分类id
loadCategory(goodsEdit.category.id);

// 分类下拉框 开始

// 加载分类
function loadCategory(cur) {
	
	$.ajax({
		
		url: '/api/category', 
		type: 'get',
		dataType: 'json',
		success: function(data) {
			
			var html = '<option value="">请选择分类</option>';
			
			$.each(data.data.list, function(index, item) {
				
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

// 名称
$("input[name='name']").val(goodsEdit.name);
// 价格
$("input[name='price']").val(goodsEdit.price);
// 库存
$("input[name='count']").val(goodsEdit.count);
// 介绍
$("textarea[name='introduce']").val(goodsEdit.introduce);
// 推荐
$("input[name='recommend']").attr('checked', goodsEdit.recommend==1);
// 状态
$("input[name='state']").attr('checked', goodsEdit.state==1);

// 删除会话里保存的数据
sessionStorage.removeItem('goodsEdit');

form.on('submit(goodsEdit)', function(data) {
	
	console.log(data);
	
	// ajax异步请求
	$.ajax({
		
		url:'/api/goods',
		
		// 请求方式
		type:'put',
		
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
				parent.layer.msg('修改成功！');
				
				// 调用父元素
				parent.layer.closeAll('iframe');
				
			} else {
				layer.msg(data.msg);
			}
			
		},
		
		error: function() {
			layer.msg('修改失败！');
		}
		
	});
	
//	为事件处理函数返回false,可以防止默认的事件行为
//		例如：默认情况下点击一个元素，
//			页面会跳转到该元素href属性指定的页
	// 这里取消表单的提交，让它发put请求
	return false;
	
	});

});





//$("input[name='recommend']").attr('checked', goodsEdit.recommend==1);
//$("input[name='recommend'][value='"+goodsEdit.recommend+"']").attr("checked", true);

//$("input[name='state']").attr('checked', goodsEdit.state==1);
//$("input[name='state'][value='"+goodsEdit.state+"']").attr("checked", true);

//图片 开始

//console.log(goodsEdit.goodsPicture[0].picture);

//图片+遮罩层
//设成行内块元素: <div style="position:relative; display:inline-block;">
//遮罩层: 向上移动0像素就显示出来了top:0px
/*
$.each(goodsEdit.goodsPicture, function(index, item){

	$('#pic-list').append('<div class="img-box" style="position:relative; display:inline-block; cursor:pointer;">'+
		'<img style="height:92px; width:92px;" src="'+item.picture+'" data-url="'+item.picture+'">'+
		'<div class="div-del" style="position:absolute; background-color:#55555588; display:none;'+
		'width:92px; height:92px; top:0px; text-align:center; color:#FFF; line-height:92px">点击删除</div></div>');
	
});

//鼠标移到图片上
$("#pic-list").on('mouseover', '.img-box', function(){
	$(this).find('.div-del').show().fadeIn(300);
});

//鼠标移出图片
$("#pic-list").on('mouseout', '.img-box', function(){
	$(this).find('.div-del').stop().fadeOut(300);
});

//鼠标点击图片
$("#pic-list").on('click', '.div-del', function(){
	$(this).parents('.img-box').remove();
});

//在右侧显示图片
//document.getElementById("img").src=goodsEdit.picture;

//图片 结束
*/











