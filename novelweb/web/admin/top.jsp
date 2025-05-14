<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<%=path%>/css/top.css" rel="stylesheet" type="text/css">
<title>菜单栏</title>
</head>
<body>

	<header id="header">
	<div id="menu">
		<div id="logo">
			 
			<div id="system_title">潘多码小说网</div>
		</div>
		<div id="rinfo">
			当前用户：
			<span>${sessionScope.adminuser.uname}</span>
			<span id="exit">
				<a href="#" onclick="exit();">退出</a>
			</span>
		</div>
	</div>
	</header>

</body>
 <script type="text/javascript">
    function exit(){
    	window.top.location.href = '<%=path%>/tadmin_loginout.action';
    }
 </script>
</html>