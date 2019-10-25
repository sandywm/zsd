<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html> 
<html>
  <head>
    <title>知识典</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache"> 
	<meta http-equiv="keywords" content="学生模块">
	<meta http-equiv="description" content="学生角色">
	<link href="/plugins/layui/css/layui.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="/Module/welcome/css/welcome.css"/>
	<link href="/plugins/pace/pace-theme-flash.min.css" rel="stylesheet" type="text/css"/>
	<script src="/plugins/pace/pace.min.js" type="text/javascript"></script>	
    </head>
    <script type="text/javascript">
		var roleName = "${sessionScope.login_user_role_name}";
	</script>
	<body>
		<!-- a href="hw.do?action=goTeaQuePage">跳转~~</a -->
		<%@include file="/Module/leftSideBar/header.html"%>
		<div class="mainConWrap">
			<div class="innerMainCon">
				<%@include file="/Module/leftSideBar/leftSideBar.html"%>
				<div class="rightMainCon">
					<div class="topMainPart">
						<div class="leftMainPart">
							<!-- 我的作业 -->
							<div class="myHwBuffetMsg">
								<div class="modTit">
									<strong>我的自助餐/作业</strong>
								</div>
								<div class="currHwbfMsg">
									<a class="hasNewMsg_buff" href="javascript:void(0)">
										<span class="currMsgTxt">您有新的自助餐待学习</span>
										<span class="newMsgNum newMsgNum_buff"></span>
									</a>
									<p class="noNewMsg noNewMsg_buff">暂无新的自助餐</p>
								</div>
								
								<div class="currHwbfMsg noBor">
									<a class="hasNewMsg_hw" href="javascript:void(0)">
										<span class="currMsgTxt">您有新的家庭作业待学习</span>
										<span class="newMsgNum newMsgNum_hw"></span>
									</a>
									<p class="noNewMsg noNewMsg_hw">暂无新的家庭作业</p>
								</div>
							</div>
							
							<!-- 在线学习 -->
							<div class="studyRec">
								<div class="modTit">
									<strong>在线学习</strong>
									<a href="javascript:void(0)">更多+</a>
								</div>
								<div id="studyList" class="studyList"></div>
								
							</div>
						</div>
						<!-- 我的导师 -->
						<div class="myNtTea">
							<div class="modTit">
								<strong>我的导师</strong>
								<a href="javascript:void(0)">更多+</a>
							</div>
							<div id="myNtList" class="myNtList"></div>
						</div>
					</div>
					<!-- 勤奋报告 -->
					<div class="tongjiPart">
						<div class="modTit">
							<strong>勤奋报告</strong>
							<a href="javascript:void(0)" class="moreSpecPos">更多+</a>
						</div>
						<div id="qinfenDataBox" class="tongjiData"></div>
						<div class="rateWrap">
							<p id="rateTxt"></p>
							<p id="rateAllTxt"></p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script src="/plugins/jquery/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="/plugins/echart/echarts.min.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		var page = {
			init : function(){
				this.getIndexData();
				this.loadQinfenData();
			},
			loadQinfenData : function(){
				var _this = this;
				$.ajax({
					url : '/reportCenter.do?action=getQfTjData',
					dataType:'json',
					type:'post', 
					timeout:10000,
					success:function(json){
						var noDataStr = '<div class="noData"><img src="../images/noNtData.png"/><p class="noDataTxt">暂无勤奋报告记录<p></div>';
						if(json.result == 'success'){
							$('.tongjiPart').addClass('bigHei').removeClass('smHei');
							$('#rateTxt').html('<span>' + json.axisName1 + '转化率为：</span>' + json.rate);
							$('#rateAllTxt').html('<span>' + json.axisName2 + '转化率为：</span>' + json.rateAll);
							_this.loadChart(json,json.axisName1,json.axisName2);
						}else if(json.result == 'noInfo'){
							$('.tongjiPart').addClass('smHei').removeClass('bigHei');
							$('#qinfenDataBox').html(noDataStr);
							$('#rateTxt').html('');
							$('#rateAllTxt').html('');
						}
					}
				});
			},
			loadChart : function(json,axisName1,axisName2){
				var axisName = [axisName1,axisName2];
				var myChart = echarts.init(document.getElementById('qinfenDataBox')),
					singleData = [json.oneZdSuccNum,json.oneZdFailNum,json.againXxSuccNum,json.againXxFailNum,json.noRelateNum,json.relateZdFailNum,json.relateXxSuccNum,json.relateXxFailNum],
					totalData = [json.oneZdSuccNumAll,json.oneZdFailNumAll,json.againXxSuccNumAll,json.againXxFailNumAll,json.noRelateNumAll,json.relateZdFailNumAll,json.relateXxSuccNumAll,json.relateXxFailNumAll];
				 // 指定图表的配置项和数据
				var option = {
					title: {
						text: ''
					},
					color : ['#4d47f1','#fb4aaf'],
					tooltip: {trigger: 'axis'},
					legend: {
						data: axisName,
						x : 15
					},
					grid: {
						x: 35,
						x2: 25,
						y: 60,
						y2: 120
					},
					toolbox: {
						y : -5,
						feature : {
							dataView : {show: false, readOnly: true},
							magicType : {show: false, type: ['line', 'bar']},
							restore : {show: false}
							//saveAsImage : {show: true}
						}
					},
					calculable: false,
					xAxis: {
						type: 'category',
						data: ['一次性通过总数', '一次性未通过总数', '再次诊断(学习)通过', '再次诊断(学习)未通过', '未溯源个数', '关联诊断未通过', '关联学习通过', '关联未学习通过'],
						axisLabel: {
								interval:0,//横轴信息全部显示
								rotate:-90//-30度角倾斜显示    
						}
					},
					yAxis : [
						{
							type : 'value'
						}
					],
					series: [{
						name: axisName[0],
						type: 'bar',
						data: singleData
					}, {
						name: axisName[1],
						type: 'bar',
						data: totalData
					}]
				};
				// 使用刚指定的配置项和数据显示图表。
				myChart.setOption(option);
			},
			getIndexData : function(){
				var _this = this;
				$.ajax({
					type:'post',
			        dataType:'json',
			        async:false,
			        url:'/common.do?action=getIndexData',
			        data : {limitNumber_study:5,limitNumber_nt:4},
			        success:function (json){
			        	var noDataStr = '<div class="noData specPos"><img src="../images/noNtData.png"/><p class="noDataTxt"><p></div>';
			        	if(json.buffetCount > 0){
			        		$('.noNewMsg_buff').hide();
			        		$('.hasNewMsg_buff').show().css('display','block');
			        		$('.newMsgNum_buff').html(json.buffetCount);
			        	}else{
			        		$('.noNewMsg_buff').show();
			        		$('.hasNewMsg_buff').hide();
			        		$('.newMsgNum_buff').html('');
			        	}
			        	if(json.hwCount > 0){
			        		$('.noNewMsg_hw').hide();
			        		$('.hasNewMsg_hw').show().css('display','block');
			        		$('.newMsgNum_hw').html(json.hwCount);
			        	}else{
			        		$('.noNewMsg_hw').show();
			        		$('.hasNewMsg_hw').hide();
			        		$('.newMsgNum_hw').html('');
			        	}
						if(json.studyList.length > 0){
							_this.renderStudyList(json.studyList);
						}else{
							$('#studyList').html(noDataStr);
							$('#studyList').find('.noDataTxt').html('暂无学习记录');
						}
						if(json.ntList.length > 0){
							_this.renderMyNtList(json.ntList);
						}else{
							$('#myNtList').html(noDataStr);
							$('#myNtList').find('.noDataTxt').html('暂无导师');
						}
			        }
				});
			},
			renderStudyList : function(list){
				var str = '<ul>';
				for(var i=0;i<list.length;i++){
					str += '<li><a studyLogId="'+ list[i].studyLogId +'" href="javascript:void(0)">';
					str += '<span class="subName">['+ list[i].subName +']</span>';
					str += '<span class="loreName">'+ list[i].loreName +'</span>';
					str += '<span class="studyTime">'+ list[i].studyDate +'</span>';
					str += '</a></li>';
				}
				str += '</ul>';
				$('#studyList').html(str);
			},
			renderMyNtList : function(list){
				var str = '<ul>';
				for(var i=0;i<list.length;i++){
					str += '<li class="layui-clear">';
					str += '<img class="ntPic" src="/'+ list[i].portrait +'"/>';
					str += '<div class="myNtInfo">';
					str += '<strong>'+ list[i].realName +'</strong>';
					str += '<p>'+ list[i].schoolType +' '+ list[i].subName +'</p>';
					str += '<p>'+ list[i].diffDays +'天后到期</p>';
					str += '<span>'+ list[i].bindInfo +'</span>';
					str += '</li>';
				}
				str += '</ul>';
				$('#myNtList').html(str);
			}
		};
		page.init();
	</script>
</html>
