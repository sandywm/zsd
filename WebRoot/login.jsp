<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html> 
<html>
  <head>
    <title>专利管理系统--用户登录</title>
	<link href="/plugins/layui/css/layui.css" rel="stylesheet" type="text/css"/>
	<link href="/plugins/layui/css/modules/layui-icon-extend/iconfont.css" rel="stylesheet" type="text/css"/>
	<link href="/plugins/pace/pace-theme-flash.min.css" rel="stylesheet" type="text/css"/>
	<link href="/css/login.css" rel="stylesheet" type="text/css"/>
	<script src="/plugins/pace/pace.min.js"></script>
  </head>
  
  <body>   
  	<div class="loginBg"></div>
  	<div class="loginWrap">
		<div class="innerWrap">
			<div class="leftLoginDec">
				<img src="images/loginBg.jpg" alt="知识典登录"/>
			</div>
			<div class="rightLoginForm">
				<strong>知识典登录</strong>
				<div class="formWrap">
					<div class="formBox">
						<input type="text" id="account" placeholder="请输入账号"/>
						<span class="iconBox"><i class="iconfont layui-extend-account"></i></span>
					</div>
					<div class="formBox">
						<input type="password" id="password" placeholder="请输入密码"/>
						<span class="iconBox"><i class="iconfont layui-extend-mima"></i></span>
					</div>
					<div class="formBox vercodeBox">
						<input type="text" id="inputCode" placeholder="请输入验证码"/>
						<span class="iconBox">
							<img id="sessCode" src="authImg" title="看不清换一张" alt="请输入验证码"/>
						</span>
					</div>
					<div class="loginBtnWrap layui-clear">
						<label for="rememberInp">
							<span><i class="layui-icon layui-icon-ok"></i></span>
							<input id="rememberInp" type="checkbox" />
							<p>记住密码</p>
						</label>
						<a id="loginBtn" href="javascript:void(0)">立即登录</a>
					</div>
					<div class="findPassWrap">
						<a href="javascript:void(0)">忘记密码?</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="/plugins/jquery/jquery.min.js"></script>
	<script src="/plugins/jquery/jquery.cookie.js"></script>
	<script src="/plugins/jquery/jquery.base64.js"></script>
	<script src="/plugins/layui/layui.js"></script>
	<script type="text/javascript">
		layui.config({
			base: '/plugins/frame/js/'
		}).extend({ //设定组件别名
		    common:  'common'// 表示模块文件的名字
		}).use(['layer','form','common'],function(){
			var layer = layui.layer,
				form = layui.form,
				common = layui.common;
			$("#loginBtn").on('click',function(){
				login();
			});
			$("#sessCode").on('click',function(){
				common.vercode();
			});
			$(function(){
				$("#account").on("keypress",function(){
					enterPress(event);
				});
				$("#password").on("keypress",function(){
					enterPress(event);
				});
				$("#inputCode").on("keypress",function(){
					enterPress(event);
				});
				getCookie();
			});
			$("#rememberInp").on('click',function(){
				var checkStatus = $(this).prop("checked");
				if(checkStatus){
					$("#rememberInp").parent().addClass('active');
				}else{
					$("#rememberInp").parent().removeClass('active');
				}
			});
			function login(){
				var account = $.trim($("#account").val());
				var password = $.trim($("#password").val());
				var vCode = $.trim($("#inputCode").val());
				var checkStatus = $("#rememberInp").prop("checked");
				if(account == ""){
					layer.msg("账号不能为空");
					$("#account").focus();
				}else if(password == ""){
					layer.msg("密码不能为空");
					$("#password").focus();
				}else if(vCode == ""){
					layer.msg("验证码不能为空");
					$("#inputCode").focus();
				}else{
					layer.load();
					$.ajax({
				        type:"post",
				        async:false,
				        dataType:"json",
				        url:"login.do?action=userLogin",
				        data:{account:account,password:password,vercode:vCode},
				        success:function (json){
				        	layer.closeAll('loading');
				        	if(json.result == 'success'){
				        		if(checkStatus){
				        			//添加cookie 
				        			setCookie(); 
				        		}else{
				        			var account = $.cookie("account"); //获取cookie中的用户名 
				    			    var pwd = $.cookie("pwd"); //获取cookie中的登陆密码 
				    			    if(account && pwd){
				    			    	$.cookie("account",'');
				    			    	$.cookie("pwd", ''); 
				    			    }
				        		}
				        		window.location.href = "hw.do?action=goTeaQuePage";
				        	}else if(json.result == 'fail'){
				        		layer.msg("账号密码错误");
				        	}else if(json.result == 'lock'){
				        		layer.msg("该账号无效,已被锁定");
				        	}else if(json.result == 'error'){
				        		layer.msg("登录异常");
				        	}
				        }
				    });
				}
			}
			function setCookie(){
				var account = $("#account").val(); //获取用户名信息 
			    var pwd = $("#password").val(); //获取登陆密码信息 
			    var checked = $("#rememberInp").prop("checked");//获取“是否记住密码”复选框
			    if(checked){ //判断是否选中了“记住密码”复选框 
			    	$.cookie("account",account);//调用jquery.cookie.js中的方法设置cookie中的用户名 
			     	$.cookie("pwd",$.base64.encode(pwd));//调用jquery.cookie.js中的方法设置cookie中的登陆密码，并使用base64（jquery.base64.js）进行加密 
			    }
			}
			function getCookie(){
				var account = $.cookie("account"); //获取cookie中的用户名 
			    var pwd = $.cookie("pwd"); //获取cookie中的登陆密码 
			    if(account && pwd){//密码存在的话把“记住用户名和密码”复选框勾选住 
			    	$("#account").val(account); 
			    	$("#password").val($.base64.decode(pwd)); 
			    	$("#rememberInp").prop("checked",true);
			    	$("#rememberInp").parent().addClass('active');
			    } 
			}
			//回车事件
			function enterPress(e){
				var e = e || window.event;
				if(e.keyCode == 13){
					login();
				}
			}
		});

		
	</script>
  </body>
  
</html>
