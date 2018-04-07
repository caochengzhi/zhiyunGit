<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/init/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人中心</title>
</head>
<body>
	<ol class="breadcrumb">
		<li class="breadcrumb-item">首页</li>
		<li class="breadcrumb-item">系统管理</li>
		<li class="breadcrumb-item active">个人中心</li>
	</ol>

	<div class="container">
		<div class="panel-body">
			<ul id="myTab" class="nav nav-tabs" >
				<li class="active"><a href="#home" data-toggle="tab"> 个人信息 </a></li>
				<li><a href="#password" data-toggle="tab">修改密码</a></li>
			</ul>
			<div id="myTabContent" class="tab-content" style="padding-top: 50px;">
				<div class="tab-pane fade in active" id="home">
					<form id="frm" name="frm" action="" method="post" class="form-horizontal">
						<div class="form-group">
							<div id="imagediv" class="col-sm-5">
								<img alt="个人头像设置" src="images/backgrounds/1.jpg" width="100%" height="40%">
							</div>
							<div class="col-sm-7">
								<div class="form-group">
									<label class="col-xs-3 col_style_label">登录名：</label>
									<div class="input-group col-sm-8">
										<input type="text" class="form-control" name="loginName" id="loginName" value="${user.loginName}" placeholder="输入登录名...不可为空" /> 
										<span class="input-group-addon"> <span class="fa fa-key"></span></span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-xs-3 col_style_label">用户昵称：</label>
									<div class="input-group col-sm-8">
										<input type="text" class="form-control" name="userName" id="userName" value="${user.userName }" placeholder="输入用户名...不可为空" /> 
										<span class="input-group-addon"> <span class="glyphicon glyphicon-user"></span></span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-xs-3 col_style_label">电子邮箱：</label>
									<div class="input-group col-sm-8">
										<input type="text" class="form-control" id="email" name="email" value=${user.email }>
										<span class="input-group-addon"> <span class="glyphicon glyphicon-envelope"></span></span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-xs-3 col_style_label">手机号码：</label>
									<div class="input-group col-sm-8">
										<input type="text" class="form-control" name="phoneNumber" id="phoneNumber" value="${user.phoneNumber }" placeholder="请输入手机号码" /> 
										<span class="input-group-addon"> <span class="glyphicon glyphicon-phone"></span></span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-xs-3 col_style_label">办公电话：</label>
									<div class="input-group col-sm-8">
										<input type="text" class="form-control" name="Telephone" id="Telephone" value="${user.telephone }" placeholder="请输入固定电话（固话加区号）" /> 
										<span class="input-group-addon"> <span class="glyphicon glyphicon-phone-alt"></span></span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-xs-3 col_style_label">性别：</label>
									<div class="col-sm-8">
										<label> <input type="radio" name="sex" <c:if test="${user.sex=='男' }">checked='checked'</c:if> value="男" /> 男 </label> 
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
										<label> <input type="radio" name="sex" <c:if test="${user.sex=='女' }">checked='checked'</c:if> value="女" /> 女 </label>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button id="savebtn" type="submit" class="btn btn-success" ><i class="glyphicon glyphicon-ok"></i>保 存</button>
							<button type="button" class="btn btn-default" onclick="javascript:history.back(-1);"><i class="fa fa-reply-all"></i>返 回</button>  
						</div>
					</form>
				</div>
				<div class="tab-pane fade" id="password">
					<jsp:include page="/jsp/init/userPassword.jsp"/>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
	$(document).ready(function() { 
		
		$('#frm').bootstrapValidator({
	        container: 'tooltip',
	        feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	        	loginName: {
	                validators: {
	                    stringLength: {
	                        min: 2,
	                        message: '登录名不允许少于5个字符!'
	                    },
	                    notEmpty: {
	                        message: '登录名不允许为空!'
	                    }
	                }
	            },
	            userName: {
	                validators: {
	                    stringLength: {
	                        min: 2,
	                        message: '用户名不允许少于2个字符!'
	                    },
	                    notEmpty: {
	                        message: '用户名不允许为空!'
	                    }
	                }
	            },
	            email: {
	                validators: {
	                    emailAddress: {
	                        message: '邮箱地址格式错误'
	                    }
	                }
	            },
	            phoneNumber: {
	                validators: {
	                    digits: {
	                        message: '电话号码格式错误'
	                    },
	                    phone:{country:'CN'}
	                }
	            },
	            Telephone:{
	            	validators: {
	                    digits: {
	                        message: '电话号码格式错误,固话请加区号'
	                    },
	                    phone:{country:'CN'}
	                }
	            }
	        }
	    });
	})
</script>
	
</body>
</html>