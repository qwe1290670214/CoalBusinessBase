<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

  <%@include file="../../include.jsp" %>
   <script type="text/javascript" src="<%=basePath %>js/My97DatePicker/WdatePicker.js"></script>
<style>
	   body{font-family:"微软雅黑";padding-top:10px;margin:0px;}
	</style>
	<script>
	  
	    
		function pageOver(obj){
		  obj.parentNode.className="active";
		}
		
		function pageOut(obj){
		  obj.parentNode.className="disable";
		}
		
		//首页
		function mainPage(){
			var path=document.getElementById("path").value;
			//将form的action赋值
			document.getElementById("searchform").setAttribute("action",path+"orgin/organControl?action=organPage&pageNow=1");
			document.getElementById("searchform").submit();//提交表单
		}
		
		//上一页
		function lastPage(pageNow){
			var path=document.getElementById("path").value;
			if(pageNow=="1"){
				 
				//将form的action赋值
				document.getElementById("searchform").setAttribute("action",path+"orgin/organControl?action=organPage&pageNow=1");
				document.getElementById("searchform").submit();//提交表单
			}else{
				//将form的action赋值
				document.getElementById("searchform").setAttribute("action",path+"orgin/organControl?action=organPage&pageNow="+ (pageNow-1));
				document.getElementById("searchform").submit();//提交表单
				
			}	
		}
		
		//下一页
		function nextPage(pageNow,totalPage){
			var path=document.getElementById("path").value;
			if(pageNow==totalPage){
				//将form的action赋值
				document.getElementById("searchform").setAttribute("action",path+"orgin/organControl?action=organPage&pageNow="+ totalPage);
				document.getElementById("searchform").submit();//提交表单
			}else{
				//将form的action赋值
				document.getElementById("searchform").setAttribute("action",path+"orgin/organControl?action=organPage&pageNow="+(parseInt(pageNow)+1));
				document.getElementById("searchform").submit();//提交表单
				 
			}	
		}
		//尾页
		function totalPage(totalPage){
			var path=document.getElementById("path").value; 
			//将form的action赋值
			document.getElementById("searchform").setAttribute("action",path+"orgin/organControl?action=organPage&pageNow="+totalPage);
			document.getElementById("searchform").submit();//提交表单
		  
			 
		}
	   //点击添加
	   function addPtOrgan(){
		   var path=document.getElementById("path").value; 
		   window.location=path+"orgin/organControl?action=loadAaddPtOrganPage";
		   
	   }
	</script>
</head>
<body>
 	<input type="hidden" value="<%=basePath %>" id="path">
			<!--toolsbar-->
			<div class="panel panel-default">
	        <div class="panel-body">

			<form class="form-inline" id="searchform" method="post">
			   
				  <div class="form-group">
					<label for="organName">名称</label>
					<input type="text" class="form-control input-sm" name="organName" value="${organName}" id="organName" placeholder="组织机构名称">
				  </div>
				  <div class="form-group">
					<label for="inuse">启用/禁用</label>
					  <input type="hidden" value="${inuse}" id="inuse_show">
					 <select class="form-control input-sm" name="inuse" id="inuse">
  							   <option value="">--全部--</option>
  							   <option value="1">启用</option>
							   <option value="0"> 禁用</option>
					</select>
					<script type="text/javascript">
					   $("#inuse").val($("#inuse_show").val());
					</script>
				  </div>
				  <div class="form-group">
					<label for="modDate">日期</label>
					<input type="text"  onFocus="WdatePicker()"  value="${modDateMin}" class="Wdate form-control input-sm"  name="modDateMin" id="modDateMin" placeholder="起始日期">-
					<input type="text" onFocus="WdatePicker()" value="${modDateMax}"  class="Wdate form-control input-sm" name="modDateMax"  id="modDateMax" placeholder="截止日期">
				  </div>
					<button type="button" class="btn btn-primary" onclick="mainPage()">查询</button>
					<button type="button" class="btn btn-primary" onclick="addPtOrgan()">添加</button>
		   </form>
		   </div>
		   </div>
            <!--datagrid-->
            <div class="table-responsive" style="overflow-y:hidden">
               <table class="table table-striped" >
                   <tbody>
						<tr>
							<th>序号</th><th>组织编码</th><th>名称</th><th>类型</th><th>上一级名称</th>
							<th>是否启用</th><th>创建时间</th><th>操作</th>
						</tr>
					 <c:forEach items="${rows}"  var="pt" varStatus="a">
						  <tr>
						  	<td>${a.count}</td><td>${pt.organCode}</td><td>${pt.organName}</td>
						  	 <td>${pt.organType}</td><td>${pt.parentOrganName}</td>
						  	 <td>${pt.inUse}</td>
						  	 <td>${pt.modTime}</td> 
						  	  <td>
						  	    <a   class="btn btn-primary" href="<%=basePath %>orgin/organControl?action=deleteOrgan&organId=${pt.organUUID}" >删除</a>
						  	     <a   class="btn btn-primary" href="<%=basePath %>orgin/organControl?action=selectForUpdate&organId=${pt.organUUID}" >修改</a>
						  	  </td>
						  </tr>
 				     </c:forEach>
				   
				   </tbody>
              </table>
			     <!--分页-->
			  <nav  >
				  <ul class="pagination"  >
				    <li class="disabled" ><a  href="javascript:void(0)" onmouseout="pageOut(this)" onmouseover="pageOver(this)" onclick="mainPage()">首页 <span class="sr-only">首页</span></a></li>
					<li class="disabled"><a href="javascript:void(0)"  onmouseout="pageOut(this)" onmouseover="pageOver(this)" aria-label="Previous" onclick="lastPage('${pageVo.pageNow}')"><span aria-hidden="true">上一页&laquo;</span></a></li>
					<li class="disabled"><a href="javascript:void(0)" onmouseout="pageOut(this)" onmouseover="pageOver(this)" aria-label="Previous" onclick="nextPage('${pageVo.pageNow}','${pageVo.pageTotal}')"><span aria-hidden="true">下一页&raquo;</span></a></li>
					<li class="disabled"><a href="javascript:void(0)" onmouseout="pageOut(this)" onmouseover="pageOver(this)" onclick="totalPage('${pageVo.pageTotal}')">尾页 <span class="sr-only" >尾页</span></a></li>
					<li class="disabled"><a href="javascript:void(0)" > &nbsp; &nbsp; &nbsp;当前第${pageVo.pageNow}页 
					    &nbsp; &nbsp; &nbsp; 总页数：${pageVo.pageTotal}页  &nbsp;&nbsp;总记录数：${pageVo.total}</a></li>
					 
				  </ul>
			</nav>
          </div>
        
      </div>
    </div>
  </body>

</html>
