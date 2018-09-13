<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 
<%@include file="include.jsp" %>
   
	<style>
	   body{font-family:"微软雅黑"}
	   /*top背景色*/
	   .navbackground{background-color:rgb(249, 249, 249);}
	   /*隐藏二级菜单项*/
	   .nav_hidden{display:none;padding-left:30px;}
	</style>
	<script type="text/javascript">
	   //光标放到导航上触发事件
	   function toggleNav(obj){
	       var ulNav=obj.parentNode.children[1];
			   if(ulNav){
				 if(ulNav.style.display=="none"){
				 ulNav.style.display="block";
			  }else{
				 ulNav.style.display="none";
			  }
		   }
	   }
	    
		function pageOver(obj){
		  obj.parentNode.className="active";
		}
		
		function pageOut(obj){
		  obj.parentNode.className="disable";
		}
		
		function showPage(obj){
		 //修改选项卡标题
		 document.getElementById("tabTitle").innerHTML=obj.innerHTML
		 //修改iframe的url
		  var url= obj.getAttribute("url");
		  document.getElementById("mainFrame").src=url;
		}
	   
	    
	</script>

</head>
<body>
       <!--顶部菜单项-->
      <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid navbackground">
        <div class="navbar-header">
		  <image src="<%=basePath %>image/logo3.png" style="float:left;"></image>
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>

        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right" style="padding-right:50px;font-size:14px;">
            <li><a href="javascript:void(0)" style="padding-left:0px;padding-top:2px;padding-bottom:2px;padding-right:0px;">客服中心</a></li>
            <li><a href="javascript:void(0)"  style="padding-left:0px;padding-top:2px;padding-bottom:2px;">|客服热线： 400-020-8000（非工作时间：15011089832）</a></li>  
            <li style="clear:both;paddin-top:0px;">
			<span style="padding:0px;" href="javascript:void(0)">
			xx已登录
			</span>
			</li>
		  </ul>
		   
          
        </div>
      </div>
    </nav>
     <!--左侧导航-->
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar" >
            <li class="active">
				<a href="javascript:void(0)" style="font-weight:bold;">系统菜单 
				</a>
			</li>
            <li style="padding-left:20px;">
			 <ul class="nav nav-sidebar">
				<li>
				   <a href="javascript:void(0)" onclick="toggleNav(this)">组织机构管理</a>
				   <ul class="nav nav-sidebar nav_hidden" >
						 
						<li><a href="javascript:void(0)" onclick="showPage(this)" url="<%=basePath %>orgin/organControl?action=organPage&pageNow=1">组织机构查询</a></li>
						<li><a href="javascript:void(0)" onclick="showPage(this)" url="views/orgin/orginPosition.jsp">岗位信息</a></li>
						<li><a href="javascript:void(0)">部门信息</a></li>
						 
					</ul>
				</li>
				<li>
				 <a href="javascript:void(0)" onclick="toggleNav(this)">用户权限管理</a>
				  <ul class="nav nav-sidebar nav_hidden">
				    <li><a href="javascript:void(0)">用户信息</a></li>
				    <li><a href="javascript:void(0)">角色配置</a></li>
					<li><a href="javascript:void(0)">角色资源管理</a></li>
				  </ul>
				</li>
				<li>
				  <a href="javascript:void(0)" onclick="toggleNav(this)">资源管理</a>
				 <ul class="nav nav-sidebar nav_hidden">
				    <li><a href="javascript:void(0)">菜单资源管理</a></li>
					<li><a href="javascript:void(0)" onclick="showPage(this)" url="<%=basePath%>sources/ptMenuControl?action=loadPtMenuPage">菜单信息设置</a></li>
				  </ul>
				</li>
				 
             </ul>
			</li>
           
          </ul>
         
          
        </div>
		
		 <!--right主体部分-->
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		      <!--tab-->
			<ul class="nav nav-pills nav-justified" style="border-bottom:solid 3px rgb(66, 139, 202);">
			    <li  class="active" role="presentation" style="width:120px;">
				  <a href="javascript:void(0)" id="tabTitle" >首页</a>
				</li>
			</ul>
            <iframe  id="mainFrame" frameborder=0  style="width:100%;height:550px;" src="<%=basePath %>welcome.jsp">
				 
	        </iframe>
        </div>
      </div>
    </div>
</body>
</html>