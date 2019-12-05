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
		<%@include file="/Module/leftSideBar/loading.html"%>
		<div class="queWrap">
			<!-- loreName -->
			<div class="loreNameWrap">
				<a class="backBtn" href="javascript:void(0)">
					<i class="iconfont layui-extend-fanhui"></i>
					<span>返回</span>
				</a>
				<div class="loreNameTit">
					<h2 id="bigLoreName" class="ellip"></h2>
					<p id="smLoreName" class="ellip"></p>
				</div>
			</div>
			<!-- queList -->
			<div class="queListWrap">
				<a class="showCardBtn" href="javascript:void(0)" title="展开答题卡">答题卡</a>
				<!-- sideQueCard -->
				<div class="sideQueCard">
					<strong class="sideTit">答题卡</strong>
					<div class="cardList">
						<ul id="innerQuesCardUl"></ul>
					</div>
					<a href="javascript:void(0)" class="packUpBtn" title="收起"></a>
				</div>
				
				<!-- queList -->
				<div class="queList">
					<ul id="quesAreaUl"></ul>
				</div>
			</div>
		</div>
		<!-- rightAns && errAns -->
		<div class="rightErrTip">
			<div class="rightErrImg">
				<img class="rightImg" src="Module/studyOnline/images/rightImg.png"/>
				<img class="errorImg" src="Module/studyOnline/images/errorImg.png"/>
			</div>
			<div class="rightErrTxt">
				<p class="infoTit rightInfoTit">
					<i class="iconfont layui-extend-xiaolian"></i>
					<span>恭喜你答对了！</span>
				</p>
				<span class="infoCon rightInfoCon">恭喜你答对了，获得10金币奖励</span>
				<p class="infoTit errInfoTit">
					<i class="iconfont layui-extend-cry"></i>
					<span>很遗憾你答错了！</span>
				</p>
				<span class="infoCon errInfoCon">胜不骄败不馁，再接再厉取得好成绩</span>
			</div>
		</div>
	</body>
	<script src="/plugins/jquery/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="/plugins/frame/js/comMethod.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		var loreId = ${ requestScope.loreId };
		var studyLogId = ${ requestScope.studyLogId };
		var nextLoreIdArray = "${ requestScope.nextLoreIdArray }";
		var loreTaskName = "${ requestScope.loreTaskName }";
		var completeNum = 0,questionLength = 0; //当前已经做过题的数量包括对和错
		var perScale = 0; // 当前已做过题的比例
		var lastCommitNumber = 0;
		var currentAllQuestionFlag = 1,lorePath='',currPageType='zhenduanPage',byComPos='';//判断是否从学习记录详情进来标识;
		var quesPage = {
			init : function(){
				this.loadQuesList();
				this.bindEvent();
			},
			bindEvent : function(){
				$('.packUpBtn').on('click',function(){
					$('.sideQueCard').stop().animate({'left':-131},function(){
						$('.packUpBtn').hide();
						$('.showCardBtn').show();
					});
					//155
					//70
					$('.queList').stop().animate({'left':70});
				});
				$('.backBtn').on('click',function(){
					window.location.href = 'onlineStudy.do?action=goStudyMapPage&loreId=' + loreId + '&studyLogId=' + studyLogId;
				});
				$('.showCardBtn').on('click',function(){
					$('.showCardBtn').hide();
					$('.packUpBtn').show();
					$('.queList').stop().animate({'left':155});
					$('.sideQueCard').stop().animate({'left':0});
				});
			},
			loadQuesList : function(){
				var _this = this;
				var field = { loreId:loreId,studyLogId:studyLogId,nextLoreIdArray:nextLoreIdArray,loreTaskName:escape(loreTaskName) };
				$('.loading').show();
				$.ajax({
					url :'/onlineStudy.do?action=getQuestionData',
					data:field, 
					dataType:'json',
					type:'post',
					timeout:10000,
					success:function(json){
						$('.loading').hide();
						if(json.result == 'success'){
							$('#bigLoreName').html(json.loreName);
							if(json.loreTaskName == '针对性诊断'){
								$('#smLoreName').html('针对' + json.loreName + '而设定的诊断题目,诊断共'+ json.lqList.length +'题,点击题号即可查看该题');
							}else{
								$('#smLoreName').html('针对' + json.loreTaskName + '而设定的诊断题目,诊断共'+ json.lqList.length +'题,点击题号即可查看该题');
							}
							renderQuesList(json.lqList);
						}else if(json.result == 'jtsfNotStart'){
							zsd_toast('当前知识点解题示范未学习，请先去学习',1500);
						}
					},
					error:function(xhr,type,errorThrown){
						$('.loading').hide();
						zsd_toast('服务器异常',1500);
					}
				});
			}
		};
		quesPage.init();
		function renderQuesList(list){
			console.log(list)
			if(list != null){
				questionLength = list.length;
				for(var i=0;i<questionLength;i++){
					var index = i + 1,currentLoreId = list[i].currLoreId;
					//侧边题号
					var li_index = ""; 
					var jiucuo_index = "";
					var li_question = "";
					if(index == 1){
						if(currPageType == 'zhenduanPage'){
							jiucuo_index = "<a id='jiucuoBtn_"+ index +"' lqId='"+ list[i].lqId +"' currIndex='"+ index +"' currLoreName='"+ list[i].currLoreName +"' class='jiucuoBtn' style='display:block;' href='javascript:void(0)' title='我要纠错'><i class='iconfont layui-extend-tiwen'></i></a>";	
						}
						li_index =  "<li id='queIndex_"+index+"' class='quesCardNum active' onclick='showQuestionByIndex("+index+")'><span>"+ index +"</span></li>";//题库序列号li
						li_question = "<li id='question_"+index+"' class='quesListLi' style='opacity:1;display:block;'></li>";//题库列表li-显示
					}else{
						if(currPageType == 'zhenduanPage'){
							jiucuo_index = "<a id='jiucuoBtn_"+ index +"' lqId='"+ list[i].lqId +"' currIndex='"+ index +"' currLoreName='"+ list[i].currLoreName +"' class='jiucuoBtn' href='javascript:void(0)' title='我要纠错'><i class='iconfont layui-extend-tiwen'></i></a>";	
						}
						li_index =  "<li id='queIndex_"+index+"' class='quesCardNum' onclick='showQuestionByIndex("+index+")'><span>"+ index +"</span></li>";//题库序列号li
						li_question = "<li id='question_"+index+"' class='quesListLi'></li>";//题库列表li-隐藏
					}
					if(currPageType == 'zhenduanPage'){
						$('.loreNameWrap').append(jiucuo_index);
					}
					$('#innerQuesCardUl').append(li_index);
					//核心区域内容
					$('#quesAreaUl').append(li_question);
					//queNum&&queType
					var queNumType = '<div class="queNumType"><span class="queNumSpan">'+ index +'</span><span>'+ list[i].lqType +'</span></div>';
					//queSub
					var queSubCon = '<div id="queSubBox_'+ index +'" class="queSub">'+ list[i].lqSub +'</div>';
					var quesOptions = '<div id="questionOption_'+ index +'" class="queOptWrap"></div>';
					//queAnsOpt
					var queAnsOpts = '<div id="queAnsOptWrap_'+ index +'" class="queAnsOptWrap"></div>';
					//myAns
					var myAnsWrap = '<div id="myAnsWrap_'+ index +'" class="myAnsWrap"></div>';
					//tk&wd que
					var tkAndWdQue = '<div id="tkWdWrap_'+ index +'" class="tkAndWdQue"></div>';
					//queBtnWrap
					var queBtnWrap = '<div id="questionSubmit_'+ index +'" class="queBtnWrap"></div>';
					$("#question_"+index).append(queNumType + queSubCon + quesOptions + queAnsOpts + myAnsWrap + tkAndWdQue + queBtnWrap);
					//生成随机答案选项数组(将随机答案选项添加到questionOption__index的dd标签中)
					var answerOptionArray = new Array();
					if(list[i].completeStatus == 1){//已经做过的题
						answerOptionArray = assignToArray(list[i].answerA,list[i].answerB,list[i].answerC,list[i].answerD,list[i].answerE,list[i].answerF);
					}else{//如果是没有做过的题，就需要将选项进行随机排列
						answerOptionArray = radomAnswerArray( assignToArray(list[i].answerA,list[i].answerB,list[i].answerC,list[i].answerD,list[i].answerE,list[i].answerF) );
					}
					var j = 0,answerA = "",answerB = "",answerC = "",answerD = "",answerE = "",answerF = "";
					
					if(list[i].answerA != ''){
						if(checkAnswerImg(list[i].answerA)){
							answerA = '<img src="'+ answerOptionArray[j++] +'"/>';
						}else{
							answerA = answerOptionArray[j++];
						}
						var divOption = '<div id="1_'+ index +'" class="queOpt" name="optionList_'+ index +'">A、'+ answerA +'</div>';
						$('#questionOption_' + index).append(divOption);
					}
					if(list[i].answerB != ''){
						if(checkAnswerImg(list[i].answerB)){
							answerB = '<img src="'+ answerOptionArray[j++] +'"/>';
						}else{
							answerB = answerOptionArray[j++];
						}
						var divOption = '<div id="2_'+ index +'" class="queOpt" name="optionList_'+ index +'">B、'+ answerB +'</div>';
						$('#questionOption_' + index).append(divOption);
					}
					if(list[i].answerC != ''){
						if(checkAnswerImg(list[i].answerC)){
							answerC = '<img src="'+ answerOptionArray[j++] +'"/>';
						}else{
							answerC = answerOptionArray[j++];
						}
						var divOption = '<div id="3_'+ index +'" class="queOpt" name="optionList_'+ index +'">C、'+ answerC +'</div>';
						$('#questionOption_' + index).append(divOption);
					}
					if(list[i].answerD != ''){
						if(checkAnswerImg(list[i].answerD)){
							answerD = '<img src="'+ answerOptionArray[j++] +'"/>';
						}else{
							answerD = answerOptionArray[j++];
						}
						var divOption = '<div id="4_'+ index +'" class="queOpt" name="optionList_'+ index +'">D、'+ answerD +'</div>';
						$('#questionOption_' + index).append(divOption);
					}
					if(list[i].answerE != ''){
						if(checkAnswerImg(list[i].answerE)){
							answerE = '<img src="'+ answerOptionArray[j++] +'"/>';
						}else{
							answerE = answerOptionArray[j++];
						}
						var divOption = '<div id="5_'+ index +'" class="queOpt" name="optionList_'+ index +'">E、'+ answerE +'</div>';
						$('#questionOption_' + index).append(divOption);
					}
					if(list[i].answerF != ''){
						if(checkAnswerImg(list[i].answerF)){
							answerF = '<img src="'+ answerOptionArray[j++] +'"/>';
						}else{
							answerF = answerOptionArray[j++];
						}
						var divOption = '<div id="6_'+ index +'" class="queOpt" name="optionList_'+ index +'">F、'+ answerF +'</div>';
						$('#questionOption_' + index).append(divOption);
					}
					var answerNumber = 0;
					var questionType_flag = false;//表示是问答和填空题
					if(list[i].lqType == "问答题" || list[i].lqType == "填空题"){
						$('#tkWdWrap_'+index).show();
						questionType_flag = true;
						answerNumber = 1;//只有错和对，所以赋值1;
						var tkwdBefore = '<div id="tkWdBefore_'+ index +'" class="tkAndWdInfo">';
						tkwdBefore += '<i class="iconfont layui-extend-gonggao"></i><p>请拿出纸和笔验算一下，这道题主要考察解题规范和解题步，要认证验算！得出结果后点击提交即可。</p></div>';
						//验算完成显示正确答案并选择自己算的对或错
						//正确答案
						var tkWdRealAns = '<div id="tkAndWdRealAns_'+ index +'" class="tkAndWdRealAns"><span>正确答案：</span><div class="tkwdRealAns">'+ replaceChara( list[i].realAnswer ) +'</div></div>';
						var tkwdOptStr = '<div id="tkAndWdOpt_'+ index +'" class="tkAndWdOpt">';
						tkwdOptStr += '<span class="queAnsTit">我的答案：</span>';
						tkwdOptStr += '<label><em class="ansWords">对</em><input type="radio" class="optionRadio" name="answer_option_radio_'+ index +'1" value="1"/><span class="radiusSpan"><i class="iconfont layui-extend-duihao"></i></span></label>';
						tkwdOptStr += '<label><em class="ansWords">错</em><input type="radio" class="optionRadio" name="answer_option_radio_'+ index +'1" value="0"/><span class="radiusSpan"><i class="iconfont layui-extend-duihao"></i></span></label>';
						tkwdOptStr += '<p class="noticeTxt"><i class="iconfont layui-extend-gonggao"></i><span>请如实选择</span></p></div>';
						tkwdOptStr += '</div>';
						var tkwdMyOptRes = '<div id="tkAndWdMyAns_'+ index +'" class="tkAndWdMyAns"></div>';
						$('#tkWdWrap_'+index).append(tkwdBefore + tkWdRealAns + tkwdOptStr + tkwdMyOptRes);
					}else{
						questionType_flag = false;
						answerNumber = list[i].answerNum;
						for(var k = 1 ; k <= answerNumber ; k++){
							var number_new = index + "" + k;
							var option_div = "<div id='option_div_"+number_new+"' class='queAnsOpt'></div>";
							$('#queAnsOptWrap_' + index).append(option_div);
							var answer_span = "<span class='queAnsTit'>选项"+k+"：</span>"; 
							if(answerNumber == 1){
								answer_span = "<span class='queAnsTit'>选项：</span>";
							}
							$("#option_div_"+number_new).append(answer_span);
							var jj = 0;
							for(var ii = 1 ; ii <= j ; ii++){
								var spanNumber = k + "" + ii + "_" + index;
								var answer_option_span_start = "<label id='answer_option_span_"+spanNumber+"' class='ansOpt'>";
								var input_radio_value = answerOptionArray[jj++];
								var input_radio = "",iconDOM = '';
								var input_lable_value = "<em id='answer_option_label_"+spanNumber+"' class='ansWords'>"+ transOption(ii) +"</em>";
								if(list[i].lqType == "多选题"){
									input_radio = "<input type='checkbox' onclick=selectMultAnser(this,"+index+") id='answer_option_radio_"+ index +"_"+ii+"' name='answer_option_radio_"+number_new+"' value='"+input_radio_value+"'/>";
									iconDOM = '<span class="squareSpa"><i class="iconfont layui-extend-duihao"></i></span>';
								}else{
									input_radio = "<input type='radio' class='optionRadio' id='answer_option_radio_"+ index +"_"+ii+"' name='answer_option_radio_"+number_new+"' value='"+input_radio_value+"'/>";
									iconDOM = '<span class="radiusSpan"><i class="iconfont layui-extend-duihao"></i></span>';
								}
								var answer_option_span_end = "</label>";
								$("#option_div_"+number_new).append(answer_option_span_start + input_lable_value + input_radio + iconDOM + answer_option_span_end);
							}
							//多选题选择的答案
							var selectAnserValue = "<input type='hidden' id='selectMultAnsesr_"+index+"'/>";
							var selectLabelAnserValue = "<input type='hidden' id='selectLabelMultAnsesr_"+index+"'/>";
							if(list[i].lqType == "多选题"){
								$("#questionOption_"+index).append(selectAnserValue + selectLabelAnserValue);
							}
						}
					}
					//提交动作和显示结果
					var answer_array = arrayToJson(answerOptionArray);
					//提交按钮
					var subMitBtn = "<a id='subQuesBtn_"+ index +"' href='javascript:void(0)' onclick=submitAnswer('"+ list[i].lqType +"',"+ currentLoreId +","+ index +","+ answerNumber +",'"+ escape(answer_array) +"',"+ list[i].lqId +","+ studyLogId +") style='display:block;'>提交</a>";
					//创建进入下一题按钮
					var nextNumber = index+1;
					var goNextBtn = '<a id="goNextBtn_'+ index +'" href="javascript:void(0)" onclick=goNextQuestion('+ nextNumber +')>进入下一题</a>';
					//验算完成
					var showResBtn = '<a id="showResBtn_'+ index +'" href="javascript:void(0)" onclick=showResult('+ index +')>验算完成</a>';
					var doneBtn = '';
					if(i == questionLength - 1){//最后一题
						//最后一题 做完了
						doneBtn = '<a id="doneBtn" href="javascript:void(0)" onclick=lastSubmitAnswer('+ currentLoreId +',"'+ list[i].loreType +'")>做完了</a>';
					}
					//组合提交层 btn group
					$('#questionSubmit_' + index).append(subMitBtn + goNextBtn + showResBtn + doneBtn);
					if(questionType_flag){//问题和填空题
						//隐藏首次的提交按钮，出现验算完成按钮
						$("#subQuesBtn_"+index).hide();
						$("#showResBtn_"+index).show().css('display','block');
					}else{
						$("#subQuesBtn_"+index).show().css('display','block');
						$("#showResBtn_"+index).hide();
					}
					if(list[i].completeStatus == 1){//做过的题
						var questionType_temp = list[i].lqType;
						completeNum++;
						if(list[i].result == 1){//正确
							if(questionType_temp == '单选题' || questionType_temp == '多选题' || questionType_temp == '判断题' || questionType_temp == '填空选择题'){
								var strMyAns = '<span>我的解答：</span><p class="rightAns">'+ list[i].myAnswer +'</p><i class="iconfont layui-extend-duihao rightAnsIcon"></i>';
								$('#queAnsOptWrap_' + index).hide();
								$('#myAnsWrap_' + index).show().html(strMyAns);
							}else if(questionType_temp == '问答题' || questionType_temp == '填空题'){
								var strMyAnsTkWd = '<span class="queAnsTit">我的解答：</span><p class="tkWdSucc">回答正确</p>';
								$('#tkAndWdMyAns_' + index).show().html(strMyAnsTkWd);;
							}
							currentAllQuestionFlag *= 1;
							$('#innerQuesCardUl li').eq(i).addClass('rightAns');
							//errAns
						}else if(list[i].result == 0){
							if(questionType_temp == '单选题' || questionType_temp == '多选题' || questionType_temp == '判断题' || questionType_temp == '填空选择题'){
								var strMyAns = '<span>我的解答：</span><p class="errAns">'+ list[i].myAnswer +'</p><i class="iconfont layui-extend-guanbi errAnsIcon"></i>';
								$('#queAnsOptWrap_' + index).hide();
								$('#myAnsWrap_' + index).show().html(strMyAns);
							}else if(questionType_temp == '问答题' || questionType_temp == '填空题'){
								var strMyAnsTkWd = '<span class="queAnsTit">我的解答：</span><p class="tkWdErr">回答错误</p>';
								$('#tkAndWdMyAns_' + index).show().html(strMyAnsTkWd);
							}
							currentAllQuestionFlag *= 0;
							$('#innerQuesCardUl li').eq(i).addClass('errAns');
						}
						$('#subQuesBtn_' + index).hide();//隐藏提交按钮DIV
						$('#showResBtn_' + index).hide();//验算完成按钮隐藏
						if(i == questionLength - 1){//表示是最后一题
							$('#doneBtn').show().css('display','block');//显示最后总提交按钮DIV
						}else{
							$('#goNextBtn_'+index).show().css('display','block');//显示下一题按钮DIV
						}
						lastCommitNumber++;
						
					}
				}
				choiceOptionAns();
				
				
			}
		}
		//最后提交
		function lastSubmitAnswer(currentLoreId,loreTypeName){
			var studyLogId_curr = 0;
			var step_new = 0; 
			if(lastCommitNumber == questionLength){
				//$('.maskLayer').show();
				if(currPageType == 'zhenduanPage'){
					if(loreTypeName == "再次诊断"){
						if(currentLoreId == originLoreId){
							step_new = 4;
						}else{
							step_new = 3;
						}
						if(currentAllQuestionFlag == 1){//表示当前题型全部正确
							//step:stepComplete:isFinish:access
							studyLogId_curr = updateLogStatus(step_new,0,1,1,currentLoreId,loreTypeName);
						}else{
							studyLogId_curr = updateLogStatus(step_new,0,1,2,currentLoreId,loreTypeName);//部分正确，需要进入5部学习法
						}
					}else{
						if(currentAllQuestionFlag == 1){//表示当前题型全部正确
							//access:1--当前级全部正确，2:当前级部分正确或者无正确
							//step1:诊断时如果是本知识典直接全部正确，那么修改isfinish为2，stepComplete为1，access为1
							//step2:诊断时如果是关联知识点当前级全部正确。（转向学习状态）isfinish为1，stepComplete为1，access为1
							//step=0:表示不修改step的值
							if(currentLoreId == originLoreId){//本知识点全部正确
								studyLogId_curr = updateLogStatus(0,1,2,1,currentLoreId,loreTypeName);	
							}else{//是关联知识点当前级全部正确，需要走到第三阶段，关联性诊断的学习阶段
								studyLogId_curr = updateLogStatus(0,1,1,1,currentLoreId,loreTypeName);	
							}
						}else{//表示当前题型没有全部正确(继续往下级子知识点)
							if(checkLoreId(currentLoreId)){
								//表示返钱知识点的关联性诊断已经完成，需要走到第三阶段，关联性诊断的学习阶段
								studyLogId_curr = updateLogStatus(0,1,1,2,currentLoreId,loreTypeName);
							}else{//第1个参数表示：当前知识点的关联性诊断还未完成
								studyLogId_curr = updateLogStatus(0,0,1,2,currentLoreId,loreTypeName);
							}
						}
					}
					//执行跳转
					window.location.href = 'onlineStudy.do?action=goTracebackPage&loreId=' + loreId + '&studyLogId=' + studyLogId_curr;
					/*var currField = {loreId:originLoreId,studyLogId:studyLogId_curr,userId:_self.userId,logType:1};
					app.openwin('traceBackList.html',{field:currField,byComPos:byComPos});
					var quesPage = plus.webview.currentWebview();
					setTimeout(function(){
						quesPage.close('none');
						$('.maskLayer').hide();
					},500);*/
				}else if(currPageType == 'studyPage'){//学习页面的最后提交
					//表示巩固训练做完，修改step的值为3，stepComplete=0,,isFinish=0,access=3
					//access:2--巩固训练完成（需要进入该阶段的再次诊断）
					//access:1--该阶段的再次诊断完成（需要进入下一个知识典的5步学习法）
					//access:0--5部学习法未完成
					//step默认为3--学习关联知识典阶段
					//只要巩固训练做完后就修改状态
					//先查询有无再次诊断的记录
					if(currentLoreId == originLoreId){
						step_new = 4;
					}else{
						step_new = 3;
					}
					//检测登录状态
					studyLogId_curr = updateLogStatus(step_new,0,1,3,currentLoreId,loreTypeName);	
					//执行跳转
					window.location.href = 'onlineStudy.do?action=goTracebackPage&loreId=' + loreId + '&studyLogId=' + studyLogId_curr;
					/*var currField = {loreId:originLoreId,studyLogId:studyLogId_curr,userId:_self.userId,logType:1};
					app.openwin('traceBackList.html',{field:currField,byComPos:byComPos}); 
					var studyPage = plus.webview.currentWebview();
					var stepWel = plus.webview.getWebviewById('studyWelcome.html');
					setTimeout(function(){
						stepWel.close('none');
						studyPage.close('none');
						$('.maskLayer').hide();
					},500);*/
				}
			}else{
				zsd_toast('您还有试题没做完，请做完再提交！',1500);
			}
		}
		//最后提交更新当前学习状态
		/**
			当前阶段完成，修改指定logId的isFinish状态、stepComplete状态，access状态
			stepComplete:该阶段完成状态0：未完成，1：完成
			isFinish:该知识点完成状态1：未完成，2：完成
			access：该阶段关联知识点完成状态0：未完成，1：完成
		**/
		function updateLogStatus(step,stepComplete,isFinish,access,currentLoreId,loreTypeName){
			var studyLogId_curr = 0;
			var currentStepLoreArray = nextLoreIdArray;
			//var type = loreTypeName == '巩固训练' ? 'study' : '';
			var type = loreTypeName;
			if(type == '巩固训练'){
				type = 'study';
			}else if(type == '针对性诊断'){
				type = 'zdxzd';
			}else if(type == '再次诊断'){
				type = 'againzd';
			}
			/*
				loreId originLoreId
				studyLogId _self.studyLogId
				type type
				currentStepLoreArray currentStepLoreArray
				step step
				access access
				currentLoreId currentLoreId
				stepComplete stepComplete
				isFinish isFinish
				userId _self.userId
				logType 1
			
			*/
			var field = {loreId:loreId,studyLogId:studyLogId,type:type,
					currentStepLoreArray:currentStepLoreArray,step:step,access:access,currentLoreId:currentLoreId,
					isFinish:isFinish,stepComplete:stepComplete,logType:1};
			$.ajax({
				type:"post",
				async:false,
				dataType:"json",
				data:field,
				url:"/onlineStudy.do?action=updateLogStatus",
				success:function (json){ //jsonstudyLogId，目的是将studyLogId传递出来
					if(json.result == 'success'){
						originLoreId = json.loreId;
						studyLogId_curr = json.studyLogId;
					}else if(json.result == 'error'){
						zsd_toast('服务器异常',1500);
					}else if(json.result == 'accountDue'){
						zsd_toast('当前会员已到期，请续费',1500);
					}
				}
			});
			return studyLogId_curr;
		}
		//每题提交
		function submitAnswer(lqType,currentLoreId,value,answerNumber,answerOptionArray,lqId,studyLogId){
			var selectAnserValue_result = "";
			var selectAnserLableValue_result = "";
			var flag = false;
			var regS = new RegExp("\\Module/commonJs/ueditor/jsp/lore/","g");//替换所有带特殊符号的字符串
			if(lqType == '多选题'){
				var selectMultAnserValue = $("#selectMultAnsesr_"+value).val();
				if(selectMultAnserValue == ""){
					selectAnserValue_result = "";
					selectAnserLableValue_result = "";
					zsd_toast('请选择答案',1500);
					flag = false;
				}else{
					if( checkAnswerImg(selectMultAnserValue) ){
						//替换所有
						selectAnserValue_result += selectMultAnserValue.replace(regS,"") + ",";
					}else{
						selectAnserValue_result += selectMultAnserValue + ",";
					}
					selectAnserLableValue_result = $("#selectLabelMultAnsesr_"+value).val() + ",";
					flag = true;
				}
			}else{
				for(var i = 1 ; i <= answerNumber ; i++){
					var selectAnserValue = $("input[name='answer_option_radio_"+value+i+"']:checked").val();
					if(selectAnserValue == undefined){
						zsd_toast('请选择答案',1500);
						//清空数据
						selectAnserValue_result = "";
						selectAnserLableValue_result = "";
						flag = false;
						return;
					}else{
						if(checkAnswerImg(selectAnserValue)){
							selectAnserValue_result += selectAnserValue.replace(regS,"") + ",";
						}else{
							selectAnserValue_result += selectAnserValue + ",";
						}
						if(lqType == "问答题" || lqType == "填空题"){
							if(selectAnserValue_result == "1,"){
								selectAnserLableValue_result = "回答正确,";
							}else{
								selectAnserLableValue_result = "回答错误,";	
							}
						}else{
							var selectAnserRadioId = $("input[name='answer_option_radio_"+value+i+"']:checked").attr("id");
							var number1 = i;
							var number2 = selectAnserRadioId.replace("answer_option_radio_"+value+"_","");
							selectAnserLableValue_result += $("#answer_option_label_"+number1 + "" + number2+"_"+value).html() + ",";
						}
						flag = true;
					}
				}
			}
			if(flag){
				selectAnserValue_result = delLastSeparator(selectAnserValue_result);
				selectAnserLableValue_result = delLastSeparator(selectAnserLableValue_result);	
				var field = {loreId:loreId,studyLogId:studyLogId,currentLoreId:currentLoreId,
						answerOptionArray:answerOptionArray,questionStep:value,
						myAnswer:escape(selectAnserLableValue_result),lqId:lqId,loreTaskName:escape( loreTaskName ),logType:1};
				$('.loading').show();
				$.ajax({
					url : '/onlineStudy.do?action=insertStudyInfo',
					data:field, 
					dataType:'json',
					type:'post',
					timeout:10000,
					success:function(json){
						$('.loading').hide();
						if(json.result == 'success'){
							console.log( json )
							//json.studyStatus 0错 1对
							renderNowStudyInfo(json.studyResult,lqType,selectAnserLableValue_result,value);
							$('#subQuesBtn_'+value).hide();//隐藏提交按钮div
							if(value == questionLength){//表示最后一题
								$('#doneBtn').show().css('display','block'); //显示最后提交按钮div
								$('#goNextBtn_'+value).hide(); //隐藏下一题按钮div
							}else{
								$('#goNextBtn_'+value).show().css('display','block'); //显示下一题按钮div
							}
							lastCommitNumber++;
							
						}else if(json.result == 'timeErr'){
							zsd_toast('做题太快,请休息一下再做哦~',1500);
						}else if(json.result == 'error'){
							zsd_toast('服务器异常，请稍后重试~',1500);
						}else if(json.result == 'reSubmit'){
							zsd_toast('当前不能重复提交',1500);
						}else if(json.result == 'accountDue'){
							zsd_toast('当前会员已到期，请续费',1500);
						}
					},
					error:function(xhr,type,errorThrown){
						$('.loading').hide();
						zsd_toast('服务器异常',1500);
					}
				});
			}
		}
		//每次提交后将当前做题正确错误的状态回显
		function renderNowStudyInfo(studyResult,lqType,myAnswer,questionStep){
			if(studyResult == 0){//错
				if(lqType == '单选题' || lqType == '多选题' || lqType == '判断题' || lqType == '填空选择题'){
					var strMyAns = '<span>我的解答：</span><p class="errAns">'+ myAnswer +'</p><i class="iconfont layui-extend-guanbi errAnsIcon"></i>';
					$('#queAnsOptWrap_' + questionStep).hide();
					$('#myAnsWrap_' + questionStep).show().html(strMyAns);
				}else if(questionType_temp == '问答题' || questionType_temp == '填空题'){
					var strMyAnsTkWd = '<span class="queAnsTit">我的解答：</span><p class="tkWdErr">回答错误</p>';
					$('#tkAndWdMyAns_' + questionStep).show().html(strMyAnsTkWd);
				}
				currentAllQuestionFlag *= 0;
				showTipInfo(0);
				$("#innerQuesCardUl li").eq(questionStep-1).addClass("errAns");
			}else if(studyResult == 1){//对
				if(lqType == '单选题' || lqType == '多选题' || lqType == '判断题' || lqType == '填空选择题'){
					var strMyAns = '<span>我的解答：</span><p class="rightAns">'+ myAnswer +'</p><i class="iconfont layui-extend-duihao rightAnsIcon"></i>';
					$('#queAnsOptWrap_' + questionStep).hide();
					$('#myAnsWrap_' + questionStep).show().html(strMyAns);
				}else if(lqType == '问答题' || lqType == '填空题'){
					var strMyAnsTkWd = '<span class="queAnsTit">我的解答：</span><p class="tkWdSucc">回答正确</p>';
					$('#tkAndWdMyAns_' + questionStep).show().html(strMyAnsTkWd);;
				}
				currentAllQuestionFlag *= 1;
				showTipInfo(1);
				$("#innerQuesCardUl li").eq(questionStep-1).addClass("rightAns");
			}
		}
		//做对做错提示信息
		function showTipInfo(opt){
			if(opt == 0){//错误
				$('.errorImg').show();
				$('.rightImg').hide();
				$('.errInfoTit').show();
				$('.rightInfoTit').hide();
				$('.errInfoCon').show();
				$('.rightInfoCon').hide();
			}else{
				$('.errorImg').hide();
				$('.rightImg').show();
				$('.errInfoTit').hide();
				$('.rightInfoTit').show();
				$('.errInfoCon').hide();
				$('.rightInfoCon').show();
			}
			$(".rightErrTip").show().stop().animate({'opacity':1},500,function(){
				setTimeout(function(){
					$(".rightErrTip").stop().animate({'opacity':0},500,function(){
						$(".rightErrTip").hide();
					});
				},1000);
				
			})
		}
		//切换下一题
		function goNextQuestion(number){
			if(number <= questionLength){
				$(".quesListLi").hide().css({"opacity":0});//全部题隐藏
				if(currPageType == 'zhenduanPage'){
					$('.jiucuoBtn').hide();
				}
				for(var i = 1 ; i <= questionLength ; i++){
					if(i != number){
						$("#queIndex_"+i).removeClass('active');//题号
					}else{
						$("#queIndex_"+i).addClass('active');
						$("#question_"+number).show().animate({"opacity":1},200);
						if(currPageType == 'zhenduanPage'){
							$('#jiucuoBtn_' + number).show();
						}
					}
				}
				$(".currQuesNum").html(number);
			}
		}
		//问答题和填空题时验算完成显示答案
		function showResult(index){
			$("#tkWdNoticeTxt_"+index).hide();//隐藏提示
			$("#showResBtn_"+index).hide();//隐藏验算完成按钮(显示正确答案)
			$("#tkAndWdRealAns_"+index).show();//显示正确答案和结果
			$("#tkAndWdOpt_"+index).show();
			$("#subQuesBtn_"+index).show();//显示提交按钮
		}
		//多选题点击动作
		function selectMultAnser(obj,number){
			var currSelectAnswer = $("#selectMultAnsesr_"+number).val();
			var currSelectLabelValue = $("#selectLabelMultAnsesr_"+number).val();
			if(obj.checked){ //选中
				$(obj).parent().addClass('active');
				var selectIndex = obj.id.replace("answer_option_radio_"+number+"_","");
				if(currSelectAnswer == ""){
					currSelectAnswer += obj.value;
					currSelectLabelValue += $("#answer_option_label_1"+selectIndex+"_"+number).html();
				}else{
					currSelectAnswer += "," + obj.value;
					currSelectLabelValue += "," + $("#answer_option_label_1"+selectIndex+"_"+number).html();
				}	
			}else{//未选中
				$(obj).parent().removeClass('active');
				var resultArray = currSelectAnswer.split(",");
				var labelArray = currSelectLabelValue.split(",");//和答案多少一样，可共用
				var strLength = resultArray.length;
				var repalceStr = "";
				var replaceLabelStr = "";
				for(var i = 0 ; i < strLength;i++){
					if(resultArray[i] == obj.value){
						if(i == 0){//首位
							if(strLength == 1){
								repalceStr = obj.value;
								replaceLabelStr = labelArray[i];
							}else{
								repalceStr = obj.value + ",";
								replaceLabelStr = labelArray[i] + ",";
							}
						}else{//中间任何位置+末尾
							repalceStr = "," + obj.value;
							replaceLabelStr = "," + labelArray[i];
						}
						break;
					}
				}
				currSelectAnswer = currSelectAnswer.replace(repalceStr,"");
				currSelectLabelValue = currSelectLabelValue.replace(replaceLabelStr,"");
			}
			$("#selectMultAnsesr_"+number).val(currSelectAnswer);
			$("#selectLabelMultAnsesr_"+number).val(currSelectLabelValue);
		}
		//单选题 判断题 填空选择题 问答题以及填空题点击动作
		function choiceOptionAns(){
			$('.optionRadio').each(function(){
				$(this).on("click",function(){
					$(this).attr("checked",true);
					$(this).parent('label').addClass('active').siblings().removeClass('active');
				});
			});
		}
		//根据底部答题卡题号切换对应题
		function showQuestionByIndex(number){
			$(".quesListLi").hide().css({"opacity":0});
			$("#question_"+number).show().animate({"opacity":1},200);
			$(".quesCardNum").removeClass("active");
			$("#queIndex_"+number).addClass("active");
			$('.jiucuoBtn').hide();
			$('#jiucuoBtn_' + number).show().css('display','block');
		}
		//数组答案选项随机排序
		function radomAnswerArray(array){
			var array_new = array;
			array_new.sort(function(){
				return Math.random() > 0.5 ? -1 : 1;
			});
			return array_new;
		}
		//去掉末尾分隔符（","）
		function delLastSeparator(result){
			if(result != ""){
				return result.substring(0,result.length - 1);
			}else{
				return "";
			}
		}
		//将数字转化成字母选项（1,2--A,B）
		function transOption(number){
			if(number == 1){
				return "A";
			}else if(number == 2){
				return "B";
			}else if(number == 3){
				return "C";
			}else if(number == 4){
				return "D";
			}else if(number == 5){
				return "E";
			}else if(number == 6){
				return "F";
			}
		}
		//将选项赋值到数组中
		function assignToArray(optionA,optionB,optionC,optionD,optionE,optionF){
			var array = new Array();
			var i = 0;
			if(optionA != ""){
				array[i++] = optionA;
			}
			if(optionB != ""){
				array[i++] = optionB;
			}
			if(optionC != ""){
				array[i++] = optionC;
			}
			if(optionD != ""){
				array[i++] = optionD;
			}
			if(optionE != ""){
				array[i++] = optionE;
			}
			if(optionF != ""){
				array[i++] = optionF;
			}
			return array;
		}
		//数组答案选项随机排序
		function radomAnswerArray(array){
			var array_new = array;
			array_new.sort(function(){
				return Math.random() > 0.5 ? -1 : 1;
			});
			return array_new;
		}
		//替换所有的单引号为自定义字符
		function replaceChara(value){
			return value.replace(/&#wmd;/g,"'");
		}
		//检查答案是否为图片
		function checkAnswerImg(answer){
			if(answer.indexOf("jpg") > 0 || answer.indexOf("gif") > 0 || answer.indexOf("bmp") > 0 || answer.indexOf("png") > 0){
				return true;
			}
			return false;
		}
		//数组转json
		function arrayToJson(o) { 
		    var r = [];   
		    if (typeof o == "string") 
		    	return "\"" + o.replace(/([\'\"\\])/g, "\\$1").replace(/(\n)/g, "\\n").replace(/(\r)/g, "\\r").replace(/(\t)/g, "\\t") + "\"";   
		    if (typeof o == "object") {   
		    	if (!o.sort) {   
		    		for (var i in o)   
					    r.push(i + ":" + arrayToJson(o[i]));   
				    if (!!document.all && !/^\n?function\s*toString\(\)\s*\{\n?\s*\[native code\]\n?\s*\}\n?\s*$/.test(o.toString)){   
				    	r.push("toString:" + o.toString.toString());   
				    }   
				    r = "{" + r.join() + "}";   
				} else {   
				    for (var i = 0; i < o.length; i++) {   
				    	r.push(arrayToJson(o[i]));   
				    }   
				    r = "[" + r.join() + "]";   
				}   
				return r;   
			}   
		    return o.toString();   
		}
		//将字母转成数字
		function transOption_1(myAnswer){
			if(myAnswer == "A"){
				return 1;
			}else if(myAnswer == "B"){
				return 2;
			}else if(myAnswer == "C"){
				return 3;
			}else if(myAnswer == "D"){
				return 4;
			}else if(myAnswer == "E"){
				return 5;
			}else if(myAnswer == "F"){
				return 6;
			}
		}
		function checkLoreId(currentLoreId){ 
			var pathArray = lorePath.split(":");
			for(var i = 0 ; i < pathArray.length; i++){
				var pathDetailArray = pathArray[i].split("|");
				for(j = 0 ; j < pathDetailArray.length ; j++){
					if(currentLoreId == pathDetailArray[j]){
						if(i == 0 && j == pathDetailArray.length - 1){//本知识点--stepComplete = 1
							return true;
						}else if(i > 0 && i < pathArray.length - 1){//中间知识点--stepComplete = 0
							return false;
						}else if(i == pathArray.length - 1){//溯源最后一个知识点--stepComplete = 1
							return true;
						}
					}
				}
			}
		}
	</script>
</html>
