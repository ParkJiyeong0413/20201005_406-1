<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

$('input#inputFile').on('change',function(event){
	//alert('file change');
	
	//업로드 확인변수 초기화
	$('input[name="checkUpload"]').val(0);
	
	var fileFormat=
		this.value.substr(this.value.lastIndexOf(".")+1).toUpperCase();
		
	//이미지 확장자 jpg 확인
	if(!(fileFormat=="JPG" || fileFormat=="JPEG")){
		alert("이미지는 jpg/jpeg 형식만 가능합니다.");
		$(this).val("");		
		return;
	} 
	//이미지 파일 용량 체크
	if(this.files[0].size>1024*1024*1){
		alert("사진 용량은 1MB 이하만 가능합니다.");
		return;
	};
	
	document.getElementById('inputFileName').value=this.files[0].name;
	
	if (this.files && this.files[0]) {
		var reader = new FileReader();
		 reader.onload = function (e) {
			  $('div#pictureView')
	        	.css({'background-image':'url('+e.target.result+')',
					  'background-position':'center',
					  'background-size':'cover',
					  'background-repeat':'no-repeat'
	        		});
		  }
		reader.readAsDataURL(this.files[0]);
		
	}
	
});

function upload_go(){
	//alert("upload btn click");
	
	if($('input[name="pictureFile"]').val()==""){
		alert("사진을 선택하세요.");
		$('input[name="pictureFile"]').click();
		return;
	};	
	
	//form 태그 양식을 객체화	
	var form = new FormData($('form[role="imageForm"]')[0]);
	
	$.ajax({
		url:"<%=request.getContextPath()%>/member/picture.do",
		data:form,
		type:'post',
		processData:false,
		contentType:false,
		success:function(data){
			//업로드 확인변수 세팅
			$('input[name="checkUpload"]').val(1);
			
			//저장된 파일명 저장.
			$('input#oldFile').val(data); // 변경시 삭제될 파일명
			$('form[role="form"]  input[name="picture"]').val(data);
			
			alert("사진이 업로드 되었습니다.");
		},
		error:function(error){
			alert("현재 사진 업로드가 불가합니다.\n 관리자에게 연락바랍니다.");
		}
	});
}
	
