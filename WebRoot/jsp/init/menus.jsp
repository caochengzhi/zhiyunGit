<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <link rel="stylesheet" href="css/reset.css">
 <link rel="stylesheet" href="css/menu-style.css" media="screen" type="text/css" />
 <script src="js/bootstrap3.3.7/js/metisMenu.min.js"></script>
 <style type="text/css">
 .font-weight{
	font-weight: 500; 
	font-size: 15px;
 }
 </style>
</head>
<body style="background: #6e757f;">

	<div class="navbar-default sidebar" role="navigation" >
	
		<div class="sidebar-nav navbar-collapse" >
		
			<ul class="nav" id="side-menu">
			
				<li class="nav__item">
					<a href="javascript:submit();" class="nav__item-link is-active"><i class="fa fa-home fa-fw"></i>Dashboard<span class="badge badge--warning">33条提醒</span></a>
				</li>
				
				<li class="nav__item"><a href="#" class="nav__item-link"><i class="fa fa-gear fa-fw"></i>
					<span class="font-weight">系统管理</span>
					<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a href="" class="nav__item-link ">系统注册</a></li>
						<li><a id="usermanager" href="javascript:topage('userManager/toSearch');" class="nav__item-link">用户管理</a></li>
						<li><a href="" class="nav__item-link">角色管理</a></li>
						<li><a href="" class="nav__item-link">日志管理</a></li>
					</ul>
				</li>

				<li class="active"><a href="#" class="nav__item-link"><i class="fa fa-paste fa-fw"></i>
					<span class="font-weight">订单管理</span>
					<span class="fa arrow"></span></a>
					<ul class="nav nav-second-level">
						<li><a class="nav__item-link" class="active" href="blank.html" >实例Demo</a></li>
						<li><a href="login.html" class="nav__item-link">Login Page</a></li>
					</ul> 
				</li>
				<li><a href="index.html" class="nav__item-link"><i class="fa fa-flag fa-fw"></i>FAQ</a></li>
				
			</ul>
		</div>
	</div>
	
	<form action="" name="frm" target="iframepage" method="post"></form>
	
	<script type="text/javascript">
		function topage(actionCode){
			var frm = document.frm;
			frm.action = actionCode;
			frm.submit();
		}
	</script>
</body>
</html>