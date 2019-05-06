/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.zsd.action.lore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.zsd.factory.AppFactory;
import com.zsd.module.LoreInfo;
import com.zsd.service.LoreInfoManager;
import com.zsd.tools.CommonTools;
import com.zsd.util.Constants;

/** 
 * MyEclipse Struts
 * Creation date: 05-04-2019
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class LoreAction extends DispatchAction {
	
	/**
	 * 导向知识典管理页面
	 * @author wm
	 * @date 2019-5-4 下午10:53:00 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward goLorePage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return mapping.findForward("lorePage");
	}
	
	/**
	 * 根据章节编号分页获取知识点列表
	 * @author wm
	 * @date 2019-5-4 下午10:55:52 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward getLoreData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		LoreInfoManager lm = (LoreInfoManager) AppFactory.instance(null).getApp(Constants.WEB_LORE_INFO);
		Integer cptId = CommonTools.getFinalInteger("cptId", request);
		Integer loreId = CommonTools.getFinalInteger("loreId", request);
		LoreInfo lore = lm.getEntityById(loreId);
		System.out.println(lore != null);
		return null;
	}
}