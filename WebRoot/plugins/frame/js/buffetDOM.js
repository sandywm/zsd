/**
 * @Description:自助餐公共DOM
 * @author: hlf
 */
//自定义模块
layui.define(['form','buffetLoreMet'],function(exports){
	var $ = layui.jquery,form=layui.form,blMet = layui.buffetLoreMet;
    var obj = {
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
    	//创建自助餐基础类型
    	createBaseTypeDOM : function(){
    		var baseStr = '';
    		baseStr += '<div class="typeBox layui-clear" style="margin-bottom:0;"><span style="float:left;">基础类型：</span>';
    		baseStr += '<input type="hidden" id="baseTypeInp"/>';
    		baseStr += '<div class="layui-form" style="float:left;width:92%;">';
    		if(globalOpts == 'add'){
    			baseStr += '<input type="radio" name="baseType" lay-filter="baseTypeFilter" value="1" title="兴趣激发"><input type="radio" lay-filter="baseTypeFilter" name="baseType" value="2" title="方法归纳">';
        		baseStr += '<input type="radio" name="baseType" lay-filter="baseTypeFilter" value="3" title="思维训练"><input type="radio" lay-filter="baseTypeFilter" name="baseType" value="4" title="智力开发">';
        		baseStr += '<input type="radio" name="baseType" lay-filter="baseTypeFilter" value="5" title="能力培养"><input type="radio" lay-filter="baseTypeFilter" name="baseType" value="6" title="中/高考涉猎 ">';
    		}else{
    			baseStr += '<p id="baseTypeTxt"></p>';
    		}
    		
    		baseStr += '</div></div>';
    		return baseStr;
    	},
    	//创建思维类型DOM
    	createMindTypeDOM : function(){
    		var mindStr = '';
    		mindStr += '<div class="typeBox layui-clear" style="margin-bottom:0;"><span style="float:left;">思维类型：</span>';
    		mindStr += '<div class="layui-form" style="float:left;width:92%;">';
    		mindStr += '<input type="checkbox" name="mindType" lay-filter="mindTypeFilter" value="1" title="定向思维" lay-skin="primary"><input type="checkbox" name="mindType" lay-filter="mindTypeFilter" value="2" title="发散思维" lay-skin="primary">';
    		mindStr += '<input type="checkbox" name="mindType" lay-filter="mindTypeFilter" value="3" title="逆向思维" lay-skin="primary"><input type="checkbox" name="mindType" lay-filter="mindTypeFilter" value="4" title="抽象思维" lay-skin="primary">';
    		mindStr += '<input type="checkbox" name="mindType" lay-filter="mindTypeFilter" value="5" title="类比思维" lay-skin="primary"><input type="checkbox" name="mindType" lay-filter="mindTypeFilter" value="6" title="移植思维" lay-skin="primary">';
    		mindStr += '<input type="checkbox" name="mindType" lay-filter="mindTypeFilter" value="7" title="形象思维" lay-skin="primary"><input type="checkbox" name="mindType" lay-filter="mindTypeFilter" value="8" title="联想思维" lay-skin="primary">';
    		mindStr += '</div></div>';
    		return mindStr;
    	},
    	//创建能力类型DOM
    	createAbilityTypeDOM : function(){
    		var abilityStr = '';
    		abilityStr += '<div class="typeBox layui-clear" style="margin-bottom:0;"><span style="float:left;">能力类型：</span>';
    		abilityStr += '<div class="layui-form" style="float:left;width:92%;">';
    		abilityStr += '<input type="checkbox" lay-filter="abilityTypeFilter" name="abilityType" value="1" title="理解能力" lay-skin="primary"><input type="checkbox" name="abilityType" lay-filter="abilityTypeFilter" value="2" title="分析能力" lay-skin="primary">';
    		abilityStr += '<input type="checkbox" lay-filter="abilityTypeFilter" name="abilityType" value="3" title="表达能力" lay-skin="primary"><input type="checkbox" name="abilityType" lay-filter="abilityTypeFilter" value="4" title="实践能力" lay-skin="primary">';
    		abilityStr += '<input type="checkbox" lay-filter="abilityTypeFilter" name="abilityType" value="5" title="质疑能力" lay-skin="primary"><input type="checkbox" name="abilityType" lay-filter="abilityTypeFilter" value="6" title="联想能力" lay-skin="primary">';
    		abilityStr += '<input type="checkbox" lay-filter="abilityTypeFilter" name="abilityType" value="7" title="综合能力" lay-skin="primary"><input type="checkbox" name="abilityType" lay-filter="abilityTypeFilter" value="8" title="创造能力" lay-skin="primary">';
    		abilityStr += '</div></div>';
    		return abilityStr;
    	},
    	//创建自助餐内容
    	createBuffetCon : function(nowType){
    		var bfCon = '';
    		bfCon += '<div id="queCon_'+ nowType +'_'+ currNum +'" class="typeCon">';
    		//标题
    		bfCon += '<div class="layui-form-item"><label class="layui-form-label">标题：</label>';
    		bfCon += '<div class="layui-input-block">';
    		bfCon += '<input id="'+ nowType +'Inp_'+ currNum +'" disabled type="text" class="layui-input '+ nowType +'_Inp" value=""/></div></div>';
    		//题干
    		bfCon += '<div class="layui-form-item"><label class="layui-form-label">题干：</label>';
    		bfCon += '<div class="layui-input-block"><div id="con_'+ nowType +'_'+ currNum +'"></div></div></div>';
    		//对应各种题型
    		bfCon += '<div id="ansSelWrap_'+ nowType +'"  class="ansSelWrap layui-form-item"><label id="nowTxt_'+ nowType +'" class="layui-form-label"></label>';
    		bfCon += '<div id="answerSelectDiv_'+ nowType +'" class="layui-input-block answerSelectDiv"></div>';
    		bfCon += '<div id="wendaTypeWrap_'+ nowType +'" class="layui-input-block wendaTypeWrap"></div>';
    		bfCon += '<div id="tkTypeWrap_'+ nowType +'" class="layui-input-block tkTypeWrap"></div>';
    		bfCon += '</div>';
    		//解析
    		bfCon += '<div class="layui-form-item"><label class="layui-form-label">解析：</label>';
    		bfCon += '<div class="layui-input-block"><div id="conAnaly_'+ nowType +'_'+ currNum +'"></div></div></div>';
    		if(currPage == 'buffetPage'){
    			//提示
        		bfCon += '<div class="layui-form-item layui-form"><label class="layui-form-label">提示：</label>';
        		bfCon += '<div class="layui-input-block selTipsBox">';
        		bfCon += '<input type="hidden" id="tipsInp_'+ nowType +'" class="tipsInp"/>';
        		bfCon += '<select id="selTipsSel_'+ nowType +'" lay-filter="selTipsSel"></select>';
        		bfCon += '<div id="tipsCon_'+ nowType +'" class="tipsCon"></div>';
        		bfCon += '</div></div>';
    		}
    		bfCon += '</div>';
    		return bfCon;
    	},
    	//浏览知识点
    	createViewLoreDOM : function(){
    		var viewStr = '';
    		viewStr += '<div class="viewLoreWrap">';
    		viewStr += '<ul class="loreNav buffetNav"><li class="active" currPos="xqjfTit">兴趣激发</li><li currPos="ffgnTit">方法归纳</li>';
    		viewStr += '<li currPos="swxlTit">思维训练</li><li currPos="zlkfTit">智力开发</li><li currPos="nlpyTit">能力培养</li>';
    		viewStr += '<li currPos="zgkslTit">中/高考涉猎</li></ul>';
    		viewStr += '<div class="loreDetCon">';
			//兴趣激发
    		viewStr += '<div class="comLoreCon xqjfLore"><strong id="xqjfTit" class="titStrong">兴趣激发</strong><div id="xqjfLore"></div></div>';
			//方法归纳
			viewStr += '<div class="comLoreCon ffgnLore"><strong id="ffgnTit" class="titStrong">方法归纳</strong><div id="ffgnLore"></div></div>';
			//思维训练
			viewStr += '<div class="comLoreCon swxlLore"><strong id="swxlTit" class="titStrong">思维训练</strong><div id="swxlLore"></div></div>';
			//智力开发
			viewStr += '<div class="comLoreCon zlkfLore"><strong id="zlkfTit" class="titStrong">智力开发</strong><div id="zlkfLore"></div></div>';
			//能力培养
			viewStr += '<div class="comLoreCon nlpyLore"><strong id="nlpyTit" class="titStrong">能力培养</strong><div id="nlpyLore"></div></div>';
			//中/高考涉猎
			viewStr += '<div class="comLoreCon zgkslLore"><strong id="zgkslTit" class="titStrong">中/高考涉猎</strong><div id="zgkslLore"></div></div>';
			viewStr += '</div></div>';
    		return viewStr;
    	},
    	loadLoreDetail : function(loreId){
    		layer.load('1');
    		var _this = this;
    		if(currPage == 'buffetPage'){
    			var url = '/buffet.do?action=getBuffetQueData';
    		}else if(currPage == 'sysHwPage'){
    			var url = '/hw.do?action=getHwDetailData';
    		}
			$.ajax({
				type:'post',
			    dataType:'json',
			    data:{loreId:loreId},
			    url:url,
			    success:function (json){
			    	layer.closeAll('loading');	
			    	if(json.result == 'success'){
			    		_this.renderXqjfInfo(json.xqjf);
				    	_this.renderFfgnInfo(json.ffgn);
				    	_this.renderSwxlInfo(json.swxl);
				    	_this.renderZlkfInfo(json.zlkf);
				    	_this.renderNlpyInfo(json.nlpy);
				    	_this.renderZgkslInfo(json.zksl);
				    	blMet.goTarget();
			    	}else if(json.result == 'noInfo'){
			    		if(currPage == 'buffetPage'){
			    			layer.msg('暂无此知识点的巴菲特题库信息',{time:1500});
			    		}else if(currPage == 'sysHwPage'){
			    			layer.msg('暂无此知识点的家庭作业',{time:1500});
			    		}
			    	}
			    	
			    }
			});
    	},
    	//兴趣激发
    	renderXqjfInfo : function(list){
    		this.commonRenderInfo(list, 'xqjfLore');
    	},
    	//方法归纳
    	renderFfgnInfo : function(list){
    		this.commonRenderInfo(list, 'ffgnLore');
    	},
    	//思维训练
    	renderSwxlInfo : function(list){
    		this.commonRenderInfo(list, 'swxlLore');
    	},
    	//智力开发
    	renderZlkfInfo : function(list){
    		this.commonRenderInfo(list, 'zlkfLore');
    	},
    	//能力培养
    	renderNlpyInfo : function(list){
    		this.commonRenderInfo(list, 'nlpyLore');
    	},
    	//中/考高涉猎
    	renderZgkslInfo : function(list){
    		this.commonRenderInfo(list, 'zgkslLore');
    	},
    	commonRenderInfo : function(list,obj){
    		if(list != null){
				var listStr = '';
				for(var i=0;i<list.length;i++){
					listStr += '<div class="listLore">';
					if(currPage == 'buffetPage'){
						//标题
						listStr += '<strong class="smTit">标题：'+ list[i].bqTitle +'  <span class="queTypeTxt">'+ list[i].bqType +'</span></strong>';
					}else{
						//标题
						listStr += '<strong class="smTit">标题：'+ list[i].hqTitle +'  <span class="queTypeTxt">'+ list[i].hqType +'</span></strong>';
					}
					//思维类型
					listStr += '<strong class="smTit">思维类型：<span class="queTypeTxt">'+ list[i].swType +'</span></strong>';
					//能力类型
					listStr += '<strong class="smTit">能力类型：<span class="queTypeTxt">'+ list[i].nlType +'</span></strong>';
					//题干
					listStr += '<div class="con"><p class="titP">题干：</p><div>'+ list[i].subject +'</div></div>';
					if(currPage == 'buffetPage'){
						//选项
						listStr += '<div class="conSelOpt">';
							if(list[i].answerA != ''){
								listStr += '<div class="comOpt layui-clear"><span class="queSelWord">A：</span>';
									if(blMet.checkAnswerImg(list[i].answerA)){
										listStr += '<p><img src="'+ list[i].answerA +'"/></p>';
									}else{
										listStr += '<p>'+ list[i].answerA.replace("<","&lt") +'</p>';
									}
								listStr += '</div>';
							}
							if(list[i].answerB != ''){
								listStr += '<div class="comOpt layui-clear"><span class="queSelWord">B：</span>';
									if(blMet.checkAnswerImg(list[i].answerB)){
										listStr += '<p><img src="'+ list[i].answerB +'"/></p>';
									}else{
										listStr += '<p>'+ list[i].answerB.replace("<","&lt") +'</p>';
									}
								listStr += '</div>';
							}
							if(list[i].answerC != ''){
								listStr += '<div class="comOpt layui-clear"><span class="queSelWord">C：</span>';
									if(blMet.checkAnswerImg(list[i].answerC)){
										listStr += '<p><img src="'+ list[i].answerC +'"/></p>';
									}else{
										listStr += '<p>'+ list[i].answerC.replace("<","&lt") +'</p>';
									}
								listStr += '</div>';
							}
							if(list[i].answerD != ''){
								listStr += '<div class="comOpt layui-clear"><span class="queSelWord">D：</span>';
									if(blMet.checkAnswerImg(list[i].answerD)){
										listStr += '<p><img src="'+ list[i].answerD +'"/></p>';
									}else{
										listStr += '<p>'+ list[i].answerD.replace("<","&lt") +'</p>';
									}
								listStr += '</div>';
							}
							if(list[i].answerE != ''){
								listStr += '<div class="comOpt layui-clear"><span class="queSelWord">E：</span>';
									if(blMet.checkAnswerImg(list[i].answerE)){
										listStr += '<p><img src="'+ list[i].answerE +'"/></p>';
									}else{
										listStr += '<p>'+ list[i].answerE.replace("<","&lt") +'</p>';
									}
								listStr += '</div>';
							}
							if(list[i].answerF != ''){
								listStr += '<div class="comOpt layui-clear"><span class="queSelWord">F：</span>';
									if(blMet.checkAnswerImg(list[i].answerF)){
										listStr += '<p><img src= "'+ list[i].answerF +'"/></p>';
									}else{
										listStr += '<p>'+ list[i].answerF.replace("<","&lt") +'</p>';
									}
								listStr += '</div>';
							}
						listStr += '</div>';
					}
					//答案
					listStr += '<div class="conAns"><span class="ansTit">答案：</span>';
					if(currPage == 'buffetPage'){
						listStr += '<span class="ansTxtSpan">'+ list[i].queAnswer +'</span>';
					}else{
						listStr += '<span class="ansTxtSpan">'+ list[i].hqAnswer +'</span>';
					}
					listStr += '</div>';
					if(currPage == 'sysHwPage'){
						//解析
						if(list[i].hqResolution != ''){
							listStr += '<div class="con hasMargTop"><p class="titP">解析：</p><div>'+ list[i].hqResolution +'</div></div>';
						}else{
							listStr += '<div class="con hasMargTop"><p class="titP">解析：</p><div class="noAnayTxt">暂无解析！</div></div>';
						}
					}else{
						//解析
						if(list[i].queResolution != ''){
							listStr += '<div class="con hasMargTop"><p class="titP">解析：</p><div>'+ list[i].queResolution +'</div></div>';
						}else{
							listStr += '<div class="con hasMargTop"><p class="titP">解析：</p><div class="noAnayTxt">暂无解析！</div></div>';
						}
						//关联此条
						if(list[i].lexTitle != '' && list[i].lexTitle != undefined){
							listStr += '<div class="queTips"><p class="titP">关联词条：</p><p class="tipsConView">'+ list[i].lexTitle +'</p></div>';
						}
						//提示
						if(list[i].queTipTitle != '' && list[i].queTipTitle != undefined){
							listStr += '<div class="queTips"><p class="titP">提示：</p><p class="tipsConView">'+ list[i].queTipTitle +'</p></div>';
						}
					}
					listStr += '</div>';
				}
				$('#'+obj).html(listStr);
			}
    	}
    };
    form.on('radio(baseTypeFilter)',function(data){
    	var value = data.value,tit = $(this).attr('title'),url = '';
    	if(currPage == 'buffetPage'){
    		url = '/buffet.do?action=getCurrMaxNum';
    	}else if(currPage == 'sysHwPage'){
    		url = '/hw.do?action=getCurrMaxNum';
    	}
    	$('#baseTypeInp').val(value);
    	layer.load('1');
    	$.ajax({
			type:'post',
	        dataType:'json',
	        data:{loreId:loreBigId,btId:value},
	        async:false,
	        url:url,
	        success:function (json){
	        	layer.closeAll('loading');	
	        	$('#zzcInp_0').val(tit + '第' + json.currNum + '题');
	        }
		});
    });
    //输出接口
    exports('buffetDOM', obj);
});
