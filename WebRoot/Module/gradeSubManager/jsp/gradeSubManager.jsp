<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html> 
<html>
  <head>
    <title>年级科目管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache"> 
	<meta http-equiv="keywords" content="知识典管理系统,年级科目管理">
	<meta http-equiv="description" content="年级科目管理">
	<link href="/plugins/layui/css/layui.css" rel="stylesheet" type="text/css"/>
	<link href="/Module/schoolManager/css/schoolManager.css" rel="stylesheet" type="text/css"/>
	<link href="/plugins/pace/pace-theme-flash.min.css" rel="stylesheet" type="text/css"/>
	<script src="/plugins/pace/pace.min.js" type="text/javascript"></script>	
    </head>
	<body style="background:#f2f2f2;">
		<div class="layui-fluid" style="margin-top:15px;">
			<div class="layui-row">
				<div class="layui-col-md12 layui-col-lg12">
					<div class="layui-card">
						<div class="layui-card-header posRel">
	  						<span>年级科目管理</span>
	  						<a id="addGradeBtn" opts="add" class="posAbs newAddBtn" href="javascript:void(0)"><i class="layui-icon layui-icon-add-circle"></i>添加年级</a>
	  					</div>
	  					<div class="layui-card-body" pad15>
	  						<div class="layui-form searchForm layui-clear">
	  							<div class="itemDivs">
		  							<input type="hidden" id="schTypeInp" value="0"/>
									<select id="schTypeSel" lay-filter="schTypeSel">
										<option value=''>请选择学段</option>
									</select>
		  						</div>
		  						<div class="itemDivs">
									<input type="hidden" id="gradeInp"/>
									<div id="gradeBox" class="layui-input-inline">
										<select id="gradeSel" lay-filter="gradeSel">
									       	<option value="">请选择年级</option>
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
		  							<div class="layui-input-inline">
		  								<input type="hidden" id="showStaInp" value="-1"/>
										<select id="showStaSel" lay-filter="showStaSel">
		  								 	<option value="">请选择显示状态(全部)</option>
		  								 	<option value="0">显示</option>
		  								 	<option value="1">隐藏</option>
		  								 </select> 
		  							</div>
		  						</div>
		  						<div class="itemDivs">
		  							<div class="layui-input-inline">
		  								<button id="queryBtn" class="layui-btn"><i class="layui-icon layui-icon-search"></i></button>
		  							</div>
		  						</div>
	  						</div>
	  						<table id="gradSubTable" class="layui-table" lay-filter="gradSubTable"></table>
	  					</div>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script src="/plugins/jquery/jquery.min.js"></script>
   	<script src="/plugins/layui/layui.js"></script>
	<script type="text/javascript">
		var gsId = 0,globalOpts='',addEditFlag=false;
		var infoBySubOpt = 'notGet',
			infoByGsEdOpt = 'notGet',currPage='gradeSubPage';
		layui.config({
			base: '/plugins/frame/js/'
		}).use(['layer','table','baseDataMet','form'], function() {
			var layer = layui.layer,
				table = layui.table,
				baseDataMet = layui.baseDataMet,
				form = layui.form;

			var page = {
				init : function(){
					this.bindEvent();
					this.loadGradSubList();
					baseDataMet.getSubjectList('subjectSel');
					this.initDOM();
				},
				initDOM : function(){
					//创建学段
					var schTypeHtml = baseDataMet.getSchoolTye();
					$('#schTypeSel').append(schTypeHtml);
					form.render();
				},
				bindEvent : function(){
					var _this = this;
					$('#addGradeBtn').on('click',function(){
						globalOpts = $(this).attr('opts');
						addEditFlag = false;
						_this.openEducLayer('添加年级科目');
					});
					//查询
					$('#queryBtn').on('click',function(){
						_this.loadGradSubList();
					});
				},
				loadGradSubList : function(){
					layer.load('1');
					var schTypeInpVal = $('#schTypeInp').val(),
						gradeVal = $('#gradeInp').val(),
						subVal = $('#subInp').val(),
						showStaVal = $('#showStaInp').val();
					var field = {schoolType:schTypeInpVal,showStatus:showStaVal,gName:gradeVal,subId:subVal};
					table.render({
						elem: '#gradSubTable',
						height: 'full-200',
						url : '/common.do?action=getGSubjectData',
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
							{field : 'gName', title: '年级名称', align:'center',fixed: 'left' },
							{field : 'subName', title: '科目',align:'center'},
							{field : 'schoolTypeChi', title: '学校类型',align:'center'},
							{field : 'showStatusChi', title: '显示状态', align:'center'},
							{field : '', title: '操作',  fixed: 'right', align:'center',templet : function(d){
								return '<a class="layui-btn layui-btn-xs editBtn" opts="edit" lay-event="editFun"  gsId="'+ d.id +'"><i class="layui-icon layui-icon-edit"></i>编辑</a>';
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
					  	area: ['600px', '370px'],
					  	fixed: true, //不固定
					  	maxmin: false,
					  	shadeClose :false,
					  	content: '/Module/gradeSubManager/jsp/addEditGS.html',
					  	end : function(){
					  		if(addEditFlag){
					  			_this.loadGradSubList();
					  		}
					  	}
					});	
				}
			};
			table.on('tool(gradSubTable)',function(obj){
				if(obj.event == 'editFun'){//编辑年级科目
					globalOpts = $(this).attr('opts');
					gsId = $(this).attr('gsId');
					addEditFlag = false;
					page.openEducLayer('编辑年级科目');
				}
			});
			page.init();
		});
	</script>
</html>
