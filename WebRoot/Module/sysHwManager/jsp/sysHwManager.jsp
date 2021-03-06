<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html> 
<html>
  <head>
    <title>章节管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache"> 
	<meta http-equiv="keywords" content="知识典管理系统,系统家庭作业管理">
	<meta http-equiv="description" content="系统家庭作业管理">
	<link href="/plugins/layui/css/layui.css" rel="stylesheet" type="text/css"/>
	<link href="/Module/loreManager/css/loreManager.css" rel="stylesheet" type="text/css"/>
	<link href="/Module/loreManager/css/viewLore.css" rel="stylesheet" type="text/css"/>
	<link href="/Module/commonJs/ueditor/themes/default/css/ueditor.min.css" type="text/css" rel="stylesheet" />
	<link href="/plugins/pace/pace-theme-flash.min.css" rel="stylesheet" type="text/css"/>
	<script src="/plugins/pace/pace.min.js" type="text/javascript"></script>	
	<style>
		.layui-form-radio *{color:#333}
		.layui-form-checkbox[lay-skin=primary] span{
			color:#333 !important;
		} 
	</style>
    </head>
	<body style="background:#f2f2f2;">
		<div class="layui-card-header loreHeader">
			<span style="float:left;">系统家庭作业管理</span>
			<div class="layui-form" style="float:left;margin:3px 15px 0 35px;">
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
			</div>
			<!-- 搜索知识点 -->
			<div class="searchZsd">
				<div class="itemDivs" style="width:90px;margin-right:10px;">
					<div class="layui-input-inline layui-form">
						<select id="zsdSel" lay-filter="zsdSel">
							<option value="1">拼音码</option>
					       	<option value="2">标题</option>
					     </select>
					</div>
				</div>
				<div class="itemDivs" style="margin-right:10px;">
					<div class="layui-input-inline">
						<input id="zsd_py" type="text" class="layui-input" placeholder="请输入知识点拼音码"/>
						<input id="zsd_txt" type="text" class="layui-input" placeholder="请输入知识点标题"/>
					</div>
				</div>
				<div class="itemDivs">
					<div class="layui-input-inline">
						<button id="queryBtn" class="layui-btn"><i class="layui-icon layui-icon-search"></i></button>
					</div>
				</div>
			</div>
		</div>
		<div class="layui-fluid" style="margin-top:50px;padding:0;">
			<div class="layui-row">
				<div class="layui-col-md12 layui-col-lg12">
					<div class="layui-card" style="box-shadow:none;">
	  					<div class="layui-card-body" pad15>
	  						<p id="currLoc"></p>
	  						<!-- 知识点列表 -->
	  						<div class="loreList">
	  							<p class="tipsTxt_lore">请根据对应的条件对章节知识点进行查看！</p>
	  							<table id="loreListTable" class="layui-table" lay-filter="loreListTable"></table>
	  						</div>
	  						<!-- 题库列表 -->
	  						<div class="loreQuesList">
	  							<table id="loreQuesTable" class="layui-table" lay-filter="loreQuesTable"></table>
	  						</div>
	  						<!-- 题库题型对应增加编辑结构 -->
	  						<div class="queType layui-form"></div>
	  						<div class="quesAddEditBox">
	  							<div id="zzcWrap" class="comQuesBox"></div>
	  							<div id="subBtnBox">
	  								<a href="javascript:void(0)" class="layui-btn subLoreBtn">提交</a>
	  							</div>
	  						</div>
	  					</div>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script src="/plugins/jquery/jquery.min.js"></script>
   	<script src="/plugins/layui/layui.js"></script>
	<script src="/Module/commonJs/ueditor/ueditor.config.js"></script>
	<script src="/Module/commonJs/ueditor/ueditor.all.min.js"></script>
	<script type="text/javascript">
		var loreType = 'zzc';//自助餐
		
		var infoBySubOpt = 'getGradeOpt',//根据学科获取年级的信息
			infoByGsEdOpt='geteduC',//根据年级出版社获取教材信息 need
			searOpt='loreManager',nowSearType=1,
			addEditFlag  = false,editAll='',//出版社全拼id:name
			switchFlag=false,//用于填空题问答题这块切换题型时是否打开问题选项 true->打开 
			globalOpts='',cptId=0,currPage='sysHwPage',bigHwId=0,
			loreId=0,currNum=0,loreNameBig='',loreBigTit='',loreBigId=0,lqBigId=0,realAnswer='';//是否可以增加点拨指导和知识讲解;
		var result_answer = "";//ABCD
		var result_answer_text = "",answerNum=0,answerType='',
			multiAnsArr=[],mindResArr=[],abilityResArr=[];//复选框text
		layui.config({
			base: '/plugins/frame/js/'
		}).use(['form','table','baseDataMet','sysHwManager','relate','buffetDOM','buffetLoreDOM','buffetLoreMet'],function(){
			var form = layui.form,
				table = layui.table,
				baseDataMet = layui.baseDataMet,sysHw = layui.sysHwManager,buffetDOM = layui.buffetDOM,blMet = layui.buffetLoreMet,
				blDOM = layui.buffetLoreDOM,relate=layui.relate;
			var page = {
				init : function(){
					this.initDOM();
					this.subLore();
				},
				initDOM : function(){
					var _this = this;
					//获取科目
					baseDataMet.getSubjectList('subjectSel');
					//获取出版社
					baseDataMet.getEditionList('editionSel');
					
					//搜索全局知识点根据拼音和标题
	        	    $('#queryBtn').on('click',function(){
	        	    	relate.searchZsdByTitOrPy();
					});
	        	    $('#zsd_py').on('keypress',function(){
						_this.enterPress(event);
					});
					$('#zsd_txt').on('keypress',function(){
						_this.enterPress(event);
					});
				},
				//搜索的回车事件
				enterPress : function(event){
					var e = e || window.event;
					if(e.keyCode == 13){
						relate.searchZsdByTitOrPy();
					}
				},
				convertEngToChi : function(value){
		    		return value.replace(/,/g,"，").replace(/\s+/g,"").replace(/"/g,"&quot;").replace(/'/g,"&#39;");
		    	},
		    	//提交
		    	subLore : function(){
		    		var _this = this;
		    		$('.subLoreBtn').on('click',function(){
		    			var nowNum = 0,currUeEditCon,currUeEditAnaly,currUeEditAns,
							result='',resTit='',resCon='',resAnaly='',resAns='',resFlag=false,field=null;
		    			var tiganTypeInpVal = $('#tiganTypeInp').val(),
							baseTypeInpVal = $('#baseTypeInp').val(),
							ans_singleInpVal = $('#ans_singleInp').val(),//答案选项
							maxSelInpNumVal = $('#maxSelInpNum').val(),//最大选项
							spaceNum = $('#spaceNumInp').val(),
							inpAnsSelVal='',ansSelRes = '',queTiganFlag=true,baseTypeFlag=true,queSubFlag=true,ansFlag=true;
		    			currUeEditCon = UE.getEditor('con_' + loreType + '_' + nowNum).getContent();
						currUeEditAnaly = UE.getEditor('conAnaly_' + loreType + '_' + nowNum).getContent();
						if(tiganTypeInpVal == 0){
							layer.msg('请选择题干类型', {icon:5,anim:6,time:2000});
							queTiganFlag = false;
							return;
						}
						if(baseTypeInpVal == ''){
							layer.msg('请选择基础类型', {icon:5,anim:6,time:2000});
							baseTypeFlag = false;
							return;
						}
						mindResArr.length = 0;
						abilityResArr.length = 0;
						$('input[name=mindType]').each(function(i){
							var checkStatus = $('input[name=mindType]').eq(i).prop('checked');
							if(checkStatus){
								mindResArr.push($('input[name=mindType]').eq(i).val());
							}
						});
						if(mindResArr.length == 0){
							layer.msg('请选择思维类型', {icon:5,anim:6,time:2000});
							baseTypeFlag = false;
							return;
						}
						$('input[name=abilityType]').each(function(i){
							var checkStatus = $('input[name=abilityType]').eq(i).prop('checked');
							if(checkStatus){
								abilityResArr.push($('input[name=abilityType]').eq(i).val());
							}
						});
						if(abilityResArr.length == 0){
							layer.msg('请选择能力类型', {icon:5,anim:6,time:2000});
							baseTypeFlag = false;
							return;
						}
						if(currUeEditCon == ''){
							layer.msg('题干不能为空', {icon:5,anim:6,time:2000});
							queSubFlag = false;
							return;
						}
						if(tiganTypeInpVal == '单选题' || tiganTypeInpVal == '多选题' || tiganTypeInpVal == '填空选择题'){
							//02判断答案
							if(tiganTypeInpVal == '单选题'){
								if(ans_singleInpVal == ''){
									layer.msg('请选择答案', {icon:5,anim:6,time:2000});
									ansFlag = false;
									return;
								}
							}else if(tiganTypeInpVal == '多选题'){
								multiAnsArr.length = 0;
								$('input[name=answer_multi]').each(function(i){
									var checkStatus = $('input[name=answer_multi]').eq(i).prop('checked');
									if(checkStatus){
										multiAnsArr.push($('input[name=answer_multi]').eq(i).val());
									}
								});
								if(multiAnsArr.length == 0){
									layer.msg('请选择答案', {icon:5,anim:6,time:2000});
									ansFlag = false;
									return;
								}if(multiAnsArr.length == 1){
									layer.msg('该题型为多选题，需至少选择2个或2个以上的答案', {icon:5,anim:6,time:2000});
									ansFlag = false;
									return;
								}
							}else if(tiganTypeInpVal == '填空选择题'){
								var curResAns = result_answer.substring(0,result_answer.lastIndexOf(','));
								if(result_answer == ''){
									layer.msg('请选择答案', {icon:5,anim:6,time:2000});
									ansFlag = false;
									return;
								}else if(curResAns.split(',').length < spaceNum){
									layer.msg('当前所选答案数量和填空数量不匹配!', {icon:5,anim:6,time:2200});
									ansFlag = false;
									return;
								}else if(curResAns.split(',').length > spaceNum){
									layer.msg('当前所选答案累计数超过所选填空数量!', {icon:5,anim:6,time:2000});
									ansFlag = false;
									return;
								}
							}
							if(queTiganFlag && baseTypeFlag && queSubFlag && ansFlag){
								resFlag = true;
							}
						}else if(tiganTypeInpVal == '判断题'){
							if(queTiganFlag && baseTypeFlag && queSubFlag){
								resFlag = true;
							}
						}
						if(resFlag){
							var mindResStr=mindResArr.join(','),abilityResStr=abilityResArr.join(',');
							var fieldCom = {loreId:loreBigId,btId:baseTypeInpVal,mindIdStr:mindResStr,abilityIdStr:abilityResStr,queSub:escape(currUeEditCon),queResolution:escape(currUeEditAnaly),queType:escape(tiganTypeInpVal),optNum:maxSelInpNumVal};
							if(tiganTypeInpVal == '单选题'){
								if(globalOpts == 'add'){
									field = {queAnswer:escape(ans_singleInpVal)};
								}else{
									field = {hwId:bigHwId,queAnswer:escape(ans_singleInpVal)};
								}
							}else if(tiganTypeInpVal == '多选题'){
								var multiAnsStr = multiAnsArr.join(',');
								if(globalOpts == 'add'){
									field = {queAnswer:escape(multiAnsStr)};
								}else{
									field = {hwId:bigHwId,queAnswer:escape(multiAnsStr)};
								}
							}else if(tiganTypeInpVal == '填空选择题'){
								var tmpResAnsTk = result_answer_text.substring(0,result_answer_text.lastIndexOf(','));
								if(globalOpts == 'add'){
									field = {queAnswer:escape(tmpResAnsTk)};
								}else{
									field = {hwId:bigHwId,queAnswer:escape(tmpResAnsTk)};										
								}
							}else if(tiganTypeInpVal == '判断题'){
								var judgeInpVal = $('#judgeInp').val();
								if(globalOpts == 'add'){
									field = {queAnswer:escape(judgeInpVal)};
								}else{
									field = {hwId:bigHwId,queAnswer:escape(judgeInpVal)};	
								}
							}
							//进行对象组合
							field = Object.assign(field,fieldCom);
							if(globalOpts == 'add'){
								var url = '/hw.do?action=addHw';
							}else if(globalOpts == 'edit'){
								var url = '/hw.do?action=updateHwDetail';
							}
							layer.load('1');
							$.ajax({
								type:'post',
						        dataType:'json',
						        data:field,
						        url:url,
						        success:function (json){
						        	layer.closeAll('loading');
						        	if(json['result'] == 'success'){
						        		var title = globalOpts == 'add' ? '添加成功' : '编辑成功';
						        		layer.msg(title,{icon:1,time:1000},function(){
						        			if(globalOpts == 'add'){
						        				$('.loreQuesList').hide();
												$('.loreList').show();
												relate.comBackFun();
												$('#currLoc').html('');
						        			}else{
						        				relate.comBackFun();
												$('.loreQuesList').show();
												$('#currLoc').html('知识点[<span style="color:#F47837;">'+ loreNameBig +'</span>]&gt;题库列表<a class="addEditBack" href="javascript:void(0)">返回知识点列表&gt;</a>');
												//sysHw.getSysHwList(loreBigId);
												_this.bindEvent();
						        			}
		   				        		});
						        	}else if(json['result'] == 'error'){
						        		layer.msg('添加编辑失败，请稍后重试',{icon:5,anim:6,time:1500});
						        	}
						        }
							});
							
						}
						
		    			
		    		});
		    	},
				addLoreQuesInit : function(){
					var topDOM = blDOM.createTiganType();
						baseTypeDOM = buffetDOM.createBaseTypeDOM(),
						mindTypeDOM = buffetDOM.createMindTypeDOM(),
						abilityTypeDOM = buffetDOM.createAbilityTypeDOM(),
						buffetCon = buffetDOM.createBuffetCon(loreType);
					$('.queType').show().html(topDOM + baseTypeDOM + mindTypeDOM + abilityTypeDOM);
					//添加编辑词条
					$('.quesAddEditBox').show();
					
					$('#'+loreType + 'Wrap').show().html(buffetCon);
					UE.delEditor('con_'+ loreType +'_'+currNum);
					buffetDOM.initUeditor('con_'+ loreType +'_'+currNum);
					
					UE.delEditor('conAnaly_'+ loreType +'_'+currNum);
					buffetDOM.initUeditor('conAnaly_'+ loreType +'_'+currNum);
					/*if(globalOpts == 'add'){
						blMet.getCurrLoreTips(loreBigId,true);//获取提示列表
					}*/
					form.render();
					
				},
				renderHwInfo : function(json){
					console.log(json)
					var maxSelBox = blDOM.creaMaxSel(),
						spaceSelBox = blDOM.creaMaxSpace(),
						ansType = blDOM.createAnsType(),//问题类型
						ansSingle = blDOM.createAnsSingle(),
						ansMulti = blDOM.createAnsMulti(),
						wendaStr = blDOM.wendaTypeDOM(loreType),
						judgeStr = blDOM.judgeQueType(),
						tkSelStr = blDOM.createTkSelDOM(),
						tkTypeStr = blDOM.createTkTypeDOM(loreType);
					$('.maxChoice').hide().html(maxSelBox);
					$('.spaceBox').hide().html(spaceSelBox);
					for(var i=0;i<json.btList.length;i++){
						if(json.btList[i].selFlag){
							$('#baseTypeInp').val(json.btList[i].btId);
							$('#baseTypeTxt').html(json.btList[i].btName);
						}
					} 
					for(var i=0;i<json.bmList.length;i++){
						if(json.bmList[i].selFlag){
							$('input[name=mindType]').each(function(j){
								if($('input[name=mindType]').eq(j).val() == json.bmList[i].bmId){
									$('input[name=mindType]').eq(j).attr('checked',true);
								}
							});
						}
					}		
					for(var i=0;i<json.baList.length;i++){
						if(json.baList[i].selFlag){
							$('input[name=abilityType]').each(function(j){
								if($('input[name=abilityType]').eq(j).val() == json.baList[i].baId){
									$('input[name=abilityType]').eq(j).attr('checked',true);
								}
							});
						}
					}
					$('#tiganTypeInp').val(json.hwType);
					$('#tiganTypeTxt').html(json.hwType);
					//匹配最大选项（单选题 多选题 填空选择题）
					if(json.hwType == '单选题' || json.hwType == '多选题' || json.hwType == '填空选择题' || json.hwType == '判断题'){
						$('.maxChoice').show();
						$('#maxSelInpNum').val(json.optNum);//初始化最大选项
						$('#maxChoiceNumSel').val(json.optNum);//初始化最大选项
						$('#ansSelWrap_' + loreType).show();
						
						realAnswer = json.queAnswer;
						if(json.hwType == '单选题'){
							answerNum = json.optNum; //将当前选择的最大选项赋给answerNum
							result_answer = json.queAnswer + ",";
							
							$('#answerSelectDiv_' + loreType).show().html(ansSingle);
							$('#ans_singleInp').val(json.queAnswer);
							blMet.initShowInpByMaxOptNum(json.optNum,'answerBox_singel');
							form.render();
						}else if(json.hwType == '多选题'){
							answerNum = json.optNum; //将当前选择的最大选项赋给answerNum
							$('#answerSelectDiv_' + loreType).show().html(ansMulti);
							blMet.initShowInpByMaxOptNum(json.optNum,'answerBox_multi');
							form.render();
						}else if(json.hwType == '填空选择题'){
							answerNum = json.optNum; //将当前选择的最大选项赋给answerNum
							$('.spaceBox').show();
							$('#spaceNumInp').val(json.queOptNum);//初始化填空数量value
							//匹配填空数量
							$('#spaceNumSel').val(json.queOptNum);
							$('#answerSelectDiv_' + loreType).show().html(tkSelStr);
							blMet.initShowInpByMaxOptNum(json.optNum,'ansBox_multiTk');
							form.render();
						}else if(json.hwType == '判断题'){
							$('.spaceBox').html('');
							$('#answerSelectDiv_' + loreType).show().html(judgeStr);
							$('#judgeInp').val(json.queAnswer);
						}
						sysHw.initAnswerOption(realAnswer);
						$('#'+loreType + 'Inp_' + currNum).val(json.hwTitle).attr('disabled',true);
						buffetDOM.initUeditorContent('con_'+ loreType +'_' + currNum,json.queSub);//题干
						buffetDOM.initUeditorContent('conAnaly_'+ loreType +'_' + currNum,json.queResolution);//解析
					}
				},
				bindEvent : function(){
					var _this = this;
					$('.backBtn_lore').on('click',function(){
						var cptId = $('#chapterInp').val();
						baseDataMet.getLoreList(cptId);
					});
					$('.addEditBack').on('click',function(){
						$('.loreQuesList').hide();
						$('.loreList').show();
						$('.layui-table-fixed').removeClass('layui-hide');
						relate.comBackFun();
						$('#currLoc').html('');
					});
					$('.backBtn_tiku').on('click',function(){
						relate.comBackFun();
						$('.loreQuesList').show();
						$('#currLoc').html('知识点[<span style="color:#F47837;">'+ loreNameBig +'</span>]&gt;题库列表<a class="addEditBack" href="javascript:void(0)">返回知识点列表&gt;</a>');
						_this.bindEvent();
					});
				}
			};
			//每个知识点下对应的自助餐题库列表的编辑和浏览
			table.on('tool(loreQuesTable)',function(obj){
				if(obj.event == 'editFun'){//编辑
					var hwId  = $(this).attr('hwId');
					bigHwId = $(this).attr('hwId');
					layer.load('1');
					$.ajax({
						type:'post',
				        dataType:'json',
				        data:{hwId:hwId},
				        url:'/hw.do?action=getHwDetail',
				        success:function (json){
				        	layer.closeAll('loading');	
				        	if(json.result == 'success'){
				        		realAnswer = '';//每次修改知识点下不同题库 清空全局变量 realAnswer
				        		result_answer = '';
				        		result_answer_text = '';
				        		$('.loreQuesList').hide();//隐藏知识点对应题库列表
				        		currNum = 0;
				        		$('#currLoc').html('<em style="font-style:normal;float:right;">返回[<a class="backBtn_tiku" href="javascript:void(0)">'+ loreNameBig +'</a>]题库列表</em>');
								page.bindEvent();
								page.addLoreQuesInit();
				        		page.renderHwInfo(json);
				        	}else if(json.result == 'error'){
				        		layer.msg('服务器错误',{icon:5,anim:6,time:2000});
				        	}
				        	
				        }
					});
				}else if(obj.event == 'setIsInUseFun'){//设置当前知识点为有效/无效
					var hwId = $(this).attr('hwId'),
						hwTit = $(this).attr('hwTit'),
						inUse = $(this).attr('inUse'),
						inUseTxt = $(this).attr('inUseTxt');
					layer.confirm('是否要将[<span style="color:#F47837;">' + hwTit + '</span>]设置为' + '[<span style="color:#F47837;">'+ inUseTxt +']</span>', {
						title:'提示',
					  	skin: 'layui-layer-molv',
					  	btn: ['确定','取消'] //按钮
					},function(index){
						layer.load('1');
						var field = {hwId:hwId,inUse:inUse};
						$.ajax({
							type:'post',
					        dataType:'json',
					        data:field,
					        url:'/hw.do?action=setInUseStatus',
					        success:function (json){
					        	layer.closeAll('loading');
					        	if(json.result == 'success'){
					        		layer.msg('设置成功',{icon:1,time:1000},function(){
					        			layer.close(index);
						        		sysHw.getSysHwList(loreBigId);
	   				        		});
					        	}else if(json.result == 'error'){
					        		layer.msg('设置失败，请稍后重试',{icon:5,anim:6,time:2000});
					        	}
					        }
						});
						
					});
				}
			});
			//章节下下知识点的添加和编辑（自助餐）
			table.on('tool(loreListTable)',function(obj){
				if(obj.event == 'editFun'){//编辑章节
					var loreName = $(this).attr('loreName');
					loreId = $(this).attr('loreId');
					loreBigId = $(this).attr('loreId');
					globalOpts = $(this).attr('opts');
					loreNameBig = loreName;
					$('.loreQuesList').show();
					$('.loreList').hide();
					$('#currLoc').html('知识点[<span style="color:#F47837;">'+ loreName +'</span>]&gt;系统家庭作业列表<a class="addEditBack" href="javascript:void(0)">返回知识点列表&gt;</a>');
					sysHw.getSysHwList(loreId);
					page.bindEvent();
				}else if(obj.event == 'addFun'){//添加家庭作业
					var loreName = $(this).attr('loreName');
					loreNameBig = loreName;
					loreId = $(this).attr('loreId');
					loreBigId = $(this).attr('loreId');
					globalOpts = $(this).attr('opts');
					$('.loreList').hide();//章节下知识点列表隐藏
					$('#currLoc').html('知识点[<span style="color:#F47837;">'+ loreName +'</span>]&gt;增加系统家庭作业 <a class="addEditBack" href="javascript:void(0)">返回知识点列表&gt;</a>');
					//loreTypeZHN = '知识清单';
					realAnswer = '';//每次修改知识点下不同题库 清空全局变量 realAnswer
	        		result_answer = '';
	        		result_answer_text = '';
					page.bindEvent();
					page.addLoreQuesInit();
				}else if(obj.event == 'viewFun'){//浏览已添加的家庭作业
					var loreName = $(this).attr('loreName');
					loreId = $(this).attr('loreId');
					var viewLore = buffetDOM.createViewLoreDOM();
					layer.open({
						title:'浏览章节[<span style="color:#F47837;">'+ loreName +'</span>]下的家庭作业',
						type: 1,
					  	area: ['660px', '500px'],
					  	fixed: true, //不固定
					  	maxmin: false,
					  	shadeClose :false,
					  	content: viewLore
					});	
					buffetDOM.loadLoreDetail(loreId);
				}
			});
			page.init();
		});
	</script>
</html>
