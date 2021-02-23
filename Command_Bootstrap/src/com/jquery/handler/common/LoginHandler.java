package com.jquery.handler.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jquery.exception.InvalidPasswordException;
import com.jquery.exception.NotFoundIDException;
import com.jquery.handler.CommandHandler;
import com.jquery.service.MemberService;
import com.jquery.service.MemberServiceImpl;

public class LoginHandler implements CommandHandler {
	
	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url="redirect:index.do";
		
		//로그인처리
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");	
		
		HttpSession session = request.getSession();
		
		try {
			memberService.login(id, pwd);
			
			//로그인 성공
			session.setAttribute("loginUser", memberService.getMember(id));
			//session.setMaxInactiveInterval(10);
			
		}catch(NotFoundIDException | InvalidPasswordException e) {
			url="redirect:common/loginForm.do";
			session.setAttribute("msg", e.getMessage());
		}	
		return url;
	}

}





