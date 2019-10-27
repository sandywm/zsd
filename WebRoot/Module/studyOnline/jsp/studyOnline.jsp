<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html> 
<html>
  <head>
    <title>知识典在线学习</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache"> 
	<meta http-equiv="keywords" content="知识典在线学习">
	<meta http-equiv="description" content="知识典在线学习">
	<link rel="stylesheet" type="text/css" href="/css/resetPC.css"/>
	<link rel="stylesheet" type="text/css" href="/Module/studyOnline/css/welcome.css"/>
	<link rel="stylesheet" type="text/css" href="/plugins/swiper/swiper.min.css"/>
	<link href="/plugins/pace/pace-theme-flash.min.css" rel="stylesheet" type="text/css"/>
	<script src="/plugins/pace/pace.min.js" type="text/javascript"></script>	
	</head>
	<body>
		<%@include file="/Module/leftSideBar/header.html"%>
		<div class="mainConWrap">
			<div class="innerMainCon">
				<%@include file="/Module/leftSideBar/fullWidCon.html"%>
				<!-- 选择出版社 -->
				<div class="selEdiWrap">
					<p class="currEditTxt">当前教材版本：<span id="currEdiName" class="noSel"></span></p>
					<div class="ediSel">
						<p class="choiceTxt fl">选择出版社</p>
						<div class="ediName fl">
							<p id="ediNameTxt" class="noSel ellip">请选择出版社</p>
							<i class="triIcon"></i>
							<ul id="ediListUl" class="layui-anim layui-anim-upbit"></ul>
						</div>
					</div>
				</div>
				
				<!-- 上下册 -->
				<div class="volumeList">
					<ul id="volumeListUl"></ul>
				</div>
				
			</div>
		</div>
		<input type="hidden" id="subjectInp" />
		<input type="hidden" id="ediInp" />
		<input type="hidden" id="gradeInp" />
		<%@include file="/Module/leftSideBar/loading.html"%>
	</body>
	<script src="/plugins/jquery/jquery.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="/plugins/swiper/swiper.min.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		var page = {
			init : function(){
				this.loadStudyInitData();
				this.bindEvent();
				//this.loadSwiper();
			},
			bindEvent : function(){
				$('body').click(function(){
					$('#ediListUl').hide();
				});
				$('.navBarBtn').click(function(){
					var isOpen = $(this).attr('isOpen');
					if(isOpen === 'off'){
						$(this).addClass('is-active');
						$(this).attr('isOpen','on');
						$('.leftSideBarList').show();
					}else{
						$(this).removeClass('is-active');
						$(this).attr('isOpen','off');
						$('.leftSideBarList').hide();
					}
				});
				$('.ediName').on('click',function(event){
					$('#ediListUl').show();
					event.stopPropagation();
				});
			},
			getSubjectImg : function(subName,eduVolume){
				var subNameStr = '';
				if(subName == '语文'){
					if(eduVolume == '上册'){
						subNameStr = 'yuwenUp';
					}else{
						subNameStr = 'yuwenDown';
					}
				}else if(subName == '数学'){
					if(eduVolume == '上册'){
						subNameStr = 'shuxueUp';
					}else{
						subNameStr = 'shuxueDown';
					}
				}else if(subName == '英语'){
					if(eduVolume == '上册'){
						subNameStr = 'yingyuUp';
					}else{
						subNameStr = 'yingyuDown';
					}
				}else if(subName == '物理'){
					if(eduVolume == '上册'){
						subNameStr = 'wuliUp';
					}else{
						subNameStr = 'wuliDown';
					}
				}else if(subName == '化学'){
					if(eduVolume == '上册'){
						subNameStr = 'huaxueUp';
					}else{
						subNameStr = 'huaxueDown';
					}
				}else if(subName == '地理'){
					if(eduVolume == '上册'){
						subNameStr = 'diliUp';
					}else{
						subNameStr = 'diliDown';
					}
				}else if(subName == '生物'){
					if(eduVolume == '上册'){
						subNameStr = 'shengwuUp';
					}else{
						subNameStr = 'shengwuDown';
					}
				}else if(subName == '历史'){
					if(eduVolume == '上册'){
						subNameStr = 'lishiUp';
					}else{
						subNameStr = 'lishiDown';
					}
				}else if(subName == '生命科学'){
					if(eduVolume == '上册'){
						subNameStr = 'smkxUp';
					}else{
						subNameStr = 'smkxDown';
					}
				}else if(subName == '科学'){
					if(eduVolume == '上册'){
						subNameStr = 'kexueUp';
					}else{
						subNameStr = 'kexueDown';
					}
				}
				return subNameStr;
			},
			loadStudyInitData : function(){
				var _this = this,subId = $('#subjectInp').val(),
					ediId = $('#ediInp').val(),
					gradId = $('#gradeInp').val();
				var field = {subId:subId,ediId:ediId,gradeNumber:gradId};
				$('.loading').show();
				$.ajax({
					url : '/onlineStudy.do?action=getInitStudyData',
					data:field, 
					dataType:'json',
					type:'post',
					timeout:10000,
					success:function(json){
						$('.loading').hide();
						if(json.result == 'success'){
							var studyList = json.studyList;
							_this.renderSubjectHtml(studyList);
						}else if(json.result == 'noInfo'){
							$('.volumeList').html('<div class="noData_other"><img src="../images/noData.png"/><p class="noDataTxt">暂无教材，请选择学科或出版社<p></div>');
						}else if(json.result == 'error'){
							$('.volumeList').html('<div class="noData_other"><img src="../images/noData.png"/><p class="noDataTxt">获取教材信息失败<p></div>');
						}
						if(json.result == 'success' || json.result == 'noInfo' || json.result == 'error'){
							var ediList = json.ediList,gradeList = json.gradeList,subList = json.subList;
							_this.createGradeSel(gradeList);
							_this.createSubSel(subList);
							_this.createEdiSel(ediList);
							
							$('#ediListUl li').on('click',function(event){
								var ediId = $(this).attr('ediId');
								$('#ediInp').val(ediId);
								$('#ediListUl').hide();
								$('#ediNameTxt').html($(this).html());
								event.stopPropagation();
								_this.loadStudyInitData();
							});
							
						}
					},
					error:function(xhr,type,errorThrown){
						$('.loading').hide();
					}
				}); 
			},
			renderSubjectHtml : function(list){
				var _this = this,str = '<ul>';
				for(var i=0;i<list.length;i++){
					str += '<li><div class="subImg"><img src="Module/studyOnline/images/subject/'+ _this.getSubjectImg(list[i].subName,list[i].eduVolume) +'.png"/></div>';
					str += '<div class="bindNtInfo"></div>';
					str += '<div class="volumeInfo">';
					str += '<p><span>当前状态：</span><em class="staCol">'+ list[i].freeStatus +'</em></p>';
					str += '<p><span>已<span class="oneBlank"></span>使<span class="oneBlank"></span>用：</span><em class="staCol">'+ list[i].studyDays +'天</em></p>';
					if(list[i].remainDays > 0){
						str += '<p><span>还<span class="twoBlank"></span>剩：</span><em class="overDate">'+ list[i].remainDays +'天</em></p>';	
					}else{
						var remainDaysStr = (list[i].remainDays).toString().replace('-','');
						str += '<p><span>已<span class="oneBlank"></span>过<span class="oneBlank"></span>期：</span><em class="staCol">'+ remainDaysStr +'天</em></p>';
					}
					str += '<p><span>使用期限：</span>'+ list[i].date_range +'</p>';
					str += '</div>';
					str += '<a class="goStudyBtn" eduId="'+ list[i].eduId +'" eduVol="'+ list[i].eduVolume +'" href="javascript:void(0)"></a>';
					str += '</li>';
				}
				str += '</ul>';
				$('.volumeList').html(str);
				$('.goStudyBtn').on('click',function(){
					var eduId = $(this).attr('eduId'),
						eduVol = $(this).attr('eduVol');
					window.location.href = 'onlineStudy.do?action=goChaptePage&eduId=' + eduId;
				});
			},
			//创建出版社
			createEdiSel : function(list){
				var _this = this,ediStr = '';
				for(var i=0;i<list.length;i++){
					if(list[i].selFlag){
						$('#ediNameTxt').removeClass('noSel').html(list[i].ediName);
						$('#currEdiName').removeClass('noSel').html(list[i].ediName);
						$('#ediInp').val(list[i].ediId);
						ediStr += '<li ediId="'+ list[i].ediId +'" class="active">';
					}else{
						ediStr += '<li ediId="'+ list[i].ediId +'">';
					}
					ediStr += '<a href="javascript:void(0)">'+ list[i].ediName +'</a></li>';
				}
				$('#ediListUl').html(ediStr);
			},
			//创建学科
			createSubSel : function(list){
				var _this = this,subStr = '';
				if(list.length == 1){
					$('#subjectInp').val(list[0].subId);
					subStr += '<div subId="'+ list[0].subId +'" class="swiper-slide active"><a href="javascript:void(0)"><img src="Module/studyOnline/images/subject/shuxue.png"/></a></div>';
					$('#subListBox').html(subStr);
				}else if(list.length > 1){
					for(var i=0;i<list.length;i++){
						if(list[i].selFlag){
							$('#subjectInp').val(list[i].subId);
							subStr += '<div subId="'+ list[i].subId +'" class="swiper-slide active">';
						}else{
							subStr += '<div subId="'+ list[i].subId +'" class="swiper-slide">';
						}
						subStr += '<a href="javascript:void(0)"><img src="Module/studyOnline/images/subject/shuxue.png"/></a>';
						subStr += '</div>';
					}
					$('#subListBox').html(subStr);
					$('.swiper-slide').on('click',function(){
						var subId = $(this).attr('subId');
						$('#subjectInp').val(subId);
						_this.loadStudyInitData();
					});
				}
				if(list.length > 5){
					_this.loadSwiper();
				}
			},
			//创建年级
			createGradeSel : function(list){
				var _this = this,gradeStr = '';;
				if(list.length == 1){
					$('#gradeInp').val(list[0].gradeNumber);
					gradeStr += '<li gradeNumber="'+ list[0].gradeNumber +'" class="active">'+ list[0].gradeNanme +'</li>';
					$('#gradeListUl').html(gradeStr);
				}else if(list.length > 1){
					$('.tipInfoTxt').show();
					for(var i=0;i<list.length;i++){
						if(list[i].selFlag){
							$('#gradeInp').val(list[i].gradeNumber);
							$('.tipInfoTxt').html('您已进入'+ list[i].gradeName +'复习阶段，可切换年级进行复习!');
							gradeStr += '<li gradeNumber="'+ list[i].gradeNumber +'" class="active">';
						}else{
							gradeStr += '<li gradeNumber="'+ list[i].gradeNumber +'">';
						}
						gradeStr += list[i].gradeName;
						gradeStr += '</li>';
					}
					$('#gradeListUl').html(gradeStr);
					$('#gradeListUl li').on('click',function(){
						var gradeNumber = $(this).attr('gradeNumber');
						$('#gradeInp').val(gradeNumber);
						_this.loadStudyInitData();
					});
				}
			},
			loadSwiper : function(){
				var swiper = new Swiper('.innerSubList', {
					slidesPerView:5,
					spaceBetween: 30,
					pagination: {
						el: '.swiper-pagination',
						clickable: true
					}
				}); 
				
			}
		};
		page.init();
	</script>
</html>
