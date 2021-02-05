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
	<h1>네이버검색하기</h1>
	<form action="https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8" method="get" >
		검색어 : <input type="text" name="query" /><input type="submit" value="검색하기"/>
		
	</form>
</body>
</html>