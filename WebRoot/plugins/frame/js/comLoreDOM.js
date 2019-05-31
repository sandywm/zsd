/**
 * @Description: 知识点管理 公共DOM结构 增加知识点题库 以及对题库编辑的公共DOM
 * @author: hlf
 */
var loreType = 'zsqd';//点拨知道题型
var tmpGuideType = '';//用于判定点拨指导下不是主题情况下添加按钮显示的判断
var isAddClickFlag = false;//用于判断是否是通过增加按钮来添加的flag
var answerSelectImg = null,imgLayerIndex = 0,editor_answer_select = null;
//自定义模块
layui.define(['form','element','upLoadFiles'],function(exports){
	var $ = layui.jquery,form=layui.form,element = layui.element,globalUpload=layui.upLoadFiles;
    var obj = {
    	data : {
    		tkOriginAnsTxt : '',
    		tiganTypeFlag : true//是否创建了题干类型
    	},
    	getId : function(id){
    		return document.getElementById(id);
    	},
    	//初始化创建富文本编辑器
		initUeditor : function(id){
    		UE.getEditor(id,{
				initialFrameWidth : '100%',
				initialFrameHeight : 260,
				wordCount:true,
				textarea : 'description'
			});
    	},
    	initUeditorContent : function(obj,content){
    		UE.getEditor(obj).addListener("ready", function () {
		        // editor准备好之后才可以使用
		        UE.getEditor(obj).setContent(content,null);
			});
    	},
    	//头部DOM 当前知识点名字  基础题型(知识清单 点拨指导...) 以及题干类型（单选 多选...）
    	crealoreTopDOM : function(){
    		var loreTopStr = '';
    		//知识点title
    		loreTopStr += '<fieldset class="layui-elem-field layui-field-title"><legend id="zsdName"></legend></fieldset>';
    		//top 基础题型
    		loreTopStr += '<input id="basicTypeInp" type="hidden" value="知识清单"/>';
    		loreTopStr += '<div class="typeBox layui-clear"><span>基础题型：</span>';
    		loreTopStr += '<div class="typeCon">';
    		if(globalOpts == 'add'){
    			loreTopStr += '<input type="radio" inpType="zsqd" class="basicTypeRad" isCreateInp="1" name="queTypeRad" lay-filter="qusTypeFilter" value="知识清单" title="知识清单" checked>';
        		loreTopStr += '<input type="radio" inpType="zhuti" class="basicTypeRad" isCreateInp="0" name="queTypeRad" lay-filter="qusTypeFilter" value="点拨指导" title="点拨指导">';
        		loreTopStr += '<input type="radio" inpType="jtsf" class="basicTypeRad" isCreateInp="0" name="queTypeRad" lay-filter="qusTypeFilter" value="解题示范" title="解题示范">';
        		loreTopStr += '<input type="radio" inpType="ggxl" class="basicTypeRad" isCreateInp="0" name="queTypeRad" lay-filter="qusTypeFilter" value="巩固训练" title="巩固训练">';
        		loreTopStr += '<input type="radio" inpType="zdxzd" class="basicTypeRad" isCreateInp="0" name="queTypeRad" lay-filter="qusTypeFilter" value="针对性诊断" title="针对性诊断">';
        		loreTopStr += '<input type="radio" inpType="zczd" class="basicTypeRad" isCreateInp="0" name="queTypeRad" lay-filter="qusTypeFilter" value="再次诊断" title="再次诊断">';
        		loreTopStr += '<input type="radio" inpType="zsjj" class="basicTypeRad" isCreateInp="0" name="queTypeRad" lay-filter="qusTypeFilter" value="知识讲解" title="知识讲解"></div>';
    		}else if(globalOpts == 'edit'){
    			loreTopStr += '<p id="basicTypeTxt"></p>';
    		}
    		loreTopStr += '</div>';
    		loreTopStr += '<div class="tiganTypeBox"></div>';
    		loreTopStr += '<div class="lexTypeBox"></div>';
    		return loreTopStr;
    	},
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
    		tiganStr += '<div class="tiganType1 typeCon"><input id="tiganType1Inp" type="hidden" value="了解"/><select id="tiganType1Sel" lay-filter="tiganType1Sel">';
    		tiganStr += '<option value="了解">了解</option><option value="理解">理解</option><option value="应用">应用</option><option value="综合">综合</option></select></div>';
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
    		lexStr += '<a href="javascript:void(0)" class="layui-btn layui-xs addLexBtn">添加编辑词条</a>';
    		lexStr += '<i onclick="delLex(this)" currType="'+ loreType +'" class="layui-icon layui-icon-delete delLextBtn" title="删除关联词条"></i>';
    		lexStr += '</div></div>';
    		return lexStr;
    	},
    	//创建对应主要内容 
    	creaLoreConDOM : function(nowType){
    		var loreConStr = '';
    		//alert('==currNum==' + currNum)
    		//alert('==currNumLen==' + currNumLen)
    		if(isAddClickFlag){
    			if(globalOpts == 'add'){
    				currNum ++;
    			}
    		}
    	
    		//loreConStr += '<div class="queTypeCon layui-form">';
    		loreConStr += '<div id="queCon_'+ nowType +'_'+ currNum +'" class="typeCon">';
    		    		
    		if(nowType != 'zsjj'){
    			loreConStr += '<div class="layui-form-item">';
    			if(isAddClickFlag){
    				loreConStr += '<span class="newCreate"></span>';
    			}
    			loreConStr += '<label class="layui-form-label">标题：</label>';
    			if(isAddClickFlag){
    				loreConStr += '<div class="layui-input-block newCreateDiv">';
    			}else{
    				loreConStr += '<div class="layui-input-block">';
    			}
        		if(nowType == 'zsqd' || nowType == 'zd' || nowType == 'nd' || nowType == 'gjd' || nowType == 'yhd'){
        			if(isAddClickFlag){//增加删除按钮
        				if(nowType == 'zsqd'){//$('#zsqd_delBtn_' + currNum)
        					loreConStr += '<input id="'+ nowType +'Inp_'+ currNum +'" currNum="'+ currNum +'" type="text" class="layui-input '+ nowType +'_Inp" placeholder="请输入标题"/><a id="'+ nowType +'_delBtn_'+ currNum +'" currType="'+ nowType + '" onclick="delEditor(this)" currDelNum="'+ currNum +'" class="delEdiBtn delEdiBtn_'+ nowType +'" lqsId="0" href="javascript:void(0)"><i class="layui-icon layui-icon-delete"></i></a></div></div>';
        				}else{
        					loreConStr += '<input id="'+ nowType +'Inp_'+ currNum +'" currNum="'+ currNum +'" type="text" class="layui-input '+ nowType +'_Inp '+ nowType +'_Inp_spe" placeholder="请输入标题"/><a id="'+ nowType +'_delBtn_'+ currNum +'" currType="'+ nowType +'" onclick="delEditor(this)" currDelNum="'+ currNum +'" class="delEdiBtn delEdiBtn_'+ nowType +'" lqsId="0" href="javascript:void(0)"><i class="layui-icon layui-icon-delete"></i></a></div></div>';
        				}
        			}else{
        				if(nowType == 'zsqd'){
        					loreConStr += '<input id="'+ nowType +'Inp_'+ currNum +'" currNum="'+ currNum +'" type="text" class="layui-input '+ nowType +'_Inp" value="'+ loreNameBig +'" placeholder="请输入标题"/></div></div>';
        				}else{
        					loreConStr += '<input id="'+ nowType +'Inp_'+ currNum +'" currNum="'+ currNum +'" type="text" class="layui-input '+ nowType +'_Inp '+ nowType +'_Inp_spe" placeholder="请输入标题"/></div></div>';
        				}
        			}
        		}else if( nowType == 'zhuti' ){
        			if(isAddClickFlag){//增加删除按钮
        				loreConStr += '<input id="'+ nowType +'Inp_'+ currNum +'" currNum="'+ currNum +'" type="text" class="layui-input '+ nowType +'_Inp '+ nowType +'_Inp_zhuti" disabled /><a id="'+ nowType +'_delBtn_'+ currNum +'" currType="'+ nowType + '" onclick="delEditor(this)" currDelNum="'+ currNum +'" class="delEdiBtn delEdiBtn_'+ nowType +'" lqsId="0" href="javascript:void(0)"><i class="layui-icon layui-icon-delete"></i></a></div></div>';
        			}else{
        				loreConStr += '<input id="'+ nowType +'Inp_'+ currNum +'" currNum="'+ currNum +'" type="text" value="'+ loreNameBig +'" disabled class="layui-input '+ nowType +'_Inp_zhuti"/></div></div>';
        			}
        		}else{
        			loreConStr += '<input id="'+ nowType +'Inp_'+ currNum +'" currNum="'+ currNum +'" type="text" class="layui-input '+ nowType +'_Inp" style="width:100%;" disabled/></div></div>';
        		}
    		}
    		loreConStr += '<div class="layui-form-item"><label class="layui-form-label">';
    		if(nowType == 'zhuti'){
    			loreConStr += '主题：</label>';
    		}else if(nowType == 'jtsf' || nowType == 'ggxl' || nowType == 'zdxzd' || nowType == 'zczd' || nowType == 'zsjj'){
    			loreConStr += '题干：</label>';
    		}else{
    			loreConStr += '内容：</label>';
    		}
    		
    		loreConStr += '<div class="layui-input-block"><div id="con_'+ nowType +'_'+ currNum +'"></div></div></div>';

    		if(nowType == 'jtsf'){//解题示范增加答案和解析
    			//答案
    			loreConStr += '<div class="layui-form-item"><label class="layui-form-label">答案：</label>';
    			loreConStr += '<div class="layui-input-block"><div id="conAns_'+ nowType +'_'+ currNum +'"></div></div></div>';
    			//解析
    			loreConStr += '<div class="layui-form-item"><label class="layui-form-label">解析：</label>';
    			loreConStr += '<div class="layui-input-block"><div id="conAnaly_'+ nowType +'_'+ currNum +'"></div></div></div>';
    		}else if(nowType == 'ggxl' || nowType == 'zdxzd' || nowType == 'zczd'){//巩固训练 针对性诊断 再次诊断增加  问题选项 选择答案 以及 解析和提示
    			
    			loreConStr += '<div id="ansSelWrap_'+ nowType +'"  class="ansSelWrap layui-form-item"><label id="nowTxt_'+ nowType +'" class="layui-form-label"></label>';
    			loreConStr += '<div id="answerSelectDiv_'+ nowType +'" class="layui-input-block answerSelectDiv"></div>';
    			loreConStr += '<div id="wendaTypeWrap_'+ nowType +'" class="layui-input-block wendaTypeWrap"></div>';
    			loreConStr += '<div id="tkTypeWrap_'+ nowType +'" class="layui-input-block tkTypeWrap"></div>';
    			loreConStr += '</div>';
    			//解析
    			loreConStr += '<div class="layui-form-item"><label class="layui-form-label">解析：</label>';
    			loreConStr += '<div class="layui-input-block"><div id="conAnaly_'+ nowType +'_'+ currNum +'"></div></div></div>';
    			//提示
    			loreConStr += '<div class="layui-form-item layui-form"><label class="layui-form-label">提示：</label>';
    			loreConStr += '<div class="layui-input-block selTipsBox">';
    			loreConStr += '<input type="hidden" id="tipsInp_'+ nowType +'" class="tipsInp"/>';
    			loreConStr += '<select id="selTipsSel_'+ nowType +'" lay-filter="selTipsSel"></select>';
    			loreConStr += '<div id="tipsCon_'+ nowType +'" class="tipsCon"></div>';
    			loreConStr += '</div></div>';
    		}
    		if(nowType == 'zhuti'){
    			loreConStr += '<p class="zhutiNote">注：如果不区分重点、难点、关键点、易混点，就把内容直接写入主题里面，重点、难点、关键点、易混点内容留空！</p>';
    		}else if(nowType == 'zsjj'){
    			loreConStr += '<p id="zsjjTips"></p><input id="zsjjInp" type="hidden"/>';
    		}
    		loreConStr += '</div>';
    		return loreConStr;
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
    	//创建知识讲解上传DOM
    	createUpVideoDOM : function(){
    		var zsjjVideo = '';
    		zsjjVideo += '<div class="zsjjUpBox layui-form-item hasMargTop">';
    		zsjjVideo += '<label class="layui-form-label"></label>';
    		zsjjVideo += '<button id="selFileBtn" type="button" class="layui-btn layui-btn-normal">选择文件</button>';
    		zsjjVideo += '<span class="noticeSpan">注：视频不能超过50M，最多可上传1个文件</span>';
    		zsjjVideo += '<button type="button" class="layui-btn" id="upListAction">上传视频</button></div>';
			
    		zsjjVideo += '<div class="zsjjUpBox layui-form-item">';
    		zsjjVideo += '<div class="layui-input-block tabParents"><table class="layui-table" style="width:100%;">';
    		zsjjVideo += '<thead>';
    		zsjjVideo += '<tr><th>文件名</th><th>文件类型</th><th>大小</th><th>状态</th><th>进度</th><th>操作</th></tr></thead>';
    		zsjjVideo += '<tbody id="upLoadFileList"></tbody> </table></div></div>';
    		return zsjjVideo;
    	},
    	delVideoFun : function(){
    		var _this = this;
    		$('.delVideoBtn').on('click',function(){
    			layer.confirm('确定要删除当前知识讲解的视频吗？', {
				  title:'提示',
				  skin: 'layui-layer-molv',
				  btn: ['确定','取消'] //按钮
				},function(index){
					var upVideoDOM = _this.createUpVideoDOM(),
						url = '/lore.do?action=uploadFile&loreId='+loreBigId,fileType='mp4||flv';
					$('.videoBox').remove();
					$('#zsjjInp').val('');
					$('#zsjjWrap').append(upVideoDOM);
					globalUpload.uploadFiles(url,fileType,'addEditZsjj');//调用上传方法
					layer.close(index);
				});
    		});
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
    	//清空单选中value值(startNumber为起始number)
    	clearRadioValue : function(startNumber){
    		var options = document.getElementsByName("answer_singel");
    		for(var i = startNumber; i < options.length ; i++){
    			options[i].value = "";
    			options[i].checked = false;
    			$('#ans_singleInp').val('');
    			form.render();
    		}
    		if(globalOpts == 'edit'){//编辑时小于startNum的所有raido的状态重置
    			for(var i=1;i<=startNumber;i++){
    				options[i].checked = false;
    				form.render();
    			}
    		}
    	},
    	//清空复选框中value值(startNumber为起始number)
    	clearCheckBoxValue : function(startNumber){
    		var options = document.getElementsByName("answer_multi"),
    			currResultAns = $('#result_answer_new').html().split(',');
    		
    		//multiAnsArr.length = 0;//清空当前存有答案数组的length
    		for(var i = startNumber; i < options.length ; i++){
				for(var j=0;j<currResultAns.length;j++){
    				if(currResultAns[j] == options[i].id){
    					currResultAns.splice(j,1);
    					break;
    				}
    			}
    			options[i].value = "";
    			options[i].checked = false;
    		}
    		
			var tmpResultAns = currResultAns.join(',');
			if(tmpResultAns == '' || tmpResultAns == '暂未选择答案'){
				$('#result_answer_new').removeClass('hasSel').addClass('noSel').html('暂未选择答案');
				result_answer = '';
			}else{
				$('#result_answer_new').html(tmpResultAns);
				//重新拼接
				result_answer = tmpResultAns + ",";
			}
			
			if(globalOpts == 'edit'){//编辑时小于startNum的所有checkbox的状态重置
    			for(var i=0;i<startNumber;i++){
    				options[i].checked = false;
    				form.render();
    			}
    			$('#result_answer_new').removeClass('hasSel').addClass('noSel').html('暂未选择答案');
				result_answer = '';
    		}
    		form.render();
    	},
    	//清空答案选项文本框中内容
    	clearInputTextValue : function(startNumber){
    		for(var i = startNumber; i < 7 ; i++){
    			this.getId("answSelInpTxt"+i).value = "";
    		}
    	},
    	//清空答案选项图像中内容（初始化为Module/loreManager/images/defImg.png）
    	clearAnsSelImgSrc : function(startNumber){
    		for(var i = startNumber; i < 7 ; i++){
    			$('#answerSelect'+i).attr('src','Module/loreManager/images/defImg.png');
    			$('#answerSelect'+i).attr('currSrc','');
    		}
    	},
    	//清空单选框选中状态
    	clearSelectStatus : function(obj){
    		var selectOptions = document.getElementsByName(obj);
    		for(var i = 0 ; i < selectOptions.length ; i++){
    			selectOptions[i].checked = false;
    		}
    	},
    	//单选多选框填空选择数量
    	setAnswerType : function(name,value,tiganTypeVal){
    		var _this = this;
    		var msg = '当前选择的最大选项小于原数据库中最大选项，少出去的那部分数据将被清空!是否继续?';
    		if(globalOpts == 'add'){
    			for(var j = 1 ; j <= parseInt(value); j++){
        			this.getId(name+"_"+j).style.display = "block";
        		}
        		for(var i = parseInt(value)+ 1; i < 7;i++){
        			this.getId(name+"_"+i).style.display = "none";
        		}
    		}else{
				if(value < answerNum){
					layer.confirm(msg, {
					  title:'提示',
					  skin: 'layui-layer-molv',
					  btn: ['确定','取消'] //按钮
					},function(index){
						//一旦确定后更新answerNum
						answerNum = value;
						_this.clearAnswerSelect(name, value, tiganTypeVal);
						//答案选项图片上传个数/答案选项文字输入框上传个数
						_this.setAnswerSelect(value);
						layer.close(index);
					},function(){
						$('#maxSelInpNum').val(answerNum);
						$('#maxChoiceNumSel').val(answerNum);
						form.render();
					});
				}else{
					answerNum = value;//更新answerNum
					for(var j = 1 ; j <= parseInt(value); j++){
	        			this.getId(name+"_"+j).style.display = "block";
	        		}
					if(tiganTypeVal == '单选题'){
						for(var i = parseInt(value)+ 1; i <= 6;i++){
    	        			this.getId(name+"_"+i).style.display = "none";
    	        			document.getElementsByName("answer_singel")[i - 1].checked = false;;
    	        		}
					}else if(tiganTypeVal == '多选题'){
						for(var i = parseInt(value)+ 1; i <= 6;i++){
    	        			this.getId(name+"_"+i).style.display = "none";
    	        			document.getElementsByName("answer_multi")[i - 1].checked = false;;
    	        		}
					}else if(tiganTypeVal == '填空选择题'){
						$('#result_answer_new_tk').removeClass('hasSel').addClass('noSel').html('暂未选择答案');
	        			result_answer = '';
	        			result_answer_text = '';
					}
	        		
	        		//答案选项图片上传个数/答案选项文字输入框上传个数
					_this.setAnswerSelect(value);
				}
    		}
    	},
    	//当前选择的最大选项引起的动作
    	//少出去的部分将被清空，状态，数据将被清空 编辑的时候运用
    	clearAnswerSelect : function(name,newMaxNumber,questionType){
    		//设置少出去的那部分为隐藏
    		for(var j = 1 ; j <= parseInt(newMaxNumber); j++){
    			this.getId(name+"_"+j).style.display = "block";
    		}
    		for(var i = parseInt(newMaxNumber) + 1; i < 7;i++){
    			this.getId(name+"_"+i).style.display = "none";
    		}
    		if(answerType == "pic"){
    			//清空图片答案的src
    			this.clearAnsSelImgSrc(parseInt(newMaxNumber)+1);
    		}else{
    			//清空文字答案的value
    			this.clearInputTextValue(parseInt(newMaxNumber)+1);
    		}
    		//清空少出去的那部分单选按框/多选框中的value值
    		if(questionType == "单选题"){
    			this.clearRadioValue(newMaxNumber);
    		}else if(questionType == '多选题'){//清除当前新的最大选项之后的chekcbox value
    			this.clearCheckBoxValue(newMaxNumber);
    		}else if(questionType == "填空选择题"){
    			//清空input的value值，清空当前已选的所有答案->暂未选择答案，清空result_answer_text的值
				for(var i = parseInt(newMaxNumber)+ 1; i <= 6;i++){
        			this.getId(name+"_"+i).style.display = "none";
        			$('input[name=answer_multiTk]').eq(i-1).attr('alt','');
        			$('#result_answer_new_tk').removeClass('hasSel').addClass('noSel').html('暂未选择答案');
        			result_answer = '';
        			result_answer_text = '';
        		}
    		}
    	},
    	//答案选项图片上传个数/答案选项文字输入框上传个数
    	setAnswerSelect : function(value){
    		for(var j = 1 ; j <= parseInt(value); j++){
    			this.getId("answerSelImg_"+j).style.display="block";
    			this.getId("inpTxt_answ_"+j).style.display = "block";
    		}
    		for(var i = parseInt(value) + 1; i < 7;i++){
    			this.getId("answerSelImg_"+i).style.display="none";
    			this.getId("inpTxt_answ_"+i).style.display = "none";
    		}
    		if(globalOpts == 'add'){
    			this.clearAll();//增加时切换最多选项清空之前所填写的
    		}
    	},
    	//编辑初始化时根据当前的最大选项显示对应数量的文本框 img和对应数量的答案选项
    	initShowInpByMaxOptNum : function(maxOptNum,name){
    		for(var j = 1 ; j <= parseInt(maxOptNum); j++){
    			this.getId("answerSelImg_"+j).style.display="block";
    			this.getId("inpTxt_answ_"+j).style.display = "block";
    		}
    		for(var i = parseInt(maxOptNum) + 1; i < 7;i++){
    			this.getId("answerSelImg_"+i).style.display="none";
    			this.getId("inpTxt_answ_"+i).style.display = "none";
    		}
    		for(var j = 1 ; j <= parseInt(maxOptNum); j++){
    			this.getId(name+"_"+j).style.display = "block";
    		}
    		for(var i = parseInt(maxOptNum)+ 1; i < 7;i++){
    			this.getId(name+"_"+i).style.display = "none";
    		}
    	},
    	//切换题干类型清空初始所有值
    	clearAll : function(questionType){
    		var tiganTypeVal = $('#tiganTypeInp').val();
    		//清空答案选项
    		this.clearInputTextValue(1); 
    		this.clearAnsSelImgSrc(1);
    		if(tiganTypeVal == '单选题'){
    			//清空答案
        		this.clearRadioValue(0);
        		//清空选中状态
        		this.clearSelectStatus("answer_singel");
    		}
    		if(tiganTypeVal == '多选题'){
    			this.clearCheckBoxValue(0);
    			this.clearSelectStatus("answer_multi");
    			//清空复选框选择答案结果
    			$('#result_answer_new').removeClass('hasSel').addClass('noSel').html('暂未选择答案');
    		}else if(tiganTypeVal == '填空选择题'){
    			$('#result_answer_new_tk').removeClass('hasSel').addClass('noSel').html('暂未选择答案');
    			//清空每个input上的alt属性值
    			$('input[name=answer_multiTk]').attr('alt','');
    			result_answer = '';
    			result_answer_text = '';
    		}
    		//this.getId("content_answer").value = "";
    	},
    	//图片方式下的截屏
    	upAnsloadImg : function(){
    		var _this = this,imgId='';
    		$('.ansImgSel').on('click',function(){
    			imgId = $(this).attr('id');
    			$('.getSrcLayer').stop().show().animate({opacity:1},300,function(){
    				$('.getImgSrcBox').show();
    			});
    			
        	    answerSelectImg = imgId;
    	    	var oldImgSrc = "defImg.png";
    	    	var currentImgSrc = document.getElementById(answerSelectImg).src;
    	    	var currentImgSrcArray = currentImgSrc.split("/");
    	    	var currentImgEndSrc = currentImgSrcArray[currentImgSrcArray.length - 1];
    	    	var content_img = "<p><img src='"+ currentImgSrc +"'></p>";
    	    	
    	    	if(currentImgEndSrc == oldImgSrc){
    	    		//清空编辑器内容
    	    		UE.getEditor("myEditor_answer_select").setContent("",null);
    	    	}else{
    	    		UE.getEditor("myEditor_answer_select").setContent(content_img,null);
    	    	}
        	    
    		});
    		//调用选择确定图片到img src中
	    	_this.addAnswerSelect(imgId);
    	},
    	//选择截图到对应的img src中
    	addAnswerSelect : function(imgId){
    		var _this = this;
    		$('.selImgSrc').on('click',function(){
    	    	var answerSelectImgContent = editor_answer_select.getContent();
    	    	var hasContents = editor_answer_select.hasContents();
    	    	var startIndex = answerSelectImgContent.indexOf("Module");
    	    	var endIndex = answerSelectImgContent.indexOf(".jpg")+4;
    	    	var endIndex_new = answerSelectImgContent.lastIndexOf(".jpg")+4;
    	    	var realEndIndex = 0;
    	    	if(endIndex > startIndex){
    	    		realEndIndex = endIndex;
    	    	}else{
    	    		realEndIndex = endIndex_new;
    	    	}
    	    	var answerSelectImgSrc = answerSelectImgContent.substring(startIndex,realEndIndex);
    	    	var successFlag = answerSelectImgContent.indexOf("img") > 0;
    	    	var simplification_answer_select = answerSelectImgSrc.substring(answerSelectImgSrc.indexOf("lore/")+5,realEndIndex);
    	    	var question_type = $("#tiganTypeInp").val();
    	    	var i = answerSelectImg.substring(answerSelectImg.length - 1,answerSelectImg.length);
    	    	if(hasContents && successFlag){
    	    		//替换src地址
    	    		_this.getId(answerSelectImg).src = answerSelectImgSrc;
    	    		_this.getId(answerSelectImg).setAttribute('currSrc',answerSelectImgSrc);
    	    		//修改答案的value值
    	    		//判断答案是单选框还是复选框
    	    		if(question_type == "单选题"){
    	    			var options = document.getElementsByName("answer_singel");
    	    			options[i-1].value = simplification_answer_select;
    	    		}else if(question_type == "多选题"){
    	    			var options1 = document.getElementsByName("answer_multi");
    	    			options1[i-1].value = simplification_answer_select;
    	    		}else if(question_type == "填空选择题"){
    	    			var options2 = document.getElementsByName("answer_multiTk");
    	    			options2[i-1].alt = simplification_answer_select;
    	    		}
    	    	}else{
    	    		_this.getId(answerSelectImg).src = "Module/loreManager/images/defImg.png";
    	    		_this.getId(answerSelectImg).setAttribute('currSrc','');
    	    		if(question_type == "单选题"){
    	    			var options = document.getElementsByName("answer_singel");
    	    			if($('#ans_singleInp').val() == options[i-1].value){
    	    				$('#ans_singleInp').val('');
    	    			}
    	    			options[i-1].value = "";
    	    			options[i-1].checked = false;
    	    			form.render();
    	    		}else if(question_type == "多选题"){
    	    			var options1 = document.getElementsByName("answer_multi");
    	    			if(options1[i-1].checked){
    	    				var currResultAns = $('#result_answer_new').html().split(','),
    							cancelOptId = options1[i-1].id;//取消选择的ID
	    					for(var j=0;j<currResultAns.length;j++){
	    						if(cancelOptId == currResultAns[j]){
	    							currResultAns.splice(j,1);
	    							break;
	    						}
	    					}
	    					var tmpResultAns = currResultAns.join(',');
	    					if(tmpResultAns == ''){
	    						$('#result_answer_new').removeClass('hasSel').addClass('noSel').html('暂未选择答案');
	    						result_answer = '';
	    					}else{
	    						$('#result_answer_new').html(tmpResultAns);
	    						//重新拼接
	        					result_answer = tmpResultAns + ",";
	    					}
	    					options1[i-1].value = "";
	    	    			options1[i-1].checked = false;
	    	    			form.render();
    	    			}
    	    			
    	    		}else if(question_type == "填空选择题"){
    	    			var options2 = document.getElementsByName("answer_multiTk");
    	    			$('#result_answer_new_tk').removeClass('hasSel').addClass('noSel').html('暂未选择答案');
    	    			options2[i-1].alt = "";
    	    			result_answer = '';
    	    			result_answer_text = '';
    	    		}
    	    		
    	    	}
    	    	$('.getImgSrcBox').hide();
    	    	$('.getSrcLayer').stop().animate({opacity:0},300,function(){
    	    		$('.getSrcLayer').hide();
    			});
    		});
    		$('.closeImgLayer').on('click',function(){
    			$('.getImgSrcBox').hide();
    	    	$('.getSrcLayer').stop().animate({opacity:0},300,function(){
    	    		$('.getSrcLayer').hide();
    			});
    		});
    	},
    	//替换选项中存在单引号为自定义字符，双引号为中文状态下的双引号,并去除空格
    	convertEngToChi : function(value){
    		return value.replace(/,/g,"，").replace(/\s+/g,"").replace(/"/g,"”").replace(/'/g,"&#wmd;");
    	},
    	//单选 多选 填空选择题型下 文字类型下input选项blur事件
    	inputBlur : function(){
    		var _this = this;
    		$('.answerSelInp').on('blur',function(){
    			var id = $(this).attr('id');
    			var i = id.substring(id.length-1,id.length);
    			var question_type = $('#tiganTypeInp').val();
    			if(question_type == '单选题'){
    				var options = document.getElementsByName('answer_singel');
    				options[i-1].value = _this.convertEngToChi($.trim($(this).val()));
    				if(options[i-1].checked){//如果当前选项处于选中状态，input blur的时候需要更新下隐藏变量 ans_singleInp的值
    					$('#ans_singleInp').val($.trim($(this).val()));
    				}
    				if($(this).val() == ''){
    					options[i-1].checked = false;
    					form.render();
    				}
    			}else if(question_type == '多选题'){
    				var options1 = document.getElementsByName('answer_multi');
    				options1[i-1].value = _this.convertEngToChi($.trim($(this).val()));
    				//如果blur时当input文本框的value值是空，那么取消其对应多选checkbox的选中状态
    				if($(this).val() == '' && options1[i-1].checked){
    					var currResultAns = $('#result_answer_new').html().split(','),
    						cancelOptId = options1[i-1].id;//取消选择的ID
    					for(var j=0;j<currResultAns.length;j++){
    						if(cancelOptId == currResultAns[j]){
    							currResultAns.splice(j,1);
    							break;
    						}
    					}
    					var tmpResultAns = currResultAns.join(',');
    					if(tmpResultAns == ''){
    						$('#result_answer_new').removeClass('hasSel').addClass('noSel').html('暂未选择答案');
    						result_answer = '';
    					}else{
    						$('#result_answer_new').html(tmpResultAns);
    						//重新拼接
        					result_answer = tmpResultAns + ",";
    					}
            			
    					options1[i-1].checked = false;
    					form.render();
    				}
    			}else if(question_type == '填空选择题'){
    				$("input[name='answer_multiTk']").eq(i-1).attr("alt",_this.convertEngToChi($.trim($(this).val())));
    			}
    		});
    		
    	},	
    	//检查填空数量是否大于/等于你选择的答案
    	checkSelectNum : function(){
    		var selectMaxNum = $("#spaceNumInp").val(),
    			tkNewVal = $('#result_answer_new_tk').text();
    		if(tkNewVal.value != "暂未选择答案"){
    			if(tkNewVal.split(",").length < selectMaxNum){
    				return true;
    			}else{
    				return false;
    			}
    		}else{
    			return true;
    		}
    	},
    	//答案选择框返回值（填空选择题）
    	addItemTk : function(){
    		var _this = this;
    		$('.multiTkBtn').on('click',function(){
    			var selectMaxNum = $("#spaceNumInp").val(),
					alt = $(this).attr('alt'),
					questionType = $('#tiganTypeInp').val();
    			if(globalOpts == 'add'){
    				if(alt == ""){
            			layer.msg('请先填写答案选项，然后再选择答案!',{icon:5,anim:6,time:2000});
            			return;
            		}else{
            			if(_this.checkSelectNum()){
            				result_answer_text += alt + ",";
            				result_answer += $(this).val() + ",";
            			}else{
            				layer.msg('当前所选答案累计数超过所选填空数量!',{icon:5,anim:6,time:2000});
            				return;
            			}
            		}
            		$('#result_answer_new_tk').removeClass('noSel').addClass('hasSel').html(result_answer.substring(0,result_answer.length - 1));
    			}else{//编辑时采用
    				if(alt == ""){
    					layer.msg('请先填写答案选项，然后再选择答案!',{icon:5,anim:6,time:2000});
            			return;
    				}else{
    					if(_this.checkSelectNum()){
							if(result_answer == ""){
								result_answer_text += alt + ",";
								result_answer += $(this).val() + ",";
							}else{
								if(result_answer.substring(result_answer.length - 1,result_answer.length) == ","){
									result_answer_text += alt + ",";
									result_answer += $(this).val() + ",";
								}else{
									result_answer_text += "," + alt + ",";
									result_answer += "," + $(this).val() + ",";
								}
							}
						}else{
							layer.msg('当前所选答案累计数超过所选填空数量!',{icon:5,anim:6,time:2000});
            				return;
						}
    					
    				}
    				if(result_answer.substring(result_answer.length - 1,result_answer.length) == ","){
    					$('#result_answer_new_tk').removeClass('noSel').addClass('hasSel').html(result_answer.substring(0,result_answer.length - 1));
    					//getId("content_answer").value = result_answer.substring(0,result_answer.length - 1);
    				}else{
    					$('#result_answer_new_tk').removeClass('noSel').addClass('hasSel').html(result_answer);
    					//getId("content_answer").value = result_answer;
    				}
    			}
        		
    		});
    	},
    	//清空已选择答案 填空选择题使用
    	clearAllAnswer : function(){
    		$('.clearSelAnsBtn').on('click',function(){
    			$('#result_answer_new_tk').html('暂未选择答案').addClass('noSel').removeClass('hasSel');
    			//getId("content_answer").value = "";
    			result_answer = "";//复选框value
    			result_answer_text = "";//复选框text
    		});
    	},
    	//添加按钮文字内容的切换
    	switchTxt : function(currType){
    		if(currType == 'zsqd'){
    			$('.addLoreCon span').html('知识清单');
    		}else if(currType == 'zd'){
				$('.addLoreCon span').html('重点');
			}else if(currType == 'nd'){
				$('.addLoreCon span').html('难点');
			}else if(currType == 'gjd'){
				$('.addLoreCon span').html('关键点');
			}else if(currType == 'yhd'){
				$('.addLoreCon span').html('易混点');
			}
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
		},
		//切换不同题型对应显示不同结构的方法
		swithTiGanType : function(value){
			var maxSelBox = this.creaMaxSel(),
				spaceSelBox = this.creaMaxSpace(),
				ansType = this.createAnsType(),
				selAnsTxt = this.createSelAnsTxt(),
				selAnsImg = this.createSelAnsImg(),
				ansSingle = this.createAnsSingle(),
				ansMulti = this.createAnsMulti(),
				wendaStr = this.wendaTypeDOM(loreType),
				judgeStr = this.judgeQueType(),
				tkSelStr = this.createTkSelDOM(),
				tkTypeStr = this.createTkTypeDOM(loreType);
			if(value != ''){
				$('#tiganTypeInp').val(value);
				$('#ansSelWrap_' + loreType).show();
				$('#nowTxt_'+loreType).html('');
				result_answer = ""; //ABCD
				result_answer_text = "";
				if(value == '单选题' || value == '多选题'){
					result_answer = '';
					$('.spaceBox').html('');
					$('#nowTxt_'+loreType).html('');
					$('.maxChoice').html(maxSelBox);
					$('#wendaTypeWrap_'+loreType).hide();
					$('#tkTypeWrap_' + loreType).hide().html('');
					if(value == '单选题'){
						$('#answerSelectDiv_' + loreType).show().html(ansType + selAnsTxt + selAnsImg + ansSingle);
					}else if(value == '多选题'){
						//multiAnsArr.length = 0;//每次切换至多选题清空多选题答案length
						$('#answerSelectDiv_' + loreType).show().html(ansType + selAnsTxt + selAnsImg + ansMulti);
					}
					this.inputBlur();
				}else if(value == '填空题'){
					$('.maxChoice').html('');
					$('#wendaTypeWrap_'+loreType).hide();
					$('.spaceBox').html(spaceSelBox);
					$('#answerSelectDiv_' + loreType).hide().html('');
					$('#nowTxt_'+loreType).html('答案：');
					$('#tkTypeWrap_' + loreType).show().html(tkTypeStr);
					$('#tkInp_' + loreType).val(this.data.tkOriginAnsTxt);
					
				}else if(value == '填空选择题'){
					$('.maxChoice').html(maxSelBox);
					$('.spaceBox').html(spaceSelBox);
					$('#wendaTypeWrap_'+loreType).hide();
					$('#tkTypeWrap_' + loreType).hide().html('');
					$('#answerSelectDiv_' + loreType).show().html(ansType + selAnsTxt + selAnsImg + tkSelStr);
					this.inputBlur();
					this.addItemTk();
					this.clearAllAnswer();
				}else if(value == '问答题'){
					$('.maxChoice').html('');
					$('.spaceBox').html('');
					$('#answerSelectDiv_' + loreType).hide().html('');
					$('#tkTypeWrap_' + loreType).hide().html('');
					$('#nowTxt_'+loreType).html('答案：');
					$('#wendaTypeWrap_'+loreType).show();
					if($('#wenda_'+loreType +'_' + currNum).length == 0){
						$('#wendaTypeWrap_'+loreType).html(wendaStr);
						UE.delEditor('wenda_'+loreType +'_' + currNum);
						obj.initUeditor('wenda_'+loreType +'_' + currNum);
					}
				}else if(value == '判断题'){
					$('.maxChoice').html('');
					$('.spaceBox').html('');
					$('#wendaTypeWrap_'+loreType).hide();
					$('#tkTypeWrap_' + loreType).hide().html('');
					$('#answerSelectDiv_' + loreType).show().html(judgeStr);
				}
				form.render();
			}else{
				$('#tiganTypeInp').val(0);
				$('.maxChoice').html('');
				$('.spaceBox').html('');
			}
		},
		//增加点拨指导时获取能否增加主题或者4个标签点的能力
		isHasAddAbility : function(loreId,loreType){
			var field = {loreId:loreId,loreType:loreType},
				currSta = '';
			layer.load('1');
			$.ajax({
				type:'post',
		        dataType:'json',
		        data:field,
		        async:false,
		        url:'/lore.do?action=checkAddInfo',
		        success:function (json){
		        	layer.closeAll('loading');	
		        	if(json['result'] == 'add'){//能增加(当是点拨指导时，主题和4个标签点能同时增加一种，当是知识讲解时，能增加记录)
		        		currSta = 'add';
		        		if(loreType == '点拨指导'){
		        			$('#guideTips').html('注：主题和重点、难点、关键点、易混点只能同时增加一种');
		        		}else if(loreType == '知识讲解'){
		        			$('#zsjjTips').html('');
		        		}
		        	}else if(json['result'] == 'editZt'){
		        		$('#guideTips').html('注：当前只能增加和编辑主题');
		        		currSta = 'editZt';
		        	}else if(json['result'] == 'noAdd'){//当是点拨指导时，主题和4个标签点都不能增加，当是知识讲解时，也不能增加
		        		$('#zsjjTips').html('注：当前知识讲解已存在，暂不能增加，如需修改请到编辑中删除后再添加');
		        		currSta = 'noAdd';
		        	}else if(json['result'] == 'addLast'){//点拨指导返回，只能增加重点，难点，关键点，易混点
		        		currSta = 'addLast';
		        		$('#guideTips').html('注：当前只能增加重点或难点或关键点或易混点，主题暂不能增加');
		        	}
		        }
			});
			return currSta;
		},
		//获取当前最大的题库数(解题示范，巩固训练,针对性诊断,再次诊断时调用)
		getCurrMaxQueNum : function(loreType,loreId){
			layer.load('1');
			var field={loreType:loreType,loreId:loreId},queNum = 0;
			$.ajax({
				type:'post',
		        dataType:'json',
		        data:field,
		        async:false,
		        url:'/lore.do?action=getCurrMaxQueNum',
		        success:function (json){
		        	layer.closeAll('loading');	
		        	queNum = json.queNum;
		        }
			});
			return queNum;
		},
		//loreType->CHN
		swithLoreTypeToCHN : function(loreType){
			var loreTypeCHN = '';
			if(loreType == 'zsqd'){
				loreTypeCHN = '知识清单';
			}else if(loreType == 'zhuti' || loreType == 'zd' || loreType=='nd' || loreType=='gjd' || loreType=='yhd'){
				loreTypeCHN = '点拨指导';
			}else if(loreType == 'jtsf'){
				loreTypeCHN = '解题示范';
			}else if(loreType == 'ggxl'){
				loreTypeCHN = '巩固训练';
			}else if(loreType == 'zdxzd'){
				loreTypeCHN = '针对性诊断';
			}else if(loreType == 'zczd'){
				loreTypeCHN = '再次诊断';
			}else if(loreType == 'zsjj'){
				loreTypeCHN = '知识讲解';
			}
			return loreTypeCHN;
		},
		//获取当前知识点下的提示列表（知识清单、点拨指导）--增加时用
		getCurrLoreTips : function(loreId,asyncFlag){
			layer.load('1');
			queTipsArr.length = 0;
			$('.tipsCon').html('');
			$('.tipsInp').val('');
			var field = {loreId:loreId},_this = this;
			$.ajax({
				type:'post',
		        dataType:'json',
		        data:field,
		        async:asyncFlag,
		        url:'/lore.do?action=getCurrLoreTipsJson',
		        success:function (json){
		        	layer.closeAll('loading');
		        	if(json['result'] == 'success'){
		        		var tipsList = json['tipsList'];
		        		queTipsArr = tipsList;
		        		_this.renderTipsSelect(tipsList);
		        	}else if(json['result'] == 'noInfo'){
		        		
		        	}
		        }
			});
		},
		//渲染提示列表select
		renderTipsSelect : function(tipsList){
			var optStr='<option value="">请选择提示标题</option>';
			for(var i=0;i<tipsList.length;i++){
				if(tipsList[i].lqsType == '主题'){
					optStr += '<option value="'+ tipsList[i].lqsId +'">[点拨指导--]'+ tipsList[i].lqsType +'</option>';
				}else{
					optStr += '<option value="'+ tipsList[i].lqsId +'">['+ tipsList[i].lqsType +']--'+ tipsList[i].lqsTitle +'</option>';
				}
			}
			$('#selTipsSel_' + loreType).html(optStr);
			form.render();
		},
		addEditLex : function(){
			$('.addLexBtn').on('click',function(){
				addEditFlag = false;
				layer.open({
					title:'添加编辑词条',
					type: 2,
				  	area: ['750px', '500px'],
				  	fixed: true, //不固定
				  	maxmin: false,
				  	shadeClose :false,
				  	content: '/Module/loreManager/jsp/addEditLex.html',
				  	end : function(){
				  		if(addEditFlag){
				  			
				  		}
				  	}
				});	
			});
		},
		//获取lqsId
		getLqsId : function(loreType){
			//var tmpArr = [];
			var tmpObj = {lqsIdArr:[],zeroFlag:false};
			$('.delEdiBtn_' + loreType).each(function(i){
				if($(this).attr('lqsid') == 0){
					tmpObj.zeroFlag = true;
				}
				tmpObj.lqsIdArr.push($(this).attr('lqsid'));
			});
			return tmpObj;
		},
		//初始化点拨指导结构
		initDbzdDOM : function(currNum){
			$('#zhuti').html(this.creaLoreConDOM('zhuti'));
			$('#zd').html(this.creaLoreConDOM('zd'));
			$('#nd').html(this.creaLoreConDOM('nd'));
			$('#gjd').html(this.creaLoreConDOM('gjd'));
			$('#yhd').html(this.creaLoreConDOM('yhd'));
			UE.delEditor('con_zhuti_' + currNum);
			this.initUeditor('con_zhuti_' + currNum);
			
			UE.delEditor('con_zd_' + currNum);
			this.initUeditor('con_zd_' + currNum);
			
			UE.delEditor('con_nd_' + currNum);
			this.initUeditor('con_nd_' + currNum);
			
			UE.delEditor('con_gjd_' + currNum);
			this.initUeditor('con_gjd_' + currNum);
			
			UE.delEditor('con_yhd_' + currNum);
			this.initUeditor('con_yhd_' + currNum);
		}
    };
    //选择提示标题
    form.on('select(selTipsSel)',function(data){
    	var value = data.value;
    	$('#tipsInp_' + loreType).val(value);
    	if(value != ''){
    		for(var i=0;i<queTipsArr.length;i++){
        		if(value == queTipsArr[i].lqsId){
        			$('#tipsCon_' + loreType).html(queTipsArr[i].lqsContent);
        		}
        	}
    	}else{
    		$('#tipsCon_' + loreType).html('');
    	}
    	
    });
    //基础题型的切换
    form.on('radio(qusTypeFilter)', function(data){
		var value = data.value;
		var tiganTypeDOM = obj.createTiganType(),
			lexStrDOM = obj.createLexDOM($(this).attr('inpType')),
			tmpLoreType = $(this).attr('inpType'),isCreateInp = $(this).attr('isCreateInp');
		loreType = $(this).attr('inpType');
		isAddClickFlag = false;//每次点击基础题型将增加按钮的flag变为false true情况只有通过添加按钮动作来改变
		$('#basicTypeInp').val(value);
		result_answer = ""; //ABCD
		result_answer_text = "";
		isCanAdd = '';//添加时切换题型清空isCanAdd
		loreTypeZHN = obj.swithLoreTypeToCHN(tmpLoreType);
		//alert('loreTypeZHN增加=' + loreTypeZHN + "--" + tmpLoreType)
		if(tmpLoreType == 'zhuti'){
			isCanAdd = obj.isHasAddAbility(loreBigId, loreTypeZHN);
			
		}
		if(tmpLoreType == 'jtsf' || tmpLoreType == 'ggxl' || tmpLoreType == 'zdxzd' || tmpLoreType == 'zczd'){
			maxQueNum = obj.getCurrMaxQueNum(loreTypeZHN,loreBigId);
		}
		//添加题干类型
		if(tmpLoreType == 'ggxl' || tmpLoreType == 'zdxzd' || tmpLoreType == 'zczd' ){//巩固训练 针对性诊断 再次诊断增加题干类型以及了解...
			$('.tiganTypeBox').show().html(tiganTypeDOM);
			$('.lexTypeBox').show().html(lexStrDOM);
			$('.ansSelWrap').hide();
			$('.answerSelectDiv').hide().html('');
			$('#nowTxtLabel').html('');
			$('.wendaTypeWrap').hide().html('');
			obj.getCurrLoreTips(loreBigId,true);//获取提示列表
			//添加编辑词条
			obj.addEditLex();
			form.render();
		}else{
			if($('.tiganTypeWrap').length > 0){
				$('.tiganTypeBox').hide().html('');
				$('.lexWrap ').hide().html('');
			}
		}
		if(loreType == 'zsqd'){
			$('.addLoreCon').show();
			obj.switchTxt(loreType);
		}else if(loreType == 'zhuti'){
			if(tmpGuideType != ''){
				$('.addLoreCon').show();
				obj.switchTxt(tmpGuideType);
				loreType = tmpGuideType;
			}else{
				$('.addLoreCon').hide();
			}
		}else{
			$('.addLoreCon').hide();
		}
		
		$('.comQuesBox').hide();
		$('.guideWrap').hide();
		$('#'+tmpLoreType+'Wrap').show();
		
		if(isCreateInp == 0){
			if(tmpLoreType == 'zhuti'){
				obj.initDbzdDOM(currNum);
			}else{
				$('#'+tmpLoreType+'Wrap').html(obj.creaLoreConDOM(tmpLoreType));
				UE.delEditor('con_'+tmpLoreType +'_' + currNum);
				obj.initUeditor('con_'+tmpLoreType +'_' + currNum);
				if(tmpLoreType == 'zsjj'){//知识讲解
					isCanAdd = obj.isHasAddAbility(loreBigId, loreTypeZHN);
					alert(isCanAdd)
					if(isCanAdd == 'add'){
						var upStr = obj.createUpVideoDOM();
						$('#zsjjWrap').append(upStr);
						var url = '/lore.do?action=uploadFile&loreId='+loreBigId,fileType='mp4||flv';
						globalUpload.uploadFiles(url,fileType,'addEditZsjj');
					}else if(isCanAdd == 'noAdd'){
						//$('.zsjjUpBox').remove();
					}
					
				}
			}
			if(tmpLoreType == 'jtsf'){
				UE.delEditor('conAns_'+tmpLoreType +'_' + currNum);
				obj.initUeditor('conAns_'+tmpLoreType +'_' + currNum);
				
				UE.delEditor('conAnaly_'+tmpLoreType +'_' + currNum);
				obj.initUeditor('conAnaly_'+tmpLoreType +'_' + currNum);
				$('#'+tmpLoreType+'Inp_' + currNum).val(loreTypeZHN + '第' + maxQueNum + '题');
			}else if(tmpLoreType == 'ggxl' || tmpLoreType == 'zdxzd' || tmpLoreType == 'zczd'){
				UE.delEditor('conAnaly_'+tmpLoreType +'_' + currNum);
				obj.initUeditor('conAnaly_'+tmpLoreType +'_' + currNum);
				$('#'+tmpLoreType+'Inp_' + currNum).val(loreTypeZHN + '第' + maxQueNum + '题');
				//UE.delEditor('conTip_'+tmpLoreType +'_' + currNum);
				//obj.initUeditor('conTip_'+tmpLoreType +'_' + currNum);
				form.render();
			}
			$(this).attr('isCreateInp','1');
		}
		
	}); 
    //单选radio的切换
    form.on('radio(answer_singel)',function(data){
    	var value = data.value;
    	$('#ans_singleInp').val(value);
    	if(data.elem.checked == true){
    		if(value == ""){
    			layer.msg('请先填写答案选项，然后再选择答案!',{icon:5,anim:6,time:2000});
    			$(this).prop('checked',false);
    			form.render();
    			return;
    		}
    	}
    	//每次切换单选项需要重新调用下input的blur事件
    	//obj.inputBlur();
    });
    //多选checkbox的切换 复选框答案返回值
    form.on('checkbox(answer_multi)',function(data){
    	var value = data.value,question_type = $('#tiganTypeInp').val(),maxSelInpVal = $('#maxSelInpNum').val();
    	var id = $(this).attr('id');
    	if(data.elem.checked == true){
    		if(value == ""){
    			layer.msg('请先填写答案选项，然后再选择答案!',{icon:5,anim:6,time:2000});
    			if($('#result_answer_new').html() == ''){
    				result_answer = '';
        			$('#result_answer_new').removeClass('hasSel').addClass('noSel').html('暂未选择答案');
    			}
    			$(this).prop('checked',false);
    			form.render();
    			return;
    		}else{ 
    			result_answer += id + ",";
    		}
    		result_answer = result_answer.replace(id + ",","");
			if(result_answer == ''){
				$('#result_answer_new').removeClass('hasSel').addClass('noSel').html('暂未选择答案');
    			return;
			}
    		if(result_answer.length == 0){
    			$('#result_answer_new').removeClass('hasSel').addClass('noSel').html('暂未选择答案');
    			return;
    		}
    	}    	
    	$('#result_answer_new').removeClass('noSel').addClass('hasSel').html(result_answer.substring(0,result_answer.length - 1));
    });
    //tab点击事件的监听 点拨指导 重点 难点 关键点 易混点
	element.on('tab(guideNavFilter)', function(data){
		//if(globalOpts == 'add'){
			var currType = $(this).attr('queType'),
				isCreaFlag = $(this).attr('isCreaFlag');
				//loreCon = obj.creaLoreConDOM(currType),
			loreType = $(this).attr('queType');
			isCanAdd = obj.isHasAddAbility(loreBigId, loreTypeZHN);
			if(globalOpts == 'add'){
				isAddClickFlag = false;//每次点击基础题型将增加按钮的flag变为false true情况只有通过添加按钮动作来改变
			}
			if(data.index != 0){
				tmpGuideType = currType;
				$('.addLoreCon').show();
				obj.switchTxt(currType);
				if(data.index == 1){
					smLoreTypeZHN = '重点';
				}else if(data.index == 2){
					smLoreTypeZHN = '难点';
				}else if(data.index == 3){
					smLoreTypeZHN = '关键点';
				}else if(data.index == 4){
					smLoreTypeZHN = '易混点';
				}
				$('.addZhuti').hide();
			}else{
				smLoreTypeZHN = '主题';
				tmpGuideType = '';
				$('.addLoreCon').hide();
				if(globalOpts == 'edit' && $('#zhuti').html() == ''){
					$('.addZhuti').show();
				}
			}
			/*if(isCreaFlag == 0 && data.index != 0){
				$('#'+currType).html(obj.creaLoreConDOM(currType));
				UE.delEditor('con_'+currType +'_' + currNum);
				obj.initUeditor('con_'+currType +'_' + currNum);
			}
			$(this).attr('isCreaFlag','1');*/
		//}
		
	});
	//题干类型选择
	form.on('select(tiganTypeSel)', function(data){
		var value = data.value;
		obj.swithTiGanType(value);
	}); 
	//问题选项类型 文字或图片radio单选事件
	form.on('radio(answSelTypeInp)', function(data){
		var value = data.value,isSelFlag = $(this).attr('isSelFlag');
		var tiganTypeVal = $('#tiganTypeInp').val(),
			ansType = obj.createAnsType(),
			selAnsTxt = obj.createSelAnsTxt(),
			selAnsImg = obj.createSelAnsImg(),
			ansSingle = obj.createAnsSingle(),
			ansMulti = obj.createAnsMulti();
		$('#answSelTypeInp').val(value);
		//multiAnsArr.length = 0;//清空多选题已经选择的答案
		if(value == 1){//文字类型的问题
			$('.answerSelectImg').hide();
			$('.answerSelectTxt ').show();
		}else if(value == 2){//图片类型的问题
			$('.answerSelectImg').show();
			$('.answerSelectTxt ').hide();
			if(isSelFlag == 'false'){
				//调用截屏方法
				obj.upAnsloadImg();
			}
			$(this).attr('isSelFlag','true');
		}
		obj.clearAll();
	});
	//最大选项select
	form.on('select(maxChoiceNumSel)',function(data){
		var value = data.value,
			tiganTypeVal = $('#tiganTypeInp').val();
		
		$('#maxSelInpNum').val(value);
		if(tiganTypeVal == '单选题'){
			obj.setAnswerType("answerBox_singel",value,tiganTypeVal);
			if(globalOpts == 'add'){
				obj.setAnswerSelect(value);
			}
		}else if(tiganTypeVal == '多选题'){
			obj.setAnswerType("answerBox_multi",value,tiganTypeVal);
			if(globalOpts == 'add'){
				result_answer = '';//每次切换最大选项清空当前值
				obj.setAnswerSelect(value);
			}
		}else if(tiganTypeVal == '填空选择题'){
			obj.setAnswerType("ansBox_multiTk",value,tiganTypeVal);
			if(globalOpts == 'add'){
				obj.setAnswerSelect(value);
			}
		}
	});
	//填空数量 select
	form.on('select(spaceNumSel)',function(data){
		var value = data.value;
		$('#spaceNumInp').val(value);
		//清空当前已选的所有答案->暂未选择答案，清空result_answer_text的值
		$('#result_answer_new_tk').removeClass('hasSel').addClass('noSel').html('暂未选择答案');
		result_answer = '';
		result_answer_text = '';
		/*for(var i = parseInt(value)+ 1; i <= 6;i++){
			
		}*/
	});
	//了解 理解 应用 综合form select
	form.on('select(tiganType1Sel)',function(data){
		$('#tiganType1Inp').val(data.value);
	});
	//判断题radio
	form.on('radio(answer_judge)',function(data){
		$('#judgeInp').val(data.value);
	});
    //输出接口
    exports('comLoreDOM', obj);
});
