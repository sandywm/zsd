var provVal = '',cityVal='',countyVal='',provIndex=0,cityIndex=0;//用于编辑时匹配当前省市index索引
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
            		proHtml += '<option value="' + data[i].name + '" selected>' + data[i].name + '</option>';
            		provIndex = i;
            	}else{
            		proHtml += '<option value="' + data[i].name + '">' + data[i].name + '</option>';
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
            			cityStr += '<option value="'+ cityData[i].name +'" selected>'+ cityData[i].name +'</option>';
            			cityIndex = i;
            		}else{
            			cityStr += '<option value="'+ cityData[i].name +'">'+ cityData[i].name +'</option>';
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
            			countyStr += '<option value="'+ countyData[i].name +'" selected>'+ countyData[i].name +'</option>';
            		}else{
            			countyStr += '<option value="'+ countyData[i].name +'">'+ countyData[i].name +'</option>';
            		}
            	}
            	$("select[name=area]").html(countyStr);
            	form.render();
            }
            
            //form.render();
            form.on('select(province)', function (proData) {
                $("select[name=area]").html('<option value="">请选择县/区</option>');
                var value = proData.value;
                $('#provInp').val(value);
                $('#cityInp').val('');
                $('#countyInp').val('');
                if (value != '') {
                	that.citys(data[$(this).index() - 1].children);
                } else {
                    $("select[name=city]").html('').attr("disabled", "disabled");
					$("select[name=area]").html('').attr("disabled", "disabled");
					form.render();
                }
            });
        })
    }
   //加载市数据
    Address.prototype.citys = function(citys) {
        var cityHtml = '<option value="">请选择市</option>',that = this;
        for (var i = 0; i < citys.length; i++) {
        	cityHtml += '<option value="' + citys[i].name + '">' + citys[i].name + '</option>';
        }
        $("select[name=city]").html(cityHtml).removeAttr("disabled");
        form.render();
        form.on('select(city)', function (cityData) {
			$("select[name=area]").html('<option value="">请选择县/区</option>');
            var value = cityData.value;
            $('#cityInp').val(value);
            $('#countyInp').val('');
            if (value != '') {
            	that.areas(citys[$(this).index() - 1].children);
            } else {
                $("select[name=area]").html('').attr("disabled", "disabled");
                form.render();
            }
        });
    };
    
    //加载县/区数据
    Address.prototype.areas = function(areas) {
        var areaHtml = '<option value="">请选择县/区</option>';
        for (var i = 0; i < areas.length; i++) {
            areaHtml += '<option value="' + areas[i].name + '">' + areas[i].name + '</option>';
        }
        $("select[name=area]").html(areaHtml).removeAttr("disabled");
        form.render();

		form.on('select(area)', function (areaData) {
		    var value = areaData.value;
		    $('#countyInp').val(value);
			/*layer.load('1');
			$.ajax({
				url: 'http://passer-by.com/data_location/town/' + value + '.json',
				dataType: 'json',
				success: function(res) {
					layer.closeAll('loading');
					var townHtml = '<option value="">请选择乡/镇</option>';
					for(var attr in res){
						townHtml += '<option value="' + attr + '">' + res[attr] + '</option>';
					}
					$("select[name=town]").html(townHtml).removeAttr("disabled");
					form.render();
				}
			});*/
		});
		
    }
	
    var address = new Address();
    exports("address",function(){
        address.provinces();
    });
})