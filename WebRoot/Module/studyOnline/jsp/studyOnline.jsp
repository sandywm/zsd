<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html> 
<html>
  <head>
    <title>知识典在线学习</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache"> 
	<meta http-equiv="keywords" content="知识典在线学习">
	<meta http-equiv="description" content="知识典在线学习">
	</head>
	<body>
		<%@include file="/Module/leftSideBar/loading.html"%>
		<%@include file="/Module/leftSideBar/header.html"%>
		<div class="mainConWrap">
			<div class="innerMainCon">
				<%@include file="/Module/leftSideBar/fullWidCon.html"%>
				<!-- 上下册 -->
				<div class="volumeList">
					<ul id="volumeListUl"></ul>
				</div>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		$('.ediSel').show();
	</script>
</html>
