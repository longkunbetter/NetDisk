/**
 * 
 */

function download() {
	url = "fileoperator?operator=download_share&shareId="
			+ $('#shareId').text();
	$("body").append(
			$("<iframe/>").attr("src", url).attr("style", "display:none"));
}

function praise() {
	url = "social_praise?shareId=" + $('#shareId').text();
	$.get(url, function(data) {
		btn = $('#praise_btn');
		pcount = $('#pcount');

		if (data.result == 'success') {
			tx = pcount.text();
			tx = parseInt(tx) + parseInt('1');
			pcount.text(tx);
			btn.attr('onclick', 'canclePraise()');
			btn.text('取消赞');
		} else if (data.result == 'repeat') {
			alert('你已经赞过');
			btn.attr('onclick', 'canclePraise()');
			btn.text('取消赞');
		} else {
			alert('你还没有登录');
			window.location.href = "login.jsp";
		}
	})
}

function canclePraise() {
	url = "social_canclePraise?shareId=" + $('#shareId').text();
	$.get(url, function(data) {
		btn = $('#praise_btn');
		pcount = $('#pcount');

		if (data.result == 'success') {
			tx = pcount.text();
			tx = parseInt(tx) - parseInt('1');
			pcount.text(tx);
			btn.attr('onclick', 'praise()');
			btn.text('赞');
		} else {
		}
	});
}

function subComment(){
	$.post('social_addComment', 
			$('#commentForm').serialize(),
			function(data){
				if (data.result == 'success'){
					loadComments();
				}
				else{
					alert('系统繁忙，请重试');
				}
			});
}

function loadComments(){
	url = 'social_getShareComment?shareId=' + $('#shareId').text();
	$.get(url, function(data){
			cl = $('#comments_list');
			cl.empty();
			div = cl[0];
			items = data.comments;
			
			var ul = document.createElement('ul');
			
			$.each(items, function(i, item){
				var li = document.createElement('li');
				var box = document.createElement('div');
				var userDiv = document.createElement('div');
				userDiv.innerHTML = item.username + ':';
				var commentDiv = document.createElement('div');
				commentDiv.innerHTML = item.text;
				
				box.appendChild(userDiv);
				box.appendChild(commentDiv);
				li.appendChild(box);
				ul.appendChild(li);
			});
			
			div.appendChild(ul);
	});
}
	
