/**
 * @Description: 基础配置
 * @author: hlf
 */
var noGradInfo = '';//根据科目联动年级时用到 存在年级 清空 不存在有值 noGradInfo
var currPage;//用于标识是否是当前页面
//自定义模块
layui.define(['form','table','relate'],function(exports){
	var $ = layui.jquery,form=layui.form,table=layui.table,relate=layui.relate;
	var yearSyStr = '',gradeStr='';
    var obj = {
    	//获取学段
    	getSchoolTye : function(){
    		var strHtml = '';
    		strHtml += '<option value="1">小学</option>';
    		strHtml += '<option value="2">初中</option>';
    		strHtml += '<option value="3">高中</option>';
    		return strHtml;
    	},
    	//获取学年制
    	getYearSys : function(scType){
    		var strHtml = '<option value="">请选择学年制</option>';
    		if(scType == 1){//小学
    			strHtml += '<option value="5">5年制</option>';
    			strHtml += '<option value="6">6年制</option>';
    		}else if(scType == 2){//初中
    			strHtml += '<option value="3">3年制</option>';
    			strHtml += '<option value="4">4年制</option>';
    		}else if(scType == 3){//高中
    			strHtml += '<option value="3">3年制</option>';
    		}
    		return strHtml;
    	},
    	//获取年级根据学段
    	getGradeBySchType : function(scType){
    		var strHtml = '<option value="">请选择年级</option>';
    		if(scType == 1){//小学
    			strHtml += '<option value="一年级">一年级</option>';
    			strHtml += '<option value="二年级">二年级</option>';
    			strHtml += '<option value="三年级">三年级</option>';
    			strHtml += '<option value="四年级">四年级</option>';
    			strHtml += '<option value="五年级">五年级</option>';
    			strHtml += '<option value="六年级">六年级</option>';
    		}else if(scType == 2){//初中
    			strHtml += '<option value="七年级">七年级</option>';
    			strHtml += '<option value="八年级">八年级</option>';
    			strHtml += '<option value="九年级">九年级</option>';
    		}else if(scType == 3){//高中
    			strHtml += '<option value="高一">高一</option>';
    			strHtml += '<option value="高二">高二</option>';
    			strHtml += '<option value="高三">高三</option>';
    		}
    		return strHtml;
    	},
		//获取所有出版社列表
		getEditionList : function(selObj){
			layer.load('1');
			$.ajax({
				type:'post',
		        dataType:'json',
		        url:'/common.do?action=getEditionData',
		        data : {showStatus:0},
		        success:function (json){
		        	layer.closeAll("loading");
		        	if(json['msg'] == 'success'){
		        		var str_edit = '';
		        		layer.closeAll('loading');
		    			for(var i=0;i<json.data.length;i++){
		    				str_edit += '<option value="'+ json.data[i].id +':'+ json.data[i].ediName +'">'+ json.data[i].ediName +'</option>';
		    			}
		    			/*if(currPage != 'educPage' || currPage != 'relatePage'){
		    				alert(currPage + "-----------")
		    				$('#editInp').val(json.data[0].id);//默认第一个出版社的值赋给隐藏变量
		    			}*/
		    			if(currPage == 'lorePage' || currPage == 'chapterPage' || currPage == 'loreCataPage' || currPage == 'relateManager'){
		    				$('#editInp').val(json.data[0].id);//默认第一个出版社的值赋给隐藏变量
		    				if(currPage == 'lorePage' || currPage == 'relateManager'){
		    					editAll = json.data[0].id + ':' + json.data[0].ediName;
		    				}
		    			}
		    			if(currPage == 'loreCataPage'){
		    				nowEditName = json.data[0].ediName;//基础知识点必须在通用版下添加
		    			}
		    			$('#' + selObj).append(str_edit);
		    			if(currPage == 'relatePage'){
		    				$('#editionSel').attr('disabled',true).val(parent.editAll);
		    			}
		    			form.render();
		        	}else if(json['msg'] == 'noInfo'){
		        		layer.msg('暂无出版社信息',{icon:5,anim:6,time:2000});
		        	}
		        }   
			});
		},
		//获取所有科目列表
		getSubjectList : function(selObj){
			layer.load('1');
			$.ajax({
				type:'post',
		        dataType:'json',
		        url:'/common.do?action=getSubjectData',
		        data : {showStatus:0},
		        success:function (json){
		        	layer.closeAll("loading");
		        	if(json['msg'] == 'success'){
		        		var str_sub = '';
						for(var i=0;i<json.data.length;i++){
							str_sub += '<option value="'+ json.data[i].id +'">'+ json.data[i].subName +'</option>';
						}
						$('#' + selObj).append(str_sub);
						form.render();
		        	}else if(json['msg'] == 'noInfo'){
		        		layer.msg('暂无科目信息',{icon:5,anim:6,time:2000});
		        	}
		        }
			});
		},
		//获取指定学科下的年级列表
		getGradeBySubId:function(subId){
			layer.load('1');
			var _this = this;
			$.ajax({
				type:'post',
		        dataType:'json',
		        //url:'/common.do?action=getGSubjectBySubId',
		        url : '/common.do?action=getGradeDataBySubId',
		        data :{subId:subId},
		        async : false,
		        success:function (json){
		        	layer.closeAll('loading');
		        	var str_grad = '<option value="">请选择年级</option>';
		        	if(json['result'] == 'success'){
		        		noGradInfo = '';
		        		for(var i=0;i<json.gList.length;i++){
		        			str_grad += '<option value="'+ json.gList[i].gsId +'">'+ json.gList[i].gName +'</option>';
						}
		        		$('#gradeSel').html(str_grad);
		        	}else if(json['result'] == 'noInfo'){
		        		//layer.msg('暂无此科目对应的年级',{icon:5,anim:6,time:2000});
		        		layer.msg('暂无此科目对应的年级',{time:1200});
		        		noGradInfo = 'noGradInfo';
		        		$('#gradeSel').html('');
		        	}
		        	//当选择教材select存在时 选择学科需要重置教材select
		        	if($('#eduColumeSel').length > 0 && $('#gradeInp').val() != ''){
	        			$('#eduColumeInp').val('');
						$('#eduColumeSel').html('<option value="">请选择教材</option>');
						if(currPage == 'loreCataPage'){
							//知识点目录管理 知识点管理里面清空章节信息
							$('#chapterInp').val('');
							$('#chapterSel').html('<option value="">请选择章节</option>');
							_this.getLoreCataList('');
						}else if(currPage == 'lorePage'){
							//加载根据章节获取知识点的列表
							_this.getLoreList('');
						}
						if(currPage == 'chapterPage' || currPage == 'loreCataPage' || currPage == 'lorePage' || currPage == 'relatePage'){
							_this.getChapterList('');
						}
	        		}
		        	$('#gradeInp').val('');
		        	form.render();
		        }
			});
		},
		//根据年级gsId 出版社获取教材上下册
		getEduColumeByGsEdId : function(ediId,gsId){
			layer.load('1');
			var _this = this;
			$.ajax({
				type:'post',
		        dataType:'json',
		        url:'/common.do?action=getEduData',
		        data : {ediId:ediId,gsId:gsId},
		        success:function (json){
		        	layer.closeAll("loading");
		        	if(json['result'] == 'success'){
		        		var strHtml = '';
						for(var i=0;i<json.eduList.length;i++){
							strHtml += '<option value="'+ json.eduList[i].eduId +'">'+ json.eduList[i].eduColume +'</option>';
						}
						$('#eduColumeInp').val(json.eduList[0].eduId);//将教材第一个value赋给隐藏变量
						$('#eduColumeSel').html(strHtml);
						form.render();
						if(currPage == 'lorePage' || currPage == 'loreCataPage' || currPage == 'chapterPage' || currPage == 'relatePage'){//不是关联知识点模块的统一加载章节列表
							_this.getChapterList($('#eduColumeInp').val());
						}
						if(currPage == 'chapterPage'){
							$('.addBtnRt').show();
							$('.tipsTxt').hide();
						}else if(currPage == 'loreCataPage' && $('#chapterInp').val()!= ''){
							//每次选择你年级需要重新清空chaptId 因为教材信息已经清空
							$('#chapterInp').val('');
							_this.getLoreCataList('');
						}else if(currPage == 'lorePage'){
							$('.tipsTxt_lore').hide();
							_this.getLoreList('');
						}
		        	}else if(json['result'] == 'noInfo'){
		        		layer.msg('暂无教材信息',{time:1200});
		        		$('#eduColumeInp').val('');
						$('#eduColumeSel').html('<option value="">请选择教材</option>');
						if(currPage == 'lorePage' || currPage == 'loreCataPage' || currPage == 'chapterPage'){
							_this.getChapterList('');
						}
						
						if(currPage == 'loreCataPage' && $('#chapterInp').val()!= ''){
							//知识点目录管理 知识点管理里面清空章节信息
							//$('#chapterInp').val('');
							//$('#chapterSel').html('<option value="">请选择章节</option>');
							_this.getLoreCataList('');
						}else if(currPage == 'chapterPage'){
							$('.tipsTxt').hide();
						}else if(currPage == 'lorePage'){
							$('.tipsTxt_lore').hide();
							_this.getLoreList('');
						}
						form.render();
		        	}
		        }
			});
		},
		//根据章节Id获取知识点列表
    	getLoreList : function(cptId){
    		layer.load('1');
    		var _this = this;
    		if(currPage == 'lorePage'){
    			var editVal = $('#editInp').val();
        		if(editVal == 1){
        			loreSetWid = '375';
        		}else{
        			loreSetWid = '220';
        		}
        		//每次加载调用下清除返回方法
        		relate.comBackFun();
        		$('#currLoc').html('');
        		$('.loreList').show();
        		$('.loreQuesList').hide();
        		table.render({
    				elem: '#loreListTable',
    				height: 'full-150',
    				url :'/lore.do?action=getPageLoreData',
    				method : 'post',
    				where:{cptId:cptId},
    				page : true,
    				even : true,
    				limit : 20,
    				limits:[10,20,30,40],
    				text: {none : '暂无相关数据'},
    				cols : [[
    					{field : '', title: '序号', type:'numbers',fixed: 'left' , align:'center'},
    					{field : 'subName', title: '学科', width:'120', align:'center' },
    					{field : 'gradeName', title: '年级',width:'120',align:'center'},
    					{field : 'eduVolume', title: '教材',width:'120',align:'center'},
    					{field : 'cptName', title: '章节',width:'280',align:'center'},
    					{field : 'loreName', title: '知识点名称',width:'280',align:'center'},
    					{field : 'inUse',title: '是否有效',width:'120',align:'center',templet:function(d){
    						var str = '';
    						d.inUse == '有效'? str='<span class="sucColor">有效</span>' : tmpStr='<span class="warningCol">无效</span>';
    						return str;
    					}},
    					{field : '', title: '操作', fixed: 'right',width:loreSetWid, align:'center',templet : function(d){
    						var fixStr = '';
    						if(editVal == 1){//通用版
    							fixStr += '<a class="layui-btn layui-btn-xs addTikuBtn" opts="add" lay-event="addFun" loreName="'+ d.loreName +'" loreId="'+ d.loreId +'"><i class="layui-icon layui-icon-add-circle"></i>添加</a>';
    							fixStr += '<a class="layui-btn layui-btn-xs layui-btn-primary editBtn" opts="edit" lay-event="editFun" loreName="'+ d.loreName +'" loreId="'+ d.loreId +'"><i class="layui-icon layui-icon-edit"></i>编辑</a>';
    							fixStr += '<a class="layui-btn layui-btn-xs layui-btn-normal viewBtn" opts="view" lay-event="viewFun" loreName="'+ d.loreName +'" loreId="'+ d.loreId +'"><i class="layui-icon layui-icon-search"></i>浏览</a>';
    							
    						}
    						fixStr += '<a class="layui-btn layui-btn-xs layui-btn-primary viewBtn" lay-event="relateLore" loreName="'+ d.loreName +'" loreId="'+ d.loreId +'"><i class="iconfont layui-extend-guanlian"></i>关联知识点</a>';
    						fixStr += '<a class="layui-btn layui-btn-xs viewBtn" lay-event="viewLoreTree" loreName="'+ d.loreName +'" loreId="'+ d.loreId +'"><i class="iconfont layui-extend-tree"></i>知识树</a>';
    						
    						return fixStr;
    					}},
    				]],
    				done : function(res, curr, count){
    					layer.closeAll('loading');
    				}
    			});
    		}else if(currPage == 'relatePage'){
    			$.ajax({
    				type:'post',
    		        dataType:'json',
    		        data:{cptId:cptId},
    		        url:'/lore.do?action=getPageLoreData',
    		        success:function (json){
    		        	layer.closeAll('loading');	
    		        	if(json.msg == 'success'){
    		        		var loreList = json.data;
    		        		relate.renderLoreSmList(loreList);
    		        	}else if(json.msg == '暂无记录'){
    		        		layer.msg('暂无记录',{icon:5,anim:6,time:2200});
    		        	}
    		        }
    			});
    		}
    	},
		//获取章节列表 
		getChapterList : function(eduId){
			var url = '/chapter.do?action=getChapterData',
				field = {eduId :eduId};
			layer.load('1');
			if(currPage == 'chapterPage'){//章节管理
				table.render({
					elem: '#chapterTable',
					height: 'full-200',
					url :url,
					method : 'post',
					where:field,
					page : true,
					even : true,
					limit : 20,
					cellMinWidth: 100,
					limits:[10,20,30,40],
					text: {none : '暂无相关数据'},
					cols : [[
						{field : '', title: '序号', type:'numbers',fixed: 'left' , align:'center'},
						{field : 'cptName', title: '章节名称', align:'center',fixed: 'left' },
						{field : 'cptOrder', title: '排序',align:'center',style:'color:#01AAED;'},
						{field : '', title: '操作',  fixed: 'right', align:'center',templet : function(d){
							return '<a class="layui-btn layui-btn-xs editBtn" opts="edit" lay-event="editFun" cptName="'+ d.cptName +'" chapId="'+ d.id +'"><i class="layui-icon layui-icon-edit"></i>编辑</a>';
						}},
					]],
					done : function(res, curr, count){
						layer.closeAll('loading');
					}
				});
			}else if(currPage == 'loreCataPage' || currPage == 'lorePage' || currPage == 'relatePage'){
				var _this = this;
				//知识点目录管理 01：生成章节select chapterSel
				$.ajax({
					type:'post',
			        dataType:'json',
			        url:url,
			        data :field,
			        success:function (json){
			        	layer.closeAll("loading");
			        	if(json['msg'] == 'success'){
			        		var str_chapt = '',defOpt='<option value="">请选择章节</option>';
							for(var i=0;i<json.data.length;i++){
								str_chapt += '<option value="'+ json.data[i].id +'">'+ json.data[i].cptName +'</option>';
							}
							$('#chapterSel').html(defOpt + str_chapt);
							form.render();
			        	}else if(json['msg'] == '暂无记录'){
			        		//layer.msg('暂无教材信息');
			        		$('#chapterInp').val('');
							$('#chapterSel').html('<option value="">请选择章节</option>');
							form.render();
			        	}
			        }
				});
			}
		},
		//获取章节下知识点目录list
		getLoreCataList : function(cptId){
			layer.load('1');
			table.render({
				elem: '#loreCataTable',
				height: 'full-200',
				url :'/lore.do?action=getLoreCatalogData',
				method : 'post',
				where:{cptId:cptId},
				page : true,
				even : true,
				limit : 20,
				cellMinWidth: 180,
				limits:[10,20,30,40],
				text: {none : '暂无相关数据'},
				cols : [[
					{field : '', title: '序号', type:'numbers',fixed: 'left' , align:'center'},
					{field : 'mainLoreName', title: '引用知识点名称', align:'center' },
					{field : 'loreName', title: '知识点名称',align:'center'},
					{field : 'loreCode', title: '知识点编码',align:'center'},
					{field : 'inUse',title: '是否有效',align:'center',templet:function(d){
						var str = '';
						d.inUse == '有效'? str='<span class="sucColor">有效</span>' : tmpStr='<span class="warningCol">无效</span>';
						return str;
					}},
					{field : 'freeStatus', title: '是否免费',align:'center',templet:function(d){
						var tmpStr = '';
						d.freeStatus == 0 ? tmpStr='<span class="sucColor">免费</span>' : tmpStr='<span class="warningCol">收费</span>';
						return tmpStr;
					}},
					{field : '', title: '操作', fixed: 'right', align:'center',templet : function(d){
						return '<a class="layui-btn layui-btn-xs editBtn" opts="edit" lay-event="editFun" loreName="'+ d.loreName +'" loreId="'+ d.id +'"><i class="layui-icon layui-icon-edit"></i>编辑</a>';
					}},
				]],
				done : function(res, curr, count){
					layer.closeAll('loading');
				}
			});
		},
		//批量生成知识点编码（指定章节下）
		createLoreCode : function(cptId){
			layer.load('1');
			var codeRes = '';
			$.ajax({
				type:'post',
		        dataType:'json',
		        data:{cptId:cptId},
		        url:'/lore.do?action=updateBatchLoreCode',
		        async:false,
		        success:function (json){
		        	layer.closeAll('loading');	
		        	if(json['result'] == 'success'){
		        		var codeList = json.codeList;
		        		codeRes += '<div class="codeResTit">编码修改结果</div><div id="codeResCon"><ul id="codeResConUl">';
		        		for(var i=0;i<codeList.length;i++){
		        			if(codeList[i].codeResult == 'succ'){
		        				codeRes += '<li class="succResCol">';
		        			}else if(codeList[i].codeResult == 'fail'){
		        				codeRes += '<li class="failCol">';
		        			}
		        			codeRes += '<p title="'+ codeList[i].codeInfo +'">'+ codeList[i].codeInfo +'</p></li>';
		        		}
		        		codeRes += '</ul></div>';
		        	}else if(json['result'] == 'noInfo'){
		        		layer.msg('暂无信息！',{icon:5,anim:6,time:2000});
		        	}
		        }
			});
			return codeRes;
		}
    };
    
    //选择学段
    form.on('select(schTypeSel)', function(data){
		var value = data.value;
		if(value == ''){
			$('#schTypeInp').val(0);
			$('#yearSysSel').html('<option value="">请选择学年制</option>');
			form.render();
		}else{
			$('#schTypeInp').val(value);
			//获取学年制
			if($('#yearSysSel').length > 0){
				yearSyStr = obj.getYearSys(value);
				$('#yearSysSel').html(yearSyStr);
				$('#yearSysInp').val('');//清空学年制
			}
			//获取年级
			if($('#gradeSel').length > 0){
				gradeStr = obj.getGradeBySchType(value);
				$('#gradeSel').html(gradeStr);
				$('#gradeInp').val('');//清空年级信息
			}
			form.render();
		}
	}); 
    //选择学年制
    form.on('select(yearSysSel)', function(data){
		var value = data.value;
		value == '' ? $('#yearSysInp').val('') : $('#yearSysInp').val(value);
	});
    //出版社select事件
    form.on('select(editionSel)', function(data){
		var allValue = data.value,
			value = data.value.split(':')[0],
			currEditName = data.value.split(':')[1];
		/*value == '' ? $('#editInp').val('') : $('#editInp').val(value);*/
		if(value == ''){//当前页面为教材管理
			$('#editInp').val(0);
		}else{
			$('#editInp').val(value);
			if(infoByGsEdOpt == 'geteduC'){
				var gsId = $('#gradeInp').val();
				obj.getEduColumeByGsEdId(value, gsId);
				if(currPage == 'loreCataPage'){
					currEditName == '通用版' ? $('#addLoreCata').show() : $('#addLoreCata').hide();
				}else if(currPage == 'lorePage' || currPage == 'relateManager'){
					editAll = allValue;//用于知识点管理下关联知识点匹配当前关联知识点所在的出版社
				}
			}
		}
	});
    //学科select事件
	form.on('select(subjectSel)', function(data){
		var value = data.value;
		//根据科目获取对应的年级信息
		if(value != ''){
			$('#subInp').val(value);
		}else{
			$('#subInp').val('');
		}
		/*
			infoBySubOpt:
				notGet : 不需要获取年级信息(年级科目添加编辑)
				getGradeOpt : 需要获取年级信息(教材添加编辑)
		 */
		if(infoBySubOpt == 'getGradeOpt'){
			obj.getGradeBySubId(value);
		}
	});
	//年级select事件
	form.on('select(gradeSel)', function(data){
		var value = data.value;
		if(value == ''){
			if(currPage == 'chapterPage' || currPage == 'loreCataPage' || currPage == 'lorePage' || currPage == 'relatePage'){
				if($('#gradeInp').val() != 0){
					//章节管理 知识点目录管理
					obj.getChapterList('');
				}
				$('#gradeInp').val(0);
			}else if(currPage == 'gradeSubPage'){//年级学科管理
				$('#gradeInp').val('');
			}
			if(infoByGsEdOpt == 'geteduC'){
				$('#eduColumeInp').val('');
				$('#eduColumeSel').html('<option value="">请选择教材</option>');
				if(currPage == 'loreCataPage' && $('#chapterInp').val() != ''){
					//知识点目录管理 知识点管理里面清空章节信息
					$('#chapterInp').val('');
					$('#chapterSel').html('<option value="">请选择章节</option>');
					//每次选择你年级需要重新清空chaptId 因为教材信息已经清空
					obj.getLoreCataList('');
				}else if(currPage == 'lorePage'){
					//加载根据章节获取知识点的列表
					$('.tipsTxt_lore').hide();
					obj.getLoreList('');
				}
				form.render();
			}
		}else{
			$('#gradeInp').val(value);
			//获取教材 
			/*
				infoByGsEdOpt:
				 	geteduC:需要根据年级出版社获取教材信息(章节管理)
				 	notGet ： 不需要(出版社编辑)
			*/
			if(infoByGsEdOpt == 'geteduC'){
				var ediId = $('#editInp').val();
				obj.getEduColumeByGsEdId(ediId, value);
			}
		}
		
	});
	//卷册select事件 切换教材
	form.on('select(eduColumeSel)', function(data){
		var value = data.value;
		if(value != ''){
			$('#eduColumeInp').val(value);
			if(currPage != 'relateManager'){
				obj.getChapterList(value);
			}
			if(currPage == 'loreCataPage' && $('#chapterInp').val()!= ''){
				//每次选择你年级需要重新清空chaptId 因为教材信息已经清空
				$('#chapterInp').val('');
				obj.getLoreCataList('');
			}else if(currPage == 'lorePage'&& $('#chapterInp').val()!= ''){
				//加载根据章节获取知识点的列表
				$('#chapterInp').val('');
				obj.getLoreList('');
			}else if(currPage == 'relateManager'){
				
			}
		}
	});
	//章节select 切换章节
	form.on('select(chapterSel)', function(data){
		var value = data.value;
		$('.tipsTxt_lore').hide();
		if(value != ''){
			$('#chapterInp').val(value);
		}else{
			$('#chapterInp').val('');
		}
		if(currPage == 'loreCataPage'){
			$('#addLoreCata').show();
			$('#zsdCodeBtn').show();
			//加载章节对应知识点目录
			obj.getLoreCataList(value);
		}else if(currPage == 'lorePage' || currPage == 'relatePage'){
			//加载根据章节获取知识点的列表
			obj.getLoreList(value);
		}
	});
	//显示状态select
	form.on('select(showStaSel)', function(data){
		var value = data.value;
		value == '' ? $('#showStaInp').val(-1) : $('#showStaInp').val(value);
	});  
    //输出接口
    exports('baseDataMet', obj);
});
