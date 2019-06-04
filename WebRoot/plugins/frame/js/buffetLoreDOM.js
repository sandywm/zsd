/**
 * @Description: 自助餐管理 公共DOM结构 增加知识点题库 以及对题库编辑的公共DOM
 * @author: hlf
 */
//自定义模块
layui.define(['form'],function(exports){
	var $ = layui.jquery,form=layui.form;
    var obj = {
    	//题干类型 以及了解 理解...
    	createTiganType : function(){
    		//题干类型 以及了解 理解...
    		var tiganStr='';
    		//<option value="填空题">填空题</option>
    		tiganStr += '<div class="typeBox tiganTypeWrap layui-clear"><span>题干类型：</span>';
    		tiganStr += '<input id="tiganTypeInp" type="hidden" value="0"/>';
    		if(globalOpts == 'add'){
    			tiganStr += '<div class="tiganType typeCon" style="width:140px;">';
    			tiganStr += '<select id="tiganTypeSel" lay-filter="tiganTypeSel">';
    			//<option value="问答题">问答题</option>
    			tiganStr += '<option value="">请选择题干类型</option><option value="单选题">单选题</option><option value="多选题">多选题</option>';
        		tiganStr += '<option value="判断题">判断题</option><option value="填空选择题">填空选择题</option>';
        		tiganStr += '</select>';
    		}else{
    			tiganStr += '<div class="typeCon">';
    			tiganStr += '<p id="tiganTypeTxt"></p>';
    			tiganStr += '<div class="switchTgGanTypeBox" style="display:none;"><a href="javascript:void(0)" class="switchTiganBtn"><i class="iconfont layui-extend-qiehuan"></i>切换题型</a>';
    			tiganStr += '<a class="resetBtn" href="javascript:void(0)">还原</a></div>';
    		}
    		tiganStr += '</div>';
    		if(currPage == 'lorePage'){
    			tiganStr += '<div class="tiganType1 typeCon"><input id="tiganType1Inp" type="hidden" value="了解"/><select id="tiganType1Sel" lay-filter="tiganType1Sel">';
        		tiganStr += '<option value="了解">了解</option><option value="理解">理解</option><option value="应用">应用</option><option value="综合">综合</option></select></div>';
    		}
    		tiganStr += '<div class="maxChoice typeCon"></div>';
    		tiganStr += '<div class="spaceBox typeCon"></div>';
    		tiganStr += '</div>';
    		return tiganStr;
    	},
    	//巩固训练 针对性诊断 再次诊断根据题干类型对应不同的select
    	creaMaxSel : function(){
    		var maxSel = '';
    		maxSel += '<span>最大选项：</span><input id="maxSelInpNum" type="hidden" value="4"/>';
    		maxSel += '<div class="choiceSelDiv"><select id="maxChoiceNumSel" lay-filter="maxChoiceNumSel">';
    		for(var i=2;i<=6;i++ ){
    			if(i == 4){
    				maxSel += '<option value="'+ i +'" selected>'+ i +'个选项</option>';
    			}else{
    				maxSel += '<option value="'+ i +'">'+ i +'个选项</option>';
    			}
    		}
    		maxSel += '</select></div>';
    		return maxSel;
    	},
    	//创建多少空
    	creaMaxSpace : function(){
    		var spaceStr = '';
    		spaceStr += '<span>填空数量：</span><input id="spaceNumInp" type="hidden" value="2"/>';
    		spaceStr += '<div class="spaceDiv"><select id="spaceNumSel" lay-filter="spaceNumSel">';
    		for(var i = 2 ; i <= 20; i++){
    			if(i == 2){
    				if(globalOpts == 'add'){
    					spaceStr += '<option value="'+ i +'" selected>'+ i +'空</option>';
    				}else{
    					//编辑时根据后台传来的answerNum来匹配对应的填空数量
    					spaceStr += '<option value="'+ i +'">'+ i +'空</option>';
    				}
    			}else{
    				spaceStr += '<option value="'+ i +'">'+ i +'空</option>';
    			}
    		}
    		spaceStr += '</select></div>';
    		return spaceStr;
    	},
    	//创建关联此条
    	createLexDOM : function(loreType){
    		var lexStr = '';
    		lexStr += '<div class="typeBox lexWrap layui-clear"><span style="float:left;">关联词条：</span>';
    		lexStr += '<div style="float:left;width:92%;"><input type="hidden" id="'+ loreType +'_lexId"/><input type="text" id="'+ loreType +'_lexInp" class="layui-input lexInput" readonly/>';
    		lexStr += '<a href="javascript:void(0)" opts="innerQues" class="layui-btn layui-xs addLexBtn">添加编辑词条</a>';
    		lexStr += '<i onclick="delLex(this)" currType="'+ loreType +'" class="layui-icon layui-icon-delete delLextBtn" title="删除关联词条"></i>';
    		lexStr += '</div></div>';
    		return lexStr;
    	},
    	//根据不同的题干类型创建不同的单选 多选 判断 填空...
    	//01:创建答案类型 是文字还是图片结构
    	createAnsType : function(){
    		var ansTypeStr = '';
    		//问题选项
    		ansTypeStr += '<div class="answSelType layui-form layui-clear"><span class="selTypeSpan">问题选项</span><input id="answSelTypeInp" value="1" type="hidden"/>';
    		if(globalOpts == 'add'){
    			ansTypeStr += '<div class="answSelTypeBox"><input type="radio" name="answSelTypeInp" lay-filter="answSelTypeInp" value="1" isSelFlag="false"  title="文字" checked/>';
        		ansTypeStr += '<input type="radio" name="answSelTypeInp" lay-filter="answSelTypeInp" value="2" isSelFlag="false" title="图片"/>';
        		ansTypeStr += '<span class="note">注：切换问题类型后之前选择的答案将被清空</span></div>';
    		}else{
    			ansTypeStr += '<span id="currAnsSelTypeTxt"></span>';
    		}
    		
    		ansTypeStr += '</div>';
    		return ansTypeStr;
    	},
    	//根据num->ABC
    	switchABCByNum : function(i){
    		var currWord = '';
    		if(i==1){currWord='A';}
			else if(i==2){currWord='B';}
			else if(i==3){currWord='C';}
			else if(i==4){currWord='D';}
			else if(i==5){currWord='E';}
			else if(i==6){currWord='F';}
    		return currWord;
    	},
    	//02:创建答案选项 文字类型
    	createSelAnsTxt : function(){
    		var ansTypeTxt = '';
    		//对应选项文字
    		ansTypeTxt += '<div class="answerSelectTxt layui-clear">';
    		for(var i=1;i<=6;i++){
    			if(i<=4){
    				ansTypeTxt += '<div id="inpTxt_answ_'+ i +'" class="txtAnsw"><span>'+ this.switchABCByNum(i) +'</span><input title="禁止使用空格符!" id="answSelInpTxt'+ i +'" class="answerSelInp" type="text" placeholder="请输入选项答案"/></div>';
    			}else{
    				ansTypeTxt += '<div id="inpTxt_answ_'+ i +'" class="txtAnsw" style="display:none;"><span>'+ this.switchABCByNum(i) +'</span><input title="禁止使用空格符!" id="answSelInpTxt'+ i +'" class="answerSelInp" type="text" placeholder="请输入选项答案"/></div>';
    			}
    		}
    		ansTypeTxt += '</div>';
    		return ansTypeTxt;
    	},
    	//03:创建答案选项 图片类型
    	createSelAnsImg : function(){
    		var ansTypeImg = '';
    		//对应选项图片
    		ansTypeImg += '<div class="answerSelectImg layui-clear" style="display:none;">';
    		for(var i=1;i<=6;i++){
    			if(i<=4){
    				ansTypeImg += '<div id="answerSelImg_'+ i +'" class="comImgBox"><img id="answerSelect'+ i +'" class="ansImgSel" title="点击添加图片" width="100" height="100" currSrc="" src="Module/loreManager/images/defImg.png"/><p>'+ this.switchABCByNum(i) +'</p></div>';
    			}else{
    				ansTypeImg += '<div id="answerSelImg_'+ i +'" class="comImgBox" style="display:none;"><img id="answerSelect'+ i +'" class="ansImgSel" title="点击添加图片" width="100" height="100" currSrc="" src="Module/loreManager/images/defImg.png"/><p>'+ this.switchABCByNum(i) +'</p></div>';
    			}
    		}
    		ansTypeImg += '</div>';
    		return ansTypeImg;
    	},
    	//04：创建答案 单选
    	createAnsSingle : function(){
    		var ansSingle = '';
    		//对应答案 单选
    		ansSingle += '<div class="singleAns layui-clear"><span>答案：</span>';
    		ansSingle += '<div class="singleAnsBox layui-form">';
    		ansSingle += '<input type="hidden" id="ans_singleInp"/>';
    		for(var i=1;i<=6;i++){
    			if(i<=4){
    				ansSingle += '<div id="answerBox_singel_'+ i +'" class="comPartAns"><input type="radio" name="answer_singel" value="" id="answer_singel_'+ i +'" lay-filter="answer_singel" title="'+ this.switchABCByNum(i) +'"/></div>';
    			}else{
    				ansSingle += '<div id="answerBox_singel_'+ i +'" class="comPartAns" style="display:none;"><input type="radio" name="answer_singel" value="" id="answer_singel_'+ i +'" lay-filter="answer_singel" title="'+ this.switchABCByNum(i) +'"/></div>';
    			}
    		}
    		ansSingle += '</div>';
    		ansSingle += '</div>';
    		return ansSingle;
    	},
    	//05:创建答案 多选
    	createAnsMulti : function(){
    		var ansMulti = '';
    		//对应答案 多选
    		ansMulti += '<div class="multiAns layui-clear"><span>答案：</span>';
    		ansMulti += '<div class="multiAnsBox layui-form">';
    		for(var i=1;i<=6;i++){
    			if(i<=4){
    				ansMulti += '<div id="answerBox_multi_'+ i +'" class="comPartAns_multi"><input type="checkbox" lay-skin="primary" name="answer_multi" value="" id="'+ this.switchABCByNum(i) +'" lay-filter="answer_multi" title="'+ this.switchABCByNum(i) +'"/></div>';
    			}else{
    				ansMulti += '<div id="answerBox_multi_'+ i +'" class="comPartAns_multi" style="display:none;"><input type="checkbox" lay-skin="primary" name="answer_multi" value="" id="'+ this.switchABCByNum(i) +'" lay-filter="answer_multi" title="'+ this.switchABCByNum(i) +'"/></div>';
    			}	
    		}
    		ansMulti += '<p class="hasChoiceAns">已选答案：<em id="result_answer_new" class="noSel">暂未选择答案</em></p>';
    		ansMulti += '</div>';
    		ansMulti += '</div>';
    		return ansMulti;
    	},
    	//题型为问答题
    	wendaTypeDOM : function(nowType){
    		var wendaStr = '';
    		wendaStr += '<div id="wenda_'+ nowType +'_'+ currNum +'"></div>';
    		return wendaStr;
    	},
    	//题型为判断题
    	judgeQueType : function(){
    		var judgeStr = '';
    		//题型 判断题
    		judgeStr += '<div class="answSelType layui-form layui-clear"><span class="selTypeSpan">答案</span><div class="judgeTypeBox">';
    		judgeStr += '<input id="judgeInp" type="hidden" value="对"/>';
    		judgeStr += '<input type="radio" name="answer_judge" id="ansSelJudgeInp1" lay-filter="answer_judge" value="对" title="对" checked/>';
    		judgeStr += '<input type="radio" name="answer_judge" id="ansSelJudgeInp2" lay-filter="answer_judge" value="错" title="错"/>';
    		judgeStr += '</div></div>';
    		return judgeStr;
    	},
    	//题型为填空题
    	createTkTypeDOM : function(loreType){
    		var tkStr = '';
    		tkStr += '<input id="tkInp_'+ loreType +'" type="text" class="layui-input" placeholder="请输入填空题答案，多个空之间使用英文‘,’分割"/>';
    		tkStr += '<p class="zhutiNote tkNote">注：多个空之间使用英文‘,’分割</p>';
    		return tkStr;
    	},
    	//题型为填空选择题
    	createTkSelDOM : function(){
    		var ansTkSel = '';
    		//对应答案 多选
    		ansTkSel += '<div class="multiAns layui-clear"><span>答案：</span>';
    		ansTkSel += '<div class="multiAnsBox layui-form">';
    		for(var i=1;i<=6;i++){
    			if(i<=4){
    				ansTkSel += '<div id="ansBox_multiTk_'+ i +'" class="comPartAns_multi"><input type="button" class="multiTkBtn" alt="" name="answer_multiTk" value="'+ this.switchABCByNum(i) +'"/></div>';
    			}else{
    				ansTkSel += '<div id="ansBox_multiTk_'+ i +'" class="comPartAns_multi" style="display:none;"><input type="button" class="multiTkBtn" alt="" name="answer_multiTk" value="'+ this.switchABCByNum(i) +'"/></div>';
    			}
    		}
    		ansTkSel += '<p class="hasChoiceAns noSel">已选答案：<em id="result_answer_new_tk" class="noSel">暂未选择答案</em></p>';
    		ansTkSel += '<a class="clearSelAnsBtn" href="javascript:void(0)">清空已选答案</a>';
    		ansTkSel += '</div>';
    		ansTkSel += '</div>';
    		return ansTkSel;
    	},
    	tiganTypeTxt : function(tiganType){
			var tiganTypeTxtg = '';
			if(tiganType == '单选题'){
				tiganTypeTxtg = '单选题';
			}else if(tiganType == '多选题'){
				tiganTypeTxtg = '多选题';
			}else if(tiganType == '判断题'){
				tiganTypeTxtg = '判断题';
			}else if(tiganType == '问答题'){
				tiganTypeTxtg = '问答题';
			}else if(tiganType == '填空题'){
				tiganTypeTxtg = '填空题';
			}else if(tiganType == '填空选题题'){
				tiganTypeTxtg = '填空选题题';
			}
			return tiganTypeTxtg;
		}
    };
    //输出接口
    exports('buffetLoreDOM', obj);
});
