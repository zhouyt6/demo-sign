<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>角色添加</title>
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
		<!-- 导入ztree类库 -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/js/ztree/zTreeStyle.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/ztree/jquery.ztree.all-3.5.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(function(){
				//页面加载完成后,发送ajax请求,查询所有权限数据,在页面中展示为复选框
				var url= '${pageContext.request.contextPath}/permissionAction_findAll.action';
				$.post(url,function(data){
					for(var i=0;i<data.length;i++) {
						var id = data[i].id;
						var name = data[i].name;
						$('#permTD').append('<input type="checkbox" name="permissionIds" value="'+id+'" />'+name);
					}
				},'json');
				
				// 授权树初始化
				var setting = {
					data : {
						key : {
							title : "t"
						},
						simpleData : {
							enable : true
						}
					},
					check : {
						enable : true
					}
				};
				
				$.ajax({
					url : '${pageContext.request.contextPath}/menuAction_findAll.action',
					type : 'POST',
					dataType : 'json',
					success : function(data) {
						$.fn.zTree.init($("#menuTree"), setting, data);
					},
					error : function(msg) {
						alert('树加载异常!');
					}
				});
				
				// 点击保存
				$('#save').click(function(){
					if($('#roleForm').form('validate')){
						//根据id获取ztree对象
						var treeObj = $.fn.zTree.getZTreeObj('menuTree');
						//在提交表单之前需要调用ztree插件的api,动态获取 当期选中的节点
						var nodes = treeObj.getCheckedNodes(true);
						var array = new Array();
						for(var i=0;i<nodes.length;i++){
							var id = nodes[i].id; //菜单id
							array.push(id);
						}
						//将选中节点(菜单)的id拼接成字符串,将字符串赋值给隐藏域
						var menuIds = array.join(",");
						$("input[name=menuIds]").val(menuIds);
						$('#roleForm').submit();
					} 
				});
			});
		</script>
	</head>

	<body class="easyui-layout">
		<div region="north" style="height:31px;overflow:hidden;" split="false" border="false">
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true">保存</a>
			</div>
		</div>
		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="roleForm" method="post"
			action="${pageContext.request.contextPath}/roleAction_add.action">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">角色信息</td>
					</tr>
					<!-- 提供menuIds隐藏域  -->
					<input type="hidden" name="menuIds"/>
					<tr>
						<td>名称</td>
						<td>
							<input type="text" name="name" class="easyui-validatebox" data-options="required:true" />
						</td>
					</tr>
					<tr>
						<td>关键字</td>
						<td>
							<input type="text" name="keyword" class="easyui-validatebox" data-options="required:true" />
						</td>
					</tr>
					<tr>
						<td>描述</td>
						<td>
							<textarea name="description" rows="4" cols="60"></textarea>
						</td>
					</tr>
					<tr>
						<td>权限选择</td>
						<td id="permTD"></td>
					</tr>
					<tr>
						<td>菜单授权</td>
						<td>
							<ul id="menuTree" class="ztree"></ul>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>

</html>