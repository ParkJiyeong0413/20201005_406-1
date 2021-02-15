package com.jsp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dto.MemberVO;

@WebServlet("/member/detail.do")
public class MemberDetailServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="/WEB-INF/views/member/detail.jsp";
		
		//처리
		MemberVO member = new MemberVO();
		member.setId("mimi");
		member.setEmail("mimi@naver.com");
		member.setName("mimi");
		member.setPhone("000-1234-1234");
		
		request.setAttribute("member", member);
		
		request.getRequestDispatcher(url).forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
