/**
 * @Description: 知识点管理 关联知识点管理 自助餐管理下通用关联知识点方法
 * @author: hlf
 */
//自定义模块
layui.define(['table','form','scrollBar'],function(exports){
	var comScrollBar=layui.scrollBar,
		table=layui.table,form=layui.form;
    var obj = { 
    	//关联知识点下获取小知识点列表
    	renderLoreSmList : function(loreList){
    		var loreStr= '';
    		for(var i=0;i<loreList.length;i++){
    			loreStr += '<li>';
    			loreStr += '<p class="oneWid">'+ loreList[i].gradeName +'</p>';
    			loreStr += '<p class="oneWid">'+ loreList[i].subName +'</p>';
    			loreStr += '<p class="twoWid ellip" title="'+ loreList[i].cptName +'">'+ loreList[i].cptName +'</p>';
    			loreStr += '<p class="twoWid ellip" title="'+ loreList[i].loreName +'">'+ loreList[i].loreName +'</p>';
    			loreStr += '<p class="oneWid">'+ loreList[i].inUse +'</p>';
    			loreStr += '<p class="oneWid"><a class="addRelateBtn" rootLoreId="'+ loreList[i].loreId +'" href="javascript:void(0)">添加</a></p>';
    			loreStr += '</li>';
    		}
    		$('#parScroll_1').remove();
    		$('#innerSearResUl').html(loreStr);
    		$('#innerSearResUl li:odd').addClass('oddBgColor');
    		this.addLoreRelate();
    		this.actCalCreateScroll('searResBox', 'innerSearResUl',1);
    	},
    	//公共返回方法
    	comBackFun : function(){
    		if(currPage == 'lorePage'){
    			$('.queType').hide().html('');
    			$('.quesAddEditBox').hide();
    			$('.addLoreCon').hide();
    			$('.comQuesBox').html('').hide();
    			$('.guideWrap').hide();
    			$('.guideBox').html('').hide();
    			$('.guideNavLi').attr('iscreaFlag','0');
    			$('.guideNavLi').removeClass('layui-this').eq(0).addClass('layui-this');
    			$('.guideBox').removeClass('layui-show').eq(0).addClass('layui-show');
    			$('.addLoreCon').hide();
    			tmpGuideType = '';
    			loreType = 'zsqd';
    			loreTypeZHN = '知识清单';
    			smLoreTypeZHN = '主题';
    			result_answer = '';
    			result_answer_text = '';
    			isAddClickFlag = false;
    			currNum = 0;//每次返回重置currNum 便于回显的一个匹配
    		}else if(currPage == 'buffetPage' || currPage == 'sysHwPage' || currPage == 'teaHwPage'){
    			$('.queType').hide().html('');
    			$('.quesAddEditBox').hide();
    			$('.comQuesBox').html('').hide();
    		}
    	},
    	//添加关联知识点
    	addLoreRelate : function(){
    		var _this=this;
    		$('.addRelateBtn').on('click',function(){
    			if(parent.currPage == 'lorePage' || parent.currPage=='relateManager'){
    				var rootLoreId = $(this).attr('rootLoreId'),
    					editInpVal = $('#editInp').val();
	    			if(loreId == rootLoreId){
	    				layer.msg('当前知识点和需要关联的知识点相同，请重新选择',{icon:5,anim:6,time:2000});
	    				return;
	    			}
	    			layer.load('1');
	    			$.ajax({
	    				type:'post',
	    		        dataType:'json',
	    		        data:{loreId:loreId,rootLoreId:rootLoreId},
	    		        url:'/loreRelate.do?action=addRelate',
	    		        success:function (json){
	    		        	layer.closeAll('loading');
	    		        	if(json['result'] == 'success'){
	    		        		//执行增加动作
	    		        		_this.addRelateLoreAction(loreId,editInpVal);
	    		        	}else if(json['result'] == 'existInfo'){
	    		        		layer.msg('当前知识点已被关联，请重新选择',{icon:5,anim:6,time:2200});
	    		        	}else if(json['result'] == 'error'){
	    		        		layer.msg('系统错误，请稍后重试',{icon:5,anim:6,time:1000});
	    		        	}
	    		        }
	    			});
    			}else if(currPage == 'lexRelatePage'){
    				var rootLoreId = $(this).attr('rootLoreId');
    				layer.load('1');
	    			$.ajax({
	    				type:'post',
	    		        dataType:'json',
	    		        data:{loreId:rootLoreId,lexId:lexId},
	    		        url:'/lex.do?action=addLLR',
	    		        success:function (json){
	    		        	layer.closeAll('loading');
	    		        	if(json['result'] == 'success'){
	    		        		//执行增加动作
	    		        		_this.addLexRelateLoreAction(lexId);
	    		        	}else if(json['result'] == 'existInfo'){
	    		        		layer.msg('当前知识点已被关联，请重新选择',{icon:5,anim:6,time:2000});
	    		        	}
	    		        }
	    			});
    			}else if(parent.currPage == 'buffetPage'){//自助餐关联知识点增加
    				layer.load('1');
    				var rootLoreId = $(this).attr('rootLoreId');
	    			$.ajax({
	    				type:'post',
	    		        dataType:'json',
	    		        data:{opt:'add',loreId:rootLoreId,buffetId:parent.bigBuffetId},
	    		        url:'/blr.do?action=addRelateLore',
	    		        success:function (json){
	    		        	layer.closeAll('loading');
	    		        	if(json['result'] == 'success'){
	    		        		//执行增加动作
	    		        		_this.addBfRelateLoreAction(parent.bigBuffetId);
	    		        	}else if(json['result'] == 'exist'){
	    		        		layer.msg('当前知识点已被关联，请重新选择',{icon:5,anim:6,time:2000});
	    		        	}else if(json['result'] == 'error'){
	    		        		layer.msg('添加失败，请稍后重试',{icon:5,anim:6,time:2000});
	    		        	}
	    		        }
	    			});
    			}
    			
    		});
    	},
    	//词库关联知识点添加至右下侧列表
    	addLexRelateLoreAction : function(lexId){
    		var _this = this;
    		$.ajax({
				type:'post',
		        dataType:'json',
		        data:{lexId:lexId},
		        url:'/lex.do?action=getLexDetail',
		        success:function (json){
		        	layer.closeAll('loading');
		        	if(json['result'] == 'success'){
		        		var lrList = json.loreList;
		        		_this.renderHasAddRelationRes(lrList);
		        	}else if(json['result'] == 'noInfo'){
		        		$('#hasAddResUl').html('<p class="noDataTxt">暂无关联知识点</p>');
		        		//layer.msg('暂未获取到当前已关联知识点信息，请稍后重试',{icon:5,anim:6,time:1500});
		        	}
		        }
			});
    	},
    	//自助餐关联知识点添加至右下侧列表
    	addBfRelateLoreAction : function(buffetId){
    		var _this = this;
    		$.ajax({
				type:'post',
		        dataType:'json',
		        data:{buffetId:buffetId},
		        async:false,
		        url:'/blr.do?action=getRelateLoreInfo',
		        success:function (json){
		        	layer.closeAll('loading');
		        	window.localStorage.removeItem("relateObj");
	        		window.localStorage.setItem("relateObj",JSON.stringify(json));
		        	if(json['result'] == 'success'){
		        		var lrList = json.loreList;
		        		_this.renderHasAddRelationRes(lrList);
		        	}else if(json['result'] == 'noInfo'){
		        		//layer.msg('暂未获取到当前已关联知识点信息');
		        		$('#hasAddResUl').html('<p class="noDataTxt">暂无关联知识点</p>');
		        	}
		        }
			});
    	},
    	//知识点关联知识点添加至右下侧
    	addRelateLoreAction : function(loreId,editInpVal){
    		var field = null,_this = this;
    		if(editInpVal == 1){//通用版 不传升序和降序
    			field = {loreId:loreId};
    		}else{
    			field = {loreId:loreId,orderOpt:'desc'}; 
    		}
    		$.ajax({
				type:'post',
		        dataType:'json',
		        data:field,
		        async:false,
		        url:'/loreRelate.do?action=showRelationList',
		        success:function (json){
		        	window.localStorage.removeItem("relateObj");
	        		window.localStorage.setItem("relateObj",JSON.stringify(json));
		        	if(json['result'] == 'success'){
		        		var lrList = json.lrList != undefined ? json.lrList : [];
		        		_this.renderHasAddRelationRes(lrList);
		        	}else if(json['result'] == 'noInfo'){
		        		//layer.msg('暂未获取到当前已关联知识点信息，请稍后重试',{icon:5,anim:6,time:1500});
		        		$('#hasAddResUl').html('<p class="noDataTxt">暂无关联知识点</p>');
		        	}
		        }
			});
    	},
    	//显示当前已增加的关联知识点
    	renderHasAddRelationRes : function(lrList){
    		var lrStr = '';
    		if(lrList.length > 0){
    			if(parent.currPage == 'lorePage' || parent.currPage == 'relateManager'){
    				for(var i=0;i<lrList.length;i++){
            			lrStr += '<li>';
            			if(parent.currPage == 'relateManager'){//关联知识点下通用版关联生成关联其他版本结果
            				if(lrList[i].relateStatus == -1){//暂无生成其他版本的关联知识点
                				lrStr += '<p class="widOne ellip">'+ lrList[i].rootLoreName +'</p>';
                			}else if(lrList[i].relateStatus == 0){//未全部正确关联
                				lrStr += '<p class="widOne_spec ellip">'+ lrList[i].rootLoreName +'</p>';
                				lrStr += '<p class="widTwo"><span title="未全部正确关联" lrId="'+ lrList[i].lrId +'" class="viewRelateRes wrongRes"></span></p>';
                			}else{//全部正确关联 1
                				lrStr += '<p class="widOne_spec ellip">'+ lrList[i].rootLoreName +'</p>';
                				lrStr += '<p class="widTwo"><span title="已全部正确关联" lrId="'+ lrList[i].lrId +'" class="viewRelateRes succRes"></span></p>';
                			}
            			}else{
            				lrStr += '<p class="widOne ellip">'+ lrList[i].rootLoreName +'</p>';
            			}
            			lrStr += '<p class="widTwo"><i class="delRelLoreBtn layui-icon layui-icon-delete" lrId="'+ lrList[i].lrId +'"></i></p>';
            			lrStr += '</li>';
            		}
        		}else if(currPage == 'lexRelatePage'){
        			for(var i=0;i<lrList.length;i++){
        				lrStr += '<li>';
            			lrStr += '<p class="widOne ellip">'+ lrList[i].loreName +'</p>';
            			lrStr += '<p class="widTwo"><i class="delRelLoreBtn layui-icon layui-icon-delete" lrId="'+ lrList[i].llrId +'"></i></p>';
            			lrStr += '</li>';
        			}
        		}else if(parent.currPage == 'buffetPage'){
        			for(var i=0;i<lrList.length;i++){
        				lrStr += '<li>';
            			lrStr += '<p class="widOne ellip">'+ lrList[i].relateLoreName +'</p>';
            			lrStr += '<p class="widTwo"><i class="delRelLoreBtn layui-icon layui-icon-delete" lrId="'+ lrList[i].blrId +'"></i></p>';
            			lrStr += '</li>';
        			}
        		}
        		$('#hasAddResUl').html(lrStr);
        		$('#hasAddResUl li:odd').addClass('oddBgColor');
        		$('#parScroll_2').remove();
        		this.actCalCreateScroll('hasAddRes', 'hasAddResUl',2);
        		this.delHasAddRelLore();//加载删除方法
        		if(parent.currPage == 'relateManager'){
        			this.showOtherRealteRes();
        		}
    		}else{
    			$('#hasAddResUl').html('<p class="noDataTxt">暂无关联知识点</p>');
    		}
    	},
    	createRealteList : function(list){
			var str = '';
			str += '<div class="relateInfo"><ul>';
			for(var i=0;i<list.length;i++){
				if(list[i].resultStatus == 0){
					str += '<li class="failTips">'+ list[i].relateResult +'</li>';
				}else if(list[i].resultStatus == 1){
					str += '<li class="succTips">'+ list[i].relateResult +'</li>';
				}
			}
			str += '</ul></div>';
			return str;
		},
    	//关联知识点模块下查看关联其他版结果
    	showOtherRealteRes : function(){
    		var _this = this;
    		$('.viewRelateRes').on('click',function(){
    			var lrId = $(this).attr('lrId');
    			layer.load('1');
    			$.ajax({
					type:'post',
			        dataType:'json',
			        data:{lrId:lrId},
			        url:'/lrLog.do?action=getSpecLRLData',
			        success:function (json){
			        	layer.closeAll('loading');
			        	if(json['result'] == 'success'){
			        		console.log(json.relateList)
			        		var str = _this.createRealteList(json.relateList);
			        		layer.open({
								title:'[<span class="relateCptName">'+ loreName +'</span>]的关联结果',
								type: 1,
							  	area: ['600px', '350px'],
							  	fixed: true, //不固定
							  	maxmin: false,
							  	shadeClose :true,
							  	closeBtn : 1,
							  	content: str
							});
			        	}else if(json['result'] == 'noInfo'){
			        		layer.msg('暂未获取到当前关联其他版本知识点信息，请稍后重试',{icon:5,anim:6,time:1500});
			        	}else if(json['result'] == 'error'){
			        		layer.msg('获取失败',{icon:5,anim:6,time:1500});
			        	}
			        }
				});
    		});
    	},
    	//删除已添加的关联知识点
    	delHasAddRelLore : function(){
    		var _this = this;
    		$('.delRelLoreBtn').on('click',function(){
    			var lrId = $(this).attr('lrId');
    			layer.confirm('确定要删除当前已关联的知识点吗？', {
  				  title:'提示',
  				  skin: 'layui-layer-molv',
  				  btn: ['确定','取消'] //按钮
  				},function(index){
  					layer.load('1');
  					var field = null,url='';
  					if(parent.currPage == 'lorePage' || parent.currPage == 'relateManager'){
  						field = {lrId:lrId};
  						url = '/loreRelate.do?action=delRelate';
  					}else if(currPage == 'lexRelatePage'){
  						field = {llrId:lrId};
  						url = '/lex.do?action=delLLR';
  					}else if(parent.currPage == 'buffetPage'){
  						field = {opt:'del',blrId:lrId};
  						url = '/blr.do?action=addRelateLore';
  					}
  					$.ajax({
  						type:'post',
  				        dataType:'json',
  				        data:field,
  				        url:url,
  				        success:function (json){
  				        	layer.closeAll('loading');
  				        	if(json['result'] == 'success'){
  				        		if(parent.currPage == 'lorePage' || parent.currPage == 'relateManager'){
  				        			var editInpVal = $('#editInp').val();
  	  				        		layer.msg('删除成功',{icon:1,time:1000},function(){
  	  				        			_this.addRelateLoreAction(loreId, editInpVal);
  	  				        			layer.close(index);
  		   				        	});
  				        		}else if(currPage == 'lexRelatePage'){
  				        			layer.msg('删除成功',{icon:1,time:1000},function(){
  	  				        			_this.addLexRelateLoreAction(lexId);
  	  				        			layer.close(index);
  		   				        	});
  				        		}else if(parent.currPage == 'buffetPage'){
  				        			layer.msg('删除成功',{icon:1,time:1000},function(){
  	  				        			_this.addBfRelateLoreAction(parent.bigBuffetId);
  	  				        			layer.close(index);
  		   				        	});
  				        		}
  				        	}else if(json['result'] == 'noInfo'){
  				        		layer.msg('暂未获取到当前已关联知识点信息，请稍后重试',{icon:5,anim:6,time:1500});
  				        	}else if(json['result'] == 'error'){
  				        		layer.msg('删除失败，请稍后重试',{icon:5,anim:6,time:1500});
  				        	}
  				        }
  					});
  					layer.close(index);
  				});
    		});
    	},
    	//动态创建模拟滚动条
    	actCalCreateScroll : function(parObj,sonObj,scrollNum){
    		if($('#'+parObj).height() < $('#'+sonObj).height()){//创建模拟滚动条
				//var oScroll = '<div id="scrollParent" class="parentScroll"><div id="scrollSon" class="scrollBar"></div></div>';
    			var oScroll = '<div id="parScroll_'+ scrollNum +'" class="parentScroll"><div id="sonScroll_'+ scrollNum +'" class="scrollBar"></div></div>';
    			//创建动态模拟滚动条
				$('#'+parObj).append(oScroll);
				comScrollBar.scrollBar(parObj,sonObj,'parScroll_'+scrollNum,'sonScroll_'+scrollNum,15);
			}else{
				$('#'+sonObj).animate({"top":0});
				document.getElementById(parObj).onmousewheel = function(){
					return false;
				};
			}
    	},
    	//查询知识点根据知识点标题和拼音码
    	searchZsdByTitOrPy : function(){
    		var _this = this,loreSetWid=0;
    		var zsd_py = $('#zsd_py').val(),
				zsd_txt = $('#zsd_txt').val();
    		if(searOpt == 'loreManager' || searOpt == 'lexRelate'){
    			var ediId = 1;//只查通用版的
    		}else if(searOpt == 'relate'){
    			var ediId = $('#editInp').val();//只查当前指定出版社的
    		}
			if(nowSearType == 1 && zsd_py == ''){
				layer.msg('请输入知识点拼音码',{icon:5,anim:6,time:2000});
				return;
			}
			if(nowSearType == 2 && zsd_txt == ''){
				layer.msg('请输入知识点标题',{icon:5,anim:6,time:2000});
				return;
			}
			if(zsd_py != '' && zsd_py.length < 2){
				layer.msg('为了提高查询速度，必须最少输入两个字符以上',{icon:5,anim:6,time:2200});
				return;
			}else if(zsd_txt != '' && zsd_txt.length < 2){
				layer.msg('为了提高查询速度，必须最少输入两个字符以上',{icon:5,anim:6,time:2200});
				return;
			}
			if(currPage == 'lorePage'){
				loreSetWid = '375';
			}if(currPage == 'buffetPage'){
				loreSetWid = '300';
			}else if(currPage == 'teaHwPage'){
				if(roleName == '老师'){
					loreSetWid = '220';
				}else if(roleName == '知识点管理员'){
					loreSetWid = '160';
				}
			}
			layer.load('1');
			$.ajax({
				type:'post',
		        dataType:'json',
		        data:{loreName:zsd_txt,loreNamePy:zsd_py,ediId:ediId},
		        url:'/lore.do?action=getLoreList',
		        success:function (json){
		        	layer.closeAll('loading');
		        	if(json.result == 'success'){
		        		if(searOpt == 'loreManager'){
		        			_this.comBackFun();
		            		$('#currLoc').html('');
		            		$('.loreList').show();
		            		$('.loreQuesList').hide();
		            		$('.tipsTxt_lore').hide();
		            		table.render({
		        				elem: '#loreListTable',
		        				height: 'full-150',
		        				data:json.loreList,
		        				page : true,
		        				even : true,
		        				limit : 20,
		        				limits:[10,20,30,40],
		        				cellMinWidth:220,
		        				text: {none : '暂无相关数据'},
		        				cols : [[
		        					{field : '', title: '序号', type:'numbers', align:'center'},
		        					{field : 'subName', title: '学科', width:'150', align:'center' },
		        					{field : 'gradeName', title: '年级', width:'150',align:'center'},
		        					{field : 'eduName', title: '教材', width:'150',align:'center'},
		        					{field : 'cptName', title: '章节',width:'320',align:'center'},
		        					{field : 'loreName', title: '知识点名称',width:'320',align:'center'},
		        					{field : 'inUse',title: '是否有效',width:'120',align:'center',templet:function(d){
		        						var str = '';
		        						d.inUse == '启用'? str='<span class="sucColor">启用</span>' : str='<span class="warningCol">未启用</span>';
		        						return str;
		        					}},
		        					{field : '', title: '操作',fixed:'right',  width:loreSetWid,align:'center',templet : function(d){
		        						var fixStr = '';
		        						if(currPage == 'lorePage' ||currPage == 'sysHwPage' ||currPage == 'buffetPage'){
		        							fixStr += '<a class="layui-btn layui-btn-xs addTikuBtn" opts="add" lay-event="addFun" loreName="'+ d.loreName +'" loreId="'+ d.loreId +'"><i class="layui-icon layui-icon-add-circle"></i>添加</a>';
		        							fixStr += '<a class="layui-btn layui-btn-xs layui-btn-primary editBtn" opts="edit" lay-event="editFun" loreName="'+ d.loreName +'" loreId="'+ d.loreId +'"><i class="layui-icon layui-icon-edit"></i>编辑</a>';
		        							fixStr += '<a class="layui-btn layui-btn-xs layui-btn-normal viewBtn" opts="view" lay-event="viewFun" loreName="'+ d.loreName +'" loreId="'+ d.loreId +'"><i class="layui-icon layui-icon-search"></i>浏览</a>';
		        							if(currPage == 'lorePage'){
		        								fixStr += '<a class="layui-btn layui-btn-xs layui-btn-primary viewBtn" lay-event="relateLore" loreName="'+ d.loreName +'" loreId="'+ d.loreId +'"><i class="iconfont layui-extend-guanlian"></i>关联知识点</a>';
				        						fixStr += '<a class="layui-btn layui-btn-xs viewBtn" lay-event="viewLoreTree" loreName="'+ d.loreName +'" loreId="'+ d.loreId +'"><i class="iconfont layui-extend-tree"></i>知识树</a>';
		        							}
		        							if(currPage == 'buffetPage'){
		        								fixStr += '<a class="layui-btn layui-btn-xs" opts="merge" lay-event="mergeLoreFun" loreName="'+ d.loreName +'" loreId="'+ d.loreId +'"><i class="iconfont layui-extend-hebing"></i>合并知识点</a>';
		        							}
		        							
		        						}else if(currPage == 'teaHwPage'){
		        							if(roleName == '老师'){
		        								fixStr += '<a class="layui-btn layui-btn-xs addTikuBtn" opts="add" lay-event="addFun" loreName="'+ d.loreName +'" loreId="'+ d.loreId +'"><i class="layui-icon layui-icon-add-circle"></i>添加</a>';
		        							}
		        							fixStr += '<a class="layui-btn layui-btn-xs layui-btn-primary editBtn" opts="edit" lay-event="editFun" loreName="'+ d.loreName +'" loreId="'+ d.loreId +'"><i class="layui-icon layui-icon-edit"></i>编辑</a>';
		        							fixStr += '<a class="layui-btn layui-btn-xs layui-btn-normal viewBtn" opts="view" lay-event="viewFun" loreName="'+ d.loreName +'" loreId="'+ d.loreId +'"><i class="layui-icon layui-icon-search"></i>浏览</a>';
		        						}
		        						return fixStr;
		        					}},
		        				]],
		        				done : function(res, curr, count){
		        					layer.closeAll('loading');
		        				}
		        			});
		        		}else if(searOpt == 'relate' || searOpt == 'lexRelate'){
		        			_this.renderLoreSmList(json.loreList);
		        		}
		        	}else if(json.result == 'noInfo'){
		        		layer.msg('暂无记录',{icon:5,anim:6,time:2200});
		        	}
		        }
			});
    	}
    };
    //拼音码标题的切换
    form.on('select(zsdSel)',function(data){
		var value = data.value;
		nowSearType = value;
		if(value == 1){//拼音
			$('#zsd_py').show();
			$('#zsd_txt').hide().val('');
		}else{
			$('#zsd_py').hide().val('');
			$('#zsd_txt').show();
		}
	});
    //输出接口
    exports('relate', obj);
});
