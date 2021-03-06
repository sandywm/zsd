/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.zsd.action.chapter;

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
import com.zsd.service.ChapterManager;
import com.zsd.service.EducationManager;
import com.zsd.tools.CommonTools;
import com.zsd.tools.Convert;
import com.zsd.util.Constants;

/** 
 * MyEclipse Struts
 * Creation date: 05-03-2019
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class ChapterAction extends DispatchAction {
	
	/**
	 * 导向章节管理页面
	 * @author  Administrator
	 * @ModifiedBy  
	 * @date  2019-5-3 下午10:07:32
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward goChapterPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return mapping.findForward("chapterPage");
	}
	
	/**
	 * 获取章节列表数据
	 * @author  Administrator
	 * @ModifiedBy  
	 * @date  2019-5-3 下午10:07:57
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward getChapterData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ChapterManager cm = (ChapterManager) AppFactory.instance(null).getApp(Constants.WEB_CHAPTER_INFO);
		Integer eduId = CommonTools.getFinalInteger("eduId", request);
		String msg = "暂无记录";
		Map<String,Object> map = new HashMap<String,Object>();
		List<Chapter> cList = cm.ListInfoByEduId(eduId);
		if(cList.size() > 0){
			msg = "success";
			List<Object> list_d = new ArrayList<Object>();
			for(Iterator<Chapter> it = cList.iterator() ; it.hasNext();){
				Chapter cpt = it.next();
				Map<String,Object> map_d = new HashMap<String,Object>();
				map_d.put("id", cpt.getId());
				map_d.put("cptName", cpt.getChapterName());
				map_d.put("cptOrder", cpt.getChapterOrder());
				list_d.add(map_d);
			}
			map.put("data", list_d);
			map.put("count", cList.size());
			map.put("code", 0);
		}
		map.put("msg", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 根据指定的教材编号获取章节列表(学习时)
	 * @author  Administrator
	 * @ModifiedBy  
	 * @date  2019-5-3 下午10:20:53
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getSpecChapterData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ChapterManager cm = (ChapterManager) AppFactory.instance(null).getApp(Constants.WEB_CHAPTER_INFO);
		Integer eduId = CommonTools.getFinalInteger("eduId", request);
		String msg = "noInfo";
		Map<String,Object> map = new HashMap<String,Object>();
		if(eduId > 0){
			List<Chapter> cList = cm.ListInfoByEduId(eduId);
			if(cList.size() > 0){
				msg = "success";
				List<Object> list_d = new ArrayList<Object>();
				for(Iterator<Chapter> it = cList.iterator() ; it.hasNext();){
					Chapter cpt = it.next();
					Map<String,Object> map_d = new HashMap<String,Object>();
					map_d.put("id", cpt.getId());
					map_d.put("cptName", cpt.getChapterName());
					map_d.put("cptOrder", cpt.getChapterOrder());
					list_d.add(map_d);
				}
				map.put("cptList", list_d);
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 增加章节
	 * @author  Administrator
	 * @ModifiedBy  
	 * @date  2019-5-3 下午10:29:56
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addChapterData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ChapterManager cm = (ChapterManager) AppFactory.instance(null).getApp(Constants.WEB_CHAPTER_INFO);
		Integer eduId = CommonTools.getFinalInteger("eduId", request);
		String cptName = Transcode.unescape_new("cptName", request);//第##单元:章节名称
		Integer cptOrder = CommonTools.getFinalInteger("cptOrder", request);
		Map<String,String> map = new HashMap<String,String>();
		List<Chapter> cList = cm.listInfoByOpt(eduId, cptName);
		String msg = "error";
		if(cList.size() > 0){
			msg = "existInfo";
		}else{
			msg = "success";
			cm.addChapter(eduId, cptName, cptOrder);
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 获取指定章节编号的详细信息
	 * @author  Administrator
	 * @ModifiedBy  
	 * @date  2019-5-3 下午10:40:06
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getChapterDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ChapterManager cm = (ChapterManager) AppFactory.instance(null).getApp(Constants.WEB_CHAPTER_INFO);
		Integer cptId = CommonTools.getFinalInteger("cptId", request);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "noInfo";
		if(cptId > 0){
			Chapter cpt = cm.getEntityById(cptId);
			if(cpt != null){
				msg = "success";
				map.put("cptId", cpt.getId());
				String cptName = cpt.getChapterName();
				map.put("cptNameCon", cptName.split(":")[1]);
				map.put("cptNamePre", cptName.split(":")[0]);
				map.put("cptOrder", cpt.getChapterOrder());
				map.put("eduId", cpt.getEducation().getId());
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 修改指定章节信息
	 * @author  Administrator
	 * @ModifiedBy  
	 * @date  2019-5-3 下午10:38:24
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateChapterData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ChapterManager cm = (ChapterManager) AppFactory.instance(null).getApp(Constants.WEB_CHAPTER_INFO);
		Integer cptId = CommonTools.getFinalInteger("cptId", request);
		String cptName = Transcode.unescape_new("cptName", request);//第##单元:章节名称
		Integer cptOrder = CommonTools.getFinalInteger("cptOrder", request);
		Integer eduId =  CommonTools.getFinalInteger("eduId", request);
		Map<String,String> map = new HashMap<String,String>();
		String msg = "error";
		if(cptId > 0){
			Chapter cpt = cm.getEntityById(cptId);
			if(cpt != null){
				if(!cpt.getChapterName().equals(cptName)){//章节名称发生变化-需要进行名称检查
					List<Chapter> cList = cm.listInfoByOpt(eduId, cptName);
					if(cList.size() > 0){
						msg = "existInfo";
					}else{
						msg = "scucess";
						cm.updateChapter(cptId, cptName, cptOrder);
					}
				}else{
					msg = "success";
					cm.updateChapter(cptId, cptName, cptOrder);
				}
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 获取指定教材下下次出现的章节排序号、单元前缀中文名称
	 * @author Administrator
	 * @date 2019-5-5 上午09:18:59
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getCurrentMaxOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ChapterManager cm = (ChapterManager) AppFactory.instance(null).getApp(Constants.WEB_CHAPTER_INFO);
		Integer eduId = CommonTools.getFinalInteger("eduId", request);
		List<Chapter> cList = cm.ListInfoByEduId(eduId);
		Integer currentOrder = 1;
		if(cList.size() > 0){
			currentOrder = cList.get(cList.size() - 1).getChapterOrder() + 1;
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cptOrder", currentOrder);
		map.put("cptNamePre", Convert.NunberConvertChapterName(currentOrder));
		CommonTools.getJsonPkg(map, response);
	    return null;
	}
}