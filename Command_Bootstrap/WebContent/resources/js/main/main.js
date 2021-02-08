
var mCode=getParameterValueFromUrl('mCode');

if(!mCode){
	mCode="M000000";
}

$.ajax({
	url:"/common/mainMenu.do?mCode="+mCode,
	type:"get",
	success:function(data){
		goPage(data.menu.murl,data.menu.mcode);
		subMenu(data.menu.mcode.substring(0,3)+"0000");
		printData(data.menuList,$('.main-menu-list'),$('#mainMenu-list-template'),'.mainMenu');
	},
	error:function(error){
		console.log("internal server error");			
	}
});



function subMenu(mcode){
	if(mcode!="M000000"){
		$.getJSON("/common/subMenu.do?mCode="+mcode,function(data){	
			//console.log(data);		
			printData(data,$('.subMenuList'),$('#subMenu-list-template'),'.subMenu');
		});
	}else{
		$('.subMenuList').html("");		
	}
}

function goPage(url,mCode){
	  // HTML5 지원브라우저에서 사용 가능
  if (typeof(history.pushState) == 'function') {
      //현재 주소를 가져온다.
      var renewURL = location.href;
      //현재 주소 중 .do 뒤 부분이 있다면 날려버린다.
      renewURL = renewURL.substring(0, renewURL.indexOf(".html")+5);
      
      if (mCode != 'M000000') {
          renewURL += "?mCode="+mCode;
      }
      //페이지를 리로드하지 않고 페이지 주소만 변경할 때 사용
      history.pushState(mCode, null, renewURL);
   
  } else {
      location.hash = "#"+mCode;
  }
	  
	$('iframe[name="ifr"]').attr("src",url);
}
