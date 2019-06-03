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

import com.kpoint.vo.LoreQuestionVO;
import com.kpoint.vo.LoreVO;
import com.kpoint.vo.StudyDetailVO;
import com.zsd.factory.AppFactory;
import com.zsd.module.Chapter;
import com.zsd.module.ClassInfo;
import com.zsd.module.Edition;
import com.zsd.module.Education;
import com.zsd.module.GradeSubject;
import com.zsd.module.LoreInfo;
import com.zsd.module.LoreQuestion;
import com.zsd.module.StuSubjectEduInfo;
import com.zsd.module.StudyDetailInfo;
import com.zsd.module.StudyLogInfo;
import com.zsd.module.StudyTaskInfo;
import com.zsd.module.Subject;
import com.zsd.module.User;
import com.zsd.module.UserClassInfo;
import com.zsd.module.json.LoreTreeMenu;
import com.zsd.module.json.LoreTreeMenuJson;
import com.zsd.module.json.MyTreeNode;
import com.zsd.service.ChapterManager;
import com.zsd.service.EditionManager;
import com.zsd.service.EducationManager;
import com.zsd.service.GradeSubjectManager;
import com.zsd.service.LoreInfoManager;
import com.zsd.service.LoreQuestionManager;
import com.zsd.service.StuSubjectEduManager;
import com.zsd.service.StudyDetailManager;
import com.zsd.service.StudyLogManager;
import com.zsd.service.StudyTaskManager;
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
	
	/**
	 * 在线学习中章节知识点选择
	 * @author wm
	 * @date 2019-5-28 上午11:34:26
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goChaptePage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Integer eduId = CommonTools.getFinalInteger("eduId", request);//教材编号
		request.setAttribute("eduId", eduId);
		return mapping.findForward("cptPage");
	}
	
	/**
	 * 根据教材编号或者章节信息
	 * @author wm
	 * @date 2019-5-28 下午03:15:44
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getStudyCptData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		EducationManager edum = (EducationManager) AppFactory.instance(null).getApp(Constants.WEB_EDUCATION_INFO);
		LoreInfoManager lm = (LoreInfoManager)AppFactory.instance(null).getApp(Constants.WEB_LORE_INFO);
		ChapterManager cm = (ChapterManager) AppFactory.instance(null).getApp(Constants.WEB_CHAPTER_INFO);
		StudyLogManager slm = (StudyLogManager) AppFactory.instance(null).getApp(Constants.WEB_STUDY_LOG_INFO);
		Integer eduId = CommonTools.getFinalInteger("eduId", request);//教材编号
		List<Chapter> cptList = cm.ListInfoByEduId(eduId);
		String msg = "noInfo";
		Map<String,Object> map = new HashMap<String,Object>();
		List<Education> eduList = edum.listSpecInfoById(eduId);
		if(eduList.size() > 0){
			Education edu = eduList.get(0);
			map.put("eduInfo", edu.getGradeSubject().getGradeName() + edu.getGradeSubject().getSubject().getSubName() + edu.getEduVolume());
			if(cptList.size() > 0){
				msg = "success";
				List<Object> list_d = new ArrayList<Object>();
				Integer i = 1;
				for(Chapter cpt : cptList){
					
					Map<String,Object> map_d = new HashMap<String,Object>();
					map_d.put("cptId", cpt.getId());
					map_d.put("cptName", cpt.getChapterName());
					if(i.equals(1)){
						List<LoreInfo> lList = lm.listInfoByCptId(cpt.getId());
						List<Object> list_d_1 = new ArrayList<Object>();
						for(LoreInfo lore : lList){
							Map<String,Object> map_d_1 = new HashMap<String,Object>();
							map_d_1.put("loreId", lore.getId());
							map_d_1.put("loreName", lore.getLoreName());
							List<StudyLogInfo> slList = slm.listLastStudyInfoByOpt(CommonTools.getLoginUserId(request), lore.getId(), 1);
							//0:未学习,1:未通过,2:已经掌握
							if(slList.size() > 0){
								map_d_1.put("studyStatus", slList.get(0).getIsFinish());
							}else{
								map_d_1.put("studyStatus", 0);
							}
							list_d_1.add(map_d_1);
						}
						map_d.put("loreList", list_d_1);
					}
					list_d.add(map_d);
					i++;
				}
				map.put("cptList", list_d);
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 获取指定章节下知识点列表
	 * @author wm
	 * @date 2019-5-28 下午05:20:16
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
		LoreInfoManager lm = (LoreInfoManager)AppFactory.instance(null).getApp(Constants.WEB_LORE_INFO);
		StudyLogManager slm = (StudyLogManager) AppFactory.instance(null).getApp(Constants.WEB_STUDY_LOG_INFO);
		Integer cptId = CommonTools.getFinalInteger("cptId", request);//章节编号
		Integer stuId = CommonTools.getLoginUserId(request);
		String msg = "noInfo";
		Map<String,Object> map = new HashMap<String,Object>();
		List<LoreInfo> lList = lm.listInfoByCptId(cptId);
		if(lList.size() > 0){
			msg = "success";
			List<Object> list_d = new ArrayList<Object>();
			for(LoreInfo lore : lList){
				Map<String,Object> map_d = new HashMap<String,Object>();
				map_d.put("loreId", lore.getId());
				map_d.put("loreName", lore.getLoreName());
				List<StudyLogInfo> slList = slm.listLastStudyInfoByOpt(stuId, lore.getId(), 1);
				//0:未学习,1:未通过,2:已经掌握
				if(slList.size() > 0){
					map_d.put("studyStatus", slList.get(0).getIsFinish());
					map_d.put("studyLogId", slList.get(0).getId());//studyLogId
				}else{
					map_d.put("studyStatus", 0);
					map_d.put("studyLogId", 0);
				}
				list_d.add(map_d);
			}
			map.put("cptList", list_d);
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 检查一个知识点一个学生只能完成一次（自学）true-可以继续学习
	 * @author wm
	 * @date 2019-5-29 上午10:31:51
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkCurrentLore(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		StudyLogManager slm = (StudyLogManager) AppFactory.instance(null).getApp(Constants.WEB_STUDY_LOG_INFO);
		Integer loreId = CommonTools.getFinalInteger("loreId", request);
		Integer studyLogId = CommonTools.getFinalInteger("studyLogId", request);
		Integer stuId = CommonTools.getLoginUserId(request);
		boolean studyFlag = false;
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		//自学的知识点一天只能完成一次，家庭作业只要状态是完成就不能再做(家庭不用在这判断)
		List<StudyLogInfo> slList = new ArrayList<StudyLogInfo>();
		if(studyLogId > 0){
			slList.add(slm.getEntityById(studyLogId));
		}else{
			slList = slm.listLastStudyInfoByOpt(stuId, loreId, 1);
		}
		if(slList.size() == 0){
			studyFlag = true;
		}else{
			StudyLogInfo sl = slList.get(0);
			Integer isFinish = sl.getIsFinish();
			String addTime = sl.getAddTime();
			if(isFinish.equals(1)){//未完成
				studyFlag = true;
			}else{//已完成
				//匹配时间
				addTime = addTime.substring(0, 10);
				if(!addTime.equals(CurrentTime.getStringDate())){
					studyFlag = true;
				}
			}
		}
		map.put("studyFlag", studyFlag);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	
	/**
	 * 进入学习地图页面(传递当前学习知识点编号和学习记录编号)
	 * @author wm
	 * @date 2019-5-30 上午08:19:25
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goStudyMapPage(ActionMapping mapping ,ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer loreId = CommonTools.getFinalInteger("loreId", request);
		Integer studyLogId = CommonTools.getFinalInteger("studyLogId", request);
		request.setAttribute("loreId", loreId);
		request.setAttribute("studyLogId", studyLogId);
		return mapping.findForward("studyMapPage");
	}
	
	/**
	 * 获取指定学习记录的学习任务
	 * @author wm
	 * @date 2019-5-29 下午06:01:43
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getStudyTaskInfo(ActionMapping mapping ,ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StudyTaskManager stm = (StudyTaskManager)AppFactory.instance(null).getApp(Constants.WEB_STUDY_TASK_INFO);
		StudyLogManager slm = (StudyLogManager)AppFactory.instance(null).getApp(Constants.WEB_STUDY_LOG_INFO);
		Integer loreId =  CommonTools.getFinalInteger("loreId", request);
		Integer studyLogId = CommonTools.getFinalInteger("studyLogId", request);
		Integer loretType = CommonTools.getFinalInteger("loreType", request);//1:自学（默认不传）,2:家庭作业
		if(loretType.equals(0)){
			loretType = 1;
		}
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "noInfo";
		StudyLogInfo sl = null;
		if(studyLogId > 0){
			sl = slm.getEntityById(studyLogId);
		}else{
			List<StudyLogInfo> slList = slm.listLastStudyInfoByOpt(CommonTools.getLoginUserId(request), loreId, loretType);
			if(slList.size() > 0){
				sl = slList.get(0);
			}
		}
		if(sl != null){//表示已经有记录
			//获取任务描述列表
			List<StudyTaskInfo> stList = stm.listTaskInfoByOpt(sl.getId(), "");
			if(stList.size() > 0){
				msg = "success";
				List<Object> list_d = new ArrayList<Object>();
				for(StudyTaskInfo st : stList){
					Map<String,Object> map_d = new HashMap<String,Object>();
					map_d.put("taskName", st.getTaskName());
					map_d.put("coin", st.getCoin());
					list_d.add(map_d);
				}
				map.put("taskList", list_d);
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 获取学习地图数据
	 * @author wm
	 * @date 2019-5-30 上午09:11:32
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getStudyMapData(ActionMapping mapping ,ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StudyLogManager slm = (StudyLogManager)AppFactory.instance(null).getApp(Constants.WEB_STUDY_LOG_INFO);
		LoreInfoManager lm = (LoreInfoManager)AppFactory.instance(null).getApp(Constants.WEB_LORE_INFO);
		LoreQuestionManager lqm = (LoreQuestionManager) AppFactory.instance(null).getApp(Constants.WEB_LORE_QUESTION_INFO);
		StudyDetailManager sdm = (StudyDetailManager) AppFactory.instance(null).getApp(Constants.WEB_STUDY_DETAIL_INFO);
		Integer loreId =  CommonTools.getFinalInteger("loreId", request);//知识点最初的编号
		Integer studyLogId = CommonTools.getFinalInteger("studyLogId", request);//学习记录编号
		Integer currentLoreId =  0;//当前知识点编号
		Integer nextLoreId = 0;//下级子知识点编号
		Integer stuId = CommonTools.getLoginUserId(request);
		String msg = "error";
		String path = "";//针对性诊断的路线
		String studyPath = "";//学习的路线
		Integer stepCount = 0;//知识点有多少级
		Integer loreCount = 0;//有多少知识点
		Integer isFinish = 0;//该知识点完成状态（1:未完成,2:已完成）
		Integer task = 1;//第几个任务数（课后复习任务数）
		Integer money = 10;
		String loreTaskName = "";
		String buttonValue = "开始挑战";
		String pathType = "diagnosis";//类型:diagnosis--诊断，study--学习
		String loreTypeName = "针对性诊断";
		Integer access = -1;//本级知识点完成状态
		Integer quoteLoreId = 0;//通用知识点
		String nextLoreIdArray = "";//下级知识典编号数组
		if(loreId > 0){
			LoreInfo lore = lm.getEntityById(loreId);
			if(lore != null){
				if(lore.getInUse().equals(0)){//知识点有效才能继续
					quoteLoreId = lore.getMainLoreId();//通用知识点才有题
					LoreTreeMenuJson ltmj = new LoreTreeMenuJson();
					List<MyTreeNode> ltList = ltmj.showTree(loreId, 0,"desc");
					StringBuilder buff = new StringBuilder();
					ltmj.getPath(ltList, buff);
					path = buff.delete(buff.length() - 1, buff.length()).toString();
					if(!path.equals("")){
						stepCount = path.split(":").length;//多少级
						loreCount = ltmj.getLoreNum(path);//多少个知识点
					}
					studyPath = ltmj.getStudyPath(path);
					StudyLogInfo sl = null;
					if(studyLogId.equals(0)){//新诊断
						List<StudyLogInfo> slList = slm.listLastStudyInfoByOpt(stuId, loreId, 1);
						if(slList.size() > 0){
							sl = slList.get(0);
						}
					}else{//之前有记录
						sl = slm.getEntityById(studyLogId);
					}
					if(sl != null){//表示存在记录
						List<LoreQuestion> lqList = new ArrayList<LoreQuestion>();
						isFinish = sl.getIsFinish();
						if(isFinish == 2){//为学习和已经掌握都表示要重新开始
							task = 1;
							loreTaskName = "针对性诊断";
							loreTypeName = "针对性诊断";
							lqList = lqm.listInfoByLoreId(quoteLoreId, loreTypeName, 0);
							money *= lqList.size();
							currentLoreId = loreId;
						}else{//未通过，需要定位到现在需要学习的地方
							Integer step = sl.getStep();//答题阶段--（针对性诊断-1、关联性诊断-2、关联知识点学习-3、本知识点学习-4、再次诊断-5）
							Integer stepComplete = sl.getStepComplete();//本阶段整体完成情况--0:未完成,1:已完成
							access = sl.getAccess();//本阶段详细完成情况（溯源诊断时分级完成情况）
							//从detail表中获取指定logId的最后一条详情
							List<StudyDetailInfo> sdList = sdm.listInfoByLogId(studyLogId);
							if(sdList.size() > 0){
								//获取该题对应的知识点编号
								currentLoreId = sdList.get(sdList.size() - 1).getLoreInfo().getId();
								if(stepComplete == 0){//0:表示本阶段未完成（未做完题标记）
									if(step == 1){//诊断题未做完---loreId==currentLoreId
										buttonValue = "继续诊断";
										loreTaskName = "针对性诊断";
										loreTypeName = "针对性诊断";
										lqList = lqm.listInfoByLoreId(quoteLoreId, loreTypeName, 0);
										money *= lqList.size();
										task = 1;
										currentLoreId = loreId;
										nextLoreIdArray = currentLoreId + "";
									}else if(step == 2){//表示已经开始下级关联子知识点的诊断loreId不等于currentLoreId
										Integer answerNumber = 0;//题数
										String[] pathArray = path.split(":");
										Integer currentI = CommonTools.getCurrentStep(pathArray,currentLoreId);
										String[] currentPathArray = null;
										if(access == 0){//表示关联性诊断当前级还未完成。
											currentPathArray = pathArray[currentI].split("\\|");
										}else{//表示关联性诊断当前级已经完成，需要进行关联性诊断的下一级子知识点的关联性诊断
											currentPathArray = pathArray[currentI + 1].split("\\|");
											currentI = currentI + 1;
										}
										Integer currentPathLength = currentPathArray.length;
										task = currentI + 1;
										buttonValue = "继续诊断";
										loreTypeName = "针对性诊断";
										loreTaskName = task - 1+"级关联知识点诊断";
										for(Integer k = 0 ; k < currentPathLength ; k++){
											nextLoreIdArray += currentPathArray[k] + ",";
											lqList = lqm.listInfoByLoreId(CommonTools.getQuoteLoreId(Integer.parseInt(currentPathArray[k])), loreTypeName, 0);
											answerNumber += lqList.size();
										}
										money *= answerNumber;
										nextLoreIdArray = nextLoreIdArray.substring(0, nextLoreIdArray.length() - 1);
									}else if(step == 3){//关联知识点学习
										if(access == 4){//第一次进入再次诊断(列出再次诊断的全部试题)
											task = sl.getTaskNumber();
											pathType = "diagnosis";
											buttonValue = "再次诊断";
											loreTypeName = "再次诊断";
											String[] pathArray = path.split(":");
											Integer currentStep = CommonTools.getCurrentStep(pathArray, currentLoreId);
											Integer stepNumber = currentStep;
											LoreInfo lore_temp = lm.getEntityById(currentLoreId);
											loreTaskName = stepNumber +"级关联知识点("+lore_temp.getLoreName()+")诊断";
											//2014-10-22日修改（获取该知识典所有类型为loreTypeName的题型[0为题状态为有效状态]）
											List<LoreQuestion> zcList = lqm.listInfoByLoreId(CommonTools.getQuoteLoreId(currentLoreId), loreTypeName, 0);
											money *= zcList.size();
											nextLoreIdArray = String.valueOf(currentLoreId);
										}else if(access == 41){//开始学习
											task = sl.getTaskNumber();
											pathType = "study";
											buttonValue = "开始学习";
											loreTypeName = "再次诊断";
											String[] pathArray = path.split(":");
											
											Integer currentStep = CommonTools.getCurrentStep(pathArray, currentLoreId);
											Integer stepNumber = currentStep;

											LoreInfo lore_temp = lm.getEntityById(currentLoreId);
											loreTaskName = stepNumber +"级关联知识点("+lore_temp.getLoreName()+")学习";
											money = 0;
											nextLoreIdArray = String.valueOf(currentLoreId);
										}else if(access == 3){//之前有做过再次诊断，但未全部正确（做错题的不重复列表）
											task = sl.getTaskNumber();
											pathType = "diagnosis";
											buttonValue = "继续诊断";
											loreTypeName = "再次诊断";
											String[] pathArray = path.split(":");
											
											Integer currentStep = CommonTools.getCurrentStep(pathArray, currentLoreId);
											Integer  stepNumber = currentStep;
											LoreInfo lore_temp = lm.getEntityById(currentLoreId);
											loreTaskName = stepNumber +"级关联知识点("+lore_temp.getLoreName()+")诊断";
											//2014-10-22日修改（获取该知识典所有类型为loreTypeName的题型[0为题状态为有效状态]）
											//最对的题
//											List<StudyDetailVO> sdList_current_right = sdManager.listCurrentRightInfoByLogId(studyLogId, currentLoreId, loreTypeName);
//											//该知识点类型为再次诊断的全部题
//											List<LoreQuestionVO> zcList = lqm.listInfoByOption(this.getQuoteLoreId(currentLoreId), loreTypeName, 0);
//											//该知识点答对的题
//											money *= zcList.size() - sdList_current_right.size();
//											nextLoreIdArray = String.valueOf(currentLoreId);
										}
									}
								}
							}
						}
					}
				}else{
					msg = "inUseError";//知识点无效，不能继续11
				}
			}
		}
		return null;
	}
}