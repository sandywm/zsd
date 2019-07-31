/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.zsd.action.homework;

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
import com.zsd.module.BuffetAbilityTypeInfo;
import com.zsd.module.BuffetMindTypeInfo;
import com.zsd.module.BuffetTypeInfo;
import com.zsd.module.HwAbilityRelationInfo;
import com.zsd.module.HwMindRelationInfo;
import com.zsd.module.HwQueInfo;
import com.zsd.module.SendHwInfo;
import com.zsd.module.TeaQueInfo;
import com.zsd.page.PageConst;
import com.zsd.service.BuffetAllManager;
import com.zsd.service.HwAbilityRelationManager;
import com.zsd.service.HwMindRelationManager;
import com.zsd.service.HwQueManager;
import com.zsd.service.LoreInfoManager;
import com.zsd.service.SendHwManager;
import com.zsd.service.TeaQueManager;
import com.zsd.tools.CommonTools;
import com.zsd.tools.CurrentTime;
import com.zsd.util.Constants;

/** 
 * MyEclipse Struts
 * Creation date: 07-23-2019
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class HomeWorkAction extends DispatchAction {
	
	/**
	 * 导向系统家庭作业题库页面
	 * @author wm
	 * @date 2019-7-23 下午05:20:26
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward goHwPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return mapping.findForward("hwPage");
	}
	
	/**
	 * 分页获取系统家庭作业题库
	 * @author wm
	 * @date 2019-7-23 下午05:21:35
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward getHwPageData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HwQueManager hqm = (HwQueManager) AppFactory.instance(null).getApp(Constants.WEB_HW_QUE_INFO);
		HwMindRelationManager hmrm = (HwMindRelationManager) AppFactory.instance(null).getApp(Constants.WEB_HW_MIND_RELATION_INFO);
		HwAbilityRelationManager harm = (HwAbilityRelationManager) AppFactory.instance(null).getApp(Constants.WEB_HW_ABILITY_RELATION_INFO);
		Integer loreId = CommonTools.getFinalInteger("loreId", request);
		Integer count = hqm.getCountByLoreId(loreId);
		String msg = "暂无记录";
		Map<String,Object> map = new HashMap<String,Object>();
		if(count > 0){
			Integer pageSize = PageConst.getPageSize(String.valueOf(request.getParameter("limit")), 10);//等同于pageSize
			Integer pageNo = CommonTools.getFinalInteger("page", request);//等同于pageNo
			List<HwQueInfo> hqList = hqm.listPageInfoByLoreId(loreId, pageNo, pageSize);
			msg = "success";
			List<Object> list_d = new ArrayList<Object>();
			if(hqList.size() > 0){
				for(HwQueInfo hq : hqList){
					Map<String,Object> map_d = new HashMap<String,Object>();
					Integer hwId = hq.getId();
					map_d.put("id", hwId);
					map_d.put("btName", hq.getBuffetTypeInfo().getTypes());
					map_d.put("loreName", hq.getLoreInfo().getLoreName());
					map_d.put("hwTitle", hq.getTitle());
					String mindStr = "";
					String abilityStr = "";
					List<HwMindRelationInfo> hmrList = hmrm.listInfoByOpt(0, hwId);
					for(HwMindRelationInfo hmr : hmrList){
						mindStr += hmr.getBuffetMindTypeInfo().getMind() + ",";
					}
					if(!mindStr.equals("")){
						mindStr = mindStr.substring(0, mindStr.length() - 1);
					}
					List<HwAbilityRelationInfo>  harList = harm.listInfoByOpt(0, hwId);
					for(HwAbilityRelationInfo har : harList){
						abilityStr += har.getBuffetAbilityTypeInfo().getAbility();
					}
					map_d.put("mindStr", mindStr);
					map_d.put("abilityStr", abilityStr);
					map_d.put("inUse", hq.getInUse().equals(0) ? "有效" : "无效");
					list_d.add(map_d);
				}
				map.put("data", list_d);
				map.put("count", count);
				map.put("code", 0);
			}
		}
		map.put("msg", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 设置家庭作业题库有/无效状态
	 * @author wm
	 * @date 2019-7-24 上午10:25:20
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward setInUseStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HwQueManager hqm = (HwQueManager) AppFactory.instance(null).getApp(Constants.WEB_HW_QUE_INFO);
		Integer hwId = CommonTools.getFinalInteger("hwId", request);
		Integer inUse = CommonTools.getFinalInteger("inUse", request);//有效状态（0：有效，1：无效）
		Map<String,String> map = new HashMap<String,String>();
		String msg = "error";
		boolean flag = hqm.updateInUseById(hwId, inUse, CommonTools.getLoginAccount(request));
		if(flag){
			msg = "success";
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 浏览指定知识下系统家庭作业
	 * @author wm
	 * @date 2019-7-24 上午10:58:22
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getHwDetailData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HwQueManager hqm = (HwQueManager) AppFactory.instance(null).getApp(Constants.WEB_HW_QUE_INFO);
		HwMindRelationManager hmrm = (HwMindRelationManager) AppFactory.instance(null).getApp(Constants.WEB_HW_MIND_RELATION_INFO);
		HwAbilityRelationManager harm = (HwAbilityRelationManager) AppFactory.instance(null).getApp(Constants.WEB_HW_ABILITY_RELATION_INFO);
		Integer loreId = CommonTools.getFinalInteger("loreId", request);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "noInfo";
		List<Object> list_d_xqjf = new ArrayList<Object>();
		List<Object> list_d_ffgn = new ArrayList<Object>();
		List<Object> list_d_swxl = new ArrayList<Object>();
		List<Object> list_d_zlkf = new ArrayList<Object>();
		List<Object> list_d_nlpy = new ArrayList<Object>();
		List<Object> list_d_zksl = new ArrayList<Object>();
		List<HwQueInfo> hqList = hqm.listInfoByOpt(loreId, 0, -1, false);
		if(hqList.size() > 0){
			for(HwQueInfo hq : hqList){
				Map<String,Object> map_d = new HashMap<String,Object>();
				Integer hwId = hq.getId();
				List<HwMindRelationInfo> hmrList = hmrm.listInfoByOpt(0, hwId);
				List<HwAbilityRelationInfo>  harList = harm.listInfoByOpt(0, hwId);
				String swType = "";
				String nlType = "";
				for(HwMindRelationInfo hmr : hmrList){
					swType += hmr.getBuffetMindTypeInfo().getMind() + ",";
				}
				for(HwAbilityRelationInfo har : harList){
					nlType += har.getBuffetAbilityTypeInfo().getAbility() + ",";
				}
				if(!swType.equals("")){
					swType = swType.substring(0, swType.length() - 1);
				}
				if(!nlType.equals("")){
					nlType = nlType.substring(0, nlType.length() - 1);
				}
				String hqType = hq.getBuffetTypeInfo().getTypes();
				map_d.put("hqTitle", hq.getTitle());
				map_d.put("hqType", hq.getQueType());
				map_d.put("swType", swType);
				map_d.put("nlType", nlType);
				map_d.put("subject", hq.getSubject());
				map_d.put("hqAnswer", hq.getAnswer());
				map_d.put("hqResolution", hq.getResolution());//解析
				if(hqType.equals("兴趣激发")){
					list_d_xqjf.add(map_d);
				}else if(hqType.equals("方法归纳")){
					list_d_ffgn.add(map_d);
				}else if(hqType.equals("思维训练")){
					list_d_swxl.add(map_d);
				}else if(hqType.equals("智力开发")){
					list_d_zlkf.add(map_d);
				}else if(hqType.equals("能力培养")){
					list_d_nlpy.add(map_d);
				}else if(hqType.equals("中/高考涉猎")){
					list_d_zksl.add(map_d);
				}
			}
			map.put("xqjf", list_d_xqjf);
			map.put("ffgn", list_d_ffgn);
			map.put("swxl", list_d_swxl);
			map.put("zlkf", list_d_zlkf);
			map.put("nlpy", list_d_nlpy);
			map.put("zksl", list_d_zksl);
			msg = "success";
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 获取当前题型的题数
	 * @author wm
	 * @date 2019-7-24 下午04:26:56
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getCurrMaxNum(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HwQueManager hqm = (HwQueManager) AppFactory.instance(null).getApp(Constants.WEB_HW_QUE_INFO);
		Integer btId = CommonTools.getFinalInteger("btId", request);//基础类型编号
		Integer loreId = CommonTools.getFinalInteger("loreId", request);//知识点编号
		Map<String,Integer> map = new HashMap<String,Integer>();
		List<HwQueInfo> bqList = hqm.listInfoByOpt(loreId, btId, -1, true);
		Integer num = 1;
		if(bqList.size() > 0){
			num = bqList.get(0).getNum() + 1;
		}
		map.put("currNum", num);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 增加系统家庭作业
	 * @author wm
	 * @date 2019-7-24 下午04:28:29
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addHw(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HwQueManager hqm = (HwQueManager) AppFactory.instance(null).getApp(Constants.WEB_HW_QUE_INFO);
		HwMindRelationManager hmrm = (HwMindRelationManager) AppFactory.instance(null).getApp(Constants.WEB_HW_MIND_RELATION_INFO);
		HwAbilityRelationManager harm = (HwAbilityRelationManager) AppFactory.instance(null).getApp(Constants.WEB_HW_ABILITY_RELATION_INFO);
		BuffetAllManager bam = (BuffetAllManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_ALL_INFO);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "error";
		Integer btId = CommonTools.getFinalInteger("btId", request);//基础类型编号
		Integer loreId = CommonTools.getFinalInteger("loreId", request);
		String mindIdStr = CommonTools.getFinalStr("mindStr", request);//思维类型编号逗号拼接
		String abilityIdStr = CommonTools.getFinalStr("abilityIdStr", request);//能力类型编号逗号拼接
		String title = "";//标题
		List<HwQueInfo> bqList = hqm.listInfoByOpt(loreId, btId, -1, true);
		Integer orders = 0;//排序
		Integer num = 1;
		String btName = bam.getEntityByBtId(btId).getTypes();
		if(bqList.size() > 0){
			num = bqList.get(0).getNum() + 1;
			orders = bqList.get(0).getOrders() + 1;
		}else{
			if(btName.equals("兴趣激发")){
				orders = 1;
			}else if(btName.equals("方法归纳")){
				orders = 101;
			}else if(btName.equals("思维训练")){
				orders = 201;
			}else if(btName.equals("智力开发")){
				orders = 301;
			}else if(btName.equals("能力培养")){
				orders = 401;
			}else if(btName.equals("中/高考涉猎")){
				orders = 501;
			}
		}
		title = btName + "第" + num + "题";
		String queSub =  Transcode.unescape_new1("queSub", request);//题干
		String queAnswer = Transcode.unescape_new1("queAnswer", request);//答案
		String queResolution = Transcode.unescape_new1("queResolution", request);//解析
		String queType = Transcode.unescape_new1("queType", request);//题干类型
		if(!mindIdStr.equals("") && !abilityIdStr.equals("")){
			Integer hwId = hqm.addHW(btId, loreId, num, title, queSub, queAnswer, queResolution, queType, orders, CommonTools.getLoginAccount(request));
			if(hwId > 0){
				hmrm.addHMR(mindIdStr, hwId);
				harm.addHAR(abilityIdStr, hwId);
				msg = "success";
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 获取指定系统家庭作业详情
	 * @author wm
	 * @date 2019-7-24 下午05:10:23
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getHwDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HwQueManager hqm = (HwQueManager) AppFactory.instance(null).getApp(Constants.WEB_HW_QUE_INFO);
		HwMindRelationManager hmrm = (HwMindRelationManager) AppFactory.instance(null).getApp(Constants.WEB_HW_MIND_RELATION_INFO);
		HwAbilityRelationManager harm = (HwAbilityRelationManager) AppFactory.instance(null).getApp(Constants.WEB_HW_ABILITY_RELATION_INFO);
		BuffetAllManager bam = (BuffetAllManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_ALL_INFO);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "error";
		Integer hwId = CommonTools.getFinalInteger("hwId", request);//指定系统家庭作业编号
		if(hwId > 0){
			HwQueInfo hw = hqm.getEntityById(hwId);
			if(hw != null){
				msg = "success";
				map.put("hwId", hw.getId());
				map.put("hwType", hw.getQueType());
				List<BuffetTypeInfo> btList = bam.listBTInfo();
				List<Object> list_bt = new ArrayList<Object>();
				List<Object> list_mind = new ArrayList<Object>();
				List<Object> list_ability = new ArrayList<Object>();
				for(BuffetTypeInfo bt : btList){
					Map<String,Object> map_d = new HashMap<String,Object>();
					map_d.put("btId", bt.getId());
					map_d.put("btName", bt.getTypes());
					if(hw.getBuffetTypeInfo().getId().equals(bt.getId())){
						map_d.put("selFlag", true);
					}else{
						map_d.put("selFlag", false);
					}
					list_bt.add(map_d);
				}
				map.put("btList", list_bt);
				List<BuffetMindTypeInfo> bmList = bam.listBMTInfo();
				List<HwMindRelationInfo> hmrList = hmrm.listInfoByOpt(0, hwId);
				for(BuffetMindTypeInfo bmt : bmList){
					Map<String,Object> map_d = new HashMap<String,Object>();
					map_d.put("bmId", bmt.getId());
					map_d.put("bmName", bmt.getMind());
					boolean selFlag = false;
					for(HwMindRelationInfo hmr : hmrList){
						if(hmr.getBuffetMindTypeInfo().getId().equals(bmt.getId())){
							selFlag = true;
							break;
						}
					}
					map_d.put("selFlag", selFlag);
					list_mind.add(map_d);
				}
				map.put("bmList", list_mind);
				List<BuffetAbilityTypeInfo> baList = bam.listBATInfo();
				List<HwAbilityRelationInfo>  harList = harm.listInfoByOpt(0, hwId);
				for(BuffetAbilityTypeInfo bat : baList){
					Map<String,Object> map_d = new HashMap<String,Object>();
					map_d.put("baId", bat.getId());
					map_d.put("baName", bat.getAbility());
					boolean selFlag = false;
					for(HwAbilityRelationInfo har : harList){
						if(har.getBuffetAbilityTypeInfo().getId().equals(bat.getId())){
							selFlag = true;
							break;
						}
					}
					map_d.put("selFlag", selFlag);
					list_ability.add(map_d);
				}
				map.put("baList", list_ability);
				map.put("hwTitle", hw.getTitle());
				map.put("queSub", hw.getSubject());
				map.put("queAnswer", hw.getAnswer());
				map.put("queResolution", hw.getResolution());
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 修改指定的系统家庭作业
	 * @author wm
	 * @date 2019-7-25 上午11:06:24
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateHwDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HwQueManager hqm = (HwQueManager) AppFactory.instance(null).getApp(Constants.WEB_HW_QUE_INFO);
		HwMindRelationManager hmrm = (HwMindRelationManager) AppFactory.instance(null).getApp(Constants.WEB_HW_MIND_RELATION_INFO);
		HwAbilityRelationManager harm = (HwAbilityRelationManager) AppFactory.instance(null).getApp(Constants.WEB_HW_ABILITY_RELATION_INFO);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "error";
		Integer hwId = CommonTools.getFinalInteger("hwId", request);//题库编号
		String queType = Transcode.unescape_new1("queType", request);//类型
		String mindIdStr = CommonTools.getFinalStr("mindIdStr", request);//思维类型编号逗号拼接
		String abilityIdStr = CommonTools.getFinalStr("abilityIdStr", request);//能力类型编号逗号拼接
		String queSub = Transcode.unescape_new1("queSub", request);//题干
		String queAnswer = Transcode.unescape_new1("queAnswer", request);//答案，多个用逗号隔开
		String queResolution = Transcode.unescape_new1("queResolution", request);//解析
		if(hwId > 0){
			HwQueInfo hw = hqm.getEntityById(hwId);
			if(hw != null){
				if(!mindIdStr.equals("") && !abilityIdStr.equals("")){
					boolean flag = hqm.updateInfoById(hwId, queSub, queAnswer, queResolution, queType, CommonTools.getLoginAccount(request));
					if(flag){
						hmrm.delHMR(hwId);
						harm.delHAR(hwId);
						hmrm.addHMR(mindIdStr, hwId);
						harm.addHAR(abilityIdStr, hwId);
						msg = "success";
					}
				}
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 导向老师题库页面
	 * @author wm
	 * @date 2019-7-26 上午09:23:05
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward goTeaQuePage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return mapping.findForward("tqPage");
	}
	
	/**
	 * 分页获取指定知识点下（指定老师的题库列表）--如果是知识点管理员看的是所有老师上传的题库列表
	 * @author wm
	 * @date 2019-7-26 上午09:37:17
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getTeaQuePageData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		TeaQueManager tqm = (TeaQueManager) AppFactory.instance(null).getApp(Constants.WEB_TEA_QUE_INFO);
		Integer loreId = CommonTools.getFinalInteger("loreId", request);
		Integer currUserId = 0;
		String msg = "暂无记录";
		Map<String,Object> map = new HashMap<String,Object>();
		String roleName = CommonTools.getLoginRoleName(request);
		if(roleName.equals("老师") || roleName.equals("知识点管理员")){
			if(roleName.equals("老师")){
				currUserId = CommonTools.getLoginUserId(request);
			}
			Integer count = tqm.getCountByOpt(loreId, currUserId);
			if(count > 0){
				Integer pageSize = PageConst.getPageSize(String.valueOf(request.getParameter("limit")), 10);//等同于pageSize
				Integer pageNo = CommonTools.getFinalInteger("page", request);//等同于pageNo
				List<TeaQueInfo> tqList = tqm.listInfoByOpt(loreId, currUserId, true, pageNo, pageSize);
				msg = "success";
				List<Object> list_d = new ArrayList<Object>();
				for(TeaQueInfo tq : tqList){
					Map<String,Object> map_d = new HashMap<String,Object>();
					map_d.put("tqId", tq.getId());
					map_d.put("queTitle", tq.getQueTitle());
					map_d.put("tqType", "针对性诊断");
					map_d.put("inUse", tq.getInUse().equals(0) ? "有效" : "无效");
					map_d.put("addTeaName", tq.getUser().getRealName());//上传题老师
					list_d.add(map_d);
				}
				map.put("data", list_d);
				map.put("count", count);
				map.put("code", 0);
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}

	/**
	 * 获取指定题库详情
	 * @author wm
	 * @date 2019-7-26 下午04:39:15
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getTeaQueDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		TeaQueManager tqm = (TeaQueManager) AppFactory.instance(null).getApp(Constants.WEB_TEA_QUE_INFO);
		Integer tqId = CommonTools.getFinalInteger("tqId", request);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "error";
		TeaQueInfo tq = tqm.getEntityById(tqId);
		String roleName = CommonTools.getLoginRoleName(request);
		boolean flag = false;
		if(tq != null){
			if(roleName.equals("老师")){
				if(tq.getUser().getId().equals(CommonTools.getLoginUserId(request))){
					flag = true;
				}
			}else if(roleName.equals("知识点管理员")){
				flag = true;
			}
			if(flag){
				msg = "success";
				map.put("tqId", tq.getId());
				map.put("queTitle", tq.getQueTitle());
				map.put("queSub", tq.getQueSub());
				map.put("loreName", tq.getLoreInfo().getLoreName());
				map.put("queType", tq.getQueType());
				map.put("queType2", tq.getQueType2());
				map.put("queAnswer", tq.getQueAnswer());
				map.put("queResolution", tq.getQueResolution());
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 修改指定老师上传的题库详情
	 * @author wm
	 * @date 2019-7-26 下午05:10:25
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateTeaQue(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		TeaQueManager tqm = (TeaQueManager) AppFactory.instance(null).getApp(Constants.WEB_TEA_QUE_INFO);
		Integer tqId = CommonTools.getFinalInteger("tqId", request);
		String queSub = Transcode.unescape_new1("queSub", request);
		String queAnswer = Transcode.unescape_new1("queAnswer", request);
		String queResolution = Transcode.unescape_new1("queResolution", request);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "error";
		Boolean flag = false;
		String roleName = CommonTools.getLoginRoleName(request);
		TeaQueInfo tq = tqm.getEntityById(tqId);
		if(tq != null){
			if(roleName.equals("老师")){
				if(tq.getUser().getId().equals(CommonTools.getLoginUserId(request))){
					flag = true;
				}
			}else if(roleName.equals("知识点管理员")){
				flag = true;
			}
			if(flag){
				flag = tqm.updateInfoById(tqId, queSub, queAnswer, queResolution, "");
				if(flag){
					msg = "success";
				}
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 老师增加家庭作业题库
	 * @author wm
	 * @date 2019-7-26 下午05:18:50
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addTeaQue(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		TeaQueManager tqm = (TeaQueManager) AppFactory.instance(null).getApp(Constants.WEB_TEA_QUE_INFO);
		LoreInfoManager lm = (LoreInfoManager) AppFactory.instance(null).getApp(Constants.WEB_LORE_INFO);
		Integer loreId = CommonTools.getFinalInteger("loreId", request);
		String queType = Transcode.unescape_new1("queType", request);
		String queSub = Transcode.unescape_new1("queSub", request);
		String queAnswer = Transcode.unescape_new1("queAnswer", request);
		String queResolution = Transcode.unescape_new1("queResolution", request);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "error";
		String roleName = CommonTools.getLoginRoleName(request);
		if(roleName.equals("老师") && loreId > 0){
			Integer userId = CommonTools.getLoginUserId(request);
			List<TeaQueInfo> tqList = tqm.listInfoByOpt(loreId, userId, false, 0, 0);
			Integer currNum = 1;
			if(tqList.size() > 0){
				currNum = tqList.get(tqList.size() - 1).getQueNum() + 1;
			}
			Integer tqId = tqm.addTQ(loreId, 0, lm.getEntityById(loreId).getLoreName() + "第" + currNum + "题", queSub, queAnswer, queResolution, queType, "其他", userId);
			if(tqId > 0){
				msg = "success";
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 导向老师的家庭作业页面
	 * @author wm
	 * @date 2019-7-31 上午10:19:51
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goTeaHwPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return mapping.findForward("teaHwPage");
	}
	
	/**
	 * 导向学生家庭作业页面
	 * @author wm
	 * @date 2019-7-31 上午10:21:49
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goStuHwPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return mapping.findForward("stuHwPage");
	}
	
	/**
	 * 获取家庭作业发送列表（老师）
	 * @author wm
	 * @date 2019-7-31 上午10:26:35
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getSendHwData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		TeaQueManager tqm = (TeaQueManager) AppFactory.instance(null).getApp(Constants.WEB_TEA_QUE_INFO);
		SendHwManager swm = (SendHwManager) AppFactory.instance(null).getApp(Constants.WEB_SEND_HW_INFO);
		Integer currUserId = CommonTools.getLoginUserId(request);
		Integer roleId = CommonTools.getLoginRoleId(request);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "noInfo";
		List<SendHwInfo> shList = swm.listPageInfoByOpt(currUserId, 0, -1, 0, "", "", false, 1, 1);
		if(shList.size() > 0){
			msg = "success";
			for(Integer i = 0 ; i < shList.size() ; i++){
				if(i.equals(2)){
					break;
				}
				SendHwInfo shw = shList.get(i);
				Map<String,Object> map_d = new HashMap<String,Object>();
				map_d.put("sendHwId", shw.getId());
				map_d.put("classId", shw.getClassInfo().getId());
				map_d.put("classInfo", shw.getClassInfo());
				map_d.put("endDate", shw.getEndDate());
				map_d.put("hwTitle", shw.getSendDate().substring(0, 10)+"家庭作业");
				map_d.put("loreId", shw.getLoreInfo().getId());
				map_d.put("loreInfo", shw.getHwTitle());//第一单元:数据的收集和整理
				map_d.put("", shw);
				map_d.put("", shw);
				map_d.put("", shw);
			}
		}
		return null;
	}
}