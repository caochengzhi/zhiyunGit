<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<div class="navbar-header">
	  <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
	      <span class="sr-only">Toggle navigation</span>
	      <span class="icon-bar"></span>
	      <span class="icon-bar"></span>
	      <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" style="color: #fff;">智云订单管理平台</a>
	</div>
	<!-- 头信息栏 -->
    <ul class="nav navbar-top-links navbar-right">
    
    	<!-- 提醒信息栏 -->
		<li class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#"> <i class="fa fa-bell fa-fw"></i>
				<i class="fa fa-caret-down"></i>
				&nbsp;
				<span class="badge badge-pill badge-danger" style="color: red;">5</span>
			</a>
			
			<ul class="dropdown-menu dropdown-alerts">
				<li>
					<a href="#">
						<div>
							<i class="fa fa-comment fa-fw"></i> 
							New Comment 
							<span class="pull-right text-muted small">4 minutes ago</span>
						</div>
					</a>
				</li>
				<li class="divider"></li>
				<li>
					<a href="#">
						<div>
							<i class="fa fa-twitter fa-fw"></i> 
							3 New Followers 
							<span class="pull-right text-muted small">12 minutes ago</span>
						</div>
					</a>
				</li>
			</ul>
		</li>
		
		<!-- 用户管理信息栏 -->
		<li class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
				<i class="fa fa-caret-down"></i>
			</a>
			<ul class="dropdown-menu dropdown-user">
				<li><a href="javascript:onPersonal('personal')"><i class="fa fa-user"></i> 个人中心</a></li>
				<li><a href="javascript:onPersonal('password')"><i class="fa fa-key"></i> 修改密码</a></li>
				<li class="divider"></li>
				<li><a href="javascript:logout()"><i class="fa fa-sign-out"></i> 安全登出</a></li>
			</ul>
		</li>
		
	</ul>
	
	<form action="" name="frm2"  method=""></form>
	<script type="text/javascript">
		function logout(){
			var frm = document.frm2;
			frm.method = "get";
			frm.action = 'logout';
			frm.submit();
		}
		
		function onPersonal(type){
			var frm = document.frm2;
			frm.action = 'userInformation?type='+type;
			frm.method = "post";
			frm.target = "iframepage";
			frm.submit();
		}
	</script>
</html>