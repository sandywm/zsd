//返回顶部
function backTop(obj){
	var oBackTop=document.getElementById(obj);
	var timer=null;
	var bSys=true;
	window.onscroll=function(){
		var scrollTop=document.documentElement.scrollTop||document.body.scrollTop;
		/*if(scrollTop>200){
			oBackTop.style.display="block";
		}else{
			if(scrollTop==0){
				oBackTop.style.display="none";
			}
		}*/
		if(!bSys){
			clearInterval(timer);
		}
		bSys=false;
	};	
	oBackTop.onclick=function()
	{
		timer=setInterval(function(){
			var scrollTop=document.documentElement.scrollTop||document.body.scrollTop;
			if(scrollTop==0)
			{
				clearInterval(timer);
			}
			bSys=true;
			var speed=(0-scrollTop)/6;
			speed=speed>0?Math.ceil(speed):Math.floor(speed);
			document.documentElement.scrollTop=document.body.scrollTop=speed+scrollTop;
		},30);
	};
}