<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<title>회원수정</title>

<body>

  <!-- Content Wrapper. Contains page content -->
  <div>
   
  <!-- Main content -->
  <section class="content register-page" >
	<form role="form" class="form-horizontal" action="modify.do" method="post" enctype="multipart/form-data">	
		<div class="register-box" style="min-width:450px;">	
			<div class="register-card-body">	
				<div class="row">					
					<input type="hidden" name="oldPicture" value="${member.picture }" />
					<input type="file" id="inputFile" name="picture" style="display:none" />
					<div class="input-group col-md-12">
						<div class="col-md-12" style="text-align: center;">
							<div class="" id="pictureView" style="border: 1px solid green; height: 200px; width: 140px; margin: 0 auto; margin-bottom:5px;"></div>														
							<div class="input-group input-group-sm">
								<label for="inputFile" class=" btn btn-warning btn-sm btn-flat input-group-addon">사진변경</label>
								<input id="inputFileName" class="form-control" type="text" name="tempPicture" disabled
									value="${member.picture.split('\\$\\$')[1] }"/>
								<input id="picture" class="form-control" type="hidden" name="uploadPicture" />
							</div>						
						</div>												
					</div>
				</div>	
				<div class="form-group row">
					<label for="id" class="col-sm-3 control-label text-center">아이디</label>	
					<div class="col-sm-9">
						<input readonly name="id" type="text" class="form-control" id="id"
							placeholder="13글자 영문자,숫자 조합" value="${member.id }">
					</div>
				</div>
				
				<div class="form-group row">
					<label for="pwd" class="col-sm-3 control-label text-center" >패스워드</label>

					<div class="col-sm-9">
						<input name="pwd" type="password" class="form-control" id="pwd"
							placeholder="20글자 영문자,숫자,특수문자 조합" value="${member.pwd }">
					</div>
				</div>
				<div class="form-group row">
					<label for="pwd" class="col-sm-3 control-label text-center" >이 름</label>

					<div class="col-sm-9">
						<input name="name" type="text" class="form-control" id="name"
							placeholder="20글자 영문자,숫자,특수문자 조합" value="${member.name }">
					</div>
				</div>
						
									
				<div class="form-group row">
					<label for="authority" class="col-sm-3 control-label text-center" >권 한</label>
					<div class="col-sm-9">
						<select name="authority" class="form-control">
							<option ${member.authority eq 'ROLE_USER' ? 'selected' : '' } value="ROLE_USER">사용자</option>
							<option ${member.authority eq 'ROLE_MANAGER' ? 'selected' : '' } value="ROLE_MANAGER">운영자</option>
							<option ${member.authority eq 'ROLE_ADMIN' ? 'selected' : '' } value="ROLE_ADMIN">관리자</option>
						</select>
					</div>
				</div>
				
				<div class="form-group row">
					<label for="email" class="col-sm-3 control-label text-center">이메일</label>

					<div class="col-sm-9">
						<input name="email" type="email" class="form-control" id="email"
							placeholder="example@naver.com" value="${member.email }">
					</div>
				</div>
				<div class="form-group row">
                  <label for="phone" class="col-sm-3 control-label text-center">전화번호</label>
                  <div class="col-sm-9">   
                  	<input name="phone" type="text" class="form-control" id="inputPassword3" value="${member.phone }">	                
                  </div>                  
                </div>  
				
				<div class="card-footer row" style="margin-top: 0; border-top: none;">						
					<button type="button" id="modifyBtn"  onclick="modify_go();"
						class="btn btn-warning col-sm-4 text-center" >수정하기</button>
					<div class="col-sm-4"></div>
					<button type="button" id="cancelBtn" onclick="history.go(-1);"
						class="btn btn-default pull-right col-sm-4 text-center">취 소</button>
				</div>	
			</div>
		</div>
	</form>
  </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
<script src="<%=request.getContextPath() %>/resources/js/member/member.js" ></script>

<script> 
window.onload=function(){
	Preview_picture('${member.picture}');
	<%@ include file="modify_js.jsp" %>
}
</script>
</body>


