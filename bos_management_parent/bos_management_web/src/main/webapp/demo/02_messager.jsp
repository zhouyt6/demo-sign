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
	<script type="text/javascript">
	 $.messager.progress();
		   $.messager.alert('提示框','上课不要走神儿！','error'); 
		  $.messager.prompt('提示框','上课不要走神儿！',function(r){
			 alert(r);
		 }); 
		 window.setTimeout(function(){
			 $.messager.show({
				 title:'欢迎框',
				 msg:'欢迎管理员登录系统',
				 timeout:'5000',
				 showType:'silde'
			 });
		 },3000);//延迟加载函数 
		 
	</script>
</body>
</html>