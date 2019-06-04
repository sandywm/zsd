<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html> 
<html>
  <head>
    <title>关联知识点</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache"> 
	<meta http-equiv="keywords" content="知识典管理系统,关联知识点">
	<meta http-equiv="description" content="关联知识点">
	<link href="/plugins/layui/css/layui.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="/plugins/jquery-easyui/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="/plugins/jquery-easyui/themes/icon.css"/>   
	<link href="/Module/loreRelateManager/css/relateManager.css" rel="stylesheet" type="text/css"/>
	<link href="/plugins/pace/pace-theme-flash.min.css" rel="stylesheet" type="text/css"/>
	<script src="/plugins/pace/pace.min.js" type="text/javascript"></script>	
    </head>
	<body>
		<div class="layui-fluid" style="margin-top:15px;">
			<div class="layui-row">
				<div class="layui-col-md12 layui-col-lg12">
					<div class="layui-card">
						<div class="layui-card-header loreHeader">
	  						<span style="float:left">关联知识点</span>
	  						<div class="layui-form" style="float:right;margin:3px 15px 0 35px;">
								<div class="itemDivs" style="width:130px;">
									<div class="layui-input-inline">
										<input type="hidden" id="subInp" value="0"/>
										<select id="subjectSel" lay-filter="subjectSel">
										 	<option value="">请选择科目</option>
										 </select> 
									</div>
								</div>
								<div class="itemDivs" style="width:130px;">
									<input type="hidden" id="gradeInp"/>
									<div class="layui-input-inline">
										 <select id="gradeSel" lay-filter="gradeSel">
										 	<option value="">请选择年级</option>
										 </select> 
									</div>
								</div>
								<div class="itemDivs" style="width:150px;">
									<div class="layui-input-inline">
										<input type="hidden" id="editInp" value="0"/>
										<select id="editionSel" lay-filter="editionSel"></select> 
									</div>
								</div>
								<div class="itemDivs" style="width:120px;">
									<div class="layui-input-inline">
										<input type="hidden" id="eduColumeInp"/>
										<select id="eduColumeSel" lay-filter="eduColumeSel">
											<option value=''>请选择教材</option>
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
	  					<div class="layui-card-body" style="margin-top:25px;" pad15>
	  						<p class="tipsTxt_rel">请根据对应的条件查看对应的教材信息！</p>
	  						<div id="loreTree" data-options="animate:true,lines:true"></div>
	  					</div>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script src="/plugins/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="/plugins/jquery-easyui/jquery.easyui.min.js"></script>
   	<script src="/plugins/layui/layui.js"></script>
	<script type="text/javascript">
		var infoBySubOpt = 'getGradeOpt',editAll='',//根据学科获取年级的信息
			infoByGsEdOpt='geteduC',//根据年级出版社获取教材信息 need
			addEditFlag  = false,
			cptId=0,currPage='relateManager',loreBigId=0,loreBigName='',isRelatePage='relate';
		layui.config({
			base: '/plugins/frame/js/'
		}).use(['layer','form','baseDataMet'], function() {
			var layer = layui.layer,
				form = layui.form,
				baseDataMet = layui.baseDataMet;
			
			var page = {
				init : function(){
					this.initDOM();
					this.bindEvent();
				},
				initDOM : function(){
					//获取科目
					baseDataMet.getSubjectList('subjectSel');
					//获取出版社
					baseDataMet.getEditionList('editionSel');
				},
				bindEvent : function(){
					//onClick:function(node){showDetailView(node.attributes);}
					$('#queryBtn').on('click',function(){
						var eduId = $('#eduColumeInp').val(),
							ediId = $('#editInp').val();
						if(eduId == ''){
							layer.msg('请选择教材',{icon:5,anim:6,time:2200});
		    				return;
						}
						layer.load('1');
						$('#loreTree').tree({  
							url: '/loreRelate.do?action=showLoreSimpleTree&eduId=' + eduId + '&ediId=' + ediId,
							loadFilter: function(data){ 
								layer.closeAll('loading');
								if(data.length > 0){
									$('.tipsTxt_rel').hide();
									if (data.d){  
										return data.d;  
									} else {  
										return data;  
									}   
								}else{
									$('.tipsTxt_rel').show().html('暂无此教材的章节知识点信息');
								}
							},
							onClick :function(node){
								loreBigId = node.attributes.loreId,
								loreBigName = node.attributes.loreName;
								layer.open({
									title:'',
									type: 2, 
								  	area: ['1000px', '560px'],
								  	fixed: true, //不固定
								  	maxmin: false, 
								  	shadeClose :false,
								  	closeBtn:0,
								  	content: '/Module/loreManager/jsp/loreRelate.html',
								  	end : function(){
								  		window.localStorage.removeItem("relateObj");
								  	}
								});	
							}
					 	});
					});
				}
			};
			page.init();
		});
	</script>
</html>
