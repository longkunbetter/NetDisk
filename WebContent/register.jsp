<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	function form_submit(){
		p1 = $('#p1').val();
		p2 = $('#p2').val();
		if (p1 != null && p2 != null && p1 == p2){
			$('#info').submit();
		}
		else{
			alert("两次输入的密码不一致");
		}
	}
</script>
</head>
<body>
	<form action="user_register" method="post" id="info">
		用户名: <input type="text" name="username"> <br>
		密&nbsp;&nbsp;码: <input	type="password" name="password" id="p1">  <br>
		确认密码:<input type="password" id="p2"> <br>
	</form>
		<button onclick="form_submit()">提交</button>
</body>
</html>