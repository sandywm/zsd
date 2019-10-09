/**
 * @Description: 知识点管理 巩固训练 针对性诊断 再次诊断公共方法
 * @author: hlf
 */
//自定义模块
layui.define(['form','buffetLoreMet'],function(exports){
	var $ = layui.jquery,form=layui.form,blMet=layui.buffetLoreMet;
    var obj = {
    	getId : function(id){
    		return document.getElementById(id);
    	},
		//替换所有的单引号为自定义字符
    	replaceChara : function(value){
    		return value.replace(/&#wmd;/g,"'");
    	},
    	//替换选项中存在单引号为自定义字符，双引号为中文状态下的双引号
    	replaceQuote:function(value){
    		var result = value.replace(/,/g,"，").replace(/\s+/g,"").replace(/"/g,"”").replace(/'/g,"&#wmd;");
    		return result;
    	},
    	//判断答案是文字还是图片
    	findAnserType : function(answerA){
    		var questionType = $('#tiganTypeInp').val();
    		if(answerA.indexOf("Module/commonJs/ueditor/jsp/lore") < 0){
    			if(questionType == "单选题" || questionType == "多选题" || questionType == "填空选择题"){
    				answerType = "character";
    			}
    			return "character";
    		}else{
    			if(questionType == "单选题" || questionType == "多选题" || questionType == "填空选择题"){
    				answerType = "pic";
    			}
    			return "pic";
    		}
    	},
    	//初始化问题选项选择状态 问题类型是文字类型还是图片类型
    	initAnswerSelectType : function(answer1){
    		if(this.findAnserType(answer1) == "character"){//文字类型
    			$('#currAnsSelTypeTxt').html('文字');
    			//将问题选项的值给隐藏变量
    			$('#answSelTypeInp').val('1');
    			$('.answerSelectTxt').show();
    			$('.answerSelectImg ').hide();
    		}else{
    			$('#currAnsSelTypeTxt').html('图片');
    			$('#answSelTypeInp').val('2');
    			$('.answerSelectTxt').hide();
    			$('.answerSelectImg ').show();
    		}
    	},
    	convertEngToChi1 : function(value){
    		//return value.replace(/,/g,"，").replace(/\s+/g,"").replace(/"/g,"”").replace(/'/g,"&#wmd;");
    		return value.replace(/,/g,"，").replace(/\s+/g,"").replace(/\&quot;/g,'"').replace(/\&#39;/g,"'");
    	},
    	initAnswerOption : function(answerType,answer1,answer2,answer3,answer4,answer5,answer6){
    		var _this = this;
    		this.initAnswerSelectType(answer1);
    		var options,questionType = $('#tiganTypeInp').val();
    		if(questionType == "单选题"){
    			if(this.findAnserType(answer1) == "pic"){
    				realAnswer = "Module/commonJs/ueditor/jsp/lore/"+realAnswer;
    			}
    			options = document.getElementsByName("answer_singel");
    		}else if(questionType == "判断题"){
    			options = document.getElementsByName("answer_judge");
    		}else if(questionType == "多选题"){
    			options = document.getElementsByName("answer_multi");
    		}else if(questionType == "填空选择题"){
    			options = document.getElementsByName("answer_multiTk");
    		}
    		if(questionType == "单选题" || questionType == "多选题" || questionType == "填空选择题"){
    			if(answerType == "character"){//文字题型
        			if(answer1 != ""){
        				this.getId("answSelInpTxt1").value = _this.convertEngToChi1(answer1);
        				if(questionType == "填空选择题"){
        					options[0].alt = answer1;
        				}else{//单选题 多选题 判断题
        					options[0].value = answer1;
        				}
        			}
        			if(answer2 != ""){
        				this.getId("answSelInpTxt2").value = _this.convertEngToChi1(answer2);
        				if(questionType == "填空选择题"){
        					options[1].alt = answer2;
        				}else{
        					options[1].value = answer2;
        				}
        			}
        			if(answer3 != ""){
        				this.getId("answSelInpTxt3").value = _this.convertEngToChi1(answer3);
        				if(questionType == "填空选择题"){
        					options[2].alt = answer3;
        				}else{
        					options[2].value = answer3;
        				}
        			}
        			if(answer4 != ""){
        				this.getId("answSelInpTxt4").value = _this.convertEngToChi1(answer4);
        				if(questionType == "填空选择题"){
        					options[3].alt = answer4;
        				}else{
        					options[3].value = answer4;
        				}
        			}
        			if(answer5 != ""){
        				this.getId("answSelInpTxt5").value = _this.convertEngToChi1(answer5);
        				if(questionType == "填空选择题"){
        					options[4].alt = answer5;
        				}else{
        					options[4].value = answer5;
        				}
        			}
        			if(answer6 != ""){
        				this.getId("answSelInpTxt6").value = _this.convertEngToChi1(answer6);
        				if(questionType == "填空选择题"){
        					options[5].alt = answer6;
        				}else{
        					options[5].value = answer6;
        				}
        			}
        		}else{
        			if(answer1 != ""){
        				this.getId("answerSelect1").src = answer1;
        				$('#answerSelect1').attr('currSrc',answer1);
        				//this.getId("answerSelect1").setAttribut()
        				var newAnswer = answer1.split("/");
        				var newAnswerLength = newAnswer.length;
        				if(questionType == "填空选择题"){
        					options[0].alt = newAnswer[newAnswerLength-2]+"/"+newAnswer[newAnswerLength-1];
        				}else{
        					options[0].value = newAnswer[newAnswerLength-2]+"/"+newAnswer[newAnswerLength-1];
        				}
        			}
        			if(answer2 != ""){
        				this.getId("answerSelect2").src = answer2;
        				$('#answerSelect2').attr('currSrc',answer2);
        				var newAnswer = answer2.split("/");
        				var newAnswerLength = newAnswer.length;
        				if(questionType == "填空选择题"){
        					options[1].alt = newAnswer[newAnswerLength-2]+"/"+newAnswer[newAnswerLength-1];
        				}else{
        					options[1].value = newAnswer[newAnswerLength-2]+"/"+newAnswer[newAnswerLength-1];
        				}
        			}
        			if(answer3 != ""){
        				this.getId("answerSelect3").src = answer3;
        				$('#answerSelect3').attr('currSrc',answer3);
        				var newAnswer = answer3.split("/");
        				var newAnswerLength = newAnswer.length;
        				if(questionType == "填空选择题"){
        					options[2].alt = newAnswer[newAnswerLength-2]+"/"+newAnswer[newAnswerLength-1];
        				}else{
        					options[2].value = newAnswer[newAnswerLength-2]+"/"+newAnswer[newAnswerLength-1];
        				}
        			}
        			if(answer4 != ""){
        				this.getId("answerSelect4").src = answer4;
        				$('#answerSelect4').attr('currSrc',answer4);
        				var newAnswer = answer4.split("/");
        				var newAnswerLength = newAnswer.length;
        				if(questionType == "填空选择题"){
        					options[3].alt = newAnswer[newAnswerLength-2]+"/"+newAnswer[newAnswerLength-1];
        				}else{
        					options[3].value = newAnswer[newAnswerLength-2]+"/"+newAnswer[newAnswerLength-1];
        				}
        			}
        			if(answer5 != ""){
        				this.getId("answerSelect5").src = answer5;
        				$('#answerSelect5').attr('currSrc',answer5);
        				var newAnswer = answer5.split("/");
        				var newAnswerLength = newAnswer.length;
        				if(questionType == "填空选择题"){
        					options[4].alt = newAnswer[newAnswerLength-2]+"/"+newAnswer[newAnswerLength-1];
        				}else{
        					options[4].value = newAnswer[newAnswerLength-2]+"/"+newAnswer[newAnswerLength-1];
        				}
        			}
        			if(answer6 != ""){
        				this.getId("answerSelect6").src = answer6;
        				$('#answerSelect6').attr('currSrc',answer6);
        				var newAnswer = answer6.split("/");
        				var newAnswerLength = newAnswer.length;
        				if(questionType == "填空选择题"){
        					options[5].alt = newAnswer[newAnswerLength-2]+"/"+newAnswer[newAnswerLength-1];
        				}else{
        					options[5].value = newAnswer[newAnswerLength-2]+"/"+newAnswer[newAnswerLength-1];
        				}
        			}
        			//调用截屏方法
        			blMet.upAnsloadImg();
        		}
    		}
    		
    		//初始化单选多选框选择状态
    		if(questionType == "单选题"){
    			if(realAnswer == answer1){
    				options[0].checked = true;
    			}
    			if(realAnswer == answer2){
    				options[1].checked = true;
    			}
    			if(realAnswer == answer3){
    				options[2].checked = true;
    			}
    			if(realAnswer == answer4){
    				options[3].checked = true;
    			}
    			if(realAnswer == answer5){
    				options[4].checked = true;
    			}
    			if(realAnswer == answer6){
    				options[5].checked = true;
    			}
    			
    		}else if(questionType == "填空选择题" || questionType == "多选题"){
    			var realAnswerArray = realAnswer.split(",");
    			for(var i = 0 ; i < realAnswerArray.length; i++){
    				for(var j = 0 ; j < options.length; j++){
    					if(questionType == "多选题"){
    						if(!swtichToDxFlag){
    							if(realAnswerArray[i] == options[j].value && options[j].value != ''){
        							result_answer += options[j].id + ',';
        							//result_answer_text += options[j].value + ",";
        							options[j].checked = true;
        							break;
        						}
    						}
    					}else if(questionType == "填空选择题"){
    						if(realAnswerArray[i] == options[j].alt && options[j].alt != ""){
    							result_answer_text += options[j].alt + ",";
        						result_answer += options[j].value + ",";
        						break;
        					}
    					}
    				}
    			}
    			if(questionType == "多选题"){
    				if(swtichToDxFlag){//填空选择->多选
    					$('#result_answer_new').html('暂未选择答案').removeClass('hasSel').addClass('noSel');
    				}else{
    					var newRealAns = result_answer.substring(0,result_answer.length - 1);
    					//一进来先不去除最后的逗号
            			$('#result_answer_new').html(newRealAns).removeClass('noSel').addClass('hasSel');
    				}
        			//$('#result_answer_new').html('暂未选择答案').removeClass('hasSel').addClass('noSel');
    			}else if(questionType == "填空选择题"){
    				if(swithToTkFlag){//从多选->填空选择
    					result_answer = '';
    					result_answer_text = '';
    					$('#result_answer_new_tk').html('暂未选择答案').removeClass('hasSel').addClass('noSel')
    				}else{//正常编辑
    					var newRealAns = result_answer.substring(0,result_answer.length - 1);
    					$('#result_answer_new_tk').html(newRealAns).removeClass('noSel').addClass('hasSel');
    				}
    				//$('#result_answer_new_tk').html(newRealAns).removeClass('noSel').addClass('hasSel');
    				//$('#result_answer_new_tk').html('暂未选择答案').removeClass('hasSel').addClass('noSel');
        			//重新选择几个空的时候是否有必要将当前的已选择的选项答案清空，假如起初是四个空的答案，当选择的是两个空的时候，是否要清空？
        			blMet.addItemTk();
        			blMet.clearAllAnswer();
    			}
    		}else if(questionType == "判断题"){
    			if(realAnswer == "对"){
    				options[0].checked = "checked";
    			}else{
    				options[1].checked = "checked";
    			}
    		}
    		form.render();
    	}
    };
    //输出接口
    exports('lorePractice', obj);
});
