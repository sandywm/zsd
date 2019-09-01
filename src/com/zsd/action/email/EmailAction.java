/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.zsd.action.email;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.zsd.action.base.Transcode;
import com.zsd.factory.AppFactory;
import com.zsd.module.Email;
import com.zsd.service.EmailManager;
import com.zsd.tools.CommonTools;
import com.zsd.util.Constants;

/** 
 * MyEclipse Struts
 * Creation date: 08-31-2019
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class EmailAction extends DispatchAction {
	
	/**
	 * 导向邮件首页
	 * @author wm
	 * @date 2019-8-31 上午10:27:25
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward goEmailPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return mapping.findForward("emailPage");
	}
	
	/**
	 * 根据条件获取邮件列表
	 * @author wm
	 * @date 2019-8-31 上午10:27:49
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward getEmailData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		EmailManager em = (EmailManager) AppFactory.instance(null).getApp(Constants.WEB_EMAIL_INFO);
		Integer currUserId = CommonTools.getLoginUserId(request);
		String title = Transcode.unescape_new1("title", request);
		String sDate = CommonTools.getFinalStr("sDate", request);
		String eDate = CommonTools.getFinalStr("eDate", request);
		String emailType = CommonTools.getFinalStr("emailType", request);
		String cilentInfo = CommonTools.getCilentInfo_new(request);
		Integer pageNo = CommonTools.getFinalInteger("page", request);
		Integer pageSize = CommonTools.getFinalInteger("limit", request);
		String msg = "暂无记录";
		Map<String,Object> map = new HashMap<String,Object>();
		if(cilentInfo.equals("pc")){
			Integer count = em.getCountByOpt(currUserId, title, sDate, eDate, emailType);
			if(count > 0){
				List<Email> eList = em.listPageInfoByOpt(currUserId, title, sDate, eDate, emailType, pageNo, pageSize);
				if(eList.size() > 0){
					List<Object> list_d = new ArrayList<Object>();
					msg = "success";
					for(Email email : eList){
						Map<String,Object> map_d = new HashMap<String,Object>();
						map_d.put("emailId", email.getId());
						map_d.put("title", email.getEmailTitle());
						map_d.put("content", email.getEmailTitle());
						map_d.put("emailType", email.getEmailType());
						map_d.put("sendUserName", email.getUserBySendUserId().getRealName());
						list_d.add(map_d);
					}
					map.put("data", list_d);
					map.put("count", count);
					map.put("code", 0);
				}
			}
		}else{//手机端
			List<Email> eList = em.listPageInfoByOpt(currUserId, title, sDate, eDate, emailType, pageNo, pageSize);
			if(eList.size() > 0){
				List<Object> list_d = new ArrayList<Object>();
				msg = "success";
				for(Email email : eList){
					Map<String,Object> map_d = new HashMap<String,Object>();
					map_d.put("emailId", email.getId());
					map_d.put("title", email.getEmailTitle());
					map_d.put("content", email.getEmailTitle());
					map_d.put("emailType", email.getEmailType());
					map_d.put("sendUserName", email.getUserBySendUserId().getRealName());
					list_d.add(map_d);
				}
				map.put("data", list_d);
			}
		}
		map.put("msg", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 批量删除邮件
	 * @author wm
	 * @date 2019-8-31 上午10:43:26
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delBatchEmail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		EmailManager em = (EmailManager) AppFactory.instance(null).getApp(Constants.WEB_EMAIL_INFO);
		Integer currUserId = CommonTools.getLoginUserId(request);
		String emailIdStr = CommonTools.getFinalStr("emailId", request);
		String msg = "error";
		Map<String,Object> map = new HashMap<String,Object>();
		if(currUserId > 0 && !emailIdStr.equals("")){
			em.delBatchInfoByIdStr(emailIdStr);
			msg = "success";
		}
		map.put("msg", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
}