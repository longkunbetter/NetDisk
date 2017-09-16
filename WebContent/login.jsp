<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%
	if (session.getAttribute("username") != null){
		request.getRequestDispatcher("/WEB-INF/user_center.jsp").forward(request, response);
	}
%>	
	
<!doctype html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>欢迎登录</title>
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="stylesheet" href="css/amazeui.min.css">
<link rel="stylesheet" href="css/app.css">
</head>
<body>
	<div class="am-g myapp-login">
		<div class="myapp-login-bg">
			<div class="myapp-login-logo">
				<i class="am-icon-stumbleupon"></i>
			</div>
			<div class="am-u-sm-10 myapp-login-form">
				<form class="am-form" action="user_login" method="post">
					<fieldset>

						<div class="am-form-group">

							<input type="text" class="" id="doc-ipt-email-1"
								placeholder="用户名" name="username">
						</div>

						<div class="am-form-group">

							<input type="password" class="" id="doc-ipt-pwd-1"
								placeholder="密&nbsp&nbsp码" name="password">
						</div>
						<p>
							<button type="submit" class="am-btn am-btn-default">登录</button>
						</p>
						<div>
							<div class="login-text" style="display: inline;">忘记密码?</div>
							<div style="display: inline;" class="login-text"><a href="register.jsp">注册</a></div>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
	<!--[if (gte IE 9)|!(IE)]><!-->
	<script src="js/jquery.min.js"></script>
	<!--<![endif]-->
	<!--[if lte IE 8 ]>
<script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="assets/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->
	<script src="js/amazeui.min.js"></script>
	<script src="js/app.js"></script>
</body>
</html>