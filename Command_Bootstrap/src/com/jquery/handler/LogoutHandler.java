package com.jquery.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutHandler implements CommandHandler {

	@Override
	public boolean isRedirect(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="/";
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		return url;
	}

}
