var provVal = '',cityVal='',countyVal='',townVal='',provIndex=0,cityIndex=0,currTownVal=0;//用于编辑时匹配当前省市index索引
layui.define(["form","jquery"],function(exports){
    var form = layui.form,
    $ = layui.jquery,
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
        })
        
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
			$('#schInp').val(0);
			$('#gradeInp').val(0);
			$('#classInp').val(0);
			adminAddress.getSchoolData();
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
        	if(gradeInp == '0'){
        		layer.msg('请选择年级');
        		return;
        	}
		});
    };
    Address.prototype.createClasses  = function(list){
    	console.log(list)
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
    		$('#gradeInp').val(0);
    	}else{
    		$('#gradeInp').val(data.value);
    	}
    	adminAddress.createClassData();
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
			$('#gradeInp').val(0);
			$('#classInp').val(0);
			$('#stuInp').val(0);
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
				$('#gradeInp').val(0);
				$('#classInp').val(0);
				$('#stuInp').val(0);
				form.render();
			}
		});
	};
    
    //生成年级
    Address.prototype.createGradeData = function(schTypeVal,yearSystem){
    	$('#gradeInp').val(0);
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
	Address.prototype.createClassData = function(){
		layer.load('1');
		var field = {schoolId:$('#schInp').val(),gradeName:escape($('#gradeInp').val())};
		$('#classInp').val(0);
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
					layer.msg('暂无班级');
				}
			},  
			error:function(xhr,type,errorThrown){ 
				console.log(type);  
			}  
		});
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
            $('#townCode').val('');
			
			layer.load('1');
			_this.getTownData(value,'selTown');
			
			$('#schSel').html('<option value="">请选择学校</option>').val('');
			$('#gradeSel').val('').html('<option value="">请选择年级</option>');
			$('#classSel').val('').html('<option value="">请选择班级</option>');
			$('#stuSel').val('').html('<option value="">请选择学生</option>');
			$('#schInp').val(0);
			$('#gradeInp').val(0);
			$('#classInp').val(0);
			$('#stuInp').val(0);
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
			$('#gradeInp').val(0);
			$('#classInp').val(0);
			$('#stuInp').val(0);
			adminAddress.getSchoolData();
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
					layer.msg('暂无乡/镇',{icon:5,anim:6,time:2000});
				}
				
			}
		});
    };
    
    var adminAddress = new Address();
    exports("adminAddress",function(){
    	adminAddress.provinces();
    	if(currRoleName == 'town'){//默认进来学段为0 加载当前镇上的全部学段的全部学校
    		adminAddress.getSchoolData();
    	}else if(currRoleName == 'schoolType'){
    		adminAddress.rednerSchTypeHtml(schoolList);
    		adminAddress.createGradeHtml(schTypeVal,schoolList[0].yearSystem);
    	}else if(currRoleName == 'school'){
    		adminAddress.createGradeHtml(schTypeVal,yearSystem);
    	}else if(currRoleName == 'grade'){
    		//adminAddress.createClasses();
    	}
    	
    });
})