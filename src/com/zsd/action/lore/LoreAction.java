/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.zsd.action.lore;

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
import com.zsd.module.Chapter;
import com.zsd.module.Education;
import com.zsd.module.GradeSubject;
import com.zsd.module.LoreInfo;
import com.zsd.page.PageConst;
import com.zsd.service.ChapterManger;
import com.zsd.service.EducationManager;
import com.zsd.service.LoreInfoManager;
import com.zsd.tools.CommonTools;
import com.zsd.tools.Convert;
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
	 * 导向知识典目录管理页面
	 * @author wm
	 * @date 2019-5-4 下午10:53:00 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward goLoreCatalogPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return mapping.findForward("lorePage");
	}
	
	/**
	 * 根据章节编号分页获取知识点目录列表
	 * @author wm
	 * @date 2019-5-4 下午10:55:52 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward getLoreCatalogData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		LoreInfoManager lm = (LoreInfoManager) AppFactory.instance(null).getApp(Constants.WEB_LORE_INFO);
		Integer cptId = CommonTools.getFinalInteger("cptId", request);
		Integer count = lm.getCountByCptId(cptId);
		String msg = "暂无记录";
		Map<String,Object> map = new HashMap<String,Object>();
		if(count > 0){
			Integer pageSize = PageConst.getPageSize(String.valueOf(request.getParameter("limit")), 10);//等同于pageSize
			Integer pageNo = CommonTools.getFinalInteger("page", request);//等同于pageNo
			List<LoreInfo> loreList = lm.listPageInfoByCptId(cptId, pageNo, pageSize);
			msg = "success";
			List<Object> list_d = new ArrayList<Object>();
			for(Iterator<LoreInfo> it = loreList.iterator() ; it.hasNext();){
				LoreInfo lore = it.next();
				Map<String,Object> map_d = new HashMap<String,Object>();
				map_d.put("id", lore.getId());
				map_d.put("loreName", lore.getLoreName());
				Integer mainLoreId = lore.getMainLoreId();//被引用知识点
				if(mainLoreId > 0){
					LoreInfo lore_main = lm.getEntityById(mainLoreId);
					if(lore_main != null){
						map_d.put("mainLoreName", lore_main.getLoreName());
					}else{
						map_d.put("mainLoreName", "");
					}
				}else{//通用版
					map_d.put("mainLoreName", lore.getLoreName());
				}
				map_d.put("loreCode", lore.getLoreCode());
				map_d.put("inUse", lore.getInUse().equals(0) ? "有效" : "无效");
				map_d.put("freeStatus", lore.getFreeStatus());
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
	 * 增加通用版本知识点
	 * @author wm
	 * @date 2019-5-6 上午09:59:29
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addCommonLore(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		LoreInfoManager lm = (LoreInfoManager) AppFactory.instance(null).getApp(Constants.WEB_LORE_INFO);
		ChapterManger cm = (ChapterManger) AppFactory.instance(null).getApp(Constants.WEB_CHAPTER_INFO);
		Integer cptId = CommonTools.getFinalInteger("cptId", request);//章节编号
		Integer ediId = 0;//出版社编号
		Integer subId = 0;//学科编号
		String gradeName = "";
		String gradeCode = "";//年级号
		String eduVolume = "";
		String loreName = Transcode.unescape_new("loreName", request);
		String subIdCode = "";
		String msg = "error";
		if(lm.checkExistByCptId(cptId, loreName)){
			msg = "exist";
		}else{
			Chapter c = cm.getEntityById(cptId);
			if(c != null){
				msg = "success";
				Education edu = c.getEducation();
				ediId = edu.getEdition().getId();
				subId = edu.getGradeSubject().getSubject().getId();
				gradeName = edu.getGradeSubject().getGradeName();
				gradeCode = Convert.ChineseConvertNumber(gradeName);
				eduVolume = edu.getEduVolume();
				if(subId < 10){
					subIdCode = "0" + subId;
				}
				String paraCode = "";//学段号
				Integer gradeNum = Integer.parseInt(gradeCode);
				if(gradeNum < 7){
					paraCode = "01";
				}else if(gradeNum >= 7 && gradeNum <= 9){
					paraCode = "02";
				}else{
					paraCode = "03";
				}
				String eduVolumeCode = "02";//教材编号
				if(eduVolume.equals("上册")){
					eduVolumeCode = "01";
				}
				
				String ediIdCode = "";//出版社号
				if(ediId < 10){
					ediIdCode = "0" + ediId;
				}
				String cptIdCode = "";//章节号
				if(cptId < 10){
					cptIdCode = "0" + cptId;
				}
				String loreOrderCode = "";//知识点顺序
				Integer loreOrder = lm.getCurrentMaxOrderByCptId(cptId);
				if(loreOrder < 10){
					loreOrderCode = "0" + loreOrder;
				}
				
				String loreCode = subIdCode + "-" + paraCode + "-" + gradeCode + "-" + eduVolumeCode + "-" + ediIdCode + "-" + cptIdCode + "-" + loreOrderCode;
				lm.addLore(cptId, loreName, Convert.getFirstSpell(loreName), loreOrder, 0, loreCode);
				
			}
		}
		Map<String,String> map = new HashMap<String,String>();
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 修改知识点信息（名称、排序、有效、免费）
	 * @author wm
	 * @date 2019-5-6 上午11:17:36
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateLoreDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		LoreInfoManager lm = (LoreInfoManager) AppFactory.instance(null).getApp(Constants.WEB_LORE_INFO);
		Integer loreId = CommonTools.getFinalInteger("loreId", request);//知识点编号
		String loreName = Transcode.unescape_new("loreName", request);//知识点名称(""不修改)
		Integer loreOrder = CommonTools.getFinalInteger("loreOrder", request);//知识点排序号（-1不修改）
		Integer inUse = CommonTools.getFinalInteger("inUse", request);//显示状态(-1不修改)
		Integer freeStatus = CommonTools.getFinalInteger("freeStatus", request);//免费状态(-1不修改)
		LoreInfo lore = lm.getEntityById(loreId);
		String msg = "error";
		Map<String,String> map = new HashMap<String,String>();
		if(lore != null){
			if(!lore.getLoreName().equals(loreName)){//名字变化
				Integer cptId = lore.getChapter().getId();
				if(lm.checkExistByCptId(cptId, loreName)){
					msg = "existInfo";
				}else{
					msg = "success";
				}
			}else{
				msg = "success";
			}
			if(msg.equals("success")){
				boolean flag = lm.updateLore(loreId, loreName, -1, loreOrder, inUse, freeStatus);
				lm.updateLore(loreId, loreName, -1, loreOrder, inUse, freeStatus);
				if(flag){
					msg = "success";
				}else{
					msg = "error";
				}
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 批量增加知识点名称(生成其他版本知识点的时候)
	 * @author wm
	 * @date 2019-5-6 上午11:24:17
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addBatchLoreData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		LoreInfoManager lm = (LoreInfoManager) AppFactory.instance(null).getApp(Constants.WEB_LORE_INFO);
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("result", "success");
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 批量生成知识点编码(指定章节下批量修改)
	 * @author wm
	 * @date 2019-5-6 上午11:26:13
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateBatchLoreCode(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		LoreInfoManager lm = (LoreInfoManager) AppFactory.instance(null).getApp(Constants.WEB_LORE_INFO);
		ChapterManger cm = (ChapterManger) AppFactory.instance(null).getApp(Constants.WEB_CHAPTER_INFO);
		Integer cptId = CommonTools.getFinalInteger("cptId", request);//章节编号
		Integer ediId = 0;//出版社编号
		Integer subId = 0;//学科编号
		String gradeName = "";
		String gradeCode = "";//年级号
		String eduVolume = "";
		String subIdCode = "";
		
		String msg = "noInfo";
		List<LoreInfo> loreList = lm.listPageInfoByCptId(cptId, 1, 100000);
		if(loreList.size() > 0){
			Chapter c = cm.getEntityById(cptId);
			if(c != null){
				msg = "success";
				Education edu = c.getEducation();
				ediId = edu.getEdition().getId();
				subId = edu.getGradeSubject().getSubject().getId();
				gradeName = edu.getGradeSubject().getGradeName();
				gradeCode = Convert.ChineseConvertNumber(gradeName);
				eduVolume = edu.getEduVolume();
				if(subId < 10){
					subIdCode = "0" + subId;
				}
				String paraCode = "";//学段号
				Integer gradeNum = Integer.parseInt(gradeCode);
				if(gradeNum < 7){
					paraCode = "01";
				}else if(gradeNum >= 7 && gradeNum <= 9){
					paraCode = "02";
				}else{
					paraCode = "03";
				}
				String eduVolumeCode = "02";//教材编号
				if(eduVolume.equals("上册")){
					eduVolumeCode = "01";
				}
				
				String ediIdCode = "";//出版社号
				if(ediId < 10){
					ediIdCode = "0" + ediId;
				}
				String cptIdCode = "";//章节号
				if(cptId < 10){
					cptIdCode = "0" + cptId;
				}
				for(Iterator<LoreInfo> it = loreList.iterator() ; it.hasNext();){
					LoreInfo lore = it.next();
					Integer loreOrder = lore.getLoreOrder();
					String loreOrderCode = "";
					if(loreOrder < 10){
						loreOrderCode = "0" + loreOrder;
					}
					String loreCode = subIdCode + "-" + paraCode + "-" + gradeCode + "-" + eduVolumeCode + "-" + ediIdCode + "-" + cptIdCode + "-" + loreOrderCode;
					Boolean flag = lm.updateLoreCodeById(lore.getId(), loreCode);
					if(flag){
						msg += "知识点[" + lore.getLoreName() + "]增加编码成功\n";
					}else{
						msg += "知识点[" + lore.getLoreName() + "]增加编码失败\n";
					}
				}
			}
		}
		Map<String,String> map = new HashMap<String,String>();
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 导向知识点管理页面
	 * @author wm
	 * @date 2019-5-6 上午11:45:56
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward goLoreQuePage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return mapping.findForward("loreQuePage");
	}
	
	/**
	 * 根据章节分页获取知识点列表
	 * @author wm
	 * @date 2019-5-6 上午11:52:27
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getPageLoreData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		LoreInfoManager lm = (LoreInfoManager) AppFactory.instance(null).getApp(Constants.WEB_LORE_INFO);
		Integer cptId = CommonTools.getFinalInteger("cptId", request);
		Integer count = lm.getCountByCptId(cptId);
		String msg = "暂无记录";
		Map<String,Object> map = new HashMap<String,Object>();
		if(count > 0){
			Integer pageSize = PageConst.getPageSize(String.valueOf(request.getParameter("limit")), 10);//等同于pageSize
			Integer pageNo = CommonTools.getFinalInteger("page", request);//等同于pageNo
			List<LoreInfo> loreList = lm.listPageInfoByCptId(cptId, pageNo, pageSize);
			msg = "success";
			List<Object> list_d = new ArrayList<Object>();
			for(Iterator<LoreInfo> it = loreList.iterator() ; it.hasNext();){
				LoreInfo lore = it.next();
				Map<String,Object> map_d = new HashMap<String,Object>();
				map_d.put("loreName", lore.getLoreName());
				map_d.put("inUse", lore.getInUse().equals(0) ? "有效" : "无效");
				Chapter cpt = lore.getChapter();
				String cptName = cpt.getChapterName();//章节名称
				map_d.put("cptName",cptName);
				Education edu = cpt.getEducation();
				String eduVolume = edu.getEduVolume();//教材
				map_d.put("eduVolume",eduVolume);
				GradeSubject gs = edu.getGradeSubject();
				String gradeName = gs.getGradeName();//年级
				map_d.put("gradeName",gradeName);
				String subName = gs.getSubject().getSubName();//学科
				map_d.put("subName",subName);
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
	 * 分页获取指定知识点的题库列表
	 * @author wm
	 * @date 2019-5-6 下午05:22:33
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getPageLoreQuesionData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		LoreInfoManager lm = (LoreInfoManager) AppFactory.instance(null).getApp(Constants.WEB_LORE_INFO);
		
		return null;
	}
}