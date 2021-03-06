<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html>
  <head>
    <title>知识典管理系统</title>
    <meta http-equiv="keywords" content="知识点管理系统,首页"/>
	<meta http-equiv="description" content="知识点管理系统"/>
	<link href="/plugins/layui/css/layui.css" rel="stylesheet" type="text/css"/>
	<link href="/css/diyReset.css" rel="stylesheet" type="text/css"/>
	<link href="/css/common.css" rel="stylesheet" type="text/css"/>
	<link href="/css/index.css" rel="stylesheet" type="text/css"/>
	<link href="/css/dottingAnimation.css" rel="stylesheet" type="text/css"/>
	<link href="/plugins/layui/css/modules/layui-icon-extend/iconfont.css" rel="stylesheet" type="text/css"/>
	<link href="/plugins/pace/pace-theme-flash.min.css" rel="stylesheet" type="text/css"/>
	<script src="/plugins/pace/pace.min.js"></script>
	<script type="text/javascript">
		var roleName = "${sessionScope.login_user_role_name}";
		var realName = "${sessionScope.login_real_name}";
		sessionStorage.setItem("roleName", roleName);
	</script>
	<style>
		/*.layui-layer-molv .layui-layer-title{background:#4d47f1 !important;}
		.layui-layer-molv .layui-layer-btn a{background:#4d47f1 !important;border-color:#4d47f1 !important; }
		.layui-layer-molv .layui-layer-btn .layui-layer-btn1{background:none !important;border-color:#ccc !important;}
		.layui-form-radio>i:hover, .layui-form-radioed>i{color:#4d47f1 !important;}*/
	</style>
  </head>
  
  <body class="layui-layout-body">
  	<div class="layui-layout layui-layout-admin">
  		<div class="layui-header">
  			<div class="layui-logo">知识典管理系统 <i id="animation-left-nav" class="layui-icon layui-icon-shrink-right"></i></div>
  			<ul class="layui-nav layui-layout-right">
  				<li class="weather">
  					<div id="tp-weather-widget"></div>
	  					<script>(function(T,h,i,n,k,P,a,g,e){g=function(){P=h.createElement(i);a=h.getElementsByTagName(i)[0];P.src=k;P.charset="utf-8";P.async=1;a.parentNode.insertBefore(P,a)};T["ThinkPageWeatherWidgetObject"]=n;T[n]||(T[n]=function(){(T[n].q=T[n].q||[]).push(arguments)});T[n].l=+new Date();if(T.attachEvent){T.attachEvent("onload",g)}else{T.addEventListener("load",g,false)}}(window,document,"script","tpwidget","//widget.seniverse.com/widget/chameleon.js"))</script>
						<script>tpwidget("init", {
					        "flavor": "slim",
					        "location": "WX4FBXXFKE4F",
					        "geolocation": "enabled",
					        "language": "zh-chs",
					        "unit": "c",
					        "theme": "chameleon",
					        "container": "tp-weather-widget",
					        "bubble": "enabled",
					        "alarmType": "badge",
					        "color": "#F47837",
					        "uid": "UC6AD9E048",
					        "hash": "76465b415261736ddd08da3f7f9b24d0"
					    });
					    tpwidget("show");</script>
  				</li>
	            <li class="layui-nav-item">
	                <a href="javascript:;">
	                	<i class="layui-icon layui-icon-username headsmIcon"></i>
	                   	<span id="userName"></span><span class="layui-nav-more"></span>
	                </a>
	                <!-- dl class="layui-nav-child">
	                    <dd class="navLi"><a href="javascript:void(0)" tab-id="1" path="user.do?action=goUserDetailPage">基本资料</a></dd>
	                    <dd class="navLi"><a href="javascript:void(0)" tab-id="2" path="user.do?action=goUpdatePassPage">密码设置</a></dd>
	                </dl -->
	            </li>
	            <li class="tuichu">
	            	 <a id="loginOut" href="javascript:;" title="退出">
	            	 	<i class="iconfont layui-extend-tuichu"></i>
	            	 </a>
	            </li>
	    	</ul> 
  		</div>
  		<div class="layui-side layui-bg-black layui-side-menu">
  			<div class="layui-side-scroll">
  				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
  				<ul id="leftSideNav" class="layui-nav layui-nav-tree" lay-fliter="leftSideNav"></ul>
  			</div>
  		</div>
  		<!-- iframe -->
  		<div class="content-body">
  			<!-- 内容主体区域 -->
  			<div class="layui-tab layui-tab-brief" lay-filter="mainTab" lay-allowClose="true">
	            <ul class="layui-tab-title">
	                <li class="layui-this"><i class="layui-icon">&#xe68e;</i>首页</li>
	            </ul>
	            <div class="layui-tab-content">
	                <div class="layui-tab-item layui-show">
	               		<iframe id="mainIframe" src="common.do?action=goWeclomePage" frameborder="0" scrolling="yes" width="100%" height="100%"></iframe>
	                </div>
	            </div>
	        </div>
  		</div>
  		<!-- footer -->
  		<!--  div class="layui-footer">
	        © 2018 版权所有 Copyright@2018 Sandy.wm All Rights Reserved.
	    </div-->
  	</div>
  	<script src="/plugins/jquery/jquery.min.js"></script>
  	<script src="/plugins/layui/layui.js"></script>
  	<script type="text/javascript">
	  	layui.use(['element','layer'], function(){
	  		 //动态加载模块
	        var element = layui.element,
	        	layer = layui.layer,
	        	$ = layui.jquery,
	        	i = 0;
	        $('#userName').html(realName);
	        function renderModuleList(){
	        	var liItem = '';
	        	if(roleName == '知识点管理员'){
	        		liItem += '<li class="layui-nav-item navLi"><a href="javascript:void(0)" path="chapter.do?action=goChapterPage" tab-id="6">章节管理</a></li>';
	        		liItem += '<li class="layui-nav-item navLi"><a href="javascript:void(0)" path="lore.do?action=goLoreCatalogPage" tab-id="7">知识点目录管理</a></li>';
	        		liItem += '<li class="layui-nav-item navLi"><a href="javascript:void(0)" path="lore.do?action=goLoreQuePage" tab-id="8">知识点管理</a></li>';
	        		liItem += '<li class="layui-nav-item navLi"><a href="javascript:void(0)" path="loreRelate.do?action=goLoreRelatePage" tab-id="9">关联知识点</a></li>';
	        		liItem += '<li class="layui-nav-item navLi"><a href="javascript:void(0)" path="lore.do?action=goNewEdiLorePage" tab-id="21">生成其他版本知识点</a></li>';
	        		liItem += '<li class="layui-nav-item navLi"><a href="javascript:void(0)" path="lex.do?action=goLexPage" tab-id="10">词库管理</a></li>';
	        		liItem += '<li class="layui-nav-item navLi"><a href="javascript:void(0)" path="buffet.do?action=goBuffetPage" tab-id="11">自助餐管理</a></li>';
	        		liItem += '<li class="layui-nav-item navLi"><a href="javascript:void(0)" path="lqe.do?action=goLqePage" tab-id="12">错题管理</a></li>';
	        		liItem += '<li class="layui-nav-item navLi"><a href="javascript:void(0)" path="hw.do?action=goHwPage" tab-id="13">系统家庭作业管理</a></li>';
	        		liItem += '<li class="layui-nav-item navLi"><a href="javascript:void(0)" path="hw.do?action=goTeaQuePage" tab-id="14">老师家庭作业管理</a></li>';
	        		liItem += '<li class="layui-nav-item navLi"><a href="javascript:void(0)" path="lrLog.do?action=goLRLPage" tab-id="20">知识典关联日志</a></li>';
        		}else if(roleName == '超级管理员'){
	        		liItem += '<li class="layui-nav-item navLi"><a href="javascript:void(0)" path="user.do?action=goUserPage" tab-id="15">用户管理</a></li>';
	        		liItem += '<li class="layui-nav-item navLi"><a href="javascript:void(0)" path="school.do?action=goSchoolPage" tab-id="1">学校管理</a></li>';
	        		liItem += '<li class="layui-nav-item navLi"><a href="javascript:void(0)" path="common.do?action=goEditionPage" tab-id="2">出版社管理</a></li>';
	        		liItem += '<li class="layui-nav-item navLi"><a href="javascript:void(0)" path="common.do?action=goSubjectPage" tab-id="3">学科管理</a></li>';
	        		liItem += '<li class="layui-nav-item navLi"><a href="javascript:void(0)" path="common.do?action=goEducationPage" tab-id="4">教材管理</a></li>';
	        		liItem += '<li class="layui-nav-item navLi"><a href="javascript:void(0)" path="common.do?action=goGSubjectPage" tab-id="5">年级科目管理</a></li>';
	        		liItem += '<li class="layui-nav-item navLi"><a href="javascript:void(0)" path="netTeacherReview.do?action=goNtReviewPage" tab-id="15">网络导师审核管理</a></li>';
        			liItem += '<li class="layui-nav-item navLi"><a href="javascript:void(0)" path="ntsBind.do?action=goNtsPage" tab-id="16">学生导师管理</a></li>';
        		}
	        	$("#leftSideNav").html(liItem);
	        	element.init(); 
	        }
	        function createSetInfoLayer(){
	        	var setInfoTxt = '',fullLayer = '<div class="indexLayer"></div>';
	        	var isComFlag = checkCpyInitInfoSet();
	        	setInfoTxt += '<div class="setInfoTxt">';
	        	setInfoTxt += '<div><p class="firstTxt">请先设置代理机构初始信息</p></div>';
	        	setInfoTxt += '<a class="goSetCpyInfoBtn" path="cpyManager.do?action=goCpyDetailPage" tab-id="3" href="javascript:void(0)">去设置</a></div>';
	      		if(isComFlag == false){
	      			$('body').append(fullLayer + setInfoTxt);
	        		goSetCpyInfo();
	      		}
	        }
	        renderModuleList();
	        $(".navLi").click(function () {
	        	var title = $(this).text();
		        var path = $(this).children('a').attr('path');
		        var tabId = $(this).children('a').attr('tab-id');
		        commonCreateIf(tabId,title,path);
		    });
	        function commonCreateIf(tabId,title,path){
	        	// 去重复选项卡
		        for (var i = 0; i < $('.mainIframe').length; i++) {
		            if ($('.mainIframe').eq(i).attr('tab-id') == tabId) {
		                element.tabChange("mainTab", tabId);
		                event.stopPropagation();
		                return;
		            }
		        }
		        // 添加选项卡
		        element.tabAdd("mainTab", {
		            title: title,
		            content: "<iframe src='" + path + "' tab-id='" + tabId + "' class='mainIframe' frameborder='0' scrolling='yes' width='100%' height='100%'></iframe>",
		            id: tabId
		        });
		        // 切换选项卡
		        element.tabChange("mainTab", tabId);	
	        }
	        $("#animation-left-nav").click(function(){
				//这里定义一个全局变量来方便判断动画收缩的效果,也就是放在最外面
				if(i == 0){
					$(this).removeClass("layui-icon-shrink-right").addClass("layui-icon-spread-left");
					$(".layui-side").stop().animate({width:'toggle'});
					$(".content-body").stop().animate({left:'0px'});			
					//$(".layui-footer").animate({left:'0px'});
					i++;
				}else{
					$(this).removeClass("layui-icon-spread-left").addClass("layui-icon-shrink-right");
					$(".layui-side").stop().animate({width:'toggle'});
					$(".content-body").stop().animate({left:'200px'});
					//$(".layui-footer").animate({left:'200px'});
					i--;
				}		
			});
	        $("#loginOut").on('click',function(){
				layer.confirm('确认退出系统么？', {
				  title:'提示',
				  skin: 'layui-layer-molv',
				  btn: ['确定','取消'] //按钮
				},function(){
					window.location.href = "login.do?action=loginOut";
				});
			});
			//左侧导航栏收缩提示
			$('#animation-left-nav').hover(function(){
				if(i==0){
					layer.tips('收缩左侧导航栏', '#animation-left-nav', {tips:[2,'#FF8000'],time:0});
				}else{
					layer.tips('展开左侧导航栏', '#animation-left-nav', {tips:[2,'#FF8000'],time:0});
				}
				
			},function(){
				layer.closeAll('tips'); 
			});
	    });

  	</script>
  </body>
</html>
