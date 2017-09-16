package com.lk.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import com.lk.bean.Comment;
import com.lk.bean.CommentInfo;
import com.lk.bean.SocialBean;
import com.lk.service.SocialService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class SocialAction implements ModelDriven<SocialBean>{
	private static final String JSON = "json";
	
	private SocialBean social = null;
	private SocialService socialService = null;
	private HashMap<String, Object> resultMap = null;
	
	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}
	
	public void setSocialService(SocialService socialService) {
		this.socialService = socialService;
	}

	@Override
	public SocialBean getModel() {
		social = new SocialBean();
		social.setUsername((String)ActionContext.getContext().getSession().get("username"));//将用户名放入model bean中，方便后续使用
		return social;
	}
	
	public String praise(){
		resultMap = new HashMap<>();
		boolean res = socialService.addDiscuss(social.getUsername(), social.getShareId());
		if (res == true){
			resultMap.put("result", "success");
		}
		else
			resultMap.put("result", "repeat");
		return JSON;
	}
	
	public String canclePraise(){
		resultMap = new HashMap<>();
		boolean res = socialService.removeDiscuss(social.getUsername(), social.getShareId());
		if (res == true){
			resultMap.put("result", "success");
		}
		else
			resultMap.put("result", "fail");
		return JSON;
	}
	
	public String addComment(){
		boolean res = socialService.addComment(social.getUsername(), social.getShareId(), social.getCommentText());
		resultMap = new HashMap<>();
		if (res == true)
			resultMap.put("result", "success");
		else
			resultMap.put("result", "fail");
		return JSON;
	}
	
	public String getShareComment(){
		Set<Comment> comments = socialService.getShareComments(social.getShareId());
		ArrayList<CommentInfo> list = new ArrayList<>();
		for (Comment comment : comments){
			list.add(new CommentInfo(comment.getUser().getUsername(), comment.getText()));
		}
		resultMap = new HashMap<>();
		resultMap.put("comments", list);
		return JSON;
	}
	
}
