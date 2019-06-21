package com.zsd.action.base;

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
import com.zsd.module.GradeSubject;
import com.zsd.module.StudentParentInfo;
import com.zsd.module.StudyAllTjInfo;
import com.zsd.module.StudyStuQfTjInfo;
import com.zsd.module.StudyStuTjInfo;
import com.zsd.module.UserClassInfo;
import com.zsd.service.GradeSubjectManager;
import com.zsd.service.LoreQuestionManager;
import com.zsd.service.RelationZdResultManager;
import com.zsd.service.SchoolManager;
import com.zsd.service.StudentParentInfoManager;
import com.zsd.service.StudyAllTjInfoManager;
import com.zsd.service.StudyDetailManager;
import com.zsd.service.StudyLogManager;
import com.zsd.service.StudyStuQfTjManager;
import com.zsd.service.StudyStuTjInfoManager;
import com.zsd.service.UserClassInfoManager;
import com.zsd.tools.CommonTools;
import com.zsd.tools.Convert;
import com.zsd.tools.CurrentTime;
import com.zsd.util.Constants;
/** 
 * @author zong
 * @version 2019年6月11日 下午4:30:04
 */
public class ReportCenterAction  extends DispatchAction{
	/**
	 * 能力报告页面
	 * @author zong
	 * 2019年6月11日下午4:32:37
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showNLPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return mapping.findForward("nlPage");
	}
	/**
	 * 获取学生能力报告
	 * @author zong
	 * 2019年6月11日下午4:34:10
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getNLData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StudyAllTjInfoManager sAllTjInfoManager = (StudyAllTjInfoManager) AppFactory.instance(null).getApp(Constants.WEB_STUDY_ALL_TJ_INFO);
		StudyStuTjInfoManager stuTjInfoManager = (StudyStuTjInfoManager) AppFactory.instance(null).getApp(Constants.WEB_STUDY_STU_TJ_INFO);
		UserClassInfoManager ucManager = (UserClassInfoManager) AppFactory.instance(null).getApp(Constants.WEB_USER_CLASS_INFO);
		GradeSubjectManager gsManager = (GradeSubjectManager) AppFactory.instance(null).getApp(Constants.WEB_GRADE_SUBJECT_INFO);
		Integer userId = CommonTools.getLoginUserId(request);
		Integer subId = CommonTools.getFinalInteger("subjectId",request);
		String startTime = CommonTools.getFinalStr("startTime",request);
		String endTime = CommonTools.getFinalStr("endTime",request);
		
		if(startTime.equals("")){//进入后不选择时间点查询
			//表示是默认的当前日期前3天的记录(包含当前，所以-2)
			startTime = CurrentTime.getFinalDate(CurrentTime.getStringDate(), -2);
			endTime = CurrentTime.getStringDate();
		}else{
			if(endTime.equals("")){
				endTime = CurrentTime.getStringDate();
			}
		}
		//1:通过userId获取学生所在班级
		List<UserClassInfo> ucList = ucManager.listUcInfoByUserId(userId);
		String buildClassDate  = "";
		String gradeName = "";
		if(!ucList.isEmpty()){
			buildClassDate = ucList.get(0).getClassInfo().getBuildeClassDate();
			//通过班级创建时间转为年级
			gradeName = Convert.dateConvertGradeName(buildClassDate);
		}
		if(subId==0){
			//2:通过年级获得年级所在学科
			List<GradeSubject> gsList = gsManager.listSpecInfoByGname(gradeName);
			for (GradeSubject gs : gsList) {
				if(gs.getSubject().getSubName().equals("数学")){
					subId = gs.getSubject().getId();
				}
			}	
		}
		//统计新方法
		//从数据库中查询该学生在指定学科指定时间段内的学习统计情况
		List<StudyStuTjInfo> stuTjlist = stuTjInfoManager.listInfoByOpt(userId, subId, startTime, endTime);
		//从数据库中查询该学科在指定时间段内所有学生的学生统计情况
		List<StudyAllTjInfo> aAllTjlist = sAllTjInfoManager.findInfoByOpt(subId, startTime, endTime);
		
		List<ReportDetail> nlReport = new ReportDetail().getNLReport(stuTjlist, aAllTjlist);
		int days = 0;
		if(startTime != "" && endTime != ""){
			days = CurrentTime.compareDate(startTime, endTime)+1;
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("nlReport", nlReport);
		map.put("diffDays", days);
		CommonTools.getJsonPkg(map, response);
		return null;
	}

	/**
	 * 获取勤奋统计报告数据
	 * @author wm
	 * @date 2019-6-20 上午11:03:53
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getQfTjData(ActionMapping mapping ,ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		StudyLogManager slm = (StudyLogManager)AppFactory.instance(null).getApp(Constants.WEB_STUDY_LOG_INFO);
		SchoolManager sm = (SchoolManager) AppFactory.instance(null).getApp(Constants.WEB_SCHOOL_INFO);
		LoreQuestionManager lqm = (LoreQuestionManager) AppFactory.instance(null).getApp(Constants.WEB_LORE_QUESTION_INFO);
		StudyDetailManager sdm = (StudyDetailManager) AppFactory.instance(null).getApp(Constants.WEB_STUDY_DETAIL_INFO);
		RelationZdResultManager rzrm = (RelationZdResultManager)AppFactory.instance(null).getApp(Constants.WEB_RELATION_ZD_RESULT_INFO);
		StudyStuQfTjManager tjm = (StudyStuQfTjManager)AppFactory.instance(null).getApp(Constants.WEB_STUDY_STU_QFTJ_INFO);
		StudentParentInfoManager spm = (StudentParentInfoManager) AppFactory.instance(null).getApp(Constants.WEB_STUDENT_PARENT_INFO);
		UserClassInfoManager ucm = (UserClassInfoManager)AppFactory.instance(null).getApp(Constants.WEB_USER_CLASS_INFO);
		Integer userId = CommonTools.getLoginUserId(request);
		Integer roleId = CommonTools.getLoginRoleId(request);
		//家长学生传递参数学科，时间段
		//班内老师传递参数所教学科，班级，时间段
		//各级关联员，上级能看下级，下级能看上级学科，省，市，县，学段，学校，年级，班级，学生，时间段
		Integer subId = 0;
		String prov = "";
		String city = "";
		String county = "";
		Integer schoolType = 0;//小学(1),初中(2),高中(3)
		Integer schoolId = 0;
		String gradeName = "";
		Integer classId = 0;
		String sDate = "";
		String eDate = "";
		
		//学生个人的统计信息
		Integer oneZdSuccNum = 0;//一次性通过总数
		Integer oneZdFailNum = 0;//一次性未通过总数
		Integer againXxSuccNum = 0;//再次诊断(学习)通过
		Integer againXxFailNum = 0;//再次诊断(学习)未通过
		Integer noRelateNum = 0;//未溯源个数
		Integer relateZdFailNum = 0;//关联诊断未通过
		Integer relateXxSuccNum = 0;//关联学习通过
		Integer relateXxFailNum = 0;//关联未学习通过
		
		//总体统计信息
		Integer oneZdSuccNumAll = 0;//一次性通过总数
		Integer oneZdFailNumAll = 0;//一次性未通过总数
		Integer againXxSuccNumAll = 0;//再次诊断(学习)通过
		Integer againXxFailNumAll = 0;//再次诊断(学习)未通过
		Integer noRelateNumAll = 0;//未溯源个数
		Integer relateZdFailNumAll = 0;//关联诊断未通过
		Integer relateXxSuccNumAll = 0;//关联学习通过
		Integer relateXxFailNumAll = 0;//关联未学习通过
		
		String rate = "";//转化率
		String rateAll = "";
		String msg = "error";
		Map<String,Object> map = new HashMap<String,Object>();
		if(roleId > 0 && userId > 0){
			//学生和学生所在班级的统计信息进行对比
			if(roleId.equals(2) || roleId.equals(6)){//学生\家长
				if(roleId.equals(6)){//家长
					StudentParentInfo sp = spm.getEntityByParId(userId);
					if(sp != null){//获取自己孩子的id
						userId = sp.getStu().getId();
						classId = ucm.getEntityByOpt(userId, 2).getClassInfo().getId();
					}
				}
				List<StudyStuQfTjInfo> tjList = tjm.listInfoByOpt(0, subId, sDate, eDate, prov, city, county, schoolType, schoolId, gradeName, classId);
				if(tjList.size() > 0){
					msg = "success";
					Integer allNum = tjList.size();
					for(StudyStuQfTjInfo qftj : tjList){
						oneZdSuccNumAll += qftj.getOneZdSuccNum();
						oneZdFailNumAll += qftj.getOneZdFailNum();
						againXxSuccNumAll += qftj.getAgainXxSuccNum();
						againXxFailNumAll += qftj.getAgainXxFailNum();
						noRelateNumAll += qftj.getNoRelateNum();
						relateZdFailNumAll += qftj.getRelateZdFailNum();
						relateXxSuccNumAll += qftj.getRelateXxSuccNum();
						relateXxFailNumAll += qftj.getRelateXxFailNum();
						if(qftj.getUser().getId().equals(userId)){
							oneZdSuccNum += qftj.getOneZdSuccNum();
							oneZdFailNum += qftj.getOneZdFailNum();
							againXxSuccNum += qftj.getAgainXxSuccNum();
							againXxFailNum += qftj.getAgainXxFailNum();
							noRelateNum += qftj.getNoRelateNum();
							relateZdFailNum += qftj.getRelateZdFailNum();
							relateXxSuccNum += qftj.getRelateXxSuccNum();
							relateXxFailNum += qftj.getRelateXxFailNum();
						}
					}
					Integer fmNum = oneZdFailNum + relateZdFailNum;//一次性通过总数+关联诊断未通过
					Integer againXxSuccNum_real = againXxSuccNum;//再次诊断学习通过次数
					if(fmNum > 0 && againXxSuccNum_real > 0){
						rate = Convert.convertInputNumber_1(againXxSuccNum_real * 100.0  / fmNum) + "%";//转换率
					}
					
					Integer fmNumAll = oneZdFailNumAll + relateZdFailNumAll;//一次性通过总数+关联诊断未通过
					Integer againXxSuccNum_real_all = againXxSuccNumAll;//再次诊断学习通过次数
					if(fmNumAll > 0 && againXxSuccNum_real_all > 0){
						rateAll = Convert.convertInputNumber_1(againXxSuccNum_real_all * 100.0  / fmNumAll) + "%";//转换率
					}
					
					map.put("oneZdSuccNum", oneZdSuccNum);
					map.put("oneZdFailNum", oneZdFailNum);
					map.put("againXxSuccNum", againXxSuccNum);
					map.put("againXxFailNum", againXxFailNum);
					map.put("noRelateNum", noRelateNum);
					map.put("relateZdFailNum", relateZdFailNum);
					map.put("relateXxSuccNum", relateXxSuccNum);
					map.put("relateXxFailNum", relateXxFailNum);
					map.put("rate", rate);
					
					map.put("oneZdSuccNumAll", Convert.convertInputNumber_2(oneZdSuccNumAll * 1.0 / allNum));
					map.put("oneZdFailNumAll", Convert.convertInputNumber_2(oneZdFailNumAll * 1.0 / allNum));
					map.put("againXxSuccNumAll", Convert.convertInputNumber_2(againXxSuccNumAll * 1.0 / allNum));
					map.put("againXxFailNumAll", Convert.convertInputNumber_2(againXxFailNumAll * 1.0 / allNum));
					map.put("noRelateNumAll", Convert.convertInputNumber_2(noRelateNumAll * 1.0 / allNum));
					map.put("relateZdFailNumAll", Convert.convertInputNumber_2(relateZdFailNumAll * 1.0 / allNum));
					map.put("relateXxSuccNumAll", Convert.convertInputNumber_2(relateXxSuccNumAll * 1.0 / allNum));
					map.put("relateXxFailNumAll", Convert.convertInputNumber_2(relateXxFailNumAll * 1.0 / allNum));
					map.put("rateAll", rateAll);
				}else{
					msg = "noInfo";
				}
			}else if(roleId.equals(4)){//老师(班内)
				//一年级一班和一年级所有班级的平均值对比
			}else if(roleId.equals(5)){//各级管理员
				//学科必须选择，学段可隔空选择，比如省管理 员可不选市县乡而直接选择学段，那就是指定省下指定学段和全国所有省指定学段的平均值进行对比
				//当为省时，需要和全国所有省份平均值进行对比(河南省和全国省份平均值对比)
				//当为省和市时，需要和该省下所有市的平均值进行对比(濮阳市和河南省所有市平均值进行对比)
				//当为省市县时，需要和该市下所有县的平均值进行对比(范县和濮阳市下所有县平均值进行对比)
				//当为省市县学段(小学)时，需要和该市下所有县所有指定学段的平均值进行对比(濮阳市范县所有小学和濮阳市所有县的小学对比)
				//当为省市县学段(小学)学校时，需要和该县下所有指定学段(小学)所有学校的平均值进行对比(范县小学油田八小和范县所有小学进行对比)
				//当为省市县学段(小学)学校年级时，需要和该县下所有指定学段的指定年级平均值进行对比(范县小学油田八小一年级和范县所有小学一年级平均值进行对比)
				//当为省市县学段(小学)学校年级班级时，需要当前学校指定班级所在的年级平均值进行对比(油田八小一年级一班和油田八小一年级平均值进行对比)
				//当为省市县学段(小学)学校年级班级学生时，需要学生和学生所在的班级平均值进行对比(油田八小一年级一班某某学生和油田八小一年级一班的平均值进行对比)
			}
		}
		map.put("result", msg);
		return null;
	}
	
}
