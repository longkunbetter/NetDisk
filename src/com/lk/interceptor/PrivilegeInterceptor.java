package com.lk.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class PrivilegeInterceptor extends MethodFilterInterceptor {
	private static final String REFUSE = "refuse";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		if (session.get("username") == null)
			return REFUSE;
		return invocation.invoke();
	}

}
