/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.zsd.action.studyrec;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import com.zsd.module.BuffetMindRelationInfo;
import com.zsd.module.BuffetQueInfo;
import com.zsd.module.BuffetSendInfo;
import com.zsd.module.BuffetStudyDetailInfo;
import com.zsd.module.JoinLoreRelation;
import com.zsd.module.LoreInfo;
import com.zsd.module.NetTeacherStudent;
import com.zsd.module.StudyDetailInfo;
import com.zsd.module.StudyLogInfo;
import com.zsd.module.Subject;
import com.zsd.module.json.LoreTreeMenuJson;
import com.zsd.module.json.MyTreeNode;
import com.zsd.page.PageConst;
import com.zsd.service.BuffetAbilityRelationInfoManager;
import com.zsd.service.BuffetMindRelationInfoManager;
import com.zsd.service.BuffetQueInfoManager;
import com.zsd.service.BuffetSendInfoManager;
import com.zsd.service.BuffetStudyDetailManager;
import com.zsd.service.JoinLoreRelationManager;
import com.zsd.service.LoreInfoManager;
import com.zsd.service.NetTeacherStudentManager;
import com.zsd.service.StudyDetailManager;
import com.zsd.service.StudyLogManager;
import com.zsd.service.SubjectManager;
import com.zsd.tools.CommonTools;
import com.zsd.tools.CurrentTime;
import com.zsd.util.Constants;

