/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.zsd.action.studyrec;

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

import com.zsd.factory.AppFactory;
import com.zsd.module.BuffetSendInfo;
import com.zsd.module.BuffetStudyDetailInfo;
import com.zsd.module.NetTeacherStudent;
import com.zsd.module.StudyDetailInfo;
import com.zsd.module.StudyLogInfo;
import com.zsd.module.json.LoreTreeMenuJson;
import com.zsd.module.json.MyTreeNode;
import com.zsd.page.PageConst;
import com.zsd.service.BuffetSendInfoManager;
import com.zsd.service.BuffetStudyDetailManager;
import com.zsd.service.NetTeacherStudentManager;
import com.zsd.service.StudyDetailManager;
import com.zsd.service.StudyLogManager;
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
		Integer subId=CommonTools.getFinalInteger("subId", request);
		Integer isfinish=CommonTools.getFinalInteger("isfinish", request);
		Integer userId=CommonTools.getLoginUserId(request);;
		Integer logType=CommonTools.getFinalInteger("logType",request);//1:自学,2:家庭作业,3,自助餐 -1全部
		String sDate=CommonTools.getFinalStr("sDate",request);
		String eDate=CommonTools.getFinalStr("eDate",request);
 	    if(sDate.equals("")){
			//表示是默认的当前日期前3天的记录(包含当前，所以-2)
			sDate = CurrentTime.getFinalDate(CurrentTime.getStringDate(), -2);
			eDate = CurrentTime.getStringDate();
		}else{
			if(eDate.equals("")){
				eDate = CurrentTime.getStringDate();
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
		map.put("studyList", list_d);
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
		Integer pNo=CommonTools.getFinalInteger("pageNo", request);//学习记录编号
		String loreTypeName=CommonTools.getFinalStr("loreTypeName",request);//知识点类型
		Integer pageSize = 10; //多少条记录
		
		Map<String,Object> map = new HashMap<String,Object>();
		List<Object> list_d = new ArrayList<Object>();
		
		
		if(!loreTypeName.equals("") && !loreTypeName.equals("gl")){
			if(loreTypeName.equals("zdx")){
				loreTypeName = "针对性诊断";
			}else if(loreTypeName.equals("zc")){
				loreTypeName = "再次诊断";
			}else if(loreTypeName.equals("gg")){
				loreTypeName = "巩固训练";
			}
			
			Integer count = sdManager.getInfoByOption(stuLogId, loreTypeName);//总记录数
			Integer countPage =PageConst.getPageCount(count, pageSize);//总页数
			Integer pageNo=PageConst.getPageNo(pNo, countPage);//当前页
			
			List<StudyDetailInfo> sdList=	sdManager.listInfoByOption(stuLogId, loreTypeName, pageNo, pageSize);
			for (Iterator<StudyDetailInfo> itr = sdList.iterator(); itr.hasNext();) {
				StudyDetailInfo sdInfo = (StudyDetailInfo) itr.next();
				Map<String,Object> map_d= new HashMap<String,Object>();
				map_d.put("queSub",sdInfo.getLoreQuestion().getQueSub());
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
		}else if(loreTypeName.equals("gl")&& !loreTypeName.equals("")){
			//loreTypeName = "关联诊断结果";
			List<MyTreeNode> loreTreeList = new LoreTreeMenuJson().showTree(loreId, stuLogId, "desc");
			map.put("sdList", loreTreeList);
		}
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
		Integer userId=CommonTools.getFinalInteger("userId", request);//导师编号
		List<NetTeacherStudent> ntsList = ntsManager.listByntId(userId);
		Map<String,Object> map = new HashMap<String,Object>();
		List<Object> list_d = new ArrayList<Object>();
		for (Iterator<NetTeacherStudent> itr = ntsList.iterator(); itr.hasNext();) {
			NetTeacherStudent nts = (NetTeacherStudent) itr.next();
			Map<String,Object> map_d= new HashMap<String,Object>();
			map_d.put("stuId", nts.getUser().getId());//学生编号
			map_d.put("stuName", nts.getUser().getRealName());//学生姓名
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
		Integer ntId=CommonTools.getFinalInteger("userId", request);//学生编号
		String sDate=CommonTools.getFinalStr("sDate",request);
		String eDate=CommonTools.getFinalStr("eDate",request);
		Integer subId = 0;
		int diffDay =0;
		Integer BuffetSendNum = 0;//发送巴菲特数
		Integer comBuffetNum = 0;//完成巴菲特数
		Integer unComBuffetNum = 0;//未完成巴菲特数
		String  comRate = "0.00%";//完成率
		Map<String,Object> map = new HashMap<String,Object>();
		List<Object> list_d = new ArrayList<Object>();
		List<NetTeacherStudent> ntsList = ntsManager.listByntId(ntId);
		if(!ntsList.isEmpty()){
			subId = ntsList.get(0).getNetTeacherInfo().getSubject().getId();
			if(userId.equals(0)){
				userId = ntsList.get(0).getUser().getId();
			}
		    if(sDate.equals("")){
				//表示是默认的当前日期前3天的记录(包含当前，所以-2)
				sDate = CurrentTime.getFinalDate(CurrentTime.getStringDate(), -2);
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
				if(bsList.isEmpty()){
					map_d.put("bs_id",0);
					map_d.put("bs_sendTime", "");
					map_d.put("bs_result", "");
				
				}else{
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
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	/**
	 * 学习记录当前知识点图表数据信息
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
		BuffetSendInfoManager bsm = (BuffetSendInfoManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_SEND_INFO);
		Integer userId=CommonTools.getFinalInteger("stuId", request);//学生编号
		Integer bsId=CommonTools.getFinalInteger("bsId", request);//自助餐发布编号
		String subName=CommonTools.getFinalStr("subName",request);
		if(subName.equals("")){//表示网络导师发布时没指定科目名称（需要和发布时完成的知识点科目名称相同）
			List<BuffetSendInfo> bsList = bsm.listBsInfoById(bsId);
			if(bsList.size() > 0){
				subName = bsList.get(0).getStudyLogInfo().getSubject().getSubName();
			}	
		}
		//当前发布的巴菲特学习情况
		List<BuffetStudyDetailInfo> bsList = bsdManager.listInfoByBsId(bsId);
		//当前学生所有学习巴菲特汇总学习情况
		List<BuffetStudyDetailInfo> bsStuList = bsdManager.listInfoByStuId(userId, subName, -1);
		
		return null;
	}
}