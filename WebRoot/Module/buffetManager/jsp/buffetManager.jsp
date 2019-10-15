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
	<link href="/plugins/layui/css/modules/layui-icon-extend/iconfont.css" rel="stylesheet" type="text/css"/>
	<link href="/Module/commonJs/ueditor/themes/default/css/ueditor.min.css" type="text/css" rel="stylesheet" />
	<link href="/Module/loreManager/css/loreManager.css" rel="stylesheet" type="text/css"/>
	<link href="/Module/loreManager/css/viewLore.css" rel="stylesheet" type="text/css"/>
	<link href="/plugins/pace/pace-theme-flash.min.css" rel="stylesheet" type="text/css"/>
	<script src="/plugins/pace/pace.min.js" type="text/javascript"></script>	
    </head>
    <style>
    	.layui-form-checkbox[lay-skin=primary] span{color:#333;}
    </style>
	<body>
		<div class="layui-card-header loreHeader">
			<span style="float:left;">自助餐管理</span>
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
		<div class="getSrcLayer"></div>
		<div class="getImgSrcBox">
			<strong>答案选项上传<a class="closeImgLayer" href="javascript:void(0)"><i class="layui-icon layui-icon-close"></i></a></strong>
			<div id="myEditor_answer_select"></div>
			<div class="sureBtn"><a class="selImgSrc layui-btn" href="javascript:void(0)">确定</a></div>
		</div>
	</body>
	<script src="/plugins/jquery/jquery.min.js"></script>
   	<script src="/plugins/layui/layui.js"></script>
   	<script src="/Module/commonJs/ueditor/ueditor.config.js"></script>
	<script src="/Module/commonJs/ueditor/ueditor.all.min.js"></script>
	
	<script type="text/javascript">
		var loreType = 'zzc';//自助餐
		var searOpt='loreManager',nowSearType=1;
		var infoBySubOpt = 'getGradeOpt',//根据学科获取年级的信息
			infoByGsEdOpt='geteduC',//根据年级出版社获取教材信息 need
			addEditFlag  = false,editAll='',//出版社全拼id:name
			switchFlag=false,//用于填空题问答题这块切换题型时是否打开问题选项 true->打开 
			globalOpts='',cptId=0,currPage='buffetPage',bigBuffetId=0,lexContent='',
			loreId=0,currNum=0,loreNameBig='',loreBigTit='',loreBigId=0,lqBigId=0,realAnswer='';//是否可以增加点拨指导和知识讲解;
		var result_answer = "";//ABCD
		var result_answer_text = "",answerNum=0,answerType='',
			multiAnsArr=[],queTipsArr=[],mindResArr=[],abilityResArr=[];//复选框text
		
		layui.config({
			base: '/plugins/frame/js/'
		}).use(['layer','table','form','baseDataMet','relate','buffetManager','buffetDOM','buffetLoreMet','buffetLoreDOM','lorePractice'], function() {
			var layer = layui.layer,
				table = layui.table,
				form = layui.form,
				baseDataMet = layui.baseDataMet,
				buffet = layui.buffetManager,buffetDOM = layui.buffetDOM,blDOM = layui.buffetLoreDOM,blMet = layui.buffetLoreMet,
				lorePrac = layui.lorePractice,relate=layui.relate;
			var page = {
				init : function(){
					this.initDOM();
					this.initImgEditor();
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
				initImgEditor : function(){
					editor_answer_select = new baidu.editor.ui.Editor( {
	        			//这里可以选择自己需要的工具按钮名称,此处仅选择如下五个  
	        	        toolbars:[['Source', 'italic','bold', 'underline',
	        	                   'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify','|',
	        	                   'snapscreen']], 
	        			initialFrameWidth : 450,
	        			initialFrameHeight : 250,
	        			wordCount:false,
	        			textarea : 'description'
	        		});
	        		//禁止粘贴
	        		editor_answer_select.addListener('beforepaste', myEditor_answer_select); 
	        	    function myEditor_answer_select(o, html) {
	        	        html.html = "";
	        	    }
	        	    editor_answer_select.render("myEditor_answer_select");
				},
				convertEngToChi : function(value){
		    		//return value.replace(/,/g,"，").replace(/\s+/g,"").replace(/"/g,"”").replace(/'/g,"&#wmd;");
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
							//tiganType1InpVal = $('#tiganType1Inp').val(),
							answSelTypeInpVal = $('#answSelTypeInp').val(),
							ans_singleInpVal = $('#ans_singleInp').val(),//答案选项
							maxSelNum = $('#maxSelInpNum').val(),
							spaceNum = $('#spaceNumInp').val(),
							inpAnsSelVal='',ansSelRes = '',isHasSameAns=[],
							queTiganFlag=true,baseTypeFlag=true,
							queSubFlag=true,ansSelFlag=true,ansFlag=true;
						
						
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
							//01检测答案选项
							if(answSelTypeInpVal == 1){//文字
								for(var i=1;i<=maxSelNum;i++){
									inpAnsSelVal = $.trim($('#answSelInpTxt' + i).val());
									if(inpAnsSelVal == ''){
										layer.msg('答案选项不能为空', {icon:5,anim:6,time:2000});
										$('#answSelInpTxt' + i).focus();
										ansSelFlag = false;
										return;
									}else if(inpAnsSelVal != ''){
										isHasSameAns.push(inpAnsSelVal);
									}
								}
								for(var i=0;i<=isHasSameAns.length;i++){
									for(var j=i+1;j<isHasSameAns.length;j++){
										if(isHasSameAns[i].replace(/,/g,"，").replace(/\s+/g,"") == isHasSameAns[j].replace(/,/g,"，").replace(/\s+/g,"")){
											layer.msg('答案选项不能相同', {icon:5,anim:6,time:2000});
											ansSelFlag = false;
											return;
										}
									}
								}
							}else if(answSelTypeInpVal == 2){//图片
								for(var i = 1 ; i <= maxSelNum ; i++){
									if(blMet.getId('answerSelect'+i).src.indexOf('defImg.png') > 0){
										layer.msg('请将答案选项填写完整', {icon:5,anim:6,time:2000});
										ansSelFlag = false;
										return;
									}
								}
							}
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
						}else if(tiganTypeInpVal == '填空题'){
							if($('#tkInp_' + loreType).val() == ''){
								layer.msg('请填写答案', {icon:5,anim:6,time:2000});
								ansFlag = false;
								return;
							}
						}else if(tiganTypeInpVal == '问答题'){
							$('.'+loreType + '_Inp').each(function(i){
								nowNum = $(this).attr('currNum');
							});
							//答案
							currUeEditAns = UE.getEditor('wenda_' + loreType + '_' + nowNum).getContent();
							if(currUeEditCon == ''){
								layer.msg('题干不能为空', {icon:5,anim:6,time:2000});
								queSubFlag = false;
								return;
							}else if(currUeEditAns == ''){
								layer.msg('答案不能为空', {icon:5,anim:6,time:2000});
								ansFlag = false;
								return;
							}
						}
						if(queTiganFlag && baseTypeFlag && queSubFlag && ansSelFlag && ansFlag){
							resFlag = true;
						}
						if(resFlag){//ajax
							var queTipsId = $('#tipsInp_' + loreType).val() == '' ? 0 : $('#tipsInp_' + loreType).val()	,
									lexId  = $('#'+loreType+'_lexId').val(),mindResStr=mindResArr.join(','),abilityResStr=abilityResArr.join(',');
							if(tiganTypeInpVal == '单选题' || tiganTypeInpVal == '多选题' || tiganTypeInpVal == '填空选择题'){
								var answerA = answSelTypeInpVal == 1 ? _this.convertEngToChi($('#answSelInpTxt1').val()) : $('#answerSelect1').attr('currSrc'),
									answerB = answSelTypeInpVal == 1 ? _this.convertEngToChi($('#answSelInpTxt2').val()) : $('#answerSelect2').attr('currSrc'),
									answerC = answSelTypeInpVal == 1 ? _this.convertEngToChi($('#answSelInpTxt3').val()) : $('#answerSelect3').attr('currSrc'),
									answerD = answSelTypeInpVal == 1 ? _this.convertEngToChi($('#answSelInpTxt4').val()) : $('#answerSelect4').attr('currSrc'),
									answerE = answSelTypeInpVal == 1 ? _this.convertEngToChi($('#answSelInpTxt5').val()) : $('#answerSelect5').attr('currSrc'),
									answerF = answSelTypeInpVal == 1 ? _this.convertEngToChi($('#answSelInpTxt6').val()) : $('#answerSelect6').attr('currSrc'),
									fieldCom = {queType:escape(tiganTypeInpVal),mindStr:mindResStr,abilityIdStr:abilityResStr,queSub:escape(currUeEditCon),queTipId:queTipsId,queResolution:escape(currUeEditAnaly),
											lexId:lexId,answerA:escape(answerA),answerB:escape(answerB),answerC:escape(answerC),answerD:escape(answerD),answerE:escape(answerE),answerF:escape(answerF)};
							}else if(tiganTypeInpVal == '判断题'){
								var fieldCom = {queType:escape(tiganTypeInpVal),mindStr:mindResStr,abilityIdStr:abilityResStr,queSub:currUeEditCon,queTipId:queTipsId,queResolution:escape(currUeEditAnaly),
										lexId:lexId,answerA:escape($('#ansSelJudgeInp1').val()),answerB:escape($('#ansSelJudgeInp2').val())};
							}else if(tiganTypeInpVal == '填空题' || tiganTypeInpVal == '问答题'){
								var fieldCom = {queType:escape(tiganTypeInpVal),mindStr:mindResStr,abilityIdStr:abilityResStr,queSub:escape(currUeEditCon),queTipId:queTipsId,queResolution:escape(currUeEditAnaly),
										lexId:lexId};
							}
							if(tiganTypeInpVal == '单选题'){
								if(globalOpts == 'add'){
									field = {loreId:loreBigId,btId:baseTypeInpVal,queAnswer:escape(ans_singleInpVal)};
								}else{
									field = {buffetId:bigBuffetId,queAnswer:escape(ans_singleInpVal)};
								}
							}else if(tiganTypeInpVal == '多选题'){
								var multiAnsStr = multiAnsArr.join(',');
								if(globalOpts == 'add'){
									field = {loreId:loreBigId,btId:baseTypeInpVal,queAnswer:escape(multiAnsStr)};
								}else{
									field = {buffetId:bigBuffetId,queAnswer:escape(multiAnsStr)};
								}
							}else if(tiganTypeInpVal == '填空选择题'){
								var tmpResAnsTk = result_answer_text.substring(0,result_answer_text.lastIndexOf(','));
								if(globalOpts == 'add'){
									field = {loreId:loreBigId,btId:baseTypeInpVal,queAnswer:escape(tmpResAnsTk)};
								}else{
									field = {buffetId:bigBuffetId,queAnswer:escape(tmpResAnsTk)};										
								}
							}else if(tiganTypeInpVal == '判断题'){
								var judgeInpVal = $('#judgeInp').val();
								if(globalOpts == 'add'){
									field = {loreId:loreBigId,btId:baseTypeInpVal,queAnswer:escape(judgeInpVal)};
								}else{
									field = {buffetId:bigBuffetId,queAnswer:escape(judgeInpVal)};	
								}
							}else if(tiganTypeInpVal == '填空题'){
								var tkVal = $('#tkInp_' + loreType).val();
								if(globalOpts == 'add'){
									field = {loreId:loreBigId,btId:baseTypeInpVal,queAnswer:escape(tkVal)};
								}else{
									field = {buffetId:bigBuffetId,queAnswer:escape(tkVal)};
								}
							}else if(tiganTypeInpVal == '问答题'){
								if(globalOpts == 'add'){
									field = {loreId:loreBigId,btId:baseTypeInpVal,queAnswer:escape(currUeEditAns)};
								}else{
									field = {buffetId:bigBuffetId,queAnswer:escape(currUeEditAns)};
								}
							}
							
							//进行对象组合
							field = Object.assign(field,fieldCom);
							if(globalOpts == 'add'){
								var url = '/buffet.do?action=addBuffet';
							}else if(globalOpts == 'edit'){
								var url = '/buffet.do?action=updateBuffetDetail';
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
												//buffet.getBuffetList(loreBigId);
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
				},
				addLoreQuesInit : function(){
					var topDOM = blDOM.createTiganType();
						lexStrDOM = blDOM.createLexDOM(loreType),
						baseTypeDOM = buffetDOM.createBaseTypeDOM(),
						mindTypeDOM = buffetDOM.createMindTypeDOM(),
						abilityTypeDOM = buffetDOM.createAbilityTypeDOM(),
						buffetCon = buffetDOM.createBuffetCon(loreType);
					$('.queType').show().html(topDOM + lexStrDOM + baseTypeDOM + mindTypeDOM + abilityTypeDOM);
					//添加编辑词条
					blMet.addEditLex();
					$('.quesAddEditBox').show();
					
					$('#'+loreType + 'Wrap').show().html(buffetCon);
					UE.delEditor('con_'+ loreType +'_'+currNum);
					buffetDOM.initUeditor('con_'+ loreType +'_'+currNum);
					
					UE.delEditor('conAnaly_'+ loreType +'_'+currNum);
					buffetDOM.initUeditor('conAnaly_'+ loreType +'_'+currNum);
					if(globalOpts == 'add'){
						blMet.getCurrLoreTips(loreBigId,true);//获取提示列表
					}
					form.render();
					
				},
				//编辑回显公共创建editor
				createComLoreDom : function(){
					var loreCon = buffetDOM.createBuffetCon(loreType);
					$('#'+loreType + 'Wrap').show().append(loreCon);
					UE.delEditor('con_'+ loreType +'_'+currNum);
					buffetDOM.initUeditor('con_'+ loreType +'_'+currNum);
					//增加解析
					UE.delEditor('conAnaly_'+ loreType +'_' +currNum);
					buffetDOM.initUeditor('conAnaly_'+ loreType + '_'+currNum);					
				},
				//回显题库列表每个对应的详情 （编辑)
				renderbuffetTypeInfo : function(json){
					var maxSelBox = blDOM.creaMaxSel(),
						spaceSelBox = blDOM.creaMaxSpace(),
						ansType = blDOM.createAnsType(),//问题类型
						selAnsTxt = blDOM.createSelAnsTxt(),
						selAnsImg = blDOM.createSelAnsImg(),
						ansSingle = blDOM.createAnsSingle(),
						ansMulti = blDOM.createAnsMulti(),
						wendaStr = blDOM.wendaTypeDOM(loreType),
						judgeStr = blDOM.judgeQueType(),
						tkSelStr = blDOM.createTkSelDOM(),
						tkTypeStr = blDOM.createTkTypeDOM(loreType);
				
					//增加关联词条
					//$('.queType').append(tiganTypeDOM + lexStrDOM);
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
					$('#tiganTypeInp').val(json.queType);
					blMet.data.originTypeTxt = json.queType;//用于填空题切换其它题型时做个原来题型的存储
					$('#tiganTypeTxt').html(json.queType);
					//渲染关联此条内容
					if(json.lexId == 0){
						$('#'+loreType + '_lexInp').val('');
					}else{
						$('#'+loreType + '_lexInp').val(json.lexInfo[0].lexTitle);
						lexContent = json.lexInfo[0].lexContent;
					}
					//匹配 了解 理解 应用 综合匹配
					//$('#tiganType1Sel').val(listInfo[i].queType2);
					//匹配最大选项（单选题 多选题 填空选择题）
					if(json.queType == '单选题' || json.queType == '多选题' || json.queType == '填空选择题' || json.queType == '判断题'){
						$('.maxChoice').show();
						$('#ansSelWrap_' + loreType).show();
						$('#maxSelInpNum').val(json.queOptNum);//初始化最大选项
						
						var answer1 = json.anserA,
							answer2 = json.anserB,
							answer3 = json.anserC,
							answer4 = json.anserD,
							answer5 = json.anserE,
							answer6 = json.anserF;
						
						realAnswer = json.queAnswer;
						if(json.queType == '单选题'){
							$('#maxChoiceNumSel').val(json.queOptNum);
							answerNum = json.queOptNum; //将当前选择的最大选项赋给answerNum
							
							result_answer = json.queAnswer + ",";
							$('#answerSelectDiv_' + loreType).show().html(ansType + selAnsTxt + selAnsImg + ansSingle);
							$('#ans_singleInp').val(json.queAnswer);
							blMet.initShowInpByMaxOptNum(json.queOptNum,'answerBox_singel');
		        			blMet.inputBlur();
							form.render();
						}else if(json.queType == '多选题'){
							$('#maxChoiceNumSel').val(json.queOptNum);
							answerNum = json.queOptNum; //将当前选择的最大选项赋给answerNum
							
							$('#answerSelectDiv_' + loreType).show().html(ansType + selAnsTxt + selAnsImg + ansMulti);
							blMet.initShowInpByMaxOptNum(json.queOptNum,'answerBox_multi');
							blMet.inputBlur();
							form.render();
						}else if(json.queType == '填空选择题'){
							$('#maxChoiceNumSel').val(json.queOptNum);
							answerNum = json.queOptNum; //将当前选择的最大选项赋给answerNum
							$('.spaceBox').show();
							$('#spaceNumInp').val(json.answerNum);//初始化填空数量value
							//匹配填空数量
							$('#spaceNumSel').val(json.answerNum);
							$('#answerSelectDiv_' + loreType).show().html(ansType + selAnsTxt + selAnsImg + tkSelStr);
							blMet.initShowInpByMaxOptNum(json.queOptNum,'ansBox_multiTk');
							//文字类型的 调用input的blur事件
		        			blMet.inputBlur();
							form.render();
						}else if(json.queType == '判断题'){
							$('.maxChoice').html('');
							$('.spaceBox').html('');
							$('#answerSelectDiv_' + loreType).show().html(judgeStr);
							$('#judgeInp').val(json.queAnswer);
						}
						lorePrac.initAnswerOption(lorePrac.findAnserType(answer1),answer1,answer2,answer3,answer4,answer5,answer6);
						
					}else if(json.queType == '问答题'){
						$('#ansSelWrap_' + loreType).show();
						$('.switchTgGanTypeBox').show();//切换题型显示
						blMet.switchTiganFun();//调用切换题型方法
						blMet.resetOriginTiganType();//还原原题型方法
						$('#answerSelectDiv_' + loreType).hide().html('');
						$('#nowTxt_'+loreType).html('答案：');
						$('#wendaTypeWrap_'+loreType).show().html(wendaStr);
						//增加答案
						UE.delEditor('wenda_'+loreType +'_' + currNum);
						loreDOM.initUeditor('wenda_'+loreType +'_' + currNum);
						loreDOM.initUeditorContent('wenda_'+ loreType +'_' + currNum,json.queAnswer);
					}else if(json.queType == '填空题'){
						$('.switchTgGanTypeBox').show();//切换题型显示
						$('#ansSelWrap_' + loreType).show();
						blMet.switchTiganFun();//调用切换题型方法
						blMet.resetOriginTiganType();//还原原题型方法
						$('.maxChoice').html('');
						$('#wendaTypeWrap_'+loreType).hide().html('');
						$('#answerSelectDiv_' + loreType).hide().html('');
						$('.spaceBox').show();
						$('#spaceNumInp').val(json.answerNum);//初始化填空数量value
						//匹配填空数量
						$('#spaceNumSel').val(json.answerNum);
						$('#nowTxt_'+loreType).html('答案：');
						$('#tkTypeWrap_' + loreType).show().html(tkTypeStr);
						$('#tkInp_' + loreType).val(json.queAnswer);
						blMet.data.tkOriginAnsTxt = json.queAnswer;
					}
					$('#'+loreType + 'Inp_' + currNum).val(json.title).attr('disabled',true);
					buffetDOM.initUeditorContent('con_'+ loreType +'_' + currNum,json.queSub);//题干
					buffetDOM.initUeditorContent('conAnaly_'+ loreType +'_' + currNum,json.queResolution);//解析
					//if(json.queTipId != 0){
					blMet.renderTipsSelect(json.tipsList);
					queTipsArr = json.tipsList;
					if(json.queTipId != 0){
						//添加编辑提示
						$('#selTipsSel_' + loreType).val(json.queTipId);
						$('#tipsInp_zzc').val(json.queTipId);
						for(var i=0;i<json.tipsList.length;i++){
							if(json.tipsList[i].selStatus){
								$('#tipsCon_' + loreType).html(json.tipsList[i].lqsContent);
							}
						}
					}
					form.render();
				}
			};
			//每个知识点下对应的自助餐题库列表的编辑和浏览
			table.on('tool(loreQuesTable)',function(obj){
				if(obj.event == 'editFun'){//编辑
					var buffetId  = $(this).attr('buffetId');
					bigBuffetId = $(this).attr('buffetId');
					layer.load('1');
					$.ajax({
						type:'post',
				        dataType:'json',
				        data:{buffetId:buffetId},
				        url:'/buffet.do?action=getBuffetDetail',
				        success:function (json){
				        	layer.closeAll('loading');	
				        	if(json.result == 'success'){
				        		realAnswer = '';//每次修改知识点下不同题库 清空全局变量 realAnswer
				        		result_answer = '';
				        		result_answer_text = '';
				        		//var listInfo = json.listIfo;
				        		$('.loreQuesList').hide();//隐藏知识点对应题库列表
				        		currNum = 0;
				        		$('#currLoc').html('<em style="font-style:normal;float:right;">返回[<a class="backBtn_tiku" href="javascript:void(0)">'+ loreNameBig +'</a>]题库列表</em>');
								page.bindEvent();
								page.addLoreQuesInit();
								lexContent = '';
				        		page.renderbuffetTypeInfo(json);
				        	}else if(json.result == 'error'){
				        		layer.msg('服务器错误',{icon:5,anim:6,time:2000});
				        	}
				        	
				        }
					});
				}else if(obj.event == 'setIsInUseFun'){//设置当前知识点为有效/无效
					var buffetId = $(this).attr('buffetId'),
						buffetTit = $(this).attr('buffetTit'),
						inUse = $(this).attr('inUse'),
						inUseTxt = $(this).attr('inUseTxt');
					layer.confirm('是否要将[<span style="color:#F47837;">' + buffetTit + '</span>]设置为' + '[<span style="color:#F47837;">'+ inUseTxt +']</span>', {
						title:'提示',
					  	skin: 'layui-layer-molv',
					  	btn: ['确定','取消'] //按钮
					},function(index){
						layer.load('1');
						var field = {buffetId:buffetId,inUse:inUse};
						$.ajax({
							type:'post',
					        dataType:'json',
					        data:field,
					        url:'/buffet.do?action=setInUseStatus',
					        success:function (json){
					        	layer.closeAll('loading');
					        	if(json.result == 'success'){
					        		layer.msg('设置成功',{icon:1,time:1000},function(){
					        			layer.close(index);					        			
						        		buffet.getBuffetList(loreBigId);
	   				        		});
					        	}else if(json.result == 'error'){
					        		layer.msg('设置失败，请稍后重试',{icon:5,anim:6,time:2000});
					        	}
					        }
						});
						
					});
				}else if(obj.event == 'relateLoreFun'){//关联知识点
					bigBuffetId = $(this).attr('buffetId');
					loreBigTit = $(this).attr('loreTit');
					layer.open({
						title:'',
						type: 2,
					  	area: ['1000px', '560px'],
					  	fixed: true, //不固定
					  	maxmin: false,
					  	shadeClose :false,
					  	closeBtn:0,
					  	content: '/Module/buffetManager/jsp/loreRelate.html',
					  	end : function(){
					  		window.localStorage.removeItem('relateObj');
					  	}
					});	
				}else if(obj.event == 'relateLexFun'){
					bigBuffetId = $(this).attr('buffetId');
					lexOpts = $(this).attr('opts');
					layer.open({
						title:'添加编辑词条',
						type: 2,
					  	area: ['850px', '550px'],
					  	fixed: true, //不固定
					  	maxmin: false,
					  	shadeClose :false,
					  	content: '/Module/loreManager/jsp/addEditLex.html'
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
					//addEditFlag = false;
					$('.loreQuesList').show();
					$('.loreList').hide();
					$('#currLoc').html('知识点[<span style="color:#F47837;">'+ loreName +'</span>]&gt;题库列表<a class="addEditBack" href="javascript:void(0)">返回知识点列表&gt;</a>');
					buffet.getBuffetList(loreId);
					page.bindEvent();
				}else if(obj.event == 'addFun'){//添加自助餐
					var loreName = $(this).attr('loreName');
					loreNameBig = loreName;
					loreId = $(this).attr('loreId');
					loreBigId = $(this).attr('loreId');
					globalOpts = $(this).attr('opts');
					$('.loreList').hide();//章节下知识点列表隐藏
					$('#currLoc').html('知识点[<span style="color:#F47837;">'+ loreName +'</span>]&gt;增加题库 <a class="addEditBack" href="javascript:void(0)">返回知识点列表&gt;</a>');
					//loreTypeZHN = '知识清单';
					realAnswer = '';//每次修改知识点下不同题库 清空全局变量 realAnswer
	        		result_answer = '';
	        		result_answer_text = '';
					page.bindEvent();
					page.addLoreQuesInit();
				}else if(obj.event == 'viewFun'){//浏览自助餐
					var loreName = $(this).attr('loreName');
					loreId = $(this).attr('loreId');
					var viewLore = buffetDOM.createViewLoreDOM();
					layer.open({
						title:'浏览章节[<span style="color:#F47837;">'+ loreName +'</span>]下的巴菲特',
						type: 1,
					  	area: ['660px', '500px'],
					  	fixed: true, //不固定
					  	maxmin: false,
					  	shadeClose :false,
					  	content: viewLore
					});	
					buffetDOM.loadLoreDetail(loreId);
				}else if(obj.event == 'viewLoreTree'){//查看知识树
					var loreName = $(this).attr('loreName');
					loreBigId = $(this).attr('loreId');
					layer.open({
						title:'知识点[<span style="color:#F47837;">'+ loreName +'</span>]--知识树',
						type: 2,
					  	area: ['450px', '500px'],
					  	fixed: true, //不固定
					  	maxmin: false,
					  	shadeClose :false,
					  	content: '/Module/loreManager/jsp/viewLoreTree.html'
					});	
				}else if(obj.event == 'mergeLoreFun'){//合并知识点
					var loreId = $(this).attr('loreId'),
						cptId = $('#chapterInp').val(),
						loreName = $(this).attr('loreName');
					loreBigId = $(this).attr('loreId');
					layer.open({
						title:'[<span style="color:#F47837;">'+ loreName +'</span>]对应知识点',
						type: 2,
					  	area: ['450px', '500px'],
					  	fixed: true, //不固定
					  	maxmin: false,
					  	shadeClose :false,
					  	content: '/Module/buffetManager/jsp/mergeLore.html'
					});	
				}
			});
			page.init();
		});
		//删除关联此条
		function delLex(obj){
			var currType = $(obj).attr('currType');
			$('#'+currType + '_lexId').val('');
			$('#'+currType + '_lexInp').val('');
		}
	</script>
</html>
