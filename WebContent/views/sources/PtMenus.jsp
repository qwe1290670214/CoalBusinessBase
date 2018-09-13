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
   <link rel="stylesheet" href="<%=basePath %>js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
   <script type="text/javascript" src="<%=basePath %>js/ztree/js/jquery.ztree.core-3.5.js"></script>
   <script type="text/javascript" src="<%=basePath %>js/ztree/js/jquery.ztree.exhide-3.5.js"></script>
   <style>
	   body{font-family:"微软雅黑";padding-top:10px;margin:0px;}
	   #rMenu {margin:0px;position:absolute; visibility:hidden; top:0; background-color:#ccc;border:solid 1px #ccc;padding:5px;text-align: left;padding: 2px;}
	   #rMenu ul{padding:0px;margin:0px;}
	   #rMenu ul li{
	    color:#fff;
		margin: 1px 0;
		padding:0px;
		cursor: pointer;
		list-style: none outside none;
		background-color: rgb(51, 122, 183);;
		font-size:14px;
		}
		.cls{width:120px; }
	    
	</style>
	<script>
	 
	   //页面加载完毕，加载tree
	   var setting = {
			   check: {
					enable: true
				},

			async: {
				enable: true,
				url:"<%=basePath%>sources/ptMenuControl?action=selectMainMenus"
			},
			callback:{
				//onAsyncSuccess: zTreeOnAsyncSuccess 
				onRightClick: OnRightClick//右键菜单
			}
		};
		  /*  function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
	           //隐藏isHidden属性的节点
	           var zTree = $.fn.zTree.getZTreeObj("menuTree");
			   var hiddenCount = zTree.getNodesByParam("isHidden", true,null);
	           zTree.hideNodes(hiddenCount);
	      }; */
      
	   var zTree, rMenu;

	   $(document).ready(function(){
		 	var tree =$.fn.zTree.init($("#menuTree"), setting);   
		 	  //右键菜单初始化
		 	zTree = $.fn.zTree.getZTreeObj("menuTree");
			rMenu = $("#rMenu");

		});
	      
	      
	   //点击查询
	   function  searchTree(){
		   var setting = {
				   check: {
						enable: true
					},

					async: {
						enable: true,
						url:"<%=basePath%>sources/ptMenuControl?action=selectMainMenus", 
						otherParam: ["menuGroupID", $("#menuGroupID").val(), "menuType", $("#menuType").val()]
 					},
 					callback: {
 						onRightClick: OnRightClick
 					}

					 
			};
		   var tree =$.fn.zTree.init($("#menuTree"), setting);
		   //右键菜单初始化
		   zTree = $.fn.zTree.getZTreeObj("menuTree");
		   rMenu = $("#rMenu");

	   }
	   
	   //右键菜单
	   function OnRightClick(event, treeId, treeNode) {
		    // 判断是否有节点被选中如果没有，则不显示菜单
		    var treeObj = $.fn.zTree.getZTreeObj("menuTree");
            var nodes = treeObj.getSelectedNodes();
           if(nodes&&nodes.length>0){
        	   if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
   				zTree.cancelSelectedNode();
   				showRMenu("root", event.clientX, event.clientY);
   			   } else if (treeNode && !treeNode.noR) {
   				zTree.selectNode(treeNode);
   				showRMenu("node", event.clientX, event.clientY);
   			 }
           }else{
        	   alert("没选中节点");
           }
		    
		   
			
		}

		function showRMenu(type, x, y) {
			$("#rMenu ul").show();
			if (type=="root") {
				$("#m_del").hide();
				$("#m_check").hide();
				$("#m_unCheck").hide();
			} else {
				$("#m_del").show();
				$("#m_check").show();
				$("#m_unCheck").show();
			}

            y += document.body.scrollTop;
            x += document.body.scrollLeft;
            rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

			$("body").bind("mousedown", onBodyMouseDown);
		}
		function hideRMenu() {
			if (rMenu) rMenu.css({"visibility": "hidden"});
			$("body").unbind("mousedown", onBodyMouseDown);
		}
		function onBodyMouseDown(event){
			if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
				rMenu.css({"visibility" : "hidden"});
			}
		}
		
		
		//点击添加
		function addTreeNode(){
			 var treeObj = $.fn.zTree.getZTreeObj("menuTree");
	            var nodes = treeObj.getSelectedNodes();
	            alert(nodes[0].id+"---"+nodes[0].leve);
	            $("#parentNodeId").val(nodes[0].id);
	            $("#myModal").modal("show");
			hideRMenu();
			
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
					<label for="menuGroup">菜单组</label>
					 <select class="form-control input-sm" name="menuGroupID" id="menuGroupID">
  							   <option value="">--全部--</option>
  							   <option value="1231">电厂侧</option>
  							   <option value="1232">平台侧</option>
  							   <option value="1233">供应商侧</option>
  							   <option value="1234">子公司分公司侧</option>
  							  
					</select>
				  </div>
				  <div class="form-group">
					<label for="menuType">菜单类型</label>
					  
					 <select class="form-control input-sm" name="menuType" id="menuType">
  							   <option value="">--全部--</option>
  							    <option value="0">--业务--</option>
  							     <option value="1">--系统--</option>
  							      <option value="2">--实施--</option>
  							       <option value="4">--继承--</option>
  							        <option value="5">--Demo--</option>
  							        
  							  
					</select>
					 
				  </div>
				  
					<button type="button" class="btn btn-primary"  onclick="searchTree()" >查询</button>
				</form> 
		   </div>
		   </div>
            <!--datagrid-->
            <div class="table-responsive" style="overflow-y:hidden;width:180px;" >
                <ul id="menuTree" class="ztree" ></ul>
            </div>
            
            <!-- rMenu 右侧菜单-->
            <div id="rMenu">
				<ul>
					<li id="m_add" onclick="addTreeNode()">增加节点</li>
					<!-- <li id="m_del" onclick="removeTreeNode();">删除节点</li>
					<li id="m_check" onclick="checkTreeNode(true);">Check节点</li>
					<li id="m_unCheck" onclick="checkTreeNode(false);">unCheck节点</li>
					<li id="m_reset" onclick="resetTree();">恢复zTree</li> -->
				</ul>
			</div>
			
			<!-- dialog -->
			<!-- 模态框（Modal） -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content" style="width:600px;height:400px;">
						<div class="modal-header">
							 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                      &times;
                              </button>
	                         <!-- 标题 -->
						     <h4 class="modal-title" id="myModalLabel">
	              	                         添加子菜单项
	                        </h4>
						</div>
						<!-- 消息 -->
						<div class="modal-body" style="width:600px;height:240px;">
							<!-- form -->
							 <form class="form-inline" id="organAddForm"    >
								  <div class="form-group">
								    <label for="menuName" class="labelField">菜单名称：</label>
								    <input type="text" class="form-control cls"    name="menuName" id="menuName"placeholder="请输入名称" >
								  </div>
								  <div class="form-group">
								    <label for="menuName" class="labelField">禁用/启用：</label>
								    <select id="flag" name="flag" class="form-control input-sm"> 
								      <option value="1">启用</option>  
								      <option value="0">禁用</option>  
								    </select>
								  </div> 
								  <br>
								  <div class="form-group">
								    <label for="menuName" class="labelField">是否明细：</label>
								    <select class="form-control input-sm" name="isitem" id="isitem"> 
								      <option value="1">启用</option>  
								      <option value="0">禁用</option>  
								    </select>
								  </div>
								   <div class="form-group">
								    <label for="menuName" class="labelField">国际化标示：</label>
								    <select class="form-control input-sm" name="locale" id="locale"> 
								      <option value="zh-CN">zh-CN</option>  
								     
								    </select>
								  </div>
								  <input type="hidden" value="" id="parentNodeId"><!-- 父节点id -->
								  
							 </form>
						</div>
						<!-- 提示按钮 -->
						<div class="modal-footer">
							<input type="button" class="btn btn-primary" id="saveTreeBtn" value="确定">
							<input type="button" class="btn btn-default" data-dismiss="modal" value="取消">     
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal -->
			</div>
			
  </body>

</html>
