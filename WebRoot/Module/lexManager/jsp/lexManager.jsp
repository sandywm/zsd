<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html> 
<html>
  <head>
    <title>关联知识点</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache"> 
	<meta http-equiv="keywords" content="知识典管理系统,词库管理">
	<meta http-equiv="description" content="词库管理">
	<link href="/plugins/layui/css/layui.css" rel="stylesheet" type="text/css"/>
	<link href="/Module/lexManager/css/lexManager.css" rel="stylesheet" type="text/css"/>
	<link href="/plugins/layui/css/modules/layui-icon-extend/iconfont.css" rel="stylesheet" type="text/css"/>
	<link href="/plugins/pace/pace-theme-flash.min.css" rel="stylesheet" type="text/css"/>
	<script src="/plugins/pace/pace.min.js" type="text/javascript"></script>	
    </head>
	<body style="background:#f2f2f2;">
		<div class="layui-fluid" style="margin-top:15px;">
			<div class="layui-row">
				<div class="layui-col-md12 layui-col-lg12">
					<div class="layui-card">
						<div class="layui-card-header">
	  						<span style="float:left">词库管理</span>
	  						<a id="addLex" opts='add' class="posAbs newAddBtn" href="javascript:void(0)" style="right:15px;"><i class="layui-icon layui-icon-add-circle"></i>添加词库</a>
	  					</div>
	  					<div class="layui-card-body" pad15>
	  						<div class="lexSearch layui-form">
	  							<div class="item" style="margin-right:10px;width:120px;">
									<div class="layui-input-inline">
										<select id="ciTiaoSel" lay-filter="ciTiaoSel">
											<option value="1">词库拼音码</option>
									       	<option value="2">词库标题</option>
									     </select>
									</div>
								</div>
								<div class="item">
									<div class="layui-input-inline" style="width:200px;margin-right:10px;">
										<input id="lexTitInp_py" type="text" class="layui-input" placeholder="请输入词条拼音码"/>
										<input id="lexTitInp_txt" type="text" class="layui-input" placeholder="请输入词条标题"/>
									</div>
								</div>
								<div class="item">
									<div class="layui-input-inline">
										<button id="queryBtn" class="layui-btn"><i class="layui-icon layui-icon-search"></i></button>
									</div>
								</div>
								<a class="resetBtn" href="javascript:void(0)">重置<i class="layui-icon layui-icon-refresh"></i></a>
	  						</div>
	  						<table id="lexTable" class="layui-table" lay-filter="lexTable"></table>
	  					</div>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script src="/plugins/jquery/jquery.min.js"></script>
   	<script src="/plugins/layui/layui.js"></script>
	<script type="text/javascript">
		var loreBigId=0,loreBigName='',addEditFlag=false,globalOpts='',currLexId=0;
		layui.config({
			base: '/plugins/frame/js/'
		}).use(['layer','form','table'], function() {
			var layer = layui.layer,
				form = layui.form,
				table = layui.table;
			
			form.on('select(ciTiaoSel)',function(data){
				var value = data.value;
				if(value == 1){
					$('#lexTitInp_txt').hide().val('');
					$('#lexTitInp_py').show();
				}else{
					$('#lexTitInp_txt').show();
					$('#lexTitInp_py').hide().val('');
				}
			});
			
			var page = {
				init : function(){
					this.loadLexList('initLoad');
					this.bindEvent();
				},
				bindEvent : function(){
					var _this = this;
					$('.resetBtn').on('click',function(){
						$('#lexTitInp_py').val('');
						$('#lexTitInp_txt').val('');
						_this.loadLexList('queryLoad');
					});
					$('#queryBtn').on('click',function(){
						_this.loadLexList('queryLoad');
					});
					$('#addLex').on('click',function(){
						currLexId = 0;//添加时初始化lexId
						addEditFlag = false;
						globalOpts = $(this).attr('opts');
						_this.showCommonLayer('添加词库');
					});
				},
				loadLexList : function(loadOpt){
					layer.load('1');
					if(loadOpt == 'initLoad'){
						var field = {lexTitle:'',lexTitlePy:''};
					}else{
						var lexTitInp_py = $('#lexTitInp_py').val(),
							lexTitInp_txt = $('#lexTitInp_txt').val();
						var field = {lexTitle:lexTitInp_txt,lexTitlePy:lexTitInp_py};
					}
					table.render({
						elem: '#lexTable',
						height: 'full-150',
						url : '/lex.do?action=getPageLexInfo',
						method : 'post',
						where:field,
						page : true,
						even : true,
						limit : 20,
						cellMinWidth: 200,
						limits:[10,20,30,40],
						text: {none : '暂无相关数据'},
						cols : [[
							{field : '', title: '序号', type:'numbers', align:'center'},
							{field : 'lexTitle', title: '词库标题', align:'center'},
							{field : 'lexTitlePy', title: '词库拼音码',align:'center'},
							{field : '', title: '操作', align:'center',templet : function(d){
								var str = '';
								str += '<a class="layui-btn layui-btn-xs editBtn" opts="edit" lay-event="editLex"  lexTit="'+ d.lexTitle +'" lexId="'+ d.id +'"><i class="layui-icon layui-icon-edit"></i>编辑</a>';
								str += '<a class="layui-btn layui-btn-xs layui-btn-primary" lay-event="relLore" lexTit="'+ d.lexTitle +'"  lexId="'+ d.id +'"><i class="iconfont layui-extend-guanlian"></i>关联知识点</a>';
								str += '<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="delLex" lexTit="'+ d.lexTitle +'" lexId="'+ d.id +'"><i class="layui-icon layui-icon-delete"></i>删除</a>';
								return str;
							}},
						]],
						done : function(res, curr, count){
							layer.closeAll('loading');
							
						}
					});
				},
				showCommonLayer : function(title){
					var _this = this;
					layer.open({
						title:title,
						type: 2,
					  	area: ['750px', '500px'],
					  	fixed: true, //不固定
					  	maxmin: false,
					  	shadeClose :false,
					  	content: '/Module/lexManager/jsp/addEditLex.html',
					  	end : function(){
					  		if(addEditFlag){
					  			_this.loadLexList('initLoad');
					  		}
					  	}
					});	
				}
			};
			table.on('tool(lexTable)',function(obj){
				if(obj.event == 'editLex'){//编辑词条
					var lexTit = $(this).attr('lexTit');
					addEditFlag = false;
					globalOpts = $(this).attr('opts');
					currLexId = $(this).attr('lexId');
					page.showCommonLayer('编辑词库[<span style="color:#F47837;">'+ lexTit +'</span>]');
				}else if(obj.event == 'delLex'){
					var lexTit = $(this).attr('lexTit'),
						lexId = $(this).attr('lexId');
					layer.confirm('确定要删除词库[<span style="color:#F47837;">'+ lexTit +'</span>]吗？', {
					  title:'提示',
					  skin: 'layui-layer-molv',
					  btn: ['确定','取消'] //按钮
					},function(index){
						layer.load('1');
						$.ajax({
	    					type:'post',
	    			        dataType:'json',
	    			        data:{lexId:lexId},
	    			        url:'/lex.do?action=delLex',
	    			        success:function (json){
	    			        	layer.closeAll('loading');	
	    			        	if(json.result == 'success'){
	    			        		layer.msg('删除成功',{icon:1,time:1000},function(){
	    			        			page.loadLexList('initLoad');
	    			        			layer.close(index);
	    			        		});
	    			        	}else if(json.result == 'error'){
	    			        		layer.msg('删除失败，请稍后重试', {icon:5,anim:6,time:2000});
	    			        		layer.close(index);
	    			        	}
	    			        }
	    				});
					});
				}
			});
			page.init();
		});
	</script>
</html>
