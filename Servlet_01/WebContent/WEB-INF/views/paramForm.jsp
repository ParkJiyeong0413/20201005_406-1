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
	<h3><%=session.getAttribute("loginUser") %>님 환영합니다.</h3>
	<h1>parameter test</h1>
	<form action="<%=request.getContextPath() %>/main" method="post">
		k parameter : <input type="text" name="k" /><br/>
		y parameter : <input type="text" name="y" /><br/>
		color parameter : <input type="text" name="color" /><br/>
		<input type="submit" value="전송하기" />
	</form>
	
	<script>
		if(<%=request.getParameter("state")!=null && request.getParameter("state").equals("1")%>){
			alert("로그인 성공입니다.");
		}
	</script>
</body>
</html>