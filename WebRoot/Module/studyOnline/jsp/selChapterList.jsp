<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html> 
<html>
  <head>
    <title>知识典在线学习</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache"> 
	<meta http-equiv="keywords" content="知识典在线学习">
	<meta http-equiv="description" content="知识典在线学习选择章节">
	<link rel="stylesheet" type="text/css" href="/css/resetPC.css"/>
	<link href="/plugins/pace/pace-theme-flash.min.css" rel="stylesheet" type="text/css"/>
	<script src="/plugins/pace/pace.min.js" type="text/javascript"></script>	
	</head>
	<body>
		<%@include file="/Module/leftSideBar/header.html"%>
		<div class="mainConWrap">
			<div class="innerMainCon">
				<%@include file="/Module/leftSideBar/fullWidCon.html"%>				
				<!-- 章节列表 -->
				<div class="chaptList">
					
				</div>
				
				
			</div>
		</div>
	</body>
	<script type="text/javascript">
		$('.ediSel').hide();
	</script>
</html>
