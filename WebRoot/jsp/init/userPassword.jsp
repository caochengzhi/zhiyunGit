<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/init/include.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/bootstrap3.3.7/js/strength.js"></script>
<style type="text/css">
.strength_meter {
	position: absolute;
	left: 200px;
	width: 100%;
	z-index: -1;
	border-radius: 5px;
}
</style>
<title>密码修改</title>
</head>
<body>
	<form id="frm_ps" name="frm_ps" action=""  class="form-horizontal">
		<div class="form-group">
			<label class="col-xs-2 col_style_label">旧密码：</label>
			<div class="col-sm-7">
				<input type="password" class="form-control" name="loginPassword"
					id="loginPassword" placeholder="输入登录密码...不可为空" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 col_style_label"></label> 
			<label class="col-xs-7 col_style_label">密码填写建议：长度不小于6位，可以包含，大写英文字母、小写英文字母、数字和符号</label>
		</div>
		<div class="form-group">
			<label class="col-xs-2 col_style_label">新密码：</label>
			<div class="col-sm-7">
				<input type="password" class="form-control" name="confirmPassword"
					id="confirmPassword" placeholder="输入新密码,不少于6个字符...不可为空" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-2 col_style_label">再次确认：</label>
			<div class="col-sm-7">
				<input type="password" class="form-control" name="reConfirmPassword"
					id="reConfirmPassword" placeholder="再次确认新密码...不可为空" />
			</div>
		</div>
		<div class="modal-footer">
			<button type="button" class="updatebtn btn btn-success" ><i class="glyphicon glyphicon-ok"></i>保 存</button>
			<button type="button" class="btn btn-default" onclick="javascript:history.back(-1);"><i class="fa fa-reply-all"></i>返 回</button>  
		</div>
	</form>
	
	<script type="text/javascript">
		$(document).ready(function() { 
			 
			$('#confirmPassword').strength({
				strengthClass: 'form-control',
				strengthMeterClass: 'strength_meter',
				strengthButtonText: '显示密码',
				strengthButtonTextToggle: '隐藏密码'
			});
			
			$(".updatebtn").click(function(){
				var frm_ps = $("#frm_ps");
				var bootstrapValidator = frm_ps.data('bootstrapValidator');  
				bootstrapValidator.validate();  
				if (bootstrapValidator.isValid()){
					$.ajax({ 
						type: 'post', 
						data: frm_ps.serialize(), 
						url: 'changePassword',
						dataType:'text', 
						cache : false,
						success: function (data) {
							if("success"==data){
								toastr.success('保存成功!');
								frm_ps[0].reset();
							}else{
								toastr.warning('保存失败,'+data);
							}
						}   
					}); 
			     }   
			});
			
			$('#frm_ps').bootstrapValidator({
		        feedbackIcons: {
		            valid: 'glyphicon glyphicon-ok',
		            invalid: 'glyphicon glyphicon-remove',
		            validating: 'glyphicon glyphicon-refresh'
		        },
		        fields: {		      
		            loginPassword:{
		            	validators: {
		                    stringLength: {
		                        min: 6,
		                        message: '密码不允许少于6个字符!'
		                    },
		                    notEmpty: {message:'旧密码不允许为空!'}
		                    
		                }
		            },
		            confirmPassword: {
		                validators: {
		                	stringLength: {
		                        min: 6,
		                        message: '密码不允许少于6个字符!'
		                    },
		                    notEmpty: {message:'新密码不允许为空!'}
		                }
		            },
		            reConfirmPassword: {
		                validators: {
		                	stringLength: {
		                        min: 6,
		                        message: '密码不允许少于6个字符!'
		                    },
		                    notEmpty: {message:'新密码不允许为空!'},
		                    identical: {
		                        field: 'confirmPassword'
		                    }
		                }
		            }
		        }
		    });
		});
</script>
</body>
</html>