/** 
 * MyEclipse Struts
 * Creation date: 06-14-2019
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class StudyRecordAction extends DispatchAction {
	/*
	 * Generated Methods
	 */
 
	/** 
	 * 学习记录页面
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward goStuRecPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return mapping.findForward("stuRecPage");
	}
	/**
	 * 根据学生编号,学科编号,完成状态,类型, 时间段获取学习记录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward StuLogListByOption(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		StudyLogManager slManager = (StudyLogManager) AppFactory.instance(null).getApp(Constants.WEB_STUDY_LOG_INFO);
		BuffetSendInfoManager bsManager = (BuffetSendInfoManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_SEND_INFO);
		SubjectManager subManager = (SubjectManager) AppFactory.instance(null).getApp(Constants.WEB_SUBJECT_INFO);
		Integer subId=CommonTools.getFinalInteger("subId", request);
		Integer isfinish=CommonTools.getFinalInteger("isfinish", request);
		Integer userId=CommonTools.getLoginUserId(request);
		Integer logType=CommonTools.getFinalInteger("logType",request);//1:自学,2:家庭作业,3,自助餐 -1全部
		String sDate=CommonTools.getFinalStr("sDate",request);
		String eDate=CommonTools.getFinalStr("eDate",request);
		String subName ="";
 	    if(sDate.equals("")){
			//表示是默认的当前日期前3天的记录(包含当前，所以-2)
			sDate = CurrentTime.getFinalDate(CurrentTime.getStringDate(), -3);
			eDate = CurrentTime.getStringDate();
		}else{
			if(eDate.equals("")){
				eDate = CurrentTime.getStringDate();
			}
		}
 	    Integer diffDay = CurrentTime.compareDate(sDate,eDate);
 	    if(subId.equals(0)){
 	    	List<Subject> sublist = subManager.listInfoBySubName("数学");
 	    	if(!sublist.isEmpty()){
 	    		subId = sublist.get(0).getId();
 	    		subName= "数学";
 	    	}
 	    }else{
 	    	List<Subject> subs = subManager.listEntityById(subId);
 	    	if(!subs.isEmpty()){
 	    		subName =subs.get(0).getSubName();
 	    	}
 	    }
 	    
		Map<String,Object> map = new HashMap<String,Object>();
		List<Object> list_d = new ArrayList<Object>();
		
		Integer allStudyLog =0; //所有学习记录
		Integer noFinishSl =0; //没有完成记录
		Integer finishSl =0; //完成记录
		String comRate = "0.00%";//完成率
		//学生学习记录
		if(logType.equals(1)||logType.equals(2)||logType.equals(-1)){
			List<StudyLogInfo> slList =  slManager.listSlInfoByopt(userId, subId, isfinish, logType, sDate, eDate);
			allStudyLog += slList.size();
			for (Iterator<StudyLogInfo> itr = slList.iterator(); itr.hasNext();) {
				StudyLogInfo slInfo = (StudyLogInfo) itr.next();
				Map<String,Object> map_d= new HashMap<String,Object>();
				map_d.put("studyLogId", slInfo.getId()); //学习记录主键
				map_d.put("subName", slInfo.getSubject().getSubName()); //学科名称
				map_d.put("chapterName", slInfo.getLoreInfo().getChapter().getChapterName());//章节名称
				map_d.put("loreId", slInfo.getLoreInfo().getId()); //知识点主键
				map_d.put("loreName", slInfo.getLoreInfo().getLoreName());//知识点名称
				Integer finishFlag = slInfo.getIsFinish();
				if(finishFlag.equals(1)){
					noFinishSl++;
				}else if(finishFlag.equals(2)){
					finishSl++;
				}
				map_d.put("isFinish",finishFlag); //完成状态
				map_d.put("step", slInfo.getStep());//答题阶段
				map_d.put("stepCom", slInfo.getStepComplete());//阶段完成情况
				map_d.put("logType", slInfo.getLogType()); //学习记录类型
				map_d.put("studyTime", slInfo.getAddTime());
				list_d.add(map_d);
			}
		}
		//自助餐发布信息
		if(logType.equals(-1)|| logType.equals(3)){
			List<BuffetSendInfo> bslist=bsManager.listBsInfoByOption(userId, subId, isfinish, sDate, eDate);
			allStudyLog += bslist.size();
			for (Iterator<BuffetSendInfo> it = bslist.iterator(); it.hasNext();) {
				BuffetSendInfo bsInfo = (BuffetSendInfo) it.next();
				Map<String,Object> map_d= new HashMap<String,Object>();
				map_d.put("studyLogId", bsInfo.getStudyLogInfo().getId());
				map_d.put("subName", bsInfo.getStudyLogInfo().getSubject().getSubName());
				map_d.put("chapterName", bsInfo.getStudyLogInfo().getLoreInfo().getChapter().getChapterName());
				map_d.put("loreId", bsInfo.getStudyLogInfo().getLoreInfo().getId());
				map_d.put("loreName", bsInfo.getStudyLogInfo().getLoreInfo().getLoreName());
				Integer comSta = bsInfo.getStudyResult();
				if(comSta.equals(1)){
					noFinishSl++;
				}else if(comSta.equals(2)){
					finishSl++;
				}
				map_d.put("isFinish", bsInfo.getStudyResult());
				map_d.put("step", bsInfo.getComNumber());
				map_d.put("stepCom", bsInfo.getSendNumber());
				map_d.put("studyTime", bsInfo.getSendTime());
				list_d.add(map_d);
			}
		}
		SortClass sort = new SortClass();
		Collections.sort(list_d, sort);
		
		if(allStudyLog > 0){
			 comRate = String.format("%.2f", (double)finishSl * 100 / allStudyLog) + "%";//完成率
		}
		map.put("allStudyLog", allStudyLog);
		map.put("noFinish", noFinishSl);
		map.put("finish", finishSl);
		map.put("comRate", comRate);
		map.put("diffday", diffDay);
		map.put("subName", subName);
		map.put("studyList", list_d);
		map.put("sDate", sDate);
		map.put("eDate", eDate);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	 @SuppressWarnings("rawtypes")
	 private class SortClass implements Comparator {
	    @SuppressWarnings("unchecked")
		@Override
	    public int compare(Object obj0, Object obj1) {
	      Map<String, String> map0 = (Map) obj0;
	      Map<String, String> map1 = (Map) obj1;
	      int flag = map0.get("studyTime").toString().compareTo(map1.get("studyTime").toString());
	      return -flag; // 不取反，则按正序排列
	    }
	 }
	
	/**
	 * 查看指定学习记录结果
	 * @author zdf
	 * 2019-6-24 上午10:26:08
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward StuLogByResult(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		StudyLogManager slManager = (StudyLogManager) AppFactory.instance(null).getApp(Constants.WEB_STUDY_LOG_INFO);
		Integer stuLogId=CommonTools.getFinalInteger("stuLogId", request);
		StudyLogInfo slInfo =	slManager.getEntityById(stuLogId);
		Map<String,Object> map = new HashMap<String,Object>();
		Integer isFinish= slInfo.getIsFinish();
		Integer step = slInfo.getStep();
		Integer stepC= slInfo.getStepComplete();
		Integer score = slInfo.getFinalScore();
		String sysAssess = slInfo.getSysAssess();
		String teaAssess =slInfo.getTeaAssess();
		Integer loreId= slInfo.getLoreInfo().getId();
		String  loreName = slInfo.getLoreInfo().getLoreName();
		String step1="";
		String step2="";
		String step3="";
		String step4="";
		String step5="";
		String stepCom="";
		String finalScore="";

		if(score.equals(0)){
			finalScore = "暂无";
		}else{
			finalScore= score+"分";
		
		}
		if(sysAssess.equals("")|| sysAssess==null){
			sysAssess = "暂无系统评价";
		}
		if(teaAssess.equals("")|| teaAssess==null){
			teaAssess = "暂无导师评价";
		}
		if(isFinish.equals(2)){
			step1="通过";
			step2="通过";
			step3="完成";
			step4="完成";
			step5="通过";
			stepCom="完成";
		}else{
			if(step == 1){//本知识点的针对性诊断做完题(未通过)，还未进入到关联知识点
				step1="未通过";
 				step2="未诊断";
     			step3="未学习";
     			step4="未学习";
     			step5="未诊断";
     			stepCom ="未完成";
			}else if(step == 2){
				if(stepC == 0){//表示关联性诊断未完成
					step1="未通过";
     				step2="诊断未完成";
         			step3="未学习";
         			step4="未学习";
         			step5="未诊断";
				}else{//关联性诊断已经完成
					step1="未通过";
					step2="诊断已完成";
         			step3="未学习";
         			step4="未学习";
         			step5="未诊断";
				}
				stepCom ="未完成";
			}else if(step == 3){
				if(stepC == 0){//表示关联知识点未完成学习
					step1="未通过";
     				step2="诊断已完成";
         			step3="学习未完成";
         			step4="未学习";
         			step5="未诊断";
				}else{//表示关联知识点完成学习
					step1="未通过";
     				step2="诊断已完成";
         			step3="学习已完成";
         			step4="未学习";
         			step5="未诊断";
				}
				stepCom ="未完成";
			}else if(step == 4){
				step1="未通过";
 				step2="诊断已完成";
     			step3="学习已完成";
     			step4="学习未完成";
     			step5="未诊断";
     			stepCom ="未完成";
			}else{//本知识点再次诊断
				if(stepC == 0){//表示本知识点未完成诊断
					step1="未通过";
     				step2="诊断已完成";
         			step3="学习未完成";
         			step4="学习未完成";
         			step5="诊断未通过";
         			stepCom ="未完成";
				}
			}
		}
		map.put("step1", step1);
		map.put("step2", step2);
		map.put("step3", step3);
		map.put("step4", step4);
		map.put("step5", step5);
		map.put("stepCom", stepCom);
		map.put("sysAssess", sysAssess);
		map.put("teaAssess", teaAssess);
		map.put("finalScore", finalScore);
		map.put("stuLogId", stuLogId);
		map.put("loreId", loreId);
		map.put("loreName", loreName);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	/**
	 * 获取指定学习记录详情
	 * @author zdf
	 * 2019-6-25 上午10:59:28
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getStuLogByDetail(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		StudyDetailManager sdManager = (StudyDetailManager) AppFactory.instance(null).getApp(Constants.WEB_STUDY_DETAIL_INFO);
		Integer stuLogId=CommonTools.getFinalInteger("stuLogId", request);//学习记录编号
		Integer loreId=CommonTools.getFinalInteger("loreId", request);//学习记录编号
//		Integer pNo=CommonTools.getFinalInteger("pageNo", request);//学习记录编号
		Integer pageNo = CommonTools.getFinalInteger("pageNo", request);//当前页
		String loreTypeName=CommonTools.getFinalStr("loreTypeName",request);//知识点类型
		Integer pageSize = 10; //多少条记录
		String msg = "noInfo";
		Map<String,Object> map = new HashMap<String,Object>();
		List<Object> list_d = new ArrayList<Object>();

		if(loreTypeName.equals("gl")){//关联诊断结果
			List<MyTreeNode> loreTreeList = new LoreTreeMenuJson().showTree(loreId, stuLogId, "desc");
			msg = "success";
			map.put("sdList", loreTreeList);
		}else{//三种类型诊断题，默认不传为针对性诊断
			if(loreTypeName.equals("zdx") || loreTypeName.equals("")){
				loreTypeName = "针对性诊断";
			}else if(loreTypeName.equals("zc")){
				loreTypeName = "再次诊断";
			}else if(loreTypeName.equals("gg")){
				loreTypeName = "巩固训练";
			}
			List<StudyDetailInfo> sdList =	sdManager.listInfoByOption(stuLogId, loreTypeName, pageNo, pageSize);
			if(sdList.size() > 0){
				msg = "success";
				for (Iterator<StudyDetailInfo> itr = sdList.iterator(); itr.hasNext();) {
					StudyDetailInfo sdInfo = (StudyDetailInfo) itr.next();
					Map<String,Object> map_d= new HashMap<String,Object>();
					map_d.put("queSub",sdInfo.getLoreQuestion().getQueSub());
					map_d.put("queType",sdInfo.getLoreQuestion().getQueType());
					if(!sdInfo.getA().equals("")){
						map_d.put("A", sdInfo.getA());	
					}
					if(!sdInfo.getB().equals("")){
						map_d.put("B", sdInfo.getB());
					}
					if(!sdInfo.getC().equals("")){
						map_d.put("C", sdInfo.getC());
					}
					if(!sdInfo.getD().equals("")){
						map_d.put("D", sdInfo.getD());
					}
					if(!sdInfo.getE().equals("")){
						map_d.put("E", sdInfo.getE());
					}
					if(!sdInfo.getF().equals("")){
						map_d.put("F", sdInfo.getF());
					}
					map_d.put("myAns", sdInfo.getMyAnswer());
					map_d.put("realAns", sdInfo.getRealAnswer());
					list_d.add(map_d);
				}
				map.put("sdList", list_d);
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	
	
	/**
	 * 根据网络导师编号获取绑定学生
	 * @author zdf
	 * 2019-6-28 下午05:50:15
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getStuByntId(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		NetTeacherStudentManager ntsManager = (NetTeacherStudentManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_STUDENT);
		Integer userId=CommonTools.getLoginUserId(request);//老师用户编号
		List<NetTeacherStudent> ntsList = ntsManager.listByntId(userId);
		Map<String,Object> map = new HashMap<String,Object>();
		List<Object> list_d = new ArrayList<Object>();
		for (Iterator<NetTeacherStudent> itr = ntsList.iterator(); itr.hasNext();) {
			NetTeacherStudent nts = (NetTeacherStudent) itr.next();
			Map<String,Object> map_d= new HashMap<String,Object>();
			map_d.put("stuId", nts.getUser().getId());//学生编号
			map_d.put("stuName", nts.getUser().getRealName());//学生姓名
			map_d.put("portrait", nts.getUser().getPortrait());//头像
			nts.getNetTeacherInfo().getSubject().getId();
			list_d.add(map_d);
		}
		map.put("stulist", list_d);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 培优指定学生学习记录(培优辅查)
	 * @author zdf
	 * 2019-6-28 下午05:28:37
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getBuffetSendList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		StudyLogManager slManager = (StudyLogManager) AppFactory.instance(null).getApp(Constants.WEB_STUDY_LOG_INFO);
		NetTeacherStudentManager ntsManager = (NetTeacherStudentManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_STUDENT);
		BuffetSendInfoManager bsManager = (BuffetSendInfoManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_SEND_INFO);
		Integer userId=CommonTools.getFinalInteger("stuId", request);//学生编号
		Integer sendFlag=CommonTools.getFinalInteger("sendFlag", request);//发布标记
		Integer ntId=CommonTools.getLoginUserId(request);
		String sDate=CommonTools.getFinalStr("sDate",request);
		String eDate=CommonTools.getFinalStr("eDate",request);
		Integer subId = 0;
		int diffDay =0;
		Integer BuffetSendNum = 0;//发送巴菲特数
		Integer comBuffetNum = 0;//完成巴菲特数
		Integer unComBuffetNum = 0;//未完成巴菲特数
		String  comRate = "0.00%";//完成率
		String stuUserName ="";
		Map<String,Object> map = new HashMap<String,Object>();
		List<Object> list_d = new ArrayList<Object>();
		List<NetTeacherStudent> ntsList = ntsManager.listByntId(ntId);
		if(!ntsList.isEmpty()){
			subId = ntsList.get(0).getNetTeacherInfo().getSubject().getId();
			if(!userId.equals(0)){
				stuUserName = ntsList.get(0).getUser().getRealName();
			}
		    if(sDate.equals("")){
				//表示是默认的当前日期前3天的记录(包含当前，所以-2)
				sDate = CurrentTime.getFinalDate(CurrentTime.getStringDate(), -3);
				eDate = CurrentTime.getStringDate();
			}else{
				if(eDate.equals("")){
					eDate = CurrentTime.getStringDate();
				}
			}
		    diffDay = CurrentTime.compareDate(sDate,eDate);
			List<StudyLogInfo> slList =  slManager.listStuLogByOption(userId,subId, sDate, eDate);
			for (Iterator<StudyLogInfo> itr = slList.iterator(); itr.hasNext();) {
				StudyLogInfo slInfo = (StudyLogInfo) itr.next();
				Map<String,Object> map_d= new HashMap<String,Object>();
				Integer stuLogId = slInfo.getId();
				map_d.put("studyLogId", stuLogId); //学习记录主键
				map_d.put("loreId", slInfo.getLoreInfo().getId()); //知识点主键
				map_d.put("loreName", slInfo.getLoreInfo().getLoreName());//知识点名称
				map_d.put("mainLoreId", slInfo.getLoreInfo().getMainLoreId());//引用知识点
				map_d.put("stuId", slInfo.getUser().getId());//学生编号
				List<BuffetSendInfo> bsList = bsManager.listBsInfoById(stuLogId);
				if(bsList.isEmpty() && sendFlag.equals(0)){
					map_d.put("bs_id",0);
					map_d.put("bs_sendTime", "");
					map_d.put("bs_result", "");
					list_d.add(map_d);
				}else if(!bsList.isEmpty() && sendFlag.equals(1)){
					BuffetSendInfo bs = bsList.get(0);
					map_d.put("bs_id", bs.getId());
					map_d.put("bs_sendTime", bs.getSendTime());
					map_d.put("bs_result", bs.getStudyResult());
					BuffetSendNum++;
					if(bs.getStudyResult().equals(1)){
						comBuffetNum++;
					}
					list_d.add(map_d);
				}else if(sendFlag.equals(2)){
					if(bsList.isEmpty()){
						map_d.put("bs_id",0);
						map_d.put("bs_sendTime", "");
						map_d.put("bs_result", "");
					}else if(!bsList.isEmpty()){
						BuffetSendInfo bs = bsList.get(0);
						map_d.put("bs_id", bs.getId());
						map_d.put("bs_sendTime", bs.getSendTime());
						map_d.put("bs_result", bs.getStudyResult());
						BuffetSendNum++;
						if(bs.getStudyResult().equals(1)){
							comBuffetNum++;
						}
					}
					list_d.add(map_d);
				}
				
			}
			if(BuffetSendNum > 0){
				unComBuffetNum = BuffetSendNum - comBuffetNum;
				comRate = String.format("%.2f", (double)comBuffetNum * 100 / BuffetSendNum) + "%";//完成率
			}
		}
		
		map.put("studyList", list_d);
		map.put("BuffetSendNum", BuffetSendNum);
		map.put("comBuffetNum", comBuffetNum);
		map.put("unComBuffetNum", unComBuffetNum);
		map.put("comRate", comRate);
		map.put("diffDay", diffDay);
		map.put("subId", subId);//学科编号
		map.put("sDate", sDate);//开始时间
		map.put("eDate", eDate);//结束时间
		map.put("stuUserName", stuUserName);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	/**
	 * 指定学生的知识点图表数据信息
	 * @author zdf
	 * 2019-7-2 上午11:23:39
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getBuffetChart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BuffetStudyDetailManager bsdManager = (BuffetStudyDetailManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_STUDY_DETAIL_INFO);
		NetTeacherStudentManager ntsManager = (NetTeacherStudentManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_STUDENT);
		Integer stuId=CommonTools.getFinalInteger("stuId", request);//学生编号
		Integer userId=CommonTools.getLoginUserId(request);//老师用户编号
		List<NetTeacherStudent> ntsList = ntsManager.listByntId(userId);
		String subName = "all";
		if(ntsList.size()>0){
			subName = ntsList.get(0).getNetTeacherInfo().getSubject().getSubName();
		}
		
		//当前学生所有学习巴菲特汇总学习情况
		List<BuffetStudyDetailInfo> bsStuList = bsdManager.listInfoByStuId(stuId, subName, -1);
		
		List<BuffetStudyDetailInfoJson> bsdJson = new BuffetStudyDetailInfoJson().getBuffetStudyInfoJson(bsStuList);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result", bsdJson);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	/**
	 * 获取发布自助餐信息
	 * @author zdf
	 * 2019-7-4 下午04:59:43
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getShowBuffetSend(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BuffetQueInfoManager bqManager = (BuffetQueInfoManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_QUE_INFO);
		JoinLoreRelationManager jlrManager = (JoinLoreRelationManager) AppFactory.instance(null).getApp(Constants.WEB_JOIN_LORE_RELATE_INFO);
		BuffetMindRelationInfoManager bmrManager = (BuffetMindRelationInfoManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_MIND_RELATION_INFO);
		BuffetAbilityRelationInfoManager barManager= (BuffetAbilityRelationInfoManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_ABILITY_RELATION_INFO);
		StudyLogManager slm = (StudyLogManager)AppFactory.instance(null).getApp(Constants.WEB_STUDY_LOG_INFO);
	/*	Integer userId=CommonTools.getFinalInteger("stuId", request);//学生编号
		Integer subId=CommonTools.getFinalInteger("subId", request);//学科编号
		String sDate=CommonTools.getFinalStr("sDate",request);//开始时间
		String eDate=CommonTools.getFinalStr("eDate",request);//结束时间
*/		Integer studyLogId=CommonTools.getFinalInteger("studyLogId", request);//学习记录编号
//		Integer currLoreId=CommonTools.getFinalInteger("loreId", request);//知识点编号
//		Integer quoteLoreId=CommonTools.getFinalInteger("quoteLoreId", request);//知识点题库编号
		Integer quoteLoreId = 0;
		String msg = "error";
		Map<String,Object> map = new HashMap<String,Object>();
		if(studyLogId > 0){
			StudyLogInfo sl  = slm.getEntityById(studyLogId);
			if(sl != null){
				map.put("loreName", sl.getLoreInfo().getLoreName());//标题用
				quoteLoreId = sl.getLoreInfo().getMainLoreId();//被引用知识点
			    JoinLoreRelation jlr = jlrManager.getInfoByLoreId(quoteLoreId);//获取当前知识点的通用版知识点与之合并的知识点记录
				List<BuffetQueInfo> bList = new ArrayList<BuffetQueInfo>();
				String basicLoreIdStr = "";
				if(jlr!=null){
					basicLoreIdStr = jlr.getLoreIdArray();
					String[] basicLoreIdArray = basicLoreIdStr.split(",");
					for(Integer i = 0 ; i < basicLoreIdArray.length ; i++){
						bList = bqManager.listInfoByOption(Integer.parseInt(basicLoreIdArray[i]), 0);//根据知识点编号获取启用的巴菲特列表
						if(bList.size() > 0){
							//获取真实的存在巴菲特题库的被引用的知识点编号
							quoteLoreId = Integer.parseInt(basicLoreIdArray[i]);
							break;
						}
					}
				}else{
					bList = bqManager.listInfoByOption(quoteLoreId, 0);//根据知识点编号获取启用的巴菲特列表
				}
				List<Object> list_d = new ArrayList<Object>();
				if(bList.size() > 0){
					msg = "success";
					for (Iterator<BuffetQueInfo> itr = bList.iterator(); itr.hasNext();) {
						BuffetQueInfo bqInfo = (BuffetQueInfo) itr.next();
						Map<String,Object> map_d= new HashMap<String,Object>();
						map_d.put("buffetId", bqInfo.getId());//自助餐题库编号
						map_d.put("bTypeName", bqInfo.getBuffetTypeInfo().getTypes());//自助餐类型名
						map_d.put("title", bqInfo.getTitle());//自助餐题库标题
					    List<BuffetMindRelationInfo> bmrList = 	bmrManager.listBmrInfoBybqId(bqInfo.getId());
						String mindIdStr = "";
						String mindNameStr = "";
						for(Iterator<BuffetMindRelationInfo> it_mind = bmrList.iterator() ; it_mind.hasNext();){
							BuffetMindRelationInfo bmr = it_mind.next();
							mindIdStr += bmr.getBuffetMindTypeInfo().getId()+":";
							mindNameStr += bmr.getBuffetMindTypeInfo().getMind()+",";
						}
						if(!mindIdStr.equals("")){
							map_d.put("mindId", mindIdStr.substring(0,mindIdStr.length()-1));
							map_d.put("mindName", mindNameStr.substring(0,mindNameStr.length()-1));
						}else{
							map_d.put("mindId", mindIdStr);
							map_d.put("mindName", mindNameStr);
						}
						List<BuffetAbilityRelationInfo> barList = barManager.listBarInfoBybqId(bqInfo.getId());
						String abilityIdStr = "";
						String abilityNameStr = "";
						for(Iterator<BuffetAbilityRelationInfo> it_ability = barList.iterator() ; it_ability.hasNext();){
							BuffetAbilityRelationInfo bar = it_ability.next();
							abilityIdStr += bar.getBuffetAbilityTypeInfo().getId()+":";
							abilityNameStr += bar.getBuffetAbilityTypeInfo().getAbility()+",";
						}
						if(!abilityIdStr.equals("")){
							map_d.put("abilityId", abilityIdStr.substring(0,abilityIdStr.length()-1));
							map_d.put("abilityName", abilityNameStr.substring(0,abilityNameStr.length()-1));
						}else{
							map_d.put("abilityId", abilityIdStr);
							map_d.put("abilityName", abilityNameStr);
						}
						list_d.add(map_d);
					}
					map.put("buffetList", list_d);
					map.put("studyLogId", studyLogId);
					map.put("quoteLoreId", quoteLoreId);
				}else{
					msg = "noInfo";
				}
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	/**
	 * 查看自助餐详情信息
	 * @author zdf
	 * 2019-7-8 下午05:33:02
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showBuffetDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
//		LoreInfoManager loreManager = (LoreInfoManager) AppFactory.instance(null).getApp(Constants.WEB_LORE_INFO);
		BuffetQueInfoManager bqManager = (BuffetQueInfoManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_QUE_INFO);
		BuffetMindRelationInfoManager bmrManager = (BuffetMindRelationInfoManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_MIND_RELATION_INFO);
		BuffetAbilityRelationInfoManager barManager= (BuffetAbilityRelationInfoManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_ABILITY_RELATION_INFO);
		StudyLogManager slm = (StudyLogManager)AppFactory.instance(null).getApp(Constants.WEB_STUDY_LOG_INFO);
		Integer studyLogId=CommonTools.getFinalInteger("studyLogId", request);//学习记录编号
//		Integer loreId=CommonTools.getFinalInteger("loreId", request);//知识点编号	
		Integer basicLoreId = CommonTools.getFinalInteger("quoteLoreId", request);//存在自助餐题库的知识点
		String buffetTypeName=Transcode.unescape_new("buffetTypeName",request);
		String msg = "error";
//		LoreInfo loreInfo =  loreManager.getEntityById(loreId);
//		Integer basicLoreId = 0;//通用版知识点编号
//		if(loreInfo!=null){
//			basicLoreId = loreInfo.getMainLoreId();//当前的loreId是通用版知识典
//			if(basicLoreId.equals(0)){
//				basicLoreId = loreId;
//			}
//		}
		Map<String,Object> map = new HashMap<String,Object>();
		if(studyLogId > 0 && basicLoreId > 0){
			StudyLogInfo sl  = slm.getEntityById(studyLogId);
			if(sl != null){
				map.put("loreName", sl.getLoreInfo().getLoreName());//标题用
				List<Object> list_d = new ArrayList<Object>();
				List<BuffetQueInfo> bqList = bqManager.listInfoByLoreAndBuffetType(basicLoreId, buffetTypeName);
				if(bqList.size() > 0){
					msg = "success";
					for (Iterator<BuffetQueInfo> itr = bqList.iterator(); itr.hasNext();) {
						BuffetQueInfo bqInfo = (BuffetQueInfo) itr.next();
						Map<String,Object> map_d= new HashMap<String,Object>();
						map_d.put("buffetId", bqInfo.getId());//自助餐题库编号
						map_d.put("queType", bqInfo.getQueType());//题型
						map_d.put("title", bqInfo.getTitle());//自助餐题库标题
						map_d.put("subject", bqInfo.getSubject());//题干
						if(!bqInfo.getA().equals("")){
							map_d.put("A", bqInfo.getA());	
						}
						if(!bqInfo.getB().equals("")){
							map_d.put("B", bqInfo.getB());
						}
						if(!bqInfo.getC().equals("")){
							map_d.put("C", bqInfo.getC());
						}
						if(!bqInfo.getD().equals("")){
							map_d.put("D", bqInfo.getD());
						}
						if(!bqInfo.getE().equals("")){
							map_d.put("E", bqInfo.getE());
						}
						if(!bqInfo.getF().equals("")){
							map_d.put("F", bqInfo.getF());
						}
						map_d.put("answer", bqInfo.getAnswer());
						map_d.put("resolution", bqInfo.getResolution());
						map_d.put("tips", bqInfo.getTips());
						 List<BuffetMindRelationInfo> bmrList = 	bmrManager.listBmrInfoBybqId(bqInfo.getId());
							String mindIdStr = "";
							String mindNameStr = "";
							for(Iterator<BuffetMindRelationInfo> it_mind = bmrList.iterator() ; it_mind.hasNext();){
								BuffetMindRelationInfo bmr = it_mind.next();
								mindIdStr += bmr.getBuffetMindTypeInfo().getId()+":";
								mindNameStr += bmr.getBuffetMindTypeInfo().getMind()+",";
							}
							if(!mindIdStr.equals("")){
								map_d.put("mindId", mindIdStr.substring(0,mindIdStr.length()-1));
								map_d.put("mindName", mindNameStr.substring(0,mindNameStr.length()-1));
							}else{
								map_d.put("mindId", mindIdStr);
								map_d.put("mindName", mindNameStr);
							}
							List<BuffetAbilityRelationInfo> barList = barManager.listBarInfoBybqId(bqInfo.getId());
							String abilityIdStr = "";
							String abilityNameStr = "";
							for(Iterator<BuffetAbilityRelationInfo> it_ability = barList.iterator() ; it_ability.hasNext();){
								BuffetAbilityRelationInfo bar = it_ability.next();
								abilityIdStr += bar.getBuffetAbilityTypeInfo().getId()+":";
								abilityNameStr += bar.getBuffetAbilityTypeInfo().getAbility()+",";
							}
							if(!abilityIdStr.equals("")){
								map_d.put("abilityId", abilityIdStr.substring(0,abilityIdStr.length()-1));
								map_d.put("abilityName", abilityNameStr.substring(0,abilityNameStr.length()-1));
							}else{
								map_d.put("abilityId", abilityIdStr);
								map_d.put("abilityName", abilityNameStr);
							}
						  list_d.add(map_d);
					}
					map.put("buffetList", list_d);
					map.put("studyLogId", studyLogId);
					map.put("quoteLoreId", basicLoreId);
				}else{
					msg = "noInfo";
				}
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
		
	}
	/**
	 * 发送自助餐
	 * @author zdf
	 * 2019-7-9 下午03:07:52
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sendBuffet(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BuffetQueInfoManager bqManager = (BuffetQueInfoManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_QUE_INFO);
		BuffetSendInfoManager bsManager = (BuffetSendInfoManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_SEND_INFO);
		BuffetStudyDetailManager bsdManager = (BuffetStudyDetailManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_STUDY_DETAIL_INFO);
		String buffetIdStr = CommonTools.getFinalStr("buffetIdArray",request);
		Integer studyLogId = CommonTools.getFinalInteger("studyLogId",request);
		Integer sendMode = CommonTools.getFinalInteger("sendMode",request);
		Integer userId=CommonTools.getLoginUserId(request);
		String[] buffetIdArray = buffetIdStr.split(",");
		//插入发布记录表
		Integer buffetQuestionLength = buffetIdArray.length;
		Integer bsId = bsManager.addBuffetSend(studyLogId, CurrentTime.getCurrentTime(), userId, sendMode, buffetQuestionLength);
		String  status = "fail";
		Map<String,Object> map = new HashMap<String,Object>();
		if(bsId > 0){
			for(Integer i = 0 ; i < buffetQuestionLength ; i++){
				Integer buffetId = Integer.parseInt(buffetIdArray[i]);
				BuffetQueInfo bqInfo = bqManager.getEntityById(buffetId);
				String realAnswer = "";
				if(bqInfo!=null){
					realAnswer = bqInfo.getAnswer();
					//插入巴菲特学习情况（初始）
					bsdManager.addBuffetStudyDetil(bsId, buffetId, realAnswer, "", -1, null, "","","","","","");
				}
			}
		 status="success";	
		}
		map.put("status", status);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	/**
	 * 获取指定发布自助餐详情
	 * @author zdf
	 * 2019-7-17 下午05:23:38
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sendBuffetDetil(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BuffetStudyDetailManager bsdManager = (BuffetStudyDetailManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_STUDY_DETAIL_INFO);
		BuffetMindRelationInfoManager bmrManager = (BuffetMindRelationInfoManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_MIND_RELATION_INFO);
		BuffetAbilityRelationInfoManager barManager= (BuffetAbilityRelationInfoManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_ABILITY_RELATION_INFO);
		Integer bsId = CommonTools.getFinalInteger("bsId",request);
		Map<String,Object> map = new HashMap<String,Object>();
		List<Object> list_d = new ArrayList<Object>();
		List<BuffetStudyDetailInfo> bsdlist = bsdManager.listInfoByBsId(bsId);
		for (Iterator<BuffetStudyDetailInfo> itr = bsdlist.iterator(); itr.hasNext();) {
			BuffetStudyDetailInfo bsdInfo = (BuffetStudyDetailInfo) itr.next();
			Map<String,Object> map_d= new HashMap<String,Object>();
			map_d.put("quetitle", bsdInfo.getBuffetQueInfo().getTitle());//标题
			map_d.put("queType", bsdInfo.getBuffetQueInfo().getQueType()); //题型一
			map_d.put("subject", bsdInfo.getBuffetQueInfo().getSubject());//题干
		    List<BuffetMindRelationInfo> bmrList = 	bmrManager.listBmrInfoBybqId(bsdInfo.getBuffetQueInfo().getId());
			String mindIdStr = "";
			String mindNameStr = "";
			for(Iterator<BuffetMindRelationInfo> it_mind = bmrList.iterator() ; it_mind.hasNext();){
				BuffetMindRelationInfo bmr = it_mind.next();
				mindIdStr += bmr.getBuffetMindTypeInfo().getId()+":";
				mindNameStr += bmr.getBuffetMindTypeInfo().getMind()+",";
			}
			if(!mindIdStr.equals("")){
				map_d.put("mindId", mindIdStr.substring(0,mindIdStr.length()-1));
				map_d.put("mindName", mindNameStr.substring(0,mindNameStr.length()-1));
			}else{
				map_d.put("mindId", mindIdStr);
				map_d.put("mindName", mindNameStr);
			}
			List<BuffetAbilityRelationInfo> barList = barManager.listBarInfoBybqId(bsdInfo.getBuffetQueInfo().getId());
			String abilityIdStr = "";
			String abilityNameStr = "";
			for(Iterator<BuffetAbilityRelationInfo> it_ability = barList.iterator() ; it_ability.hasNext();){
				BuffetAbilityRelationInfo bar = it_ability.next();
				abilityIdStr += bar.getBuffetAbilityTypeInfo().getId()+":";
				abilityNameStr += bar.getBuffetAbilityTypeInfo().getAbility()+",";
			}
			if(!abilityIdStr.equals("")){
				map_d.put("abilityId", abilityIdStr.substring(0,abilityIdStr.length()-1));
				map_d.put("abilityName", abilityNameStr.substring(0,abilityNameStr.length()-1));
			}else{
				map_d.put("abilityId", abilityIdStr);
				map_d.put("abilityName", abilityNameStr);
			}
			map_d.put("A", bsdInfo.getA());
			map_d.put("B", bsdInfo.getB());
			map_d.put("C", bsdInfo.getC());
			map_d.put("D", bsdInfo.getD());
			map_d.put("E", bsdInfo.getE());
			map_d.put("F", bsdInfo.getF());
			map_d.put("realAnswer", bsdInfo.getRealAnswer());//正确答案
			map_d.put("myAnswer", bsdInfo.getMyAnswer());//我的答案
			list_d.add(map_d);
		}
		map.put("buffetList", list_d);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	/**
	 * 获取指定发布自助餐统计
	 * @author zdf
	 * 2019-7-19 下午04:23:03
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sendBuffetDetil1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		BuffetStudyDetailManager bsdManager = (BuffetStudyDetailManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_STUDY_DETAIL_INFO);
		BuffetMindRelationInfoManager bmrManager = (BuffetMindRelationInfoManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_MIND_RELATION_INFO);
		BuffetAbilityRelationInfoManager barManager= (BuffetAbilityRelationInfoManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_ABILITY_RELATION_INFO);
		Integer bsId = CommonTools.getFinalInteger("bsId",request);
		Map<String,Object> map = new HashMap<String,Object>();
		List<BuffetStudyDetailInfo> bsdlist = bsdManager.listInfoByBsId(bsId);
		int total =bsdlist.size();
		int rightNum =0;
		int errorNum=0;
		if(total>0){
			for (Iterator<BuffetStudyDetailInfo> itr = bsdlist.iterator(); itr.hasNext();) {
				BuffetStudyDetailInfo bsdInfo = (BuffetStudyDetailInfo) itr.next();
				if(bsdInfo.getCurrComStatus().equals(1)){
					rightNum+=1;
				}
			}
		}
		errorNum = total-rightNum;
		DecimalFormat df  = new DecimalFormat("######0.00");
		String  rate  = df.format(((double)rightNum / (double)total) * 100);
		List<BuffetStudyDetailStatisticsJson> bsdJson = new BuffetStudyDetailStatisticsJson().getBsdStatisticsJson(bsdlist);
		map.put("total", total);
		map.put("rightNum", rightNum);
		map.put("errorNum", errorNum);
		map.put("comRate", rate);
		map.put("result", bsdJson);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
}