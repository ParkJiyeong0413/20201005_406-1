
//팝업창들 뛰우기
//새로운 Window창을 Open할 경우 사용되는 함수 ( arg : 주소 , 창타이틀 , 넓이 , 길이 )
function OpenWindow(UrlStr, WinTitle, WinWidth, WinHeight) {
	winleft = (screen.width - WinWidth) / 2;
	wintop = (screen.height - WinHeight) / 2;
	var win = window.open(UrlStr , WinTitle , "scrollbars=yes,width="+ WinWidth +", " 
							+"height="+ WinHeight +", top="+ wintop +", left=" 
							+ winleft +", resizable=yes, status=yes"  );
win.focus() ; 
}

//팝업창 닫기
function CloseWindow(){
	window.opener.location.reload(true);
	window.close();
}



//page parameter
var pageParams={
	page:1,
	perPageNum:10,
	searchType:'',
	keyword:''	
}
//page paramenter & cookie initialize
function initPageParam(prefix){
	pageParams.page=1;
	pageParams.perPageNum=10;
	pageParams.searchType='';
	pageParams.keyword='';
	
	var paramKeys = Object.keys(pageParams);
	for(var key of paramKeys){	
		if(prefix){	
			$.removeCookie(prefix+key,{path:"/"});			
		}else{
			$.removeCookie(key,{path:"/"});
		}
	}
}


function setPageParams(page,perPageNum,searchType,keyword){	
	if(page) pageParams.page = page;
	if(perPageNum) pageParams.perPageNum = perPageNum;
	if(searchType) pageParams.searchType = searchType;
	if(keyword) pageParams.keyword = keyword;
	
}


// page number 가져오기
function getParameterValueFromUrl(paramName){
	
	var paramJson = {};	
	var queryParam = window.location.href.split("?")[1];
	var params = queryParam.split("&");
	for (var param of params){
		paramJson[param.split("=")[0]]=param.split("=")[1];
	}
	
	return paramName ? paramJson[paramName] : queryParam;
}




// handelbars printElement (args : data Array, appent target, handlebar template, remove class)
function printData(dataArr,target,templateObject,removeClass){
	
	var template=Handlebars.compile(templateObject.html());
	
	var html = template(dataArr);
	
	$(removeClass).remove();
	target.append(html);
}

Handlebars.registerHelper({
	"hasReplycnt":function(replycnt){
		return (replycnt > 0) ? 'visible' : 'none';
	},
	"prettifyDate" : prettifyDate
});


// java.util.Date().getTimes()를 변환.
function prettifyDate(timeValue){
	var dateObj=new Date(timeValue);
	var year=dateObj.getFullYear();
	var month=dateDecialFormat(dateObj.getMonth()+1);
	var date=dateDecialFormat(dateObj.getDate());	
	return year+"-"+month+"-"+date;
}
function dateDecialFormat(number){
	number=""+number;
	return number > 2 ? number : "0"+number;
}	


//pagination 
function printPaging(pageMaker,target){
	
	var str="";
	if(pageMaker.realEndPage>0) str+="<li class='page-item'><a class='page-link' href='javascript:searchList_go("+1+");'> <i class='fas fa-angle-double-left'/> </a></li>";
	if(pageMaker.prev){
		str+="<li class='page-item'><a class='page-link' href='javascript:searchList_go("+(pageMaker.startPage-1)
				+");'> <i class='fas fa-angle-left'/> </a></li>";			
	}
	for(var i=pageMaker.startPage;i<=pageMaker.endPage;i++){
		var strClass = pageMaker.cri.page == i ? 'active' : '';
		str+="<li class='page-item "+strClass+"'><a class='page-link' href='javascript:searchList_go("+i+");'>"+
		i+"</a></li>";
	}
	if(pageMaker.next){
		str+="<li class='page-item' ><a class='page-link' href='javascript:searchList_go("+(pageMaker.endPage+1)
			+");'> <i class='fas fa-angle-right'/> </a></li>";
	}
	if(pageMaker.realEndPage>0) str+="<li class='page-item' ><a class='page-link' href='javascript:searchList_go("+(pageMaker.realEndPage)
	+");'> <i class='fas fa-angle-double-right'/> </a></li>";
	
	target.html(str);
}


//cookie 생성 (쿠키명 , 값, 일수)
function setCookie(cookie_name, value, days) {
	var exdate = new Date();
	if(days) exdate.setDate(exdate.getDate() + days);
	// 설정 일수만큼 현재시간에 만료값으로 지정
	
	var cookie_value = escape(value) + ((days == null) ? '' : '; expires=' + exdate.toUTCString());
	document.cookie = cookie_name + '=' + cookie_value;
}

//cookie 삭제 (쿠키명)
function getCookie(cookie_name) {
	var x, y;
	var val = document.cookie.split(';');
	
	for (var i = 0; i < val.length; i++) {
	x = val[i].substr(0, val[i].indexOf('='));
	y = val[i].substr(val[i].indexOf('=') + 1);
	x = x.replace(/^\s+|\s+$/g, ''); // 앞과 뒤의 공백 제거하기
		if (x == cookie_name) {
		  return unescape(y); // unescape로 디코딩 후 값 리턴
		}
	}
}
function summernote_start(content){
	content.summernote({
		placeholder:'여기에 내용을 적으세요.',
		height:250,
		disableResizeEditor: true,
		callbacks:{
			onImageUpload : function(files, editor, welEditable) {
				//alert("image insert!!");
				//file size check!
				for (var i = files.length - 1; i >= 0; i--) {
					if(files[i].size > 1024*1024*5){
	            		alert("이미지는 5MB 미만입니다.");
	            		return;
					}
				}
				
				//file sending
				for (var i = files.length - 1; i >= 0; i--) {
	            	sendImg(files[i], this,'/common/summernote/uploadImg.do');
				}	
			},
			onMediaDelete : function(target) {
				//alert(target[0].src);
				var answer=confirm("정말 이미지를 삭제하시겠습니다.");
				if(answer){
					deleteImg(target[0].src,'/common/summernote/deleteImg.do');
				}
			}
		}
	});
}

//summernote img upload
function sendImg(file, el,uploadURL) {
	
	var form_data = new FormData();
	form_data.append("file", file); 
		
	$.ajax({
  	data: form_data,
  	type: "POST",
  	url: uploadURL,
  	contentType: false,	    	
  	processData: false,
  	success: function(img_url) {
    		$(el).summernote('editor.insertImage', img_url);
  	}
	});
}

//summernote img delete
function deleteImg(src,deleteURL) {
	
	var splitSrc= src.split("=");
	var fileName = splitSrc[splitSrc.length-1];
  
	//alert(fileName);
	var fileData = {
			fileName:fileName
	}
	
	$.ajax({
		url:deleteURL,
		data : JSON.stringify(fileData),
		type:"post",
		success:function(res){
			console.log(res);
		}
	});
}

