/**
 * @Description: 知识点管理 自助餐管理 公共方法
 * @author: hlf
 */
var answerSelectImg = null,imgLayerIndex = 0,editor_answer_select = null,lexOpts='';
//自定义模块
layui.define(['form','buffetLoreDOM'],function(exports){
	var $ = layui.jquery,form=layui.form,blDOM = layui.buffetLoreDOM;
    var obj = {
    	data : {
    		tkOriginAnsTxt : '',
    		tiganTypeFlag : true,//是否创建了题干类型
    		originTypeTxt : '',//点击切换题型起原来题型
    		hasSwitchTxt : '',
    		isSwitchFlag : false//对于填空题 问答题是否切换其他题型的flag
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
    		//return value.replace(/,/g,"，").replace(/\s+/g,"").replace(/"/g,"”").replace(/'/g,"&#wmd;");
    		return value.replace(/,/g,"，").replace(/\s+/g,"").replace(/"/g,"&quot;").replace(/'/g,"&#39;");
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
    					$('#ans_singleInp').val(_this.convertEngToChi($.trim($(this).val())));
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
    				//每次blur要清空已选答案
    				$('#result_answer_new_tk').removeClass('hasSel').addClass('noSel').html('暂未选择答案');
    				result_answer = '';
    				result_answer_text = '';
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
    			result_answer = "";//复选框value
    			result_answer_text = "";//复选框text
    		});
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
			var maxSelBox = blDOM.creaMaxSel(),
				spaceSelBox = blDOM.creaMaxSpace(),
				ansType = blDOM.createAnsType(),
				selAnsTxt = blDOM.createSelAnsTxt(),
				selAnsImg = blDOM.createSelAnsImg(),
				ansSingle = blDOM.createAnsSingle(),
				ansMulti = blDOM.createAnsMulti(),
				wendaStr = blDOM.wendaTypeDOM(loreType),
				judgeStr = blDOM.judgeQueType(),
				tkSelStr = blDOM.createTkSelDOM(),
				tkTypeStr = blDOM.createTkTypeDOM(loreType);
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
					if(currPage == 'lorePage' || currPage == 'buffetPage'){
						$('.maxChoice').html(maxSelBox);
					}
					$('#wendaTypeWrap_'+loreType).hide();
					$('#tkTypeWrap_' + loreType).hide().html('');
					if(value == '单选题'){
						if(currPage == 'lorePage' || currPage == 'buffetPage'){
							$('#answerSelectDiv_' + loreType).show().html(ansType + selAnsTxt + selAnsImg + ansSingle);
							this.inputBlur();
						}else{
							$('#answerSelectDiv_' + loreType).show().html(ansSingle);
						}
					}else if(value == '多选题'){
						//multiAnsArr.length = 0;//每次切换至多选题清空多选题答案length
						if(currPage == 'lorePage' || currPage == 'buffetPage'){
							$('#answerSelectDiv_' + loreType).show().html(ansType + selAnsTxt + selAnsImg + ansMulti);
							this.inputBlur();
						}else{
							$('#answerSelectDiv_' + loreType).show().html(ansMulti);
						}
					}
				}else if(value == '填空题'){
					$('.maxChoice').html('');
					$('#wendaTypeWrap_'+loreType).hide();
					$('.spaceBox').html(spaceSelBox);
					$('#answerSelectDiv_' + loreType).hide().html('');
					$('#nowTxt_'+loreType).html('答案：');
					$('#tkTypeWrap_' + loreType).show().html(tkTypeStr);
					$('#tkInp_' + loreType).val(this.data.tkOriginAnsTxt);
					
				}else if(value == '填空选择题'){
					if(currPage == 'lorePage' || currPage == 'buffetPage'){
						$('.maxChoice').html(maxSelBox);
					}
					$('.spaceBox').html(spaceSelBox);
					$('#wendaTypeWrap_'+loreType).hide();
					$('#tkTypeWrap_' + loreType).hide().html('');
					if(currPage == 'lorePage' || currPage == 'buffetPage'){
						$('#answerSelectDiv_' + loreType).show().html(ansType + selAnsTxt + selAnsImg + tkSelStr);
						this.inputBlur();
					}else{
						$('#answerSelectDiv_' + loreType).show().html(tkSelStr);
					}
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
						this.initUeditor('wenda_'+loreType +'_' + currNum);
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
				lexOpts = $(this).attr('opts');
				addEditFlag = false;
				layer.open({
					title:'添加编辑词条',
					type: 2,
				  	area: ['850px', '550px'],
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
		//浏览知识点和自助餐时判断当前选项是否为图片
		checkAnswerImg : function(answer){
			if(answer == "" || answer == null){
				
			}else{
				if(answer.indexOf("jpg") > 0 || answer.indexOf("gif") > 0 || answer.indexOf("bmp") > 0 || answer.indexOf("png") > 0){
					return true;
				}
			}
			return false;
		},
		//浏览知识点和自助餐时的公共锚点方法
		goTarget : function(){
			$('.loreNav').on('click','li',function(){
				var currPos = $(this).attr('currPos'),pos = 0;
				$(this).addClass('active').siblings().removeClass('active');
				pos = document.getElementById(currPos).offsetTop;
				$('.loreDetCon').stop().animate({scrollTop:pos},300);
			});
			$('.loreDetCon').on('scroll',function(){
				var scrollTop = $(this).scrollTop();
				var posTop = document.querySelectorAll('.titStrong');
				for(var i=0;i<posTop.length;i++){
					if(posTop[i].offsetTop <= scrollTop){
						$('.loreNav li').removeClass('active');
						$('.loreNav li').eq(i).addClass('active');
					}
				}
			});
		},
		//填空题 问答题 切换题型(单选 多选 判断 填空选择题)方法
    	switchTiganFun : function(){
    		var _this = this;
    		var tmpLayerIndex = 0;
    		$('.switchTiganBtn').on('click',function(){
    			_this.data.isSwitchFlag = false;
    			var otherTiganType = _this.otherTgGanTypeDOM();
    			tmpLayerIndex = layer.open({
					title:'切换题型',
					type: 1,
				  	area: ['480px', '180px'],
				  	fixed: true, //不固定
				  	maxmin: false,
				  	shadeClose :true,
				  	closeBtn : 1,
				  	content: otherTiganType,
				  	end : function(){
				  		if(_this.data.isSwitchFlag){
				  			$('#tiganTypeTxt').html(_this.data.hasSwitchTxt);
				  			$('.resetBtn').show().html('还原至' + _this.data.originTypeTxt);
				  			$('.switchTiganBtn').hide();
				  			if(_this.data.hasSwitchTxt != '判断题'){
				  				$('.maxChoice').show();
				  			}
				  		}
				  	}
				});
				form.render();
				$('.switchSureBtn').on('click',function(){
					var otherInpVal = $('#otherTiganInp').val();
					if(otherInpVal == ''){
						layer.msg('请选择要切换到的题型',{icon:5,anim:6,time:2000});
						return;
					}
					_this.data.hasSwitchTxt = otherInpVal;
					_this.data.isSwitchFlag = true;
					//确认切换动作后将switchFlag变为true
					switchFlag = true;//true 打开问题选项 文字或图片
					_this.swithTiGanType(otherInpVal);
					layer.close(tmpLayerIndex);
	    		});
    		});
    	},
    	//还原至原题型方法
    	resetOriginTiganType : function(){
    		var _this = this;
    		//还原至原题题型
			$('.resetBtn').on('click',function(){
				layer.confirm('确认要将此题型再还原至' + _this.data.originTypeTxt + '题型', {
				  title:'提示',
				  skin: 'layui-layer-molv',
				  btn: ['确定','取消'] //按钮
				},function(index){
					switchFlag = false;//还原至原题型后switchFlag为false
					$('#tiganTypeTxt').html(_this.data.originTypeTxt);
					_this.swithTiGanType(_this.data.originTypeTxt);
					$('.switchTiganBtn').show();
					$('.resetBtn').hide().html('');
					layer.close(index);
				});
			});
    	},
    	//切换至其他题型结构
    	otherTgGanTypeDOM : function(){
    		var otherStr = '';
    		otherStr += '<div class="otherDOMWrap layui-form">';
    		otherStr += '<input type="hidden" id="otherTiganInp" value=""/>';
    		otherStr += '<div>';
    		otherStr += '<input type="radio" name="otherTigan" lay-filter="otherTigan" value="单选题" title="单选题">';
    		otherStr += '<input type="radio" name="otherTigan" lay-filter="otherTigan" value="多选题" title="多选题">';
    		otherStr += '<input type="radio" name="otherTigan" lay-filter="otherTigan" value="填空选择题" title="填空选择题">';
    		otherStr += '<input type="radio" name="otherTigan" lay-filter="otherTigan" value="判断题" title="判断题">';
    		otherStr += '</div>';
    		otherStr += '<div class="saveBtnWrap"><button class="layui-btn switchSureBtn">切换</button></div>';
    		otherStr += '</div>';
    		return otherStr;
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
    			//console.log('--result_answer=' + result_answer)
    			if($('#result_answer_new').html() == ''){
    				result_answer = '';
        			$('#result_answer_new').removeClass('hasSel').addClass('noSel').html('暂未选择答案');
    			}
    			$(this).prop('checked',false);
    			form.render();
    			return;
    		}else{ 
    			result_answer += id + ",";
    			//console.log('选中-result_answer=' + result_answer)
    		}
    		
    	}else{
    		result_answer = result_answer.replace(id + ",","");
    		//console.log('取消-result_answer=' + result_answer)
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
	//题干类型选择
	form.on('select(tiganTypeSel)', function(data){
		var value = data.value;
		obj.swithTiGanType(value);
	}); 
	//问题选项类型 文字或图片radio单选事件
	form.on('radio(answSelTypeInp)', function(data){
		var value = data.value,isSelFlag = $(this).attr('isSelFlag');
		var tiganTypeVal = $('#tiganTypeInp').val(),
			ansType = blDOM.createAnsType(),
			selAnsTxt = blDOM.createSelAnsTxt(),
			selAnsImg = blDOM.createSelAnsImg(),
			ansSingle = blDOM.createAnsSingle(),
			ansMulti = blDOM.createAnsMulti();
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
    exports('buffetLoreMet', obj);
});
