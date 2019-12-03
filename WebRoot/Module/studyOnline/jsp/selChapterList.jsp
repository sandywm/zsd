<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html> 
<html>
  <head>
    <title>知识典在线学习</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache"> 
	<meta http-equiv="keywords" content="知识典在线学习">
	<meta http-equiv="description" content="知识典在线学习选择章节">
	</head>
	<body>
		<%@include file="/Module/leftSideBar/loading.html"%>
		<%@include file="/Module/leftSideBar/header.html"%>
		<div class="mainConWrap">
			<div class="innerMainCon">
				<%@include file="/Module/leftSideBar/fullWidCon.html"%>				
				<!-- 章节列表 -->
				<div class="chaptList clearfix">
					<div class="leftChaptName"></div>
					<div class="loreList">
						<div class="chaptNameTit">
							<strong id="currChaptName" class="ellip"></strong>
							<div class="staBox">
								<p class="hasGetSta">
									<i class="iconfont layui-extend-zhangjie"></i>
									<span>课后诊断已掌握</span>
								</p>
								<p class="notGetSta">
									<i class="iconfont layui-extend-zhangjie"></i>
									<span>课后诊断未掌握</span>
								</p>
								<p class="noLearnSta">
									<i class="iconfont layui-extend-zhangjie"></i>
									<span>课后诊断未学习</span>
								</p>
							</div>
						</div>
						<!-- loreList -->
						<div class="listLore">
							<ul id="listLoreUl" class="clearfix"></ul>
							<img src="Module/studyOnline/images/chapBg.png"/>
						</div>
					</div>
				</div>
				
				
			</div>
		</div>
	</body>
	<script type="text/javascript">
		var eduId = ${ requestScope.eduId };
		var cptId = ${ requestScope.cptId };
		var strBack = '<a href="onlineStudy.do?action=goStudyPage" class="backBtn"><i class="iconfont layui-extend-fanhui"></i><span>返回教材</span></a>';
		var chaptPage = {
			init : function(){
				$('.ediSel').remove();
				this.loadChaptList();
				$('.selEdiWrap').append(strBack);
			},
			//加载章节列表
			loadChaptList : function(){
				var _this = this;
				$('.loading').show();
				$('.chaptList').show();
				$.ajax({
					url : '/onlineStudy.do?action=getStudyCptData',
					data:{eduId:eduId}, 
					dataType:'json',
					type:'post',
					timeout:10000,
					success:function(json){
						$('.loading').hide();
						if(json.result == 'success'){
							var cptList = json.cptList;
							_this.renderChapterHtml(cptList);
						}else if(json.result == 'noInfo'){
							$('.leftChaptName').html('<div class="noInfo_chapt"><i class="iconfont layui-extend-zanwujilu"></i><p>暂无章节</p></div>');
							$('#listLoreUl').html('<div class="noInfo_lore"><i class="iconfont layui-extend-zanwujilu1"></i><p>暂无知识点</p></div>');
						}
					},
					error:function(xhr,type,errorThrown){
						$('.loading').hide();
						zsd_toast('服务器异常',1500);
					}
				}); 
			},
			//生成章节列表
			renderChapterHtml : function(list){
				var strLeft = '<ul>',strRight = '',_this = this;
				for(var i=0;i<list.length;i++){
					if(cptId == 0){
						if(i == 0){
							$('#currChaptName').html(list[0].cptName);
							strLeft += '<li class="active" cptId="'+ list[i].cptId +'">';
							for(var j=0;j<list[0].loreList.length;j++){
								if(list[0].loreList[j].studyStatus == 0){//0:未学习 1未通过 2:已经掌握
									strRight += '<li class="noLearnSta" loreName="'+ list[0].loreList[j].loreName +'" studyStatus="'+ list[0].loreList[j].studyStatus +'" loreId="'+ list[0].loreList[j].loreId +'" studyLogId="'+ list[0].loreList[j].studyLogId +'">';
								}else if(list[0].loreList[j].studyStatus == 1){
									strRight += '<li class="notGetSta" loreName="'+ list[0].loreList[j].loreName +'" studyStatus="'+ list[0].loreList[j].studyStatus +'" loreId="'+ list[0].loreList[j].loreId +'" studyLogId="'+ list[0].loreList[j].studyLogId +'">';
								}else{
									strRight += '<li class="hasGetSta" loreName="'+ list[0].loreList[j].loreName +'" studyStatus="'+ list[0].loreList[j].studyStatus +'" loreId="'+ list[0].loreList[j].loreId +'" studyLogId="'+ list[0].loreList[j].studyLogId +'">';
								}
								strRight += '<i class="iconfont layui-extend-zhangjie"></i>';
								strRight += '<span>'+ list[0].loreList[j].loreName +'</span>';
								strRight += '</li>';
							}
						}else{
							strLeft += '<li cptId="'+ list[i].cptId +'">';
						}
					}else{
						if(cptId == list[i].cptId){
							$('#currChaptName').html(list[i].cptName);
							strLeft += '<li class="active" cptId="'+ list[i].cptId +'">';
						}else{
							strLeft += '<li cptId="'+ list[i].cptId +'">';
						}
					}
					strLeft += list[i].cptName;
					strLeft += '</li>';
				}
				strLeft += '</ul>';
				$('.leftChaptName').html(strLeft);
				$('#listLoreUl').html(strRight);
				_this.getLoreList();
				_this.goStudyMap();
				if(cptId != 0){
					_this.showCptLorelist(cptId);
				}
			},
			//生成知识点根据章节
			getLoreList : function(){
				var _this = this;
				$('.leftChaptName li').on('click',function(){
					var chaptId = $(this).attr('cptId');
					$('.leftChaptName li').removeClass('active');
					$(this).addClass('active');
					$('#currChaptName').html($(this).html());
					_this.showCptLorelist(chaptId);
				});
			},
			showCptLorelist : function(chaptId){
				var _this= this;
				$('.loading').show();
				$.ajax({
					url : '/onlineStudy.do?action=getLoreData',
					data:{cptId:chaptId}, 
					dataType:'json',
					type:'post',
					timeout:10000,
					success:function(json){
						$('.loading').hide();
						if(json.result == 'success'){
							var loreList = json.loreList;
							_this.renderLoreList(loreList);
						}else if(json.result == 'noInfo'){
							$('#listLoreUl').html('<div class="noInfo_lore"><i class="iconfont layui-extend-zanwujilu1"></i><p>暂无知识点</p></div>');
						}
					},
					error:function(xhr,type,errorThrown){
						$('.loading').hide();
					}
				}); 
			},
			renderLoreList : function(loreList){
				var str = ''; 
				for(var i=0;i<loreList.length;i++){
					if(loreList[i].studyStatus == 0){//0:未学习 1未通过 2:已经掌握
						str += '<li class="noLearnSta" loreName="'+ loreList[i].loreName +'" studyStatus="'+ loreList[i].studyStatus +'" loreId="'+ loreList[i].loreId +'" studyLogId="'+ loreList[i].studyLogId +'">';
					}else if(loreList[i].studyStatus == 1){
						str += '<li class="notGetSta" loreName="'+ loreList[i].loreName +'" studyStatus="'+ loreList[i].studyStatus +'" loreId="'+ loreList[i].loreId +'" studyLogId="'+ loreList[i].studyLogId +'">';
					}else{
						str += '<li class="hasGetSta" loreName="'+ loreList[i].loreName +'" studyStatus="'+ loreList[i].studyStatus +'" loreId="'+ loreList[i].loreId +'" studyLogId="'+ loreList[i].studyLogId +'">';
					}
					str += '<i class="iconfont layui-extend-zhangjie"></i>';
					str += '<span>'+ loreList[i].loreName +'</span>';
					str += '</li>';
				}
				$('#listLoreUl').html(str);
				this.goStudyMap();
			},
			//检测知识点完成次数 一天只能完成一次
			checkCurrentLore : function(studyLogId){
				var flag = false;
				$.ajax({
					url : '/onlineStudy.do?action=checkCurrentLore',
					data:{studyLogId:studyLogId}, 
					dataType:'json',
					type:'post',
					async:false,
					timeout:10000,
					success:function(json){
						if(json.studyFlag == true){
							flag = true;
						}else if(json.studyFlag == false){
							flag = false;
						}
					},
					error:function(xhr,type,errorThrown){
						zsd_toast('服务器异常',1500);
					}
				});
				return flag;
			},
			goStudyMap : function(){
				var _this = this;
				$('#listLoreUl li').on('click',function(){
					var loreId = $(this).attr('loreId'),
						loreName = $(this).attr('loreName'),
						studyLogId = $(this).attr('studyLogId'),
						studyStatus = $(this).attr('studyStatus'),flag = false;
					var strBack = '<a href="javascript:void(0)" class="backBtn"><i class="iconfont layui-extend-fanhui"></i><span>返回章节列表</span></a>';
					if(studyStatus == 2){
						if( !_this.checkCurrentLore(studyLogId) ){//当天已经完成，不能再做
							zsd_toast('一个知识点一天只能完成一次',1800);
							flag = false;
						}else{
							flag = true;
						}
					}else{
						flag = true;	
					}
					if(flag){
						window.location.href = 'onlineStudy.do?action=goStudyMapPage&loreId=' + loreId + '&studyLogId=' + studyLogId;
					}
				});
			}
		};
		chaptPage.init();
		
	</script>
</html>
