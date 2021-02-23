package com.jquery.handler.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jquery.handler.CommandHandler;

public class LogoutHandler implements CommandHandler {

	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="redirect:";
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		return url;
	}

}
