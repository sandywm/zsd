package com.zsd.web;

import com.zsd.factory.AppFactory;
import com.zsd.module.User;
import com.zsd.service.UserManager;
import com.zsd.tools.CommonTools;
import com.zsd.tools.DataBaseSqlVerify;
import com.zsd.util.Constants;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		String requestUrl[] = httpServletRequest.getRequestURI().split(";");
		//攻击检测增加代码---start
		String param =  httpServletRequest.getQueryString();//action动作地址
		String filePath = "e:\\attackReport.txt";
		String oldParam = param;
		if(param == null || param.equals("")){
			
		}else{
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
		//攻击检测增加代码---end
		//客户端信息
		String clientInfo = CommonTools.getCilentInfo_new(httpServletRequest);
		if(clientInfo.indexOf("App") > 0){//手机端不检验
			chain.doFilter(request, response);
		}else{
			String requesturi = requestUrl[0]; 
			// 通过检查session中的变量，过滤请求
			HttpSession session = httpServletRequest.getSession(false);
			Integer loginFlag = -1;
			String loginType = "";
			Integer userId = 0;
			if(session != null){
				//获取用户session中的loginFlag
				loginFlag = (Integer)session.getAttribute(Constants.LOGIN_STATUS);
				//获取用户session中的用户编号
				userId = (Integer)session.getAttribute(Constants.LOGIN_USER_ID);
				if(userId == null){
					userId = 0;
				}
			}
			Integer loginFlag_dataBase = -1;
			Integer accountStatus = 1;
			if(userId.equals(0)){
				if(!requesturi.endsWith("/login.do") 
						&& !requesturi.endsWith("/baseInfo.do")
						&& !requesturi.endsWith("/authImg")
						&& !requesturi.endsWith("jsp")
						&& !requesturi.endsWith("css") 
						&& !requesturi.endsWith("js")
						&& !requesturi.endsWith("png")
						&& !requesturi.endsWith("gif")
						&& !requesturi.endsWith("jpg")
						&& !requesturi.endsWith("jpeg")
						&& !requesturi.endsWith("ico")
						&& !requesturi.endsWith("ttf")
						&& !requesturi.endsWith("json")
						&& !requesturi.endsWith("html")
						&& !requesturi.endsWith(httpServletRequest.getContextPath()+ "/")){
	                String url = "window.top.location.href='login.do?action=loginOut'";
					String authorizeScript = "由于您60分钟内没上线，系统已强制您下线，请重新登录！";
					Ability.PrintAuthorizeScript(url,authorizeScript, httpServletResponse);
	                return;
	            }
				chain.doFilter(request, response);
			}else{
				try {
					UserManager um = (UserManager) AppFactory.instance(null).getApp(Constants.WEB_USER_INFO);
					//获取数据库中指定currentUser的loginFlag
					User user = um.listEntityById(userId).get(0);
					loginFlag_dataBase = user.getLoginStatus();
					accountStatus = user.getAccountStatus();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(!loginFlag.equals(loginFlag_dataBase)){
					session.invalidate();
					String url = "window.location.href='login.do?action=loginOut'";
					String authorizeScript = "该账号已经在别处登录，系统已强制您下线，请重新登录！";
					Ability.PrintAuthorizeScript(url,authorizeScript, httpServletResponse);
				}else if(accountStatus.equals(0)){
					session.invalidate();
					String url = "window.location.href='login.do?action=loginOut'";
					String authorizeScript = "该账号状态为无效，系统已强制您下线，请重新登录！";
					Ability.PrintAuthorizeScript(url,authorizeScript, httpServletResponse);
				}else{
					chain.doFilter(request, response);
				}
			}
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException
	{
		this.filterConfig = filterConfig;
		this.forwardPath = filterConfig.getInitParameter("forwardpath");
	}
}
