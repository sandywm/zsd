/**
 * @Description: 知识点管理 01：根据章节cptId获取对应章节知识点列表 
 * @author: hlf
 */
//自定义模块
layui.define(['form','table'],function(exports){
	var $ = layui.jquery,form=layui.form,table=layui.table;
    var obj = {
    	//根据知识点loreId获取自助餐对应题库列表
    	getBuffetList : function(loreId){
    		layer.load('1');
    		var editId = $('#editInp').val();
    		table.render({
				elem: '#loreQuesTable',
				height: 'full-150',
				url :'/buffet.do?action=getPageBuffetInfo',
				method : 'post',
				where:{loreId:loreId},
				page : true,
				even : true,
				limit : 20,
				limits:[10,20,30,40],
				text: {none : '暂无相关数据'},
				cols : [[
					{field : '', title: '序号', type:'numbers', align:'center'},
					{field : 'btName', title: '类型',width:'200', align:'center' },
					{field : 'loreName', title: '知识点', width:'240',align:'center' },
					{field : 'loreTitle', title: '名称',width:'200',align:'center'},
					{field : 'mindStr', title: '思维',width:'260',align:'center'},
					{field : 'abilityStr', title: '能力',width:'260',align:'center'},
					{field : 'inUse', title: '是否有效',width:'150',align:'center',templet:function(d){
						var str = '';
						d.inUse == '有效'? str='<span class="sucColor">有效</span>' : str='<span class="warningCol">无效</span>';
						return str;
					}},
					{field : '', title: '操作',fixed:'right',align:'center',width:'350',templet : function(d){
						var fixStr = '';
						if(editId == 1){
							fixStr += '<a class="layui-btn layui-btn-xs editBtn" opts="edit" buffetId="'+ d.id +'" lay-event="editFun"><i class="layui-icon layui-icon-edit"></i>修改</a>';
							fixStr += '<a class="layui-btn layui-btn-xs layui-btn-primary" buffetId="'+ d.id +'" loreTit="'+ d.loreTitle +'" lay-event="relateLoreFun"><i class="iconfont layui-extend-guanlian"></i>关联知识点</a>';
						}
						if(d.inUse == '有效'){
							fixStr += '<a class="layui-btn layui-btn-xs layui-btn-danger setBtn" opts="set" lay-event="setIsInUseFun" inUse="1" inUseTxt="无效" buffetTit="'+ d.loreTitle +'" buffetId="'+ d.id +'"><i class="layui-icon layui-icon-set"></i>设为无效</a>';
						}else{
							fixStr += '<a class="layui-btn layui-btn-xs layui-btn-normal setBtn" opts="set" lay-event="setIsInUseFun" inUse="0" inUseTxt="有效" buffetTit="'+ d.loreTitle +'" buffetId="'+ d.id +'"><i class="layui-icon layui-icon-set"></i>设为有效</a>';
						}
						fixStr += '<a class="layui-btn layui-btn-xs" opts="bfRelLex" buffetId="'+ d.id +'" lay-event="relateLexFun"><i class="iconfont layui-extend-guanlian"></i>关联词条</a>';
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
		        url:'/buffet.do?action=getPageBuffetInfo',
		        success:function (json){
		        	layer.closeAll('loading');	
		        }
			});*/
    	}
    };
    
    //切换其他题型 其他题型的form radio方法
    form.on('radio(otherTigan)',function(data){
    	var value = data.value;
    	$('#otherTiganInp').val(value);
    });
    //输出接口
    exports('buffetManager', obj);
});
