<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>登录首页</title>

<!-- CSS -->
<link rel="stylesheet" href="js/bootstrap3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="js/assets/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="js/assets/css/form-elements.css">
<link rel="stylesheet" href="js/assets/css/style.css">

<!-- 浏览器头的logo cons -->
<link rel="shortcut icon" href="js/assets/ico/favicon.png">
</head>

<body>
	<!-- Top content -->
	<div class="top-content">
		<div class="container">
			<div class="row">
				<div class="col-sm-8 col-sm-offset-2 text">
					<h1>
						<strong>智云订单管理平台</strong>
					</h1>
					<div class="description">
						<p>Based on the cloud order form input, the information track
							prompt feedback controls the platform</p>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-6 col-sm-offset-3 form-box">
					<div class="form-top">
						<div class="form-top-left">
							<h3>Login to our site</h3>
						</div>
						<div class="form-top-right">
							<i class="fa fa-lock"></i>
						</div>
					</div>
					<div class="form-bottom">
						<form id="myform" role="form" action="login" method="post" class="login-form">
							<div class="form-group">
								<label>登录名</label>
								<div class="input-group">
									<span class="input-group-addon"> <span class="glyphicon glyphicon-user"></span></span> 
									<input type="text" name="username" class="form-control" placeholder="登录账号">
								</div>
							</div>

							<div class="form-group">
								<label>密&nbsp;&nbsp;&nbsp;码</label>
								<div class="input-group">
									<span class="input-group-addon"> <span class="glyphicon glyphicon-lock"></span></span> 
									<input type="password" name="password" class="form-control" placeholder="登录密码">
								</div>
							</div>
							
							<div class="form-group" id="orguidgroup">
								<label>企业号</label>
								<div class="input-group">
									<span class="input-group-addon"> <span class="glyphicon glyphicon-home"></span></span>
								    <input type="text" name="organizationId" class="form-control" placeholder="企业号">
								</div>
							</div>
							
							<div class="form-group" id="orguidgroup">
								<label>验证码</label>
								<div class="input-group">
									<span class="input-group-addon"> <span class="glyphicon glyphicon-check"></span></span>
								    <input type="text" name="captcha" id="kaptcha" class="form-control" placeholder="验证码">
								</div>
								<img src="authImage" width="120" id="verifyImage" title="看不清，点击换一张" /> 
							    <br><small>看不清，点击换一张</small>
							</div>

							<button type="submit" class="btn-success btn">登&nbsp;&nbsp;&nbsp;录</button>
							<b style="color: red;">${errorMsg}</b>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Javascript -->
	<script type="text/javascript" src="js/jquery/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/bootstrap3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/assets/js/jquery.backstretch.min.js"></script>
	<script type="text/javascript" src="js/assets/js/scripts.js"></script>
	<script type="text/javascript" src="js/bootstrap3.3.7/js/bootstrapValidator.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			 $('#myform').bootstrapValidator({
			        container: 'tooltip',
			        fields: {
			        	organizationId: {
			        		validators: {
			                    digits: {
			                        message: '企业号必须为数字类型!'
			                    }
			                }
			            }
			        }
			 })
		
		})
	</script>
	
	<script type="text/javascript">
		$('#verifyImage').click(function() {
	        $(this).attr('src','authImage?' + Math.floor(Math.random() * 100));
	    });
	    
	    $('#kaptcha').bind({ 
	        blur:function(){
	            var params  ={
	                kaptcha:this.value                    
	            };
	            $.ajax({
	                 type: "POST",
	                 url: "checkKaptcha",
	                 data: {verityCode:params.kaptcha},
	                 dataType: "json",
	                 success: function(data){
	                	 if(data.msg==-1)
	                        $('#kaptcha').val('');
	                 }
	             });
	        } 
	    });
	</script>
</body>

</html>