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
<script type="text/javascript">
	function doAdd() {
		$('#tt').tabs('add',{    
		    title:'动态选型卡',    
		    content:'这是一个新添加的动态选型卡',    
		    closable:true, 
		    tools:[{    
		        iconCls:'icon-mini-refresh',    
		        handler:function(){    
		            alert('refresh');    
		        }    
		    }]    
		});  
	}
</script>
</head>
<body class="easyui-layout">
	 <div data-options="region:'north'" title="BOS系统管理系统" style="height:100px">系统管理系统</div>	
	 <div data-options="region:'west'"  title="西"  style="width:200px">
	 	<!-- 折叠面板 accordion  -->
	 	<div class="easyui-accordion" fit="true">
	 		<div title="面板1" data-options="iconCls:'icon-help'">
	 			<a onclick="doAdd();" class="easyui-linkbutton">动态添加选项卡</a>
	 		</div>
	 		<div title="面板2">
	 		<!-- 基于标准json 构建的ztree树  -->
	 		<!-- 1.创建容器 :ul标签  -->
	 		<!-- 2.定义核心变量setting,如果使用的是标准json构建,setting可以是{}, 使用默认值 -->
	 		<!-- 3.定义节点数组: -->
			<!-- 4.初始化树 -->
			
			<!-- 1.创建容器 :ul标签  -->
	 		<ul id="ztree1" class="ztree"></ul>
	 		<script type="text/javascript">
	 			$(function() {
	 				/* 2.定义核心变量setting,如果使用的是标准json构建,setting可以是{}, 使用默认值 */
					var setting = {};
					/* 3.定义节点数组: */
					var nodes = [
					             {name:'系统管理'},
					             {name:'用户管理',children: [
						                         			{name:'用户添加'},
						                         			{name:'用户修改'},
						                         			{name:'用户删除'}
					             						  ]},
					             {name:'权限管理'}
					             ];
					/* 4.初始化树 */
					$.fn.zTree.init($("#ztree1"), setting, nodes);
				});
	 		</script>
	 		</div>
	 		<div title="面板3">
	 			<!-- 基于简单json构建的ztree树 -->
	 			<!-- 1.创建rongqi: ul标签 -->
	 			<ul id="ztree2" class="ztree"></ul>
	 			<script type="text/javascript">
	 				$(function() {
	 					/* 2.定义核心变量setting data */
		 				var setting = {
	 							data:{
	 								simpleData:{
	 									enable:true  //开启简单json数据功能
	 								}
	 							}
	 					};
		 				/* 3.定义节点数组: */
						var nodes = [
							             //id 节点的唯一标识
							             // pId : 父节点的id值
							       		{id:'1',pId:'0',name:'系统管理'},
							       		{id:'2',pId:'0',name:'用户管理'},
							       		{id:'21',pId:'2',name:'用户添加'},
							       		{id:'22',pId:'2',name:'用户修改'},
							       		{id:'23',pId:'2',name:'用户删除'},
							       		{id:'3',pId:'0',name:'权限管理'}
						            ]; 
						/* 4.初始化树 */
						$.fn.zTree.init($("#ztree2"), setting, nodes);
					});
	 			</script>
	 		</div>
	 		<div title="面板4">
	 		<!-- 基于标准json 构建的ztree树  -->
	 		<!-- 1.创建容器 :ul标签  -->
	 		<ul id="ztree3" class="ztree"></ul>
	 		<script type="text/javascript">
	 		/* 2.定义核心变量setting,如果使用的是标准json构建,setting可以是{}, 使用默认值 */
	 		 var setting = {
	 				data: {
	 					simpleData: {
	 						enable: true // 开启简单json数据功能
	 					}
	 				},
	 				callback:{
	 					onClick: function zTreeOnClick(event, treeId, treeNode) {
	 						//1.判断是否是根节点
	 						 var page= treeNode.page;
	 					     if(page!= undefined) {
	 							//2.不是根节点，子节点，可以添加选项卡
								//2.1判断选项卡是否存在
								if($('#tt').tabs('exists',treeNode.name)) {
									//2.2已存在，选中
									$('#tt').tabs('select',treeNode.name);
								} else {
									$('#tt').tabs('add',{    
									    title:treeNode.name,    
									    content:'<iframe src="${pageContext.request.contextPath}/'+page+'" style="height:100%;width:100%" frameborder="0"></iframe>',    
									    closable:true, 
									    tools:[{    
									        iconCls:'icon-mini-refresh',    
									        handler:function(){    
									            alert('refresh');    
									        }    
									    }]    
									});  
								} 
	 						} 
	 					}
	 				}
				};
	 		/* 3.使用ajax动态加载节点数据 */
	 		var url="${pageContext.request.contextPath}/data/menu.json";
	 		$.post(url,{},function(data){
	 			/* 4.初始化树 */
	 			$.fn.zTree.init($("#ztree3"), setting, data);
	 		},'json');
	 		</script>
	 		
	 		</div>
	 	</div>
	 </div>
	 <div data-options="region:'center'">
	 	<!-- 选项卡  
	 	fit : 是自适应属性,设置true填充整个父容器  -->
	 	<div  id="tt" class="easyui-tabs" fit="true">
	 		<div title="面板1" data-options="iconCls:'icon-help',closable:true">111</div>
	 	</div>
	 </div>	
	 <div data-options="region:'east'"  style="width:100px">东</div>	
	 <div data-options="region:'south'"  style="height:50px">南</div>	
</body>
</html>