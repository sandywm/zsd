/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.zsd.action.question;

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
import com.zsd.module.ClassInfo;
import com.zsd.module.GradeSubject;
import com.zsd.module.NetTeacherInfo;
import com.zsd.module.NetTeacherStudent;
import com.zsd.module.QuestionInfo;
import com.zsd.module.UserClassInfo;
import com.zsd.page.PageConst;
import com.zsd.service.ClassInfoManager;
import com.zsd.service.GradeSubjectManager;
import com.zsd.service.NetTeacherInfoManager;
import com.zsd.service.NetTeacherStudentManager;
import com.zsd.service.QuestionInfoManager;
import com.zsd.service.UserClassInfoManager;
import com.zsd.tools.CommonTools;
import com.zsd.tools.Convert;
import com.zsd.tools.CurrentTime;
import com.zsd.util.Constants;

/**
 * MyEclipse Struts Creation date: 05-20-2019
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class QuestionInfoAction extends DispatchAction {
	/**
	 * 添加我的提问
	 * @author zong
	 * 2019-5-23下午05:21:41
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addQuestion(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QuestionInfoManager qManager = (QuestionInfoManager) AppFactory.instance(null).getApp(Constants.WEB_QUESTION_INFO);
		Integer subId = CommonTools.getFinalInteger("subId", request);
		Integer ntId = CommonTools.getFinalInteger("ntId", request);
		Integer userId = CommonTools.getLoginUserId(request);
		String queTitle=Transcode.unescape_new1("qTitle",request);
		String queContent=Transcode.unescape_new1("qCon",request);
		String queTime= CurrentTime.getCurrentTime();
		String queImg=CommonTools.getFinalStr("queImg",request);
		Integer qinfo =qManager.adddQue(subId, userId , ntId, queTitle, queContent,queImg,queTime, "", "", 0);
		Map<String, Object> map = new HashMap<String, Object>();
		String msg ="fail";
		if(qinfo>0){
			msg = "success";
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}

	/**
	 * 分页获取我的提问列表
	 * 
	 * @author zong 2019-5-20上午10:44:17
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getQuestionList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QuestionInfoManager qManager = (QuestionInfoManager) AppFactory.instance(null).getApp(Constants.WEB_QUESTION_INFO);
		Integer userId = CommonTools.getLoginUserId(request);
		Integer subId = CommonTools.getFinalInteger("subId", request);
		Integer readStatus = CommonTools.getFinalInteger("readStatus", request);
		Integer count = qManager.getInfoByOptCount(userId,subId, readStatus);
		String msg = "暂无记录";
		Map<String, Object> map = new HashMap<String, Object>();
		if (count > 0) {
			Integer pageSize = PageConst.getPageSize(
					String.valueOf(request.getParameter("limit")), 10);// 等同于pageSize
			Integer pageNo = CommonTools.getFinalInteger("page", request);// 等同于pageNo
			List<QuestionInfo> qList = qManager.listInfoByOpt(userId,subId,
					readStatus, pageNo, pageSize);
			List<Object> list_d = new ArrayList<Object>();
			for (Iterator it = qList.iterator(); it.hasNext();) {
				QuestionInfo qInfo = (QuestionInfo) it.next();
				Map<String, Object> map_d = new HashMap<String, Object>();
				map_d.put("qId", qInfo.getId()); //我的提问编号
				map_d.put("queTitle", qInfo.getQueTitle());
				map_d.put("queTime", qInfo.getQueTime());
				if (qInfo.getReadStatus() == 0) {
					map_d.put("readSta", "未回复");
				} else {
					map_d.put("readSta", "已回复");
				}
				list_d.add(map_d);
			}
			map.put("data", list_d);
			map.put("count", count);
			map.put("code", 0);
			msg = "success";
		}
		map.put("msg", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}

	/**
	 *  根据学生编号获取年级所在的学科
	 * 
	 * @author zong 2019-5-21下午04:53:21
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getSubName(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserClassInfoManager ucManager = (UserClassInfoManager) AppFactory.instance(null).getApp(Constants.WEB_USER_CLASS_INFO);
		ClassInfoManager cManager = (ClassInfoManager) AppFactory.instance(null).getApp(Constants.WEB_CLASS_INFO);
		GradeSubjectManager gsm = (GradeSubjectManager) AppFactory.instance(null).getApp(Constants.WEB_GRADE_SUBJECT_INFO);
		Integer userId = CommonTools.getLoginUserId(request);
		List<UserClassInfo> ucList = ucManager.listUcInfoByUserId(userId);
		String bcdate = "";
		if (!ucList.isEmpty()) {
			Integer cId = ucList.get(0).getClassInfo().getId();
			List<ClassInfo> cList = cManager.listClassInfoById(cId);
			if (!cList.isEmpty()) {
				bcdate = cList.get(0).getBuildeClassDate();
			}
		}
		String gName = Convert.dateConvertGradeName(bcdate);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "noInfo";
		List<GradeSubject> gsList = gsm.listSpecInfoByGname(gName);
		if(gsList.size() > 0){
			msg = "success";
			List<Object> list_d = new ArrayList<Object>();
			for(Iterator<GradeSubject> it = gsList.iterator() ; it.hasNext();){
				GradeSubject gs = it.next();
				Map<String,Object> map_d = new HashMap<String,Object>();
				map_d.put("subId", gs.getSubject().getId());
				map_d.put("subName", gs.getSubject().getSubName());
				list_d.add(map_d);
			}
			map.put("subList", list_d);
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	/**
	 * 根据学生编号获取年级所在的学科以及学科所对应的网络导师
	 * @author zong
	 * 2019-5-22上午09:35:16
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getSubNt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserClassInfoManager ucManager = (UserClassInfoManager) AppFactory.instance(null).getApp(Constants.WEB_USER_CLASS_INFO);
		ClassInfoManager cManager = (ClassInfoManager) AppFactory.instance(null).getApp(Constants.WEB_CLASS_INFO);
		GradeSubjectManager gsm = (GradeSubjectManager) AppFactory.instance(null).getApp(Constants.WEB_GRADE_SUBJECT_INFO);
		NetTeacherStudentManager ntsManager = (NetTeacherStudentManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_STUDENT);
		Integer userId = CommonTools.getLoginUserId(request);
		List<UserClassInfo> ucList = ucManager.listUcInfoByUserId(userId);
		String bcdate = "";
		if (!ucList.isEmpty()) {
			Integer cId = ucList.get(0).getClassInfo().getId();
			List<ClassInfo> cList = cManager.listClassInfoById(cId);
			if (!cList.isEmpty()) {
				bcdate = cList.get(0).getBuildeClassDate();
			}
		}
		String gName = Convert.dateConvertGradeName(bcdate);
		Map<String,Object> map = new HashMap<String,Object>();
		List<GradeSubject> gsList = gsm.listSpecInfoByGname(gName);
		List<NetTeacherStudent> ntsList = ntsManager.listByStuId(userId);
		if(gsList.size() > 0){
			List<Object> list_d = new ArrayList<Object>();
			for(Iterator<GradeSubject> it = gsList.iterator() ; it.hasNext();){
				GradeSubject gs = it.next();
				Map<String,Object> map_d = new HashMap<String,Object>();
				Integer subId = gs.getSubject().getId();//学科编号
				String subName = gs.getSubject().getSubName(); //学科名
				for (Iterator<NetTeacherStudent> itt = ntsList.iterator(); itt.hasNext();) {
					NetTeacherStudent nts = (NetTeacherStudent) itt.next();
					Integer sId  = nts.getNetTeacherInfo().getSubject().getId();//学科编号
					if(subId.equals(sId)){
						Integer ntId = nts.getNetTeacherInfo().getId();
						String ntName = nts.getNetTeacherInfo().getUser().getRealName();
						map_d.put("sntId", subId+"/"+ntId);
						map_d.put("sntName",subName+"("+ntName+")");
					}else{
						map_d.put("sntId", subId+"/"+0);
						map_d.put("sntName",subName+"(暂无导师)");
					}
					list_d.add(map_d);
				}
				
			}
			map.put("sntList", list_d);
		}
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	/**
	 * 我的答疑(网络导师下学生)
	 * @author zong
	 * 2019-5-23上午10:56:36
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QuestionInfoManager qManager = (QuestionInfoManager) AppFactory.instance(null).getApp(Constants.WEB_QUESTION_INFO);
		NetTeacherInfoManager ntManager = (NetTeacherInfoManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_INFO);
		Integer userId = CommonTools.getLoginUserId(request);
		List<NetTeacherInfo> ntlist = ntManager.listntInfoByuserId(userId);
		Integer ntId = ntlist.get(0).getId();
		Map<String,Object> map = new HashMap<String,Object>();
		List<Object> list_d = new ArrayList<Object>();
		List<QuestionInfo> qlist = qManager.findInfoByntId(ntId);
		for (Iterator<QuestionInfo> it = qlist.iterator(); it.hasNext();) {
			QuestionInfo qInfo = (QuestionInfo) it.next();
			Map<String,Object> map_d = new HashMap<String,Object>();
			map_d.put("stuId", qInfo.getUser().getId());
			map_d.put("stuName", qInfo.getUser().getRealName());
			list_d.add(map_d);
		}
		map.put("stuList", list_d);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	/**
	 * 根据学生编号,回复状态获取我的答疑信息(我的答疑)
	 * @author zong
	 * 2019-5-23下午05:10:15
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getQueByntList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		QuestionInfoManager qManager = (QuestionInfoManager) AppFactory.instance(null).getApp(Constants.WEB_QUESTION_INFO);
		Integer stuId = CommonTools.getFinalInteger("stuId", request);
		Integer readStatus = CommonTools.getFinalInteger("readStatus", request);
		Integer userId = CommonTools.getLoginUserId(request);	
		Integer count = qManager.getInfoByStuCount(userId,stuId, readStatus);
		String msg = "暂无记录";
		Map<String, Object> map = new HashMap<String, Object>();
		if (count > 0) {
			Integer pageSize = PageConst.getPageSize(
					String.valueOf(request.getParameter("limit")), 10);// 等同于pageSize
			Integer pageNo = CommonTools.getFinalInteger("page", request);// 等同于pageNo
			List<QuestionInfo> qList = qManager.listInfoByStu(userId,stuId,
					readStatus, pageNo, pageSize);
			List<Object> list_d = new ArrayList<Object>();
			for (Iterator<QuestionInfo> it = qList.iterator(); it.hasNext();) {
				QuestionInfo qInfo = (QuestionInfo) it.next();
				Map<String, Object> map_d = new HashMap<String, Object>();
				map_d.put("qId", qInfo.getId());
				map_d.put("queTitle", qInfo.getQueTitle());
				map_d.put("queTime", qInfo.getQueTime());
				if (qInfo.getReadStatus() == 0) {
					map_d.put("readSta", "未回复");
				} else {
					map_d.put("readSta", "已回复");
				}
				list_d.add(map_d);
			}
			map.put("data", list_d);
			map.put("count", count);
			map.put("code", 0);
			msg = "success";
		}
		map.put("msg", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	public ActionForward getQuedetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		QuestionInfoManager qManager = (QuestionInfoManager) AppFactory.instance(null).getApp(Constants.WEB_QUESTION_INFO);
		Integer qId = CommonTools.getFinalInteger("qId", request);
		Map<String, Object> map = new HashMap<String, Object>();
		List<QuestionInfo> qList = qManager.listInfoById(qId);
		List<Object> list_d = new ArrayList<Object>();
		for (Iterator<QuestionInfo> it = qList.iterator(); it.hasNext();) {
			QuestionInfo qInfo = (QuestionInfo) it.next();
			Map<String, Object> map_d = new HashMap<String, Object>();
			map_d.put("qId", qInfo.getId());
			map_d.put("queTitle", qInfo.getQueTitle());
			map_d.put("queContent", qInfo.getQueContent());
			map_d.put("queImg", qInfo.getQueImg());
			map_d.put("replyContent", qInfo.getQueReplyContent());
			map_d.put("replyImg", qInfo.getQueReplyImg());
			map_d.put("queTime", qInfo.getQueTime());
			map_d.put("replyTime", qInfo.getQueReplyTime());
			map_d.put("ntRealName", qInfo.getNetTeacherInfo().getUser().getRealName());
			if (qInfo.getReadStatus() == 0) {
				map_d.put("readSta", "未回复");
			} else {
				map_d.put("readSta", "已回复");
			}
			list_d.add(map_d);
		}
		map.put("data", list_d);
		CommonTools.getJsonPkg(map, response);
     	return null;
		
	}
	
	/**
	 * 回复学生所提问题
	 * @author zong
	 * 2019-5-23下午05:12:38
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateQue(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QuestionInfoManager qManager = (QuestionInfoManager) AppFactory.instance(null).getApp(Constants.WEB_QUESTION_INFO);
		String queReplyContent =Transcode.unescape_new1("qReCon",request);
		Integer qId = CommonTools.getFinalInteger("qId", request);
		String queReplyImg=CommonTools.getFinalStr("queReplyImg",request);
		String queReplyTime = CurrentTime.getCurrentTime();
		Map<String, Object> map = new HashMap<String, Object>();
		
		boolean qFlag = qManager.updateQue(qId, queReplyContent,queReplyImg, queReplyTime, 1);
		String msg ="fail";
		if(qFlag){
			msg ="success";
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
}