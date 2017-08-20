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
                            <h1><strong>智云订单管理平台</strong></h1>
                            <div class="description">
                            	<p>Based on the cloud order form input, the information track prompt feedback controls the platform</p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 form-box">
                        	<div class="form-top">
                        		<div class="form-top-left">
                        			<h3>Login to our site</h3>
                            		<p>Enter your username and password to log on:</p>
                        		</div>
                        		<div class="form-top-right">
                        			<i class="fa fa-lock"></i>
                        		</div>
                            </div>
                            <div class="form-bottom">
			                    <form role="form" action="login/Verification" method="post" class="login-form">
			                    	<div class="form-group">
			                    		<label class="sr-only" for="form-username">Username</label>
			                        	<input type="text" name="username" placeholder="Username..." class="form-username form-control" id="form-username">
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="form-password">Password</label>
			                        	<input type="password" name="password" placeholder="Password..." class="form-password form-control" id="form-password">
			                        </div>
			                        <button type="submit" class="btn-success btn">Sign in!</button>
			                    </form>
		                    </div>
                        </div>
                    </div>
                </div>
        </div>

        <!-- Javascript -->
        <script src="js/jquery/jquery-3.2.1.min.js"></script>
        <script src="js/bootstrap3.3.7/js/bootstrap.min.js"></script>
        <script src="js/assets/js/jquery.backstretch.min.js"></script>
        <script src="js/assets/js/scripts.js"></script>
        
        <!--[if lt IE 10]>
            <script src="js/assets/js/placeholder.js"></script>
        <![endif]-->

    </body>

</html>