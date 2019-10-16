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
	<link href="/Module/commonJs/ueditor/themes/default/css/ueditor.min.css" type="text/css" rel="stylesheet" />
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
   	<script src="/Module/commonJs/ueditor/ueditor.config.js"></script>
	<script src="/Module/commonJs/ueditor/ueditor.all.min.js"></script>
	<script type="text/javascript">
		var addEditFlag=false,globalOpts='',currLexId=0,bigLexTit='';
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
				data : function(){
					layerIndex : 0
				},
				init : function(){
					this.loadLexList('initLoad');
					this.bindEvent();
				},
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
		    	enterPress : function(){
					var e = e || window.event;
					if(e.keyCode == 13){
						this.loadLexList();
					}
				},
				bindEvent : function(){
					var _this = this;
					$('.resetBtn').on('click',function(){
						$('#lexTitInp_py').val('');
						$('#lexTitInp_txt').val('');
						_this.loadLexList('queryLoad');
					});
					$('#lexTitInp_py').on('keypress',function(){
						_this.enterPress(event);
					});
					$('#lexTitInp_txt').on('keypress',function(){
						_this.enterPress(event);
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
				//添加编辑词库动作
				addEditLexFun : function(){
					var _this = this;
					$('#addLexBtn').on('click',function(){
		    			var lexTit = $.trim($('#lexTit').val()),
		    				lexCon = UE.getEditor('lexEditor').getContent();
		    			if(lexTit == ''){
		    				layer.msg('请填写词库标题', {icon:5,anim:6,time:2000});
		    			}else if(lexCon == ''){
		    				layer.msg('请填写词库内容', {icon:5,anim:6,time:2000});
		    			}else{
		    				layer.load('1');
		    				var field = null,url='';
		    				if(globalOpts == 'add'){
		    					field = {lexTitle:lexTit,lexContent:lexCon};
		    					url = '/lex.do?action=addLex';
		    				}else{
		    					field = {lexId:currLexId,lexTitle:lexTit,lexContent:lexCon};
		    					url = '/lex.do?action=updateLex';
		    				}
		    				$.ajax({
		    					type:'post',
		    			        dataType:'json',
		    			        data:field,
		    			        url:url,
		    			        success:function (json){
		    			        	layer.closeAll('loading');	
		    			        	var title = globalOpts == 'add' ? '添加词库成功' : '编辑词库成功';
		    			        	if(json.result == 'success'){
		    			        		layer.msg(title,{icon:1,time:1000},function(){
						        			addEditFlag = true;
						        			layer.close(_this.data.layerIndex);
		   				        		});
		    			        	}
		    			        }
		    				});
		    			}
		    		});
				},
				showCommonLayer : function(title){
					var _this = this;
					var lexStr = this.addEdITLexDOM();
					this.data.layerIndex = layer.open({
						title:title,
						type: 1,
					  	area: ['750px', '500px'],
					  	fixed: true, //不固定
					  	maxmin: false,
					  	shadeClose :false,
					  	content: lexStr,
					  	zIndex:100,
					  	end : function(){
					  		if(addEditFlag){
					  			_this.loadLexList('initLoad');
					  		}
					  	}
					});	
					if(globalOpts == 'edit'){
						layer.load('1');
	    				$.ajax({
	    					type:'post',
	    			        dataType:'json',
	    			        data:{lexId:currLexId},
	    			        url:'/lex.do?action=getLexDetail',
	    			        success:function (json){
	    			        	layer.closeAll('loading');	
	    			        	if(json.result == 'success'){
	    			        		$('#lexTit').val(json.lexTitle);
	    			        		UE.delEditor('lexEditor');
	    							_this.initUeditor('lexEditor');//初始化富文本
	    			        		_this.initUeditorContent('lexEditor',json.lexContent);
	    			        	}else if(json.result == 'noInfo'){
	    			        		layer.msg('暂无此词库信息', {icon:5,anim:6,time:2000});
	    			        	}
	    			        }
	    				});
					}else{
						UE.delEditor('lexEditor');
						this.initUeditor('lexEditor');//初始化富文本
					}
					this.addEditLexFun();
				},
				//添加编辑词库结构
				addEdITLexDOM : function(){
					var lexStr = '';
					lexStr += '<div class="lexBox layui-clear"><div class="lexTit layui-form-item" style="margin-top:15px;margin-bottom:10px;">';
					lexStr += '<label class="layui-form-label">词库标题：</label><div class="layui-input-block" style="width:82%;"><input id="lexTit" type="text" placeholder="请输入词库标题" class="layui-input"/></div></div>';
					lexStr += '<div class="lexCon layui-form-item"><label class="layui-form-label">词库内容：</label>';
					lexStr += '<div class="layui-input-block" style="width:82%;"><div id="lexEditor"></div></div></div>';
					lexStr += '</div>';
					lexStr += '<div class="lexBot"><button id="addLexBtn" class="layui-btn">添加</button></div>';
					return lexStr;
				}
			};
			table.on('tool(lexTable)',function(obj){
				if(obj.event == 'editLex'){//编辑词条
					var lexTit = $(this).attr('lexTit');
					addEditFlag = false;
					globalOpts = $(this).attr('opts');
					currLexId = $(this).attr('lexId');
					page.showCommonLayer('编辑词库[<span style="color:#F47837;">'+ lexTit +'</span>]');
				}else if(obj.event == 'relLore'){//关联知识点
					currLexId = $(this).attr('lexId');
					bigLexTit = $(this).attr('lexTit');
					layer.open({
						title:'',
						type: 2, 
					  	area: ['1000px', '560px'],
					  	fixed: true, //不固定
					  	maxmin: false, 
					  	shadeClose :false,
					  	closeBtn:0,
					  	content: '/Module/lexManager/jsp/loreRelate.html'
					});	
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
