<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html> 
<html>
  <head>
    <title>用户管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache"> 
	<meta http-equiv="keywords" content="知识典管理系统,用户管理">
	<meta http-equiv="description" content="用户管理">
	<link href="/plugins/layui/css/layui.css" rel="stylesheet" type="text/css"/>
	<link href="/Module/schoolManager/css/schoolManager.css" rel="stylesheet" type="text/css"/>
	<link href="/plugins/pace/pace-theme-flash.min.css" rel="stylesheet" type="text/css"/>
	<script src="/plugins/pace/pace.min.js" type="text/javascript"></script>	
	<style>
		.itemDivs{width:150px;}
	</style>
    </head>
	<body style="background:#f2f2f2;">
		<div class="layui-fluid" style="margin-top:15px;">
			<div class="layui-row">
				<div class="layui-col-md12 layui-col-lg12">
					<div class="layui-card">
						<div class="layui-card-header posRel"">
	  						<span>用户管理</span>
	  					</div>
	  					<div class="layui-card-body">
	  						<input type="hidden" id="provInp"/>
	  						<input type="hidden" id="cityInp"/>
	  						<input type="hidden" id="countyInp"/>
	  						<input type="hidden" id="townInp"/>
	  						<input type="hidden" id="schoolTypeInp" value="0"/>
	  						<!-- 查询条件 -->
		  					<div class="layui-form searchForm layui-clear">
			  					<div class="itemDivs">
		  							<input type="hidden" id="roleInp" value="0"/>
									<select id="roleSel" lay-filter="roleSel">
										<option value="">请选择角色(全部)</option>
										<option value="2">学生</option>
										<option value="3">网络导师</option>
										<option value="4">老师</option>
										<option value="5">管理员</option>
										<option value="6">家长</option>
									</select>
		  						</div>
		  						<div class="itemDivs">
		  							<input id="accInp" class="layui-input" placeholder="请输入账号" maxlength="30"/>
		  						</div>
		  						<div class="itemDivs">
		  							<input id="userNameInp" class="layui-input"  autocomplete="off" placeholder="请输入真实姓名" maxlength="30"/>
		  						</div>
		  						<div id="adminWrap" class="itemDivs" style="width:100px;">
		  							<input type="hidden" id="typeInp" value="1"/>
									<select lay-filter="typeSel">
										<option value="1">选择地区</option>
										<option value="2">选择学校</option>
									</select>
		  						</div>
		  						<div class="districtWrap">
		  							<div class="itemDivs" style="width:120px;">
		  							<div class="layui-input-inline">
		  								<input type="hidden" id="provInp"/>
										<select  name="province" lay-filter="province" class="province">
									       	<option value="">请选择省份</option>
									    </select>
		  							</div>
		  						</div>
		  						<div class="itemDivs" style="width:120px;">
		  							<div class="layui-input-inline">
		  								<input type="hidden" id="cityInp"/>
										<select name="city" lay-filter="city" disabled>
									       	<option value="">请选择城市</option>
									    </select>
		  							</div>
		  						</div>
		  						<div class="itemDivs" style="width:120px;">
		  							<input type="hidden" id="countyInp"/>
		  							<div class="layui-input-inline">
		  								<select name="area" lay-filter="area" disabled>
								       		<option value="">请选择县/区</option>
								     	</select>
		  							</div>
		  						</div>
		  						<div class="itemDivs" style="width:120px;">
		  							<div class="layui-input-inline">
		  								<input type="hidden" id="townInp"/>
										<select class="town" name="town" lay-filter="town" disabled>
									       	<option value="">请选择乡/镇</option>
									    </select>
		  							</div>
		  						</div>
		  						</div>
		  						<div id="selSchoolWrap" class="itemDivs" style="width:180px;">
		  							<input type="hidden" id="schoolIdInp" value="0"/>
		  							<p id="schNameTxt" class="selSch notSel">请选择学校</p>
		  							<i class="layui-icon layui-icon-triangle-d"></i>
		  						</div>
		  						<div style="float:left;">
		  							<div class="layui-input-inline">
		  								<button id="queryBtn" class="layui-btn"><i class="layui-icon layui-icon-search"></i></button>
		  							</div>
		  						</div>
								<a class="resetBtn" style="margin-left:20px;" href="javascript:void(0)">重置<i class="layui-icon layui-icon-refresh"></i></a>
		  					</div>
	  						<div class="userList">
	  							<table id="userListTab" class="layui-table" lay-filter="userListTab"></table>
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
		var globalUserId = 0,isUpDateFlag = false;
		layui.config({
			base : "/plugins/frame/js/" //address.js的路径
		}).use([ 'layer','table','form','address'], function() {
			var layer = layui.layer, 
				table=layui.table,
				form = layui.form,
				address = layui.address();
			form.on('select(roleSel)', function(data){
				var value = data.value;
				if(value == ''){//全部或其他身份
					$('#roleInp').val(0);
					$('#selSchoolWrap').hide();
					$('.districtWrap').hide();
					$('#adminWrap').hide();
				}else if(value == '2' || value == '6' || value == '4'){
					$('#selSchoolWrap').show();
					$('#roleInp').val(value);
					$('#adminWrap').hide();
					$('.districtWrap').hide();
				}else if(value == '5'){//管理员
					$('#adminWrap').show();
					$('#roleInp').val(value);
					if($('#typeInp').val() == '1'){
						$('#selSchoolWrap').hide();
						$('.districtWrap').show();
					}else{
						$('#selSchoolWrap').show();
						$('.districtWrap').hide();
					}
				}else if(value == '3'){//网络导师
					$('#roleInp').val(value);
					$('#selSchoolWrap').hide();
					$('.districtWrap').hide();
					$('#adminWrap').hide();
				}
				page.loadUserList();
			});
			//管理员选择地区
			form.on('select(typeSel)', function(data){
				var value = data.value;
				$('#typeInp').val(value);
				if(value == 1){
					$('.districtWrap').show();
					$('#selSchoolWrap').hide().removeClass('hasMargLeft');
				}else{
					$('.districtWrap').hide();
					$('#selSchoolWrap').show().addClass('hasMargLeft');
				}
			});
			var page = {
				init:function(){
					this.loadUserList();
					this.bindEvent();
				},
				enterPress : function(){
					var e = e || window.event;
					if(e.keyCode == 13){
						this.loadUserList();
					}
				},
				bindEvent : function(){
					//查询
					var _this = this;
					$('#queryBtn').on('click',function(){
						_this.loadUserList();
					});
					$('#accInp').on('keypress',function(){
						_this.enterPress(event);
					});
					$('#userNameInp').on('keypress',function(){
						_this.enterPress(event);
					});
					//重置
					$('.resetBtn').on('click',function(){
						$('#accInp').val('');
						$('#userNameInp').val('');
						$('#schoolIdInp').val(0);
						$('#schNameTxt').html('请选择学校').addClass('notSel');
						$('#roleInp').val(0);
						$('#roleSel').val('');
						form.render();
						_this.loadUserList();
					});
					//选择学校
					$('#selSchoolWrap').on('click',function(){
						isUpDateFlag = false;
						layer.open({
							title:'选择学校',
							type: 2,
						  	area: ['1150px', '550px'],
						  	fixed: true, //不固定
						  	maxmin: false,
						  	shadeClose :false,
						  	content: '/Module/userManager/jsp/selectSchool.html',
						  	end : function(){
						  		if(isUpDateFlag){
						  			page.loadUserList();
						  		}
						  	}
						});	
					});
				},
				loadUserList : function(){
					var accName = $.trim($('#accInp').val()),
						userNameInp = $.trim($('#userNameInp').val()),
						schNameInp = $.trim($('#schNameInp').val()),
						roleInp = $('#roleInp').val(),
						schoolTypeInp = $('#schoolTypeInp').val(),
						schoolIdInp = $('#schoolIdInp').val(),
						provInp = $('#provInp').val(),
						cityInp = $('#cityInp').val(),
						countyInp = $('#countyInp').val(),
						townInp = $('#townInp').val();
					var field = {accName:escape( accName ),roleId:roleInp,
						realName:escape( userNameInp ),schoolId:schoolIdInp,prov:provInp,city:cityInp,county:countyInp,town:townInp,schoolType:schoolTypeInp};
					table.render({
						elem: '#userListTab',
						height: 'full-150',
						url : '/user.do?action=getUserByOption',
						method : 'post',
						where:field,
						page : true,
						even : true,
						limit : 10,
						limits:[10,20,30,40],
						text: {none : '暂无相关数据'},
						cols : [[
							{field : '', title: '序号', type:'numbers', align:'center'},
							{field : 'accName', title: '账号', width:'120', align:'center'},
							{field : 'realName', title: '真实姓名', width:'100', align:'center'},
							{field : 'nickName', title: '昵称', width:'150', align:'center'},
							{field : 'roleName', title: '角色身份', width:'100',align:'center'},
							{field : 'subName', title: '担任科目', width:'100',align:'center'},
							{field : 'sex', title: '性别', align:'center'},
							{field : 'accStatus', title: '账号状态', width:'90', align:'center',templet:function(d){
								if(d.accStatus == '1'){
									return '<span class="canUseAcc">有效</span>';
								}else if(d.accStatus == '0'){
									return '<span class="canNotUseAcc">无效</span>';
								}
							}},
							{field : 'freeStatus', title: '是否免费', width:'90', align:'center',templet:function(d){
								if(d.freeStatus == '1'){
									return '<span class="canUseAcc">免费</span>';
								}else if(d.freeStatus == '0'){
									return '<span class="canNotUseAcc">收费</span>';
								}
							}},
							{field : 'endDate', title: '到期时间',width:'170',align:'center'},
							{field : 'prov', title: '省份',width:'120', align:'center'},
							{field : 'city', title: '城市', width:'120',align:'center'},
							{field : 'county', title: '县/区',width:'120',align:'center'},
							{field : 'town', title: '乡/镇',width:'120',align:'center'},
							{field : 'schoolType', title: '学段',width:'90', align:'center'},
							{field : 'schoolName', title: '学校名称',width:'150', align:'center'},
							{field : 'gradeName', title: '年级',width:'100', align:'center'},
							{field : 'className', title: '班级',width:'100', align:'center'},
							{field : '', title: '操作', fixed:'right',align:'center',width:'110',templet : function(d){
								if(d.roleName == '学生'){
									return '<a class="layui-btn layui-btn-xs" realName="'+ d.realName +'" userId="'+ d.id +'" lay-event="editFun"><i class="layui-icon layui-icon-edit"></i>编辑</a>';	
								}else{
									return '';
								}
							}}
						]],
						done : function(res, curr, count){
							layer.closeAll('loading');
						}
					});
				}
			};
			table.on('tool(userListTab)',function(obj){
				if(obj.event == 'editFun'){
					var realName = $(this).attr('realName');
					isUpDateFlag = false;
					globalUserId = $(this).attr('userId');
					layer.open({
						title:'编辑学生['+ realName +']的信息',
						type: 2,
					  	area: ['500px', '450px'],
					  	fixed: true, //不固定
					  	maxmin: false,
					  	shadeClose :false,
					  	content: '/Module/userManager/jsp/editUserInfo.html',
					  	end : function(){
					  		if(isUpDateFlag){
					  			page.loadUserList();
					  		}
					  	}
					});	
				}
			});
			page.init();
		});
	</script>
</html>
