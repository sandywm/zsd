<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html> 
<html>
  <head>
    <title>知识典下载</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache"> 
	<meta http-equiv="keywords" content="知识典App 安卓下载 ios下载">
	<meta http-equiv="description" content="知识典App下载">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<script src="/Module/downApp/css/flexible.js" type="text/javascript"></script>
	<link href="/Module/downApp/css/reset.css" type="text/css" rel="stylesheet" />
	<link href="/Module/downApp/css/downApp.css" rel="stylesheet" type="text/css"/>
	</head>
	<style>
		li{list-style:none;}
	</style>
	<body>
		<div class="downTopPart clearfix">
			<div class="logo">
				<img src="../../images/logo.png" alt="知识典"/>
			</div>
			<div class="topPartCon">
				<strong class="appName">知识典</strong>
				<p class="appSize"><span>大小：</span><i class="appSizeTxt"></i></p>
				<p class="version"><span>版本：</span><i class="versionTxt"></i></p>
				<div class="tagGroup">
					<span>官方</span>
					<span>无广告</span>
					<span>免费</span>
				</div>
				<span class="downBtn">下载</span>
			</div>
		</div>
		<div class="introCon">
			<ul>
				<li> 
					<img class="topImg" src="../../images/bannerDec.png"/>
					<img class="titImg" src="../../images/studyTit.png" alt="在线学习"/>
					<p class="introTxt">解决学生“从哪里学？”“如何学”、“如何 更好地学”以及“学得怎么样”。 从诊断到溯源，从学习到帮辅， 真正实现学习的全方位精准打造。</p>
				</li>
				<!--  style="background:#29c7c6;" -->
				<li>
					<img class="topImg hwImg" src="../../images/hwBannerDec.png"/>
					<img class="titImg" src="../../images/hwTit.png" alt="家庭作业"/>
					<p class="introTxt">学生在知识典平台上学习的过程中，会生成多元的能力、思维痕迹的报告。包括记忆、计算等能力，也包括发散、抽象等思维品质。</p>
				</li>
				<!--  style="background:#e10080;" -->
				<li>
					<img class="topImg zzcImg" src="../../images/zzcBannerDec.png"/>
					<img class="titImg zzcTitImg" src="../../images/zzcTit.png" alt="自助餐"/>
					<p class="introTxt">平台系统建立了强大而精准的引导功能，根据学生学习的情况进行准确而及时的引导，让学生能够学习他们最需要的知识。</p>
				</li>
			</ul>
		</div>
		<div class="introTxtWrap">
			<strong class="introTxtTit">应用简介：</strong>
			<p>“一个中心”：以学生的个性学习为中心；</p>
			<p>“四大系统”：“智能诊断系统”、“溯源学习系统”、 </p>
			<p>“导师导学系统”和“多元评估系统”</p>
			<p>“八大功能”：分别是智能诊断系统下的“针对诊断”</p>
			<p>和“再次诊断”；溯源学习系统下的“追根溯源”和“五步学习”；</p>
			<p>多元评估系统下的“素养报告”和“任务驱动”；</p>
			<p>导师导学系统下的“网络引导”和“导师导学”。</p>
			<p>四大系统四维一体，解决学生“从哪里学？”</p>
			<p>“如何学”、“如何更好地学”以及“学得怎么样”。</p>
			<p>从诊断到溯源，从学习到帮辅，真正实现学习的全方位精准打造。</p>
		</div>
		<div class="layer"></div>
		<div class="tipDiv">
			<p>微信用户戳这里并点击<span>在浏览器中打开</span><br>即可正常下载哦~</p>
		</div>
		<script src="/plugins/jquery/jquery.min.js"></script>
		<script type="text/javascript">
			var page = {
				init : function(){
					this.initDownApp();
					this.getNewAppVersion();
					this.bindEvent();
				},
				bindEvent : function(){
					var _this = this;
					$('.downBtn').on('click',function(){
						_this.checkUserAgent();
					});
				},
				initDownApp : function(){
					var mobileInfo = this.getSelfMobileInfo();
					if(mobileInfo == "andriodWeb"){
						window.location.href = "/Module/appDown/zsd.apk";
					}else if(mobileInfo == "iphoneWeb"){
						alert("暂未发布,请等待1!");
						//window.location.href = "https://itunes.apple.com/cn";
					}else{
						//alert("暂未发布,请等待!");
						window.location.href = "/Module/appDown/zsd.apk";
					}
				},
				getSelfMobileInfo : function(){
					var mobileInfo = "";
					$.ajax({
						  type:"post",
						  async:false,//同步
						  dataType:"json",
						  url:"/baseInfo.do?action=getCilentInfo",
						  success:function (json){ 
							  mobileInfo = json["result"];
						  }
					});
					return mobileInfo;
				},
				isWeiXin : function(){
					 var ua = window.navigator.userAgent.toLowerCase();
				    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
				        return true;
				    }else{
				        return false;
				    }
				},
				checkUserAgent : function(){
					if(this.isWeiXin()){
				    	$(".layer").show();
				    	$(".tipDiv").show();
				    }else{
				    	this.initDownApp();
				    }
				},
				//获取版本号
				getNewAppVersion : function(){
					$.ajax({
					  type:"post",
					  dataType:"json",
					  data : {opt:'new'},
					  url:"/baseInfo.do?action=getNewAppVersion",
					  success:function (json){ 
						  $('.appSizeTxt').html(json.appSize);
						  $('.versionTxt').html(json.version);
					  }
					});
				}
			};
			page.init();
		</script>
	</body>
</html>
