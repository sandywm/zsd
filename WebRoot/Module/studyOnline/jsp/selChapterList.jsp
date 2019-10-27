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
	<link rel="stylesheet" type="text/css" href="/Module/studyOnline/css/welcome.css"/>
	<link rel="stylesheet" type="text/css" href="/plugins/swiper/swiper.min.css"/>
	<link href="/plugins/pace/pace-theme-flash.min.css" rel="stylesheet" type="text/css"/>
	<script src="/plugins/pace/pace.min.js" type="text/javascript"></script>	
	</head>
	<body>
		<%@include file="/Module/leftSideBar/header.html"%>
		<div class="mainConWrap">
			<div class="innerMainCon">
				<%@include file="/Module/leftSideBar/fullWidCon.html"%>				
				<!-- 选择出版社 -->
				<div class="selEdiWrap">
					<p class="currEditTxt">当前教材版本：<span class="noSel">请选择出版社</span></p>
					<div class="ediSel">
						<p class="choiceTxt fl">选择出版社</p>
						<div class="ediName fl">
							<p class="noSel ellip">人民教育出版社</p>
							<i class="triIcon"></i>
							
							<ul id="ediListUl" class="layui-anim layui-anim-upbit">
								<li class="active">
									<a href="javascript:void(0)">人民教育出版社</a>
								</li>
								<li>
									<a href="javascript:void(0)">上海教育出版社</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				
				<!-- 章节列表 -->
				<div class="chaptList">
					
				</div>
				
				
				
			</div>
		</div>
	</body>
	<script src="/plugins/jquery/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="/plugins/swiper/swiper.min.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		var page = {
			init : function(){
				this.bindEvent();
				this.loadSwiper();
			},
			bindEvent : function(){
				$('.navBarBtn').click(function(){
					var isOpen = $(this).attr('isOpen');
					if(isOpen === 'off'){
						$(this).addClass('is-active');
						$(this).attr('isOpen','on');
						$('.leftSideBarList').show();
					}else{
						$(this).removeClass('is-active');
						$(this).attr('isOpen','off');
						$('.leftSideBarList').hide();
					}
				});
			},
			loadSwiper : function(){
				var swiper = new Swiper('.innerSubList', {
					slidesPerView:5,
					spaceBetween: 30,
					pagination: {
						el: '.swiper-pagination',
						clickable: true
					}
				}); 
				
			}
		};
		page.init();
	</script>
</html>
