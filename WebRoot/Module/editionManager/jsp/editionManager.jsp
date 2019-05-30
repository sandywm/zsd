<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html> 
<html>
  <head>
    <title>出版社管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache"> 
	<meta http-equiv="keywords" content="知识典管理系统,学校信息管理">
	<meta http-equiv="description" content="学校信息管理添加修改">
	<link href="/plugins/layui/css/layui.css" rel="stylesheet" type="text/css"/>
	<link href="/plugins/pace/pace-theme-flash.min.css" rel="stylesheet" type="text/css"/>
	<script src="/plugins/pace/pace.min.js" type="text/javascript"></script>	
    </head>
	<body style="background:#f2f2f2;">
		<div class="layui-fluid" style="margin-top:15px;">
			<div class="layui-row">
				<div class="layui-col-md12 layui-col-lg12">
					<div class="layui-card">
						<div class="layui-card-header posRel">
	  						<span>出版社管理</span>
	  						<a id="addEdition" class="posAbs newAddBtn" href="javascript:void(0)"><i class="layui-icon layui-icon-add-circle"></i>添加出版社</a>
	  					</div>
	  					<div class="layui-card-body" pad15>
	  						<div id="editionList">
	  							<table id="editionTable" class="layui-table" lay-filter="editionTable"></table>
	  						</div>
	  					</div>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script src="/plugins/jquery/jquery.min.js"></script>
   	<script src="/plugins/layui/layui.js"></script>
	<script type="text/javascript">
		layui.use(['layer','table'], function() {
			var layer = layui.layer,
				table = layui.table;
			
			var page = {
				init : function(){
					this.bindEvent();
					this.loadEdition();
				},
				bindEvent : function(){
					$('#addEdition').on('click',function(){
						layer.msg('后续开放', {icon:5,anim:6,time:1500});
					});
				},
				loadEdition : function(){
					layer.load('1');
					var field = {showStatus:0,ediId:0};
					table.render({
						elem: '#editionTable',
						height: 'full-200',
						url : '/common.do?action=getEditionData',
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
							{field : 'ediName', title: '出版社名称', align:'center',fixed: 'left' },
							{field : 'ediOrder', title: '排序',align:'center',style:'color:#01AAED;'},
							{field : 'showStatusChi', title: '显示状态', align:'center'},
							{field : '', title: '操作',  fixed: 'right', align:'center',templet : function(d){
								return '<a class="layui-btn layui-btn-primary layui-btn-xs editBtn" lay-event="editFun"  ediId="'+ d.id +'"><i class="layui-icon layui-icon-edit"></i>编辑</a>';
							}},
						]],
						done : function(res, curr, count){
							layer.closeAll("loading");
						}
					});
				}
			};
			table.on('tool(editionTable)',function(obj){
				if(obj.event == 'editFun'){
					layer.msg('后续开放', {icon:5,anim:6,time:1500});
				}
			});
			page.init();
			
			/*$.ajax({
				type:'post',
		        dataType:'json',
		        data : {showStatus : -1},
		        url:'/common.do?action=getEditionData',
		        success:function (json){
		        	layer.closeAll("loading");
		        	console.log(json)
		        	if(json['result'] == 'success'){
		        		
		        	}
		        }
			});*/
		});
	</script>
</html>
