package com.zsd.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zsd.factory.AppFactory;
//import com.zsd.model.User;
//import com.zsd.service.UserManager;
import com.zsd.tools.CommonTools;
import com.zsd.tools.DataBaseSqlVerify;
import com.zsd.util.Constants;
@SuppressWarnings("unused")
public class UserLoginFilter implements Filter{
	
	private String forwardPath = null;
	private FilterConfig filterConfig = null;

	public void destroy()
	{
		this.forwardPath = null;
		this.filterConfig = null;
	}

	@SuppressWarnings("null")
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException{
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		String cilentInfo = CommonTools.getCilentInfo_new(httpServletRequest);
		String requestUrl[] = httpServletRequest.getRequestURI().split(";");
		//攻击检测增加代码---start
		String param =  httpServletRequest.getQueryString();//action动作地址
		String filePath = "e:\\attackReport.txt";
		String oldParam = param;
//		UserManager userManager = null;
		if(param == null || param.equals("")){
			
		}else{
			if(param.endsWith("app") || param.endsWith("appInit")){//手机app调用网页处理
				cilentInfo = "App";
			}
			Integer indexNumber = param.indexOf("&");
			if(indexNumber >= 0){
				param = param.substring(indexNumber);
				boolean flag = DataBaseSqlVerify.checkSql(param);
    			if(flag){//sql注入攻击
    				String subject = "检测到sql攻击";
    				CommonTools.sendAttackReport(filePath,subject, httpServletRequest,oldParam);
    				return;
    			}else{//没攻击继续查询xss攻击
    				flag = DataBaseSqlVerify.checkXss(param);
    				if(flag){//存在攻击
    					String subject = "检测到xss攻击";
    					CommonTools.sendAttackReport(filePath,subject, httpServletRequest,oldParam);
    					return;
    				}
    			}
    		}
		}
		
		//System.out.println(httpServletRequest.getHeader("x-requested-with") + ":::::" + httpServletRequest.getRequestURI() + "::::::" + param);
		if(cilentInfo.indexOf("App") >= 0 || cilentInfo.equals("mobileBrowser")){//手机端不检验
//			if(param != null){
//				if(param.endsWith("app")){//如果是手机app调用页面
//					HttpSession session = httpServletRequest.getSession(false);
//					Integer login_status_sess = -2;
//					Integer login_status_dataBase = -1;
//					Integer userId = 0;
//					if(session != null){
//						login_status_sess = CommonTools.getFinalInteger(String.valueOf(session.getAttribute("loginStatus")));
//						userId =  CommonTools.getFinalInteger(String.valueOf(session.getAttribute("userId")));
//						try {
//							userManager = (UserManager)AppFactory.instance(null).getApp(Constants.WEB_USER);
//							List<UserVO> uList = userManager.listUserInfoByID(userId);
//							if(uList.size() > 0){
//								login_status_dataBase = uList.get(0).getLoginStatus();
//								if(login_status_dataBase.equals(login_status_sess)){
//									chain.doFilter(request, response);
//								}else{
//									//判断是否是ajax请求
//									if (httpServletRequest.getHeader("x-requested-with") != null && "XMLHttpRequest".equalsIgnoreCase(httpServletRequest.getHeader("x-requested-with"))) {
//										httpServletResponse.setHeader("loginStatus", "loginOut");
//										chain.doFilter(request, response);
//										return;
//									}else{
//										session.invalidate();
//										String authorizeScript = "该账号已经在别处登录，系统已强制您下线，请重新登录！";
//										Ability.PrintAuthorizeScript2(authorizeScript, httpServletResponse);
//									}
//								}
//							}
//						} catch (Exception e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}else{
//						//判断是否是ajax请求
//						if (httpServletRequest.getHeader("x-requested-with") != null && "XMLHttpRequest".equalsIgnoreCase(httpServletRequest.getHeader("x-requested-with"))) {
//							httpServletResponse.setHeader("loginStatus", "timeOut");
//							chain.doFilter(request, response);
//							return;
//						}else{
//							String authorizeScript = "由于您60分钟内无操作，系统已强制您下线，请重新登录！";
//							Ability.PrintAuthorizeScript2(authorizeScript, httpServletResponse);
//						}
//					}
//				}else{//如果是手机客户端（手机app调用初始页面，无需判定）
//					chain.doFilter(request, response);
//				}
//			}else{
//				chain.doFilter(request, response);
//			}
			
			chain.doFilter(request, response);
		}else{
			//攻击检测增加代码---end
//		    String requesturi = requestUrl[0]; 
//			// 通过检查session中的变量，过滤请求
//			HttpSession session = httpServletRequest.getSession(false);
//			Integer loginFlag = -1;
//			User user = null;
//			if(session != null){
//				//获取用户session中的loginFlag
//				loginFlag = (Integer)session.getAttribute(Constants.LOGIN_STATUS);
//				//获取用户session中的账号
//				user = (User)session.getAttribute(Constants.LOGIN_USER);
//			}
			//获取用户session中的loginFlag
//			Integer loginFlag = (Integer)session.getAttribute(Constants.LOGIN_STATUS);
			//获取用户session中的账号
//			User user = (User)session.getAttribute(Constants.LOGIN_USER);
//			String currentUser = null;
//			if(user != null){
//				currentUser = user.getUsername();
//			}
//			Integer loginFlag_dataBase = -1;
//			if(currentUser != null){
//				try {
//					userManager = (UserManager)AppFactory.instance(null).getApp(Constants.WEB_USER);
//					//获取数据库中指定currentUser的loginFlag
//					loginFlag_dataBase = userManager.getUserLoginStatus(currentUser);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				if(!loginFlag.equals(loginFlag_dataBase)){
//					session.invalidate();
//					String url = "window.top.location.href='login.do?action=loginOut'";
//					String authorizeScript = "该账号已经在别处登录，系统已强制您下线，请重新登录！";
//					Ability.PrintAuthorizeScript(url,authorizeScript, httpServletResponse);
//				}else{
//					chain.doFilter(request, response);
//				}
//			}else{
//				if(!requesturi.endsWith("/forgetPassword.do") 					
//						&& !requesturi.endsWith("/login.do") 
//						&& !requesturi.endsWith("/easyLogin.do")
//						&& !requesturi.endsWith("/wxAuth")
//						&& !requesturi.endsWith("/layIm.do")
//						&& !requesturi.endsWith("/wxRedirect")
//						&& !requesturi.endsWith("/afterlogin.do")
//						&& !requesturi.endsWith("/quickDeal.do")
//						&& !requesturi.endsWith("/wbIndex.do")
//						&& !requesturi.endsWith("/wbRedirect.do")
//						&& !requesturi.endsWith("/index.do")
//						&& !requesturi.endsWith("/authImg")
//						&& !requesturi.endsWith("/StartCaptchaServlet")
//						&& !requesturi.endsWith("/VerifyCheckCodeServlet")
//						&& !requesturi.endsWith("/commonManager.do")
//						&& !requesturi.endsWith("/newsManager.do")
//						&& !requesturi.endsWith("/wxNotify.do")
//						&& !requesturi.endsWith("jsp")
//						&& !requesturi.endsWith("css") 
//						&& !requesturi.endsWith("js")
//						&& !requesturi.endsWith("png")
//						&& !requesturi.endsWith("gif")
//						&& !requesturi.endsWith("jpg")
//						&& !requesturi.endsWith("jpeg")
//						&& !requesturi.endsWith("html")
//						&& !requesturi.endsWith("swf")
//						&& !requesturi.endsWith("ico")
//						&& !requesturi.endsWith("mp4")
//						&& !requesturi.endsWith("apk")
//						&& !requesturi.endsWith("flv")
//						&& !requesturi.endsWith(httpServletRequest.getContextPath()+ "/")){
//	                //System.out.println("Filter启动了作用....");
//	                String url = "window.top.location.href='login.do?action=loginOut'";
//					String authorizeScript = "由于您60分钟内没上线，系统已强制您下线，请重新登录！";
//					Ability.PrintAuthorizeScript(url,authorizeScript, httpServletResponse);
//	                return;
//	            }
//				chain.doFilter(request, response);
//			}
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException
	{
		this.filterConfig = filterConfig;
		this.forwardPath = filterConfig.getInitParameter("forwardpath");
	}
}
