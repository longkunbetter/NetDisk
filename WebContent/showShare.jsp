<%@page import="java.util.*, com.lk.bean.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	HashMap<String, Object> hm = (HashMap) request.getAttribute("resultMap");
	if (hm == null || hm.get("result").equals("fail")) {
		response.sendRedirect("error.html");
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="js/social.js"></script>
<title>用户${resultMap.shareUser}分享的文件</title>
</head>

<body>
	<p id="shareId" hidden="hidden">${resultMap.shareId}</p>
	文件名:${resultMap.filename}
	<br>
	<button onclick="download()">下载</button>
	<br>
	<%
		Set<Discuss> set = (Set<Discuss>) hm.get("discuss");
		if (set != null) {
			Discuss[] discuss = new Discuss[set.size()];
			set.toArray(discuss);
			int count = discuss.length;

			if (0 < count && count < 5) {
				for (int i = 0; i < count - 1; i++) {
					out.print(discuss[i].getUser().getUsername() + "、");
				}

				out.print(discuss[count - 1].getUser().getUsername() + "觉得很赞");
			} else if (count >= 5) {
				for (int i = 0; i < 4; i++) {
					out.print(discuss[i].getUser().getUsername() + "、");
				}
				out.print(discuss[4].getUser().getUsername() + "等<span id='pcount'>" + count + "</span>人觉得很赞");
			}
		}
	%>
	<br>
	<button type="button" onclick="praise()" id="praise_btn">赞一下</button>
	<br> 评论:
	<div id="comments_list"></div>
	<br>
	
	<form action="" method="post" id="commentForm">
		<input type="hidden" value="${resultMap.shareId}" name="shareId"> 
		 添加评论: <input type="text" name="commentText"></input>
		<button type="button" onclick="subComment()">提交</button>
	</form>
</body>

<script type="text/javascript">
	loadComments();
</script>
</html>