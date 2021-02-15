package com.jsp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/weather")
public class WeatherServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url="/WEB-INF/views/weather.jsp";
		
		int weatherNum = Integer.parseInt(request.getParameter("weather"));
		
		
		String[] weatherArr = {"맑음","흐림","눈","비","갬"};
		
		request.setAttribute("weather", weatherArr[weatherNum]);
		
		HttpSession session = request.getSession();
		session.setAttribute("weather", "session:"+weatherArr[weatherNum]);
		
		
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
