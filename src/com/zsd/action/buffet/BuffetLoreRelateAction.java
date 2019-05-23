/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.zsd.action.buffet;

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

import com.zsd.factory.AppFactory;
import com.zsd.module.BuffetLoreRelateInfo;
import com.zsd.module.BuffetQueInfo;
import com.zsd.module.json.LoreBuffetTreeMenuJson;
import com.zsd.module.json.MySimpleTreeNode;
import com.zsd.service.BuffetLoreRelateInfoManager;
import com.zsd.service.BuffetQueInfoManager;
import com.zsd.tools.CommonTools;
import com.zsd.util.Constants;

/** 
 * MyEclipse Struts
 * Creation date: 05-22-2019
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class BuffetLoreRelateAction extends DispatchAction {
	
	/**
	 * 设置关联知识点(增加，删除)
	 * @author wm
	 * @date 2019-5-21 下午05:38:04
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addRelateLore(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		BuffetQueInfoManager bm = (BuffetQueInfoManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_QUE_INFO);
		BuffetLoreRelateInfoManager blrm = (BuffetLoreRelateInfoManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_LORE_RELATE_INFO);
		String opt = CommonTools.getFinalStr("opt", request);//操作方式(add,del)
		Map<String,String> map = new HashMap<String,String>();
		String msg = "error";
		if(opt.equals("add")){
			Integer buffetId = CommonTools.getFinalInteger("buffetId", request);
			Integer loreId = CommonTools.getFinalInteger("loreId", request);//通用版知识点
			BuffetQueInfo buffet =  bm.getEntityById(buffetId);
			if(buffet != null){
				if(blrm.listInfoByOpt(buffetId, loreId).size() == 0){
					Integer blrId = blrm.addBLR(buffetId, loreId, 1, buffet.getLoreInfo().getId());
					if(blrId > 0){
						msg = "success";
					}
				}else{
					msg = "exist";
				}
			}
		}else if(opt.equals("del")){
			Integer blrId = CommonTools.getFinalInteger("blrId", request);
			boolean flag = blrm.delBLRById(blrId);
			if(flag){
				msg = "success";
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 获取指定自助餐的关联知识点
	 * @author wm
	 * @date 2019-5-21 下午05:53:22
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getRelateLoreInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		BuffetLoreRelateInfoManager blrm = (BuffetLoreRelateInfoManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_LORE_RELATE_INFO);
		Integer buffetId = CommonTools.getFinalInteger("buffetId", request);
		List<BuffetLoreRelateInfo> blrList = blrm.listInfoByBuffetId(buffetId);
		Integer blrSize = blrList.size();
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "noInfo";
		if(blrSize > 0){
			msg = "success";
			List<Object> list_d = new ArrayList<Object>();
			for(Integer i = 0 ; i < blrSize ; i++){
				BuffetLoreRelateInfo blr = blrList.get(i);
				Map<String,Object> map_d = new HashMap<String,Object>();
				map_d.put("blrId", blr.getId());
				map_d.put("relateLoreName", blr.getLoreInfoByLoreId().getLoreName());
				list_d.add(map_d);
			}
			map.put("loreList", list_d);
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 关联只在通用版里面完成
	 * 获取当前知识点下（通用版）指定巴菲特题所关联的知识点列表(显示知识树)
	 * @author wm
	 * @date 2019-5-22 上午09:34:10
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showBuffetLoreTree_1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		LoreBuffetTreeMenuJson ltmj = new LoreBuffetTreeMenuJson();
		Integer buffetId = Integer.parseInt(request.getParameter("buffetId"));
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "success";
		List<MySimpleTreeNode> result = ltmj.showBuffetTree_1(buffetId);
		map.put("result", msg);
		map.put("relateList", result);
		CommonTools.getJsonPkg(map, response);
	    return null;
	}
}