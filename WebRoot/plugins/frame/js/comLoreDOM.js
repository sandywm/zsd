/**
 * @Description: 知识点管理 公共DOM结构 增加知识点题库 以及对题库编辑的公共DOM
 * @author: hlf
 */
//自定义模块
layui.define(['form','element','upLoadFiles','buffetLoreDOM','buffetLoreMet'],function(exports){
	var $ = layui.jquery,form=layui.form,element = layui.element,
		globalUpload=layui.upLoadFiles,blDOM = layui.buffetLoreDOM,
		blMet = layui.buffetLoreMet;
    var obj = {
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
    		if(currPage == 'lorePage'){
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
    		}
    		loreTopStr += '<div class="tiganTypeBox"></div>';
    		if(currPage == 'lorePage'){
    			loreTopStr += '<div class="lexTypeBox"></div>';
    		}
    		return loreTopStr;
    	},
    	//创建对应主要内容 
    	creaLoreConDOM : function(nowType){
    		var loreConStr = '';
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
    			//对应各种题型
    			loreConStr += '<div id="ansSelWrap_'+ nowType +'"  class="ansSelWrap layui-form-item"><label id="nowTxt_'+ nowType +'" class="layui-form-label"></label>';
    			loreConStr += '<div id="answerSelectDiv_'+ nowType +'" class="layui-input-block answerSelectDiv"></div>';
    			loreConStr += '<div id="wendaTypeWrap_'+ nowType +'" class="layui-input-block wendaTypeWrap"></div>';
    			loreConStr += '<div id="tkTypeWrap_'+ nowType +'" class="layui-input-block tkTypeWrap"></div>';
    			loreConStr += '</div>';
    			//解析
    			loreConStr += '<div class="layui-form-item"><label class="layui-form-label">解析：</label>';
    			loreConStr += '<div class="layui-input-block"><div id="conAnaly_'+ nowType +'_'+ currNum +'"></div></div></div>';
    			if(currPage == 'lorePage'){
    				//提示
        			loreConStr += '<div class="layui-form-item layui-form"><label class="layui-form-label">提示：</label>';
        			loreConStr += '<div class="layui-input-block selTipsBox">';
        			loreConStr += '<input type="hidden" id="tipsInp_'+ nowType +'" class="tipsInp"/>';
        			loreConStr += '<select id="selTipsSel_'+ nowType +'" lay-filter="selTipsSel"></select>';
        			loreConStr += '<div id="tipsCon_'+ nowType +'" class="tipsCon"></div>';
        			loreConStr += '</div></div>';
    			}
    		}
    		if(nowType == 'zhuti'){
    			loreConStr += '<p class="zhutiNote">注：如果不区分重点、难点、关键点、易混点，就把内容直接写入主题里面，重点、难点、关键点、易混点内容留空！</p>';
    		}else if(nowType == 'zsjj'){
    			loreConStr += '<p id="zsjjTips"></p><input id="zsjjInp" type="hidden"/>';
    		}
    		loreConStr += '</div>';
    		return loreConStr;
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
		//浏览知识点DOM
		createViewLoreDOM : function(){
			var strLore = '<div class="viewLoreWrap">';
			strLore += '<ul class="loreNav"><li class="active" currPos="zsqdTit">知识清单</li><li currPos="dbzdTit">点拨指导</li>';
			strLore += '<li currPos="jtsfTit">解题示范</li><li currPos="ggxlTit">巩固训练</li><li currPos="zdxzdTit">针对性诊断</li>';
			strLore += '<li currPos="zczdTit">再次诊断</li><li currPos="zsjjTit">知识讲解</li></ul>';
			strLore += '<div class="loreDetCon">';
			//知识清单
			strLore += '<div class="comLoreCon zsqdLore"><strong id="zsqdTit" class="titStrong">知识清单</strong><div id="zsqdLore"></div></div>';
			//点拨指导
			strLore += '<div class="comLoreCon dbzdLore"><strong id="dbzdTit" class="titStrong">点拨指导</strong><div id="dbzdLore"></div></div>';
			//解题示范
			strLore += '<div class="comLoreCon jtsfLore"><strong id="jtsfTit" class="titStrong">解题示范</strong><div id="jtsfLore"></div></div>';
			//巩固训练
			strLore += '<div class="comLoreCon ggxlLore"><strong id="ggxlTit" class="titStrong">巩固训练</strong><div id="ggxlLore"></div></div>';
			//针对性诊断
			strLore += '<div class="comLoreCon zdxzdLore"><strong id="zdxzdTit" class="titStrong">针对性诊断</strong><div id="zdxzdLore"></div></div>';
			//再次诊断
			strLore += '<div class="comLoreCon zczdLore"><strong id="zczdTit" class="titStrong">再次诊断</strong><div id="zczdLore"></div></div>';
			//知识讲解
			strLore += '<div class="comLoreCon zsjjLore"><strong id="zsjjTit" class="titStrong">知识讲解</strong><div id="zsjjLore"></div></div>';
			strLore += '</div></div>';
			return strLore;
		},
		//浏览知识点加载信息
		loadLoreDetail : function(){
			layer.load('1');
			var _this = this;
			$.ajax({
				type:'post',
			    dataType:'json',
			    data:{loreId:loreId},
			    url:'/lore.do?action=getLoreQuestionData',
			    success:function (json){
			    	layer.closeAll('loading');	
			    	if(json.result == 'success'){
			    		_this.renderZsqdInfo(json.zsqdList);
				    	_this.renderDbzdInfo(json.dbzdList);
				    	_this.renderJtsfInfo(json.jtsfList);
				    	_this.renderGgxlInfo(json.ggxlList);
				    	_this.renderZdxzdInfo(json.zdxList);
				    	_this.renderZczdInfo(json.zczdList);
				    	_this.renderZsjjInfo(json.zsjjList);
				    	blMet.goTarget();
			    	}else if(json.result == 'noInfo'){
			    		layer.msg('暂无此知识点的题库信息',{icon:5,anim:6,time:2000});
			    	}
			    	
			    }
			});
		},
		//知识讲解
		renderZsjjInfo : function(zsjjList){
			var zsjjStr = '';
			for(var i=0;i<zsjjList.length;i++){
				zsjjStr += '<div class="listLore">';
				zsjjStr += '<div class="con hasMargBot"><p class="titP">题干：</p><div>'+ zsjjList[i].queSub +'</div></div>';
				zsjjStr += '<div class="con"><p class="titP">视频：</p><div><img class="showVideoBtn" alt="'+ zsjjList[i].videoPath +'" src="Module/loreManager/images/video.jpg"/></div></div>';
				zsjjStr += '</div>';
			}
			$('#zsjjLore').html(zsjjStr);
			$('.showVideoBtn').on('click',function(){
				window.localStorage.removeItem("videoPath");
				var videoPath = $(this).attr('alt');
				window.localStorage.setItem("videoPath",videoPath);
				if(videoPath.indexOf('flv') > 0){
					window.top.layer.open({
						title:'视频文件播放窗口',
						type: 2,
					  	area: ['700px', '500px'],
					  	fixed: true, //不固定
					  	maxmin: false,
					  	shadeClose :false,
					  	content: '/Module/loreManager/jsp/playVideo.html',
					  	end : function(){
					  		window.localStorage.removeItem("videoPath");
					  	}
					});	
				}else{
					layer.msg('暂不支持该视频格式播放',{icon:5,anim:6,time:2000});
				}
				
			});
		},
		//再次诊断
		renderZczdInfo : function(zczdList){
			this.commonRenderInfo(zczdList,'zczdLore');
		},
		//针对性诊断
		renderZdxzdInfo : function(zdxList){
			this.commonRenderInfo(zdxList,'zdxzdLore');
		},
		//巩固训练
		renderGgxlInfo : function(ggxlList){
			this.commonRenderInfo(ggxlList,'ggxlLore');
		},
		//巩固训练 针对性诊断 再次诊公共方法
		commonRenderInfo : function(list,obj){
			if(list != null){
				var listStr = '';
				for(var i=0;i<list.length;i++){
					listStr += '<div class="listLore">';
					listStr += '<strong class="smTit">标题：'+ list[i].queTitle +'  <span class="queTypeTxt">'+ list[i].queType +'</span><span class="queType1">'+ list[i].queType2 +'</span></strong>';
					//题干
					listStr += '<div class="con"><p class="titP">题干：</p><div>'+ list[i].queSub +'</div></div>';
					//选项
					listStr += '<div class="conSelOpt">';
						if(list[i].answerA != ''){
							listStr += '<div class="comOpt layui-clear"><span class="queSelWord">A：</span>';
								if(blMet.checkAnswerImg(list[i].answerA)){
									listStr += '<p><img src="'+ list[i].answerA +'"/></p>';
								}else{
									listStr += '<p>'+ list[i].answerA +'</p>';
								}
							listStr += '</div>';
						}
						if(list[i].answerB != ''){
							listStr += '<div class="comOpt layui-clear"><span class="queSelWord">B：</span>';
								if(blMet.checkAnswerImg(list[i].answerB)){
									listStr += '<p><img src="'+ list[i].answerB +'"/></p>';
								}else{
									listStr += '<p>'+ list[i].answerB +'</p>';
								}
							listStr += '</div>';
						}
						if(list[i].answerC != ''){
							listStr += '<div class="comOpt layui-clear"><span class="queSelWord">C：</span>';
								if(blMet.checkAnswerImg(list[i].answerC)){
									listStr += '<p><img src="'+ list[i].answerC +'"/></p>';
								}else{
									listStr += '<p>'+ list[i].answerC +'</p>';
								}
							listStr += '</div>';
						}
						if(list[i].answerD != ''){
							listStr += '<div class="comOpt layui-clear"><span class="queSelWord">D：</span>';
								if(blMet.checkAnswerImg(list[i].answerD)){
									listStr += '<p><img src="'+ list[i].answerD +'"/></p>';
								}else{
									listStr += '<p>'+ list[i].answerD +'</p>';
								}
							listStr += '</div>';
						}
						if(list[i].answerE != ''){
							listStr += '<div class="comOpt layui-clear"><span class="queSelWord">E：</span>';
								if(blMet.checkAnswerImg(list[i].answerE)){
									listStr += '<p><img src="'+ list[i].answerE +'"/></p>';
								}else{
									listStr += '<p>'+ list[i].answerE +'</p>';
								}
							listStr += '</div>';
						}
						if(list[i].answerF != ''){
							listStr += '<div class="comOpt layui-clear"><span class="queSelWord">F：</span>';
								if(blMet.checkAnswerImg(list[i].answerF)){
									listStr += '<p><img src= "'+ list[i].answerF +'"/></p>';
								}else{
									listStr += '<p>'+ list[i].answerF +'</p>';
								}
							listStr += '</div>';
						}
					listStr += '</div>';
					//答案
					listStr += '<div class="conAns"><span class="ansTit">答案：</span>';
					listStr += '<span class="ansTxtSpan">'+ list[i].queAnswer +'</span>';
					listStr += '</div>';
					//解析
					if(list[i].queResolution != ''){
						listStr += '<div class="con hasMargTop"><p class="titP">解析：</p><div>'+ list[i].queResolution +'</div></div>';
					}
					//关联此条
					if(list[i].lexTitle != '' && list[i].lexTitle != undefined){
						listStr += '<div class="queTips"><p class="titP">关联词条：</p><p class="tipsConView">'+ list[i].lexTitle +'</p></div>';
					}
					//提示
					if(list[i].queTipTitle != '' && list[i].queTipTitle != undefined){
						listStr += '<div class="queTips"><p class="titP">提示：</p><p class="tipsConView">'+ list[i].queTipTitle +'</p></div>';
					}
					listStr += '</div>';
				}
				$('#'+obj).html(listStr);
			}
		},
		getRootPath : function(){
			//获取当前网址，如： http://localhost:8088/test/test.jsp
		    var curPath = window.document.location.href;
		    //获取主机地址之后的目录，如： test/test.jsp
		    var pathName = window.document.location.pathname;
		    var pos = curPath.indexOf(pathName);
		    //获取主机地址，如： http://localhost:8088
		    var localhostPath = curPath.substring(0, pos);
		    //获取带"/"的项目名，如：/test
		    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
		    return (localhostPath + '/');//发布前用此
		},
		//解题示范
		renderJtsfInfo : function(jtsfList){
			var jtsfStr = '';
			for(var i=0;i<jtsfList.length;i++){
				jtsfStr += '<div class="listLore">';
				jtsfStr += '<div class="con hasMargBot"><strong class="queTit">题干：</strong><div>'+ jtsfList[i].queSub +'</div></div>';
				jtsfStr += '<div class="con hasMargBot"><strong class="queTit">答案：</strong><div>'+ jtsfList[i].queAnswer +'</div></div>';
				jtsfStr += '<div class="con"><strong class="queTit">解析：</strong><div>'+ jtsfList[i].queResolution +'</div></div>';
				jtsfStr += '</div>';
			}
			$('#jtsfLore').html(jtsfStr);
		},
		//点拨指导
		renderDbzdInfo : function(dbzdList){
			var dbzdStr = '';
			for(var i=0;i<dbzdList.length;i++){
				if(i == 0){
					dbzdStr += '<div class="smModPart zhutiPart"><strong class="dbzdSmTit"><img src="../images/lubiao.png"/>';
					dbzdStr += '<span>'+ dbzdList[i].lqsType +'</span></strong>';
					dbzdStr += '<div class="listLore"><strong class="smTit">标题：'+ dbzdList[i].lqsTitle.replace("<","&lt") +'</strong>';
					dbzdStr += '<div class="con"><p class="titP">内容：</p><div>'+ dbzdList[i].lqsCon +'</div></div>';
					
				}else{
					if(dbzdList[i-1].lqsType == dbzdList[i].lqsType){
						dbzdStr += '<div class="listLore"><strong class="smTit">标题：'+ dbzdList[i].lqsTitle.replace("<","&lt") +'</strong>';
						dbzdStr += '<div class="con"><p class="titP">内容：</p><div>'+ dbzdList[i].lqsCon +'</div></div>';
					}else{
						dbzdStr += '<div class="smModPart zhutiPart"><strong class="dbzdSmTit"><img src="../images/lubiao.png"/>';
						dbzdStr += '<span>'+ dbzdList[i].lqsType +'</span></strong>';
						dbzdStr += '<div class="listLore"><strong class="smTit">标题：'+ dbzdList[i].lqsTitle.replace("<","&lt") +'</strong>';
						dbzdStr += '<div class="con"><p class="titP">内容：</p><div>'+ dbzdList[i].lqsCon +'</div></div>';
					}
				}
				dbzdStr += '</div>';
			}
			$('#dbzdLore').html(dbzdStr);
		},
		//知识清单
		renderZsqdInfo : function(zsqdList){
			var zsqdStr = '';
			for(var i=0;i<zsqdList.length;i++){
				zsqdStr += '<div class="listLore"><strong class="smTit">标题：'+ zsqdList[i].lqsTitle +'</strong>';
				zsqdStr += '<div class="con"><p class="titP">内容：</p><div>'+ zsqdList[i].lqsCon +'</div></div></div>';
			}
			$('#zsqdLore').html(zsqdStr);
		}
    };
    //基础题型的切换
    form.on('radio(qusTypeFilter)', function(data){
		var value = data.value;
		var tiganTypeDOM = blDOM.createTiganType(),
			lexStrDOM = blDOM.createLexDOM($(this).attr('inpType')),
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
			maxQueNum = blMet.getCurrMaxQueNum(loreTypeZHN,loreBigId);
		}
		//添加题干类型
		if(tmpLoreType == 'ggxl' || tmpLoreType == 'zdxzd' || tmpLoreType == 'zczd' ){//巩固训练 针对性诊断 再次诊断增加题干类型以及了解...
			$('.tiganTypeBox').show().html(tiganTypeDOM);
			$('.lexTypeBox').show().html(lexStrDOM);
			$('.ansSelWrap').hide();
			$('.answerSelectDiv').hide().html('');
			$('#nowTxtLabel').html('');
			$('.wendaTypeWrap').hide().html('');
			blMet.getCurrLoreTips(loreBigId,true);//获取提示列表
			//添加编辑词条
			blMet.addEditLex();
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
    //输出接口
    exports('comLoreDOM', obj);
});
