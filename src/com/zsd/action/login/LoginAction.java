/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.zsd.action.login;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.zsd.factory.AppFactory;
import com.zsd.module.User;
import com.zsd.service.UserManager;
import com.zsd.tools.CommonTools;
import com.zsd.tools.CurrentTime;
import com.zsd.tools.MD5;
import com.zsd.util.Constants;

/** 
 * MyEclipse Struts
 * Creation date: 04-24-2019
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class LoginAction extends DispatchAction {
	
	/**
	 * 登录动作
	 * @description
	 * @author Administrator
	 * @date 2019-4-24 下午04:19:09
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 登出系统
	 * @description
	 * @author Administrator
	 * @date 2019-4-24 下午04:19:21
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward loginOut(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession(false).invalidate();
		return mapping.findForward("loginOut");
	}
	
	/**
	 * 导向注册页面
	 * @description
	 * @author Administrator
	 * @date 2019-4-24 下午04:20:12
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goSignPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return mapping.findForward("signPage");
	}
	/**
	 * 用户登录
	 * @author zong
	 * @date  2019-4-29 下午04:21:29
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward userLogin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserManager uManager = (UserManager) AppFactory.instance(null).getApp(Constants.WEB_USER_INFO);
		MD5 md5 = new MD5();
		HttpSession session = request.getSession(false);
		Map<String,Object> map = new HashMap<String,Object>();
		String account =CommonTools.getFinalStr("account",request);
		String password=md5.calcMD5(CommonTools.getFinalStr("password",request));
		String msg = "error";
		if(account!=""&& password!=""){
			boolean uFlag = uManager.userLogin(account, password);
			if(uFlag){
				//登录成功
				String currdate = CurrentTime.getCurrentTime();
				List<User> uList = uManager.listInfoByAccount(account);
				Integer uid = uList.get(0).getId();
				String  userAcc = uList.get(0).getUserAccount();
				//判断用户账号有效状态
				Integer status = uList.get(0).getAccountStatus();
				if(status.equals(1)){//状态 0:无效,1:有效
					Integer loginStatus = uList.get(0).getLoginStatus();//每次登陆，loginStatus自动加1，满50时恢复0状态
					if(loginStatus < 50){
						loginStatus++;
					}else{
						loginStatus = 0;
					}
					//修改用户的登录IP、登录时间、登录次数
					uManager.updateUserLogin(uid, currdate, CommonTools.getIpAddress(request), uList.get(0).getLoginTimes() + 1, loginStatus);
					session.setAttribute("userId",uid);
					session.setAttribute("userAcc",userAcc);
					msg = "success";
				}else{//账号无效
					msg = "lock";
				}
				msg = "success";
				
			}else{
				msg = "fail";
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
}