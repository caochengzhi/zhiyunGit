<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/init/include.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>智云订单管理平台</title>

</head>
<body>
	<div id="">
		<nav class="navbar navbar-default  menu__header" role="navigation" style="margin-bottom: 1">
			<!-- head -->
            <jsp:include page="/jsp/init/head.jsp"/>
            <!-- menu -->
            <jsp:include page="/jsp/init/menus.jsp"/>
        </nav>
	</div>
	<div id="page-wrapper">
       <iframe src="jsp/init/demo.jsp" marginheight="0" marginwidth="0" frameborder="10" scrolling="no" width="100%" height="100%" id="iframecon" name="iframepage" ></iframe>
   </div>
 
</body>
</html>