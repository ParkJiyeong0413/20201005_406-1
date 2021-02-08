package com.jquery.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jquery.utils.JsonResolver;

public class GetLoginUserHandler implements CommandHandler {

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		session.setAttribute("loginUser", "mimi"); //임시.......
		
		Object loginUser = session.getAttribute("loginUser");
		if(loginUser!=null) {
			JsonResolver.view(response, loginUser);		
		}else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
		
		return null;
	}

}





