package com.lk.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class SocialInterceptor extends MethodFilterInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		String username = (String) invocation.getInvocationContext().getSession().get("username");
		if (username == null){
			return "refuse";
		}
		else
			return invocation.invoke();
	}

}
