<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人中心</title>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/jsonParse.js"></script>
<script type="text/javascript">
	function loginout() {
		if (confirm("确定要退出吗") == true) {
			window.location.href = "user_loginout";
		}
	}
</script>
</head>
<body>
	欢迎用户${username} &nbsp;&nbsp;
	<a href="javascript:loginout()">退出登录</a>
	<br>
	<br> 我的文件
	<br>
	<br>
	<div>
		当前目录:
		<div style="display: inline;" id="current_path"></div>
	</div>
	<br>
	<br>
	<div>
		<a href="javascript:back_to()">返回上层目录</a>
		<div style="float: right">
			<a href="javascript:upload()">上传</a> <a href="javascript:makedir()">新建文件夹</a>
		</div>
	</div>
	<br>
	<div id="file_list"></div>

	<form id="upform" enctype="multipart/form-data" method="post" action="fileoperator">
		<input name="file" type="file"></input>
		<input name="operator" type="hidden" value="upload"></input>
		<input name="path" type="hidden"></input>
	</form>
</body>
<script type="text/javascript">
	loadAbsoluteDir('/');
</script>
</html>
