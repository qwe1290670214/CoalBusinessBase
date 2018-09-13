 <% 
String path = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">  
    <!-- Bootstrap core CSS -->
    <link href="<%=basePath %>bootstrap/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
	<link href="<%=basePath %>bootstrap/bootstrap-3.3.7-dist/css/bashboard.css" rel="stylesheet">
		<link href="<%=basePath %>bootstrap/bootstrap-validator/dist/css/bootstrapValidator.min.css" rel="stylesheet">
	
	<script type="text/javascript" src="<%=basePath %>bootstrap/bootstrap-3.3.7-dist/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>bootstrap/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="<%=basePath %>bootstrap/bootstrap-validator/dist/js/bootstrapValidator.min.js"></script>
 