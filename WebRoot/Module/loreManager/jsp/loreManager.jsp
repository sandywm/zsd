<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html> 
<html>
  <head>
    <title>知识点管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache"> 
	<meta http-equiv="keywords" content="知识典管理系统,知识点管理">
	<meta http-equiv="description" content="知识点管理">
	<link href="/plugins/layui/css/layui.css" rel="stylesheet" type="text/css"/>
	<link href="/plugins/layui/css/modules/layui-icon-extend/iconfont.css" rel="stylesheet" type="text/css"/>
	<link href="/Module/commonJs/ueditor/themes/default/css/ueditor.min.css" type="text/css" rel="stylesheet" />
	<link href="/Module/loreManager/css/loreManager.css" rel="stylesheet" type="text/css"/>
	<link href="/Module/loreManager/css/viewLore.css" rel="stylesheet" type="text/css"/>
	<link href="/plugins/pace/pace-theme-flash.min.css" rel="stylesheet" type="text/css"/>
	<script src="/plugins/pace/pace.min.js" type="text/javascript"></script>	
    </head>
	<body>
		<div class="layui-card-header loreHeader">
			<span style="float:left;">知识点管理</span>
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
				<!-- div class="itemDivs" style="margin-right:0px;">
					<div class="layui-input-inline">
						<button id="queryBtn" class="layui-btn"><i class="layui-icon layui-icon-search"></i></button>
					</div>
				</div -->
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
	  							<div id="zsqdWrap" class="comQuesBox" style="display:block;"></div>
	  							<div id="zhutiWrap" class="layui-tab layui-tab-brief guideWrap" lay-filter="guideNavFilter">
	  								<ul class="layui-tab-title">
	  									<li queType="zhuti" isCreaFlag="0" class="layui-this guideNavLi">主题</li>
	  									<li queType="zd" isCreaFlag="0" class="guideNavLi">重点</li>
	  									<li queType="nd" isCreaFlag="0" class="guideNavLi">难点</li>
	  									<li queType="gjd" isCreaFlag="0" class="guideNavLi">关键点</li>
	  									<li queType="yhd" isCreaFlag="0" class="guideNavLi">易混点</li>
	  								</ul>
	  								<p id="guideTips"></p>
	  								<div class="layui-tab-content">
	  									<!-- id="zhutiWrap" zhuti -->
	  									<div id="zhuti" class="layui-tab-item layui-show guideBox"></div>
	  									<div id="zd" class="layui-tab-item guideBox"></div>
	  									<div id="nd" class="layui-tab-item guideBox"></div>
	  									<div id="gjd" class="layui-tab-item guideBox"></div>
	  									<div id="yhd" class="layui-tab-item guideBox"></div>
	  								</div>
	  								<!--  a class="addGuide" href="javascript:void(0)">添加重点</a-->
	  							</div>
	  							<div id="jtsfWrap" class="comQuesBox"></div>
	  							<div id="ggxlWrap" class="comQuesBox"></div>
	  							<div id="zdxzdWrap" class="comQuesBox"></div>
	  							<div id="zczdWrap" class="comQuesBox"></div>
	  							<div id="zsjjWrap" class="comQuesBox"></div>
	  							
	  							<div id="subBtnBox">
	  								<a href="javascript:void(0)" class="layui-btn subLoreBtn">提交</a>
	  							</div>
	  						</div>
	  					</div>
					</div>
				</div>
			</div>
		</div>
		<a class="addLoreCon" href="javascript:void(0)"><i class="layui-icon layui-icon-add-1"></i><span>知识清单</span></a>
		<a class="addZhuti" href="javascript:void(0)"><i class="layui-icon layui-icon-add-1"></i><span>主题</span></a>
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
		var loreType = 'zsqd';//点拨知道题型
		var tmpGuideType = '';//用于判定点拨指导下不是主题情况下添加按钮显示的判断
		var isAddClickFlag = false;//用于判断是否是通过增加按钮来添加的flag
	
		var infoBySubOpt = 'getGradeOpt',nowSearType=1,searOpt='loreManager',//根据学科获取年级的信息
			infoByGsEdOpt='geteduC',//根据年级出版社获取教材信息 need
			addEditFlag  = false,editAll='',//出版社全拼id:name
			globalOpts='',cptId=0,currPage='lorePage',
			loreId=0,currNum=0,loreNameBig='',loreBigId=0,lqBigId=0,maxQueNum=0,loreTypeZHN='知识清单',realAnswer='',isCanAdd='';//是否可以增加点拨指导和知识讲解;
		var result_answer = "";//ABCD
		var result_answer_text = "",answerNum=0,answerType='',
			smLoreTypeZHN='主题',//用于点拨指导类型的判断
			multiAnsArr=[],queTipsArr=[],currNumLen=0;//复选框text
		
		layui.config({
			base: '/plugins/frame/js/'
		}).use(['layer','table','form','baseDataMet','relate','uploadPro','loreManager','comLoreDOM','buffetLoreDOM','buffetLoreMet','lorePractice'], function() {
			var layer = layui.layer,
				table = layui.table,
				form = layui.form,
				baseDataMet = layui.baseDataMet,
				uploadPro = layui.uploadPro,
				lore = layui.loreManager,loreDOM = layui.comLoreDOM,
				blDOM = layui.buffetLoreDOM,blMet = layui.buffetLoreMet,
				lorePrac = layui.lorePractice,relate=layui.relate;
			var page = {
				init : function(){
					this.initDOM();
					this.addLoreCon();
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
				//添加对应题型(知识清单 点拨指导 重点 难点 易混点 关键点)
				addLoreCon : function(){
					$('.addLoreCon').on('click',function(){
						isAddClickFlag = true;
						if(loreType == 'zsqd'){
							var loreCon = loreDOM.creaLoreConDOM(loreType);
							$('#'+loreType + 'Wrap').append(loreCon);
							UE.delEditor('con_'+ loreType +'_'+currNum);
							loreDOM.initUeditor('con_'+ loreType +'_'+currNum);
							if(globalOpts == 'edit'){
								currNum ++;//编辑添加时++
							}
						}else if(loreType == 'zd' || loreType == 'nd' || loreType == 'gjd' || loreType == 'yhd'){
							//currNum = currNumLen;
							if(globalOpts == 'edit'){
								currNum ++;//编辑添加时++
							}
							var loreCon = loreDOM.creaLoreConDOM(loreType);
							$('#'+loreType).append(loreCon);
							UE.delEditor('con_'+ loreType +'_'+currNum);
							loreDOM.initUeditor('con_'+ loreType +'_'+currNum);
						}
					});
					$('.addZhuti').on('click',function(){
						if($('#zhuti').html() == ''){
							$('#zhuti').html(loreDOM.creaLoreConDOM('zhuti'));
							UE.delEditor('con_zhuti_' + currNum);
							loreDOM.initUeditor('con_zhuti_' + currNum);
							$('#zhutiInp_' + currNum).val(loreNameBig);
						}else{
							layer.msg('主题只能有一个，不能重复添加', {icon:5,anim:6,time:2000});
						}
						$('.addZhuti').hide();
					});
				},
				//提交
				subLore : function(){
					var _this = this;
					$('.subLoreBtn').on('click',function(){
						var inputTitVal,nowNum = 0,currUeEditCon,currUeEditAnaly,currUeEditAns,zsjjInpVal=$('#zsjjInp').val(),
							result='',resTit='',resCon='',resAnaly='',resAns='',resFlag=false,field=null;
						if(loreType == 'zsqd' || loreType == 'zd' || loreType == 'nd' || loreType == 'gjd' || loreType == 'yhd'){//知识清单
							if($('.'+loreType + '_Inp').length > 0){
								$('.'+loreType + '_Inp').each(function(i){
									inputTitVal = $(this).val(),nowNum = $(this).attr('currNum');
									currUeEditCon = UE.getEditor('con_' + loreType + '_' + nowNum).getContent();
									if(isCanAdd == 'editZt'){
										layer.msg('当前已存在主题!重点、难点、关键点、易混点，暂不能增加!', {icon:5,anim:6,time:2200});
										resCon = '';
										resTit = '';
										return false;
									}else if(inputTitVal == ''){
										layer.msg('标题不能为空', {icon:5,anim:6,time:2000});
										$(this).focus();
										resTit = '';
										return false;
									}else if(currUeEditCon == ''){
										layer.msg('内容不能为空', {icon:5,anim:6,time:2000});
										resCon = '';
										return false;
									}else if(inputTitVal != "" && currUeEditCon != ""){
										resTit += inputTitVal + '&zsd&';
										resCon += currUeEditCon + '&zsd&';
									}
								});
							}else{
								if(loreType == 'zd' || loreType == 'nd' || loreType == 'gjd' || loreType == 'yhd'){
									if(isCanAdd == 'editZt'){
										layer.msg('当前已存在主题!重点、难点、关键点、易混点，暂不能增加!', {icon:5,anim:6,time:2200});
										resCon = '';
										resTit = '';
									}else{
										layer.msg('当前' + smLoreTypeZHN + '个数为0，请至少添加一项进行保存', {icon:5,anim:6,time:2000});
									}
								}else{
									layer.msg('当前' + smLoreTypeZHN + '个数为0，请至少添加一项进行保存', {icon:5,anim:6,time:2000});
								}
								return;
							}
							if(resTit != '' && resCon != ''){
								resFlag = true;
							}else{
								resFlag = false;
							}
						}else if(loreType == 'zhuti'){
							if($('.'+loreType + '_Inp_zhuti').length > 0){
								$('.'+loreType + '_Inp_zhuti').each(function(){
									nowNum = $(this).attr('currNum');
								});
								currUeEditCon = UE.getEditor('con_' + loreType + '_' + nowNum).getContent();
								if(isCanAdd == 'addLast'){
									layer.msg('当前只能增加重点或难点或关键点或易混点，主题暂不能增加!', {icon:5,anim:6,time:2200});
									resCon = '';
									return false;
								}else if(currUeEditCon == ''){
									layer.msg('内容不能为空', {icon:5,anim:6,time:2000});
									resCon = '';
									return false;
								}else if(currUeEditCon != ''){
									resCon += currUeEditCon + '&zsd&';
								}
								if(resCon != ''){
									resFlag = true;
								}else{
									resFlag = false;
								}
							}else{
								if(isCanAdd == 'addLast'){
									layer.msg('当前只能增加重点或难点或关键点或易混点，主题暂不能增加!', {icon:5,anim:6,time:2200});
									resCon = '';
									return;
								}else if(isCanAdd == 'add'){//表示可以增加主题或其他几类
									layer.msg('请点击添加主题按钮进行添加', {icon:5,anim:6,time:2200});
								}
							}
						}else if(loreType == 'jtsf'){//解题示范
							$('.'+loreType + '_Inp').each(function(i){
								nowNum = $(this).attr('currNum');
							});
							currUeEditCon = UE.getEditor('con_' + loreType + '_' + nowNum).getContent();
							currUeEditAns = UE.getEditor('conAns_' + loreType + '_' + nowNum).getContent();
							currUeEditAnaly = UE.getEditor('conAnaly_' + loreType + '_' + nowNum).getContent();
							if(currUeEditCon == ''){
								layer.msg('题干不能为空', {icon:5,anim:6,time:2000});
								resCon = '';
								resFlag = false;
								return false;
							}else if(currUeEditAns == ''){
								layer.msg('答案不能为空', {icon:5,anim:6,time:2000});
								resAns = '';
								resFlag = false;
								return false;
							}else if(currUeEditAnaly == ''){
								layer.msg('解析不能为空', {icon:5,anim:6,time:2000});
								resAnaly = '';
								resFlag = false;
								return false;
							}else if(currUeEditCon != '' && currUeEditAnaly != '' && currUeEditAnaly != ''){
								resCon = currUeEditCon;
								resAns = currUeEditAns;
								resAnaly = currUeEditAnaly;
								resFlag = true;
							}
						}else if(loreType == 'ggxl' || loreType == 'zdxzd' || loreType == 'zczd'){
							var tiganTypeInpVal = $('#tiganTypeInp').val(),
								tiganType1InpVal = $('#tiganType1Inp').val(),
								answSelTypeInpVal = $('#answSelTypeInp').val(),
								ans_singleInpVal = $('#ans_singleInp').val(),//答案选项
								maxSelNum = $('#maxSelInpNum').val(),
								spaceNum = $('#spaceNumInp').val(),
								inpAnsSelVal='',ansSelRes = '',isHasSameAns=[],
								queTiganFlag=true,
								queSubFlag=true,ansSelFlag=true,ansFlag=true;
								
							$('.'+loreType + '_Inp').each(function(i){
								nowNum = $(this).attr('currNum');
							});
							currUeEditCon = UE.getEditor('con_' + loreType + '_' + nowNum).getContent();
							currUeEditAnaly = UE.getEditor('conAnaly_' + loreType + '_' + nowNum).getContent();
							if(tiganTypeInpVal == 0){
								layer.msg('请选择题干类型', {icon:5,anim:6,time:2000});
								queTiganFlag = false;
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
							if(queTiganFlag && queSubFlag && ansSelFlag && ansFlag){
								resFlag = true;
							}
						}else if(loreType == 'zsjj'){
							currUeEditCon = UE.getEditor('con_' + loreType + '_' + nowNum).getContent();
							var noUpDoneLen = $('.noUpDone').length,hasUpDoneLen = $('.hasUpDone').length;
							if(isCanAdd == 'noAdd' && globalOpts == 'add'){
								layer.msg('当前已存知识讲解暂不能增加，如需修改请在编辑中删除后再增加', {icon:5,anim:6,time:2200});
								resCon = '';
								resFlag = false;
								return;
							}else if(currUeEditCon == ''){
								layer.msg('题干不能为空', {icon:5,anim:6,time:2000});
								resFlag = false;
								return;
							}else if(zsjjInpVal == ''){
								layer.msg('请上传知识讲解视频', {icon:5,anim:6,time:2000});
								resFlag = false;
								return;
							}else if((hasUpDoneLen+noUpDoneLen) > 1){
								layer.msg('最多只能上传一个视频', {icon:5,anim:6,time:2000});
								resFlag = false;
								return;
							}else if(currUeEditCon != '' && zsjjInpVal != ''){
								resCon = currUeEditCon;
								resFlag = true;
							}
						}
						if(resFlag){//ajax
							var lqsIdStr_up='',tmpLqsIdUpObj = null;;
							if(loreType == 'zsqd' || loreType == 'zd' || loreType == 'nd' || loreType == 'gjd' || loreType == 'yhd'){
								resTit = resTit.substring(0,resTit.lastIndexOf('&zsd&'));
								resCon = resCon.substring(0,resCon.lastIndexOf('&zsd&'));
								if(loreType == 'zsqd'){
									if(globalOpts == 'add'){
										field = {loreId:loreBigId,loreType:loreTypeZHN,queTitle:resTit,queSub:resCon};
									}else{
										tmpLqsIdUpObj = blMet.getLqsId('zsqd');
										if(tmpLqsIdUpObj.lqsIdArr.length > 0){
											lqsIdStr_up = tmpLqsIdUpObj.lqsIdArr.length == 1 ? tmpLqsIdUpObj.lqsIdArr[0] : tmpLqsIdUpObj.lqsIdArr.join('&zsd&');//当一个都没删除当前存在页面的id就是数组的所有项
										}else{
											lqsIdStr_up='';
										}
										field = {lqId:lqBigId,lqsIdStr_up:lqsIdStr_up,/*lqsIdStr_del:lqsIdStr_del,*/lqsTitleStr:resTit,lqsConStr:resCon};
									}
								}else if(loreType == 'zd'){
									if(globalOpts == 'add'){
										field = {loreId:loreBigId,loreType:loreTypeZHN,titleZd:resTit,contentZd:resCon};
									}else{
										tmpLqsIdUpObj = blMet.getLqsId('zd');
										if(tmpLqsIdUpObj.lqsIdArr.length > 0){
											lqsIdStr_up = tmpLqsIdUpObj.lqsIdArr.length == 1 ? tmpLqsIdUpObj.lqsIdArr[0] : tmpLqsIdUpObj.lqsIdArr.join('&zsd&');//当一个都没删除当前存在页面的id就是数组的所有项
										}else{
											lqsIdStr_up='';
										}
										if(tmpLqsIdUpObj.zeroFlag){//表示编辑的时候新增加了
											field = {lqId:lqBigId,lqsType:smLoreTypeZHN,lqsIdStr_up:lqsIdStr_up,/*lqsIdStr_del:lqsIdStr_del,*/lqsTitleStr:resTit,lqsConStr:resCon};
										}else{
											field = {lqId:lqBigId,lqsIdStr_up:lqsIdStr_up,/*lqsIdStr_del:lqsIdStr_del,*/lqsTitleStr:resTit,lqsConStr:resCon};
										}
									}
								}else if(loreType == 'nd'){
									if(globalOpts == 'add'){
										field = {loreId:loreBigId,loreType:loreTypeZHN,titleNd:resTit,contentNd:resCon};
									}else{
										tmpLqsIdUpObj = blMet.getLqsId('nd');
										if(tmpLqsIdUpObj.lqsIdArr.length > 0){
											lqsIdStr_up = tmpLqsIdUpObj.lqsIdArr.length == 1 ? tmpLqsIdUpObj.lqsIdArr[0] : tmpLqsIdUpObj.lqsIdArr.join('&zsd&');//当一个都没删除当前存在页面的id就是数组的所有项
										}else{
											lqsIdStr_up='';
										}
										if(tmpLqsIdUpObj.zeroFlag){
											field = {lqId:lqBigId,lqsType:smLoreTypeZHN,lqsIdStr_up:lqsIdStr_up,/*lqsIdStr_del:lqsIdStr_del,*/lqsTitleStr:resTit,lqsConStr:resCon};
										}else{
											field = {lqId:lqBigId,lqsIdStr_up:lqsIdStr_up,/*lqsIdStr_del:lqsIdStr_del,*/lqsTitleStr:resTit,lqsConStr:resCon};
										}
										
									}
								}else if(loreType == 'gjd'){
									if(globalOpts == 'add'){
										field = {loreId:loreBigId,loreType:loreTypeZHN,titleGjd:resTit,contentGjd:resCon};
									}else{
										tmpLqsIdUpObj = blMet.getLqsId('gjd');
										if(tmpLqsIdUpObj.lqsIdArr.length > 0){
											lqsIdStr_up = tmpLqsIdUpObj.lqsIdArr.length == 1 ? tmpLqsIdUpObj.lqsIdArr[0] : tmpLqsIdUpObj.lqsIdArr.join('&zsd&');//当一个都没删除当前存在页面的id就是数组的所有项
										}else{
											lqsIdStr_up='';
										}
										if(tmpLqsIdUpObj.zeroFlag){
											field = {lqId:lqBigId,lqsType:smLoreTypeZHN,lqsIdStr_up:lqsIdStr_up,/*lqsIdStr_del:lqsIdStr_del,*/lqsTitleStr:resTit,lqsConStr:resCon};
										}else{
											field = {lqId:lqBigId,lqsIdStr_up:lqsIdStr_up,/*lqsIdStr_del:lqsIdStr_del,*/lqsTitleStr:resTit,lqsConStr:resCon};
										}
									}
								}else if(loreType == 'yhd'){
									if(globalOpts == 'add'){
										field = {loreId:loreBigId,loreType:loreTypeZHN,titleYhd:resTit,contentYhd:resCon};
									}else{
										tmpLqsIdUpObj = blMet.getLqsId('yhd');
										if(tmpLqsIdUpObj.lqsIdArr.length > 0){
											lqsIdStr_up = tmpLqsIdUpObj.lqsIdArr.length == 1 ? tmpLqsIdUpObj.lqsIdArr[0] : tmpLqsIdUpObj.lqsIdArr.join('&zsd&');//当一个都没删除当前存在页面的id就是数组的所有项
										}else{
											lqsIdStr_up='';
										}
										if(tmpLqsIdUpObj.zeroFlag){
											field = {lqId:lqBigId,lqsType:smLoreTypeZHN,lqsIdStr_up:lqsIdStr_up,/*lqsIdStr_del:lqsIdStr_del,*/lqsTitleStr:resTit,lqsConStr:resCon};
										}else{
											field = {lqId:lqBigId,lqsIdStr_up:lqsIdStr_up,/*lqsIdStr_del:lqsIdStr_del,*/lqsTitleStr:resTit,lqsConStr:resCon};
										}
									}
								}
							}else if(loreType == 'zhuti'){
								resCon = resCon.substring(0,resCon.lastIndexOf('&zsd&'));
								if(globalOpts == 'add'){
									field = {loreId:loreBigId,loreType:loreTypeZHN,contentZt:resCon};
								}else{
									tmpLqsIdUpObj = blMet.getLqsId('zhuti');
									if(tmpLqsIdUpObj.lqsIdArr.length > 0){
										lqsIdStr_up = tmpLqsIdUpObj.lqsIdArr.length == 1 ? tmpLqsIdUpObj.lqsIdArr[0] : tmpLqsIdUpObj.lqsIdArr.join('&zsd&');//当一个都没删除当前存在页面的id就是数组的所有项
									}else{
										lqsIdStr_up='';
									}
									if(tmpLqsIdUpObj.zeroFlag){
										field = {lqId:lqBigId,lqsType:smLoreTypeZHN,lqsIdStr_up:lqsIdStr_up,lqsConStr:resCon};
									}else{
										field = {lqId:lqBigId,lqsIdStr_up:lqsIdStr_up,lqsConStr:resCon};
									}
								}
								
							}else if(loreType == 'jtsf'){
								if(globalOpts == 'add'){
									field = {loreId:loreBigId,loreType:loreTypeZHN,queSub:resCon,queAnswer:resAns,queResolution:resAnaly};
								}else{
									field = {lqId:lqBigId,queSub:resCon,queAnswer:resAns,queResolution:resAnaly};
								}
							}else if(loreType == 'ggxl' || loreType == 'zdxzd' || loreType == 'zczd'){
								var queTipsId = $('#tipsInp_' + loreType).val() == '' ? 0 : $('#tipsInp_' + loreType).val()	,
									lexId  = $('#'+loreType+'_lexId').val();
								if(tiganTypeInpVal == '单选题' || tiganTypeInpVal == '多选题' || tiganTypeInpVal == '填空选择题'){
									var answerA = answSelTypeInpVal == 1 ? $('#answSelInpTxt1').val() : $('#answerSelect1').attr('currSrc'),
											answerB = answSelTypeInpVal == 1 ? $('#answSelInpTxt2').val() : $('#answerSelect2').attr('currSrc'),
											answerC = answSelTypeInpVal == 1 ? $('#answSelInpTxt3').val() : $('#answerSelect3').attr('currSrc'),
											answerD = answSelTypeInpVal == 1 ? $('#answSelInpTxt4').val() : $('#answerSelect4').attr('currSrc'),
											answerE = answSelTypeInpVal == 1 ? $('#answSelInpTxt5').val() : $('#answerSelect5').attr('currSrc'),
											answerF = answSelTypeInpVal == 1 ? $('#answSelInpTxt6').val() : $('#answerSelect6').attr('currSrc'),
											fieldCom = {queType:tiganTypeInpVal,queType2:tiganType1InpVal,queSub:currUeEditCon,queTipId:queTipsId,queResolution:currUeEditAnaly,
													lexId:lexId,answerA:answerA,answerB:answerB,answerC:answerC,answerD:answerD,answerE:answerE,answerF:answerF};
								
								}else if(tiganTypeInpVal == '判断题'){
									var fieldCom = {queType:tiganTypeInpVal,queType2:tiganType1InpVal,queSub:currUeEditCon,queTipId:queTipsId,queResolution:currUeEditAnaly,
											lexId:0,answerA:$('#ansSelJudgeInp1').val(),answerB:$('#ansSelJudgeInp2').val()};
								}else if(tiganTypeInpVal == '填空题' || tiganTypeInpVal == '问答题'){
									var fieldCom = {queType:tiganTypeInpVal,queType2:tiganType1InpVal,queSub:currUeEditCon,queTipId:queTipsId,queResolution:currUeEditAnaly,
											lexId:0};
								}
								if(tiganTypeInpVal == '单选题'){
									if(globalOpts == 'add'){
										field = {loreId:loreBigId,loreType:loreTypeZHN,queAnswer:ans_singleInpVal};
									}else{
										field = {lqId:lqBigId,queAnswer:ans_singleInpVal};
									}
								}else if(tiganTypeInpVal == '多选题'){
									var multiAnsStr = multiAnsArr.join(',');
									if(globalOpts == 'add'){
										field = {loreId:loreBigId,loreType:loreTypeZHN,queAnswer:multiAnsStr};
									}else{
										field = {lqId:lqBigId,queAnswer:multiAnsStr};
									}
								}else if(tiganTypeInpVal == '填空选择题'){
									var tmpResAnsTk = result_answer_text.substring(0,result_answer_text.lastIndexOf(','));
									if(globalOpts == 'add'){
										field = {loreId:loreBigId,loreType:loreTypeZHN,queAnswer:tmpResAnsTk};
									}else{
										field = {lqId:lqBigId,queAnswer:tmpResAnsTk};										
									}
								}else if(tiganTypeInpVal == '判断题'){
									var judgeInpVal = $('#judgeInp').val();
									if(globalOpts == 'add'){
										field = {loreId:loreBigId,loreType:loreTypeZHN,queAnswer:judgeInpVal};
									}else{
										field = {lqId:lqBigId,queAnswer:judgeInpVal};	
									}
								}else if(tiganTypeInpVal == '填空题'){
									var tkVal = $('#tkInp_' + loreType).val();
									if(globalOpts == 'add'){
										field = {loreId:loreBigId,loreType:loreTypeZHN,queAnswer:tkVal};
									}else{
										field = {lqId:lqBigId,queAnswer:tkVal};
									}
								}else if(tiganTypeInpVal == '问答题'){
									if(globalOpts == 'add'){
										field = {loreId:loreBigId,loreType:loreTypeZHN,queAnswer:currUeEditAns};
									}else{
										field = {lqId:lqBigId,queAnswer:currUeEditAns};
									}
								}
								
								//进行对象组合
								field = Object.assign(field,fieldCom);
							}else if(loreType == 'zsjj'){
								if(globalOpts == 'add'){
									field = {loreId:loreBigId,loreType:loreTypeZHN,queSub:resCon,queAnswer:zsjjInpVal};
								}else{
									field = {lqId:lqBigId,queSub:resCon,queAnswer:zsjjInpVal};
								}
								
							}
							if(globalOpts == 'add'){
								var url = '/lore.do?action=addLoreQuesion';
							}else if(globalOpts == 'edit'){
								var url = '/lore.do?action=updateLoreQuesionDetail';
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
												_this.bindEvent();
						        			}
		   				        		});
						        	}else if(json['result'] == 'noInfo'){
						        		layer.msg('请稍后重试',{icon:5,anim:6,time:1500});
						        	}else if(json['result'] == 'noAddZt' && loreTypeZHN == '点拨指导'){
						        		layer.msg('系统已存在主题或已存在重点、难点、关键点、易混点，暂不能增加主题',{icon:5,anim:6,time:2200});
						        	}else if(json['result'] == 'noAddPoint' && loreTypeZHN == '点拨指导'){
						        		layer.msg('系统已存在主题，暂不能重点、难点、关键点、易混点',{icon:5,anim:6,time:2000});
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
						relate.comBackFun();
						$('#currLoc').html('');
					});
					$('.backBtn_tiku').on('click',function(){
						//lore.getQuesList(loreBigId);
						relate.comBackFun();
						$('.loreQuesList').show();
						$('#currLoc').html('知识点[<span style="color:#F47837;">'+ loreNameBig +'</span>]&gt;题库列表<a class="addEditBack" href="javascript:void(0)">返回知识点列表&gt;</a>');
						_this.bindEvent();
					});
				},
				addLoreQuesInit : function(){
					var topDOM = loreDOM.crealoreTopDOM(),
						loreCon = loreDOM.creaLoreConDOM(loreType);
					$('.queType').show();
					$('.queType').html(topDOM);
					$('.quesAddEditBox').show();
					$('#zsdName').html(loreNameBig);
					if(loreType == 'zsqd'){
						$('.addLoreCon').show();
						$('.addLoreCon span').html('知识清单');
					}
					if(globalOpts == 'add'){
						$('#'+loreType + 'Wrap').show().html(loreCon);
						loreDOM.switchTxt(loreType);
						UE.delEditor('con_'+ loreType +'_'+currNum);
						loreDOM.initUeditor('con_'+ loreType +'_'+currNum);
						//this.subLore();
					}
					form.render();
					
				},
				//编辑回显公共创建editor
				createComLoreDom : function(){
					var loreCon = loreDOM.creaLoreConDOM(loreType);
					$('#'+loreType + 'Wrap').show().append(loreCon);
					UE.delEditor('con_'+ loreType +'_'+currNum);
					loreDOM.initUeditor('con_'+ loreType +'_'+currNum);
					if(loreType == 'jtsf'){
						//解题示范增加答案和解析
						UE.delEditor('conAns_'+ loreType +'_'+currNum);
						loreDOM.initUeditor('conAns_'+ loreType +'_'+currNum);
						
						UE.delEditor('conAnaly_'+ loreType +'_' +currNum);
						loreDOM.initUeditor('conAnaly_'+ loreType + '_'+currNum);
					}else if(loreType == 'ggxl' || loreType == 'zdxzd' || loreType == 'zczd'){
						//增加解析
						UE.delEditor('conAnaly_'+ loreType +'_' +currNum);
						loreDOM.initUeditor('conAnaly_'+ loreType + '_'+currNum);
					}
					
				},
				//回显题库列表每个对应的详情 （编辑)
				renderLoreTypeInfo : function(listInfo){
					isAddClickFlag = true;//编辑时打开删除按钮
					var tmpQueTipId = '';
					if(loreType == 'ggxl' || loreType == 'zdxzd' || loreType == 'zczd'){
						var tiganTypeDOM = blDOM.createTiganType(), 
							maxSelBox = blDOM.creaMaxSel(),
							spaceSelBox = blDOM.creaMaxSpace(),
							ansType = blDOM.createAnsType(),//问题类型
							selAnsTxt = blDOM.createSelAnsTxt(),
							selAnsImg = blDOM.createSelAnsImg(),
							ansSingle = blDOM.createAnsSingle(),
							ansMulti = blDOM.createAnsMulti(),
							wendaStr = blDOM.wendaTypeDOM(loreType),
							judgeStr = blDOM.judgeQueType(),
							tkSelStr = blDOM.createTkSelDOM(),
							tkTypeStr = blDOM.createTkTypeDOM(loreType),
							lexStrDOM = blDOM.createLexDOM(loreType);
						
						//增加关联词条
						$('.queType').append(tiganTypeDOM + lexStrDOM);
						$('.maxChoice').hide().html(maxSelBox);
						$('.spaceBox').hide().html(spaceSelBox);
					}
					if(listInfo.length > 0){
						currNumLen = listInfo.length;
						console.log(listInfo)
						for(var i=0;i<listInfo.length;i++){
							currNum = i;
							loreTypeZHN = listInfo[i].lqType;
							if(listInfo[i].lqType == '主题' || listInfo[i].lqType == '重点' || listInfo[i].lqType == '难点' || listInfo[i].lqType == '关键点' || listInfo[i].lqType == '易混点'){
								$('#basicTypeTxt').html('点拨指导');
								$('#basicTypeInp').val('点拨指导');
								//loreTypeZHN = '点拨指导';
								$('.guideWrap').show();
							
								if(listInfo[i].lqType == '主题'){
									//存在主题的话就不存在 其他
									$('#zd').html('');
									$('#nd').html('');
									$('#gjd').html('');
									$('#yhd').html('');
									loreTypeZHN = '点拨指导';
									var loreCon = loreDOM.creaLoreConDOM('zhuti');
									$('#zhuti').append(loreCon);
									$('#zhutiInp_' + currNum).val(loreNameBig);
									//$('#zhutiInp_' + currNum).val(listInfo[i].lqsTitle);
									$('#zhuti_delBtn_' + currNum).attr('lqsId',listInfo[i].lqsId);
									UE.delEditor('con_zhuti_'+currNum);
									loreDOM.initUeditor('con_zhuti_'+currNum);
									loreDOM.initUeditorContent('con_zhuti_' + currNum,listInfo[i].lqsCon);
									
								}else{
									$('#zhuti').html('<p>该知识点已存在重点、难点、关键点、易混点，暂时不能增加主题内容!如果确定要增加主题内容，请清除重点、难点、关键点、易混点后再到本页面添加主题内容!</p>');
									if(listInfo[i].lqType == '重点'){
										var loreCon = loreDOM.creaLoreConDOM('zd');
										$('#zd').append(loreCon);
										$('#zdInp_' + currNum).val(listInfo[i].lqsTitle);
										UE.delEditor('con_zd_'+currNum);
										loreDOM.initUeditor('con_zd_'+currNum);
										loreDOM.initUeditorContent('con_zd_' + currNum,listInfo[i].lqsCon);
										$('#zd_delBtn_' + currNum).attr('lqsId',listInfo[i].lqsId);
										loreTypeZHN = '点拨指导';
										//allLqsIdArr.push(listInfo[i].lqsId);//存储所有的lqsId
										
									}else if(listInfo[i].lqType == '难点'){
										var loreCon = loreDOM.creaLoreConDOM('nd');
										$('#nd').append(loreCon);
										$('#ndInp_' + currNum).val(listInfo[i].lqsTitle);
										UE.delEditor('con_nd_'+currNum);
										loreDOM.initUeditor('con_nd_'+currNum);
										loreDOM.initUeditorContent('con_nd_' + currNum,listInfo[i].lqsCon);
										$('#nd_delBtn_' + currNum).attr('lqsId',listInfo[i].lqsId);
										loreTypeZHN = '点拨指导';
									}else if(listInfo[i].lqType == '关键点'){
										var loreCon = loreDOM.creaLoreConDOM('gjd');
										$('#gjd').append(loreCon);
										$('#gjdInp_' + currNum).val(listInfo[i].lqsTitle);
										UE.delEditor('con_gjd_'+currNum);
										loreDOM.initUeditor('con_gjd_'+currNum);
										loreDOM.initUeditorContent('con_gjd_' + currNum,listInfo[i].lqsCon);
										$('#gjd_delBtn_' + currNum).attr('lqsId',listInfo[i].lqsId);
										loreTypeZHN = '点拨指导';
									}else if(listInfo[i].lqType == '易混点'){
										var loreCon = loreDOM.creaLoreConDOM('yhd');
										$('#yhd').append(loreCon);
										$('#yhdInp_' + currNum).val(listInfo[i].lqsTitle);
										UE.delEditor('con_yhd_'+currNum);
										loreDOM.initUeditor('con_yhd_'+currNum);
										loreDOM.initUeditorContent('con_yhd_' + currNum,listInfo[i].lqsCon);
										$('#yhd_delBtn_' + currNum).attr('lqsId',listInfo[i].lqsId);
										loreTypeZHN = '点拨指导';
									}
								}
							}else{
								smLoreTypeZHN = listInfo[i].lqType;
								$('#basicTypeInp').val(listInfo[i].lqType);
								$('#basicTypeTxt').html(listInfo[i].lqType);
								this.createComLoreDom();//创建DOM结构
								if(listInfo[i].lqType == '知识清单'){
									$('#zsqdInp_' + currNum).val(listInfo[i].lqsTitle);
									$('#zsqd_delBtn_' + currNum).attr('lqsId',listInfo[i].lqsId);
									//allLqsIdArr.push(listInfo[i].lqsId);//存储所有的lqsId
									loreDOM.initUeditorContent('con_zsqd_' + currNum,listInfo[i].lqsCon);
								}else if(listInfo[i].lqType == '解题示范'){
									//this.createComLoreDom();
									$('#jtsfInp_' + currNum).val(listInfo[i].lqTitle).attr('disabled',true);
									loreDOM.initUeditorContent('con_jtsf_' + currNum,listInfo[i].lqSub);//题干
									loreDOM.initUeditorContent('conAns_jtsf_' + currNum,listInfo[i].lqAnswer);//答案
									loreDOM.initUeditorContent('conAnaly_jtsf_' + currNum,listInfo[i].lqResolution);//解析
								}else if(listInfo[i].lqType == '巩固训练' || listInfo[i].lqType == '针对性诊断' || listInfo[i].lqType == '再次诊断'){
									var tiganType = blDOM.tiganTypeTxt(listInfo[i].queType);
									$('#tiganTypeInp').val(listInfo[i].queType);
									lore.data.originTypeTxt = listInfo[i].queType;//用于填空题切换其它题型时做个原来题型的存储
									$('#tiganType1Inp').val(listInfo[i].queType2);//了解 理解 应用 综合匹配
									$('#tiganTypeTxt').html(listInfo[i].queType);
									//渲染关联此条内容
									$('#'+loreType + '_lexId').val(listInfo[i].lexId);
									$('#'+loreType + '_lexInp').val(listInfo[i].lexTitle);
									//匹配 了解 理解 应用 综合匹配
									$('#tiganType1Sel').val(listInfo[i].queType2);
									//匹配最大选项（单选题 多选题 填空选择题）
									if(listInfo[i].queType == '单选题' || listInfo[i].queType == '多选题' || listInfo[i].queType == '填空选择题' || listInfo[i].queType == '判断题'){
										$('.maxChoice').show();
										$('#ansSelWrap_' + loreType).show();
										$('#maxSelInpNum').val(listInfo[i].queOptNum);//初始化最大选项
										
										var answer1 = listInfo[i].anserA,
											answer2 = listInfo[i].anserB,
											answer3 = listInfo[i].anserC,
											answer4 = listInfo[i].anserD,
											answer5 = listInfo[i].anserE,
											answer6 = listInfo[i].anserF;
										
										realAnswer = listInfo[i].lqAnswer;
										if(listInfo[i].queType == '单选题'){
											$('#maxChoiceNumSel').val(listInfo[i].queOptNum);
											answerNum = listInfo[i].queOptNum; //将当前选择的最大选项赋给answerNum
											
											result_answer = listInfo[i].lqAnswer + ",";
											$('#answerSelectDiv_' + loreType).show().html(ansType + selAnsTxt + selAnsImg + ansSingle);
											$('#ans_singleInp').val(listInfo[i].lqAnswer);
											blMet.initShowInpByMaxOptNum(listInfo[i].queOptNum,'answerBox_singel');
						        			blMet.inputBlur();
											form.render();
										}else if(listInfo[i].queType == '多选题'){
											$('#maxChoiceNumSel').val(listInfo[i].queOptNum);
											answerNum = listInfo[i].queOptNum; //将当前选择的最大选项赋给answerNum
											
											$('#answerSelectDiv_' + loreType).show().html(ansType + selAnsTxt + selAnsImg + ansMulti);
											blMet.initShowInpByMaxOptNum(listInfo[i].queOptNum,'answerBox_multi');
											blMet.inputBlur();
											//multiAnsArr = listInfo[i].lqAnswer.split(',');
											form.render();
										}else if(listInfo[i].queType == '填空选择题'){
											$('#maxChoiceNumSel').val(listInfo[i].queOptNum);
											answerNum = listInfo[i].queOptNum; //将当前选择的最大选项赋给answerNum
											$('.spaceBox').show();
											$('#spaceNumInp').val(listInfo[i].answerNum);//初始化填空数量value
											//匹配填空数量
											$('#spaceNumSel').val(listInfo[i].answerNum);
											$('#answerSelectDiv_' + loreType).show().html(ansType + selAnsTxt + selAnsImg + tkSelStr);
											blMet.initShowInpByMaxOptNum(listInfo[i].queOptNum,'ansBox_multiTk');
											//文字类型的 调用input的blur事件
						        			blMet.inputBlur();
											form.render();
										}else if(listInfo[i].queType == '判断题'){
											$('.maxChoice').html('');
											$('.spaceBox').html('');
											$('#answerSelectDiv_' + loreType).show().html(judgeStr);
											$('#judgeInp').val(listInfo[i].lqAnswer);
										}
										lorePrac.initAnswerOption(lorePrac.findAnserType(answer1),answer1,answer2,answer3,answer4,answer5,answer6);
										
									}else if(listInfo[i].queType == '问答题'){
										$('#ansSelWrap_' + loreType).show();
										$('.switchTgGanTypeBox').show();//切换题型显示
										lore.switchTiganFun();//调用切换题型方法
										lore.resetOriginTiganType();//还原原题型方法
										$('#answerSelectDiv_' + loreType).hide().html('');
										$('#nowTxt_'+loreType).html('答案：');
										$('#wendaTypeWrap_'+loreType).show().html(wendaStr);
										//增加答案
										UE.delEditor('wenda_'+loreType +'_' + currNum);
										loreDOM.initUeditor('wenda_'+loreType +'_' + currNum);
										loreDOM.initUeditorContent('wenda_'+ loreType +'_' + currNum,listInfo[i].lqAnswer);//解析
									}else if(listInfo[i].queType == '填空题'){
										$('.switchTgGanTypeBox').show();//切换题型显示
										$('#ansSelWrap_' + loreType).show();
										lore.switchTiganFun();//调用切换题型方法
										lore.resetOriginTiganType();//还原原题型方法
										$('.maxChoice').html('');
										$('#wendaTypeWrap_'+loreType).hide().html('');
										$('#answerSelectDiv_' + loreType).hide().html('');
										$('.spaceBox').show();
										$('#spaceNumInp').val(listInfo[i].answerNum);//初始化填空数量value
										//匹配填空数量
										$('#spaceNumSel').val(listInfo[i].answerNum);
										$('#nowTxt_'+loreType).html('答案：');
										$('#tkTypeWrap_' + loreType).show().html(tkTypeStr);
										$('#tkInp_' + loreType).val(listInfo[i].lqAnswer);
										blMet.data.tkOriginAnsTxt = listInfo[i].lqAnswer;
									}
									$('#'+loreType + 'Inp_' + currNum).val(listInfo[i].lqTitle).attr('disabled',true);
									loreDOM.initUeditorContent('con_'+ loreType +'_' + currNum,listInfo[i].lqSub);//题干
									loreDOM.initUeditorContent('conAnaly_'+ loreType +'_' + currNum,listInfo[i].lqResolution);//解析
									if(listInfo[i].queTipId != 0){
										tmpQueTipId = listInfo[i].queTipId;
									}
								}else if(listInfo[i].lqType == '知识讲解'){
									loreDOM.initUeditorContent('con_zsjj_' + currNum,listInfo[i].lqSub);
									$('#zsjjInp').val(listInfo[i].lqAnswer);
									var videoBox = '<div class="videoBox"><i title="删除" class="iconfont layui-extend-shanchu delVideoBtn"></i><img alt="'+ listInfo[i].lqAnswer +'" src="Module/loreManager/images/video.jpg"/></div>';	
									$('#zsjjWrap').append(videoBox);
									loreDOM.delVideoFun();
								}
							}
							
						}
						//loreDOM.delEditorFun();
						if(loreType == 'zsqd'){
							currNum = listInfo.length;
						}else if(loreType == 'zhuti' || loreType == 'zd' || loreType == 'nd' || loreType == 'gjd' || loreType== 'yhd'){
							isCanAdd = loreDOM.isHasAddAbility(loreBigId, loreTypeZHN);//初始化当前点拨指导下的增加权限
						}else if(loreType == 'ggxl' || loreType == 'zdxzd' || loreType == 'zczd'){
							blMet.getCurrLoreTips(loreBigId,false);//获取提示列表
							//添加编辑词条
							blMet.addEditLex();
							$('#selTipsSel_' + loreType).val(tmpQueTipId);
							$('#tipsInp_ggxl').val(tmpQueTipId);
							for(var i=0;i<listInfo[0].tipsList.length;i++){
								if(listInfo[0].tipsList[i].selStatus){
									$('#tipsCon_' + loreType).html(listInfo[0].tipsList[i].lqsContent);
								}
							}
						}
					}else{
						if(loreType == 'zhuti' || loreType == 'zd' || loreType == 'nd' || loreType == 'gjd' || loreType== 'yhd'){
							//重新创建点拨指导
							$('#basicTypeTxt').html('点拨指导');
							$('#basicTypeInp').val('点拨指导');
							$('.guideWrap').show();
							loreDOM.initDbzdDOM(currNum);
							isCanAdd = loreDOM.isHasAddAbility(loreBigId, loreTypeZHN);//初始化当前点拨指导下的增加权限
							$('#zhutiInp_' + currNum).val(loreNameBig);
						}
					}
					form.render();
				}
			};
			//<a href="javascript:void(0)">1-5各数的认识</a>&gt;题库列表&gt;增加题型
			//每个知识点下对应的题库列表的编辑和浏览
			table.on('tool(loreQuesTable)',function(obj){
				if(obj.event == 'editFun'){//编辑
					var lqId = $(this).attr('lqId'),lqType = $(this).attr('lqType');
					lqBigId = $(this).attr('lqId');
					loreTypeZHN = lqType;
					if(lqType == '知识清单'){
						loreType = 'zsqd';
					}else if(lqType == '点拨指导'){
						loreType = 'zhuti';
					}else if(lqType == '解题示范'){
						loreType = 'jtsf';
					}else if(lqType == '巩固训练'){
						loreType = 'ggxl';
					}else if(lqType == '针对性诊断'){
						loreType = 'zdxzd';
					}else if(lqType == '再次诊断'){
						loreType = 'zczd';
					}else if(lqType == '知识讲解'){
						loreType = 'zsjj';
					}			
					layer.load('1');
					$.ajax({
						type:'post',
				        dataType:'json',
				        data:{lqId:lqId},
				        url:'/lore.do?action=getLoreQuesionDetail',
				        success:function (json){
				        	layer.closeAll('loading');	
				        	if(json.msg == 'success'){
				        		realAnswer = '';//每次修改知识点下不同题库 清空全局变量 realAnswer
				        		result_answer = '';
				        		result_answer_text = '';
				        		var listInfo = json.listIfo;
				        		$('.loreQuesList').hide();//隐藏知识点对应题库列表
				        		$('#currLoc').html('<em style="font-style:normal;float:right;">返回[<a class="backBtn_tiku" href="javascript:void(0)">'+ loreNameBig +'</a>]题库列表</em>');
								page.bindEvent();
								page.addLoreQuesInit();
				        		page.renderLoreTypeInfo(listInfo);
				        		//page.subLore();
				        	}else if(json.msg == 'noInfo'){
				        		layer.msg('暂无信息',{icon:5,anim:6,time:2000});
				        	}
				        	
				        }
					});
				}else if(obj.event == 'setIsInUseFun'){//设置当前知识点为有效/无效
					var lqId = $(this).attr('lqId'),
						lqTit = $(this).attr('lqTitle'),
						inUse = $(this).attr('inUse'),
						inUseTxt = $(this).attr('inUseTxt');
				
					layer.confirm('是否要将[<span style="color:#F47837;">' + lqTit + '</span>]设置为' + '[<span style="color:#F47837;">'+ inUseTxt +']</span>', {
						title:'提示',
					  	skin: 'layui-layer-molv',
					  	btn: ['确定','取消'] //按钮
					},function(index){
						layer.load('1');
						var field = {lqId:lqId,inUse:inUse};
						$.ajax({
							type:'post',
					        dataType:'json',
					        data:field,
					        url:'/lore.do?action=updateLQInUse',
					        success:function (json){
					        	layer.closeAll('loading');
					        	if(json.msg == 'success'){
					        		layer.msg('设置成功',{icon:1,time:1000},function(){
						        		layer.close(index);
						        		lore.getQuesList(loreId);
	   				        		});
					        	}else if(json.msg == 'error'){
					        		layer.msg('设置失败，请稍后重试',{icon:5,anim:6,time:2000});
					        	}
					        }
						});
						
					});
				
				}
			});
			//章节下下知识点的添加和编辑
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
					lore.getQuesList(loreId);
					page.bindEvent();
				}else if(obj.event == 'addFun'){//添加知识点
					var loreName = $(this).attr('loreName');
					loreNameBig = loreName;
					loreId = $(this).attr('loreId');
					loreBigId = $(this).attr('loreId');
					globalOpts = $(this).attr('opts');
					$('.loreList').hide();//章节下知识点列表隐藏
					$('#currLoc').html('知识点[<span style="color:#F47837;">'+ loreName +'</span>]&gt;增加题库 <a class="addEditBack" href="javascript:void(0)">返回知识点列表&gt;</a>');
					loreTypeZHN = '知识清单';
					realAnswer = '';//每次修改知识点下不同题库 清空全局变量 realAnswer
	        		result_answer = '';
	        		result_answer_text = '';
					page.bindEvent();
					page.addLoreQuesInit();
				}else if(obj.event == 'viewFun'){//浏览知识点
					var loreName = $(this).attr('loreName');
					loreId = $(this).attr('loreId');
					/*layer.open({
						title:'浏览知识点[<span style="color:#F47837;">'+ loreName +'</span>]',
						type: 2,
					  	area: ['700px', '500px'],
					  	fixed: true, //不固定
					  	maxmin: false,
					  	shadeClose :false,
					  	content: '/Module/loreManager/jsp/viewLore.html'
					});*/
					var viewLore = loreDOM.createViewLoreDOM();
					layer.open({
						title:'浏览知识点[<span style="color:#F47837;">'+ loreName +'</span>]',
						type: 1,
					  	area: ['750px', '500px'],
					  	fixed: true, //不固定
					  	maxmin: false,
					  	shadeClose :false,
					  	content: viewLore
					});
					loreDOM.loadLoreDetail();
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
				}else if(obj.event == 'relateLore'){//关联知识点
					var loreName = $(this).attr('loreName');
					loreBigId = $(this).attr('loreId');
					loreBigName = $(this).attr('loreName');
					layer.open({
						title:'',
						type: 2,
					  	area: ['1000px', '560px'],
					  	fixed: true, //不固定
					  	maxmin: false,
					  	shadeClose :false,
					  	closeBtn:0,
					  	content: '/Module/loreManager/jsp/loreRelate.html'
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
		//删除知识清单 重点 难点 关键点 易混点 主题
		function delEditor(obj){
			var currType = $(obj).attr('currType'),
				currDelNum = $(obj).attr('currDelNum');
			if(globalOpts == 'edit'){
				//delLqsIdArr
				var lqsId = $(obj).attr('lqsId');
				if(lqsId != 0){
					layer.confirm('确认要删除当前内容', {
					  title:'提示',
					  skin: 'layui-layer-molv',
					  btn: ['确定','取消'] //按钮
					},function(index){
						layer.load('1');
						$.ajax({
							type:'post',
					        dataType:'json',
					        data:{lqsId:lqsId},
					        url:'/lore.do?action=delLQSInfo',
					        success:function (json){
					        	layer.closeAll('loading');	
					        	$("#queCon_"+currType + '_' + currDelNum).remove();
					        	if(loreType != 'zsqd'){
					        		$.ajax({
					    				type:'post',
					    		        dataType:'json',
					    		        data:{loreId:loreBigId, loreType:loreTypeZHN},
					    		        async:false,
					    		        url:'/lore.do?action=checkAddInfo',
					    		        success:function (json){
					    		        	layer.closeAll('loading');	
					    		        	if(json['result'] == 'add'){//能增加(当是点拨指导时，主题和4个标签点能同时增加一种，当是知识讲解时，能增加记录)
					    		        		$('#guideTips').html('注：主题和重点、难点、关键点、易混点只能同时增加一种');
					    		        		isCanAdd = 'add';
					    		        		if(currType == 'zhuti'){
					    		        			$('.addZhuti').show();
					    		        		}else{
					    		        			$('#zhuti').html('');
					    		        		}
					    		        	}else if(json['result'] == 'editZt'){
					    		        		$('#guideTips').html('注：当前只能增加和编辑主题');
					    		        		isCanAdd = 'editZt';
					    		        	}else if(json['result'] == 'addLast'){//点拨指导返回，只能增加重点，难点，关键点，易混点
					    		        		$('#guideTips').html('注：当前只能增加重点或难点或关键点或易混点，主题暂不能增加');
					    		        		isCanAdd = 'addLast';
					    		        	}
					    		        }
					    			});
					        	}
					        	layer.close(index);
					        }
						});
					});
				}else{//lqsId为0是直接删除
					$("#queCon_"+currType + '_' + currDelNum).remove();
					if(currType == 'zhuti'){
	        			$('.addZhuti').show();
	        		}
				}
				
			}else{
				$("#queCon_"+currType + '_' + currDelNum).remove();
			}
		}
	</script>
</html>
