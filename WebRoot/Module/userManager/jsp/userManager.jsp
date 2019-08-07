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
	<link href="/Module/userManager/css/userManager.css" rel="stylesheet" type="text/css"/>
	<link href="/plugins/pace/pace-theme-flash.min.css" rel="stylesheet" type="text/css"/>
	<script src="/plugins/pace/pace.min.js" type="text/javascript"></script>	
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
	  						<!-- 查询条件 -->
		  					<div class="layui-form searchForm layui-clear">
		  						<div class="itemDivs" style="width:180px;">
		  							<input id="accInp" class="layui-input" placeholder="请输入登录账号" maxlength="30"/>
		  						</div>
		  						<div class="itemDivs" style="width:180px;">
		  							<input id="userNameInp" class="layui-input" placeholder="请输入真实姓名" maxlength="30"/>
		  						</div>
		  						<div class="itemDivs" style="width:180px;">
		  							<input id="schNameInp" class="layui-input" placeholder="请输入学校名称" maxlength="30"/>
		  						</div>
		  						<div class="itemDivs" style="width:180px;">
		  							<input type="hidden" id="roleInp" value=""/>
									<select id="roleSel" lay-filter="roleSel">
										<option value="">请选择角色</option>
									</select>
		  						</div>
		  						<div class="itemDivs">
		  							<div class="layui-input-inline">
		  								<button id="queryBtn" class="layui-btn"><i class="layui-icon layui-icon-search"></i></button>
		  							</div>
		  						</div>
		  						<!--  a class="resetBtn" href="javascript:void(0)"><i class="layui-icon layui-icon-refresh"></i>重置</a-->
		  					</div>
	  						<div>
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
		var addEditFlag=true;
		layui.use([ 'layer','table','form'], function() {
			var layer = layui.layer, 
				table=layui.table,
				form = layui.form;
			var page = {
				init:function(){
					this.loadUserList();
					this.bindEvent();
				},
				bindEvent : function(){
					//查询
					var _this = this;
					
					$('#queryBtn').on('click',function(){
						_this.loadUserList();
					});
				},
				loadUserList : function(){
					var accName = $.trim($('#accInp').val()),
						userNameInp = $.trim($('#userNameInp').val()),
						schNameInp = $.trim($('#schNameInp').val());
						roleInp = $('#roleInp').val();
					var field = {accName:accName,realName:userNameInp,sDate:stDate,eDate:edDate,checkSta:checkStaInp};
					table.render({
						elem: '#ntListTab',
						height: 'full-200',
						url : '/netTeacherReview.do?action=getNtcReviewList',
						method : 'post',
						where:field,
						page : true,
						even : true,
						limit : 10,
						limits:[10,20,30,40],
						cellMinWidth:160,
						text: {none : '暂无相关数据'},
						cols : [[
							{field : '', title: '序号', type:'numbers',fixed: 'left' , align:'center'},
							{field : 'accName', title: '账号', align:'center'},
							{field : 'realName', title: '真实姓名', align:'center'},
							{field : 'mobile', title: '手机号码', align:'center'},
							{field : 'subname', title: '科目',align:'center'},
							{field : 'lastLoginDate', title: '最后登录时间',align:'center'},
							{field : 'certSta', title: '证件情况',align:'center'},
							{field : 'checkSta', title: '审核状态', align:'center'},
							{field : '', title: '操作', align:'center',templet : function(d){
								return '<a class="layui-btn layui-btn-xs" ntId="'+ d.id +'" lay-event="verifyNtFun"><i class="layui-icon layui-icon-edit"></i>审核</a>';
							}}
						]],
						done : function(res, curr, count){
							layer.closeAll('loading');
						}
					});
				}
			};
			table.on('tool(ntListTab)',function(obj){
				if(obj.event == 'verifyNtFun'){
					verifyFlag = false;
					ntId = $(this).attr('ntId');
					layer.open({
						title:'网络导师黄利峰的证件信息审核',
						type: 2,
					  	area: ['1000px', '500px'],
					  	fixed: true, //不固定
					  	maxmin: false,
					  	shadeClose :false,
					  	content: '/Module/ntVerifyManager/jsp/verifyNt.html',
					  	end : function(){
					  		if(verifyFlag){
					  			_this.loadNtList();
					  		}
					  	}
					});	
				}
			});
			page.init();
		});
	</script>
</html>
