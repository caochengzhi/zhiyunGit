<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%   
String path = request.getContextPath();   
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";   
%>  
<html>
	<head>
	<base href="<%=basePath%>"> 
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
	<head>
		<link rel="stylesheet" type="text/css" href="js/bootstrap3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="js/bootstrap3.3.7/css/bootstrap-theme.min.css">
		<link rel="stylesheet" type="text/css" href="js/bootstrap3.3.7/css/grid.css">
		
		<script type="text/javascript" src="js/jquery/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="js/bootstrap3.3.7/js/bootstrap.min.js"></script>
	</head>
</html>