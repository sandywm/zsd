var switchToImg = {
	//由科目->图标
	switchIconBySubName : function(subName){
		var subIconClsName = '';
		if(subName == '语文'){
			subIconClsName = 'layui-extend-yuwen';
		}else if(subName == '数学'){
			subIconClsName = 'layui-extend-shuxue';
		}else if(subName == '英语'){
			subIconClsName = 'layui-extend-yingyu';
		}else if(subName == '物理'){
			subIconClsName = 'layui-extend-wuli';
		}else if(subName == '化学'){
			subIconClsName = 'layui-extend-huaxue';
		}else if(subName == '地理'){
			subIconClsName = 'layui-extend-dili';
		}else if(subName == '生物'){
			subIconClsName = 'layui-extend-shengwu';
		}else if(subName == '历史'){
			subIconClsName = 'layui-extend-lishi';
		}else if(subName == '生命科学'){
			subIconClsName = 'layui-extend-shengmingkexue';
		}else if(subName == '科学'){
			subIconClsName = 'layui-extend-kexue';
		}
		return subIconClsName;
	}
};