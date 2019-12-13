<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html> 
<html>
  <head>
    <title>知识典</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache"> 
	<meta http-equiv="keywords" content="各地级管理员">
	<meta http-equiv="description" content="各地级管理员">
	<link rel="stylesheet" type="text/css" href="/css/header.css"/>
	<link href="/plugins/layui/css/layui.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="/Module/adminManager/css/welcome.css"/>
	<link href="/plugins/pace/pace-theme-flash.min.css" rel="stylesheet" type="text/css"/>
	<script src="/plugins/pace/pace.min.js" type="text/javascript"></script>	
    </head>
    <style>
    	.layui-form-select dl dd.layui-this{background:#4d47f1;}
    </style>
	<body>
		<div class="headerWrap">
			<img src="images/logoPc.png" alt="知识典" style="margin-left:20px;"/>
			<div class="headNavItem">
				<span class="logOutBtn" style="margin-right:20px;width:auto;">
					<i class="iconfont layui-extend-tuichu"></i>
					退出
				</span>
			</div>
			<p id="welcomeTxt"></p>
		</div>
		<div class="logOutPopWin layui-anim layui-anim-scale">
			<div class="topPopWin">
				<p>退出系统提示</p>
				<a class="closeLogOut" href="javascript:void(0)">
					<i class="iconfont layui-extend-guanbi"></i>
				</a>
			</div>
			<div class="midPopWin">确认退出系统么？</div>
			<div class="botPopWin clearfix">
				<a class="cancel" href="javascript:void(0)">取消</a>
				<a class="sureLogOut" href="javascript:void(0)">确定</a>
			</div>
		</div>
		<div class="mainWrap">
			<div class="leftNav">
				<ul>
					<li class="leftNavItem">
						<a href="reportCenter.do?action=goQfbgPage">勤奋报告</a>
					</li>
					<li class="leftNavItem active">
						<a href="javascript:void(0)">知识典学习统计</a>
					</li>
				</ul>
			</div>
			<div class="rightCon layui-form">
				<input type="hidden" id="cityInp"/>
				<input type="hidden" id="countyInp"/>
				<input type="hidden" id="townInp"/>
				<input type="hidden" id="schInp" value="0"/>
				<input type="hidden" id="gradeInp" value=""/>
				<input type="hidden" id="classInp" value="0"/>
				<div class="filter layui-clear">
					<div class="cityItem itemDivs">
						<div class="layui-input-inline">
							<select name="city" lay-filter="city">
						       	<option value="">请选择城市</option>
						    </select>
						</div>
 					</div>
					<div class="countyItem itemDivs">
						<div class="layui-input-inline">
							<select name="area" lay-filter="area">
					       		<option value="">请选择县/区</option>
					     	</select>
						</div>
					</div>
					<div class="townItem itemDivs">
						<div class="layui-input-inline">
							<select class="town" name="town" lay-filter="town">
						       	<option value="">请选择乡/镇</option>
						    </select>
						</div>
					</div>
					<div class="schTypeItem itemDivs">
						<div class="layui-input-inline layui-form">
							<select id="schTypeSel" lay-filter="schTypeSel">
								<option value=''>(全部学段)</option>
								<option value='1'>小学</option>
								<option value='2'>初中</option>
								<option value='3'>高中</option>
							</select>
						</div>
					</div>
					<div class="schSelItem itemDivs">
						<div class="layui-input-inline layui-form">
							<select id="schSel" lay-filter="schSel">
								<option value="">请选择学校</option>
							</select>
						</div>
					</div>
					<div class="gradeSelItem itemDivs">
						<div class="layui-input-inline layui-form">
							<select id="gradeSel" lay-filter="gradeSel">
								<option value=''>请选择年级</option>
							</select>
						</div>
					</div>
					<div class="itemDivs">
						<div class="layui-input-inline layui-form">
							<select id="classSel" lay-filter="classSel">
								<option value=''>请选择班级</option>
							</select>
						</div>
					</div>
					<div class="itemDivs">
						<div class="layui-input-inline layui-form">
							<input type="hidden" id="stuInp" value="0"/>
							<select id="stuSel" lay-filter="stuSel">
								<option value=''>请选择学生</option>
							</select>
						</div>
					</div>
					<div class="itemDivs" style="width:120px;">
						<div class="layui-input-inline layui-form">
							<input type="hidden" id="subIdInp" value="0"/>
							<select id="subSel" lay-filter="subSel"></select>
						</div>
					</div>
					<div class="itemDivs_spec" style="float:left;margin-right:15px;margin-bottom:15px;">
						<div class="layui-input-inline" style="width:120px;">
							 <input id="stDate" type="text" placeholder="请选择起始时间" readonly class="layui-input"/>
						</div>
						<span style="margin:0 5px;">至</span>
						<div class="layui-input-inline" style="width:120px;">
							 <input id="edDate" type="text" placeholder="请选择结束时间" readonly class="layui-input"/>
						</div>
					</div>
					
					<div class="itemDivs">
						<div class="layui-input-inline layui-form">
							<input type="text" id="stuNameInp" placeholder="请输入学生姓名" class="layui-input"/>
							<a class="clearBtn" title="清空" href="javascript:void(0)"><i class="iconfont layui-extend-guanbi"></i></a>
						</div>
					</div>
					
					<div style="float:left;">
						<div class="layui-input-inline">
							<button id="queryBtn" class="layui-btn" style="background:#4d47f1;"><i class="layui-icon layui-icon-search"></i></button>
						</div>
					</div>
					
				</div>
				<div id="xxTjWrap">
					<button id="exportBtn" class="layui-btn"><i class="iconfont layui-extend-daochuexcel"></i>导出excel</button>
					<table id="xxTjTable" class="layui-table" lay-filter="xxTjTable"></table>
				</div>
			</div>
		</div>
	</body>
	<script src="/plugins/jquery/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="/plugins/layui/layui.js"></script>
	<script type="text/javascript">
		var currRoleName = '',schTypeVal = 0,schoolList = [],yearSystem = 0,currPage = 'xxTjPage';
		layui.config({
			base : "/plugins/frame/js/"
		}).use(['layer','adminAddress','form','laydate','table'],function(){
			var layer = layui.layer,address = layui.adminAddress,form = layui.form,laydate=layui.laydate,table=layui.table;
			laydate.render({'elem':'#stDate',theme: '#4d47f1'});
			laydate.render({'elem':'#edDate',theme: '#4d47f1'});
			var page = {
				init : function(){
					this.loadQfRepInfo();
					this.bindEvent();
				},
				bindEvent : function(){
					var _this = this;
					$('.logOutBtn').on('click',function(){
						layer.confirm('确认退出系统么？', {
						  title:'提示',
						  skin: 'layui-layer-molv',
						  btn: ['确定','取消'] //按钮
						},function(){
							window.location.href = "login.do?action=loginOut";
						});
					});
					$('#exportBtn').on('click',function(){
						layer.load('1');
						var stDate = $('#stDate').val(),
							edDate = $('#edDate').val(),
							cityInp = $('#cityInp').val(),
							countyInp = $('#countyInp').val(),
							townInp = $('#townInp').val(),
							schInp = $('#schInp').val(),
							gradeInp = $('#gradeInp').val(),
							classInp = $('#classInp').val(),
							stuIdInp = $('#stuInp').val(),
							subIdInp = $('#subIdInp').val(),
							stuNameInp = $('#stuNameInp').val();
						var field = JSON.stringify({province:escape(provVal),city:escape(cityInp),county:escape(countyInp),town:escape(townInp),schoolType:schTypeVal,
							schoolId:schInp,gradeName:gradeInp,classId:classInp,subId:subIdInp,stuId:stuIdInp,sDate:stDate,eDate:edDate,stuName:escape(stuNameInp)});
						 var form = $("<form>");   //定义一个form表单
							form.attr('style', 'display:none;'); //在form表单中添加查询参数
							form.attr('target', '');
							form.attr('method', 'post');
							form.attr('action', '/studyRecord.do?action=exportLogInfoToExcel');
							var input1 = $('<input>');
							input1.attr('type', 'text');
							input1.attr('name', 'field');
							input1.attr('value', field);
							$('body').append(form);  //将表单放置在web中 
							form.append(input1);   //将查询参数控件提交到表单上
						  	form.submit();
							layer.closeAll('loading');
					});
				},
				loadQfRepInfo : function(){
					var _this = this;
					$.ajax({
					    type:"post",
					    dataType:"json",
					    url:"/user.do?action=getManagerDetail",
					    success:function (json){
					    	//console.log(json)
					    	if(json.msg == 'success'){
					    		currRoleName = json.roleName;
					    		if(json.roleName == 'prov'){
						    		provVal = json.prov;
						    		$('#welcomeTxt').html('欢迎您，' + json.prov +' 管理员');
						    	}else if(json.roleName == 'city'){
						    		$('.cityItem').hide().remove();
						    		provVal = json.prov;
						    		cityVal = json.city;
						    		$('#cityInp').val(json.city);
						    		$('#welcomeTxt').html('欢迎您，' + json.prov + json.city + ' 管理员');
						    	}else if(json.roleName == 'county'){
						    		$('.filter').addClass('hasMargBot');
						    		$('.cityItem').hide().remove();
						    		$('.countyItem').hide().remove();
						    		provVal = json.prov;
						    		cityVal = json.city;
						    		$('#cityInp').val(json.city);
						    		countyVal = json.county;
						    		$('#welcomeTxt').html('欢迎您，' + json.prov + json.city + json.county + '管理员');
						    	}else if(json.roleName == 'town'){
						    		$('.filter').addClass('hasMargBot');
						    		$('.cityItem').hide().remove();
						    		$('.countyItem').hide().remove();
						    		$('.townItem').hide().remove();
						    		$('#cityInp').val(json.city);
						    		$('#townInp').val(json.town);
						    		$('#countyInp').val(json.county);
						    		provVal = json.prov;
						    		cityVal = json.city;
						    		countyVal = json.county;
						    		townVal = json.town;
						    		$('#welcomeTxt').html('欢迎您，' + json.prov + json.city + json.county + json.town + '管理员');
						    	}else if(json.roleName == 'schoolType'){
						    		$('.filter').addClass('hasMargBot');
						    		$('.cityItem').hide().remove();
						    		$('.countyItem').hide().remove();
						    		$('.townItem').hide().remove();
						    		$('.schTypeItem').hide().remove();
						    		$('#cityInp').val(json.city);
						    		$('#countyInp').val(json.county);
						    		$('#townInp').val(json.town);
						    		provVal = json.prov;
						    		cityVal = json.city;
						    		countyVal = json.county;
						    		townVal = json.town;
						    		schTypeVal = json.schoolType;
						    		if(json.schoolType == 1){
						    			$('#welcomeTxt').html('欢迎您，' + json.prov + json.city + json.county + json.town + '-小学 管理员');
						    		}else if(json.schoolType == 2){
						    			$('#welcomeTxt').html('欢迎您，' + json.prov + json.city + json.county + json.town + '-初中  管理员');
						    		}else if(json.schoolType == 3){
						    			$('#welcomeTxt').html('欢迎您，' + json.prov + json.city + json.county + json.town + '-高中  管理员');
						    		}
						    		if(json.schooList.length > 0){
						    			schoolList = json.schooList;
						    		}else{
						    			layer.msg('暂无学校');
						    		}
						    	}else if(json.roleName == 'school'){
						    		$('.filter').addClass('hasMargBot');
						    		$('.cityItem').hide().remove();
						    		$('.countyItem').hide().remove();
						    		$('.townItem').hide().remove();
						    		$('.schTypeItem').hide().remove();
						    		$('.schSelItem').hide().remove();
						    		$('#cityInp').val(json.city);
						    		$('#countyInp').val(json.county);
						    		$('#townInp').val(json.town);
						    		$('#schInp').val(json.schoolId);
						    		provVal = json.prov;
						    		cityVal = json.city;
						    		countyVal = json.county;
						    		townVal = json.town;
						    		schTypeVal = json.schoolType;
						    		yearSystem = json.yearSystem;
						    		$('#welcomeTxt').html('欢迎您，' + json.prov + json.city + json.county + json.town +'--'+ json.schoolName +' 管理员');
						    	}else if(json.roleName == 'grade'){
						    		$('.filter').addClass('hasMargBot');
						    		$('.cityItem').hide().remove();
						    		$('.countyItem').hide().remove();
						    		$('.townItem').hide().remove();
						    		$('.schTypeItem').hide().remove();
						    		$('.schSelItem').hide().remove();
						    		$('.gradeSelItem').hide().remove();
						    		$('#cityInp').val(json.city);
						    		$('#countyInp').val(json.county);
						    		$('#townInp').val(json.town);
						    		$('#schInp').val(json.schoolId);
						    		$('#gradeInp').val(json.gradeName);
						    		provVal = json.prov;
						    		cityVal = json.city;
						    		countyVal = json.county;
						    		townVal = json.town;
						    		schTypeVal = json.schoolType;
						    		yearSystem = json.yearSystem;
						    		$('#welcomeTxt').html('欢迎您，' + json.prov + json.city + json.county + json.town +'--'+ json.schoolName + json.gradeName +' 管理员');
						    	}else if(json.msg == 'noInfo'){
							    	
							    }
					    		layui.adminAddress();
					    	}
					    }
					});
				}
			};
			page.init();
		});
		
	
	</script>
</html>
