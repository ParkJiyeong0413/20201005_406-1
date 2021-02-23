package com.jquery.handler.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jquery.handler.CommandHandler;

public class LoginFormHandler implements CommandHandler {

	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="common/loginForm";
		
		
		return url;
	}

}









