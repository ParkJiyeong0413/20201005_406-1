package com.jquery.handler;

import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jquery.dto.MemberVO;
import com.jquery.service.MemberService;
import com.jquery.service.MemberServiceImpl;

public class MemberRegistHandler implements CommandHandler {

	private MemberService memberService=new MemberServiceImpl();
	public void setMemberService(MemberService memberService) {
		this.memberService=memberService;
	}
	
	@Override
	public boolean isRedirect(HttpServletRequest request) {
		return false;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = null;

		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String picture = request.getParameter("picture");
		String authority = request.getParameter("authority");
		String name = request.getParameter("name");

		String phone = "";
		for (String data : request.getParameterValues("phone")) {
			phone += data;
		}

		// MemberVO setting
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setPwd(pwd);
		member.setPhone(phone);
		member.setEmail(email);
		member.setPicture(picture);
		member.setAuthority(authority);
		member.setName(name);
		
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		try {
			memberService.regist(member);
			
			out.println("<script>");
			out.println("window.opener.location.href='"+
					request.getContextPath()+"/member/list.do';");
			out.println("window.close();");
			out.println("</script>");
		}catch (SQLException e){
			e.printStackTrace();
			out.println("<script>");
			out.println("alert('회원등록이 실패했습니다.');");
			out.println("window.close();");
			out.println("</script>");
		}		

		return url;
	}

}






