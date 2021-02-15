<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h3>표현식</h3>
	<ul>
		<li>parameter : <%=request.getParameter("weather") %></li>
		<li>attribute : <%=request.getAttribute("weather") %></li>
		<li>null : <%=request.getParameter("temp") %></li>
	</ul>
	
	<h3>Expression Language</h3>
	<ul>
		<li>parameter : ${param.weather}</li>
		<li>attribute : ${sessionScope.weather}</li>
		<li>null : ${param.tem}</li>
	</ul>
</body>
</html>




