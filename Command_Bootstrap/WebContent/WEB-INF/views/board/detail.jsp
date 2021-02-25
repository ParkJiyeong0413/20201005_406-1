<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<title>상세보기</title>

<body class="hold-transition sidebar-mini">
  <!-- Content Wrapper. Contains page content -->
  <div style="width:100%;">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0 text-dark">Board Detail</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">상세보기</a></li>
              <li class="breadcrumb-item active">Free Board</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <div class="content">
    	<div class="row">
			<div class="col-md-12">
				<div class="card card-outline card-info">
					<div class="card-header">
						<h3 class="card-title">상세보기</h3>
						<div class="card-tools">
							<button type="button" id="modifyBtn" class="btn btn-warning" onclick="modify_go();">MODIFY</button>						
						    <button type="button" id="removeBtn" class="btn btn-danger" onclick="remove_go();">REMOVE</button>					    
						    <button type="button" id="listBtn" class="btn btn-primary" onclick="CloseWindow();">CLOSE</button>
						</div>
					</div>
					<div class="card-body">
						<div class="form-group col-sm-12">
							<label for="title">제 목</label>
							<input type="text" class="form-control" id="title" value="${board.title }" readonly />							
						</div>
						<div class="row">	
							<div class="form-group col-sm-4" >
								<label for="writer">작성자</label>
								<input type="text" class="form-control" id="writer" value="${board.writer }" readonly />
							</div>		
							
							<div class="form-group col-sm-4" >
								<label for="regDate">작성일</label>
								<input type="text" class="form-control" id="regDate" 
								value="<fmt:formatDate value="${board.regDate }" pattern="yyyy-MM-dd" />" readonly />
							
							</div>
							<div class="form-group col-sm-4" >
								<label for="viewcnt">조회수</label>
								<input type="text" class="form-control" id="viewcnt" value="${board.viewcnt }" readonly />
							</div>
						</div>		
						<div class="form-group col-sm-12">
							<label for="content">내 용</label>
							<div id="content">
								${board.content }
							</div>	
						</div>						
					</div>
					<div class="card-footer" style="padding:0;">
						<div class="card card-warning">
							<div class="card-header" style="padding-top:0;padding-bottom:0;">
								<i class="nav-icon fas fa-book"></i>&nbsp;<span style="font-weight:bold;">Attaches</span>
							</div>
							<div class="card-body">
								<div class="row attachList">
								<c:forEach items="${board.attachList }" var="attach">
									<div class="col-md-4 col-sm-4 col-xs-12"  style="cursor:pointer;"
										onclick="location.href='<%=request.getContextPath()%>
												/attach/getFile.do?bno=${board.bno }&ano=${attach.ano }';">
										<div class="info-box">	
										 	<span class="info-box-icon bg-yellow"><i class="fa fa-copy"></i></span>
											<div class="info-box-content">
												<span class ="info-box-text">
													<fmt:formatDate value="${attach.regDate }" pattern="yyyy-MM-dd" />	
												</span>
												<span class ="info-box-number">${attach.fileName }</span>
											</div>
										</div>
									 </div>		
								</c:forEach>	
								</div> 
							</div>
						</div>
					</div>
												
				</div><!-- end card -->				
			</div><!-- end col-md-12 -->
		</div><!-- end row  -->
		
		<!-- reply component start --> 
		<div class="row">
			<div class="col-md-12">
				<div class="card card-info">					
					<div class="card-body">
						<!-- The time line -->
						<div class="timeline" id="repliesDiv">
							<!-- timeline time label -->
							<div class="time-label" >
								<span class="bg-green">Replies List </span>							
							</div>
							
						</div>
						<div class='text-center'>
							<ul id="pagination" class="pagination justify-content-center m-0">
								
							</ul>
						</div>
					</div>
					<div class="card-footer">
						<input class="form-control" type="hidden" placeholder="USER ID"	value="${loginUser.id }" id="newReplyWriter"> 
						<label for="newReplyText">Reply Text</label>
						<input class="form-control" type="text"	placeholder="REPLY TEXT" id="newReplyText">
						<br/>
						<button type="button" class="btn btn-primary" onclick="replyRegist_go();" id="replyAddBtn">ADD REPLY</button>
					</div>				
				</div>			
				
			</div><!-- end col-md-12 -->
		</div><!-- end row -->
		
		
    </div>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->



<!-- Modal -->
<div id="modifyModal" class="modal modal-default fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" style="display:none;"></h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>        
      </div>
      <div class="modal-body" data-rno>
      	<form role="frm">
        	<input type="hidden" name="rno" id="rno" />
        	<input type="hidden" name="replyer" id="replyer" />
        	<p><input type="text" name="replytext" id="replytext" class="form-control"></p>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-info" id="replyModBtn">Modify</button>
        <button type="button" class="btn btn-danger" id="replyDelBtn">DELETE</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>


<script  type="text/x-handlebars-template"  id="attach-div-template">
{{#each .}}
	<div class="col-md-4 col-sm-4 col-xs-12 attach"  style="cursor:pointer;"
		 onclick="location.href='/board/attach/getFile.do?bno={{bno }}&ano={{ano }}';">
		<div class="info-box">	
		 	<span class="info-box-icon bg-yellow">
				<i class="fa fa-copy"></i>
			</span>
			<div class="info-box-content">
				<span class ="info-box-text">{{prettifyDate regDate }}</span>
				<span class ="info-box-number">{{fileName }}</span>
			</div>
		</div>
	</div>	
{{/each}}										 
</script>

<script>

var bno='${board.bno}';

function modify_go(){
	location.href="/board/modifyForm.do?bno="+bno;	
}
function remove_go(){
	if(confirm("정말 삭제하시겠습니까?")){
		location.href="/board/remove.do?bno="+bno;
	}
}
</script>

<%@ include file="detail_reply.jsp" %>

</body>










