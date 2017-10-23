<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
		<title>智云科技</title>
		
		<style type="text/css">
			body{ margin:0; padding:0; background:#efefef; font-family:Georgia, Times, Verdana, Geneva, Arial, Helvetica, sans-serif; }
			div#errorBox{ background: url(404_bg.png) no-repeat top left; width:943px; height:572px; margin:auto; }
			div#errorText{ color:#39351e; padding:15px 0 0 0px }
			div#errorText p{ width:303px; font-size:14px; line-height:26px; }
			h1{ font-size:50px; margin-bottom:35px; }
		</style>
		
	</head>
	<body>
			<div id="errorBox">
				<div id="errorText" align="left">
					<h1 align="center">500网页崩溃了!!!</h1>
					${errorMsg}
					<p>
						火星不太安全，我可以免费送你回地球
						${currentTime }
					</p>
				</div>
			</div>
	</body>
</html>