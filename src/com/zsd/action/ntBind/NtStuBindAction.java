/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.zsd.action.ntBind;

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

import com.zsd.action.base.Transcode;
import com.zsd.factory.AppFactory;
import com.zsd.module.NetTeacherInfo;
import com.zsd.module.NetTeacherStudent;
import com.zsd.module.School;
import com.zsd.module.User;
import com.zsd.page.PageConst;
import com.zsd.service.NetTeacherInfoManager;
import com.zsd.service.NetTeacherStudentManager;
import com.zsd.service.SchoolManager;
import com.zsd.service.UserManager;
import com.zsd.tools.CommonTools;
import com.zsd.tools.CurrentTime;
import com.zsd.util.Constants;

/** 
 * MyEclipse Struts
 * Creation date: 10-11-2019
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class NtStuBindAction extends DispatchAction {
	
	/**
	 * 导向学生导师绑定列表
	 * @author wm
	 * @date 2019-10-12 上午08:28:41
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goNtsPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return mapping.findForward("ntsPage");
	}
	
	/**
	 * 分页获取学生导师绑定列表
	 * @author wm
	 * @date 2019-10-12 上午08:31:30
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getNtsBindData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		NetTeacherStudentManager ntsm = (NetTeacherStudentManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_STUDENT);
		String stuAccount = CommonTools.getFinalStr("stuAccount", request);//学生账号
		String stuRealName = Transcode.unescape_new1("stuRealName", request);//学生真实姓名
		String ntAccount = CommonTools.getFinalStr("ntAccount", request);//网络导师账号
		String ntRealName = Transcode.unescape_new1("ntRealName", request);//网络真实姓名
		Integer subId = CommonTools.getFinalInteger("subId", request);//（0表示全部）
		Integer schoolType = CommonTools.getFinalInteger("schoolType", request);//（0表示全部）
		Integer bindStatus = CommonTools.getFinalInteger("bindStatus", request);//（-2：表示全部，-1：免费试用，1：付费绑定，2：免费绑定）
		Integer pageSize = PageConst.getPageSize(String.valueOf(request.getParameter("limit")), 10);//等同于pageSize
		Integer pageNo = CommonTools.getFinalInteger("page", request);//等同于pageNo
		String bindSdate = CommonTools.getFinalStr("bindSdate", request);
		String bindEdate = CommonTools.getFinalStr("bindEdate", request);
		String currDate = CurrentTime.getStringDate();
		String  msg = "暂无记录";
		Map<String, Object> map = new HashMap<String, Object>();
		Integer count = ntsm.getCountByOpt(stuAccount, stuRealName, ntAccount, ntRealName, subId, schoolType, bindStatus, bindSdate, bindEdate);
		if(count > 0){
			msg = "success";
			List<NetTeacherStudent> ntsList = ntsm.listAllPageInfoByOpt(stuAccount, stuRealName, ntAccount, ntRealName, subId, schoolType, bindStatus, bindSdate, bindEdate, pageNo, pageSize);
			List<Object> list_d = new ArrayList<Object>();
			for(NetTeacherStudent nts : ntsList){
				Map<String, Object> map_d = new HashMap<String, Object>();
				map_d.put("ntsId",nts.getId());
				map_d.put("stuAccount",nts.getUser().getUserAccount());
				map_d.put("stuRealName",nts.getUser().getRealName());
				NetTeacherInfo nt = nts.getNetTeacherInfo();
				map_d.put("ntAccount",nt.getUser().getUserAccount());
				map_d.put("ntRealName",nt.getUser().getRealName());
				Integer schoolType_tmp = nt.getSchoolType();
				String schoolTypeChi = "";
				if(schoolType_tmp.equals(1)){
					schoolTypeChi = "小学";
				}else if(schoolType_tmp.equals(2)){
					schoolTypeChi = "初中";
				}else if(schoolType_tmp.equals(3)){
					schoolTypeChi = "高中";
				}
				map_d.put("ntSubName", schoolTypeChi+nt.getSubject().getSubName());
				Integer bindStatus_tm = nts.getBindStatus();
				String bindStatusChi = "";
				String clearDate = nts.getClearDate();
				String endDate = nts.getEndDate();
				String validInfo = "";
				if(bindStatus_tm.equals(-1)){
					bindStatusChi = "免费试用";
				}else if(bindStatus_tm.equals(0)){
					bindStatusChi = "取消绑定";
				}else if(bindStatus_tm.equals(1)){
					bindStatusChi = "付费绑定";
				}else if(bindStatus_tm.equals(2)){
					bindStatusChi = "免费绑定";
				}
				if(clearDate.equals("")){
					if(CurrentTime.compareDate(currDate, endDate) > 0){
						validInfo = "未到期";
					}else{
						validInfo = "已到期";
					}
				}else{//升学取消
					validInfo = "升学清除";
				}
				map_d.put("bindStatus", bindStatus_tm);
				map_d.put("bindStatusChi", bindStatusChi);
				map_d.put("validInfo", validInfo);
				map_d.put("bindDate", nts.getBindDate());
				map_d.put("endDate", endDate);
				list_d.add(map_d);
			}
			map.put("data", list_d);
			map.put("count", count);
			map.put("code", 0);
		}
		map.put("msg", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 免费试用绑定网络导师
	 * @author wm
	 * @date 2019-10-11 上午09:46:47
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward ntsFreeBind(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		NetTeacherStudentManager ntsm = (NetTeacherStudentManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_STUDENT);
		NetTeacherInfoManager ntm = (NetTeacherInfoManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_INFO);
		UserManager um = (UserManager) AppFactory.instance(null).getApp(Constants.WEB_USER_INFO);
		SchoolManager sm = (SchoolManager) AppFactory.instance(null).getApp(Constants.WEB_SCHOOL_INFO);
		Integer stuId = CommonTools.getLoginUserId(request);
		Integer roleId = CommonTools.getLoginRoleId(request);
		Integer ntId = CommonTools.getFinalInteger("ntId", request);
		String currDate = CurrentTime.getStringDate();
		Map<String,String> map = new HashMap<String,String>();
		String msg = "error";
		if(stuId > 0 && roleId.equals(Constants.STU_ROLE_ID) && ntId > 0){
			List<User> uList = um.listEntityById(stuId);
			if(uList.size() > 0){
				Integer schoolId = uList.get(0).getSchoolId();//获取学生所在的学校
				List<School> sList = sm.listInfoById(schoolId);
				if(sList.size() > 0){
					Integer stuSchoolType = sList.get(0).getSchoolType();
					List<NetTeacherInfo>  ntList = ntm.listntInfoByTeaId(ntId);
					if(ntList.size() > 0){
						NetTeacherInfo nt = ntList.get(0);
						if(nt.getCheckStatus().equals(2)){
							Integer subId = nt.getSubject().getId();
							Integer ntSchoolType = nt.getSchoolType();
							if(stuSchoolType.equals(ntSchoolType)){
								if(ntsm.listAllInfoByOpt(stuId, subId).size() == 0){//免费试用绑定
									ntsm.addNTS(stuId, ntId, currDate, -1, CurrentTime.getFinalDate(6), 0, "", "", 0);
									msg = "success";
								}
							}else{
								msg = "paraDiff";//学段不一致
							}
						}else{
							msg = "checkFail";//该导师没有审核通过
						}
					}
				}
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 绑定网络导师--针对后台操作人员设置修改延长绑定时间--只针对免费试用、免费绑定
	 * @author wm
	 * @date 2019-10-11 上午10:05:22
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward upBind(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		NetTeacherStudentManager ntsm = (NetTeacherStudentManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_STUDENT);
		SchoolManager sm = (SchoolManager) AppFactory.instance(null).getApp(Constants.WEB_SCHOOL_INFO);
		Integer currUserId = CommonTools.getLoginRoleId(request);
		Integer roleId = CommonTools.getLoginRoleId(request);
		Integer ntsId = CommonTools.getFinalInteger("ntsId", request);
		String bindDate = CommonTools.getFinalStr("bindDate", request);
		Map<String,String> map = new HashMap<String,String>();
		String msg = "error";
		if(currUserId > 0 && ntsId > 0 && roleId.equals(Constants.SUPER_ROLE_ID)){
			NetTeacherStudent nts = ntsm.getEntityById(ntsId);
			if(!nts.getBindStatus().equals(1) || nts.getClearStatus().equals(1)){//不能修改付费绑定的信息和升学清除的信息
				User stu = nts.getUser();
				NetTeacherInfo nt = nts.getNetTeacherInfo();
				Integer stuId = stu.getId();
				Integer ntId = nt.getId();
				List<School> sList = sm.listInfoById(stu.getSchoolId());
				if(sList.size() > 0){
					Integer stuSchoolType = sList.get(0).getSchoolType();
					if(nt.getCheckStatus().equals(2)){
						Integer ntSchoolType = nt.getSchoolType();
						if(stuSchoolType.equals(ntSchoolType)){
							//存在其他绑定时不能修改
							NetTeacherStudent nts_bind = ntsm.getValidInfoByOpt(stuId, nt.getSubject().getId());//获取正在绑定的信息（免费试用，免费绑定，付费绑定）
							if(nts_bind != null){//存在该科正在绑定的导师
								if(nts_bind.getNetTeacherInfo().getId().equals(ntId)){
									//可以修改当前记录
									//延长或缩短期限
									ntsm.updateInfoById(ntsId, nts.getBindDate(), nts.getBindStatus(), bindDate, 0);
									msg = "success";
								}else{
									//存在当前学科其他老师的有效绑定，不能修改当前记录
									msg = "notUpdate";
								}
							}else{//绑定被取消或者到期,不存在有效绑定的导师
								//延长或缩短期限并设置绑定状态为免费试用
								ntsm.updateInfoById(ntsId, nts.getBindDate(), -1, bindDate, 0);
								msg = "success";
							}
						}else{
							msg = "paraDiff";//学段不一致
						}
					}else{
						msg = "checkFail";//该导师没有审核通过
					}
				}
			}else{
				msg = "notModify";//付费绑定和已升学清除信息不能修改
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
}