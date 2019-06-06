/**
 * @Description: 知识点管理 01：根据章节cptId获取对应章节知识点列表 
 * @author: hlf
 */
//自定义模块
layui.define(['form','table'],function(exports){
	var $ = layui.jquery,form=layui.form,table=layui.table;
    var obj = {
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
