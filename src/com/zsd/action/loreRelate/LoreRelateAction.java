/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.zsd.action.loreRelate;

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

import com.zsd.factory.AppFactory;
import com.zsd.service.LoreInfoManager;
import com.zsd.service.LoreRelateLogManager;
import com.zsd.service.LoreRelateManager;
import com.zsd.util.Constants;
import com.zsd.module.Edition;
import com.zsd.module.Education;
import com.zsd.module.LoreInfo;
import com.zsd.module.LoreRelateInfo;
import com.zsd.module.json.LoreTreeMenuJson;
import com.zsd.module.json.MyTreeNode;
import com.zsd.tools.CommonTools;

/** 
 * MyEclipse Struts
 * Creation date: 05-13-2019
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class LoreRelateAction extends DispatchAction {
	
	/**
	 * 导向关联知识点页面
	 * @author wm
	 * @date 2019-5-13 下午05:12:33
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goLoreRelatePage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return mapping.findForward("lrPage");
	}
	
	/**
	 * 显示指定知识点的知识树
	 * @author wm
	 * @date 2019-5-28 下午04:02:42
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showLoreTree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		LoreInfoManager lm = (LoreInfoManager)AppFactory.instance(null).getApp(Constants.WEB_LORE_INFO);
		Integer loreId = CommonTools.getFinalInteger("loreId", request);
		String orderOpt = "";
		if(loreId > 0){
			LoreInfo lore = lm.getEntityById(loreId);
			if(lore != null){
				Integer ediId = lore.getChapter().getEducation().getEdition().getId();
				if(ediId.equals(1)){//通用版-按照正常数据库顺序
					
				}else{
					orderOpt = "desc";
				}
			}
		}
		LoreTreeMenuJson ltmj = new LoreTreeMenuJson();
		List<MyTreeNode> result = ltmj.showTree(loreId,0,orderOpt);
		CommonTools.getJsonPkg(result, response);
		return null;
	}
	
	
	/**
	 * 根据教材获取简单知识树
	 * @author wm
	 * @date 2019-5-13 上午10:28:10
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward showLoreSimpleTree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Integer eduId = CommonTools.getFinalInteger("eduId", request);
		Integer ediId = CommonTools.getFinalInteger("ediId", request);
		LoreTreeMenuJson ltmj = new LoreTreeMenuJson();
		List<MyTreeNode> result = ltmj.showLoreSimpleTree(eduId,ediId);
		CommonTools.getJsonPkg(result, response);
		return null;
	}
	
	/**
	 * 显示指定知识点的关联知识点
	 * @author wm
	 * @date 2019-5-13 上午11:18:12
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showRelationList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		LoreRelateManager lrm = (LoreRelateManager)AppFactory.instance(null).getApp(Constants.WEB_LORE_RELATE_INFO);
		LoreInfoManager lm = (LoreInfoManager)AppFactory.instance(null).getApp(Constants.WEB_LORE_INFO);
		Integer loreId = CommonTools.getFinalInteger("loreId", request);
		String orderOpt = CommonTools.getFinalStr("orderOpt", request);//asc,desc--其他版本需要降序排列，通用版不用排序
		String msg = "noInfo";
		Map<String,Object> map = new HashMap<String,Object>();
		LoreInfo lore = lm.getEntityById(loreId);
		if(lore != null){
			Education edu = lore.getChapter().getEducation();
			Integer ediId = edu.getEdition().getId();
			msg = "success";
			List<LoreRelateInfo> lrList = lrm.listRelateInfoByOpt(loreId, 0, -1,orderOpt);
			if(lrList.size() > 0){
				List<Object> list_d = new ArrayList<Object>();
				for(Iterator<LoreRelateInfo> it = lrList.iterator() ; it.hasNext();){
					LoreRelateInfo lr = it.next();
					Map<String,Object> map_d = new HashMap<String,Object>();
					LoreInfo rootLore = lr.getRootLoreInfo();
					if(rootLore != null){
						map_d.put("lrId", lr.getId());
						map_d.put("rootLoreName", rootLore.getLoreName());
						list_d.add(map_d);
					}
				}
				map.put("lrList", list_d);
			}
			map.put("loreId", lore.getId());
			map.put("loreName", lore.getLoreName());
			map.put("eduVolume", edu.getEduVolume());
			map.put("eduId", edu.getId());
			map.put("gsId", edu.getGradeSubject().getId());
			map.put("subId", edu.getGradeSubject().getSubject().getId());
			map.put("ediId", ediId);
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
	    return null;
	}
	
	/**
	 * 删除一条关联记录（并自动删除其他版本知识点关联）
	 * @author wm
	 * @date 2019-5-13 上午11:56:49
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delRelate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		LoreRelateManager lrm = (LoreRelateManager)AppFactory.instance(null).getApp(Constants.WEB_LORE_RELATE_INFO);
		LoreInfoManager lm = (LoreInfoManager) AppFactory.instance(null).getApp(Constants.WEB_LORE_INFO);
		Integer lrId = CommonTools.getFinalInteger("lrId", request);
		String msg = "noInfo";
		Map<String,String> map = new HashMap<String,String>();
		if(lrId > 0){
			LoreRelateInfo lr = lrm.getEntityById(lrId);
			if(lr != null){
				boolean flag = lrm.deleteLoreRelate(lrId);
				if(flag){
					msg = "success";
					Integer ediId = lr.getLoreInfo().getChapter().getEducation().getEdition().getId();//出版社
					//如果删除的知识点关联是通用版的话，那么其他的版本也需要相应删除
					if(ediId.equals(1)){
						Integer mainLoreId_ty = lr.getLoreInfo().getId();
						Integer rootLoreId_ty = lr.getRootLoreInfo().getId();
						List<LoreInfo> lList_main = lm.listInfoByMainLoreId(mainLoreId_ty);//获取其他版本下的主知识点列表
						List<LoreInfo> lList_root = lm.listInfoByMainLoreId(rootLoreId_ty);//获取其他版本下的关联知识点列表
						Integer num = 0;
						if(lList_main.size() == lList_root.size()){
							num = lList_main.size();//如果记录条数相等，随便取那个都可
						}else{
							//如果记录条数不等，取记录条数少的为准
							if(lList_main.size() > lList_root.size()){
								num = lList_root.size();
							}else{
								num = lList_main.size();
							}
						}
						if(num > 0){
							for(Integer i = 0 ; i < num ; i++){
								Integer mainLoreId_edi = lList_main.get(i).getId();
								Integer rootLoreId_edi = lList_root.get(i).getId();
								List<LoreRelateInfo>  lrList = lrm.listRelateInfoByOpt(mainLoreId_edi, rootLoreId_edi, -1, "");
								if(lrList.size() > 0){
									lrm.deleteLoreRelate(lrList.get(0).getId());
								}
							}
						}
					}
					//如果是其他版本的话就不需要
				}else{
					msg = "error";
				}
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return  null;
	}
	
	/**
	 * 增加一条关联信息（并自动创建其他版本知识点关联）
	 * @author wm
	 * @date 2019-5-13 下午04:06:21
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addRelate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		LoreRelateManager lrm = (LoreRelateManager)AppFactory.instance(null).getApp(Constants.WEB_LORE_RELATE_INFO);
		LoreInfoManager lm = (LoreInfoManager) AppFactory.instance(null).getApp(Constants.WEB_LORE_INFO);
		LoreRelateLogManager lrlm = (LoreRelateLogManager) AppFactory.instance(null).getApp(Constants.WEB_LORE_RELATE_LOG_INFO);
		Integer loreId = CommonTools.getFinalInteger("loreId", request);
		Integer rootLoreId = CommonTools.getFinalInteger("rootLoreId", request);
		String msg = "error";
		List<String> list_result = new ArrayList<String>();
		Map<String,Object> map = new HashMap<String,Object>();
		if(loreId > 0 && rootLoreId > 0){
			if(lrm.listRelateInfoByOpt(loreId, rootLoreId, -1,"").size() == 0){
				lrm.addLoreRelate(loreId, rootLoreId,"manu");
				LoreInfo lore = lm.getEntityById(loreId);
				if(lore != null){
					Integer ediId = lore.getChapter().getEducation().getEdition().getId();//出版社
					if(ediId.equals(1)){//通用版
						//自动生成其他版本的关联记录
						List<LoreInfo> lList_main = lm.listInfoByMainLoreId(loreId);//获取其他版本下的主知识点列表
						List<LoreInfo> lList_root = lm.listInfoByMainLoreId(rootLoreId);//获取其他版本下的关联知识点列表
						Integer num = lList_main.size();
						Integer num1 = lList_root.size();
//						if(lList_main.size() == lList_root.size()){
//							num = lList_main.size();//如果记录条数相等，随便取那个都可
//						}else{
//							//如果记录条数不等，取记录条数少的为准
//							if(lList_main.size() > lList_root.size()){
//								num = lList_root.size();
//							}else{
//								num = lList_main.size();
//							}
//						}
						if(num > 0 && num1 > 0){
							for(Integer i = 0 ; i < num ; i++){
								Integer mainLoreId_edi = lList_main.get(i).getId();
								Edition edi = lList_main.get(i).getChapter().getEducation().getEdition();
								Integer mainLoreEdiId = edi.getId();
								String mainLoreEdiName = edi.getEdiName();
								Long mainLoreCode =  Long.parseLong(lList_main.get(i).getLoreCode().replace("-", ""));
								boolean addFlag = false;
								for(Integer j = 0 ; j < num1 ; j++){
									Integer rootLoreId_edi = lList_root.get(j).getId();
									Integer rootLoreEdiId = lList_root.get(j).getChapter().getEducation().getEdition().getId();
									if(mainLoreEdiId.equals(rootLoreEdiId)){//相同出版社
										Long rootLoreCode =  Long.parseLong(lList_root.get(j).getLoreCode().replace("-", ""));
										if(mainLoreCode > rootLoreCode){//主知识点编码大于子知识点编码
											List<LoreRelateInfo>  lrList = lrm.listRelateInfoByOpt(mainLoreId_edi, rootLoreId_edi, -1, "");
											if(lrList.size() == 0){
												lrm.addLoreRelate(mainLoreId_edi, rootLoreId_edi,"auto");
												addFlag = true;
											}
											break;
										}
									}
								}
								if(addFlag == false){//创建失败
									list_result.add(mainLoreEdiName+"下["+lList_main.get(i).getLoreName()+"]知识点关联失败!失败原因：子知识点编码大于主知识点编码");
									//增加记录至关联结果日志表
									lrlm.addLRL(mainLoreId_edi, "add", 0, mainLoreEdiName+"下["+lList_main.get(i).getLoreName()+"]知识点关联失败!失败原因：子知识点编码大于主知识点编码", CommonTools.getLoginAccount(request));
								}else{
									list_result.add(mainLoreEdiName+"下["+lList_main.get(i).getLoreName()+"]知识点关联成功!");
									lrlm.addLRL(mainLoreId_edi, "add", 1, mainLoreEdiName+"下["+lList_main.get(i).getLoreName()+"]知识点关联成功!", CommonTools.getLoginAccount(request));
								}
							}
						}
					}else{
						//其他版本无需其他动作
					}
				}
				msg = "success";
			}else{
				msg = "existInfo";
			}
		}
		map.put("result", msg);
		if(msg.equals("success")){
			map.put("relateList", list_result);
		}
		CommonTools.getJsonPkg(map, response);
		return  null;
	}
	
	/**
	 * 自动创建其他版本知识点关联(生成其他版本知识点的时候)
	 * @author wm
	 * @date 2019-5-20 上午08:16:43
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward autoAddEdiRelate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		LoreRelateManager lrm = (LoreRelateManager)AppFactory.instance(null).getApp(Constants.WEB_LORE_RELATE_INFO);
		//获取通用版知识点关联
		Integer subId = CommonTools.getFinalInteger("subId", request);
		Integer ediId = CommonTools.getFinalInteger("ediId", request);
		if(subId > 0){
			List<LoreRelateInfo> lrList = lrm.listInfoByOpt(subId, 1, "");//获取通用版本知识点的关联信息
			if(lrList.size() > 0){
				
			}
		}
		return null;
	}
	
	/**
	 * 自动创建通用版关联（未开启）
	 * @author wm
	 * @date 2019-5-18 下午04:09:27
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward autoAddTyRelate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		/**
		 * 数学：1-2年级（青岛10），3-9年级（鲁教版3），10-12年级（人教版2）
		 * 化学：6-9年级（鲁教版3），10-12年级（人教版2）
		 * 物理：6-9年级（沪科版11），10-12年级（人教版2）
		 */
		LoreRelateManager lrm = (LoreRelateManager)AppFactory.instance(null).getApp(Constants.WEB_LORE_RELATE_INFO);
		LoreInfoManager lm = (LoreInfoManager) AppFactory.instance(null).getApp(Constants.WEB_LORE_INFO);
		Integer subId = CommonTools.getFinalInteger("subId", request);//数学2，物理4，化学5，生物7
		Integer ediId = CommonTools.getFinalInteger("ediId", request);
		String gradeNoArea = CommonTools.getFinalStr("gradeNoArea", request);//1,2,n
		List<LoreRelateInfo> lrList = lrm.listInfoByOpt(subId, ediId, gradeNoArea);
		Map<String,String> map = new HashMap<String,String>();
		Integer i = 1;
		Integer errorNum = 0;
		Integer noReplaceNum = 0;
		if(lrList.size() > 0){
			for(Iterator<LoreRelateInfo> it = lrList.iterator() ; it.hasNext();){
				LoreRelateInfo lr = it.next();
//				System.out.println("第"+(i++)+"条记录："+lr.getId() + "--" + lr.getLoreInfo().getId() + "--(" + lr.getLoreInfo().getMainLoreId() + ")--" + lr.getRootLoreInfo().getId() + "--(" + lr.getRootLoreInfo().getMainLoreId() + ")");
				Integer loreId_edi_main = lr.getLoreInfo().getId(); //关联主知识点
				Integer loreId_edi_root = lr.getRootLoreInfo().getId();//关联子知识点
				Integer loreId_ty_main = lr.getLoreInfo().getMainLoreId();//通用主知识点
				Integer loreId_ty_root = lr.getRootLoreInfo().getMainLoreId();//通用关联知识点
				if(loreId_ty_main.equals(0) || loreId_ty_root.equals(0)){
					errorNum++;
					Integer right_loreId_edi_main = loreId_ty_main;
					Integer right_loreId_edi_root = loreId_ty_root;
					if(loreId_ty_main.equals(0)){
						//获取同当前出版社一致的知识点
						LoreInfo lore = lm.getLoreInfoByOpt(loreId_edi_main, ediId);
						if(lore != null){
							right_loreId_edi_main = lore.getId();
						}else{
							noReplaceNum++;
						}
					}
					if(loreId_ty_root.equals(0)){
						//获取同当前出版社一致的知识点
						LoreInfo lore = lm.getLoreInfoByOpt(loreId_edi_root, ediId);
						if(lore != null){
							right_loreId_edi_root = lore.getId();
						}else{
							noReplaceNum++;
						}
					}
					System.out.println("-------------------------错误记录："+lr.getId() + "------------------------------" + loreId_edi_main + "--(" + loreId_ty_main + ")--(正确知识点--- "+right_loreId_edi_main+")" + loreId_edi_root + "--(" + loreId_ty_root + ")--(正确知识点--- "+right_loreId_edi_root+")");
				}else{
					System.out.println("第"+(i++)+"条正确记录："+lr.getId() + "--" + loreId_edi_main + "--(" + loreId_ty_main + ")--" + loreId_edi_root + "--(" + loreId_ty_root + ")");
				}
//				LoreInfo lore_main = lm.getEntityById(loreId_edi_main);
//				LoreInfo lore_root = lm.getEntityById(loreId_edi_root);
//				if(lore_main != null){
//					Integer loreId_ty_main = lore_main.getMainLoreId();//通用版的主知识点
//					Integer loreId_ty_root = lore_root.getMainLoreId();//通用版的关联知识点
//					if(lrm.listRelateInfoByOpt(loreId_ty_main, loreId_ty_root, -1, "").size() == 0){
//						lrm.addLoreRelate(loreId_ty_main, loreId_ty_root);
//					}
//				}
			}
			System.out.println("关联错误知识点数量: "+errorNum);
			System.out.println("无法替换关联错误知识点数量: "+noReplaceNum);
			map.put("result", "success");
		}else{
			map.put("result", "noInfo");
		}
		CommonTools.getJsonPkg(map, response);
		return null;
	}
}