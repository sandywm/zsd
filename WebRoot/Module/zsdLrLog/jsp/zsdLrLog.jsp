<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html> 
<html>
	<head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache"> 
	<meta http-equiv="keywords" content="知识典管理系统,知识典关联日志">
	<meta http-equiv="description" content="知识典关联日志">
	<link href="/plugins/layui/css/layui.css" rel="stylesheet" type="text/css"/>
	<link href="/Module/newEditionLore/css/newEditionLore.css" rel="stylesheet" type="text/css"/>
	<link href="/plugins/pace/pace-theme-flash.min.css" rel="stylesheet" type="text/css"/>
	<script src="/plugins/pace/pace.min.js" type="text/javascript"></script>	
	<title>知识典关联日志</title>
	</head>
	<body style="background:#f2f2f2;">
		<div class="layui-fluid" style="margin-top:15px;">
			<div class="layui-row">
				<div class="layui-col-md12 layui-col-lg12">
					<div class="layui-card">
						<div class="layui-card-header">
	  						<span style="float:left">知识典关联日志</span>
	  						<div class="layui-form" style="float:right;margin:3px 0px 0 35px;">
								<div class="itemDivs" style="width:180px;">
									<div class="layui-input-inline">
										<input type="hidden" id="editInp" value="0"/>
										<select id="editionSel" lay-filter="editionSel">
										<option value=''>请选择出版社(全部)</option></select> 
									</div>
								</div>
								<div class="itemDivs" style="width:180px;">
									<div class="layui-input-inline">
										<input type="hidden" id="relateStaInp" value="-1"/>
										<select id="relateStaSel" lay-filter="relateStaSel">
											<option value=''>请选择关联状态(全部)</option>
											<option value='1'>成功</option>
											<option value='0'>失败</option>
										</select> 
									</div>
								</div>
								<div class="itemDivs">
									<div class="layui-input-inline">
										<button id="queryBtn" class="layui-btn"><i class="layui-icon layui-icon-search"></i></button>
									</div>
								</div>
							</div>
	  					</div>
	  					<div class="layui-card-body" pad15>
	  						<table id="zsdLrLogTable" class="layui-table" lay-filter="zsdLrLogTable"></table>
	  					</div>
					</div>
				</div>
			</div>
		</div>
		
		
		
		
		<script src="/plugins/jquery/jquery.min.js"></script>
   		<script src="/plugins/layui/layui.js"></script>	
  		<script type="text/javascript">
  			layui.use(['form','table'],function(){
  				var form = layui.form,table = layui.table;
	  			form.on('select(editionSel)', function(data){
	  				data.value == '' ? $('#editInp').val(0) : $('#editInp').val(data.value.split(':')[0]);
	  				page.loadZsdLrLogList();
	  			});
	  			form.on('select(relateStaSel)', function(data){
	  				data.value == '' ? $('#relateStaInp').val(-1) : $('#relateStaInp').val(data.value);
	  				page.loadZsdLrLogList();
	  			});
  				var page = {
  					init : function(){
  						this.loadEdition();
  						this.bindEvent();
  						this.loadZsdLrLogList();
  					},
  					bindEvent : function(){
  						var _this = this;
  						$('#queryBtn').on('click',function(){
  							_this.loadZsdLrLogList();
  						});
  					},
  					loadEdition : function(){
  						layer.load('1');
  						$.ajax({
  							type:'post',
  					        dataType:'json',
  					        url:'/common.do?action=getEditionData',
  					        data : {showStatus:0,opt:2},
  					        success:function (json){
  					        	layer.closeAll("loading");
  					        	if(json['msg'] == 'success'){
  					        		var str_edit = '';
  					        		for(var i=0;i<json.data.length;i++){
  					    				str_edit += '<option value="'+ json.data[i].id +':'+ json.data[i].ediName +'">'+ json.data[i].ediName +'</option>';
  					    			}
  					        		$('#editionSel').append(str_edit);
  					        		form.render();
  					        	}else if(json['msg'] == 'noInfo'){
  					        		layer.msg('暂无出版社信息',{icon:5,anim:6,time:2000});
  					        	}
  					        }   
  						});
  					},
  					loadZsdLrLogList : function(){
  						var ediInp = $('#editInp').val(),
  							relateStaInp = $('#relateStaInp').val();
  						var field = {ediId : ediInp,relateStatus:relateStaInp};
  						table.render({
  							elem: '#zsdLrLogTable',
  							height: 'full-150',
  							url : '/lrLog.do?action=getLRLData',
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
  								{field : 'cptName', title: '章节名称',align:'center'},
  								{field : 'loreName', title: '知识点名称', align:'center'},
  								{field : 'schoolType', title: '学段',width:100,align:'center'},
  								{field : 'gradeName', title: '年级',width:120,align:'center'},
  								{field : 'subName', title: '科目',width:100,align:'center'},
  								{field : 'ediName', title: '出版社',align:'center'},
  								{field : 'eduVolume', title: '上下册',width:100,align:'center'},
  								{field : 'relateStatus', title: '关联状态',width:100,align:'center',templet : function(d){
  									if(d.relateStatus == 0){
  										return '<span class="failTips">失败</span>';
  									}else if(d.relateStatus == 1){
  										return '<span class="succTips">成功</span>';
  									}
  								}},
  								{field : 'relateUser', title: '操作人员',width:120,align:'center'},
  								{field : 'relateDate', title: '关联日期',align:'center'},
  								{field : '', title: '操作',width:120,fixed:'right',align:'center',templet:function(d){
  									return '<a relateSta="'+ d.relateStatus +'" relateRes="'+ d.relateResult +'" lay-event="viewRes" class="layui-btn layui-btn-primary layui-btn-xs" href="javascript:void(0)"><i class="layui-icon layui-icon-search"></i>查看</a>';
  								}},
  							]],
  							done : function(res, curr, count){
  								layer.closeAll('loading');
  								
  							}
  						});
  					}
  				};
  				table.on('tool(zsdLrLogTable)',function(obj){
  					if(obj.event === 'viewRes'){
  						var currTipsCol = '';
  						var relateRes = $(this).attr('relateRes'),
  							relateSta = $(this).attr('relateSta');
  						if(relateSta == 0){
  							currTipsCol = 'failTips';
  						}else{
  							currTipsCol = 'succTips';
  						}
  						layer.alert('<span class="'+ currTipsCol +'">'+ relateRes +'</span>', {
  							title : '关联结果',
  							shadeClose : true,
  							btn : [],
  						    skin: 'layer-ext-moon' //该皮肤由layer.seaning.com友情扩展。关于皮肤的扩展规则，去这里查阅
  						});
  					}
  				});
  				page.init();
  			});
		</script>
	</body>
</html>
