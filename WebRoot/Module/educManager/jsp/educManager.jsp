<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html> 
<html>
  <head>
    <title>教材管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache"> 
	<meta http-equiv="keywords" content="知识典管理系统,教材管理">
	<meta http-equiv="description" content="教材添加修改">
	<link href="/plugins/layui/css/layui.css" rel="stylesheet" type="text/css"/>
	<link href="/plugins/pace/pace-theme-flash.min.css" rel="stylesheet" type="text/css"/>
	<link href="/Module/educManager/css/addEditEduc.css" rel="stylesheet" type="text/css"/>
	<script src="/plugins/pace/pace.min.js" type="text/javascript"></script>	
    </head>
	<body style="background:#f2f2f2;">
		<div class="layui-fluid" style="margin-top:15px;">
			<div class="layui-row">
				<div class="layui-col-md12 layui-col-lg12">
					<div class="layui-card">
						<div class="layui-card-header posRel">
	  						<span>教材管理</span>
	  						<a id="addEduc" opts='add' class="posAbs newAddBtn" href="javascript:void(0)" style="right:15px;"><i class="layui-icon layui-icon-add-circle"></i>添加教材</a>
	  					</div>
	  					
	  					<div class="layui-card-body">
	  						<!-- 查询条件 -->
		  					<div class="layui-form searchForm layui-clear">
		  						<div class="itemDivs">
		  							<div class="layui-input-inline">
		  								<input type="hidden" id="editInp" value="0"/>
										<select id="editionSel" lay-filter="editionSel">
											<option value="">请选择出版社</option>
										</select> 
		  							</div>
		  						</div>
		  						<div class="itemDivs">
		  							<div class="layui-input-inline">
		  								<input type="hidden" id="subInp" value="0"/>
										<select id="subjectSel" lay-filter="subjectSel">
		  								 	<option value="">请选择科目</option>
		  								 </select> 
		  							</div>
		  						</div>
		  						<div class="itemDivs">
		  							<input type="hidden" id="gradeInp" value="0"/>
		  							<div class="layui-input-inline">
		  								 <select id="gradeSel" lay-filter="gradeSel">
		  								 	<option value="">请选择年级</option>
		  								 </select> 
		  							</div>
		  						</div>
		  						<div class="itemDivs">
		  							<div class="layui-input-inline">
		  								<button id="queryBtn" class="layui-btn"><i class="layui-icon layui-icon-search"></i></button>
		  							</div>
		  						</div>
		  					</div>
	  						<div id="editionList">
	  							<table id="educTable" class="layui-table" lay-filter="educTable"></table>
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
		var educId = 0,addEditFlag = false,globalOpts='',currPage='educPage';
		var infoBySubOpt = 'getGradeOpt',//根据当前科目信息是否需要获取年级
			infoByGsEdOpt = 'notGet';
		layui.config({
			base: '/plugins/frame/js/'
		}).extend({ //设定组件别名
			baseDataMet: 'baseDataMet'// 表示模块文件的名字
		}).use(['layer','table','form','baseDataMet'], function() {
			var layer = layui.layer,
				table = layui.table,
				baseDataMet = layui.baseDataMet,
				form = layui.form;
			
			
			var page = {
				init : function(){
					//获取 出版社 科目列表
					baseDataMet.getEditionList('editionSel');
					baseDataMet.getSubjectList('subjectSel');
					this.bindEvent();
					this.loadEducList();
				},
				bindEvent : function(){
					//添加教材
					var _this = this;
					$('#addEduc').on('click',function(){
						addEditFlag = false;
						globalOpts = $(this).attr('opts');
						_this.openEducLayer('添加教材');
					});
					//查询
					$('#queryBtn').on('click',function(){
						_this.loadEducList();
					});
				},
				loadEducList : function(){
					layer.load('1');
					var ediIdVal = $('#editInp').val();
						gradVal = $('#gradeInp').val();
					var field = {ediId:$('#editInp').val(),gsId:gradVal};
					table.render({
						elem: '#educTable',
						height: 'full-200',
						url : '/common.do?action=getEducationData',
						method : 'post',
						where:field,
						page : true,
						even : true,
						limit : 10,
						cellMinWidth: 100,
						limits:[10,20,30,40],
						text: {none : '暂无相关数据'},
						cols : [[
							{field : '', title: '序号', type:'numbers',fixed: 'left' , align:'center'},
							{field : 'schoolType', title: '学校类型', align:'center'},
							{field : 'gradeName', title: '年级',align:'center'},
							{field : 'ediName', title: '出版社', align:'center'},
							{field : 'subName', title: '科目', align:'center'},
							{field : 'eduColume', title: '卷册', align:'center'},
							{field : 'showStatusChi', title: '显示状态', align:'center'},
							{field : '', title: '操作',  fixed: 'right', align:'center',templet : function(d){
								return '<a class="layui-btn layui-btn-xs editBtn" opts="edit" lay-event="editFun"  eduId="'+ d.eduId +'"><i class="layui-icon layui-icon-edit"></i>编辑</a>';
							}},
						]],
						done : function(res, curr, count){
							layer.closeAll('loading');
							
						}
					});
				},
				openEducLayer : function(title){
					var _this = this;
					layer.open({
						title:title,
						type: 2,
					  	area: ['600px', '350px'],
					  	fixed: true, //不固定
					  	maxmin: false,
					  	shadeClose :false,
					  	content: '/Module/educManager/jsp/addEditEduc.html',
					  	end : function(){
					  		if(addEditFlag){
					  			_this.loadEducList();
					  		}
					  	}
					});	
				}
			};
			table.on('tool(educTable)',function(obj){
				if(obj.event == 'editFun'){
					var opts = $(this).attr('opts');
					educId = $(this).attr('eduId');
					globalOpts = $(this).attr('opts');
					addEditFlag = false;
					page.openEducLayer('编辑教材');
				}
			});
			page.init();
		});
	</script>
</html>
