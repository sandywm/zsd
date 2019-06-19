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
import com.zsd.module.StudyAllTjInfo;
import com.zsd.module.StudyStuTjInfo;
import com.zsd.module.UserClassInfo;
import com.zsd.service.GradeSubjectManager;
import com.zsd.service.StudyAllTjInfoManager;
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

}
