<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>管理定区/调度排班</title>
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
		<script type="text/javascript">
			function doAdd(){
				$('#addWindow').window("open");
			}
			
			function doEdit(){
				alert("修改...");
			}
			
			function doDelete(){
				alert("删除...");
			}
			
			function doSearch(){
				$('#searchWindow').window("open");
			}
			var id; //当前选中的定区id
			function doAssociations(){
				//获取datagrid选中数据
				var rows =  $('#grid').datagrid('getSelections');
				if(rows.length != 1){
					$.messager.alert('提示信息','请选择一个定区','warning');
				} else {
					id = rows[0].id;
					//打开关联窗口
					$('#customerWindow').window('open');
					//发送ajax请求查询并显示未关联定区的客户
					//5.1清空下拉框
				   $('#noassociationSelect').empty();
					var url ="${pageContext.request.contextPath}/fixedAreaAction_findNoAssociateCustomers.action";
					$.post(url,{},function(data){
						for (var i= 0; i < data.length;i++) {
							var id = data[i].id;
							var username = data[i].username;
							$('#noassociationSelect').append('<option value="'+id+'">'+username+'</option>');
						}
					},'json'); 
					//5.发送ajax请求查询关联定区的客户，将返回客户显示到右边下拉框
					//5.1清空下拉框         传的参数定区id-----id:rows[0].id  
					 $('#associationSelect').empty();
					var url = "${pageContext.request.contextPath}/fixedAreaAction_findAssociateCustomers.action";
					$.post(url,{id:rows[0].id},function(data){
						for(var i=0; i<data.length; i++){
							var id = data[i].id;
							var username = data[i].username;
							$('#associationSelect').append('<option value="'+id+'">'+username+'</option>');
						}
					},'json'); 
				}
			}
			/*关联分区的操作  */
			function doAss(){
				//获取datagrid选中数据
				var rows =  $('#grid').datagrid('getSelections');
				if(rows.length != 1){
					$.messager.alert('提示信息','请选择一个定区','warning');
				} else {
					id = rows[0].id;
					//打开关联窗口
					$('#subareaWindow').window('open');
					//发送ajax请求查询并显示未关联定区的客户
					//5.1清空下拉框
				   $('#noassSelect').empty();
					var url ="${pageContext.request.contextPath}/subAreaAction_findNoAssociateSubArea.action";
					$.post(url,{},function(data){
						for (var i= 0; i < data.length;i++) {
							var id = data[i].id;
							var keyWords = data[i].keyWords;
							$('#noassSelect').append('<option value="'+id+'">'+keyWords+'</option>');
						}
					},'json'); 
					//5.发送ajax请求查询关联定区的客户，将返回客户显示到右边下拉框
					//5.1清空下拉框         传的参数定区id-----id:rows[0].id  
					 $('#assSelect').empty();
					var url = "${pageContext.request.contextPath}/subAreaAction_findAssociateSubArea.action";
					$.post(url,{id:rows[0].id},function(data){
						for(var i=0; i<data.length; i++){
							var id = data[i].id;
							var keyWords = data[i].keyWords;
							$('#assSelect').append('<option value="'+id+'">'+keyWords+'</option>');
						}
					},'json'); 
				}
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
				id : 'button-association',
				text : '关联客户',
				iconCls : 'icon-sum',
				handler : doAssociations
			},{
				id : 'button-association-courier',
				text : '关联快递员',
				iconCls : 'icon-sum',
				handler : function(){
					// 判断是否已经选中了一个定区，弹出关联快递员窗口 
					var rows = $("#grid").datagrid('getSelections');
					if(rows.length==1){
						//给id赋值,给定区id赋值
						id = rows[0].id;
						// 只选择了一个定区
						// 弹出定区关联快递员 窗口 
						$("#courierWindow").window('open');
					}else{
						// 没有选中定区，或者选择 了多个定区
						$.messager.alert("警告","关联快递员,只能（必须）选择一个定区","warning");
					}
				}
			},{
				id : 'button-association2',
				text : '关联分区',
				iconCls : 'icon-sum',
				handler : doAss
			}];
			// 定义列
			var columns = [ [ {
				field : 'id',
				title : '编号',
				width : 80,
				align : 'center',
				checkbox:true
			},{
				field : 'fixedAreaName',
				title : '定区名称',
				width : 120,
				align : 'center'
			}, {
				field : 'fixedAreaLeader',
				title : '负责人',
				width : 120,
				align : 'center'
			}, {
				field : 'telephone',
				title : '联系电话',
				width : 120,
				align : 'center'
			}, {
				field : 'company',
				title : '所属公司',
				width : 120,
				align : 'center'
			} ] ];
			
			$(function(){
				// 先将body隐藏，再显示，不会出现页面刷新效果
				$("body").css({visibility:"visible"});
				
				// 定区数据表格
				$('#grid').datagrid( {
					iconCls : 'icon-forward',
					fit : true,
					border : true,
					rownumbers : true,
					striped : true,
					pageList: [30,50,100],
					pagination : true,
					toolbar : toolbar,
					url : "${pageContext.request.contextPath}/fixedareaAction_pageQuery.action",
					idField : 'id',
					columns : columns,
					onDblClickRow : doDblClickRow,
					singleSelect:true
				});
				
				// 添加、修改定区
				$('#addWindow').window({
			        title: '添加修改定区',
			        width: 600,
			        modal: true,
			        shadow: true,
			        closed: true,
			        height: 400,
			        resizable:false
			    });
				
				// 查询定区
				$('#searchWindow').window({
			        title: '查询定区',
			        width: 400,
			        modal: true,
			        shadow: true,
			        closed: true,
			        height: 400,
			        resizable:false
			    });
				$("#btn").click(function(){
					alert("执行查询...");
				});
				
			});
		
			function doDblClickRow(){
				alert("双击表格数据...");
				$('#association_subarea').datagrid( {
					fit : true,
					border : true,
					rownumbers : true,
					striped : true,
					url : "${pageContext.request.contextPath}/data/association_subarea.json",
					columns : [ [{
						field : 'id',
						title : '分拣编号',
						width : 120,
						align : 'center'
					},{
						field : 'province',
						title : '省',
						width : 120,
						align : 'center',
						formatter : function(data,row ,index){
							if(row.area!=null){
								return row.area.province;
							}
							return "";
						}
					}, {
						field : 'city',
						title : '市',
						width : 120,
						align : 'center',
						formatter : function(data,row ,index){
							if(row.area!=null){
								return row.area.city;
							}
							return "";
						}
					}, {
						field : 'district',
						title : '区',
						width : 120,
						align : 'center',
						formatter : function(data,row ,index){
							if(row.area!=null){
								return row.area.district;
							}
							return "";
						}
					}, {
						field : 'addresskey',
						title : '关键字',
						width : 120,
						align : 'center'
					}, {
						field : 'startnum',
						title : '起始号',
						width : 100,
						align : 'center'
					}, {
						field : 'endnum',
						title : '终止号',
						width : 100,
						align : 'center'
					} , {
						field : 'single',
						title : '单双号',
						width : 100,
						align : 'center'
					} , {
						field : 'position',
						title : '位置',
						width : 200,
						align : 'center'
					} ] ]
				});
				$('#association_customer').datagrid( {
					fit : true,
					border : true,
					rownumbers : true,
					striped : true,
					url : "${pageContext.request.contextPath}/data/association_customer.json",
					columns : [[{
						field : 'id',
						title : '客户编号',
						width : 120,
						align : 'center'
					},{
						field : 'name',
						title : '客户名称',
						width : 120,
						align : 'center'
					}, {
						field : 'company',
						title : '所属单位',
						width : 120,
						align : 'center'
					}]]
				});
				
			}
		</script>
	</head>

	<body class="easyui-layout" style="visibility:hidden;">
		<div region="center" border="false">
			<table id="grid"></table>
		</div>
		<div region="south" border="false" style="height:150px">
			<div id="tabs" fit="true" class="easyui-tabs">
				<div title="关联分区" id="subArea" style="width:100%;height:100%;overflow:hidden">
					<table id="association_subarea"></table>
				</div>
				<div title="关联客户" id="customers" style="width:100%;height:100%;overflow:hidden">
					<table id="association_customer"></table>
				</div>
			</div>
		</div>

		<!-- 添加 修改定区 -->
		<div class="easyui-window" title="定区添加修改" id="addWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
			<div style="height:31px;overflow:hidden;" split="false" border="false">
				<div class="datagrid-toolbar">
					<a id="save" icon="icon-save" href="#" class="easyui-linkbutton" plain="true">保存</a>
					<script type="text/javascript">
						$('#save').click(function(){
							if($('#fixedAreaForm').form('validate')){
								$('#fixedAreaForm').submit();
							}
						});
					</script>
					</script>
				</div>
			</div>

			<div style="overflow:auto;padding:5px;" border="false">
				<form id="fixedAreaForm" method="post" action="${pageContext.request.contextPath}/fixedArea_save.action">
					<table class="table-edit" width="80%" align="center">
						<tr class="title">
							<td colspan="2">定区信息</td>
						</tr>
						<tr>
							<td>定区编码</td>
							<td><input type="text" name="id" class="easyui-validatebox"
								required="true" /></td>
						</tr>
						<tr>
							<td>定区名称</td>
							<td><input type="text" name="fixedAreaName"
								class="easyui-validatebox" required="true" /></td>
						</tr>
						<tr>
							<td>负责人</td>
							<td><input type="text" name="fixedAreaLeader"
								class="easyui-validatebox" required="true" /></td>
						</tr>
						<tr>
							<td>联系电话</td>
							<td><input type="text" name="telephone"
								class="easyui-validatebox" required="true" /></td>
						</tr>
						<tr>
							<td>所属公司</td>
							<td><input type="text" name="company"
								class="easyui-validatebox" required="true" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<!-- 查询定区 -->
		<div class="easyui-window" title="查询定区窗口" id="searchWindow" collapsible="false" minimizable="false" maximizable="false" style="top:20px;left:200px">
			<div style="overflow:auto;padding:5px;" border="false">
				<form>
					<table class="table-edit" width="80%" align="center">
						<tr class="title">
							<td colspan="2">查询条件</td>
						</tr>
						<tr>
							<td>定区编码</td>
							<td>
								<input type="text" name="id" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>所属单位</td>
							<td>
								<input type="text" name="company" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td>分区</td>
							<td>
								<input type="text" name="subareaName" class="easyui-validatebox" required="true" />
							</td>
						</tr>
						<tr>
							<td colspan="2"><a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a> 
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>

		<!-- 关联客户窗口 -->
		<div modal="true" class="easyui-window" title="关联客户窗口" id="customerWindow" collapsible="false" closed="true" minimizable="false" maximizable="false" style="top:20px;left:200px;width: 400px;height: 300px;">
			<div style="overflow:auto;padding:5px;" border="false">
				<form id="customerForm" action="${pageContext.request.contextPath}/fixedAreaAction_assignCustomers2FixedArea.action" method="post">
					<table class="table-edit" width="80%" align="center">
						<tr class="title">
							<td colspan="3">关联客户</td>
						</tr>
						<tr>
							<td>
								<input type="hidden" name="id" id="customerFixedAreaId" />
								<select id="noassociationSelect" multiple="multiple" size="10"></select>
							</td>
							<td>
								<input type="button" value="》》" id="toRight">
								<br/>
								<input type="button" value="《《" id="toLeft">
								<script type="text/javascript">
									$(function(){
										//1.给向右的按钮绑定事件
										$('#toRight').click(function(){
											//2.选中左边的项
											//3,将左边的项移动到右边
											$('#associationSelect').append($('#noassociationSelect option:selected'));
										});
										//1.给向左的按钮绑定事件
										$('#toLeft').click(function(){
											//2.选中右边的项
											//3,将右边的项移动到左边
											$('#noassociationSelect').append($('#associationSelect option:selected'));
										});
									});
								</script>
							</td>
							<td>
								<select id="associationSelect" name="customerIds" multiple="multiple" size="10"></select>
							</td>
						</tr>
						<tr>
							<td colspan="3"><a id="associationBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">关联客户</a>
								<script type="text/javascript">
									$(function(){
										$('#associationBtn').click(function(){
											//1.给定区id隐藏域赋值
											$('#customerFixedAreaId').val(id);
											//2.将右边下拉框所有选项选中
											$('#associationSelect option').attr('selected','selected');
											//3.提交表单
											$('#customerForm').submit();
										});
									});
								</script>
							 </td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<!-- 关联分区窗口 -->
		<div modal="true" class="easyui-window" title="关联分区窗口" id="subareaWindow" collapsible="false" closed="true" minimizable="false" maximizable="false" style="top:20px;left:200px;width: 400px;height: 300px;">
			<div style="overflow:auto;padding:5px;" border="false">
				<form id="subareaForm" action="${pageContext.request.contextPath}/fixedAreaAction_assignSubArea2FixedArea.action" method="post">
					<table class="table-edit" width="80%" align="center">
						<tr class="title">
							<td colspan="3">关联分区</td>
						</tr>
						<tr>
							<td>
								<input type="hidden" name="id" id="subareaFixedAreaId" />
								<select id="noassSelect" multiple="multiple" size="10"></select>
							</td>
							<td>
								<input type="button" value="》》" id="toR">
								<br/>
								<input type="button" value="《《" id="toL">
								<script type="text/javascript">
									$(function(){
										//1.给向右的按钮绑定事件
										$('#toR').click(function(){
											//2.选中左边的项
											//3,将左边的项移动到右边
											$('#assSelect').append($('#noassSelect option:selected'));
										});
										//1.给向左的按钮绑定事件
										$('#toL').click(function(){
											//2.选中右边的项
											//3,将右边的项移动到左边
											$('#noassSelect').append($('#assSelect option:selected'));
										});
									});
								</script>
							</td>
							<td>
								<select id="assSelect" name="subareaIds" multiple="multiple" size="10"></select>
							</td>
						</tr>
						<tr>
							<td colspan="3"><a id="assBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">关联分区</a>
								<script type="text/javascript">
									$(function(){
										$('#assBtn').click(function(){
											//1.给定区id隐藏域赋值
											$('#subareaFixedAreaId').val(id);
											//2.将右边下拉框所有选项选中
											$('#assSelect option').attr('selected','selected');
											//3.提交表单
											$('#subareaForm').submit();
										});
									});
								</script>
							 </td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		
		<!-- 关联快递员窗口 -->
		<div class="easyui-window" title="关联快递员窗口" id="courierWindow" modal="true"
			collapsible="false" closed="true" minimizable="false" maximizable="false" style="top:20px;left:200px;width: 700px;height: 300px;">
			<div style="overflow:auto;padding:5px;" border="false">
				<form id="courierForm" 
					action="${pageContext.request.contextPath}/fixedAreaAction_associationCourierToFixedArea.action" method="post">
					<table class="table-edit" width="80%" align="center">
						<tr class="title">
							<td colspan="2">关联快递员</td>
						</tr> 
						<tr>
							<td>选择快递员</td>
							<td>
								<!-- 存放定区编号 -->
								<input type="hidden" name="id" id="courierFixedAreaId" /> 
								<input data-options="ditable:false, url:'${pageContext.request.contextPath}/courierAction_listajax.action',valueField:'id',textField:'name'"
									 type="text" name="courierId" class="easyui-combobox" required="true" />
							</td>
						</tr>
						<tr>
							<td>选择收派时间</td>
							<td>
								<input type="text" name="takeTimeId" class="easyui-combobox" required="true" 
								data-options="ditable:false, url:'${pageContext.request.contextPath}/taketimeAction_listajax.action',valueField:'id',textField:'name'"/>
								
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<a id="associationCourierBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">关联快递员</a>
								<script type="text/javascript">
									$(function(){
										$('#associationCourierBtn').click(function(){
											//给隐藏域赋值
											$('#courierFixedAreaId').val(id);
											var b = $('#courierForm').form('validate');
											if(b){
												$('#courierForm').submit();
											}
										});
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