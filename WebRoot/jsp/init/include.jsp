<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
		<!-- <link rel="stylesheet" type="text/css" href="js/bootstrap3.3.7/css/grid.css"> -->
		<link rel="stylesheet" type="text/css" href="js/bootstrap3.3.7/css/bootstrap-table.min.css">
		<link rel="stylesheet" type="text/css" href="js/bootstrap3.3.7/css/bootstrapValidator.min.css">
		<link rel="stylesheet" type="text/css" href="js/bootstrap3.3.7/css/bootstrap-switch.min.css">
		<link rel="stylesheet" type="text/css" href="js/bootstrap3.3.7/css/select2.min.css">
		<link rel="stylesheet" type="text/css" href="js/bootstrap3.3.7/css/jquery.treegrid.min.css">
		<link rel="stylesheet" href="js/zTree/css/metroStyle/metroStyle.css" type="text/css">
	
		<link rel="stylesheet" type="text/css" href="css/breadcrumb-style.css"/>
		<link rel="stylesheet" type="text/css" href="css/scdp-style.css"/>
		<link rel="stylesheet" type="text/css" href="css/toastr.min.css">
	
		<link href="js/bootstrap3.3.7/css/sb-admin-2.min.css" rel="stylesheet">
		<link href="js/bootstrap3.3.7/css/font-awesome.min.css" rel="stylesheet" type="text/css">
		
		<script type="text/javascript" src="js/jquery/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="js/bootstrap3.3.7/js/scdp.js"></script>
		<script type="text/javascript" src="js/bootstrap3.3.7/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/bootstrap3.3.7/js/bootstrap-table.min.js"></script>
		<script type="text/javascript" src="js/bootstrap3.3.7/js/bootstrap-table-treegrid.js"></script>
		<script type="text/javascript" src="js/bootstrap3.3.7/js/bootstrap-table-zh-CN.min.js"></script>
		<script type="text/javascript" src="js/bootstrap3.3.7/js/bootstrap-table-export.min.js"></script>
		<script type="text/javascript" src="js/bootstrap3.3.7/js/bootstrapValidator.min.js"></script>
		<script type="text/javascript" src="js/bootstrap3.3.7/js/select2.full.min.js"></script>
		<script type="text/javascript" src="js/bootstrap3.3.7/js/bootstrap-switch.min.js"></script>
		<script type="text/javascript" src="js/bootstrap3.3.7/js/bootbox.min.js"></script>
		<script type="text/javascript" src="js/bootstrap3.3.7/js/toastr.min.js"></script>
		<script type="text/javascript" src="js/zTree/js/jquery.ztree.core.min.js"></script>
		<script type="text/javascript" src="js/zTree/js/jquery.ztree.excheck.min.js"></script>
		
		
		<script src="js/bootstrap3.3.7/js/sb-admin-2.min.js"></script>
		
		<style type="text/css">
			.col_style_label{
				background: #ffffff;
				border:0px;
				padding-top: 8px;
			}
			
			.col_style_input{
				background: #ffffff;
				border:0px;"
			}
		</style>
		
		<script type="text/javascript">
		$(document).ready(function() {
			//弹出框参数设置，若用默认值可以省略以下面代
			toastr.options = {
				"closeButton": true, //是否显示关闭按钮
				"positionClass": "toast-top-center",//弹出窗的位置
				"showDuration": "300",//显示的动画时间
				"hideDuration": "1000",//消失的动画时间
				"timeOut": "3000", //展现时间
				"extendedTimeOut": "1000",//加长展示时间
				"showEasing": "swing",//显示时的动画缓冲方式
				"hideEasing": "linear",//消失时的动画缓冲方式
				"showMethod": "fadeIn",//显示时的动画方式
				"hideMethod": "fadeOut" //消失时的动画方式
			}
		});
		
		</script>
	</head>
	<form action="" name="subfrm" id="subfrm" target="iframepage" method="post"></form>
</html>