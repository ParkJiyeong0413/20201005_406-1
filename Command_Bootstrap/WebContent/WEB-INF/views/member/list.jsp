<%@page import="com.jquery.command.SearchCriteria"%>
<%@page import="com.jquery.command.PageMaker"%>
<%@page import="com.jquery.dto.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="cri" value="${pageMaker.cri }" /> 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Font Awesome -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- icheck bootstrap -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/dist/css/adminlte.min.css">
<!-- Google Font: Source Sans Pro -->
<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">

</head>
<body>
	    <!-- Main content -->
    	<section class="content">
    	  <div class="card">    		
    	  	<div class="card-header with-border">
    	  		<button type="button" class="btn btn-primary" 	onclick="OpenWindow('registForm.do','회원등록',800,700);" >회원등록</button>
    	  		<div id="keyword" class="card-tools" style="width:550px;">
				  <div class="input-group row">	
				 	 <!-- sort num -->
				 	 <select class="form-control col-md-3" name="perPageNum" id="perPageNum" onchange="searchList_go(${cri.page });">
				  		<option value="10" >정렬개수</option>
				  		<option value="2" ${cri.perPageNum == 2 ? 'selected':''}>2개씩</option>
				  		<option value="3" ${cri.perPageNum == 3 ? 'selected':''}>3개씩</option>
				  		<option value="5" ${cri.perPageNum == 5 ? 'selected':''}>5개씩</option>
				  	</select>
				  <!-- search bar -->
				 	<select class="form-control col-md-3" name="searchType" id="searchType">
						<option value=""  ${cri.searchType eq '' ? 'selected':''}>검색구분</option>
						<option value="i"  ${cri.searchType eq 'i' ? 'selected':''}>아이디</option>
						<option value="p"  ${cri.searchType eq 'p' ? 'selected':''}>전화번호</option>
						<option value="e"  ${cri.searchType eq 'e' ? 'selected':''}>이메일</option>
					</select>			 										
					<input  class="form-control" type="text" name="keyword" 
					placeholder="검색어를 입력하세요." value="${cri.keyword }"/>
					<span class="input-group-append">
						<button class="btn btn-primary" type="button" 
						id="searchBtn" data-card-widget="search" onclick="searchList_go(1);">
							<i class="fa fa-fw fa-search"></i>
						</button>
					</span>
					<!-- end : search bar -->
				  </div>
				 </div>    	  		
    	  	</div>	  
    		<div class="card-body" style="text-align:center;">
    		  <div class="row">
	             <div class="col-sm-12">	
		    		<table class="table table-bordered">
		    			<tr>
		                	<th>아이디</th>
		                	<th>패스워드</th>
		                	<th>이메일</th>
		                	<th>전화번호</th>
		                	<th>등록날짜</th> <!-- yyyy-MM-dd  -->
		               	</tr>
		              
		              	
		              	<c:forEach items="${memberList }" var="member" >
		              	
		              	<tr onclick="OpenWindow('detail.do?id=${member.id}','','800','900');" style="cursor:pointer;">
		              		<td>${member.id }</td>
		              		<td>${member.pwd }</td>
		              		<td>${member.email }</td>
		              		<td>${member.phone }</td>
		              		<td><fmt:formatDate value="${member.regDate }" pattern="" /></td>
		              	</tr>
		              	
		              	</c:forEach>
		              	
		              	
		    		</table>
    		   </div> <!-- col-sm-12 -->
    		 </div> <!-- row -->
    		</div> <!-- card-body -->
    		<div class="card-footer">
  				<%@ include file="/WEB-INF/views/common/pagination.jsp" %>
    		</div> <!-- card-footer -->
          </div> <!-- card  -->
    	</section>	


<!-- jQuery -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/dist/js/adminlte.min.js"></script>

<script src="<%=request.getContextPath() %>/resources/js/common.js"></script>
 

</body>








