<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>



<script type="text/x-handlebars-template"  id="reply-list-template" >
{{#each .}}
<div class="replyLi" >
	<i class="fas fa-comments bg-yellow"></i>
 	<div class="timeline-item" >
  		<span class="time">
    		<i class="fa fa-clock"></i>{{prettifyDate regdate}}
	 		<a class="btn btn-primary btn-xs" id="modifyReplyBtn" data-rno={{rno}}
	    		data-replyer={{replyer}} data-toggle="modal" data-target="#modifyModal">Modify</a>
  		</span>
	
  		<h3 class="timeline-header"><strong style="display:none;">{{rno}}</strong>{{replyer}}</h3>
  		<div class="timeline-body" id="{{rno}}-replytext">{{replytext}} </div>
	</div>
</div>
{{/each}}	
</script>


<script> 
//reply list
function getPage(pageInfo){	 
	$.getJSON(pageInfo,function(data){	
		printData(data.replyList,$('#repliesDiv'),$('#reply-list-template'),'.replyLi');
		printPaging(data.pageMaker,$('.pagination'));		
	});
}


function searchList_go(page){	
	
	replyPage=page;
	setPageParams(replyPage,10, '','');
	
	var pageParamsKeys = Object.keys(pageParams);
	for(var key of pageParamsKeys){		
		$.cookie("reply_"+key,pageParams[key],{path:"/"});        
	}
	getPage("/board/replies/list.do?bno="+bno+"&page="+replyPage);
	
	return false;
}

function replyRegist_go(){
	var replyer=$('#newReplyWriter').val();
	var replytext=$('#newReplyText').val();
	
	if(!(replyer && replytext)){
		alert("작성자 혹은 내용은 필수입니다.");
		return;
	}

	var data={
			"bno":""+bno+"",
			"replyer":replyer,
			"replytext":replytext	
	}
	
	$.ajax({
		url:"/board/replies/regist.do",
		type:"post",
		data:JSON.stringify(data),	
		success:function(data){
			var result=data.split(',');
			if(result[0]=="SUCCESS"){
				alert('댓글이 등록되었습니다.');
				replyPage=result[1]; //페이지이동
				$.cookie("reply_page",result[1],{path:"/"}); //상태유지
				getPage("/board/replies/list.do?bno="+bno+"&page="+result[1]); //리스트 출력
				$('#newReplyWriter').val("");
				$('#newReplyText').val("");
			}else{
				alert('댓글이 등록을 실패했습니다.');	
			}
		}
	});
	
}

window.onload=function(){
	
	//댓글 리스트
	var replyPage=$.cookie('reply_page') ? $.cookie('reply_page'):1;

	searchList_go(replyPage);

	
	//reply modify 권한체크
	$('div.timeline').on('click','#modifyReplyBtn',function(event){	
		//alert("modify reply btn click");
		var rno = $(this).attr("data-rno");
		var replyer = $(this).attr("data-replyer");
		var replytext = $('#'+rno+'-replytext').text();
		
		$('#modifyModal input#replytext').val(replytext);
		$('#modifyModal input#rno').val(rno);
		$('#modifyModal input#replyer').val(replyer);
	})
	
	
	$('#replyModBtn').on('click',function(event){
		var form = $('#modifyModal form[role="frm"]');
		//alert(form.serialize());
		
		$.ajax({
			url:"/board/replies/modify.do",
			type:"post",
			data:form.serialize(),
			success:function(result){
				alert("수정되었습니다.");			
				getPage("/board/replies/list.do?bno="+bno+"&page="+replyPage);
			},
			error:function(error){
				alert('수정 실패했습니다.');		
			},
			complete:function(){
				$('#modifyModal').modal('hide');
			}
		});
	});
	
	
	
	$('#replyDelBtn').on('click',function(event){
		//alert("reply delete btn click");
		var rno = $('#modifyModal input#rno').val();
		//alert(rno);
		
		$.ajax({
			url:"/board/replies/remove.do",
			type:"post",
			data:JSON.stringify({rno:rno,bno:bno}),
			success:function(page){
				alert("삭제되었습니다.");
				$.cookie('reply_page',page,{path:"/"});
				getPage("/board/replies/list.do?bno="+bno+"&page="+page);			
			},
			error:function(error){
				alert('삭제 실패했습니다.');		
			},
			complete:function(){
				$('#modifyModal').modal('hide');
			}
		});
	});
}
</script>