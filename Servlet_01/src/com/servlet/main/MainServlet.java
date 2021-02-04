package com.servlet.main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("안녕하세요, 이곳은 MainServlet 입니다.");

		// 입력
		
		// console 입력
		//Scanner scan = new Scanner(System.in);
		//int k = Integer.parseInt(scan.nextLine());
		//int y = Integer.parseInt(scan.nextLine());

		//browser 입력 : request
		int k = Integer.parseInt(request.getParameter("k"));
		int y = Integer.parseInt(request.getParameter("y"));
				
		// 처리
		int sum = k + y;

		// 출력
		
		//console 출력
		//System.out.println(k + " + " + y + " = " + sum);
		
		//browser 출력 : response
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>서블릿이해하기</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>");
		out.println(k + " + " + y + " = " + sum);
		out.println("</h1>");
		out.println("</body>");
		out.println("<script>");
		out.println("alert('반갑습니다.');");
		out.println("</script>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}






