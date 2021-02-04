<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%

	//browser 입력 : request
	int k = Integer.parseInt(request.getParameter("k"));
	int y = Integer.parseInt(request.getParameter("y"));
	
	// 처리
	int sum = k + y;
	
	//browser 출력 : response
	//response.setContentType("text/html;charset=utf-8");
	//PrintWriter out = response.getWriter();
		
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


%>
