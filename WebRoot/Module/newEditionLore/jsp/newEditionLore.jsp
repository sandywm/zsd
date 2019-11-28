<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html> 
<html>
	<head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache"> 
	<meta http-equiv="keywords" content="知识典管理系统,生成其他版本知识点">
	<meta http-equiv="description" content="生成其他版本知识点">
	<link href="/plugins/layui/css/layui.css" rel="stylesheet" type="text/css"/>
	<link href="/Module/newEditionLore/css/newEditionLore.css" rel="stylesheet" type="text/css"/>
	<link href="/plugins/pace/pace-theme-flash.min.css" rel="stylesheet" type="text/css"/>
	<script src="/plugins/pace/pace.min.js" type="text/javascript"></script>	
	<title>生成其他版本知识点</title>
	</head>
	<body style="background:#f2f2f2;">
		<div class="newEdiWrap">
			<!-- 通用版 -->
			<div class="leftTyPart comNewMod">
				<span class="lineSpan"></span>
				<div class="comModTit">
					<span>通用版</span>
				</div>
				<!-- 通用筛选 -->
				<div class="comFilter layui-form">
					<div class="itemDivs" style="width:130px;">
						<div class="layui-input-inline">
							<input type="hidden" id="subInp" value="0"/>
							<select id="subjectSel" lay-filter="subjectSel">
							 	<option value="">请选择科目</option>
							 </select> 
						</div>
					</div>
					<div class="itemDivs" style="width:130px;">
						<input type="hidden" id="editInp" value="1"/>
						<input type="hidden" id="gradeInp"/>
						<div class="layui-input-inline">
							 <select id="gradeSel" lay-filter="gradeSel">
							 	<option value="">请选择年级</option>
							 </select> 
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
					
					<div class="itemDivs chaptItem">
						<input type="hidden" id="chapterInp"/>
						<select id="chapterSel" lay-filter="chapterSel">
							<option value=''>请选择章节</option>
						</select> 
					</div>
				</div>
				<!-- 对应筛选出来的list -->
				<ul id="tyListData" class="listData"></ul>
			</div>
			<!-- 其他版 -->
			<div class="otherEdit comNewMod">
				<div class="comModTit">
					<span>其他版</span>
				</div>
				<!-- 其他版本筛选 -->
				<div class="comFilter layui-form">
					<div class="itemDivs" style="width:130px;">
						<div class="layui-input-inline">
							<input type="hidden" id="subInp_new" value="0"/>
							<select id="subjectSel_new" lay-filter="subjectSel_new">
							 	<option value="">请选择科目</option>
							 </select> 
						</div>
					</div>
					<div class="itemDivs" style="width:130px;">
						<input type="hidden" id="gradeInp_new"/>
						<div class="layui-input-inline">
							 <select id="gradeSel_new" lay-filter="gradeSel_new">
							 	<option value="">请选择年级</option>
							 </select> 
						</div>
					</div>
					<div class="itemDivs" style="width:140px;margin-right:0;">
						<div class="layui-input-inline">
							<input type="hidden" id="editInp_new" value="0"/>
							<select id="editionSel_new" lay-filter="editionSel_new"></select> 
						</div>
					</div>
					
					<div class="itemDivs" style="width:120px;margin-top:15px;">
						<div class="layui-input-inline">
							<input type="hidden" id="eduColumeInp_new"/>
							<select id="eduColumeSel_new" lay-filter="eduColumeSel_new">
								<option value=''>请选择教材</option>
							</select> 
						</div>
					</div>
					
					<div class="itemDivs chaptItem_new">
						<input type="hidden" id="chapterInp_new"/>
						<select id="chapterSel_new" lay-filter="chapterSel_new">
							<option value=''>请选择章节</option>
						</select> 
					</div>
					
					<div class="itemDivs" style="margin-top:15px;">
						<div class="layui-input-inline">
							<button id="queryBtn" class="layui-btn" style="background:#1E9FFF;"><i class="layui-icon layui-icon-search"></i></button>
						</div>
					</div>
					
				</div>
				<!-- 对应筛选出来的list -->
				<ul id="qtListData" class="listData_new"></ul>
				<div class="saveBtn">
					<span id="save">保存</span>
				</div>
			</div>
		</div>  	
		<script src="/plugins/jquery/jquery.min.js"></script>
   		<script src="/plugins/layui/layui.js"></script>	
  		<script type="text/javascript">
  			var infoBySubOpt = 'getGradeOpt',editAll='',//根据学科获取年级的信息
				infoByGsEdOpt='geteduC';//根据年级出版社获取教材信息 need
			var tyEditVal = 1,currPage = 'newEditPage';
			var hasAddArray = [];
			layui.config({
				base: '/plugins/frame/js/'
			}).use(['layer','form','baseDataMet'],function(){
				var layer = layui.layer,
					form = layui.form,
					baseDataMet = layui.baseDataMet;
				var page = {
					init : function(){
						this.initDOM();
						this.bindEvent();
					},
					bindEvent : function(){
						var _this = this;
						$('#queryBtn').on('click',function(){
							var chapterInp_newVal = $('#chapterInp_new').val();
							if(chapterInp_newVal == ''){
								layer.msg('请选择其他版章节');
								return;
							}
							baseDataMet.getLoreList_newEdit(chapterInp_newVal);
						});
						$('#save').on('click',function(){
							var loreCatalog = new Array();
							var cptIdNew = $('#chapterInp_new').val();
							var aObj = $('#qtListData').find('a');
							var existStr_new_array_1 = new Array();
							var existFlag = false;
							var existStr = "";
							var i = 0;
							var j = 0;
							var k = 0;
							var flag = true;
							
							if(parseInt( cptIdNew ) > 0 ){
								if(aObj.length == 0){
									layer.msg('请先从通用版选择知识点章节添加!');
								}else{
									$('.newouterlore input').each(function(){
										//step:1 先判断所增加的知识典中有没有名字相同的
										var loreInputValue = $("#"+this.id).val();
										if(loreInputValue != ""){
											if(i == 0){
												existStr_new_array_1[k++] = loreInputValue;
												existFlag = false;
											}else{
												if(checkExistByRigthLore(existStr_new_array_1,loreInputValue)){
													existFlag = true;
													layer.msg('当前存在着相同的知识点名称!');
												}else{
													existStr_new_array_1[k++] = loreInputValue;
													existFlag = false;
												}
											}
											i++;
										}else{
											layer.msg('知识点目录不能为空,如不想保留，请直接删除!');
											existFlag = true;
											return false;
										}
										
									});
									//step:2判断所增加的知识典中有没有和该章节下已有知识典的名字相同的
									if(!existFlag){
										$('.newouterlore input').each(function(){
											var newLoreName = $("#"+this.id).val(),
												currLoreId = $("#" + this.id).attr('loreId');
											if(checkExistLoreCatalogData(currLoreId,cptIdNew)){	
												existFlag = true;
												layer.msg('当前章节下已存在相同的知识点名称');
											}else{
												loreCatalog[j] = newLoreName.replace(/^(\s|\u00A0)+/,'').replace(/(\s|\u00A0)+$/,'') + "," + (this.id).replace("newLoreName_","");
												existFlag = false;
												j++;
											}
										});
									}
									if(!existFlag){
										var loreCatalogStr = arrayToJson(loreCatalog);
										$.ajax({
											type:'post',
											dataType:'json',
											data : {cptId:cptIdNew,loreCatalogNameStr:loreCatalogStr},
											url:'/lore.do?action=addNewEdiLoreCatalog',
											success:function (json){ 
												if(json.result == 'success'){
													var length = k;
													layer.msg('您刚才成功增加了'+ length + '个知识典目录!请点击预览查看!',{icon:1,time:1000},function(){
														hasAddArray.length = 0;
														//清空左侧知识典目录数据
														removeChildren("tyListData");
														//清空右侧知识典目录数据
														removeRightLoreCatalog();
														//初始化左侧章节列表
														$("#chapterInp").val('');
														$('#chapterSel').val('');
														form.render();
														var str = _this.createRealteList(json.relateList);
														var currCptName = $('#chapterSel_new').next().find('input').val();
														layer.open({
															title:'[<span class="relateCptName">'+ currCptName +'</span>]的关联结果',
															type: 1,
														  	area: ['600px', '350px'],
														  	fixed: true, //不固定
														  	maxmin: false,
														  	shadeClose :true,
														  	closeBtn : 1,
														  	content: str
														});
													});
												}else if(json.result == 'error'){
													layer.msg('添加失败');
												}
											}
										});	
									}
								}
							}else{
								layer.msg('请选择其他版章节');
							}
						});
					},
					createRealteList : function(list){
						var str = '';
						str += '<div class="relateInfo"><ul>';
						for(var i=0;i<list.length;i++){
							if(list[i].split('&wmd&')[0] == 0){
								str += '<li class="failTips">'+ list[i].split('&wmd&')[1] +'</li>';
							}else if(list[i].split('&wmd&')[0] == 1){
								str += '<li class="succTips">'+ list[i].split('&wmd&')[1] +'</li>';
							}
						}
						str += '</ul></div>';
						return str;
					},
					initDOM : function(){
						//获取科目
						baseDataMet.getSubjectList('subjectSel');
						baseDataMet.getSubjectList('subjectSel_new');
						//获取出版社
						baseDataMet.getEditionList('editionSel_new',2);
						
					}
				};
				page.init();
			});
			function removeChildren(obj){
				var div = $("#"+obj).find('li');
				for(var i = 0 ; i < div.length; i++){
					if(obj == "tyListData"){
						$("#leftLi"+i).remove();
					}
				}
			}
			function removeRightLoreCatalog(){
				var aObj = $("#qtListData").find("li");
				for(var i = 0 ; i < aObj.length ; i++){
					var liObjId = $(aObj[i]).attr("id");
					$("#"+liObjId).remove();
				}
			}
			//数组转json
			function arrayToJson(o) {   
			    var r = [];   
			    if (typeof o == "string") 
			    	return "\"" + o.replace(/([\'\"\\])/g, "\\$1").replace(/(\n)/g, "\\n").replace(/(\r)/g, "\\r").replace(/(\t)/g, "\\t") + "\"";   
			    if (typeof o == "object") {   
			    	if (!o.sort) {   
			    		for (var i in o)   
						    r.push(i + ":" + arrayToJson(o[i]));   
					    if (!!document.all && !/^\n?function\s*toString\(\)\s*\{\n?\s*\[native code\]\n?\s*\}\n?\s*$/.test(o.toString)){   
					    	r.push("toString:" + o.toString.toString());   
					    }   
					    r = "{" + r.join() + "}";   
					} else {   
					    for (var i = 0; i < o.length; i++) {   
					    	r.push(arrayToJson(o[i]));   
					    }   
					    r = "[" + r.join() + "]";   
					}   
					return r;   
				}   
			    return o.toString();   
			}  
			//上移
			function loreUp(elem){
				var cur_lore = $(elem).parents('.newouterlore');
				if($(cur_lore).index()==0) {
					return ;
				}
				var prev = $(elem).parents('.newouterlore').prev();
				cur_lore.animate({opacity:0},520,function(){
					$(cur_lore).after(prev);
					cur_lore.animate({opacity:1});
					loreOrder();
				});
			}
			//下移
			function loreDown(elem) {
				var cur_lore = $(elem).parents('.newouterlore');
				if($(cur_lore).index()==$(".newouterlore").last().index()) { //如果是最后一个元素不处理
					return ;
				}
				var next = $(elem).parents('.newouterlore').next(); 
				cur_lore.animate({opacity:0},520,function(){
					$(cur_lore).before(next);
					cur_lore.animate({opacity:1});
					loreOrder();
				});
			}
			//查询右侧是否已存在新增加的知识典
			function checkExistByRigthLore(array,newLoreName){
				var existFlag = false;
				for(var i = 0 ; i < array.length; i++){
					if(array[i] == newLoreName){
						existFlag = true;;
						break;
					}else{
						existFlag = false;
					}
				}
				return existFlag;
			}
			function checkExistLoreCatalogData(loreId,cptId){
				var existFlag = false;
				layer.load('1');
				$.ajax({
					type:'post',
			        dataType:'json',
			        async : false,
			        data:{loreId:loreId,cptId:cptId},
			        url:'/lore.do?action=checkExistLoreCatalogData',
			        success:function (json){
			        	layer.closeAll('loading');	
			        	if(json.result == 'existInfo'){
			        		existFlag = true;
			        	}else if(json.result == 'noInfo'){
			        		existFlag = false;
			        	}else if(json.result == 'error'){
			        		existFlag = false;
			        		layer.msg('服务器错误，请稍后重试');
			        	}
			        }
				});
				return existFlag;
			}
			function addLore(obj){
				var loreId = $(obj).attr('loreId'),
					loreName = $(obj).attr('loreName'),
					cptIdNew = $('#chapterInp_new').val();
				if(parseInt( cptIdNew ) > 0 ){
					if(checkExistLoreCatalogData(loreId,cptIdNew)){
						layer.msg('当前章节下已存在相同的知识点名称');
					}else{
						var str = '';
						for(var i=0;i<hasAddArray.length;i++){
							if(hasAddArray[i] == loreId){
								layer.msg('当前其他版已存在着相同的知识点名称');
								return;
							}
						}
						hasAddArray.push(loreId);
						str += '<li id="newEditionlore_'+ loreId +'" class="newouterlore layui-clear">';
						str += '<div class="inpDiv">';
						str += '<input id="newLoreName_'+ loreId +'" loreId="'+ loreId +'" value="'+ loreName +'" type="text"/>';
						str += '</div>';
						str += '<div class="moveUpDown"></div>';
						str += '<div class="delWrap">';
						str += '<a id="'+ loreId +'"onclick="delLore(this)"  class="delNewPoint" href="javascript:void(0)">';
						str += '<i class="layui-icon layui-icon-delete"></i></a>';
						str += '</div>';
						str += '</li>';
						$('#qtListData').append(str);
						$('.newouterlore').animate({opacity:1},520);
						loreOrder();
					}
				}else{
					layer.msg('请选择其他版章节');
				}
			}
			function delLore(obj){
				var loreId = $(obj).attr('id');
				$("#newEditionlore_"+loreId).animate({opacity:0},520,function(){
					$("#newEditionlore_"+loreId).remove();
					loreOrder(); //排序
					for(var i=0;i<hasAddArray.length;i++){
						if(hasAddArray[i] == loreId){
							hasAddArray.splice(i,1);
						}
					}
				});
			}
			function loreOrder(){
				var lore_length = $('.newouterlore').length;
				$('.newouterlore').each(function (i, obj) {
					var index = i+1;
					//添加操作按钮
					if (index==1) {
						$(this).find('.moveUpDown').html('<a href="javascript:void(0)" title="下移" onclick="loreDown(this)" class="move-down"></a>');
					} else if (index == lore_length) {
						$(this).find('.moveUpDown').html('<a href="javascript:void(0)" title="上移" onclick="loreUp(this)" class="move-up"></a>');
					} else {
						$(this).find('.moveUpDown').html('<a href="javascript:void(0)" title="下移" onclick="loreDown(this)" class="move-down"></a><a href="javascript:void(0)" title="上移" onclick="loreUp(this)" class="move-up"></a>');
					}
				});
			}
		</script>
	</body>
</html>
