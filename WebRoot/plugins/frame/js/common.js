/**
 * @Description: 基础配置
 * @author: hlf
 */
var noGradInfo = '';//根据科目联动年级时用到 存在年级 清空 不存在有值 noGradInfo
//自定义模块
layui.define(['form'],function(exports){
	var $ = layui.jquery,form=layui.form;
    var obj = {
    	getId : function(id){
    		return document.getElementById(id);
    	},
    	//通用验证码更换	
        vercode : function(){        	
        	var obj = this.getId("sessCode");
			obj.src = "authImg?code="+Math.random()+100;
		}
    };
    //输出接口
    exports('common', obj);
});
