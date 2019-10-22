<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html> 
<html>
  <head>
    <title>学生导师管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache"> 
	<meta http-equiv="keywords" content="知识典管理系统,学生导师管理">
	<meta http-equiv="description" content="学生导师管理">
	<link href="/plugins/layui/css/layui.css" rel="stylesheet" type="text/css"/>
	<link href="/plugins/pace/pace-theme-flash.min.css" rel="stylesheet" type="text/css"/>
	<script src="/plugins/pace/pace.min.js" type="text/javascript"></script>
	<style>
		.itemDivs{float:left;margin-right:15px;}
		.itemDivs.hasMargL{margin-left:-16px;}
		#stuNameInp,#ntNameInp{display:none;}
		.hasOverDate{color:#FF5722;}
		.ingDate{color:#1E9FFF;}
		.resetBtn{margin-top:15px;float:left;}
		.resetBtn:hover{color:#F47837;}
		.dateItem{width:90%;margin:10px auto 0;height:40px;line-height:40px;color:#333;}
		.dateItem span,.dateItem p{float:left;}
		.editDateInp{width:60%;float:left;}
		.saveBtn{width:120px;height:35px;line-height:35px;display:block;margin:0 auto;background:#009f95;color:#fff;cursor:pointer;border-radius:4px;text-align:center;}
		.saveBtn:hover{opacity:.8;}
	</style>	
    </head>
	<body style="background:#f2f2f2;">
		<div class="layui-fluid" style="margin-top:15px;">
			<div class="layui-row">
				<div class="layui-col-md12 layui-col-lg12">
					<div class="layui-card">
	  					<div class="layui-card-body" pad15>
	  						<div class="layui-form">
	  							<div class="itemDivs" style="width:100px;">
									<div class="layui-input-inline">
										<select name="stAccNameSel" lay-filter="stAccNameSel">
									       	<option value="1">学生账号</option>
									       	<option value="2">学生姓名</option>
									    </select>
									</div>
								</div>
								<div class="itemDivs hasMargL" style="width:120px;">
									<div class="layui-input-inline">
										<input id="stuAccInp" type="text" placeholder="请输入学生账号" class="layui-input" autocomplete="off""/>
										<input id="stuNameInp" type="text" placeholder="请输入学生姓名" class="layui-input" autocomplete="off""/>
									</div>
								</div>
								<div class="itemDivs" style="width:100px;">
									<div class="layui-input-inline">
										<select name="ntAccNameSel" lay-filter="ntAccNameSel">
									       	<option value="1">导师账号</option>
									       	<option value="2">导师姓名</option>
									    </select>
									</div>
								</div>
								<div class="itemDivs hasMargL" style="width:120px;">
									<div class="layui-input-inline">
										<input id="ntAccInp" type="text" placeholder="请输入导师账号" class="layui-input" autocomplete="off""/>
										<input id="ntNameInp" type="text" placeholder="请输入导师姓名" class="layui-input" autocomplete="off""/>
									</div>
								</div>
								<div class="itemDivs" style="width:130px;">
									<div class="layui-input-inline">
										<input type="hidden" id="schTypeInp" value="0"/>
										<select id="shTypeSel" lay-filter="shTypeSel">
									       	<option value="">请选择学段</option>
									       	<option value="1">小学</option>
									       	<option value="2">初中</option>
									       	<option value="3">高中</option>
									    </select>
									</div>
								</div>
								<div class="itemDivs" style="width:130px;">
									<input type="hidden" id="subjectInp" value="0"/>
									<div class="layui-input-inline">
										<select id="subjectSel" lay-filter="subjectSel">
									       	<option value="">请选择学科</option>
									    </select>
									</div>
								</div>
								<div class="itemDivs" style="width:180px;">
									<input type="hidden" id="bindStaInp" value="-2"/>
									<div class="layui-input-inline">
										<select id="bindStaSel" lay-filter="bindStaSel">
									       	<option value="">请选择绑定状态(全部)</option>
									       	<option value="-1">免费试用</option>
									       	<option value="1">付费绑定</option>
									       	<option value="2">免费绑定</option>
									       	<option value="0">取消绑定</option>
									    </select>
									</div>
								</div>
								
								<div class="itemDivs" style="margin-top:10px;">
									<div class="layui-input-inline" style="width:150px;">
										 <input id="stDate" type="text" placeholder="请选择绑定起始日期" readonly class="layui-input"/>
									</div>
									<span style="margin:0 5px;">至</span>
									<div class="layui-input-inline" style="width:150px;">
										 <input id="edDate" type="text" placeholder="请选择绑定结束日期" readonly class="layui-input"/>
									</div>
								</div>
								<div class="itemDivs" style="margin-right:15px;margin-top:10px;">
									<div class="layui-input-inline">
										<button id="queryBtn" class="layui-btn"><i class="layui-icon layui-icon-search"></i></button>
									</div>
								</div>
								<a class="resetBtn" href="javascript:void(0)">重置<i class="layui-icon layui-icon-refresh"></i></a>
							</div>
	  						<div id="editionList">
	  							<table id="subTable" class="layui-table" lay-filter="subTable"></table>
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
		var globalDate = '',ntsId = 0,isUpFlag = false,globalIndex = 0,globNtName = '';
		layui.use(['layer','table','form','laydate'], function() {
			var layer = layui.layer,
				table = layui.table,form=layui.form,laydate=layui.laydate;
			laydate.render({elem: '#stDate',type: 'month'});
			laydate.render({elem: '#edDate',type: 'month'});
			form.on('select(stAccNameSel)',function(data){
				if(data.value == 1){
					$('#stuAccInp').show().val('');
					$('#stuNameInp').hide().val('');
				}else if(data.value == 2){
					$('#stuAccInp').hide().val('');
					$('#stuNameInp').show().val('');
				}
			});
			form.on('select(ntAccNameSel)',function(data){
				if(data.value == 1){
					$('#ntAccInp').show().val('');
					$('#ntNameInp').hide().val('');
				}else if(data.value == 2){
					$('#ntAccInp').hide().val('');
					$('#ntNameInp').show().val('');
				}
			});
			//选择学段加载学科
			form.on('select(shTypeSel)',function(data){
				if(data.value == ''){
					$('#schTypeInp').val(0);
					$('#subjectInp').val(0);
					$('#subjectSel').html('<option value="">请选择学科</option>');
					form.render();
				}else{
					$('#schTypeInp').val(data.value);
					page.renderSubList(data.value);
				}
				page.loadStNtList();
			});
			form.on('select(subjectSel)',function(data){
				if(data.value == ''){
					$('#subjectInp').val(0);
				}else{
					$('#subjectInp').val(data.value);
				}
				page.loadStNtList();
			});
			//绑定状态
			form.on('select(bindStaSel)',function(data){
				data.value == '' ? $('#bindStaInp').val(-2) : $('#bindStaInp').val(data.value);
				page.loadStNtList();
			});
			var page = {
				init : function(){
					this.bindEvent();
					this.loadStNtList();
				},
				enterPress : function(){
					var e = e || window.event;
					if(e.keyCode == 13){
						this.loadStNtList();
					}
				},
				bindEvent : function(){
					var _this = this;
					$('#queryBtn').on('click',function(){
						_this.loadStNtList();
					});
					$('#stuAccInp').on('keypress',function(){
						_this.enterPress(event);
					});
					$('#stuNameInp').on('keypress',function(){
						_this.enterPress(event);
					});
					$('#ntAccInp').on('keypress',function(){
						_this.enterPress(event);
					});
					$('#ntNameInp').on('keypress',function(){
						_this.enterPress(event);
					});
					$('.resetBtn').on('click',function(){
						$('#stuAccInp').val('');
						$('#stuNameInp').val();
						$('#ntAccInp').val('');
						$('#ntNameInp').val('');
						$('#stDate').val('');
						$('#edDate').val('');
						$('#shTypeSel').val('');
						$('#schTypeInp').val(0);
						$('#subjectInp').val(0);
						$('#subjectSel').val('');
						$('#bindStaInp').val(-2);
						$('#bindStaSel').val('');
						_this.loadStNtList();
						form.render();
						
					});
				},
				renderSubList : function(schType){
					layer.load('1');
					$.ajax({ 
						url : '/common.do?action=getSubjectDataBySchType',
						dataType:'json',
						data:{schType:schType},
						type:'post',
						timeout:10000,  
						success:function(json){  
							layer.closeAll('loading');
							if(json.result == 'success'){
								var str = '<option value="">请选择学科</option>';
								for(var i=0;i<json.subList.length;i++){
									str += '<option value="'+ json.subList[i].subId +'">'+ json.subList[i].subName +'</option>';
								}
								$('#subjectSel').html(str);
								form.render();
							}else if(json.result == 'noInfo'){
								layer.msg('暂无科目信息');
							}
						},  
						error:function(xhr,type,errorThrown){  
							console.log(type);  
						}  
					});
				},
				loadStNtList : function(){
					var stuAccInp = $.trim( $('#stuAccInp').val() ),
						stuNameInp = $.trim( $('#stuNameInp').val() ),
						ntAccInp = $.trim( $('#ntAccInp').val() ),
						ntNameInp = $.trim( $('#ntNameInp').val() ),
						schTypeInp = $('#schTypeInp').val(),
						subjectInp = $('#subjectInp').val(),
						bindStaInp = $('#bindStaInp').val(),
						stDate = $('#stDate').val(),
						eDate = $('#edDate').val();
					if(stDate == '' && eDate != ''){
						layer.msg('请选择绑定起始日期');
						return;
					}
					if(stDate != '' && eDate == ''){
						layer.msg('请选择绑定结束日期');
						return;
					}
					if(stDate > eDate){
						layer.msg('绑定起始日期不能大于绑定结束日期');
						return;
					}
					layer.load('1');
					var field = {stuAccount:escape(stuAccInp),stuRealName:escape(stuNameInp),ntAccount:escape(ntAccInp),ntRealName:escape(ntNameInp),
									subId:subjectInp,schoolType:schTypeInp,bindStatus:bindStaInp,bindSdate:stDate,bindEdate:eDate};
					table.render({
						elem: '#subTable',
						height: 'full-200',
						url : '/ntsBind.do?action=getNtsBindData',
						method : 'post',
						where:field,
						page : true,
						even : true,
						limit : 10,
						limits:[10,20,30,40],
						text: {none : '暂无相关数据'},
						cols : [[
							{field : '', title: '序号', type:'numbers',fixed: 'left' , align:'center'},
							{field : 'stuAccount', title: '学生账号', align:'center',width:'120'},
							{field : 'stuRealName', title: '学生姓名',align:'center',width:'120'},
							{field : 'ntAccount', title: '绑定导师账号', align:'center',width:'120'},
							{field : 'ntRealName', title: '绑定导师姓名',align:'center',width:'120'},
							{field : 'ntSubName', title: '担任学科',align:'center',width:'150'},
							{field : 'bindStatusChi', title: '绑定状态',align:'center',width:'150'},
							{field : 'validInfo', title: '是否到期',align:'center',width:'150',templet:function(d){
								if(d.validInfo == '已到期'){
									return '<span class="hasOverDate">已到期</span>';
								}else if(d.validInfo == '未到期'){
									return '<span class="ingDate">未到期</span>';
								}else if(d.validInfo == '升学清除'){
									return '<span class="hasOverDate">升学清除</span>';
								}
							}},
							{field : 'bindDate', title: '绑定日期',align:'center',width:'150'},
							{field : 'endDate', title: '绑定到期时间',align:'center',width:'180'},
							{field : '', title: '操作',  fixed: 'right', align:'center',width:'150',templet : function(d){
								if(d.bindStatus == '-1' || d.bindStatus == '2'){
									return '<a class="layui-btn layui-btn-xs editBtn" stuName="'+ d.stuRealName +'" ntName="'+ d.ntRealName +'" bindSta="'+ d.bindStatus +'" lay-event="editFun"  ntsId="'+ d.ntsId +'" endDate="'+ d.endDate +'"><i class="layui-icon layui-icon-edit"></i>编辑到期时间</a>';
								}else{
									return '';
								}
							}},
						]],
						done : function(res, curr, count){
							layer.closeAll('loading');
						}
					});
				},
				createDateDOM : function(stuName,globNtName){
					var str = '';
					str += '<div class="dateItem"><span>到期期限：</span>';
					str += '<input type="text" id="editEDate" class="layui-input editDateInp" placeholder="请选择到期日期" readonly/></div>';
					str += '<div class="dateItem"><span>学生姓名：</span>';
					str += '<p>'+ stuName +'</p></div>';
					str += '<div class="dateItem"><span>导师姓名：</span>';
					str += '<p>'+ globNtName +'</p></div>';
					str += '<span class="saveBtn">保存</span>';
					return str;
				},
				saveEditDate : function(){
					$('.saveBtn').on('click',function(){
						var editEDate = $('#editEDate').val();
						if(editEDate == ''){
							layer.msg('请选择到期日期');
							return;
						}
						if(globalDate > editEDate){
							layer.msg('当前到期日期不能小于之前到期日期');
							return;
						}
						layer.load('1');
						$.ajax({ 
							url : '/ntsBind.do?action=upBind',
							dataType:'json',
							data:{ntsId:ntsId,bindDate:$('#editEDate').val()},
							type:'post',
							timeout:10000,  
							success:function(json){  
								layer.closeAll('loading');
								if(json.result == 'success'){
									layer.msg('修改成功',{icon:1,time:1200},function(){
										isUpFlag = true;
										layer.close(globalIndex);
	   				        		});
								}else if(json.result == 'error'){
									layer.msg('服务器错误');
								}else if(json.result == 'paraDiff'){
									layer.msg('该导师辅导学段和该学生目前所处的学段不一致');
								}else if(json.result == 'checkFail'){
									layer.msg('该导师辅审核未通过');
								}else if(json.result == 'notUpdate'){
									layer.msg('该科目下存在其他有效绑定导师，暂不能修改');
								}else if(json.result == 'notModify'){
									layer.msg('暂不能修改付费绑定或已升学绑定信息');
								}
							},  
							error:function(xhr,type,errorThrown){  
								console.log(type);  
							}  
						});
					});
				}
			};
			table.on('tool(subTable)',function(obj){
				if(obj.event == 'editFun'){
					var bindSta = $(this).attr('bindSta'),
						stuName = $(this).attr('stuName'),
					globalDate = $(this).attr('endDate');
					ntsId = $(this).attr('ntsId');
					globNtName = $(this).attr('ntName');
					isUpFlag = false;
					var editDateDOM = page.createDateDOM(stuName,globNtName);
					globalIndex = layer.open({
						title:'编辑学生['+ stuName +']绑定期限',
						type: 1,
					  	area: ['350px', '260px'],
					  	fixed: true, //不固定
					  	maxmin: false,
					  	shadeClose :false,
					  	skin : 'layui-layer-molv',
					  	content: editDateDOM,
					  	end : function(){
					  		if(isUpFlag){
					  			page.loadStNtList();
					  		}
					  	}
					});	
					$('#editEDate').val(globalDate);
					laydate.render({elem: '#editEDate'});
					page.saveEditDate();
				}
			});
			page.init();
		});
	</script>
</html>
