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
		
		
	</body>
	<script src="/plugins/jquery/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		var loreId = ${ requestScope.loreId };
		var studyLogId = ${ requestScope.studyLogId };
		var nextLoreIdArray = "${ requestScope.nextLoreIdArray }";
		var loreTaskName = "${ requestScope.loreTaskName }";
		var completeNum = 0,questionLength = 0; //当前已经做过题的数量包括对和错
		var perScale = 0; // 当前已做过题的比例
		var lastCommitNumber = 0;
		var currentAllQuestionFlag = 1,originLoreId=0,lorePath='',currPageType='zhenduanPage',byComPos='';//判断是否从学习记录详情进来标识;
		var _self = null;
		var quesPage = {
			init : function(){
				this.loadQuesList();
			},
			loadQuesList : function(){
				var _this = this;
				//loreId,studyLogId,loreType,nextLoreIdArray,loreTaskName
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
						console.log( json)
						if(json.result == 'success'){
							$('#bigLoreName').html(json.loreName);
							if(json.loreTaskName == '针对性诊断'){
								$('#smLoreName').html('针对' + json.loreName + '而设定的诊断题目,诊断共'+ json.lqList.length +'题,点击题号即可查看该题');
							}else{
								$('#smLoreName').html('针对' + json.loreTaskName + '而设定的诊断题目,诊断共'+ json.lqList.length +'题,点击题号即可查看该题');
							}
							renderQuesList(json.lqList);
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
		function renderQuesList(list){
			if(list != null){
				questionLength = list.length;
				for(var i=0;i<questionLength;i++){
					var index = i + 1,currentLoreId = list[i].currLoreId;
					//侧边题号
					var li_index = ""; 
					if(index == 1){
						li_index =  "<li id='queIndex_"+index+"' class='quesCardNum active' onclick='showQuestionByIndex("+index+")'><span>"+ index +"</span></li>";//题库序列号li
					}else{
						li_index =  "<li id='queIndex_"+index+"' class='quesCardNum' onclick='showQuestionByIndex("+index+")'><span>"+ index +"</span></li>";//题库序列号li
					}
					$('#innerQuesCardUl').append(li_index);
					//核心区域内容
					var li_question = ""; 
					if(index == 1){
						li_question = "<li id='question_"+index+"' style='opacity:1;display:block;'></li>";//题库列表li-显示
					}else{
						li_question = "<li id='question_"+index+"'></li>";//题库列表li-隐藏
					}
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
						var tkwdBefore = '<div id="tkWdBefore_'+ index +'" class="tkAndWdInfo"">';
						tkwdBefore += '<i class="iconfont layui-extend-gonggao"></i><p>请拿出纸和笔验算一下，这道题主要考察解题规范和解题步，要认证验算！得出结果后点击提交即可。</p></div>';
						//验算完成显示正确答案并选择自己算的对或错
						var myTkWdAnsRes = '<div id="tkWdRightAns_'+ index +'" class="tkAndWdOpt">';
						myTkWdAnsRes += '<span class="queAnsTit">我的答案：</span>';
						myTkWdAnsRes += '<label><em class="ansWords">对</em><input type="radio" name="answer_option_radio_'+ index +'1" value="1"/><span class="radiusSpan"><i class="iconfont layui-extend-duihao"></i></span></label>';
						myTkWdAnsRes += '<label><em class="ansWords">错</em><input type="radio" name="answer_option_radio_'+ index +'1" value="0"/><span class="radiusSpan"><i class="iconfont layui-extend-duihao"></i></span></label>';
						myTkWdAnsRes += '<p class="noticeTxt"><i class="iconfont layui-extend-gonggao"></i><span>请如实选择</span></p></div>';
						myTkWdAnsRes += '</div>';
						$('#tkWdWrap_'+index).append(tkwdBefore + myTkWdAnsRes);
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
								var spanNumber = k + "" + ii;
								var answer_option_span_start = "<label id='answer_option_span_"+spanNumber+"' class='ansOpt'>";
								var input_radio_value = answerOptionArray[jj++];
								var input_radio = "",iconDOM = '';
								var input_lable_value = "<em id='answer_option_label_"+spanNumber+"' class='ansWords'>"+ transOption(ii) +"</em>";
								if(list[i].lqType == "多选题"){
									input_radio = "<input type='checkbox' class='selAnsInp_"+ index +"' onclick=choiceOption('"+list[i].lqType+"',"+ ii +","+index+") id='answer_option_radio_"+ index +"_"+ii+"' name='answer_option_radio_"+number_new+"' value='"+input_radio_value+"'/>";
									iconDOM = '<span class="squareSpa"><i class="iconfont layui-extend-duihao"></i></span>';
								}else{
									input_radio = "<input type='radio' class='selAnsInp_"+ index +"' onclick=choiceOption('"+list[i].lqType+"',"+ ii +","+index+") id='answer_option_radio_"+ index +"_"+ii+"' name='answer_option_radio_"+number_new+"' value='"+input_radio_value+"'/>";
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
							if(questionType_temp == '单选题' || questionType_temp == '多选题' || questionType_temp == '填空选择题'){
								var strMyAns = '<span>我的解答：</span><p class="rightAns">'+ list[i].myAnswer +'</p><i class="iconfont layui-extend-duihao rightAnsIcon"></i>';
								$('#queAnsOptWrap_' + index).hide();
								$('#myAnsWrap_' + index).show().html(strMyAns);
							}else if(questionType_temp == '问答题' || questionType_temp == '填空题'){
								var strMyAnsTkWd = '<span class="queAnsTit">我的答案：</span>';
								$('#tkWdBefore_' + index).hide();
								$('#tkWdRightAns_' + index).show().html(strMyAnsTkWd);;
							}
						}
						
					}
					
					
					
				}
			}
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
