<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:set var="boardList" value="${dataMap.boardList }" />
<c:set var="pageMaker" value="${dataMap.pageMaker }" />


 <style>

table th,td{
	text-align:center;		
}
	
</style>

<body class="hold-transition sidebar-mini">



  <!-- Content Wrapper. Contains page content -->
  <div style="width:100%;">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0 text-dark">Free Board</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">목 록</a></li>
              <li class="breadcrumb-item active">Free Board</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <div class="content">
     		<div class="card">
			<div class="card-header with-border">
				<button type="button" class="btn btn-primary" id="registBtn" onclick="OpenWindow('registForm.do','글등록',800,700);">글등록</button>				
				<div id="keyword" class="card-tools" style="width:550px;">
					<div class="input-group row">	
						 <!-- sort num -->
					  	<select class="form-control col-md-3" name="perPageNum" id="perPageNum">
					  		<option value="10" ${pageMaker.cri.perPageNum==10 ? 'selected':'' } >정렬개수</option>
					  		<option value="20" ${pageMaker.cri.perPageNum==20 ? 'selected':'' }>20개씩</option>
					  		<option value="30" ${pageMaker.cri.perPageNum==30 ? 'selected':'' }>30개씩</option>
					  		<option value="50" ${pageMaker.cri.perPageNum==50 ? 'selected':'' }>50개씩</option>
					  		
					  	</select>					
						<select class="form-control col-md-3" name="searchType" id="searchType">
							<option value="tcw" ${pageMaker.cri.searchType eq 'tcw' ? 'selected':'' }>전 체</option>
							<option value="t" ${pageMaker.cri.searchType eq 't' ? 'selected':'' }>제 목</option>
							<option value="w" ${pageMaker.cri.searchType eq 'w' ? 'selected':'' }>작성자</option>
							<option value="c" ${pageMaker.cri.searchType eq 'c' ? 'selected':'' }>내 용</option>
							<option value="tc" ${pageMaker.cri.searchType eq 'tc' ? 'selected':'' }>제목+내용</option>
							<option value="cw" ${pageMaker.cri.searchType eq 'cw' ? 'selected':'' }>작성자+내용</option>																			
						</select>					
						<input  class="form-control col-md-5" type="text" name="keyword" placeholder="검색어를 입력하세요." value=""/>
						<span class="input-group-append col-me-1">
							<button class="btn btn-primary" type="button" onclick="searchList_go(1);" 
							data-card-widget="search">
								<i class="fa fa-fw fa-search"></i>
							</button>
						</span>
					</div>
				</div>						
			</div>
			<div class="card-body">
				<table class="table table-bordered text-center boardList" >					
					<tr style="font-size:0.95em;">
						<th style="width:10%;">번 호</th>
						<th style="width:50%;">제 목</th>
						<th style="width:15%;">작성자</th>
						<th>등록일</th>
						<th style="width:10%;">조회수</th>
					</tr>				
					<c:if test="${empty boardList }" >
						<tr>
							<td colspan="5">
								<strong>해당 내용이 없습니다.</strong>
							</td>
						</tr>
					</c:if>
					<c:forEach items="${boardList }" var="board">
						<tr style='font-size:0.85em;'>
							<td>${board.bno }</td>
							<td id="boardTitle" style="text-align:left;max-width: 100px; overflow: hidden;
										 white-space: nowrap; text-overflow: ellipsis;">
							<a href="javascript:OpenWindow('detail.do?bno=${board.bno }','상세보기',800,700);">
								<span class="col-sm-12 ">${board.title }
									<c:if test="${board.replycnt ne 0 }">		
										<span class="nav-item">															
										&nbsp;&nbsp;<i class="fa fa-comment"></i>
										<span class="badge badge-warning navbar-badge">${board.replycnt}</span>
										</span>
										
									</c:if>
								</span>
							</a>
							</td>
							<td>${board.writer }</td>
							<td>
								<fmt:formatDate value="${board.regDate }" pattern="yyyy-MM-dd"/>
							</td>
							<td><span class="badge bg-red">${board.viewcnt }</span></td>
						</tr>
					</c:forEach>
								
				</table>				
			</div>
			<div class="card-footer">
				<%@ include file="/WEB-INF/views/common/pagination.jsp" %>	
			</div>
		</div>
		
    </div>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
</body>









