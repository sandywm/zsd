<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html> 
<html>
  <head>
    <title>知识典后台欢迎页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache"> 
	<meta http-equiv="keywords" content="知识典管理系统">
	<meta http-equiv="description" content="知识典管理系统">
	<link href="/Module/downApp/css/reset.css" type="text/css" rel="stylesheet" />
	<link href="/plugins/pace/pace-theme-flash.min.css" rel="stylesheet" type="text/css"/>
	<script src="/plugins/pace/pace.min.js" type="text/javascript"></script>	
    </head>
	<style>
  		.welcomeWrap{
  			width:1100px;
  			height:275px;
  			position:absolute;
  			left:50%;
  			top:50%;
  			margin-left:-550px;
  			margin-top:-138px;
  			display:none;
  		}
  		.welcomeTxt{
  			float:left;
  		}
  		.welcomeWrap img{
  			float:right;
  		}
  		.welcomeWrap.spWelcome img{
  			margin-top:-40px;
  			margin-right:100px;
  		}
  		.welcomeTxt{
  			width:400px;
  			height:275px;
  			margin-left:240px;
  		}
  		.welcomeTxt strong{
  			display:block;
  			font-size:26px;
  			color:#333;
  			margin:80px 0 20px 0;
  		}
  		.welcomeTxt p{
  			color:#009688;
  			font-size:38px;
  			font-weight:bold;
  		}
  	</style>
	<body>
		<div class="welcomeWrap zsdWelcome clearfix ">
		<div class="welcomeTxt">
			<strong>欢迎进入</strong>
			<p>知识典平台管理中心</p>
		</div>
		<img src="../../../images/zsdBg.jpg"/>
	</div>
	<div class="welcomeWrap spWelcome clearfix">
		<div class="welcomeTxt">
			<strong>欢迎进入</strong>
			<p>知识典后台管理中心</p>
		</div>
		<img src="../../../images/spBg.jpg"/>
	</div>
	</body>
	<script src="/plugins/jquery/jquery.min.js"></script>
	<script type="text/javascript">
		var roleName = "${sessionScope.login_user_role_name}";
		if(roleName == '知识点管理员'){
			$('.zsdWelcome').show();
		}else if(roleName == '管理员'){
			$('.spWelcome').show();
		}
	</script>
</html>
