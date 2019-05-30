/**
 * @Description: 上传通用
 * @author: hlf
 */
var errorTypeFlag = false;//判断当前上传的文件类型是否是正确的
layui.define(['element','jquery','uploadPro','form'],function(exports){
	var element = layui.element,
		upload = layui.uploadPro,form = layui.form;
	var obj = {
		uploadFiles : function(url,fileType,opts){
			//addEditZlOpts batchImp_tzs zlTaskOpts zlTaskOpts_bz zlTaskOpts_cus batchImp_fee
			//this.data.globalOpts = opts;
			var imageListView = $('#upLoadFileList')
			,_this = this
			 //,alreadyUploadFiles={}//记录已经上传成功的文件相对路径（后台返回）
			,uploadListIns=upload.render({
				  elem : '#selFileBtn'
				  ,url: url//这里设置自己的上传接口
				  ,accept: 'file'
				  ,exts : fileType
				  ,multiple: true
				  ,auto: false
				  ,number: 1
				  ,bindAction: '#upListAction' 
				 	,xhr:xhrOnProgress
				 	,progress: function(index,value){//上传进度回调 value进度值
				 	    element.progress('image'+index, value+'%');//设置页面进度条
				 	}
				  ,errorMsg: function(content){
				  	layer.msg(content, {icon: 2, shift: 6});
				  }
				  ,before : function(obj){
					  var that = this,isFmxxTmpFlag = true;;
					  //假如用户分了两次或者多次上传若个干文件，那么，用已经上传的文件和当前已经选中的文件的数量相加和maxNum做比较
					  $('#upListAction').hide();
					  var hasUpDoneLen = $('.hasUpDone').length;
					  var noUpDoneLen = $('.noUpDone').length;
					  if((hasUpDoneLen + noUpDoneLen) > 1){
						  that.errorMsg('最多只能上传1个文件');
						  return false;
					  }else{
						  /*if(opts == 'addEditZsjj'){
							  $('.deleteBtn_sel').show();
						  }else{
							  $('.deleteBtn_sel').hide();
						  }*/
					  }
				  }
				  ,choose: function(obj){
				  	  var that = this;
				      obj.preview(function(index, file, result){ 
				      	var files = that.files = obj.pushFile(); //将每次选择的文件追加到文件队列
				      	var fileNames = file.name.split('.')[0]; 
						var fileType = file.name.substr(file.name.lastIndexOf('.'));
						var size = file.size;
				        if(fileNames.length > 50){
							layer.msg('上传的文件名不能超过50个字符',{icon:5,anim:6,time:1000});
		    				return;
						}
						if(fileType == '.jpg' || fileType == '.png' || fileType == '.bmp' || fileType == '.gif' || fileType == '.jpeg'){
			    			//如果是图片，单个图片不能大于5M
			    			if(size > (5 * 1024 * 1024)){
			    				layer.msg('上传的图片文件不能大于5M',{icon:5,anim:6,time:1000});
			    				return;
			    			}
			    		}else{
			    			//如果是其他文件类类型，单个文件不能大于10M
			    			if(opts == 'addEditZsjj'){
			    				if(size > (50 * 1024 * 1024)){
				    				layer.msg('上传的视频文件不能大于50M',{icon:5,anim:6,time:2000});
				    				return;
				    			}
			    			}
			    		}
				       var tr = $(['<tr class="hasSelTr noUpDone" id="upload-'+ index +'">',
						'<td style="max-width:260px;">'+ file.name +'</td>',
						'<td>'+ fileType.substr(1) +'</td>',
						//opts == 'addEditZlOpts' ? '<td><div style="max-width:130px;" class="ajFjTypeTxt layui-form">'+ zlAjTypeStr +'</div></td>' : '',
						//opts == 'zlTaskOpts_bz' ? '<td style="max-width:110px;"><div class="zlTypeTxt layui-form">'+ zlBzTypeStr +'</div></td>' : '',
						//opts == 'zlTaskOpts_cus' ? '<td style="max-width:110px;"><div class="cusTypeTxt layui-form">' + zlCusTypeStr + '</div></td>' : '',
						//'<td><div class="zlTypeTxt layui-form">'+ zlTypeTxt +'</div></td>',
						'<td>'+ (file.size/1014).toFixed(1) +'kb</td>',
						'<td>等待上传</td>',
						'<td style="width:120px;">',
				     	'<div class="layui-progress layui-progress-big layui-progress-radius-fix" lay-showpercent="true" lay-filter="image'+index+'">',
				     	  '<div class="layui-progress-bar progressBarBg" lay-percent="0%">',
				     	    '<span class="layui-progress-text">0%</span>',
				     	  '</div>',
				     	'</div>',
				     	'</td>',
						'<td>',
						'<button class="layui-btn layui-btn-xs reloadBtn reloadBtn_sel layui-hide">重传</button>',
						'<input class="uploadInpHid" name="notUpInp" type="hidden"/>',
						'<button class="layui-btn layui-btn-xs layui-btn-danger deleteBtn deleteBtn_sel">删除</button>',
						'</td>',
						'</tr>'].join(''));
					    var noUpDoneLen = $('.noUpDone').length;
					    var hasUpDoneLen = $('.hasUpDone').length;
				     	//删除
				        tr.find('.deleteBtn_sel').on('click', function(){
				        	delete files[index]; //删除对应的文件
				        	if((hasUpDoneLen + noUpDoneLen) == 0){
				        		$('#fujianInp').val('');
				        	}
				        	$('#upListAction').hide();
				            tr.remove();		
				            uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
				        });
				       //单个重传
				       tr.find('.reloadBtn_sel').on('click', function(){
				         obj.upload(index, file);
				       });
				       imageListView.append(tr);
				       $('#upListAction').show();
				       form.render();
				  	});
				  }
				  ,done: function(res, index, upload){
					layer.closeAll('loading');
				    if(res.msg == 'success'){ //上传成功
				      var tr = imageListView.find('tr#upload-'+ index)
				      ,tds = tr.children(),noUpDoneLen = $('.noUpDone').length,hasUpDoneLen = $('.hasUpDone').length;
				      tr.removeClass('noUpDone').addClass("hasUpDone");
				      //alreadyUploadFiles[index]=res.data && res.data[0];//缓存已上传的文件
				      $('#upListAction').hide();
				      if(opts == 'addEditZsjj'){//批量导入通知书 上传成功后隐藏/移除 删除按钮
				    	  tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
					      tds.eq(5).find('.uploadInpHid').attr('name','hasUpSuccInp');
					      tds.eq(5).find('.uploadInpHid').val(res.data);
					      $('#zsjjInp').val(res.data);
						  //$('.hasSelWords').html('已选择' + (noUpDoneLen + hasUpDoneLen) + '个文件, '+ (noUpDoneLen-1) +'个未上传，' + (hasUpDoneLen+1) + "已上传");
				      }
				      return delete this.files[index]; //删除文件队列已经上传成功的文件
				    }else if(res.msg == 'suffixError'){
						layer.msg('上传的视频文件格式必须为flv或mp4',{icon:5,anim:6,time:1500});
						errorTypeFlag = true;
					}
				    this.error(index, upload);
				  }
				  ,error: function(index, upload){
				    var tr = imageListView.find('tr#upload-'+ index)
				    ,tds = tr.children();	
				    if(opts == 'addEditZsjj'){
				    	tds.eq(2).html('<span style="color: #ff5722;">上传失败</span>');
					    tds.eq(3).find('.layui-progress-bar').css('background-color','#ff5722');
					    tds.eq(4).find('.reloadBtn_sel').removeClass('layui-hide'); //显示重传
				    }
				  }
			});
		}
	};
	//输出接口
    exports('upLoadFiles', obj);
});