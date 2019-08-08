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
    }
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
            form.render();
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
		});
		
		//选择乡镇
		form.on('select(town)', function (data) {
			var value = data.value.split('-')[0];
			var townVal = data.value.split('-')[1];
			$('#townCode').val(value);
			$('#townInp').val(townVal);
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
    
    var address = new Address();
    exports("address",function(){
        address.provinces();
    });
})