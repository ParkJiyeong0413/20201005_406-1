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
	<h1>parameter test</h1>
	<form action="<%=request.getContextPath() %>/main" method="get">
		k parameter : <input type="text" name="k" /><br/>
		y parameter : <input type="text" name="y" /><br/>
		color parameter : <input type="text" name="color" /><br/>
		<input type="submit" value="전송하기" />
	</form>
</body>
</html>