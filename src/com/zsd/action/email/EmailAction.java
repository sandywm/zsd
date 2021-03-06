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
	 * 获取指定用户下未读邮件记录条数
	 * @author wm
	 * @date 2019-10-2 上午09:14:43
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward getUnReadCount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		EmailManager em = (EmailManager) AppFactory.instance(null).getApp(Constants.WEB_EMAIL_INFO);
		Integer currUserId = CommonTools.getLoginUserId(request);
		String msg = "error";
		Map<String,Object> map = new HashMap<String,Object>();
		Integer count = 0;
		if(currUserId > 0){
			count = em.getUnReadCount(currUserId);
			msg = "success";
		}
		map.put("result", msg);
		if(msg.equals("success")){
			map.put("unReadeCount", count);
		}
		CommonTools.getJsonPkg(map, response);
		return null;
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
		String msg = "noInfo";
		Map<String,Object> map = new HashMap<String,Object>();
		List<Email> eList = em.listPageInfoByOpt(currUserId, title, sDate, eDate, emailType, pageNo, pageSize);
		if(eList.size() > 0){
			List<Object> list_d = new ArrayList<Object>();
			msg = "success";
			for(Email email : eList){
				Map<String,Object> map_d = new HashMap<String,Object>();
				map_d.put("emailId", email.getId());
				map_d.put("title", email.getEmailTitle());
				map_d.put("content", email.getEmailContent());
				map_d.put("sendDate", email.getSendTime());
				map_d.put("emailType", email.getEmailType());
				map_d.put("sendUserName", email.getUserBySendUserId().getRealName());
				map_d.put("readStatus", email.getReadStatus());
				list_d.add(map_d);
			}
			map.put("data", list_d);
			if(cilentInfo.equals("pc") || cilentInfo.indexOf("Web") > 0){
				Integer count = em.getCountByOpt(currUserId, title, sDate, eDate, emailType);
				map.put("count", count);
				map.put("code", 0);
			}
		}else{
			if(cilentInfo.equals("pc")){
				msg = "暂无记录";
			}
		}
		map.put("msg", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 获取邮件详情
	 * @author wm
	 * @date 2019-10-3 上午08:55:21
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getEmailDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		EmailManager em = (EmailManager) AppFactory.instance(null).getApp(Constants.WEB_EMAIL_INFO);
		Integer userId = CommonTools.getLoginUserId(request);
		Integer emailId = CommonTools.getFinalInteger("emailId", request);
		String msg = "error";
		Map<String,Object> map = new HashMap<String,Object>();
		if(userId > 0 && emailId > 0){
			Email email = em.getEntityById(emailId);
			if(email != null){
				if(email.getUserByToUserId().getId().equals(userId) || email.getUserBySendUserId().getId().equals(userId)){
					//只有涉及到邮件双方人员才能查看
					msg = "success";
					map.put("emailId", email.getId());
					map.put("title", email.getEmailTitle());
					map.put("content", email.getEmailContent());
					map.put("sendDate", email.getSendTime());
					map.put("emailType", email.getEmailType());
					map.put("sendUserName", email.getUserBySendUserId().getRealName());
					if(email.getReadStatus().equals(0)){
						em.updateBatchInfoByIdStr(String.valueOf(emailId), userId);
					}
				}
			}
		}
		map.put("result", msg);
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
			em.delBatchInfoByIdStr(emailIdStr,currUserId);
			msg = "success";
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 批量设置邮件已读标识
	 * @author wm
	 * @date 2019-9-11 下午04:50:56
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateBatchEmail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		EmailManager em = (EmailManager) AppFactory.instance(null).getApp(Constants.WEB_EMAIL_INFO);
		Integer currUserId = CommonTools.getLoginUserId(request);
		String emailIdStr = CommonTools.getFinalStr("emailId", request);
		String msg = "error";
		Map<String,Object> map = new HashMap<String,Object>();
		if(currUserId > 0 && !emailIdStr.equals("")){
			em.updateBatchInfoByIdStr(emailIdStr,currUserId);
			msg = "success";
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
}