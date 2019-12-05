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
function zsd_toast(msg,duration){
    duration=isNaN(duration)?3000:duration;
    var m = document.createElement('div');
    m.className = 'layui-anim layui-anim-scale';
    m.innerHTML = msg;
    m.style.cssText="max-width:60%;min-width: 120px;padding:0 14px;height: 45px;color: rgb(255, 255, 255);line-height: 45px;text-align: center;border-radius: 4px;position: fixed;top: 50%;left: 42%;transform: translate(-50%, -50%);z-index: 999999;background: rgba(0, 0, 0,.7);font-size: 14px;";
    document.body.appendChild(m);
    setTimeout(function() {
      var d = 0.5;
      m.style.webkitTransition = '-webkit-transform ' + d + 's ease-in, opacity ' + d + 's ease-in';
      m.style.opacity = '0';
      setTimeout(function() { document.body.removeChild(m) }, d * 1000);
    }, duration);
  }