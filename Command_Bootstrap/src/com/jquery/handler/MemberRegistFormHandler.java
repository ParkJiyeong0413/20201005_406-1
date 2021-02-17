 package com.jquery.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberRegistFormHandler implements CommandHandler {

	@Override
	public boolean isRedirect(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="/WEB-INF/views/member/regist.jsp";
		return url;
	}

}
