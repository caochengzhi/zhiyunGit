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
		<form id="frm" name="frm" action="userManager/saveUser" method="post" class="form-horizontal">
			<legend>基本信息</legend>
			<div class="form-group">
				<div class="col-sm-6">
					<div class="form-group">
						<label class="col-xs-3 col_style_label">登录名：</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="loginName"  <c:if test="${user.userId !=null }">readonly</c:if>
								id="loginName" value="${user.loginName}" placeholder="输入登录名...不可为空" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-3 col_style_label">用户名：</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="userName"
								id="userName" value="${user.userName }" placeholder="输入用户名...不可为空" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-3 col_style_label">登录密码：</label>
						<div class="col-sm-8">
							<input type="password" class="form-control" name="loginPassword" <c:if test="${user.userId !=null }">readonly</c:if>
								id="loginPassword" value="${user.loginPassword }" placeholder="登录密码,不少于6个字符...不可为空" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-3 col_style_label">是否生效：</label>
						<div class="col-sm-8">
							<select class="form-control" id="validselect" name="isValid" >
								<option value="Y" <c:if test="${user.isValid=='Y' }">selected</c:if>>Y</option>
								<option value="N" <c:if test="${user.isValid=='N' }">selected</c:if>>N</option>
							</select>
						</div>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="form-group">
						<label class="col-xs-3 col_style_label">移动号码：</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="phoneNumber"
								id="phoneNumber" value="${user.phoneNumber }" placeholder="手机号码" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-3 col_style_label">办公电话：</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" name="telephone"
								id="telephone" value="${user.telephone }" placeholder="固定电话（固话加区号）" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-xs-3 col_style_label">邮箱地址：</label>
						<div class="col-sm-8">
							<input type="text" value="${user.email }" class="form-control" id="email" name="email" />
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
			
			<legend>角色分配</legend>
			<div class="form-group">
			<label class="col-xs-2 col_style_label">角色分配：</label>
			<div class="col-sm-9">
				<select class="form-control" id="rolesSelect" name="roleIds">
				</select>
			</div>
			</div>
			<input type="hidden" id="userId" name="userId" value="${user.userId }">
			<input type="hidden" id="creater" name="creater" value="${user.creater }">
			<div align="right">
				<button id="savebtn" type="submit" class="btn btn-success" ><i class="glyphicon glyphicon-ok"></i>保 存</button>
				<button type="button" class="btn btn-default" onclick="javascript:history.back(-1);"><i class="fa fa-reply-all"></i>返 回</button>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() { 
		$('#rolesSelect').select2({
			 data: getAjaxJson('','baseConig/getRolesNameList'),
		     placeholder:'请选择...',//默认文字提示
		     language: "zh-CN",//汉化
		     multiple:true,//是否多选
		     allowClear: true//允许清空
		}); 
		var array = new Array();
		<c:forEach items="${user.roles}" var="role" varStatus="status" >
			var roleId = '${role.roleId}';
			array.push(roleId);
		</c:forEach>
		$("#rolesSelect").val(array).trigger('change');
		
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