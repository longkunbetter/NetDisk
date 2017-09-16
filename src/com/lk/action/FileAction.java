package com.lk.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.lk.bean.NetDiskFile;
import com.lk.bean.ShareItem;
import com.lk.bean.UserOperator;
import com.lk.dao.FileDao;
import com.lk.service.ShareService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class FileAction extends ActionSupport implements ModelDriven<UserOperator> {
	private static final String JSON = "json";
	private static final String DOWNLOAD = "download";
	private static final String SHOW_SHARE = "show_share";

	private static final long serialVersionUID = 1L;

	private FileDao fileDao = null;
	private ShareService shareService = null;

	private Map<String, Object> resultMap = null;
	private UserOperator userOperator = null;
	private String user = null;
	private String tmpFilePath = null;
	private String filename = null;
	private ShareItem shareItem = null;
	
	public ShareItem getShareItem() {
		return shareItem;
	}

	public void setShareService(ShareService shareService) {
		this.shareService = shareService;
	}

	public void setFileDao(FileDao fileDao){
		this.fileDao = fileDao;
	}
	
	public String getFilename(){
		return this.filename;
	}
	
	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public UserOperator getUserOperator() {
		return this.userOperator;
	}

	public InputStream getDownloadFile() {
		FileInputStream fis = null;
		try {
			File file = new File(this.tmpFilePath);
			System.out.println(this.tmpFilePath + "x");
			fis = new FileInputStream(this.tmpFilePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return fis;
	}

	@Override
	public UserOperator getModel() {
		this.userOperator = new UserOperator();
		user = (String) ActionContext.getContext().getSession().get("username");
		return this.userOperator;
	}

	public String fileop() {
		switch (this.userOperator.getOperator()) {
		case "list":
			return this.showDir();
		case "download":
			return this.download();
		case "upload":
			return this.upload();
		case "rename":
			return this.rename();
		case "delete":
			return this.delete();
		case "mkdir":
			return this.makeDir();
		case "share":
			return this.shareFile();
		case "download_share":
			return this.downloadShareFile();
		case "get_share":
			return this.getShareFile();
		default:
			return null;
		}
	}
	
	public String rename(){
		this.fileDao.fileRename(this.user, this.userOperator.getPath(), this.userOperator.getNewName());
		PrintWriter out = this.getResopnseWriter();
		out.print("success");
		return SUCCESS;
	}
	
	public String delete(){
		this.fileDao.fileDelete(this.user, this.userOperator.getPath());
		PrintWriter out = this.getResopnseWriter();
		out.print("success");
		return SUCCESS;
	}
	
	public String makeDir(){
		this.fileDao.makeDir(this.user, this.userOperator.getPath());
		PrintWriter out = this.getResopnseWriter();
		out.print("success");
		return SUCCESS;
	}

	public String upload() {
		String uploadPath = this.userOperator.getFile().getAbsolutePath();
		this.fileDao.fileUpload(this.user, uploadPath, this.userOperator.getPath(), this.userOperator.getFileFileName());
		PrintWriter out = this.getResopnseWriter();
		out.print("success");
		return SUCCESS;
	}

	public String download() {
		this.tmpFilePath = this.fileDao.fileDownload(this.user, this.userOperator.getPath());
		this.filename = this.tmpFilePath.split("_tmp_")[1];
		return DOWNLOAD;
	}

	public String showDir() {
		String[] strs = fileDao.listPath(this.user, this.userOperator.getPath());
		ArrayList<NetDiskFile> list = new ArrayList<NetDiskFile>();
		for (String s : strs) {
			if (s.length() == 0)
				break;
			
			if (s.charAt(0) == 'D') { // 是目录类型
				list.add(new NetDiskFile(s.substring(4), "dir"));
			} else if (s.charAt(0) == 'e'){// 空的情况
				break;
			}
			else
				list.add(new NetDiskFile(s.substring(5), "file"));//文件的情况
		}
		this.resultMap = new HashMap<>();
		this.resultMap.put("currentDir", this.userOperator.getPath());
		this.resultMap.put("files", list);
		return JSON;
	}
	
	public String shareFile(){
		int res = this.shareService.addShare(this.user, this.userOperator.getPath());
		resultMap = new HashMap<>();
		if (res > -1){
			resultMap.put("result", "success");
			resultMap.put("shareId", res);
		}
		else{
			resultMap.put("result", "fail");
		}
		return JSON;
	}
	
	public String getShareFile(){
		ShareItem shareItem = this.shareService.getShareItem(this.userOperator.getShareId());
		resultMap = new HashMap<>();
		if (shareItem == null)
		{
			resultMap.put("result", "fail");
			return JSON;
		}	
		
		resultMap.put("result", "success");
		resultMap.put("shareUser", shareItem.getUser().getUsername());
		resultMap.put("shareId", shareItem.getId());
		resultMap.put("filename", shareItem.getFilename());
		resultMap.put("comments", shareItem.getComments());
		resultMap.put("discuss", shareItem.getDiscuss());
		ActionContext.getContext().put("result", resultMap);
		return SHOW_SHARE;
	}
	
	public String downloadShareFile(){
		int shareId = this.userOperator.getShareId();
		this.tmpFilePath = this.shareService.downloadShare(shareId);
		this.filename = this.tmpFilePath.split("_tmp_")[1];
		return DOWNLOAD;
	}
	
	private PrintWriter getResopnseWriter(){
		HttpServletResponse response = ServletActionContext.getResponse();  
		response.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        try {
        	out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}  
        return out;
	}

}
