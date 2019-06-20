<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> datagrid 的行编辑</title>
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

	<h3>3.使用api动态构建datagrid</h3>
	<table id="grid"></table>
	<script type="text/javascript">
		$(function(){
			$('#grid').datagrid({    
			    url:'${pageContext.request.contextPath}/data/courier.json',    
			    columns:[[    
			        {field:'courierNum',checkbox:true},   //checkbox开启复选框   
			        {field:'id',title:'编号',width:100,editor:{
			        	type:'validatebox',
			        	options:{
			        		required:true
			        	}
			        }},    
			        {field:'name',title:'姓名',width:100,editor:{
			        	type:'validatebox',
			        	options:{
			        		required:true
			        	}
			        }},    
			        {field:'telephone',title:'手机',width:100,align:'right'}    
			    ]], // 二维数组，设置表格的列属性
			    toolbar:[
			             {text:'添加',iconCls:'icon-add',handler:function(){
			            	// 在第三行的位置插入一个新行
							 $('#grid').datagrid('insertRow',{
								index: 0,	// 索引从0开始
								row: {
									name: '新名称',
									age: 30,
									note: '新消息'
								}
							});
			            	 var rows =  $('#grid').datagrid('getSelections');
				            	if (null != rows) {
									for(var i =0 ;i<rows.length;i++){
				            			 //2,调用api删除指定的行
										var index = $('#grid').datagrid('getRowIndex',rows[i]); //获取选中的行号
										 $('#grid').datagrid('beginEdit',index);
									}
								}
			             }},// text:设置按钮名称。handler相当于onclick
			             {text:'修改',handler:function(){
			            	 //1.获取要修改选中的行
				            	var rows =  $('#grid').datagrid('getSelections');
				            	if (null != rows) {
									for(var i =0 ;i<rows.length;i++){
				            			 //2,调用api删除指定的行
										var index = $('#grid').datagrid('getRowIndex',rows[i]); //获取选中的行号
										 $('#grid').datagrid('beginEdit',index);
									}
								}
				             }},
			             {text:'删除',handler:function(){
			            	 //1.获取客户选中的行
			            	var rows =  $('#grid').datagrid('getSelections');
			            	if (null != rows) {
								for(var i =0 ;i<rows.length;i++){
			            			 //2,调用api删除指定的行
									var index = $('#grid').datagrid('getRowIndex',rows[i]); //获取选中的行号
									 $('#grid').datagrid('deleteRow',index);
								}
							}
			             }},
			             {text:'保存',handler:function(){
			            	 //1.获取客户选中的行
				            	var rows =  $('#grid').datagrid('getSelections');
				            	if (null != rows) {
									for(var i =0 ;i<rows.length;i++){
				            			 //2,调用api删除指定的行
										var index = $('#grid').datagrid('getRowIndex',rows[i]); //获取选中的行号
										 $('#grid').datagrid('endEdit',index);
									}
								}
				             }},
			             ], //工具条，一位数组
			    pagination:true, //开启分页查询条
			   	pageList:[10,20,30],
			    onAfterEdit:function(rowIndex, rowData, changes){
			    	alert(rowIndex+"---"+rowData.name+"---"+changes.name);
			    }
			}); 
		});
	</script>
</body>
</html>


