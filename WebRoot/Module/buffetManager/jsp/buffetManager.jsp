<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html> 
<html>
  <head>
    <title>自助餐管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache"> 
	<meta http-equiv="keywords" content="知识典管理系统,自助餐管理">
	<meta http-equiv="description" content="自助餐管理">
	<link href="/plugins/layui/css/layui.css" rel="stylesheet" type="text/css"/>
	<link href="/Module/buffetManager/css/buffet.css" rel="stylesheet" type="text/css"/>
	<link href="/plugins/pace/pace-theme-flash.min.css" rel="stylesheet" type="text/css"/>
	<script src="/plugins/pace/pace.min.js" type="text/javascript"></script>	
    </head>
	<body>
		<div class="layui-fluid" style="margin-top:15px;">
			<div class="layui-row">
				<div class="layui-col-md12 layui-col-lg12">
					<div class="layui-card">
						<div class="layui-card-header loreHeader">
	  						<span style="float:left">自助餐管理</span>
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
					
				}
			};
			page.init();
		});
	</script>
</html>
