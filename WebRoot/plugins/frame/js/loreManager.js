/**
 * @Description: 知识点管理 01：根据章节cptId获取对应章节知识点列表 
 * @author: hlf
 */
//自定义模块
layui.define(['form','table','buffetLoreMet'],function(exports){
	var $ = layui.jquery,form=layui.form,table=layui.table,
		blMet = layui.buffetLoreMet;
    var obj = {
    	data : {
    		originTypeTxt : '',//点击切换题型起原来题型
    		hasSwitchTxt : '',
    		isSwitchFlag : false//对于填空题 问答题是否切换其他题型的flag
    	},
    	//根据知识点loreId获取对应题库列表
    	getQuesList : function(loreId){
    		layer.load('1');
    		table.render({
				elem: '#loreQuesTable',
				height: 'full-150',
				url :'/lore.do?action=getPageLoreQuesionData',
				method : 'post',
				where:{loreId:loreId},
				page : true,
				even : true,
				limit : 20,
				limits:[10,20,30,40],
				cellMinWidth:260,
				text: {none : '暂无相关数据'},
				cols : [[
					{field : '', title: '序号', type:'numbers', align:'center'},
					{field : 'lqTitle', title: '名称', align:'center' },
					{field : 'lqType', title: '题型',align:'center'},
					{field : 'inUse', title: '是否有效',align:'center',templet:function(d){
						var str = '';
						d.inUse == '有效'? str='<span class="sucColor">有效</span>' : str='<span class="warningCol">无效</span>';
						return str;
					}},
					{field : '', title: '操作',align:'center',templet : function(d){
						var fixStr = '';
						fixStr += '<a class="layui-btn layui-btn-xs editBtn" opts="edit" lay-event="editFun" lqTitle="'+ d.lqTitle +'" lqType="'+ d.lqType +'" lqId="'+ d.lqId +'"><i class="layui-icon layui-icon-edit"></i>修改</a>';
						if(d.inUse == '有效'){
							fixStr += '<a class="layui-btn layui-btn-xs layui-btn-danger setBtn" opts="set" lay-event="setIsInUseFun" inUse="1" inUseTxt="无效" lqTitle="'+ d.lqTitle +'" lqId="'+ d.lqId +'"><i class="layui-icon layui-icon-set"></i>设为无效</a>';
						}else{
							fixStr += '<a class="layui-btn layui-btn-xs layui-btn-normal setBtn" opts="set" lay-event="setIsInUseFun" inUse="0" inUseTxt="有效" lqTitle="'+ d.lqTitle +'" lqId="'+ d.lqId +'"><i class="layui-icon layui-icon-set"></i>设为有效</a>';
						}
						return fixStr;
					}},
				]],
				done : function(res, curr, count){
					layer.closeAll('loading');
				}
			});
    		/*$.ajax({
				type:'post',
		        dataType:'json',
		        data:{loreId:loreId},
		        url:'/lore.do?action=getPageLoreQuesionData',
		        success:function (json){
		        	layer.closeAll('loading');	
		        	console.log(json)
		        }
			});*/
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
					//确认切换动作后将globalOpts变为add
					blMet.swithTiGanType(otherInpVal);
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
					$('#tiganTypeTxt').html(_this.data.originTypeTxt);
					//还原至原题型后globalOpts为edit
					blMet.swithTiGanType(_this.data.originTypeTxt);
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
    
    //切换其他题型 其他题型的form radio方法
    form.on('radio(otherTigan)',function(data){
    	var value = data.value;
    	$('#otherTiganInp').val(value);
    });
    //输出接口
    exports('loreManager', obj);
});
