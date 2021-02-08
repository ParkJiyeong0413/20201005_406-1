package com.servlet.main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 화면 요청
		String url = "/WEB-INF/views/loginForm.jsp";
		
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 처리 요청
		String url=request.getContextPath()+"/main?state=1";
		
		//입력처리
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		//로그인 처리
		System.out.println("로그인을 시도합니다.");
		System.out.println("id : "+id);
		System.out.println("pwd : "+pwd);
		
		
		if(id.equals("mimi") && pwd.equals("mimi")) { //로그인 성공
			
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", "mimi");
			
		}else {
			url=request.getContextPath()+"/login?error=1";
		}
		
		//화면전환
		
		response.sendRedirect(url);
	}

}




