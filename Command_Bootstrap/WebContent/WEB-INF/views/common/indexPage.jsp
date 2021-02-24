<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<body>
  <div class="content-wrapper">
     	<iframe name="ifr" src="/home.html" frameborder="0" style="width:100%;height:80vh;"></iframe>
  </div>
  
<!-- main  -->
<script src="/resources/js/main/main.js"></script>

<script type="text/x-handlebars-template"  id="subMenu-list-template" >
{{#each .}}
	<li class="nav-item subMenu" >
      	<a href="javascript:goPage('{{murl}}','{{mcode}}');initPageParam();" class="nav-link">
          <i class="{{micon}}"></i>
             <p>{{mname}}</p>
        </a>
	</li>
{{/each}}
</script>

<script>
goPage('${menu.murl}','${menu.mcode}');
subMenu('${menu.mcode}'.substring(0,3)+"0000");
</script>

</body>





