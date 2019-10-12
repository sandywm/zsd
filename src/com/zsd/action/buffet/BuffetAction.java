/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.zsd.action.buffet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import com.zsd.module.BuffetAbilityRelationInfo;
import com.zsd.module.BuffetAbilityTypeInfo;
import com.zsd.module.BuffetMindRelationInfo;
import com.zsd.module.BuffetMindTypeInfo;
import com.zsd.module.BuffetQueInfo;
import com.zsd.module.BuffetTypeInfo;
import com.zsd.module.JoinLoreRelation;
import com.zsd.module.LexInfo;
import com.zsd.module.LoreInfo;
import com.zsd.module.LoreQuestionSubInfo;
import com.zsd.page.PageConst;
import com.zsd.service.BuffetAllManager;
import com.zsd.service.BuffetQueInfoManager;
import com.zsd.service.JoinLoreRelationManager;
import com.zsd.service.LexInfoManager;
import com.zsd.service.LoreInfoManager;
import com.zsd.service.LoreQuestionManager;
import com.zsd.tools.CommonTools;
import com.zsd.tools.CurrentTime;
import com.zsd.util.Constants;

/** 
 * MyEclipse Struts
 * Creation date: 05-21-2019
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class BuffetAction extends DispatchAction {
	
	/**
	 * 导向自助餐管理页面
	 * @author wm
	 * @date 2019-5-21 下午03:51:35
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward goBuffetPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return mapping.findForward("buffetPage");
	}
	
	/**
	 * 根据条件分页获取自助餐列表
	 * @author wm
	 * @date 2019-5-21 下午03:51:49
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward getPageBuffetInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		BuffetQueInfoManager bm = (BuffetQueInfoManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_QUE_INFO);
		BuffetAllManager bam = (BuffetAllManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_ALL_INFO);
		Integer loreId = CommonTools.getFinalInteger("loreId", request);
		Integer count = bm.getCountByLoreId(loreId);
		String msg = "暂无记录";
		Map<String,Object> map = new HashMap<String,Object>();
		if(count > 0){
			Integer pageSize = PageConst.getPageSize(String.valueOf(request.getParameter("limit")), 10);//等同于pageSize
			Integer pageNo = CommonTools.getFinalInteger("page", request);//等同于pageNo
			List<BuffetQueInfo> bList = bm.listPageInfoByLoreId(loreId, pageNo, pageSize);
			msg = "success";
			List<Object> list_d = new ArrayList<Object>();
			for(Iterator<BuffetQueInfo> it = bList.iterator() ; it.hasNext();){
				BuffetQueInfo buffet = it.next();
				Map<String,Object> map_d = new HashMap<String,Object>();
				Integer buffetId = buffet.getId();
				map_d.put("id", buffetId);
				map_d.put("btName", buffet.getBuffetTypeInfo().getTypes());//类型
				map_d.put("loreName", buffet.getLoreInfo().getLoreName());
				map_d.put("loreTitle", buffet.getTitle());
				String mindStr = "";
				String abilityStr = "";
				List<BuffetMindRelationInfo> bmList = bam.listBMRInfoByBuffetId(buffetId);
				if(bmList.size() > 0){
					for(Integer i = 0 ; i < bmList.size() ; i++){
						mindStr += bmList.get(i).getBuffetMindTypeInfo().getMind() + "、";
					}
					mindStr = mindStr.substring(0, mindStr.length() - 1);
				}
				List<BuffetAbilityRelationInfo> baList = bam.listBARInfoByBuffetId(buffetId);
				if(baList.size() > 0){
					for(Integer i = 0 ; i < baList.size() ; i++){
						abilityStr += baList.get(i).getBuffetAbilityTypeInfo().getAbility() + "、";
					}
					abilityStr = abilityStr.substring(0, abilityStr.length() - 1);
				}
				map_d.put("mindStr", mindStr);
				map_d.put("abilityStr", abilityStr);
				map_d.put("inUse", buffet.getInUse().equals(0) ? "有效" : "无效");
				list_d.add(map_d);
			}
			map.put("data", list_d);
			map.put("count", count);
			map.put("code", 0);
		}
		map.put("msg", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 设置自助餐题库有/无效状态
	 * @author wm
	 * @date 2019-5-21 下午05:31:54
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
		BuffetQueInfoManager bm = (BuffetQueInfoManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_QUE_INFO);
		Integer buffetId = CommonTools.getFinalInteger("buffetId", request);
		Integer inUse = CommonTools.getFinalInteger("inUse", request);//有效状态（0：有效，1：无效）
		Map<String,String> map = new HashMap<String,String>();
		String msg = "error";
		boolean flag = bm.updateInUseStatusById(buffetId, inUse, CommonTools.getLoginAccount(request), CurrentTime.getStringDate());
		if(flag){
			msg = "success";
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 浏览指定知识下的自助餐
	 * @author wm
	 * @date 2019-5-22 上午10:25:25
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getBuffetQueData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		BuffetQueInfoManager bm = (BuffetQueInfoManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_QUE_INFO);
		BuffetAllManager bam = (BuffetAllManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_ALL_INFO);
		LoreQuestionManager lqm = (LoreQuestionManager) AppFactory.instance(null).getApp(Constants.WEB_LORE_QUESTION_INFO);
		LexInfoManager lexm = (LexInfoManager) AppFactory.instance(null).getApp(Constants.WEB_LEX_INFO);
		Integer loreId = CommonTools.getFinalInteger("loreId", request);
		List<BuffetQueInfo> bqList = bm.listInfoByOpt(loreId, 0, -1);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "noInfo";
		List<Object> list_d_xqjf = new ArrayList<Object>();
		List<Object> list_d_ffgn = new ArrayList<Object>();
		List<Object> list_d_swxl = new ArrayList<Object>();
		List<Object> list_d_zlkf = new ArrayList<Object>();
		List<Object> list_d_nlpy = new ArrayList<Object>();
		List<Object> list_d_zksl = new ArrayList<Object>();
		if(bqList.size() > 0){
			msg = "success";
			for(Iterator<BuffetQueInfo> it = bqList.iterator() ; it.hasNext();){
				BuffetQueInfo buffet = it.next();
				Map<String,Object> map_d = new HashMap<String,Object>();
				Integer buffetId = buffet.getId();
				List<BuffetAbilityRelationInfo> barList = bam.listBARInfoByBuffetId(buffetId);
				List<BuffetMindRelationInfo> bmrList = bam.listBMRInfoByBuffetId(buffetId);
				String swType = "";
				String nlType = "";
				for(Iterator<BuffetAbilityRelationInfo> it_a = barList.iterator() ; it_a.hasNext();){
					BuffetAbilityRelationInfo bar = it_a.next();
					nlType += bar.getBuffetAbilityTypeInfo().getAbility() + ",";
				}
				for(Iterator<BuffetMindRelationInfo> it_m = bmrList.iterator() ; it_m.hasNext();){
					BuffetMindRelationInfo bmr = it_m.next();
					swType += bmr.getBuffetMindTypeInfo().getMind() + ",";
				}
				if(!swType.equals("")){
					swType = swType.substring(0, swType.length() - 1);
				}
				if(!nlType.equals("")){
					nlType = nlType.substring(0, nlType.length() - 1);
				}
				String buffetType = buffet.getBuffetTypeInfo().getTypes();
				map_d.put("bqTitle", buffet.getTitle());
				map_d.put("bqType", buffet.getQueType());
				map_d.put("swType", swType);
				map_d.put("nlType", nlType);
				map_d.put("subject", buffet.getSubject());
				String answerA = buffet.getA();
				String answerB = buffet.getB();
				String answerC = buffet.getC();
				String answerD = buffet.getD();
				String answerE = buffet.getE();
				String answerF = buffet.getF();
				map_d.put("answerA", answerA);//选项A
				map_d.put("answerB", answerB);//选项B
				map_d.put("answerC", answerC);//选项C
				map_d.put("answerD", answerD);//选项D
				map_d.put("answerE", answerE);//选项E
				map_d.put("answerF", answerF);//选项F
				String queAnswer = buffet.getAnswer();
				//需要匹配出选项
				String[] answerQueArr = queAnswer.split(",");
				String queAnswer_text = "";
				String replaceStr = "Module/commonJs/ueditor/jsp/lore/";
				for(Integer i = 0 ; i < answerQueArr.length ; i++){
					if(answerQueArr[i].equals(answerA.replace(replaceStr,""))){
						queAnswer_text += "A,";
						continue;
					}
					if(answerQueArr[i].equals(answerB.replace(replaceStr,""))){
						queAnswer_text += "B,";
						continue;
					}
					if(answerQueArr[i].equals(answerC.replace(replaceStr,""))){
						queAnswer_text += "C,";
						continue;
					}
					if(answerQueArr[i].equals(answerD.replace(replaceStr,""))){
						queAnswer_text += "D,";
						continue;
					}
					if(answerQueArr[i].equals(answerE.replace(replaceStr,""))){
						queAnswer_text += "E,";
						continue;
					}
					if(answerQueArr[i].equals(answerF.replace(replaceStr,""))){
						queAnswer_text += "F,";
						continue;
					}
				}
				if(!queAnswer_text.equals("")){
					queAnswer_text = queAnswer_text.substring(0, queAnswer_text.length() - 1);
				}
				map_d.put("queAnswer", queAnswer_text);
				map_d.put("queResolution", buffet.getResolution());//解析
				Integer queTipId = buffet.getTips();//提示
				if(queTipId > 0){//有提示
					LoreQuestionSubInfo  lqs = lqm.getEntityByLqsId(queTipId);
					if(lqs != null){
						map_d.put("queTipTitle", lqs.getLqsTitle()+"("+lqs.getLoreTypeName()+")");
					}
				}else{
					map_d.put("queTipTitle", "");
				}
				Integer lexId = buffet.getLexId();
				if(lexId > 0){
					LexInfo lex = lexm.getEntityById(lexId);
					if(lex != null){
						map_d.put("lexTitle", lex.getLexTitle());
					}
				}else{
					map_d.put("lexTitle", "");
				}
				
				if(buffetType.equals("兴趣激发")){
					list_d_xqjf.add(map_d);
				}else if(buffetType.equals("方法归纳")){
					list_d_ffgn.add(map_d);
				}else if(buffetType.equals("思维训练")){
					list_d_swxl.add(map_d);
				}else if(buffetType.equals("智力开发")){
					list_d_zlkf.add(map_d);
				}else if(buffetType.equals("能力培养")){
					list_d_nlpy.add(map_d);
				}else if(buffetType.equals("中/高考涉猎")){
					list_d_zksl.add(map_d);
				}
			}
			map.put("xqjf", list_d_xqjf);
			map.put("ffgn", list_d_ffgn);
			map.put("swxl", list_d_swxl);
			map.put("zlkf", list_d_zlkf);
			map.put("nlpy", list_d_nlpy);
			map.put("zksl", list_d_zksl);
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 根据知识点编号获取合并知识点信息
	 * @author wm
	 * @date 2019-5-22 下午03:25:13
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getJLRLiSt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		LoreInfoManager lm = (LoreInfoManager) AppFactory.instance(null).getApp(Constants.WEB_LORE_INFO);
		JoinLoreRelationManager jlrm = (JoinLoreRelationManager) AppFactory.instance(null).getApp(Constants.WEB_JOIN_LORE_RELATE_INFO);
		BuffetQueInfoManager bm = (BuffetQueInfoManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_QUE_INFO);
		Integer currLoreId = CommonTools.getFinalInteger("loreId", request);
		Integer cptId = CommonTools.getFinalInteger("cptId", request);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "success";
		List<Object> list_d = new ArrayList<Object>();
		if(currLoreId > 0 && cptId.equals(0)){
			LoreInfo lore = lm.getEntityById(currLoreId);
			if(lore != null){
				cptId = lore.getChapter().getId();
			}
		}
		JoinLoreRelation jlr = jlrm.getInfoByLoreId(currLoreId);
		List<LoreInfo> lList = lm.listInfoByCptId(cptId);
		for(Iterator<LoreInfo> it = lList.iterator() ; it.hasNext();){
			LoreInfo lore = it.next();
			Integer jlrId = 0;
			Integer loreId = lore.getId();
			String loreName = lore.getLoreName();
			boolean referFlag = false;
			boolean showFlag = false;
			if(jlr != null){
				String loreIdArrayStr = jlr.getLoreIdArray();
				String[] loreIdArray = loreIdArrayStr.split(",");
				for(Integer i = 0 ; i < loreIdArray.length ; i++){
					if(loreId.equals(Integer.parseInt(loreIdArray[i]))){
						referFlag = true;
						break;//跳出当前循环
					}
				}
				if(referFlag){
					jlrId = jlr.getId();
				}
			}
			//判断此知识点有无合并记录（和当前知识点合并记录除外）
			JoinLoreRelation jlrList_curr = jlrm.getInfoByLoreId(loreId);//根据知识点获取和该知识点合并的其他知识点
			if(jlrList_curr == null){
				showFlag = true;
			}else{
				String loreIdArrayStr = jlrList_curr.getLoreIdArray();
				String[] loreIdArray = loreIdArrayStr.split(",");
				for(Integer i = 0 ; i < loreIdArray.length ; i++){
					if(currLoreId.equals(Integer.parseInt(loreIdArray[i]))){
						showFlag = true;
						break;//跳出当前循环
					}
				}
			}
			Map<String,Object> map_d = new HashMap<String,Object>();
			if(showFlag){
				map_d.put("jlrId", jlrId);//主键
				map_d.put("loreId", loreId);//知识点编号
				boolean currLoreFlag = loreId.equals(currLoreId);
				map_d.put("currLoreStatus", currLoreFlag);//当前知识点标记
				map_d.put("loreName", loreName);//知识点名称
				if(!referFlag){
					referFlag = currLoreFlag;
				}
				map_d.put("selFlag", referFlag);//选中状态
				if(bm.listInfoByOpt(loreId, 0, -1).size() > 0){
					map_d.put("tkStatus", 1);//是否有题库
				}else{
					map_d.put("tkStatus", 0);//是否有题库
				}
				list_d.add(map_d);
			}
		}
		map.put("remainLoreList", list_d);
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 增加/修改/删除合并知识点信息
	 * @author wm
	 * @date 2019-5-22 下午05:41:14
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward setJLR(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		JoinLoreRelationManager jlrm = (JoinLoreRelationManager) AppFactory.instance(null).getApp(Constants.WEB_JOIN_LORE_RELATE_INFO);
		BuffetQueInfoManager bm = (BuffetQueInfoManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_QUE_INFO);
		Integer jlrId = CommonTools.getFinalInteger("jlrId", request);
		String loreIdStr = CommonTools.getFinalStr("loreIdStr", request);
		Map<String,String> map = new HashMap<String,String>();
		String msg = "error";
		if(jlrId.equals(0)){
			//增加
			if(!loreIdStr.equals("")){
				Integer tkNum = 0;
				String[] loreIdArr = loreIdStr.split(",");
				for(Integer i = 0 ; i < loreIdArr.length ; i++){
					if(bm.listInfoByOpt(Integer.parseInt(loreIdArr[i]), 0, -1).size() > 0){
						tkNum++;
					}
				}
				if(tkNum <= 1){
					jlrm.addJLR(loreIdStr);
					msg = "success";
				}else{
					msg = "outNum";//合并知识点中有多个题库
				}
			}
		}else{
			//修改或者删除
			if(!loreIdStr.equals("")){
				if(loreIdStr.split(",").length > 1){//一个以上
					Integer tkNum = 0;
					String[] loreIdArr = loreIdStr.split(",");
					for(Integer i = 0 ; i < loreIdArr.length ; i++){
						if(bm.listInfoByOpt(Integer.parseInt(loreIdArr[i]), 0, -1).size() > 0){
							tkNum++;
						}
					}
					if(tkNum <= 1){
						jlrm.updateJLR(jlrId, loreIdStr);
						msg = "success";
					}else{
						msg = "outNum";//合并知识点中有多个题库
					}
				}else{//只有一个知识点--多个减少成一个
					jlrm.delJLR(jlrId);
					msg = "success";
				}
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 获取当前题型的题数
	 * @author wm
	 * @date 2019-6-2 上午09:31:36
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
		BuffetQueInfoManager bm = (BuffetQueInfoManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_QUE_INFO);
		Integer btId = CommonTools.getFinalInteger("btId", request);//基础类型编号
		Integer loreId = CommonTools.getFinalInteger("loreId", request);//知识点编号
		Map<String,Integer> map = new HashMap<String,Integer>();
		BuffetQueInfo bq = bm.getCurrMaxNumAndOrderByOpt(loreId, btId);
		Integer num = 1;
		if(bq != null){
			num = bq.getBuffetNum() + 1;
		}
		map.put("currNum", num);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 增加自助餐
	 * @author wm
	 * @date 2019-5-23 上午10:59:36
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addBuffet(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		BuffetQueInfoManager bm = (BuffetQueInfoManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_QUE_INFO);
		BuffetAllManager bam = (BuffetAllManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_ALL_INFO);
		Map<String,String> map = new HashMap<String,String>();
		String msg = "error";
		Integer btId = CommonTools.getFinalInteger("btId", request);//基础类型编号
		String mindIdStr = CommonTools.getFinalStr("mindStr", request);//思维类型编号逗号拼接
		String abilityIdStr = CommonTools.getFinalStr("abilityIdStr", request);//能力类型编号逗号拼接
		Integer tipsId = CommonTools.getFinalInteger("queTipId", request);//提示
		Integer lexId =  CommonTools.getFinalInteger("lexId", request);//词条
		Integer loreId = CommonTools.getFinalInteger("loreId", request);//知识点编号
		String title = "";//标题
		BuffetQueInfo bq = bm.getCurrMaxNumAndOrderByOpt(loreId, btId);
		Integer order = 0;//排序
		Integer num = 1;
		String btName = bam.getEntityByBtId(btId).getTypes();
		if(bq != null){
			num = bq.getBuffetNum() + 1;
			order = bq.getQueOrder() + 1;
		}else{
			if(btName.equals("兴趣激发")){
				order = 1;
			}else if(btName.equals("方法归纳")){
				order = 101;
			}else if(btName.equals("思维训练")){
				order = 201;
			}else if(btName.equals("智力开发")){
				order = 301;
			}else if(btName.equals("能力培养")){
				order = 401;
			}else if(btName.equals("中/高考涉猎")){
				order = 501;
			}
		}
		title = btName + "第" + num + "题";
		String queSub =  Transcode.unescape_new1("queSub", request);//题干
		String queAnswer = Transcode.unescape_new1("queAnswer", request);//答案
		String queResolution = Transcode.unescape_new1("queResolution", request);//解析
		String queType = Transcode.unescape_new1("queType", request);//题干类型
		String answerA = Transcode.unescape_new1("answerA", request);
		String answerB = Transcode.unescape_new1("answerB", request);
		String answerC = Transcode.unescape_new1("answerC", request);
		String answerD = Transcode.unescape_new1("answerD", request);
		String answerE = Transcode.unescape_new1("answerE", request);
		String answerF = Transcode.unescape_new1("answerF", request);
		if(!mindIdStr.equals("") && !abilityIdStr.equals("")){
			Integer buffetId = bm.addBQ(btId, loreId, num, title, queSub, queAnswer, lexId, tipsId, queResolution, queType, order, answerA, 
					answerB, answerC, answerD, answerE, answerF, CommonTools.getLoginAccount(request), CurrentTime.getCurrentTime());
			if(buffetId > 0){
				bam.addBMR(buffetId, mindIdStr);
				bam.addBAR(buffetId, abilityIdStr);
				msg = "success";
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 获取指定自助餐详情
	 * @author wm
	 * @date 2019-5-23 上午11:33:14
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getBuffetDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		BuffetQueInfoManager bm = (BuffetQueInfoManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_QUE_INFO);
		BuffetAllManager bam = (BuffetAllManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_ALL_INFO);
		LoreQuestionManager lqm = (LoreQuestionManager) AppFactory.instance(null).getApp(Constants.WEB_LORE_QUESTION_INFO);
		LexInfoManager lexm = (LexInfoManager) AppFactory.instance(null).getApp(Constants.WEB_LEX_INFO);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "error";
		Integer buffetId =  CommonTools.getFinalInteger("buffetId", request);
		if(buffetId > 0){
			BuffetQueInfo bq = bm.getEntityById(buffetId);
			if(bq != null){
				msg = "success";
				map.put("buffetId", buffetId);
				String queType = bq.getQueType();
				map.put("queType", queType);
				List<BuffetTypeInfo> btList = bam.listBTInfo();
				List<Object> list_bt = new ArrayList<Object>();
				List<Object> list_bm = new ArrayList<Object>();
				List<Object> list_ba = new ArrayList<Object>();
				for(Iterator<BuffetTypeInfo> it = btList.iterator() ; it.hasNext();){
					BuffetTypeInfo bt = it.next();
					Map<String,Object> map_d = new HashMap<String,Object>();
					map_d.put("btId", bt.getId());
					map_d.put("btName", bt.getTypes());
					if(bq.getBuffetTypeInfo().getId().equals(bt.getId())){
						map_d.put("selFlag", true);
					}else{
						map_d.put("selFlag", false);
					}
					list_bt.add(map_d);
				}
				map.put("btList", list_bt);
				List<BuffetMindTypeInfo> bmList = bam.listBMTInfo();
				List<BuffetMindRelationInfo> bmrList = bam.listBMRInfoByBuffetId(buffetId);
				for(Iterator<BuffetMindTypeInfo> it = bmList.iterator() ; it.hasNext();){
					BuffetMindTypeInfo bmt = it.next();
					Map<String,Object> map_d = new HashMap<String,Object>();
					map_d.put("bmId", bmt.getId());
					map_d.put("bmName", bmt.getMind());
					boolean selFlag = false;
					for(Integer i = 0 ; i < bmrList.size() ; i++){
						BuffetMindRelationInfo bmr = bmrList.get(i);
						if(bmr.getBuffetMindTypeInfo().getId().equals(bmt.getId())){
							selFlag = true;
							break;
						}
					}
					map_d.put("selFlag", selFlag);
					list_bm.add(map_d);
				}
				map.put("bmList", list_bm);
				List<BuffetAbilityTypeInfo> baList = bam.listBATInfo();
				List<BuffetAbilityRelationInfo> barList = bam.listBARInfoByBuffetId(buffetId);
				for(Iterator<BuffetAbilityTypeInfo> it = baList.iterator() ; it.hasNext();){
					BuffetAbilityTypeInfo bat = it.next();
					Map<String,Object> map_d = new HashMap<String,Object>();
					map_d.put("baId", bat.getId());
					map_d.put("baName", bat.getAbility());
					boolean selFlag = false;
					for(Integer i = 0 ; i < barList.size() ; i++){
						BuffetAbilityRelationInfo bar = barList.get(i);
						if(bar.getBuffetAbilityTypeInfo().getId().equals(bat.getId())){
							selFlag = true;
							break;
						}
					}
					map_d.put("selFlag", selFlag);
					list_ba.add(map_d);
				}
				map.put("baList", list_ba);
				map.put("title", bq.getTitle());
				map.put("queSub", bq.getSubject());
				map.put("queAnswer", bq.getAnswer());
				map.put("queResolution", bq.getResolution());
				String answerA = bq.getA();
				String answerB = bq.getB();
				String answerC = bq.getC();
				String answerD = bq.getD();
				String answerE = bq.getE();
				String answerF = bq.getF();
				map.put("anserA", answerA);
				map.put("anserB", answerB);
				map.put("anserC", answerC);
				map.put("anserD", answerD);
				map.put("anserE", answerE);
				map.put("anserF", answerF);
				Integer queOptNum = 0;//问题选项
				Integer answerNum = 0;//答案数量
				if(queType.equals("单选题") || queType.equals("多选题") || queType.equals("填空选择题")){
					//有最大选项
					if(!answerA.equals("")){
						queOptNum++;
					}
					if(!answerB.equals("")){
						queOptNum++;
					}
					if(!answerC.equals("")){
						queOptNum++;
					}
					if(!answerD.equals("")){
						queOptNum++;
					}
					if(!answerE.equals("")){
						queOptNum++;
					}
					if(!answerF.equals("")){
						queOptNum++;
					}
					map.put("queOptNum", queOptNum);
					if(queType.equals("填空选择题")){
						//有最大选项和填空数量
						answerNum = bq.getAnswer().split(",").length;//多个答案用,隔开
						map.put("answerNum", answerNum);
					}
				}else if(queType.equals("填空题")){
					answerNum = bq.getAnswer().split(",").length;//多个答案用,隔开
					map.put("answerNum", answerNum);
				}
				Integer queTipId = bq.getTips();
				map.put("queTipId", queTipId);
				List<LoreQuestionSubInfo> lqsList = lqm.listInfoByLoreId(bq.getLoreInfo().getId());
				List<Object> list_d_1 = new ArrayList<Object>();
				if(lqsList.size() > 0){
					if(queTipId > 0){//提示为知识清单或者点拨指导的一内容
						for(Iterator<LoreQuestionSubInfo> it = lqsList.iterator() ; it.hasNext();){
							LoreQuestionSubInfo lqs = it.next();
							Map<String,Object> map_d_1 = new HashMap<String,Object>();
							map_d_1.put("lqsId", lqs.getId());
							map_d_1.put("lqsTitle", lqs.getLqsTitle());
							map_d_1.put("lqsContent", lqs.getLqsContent());
							map_d_1.put("lqsType", lqs.getLoreTypeName());
							if(queTipId.equals(lqs.getId())){
								map_d_1.put("selStatus", true);
							}else{
								map_d_1.put("selStatus", false);
							}
							list_d_1.add(map_d_1);
						}
					}else{
						for(Iterator<LoreQuestionSubInfo> it = lqsList.iterator() ; it.hasNext();){
							LoreQuestionSubInfo lqs = it.next();
							Map<String,Object> map_d_1 = new HashMap<String,Object>();
							map_d_1.put("lqsId", lqs.getId());
							map_d_1.put("lqsTitle", lqs.getLqsTitle());
							map_d_1.put("lqsContent", lqs.getLqsContent());
							map_d_1.put("lqsType", lqs.getLoreTypeName());
							map_d_1.put("selStatus", false);
							list_d_1.add(map_d_1);
						}
					}
				}
				map.put("tipsList", list_d_1);
				Integer lexId = bq.getLexId();
				map.put("lexId", lexId);
				if(lexId > 0){
					LexInfo lex = lexm.getEntityById(lexId);
					if(lex != null){
						Map<String,Object> map_d = new HashMap<String,Object>();
						List<Object> list_d = new ArrayList<Object>();
						map_d.put("lexTitle", lex.getLexTitle());
						map_d.put("lexContent", lex.getLexContent());
						list_d.add(map_d);
						map.put("lexInfo", list_d);
					}
				}
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 修改指定的自助餐信息
	 * @author wm
	 * @date 2019-5-23 上午11:33:57
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateBuffetDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		BuffetQueInfoManager bm = (BuffetQueInfoManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_QUE_INFO);
		BuffetAllManager bam = (BuffetAllManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_ALL_INFO);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "error";
		Integer buffetId =  CommonTools.getFinalInteger("buffetId", request);
		String queType = Transcode.unescape_new1("queType", request);//类型
		Integer queTipId = CommonTools.getFinalInteger("queTipId", request);//词条编号（提示）
		Integer lexId = CommonTools.getFinalInteger("lexId", request);//词库编号
		String mindIdStr = CommonTools.getFinalStr("mindStr", request);//思维类型编号逗号拼接
		String abilityIdStr = CommonTools.getFinalStr("abilityIdStr", request);//能力类型编号逗号拼接
		String queSub = Transcode.unescape_new1("queSub", request);//题干
		String answerA = Transcode.unescape_new1("answerA", request);
		String answerB = Transcode.unescape_new1("answerB", request);
		String answerC = Transcode.unescape_new1("answerC", request);
		String answerD = Transcode.unescape_new1("answerD", request);
		String answerE = Transcode.unescape_new1("answerE", request);
		String answerF = Transcode.unescape_new1("answerF", request);
		String queAnswer = Transcode.unescape_new1("queAnswer", request);//答案，多个用逗号隔开
		String queResolution = Transcode.unescape_new1("queResolution", request);//解析
		if(buffetId > 0){
			BuffetQueInfo bq = bm.getEntityById(buffetId);
			if(bq != null && !mindIdStr.equals("") && !abilityIdStr.equals("")){
				boolean flag = bm.updateInfoById(buffetId, queSub, queAnswer, queTipId, lexId, queResolution, 
						queType, answerA, answerB, answerC, answerD, answerE, answerF, CommonTools.getLoginAccount(request), CurrentTime.getCurrentTime());
				if(flag){
					//修改能力、思维关联信息
					bam.delBAR(buffetId);
					bam.delBMR(buffetId);
					bam.addBAR(buffetId, abilityIdStr);
					bam.addBMR(buffetId, mindIdStr);
					msg = "success";
				}
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 获取指定自助餐关联的词条信息
	 * @author wm
	 * @date 2019-6-3 下午04:13:08
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getRelationLexInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		BuffetQueInfoManager bm = (BuffetQueInfoManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_QUE_INFO);
		LexInfoManager lexm = (LexInfoManager) AppFactory.instance(null).getApp(Constants.WEB_LEX_INFO);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "error";
		Integer buffetId =  CommonTools.getFinalInteger("buffetId", request);
		if(buffetId > 0){
			BuffetQueInfo bq = bm.getEntityById(buffetId);
			if(bq != null){
				Integer lexId = bq.getLexId();
				if(lexId > 0){
					LexInfo lex = lexm.getEntityById(lexId);
					if(lex != null){
						msg = "success";
						map.put("lexTitle", lex.getLexTitle());
						map.put("lexContent", lex.getLexContent());
					}
				}else{
					msg = "noInfo";
				}
			}
		}
		map.put("result", msg);
		map.put("buffetId", buffetId);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 修改指定自助餐的关联词条
	 * @author wm
	 * @date 2019-6-3 下午04:19:50
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateRelationLexInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		BuffetQueInfoManager bm = (BuffetQueInfoManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_QUE_INFO);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "error";
		Integer buffetId =  CommonTools.getFinalInteger("buffetId", request);
		Integer lexId =  CommonTools.getFinalInteger("lexId", request);
		if(buffetId > 0 && lexId >= 0){
			BuffetQueInfo bq = bm.getEntityById(buffetId);
			if(bq != null){
				if(!lexId.equals(bq.getLexId())){//不相同执行数据库操作
					bm.updateLexInfoById(buffetId, lexId);
				}
				msg = "success";
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
}