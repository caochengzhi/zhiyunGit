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
				<li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a></li>
				<li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a></li>
				<li class="divider"></li>
				<li><a href="#"><i class="fa fa-sign-out fa-fw"></i>Logout</a></li>
			</ul>
		</li>
		
	</ul>
</html>