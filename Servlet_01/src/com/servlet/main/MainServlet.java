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
		
		//forword
		request.setAttribute("sum",sum);
		
		
		request.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}






