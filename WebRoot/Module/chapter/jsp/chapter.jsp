<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html> 
<html>
  <head>
    <title>章节管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache"> 
	<meta http-equiv="keywords" content="知识典管理系统,章节管理">
	<meta http-equiv="description" content="章节管理">
	<link href="/plugins/layui/css/layui.css" rel="stylesheet" type="text/css"/>
	<link href="/Module/chapter/css/chapter.css" rel="stylesheet" type="text/css"/>
	<link href="/plugins/pace/pace-theme-flash.min.css" rel="stylesheet" type="text/css"/>
	<script src="/plugins/pace/pace.min.js" type="text/javascript"></script>	
    </head>
	<body style="background:#f2f2f2;">
		<div class="layui-fluid" style="margin-top:15px;">
			<div class="layui-row">
				<div class="layui-col-md12 layui-col-lg12">
					<div class="layui-card">
						<div class="layui-card-header posRel">
	  						<span>章节管理</span>
	  						<a id="addChapter" class="posAbs newAddBtn addBtnRt" opts='add' href="javascript:void(0)"><i class="layui-icon layui-icon-add-circle"></i>添加章节</a>
	  						<div class="layui-form layui-clear" style="float:right;margin-right:130px;">
	  							<div class="itemDivs">
		  							<div class="layui-input-inline">
		  								<input type="hidden" id="subInp" value="0"/>
										<select id="subjectSel" lay-filter="subjectSel">
		  								 	<option value="">请选择科目</option>
		  								 </select> 
		  							</div>
		  						</div>
		  						<div class="itemDivs">
		  							<input type="hidden" id="gradeInp"/>
		  							<div class="layui-input-inline">
		  								 <select id="gradeSel" lay-filter="gradeSel">
		  								 	<option value="">请选择年级</option>
		  								 </select> 
		  							</div>
		  						</div>
		  						<div class="itemDivs">
		  							<div class="layui-input-inline">
		  								<input type="hidden" id="editInp" value="0"/>
										<select id="editionSel" lay-filter="editionSel"></select> 
		  							</div>
		  						</div>
		  						<div class="itemDivs">
		  							<div class="layui-input-inline">
		  								<input type="hidden" id="eduColumeInp"/>
										<select id="eduColumeSel" lay-filter="eduColumeSel"></select> 
		  							</div>
		  						</div>
		  						<div class="itemDivs" style="margin-right:0;">
		  							<div class="layui-input-inline">
		  								<button id="queryBtn" class="layui-btn"><i class="layui-icon layui-icon-search"></i></button>
		  							</div>
		  						</div>
	  						</div>
	  					</div>
	  					<div class="layui-card-body" pad15>
	  						<div id="editionList">
	  							<p class="tipsTxt">请根据对应的条件对章节进行查看和管理！</p>
	  							<table id="chapterTable" class="layui-table" lay-filter="chapterTable"></table>
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
		var infoBySubOpt = 'getGradeOpt',//根据学科获取年级的信息
			infoByGsEdOpt='geteduC',//根据年级出版社获取教材信息 need
			addEditFlag  = false,
			eduId = 0,globalOpts='',cptId=0,currPage='chapterPage';
		layui.config({
			base: '/plugins/frame/js/'
		}).use(['layer','table','form','baseDataMet'], function() {
			var layer = layui.layer,
				table = layui.table,
				form = layui.form,
				baseDataMet = layui.baseDataMet;
			
			var page = {
				init : function(){
					this.bindEvent();
					this.initDOM();
				},
				initDOM : function(){
					//获取科目
					baseDataMet.getSubjectList('subjectSel');
					//获取出版社
					baseDataMet.getEditionList('editionSel');
				},
				bindEvent : function(){
					var _this = this;
					//添加章节
					$('#addChapter').on('click',function(){
						eduId = $('#eduColumeInp').val();
						if(eduId != ''){
							addEditFlag = false;
							globalOpts = $(this).attr('opts');
							_this.openEducLayer('添加章节');
						}else{
							layer.msg('请选择教材',{icon:5,anim:6,time:2000});
						}
					});
					//查询
					$('#queryBtn').on('click',function(){
						_this.loadChapterList();
					});
				},
				loadChapterList : function(){
					var subVal = $('#subInp').val(),
						gradeInpVal = $('#gradeInp').val(),
						eduColumeVal = $('#eduColumeInp').val();
					if(subVal == 0){
						layer.msg('请选择科目',{icon:5,anim:6,time:2000});
					}else if(gradeInpVal == 0){
						layer.msg('请选择年级',{icon:5,anim:6,time:2000});
					}else{
						$('.tipsTxt').hide();
						$('#addChapter').show();
						baseDataMet.getChapterList(eduColumeVal);
					}
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
					  	content: '/Module/chapter/jsp/addEditChapter.html',
					  	end : function(){
					  		if(addEditFlag){
					  			var eduColumeVal = $('#eduColumeInp').val();
					  			baseDataMet.getChapterList(eduColumeVal);
					  		}
					  	}
					});	
				}
			};
			table.on('tool(chapterTable)',function(obj){
				if(obj.event == 'editFun'){//编辑章节
					var cptName = $(this).attr('cptName');
					eduId = $('#eduColumeInp').val();
					cptId = $(this).attr('chapId');
					addEditFlag = false;
					globalOpts = $(this).attr('opts');
					page.openEducLayer('编辑章节[<span style="color:#F47837;">'+ cptName +'</span>]');
				}
			});
			page.init();
		});
	</script>
</html>
