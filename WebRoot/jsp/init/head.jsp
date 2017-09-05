<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<div class="navbar-header">
      <a class="navbar-brand" style="color: #fff;">智云订单管理平台</a>
	</div>
	<!-- 头信息栏 -->
    <ul class="nav navbar-top-links navbar-right">
    
    	<!-- 提醒信息栏 -->
		<li class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" href="#"> <i class="fa fa-bell fa-fw"></i>
				<i class="fa fa-caret-down"></i>
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