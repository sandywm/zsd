package com.zsd.action.base;

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
import com.zsd.module.ClassInfo;
import com.zsd.module.GradeSubject;
import com.zsd.module.StudentParentInfo;
import com.zsd.module.StudyAllTjInfo;
import com.zsd.module.StudyLogInfo;
import com.zsd.module.StudyStuQfTjInfo;
import com.zsd.module.StudyStuTjInfo;
import com.zsd.module.Subject;
import com.zsd.module.UserClassInfo;
import com.zsd.service.ClassInfoManager;
import com.zsd.service.GradeSubjectManager;
import com.zsd.service.SchoolManager;
import com.zsd.service.StudentParentInfoManager;
import com.zsd.service.StudyAllTjInfoManager;
import com.zsd.service.StudyLogManager;
import com.zsd.service.StudyStuQfTjManager;
import com.zsd.service.StudyStuTjInfoManager;
import com.zsd.service.SubjectManager;
import com.zsd.service.TownManager;
import com.zsd.service.UserClassInfoManager;
import com.zsd.service.UserManager;
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
		request.setAttribute("optPage", "nlTag");
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
		StudentParentInfoManager spm = (StudentParentInfoManager)AppFactory.instance(null).getApp(Constants.WEB_STUDENT_PARENT_INFO);
		GradeSubjectManager gsManager = (GradeSubjectManager) AppFactory.instance(null).getApp(Constants.WEB_GRADE_SUBJECT_INFO);
		Integer userId = CommonTools.getLoginUserId(request);
		Integer roleId = CommonTools.getLoginRoleId(request);
		Integer subId = CommonTools.getFinalInteger("subjectId",request);
		String startTime = CommonTools.getFinalStr("startTime",request);
		String endTime = CommonTools.getFinalStr("endTime",request);
		if(roleId.equals(Constants.PATENT_ROLE_ID)){//家长角色需要获取自己孩子的userId
			StudentParentInfo sp = spm.getEntityByParId(userId);
			if(sp != null){
				userId = sp.getStu().getId();//孩子的Id
			}
		}
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
		map.put("sDate", startTime);
		map.put("eDate", endTime);
		CommonTools.getJsonPkg(map, response);
		return null;
	}

	/**
	 * 跳转到勤奋报告页面
	 * @author wm
	 * @date 2019-12-13 下午03:12:33
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goQfbgPage(ActionMapping mapping ,ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("qfbgPage");
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
		StudyStuQfTjManager tjm = (StudyStuQfTjManager)AppFactory.instance(null).getApp(Constants.WEB_STUDY_STU_QFTJ_INFO);
		StudentParentInfoManager spm = (StudentParentInfoManager) AppFactory.instance(null).getApp(Constants.WEB_STUDENT_PARENT_INFO);
		SubjectManager sm = (SubjectManager) AppFactory.instance(null).getApp(Constants.WEB_SUBJECT_INFO);
		UserClassInfoManager ucm = (UserClassInfoManager)AppFactory.instance(null).getApp(Constants.WEB_USER_CLASS_INFO);
		SchoolManager schm = (SchoolManager) AppFactory.instance(null).getApp(Constants.WEB_SCHOOL_INFO);
 		UserManager um = (UserManager) AppFactory.instance(null).getApp(Constants.WEB_USER_INFO);
		ClassInfoManager cm = (ClassInfoManager) AppFactory.instance(null).getApp(Constants.WEB_CLASS_INFO);
		TownManager tm = (TownManager) AppFactory.instance(null).getApp(Constants.WEB_TOWN_INFO); 
		StudyLogManager slm = (StudyLogManager) AppFactory.instance(null).getApp(Constants.WEB_STUDY_LOG_INFO);
		Integer userId = CommonTools.getLoginUserId(request);//必须传
		Integer roleId = CommonTools.getLoginRoleId(request);//必须传
//		userId = 1;
//		roleId = 2;
		//家长学生传递参数学科，时间段
		//班内老师传递参数所教学科，班级，时间段
		//各级关联员，上级能看下级，下级能看上级学科，省，市，县，学段，学校，年级，班级，学生，时间段
		Integer subId = CommonTools.getFinalInteger("subId", request);//必须传(班内老师不用传)
		String prov = Transcode.unescape_new1("prov", request);
		String city = Transcode.unescape_new1("city", request);
		String county = Transcode.unescape_new1("county", request);
		String town = Transcode.unescape_new1("town", request);
		Integer schoolType = CommonTools.getFinalInteger("schoolType", request);//小学(1),初中(2),高中(3)
		Integer schoolId = CommonTools.getFinalInteger("schoolId", request);
		String gradeName = Transcode.unescape_new1("gradeName", request);
		Integer classId = CommonTools.getFinalInteger("classId", request);
		String sDate = CommonTools.getFinalStr("sDate", request);
		String eDate = CommonTools.getFinalStr("eDate", request);//默认最近7天
		Integer stuId = CommonTools.getFinalInteger("stuId", request);//选择学生（班内老师和层级管理员传递）
		
		//学生/家长时传递userId
		//班内老师时传递userId,stuId,gradeName,classId
		//各级管理员
		
		String contentInfo = "";
		Integer diffDays = 3;
		String axisName1 = "";//当前筛选的值（一年级一班王杰对比一年级一班的平均值）
		String axisName2 = "";//当前级别的平均值
		String subName = "";
		
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
		
		String rate = "0.00%";
        String rate_1 = "0.00%";
        String rateAll = "0.00%";
        String rateAll_1 = "0.00%";
		String msg = "error";
		Integer allNum = 1;
		Map<String,Object> map = new HashMap<String,Object>();
		if(roleId > 0){
			if(CommonTools.getCilentInfo_new(request).equals("pc")){
				if(sDate.equals("") && eDate.equals("")){
					eDate = CurrentTime.getStringDate();
					sDate = eDate.substring(0, 4) + "-01-01";
				}
				diffDays = CurrentTime.compareDate(sDate, eDate) + 1;
			}else{
				if(sDate.equals("") && eDate.equals("")){
					eDate = CurrentTime.getStringDate();
					sDate = CurrentTime.getFinalDate(-2);
				}else{
					diffDays = CurrentTime.compareDate(sDate, eDate) + 1;
				}
			}
			if(roleId.equals(Constants.TEA_ROLE_ID)){
				List<UserClassInfo> ucList = ucm.listTeaInfoByOpt(userId, roleId);
				if(ucList.size() > 0){
					subId = ucList.get(0).getSubjectId();
					subName = ucList.get(0).getSubjectName();
				}
			}else{
				if(subId > 0){
					List<Subject> subList = sm.listEntityById(subId);
					if(subList.size() > 0){
						subName = subList.get(0).getSubName();
					}
				}else{
					subId = Constants.MATH_ID;
					subName = "数学";
				}
			}
			contentInfo = "最近"+diffDays+"天"+subName+"的统计";
			List<StudyStuQfTjInfo> tjList = new ArrayList<StudyStuQfTjInfo>();
			//学生和学生所在班级的平均统计信息进行对比
			if(roleId.equals(Constants.STU_ROLE_ID) || roleId.equals(Constants.PATENT_ROLE_ID)){//学生\家长
				Integer schoolId_tmp = 0;
				if(userId > 0){
					stuId = userId;
					axisName1 = "我的统计";
					if(roleId.equals(Constants.PATENT_ROLE_ID)){//家长
						StudentParentInfo sp = spm.getEntityByParId(userId);
						if(sp != null){//获取自己孩子的id
							userId = sp.getStu().getId();
							stuId = userId;
							axisName1 = sp.getStu().getRealName()+"的统计";
						}
					}
					//获取学生所在的班级
					List<UserClassInfo> ucList = ucm.listInfoByOpt_1(userId, Constants.STU_ROLE_ID);
					if(ucList.size() > 0){
						UserClassInfo uc = ucList.get(0);
						schoolId_tmp = uc.getUser().getSchoolId();
						classId = uc.getClassInfo().getId();
						String className_temp = uc.getClassInfo().getClassName();
						axisName2 = Convert.dateConvertGradeName(uc.getClassInfo().getBuildeClassDate())+className_temp+"的统计";
//						allNum = tjm.getDistinctCountByOpt(0, subId, sDate, eDate, "", "", "", "", 0, 0, "", classId);
					}
					//学生和家长身份时，只需要用到起始时间，学科，班级编号
				}
				if(schoolId_tmp != 0){//其他学校不参与统计
					tjList = tjm.listInfoByOpt(0, subId, sDate, eDate, "", "", "", "", 0, 0, "", classId);//获取指定班级的统计信息
				}
			}else if(roleId.equals(Constants.TEA_ROLE_ID)){//老师(班内)
				//需要条件起始时间(必须)，学科(必须)，年级名称(不必须)，班级编号(必须)，学生编号(不必须)
				//当选择的是班级时--一年级一班和一年级所有班级的平均值对比
				//当选择的是班级列表下的学生时--学生和当前班级的平均统计信息进行对比
				if(stuId.equals(0)){//当选择的是班级时--一年级一班和一年级所有班级的平均值对比
					if(classId > 0){
						schoolId = um.listEntityById(userId).get(0).getSchoolId();
						//获取班内老师所在的年级
						List<ClassInfo> cList = cm.listClassInfoById(classId);
						if(cList.size() > 0){
							gradeName = Convert.dateConvertGradeName(cList.get(0).getBuildeClassDate());
							tjList = tjm.listInfoByOpt(0, subId, sDate, eDate, "", "", "", "", 0, schoolId, gradeName, 0);//获取指定学校指定年级的统计信息
							axisName1 = gradeName+cList.get(0).getClassName()+"的统计";
							axisName2 = gradeName+"的统计";
						}
//							allNum = tjm.getDistinctCountByOpt(0, subId, sDate, eDate, "", "", "", "", 0, schoolId, gradeName, 0);
					}
				}else{//当选择的是班级列表下的学生时--学生和当前班级的平均统计信息进行对比
					if(classId > 0){
						tjList = tjm.listInfoByOpt(0, subId, sDate, eDate, "", "", "", "", 0, 0, "", classId);//获取指定班级的统计信息
						List<ClassInfo> cList = cm.listClassInfoById(classId);
						if(cList.size() > 0){
							gradeName = Convert.dateConvertGradeName(cList.get(0).getBuildeClassDate());
							axisName1 = um.listEntityById(stuId).get(0).getRealName()+"的统计";
							axisName2 = gradeName+cList.get(0).getClassName()+"的统计";
//							allNum = tjm.getDistinctCountByOpt(0, subId, sDate, eDate, "", "", "", "", 0, 0, "", classId);
						}
					}
				}
			}else if(roleId.equals(5)){//各级管理员
				//1：学科必须选择，学段可隔空选择，比如省管理 员可不选市县乡而直接选择学段，那就是指定省下指定学段和全国所有省指定学段的平均值进行对比
				//2：当为省时，需要和全国所有省份平均值进行对比(河南省和全国省份平均值对比)
				//3：当为省和市时，需要和该省下所有市的平均值进行对比(濮阳市和河南省所有市平均值进行对比)
				//4：当为省市县时，需要和该市下所有县的平均值进行对比(范县和濮阳市下所有县平均值进行对比)
				//5：当为省市县乡时，需要和该县下所有乡的平均值进行对比（濮城镇和范县下所有乡的平均值进行对比）
				//5：当为省市县乡学段(小学)时，需要和该市下所有县所有指定学段的平均值进行对比(濮阳市范县濮城镇所有小学和濮阳市范县下所有乡镇的小学对比)
				//6：当为省市县乡学段(小学)学校时，需要和该县下所有指定学段(小学)所有学校的平均值进行对比(范县濮城镇小学油田八小和濮城镇所有小学进行对比)
				//7：当为省市县乡学段(小学)学校年级时，需要和该县下所有指定学段的指定年级平均值进行对比(范县小学油田八小一年级和范县所有小学一年级平均值进行对比)
				//8：当为省市县乡学段(小学)学校年级班级时，需要当前学校指定班级所在的年级平均值进行对比(油田八小一年级一班和油田八小一年级平均值进行对比)
				//9：当为省市县乡学段(小学)学校年级班级学生时，需要学生和学生所在的班级平均值进行对比(油田八小一年级一班某某学生和油田八小一年级一班的平均值进行对比)
				if(!prov.equals("")){
					String schoolTypeName = "";
					if(schoolType.equals(1)){
						schoolTypeName = "小学";
					}else if(schoolType.equals(2)){
						schoolTypeName = "初中";
					}else if(schoolType.equals(3)){
						schoolTypeName = "高中";
					}
					if(!city.equals("")){
						if(!county.equals("")){
							if(!town.equals("")){
								if(schoolId > 0){
									String schoolName = schm.listInfoById(schoolId).get(0).getSchoolName();
									if(!gradeName.equals("")){
										if(classId > 0){
											String className = cm.listClassInfoById(classId).get(0).getClassName();
											if(stuId > 0){
												String stuName = um.listEntityById(stuId).get(0).getRealName();
												tjList = tjm.listInfoByOpt(0, subId, sDate, eDate, "", "", "", "", 0, 0, "", classId);//获取指定班级的统计信息
//												allNum = tjm.getDistinctCountByOpt(0, subId, sDate, eDate, "", "", "", "", 0, 0, "", classId);
												axisName1 = prov+city+county+town+schoolName+gradeName+className+stuName+"的统计";
												axisName2 = prov+city+county+town+schoolName+gradeName+className+"的统计";
											}else{
												//当选择的是班级时--一年级一班和一年级所有班级的平均值对比
												tjList = tjm.listInfoByOpt(0, subId, sDate, eDate, "", "", "", "", 0, schoolId, gradeName, 0);//获取指定学校指定年级的统计信息
//												allNum = tjm.getDistinctCountByOpt(0, subId, sDate, eDate, "", "", "", "", 0, schoolId, gradeName, 0);
												axisName1 = prov+city+county+town+schoolName+gradeName+className+"的统计";
												axisName2 = prov+city+county+town+schoolName+gradeName+"的统计";
											}
										}else{
											//油田八小一年级和当前学校所处乡的所有小学一年级的平均值对比
											tjList = tjm.listInfoByOpt(0, subId, sDate, eDate, prov, city, county, town, schoolType, 0, gradeName, 0);//获取指定乡下所有指定年级的统计信息
//											allNum = tjm.getDistinctCountByOpt(0, subId, sDate, eDate, prov, city, county, town, schoolType, 0, gradeName, 0);
											axisName1 = prov+city+county+town+schoolName+gradeName+"的统计";
											axisName2 = prov+city+county+town+gradeName+"的统计";
										}
									}else{
										//schoolType必须是大于0
										//指定学校和指定乡下所有指定学段的平均值进行对比（小学油田八小和城关镇下所有小学平均值进行对比）
										tjList = tjm.listInfoByOpt(0, subId, sDate, eDate, prov, city, county, town, schoolType, 0, "", 0);//城关镇下所有小学
//										allNum = tjm.getDistinctCountByOpt(0, subId, sDate, eDate, prov, city, county, town, schoolType, 0, "", 0);
										axisName1 = prov+city+county+town+schoolName+"的统计";
										axisName2 = prov+city+county+town+schoolTypeName+"的统计";
									}
								}else{
									if(schoolType > 0){//当是省+市+县+乡+学段
										//指定乡下指定学段和指定县下所有乡的指定学段的平均值进行对比（范县城关镇所有小学和范县下所有乡的小学平均值进行对比）
										tjList = tjm.listInfoByOpt(0, subId, sDate, eDate, prov, city, county, "", schoolType, 0, "", 0);//范县下所有乡的小学的记录
//										allNum = tjm.getDistinctCountByOpt(0, subId, sDate, eDate, prov, city, county, "", schoolType, 0, "", 0);
										axisName1 = prov+city+county+town+schoolTypeName+"的统计";
										axisName2 = prov+city+county+schoolTypeName+"的统计";
									}else{
										//指定乡和当前县下所有乡的平均值进行对比（范县城关镇和范县下所有乡的平均值进行对比）
										tjList = tjm.listInfoByOpt(0, subId, sDate, eDate, prov, city, county, "", 0, 0, "", 0);//范县下所有乡的记录
//										allNum = tjm.getDistinctCountByOpt(0, subId, sDate, eDate, prov, city, county, "", 0, 0, "", 0);
										axisName1 = prov+city+county+town+"的统计";
										axisName2 = prov+city+county+"的统计";
									}
								}
							}else{
								if(schoolType > 0){//当是省+市+县+学段
									//指定县下指定学段和指定市下所有县的指定学段的平均值进行对比（濮阳市范县小学和濮阳市下所有县的小学平均值进行对比）
									tjList = tjm.listInfoByOpt(0, subId, sDate, eDate, prov, city, "", "", schoolType, 0, "", 0);//濮阳市下所有县的小学的记录
//									allNum = tjm.getDistinctCountByOpt(0, subId, sDate, eDate, prov, city, "", "", schoolType, 0, "", 0);
									axisName1 = prov+city+county+schoolTypeName+"的统计";
									axisName2 = prov+city+schoolTypeName+"的统计";
								}else{
									//指定县当前市下所有县的平均值进行对比（濮阳市范县和濮阳市下所有县的平均值进行对比）
									tjList = tjm.listInfoByOpt(0, subId, sDate, eDate, prov, city, "", "", 0, 0, "", 0);//濮阳市下所有县的记录
//									allNum = tjm.getDistinctCountByOpt(0, subId, sDate, eDate, prov, city, "", "", 0, 0, "", 0);
									axisName1 = prov+city+county+"的统计";
									axisName2 = prov+city+"的统计";
								}
							}
						}else{
							if(schoolType > 0){//当是省+市+学段
								//指定市下指定学段和指定省下所有市的指定学段的平均值进行对比（河南省濮阳市小学和河南省所有市的小学平均值进行对比）
								tjList = tjm.listInfoByOpt(0, subId, sDate, eDate, prov, "", "", "", schoolType, 0, "", 0);//河南省所有市的小学记录
//								allNum = tjm.getDistinctCountByOpt(0, subId, sDate, eDate, prov, "", "", "", schoolType, 0, "", 0);
								axisName1 = prov+city+schoolTypeName+"的统计";
								axisName2 = prov+schoolTypeName+"的统计";
							}else{
								//需要和该省下所有市的平均值进行对比(濮阳市和河南省所有市平均值进行对比)
								tjList = tjm.listInfoByOpt(0, subId, sDate, eDate, prov, "", "", "", 0, 0, "", 0);//河南省所有市的记录
//								allNum = tjm.getDistinctCountByOpt(0, subId, sDate, eDate, prov, "", "", "", 0, 0, "", 0);
								axisName1 = prov+city+"的统计";
								axisName2 = prov+"的统计";
							}
						}
					}else{//当市为空的时候
						if(schoolType > 0){//当是省+学段
							//指定省下指定学段和全国所有省指定学段的平均值进行对比（河南省小学和全国小学平均值进行对比）
							tjList = tjm.listInfoByOpt(0, subId, sDate, eDate, "", "", "", "", schoolType, 0, "", 0);//全国小学记录
//							allNum = tjm.getDistinctCountByOpt(0, subId, sDate, eDate, "", "", "", "", schoolType, 0, "", 0);
							axisName1 = prov+schoolTypeName+"的统计";
							axisName2 = "全国"+schoolTypeName+"的统计";
						}else{
							//需要和全国所有省份平均值进行对比(河南省和全国省份平均值对比)
							tjList = tjm.listInfoByOpt(0, subId, sDate, eDate, "", "", "", "", 0, 0, "", 0);//全国记录
//							allNum = tjm.getDistinctCountByOpt(0, subId, sDate, eDate, "", "", "", "", 0, 0, "", 0);
							axisName1 = prov+"的统计";
							axisName2 = "全国的统计";
						}
					}
				}
			}
			if(tjList.size() > 0){
				msg = "success";
				Integer specNum = 0;
				allNum = tjList.size();
				for(StudyStuQfTjInfo qftj : tjList){
					oneZdSuccNumAll += qftj.getOneZdSuccNum();
					oneZdFailNumAll += qftj.getOneZdFailNum();
					againXxSuccNumAll += qftj.getAgainXxSuccNum();
					againXxFailNumAll += qftj.getAgainXxFailNum();
					noRelateNumAll += qftj.getNoRelateNum();
					relateZdFailNumAll += qftj.getRelateZdFailNum();
					relateXxSuccNumAll += qftj.getRelateXxSuccNum();
					relateXxFailNumAll += qftj.getRelateXxFailNum();
					if(roleId.equals(Constants.STU_ROLE_ID) || roleId.equals(Constants.PATENT_ROLE_ID)){//学生\家长
						specNum = 1;//学生求总数，班级求平均值
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
					}else if(roleId.equals(Constants.TEA_ROLE_ID)){//老师(班内)
						boolean flag = false;
						if(stuId.equals(0)){//当选择的是班级时--一年级一班和一年级所有班级的平均值对比
							if(qftj.getClassInfo().getId().equals(classId)){
								flag = true;
							}
						}else{//当选择的是班级列表下的学生时--学生和当前班级的平均统计信息进行对比
							if(qftj.getUser().getId().equals(stuId)){
								flag = true;
							}
						}
						if(flag){
							oneZdSuccNum += qftj.getOneZdSuccNum();
							oneZdFailNum += qftj.getOneZdFailNum();
							againXxSuccNum += qftj.getAgainXxSuccNum();
							againXxFailNum += qftj.getAgainXxFailNum();
							noRelateNum += qftj.getNoRelateNum();
							relateZdFailNum += qftj.getRelateZdFailNum();
							relateXxSuccNum += qftj.getRelateXxSuccNum();
							relateXxFailNum += qftj.getRelateXxFailNum();
							if(stuId.equals(0)){
								specNum++;
							}else{
								specNum = 1;
							}
						}
					}else if(roleId.equals(5)){//各级管理员
						boolean flag = false;
						if(!prov.equals("")){
							//获取全国省份数量
							if(!city.equals("")){
								//获取指定省份下市的数量
								if(!county.equals("")){
									//获取全国指定市下面县的数量
									if(!town.equals("")){
										//获取全国指定县下面乡数量
										if(schoolId > 0){
											//获取全部指定乡下面指定学段的学校数量
//											allNum = schm.getCountByOpt("", "", "", "", town, schoolType, 0, 0);
											if(!gradeName.equals("")){//无需判断学段
												//获取全国指定乡下面指定学段指定年级的数量
//												Integer gradeNo = Integer.parseInt(Convert.ChineseConvertNumber(gradeName));
//												String buildClassDate = Convert.numberConvertBuildClassDate(gradeNo);
//												List<ClassInfo> cList = cm.listClassInfoByOpt(buildClassDate, town,0);
//												List<ClassInfo> list_d = new ArrayList<ClassInfo>();
//												for(Integer i = 0; i <  cList.size() ; i++){
//													if(i.equals(0)){
//														list_d.add(cList.get(i));
//													}else{
//														boolean existFlag = false;
//														for(ClassInfo c : list_d){
//															if(c.getSchool().getId().equals(cList.get(i).getSchool().getId())){
//																existFlag = true;
//																break;
//															}
//														}
//														if(!existFlag){
//															list_d.add(cList.get(i));
//														}
//													}
//												}
//												allNum = list_d.size();
												if(classId > 0){//无需判断学段
													if(stuId > 0){//无需判断学段
														if(qftj.getUser().getId().equals(stuId)){
															flag = true;
														}
													}else{
														if(qftj.getClassInfo().getId().equals(classId)){
															flag = true;
														}
													}
												}else{
													if(qftj.getGradeName().equals(gradeName)){
														flag = true;
													}
												}
											}else{
												if(qftj.getSchool().getId().equals(schoolId)){
													flag = true;
												}
											}
										}else{
											if(qftj.getTown().equals(town)){
												if(schoolType > 0){
													if(schoolType.equals(qftj.getSchoolType())){
														flag = true;
													}
												}else{
													flag = true;
												}
											}
										}
									}else{
										if(qftj.getCounty().equals(county)){
											if(schoolType > 0){
												if(schoolType.equals(qftj.getSchoolType())){
													flag = true;
												}
											}else{
												flag = true;
											}
										}
									}
								}else{
									if(qftj.getCity().equals(city)){
										if(schoolType > 0){
											if(schoolType.equals(qftj.getSchoolType())){
												flag = true;
											}
										}else{
											flag = true;
										}
									}
								}
							}else{
								if(qftj.getProv().equals(prov)){
									if(schoolType > 0){
										if(schoolType.equals(qftj.getSchoolType())){
											flag = true;
										}
									}else{
										flag = true;
									}
								}
							}
						}
						if(flag){
							oneZdSuccNum += qftj.getOneZdSuccNum();
							oneZdFailNum += qftj.getOneZdFailNum();
							againXxSuccNum += qftj.getAgainXxSuccNum();
							againXxFailNum += qftj.getAgainXxFailNum();
							noRelateNum += qftj.getNoRelateNum();
							relateZdFailNum += qftj.getRelateZdFailNum();
							relateXxSuccNum += qftj.getRelateXxSuccNum();
							relateXxFailNum += qftj.getRelateXxFailNum();
							if(stuId.equals(0)){
								specNum++;
							}else{
								specNum = 1;
							}
						}
					}
				}
				Double oneZdFailNum_new = 0.0;
				Double relateZdFailNum_new = 0.0;
				Double fmNum = 0.0;//一次性通过总数+关联诊断未通过
				Double againXxSuccNum_real = 0.0;//再次诊断学习通过次数
				Double oneZdSuccNum_1 = 0.0;
				Double againXxFailNum_1 = 0.0;
				Double noRelateNum_1 = 0.0;
				Double relateXxSuccNum_1 = 0.0;
				Double relateXxFailNum_1 = 0.0;
				if(specNum > 0){
					specNum = 1;
					oneZdFailNum_new = Convert.convertInputNumber_2(oneZdFailNum * 1.0 / specNum);//一次诊断未通过
					relateZdFailNum_new = Convert.convertInputNumber_2(relateZdFailNum * 1.0 / specNum);//关联诊断未通过
					againXxSuccNum_real = Convert.convertInputNumber_2(againXxSuccNum * 1.0 / specNum);//再次诊断学习通过次数
					oneZdSuccNum_1 = Convert.convertInputNumber_2(oneZdSuccNum * 1.0 / specNum);//一次诊断通过次数
					againXxFailNum_1 = Convert.convertInputNumber_2(oneZdFailNum_new - againXxSuccNum_real);//再次诊断（学习）未通过等于一次性未通过总数-再次诊断（学习）通过
					if(againXxFailNum_1 < 0){
						againXxFailNum_1 = 0.0;
					}
					if(noRelateNum < 0){
						noRelateNum = 0;
					}
					noRelateNum_1 = Convert.convertInputNumber_2(noRelateNum * 1.0 / specNum);
					relateXxSuccNum_1 = Convert.convertInputNumber_2(relateXxSuccNum * 1.0 / specNum);
					if(relateZdFailNum_new < relateXxSuccNum_1){
						relateZdFailNum_new = relateXxSuccNum_1;
					}
					relateXxFailNum_1 = Convert.convertInputNumber_2(relateZdFailNum_new - relateXxSuccNum_1);//关联学习未通过等于关联诊断未通过总数-关联学习通过
					if(relateXxFailNum_1 < 0){
						relateXxFailNum_1 = 0.0;
					}
				}
				if(oneZdFailNum_new > 0){
					//本知识点转换率=再次学习通过/一次性未通过
					rate = Convert.convertInputNumber_1(againXxSuccNum_real * 100.0  / oneZdFailNum_new) + "%";//转换率
					//关联知识点转换率=关联学习通过/关联诊断未通过
					rate_1 = Convert.convertInputNumber_1(relateXxSuccNum_1 * 100.0  / relateZdFailNum_new) + "%";//转换率
				}
				Double oneZdFailNumAll_new = 0.0;
				Double relateZdFailNumAll_new = 0.0;
				Double fmNumAll = 0.0;//一次性通过总数+关联诊断未通过
				Double againXxSuccNum_real_all = 0.0;//再次诊断学习通过次数
				Double oneZdSuccNum_all_1 = 0.0;
				Double againXxFailNum_all_1 = 0.0;
				Double noRelateNum_all_1 = 0.0;
				Double relateXxSuccNum_all_1 = 0.0;
				Double relateXxFailNum_all_1 = 0.0;
				if(allNum > 0){
					if(stuId > 0){//管理员指定学生或者学生 自己 登录时
						//获取该同学全部的学生数量
						allNum = um.getCountByOpt("", "", "", "", "", 0, schoolId, 0, classId, 0);
					}else if(classId > 0){//班级时
						//获取当前班级所在年级下班级的数量
						Integer gradeNo = Integer.parseInt(Convert.ChineseConvertNumber(gradeName));
						String buildClassDate = Convert.numberConvertBuildClassDate(gradeNo);
						allNum = cm.listClassInfoByOpt(buildClassDate, "",schoolId).size();
					}else if(!gradeName.equals("")){//指定年级时
						//获取指定乡镇下的指定年级数量
						Integer gradeNo = Integer.parseInt(Convert.ChineseConvertNumber(gradeName));
						String buildClassDate = Convert.numberConvertBuildClassDate(gradeNo);
						List<ClassInfo> cList = cm.listClassInfoByOpt(buildClassDate, town,0);
						List<ClassInfo> list_d = new ArrayList<ClassInfo>();
						for(Integer i = 0; i <  cList.size() ; i++){
							if(i.equals(0)){
								list_d.add(cList.get(i));
							}else{
								boolean existFlag = false;
								for(ClassInfo c : list_d){
									if(c.getSchool().getId().equals(cList.get(i).getSchool().getId())){
										existFlag = true;
										break;
									}
								}
								if(!existFlag){
									list_d.add(cList.get(i));
								}
							}
						}
						allNum = list_d.size();
					}else if(schoolId > 0){//指定学校时
						//获取指定乡镇下指定学段的学校数量
						allNum = schm.getCountByOpt("", prov, city, county, town, schoolType, 0, 0);
					}else if(!town.equals("")){//指定乡镇时
						//获取指定该镇说在县下所有乡镇的数量
						allNum = tm.listInfoByCountyName(county).size();
					}else if(!county.equals("")){//指定县时
						//获取指定该县所在市下所有县的数量
						allNum = CommonTools.getSpecProvJson(prov,city).split(",").length;
					}else if(!city.equals("")){//指定市时
						//获取指定市和该市所在省下所有市的数量
						allNum = CommonTools.getSpecProvJson(prov,"").split(",").length;
					}else{
						allNum = CommonTools.getSpecProvJson("","").split(",").length;
					}
					oneZdFailNumAll_new = Convert.convertInputNumber_2(oneZdFailNumAll * 1.0 / allNum);
					relateZdFailNumAll_new = Convert.convertInputNumber_2(relateZdFailNumAll * 1.0 / allNum);
					againXxSuccNum_real_all = Convert.convertInputNumber_2(againXxSuccNumAll * 1.0 / allNum);//再次诊断学习通过次数
					oneZdSuccNum_all_1 = Convert.convertInputNumber_2(oneZdSuccNumAll * 1.0 / allNum);
					againXxFailNum_all_1 = Convert.convertInputNumber_2(oneZdFailNumAll_new - againXxSuccNum_real_all);//再次诊断（学习）未通过等于一次性未通过总数-再次诊断（学习）通过
					if(noRelateNumAll < 0){
						noRelateNumAll = 0;
					}
					noRelateNum_all_1 = Convert.convertInputNumber_2(noRelateNumAll * 1.0 / allNum);
					relateXxSuccNum_all_1 = Convert.convertInputNumber_2(relateXxSuccNumAll * 1.0 / allNum);//关联学习
					if(relateZdFailNumAll_new < relateXxSuccNum_all_1){
						relateZdFailNumAll_new = relateXxSuccNum_all_1;
					}
					relateXxFailNum_all_1 = Convert.convertInputNumber_2(relateZdFailNumAll_new - relateXxSuccNum_all_1);//关联学习未通过等于关联诊断未通过总数-关联学习通过
				}
				if(oneZdFailNum_new > 0){
					//本知识点转换率=再次学习通过/一次性未通过
					rateAll = Convert.convertInputNumber_1(againXxSuccNum_real_all * 100.0  / oneZdFailNumAll_new) + "%";//转换率
					//关联知识点转换率=关联学习通过/关联诊断未通过
					rateAll_1 = Convert.convertInputNumber_1(relateXxSuccNum_all_1 * 100.0  / relateZdFailNumAll_new) + "%";//转换率
				}
				map.put("oneZdSuccNum", oneZdSuccNum_1);
				map.put("oneZdFailNum", oneZdFailNum_new);
				map.put("againXxSuccNum", againXxSuccNum_real);
				map.put("againXxFailNum", againXxFailNum_1);
				map.put("noRelateNum", noRelateNum_1);
				map.put("relateZdFailNum", relateZdFailNum_new);
				map.put("relateXxSuccNum", relateXxSuccNum_1);
				map.put("relateXxFailNum", relateXxFailNum_1);
				map.put("rate", rate);
				map.put("rate_gl", rate_1);
				map.put("axisName1", axisName1);
				
				map.put("oneZdSuccNumAll", oneZdSuccNum_all_1);
				map.put("oneZdFailNumAll", oneZdFailNumAll_new);
				map.put("againXxSuccNumAll", againXxSuccNum_real_all);
				map.put("againXxFailNumAll", againXxFailNum_all_1);
				map.put("noRelateNumAll", noRelateNum_all_1);
				map.put("relateZdFailNumAll", relateZdFailNumAll_new);
				map.put("relateXxSuccNumAll", relateXxSuccNum_all_1);
				map.put("relateXxFailNumAll", relateXxFailNum_all_1);
				map.put("rateAll", rateAll);
				map.put("rateAll_gl", rateAll_1);
				map.put("axisName2", axisName2);
				map.put("contentInfo", contentInfo);
			}else{
				msg = "noInfo";
			}
		}
		map.put("result", msg);
		map.put("sDate", sDate);
		map.put("eDate", eDate);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
}
