<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html> 
<html>
  <head>
    <title>学校管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache"> 
	<meta http-equiv="keywords" content="知识典管理系统,学校信息管理">
	<meta http-equiv="description" content="学校信息管理添加修改">
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
						<div class="layui-card-header posRel"">
	  						<span>学校信息管理</span>
	  						<a id="addSchool" opts='add' class="posAbs newAddBtn" href="javascript:void(0)" style="right:15px;"><i class="layui-icon layui-icon-add-circle"></i>添加学校</a>
	  					</div>
	  					<div class="layui-card-body">
	  						<!-- 查询条件 -->
		  					<div class="layui-form searchForm layui-clear">
		  						<div class="itemDivs" style="width:180px;">
		  							<input id="schNameInp" class="layui-input" placeholder="请输入学校名称(30字以内)" maxlength="30"/>
		  						</div>
		  						<div class="itemDivs" style="width:130px;">
		  							<input type="hidden" id="schTypeInp" value="0"/>
									<select id="schTypeSel" lay-filter="schTypeSel">
										<option value=''>请选择学段</option>
									</select>
		  						</div>
		  						<div class="itemDivs" style="width:150px;">
		  							<div class="layui-input-inline">
		  								<input type="hidden" id="provInp"/>
										<select  name="province" lay-filter="province" class="province">
									       	<option value="">请选择省份</option>
									    </select>
		  							</div>
		  						</div>
		  						<div class="itemDivs" style="width:150px;">
		  							<div class="layui-input-inline">
		  								<input type="hidden" id="cityInp"/>
										<select name="city" lay-filter="city" disabled>
									       	<option value="">请选择城市</option>
									    </select>
		  							</div>
		  						</div>
		  						<div class="itemDivs" style="width:150px;">
		  							<input type="hidden" id="countyInp"/>
		  							<div class="layui-input-inline">
		  								<select name="area" lay-filter="area" disabled>
								       		<option value="">请选择县/区</option>
								     	</select>
		  							</div>
		  						</div>
		  						<div class="itemDivs">
		  							<div class="layui-input-inline">
		  								<button id="queryBtn" class="layui-btn"><i class="layui-icon layui-icon-search"></i></button>
		  							</div>
		  						</div>
		  						<!--  a class="resetBtn" href="javascript:void(0)"><i class="layui-icon layui-icon-refresh"></i>重置</a-->
		  					</div>
	  						<div id="shoolList">
	  							<table id="schoolTable" class="layui-table" lay-filter="schoolTable"></table>
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
		var addEditFlag = false,schId=0,globalOpts='';
		layui.config({
			base : "/plugins/frame/js/" //address.js的路径
		}).use([ 'layer', 'jquery', 'address','table','form','baseDataMet'], function() {
			var layer = layui.layer, $ = layui.jquery, 
				address = layui.address(),
				table=layui.table,
				form = layui.form,
				baseDataMet = layui.baseDataMet;
			var page = {
				init:function(){
					this.loadSchoolList();
					this.initDOM();
					this.bindEvent();
				},
				initDOM : function(){
					//创建学段
					var schTypeHtml = baseDataMet.getSchoolTye();
					$('#schTypeSel').append(schTypeHtml);
				},
				bindEvent : function(){
					var _this = this;
					$('#addSchool').on('click',function(){
						globalOpts = $(this).attr('opts');
						addEditFlag = false;
						_this.openEducLayer('添加学校');
					});
					//查询
					var _this = this;
					$('#queryBtn').on('click',function(){
						_this.loadSchoolList();
					});
				},
				loadSchoolList : function(){
					var schNameInpVal = $.trim($('#schNameInp').val()),
						schTypeInpVal = $('#schTypeInp').val(),
						provInpVal = $('#provInp').val(),
						cityInpVal = $('#cityInp').val(),
						countyInpVal = $('#countyInp').val();
					var field = {prov:provInpVal,city:cityInpVal,county:countyInpVal,town:'',
									schoolType:schTypeInpVal,schoolName:schNameInpVal};
					table.render({
						elem: '#schoolTable',
						height: 'full-200',
						url : '/school.do?action=getPageSchoolData',
						method : 'post',
						where:field,
						page : true,
						even : true,
						limit : 10,
						limits:[10,20,30,40],
						text: {none : '暂无相关数据'},
						cols : [[
							{field : '', title: '序号', type:'numbers',fixed: 'left' , align:'center'},
							{field : 'schoolName', title: '学校名称', align:'center'},
							{field : 'schoolType', title: '学段', align:'center'},
							{field : 'yearSystem', title: '学年制',align:'center'},
							{field : 'prov', title: '省份',align:'center'},
							{field : 'city', title: '城市',align:'center'},
							{field : 'county', title: '县/区', align:'center'},
							{field : '', title: '操作',  fixed: 'right', align:'center',templet : function(d){
								return '<a class="layui-btn layui-btn-xs editBtn" opts="edit" schoolName="'+ d.schoolName +'" lay-event="editSchool"  schId="'+ d.id +'"><i class="layui-icon layui-icon-edit"></i>编辑</a>';
							}},
						]],
						done : function(res, curr, count){
							layer.closeAll('loading');
						}
					});
				},
				openEducLayer : function(title){
					var _this = this;
					var fullIndex = layer.open({
						title:title,
						type: 2,
					  	area: ['600px', '450px'],
					  	fixed: true, //不固定
					  	maxmin: false,
					  	shadeClose :false,
					  	content: '/Module/schoolManager/jsp/addEditSchool.html',
					  	end : function(){
					  		if(addEditFlag){
					  			_this.loadSchoolList();
					  		}
					  	}
					});	
				}
			};
			table.on('tool(schoolTable)',function(obj){
				if(obj.event == 'editSchool'){
					var opts = $(this).attr('opts'),schoolName=$(this).attr('schoolName');
					schId = $(this).attr('schId');
					globalOpts = $(this).attr('opts');
					addEditFlag = false;
					page.openEducLayer('编辑学校[<span style="color:#F47837">'+ schoolName +'</span>]');
				}
			});
			page.init();
		});
	</script>
</html>
