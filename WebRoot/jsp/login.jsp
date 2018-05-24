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
			<div class="row" style="margin-top: 100px;">
				<div class="col-sm-4 col-sm-offset-4">
					<div class="form-top" >
						<div class="form-top-left">
							<h3>智云订单管理平台</h3>
						</div>
						<div class="form-top-right">
							<i class="fa fa-lock"></i>
						</div>
					</div>
					<div class="form-bottom">
						<form id="myform" role="form" action="login" method="post" class="login-form">
						
							<div class="form-group">
									<div class="input-group">
										<span class="input-group-addon"> <span class="glyphicon glyphicon-user"></span></span> 
										<input type="text" name="username" id="username" class="form-control" placeholder="请输入账号">
									</div>
							</div>
							
							<div class="form-group">
								<div class="input-group">
									<span class="input-group-addon"> <span class="glyphicon glyphicon-lock"></span></span> 
									<input type="password" name="password" class="form-control" placeholder="请输入密码">
								</div>
							</div>
							<div class="form-group">
								<div class="input-group">
									<span class="input-group-addon"> <span class="glyphicon glyphicon-home"></span></span>
								    <input type="text" name="organizationId" class="form-control" placeholder="租户">
								    <!-- <select class="form-control" id="orgselect" name="organizationId" >
								    	<option value="" ></option>
										<option value="Y" >Y</option>
										<option value="N" >N</option>
									</select> -->
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-5">
									<img src="authImage" style="height: 49px;cursor: hand;" id="verifyImage" title="看不清，点击换一张" /> 
								</div>
								<div class="input-group col-sm-7">
									<span class="input-group-addon"> <span class="glyphicon glyphicon-edit"></span></span>
									<input type="text" name="captcha" id="kaptcha" class="form-control" placeholder="输入验证码">
								</div>
							</div>
							<div class="form-group" align="right">
								<button type="submit" class="btn-success btn">登&nbsp;&nbsp;&nbsp;录</button>
								<b style="color: red;">${errorMsg}</b>
							</div>
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
	
	<script type="text/javascript">
	
		function getCookie(name) {
			var strcookie = document.cookie;//获取cookie字符串
			var arrcookie = strcookie.split("; ");//分割
			//遍历匹配
			for (var i = 0; i < arrcookie.length; i++) {
				var arr = arrcookie[i].split("=");
				if (arr[0] == name) {
					return arr[1];
				}
			}
			return "";
		}
		
		$('#username').val(getCookie("username"));
	</script>
  
</body>

</html>