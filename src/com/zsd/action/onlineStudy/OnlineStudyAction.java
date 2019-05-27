/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.zsd.action.onlineStudy;

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
import com.zsd.module.ClassInfo;
import com.zsd.module.Edition;
import com.zsd.module.Education;
import com.zsd.module.GradeSubject;
import com.zsd.module.StuSubjectEduInfo;
import com.zsd.module.Subject;
import com.zsd.module.User;
import com.zsd.module.UserClassInfo;
import com.zsd.service.EditionManager;
import com.zsd.service.EducationManager;
import com.zsd.service.GradeSubjectManager;
import com.zsd.service.StuSubjectEduManager;
import com.zsd.service.UserClassInfoManager;
import com.zsd.tools.CommonTools;
import com.zsd.tools.Convert;
import com.zsd.tools.CurrentTime;
import com.zsd.util.Constants;

/** 
 * MyEclipse Struts
 * Creation date: 05-24-2019
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class OnlineStudyAction extends DispatchAction {
	
	/**
	 * 导向在线学习页面
	 * @author wm
	 * @date 2019-5-24 下午04:22:51
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goStudyPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return mapping.findForward("studyPage");
	}
	
	/**
	 * 获取在线学习初始数据
	 * @author wm
	 * @date 2019-5-24 下午04:23:41
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getInitStudyData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		GradeSubjectManager gsm = (GradeSubjectManager) AppFactory.instance(null).getApp(Constants.WEB_GRADE_SUBJECT_INFO);
		UserClassInfoManager ucm = (UserClassInfoManager)AppFactory.instance(null).getApp(Constants.WEB_USER_CLASS_INFO);
		EditionManager em = (EditionManager) AppFactory.instance(null).getApp(Constants.WEB_EDITION_INFO);
		EducationManager edum = (EducationManager) AppFactory.instance(null).getApp(Constants.WEB_EDUCATION_INFO);
		StuSubjectEduManager ssem = (StuSubjectEduManager)  AppFactory.instance(null).getApp(Constants.WEB_STU_SUB_EDU_INFO);
		Integer userId = CommonTools.getLoginUserId(request);
		Integer roleId = CommonTools.getLoginRoleId(request);
		String roleName = CommonTools.getLoginRoleName(request);
		userId = 1;
		roleId = 2;
		roleName = "学生";
		Map<String,Object> map = new HashMap<String,Object>();
		Integer subId = CommonTools.getFinalInteger("subId", request);//学科编号
		Integer ediId = CommonTools.getFinalInteger("ediId", request);//出版社编号
		Integer gradeNumber = CommonTools.getFinalInteger("gradeNumber", request);//高三初三复习时传递过来的年级编号
		String opt = "manu";//手动选择
		List<Object> list_edu = new ArrayList<Object>();
		String msg = "error";
		Integer gsId_curr = 0;
		if(roleName.equals("学生")){
			msg = "success";
			String gradeName = "";
			//获取该学生的班级,然后获取该班级所在年级
			UserClassInfo uc = ucm.getEntityByOpt(userId, roleId);
			ClassInfo c = null;
			if(uc != null){
				c = uc.getClassInfo();
				User user = uc.getUser();
				Integer remainDays = CurrentTime.compareDate(CurrentTime.getStringDate(), user.getEndDate());
				Integer freeStatus = user.getFreeStatus();//0:收费,1:免费
				if(gradeNumber.equals(0)){//如果页面没传递，直接通过学生获取
					gradeNumber = Convert.dateConvertGradeNumber(c.getBuildeClassDate());
				}
				if(gradeNumber > 0){
					if(gradeNumber > 12){
						gradeNumber = 12;
					}
					if(subId.equals(0)){
						opt = "init";//初始加载
						subId = 2;//默认为数学
					}
					gradeName = Convert.NunberConvertChinese(gradeNumber);
					//获取当前年级对应的学科列表
					List<GradeSubject>  gsList = gsm.listSpecInfoByGname(gradeName);
					List<Object> list_sub = new ArrayList<Object>();
					List<Object> list_edi = new ArrayList<Object>();
					if(gsList.size() > 0){
						for(Iterator<GradeSubject> it = gsList.iterator() ; it.hasNext();){
							GradeSubject gs = it.next();
							Subject sub = gs.getSubject();
							Map<String,Object> map_d = new HashMap<String,Object>();
							map_d.put("subId", sub.getId());
							if(sub.getId().equals(subId)){//默认为数学
								gsId_curr = gs.getId();
								map_d.put("selFlag", true);
							}else{
								map_d.put("selFlag", false);
							}
							map_d.put("subName", sub.getSubName());
							map_d.put("subImg", sub.getImgUrl());
							list_sub.add(map_d);
						}
						map.put("subList", list_sub);
						if(opt.equals("init")){//打开在线学习页面初始化加载时
							//获取学生学科教材信息列表
							List<StuSubjectEduInfo> sseList = ssem.listInfoByOpt(userId, subId);
							if(sseList.size() > 0){
								msg = "success";
								for(StuSubjectEduInfo sse : sseList){
									Education edu_study = sse.getEducation();
									Integer ediId_study = edu_study.getEdition().getId();
									if(ediId.equals(0)){
										ediId = ediId_study;
									}
									List<Education> eduList = edum.listInfoByOpt(ediId, gsId_curr);//获取当前年级学科、出版社下的教材信息
									if(eduList.size() > 0){
										for(Integer i = 0 ; i < eduList.size() ; i++){
											Education edu = eduList.get(i);
											ssem.updateSSEById(sse.getId(), edu.getId());
											Map<String,Object> map_d = new HashMap<String,Object>();
											map_d.put("eduId", edu.getId());
											Subject sub = edu.getGradeSubject().getSubject();
											map_d.put("subName", sub.getSubName());
											map_d.put("subImg", sub.getImgUrl());
											map_d.put("eduVolume", edu.getEduVolume());
											map_d.put("remainDays", remainDays);
											map_d.put("freeStatus", freeStatus.equals(0) ? "免费试用/付费使用" : "免费使用");
											list_edu.add(map_d);
										}
										map.put("studyList", list_edu);
										map.put("selTxt", gradeName+"("+edu_study.getEdition().getEdiName()+")");
										break;
									}else{
										msg = "noInfo";
										map.put("selTxt", gradeName+"("+edu_study.getEdition().getEdiName()+")");
									}
								}
							}else{
								msg = "noStudyInfo";//mei
								map.put("selTxt", gradeName+"("+em.listInfoByShowStatus(2, -1).get(0).getEdiName()+")");
							}
						}else if(opt.equals("manu")){
							List<Education> eduList = edum.listInfoByOpt(ediId, gsId_curr);//获取当前年级学科、出版社下的教材信息
							if(eduList.size() > 0){
								msg = "success";
								//获取学生学科教材信息列表
								List<StuSubjectEduInfo> sseList = ssem.listInfoByOpt(userId, subId);
								if(sseList.size() > 0){
									for(Integer j = 0 ; j < sseList.size() ; j++){
										ssem.updateSSEById(sseList.get(j).getId(), eduList.get(j).getId());
									}
								}else{
									for(Integer i = 0 ; i < eduList.size() ; i++){
										ssem.addSSE(userId, subId, eduList.get(i).getId());
									}
								}
								for(Integer i = 0 ; i < eduList.size() ; i++){
									Education edu = eduList.get(i);
									Map<String,Object> map_d = new HashMap<String,Object>();
									map_d.put("eduId", edu.getId());
									Subject sub = edu.getGradeSubject().getSubject();
									map_d.put("subName", sub.getSubName());
									map_d.put("subImg", sub.getImgUrl());
									map_d.put("eduVolume", edu.getEduVolume());
									map_d.put("remainDays", remainDays);
									map_d.put("freeStatus", freeStatus.equals(0) ? "免费试用/付费使用" : "免费使用");
									list_edu.add(map_d);
								}
								map.put("studyList", list_edu);
								map.put("selTxt", gradeName+"("+eduList.get(0).getEdition().getEdiName()+")");
							}else{
								msg = "noInfo";
								map.put("selTxt", gradeName+"("+em.listInfoByShowStatus(2, -1).get(0).getEdiName()+")");
							}
						}
						//获取出版社列表
						List<Edition> ediList = em.listInfoByShowStatus(0, 0);
						for(Iterator<Edition> it = ediList.iterator() ; it.hasNext();){
							Edition edi = it.next();
							Map<String,Object> map_d = new HashMap<String,Object>();
							map_d.put("ediId", edi.getId());
							map_d.put("ediName", edi.getEdiName());
							if(ediId.equals(edi.getId())){
								map_d.put("selFlag", true);
							}else{
								map_d.put("selFlag", false);
							}
							list_edi.add(map_d);
						}
						map.put("ediList", list_edi);
					}else{
						 msg = "error";
					}
				}else{
					 msg = "error";
				}
			}else{
				 msg = "error";
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
}