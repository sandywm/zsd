<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html> 
<html>
  <head>
    <title>知识点目录管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache"> 
	<meta http-equiv="keywords" content="知识典管理系统,知识点目录管理">
	<meta http-equiv="description" content="知识点目录管理">
	<link href="/plugins/layui/css/layui.css" rel="stylesheet" type="text/css"/>
	<link href="/Module/chapter/css/chapter.css" rel="stylesheet" type="text/css"/>
	<link href="/plugins/layui/css/modules/layui-icon-extend/iconfont.css" rel="stylesheet" type="text/css"/>
	<link href="/plugins/pace/pace-theme-flash.min.css" rel="stylesheet" type="text/css"/>
	<script src="/plugins/pace/pace.min.js" type="text/javascript"></script>	
	<style>
		.layui-layer{border-radius:4px !important;}
	</style>
    </head>
	<body style="background:#f2f2f2;">
		<div class="layui-fluid" style="margin-top:15px;">
			<div class="layui-row">
				<div class="layui-col-md12 layui-col-lg12">
					<div class="layui-card">
						<div class="layui-card-header posRel">
	  						<span>知识点目录管理</span>
	  						<a id="addLoreCata" class="posAbs newAddBtn" opts='add' href="javascript:void(0)"><i class="layui-icon layui-icon-add-circle"></i>添加基础知识点</a>
	  					</div>
	  					<div class="layui-card-body" pad15>
	  						<div class="layui-form layui-clear">
	  							<div class="itemDivs">
		  							<button id="zsdCodeBtn" class="layui-btn"><i class="iconfont layui-extend-zhuanhuan"></i>批量修改知识点编码</button>
		  						</div>
	  							<div style="float:right">
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
			  								<input type="hidden" id="chapterInp"/>
											<select id="chapterSel" lay-filter="chapterSel">
												<option value=''>请选择章节</option>
											</select> 
			  							</div>
			  						</div>
			  						<div class="itemDivs" style="margin-right:0px;">
			  							<div class="layui-input-inline">
			  								<button id="queryBtn" class="layui-btn"><i class="layui-icon layui-icon-search"></i></button>
			  							</div>
			  						</div>
	  							</div>
	  						</div>
	  						<div>
	  							<p class="tipsTxt_lore">请根据对应的条件对章节知识点进行查看和管理！</p>
	  							<table id="loreCataTable" class="layui-table" lay-filter="loreCataTable"></table>
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
			globalOpts='',cptId=0,currPage='loreCataPage',loreId=0,nowEditName='';
		
		layui.config({
			base: '/plugins/frame/js/'
		}).use(['layer','table','form','baseDataMet','scrollBar'], function() {
			var layer = layui.layer,
				table = layui.table,
				form = layui.form,
				baseDataMet = layui.baseDataMet,
				scrollBar = layui.scrollBar ;
			
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
					//添加章节下对应基础知识点(通用版下)
					$('#addLoreCata').on('click',function(){
						cptId = $('#chapterInp').val();//获取章节Id
						if(cptId != ''){
							if(nowEditName == '通用版'){
								addEditFlag = false;
								globalOpts = $(this).attr('opts');
								_this.openEducLayer('添加基础知识点','200px');
							}else{
								layer.msg('基础知识点必须在通用版下添加',{icon:5,anim:6,time:2000});
							}
						}else{
							layer.msg('请选择章节',{icon:5,anim:6,time:2000});
						}
					});
					//查询
					$('#queryBtn').on('click',function(){
						_this.loadLoreCataLogList();
					});
					//批量生成知识点编码
					/*$('#zsdCodeBtn').on('click',function(){
						cptId = $('#chapterInp').val();//获取章节Id
						if(cptId != ''){
							var codeRes = baseDataMet.createLoreCode(cptId);
							layer.open({
								title:'',
								type: 1,
							  	area: ['400px', '300px'],
							  	fixed: true, //不固定
							  	maxmin: false,
							  	shadeClose :true,
							  	closeBtn : 0,
							  	content: codeRes,
							  	end : function(){
							  		_this.loadLoreCataLogList();
							  	}
							});
			        		$('#codeResCon li:odd').addClass('oddColor');
			        		
			        		if($('#codeResCon').height() < $('#codeResConUl').height()){
			    				var oScroll = '<div id="scrollParent" class="parentScroll"><div id="scrollSon" class="scrollBar"></div></div>';
			    				//创建动态模拟滚动条
			    				$('#codeResCon').append(oScroll);
			    				scrollBar.scrollBar('codeResCon','codeResConUl','scrollParent','scrollSon',20);
			    			}
						}else{
							layer.msg('请选择章节',{icon:5,anim:6,time:2000});
						}
					});*/
				},
				//加载章节基础知识点记录
				loadLoreCataLogList : function(){
					var subVal = $('#subInp').val(),
						gradeInpVal = $('#gradeInp').val(),
						eduColumeVal = $('#eduColumeInp').val(),
						chapterVal = $('#chapterInp').val();
					if(subVal == 0){
						layer.msg('请选择科目',{icon:5,anim:6,time:2000});
					}else if(gradeInpVal == 0){
						layer.msg('请选择年级',{icon:5,anim:6,time:2000});
					}else if(chapterVal == ''){
						layer.msg('请选择章节',{icon:5,anim:6,time:2000});
					}else{
						$('.tipsTxt').hide();
						$('#addChapter').show();
						baseDataMet.getLoreCataList(chapterVal);
					}
				},
				openEducLayer : function(title,currHei){
					var _this = this;
					layer.open({
						title:title,
						type: 2,
					  	area: ['600px', currHei],
					  	fixed: true, //不固定
					  	maxmin: false,
					  	shadeClose :false,
					  	content: '/Module/loreCatalogManager/jsp/addEditLoreCata.html',
					  	end : function(){
					  		if(addEditFlag){
					  			var chapterVal = $('#chapterInp').val();
					  			baseDataMet.getLoreCataList(chapterVal);
					  		}
					  	}
					});	
				}
			};
			table.on('tool(loreCataTable)',function(obj){
				if(obj.event == 'editFun'){//编辑章节
					var loreName = $(this).attr('loreName');
					loreId = $(this).attr('loreId');
					globalOpts = $(this).attr('opts');
					addEditFlag = false;
					page.openEducLayer('编辑基础知识点[<span style="color:#F47837;">'+ loreName +'</span>]','380px');
				}
			});
			page.init();
		});
	</script>
</html>
