package com.servlet.main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 화면 요청
		String url = "/WEB-INF/views/loginForm.jsp";
		
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 처리 요청
			
		//입력처리
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		//로그인 처리
		System.out.println("로그인을 시도합니다.");
		System.out.println("id : "+id);
		System.out.println("pwd : "+pwd);
		
		String message = "<script>";
		
		if(id.equals("mimi") && pwd.equals("mimi")) { //로그인 성공
			message+="alert('로그인 성공입니다.');";
			message+="location.href='"+request.getContextPath()+"/main';";
		}else {
			message+="alert('아이디 혹은 패스워드가 일치하지 않습니다');";
			message+="location.href='"+request.getContextPath()+"/login';";
		}
		 message+="</script>";
		
		//화면전환
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print(message);
	}

}




