function loadDir(path) {
	path = $('#current_path').html() + path + '/';
	loadAbsoluteDir(path);
}

function loadAbsoluteDir(path) {
	$('#current_path').html(path);
	url = 'fileoperator?path=' + path + '&operator=list';
	loadJson(url);
}

function makedir() {
	dirname = prompt("请输入文件夹名:", "newDir");
	if (dirname != null) {
		path = $('#current_path').html() + dirname;
		url = 'fileoperator?operator=mkdir&path=' + path;
		$.get(url, function(data) {
			if (data == 'success') {
				alert('新建成功');
				loadAbsoluteDir($('#current_path').html());
			} else
				alert('新建失败，请检查权限或该文件夹是否已存在');
		});
	}
}

function back_to() {
	var current_path = $('#current_path').html();
	if (current_path == '/' || current_path.length <= 1) {
		alert('已经在你的顶层目录了');
		return;
	}

	var i = current_path.length - 2;
	while (i >= 0) {
		if (current_path.charAt(i) == '/')
			break;
		i--;
	}
	var newPath = current_path.substring(0, i + 1);
	loadAbsoluteDir(newPath);
}

function downloadFile(url) {
	url = 'fileoperator?path=' + $('#current_path').html() + url
			+ '&operator=download';
	$("body").append(
			$("<iframe/>").attr("src", url).attr("style", "display:none"));
}

function upload() {
	var form = $('#upform');
	$('input[name=path]').attr('value', $('#current_path').html());
	form.submit();
}

function deleteFile(path) {
	url = $('#current_path').html() + path;
	url = 'fileoperator?operator=delete&path=' + url;
	$.get(url, function(data) {
		if (data == 'success') {
			alert('删除成功');
			loadAbsoluteDir($('#current_path').html());
		}
	});
}

function loadJson(url) {
	$.getJSON(url, parseJson);
}

function rename(path) {
	var newName = prompt("请输入:", path);
	var filepath = $('#current_path').html() + path;
	var url = "fileoperator?operator=rename&path=" + filepath + "&newName="
			+ newName;
	$.get(url, function(data) {
		if (data == 'success') {
			alert('修改成功');
			loadAbsoluteDir($('#current_path').html());
		}
	});
}

function parseJson(msg) {
	var json = eval(msg);
	array = json.files;
	$('#file_list').empty();
	mainDiv = $('#file_list')[0];
	$.each(array, function(i, item) {
		var fileDiv = document.createElement("div");
		var pathShow = document.createElement("a");
		if (item.type == 'file') {
			aHref = 'javascript:downloadFile(\'' + item.path + '\')';
		} else {
			aHref = 'javascript:loadDir(\'' + item.path + '\')';
		}

		pathShow.setAttribute('href', aHref);
		pathShow.setAttribute('style', 'float:left');
		pathShow.innerHTML = item.path;
		fileDiv.appendChild(pathShow);

		var rightDiv = document.createElement("div");
		rightDiv.setAttribute('style', 'float:right');

		var renameText = document.createElement("a");
		renameText.setAttribute('href', 'javascript:rename(\'' + item.path
				+ '\')');
		renameText.innerHTML = '重命名';
		rightDiv.appendChild(renameText);

		rightDiv.innerHTML += '&nbsp&nbsp';
		
		var deleteText = document.createElement("a");
		deleteText.setAttribute('href', 'javascript:deleteFile(\'' + item.path
				+ '\')');
		deleteText.innerHTML = '删除';
		rightDiv.appendChild(deleteText);

		rightDiv.innerHTML += '&nbsp&nbsp';

		var shareText = document.createElement("a");
		shareText.setAttribute('href', 'javascript:shareFile(\'' + item.path
				+ '\')');
		shareText.innerHTML = '分享';
		rightDiv.appendChild(shareText);

		fileDiv.appendChild(rightDiv);

		mainDiv.appendChild(fileDiv);
		br1 = document.createElement("br");
		mainDiv.appendChild(br1);
	})
}

function shareFile(path) {
	var fullpath = $('#current_path').html() + path;
	url = $.get("fileoperator?operator=share&path=" + fullpath, function(data) {
		var json = eval(data);
		if (json.result == 'success'){
			alert('分享成功，分享号为:' + json.shareId);
			window.location.href = 'fileoperator?operator=showShare?shareId=' + json.shareId;
		}else{
			alert('分享失败,请检查文件是否已被删除或该文件已经被分享');
		}
	});
}
