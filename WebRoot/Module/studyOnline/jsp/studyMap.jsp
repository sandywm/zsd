<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html> 
<html>
  <head>
    <title>知识典在线学习</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache"> 
	<meta http-equiv="keywords" content="知识典在线学习">
	<meta http-equiv="description" content="知识典在线学习 学习地图">
	</head>
	<body>
		<%@include file="/Module/leftSideBar/loading.html"%>
		<%@include file="/Module/leftSideBar/header.html"%>
		<div class="mainConWrap">
			<div class="innerMainCon">
				<%@include file="/Module/leftSideBar/fullWidCon.html"%>				
				<!-- map -->
				<div class="studyMap">
					<div class="fourStep">
						<ul class="clearfix">
							<li currType="知识讲解">
								<img src="Module/studyOnline/images/spjj.png" alt="知识讲解"/>
							</li>
							<li currType="点拨指导">
								<img src="Module/studyOnline/images/dbzd.png" alt="点拨指导"/>
							</li>
							<li currType="知识清单">
								<img src="Module/studyOnline/images/zsqd.png" alt="知识清单"/>
							</li>
							<li currType="解题示范" class="noMargR">
								<img src="Module/studyOnline/images/jtsf.png" alt="解题示范"/>
							</li>
						</ul>
					</div>
					<!-- mapDet -->
					<div class="mapDet clearfix">
						<div class="taskDet">
							<strong class="taskTit">任务描述及奖励</strong>
							<div class="listTask">
								<ul id="listTaskUl" class="listTaskUl"></ul>
							</div>
						</div>
						<!-- 右侧主体内容 -->
						<div class="currMainTask">
							<strong class="currTaskTit ellip">当前任务 <span>1级关联知识点(命题的意义)学习 </span><em>(第13个任务)</em></strong>
							<div class="leftMainTask">
								<img class="topImg" src="Module/studyOnline/images/goldenTxt.jpg" alt="金币奖励"/>
								<strong class="golden"></strong>
								<img class="botImg" src="Module/studyOnline/images/golden.jpg"/>
							</div>
							<div class="rightMainTask">
								<p class="suyuanTit">溯源路线图</p>
								<p class="levelTxt">共<span class="totalLevel"></span>级</p>
								<p class="levelTxt"><span class="totalLevel"></span>个知识点</p>
								<a class="viewTracBtn" href="javascript:void(0)">点击查看&gt;&gt;</a>
								<p class="taskInfo">学习了<span class="loreName">命题</span>这个知识点，掌握的怎么样呢？ 检测一下吧,找出没有掌握的根源！</p>
								<a id="btnVal" class="studyBtn" href="javascript:void(0)"></a>
							</div>
						</div>
					</div>
				</div>				
			</div>
		</div>
		<!-- 知识讲解 -->
		<div class="popWin layui-anim layui-anim-scale">
			<div class="popWinTop">
				<h2 id="popWinTit">知识讲解</h2>
				<p id="popSmIntro">一线老师为您精心制作的视频</p>
				<a href="javascript:void(0)" class="closeStepBtn"><i class="iconfont layui-extend-guanbi"></i></a>
			</div>
			<div id="popWinCon_zsjj" class="popWinCon"></div>
			<div id="popWinCon_dbzd" class="popWinCon"></div>
			<div id="popWinCon_zsqd" class="popWinCon"></div>
			<div id="popWinCon_jtsf" class="popWinCon"></div>
		</div>
	</body>
	<script src="/plugins/flowPlayer/flowplayer-3.2.13.min.js" charset="utf-8"></script>
	<script type="text/javascript">
		var loreId = ${ requestScope.loreId };
		var	studyLogId = ${ requestScope.studyLogId };
		var eduId = 0;
		var cptId = 0;
		var strBack = '<a href="javascript:void(0)" class="backBtn"><i class="iconfont layui-extend-fanhui"></i><span>返回章节列表</span></a>';
		var mapPage = {
			data : {
				pathType : '',
				nextLoreId : 0,
				studyLogId : 0,
				loreType : '',
				loreTaskName : '' 
			},
			init : function(){
				this.bindEvent();
				this.initStudyMapData();
				this.loadTaskAwardInfo(loreId,studyLogId);
				$('.selEdiWrap').append(strBack);
				this.backChapList();
			},
			initStudyMapData : function(){
				var _this = this;
				$.ajax({
					url : '/onlineStudy.do?action=getStudyMapData',
					data:{loreId:loreId,studyLogId:studyLogId,logType:1}, 
					dataType:'json',
					type:'post',
					timeout:10000,
					success:function(json){
						$('.loading').hide();
						if(json.result == 'success'){ 
							console.log( json )
							$('.currTaskTit').html('当前任务  <span>' + json.loreTaskName + '</span><em>(第'+ json.task +'个任务)</em>');
							$('.golden').html(json.coin);
							$('.totalLevel').html(json.stepCount);
							$('.totalLevel').html(json.loreCount);
							$('#btnVal').html(json.buttonValue);
							eduId = json.eduId;
							cptId = json.cptId;
							_this.data.pathType = json.pathType;
							_this.data.nextLoreId = json.nextLoreIdArray;
							_this.data.studyLogId = json.studyLogId;
							_this.data.loreType = escape( json.loreType );
							_this.data.loreTaskName = encodeURI( json.loreTaskName );
							$('#currLoreName').html('&gt;' + json.loreName);
							/*h('#currTaskName').html('任务' + json.task + '：' + json.loreTaskName);
							h('#goldenNum').html(json.coin); 
							h('#btnVal').html(json.buttonValue);
							h('.loreName').html(json.loreName);
							h('.totalLevel').html('共' + json.stepCount + '级');
							h('.totalLoreNum').html('共' + json.loreCount + '个知识点');
							_this.data.loreName = json.loreName;
							_this.data.loreType = json.loreType;
							_this.data.nextLoreId = json.nextLoreIdArray;
							_this.data.loreTaskName = json.loreTaskName;
							_this.data.loreId = json.loreId;
							_this.data.studyLogId = json.studyLogId;
							_this.data.isFinish = json.isFinish;
							_this.data.pathType = json.pathType;*/
						}else if(json.result == 'inUseError'){
							zsd_toast('当前知识点无效,不能做题',1800);
						}else if(json.result == 'error'){
							zsd_toast('获取当前任务信息失败',1800);
						}
					},
					error:function(xhr,type,errorThrown){
						$('.loading').hide();
						zsd_toast('服务器异常',1500);
					}
				});
			},
			backChapList : function(){
				var _this = this;
				//返回章节列表
				$('.backBtn').on('click',function(){
					window.location.href = 'onlineStudy.do?action=goChaptePage&eduId=' + eduId + '&cptId=' + cptId;
				});
			},
			//map页面加载奖励信息
			loadTaskAwardInfo : function(loreId,studyLogId){
				var _this = this;
				$.ajax({
					url : '/onlineStudy.do?action=getStudyTaskInfo',
					data:{loreId:loreId,studyLogId:studyLogId,logType:1}, 
					dataType:'json',
					type:'post',
					timeout:10000,
					success:function(json){
						$('.noData_task').remove();
						if(json.result == 'success'){ 
							_this.renderTaskAwardList(json.taskList);
						}else if(json.result == 'noInfo'){
							$('#listTaskUl').html('');
							$('.listTask').append('<div class="noData_task"><i class="iconfont layui-extend-zanwujilu"></i><p>暂无记录</p></div>');
						}
					}
				});
			},
			renderTaskAwardList : function(list){
				var str = '';
				for(var i=0;i<list.length;i++){
					str += '<li>';
					str += '<span class="taskName ellip">'+ list[i].taskName +'</span>';
					str += '<span class="goldenNum">+'+ list[i].coin +'</span>';
					str += '</li>';
				}
				$('#listTaskUl').html(str);
			},
			bindEvent : function(){
				var _this = this;
				$('#btnVal').on('click',function(){
					window.location.href = 'onlineStudy.do?action=goQuestionPage&loreId=' + loreId + '&studyLogId=' + _this.data.studyLogId + '&pathType=' + _this.data.pathType + '&loreType=' + _this.data.loreType + '&nextLoreIdArray=' + _this.data.nextLoreId + '&loreTaskName=' + _this.data.loreTaskName;
					alert('onlineStudy.do?action=goQuestionPage&loreId=' + loreId + '&studyLogId=' + _this.data.studyLogId + '&pathType=' + _this.data.pathType + '&loreType=' + _this.data.loreType + '&nextLoreIdArray=' + _this.data.nextLoreId + '&loreTaskName=' + _this.data.loreTaskName);
				});
				$('.closeStepBtn').on('click',function(){
					$('.layer').hide();
					$('.popWin').hide();
					$('.popWinCon').hide().html('');
				});
				$('.fourStep li').on('click',function(){
					var currType = $(this).attr('currType');
					var field = {loreId:loreId,studyLogId:studyLogId};
					var currField = {loreTypeName:escape(currType)};
					currField = Object.assign(currField,field);
					$('.loading').show();
					$.ajax({
						url : '/onlineStudy.do?action=getSourceDetail',
						data:currField, 
						dataType:'json',
						type:'post',
						timeout:10000,
						success:function(json){
							$('.loading').hide();
							console.log( json );
							if(json.result == 'success'){
								$('.layer').show();
								$('.popWin').show();
								if(currType == '知识讲解'){
									$('#popWinCon_zsjj').show();
									$('#popWinTit').html('知识讲解');
									$('#popSmIntro').html('一线老师为您精心制作的视频');
									if(json.sourceDetail.indexOf('flv')){
										flowplayer("popWinCon_zsjj", "/plugins/flowPlayer/flowplayer-3.2.18.swf",{
											clip:{
												url: "/" + json.sourceDetail,
												autoPlay:false,
												autoBuffering: true
											}
										});
									}else{
										zsd_toast('暂不支持该视频格式播放',1500);
									}
								}else if(currType == '点拨指导'){
									$('#popWinCon_dbzd').show();
									$('#popWinTit').html('点拨指导');
									$('#popSmIntro').html('重点、难点、关键点、易混点，认真掌握每一点');
									_this.renderDbzdDOM(json.sourceDetailList);
								}else if(currType == '知识清单'){
									$('#popWinCon_zsqd').show();
									$('#popWinTit').html('知识清单');
									$('#popSmIntro').html('本节知识点需要背诵的内容');
									_this.renderZsqdDOM(json.sourceDetailList);
								}else if(currType == '解题示范'){
									$('#popWinCon_jtsf').show();
									$('#popWinTit').html('解题示范');
									$('#popSmIntro').html('学习解题方法，规范解题步骤');
									_this.renderJtsfDOM(json.sourceDetailList);
								}
							}else if(json.result == 'noInfo'){
								var strNoData = '<div class="noData_task"><i class="iconfont layui-extend-zanwujilu"></i><p>暂无记录</p></div>';
								if(currType == '点拨指导'){
									$('#popWinCon_dbzd').show().html(strNoData);
								}else if(currType == '知识清单'){
									$('#popWinCon_zsqd').show().html(strNoData);
								}else if(currType == '解题示范'){
									$('#popWinCon_jtsf').show().html(strNoData);
								}
							}else if(json.result == 'zsjjNotStart'){
								zsd_toast('请先观看视频讲解',1500);
							}else if(json.result == 'dbzdNotStart'){
								zsd_toast('请先观看点拨指导',1500);
							}else if(json.result == 'zsqdNotStart'){
								zsd_toast('请详细观看知识清单',1500);
							}
						},
						error:function(xhr,type,errorThrown){
							$('.loading').hide();
							zsd_toast('服务器异常',1500);
						}
					});
				});
			},
			//生成解题示范DOM
			renderJtsfDOM : function(jtsfList){
				var jtsfStr = '';
				for(var i=0;i<jtsfList.length;i++){
					jtsfStr += '<div class="smModPart zhutiPart"><strong class="dbzdSmTit"><i class="iconfont layui-extend-star"></i>';
					jtsfStr += '<span>'+ jtsfList[i].queTitle +'</span></strong>';
					jtsfStr += '<div class="listLoreStep">';
					jtsfStr += '<div class="con hasMargBot"><strong class="queTit">题干：</strong><div>'+ jtsfList[i].queSub +'</div></div>';
					jtsfStr += '<div class="con hasMargBot"><strong class="queTit">答案：</strong><div>'+ jtsfList[i].queAnswer +'</div></div>';
					jtsfStr += '<div class="con"><strong class="queTit">解析：</strong><div>'+ jtsfList[i].queResolution +'</div></div>';
					jtsfStr += '</div>';
				}
				$('#popWinCon_jtsf').html(jtsfStr);
			},
			//生成知识清单DOM
			renderZsqdDOM : function(zsqdList){
				var zsqdStr = '';
				for(var i=0;i<zsqdList.length;i++){
					zsqdStr += '<div class="listLoreStep"><strong class="smTit">标题：'+ zsqdList[i].lqsTitle +'</strong>';
					zsqdStr += '<div class="con"><p class="titP">内容：</p><div>'+ zsqdList[i].lqsContent +'</div></div></div>';
				}
				$('#popWinCon_zsqd').html(zsqdStr);
			},
			//生成点拨指导结构
			renderDbzdDOM : function(dbzdList){
				var dbzdStr = '';
				for(var i=0;i<dbzdList.length;i++){
					if(i == 0){
						dbzdStr += '<div class="smModPart zhutiPart"><strong class="dbzdSmTit"><i class="iconfont layui-extend-star"></i>';
						dbzdStr += '<span>'+ dbzdList[i].loreType +'</span></strong>';
						dbzdStr += '<div class="listLoreStep"><strong class="smTit">标题：'+ dbzdList[i].lqsTitle.replace("<","&lt") +'</strong>';
						dbzdStr += '<div class="con"><p class="titP">内容：</p><div>'+ dbzdList[i].lqsContent +'</div></div>';
					}else{
						if(dbzdList[i-1].loreType == dbzdList[i].loreType){
							dbzdStr += '<div class="listLoreStep"><strong class="smTit">标题：'+ dbzdList[i].lqsTitle.replace("<","&lt") +'</strong>';
							dbzdStr += '<div class="con"><p class="titP">内容：</p><div>'+ dbzdList[i].lqsContent +'</div></div>';
						}else{
							dbzdStr += '<div class="smModPart zhutiPart"><strong class="dbzdSmTit"><i class="iconfont layui-extend-star"></i>';
							dbzdStr += '<span>'+ dbzdList[i].loreType +'</span></strong>';
							dbzdStr += '<div class="listLoreStep"><strong class="smTit">标题：'+ dbzdList[i].lqsTitle.replace("<","&lt") +'</strong>';
							dbzdStr += '<div class="con"><p class="titP">内容：</p><div>'+ dbzdList[i].lqsContent +'</div></div>';
						}
					}
					dbzdStr += '</div>';
				}
				$('#popWinCon_dbzd').show().html(dbzdStr);
			}
			
		};
		mapPage.init();
		
	</script>
</html>
