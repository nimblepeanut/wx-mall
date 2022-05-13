
/**
 * ECharts页面
 */

// 柱状图 开始

// 柱状图
var goodsCountBar = echarts.init(document.getElementById("goods-count-bar"));

// ajax请求远程数据 开始
$.ajax({
	
	url: '/api/category/echarts',
	dateType: 'json',
	
	// 成功 开始
	success: function(data) {
		
		// 柱状图，将data里面的数据解析成两个数组
		var xData = [];
		var yData = [];
		
		// 循环获取分类名称和数量
		$.each(data.data, function(index, item) {
			
//			console.log(item.name);
//			console.log(item.count);
			
			xData.push(item.name);
			yData.push(item.count);
			
		});
		
		// 刷新数据
		goodsCountBar.setOption({
			
			// 工具栏
			toolbox: {
				right : 100,
				feature: {
					dataView: { show: true, readOnly: false },
				    magicType: { show: true, type: ['bar'] },
				    restore: { show: true },
				    saveAsImage: { show: true }
				}
			},
			
			// 标题
			title: {
	      	    top: 30,
	      	    left: 'center',
				text: "各分类商品数量",
				textStyle:{
//					 color:'#5470C6',
					 center: ['50%', '50%'],
				}
			}, 

			// x轴数据
			xAxis : {
				// 样式
				type : 'category',
				data : xData
			},
			
			// y轴数据
			yAxis : {
				type : 'value'
			},
			
			// y轴刻度
			series : [ {
				// 图形样式：柱状图
				type : 'bar',
				data : yData,
				// top就是每个矩形最上面的数字
				label: {
					show: true,
					position: 'top',
					// 样式
					textStyle: {
						gontSize: 30
					}
				}
			
			}]

		});
		
	},
	// 成功 结束
	
	error: function() {
		alert("请求失败！");
	}
	
});

// ajax 结束

// 柱状图 结束

// 饼图 开始

// 别忘了，初始化Echarts
var goodsCountPie = echarts.init(document.getElementById("goods-count-pie"));

// ajax请求远程数据 开始
$.ajax({
	
	url: '/api/category/echarts',
	dateType: 'json',
	
	// 成功 开始
	success: function(data) {
		
		// 饼图
		var pie = [];
		// 不能直接就这样赋值，因为data.data里面还有别的数据
		// var pie = data.data;
		
		// 循环获取分类名称和数量
		$.each(data.data, function(index, item) {
			
			pie.push({name: item.name, value: item.count});
			
		});
		
//		console.log("pie:");
//		console.log(pie);
		
		// 刷新数据
		goodsCountPie.setOption({
			
			// 标题
			title: {
	      	    left: 'center',
				text: "各分类所占百分比",
				 textStyle:{
//					 color:'#5470C6',
				 }
			}, 
			
			// 图例组件
			legend: {
				// 位置
//				top: 'top',
				top: 50
			},
				  
			// 工具栏
			toolbox: {
				right : 100,
				show: true,
				feature: {
					mark: { show: true },
					dataView: { show: true, readOnly: false },
					restore: { show: true },
					saveAsImage: { show: true }
				}
			},
			
			series: [{
				name: 'Nightingale Chart',
				type: 'pie',
				radius: [50, 250],
				center: ['50%', '50%'],
				roseType: 'area',
				itemStyle: {
					borderRadius: 8
				},
				      
				// 数据
				data: pie,
				
				label: {
					formatter: '{b}: {d}%',
					fontSize: 18
				}
	      
			}]
			
		});
			
	},
	
	// 成功 结束
	
	error: function() {
		alert("请求失败！");
	}
	
});

// ajax 结束

// 饼图 结束









