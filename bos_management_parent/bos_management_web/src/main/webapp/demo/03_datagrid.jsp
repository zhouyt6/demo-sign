<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Layout布局</title>
<!-- 引入easyui 的资源文件  -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<!-- 引入ztree 的资源文件 -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
</head>
<body>
	<table class="easyui-datagrid">
		<thead>
		<tr><th data-options="field:'id'">编号</th>
			<th data-options="field:'name'">姓名</th>
			<th data-options="field:'tel'">手机</th>
		</tr>
		</thead>
		<tbody>
		<tr>
			<td>1</td>
			<td>马蓉</td>
			<td>13234567890</td>
		</tr>
		<tr>
			<td>2</td>
			<td>李凯</td>
			<td>12323434334</td>
		</tr>
	</table>
	<hr>
	<table class="easyui-datagrid" data-options="url:'${pageContext.request.contextPath }/data/courier.json'">
		<thead>
		<!-- filed:设置从返回的json数据的哪个key上获取值 -->
		<tr><th data-options="field:'id'">编号</th>
			<th data-options="field:'name'">姓名</th>
			<th data-options="field:'telephone'">手机</th>
		</tr>
		</thead>
	</table>
	<hr>
	<h3>3.使用api动态构建datagrid</h3>
	<table id="grid"></table>
	<script type="text/javascript">
		$(function(){
			$('#grid').datagrid({    
			    url:'${pageContext.request.contextPath}/data/courier.json',    
			    columns:[[    
			        {field:'courierNum',title:'编号',checkbox:true},   //checkbox开启复选框   
			        {field:'id',title:'编号',width:100},    
			        {field:'name',title:'姓名',width:100},    
			        {field:'telephone',title:'手机',width:100,align:'right'}    
			    ]], // 二维数组，设置表格的列属性
			    toolbar:[
			             {text:'添加',iconCls:'icon-add',handler:function(){
			            	 alert('添加');
			             }},// text:设置按钮名称。handler相当于onclick
			             {text:'修改'},
			             {text:'作废'},
			             {text:'还原'},
			             ], //工具条，一位数组
			    pagination:true, //开启分页查询条
			    //pageList:[10,20,30]
			}); 
		});
	</script>
</body>
</html>


