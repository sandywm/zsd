<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html> 
<html>
  <head>
    <title>网络导师审核</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache"> 
	<meta http-equiv="keywords" content="知识典管理系统,网络导师审核">
	<meta http-equiv="description" content="网络导师审核">
	<link href="/plugins/layui/css/layui.css" rel="stylesheet" type="text/css"/>
	<link href="/Module/ntVerifyManager/css/ntVerifyManager.css" rel="stylesheet" type="text/css"/>
	<link href="/plugins/pace/pace-theme-flash.min.css" rel="stylesheet" type="text/css"/>
	<script src="/plugins/pace/pace.min.js" type="text/javascript"></script>	
    </head>
	<body style="background:#f2f2f2;">
		<div class="layui-fluid" style="margin-top:15px;">
			<div class="layui-row">
				<div class="layui-col-md12 layui-col-lg12">
					<div class="layui-card">
						<div class="layui-card-header posRel"">
	  						<span>网络导师审核</span>
	  					</div>
	  					<div class="layui-card-body">
	  						<!-- 查询条件 -->
		  					<div class="layui-form searchForm layui-clear" style="float:right;">
		  						<div class="itemDivs" style="width:180px;">
		  							<input id="accInp" class="layui-input" placeholder="请输入账号" maxlength="30"/>
		  						</div>
		  						<div class="itemDivs" style="width:180px;">
		  							<input id="userNameInp" class="layui-input" placeholder="请输入真实姓名" maxlength="30"/>
		  						</div>
		  						<div class="itemDivs" style="width:180px;">
		  							<input type="hidden" id="checkStaInp" value="-1"/>
									<select id="checkStaSel" lay-filter="checkStaSel">
										<option value="">请选择审核状态(全部)</option>
										<option value="0">未审核</option>
										<option value="1">审核未通过</option>
										<option value="2">审核通过</option>
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
	  							<table id="ntListTab" class="layui-table" lay-filter="ntListTab"></table>
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
		var verifyFlag = true,ntcId=0;
		layui.use([ 'layer','table','laydate','form'], function() {
			var layer = layui.layer, 
				table=layui.table,
				form = layui.form,laydate=layui.laydate;
			laydate.render({'elem':'#stDate'});
			laydate.render({'elem':'#edDate'});
			
			form.on('select(checkStaSel)',function(data){
				var value = data.value == '' ? -1 : data.value;
				$('#checkStaInp').val(value);
			});
			var page = {
				init:function(){
					this.loadNtList();
					this.bindEvent();
				},
				bindEvent : function(){
					//查询
					var _this = this;
					$('#queryBtn').on('click',function(){
						_this.loadNtList();
					});
				},
				loadNtList : function(){
					var accName = $.trim($('#accInp').val()),
						userNameInp = $.trim($('#userNameInp').val()),
						checkStaInp = $('#checkStaInp').val();
					var field = {accName:escape(accName),realName:escape(userNameInp),checkSta:checkStaInp};
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
						cellMinWidth:180,
						text: {none : '暂无相关数据'},
						cols : [[
							{field : '', title: '序号', type:'numbers' , align:'center'},
							{field : 'accName', title: '账号', align:'center'},
							{field : 'realName', title: '真实姓名', align:'center'},
							{field : 'mobile', title: '手机号码', align:'center'},
							{field : 'subName', title: '担任科目',align:'center'},
							{field : 'lastLoginDate', title: '最后登录时间',align:'center'},
							{field : 'certSta', title: '证件情况',align:'center',templet:function(d){
								if(d.certSta == '暂无'){
									return '<span class="noUpTxt">未上传</span>';
								}else if(d.certSta == '齐全'){
									return '<span class="passTxt">证件齐全</span>';
								}else if(d.certSta == '不全'){
									return '<span class="noPassTxt">证件不全</span>';
								}
							}},
							{field : 'checkSta', title: '审核状态', align:'center',templet:function(d){
								if(d.checkSta == '通过'){
									return '<span class="passTxt">通过</span>';
								}else if(d.checkSta == '未通过' || d.checkSta == '未审核'){
									return '<span class="noPassTxt">'+ d.checkSta +'</span>';
								}
							}},
							{field : '', title: '操作', align:'center',templet : function(d){
								return '<a class="layui-btn layui-btn-xs" realName="'+ d.realName +'" ntcId="'+ d.ntcId +'" lay-event="verifyNtFun"><i class="layui-icon layui-icon-edit"></i>审核</a>';
							}}
						]],
						done : function(res, curr, count){
							console.log(res)
							layer.closeAll('loading');
						}
					});
				}
			};
			table.on('tool(ntListTab)',function(obj){
				if(obj.event == 'verifyNtFun'){
					var realName = $(this).attr('realName');
					verifyFlag = false;
					ntcId = $(this).attr('ntcId');
					layer.open({
						title:'网络导师'+ realName +'的证件信息审核',
						type: 2,
					  	area: ['1020px', '500px'],
					  	fixed: true, //不固定
					  	maxmin: false,
					  	shadeClose :false,
					  	content: '/Module/ntVerifyManager/jsp/verifyNt.html',
					  	end : function(){
					  		if(verifyFlag){
					  			page.loadNtList();
					  		}
					  	}
					});	
				}
			});
			page.init();
		});
	</script>
</html>
