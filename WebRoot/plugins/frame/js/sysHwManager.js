/**
 * @Description: 知识点管理 01：根据章节cptId获取对应章节知识点列表 
 * @author: hlf
 */
//自定义模块
layui.define(['form','table','buffetLoreMet'],function(exports){
	var $ = layui.jquery,form=layui.form,table=layui.table,blMet=layui.buffetLoreMet;
    var obj = {
    	//根据知识点loreId获取家庭作业题库列表
    	getSysHwList : function(loreId){
    		layer.load('1');
    		var editId = $('#editInp').val();
    		table.render({
				elem: '#loreQuesTable',
				height: 'full-150', 
				url :'/hw.do?action=getHwPageData',
				method : 'post',
				where:{loreId:loreId},
				page : true,
				even : true,
				limit : 20,
				limits:[10,20,30,40],
				text: {none : '暂无相关数据'},
				cellMinWidth:150,
				cols : [[
					{field : '', title: '序号', type:'numbers', align:'center'},
					{field : 'btName', title: '类型', align:'center' },
					{field : 'loreName', title: '知识点',align:'center' },
					{field : 'hwTitle', title: '作业名称',align:'center'},
					{field : 'mindStr', title: '思维',align:'center'},
					{field : 'abilityStr', title: '能力',align:'center'},
					{field : 'inUse', title: '是否有效',align:'center',templet:function(d){
						var str = '';
						d.inUse == '有效'? str='<span class="sucColor">有效</span>' : str='<span class="warningCol">无效</span>';
						return str;
					}},
					{field : '', title: '操作',align:'center',templet : function(d){
						var fixStr = '';
						if(editId == 1){
							fixStr += '<a class="layui-btn layui-btn-xs editBtn" opts="edit" hwId="'+ d.id +'" lay-event="editFun"><i class="layui-icon layui-icon-edit"></i>修改</a>';
						}
						if(d.inUse == '有效'){
							fixStr += '<a class="layui-btn layui-btn-xs layui-btn-danger setBtn" opts="set" lay-event="setIsInUseFun" inUse="1" inUseTxt="无效" hwTit="'+ d.hwTitle +'" hwId="'+ d.id +'"><i class="layui-icon layui-icon-set"></i>设为无效</a>';
						}else{
							fixStr += '<a class="layui-btn layui-btn-xs layui-btn-normal setBtn" opts="set" lay-event="setIsInUseFun" inUse="0" inUseTxt="有效" hwTit="'+ d.hwTitle +'" hwId="'+ d.id +'"><i class="layui-icon layui-icon-set"></i>设为有效</a>';
						}
						return fixStr;
					}},
				]],
				done : function(res, curr, count){
					layer.closeAll('loading');
				}
			});
    	},
    	//获取班内老师上传家庭作业题库列表
    	getTeaHwList : function(){
    		layer.load('1');
    		var editId = $('#editInp').val();
    		table.render({
				elem: '#loreQuesTable',
				height: 'full-150', 
				url :'/hw.do?action=getTeaQuePageData',
				method : 'post',
				where:{loreId:loreId},
				page : true,
				even : true,
				limit : 20,
				limits:[10,20,30,40],
				text: {none : '暂无相关数据'},
				cellMinWidth:150,
				cols : [[
					{field : '', title: '序号', type:'numbers', align:'center'},
					{field : 'queTitle', title: '知识点',align:'center'},
					{field : 'tqType', title: '作业类型',align:'center'},
					{field : 'addTeaName', title: '上传者',align:'center'},
					{field : 'inUse', title: '是否有效',align:'center',templet:function(d){
						var str = '';
						d.inUse == '有效'? str='<span class="sucColor">有效</span>' : str='<span class="warningCol">无效</span>';
						return str;
					}},
					{field : '', title: '操作',align:'center',templet : function(d){
						var fixStr = '';
						if(editId == 1){
							fixStr += '<a class="layui-btn layui-btn-xs editBtn" opts="edit" tqId="'+ d.tqId +'" lay-event="editFun"><i class="layui-icon layui-icon-edit"></i>修改</a>';
						}
						if(d.inUse == '有效'){
							fixStr += '<a class="layui-btn layui-btn-xs layui-btn-danger setBtn" opts="set" lay-event="setIsInUseFun" inUse="1" inUseTxt="无效" queTit="'+ d.queTitle +'" tqId="'+ d.tqId +'"><i class="layui-icon layui-icon-set"></i>设为无效</a>';
						}else{
							fixStr += '<a class="layui-btn layui-btn-xs layui-btn-normal setBtn" opts="set" lay-event="setIsInUseFun" inUse="0" inUseTxt="有效" queTit="'+ d.queTitle +'" tqId="'+ d.tqId +'"><i class="layui-icon layui-icon-set"></i>设为有效</a>';
						}
						return fixStr;
					}},
				]],
				done : function(res, curr, count){
					console.log(res)
					layer.closeAll('loading');
				}
			});
    	},
    	//初始化家庭作业详情
    	initAnswerOption : function(realAnswer){
    		var options,questionType = $('#tiganTypeInp').val();
    		if(questionType == "单选题"){
    			options = document.getElementsByName("answer_singel");
    		}else if(questionType == "判断题"){
    			options = document.getElementsByName("answer_judge");
    		}else if(questionType == "多选题"){
    			options = document.getElementsByName("answer_multi");
    		}else if(questionType == "填空选择题"){
    			options = document.getElementsByName("answer_multiTk");
    		}
    		//初始化单选多选框选择状态
    		if(questionType == "单选题"){
    			for(var i=0;i<options.length;i++){
    				if(realAnswer == options[i].value){
    					options[i].checked = true;
    				}
    			}
    		}else if(questionType == "填空选择题" || questionType == "多选题"){
    			var realAnswerArray = realAnswer.split(",");
    			for(var i = 0 ; i < realAnswerArray.length; i++){
    				for(var j = 0 ; j < options.length; j++){
    					if(questionType == "多选题"){
    						if(realAnswerArray[i] == options[j].value){
    							result_answer += options[j].id + ',';
    							//result_answer_text += options[j].value + ",";
    							options[j].checked = true;
    							break;
    						}
    					}else if(questionType == "填空选择题"){
    						if(realAnswerArray[i] == options[j].alt){
    							result_answer_text += options[j].alt + ",";
        						result_answer += options[j].value + ",";
        						break;
        					}
    					}
    				}
    			}
    			if(questionType == "多选题"){
    				var newRealAns = result_answer.substring(0,result_answer.length - 1);
    				//一进来先不去除最后的逗号
        			$('#result_answer_new').html(newRealAns).removeClass('noSel').addClass('hasSel');
    			}else if(questionType == "填空选择题"){
    				var newRealAns = result_answer.substring(0,result_answer.length - 1);
    				$('#result_answer_new_tk').html(newRealAns).removeClass('noSel').addClass('hasSel');
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
    exports('sysHwManager', obj);
});
