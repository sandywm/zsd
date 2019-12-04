<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html> 
<html>
  <head>
    <title>知识典在线学习</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache"> 
	<meta http-equiv="keywords" content="知识典在线学习">
	<meta http-equiv="description" content="知识典在线学习 在线答题">
	<link rel="stylesheet" type="text/css" href="/css/resetPC.css"/>
	<link rel="stylesheet" type="text/css" href="/css/layuiAnim.css"/>
	<link rel="stylesheet" type="text/css" href="/Module/studyOnline/css/questionList.css"/>
	</head>
	<body>
		<%@include file="/Module/leftSideBar/header.html"%>
		<div class="queWrap">
			<!-- loreName -->
			<div class="loreNameWrap">
				<a class="backBtn" href="javascript:void(0)">
					<i class="iconfont layui-extend-fanhui"></i>
					<span>返回</span>
				</a>
				<div class="loreNameTit">
					<h2 class="ellip">1--5各数的认识</h2>
					<p class="ellip">针对行程问题举例而设定的诊断题目诊断共6题,点击题号即可查看该题</p>
				</div>
				<a href="javascript:void(0)" title="我要纠错">
					<i class="iconfont layui-extend-tiwen"></i>
				</a>
			</div>
			<!-- queList -->
			<div class="queListWrap">
				<a class="showCardBtn" href="javascript:void(0)" title="展开答题卡">
					答题卡
				</a>
				<!-- sideQueCard -->
				<div class="sideQueCard">
					<strong class="sideTit">答题卡</strong>
					<div class="cardList">
						<ul>
							<li class="active">
								<span>1</span>
							</li>
							<li class="rightAns"><span>2</span></li>
							<li class="errAns"><span>3</span></li>
							<li>
								<span>4</span>
							</li>
							<li>
								<span>24</span>
							</li>
						</ul>
					</div>
					<a href="javascript:void(0)" class="packUpBtn" title="收起"></a>
				</div>
				
				<!-- queList -->
				<div class="queList">
					<ul>
						<li style="display:block;opacity:1;">
							<!-- queNum&&queType -->
							<div class="queNumType">
								<span class="queNumSpan">1</span>
								<span>单选题</span>
							</div>
							<!-- queSub -->
							<div class="queSub">
								<p>甲方收到了快捷方式灯笼裤飞机上的附件是发射点立刻发生的几率客服都是裤飞机上的附件是发射点立刻发生的几率客服都是金粉世家裤飞机上的附件是发射点立刻发生的几率客服都是金粉世家裤飞机上的附件是发射点立刻发生的几率客服都是金粉世家裤飞机上的附件是发射点立刻发生的几率客服都是金粉世家金粉世家发了多少开发进度是附件是的路口附近的十六客服的是反倒是看来飞机迪斯科浪费</p>
							</div>
							<!-- queOpt -->
							<div class="queOptWrap">
								<div class="queOpt">A、圆锥、三角形、立方体、圆、长方体</div>
								<div class="queOpt">B、圆锥、三角形、立方体、圆、长方圆锥、三角形、立方体、圆、长方体圆锥、三角形、立方体、圆、长方体体</div>
								<div class="queOpt">C、圆锥、三角形、立方体、圆、长方圆锥、三角形、立方体、圆、长方体圆锥、三角形、立方体、圆、长方体体</div>
								<div class="queOpt">D、三角形、立方体、圆、长方体体</div>
							</div>
							
							<!-- queAnsOpt -->
							<div class="queAnsOptWrap">
								<div class="queAnsOpt">
									<span class="queAnsTit">选项：</span>
									<label class="ansOpt active">
										<em class="ansWords">A</em>
										<input type="radio" name="optRad"/>
										<span class="radiusSpan"><i class="iconfont layui-extend-duihao"></i></span>
									</label>
									<label class="ansOpt">
										<em class="ansWords">B</em>
										<input type="radio" name="optRad"/>
										<span class="radiusSpan"></span>
									</label>
								</div>
								
								<div class="queAnsOpt">
									<span class="queAnsTit">选项：</span>
									<label class="ansOpt active">
										<em class="ansWords">A</em>
										<input type="checkbox" name="optCheck"/>
										<span class="squareSpa"><i class="iconfont layui-extend-duihao"></i></span>
									</label>
									<label class="ansOpt">
										<em class="ansWords">B</em>
										<input type="checkbox" name="optCheck"/>
										<span class="squareSpa"><i class="iconfont layui-extend-duihao"></i></span>
									</label>
								</div>
							</div>
							
							<!-- myAns我的解答 -->
							<div class="myAnsWrap">
								<span>我的解答：</span>
								<p class="rightAns">A</p>
								<i class="iconfont layui-extend-duihao rightAnsIcon"></i>
								
								<!-- p class="errAns">B</p>
								<i class="iconfont layui-extend-guanbi errAnsIcon"></i-->
							</div>
							
							<!-- tk&wd que -->
							<div class="tkAndWdQue">
								<div class="tkAndWdInfo">
									<i class="iconfont layui-extend-gonggao"></i>
									<p>请拿出纸和笔验算一下，这道题主要考察解题规范和解题步，要认证验算！得出结果后点击提交即可。</p>
								</div>
								<div class="tkAndWdOpt">
									<span class="queAnsTit">我的答案：</span>
									<label class="active">
										<em class="ansWords">对</em>
										<input type="radio" name="optRadTkAndWd"/>
										<span class="radiusSpan"><i class="iconfont layui-extend-duihao"></i></span>
									</label>
									<label>
										<em class="ansWords">错</em>
										<input type="radio" name="optRadTkAndWd"/>
										<span class="radiusSpan"><i class="iconfont layui-extend-duihao"></i></span>
									</label>
									<p class="noticeTxt">
										<i class="iconfont layui-extend-gonggao"></i>
										<span>请如实选择</span>
									</p>
								</div>
							</div>
							
							<!-- rightAns && errAns -->
							<div class="rightErrTip">
								<div class="rightErrImg">
									<!-- img src="Module/studyOnline/images/rightImg.png"/ -->
									<img src="Module/studyOnline/images/errorImg.png"/>
								</div>
								<div class="rightErrTxt">
									<!--  p class="infoTit rightInfoTit">
										<i class="iconfont layui-extend-xiaolian"></i>
										<span>恭喜你答对了！</span>
									</p>
									<span class="infoCon">恭喜你答对了，获得10金币奖励</span-->
									<p class="infoTit errInfoTit">
										<i class="iconfont layui-extend-cry"></i>
										<span>很遗憾你答错了！</span>
									</p>
									<span class="infoCon">胜不骄败不馁，再接再厉取得好成绩</span>
								</div>
							</div>
							
							
							<!-- 进入下一题 &&提交&& 做完了 -->
							<div class="queBtnWrap">
								<a href="javascript:void(0)">提交</a>
							</div>
							
						</li>
						
						
					</ul>
					
				</div>
				
			</div>
			
			
		</div>
		
	</body>
	<script src="/plugins/jquery/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		var loreId = ${ requestScope.loreId };
		var studyLogId = ${ requestScope.studyLogId };
		var nextLoreIdArray = "${ requestScope.nextLoreIdArray }";
		var loreTaskName = "${ requestScope.loreTaskName }";
		var quesPage = {
			init : function(){
				this.loadQuesList();
			},
			loadQuesList : function(){
				var _this = this;
				//loreId,studyLogId,loreType,nextLoreIdArray,loreTaskName
				var field = { loreId:loreId,studyLogId:studyLogId,nextLoreIdArray:nextLoreIdArray,loreTaskName:loreTaskName };
				$.ajax({
					url :'/onlineStudy.do?action=getQuestionData',
					data:field, 
					dataType:'json',
					type:'post',
					timeout:10000,
					success:function(json){
					//	app.showToast(2);
						console.log( json)
						if(json.result == 'success'){
							//renderQuesList(json.lqList);
							/*if(json.lqList[0].loreType == '针对性诊断'){
								lorePath = json.path;
							}*/
							//$('.headerTit').html(json.loreName);
							//$('.bigTit').html(json.loreName);
							//$('.smTit').html(json.loreTaskName);
						}else if(json.result == 'jtsfNotStart'){
							//plus.nativeUI.toast('当前知识点解题示范未学习，请先去学习');
						}
					},
					error:function(xhr,type,errorThrown){
						//app.showToast(2);
					//	plus.nativeUI.toast('服务器异常');
					}
				});
			}
		};
		quesPage.init();
	</script>
</html>
