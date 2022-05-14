/**
 * ECharts页面
 */

// 柱状图 开始
// 柱状图
// 热力图 开始
var userCountBar = echarts.init(document.getElementById("user-countBar"));

// ajax请求远程数据 开始

$.ajax({

	url : '/api/user/echarts',
	dateType : 'json',

	// 成功 开始
	success : function(data) {

		// console.log(data.data);

		var calendar = [];

		$.each(data.data, function(index, item) {

			// console.log(item.regtime);
			// console.log(item.count);

			calendar.push([ item.regtime, item.count ])

		});

		// console.log(calendar);

		option = {

			// 标题
/*			title : {
				top : 30,
				left : 'center',
				text : '2022年用户注册热力分布图'
			},*/
				
			// 工具栏
			toolbox: {
				right : 50,
				feature: {
					dataView: { show: true, readOnly: false },
					magicType: { show: true, type: ['bar'] },
				    restore: { show: true },
					saveAsImage: { show: true }
				}
			},

			tooltip : {},

			// 图例
			visualMap : {
				min : 0,
				max : 10,
				// 默认为条形
				calculable : true,

				// 分段图例
				// type : 'piecewise',

				orient : "horizontal",
				left : "center",
				top : 30
//				top : 65
			},

			calendar : {
				top : 120,
				left : 30,
				right : 30,
				cellSize : [ 'auto', 13 ],
				range : '2022',
				itemStyle : {
					borderWidth : 0.5
				},
				yearLabel : {
					show : false
				}
			},

			series : {
				type : 'heatmap',
				coordinateSystem : 'calendar',
				data : calendar
			}
		};

		userCountBar.setOption(option);

	},

	// 成功 结束

	error : function() {
		alert("请求失败！");
	}

});

// ajax 结束

// 热力图 结束

// 折线图 开始

/**
 * 表单
 * @returns
 */
layui.use([ 'form' ], function() {

	var form = layui.form;

	// 表单提交 开始
	form.on('submit(echartsStart)', function(data) {
		
		console.log(data);

		// ajax请求远程数据
		$.ajax({

			url : '/api/user/echartsStart',
			
			data: data.field,
			
			// 服务器返回的参数类型
			dataType : 'json',

			success : function(data) {
				
				/* 初始化Echarts */
				var userLine = echarts.init(document.getElementById("user-line"));
				
				console.log(data);
				// regtime: '2021-12-30', count: 3

				// 将data里面的数据解析成两个数组
				var xData = [];
				var yData = [];

				$.each(data.data, function(index, item) {
					xData.push(item.regtime);
					yData.push(item.count);
				});

				// 刷新数据
				userLine.setOption({

					title : {
//						text : "新注册用户"
					},
					
					// 工具栏
					toolbox: {
						right : 50,
						feature: {
							dataView: { show: true, readOnly: false },
						    magicType: { show: true, type: ['bar'] },
						    restore: { show: true },
						    saveAsImage: { show: true }
						}
					},

					xAxis : {
						type : 'category',
						data : xData
					},

					yAxis : {
						type : 'value'
					},

					// 分段颜色
					visualMap : {
						top : 50,
						right : 30,
						pieces : [ {
							gt : 0,
							lte : 2,
							color : '#93CE07'
						}, {
							gt : 2,
							lte : 4,
							color : '#FBDB0F'
						}, {
							gt : 4,
							lte : 6,
							color : '#FC7D02'
						}, {
							gt : 6,
							lte : 8,
							color : '#FD0100'
						}, {
							gt : 8,
							lte : 10,
							color : '#AA069F'
						}, 
						{
							gt : 10,
							color : '#AC3B2A'
						} ],
						outOfRange : {
							color : '#999'
						}
					},

					series : [ {
						data : yData,
						// 图形样式
						// type : 'bar',
						type: 'line',
						label : {
							show : true,
							position : 'top',
							textStyle : {
								gontSize : 30
							}

						}
					} ]

				});

			},

			error : function() {
				alert("请求失败！");
			}

		});
		// ajax 结束

		// 阻止表单提交
		return false;

		// 表单提交 结束
	});

});

// 折线图 结束

