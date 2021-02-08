<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<script>
	alert('아이디 혹은 패스워드가 일치하지 않습니다');
	location.href='<%=request.getContextPath()%>/login';

</script>