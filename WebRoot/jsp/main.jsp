<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="init/include.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="js/bootstrap3.3.7/css/sb-admin-2.min.css" rel="stylesheet">
<link href="js/bootstrap3.3.7/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<script src="js/bootstrap3.3.7/js/sb-admin-2.min.js"></script>

<title>主页</title>
</head>
<body>
	<div id="container-fluid">
		<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
			<!-- head -->
            <jsp:include page="/jsp/init/head.jsp"/>
            <!-- menu -->
            <jsp:include page="/jsp/init/menus.jsp"/>
        </nav>
	</div>
		<div id="page-wrapper">
                <iframe height="100%" width="100%" draggable="false" style="border: 0;" src="jsp/login.jsp"></iframe>
        </div>
</body>
</html>