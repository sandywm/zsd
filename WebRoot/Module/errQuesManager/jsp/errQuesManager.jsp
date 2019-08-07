<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html> 
<html>
  <head>
    <title>错题管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache"> 
	<meta http-equiv="keywords" content="知识典管理系统,错题管理">
	<meta http-equiv="description" content="错题管理">
	<link href="/plugins/layui/css/layui.css" rel="stylesheet" type="text/css"/>
	<link href="/plugins/layui/css/modules/layui-icon-extend/iconfont.css" rel="stylesheet" type="text/css"/>
	<link href="/Module/commonJs/ueditor/themes/default/css/ueditor.min.css" type="text/css" rel="stylesheet" />
	<link href="/Module/loreManager/css/loreManager.css" rel="stylesheet" type="text/css"/>
	<link href="/plugins/pace/pace-theme-flash.min.css" rel="stylesheet" type="text/css"/>
	<script src="/plugins/pace/pace.min.js" type="text/javascript"></script>	
    </head>
	<body>
		<div class="layui-card-header loreHeader">
			<span style="float:left;">错题管理</span>
			<div class="layui-form" style="float:right;margin:3px 15px 0 35px;">
				<div class="itemDivs" style="width:140px;">
					<div class="layui-input-inline">
						<input type="hidden" id="errorType" value=""/>
						<select id="errorTypeSel" lay-filter="errorTypeSel">
						 	<option value="">请选择错误类型</option>
						 	<option value="noPicError">图片错误</option>
						 	<option value="contentError">内容错误</option>
						 	<option value="anserError">答案错误</option>
						 	<option value="otherError">其他错误</option>
						 </select> 
					</div>
				</div>
				<div class="itemDivs">
					<div class="layui-input-inline">
						 <input id="stDate" type="text" placeholder="请选择起始时间" readonly class="layui-input"/>
					</div>
					<span style="margin:0 5px;">至</span>
					<div class="layui-input-inline">
						 <input id="edDate" type="text" placeholder="请选择结束时间" readonly class="layui-input"/>
					</div>
				</div>
				<div class="itemDivs" style="width:140px;">
					<div class="layui-input-inline">
						<input type="hidden" id="upStaInp" value="0"/>
						<select id="upStaSel" lay-filter="upStaSel">
							<option value="">请选择修改状态</option>
							<option value="0">未修改</option>
							<option value="1">已修改</option>
						</select> 
					</div>
				</div>
				<div class="itemDivs" style="margin-right:15px;">
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
	  							<p class="tipsTxt_lore">请根据对应的条件对错题进行查看！</p>
	  							<table id="errListTab" class="layui-table" lay-filter="errListTab"></table>
	  						</div>
	  						<!-- 题库题型对应增加编辑结构 -->
	  						<div class="queType layui-form"></div>
	  						<div class="quesAddEditBox">
	  							<div id="ggxlWrap" class="comQuesBox"></div>
	  							<div id="subBtnBox">
	  								<a href="javascript:void(0)" class="layui-btn layui-btn-danger noGetBtn">不采纳</a>
	  								<a href="javascript:void(0)" class="layui-btn subLoreBtn">采纳</a>
	  								<a href="javascript:void(0)" class="layui-btn layui-btn-primary backTkListBtn" style="width:120px;">返回题库列表</a>
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
		var loreType = 'ggxl';//自助餐
		var addEditFlag = false,isAddClickFlag=true,switchFlag=false,
			globalOpts='',currPage='lorePage',lexContent='',
			currNum=0,loreNameBig='',lqBigId=0,lqeId=0,realAnswer='';//是否可以增加点拨指导和知识讲解;
		var result_answer = "";//ABCD
		var result_answer_text = "",answerNum=0,answerType='',cyStatus=0,
			multiAnsArr=[],queTipsArr=[];//复选框text
		var stDate = "${ requestScope.sDate }",edDate = "${ requestScope.eDate }";
		layui.config({
			base: '/plugins/frame/js/'
		}).use(['layer','table','laydate','form','comLoreDOM','buffetLoreMet','buffetLoreDOM','lorePractice'], function() {
			var layer = layui.layer,
				table = layui.table,
				form = layui.form,
				laydate = layui.laydate,
				loreDOM = layui.comLoreDOM,
				blDOM = layui.buffetLoreDOM,blMet = layui.buffetLoreMet,
				lorePrac = layui.lorePractice;
			
			laydate.render({'elem':'#stDate'});
			laydate.render({'elem':'#edDate'});
			//错题类型
			form.on('select(errorTypeSel)',function(data){
				var value = data.value;
				value == '' ? $('#errorType').val('') : $('#errorType').val(value);
			});
			//修改状态
			form.on('select(upStaSel)',function(data){
				var value = data.value;
				value == '' ? $('#upStaInp').val('-1') : $('#upStaInp').val(value);
			});
			
			var page = {
				init : function(){
					$('#stDate').val(stDate);
					$('#edDate').val(edDate);
					this.initImgEditor();
					this.queryErrList();
					this.subLore();
				},
				queryErrList : function(){
					var _this = this;
					$('#queryBtn').on('click',function(){
						$('.tipsTxt_lore').hide();
						_this.comBackFun();
						_this.loadErrList();
					});
				},
				//加载错误列表
				loadErrList : function(){
					var errType = $('#errorType').val(),
						stDate = $('#stDate').val(),
						edDate = $('#edDate').val(),
						upStaInp = $('#upStaInp').val();
					if(stDate == ''){
						layer.msg('请选择开始时间',{icon:5,anim:6,time:2000});
						return;
					}
					if(edDate == ''){
						layer.msg('请选择结束时间',{icon:5,anim:6,time:2000});
						return;
					}
					
					if(stDate != '' && edDate != ''){
						if(stDate > edDate){
							layer.msg('开始时间不能大于结束时间',{icon:5,anim:6,time:2000});
							return;
						}
					}
					var field = {opt:'admin',errorType:errType,sDate:stDate,eDate:edDate,checkStatus:upStaInp};
					layer.load('1');
					table.render({
						elem: '#errListTab',
						height: 'full-200',
						url :'/lqe.do?action=getPageLqeData',
						method : 'post',
						where:field,
						page : true,
						even : true,
						limit : 20,
						limits:[10,20,30,40],
						text: {none : '暂无相关数据'},
						cols : [[
							{field : '', title: '序号', type:'numbers',fixed: 'left' , align:'center'},
							{field : 'loreName', title: '知识点名称',width:'220', align:'center'},
							{field : 'lqTitle', title: '知识点标题',width:'220',align:'center'},
							{field : 'lqTitle', title: '知识点标题',width:'220',align:'center'},
							{field : 'content', title: '提交内容',width:'220',align:'center'},
							{field : 'errorType', title: '错题类型',width:'150',align:'center'},
							{field : 'addate', title: '提交日期',width:'220',align:'center'},
							{field : 'addUserName', title: '提交人员',width:'150',align:'center'},
							{field : 'updateStatuc', title: '修改状态',width:'150',align:'center',templet:function(d){
								var str = '';
								d.updateStatus == '未修改' ? str += '<span class="noFixTxt">未修改</span>' : str += '<span class="hasFixTxt">已修改</span>';
								return str;
							}},
							{field : 'updateUserName', title: '修改人员',width:'150',align:'center'},
							{field : 'updateDate', title: '修改日期',width:'220',align:'center'},
							{field : '', title: '操作',  fixed: 'right',width:'150', align:'center',templet : function(d){
								var strRes = '';
								strRes += '<a class="layui-btn layui-btn-xs layui-btn-primary" lay-event="viewSubDetFun"><i class="layui-icon layui-icon-search"></i>查看</a>';
								if(d.updateStatus == '未修改'){
									strRes += '<a class="layui-btn layui-btn-xs" opts="edit" lay-event="editFun"><i class="layui-icon layui-icon-edit"></i>修改</a>';	
								}
								return strRes;
							}},
						]],
						done : function(res, curr, count){
							console.log(res)
							layer.closeAll('loading');
						}
					});
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
					//采纳
					$('.subLoreBtn').on('click',function(){
						cyStatus = 1;
						_this.comSubFun();
					});
					//不采纳
					$('.noGetBtn').on('click',function(){
						cyStatus = 0;
						_this.comSubFun();
					});
					//返回题库列表
					$('.backTkListBtn').on('click',function(){
						_this.comBackFun();
					});
				},
				comSubFun : function(){
					var _this = this, nowNum = 0,currUeEditCon,currUeEditAnaly,currUeEditAns,
					result='',resTit='',resCon='',resAnaly='',resAns='',resFlag=false,field=null;
				
					var tiganTypeInpVal = $('#tiganTypeInp').val(),
						tiganType1InpVal = $('#tiganType1Inp').val(),
						answSelTypeInpVal = $('#answSelTypeInp').val(),
						ans_singleInpVal = $('#ans_singleInp').val(),//答案选项
						maxSelNum = $('#maxSelInpNum').val(),
						spaceNum = $('#spaceNumInp').val(),
						inpAnsSelVal='',ansSelRes = '',isHasSameAns=[],
						queTiganFlag=true,
						queSubFlag=true,ansSelFlag=true,ansFlag=true;
						
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
					if(resFlag){//ajax
						var lqsIdStr_up='',tmpLqsIdUpObj = null;;
						var queTipsId = $('#tipsInp_' + loreType).val() == '' ? 0 : $('#tipsInp_' + loreType).val()	,
								lexId  = $('#'+loreType+'_lexId').val();
						if(tiganTypeInpVal == '单选题' || tiganTypeInpVal == '多选题' || tiganTypeInpVal == '填空选择题'){
							var answerA = answSelTypeInpVal == 1 ? _this.convertEngToChi($('#answSelInpTxt1').val()) : $('#answerSelect1').attr('currSrc'),
								answerB = answSelTypeInpVal == 1 ? _this.convertEngToChi($('#answSelInpTxt2').val()) : $('#answerSelect2').attr('currSrc'),
								answerC = answSelTypeInpVal == 1 ? _this.convertEngToChi($('#answSelInpTxt3').val()) : $('#answerSelect3').attr('currSrc'),
								answerD = answSelTypeInpVal == 1 ? _this.convertEngToChi($('#answSelInpTxt4').val()) : $('#answerSelect4').attr('currSrc'),
								answerE = answSelTypeInpVal == 1 ? _this.convertEngToChi($('#answSelInpTxt5').val()) : $('#answerSelect5').attr('currSrc'),
								answerF = answSelTypeInpVal == 1 ? _this.convertEngToChi($('#answSelInpTxt6').val()) : $('#answerSelect6').attr('currSrc'),
								fieldCom = {lqId:lqBigId,lqeId:lqeId,cyStatus:cyStatus,queType:tiganTypeInpVal,queType2:tiganType1InpVal,queSub:currUeEditCon,queTipId:queTipsId,queResolution:currUeEditAnaly,
										lexId:lexId,answerA:answerA,answerB:answerB,answerC:answerC,answerD:answerD,answerE:answerE,answerF:answerF};
						}else if(tiganTypeInpVal == '判断题'){
							var fieldCom = {lqId:lqBigId,lqeId:lqeId,cyStatus:cyStatus,queType:tiganTypeInpVal,queType2:tiganType1InpVal,queSub:currUeEditCon,queTipId:queTipsId,queResolution:currUeEditAnaly,
									lexId:lexId,answerA:$('#ansSelJudgeInp1').val(),answerB:$('#ansSelJudgeInp2').val()};
						}else if(tiganTypeInpVal == '填空题' || tiganTypeInpVal == '问答题'){
							var fieldCom = {lqId:lqBigId,lqeId:lqeId,cyStatus:cyStatus,queType:tiganTypeInpVal,queType2:tiganType1InpVal,queSub:currUeEditCon,queTipId:queTipsId,queResolution:currUeEditAnaly,
									lexId:lexId};
						}
						if(tiganTypeInpVal == '单选题'){
							field = {queAnswer:ans_singleInpVal};
						}else if(tiganTypeInpVal == '多选题'){
							var multiAnsStr = multiAnsArr.join(',');
							field = {queAnswer:multiAnsStr};
						}else if(tiganTypeInpVal == '填空选择题'){
							var tmpResAnsTk = result_answer_text.substring(0,result_answer_text.lastIndexOf(','));
							field = {queAnswer:tmpResAnsTk};		
						}else if(tiganTypeInpVal == '判断题'){
							var judgeInpVal = $('#judgeInp').val();
							field = {queAnswer:judgeInpVal};	
						}else if(tiganTypeInpVal == '填空题'){
							var tkVal = $('#tkInp_' + loreType).val();
							field = {queAnswer:tkVal};
						}else if(tiganTypeInpVal == '问答题'){
							field = {queAnswer:currUeEditAns};
						}
						//进行对象组合
						field = Object.assign(field,fieldCom);
						var url = '/lore.do?action=updateLoreQuesionDetail';
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
										_this.comBackFun();
										_this.loadErrList();//重新加载list
	   				        		});
					        	}else if(json['result'] == 'error'){
					        		layer.msg('添加编辑失败，请稍后重试',{icon:5,anim:6,time:1500});
					        	}
					        }
						});
					}
				},
				bindEvent : function(){
					var _this = this;
					$('.backBtn_tiku').on('click',function(){
						_this.comBackFun();
					});
				},
				comBackFun:function(){
					$('.queType').hide().html('');
	    			$('.quesAddEditBox').hide();
	    			$('.comQuesBox').html('').hide();
	    			result_answer = '';
	    			result_answer_text = '';
	    			currNum = 0;//每次返回重置currNum 便于回显的一个匹配
					$('.loreList').show();
	    			$('#currLoc').html('');
				},
				addLoreQuesInit : function(){
					var topDOM = loreDOM.crealoreTopDOM(),
						loreCon = loreDOM.creaLoreConDOM(loreType);
					$('.queType').show();
					$('.queType').html(topDOM);

					$('.quesAddEditBox').show();
					$('#zsdName').html(loreNameBig);
					
					$('#'+loreType + 'Wrap').show().html(loreCon);
					UE.delEditor('con_'+ loreType +'_'+currNum);
					loreDOM.initUeditor('con_'+ loreType +'_'+currNum);
					
					//增加解析
					UE.delEditor('conAnaly_'+ loreType +'_' +currNum);
					loreDOM.initUeditor('conAnaly_'+ loreType + '_'+currNum);
					
					form.render();
				},
				//回显题库列表每个对应的详情 （编辑)
				renderListTypeInfo : function(listInfo){
					var tmpQueTipId = '';
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

					if(listInfo.length > 0){
						for(var i=0;i<listInfo.length;i++){
							lqBigId = listInfo[i].lqId;
							var tiganType = blDOM.tiganTypeTxt(listInfo[i].queType);
							$('#tiganTypeInp').val(listInfo[i].queType);
							$('#basicTypeTxt').html(listInfo[i].lqType);
							$('#tiganType1Inp').val(listInfo[i].queType2);//了解 理解 应用 综合匹配
							$('#tiganTypeTxt').html(listInfo[i].queType);
							
							//渲染关联此条内容
							$('#'+loreType + '_lexId').val(listInfo[i].lexId);
							$('#'+loreType + '_lexInp').val(listInfo[i].lexTitle);
							lexContent = listInfo[i].lexContent;
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
						}
						blMet.renderTipsSelect(listInfo[0].tipsList);
						queTipsArr = listInfo[0].tipsList;
						//添加编辑词条
						blMet.addEditLex();
						$('#selTipsSel_' + loreType).val(tmpQueTipId);
						$('#tipsInp_ggxl').val(tmpQueTipId);
						if(tmpQueTipId != ''){
							for(var i=0;i<listInfo[0].tipsList.length;i++){
								if(listInfo[0].tipsList[i].selStatus){
									$('#tipsCon_' + loreType).html(listInfo[0].tipsList[i].lqsContent);
								}
							}
							form.render();
						}
						
					}

				}
			};
			//章节下下知识点的添加和编辑（自助餐）
			table.on('tool(errListTab)',function(obj){
				var data = obj.data;
				if(obj.event == 'viewSubDetFun'){//查看错题详情
					var data = obj.data,str='';
					str += '<div class="errBox"><div class="errTypeBox"><strong>错误类型：</strong>'+ data.errorType +'</div>';
					str += '<div class="errCon"><strong>提交内容：</strong>'+ data.content +'</div>';
					str += '<div class="botSub">提交人员：'+ data.addUserName +'</div>';
					str += '<div class="botSub">提交日期：'+ data.addate +'</div></div>';
					layer.open({
						title:data.loreName + '-' + data.lqTitle,
						type: 1,
					  	area: ['550px', '220px'],
					  	fixed: true, //不固定
					  	maxmin: false,
					  	shadeClose :true,
					  	content: str
					});	
				}else if(obj.event == 'editFun'){//编辑错题
					lqeId = data.lqeId;
					globalOpts = $(this).attr('opts');
					loreNameBig = data.loreName;
					layer.load('1');
					$.ajax({
						type:'post',
				        dataType:'json',
				        data:{lqeId:lqeId},
				        url:'/lqe.do?action=getLqeDetail',
				        success:function (json){
				        	layer.closeAll('loading');	
				        	if(json.msg == 'success'){
				        		realAnswer = '';//每次修改知识点下不同题库 清空全局变量 realAnswer
				        		result_answer = '';
				        		result_answer_text = '';
				        		$('.loreList').hide();//隐藏知识点对应题库列表
				        		currNum = 0;
				        		lexContent = '';
				        		$('#currLoc').html('<em style="font-style:normal;float:right;"><a class="backBtn_tiku" href="javascript:void(0)">返回错题列表</a></em>');
								page.bindEvent();
								page.addLoreQuesInit();
				        		page.renderListTypeInfo(json.listIfo);
				        	}else if(json.msg == 'noInfo'){
				        		layer.msg('暂无信息',{icon:5,anim:6,time:2000});
				        	}
				        	
				        }
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
