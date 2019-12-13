var provVal = '',cityVal='',countyVal='',townVal='',provIndex=0,cityIndex=0,currTownVal=0;//用于编辑时匹配当前省市index索引
layui.define(["form","jquery","table"],function(exports){
    var form = layui.form,
    $ = layui.jquery,table=layui.table,
    Address = function(){};
    
    Address.prototype.provinces = function() {
        //加载省数据
        var proHtml = '',that = this;
        $.get("/plugins/frame/json/area.json", function (data) {
            for (var i = 0; i < data.length; i++) {
            	if(provVal != '' && provVal == data[i].name){
            		proHtml += '<option value="'+ data[i].value +'-' + data[i].name + '" selected>' + data[i].name + '</option>';
            		provIndex = i;
            	}else{
            		proHtml += '<option value="'+ data[i].value +'-' + data[i].name + '">' + data[i].name + '</option>';
            	}
            }
            //初始化省数据
            $("select[name=province]").append(proHtml);
            form.render();
            if(currRoleName == 'prov'){
            	that.citys(data[provIndex].children);
            }
            //如果市区值不为空起初 匹配
            if(cityVal != ''){
            	var cityData = data[provIndex].children;
            	var cityStr = '<option value="">请选择市</option>';
            	$("select[name=city]").attr('disabled',false);
            	that.citys(cityData);
            	for(var i=0;i<cityData.length;i++){
            		if(cityVal == cityData[i].name){
            			cityStr += '<option value="'+ cityData[i].value +'-'+ cityData[i].name +'" selected>'+ cityData[i].name +'</option>';
            			cityIndex = i;
            		}else{
            			cityStr += '<option value="'+ cityData[i].value +'-'+ cityData[i].name +'">'+ cityData[i].name +'</option>';
            		}
            	}
            	$("select[name=city]").html(cityStr);
            	if(currRoleName == 'city'){
            		that.areas(data[provIndex].children[cityIndex].children);
            	}
            	form.render();
            }
            //如果县区不为空 匹配
            if(countyVal != ''){
            	var countyData = data[provIndex].children[cityIndex].children;
            	var countyStr = '<option value="">请选择县/区</option>';
            	that.areas(countyData);
            	$("select[name=area]").attr('disabled',false);
            	for(var i=0;i<countyData.length;i++){
            		if(countyVal == countyData[i].name){
            			countyStr += '<option value="' + countyData[i].value + '-'+ countyData[i].name +'" selected>'+ countyData[i].name +'</option>';
            			currTownVal = countyData[i].value;
            		}else{
            			countyStr += '<option value="' + countyData[i].value + '-'+ countyData[i].name +'">'+ countyData[i].name +'</option>';
            		}
            	}
            	$("select[name=area]").html(countyStr);
            	if(currRoleName == 'county'){
            		that.getTownData(currTownVal,'loadTown');
            	}
            	form.render();
            }
            
            if(townVal != ''){
            	var tonwStr = '<option value="">请选择乡/镇</option>';
            	that.getTownData(currTownVal,'loadTown');
            	$("select[name=town]").attr('disabled',false);
            }
            //form.render();
            form.on('select(province)', function (proData) {
                $("select[name=area]").html('<option value="">请选择县/区</option>');
                var value = proData.value.split('-')[0],
                	name = proData.value.split('-')[1];
                $('#provInp').val(name);
                $('#provCode').val(value);
                $('#cityInp').val('');
                $('#cityCode').val('');
                $('#countyInp').val('');
                $('#countyCode').val('');
                $('#townInp').val('');
                $('#townCode').val('');
                //注册时候清空对应值
                $('#schoolIdInp').val('');
				$('#schNameTxt').html('请选择学校').addClass('noSel');
				$('#schListUl').html('');
				
				$('#gradeNum').val('');
				$('#gradeNameTxt').html('请选择年级').addClass('noSel');
				$('#gradeListUl').html('');
				
				$('#classNum').val('');
				$('#classNameTxt').html('请选择班级').addClass('noSel');
				$('#classListUl').html('');
				
                $("select[name=town]").html('<option value="">请选择乡/镇</option>').attr("disabled", "disabled");
                if (value != '') {
                	that.citys(data[$(this).index() - 1].children);
                } else {
                    $("select[name=city]").html('').attr("disabled", "disabled");
					$("select[name=area]").html('').attr("disabled", "disabled");
                }
                form.render();
            });
        });
        //学段form select
		form.on('select(schTypeSel)', function(data){
			if(data.value == ''){
				schTypeVal =  0;
			}else{
				schTypeVal = data.value;
			}
			$('#schSel').html('<option value="">请选择学校</option>').val('');
			$('#gradeSel').val('').html('<option value="">请选择年级</option>');
			$('#classSel').val('').html('<option value="">请选择班级</option>');
			$('#stuSel').val('').html('<option value="">请选择学生</option>');
			$('#schInp').val(0);
			$('#gradeInp').val('');
			$('#classInp').val(0);
			$('#stuInp').val(0);
			adminAddress.getSchoolData();
			if(currPage == 'qfRepPage'){
	    		adminAddress.loadQfData();
	    	}else if(currPage == 'xxTjPage'){
	    		adminAddress.loadXxTjData();
	    	}
			form.render();
		});
        
        //班级form
        form.on('select(classSel)', function(data){
        	if(data.value == ''){
        		$('#classInp').val(0);
        	}else{
        		$('#classInp').val(data.value);
        	}
        	var gradeInp = $('#gradeInp').val();
        	if(gradeInp == ''){
        		layer.msg('请选择年级');
        		return;
        	}
		});
        
        $('#queryBtn').on('click',function(){
			var stDate = $('#stDate').val(),
				edDate = $('#edDate').val();
			if(stDate == '' && edDate != ''){
				layer.msg('请选择开始时间');
				return;
			}
			if(edDate == '' && stDate != ''){
				layer.msg('请选择结束时间');
				return;
			}
			if(stDate != '' && edDate != ''){
				if(stDate > edDate){
					layer.msg('开始时间不能大于结束时间');
					return;
				}
			}
			if(currPage == 'qfRepPage'){
	    		adminAddress.loadQfData();
	    	}else if(currPage == 'xxTjPage'){
	    		adminAddress.loadXxTjData();
	    	}
		});
        if(currPage == 'xxTjPage'){
        	 $('.clearBtn').on('click',function(){
        		 if($('#stuNameInp').val() != ''){
        			 $('#stuNameInp').val('');
        			 adminAddress.loadXxTjData();
        		 }
             });
        }
       
    };
    Address.prototype.createClasses  = function(list){
    	var str = '<option value="">请选择班级</option>';
		for(var i=0;i<list.length;i++){
			str += '<option value="'+ list[i].classId +'">'+ list[i].className +'</option>';
		}
		$('#classSel').html(str);
		form.render();
    };
    //年级form
    form.on('select(gradeSel)', function(data){
    	if(data.value == ''){
    		$('#gradeInp').val('');
    	}else{
    		$('#gradeInp').val(data.value);
    	}
    	$('#classSel').val('').html('<option value="">请选择班级</option>');
		$('#stuSel').val('').html('<option value="">请选择学生</option>');
		$('#classInp').val(0);
		$('#stuInp').val(0);
		
    	adminAddress.createClassData();
    	if(currPage == 'qfRepPage'){
			adminAddress.loadQfData();
		}else if(currPage == 'xxTjPage'){
    		adminAddress.loadXxTjData();
    	}
	});
   //加载市数据
    Address.prototype.citys = function(citys) {
        var cityHtml = '<option value="">请选择市</option>',that = this;
        for (var i = 0; i < citys.length; i++) {
        	cityHtml += '<option value="'+ citys[i].value +'-' + citys[i].name + '">' + citys[i].name + '</option>';
        }
        $("select[name=city]").html(cityHtml).removeAttr("disabled");
        form.render();
        form.on('select(city)', function (cityData) {
			$("select[name=area]").html('<option value="">请选择县/区</option>');
            var value = cityData.value.split('-')[0],
            	name = cityData.value.split('-')[1];
            $('#cityInp').val(name);
            $('#cityCode').val(value);
            $('#countyInp').val('');
            $('#countyCode').val('');
            $('#townInp').val('');
            $('#townCode').val('');
			
            $("select[name=town]").html('<option value="">请选择乡/镇</option>').attr("disabled", "disabled");
            if (value != '') {
            	that.areas(citys[$(this).index() - 1].children);
            } else {
                $("select[name=area]").html('').attr("disabled", "disabled");
            }
            $('#schSel').html('<option value="">请选择学校</option>').val('');
			$('#gradeSel').val('').html('<option value="">请选择年级</option>');
			$('#classSel').val('').html('<option value="">请选择班级</option>');
			$('#stuSel').val('').html('<option value="">请选择学生</option>');
			$('#schInp').val(0);
			$('#gradeInp').val('');
			$('#classInp').val(0);
			$('#stuInp').val(0);
			
			if(currPage == 'qfRepPage'){
	    		adminAddress.loadQfData();
	    	}else if(currPage == 'xxTjPage'){
	    		adminAddress.loadXxTjData();
	    	}
			
            form.render();
        });
    };
    //获取学校数据
    Address.prototype.getSchoolData = function(){
		layer.load('1');
		var field = {prov:provVal,city:$('#cityInp').val(),county:$('#countyInp').val(),town:$('#townInp').val(),schoolType:schTypeVal,opt:1,yearSystem:-1};
		$.ajax({
			url : '/baseInfo.do?action=getSchoolData',
			dataType:'json',//服务器返回json格式数据  
			data:field,
			type:'post',//HTTP请求类型  
			timeout:10000,//超时时间设置为10秒；  
			success:function(json){   
				layer.closeAll('loading');
				if(json.result == 'success'){
					var schList = json.schList;
					adminAddress.rednerSchTypeHtml(schList);
					form.render();
				}else if(json.result == 'noInfo'){
					layer.msg('暂无学校');
				}
			},  
			error:function(xhr,type,errorThrown){ 
				console.log(type);  
			}  
		});
	};
    
    //生成年级
    Address.prototype.createGradeData = function(schTypeVal,yearSystem){
    	$('#gradeInp').val('');
    	$('#classInp').val(0);
    	if(schTypeVal == 1){
    		if(yearSystem == 5){
    			adminAddress.createGradeHtml(1,5);
    		}else if(yearSystem == 6){
    			adminAddress.createGradeHtml(1,6);
    		}
    	}else if(schTypeVal == 2){//初中
    		if(yearSystem == 3){
    			adminAddress.createGradeHtml(7,9);
    		}else if(yearSystem == 4){
    			adminAddress.createGradeHtml(6,9);
    		}
    	}else if(schTypeVal == 3){//高中
    		adminAddress.createGradeHtml(10,12);
    		
    	}
    }
    
    //生成学校
    Address.prototype.rednerSchTypeHtml = function(schList){
		var str = '<option value="">请选择学校</option>';
		for(var i=0;i<schList.length;i++){
			if(schTypeVal != 0){
				str += '<option value="'+ schList[i].schoolId +'-'+ schList[i].yearSystem +'">'+ schList[i].schoolName +'</option>';
			}else{
				str += '<option value="'+ schList[i].schoolId +'-'+ schList[i].yearSystem +','+ schList[i].schoolType +'">'+ schList[i].schoolName +'</option>';
			}
		}
		$('#schSel').html(str);
		
		//学校form
		form.on('select(schSel)', function(data){
			if(data.value != ''){
				if(schTypeVal == 0){//全部学校
					var yearSys = data.value.split('-')[1].split(',')[0];
					var currSchType = data.value.split('-')[1].split(',')[1];
					adminAddress.createGradeData(currSchType,yearSys);
				}else{
					var yearSys = data.value.split('-')[1];
					adminAddress.createGradeData(schTypeVal,yearSys);
				}
				$('#schInp').val(data.value.split('-')[0]);
			}else{
				$('#schInp').val(0);
				$('#gradeSel').val('').html('<option value="">请选择年级</option>');
				$('#classSel').val('').html('<option value="">请选择班级</option>');
				$('#stuSel').val('').html('<option value="">请选择学生</option>');
				$('#gradeInp').val('');
				$('#classInp').val(0);
				$('#stuInp').val(0);
				form.render();
			}
			if(currPage == 'qfRepPage'){
	    		adminAddress.loadQfData();
	    	}else if(currPage == 'xxTjPage'){
	    		adminAddress.loadXxTjData();
	    	}
		});
	};
	Address.prototype.switchNumToCHN = function(num){
		var currCHN = '';
		if(num == 1){
			currCHN = '一年级';
		}else if(num == 2){
			currCHN = '二年级';
		}else if(num == 3){
			currCHN = '三年级';
		}else if(num == 4){
			currCHN = '四年级';
		}else if(num == 5){
			currCHN = '五年级';
		}else if(num == 6){
			currCHN = '六年级';
		}else if(num == 7){
			currCHN = '七年级';
		}else if(num == 8){
			currCHN = '八年级';
		}else if(num == 9){
			currCHN = '九年级';
		}else if(num == 10){
			currCHN = '高一';
		}else if(num == 11){
			currCHN = '高二';
		}else if(num == 12){
			currCHN = '高三';
		}
		return currCHN;
	};
    //创建年级结构
	Address.prototype.createGradeHtml = function(stNum,endNum){
		var str = '<option value="">请选择年级</option>';
		for(var i=stNum;i<=endNum;i++){
			str += '<option value="'+ this.switchNumToCHN(i) +'">'+ this.switchNumToCHN(i) +'</option>';
		}
		$('#gradeSel').html(str);
		form.render();
	};
	//根据年级动态创建班级数据
	Address.prototype.createClassData = function(){
		layer.load('1');
		var field = {schoolId:$('#schInp').val(),gradeName:escape($('#gradeInp').val())};
		$('#classInp').val(0);
		console.log(field)
		$.ajax({
			url : '/baseInfo.do?action=getValidClassData',
			dataType:'json',//服务器返回json格式数据  
			data:field,
			type:'post',//HTTP请求类型  
			timeout:10000,//超时时间设置为10秒；  
			success:function(json){   
				layer.closeAll('loading');
				if(json.msg == 'success'){
					adminAddress.createClasses(json.classList);
				}else if(json.msg == 'noInfo'){
					$('#classSel').val('').html('<option value="">请选择班级</option>');
					form.render();
					layer.msg('该年级下暂无班级');
				}
			},  
			error:function(xhr,type,errorThrown){ 
				console.log(type);  
			}  
		});
		
		//班级form
		form.on('select(classSel)', function(data){
			if(data.value == ''){
				$('#classInp').val(0);
				$('#stuInp').val(0);
				$('#stuSel').val('').html('<option value="">请选择学生</option>');
				form.render();
			}else{
				$('#classInp').val(data.value);
				adminAddress.createStuData();
			}
			if(currPage == 'qfRepPage'){
	    		adminAddress.loadQfData();
	    	}else if(currPage == 'xxTjPage'){
	    		adminAddress.loadXxTjData();
	    	}
		});
		
	};
	
	//根据班级classId动态加载学生
	Address.prototype.createStuData = function(){
		layer.load('1');
		var field = {classId:$('#classInp').val()};
		$('#stuInp').val(0);
		$.ajax({
			url : '/common.do?action=getSpecClassStuData',
			dataType:'json',//服务器返回json格式数据  
			data:field,
			type:'post',//HTTP请求类型  
			timeout:10000,//超时时间设置为10秒；  
			success:function(json){   
				layer.closeAll('loading');
				if(json.result == 'success'){
					adminAddress.createStuHtml(json.userList);
				}else if(json.result == 'noInfo'){
					$('#stuSel').val('').html('<option value="">请选择学生</option>');
					form.render();
					layer.msg('该班级下暂无学生');
				}else if(json.result == 'error'){
					$('#stuSel').val('').html('<option value="">请选择学生</option>');
					form.render();
					layer.msg('服务器异常');
				}
			},  
			error:function(xhr,type,errorThrown){ 
				console.log(type);  
			}  
		});
	};
	//创建学生数据结构
	Address.prototype.createStuHtml = function(list){
		var str = '<option value="">请选择学生</option>';
		for(var i=0;i<list.length;i++){
			str += '<option value="'+ list[i].userId +'">'+ list[i].userName +'</option>';
		}
		$('#stuSel').html(str);
		form.render();
		
		//学生form
		form.on('select(stuSel)', function(data){
			if(data.value == ''){
				$('#stuInp').val(0);
			}else{
				$('#stuInp').val(data.value);
			}
			if(currPage == 'qfRepPage'){
	    		adminAddress.loadQfData();
	    	}else if(currPage == 'xxTjPage'){
	    		adminAddress.loadXxTjData();
	    	}
		});
	};
	
	//获取学科数据
	Address.prototype.createSubData = function(){
		var field = {showStatus:$('#subIdInp').val()};
		$.ajax({
			url : '/common.do?action=getSubjectData',
			dataType:'json',//服务器返回json格式数据  
			data:field,
			type:'post',//HTTP请求类型  
			timeout:10000,//超时时间设置为10秒；  
			success:function(json){  
				if(json.msg == 'success'){
					adminAddress.createSubHtml(json.data);
				}else if(json.msg == 'noInfo'){
					$('#subSel').val('').html('<option value="">请选择学科</option>');
					form.render();
					layer.msg('暂无学科');
				}
			},  
			error:function(xhr,type,errorThrown){ 
				console.log(type);  
			}  
		});
	};
	//生成学科Html
	Address.prototype.createSubHtml = function(list){
		var str = '<option value="-1">全部学科</option>';
		for(var i=0;i<list.length;i++){
			str += '<option value="'+ list[i].id +'">'+ list[i].subName +'</option>';
		}
		$('#subSel').html(str);
		form.render();
		
		
		//学科form
		form.on('select(subSel)', function(data){
			if(data.value == ''){
				$('#subIdInp').val(0);
				$('#subSel').val('').html('<option value="">请选择学科</option>');
				form.render();
			}else{
				$('#subIdInp').val(data.value);
				if(currPage == 'qfRepPage'){
					adminAddress.loadQfData();
				}else if(currPage == 'xxTjPage'){
		    		adminAddress.loadXxTjData();
		    	}
			}
		});
	};
	
	//加载勤奋echartData
	Address.prototype.loadQfData = function(){
		layer.load('1');
		var stDate = $('#stDate').val(),
			edDate = $('#edDate').val(),
			cityInp = $('#cityInp').val(),
			countyInp = $('#countyInp').val(),
			townInp = $('#townInp').val(),
			schInp = $('#schInp').val(),
			gradeInp = $('#gradeInp').val(),
			classInp = $('#classInp').val(),
			stuIdInp = $('#stuInp').val(),
			subIdInp = $('#subIdInp').val();
		//prov,city,county,town,schoolType,schoolId,gradeName,classId,stuId(可不传),subId,sDate,eDate,userId,roleId
		var field = {prov:escape(provVal),city:escape(cityInp),county:escape(countyInp),town:escape(townInp),schoolType:schTypeVal,
					schoolId:schInp,gradeName:gradeInp,classId:classInp,subId:subIdInp,stuId:stuIdInp,sDate:stDate,eDate:edDate};
		//console.log(field)
		$.ajax({
			url : '/reportCenter.do?action=getQfTjData',
			data:field, 
			dataType:'json', 
			type:'post',
			timeout:10000,
			success:function(json){
				//console.log(json)
				layer.closeAll('loading');
				$('#stDate').val(json.sDate);
				$('#edDate').val(json.eDate);
				if(json.result == 'success'){
					Address.prototype.loadChart(json,json.axisName1,json.axisName2);
					$('#rateTxt').html('<span>' + json.axisName1 + '转化率为：</span>' + json.rate);
					$('#rateAllTxt').html('<span>' + json.axisName2 + '转化率为：</span>' + json.rateAll);
				}else if(json.result == 'noInfo'){
					$('#qinfenDataBox').hide();
					$('.noDataImg').show(); 
					$('#rateTxt').html('');
					$('#rateAllTxt').html('');
				}
			},
			error:function(xhr,type,errorThrown){
			}
		}); 
	};
	
	Address.prototype.loadChart = function(json,axisName1,axisName2){
		$('.noDataImg').hide();
		$('#qinfenDataBox').show();
		var axisName = [axisName1,axisName2];
		var myChart = echarts.init(document.getElementById('qinfenDataBox')),
			singleData = [json.oneZdSuccNum,json.oneZdFailNum,json.againXxSuccNum,json.againXxFailNum,json.noRelateNum,json.relateZdFailNum,json.relateXxSuccNum,json.relateXxFailNum],
			totalData = [json.oneZdSuccNumAll,json.oneZdFailNumAll,json.againXxSuccNumAll,json.againXxFailNumAll,json.noRelateNumAll,json.relateZdFailNumAll,json.relateXxSuccNumAll,json.relateXxFailNumAll];
		 // 指定图表的配置项和数据
		var option = {
			title: {
				text: ''
			},
			tooltip: {trigger: 'axis'},
			legend: {
				data: axisName,
				x : 15
			},
			grid: {
				x: 50,
				x2: 50,
				y: 50,
				y2: 50
			},
			toolbox: {
				y : -5,
				feature : {
					dataView : {show: true, readOnly: true},
					magicType : {show: true, type: ['line', 'bar']},
					restore : {show: true}
					//saveAsImage : {show: true}
				}
			},
			calculable: false,
			xAxis: {
				type: 'category',
				data: ['一次性通过总数', '一次性未通过总数', '再次诊断(学习)通过', '再次诊断(学习)未通过', '未溯源个数', '关联诊断未通过', '关联学习通过', '关联未学习通过'],
				axisLabel: {
					interval:0//横轴信息全部显示
				}
			},
			yAxis : [
				{
					type : 'value'
				}
			],
			series: [{
				name: axisName[0],
				type: 'bar',
				barWidth:20,
				data: singleData,
				itemStyle : {
					 normal: {
			            color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
			                offset: 0,
			                color: "#f74848" // 0% 处的颜色
			            }, {
			                offset: 1,
			                color: "#ffc74c" // 100% 处的颜色
			            }], false)
					 }
					
				}
			},{
				name: axisName[1],
				type: 'bar',
				barWidth:20,
				data: totalData,
				itemStyle : {
					 normal: {
			            color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
			                offset: 0,
			                color: "#3167e1" // 0% 处的颜色
			            }, {
			                offset: 1,
			                color: "#1ee8e0" // 100% 处的颜色
			            }], false)
					 }
					
				}
			}]
		};
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option);
	};
    //加载县/区数据
    Address.prototype.areas = function(areas) {
    	var _this = this;
        var areaHtml = '<option value="">请选择县/区</option>';
        for (var i = 0; i < areas.length; i++) {
            areaHtml += '<option value="' + areas[i].value + '-'+ areas[i].name +'">' + areas[i].name + '</option>';
        }
        $("select[name=area]").html(areaHtml).removeAttr("disabled");
        form.render();

		form.on('select(area)', function (areaData) {
		    var value = areaData.value.split('-')[0];
		    var name = areaData.value.split('-')[1];
		    $('#countyInp').val(name);
		    $('#countyCode').val(value);
		    $('#townInp').val('');
			
			layer.load('1');
			_this.getTownData(value,'selTown');
			$('.town').html('<option value="">请选择乡/镇</option>').val('');
			$('#schSel').html('<option value="">请选择学校</option>').val('');
			$('#gradeSel').val('').html('<option value="">请选择年级</option>');
			$('#classSel').val('').html('<option value="">请选择班级</option>');
			$('#stuSel').val('').html('<option value="">请选择学生</option>');
			$('#schInp').val(0);
			$('#gradeInp').val('');
			$('#classInp').val(0);
			$('#stuInp').val(0);
			
			form.render();
			
			if(currPage == 'qfRepPage'){
				adminAddress.loadQfData();
			}else if(currPage == 'xxTjPage'){
	    		adminAddress.loadXxTjData();
	    	}
		});
		
		//选择乡镇
		form.on('select(town)', function (data) {
			var value = data.value.split('-')[0];
			var townVal = data.value.split('-')[1];
			$('#townCode').val(value);
			$('#townInp').val(townVal);
			
			//$('#schTypeSel').val('');
			$('#schSel').html('<option value="">请选择学校</option>').val('');
			$('#gradeSel').val('').html('<option value="">请选择年级</option>');
			$('#classSel').val('').html('<option value="">请选择班级</option>');
			$('#stuSel').val('').html('<option value="">请选择学生</option>');
			$('#schInp').val(0);
			$('#gradeInp').val('');
			$('#classInp').val(0);
			$('#stuInp').val(0);
			adminAddress.getSchoolData();
			if(currPage == 'qfRepPage'){
				adminAddress.loadQfData();
			}else if(currPage == 'xxTjPage'){
	    		adminAddress.loadXxTjData();
	    	}
			form.render();
		});
    };
    //加载乡镇数据根据countyCode
    Address.prototype.getTownData = function(value,opt){
    	$.ajax({
			url: '/baseInfo.do?action=getSpecTownData',
			dataType: 'json',
			data:{countyCode:value},
			success: function(json) {
				layer.closeAll('loading');
				if(json.result == 'success'){
					var townList = json.townList;
					var townHtml = '<option value="">请选择乡/镇</option>';
					for(var i=0;i<townList.length;i++){
						if(opt == 'selTown'){
							townHtml += '<option value="'+ townList[i].townCode +'-' + townList[i].townName + '">' + townList[i].townName + '</option>';
						}else{
							if(townVal == townList[i].townName){
								townHtml += '<option value="'+ townList[i].townCode +'-' + townList[i].townName + '" selected>' + townList[i].townName + '</option>';
							}else{
								townHtml += '<option value="'+ townList[i].townCode +'-' + townList[i].townName + '">' + townList[i].townName + '</option>';
							}
						}
					}
					$("select[name=town]").html(townHtml).removeAttr("disabled");
					form.render();
				}else if(json.result == 'noInfo'){
					layer.msg('暂无乡/镇');
				}
			}
		});
    };
    
    //加载学生学习统计
	Address.prototype.loadXxTjData = function(){
		layer.load('1');
		var stDate = $('#stDate').val(),
			edDate = $('#edDate').val(),
			cityInp = $('#cityInp').val(),
			countyInp = $('#countyInp').val(),
			townInp = $('#townInp').val(),
			schInp = $('#schInp').val(),
			gradeInp = $('#gradeInp').val(),
			classInp = $('#classInp').val(),
			stuIdInp = $('#stuInp').val(),
			subIdInp = $('#subIdInp').val(),
			stuNameInp = $('#stuNameInp').val();
		//prov,city,county,town,schoolType,schoolId,gradeName,classId,stuId(可不传),subId,sDate,eDate,userId,roleId
		var currField = {province:provVal,city:cityInp,county:countyInp,town:townInp,schoolType:schTypeVal,
				schoolId:schInp,gradeName:gradeInp,classId:classInp,subId:subIdInp,stuId:stuIdInp,sDate:stDate,eDate:edDate,stuName:stuNameInp};
		var field = {province:escape(provVal),city:escape(cityInp),county:escape(countyInp),town:escape(townInp),schoolType:schTypeVal,
					schoolId:schInp,gradeName:gradeInp,classId:classInp,subId:subIdInp,stuId:stuIdInp,sDate:stDate,eDate:edDate,stuName:escape(stuNameInp)};
		console.log(currField)
		console.log(field)
		table.render({
			elem: '#xxTjTable',
			height: 'full-260',
			url :'/studyRecord.do?action=getStuStudyLog',
			method : 'post',
			where:field,
			page : true,
			even : true,
			limit : 20,
			limits:[10,20,30,40],
			text: {none : '暂无相关数据'},
			cols : [[
				{field : '', fixed:'left', title: '序号', type:'numbers', align:'center'},
				{field : 'stuName',fixed:'left', title: '学生', width:'100', align:'center' },
				{field : 'stuSex', title: '性别',width:'80',align:'center',templet:function(d){
					if(d.stuSex == '男'){
						return '<span class="maleCol">男</span>';
					}else{
						return '<span class="femaleCol">女</span>';
					}
				}},
				{field : 'schoolTypeChi', title: '学段',width:'80',align:'center'},
				{field : 'schoolName', title: '学校名称',width:'200',align:'center'},
				{field : 'gradeName', title: '年级',width:'100',align:'center'},
				{field : 'className', title: '班级',width:'80',align:'center'},
				{field : 'allStudyNumber', title: '共学习知识点(个)',width:'150',align:'center'},
				{field : 'noSuccStudyNumber', title: '未完成知识点(个)',width:'150',align:'center'},
				{field : 'completeRate', title: '完成率',width:'100',align:'center'},
				{field : 'prov', title: '省',width:'150',align:'center'},
				{field : 'city', title: '市',width:'150',align:'center'},
				{field : 'county', title: '县/区',width:'150',align:'center'},
				{field : 'town', title: '乡/镇',width:'150',align:'center'}
				
			]],
			done : function(res, curr, count){
			//	console.log(res)
				layer.closeAll('loading');
			}
		});
	}
    
    
    var adminAddress = new Address();
    exports("adminAddress",function(){
    	adminAddress.provinces();
    	if(currPage == 'qfRepPage'){
    		adminAddress.loadQfData();
    	}else if(currPage == 'xxTjPage'){
    		adminAddress.loadXxTjData();
    	}
    	adminAddress.createSubData();
    	if(currRoleName == 'town'){//默认进来学段为0 加载当前镇上的全部学段的全部学校
    		adminAddress.getSchoolData();
    	}else if(currRoleName == 'schoolType'){
    		adminAddress.rednerSchTypeHtml(schoolList);
    		adminAddress.createGradeHtml(schTypeVal,schoolList[0].yearSystem);
    	}else if(currRoleName == 'school'){
    		adminAddress.createGradeHtml(schTypeVal,yearSystem);
    	}else if(currRoleName == 'grade'){
    		adminAddress.createClassData();
    		//adminAddress.createClasses();
    	}
    	
    });
})