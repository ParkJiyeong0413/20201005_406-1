<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>


 <nav aria-label="member list Navigation">
 	<ul class="pagination justify-content-center m-0">
 		<li class="page-item">
			<a class="page-link" href="javascript:searchList_go(1);">
			<i class="fas fa-angle-double-left"></i>
			</a>
		</li>
		<li class="page-item">
			<a class="page-link" href="javascript:searchList_go(
			${pageMaker.prev ? pageMaker.startPage-1 : 1}				
			);"><i class="fas fa-angle-left"></i></a>
		</li>
		
		<c:forEach var="pageNum" begin="${pageMaker.startPage }" end="${pageMaker.endPage }" >
			
			<li class="page-item ${pageMaker.cri.page == pageNum?'active':''}">
				<a class="page-link" href="javascript:searchList_go(${pageNum});" >${pageNum }</a>
			</li>
		</c:forEach>			
		
		<li class="page-item">
			<a class="page-link" href="javascript:searchList_go(${pageMaker.next ? pageMaker.endPage+1 : pageMaker.cri.page});">
				<i class="fas fa-angle-right" ></i>
			</a>
		</li>
		<li class="page-item">
			<a class="page-link" href="javascript:searchList_go(${pageMaker.realEndPage} );">
				<i class="fas fa-angle-double-right"></i></a>
		</li>	
 	</ul>
 </nav>

 

<form id="jobForm">
	<input type='hidden' name="page" value="${cri.page}" />
	<input type='hidden' name="perPageNum" value="${cri.perPageNum}"/>
	<input type='hidden' name="searchType" value="${cri.searchType }" />
	<input type='hidden' name="keyword" value="${cri.keyword }" />
</form>

<script>
function searchList_go(page,url){
	
	var jobForm=$('#jobForm');
	jobForm.find("[name='page']").val(page);
	jobForm.find("[name='perPageNum']").val($('select[name="perPageNum"]').val());
	jobForm.find("[name='searchType']").val($('select[name="searchType"]').val());
	jobForm.find("[name='keyword']").val($('div.input-group>input[name="keyword"]').val());
	jobForm.attr("method","post");
	if(url){
		jobForm.attr("action",url)
	}else{
		jobForm.attr("action","list.do")
	}
	
	jobForm.submit();
}

</script> 