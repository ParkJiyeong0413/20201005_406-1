<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

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
<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<a href="#"><b>관리자 로그인</b></a>
		</div>
		<!-- /.login-logo -->
		<div class="card">
		 <div class="card-body login-card-body">
			<p class="login-box-msg">Sign in to start your session</p>

			<form action="<%=request.getContextPath() %>/common/login.do"	method="post">
				<div class="form-group has-feedback">
					<input type="text" class="form-control" name="id" placeholder="아이디를 입력하세요." value="${pastID }">
					<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" class="form-control" name="pwd" placeholder="패스워드를 입력하세요."  value="mimi">
					<span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="row">
					<div class="col-sm-8">
						<div class="checkbox icheck">
							<label> <input type="checkbox" name="rememberMe" value="check"> Remember Me
							</label>
						</div>
					</div>
					<!-- /.col -->
					<div class="col-sm-4">
						<button type="submit" class="btn btn-primary btn-block btn-flat">로그인</button>
					</div>
					<!-- /.col -->
				</div>
			</form>

			

			<a href="#" style="font-weight:bold;">아이디/패스워드 찾기</a><br> 
			

		</div>
		<!-- /.login-box-body -->
	  </div>	
	</div>
	<!-- /.login-box -->

<!-- jQuery -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/dist/js/adminlte.min.js"></script>

<script>
	var message = "<%=session.getAttribute("msg")==null ? "":session.getAttribute("msg")%>";
	if(message!=""){
		alert(message);
	}
	
	$('input[name="id"]').focus();
</script>
</body>
</html>

<% session.removeAttribute("msg"); %>


