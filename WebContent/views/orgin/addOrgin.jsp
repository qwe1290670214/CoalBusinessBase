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
	   .labelField{width:130px;}
	   .form-group{padding-bottom:15px;padding-left:20px;}
	   .dropul li{float:left; width:460px;}
	   .dropul{width:460px;height:200px;overflow:auto;}
	   .dropDown_title span{display:block;width:150px;float:left;border-bottom:solid 1px #ccc; text-align:center;font-weight:bold;}
	   .dropDown_rows span{display:block;width:150px;float:left;border-bottom:solid 1px #ccc;   }
	</style>
	<script>
	  
	   $(document).ready(function(){
		   $(".dropDown_rows").click(function(){
			   
	    		 var j=$(this).children("span:eq(1)").html();
	    		 $("#dropdownOrgan").html(j);
	    		 $("#hideParentOrgan").val($(this).children("span:first").html());
	    		 
	    	});
		   
		   //校验表单
		    $('#organAddForm').bootstrapValidator({
		    	 
		    	feedbackIcons: {
		            valid: 'glyphicon glyphicon-ok',
		            invalid: 'glyphicon glyphicon-remove',
		            validating: 'glyphicon glyphicon-refresh'
		        }, fields:{
		        	//校验组织机构名称
		        	organName:{
		                validators: {
		                    notEmpty: {
		                         message: '组织机构不能为空'
		                    }
		                }
		            },
		          //类型
		        	organType:{
		                validators: {
		                    notEmpty: {
		                        message: '组织机构类型不能为空'
		                    }
		                }
		            },
		          //简称
		        	organShortName:{
		                validators: {
		                    notEmpty: {
		                        message: '组织机构简称不能为空'
		                    },
		                    regexp: {
		                        regexp: /^[A-Z]{3,4}$/,
		                        message: '简称必须3-4位A-Z大写字符'
		                    }
		                }
		            }
		        	
		        }
		    	
		    });
		   //页面加载完毕后，开始校验
		  //  $('#organAddForm').bootstrapValidator('validate');
		   //点击保存按钮，触发事件
		    $("#saveBtn").click(function(){
				  
				      //获取表单对象
				    var bootstrapValidator = $("#organAddForm").data('bootstrapValidator');
				       
				        //手动触发验证
				        bootstrapValidator.validate();
				        if(bootstrapValidator.isValid()){
				            //表单提交的方法、比如ajax提交
				           // alert("通过");
				        	 //弹出遮罩层
						    $("#myModal").modal("show");
				        	$("#saveArganBtn").click(function(){
				        		 
				        		//提交表单
				        		 document.getElementById("organAddForm").submit();
				        		 //$("#organAddForm").submit();//校验后台js 被重写了
				        		//关闭遮罩层
				        		$("#myModal").modal("hide");
				        	});
				        }else{
				        	//alert("不通过");
				        }
				        
			  });

	   });
	
	</script>
</head>
<body>
 	<input type="hidden" value="<%=basePath %>" id="path">
			<!--toolsbar-->
			<div class="panel panel-default">
			
	        <div class="panel-body container">
               <form class="form-inline" id="organAddForm" action="<%=basePath %>orgin/organControl?action=organAdd" method="post">
				  <div class="form-group">
				    <label for="organName" class="labelField">组织机构名称：</label>
				    <input type="text" class="form-control"    name="organName" id="organName"placeholder="请输入名称" >
				  </div>
				  <div class="form-group">
				    <label for="organShortName" class="labelField">英文简称：</label>
				    <input type="text" class="form-control" name="organShortName" id="organShortName" placeholder="请输入四位英文简称">
				  </div>
				  <br>
				  <div class="form-group">
				    <label for="account" class="labelField">银行账户：</label>
				    <input type="text" class="form-control" name="account" id="account"placeholder="请输入组织机构账户" >
				  </div>
				  <div class="form-group">
				    <label for="organType" class="labelField">组织机构类型：</label>
				    <input type="text" class="form-control" name="organType" id="organType" placeholder="请输入类型">
				  </div>
				  <br>
				  <div class="form-group">
				    <label for="inuse" class="labelField">启用/禁用：</label>
				    <select class="form-control input-sm" name="inuse" id="inuse">
  							   <option value="1">启用</option>
							   <option value="0"> 禁用</option>
					</select>
				  </div>
				   
				 
				   <input type="hidden" id="hideParentOrgan"  name="hideParentOrgan"> <!-- 存向后台传的上级组织机构编号 -->
				  <div class="form-group">
				        <label for="inuse" class="labelField">上级组织机构：</label>
				     <div class="btn-group">
						 	<button class="btn btn-default btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						    <span id="dropdownOrgan">选择类型
						    
						    </span> <span class="caret"></span>
						  </button>
						   <ul class="dropdown-menu dropul" >
						       <li class="dropDown_title"  id="dorpDown_title">
						          <span>编号</span><span>名称</span><span>类型</span>
						       </li> 
						         <c:forEach items="${rows}" var="e">
							       <li class="dropDown_rows"> 
							         <span style="display:none">${e.organUUID}</span> 
							         <span>${e.organCode}</span><span>${e.organName}</span>
							         <span>${e.organType}</span>
							       </li> 
						       </c:forEach>
						       
						  </ul>
					</div>
				  </div>
				  <br>
				  <div class="form-group">
				    <label for="desc" class="labelField">描述：</label>
				    <textarea class="form-control" id="desc" name="desc" rows="3" cols="40"></textarea>
				  </div> 
				  <div style="clear:both;padding-left:260px;padding-top:30px;">
				  <button type="button" class="btn btn-primary"  id="saveBtn" >保存</button><!-- data-toggle="modal" data-target="#myModal" -->
				   <button type="button" class="btn btn-primary"  id="resetBtn" >取消</button><!-- data-toggle="modal" data-target="#myModal" -->
				  </div>
			</form>
			 
		    </div>
		   </div>
             
        
      
     <!-- 提示框 -->
     
     <!-- 模态框（Modal） -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content" style="width:400px;height:200px;">
						<div class="modal-header">
							 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                      &times;
                              </button>
	                         <!-- 标题 -->
						     <h4 class="modal-title" id="myModalLabel">
	              	                         提示信息
	                        </h4>
						</div>
						<!-- 消息 -->
						<div class="modal-body">
							是否确定保存？
						</div>
						<!-- 提示按钮 -->
						<div class="modal-footer">
							<input type="button" class="btn btn-primary" id="saveArganBtn" value="确定">
							<input type="button" class="btn btn-default" data-dismiss="modal" value="取消">     
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal -->
			</div>
  </body>

</html>
