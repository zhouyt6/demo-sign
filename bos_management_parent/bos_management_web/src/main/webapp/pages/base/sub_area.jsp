<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>管理分区</title>
		<!-- 导入jquery核心类库 -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
		<!-- 导入easyui类库 -->
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/ext/portal.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/ext/jquery.portal.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/ext/jquery.cookie.js"></script>
		<script src="${pageContext.request.contextPath}/js/easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/highcharts/highcharts.js"></script>
		<script type="text/javascript">
			function doAdd(){
				$('#addWindow').window("open");
			}
			
			function doEdit(){
				$('#addWindow').window("open");
			}
			
			function doDelete(){
				alert("删除...");
			}
			
			function doSearch(){
				$('#searchWindow').window("open");
			}
			
			//导出按钮绑定的事件
			function doExport(){
				location.href='${pageContext.request.contextPath}/subareaAction_exportXls.action';
			}
			
			function doImport(){
				alert("导入");
			}
			
			function doShowChart(){
				//1.打开分区分布窗口
				$('#chartWindow').window('open');
				//2.发送ajax请求,查询分区的数据,返回json二维数组
				var url ='${pageContext.request.contextPath}/subareaAction_showChart.action';
				$.post(url,{},function(data){
					 //3.使用highcharts将数据展示成饼状图	
					 for (var i= 0; i < data.length;i++) {
							var data2 = data[i];
							var name = data2[0];
							var data3 = data2[1];
							alert(name+"===="+data3);
							$('#container').highcharts({
								chart:{
									type:'bar',
									width: 500,
									height: 300
								},
								title: {
							           text: '各省份分区分布图'
							     },
								series:[{
									name : name, 
									data : data3
								}]
							});
					 }
				});
			}
			//工具栏
			var toolbar = [ {
				id : 'button-search',	
				text : '查询',
				iconCls : 'icon-search',
				handler : doSearch
			}, {
				id : 'button-add',
				text : '增加',
				iconCls : 'icon-add',
				handler : doAdd
			}, {
				id : 'button-edit',	
				text : '修改',
				iconCls : 'icon-edit',
				handler : doEdit
			},{
				id : 'button-delete',
				text : '删除',
				iconCls : 'icon-cancel',
				handler : doDelete
			},{
				id : 'button-import',
				text : '导入',
				iconCls : 'icon-redo',
				handler : doImport
			},{
				id : 'button-export',
				text : '导出',
				iconCls : 'icon-undo',
				handler : doExport
			},{
				id : 'button-showChart',
				text : '分区分布图',
				iconCls : 'icon-search',
				handler : doShowChart
			}];
			// 定义列
			var columns = [ [ {
				field : 'id',
				checkbox : true,
			}, {
				field : 'showid',
				title : '编号',
				width : 120,
				align : 'center',
				formatter : function(data,row ,index){
					return row.id;
				}
			},{
				field : 'area.province',
				title : '省',
				width : 120,
				align : 'center',
				formatter : function(data,row ,index){
					if(row.area != null ){
						return row.area.province;
					}
					return "" ;
				}
			}, {
				field : 'area.city',
				title : '市',
				width : 120,
				align : 'center',
				formatter : function(data,row ,index){
					if(row.area != null ){
						return row.area.city;
					}
					return "" ;
				}
			}, {
				field : 'area.district',
				title : '区',
				width : 120,
				align : 'center',
				formatter : function(data,row ,index){
					if(row.area != null ){
						return row.area.district;
					}
					return "" ;
				}
			}, {
				field : 'keyWords',
				title : '关键字',
				width : 120,
				align : 'center'
			}, {
				field : 'startNum',
				title : '起始号',
				width : 100,
				align : 'center'
			}, {
				field : 'endNum',
				title : '终止号',
				width : 100,
				align : 'center'
			} , {
				field : 'assistKeyWords',
				title : '辅助关键字',
				width : 100,
				align : 'center'
			} ] ];
			
			$(function(){
				// 先将body隐藏，再显示，不会出现页面刷新效果
				$("body").css({visibility:"visible"});
				
				// 分区管理数据表格
				$('#grid').datagrid( {
					iconCls : 'icon-forward',
					fit : true,
					border : true,
					rownumbers : true,
					striped : true,
					pageList: [30,50,100],
					pagination : true,
					toolbar : toolbar,
					url : "${pageContext.request.contextPath}/subareaAction_pageQuery.action",
					idField : 'id',
					columns : columns,
					onDblClickRow : doDblClickRow
				});
				
				// 添加、修改分区
				$('#addWindow').window({
			        title: '添加修改分区',
			        width: 600,
			        modal: true,
			        shadow: true,
			        closed: true,
			        height: 400,
			        resizable:false
			    });
				
				$('#chartWindow').window({
			        width: 600,
			        modal: true,
			        shadow: true,
			        closed: true,
			        height: 400,
			        resizable:false
			    });
				
				// 查询分区
				$('#searchWindow').window({
			        title: '查询分区',
			        width: 400,
			        modal: true,
			        shadow: true,
			        closed: true,
			        height: 400,
			        resizable:false
			    });
				$("#btn").click(function(){
					/* 3.调用表单的序列化方法,获取表单的json数据 */
					 var formData = $('#searchForm').serializeJson();
					/*  调用datagrid的load方法,实现带条件的分页查询 */
					 $('#grid').datagrid('load',formData);
					 /* 5.关闭查询窗口 */
					 $('#searchWindow').window('close');
				});
			});
		
			function doDblClickRow(){
				alert("双击表格数据...");
			}
		</script>
	</head>

	<body class="easyui-layout" style="visibility:hidden;">
		<div region="center" border="false">
			<table id="grid"></table>
		</div>
		<!-- 添加 修改分区 -->
		<div class="easyui-window" title="分区添加修改" id="addWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
			<div style="height:31px;overflow:hidden;" split="false" border="false">
				<div class="datagrid-toolbar">
					<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true">保存</a>
					<script type="text/javascript">
						$('#save').click(function(){
							//校验是否合法
							 if($('#subareaForm').form('validate')) { 
								//校验通过，提交
								$('#subareaForm').submit();
							 } 
						});
					</script>
					</div>
			</div>

			<div style="overflow:auto;padding:5px;" border="false">
				<form id="subareaForm" method="post" action="${pageContext.request.contextPath}/subareaAction_save.action">
					<table class="table-edit" width="80%" align="center">
						<tr class="title">
							<td colspan="2">分区信息</td>
						</tr>
						<tr>
							<td>编号</td>
							<td>
								<input type="text" name="id" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>选择区域</td>
							<td>
								<input class="easyui-combobox" name="area.id"
									 data-options="valueField:'id',textField:'name',
									 url:'${pageContext.request.contextPath }/area_findAll.action',editable:false" />
							</td>
						</tr>
						<tr>
							<td>关键字</td>
							<td>
								<input type="text" name="keyWords" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>辅助关键字</td>
							<td>
								<input type="text" name="assistKeyWords" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>起始号</td>
							<td>
								<input type="text" name="startNum" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>终止号</td>
							<td>
								<input type="text" name="endNum" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>位置信息</td>
							<td>
								<input type="text" name="address" class="easyui-validatebox" required="true" style="width:250px;" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		
		<div class="easyui-window" title="分区分布图" id="chartWindow" collapsible="false" 
				minimizable="false" maximizable="false" style="top:20px;left:200px">
			<div id="container"></div>
		</div>
		
		<!-- 查询分区 -->
		<div class="easyui-window" title="查询分区窗口" id="searchWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
			<div style="overflow:auto;padding:5px;" border="false">
				<form id="searchForm">
					<table class="table-edit" width="80%" align="center">
						<tr class="title">
							<td colspan="2">查询条件</td>
						</tr>
						<tr>
							<td>省</td>
							<td>
								<input type="text" name="area.province" class="easyui-validatebox" required="false"/>
							</td>
						</tr>
						<tr>
							<td>市</td>
							<td>
								<input type="text" name="area.city"/>
							</td>
						</tr>
						<tr>
							<td>区（县）</td>
							<td>
								<input type="text" name="area.district"/>
							</td>
						</tr>
						<tr>
							<td>定区编号</td>
							<td>
								<input type="text" name="fixedArea.id" />
							</td>
						</tr>
						<tr>
							<td>关键字</td>
							<td>
								<input type="text" name="keyWords"  />
							</td>
						</tr>
						<tr>
							<td colspan="2"><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
								<script type="text/javascript">
								 $(function(){
										//1.给表单绑定序列化方法，将form表单数据转换成json
									$.fn.serializeJson=function(){  
							            var serializeObj={};  
							            var array=this.serializeArray();  
							            var str=this.serialize();  
							            $(array).each(function(){  
							                if(serializeObj[this.name]){  
							                    if($.isArray(serializeObj[this.name])){  
							                        serializeObj[this.name].push(this.value);  
							                    }else{  
							                        serializeObj[this.name]=[serializeObj[this.name],this.value];  
							                    }  
							                }else{  
							                    serializeObj[this.name]=this.value;   
							                }  
							            });  
							            return serializeObj;  
								 	};
								 });
								</script>
							 </td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>

</html>