<%@ page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<%!
	int sum;	

	private int sum(int a, int b){
		this.sum=a+b;
		return sum;
	}
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
		
		<style>
			body{background:${param.color}}
		</style>
	</head>
	<body>
		<h3>declaration : sum(3,4) = <%=sum(3,4) %></h3>
		<hr/>
		<h3>parameter : <%=request.getParameter("k") %> 
						+ <%=request.getParameter("y") %> 
						= <%=request.getAttribute("sum") %></h3>
	</body>
</html>









