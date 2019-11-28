<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html> 
<html>
  <head>
    <title>知识典在线学习</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache"> 
	<meta http-equiv="keywords" content="知识典在线学习">
	<meta http-equiv="description" content="知识典在线学习">
	<link rel="stylesheet" type="text/css" href="/css/resetPC.css"/>
	<link rel="stylesheet" type="text/css" href="/Module/studyOnline/css/welcome.css"/>
	<link href="/plugins/pace/pace-theme-flash.min.css" rel="stylesheet" type="text/css"/>
	<script src="/plugins/pace/pace.min.js" type="text/javascript"></script>	
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
				<!-- 章节列表 -->
				<div class="chaptList clearfix">
					<div class="leftChaptName"></div>
					<div class="loreList">
						<div class="chaptNameTit">
							<strong id="currChaptName" class="ellip"></strong>
							<div class="staBox">
								<p class="hasGetSta">
									<i class="iconfont layui-extend-zhangjie"></i>
									<span>课后诊断已掌握</span>
								</p>
								<p class="notGetSta">
									<i class="iconfont layui-extend-zhangjie"></i>
									<span>课后诊断未掌握</span>
								</p>
								<p class="noLearnSta">
									<i class="iconfont layui-extend-zhangjie"></i>
									<span>课后诊断未学习</span>
								</p>
							</div>
						</div>
						<!-- loreList -->
						<div class="listLore">
							<ul id="listLoreUl" class="clearfix"></ul>
							<img src="Module/studyOnline/images/chapBg.png"/>
						</div>
					</div>
				</div>
				<!-- map -->
				<div class="studyMap">
					<div class="fourStep">
						<ul class="clearfix">
							<li>
								<img src="Module/studyOnline/images/spjj.png" alt="视频讲解"/>
							</li>
							<li>
								<img src="Module/studyOnline/images/dbzd.png" alt="点拨指导"/>
							</li>
							<li>
								<img src="Module/studyOnline/images/zsqd.png" alt="知识清单"/>
							</li>
							<li class="noMargR">
								<img src="Module/studyOnline/images/jtsf.png" alt="解题示范"/>
							</li>
						</ul>
					</div>
					<!-- mapDet -->
					<div class="mapDet clearfix">
						<div class="taskDet">
							<strong class="taskTit">任务描述及奖励</strong>
							<div class="listTask">
								<!-- div class="noData_task">
									<i class="iconfont layui-extend-zanwujilu"></i>
									<p>暂无记录</p>
								</div -->
								<ul class="listTaskUl">
									<li>
										<span class="taskName ellip">针对性诊断</span>
										<span class="goldenNum">+20</span>
									</li>
								</ul>
							</div>
						</div>
						<!-- 右侧主体内容 -->
						<div class="currMainTask">
							<strong class="currTaskTit ellip">当前任务 <span>1级关联知识点(命题的意义)学习 </span><em>(第13个任务)</em></strong>
							<div class="leftMainTask">
								<img class="topImg" src="Module/studyOnline/images/goldenTxt.jpg" alt="金币奖励"/>
								<strong class="golden">60</strong>
								<img class="botImg" src="Module/studyOnline/images/golden.jpg"/>
							</div>
							<div class="rightMainTask">
								<p class="suyuanTit">溯源路线图</p>
								<p class="levelTxt">共<span class="totalLevel">2</span>级</p>
								<p class="levelTxt"><span class="totalLevel">2</span>个知识点</p>
								<a class="viewTracBtn" href="javascript:void(0)">点击查看&gt;&gt;</a>
								<p class="taskInfo">学习了<span class="loreName">命题</span>这个知识点，掌握的怎么样呢？ 检测一下吧,找出没有掌握的根源！</p>
								<a class="studyBtn" href="javascript:void(0)">开始挑战</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		$('.ediSel').show();
		/*var studyPage = {
			init : function(){
				$('.ediSel').show();
				this.bindEvevnt();
			},
			bindEvent : function(){
				$('.goStudyBtn').on('click',function(){
					
				});

			}
		};
		studyPage.init();*/
		
	</script>
</html>
