<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/init/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户修改</title>
</head>
<body>
	<ol class="breadcrumb">
		<li class="breadcrumb-item">首页</li>
		<li class="breadcrumb-item">系统管理</li>
		<li class="breadcrumb-item active">用户管理</li>
	</ol>

	<div class="container">
		<form id="frm" name="frm" action="" method="post" class="form-horizontal">
				<legend>用户信息注册</legend>
			<div class="form-group">
				<label class="col-xs-2 col_style_label">登录名：</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" name="loginName"
						id="loginName" placeholder="输入登录名...不可为空" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-2 col_style_label">用户名：</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" name="userName"
						id="userName" placeholder="输入用户名...不可为空" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-2 col_style_label">登录密码：</label>
				<div class="col-sm-7">
					<input type="password" class="form-control" name="loginPassword"
						id="loginPassword" placeholder="登录密码,不少于6个字符...不可为空" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-2 col_style_label">确认密码：</label>
				<div class="col-sm-7">
					<input type="password" class="form-control" name="confirmPassword"
						id="confirmPassword" placeholder="再次确认密码...不可为空" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-2 col_style_label">电话号码：</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" name="phoneNumber"
						id="phoneNumber" placeholder="手机号或固定电话（固话加区号）" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-2 col_style_label">邮箱地址：</label>
				<div class="col-sm-7">
					<input type="text" class="form-control" id="email" name="email" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-2 col_style_label">是否生效：</label>
				<div class="col-sm-3">
					<select class="form-control" id="validselect" name="isValid" >
						<option value="Y">Y</option>
						<option value="N">N</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-2 col_style_label">性别：</label>
				<div class="col-sm-6">
					<label> <input type="radio" name="sex" value="男" /> 男
					</label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <label> <input
						type="radio" name="sex" value="女" /> 女
					</label>
				</div>
			</div>
			
			<legend>用户角色分配</legend>
			<div class="form-group">
			<label class="col-xs-2 col_style_label">角色分配：</label>
			<div class="col-sm-6">
				<select class="form-control" id="roleTypeselect" name="roleType">
				</select>
			</div>
			</div>
			
			<input type="hidden" id="userId" name="userId">
			<div align="right">
				<button id="savebtn" type="submit" class="btn btn-success" ><i class="glyphicon glyphicon-ok"></i>保 存</button>
				<button type="button" class="btn btn-default" onclick="javascript:history.back(-1);"><i class="fa fa-reply-all"></i>返 回</button>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() { 
		$('#roleTypeselect').select2({
			 data: getAjaxJson('dictType=roleType','dictManager/getDictSelect'),
		     placeholder:'请选择...',//默认文字提示
		     language: "zh-CN",//汉化
		     multiple:true,//是否多选
		     allowClear: true//允许清空
		}); 
		$("#roleTypeselect").select2('val','1');
		
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
	            loginPassword:{
	            	validators: {
	                    stringLength: {
	                        min: 6,
	                        message: '密码不允许少于6个字符!'
	                    },
	                    notEmpty: {message:'密码不允许为空!'},
	                    identical: {
	                        field: 'confirmPassword'
	                    },
	                    different: {
	                        field: 'loginName',
	                        message: '不可与登录名相同'
	                    }
	                }
	            },
	            confirmPassword: {
	                validators: {
	                	stringLength: {
	                        min: 6,
	                        message: '密码不允许少于6个字符!'
	                    },
	                    notEmpty: {},
	                    identical: {
	                        field: 'loginPassword'
	                    },
	                    different: {
	                        field: 'loginName',
	                        message: '不可与登录名相同'
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
	            }
	        }
	    });
	})
</script>
</html>