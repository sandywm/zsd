/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.zsd.action.applyClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import com.zsd.module.ApplyClassInfo;
import com.zsd.module.ClassInfo;
import com.zsd.module.School;
import com.zsd.module.User;
import com.zsd.module.UserClassInfo;
import com.zsd.service.ApplyClassManager;
import com.zsd.service.ClassInfoManager;
import com.zsd.service.SchoolManager;
import com.zsd.service.UserClassInfoManager;
import com.zsd.service.UserManager;
import com.zsd.tools.CommonTools;
import com.zsd.tools.Convert;
import com.zsd.tools.CurrentTime;
import com.zsd.util.Constants;

/** 
 * MyEclipse Struts
 * Creation date: 07-28-2019
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class ApplyClassAction extends DispatchAction {
	
	/**
	 * 班级列表按照降序排列（九年级1班）
	 * @author Administrator
	 * @createDate 2019-8-3
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private class SortCName implements Comparator {
		@Override
	    public int compare(Object obj0, Object obj1) {
	      Map<String, String> map0 = (Map) obj0;
	      Map<String, String> map1 = (Map) obj1;
	      int flag = map1.get("cName").toString().compareTo(map0.get("cName").toString());
	      return flag; // 不取反，则按正序排列
	    }
	 }
	
	/**
	 * 导向班级接管、转让班级页面
	 * @author wm
	 * @date 2019-7-28 下午05:17:55
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward goApplyClassPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return mapping.findForward("applyPage");
	}
	
	/**
	 * 获取班级接管、转让班级数据
	 * @author wm
	 * @date 2019-7-28 下午05:18:38
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward getApplyClassData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ApplyClassManager acm = (ApplyClassManager) AppFactory.instance(null).getApp(Constants.WEB_APPLY_CLASS_INFO);
		Integer currUserId = CommonTools.getLoginUserId(request);
		String sDate = CommonTools.getFinalStr("sDate", request);
		String eDate = CommonTools.getFinalStr("eDate", request);
		Integer opt = CommonTools.getFinalInteger("opt", request);//我的申请(1),我的处理(2)
		Integer checkStatus = CommonTools.getFinalInteger("checkStatus", request);//审核状态[0:未处理，1：同意，2：拒绝]
		Integer pageNo = CommonTools.getFinalInteger("pageNo", request);//当前页
		String msg = "noInfo";
		Map<String,Object> map = new HashMap<String,Object>();
		if(sDate.equals("") && eDate.equals("")){//默认最近三天
			eDate = CurrentTime.getStringDate();
			sDate = CurrentTime.getFinalDate(eDate, -2);
		}
		Integer sendUserId = 0;
		Integer toUserId = 0;
		if(opt.equals(1)){//我的主动申请
			sendUserId = currUserId;
		}else if(opt.equals(2)){//别人对我的申请
			toUserId = currUserId;
		}
		String cilentInfo = CommonTools.getCilentInfo_new(request);
		if(cilentInfo.equals("pc")){
			checkStatus = -1;//移动端为全部
		}
		List<ApplyClassInfo> acList = acm.listPageInfoByOpt(sendUserId, toUserId, checkStatus, sDate, eDate, pageNo, 10);
		if(acList.size() > 0){
			msg = "success";
			List<Object> list_d = new ArrayList<Object>();
			for(ApplyClassInfo ac : acList){
				Map<String,Object> map_d = new HashMap<String,Object>();
				map_d.put("acId", ac.getId());
				Integer checkStatus_db = ac.getCheckStatus();
				String appDetail = "";
				String checkStatusChi = "";
				String appltOptChi = ac.getApplyOpt().equals(1) ? "临时" : "永久";
				String realName = ac.getUser().getRealName();
				if(checkStatus_db.equals(0)){
					checkStatusChi = "未处理";
				}else if(checkStatus_db.equals(1)){
					checkStatusChi = "同意";
				}else if(checkStatus_db.equals(2)){
					checkStatusChi = "拒绝";
				}
				if(checkStatus_db.equals(0)){
					if(opt.equals(1)){//我的主动申请
						appDetail = "你申请"+appltOptChi+"接管"+ac.getClassDetail();
					}else{
						appDetail = realName+"老师申请"+appltOptChi+"接管你的"+ac.getClassDetail();
						map_d.put("classInfo", ac.getClassDetail());
						map_d.put("applyTeaName", realName);
					}
				}else{
					if(opt.equals(1)){//我的主动申请
						appDetail = "你申请"+appltOptChi+"接管"+ac.getClassDetail()+"已"+checkStatusChi;
					}else{
						appDetail = "你已"+checkStatusChi+realName+"老师对"+ac.getClassDetail()+appltOptChi+"接管";
					}
				}
				map_d.put("applyDetail", appDetail);
				map_d.put("applyTime", ac.getApplyTime());
				map_d.put("checkStatusChi",checkStatusChi);
				map_d.put("applyOptChi",appltOptChi);
				list_d.add(map_d);
			}
			map.put("acList", list_d);
		}
		map.put("result", msg);
		map.put("sDate", sDate);
		map.put("eDate", eDate);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 获取申请详情
	 * @author wm
	 * @date 2019-7-29 上午09:39:16
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getApplyClassDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ApplyClassManager acm = (ApplyClassManager) AppFactory.instance(null).getApp(Constants.WEB_APPLY_CLASS_INFO);
		Integer acId = CommonTools.getFinalInteger("acId", request);
		Integer currUserId = CommonTools.getLoginUserId(request);
		ApplyClassInfo ac = acm.getEntityById(acId);
		String msg = "noInfo";
		Map<String,Object> map = new HashMap<String,Object>();
		if(ac != null){
			Integer applyUserId = ac.getUser().getId();
			String realName = ac.getUser().getRealName();
			Integer toUserId = ac.getToUserId();
			if(currUserId.equals(applyUserId) || currUserId.equals(toUserId)){
				msg = "success";
				map.put("applyUserName", ac.getUser().getRealName());
				String applyOptChi = ac.getApplyOpt().equals(1) ? "临时" : "永久";
				Integer checkStatus_db = ac.getCheckStatus();
				String checkStatusChi = "";
				if(checkStatus_db.equals(0)){
					checkStatusChi = "未处理";
				}else if(checkStatus_db.equals(1)){
					checkStatusChi = "同意";
				}else if(checkStatus_db.equals(2)){
					checkStatusChi = "拒绝";
				}
				String applyDetail = "";
				if(currUserId.equals(applyUserId)){//自己作为申请人
					applyDetail = "您申请"+applyOptChi+"接管"+ac.getClassDetail();
				}else{
					applyDetail = realName+"老师申请"+applyOptChi+"接管你的"+ac.getClassDetail();
				}
				map.put("acId", ac.getId());
				map.put("applyDetail", applyDetail);
				map.put("applyTime", ac.getApplyTime());
				map.put("checkStatusChi", checkStatusChi);
				map.put("checkRemark", ac.getCheckRemark());
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 获取未处理的接班申请（申请我的班级）
	 * @author wm
	 * @date 2019-7-29 上午10:53:21
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getMyUnCheckApplyInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ApplyClassManager acm = (ApplyClassManager) AppFactory.instance(null).getApp(Constants.WEB_APPLY_CLASS_INFO);
		String msg = "noInfo";
		Map<String,Object> map = new HashMap<String,Object>();
		Integer currUserId = CommonTools.getLoginUserId(request);
		List<ApplyClassInfo> acList = acm.listMyUnCheckApplyInfo(currUserId,0);
		if(acList.size() > 0){
//			List<Object> list_d = new ArrayList<Object>();
			msg = "success";
//			for(ApplyClassInfo ac : acList){
//				Map<String,Object> map_d = new HashMap<String,Object>();
//				String applyOptChi = ac.getApplyOpt().equals(1) ? "临时" : "永久";
//				String realName = ac.getUser().getRealName();
//				String appDetail = realName+"老师申请"+applyOptChi+"接管你的"+ac.getClassDetail();
//				map_d.put("acId", ac.getId());
//				map_d.put("applyDetail", appDetail);
//				list_d.add(map_d);
//			}
//			map.put("acList", list_d);
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 处理接班申请(人为)
	 * @author wm
	 * @date 2019-7-29 上午09:57:36
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dealApplyClass(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ApplyClassManager acm = (ApplyClassManager) AppFactory.instance(null).getApp(Constants.WEB_APPLY_CLASS_INFO);
		UserClassInfoManager ucm = (UserClassInfoManager) AppFactory.instance(null).getApp(Constants.WEB_USER_CLASS_INFO);
		Integer acId = CommonTools.getFinalInteger("acId", request);
		Integer selCheckStatus = CommonTools.getFinalInteger("checkStatus", request);//1：同意，2：拒绝
		Integer currUserId = CommonTools.getLoginUserId(request);
		ApplyClassInfo ac = acm.getEntityById(acId);
		String msg = "error";
		Map<String,String> map = new HashMap<String,String>();
		if(ac != null){
			Integer applyUserId = ac.getUser().getId();
			String applyUsreName = ac.getUser().getRealName();
			Integer toUserId = ac.getToUserId();//能处理
			if(toUserId.equals(currUserId)){
				if(ac.getCheckStatus().equals(0)){//未处理的才能进行处理
					if(selCheckStatus.equals(1) || selCheckStatus.equals(2)){
						Integer classId = ac.getClassInfo().getId();
						UserClassInfo uc = ucm.getEntityByOpt(currUserId, classId, 4);
						if(uc != null){
							boolean flag = acm.setCancleInfo(acId, selCheckStatus, "");
							if(flag){
								//同意时其他类似的全部修改为拒绝
								if(selCheckStatus.equals(1)){
									//修改用户班级表信息
									Integer applyOpt = ac.getApplyOpt();//1：临时，2：永久
									Integer owerUserId = 0;
									//修改UC表
									if(applyOpt.equals(2)){
										owerUserId = applyUserId;
									}
									ucm.updateInfoByOpt(uc.getId(), owerUserId, applyUserId, applyUsreName, applyOpt);
									//查看还存在其他一样的申请信息没
									List<ApplyClassInfo> acList = acm.listMyUnCheckApplyInfo(currUserId,classId);
									for(ApplyClassInfo ac1 : acList){
										acm.setCancleInfo(ac1.getId(), 2, "");
									}
								}
								msg = "success";
							}
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
	 * 获取指定学校有效年级列表（已被创建的班级）--申请接管时用
	 * @author wm
	 * @date 2019-7-29 上午11:19:57
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getValidGradeData(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		UserClassInfoManager ucm = (UserClassInfoManager) AppFactory.instance(null).getApp(Constants.WEB_USER_CLASS_INFO);
		ClassInfoManager cm = (ClassInfoManager) AppFactory.instance(null).getApp(Constants.WEB_CLASS_INFO);
		SchoolManager sm = (SchoolManager) AppFactory.instance(null).getApp(Constants.WEB_SCHOOL_INFO);
		UserManager um = (UserManager) AppFactory.instance(null).getApp(Constants.WEB_USER_INFO);
		Integer currUserId = CommonTools.getLoginUserId(request);
		List<UserClassInfo> ucList = ucm.listTeaInfoByOpt(currUserId, Constants.TEA_ROLE_ID);
		UserClassInfo uc = null;
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "noInfo";
		if(ucList.size() > 0){
			String smallBuildeClassDate = "";
			for(UserClassInfo uc_1 : ucList){
				ClassInfo c = uc_1.getClassInfo();
				String buildeClassDate = c.getBuildeClassDate();
				if(smallBuildeClassDate.equals("")){
					smallBuildeClassDate = buildeClassDate;
					uc = uc_1;
				}else{
					if(CurrentTime.compareDate(buildeClassDate, smallBuildeClassDate) > 0){//获取最小的
						smallBuildeClassDate = buildeClassDate;
						uc = uc_1;
					}
				}
			}
			String buildeClassDate = uc.getClassInfo().getBuildeClassDate();
			String gradeName = Convert.dateConvertGradeName(buildeClassDate);//当前所在的年级
			Integer schoolId = uc.getUser().getSchoolId();
			List<School> sList = sm.listInfoById(schoolId);
			if(sList.size() > 0){
				msg = "success";
				School sch = sList.get(0);
				Integer schoolType = sch.getSchoolType();//小学(1),初中(2),高中(3)
				Integer yearSystem = sch.getYearSystem();//3,4,5,6
				List<Object> list_d = new ArrayList<Object>();
				String currentTime = CurrentTime.getStringDate();
				Integer startNum = 1;
				Integer endNum = 1;
				if(schoolType.equals(1)){//5,6
					endNum = yearSystem;
				}else if(schoolType.equals(2)){//3,4
					if(yearSystem.equals(3)){
						startNum = 7;
					}else{
						startNum = 6;
					}
					endNum = 10;
				}else if(schoolType.equals(3)){
					startNum = 10;
					endNum = 12;
				}
				for(int gradeNo = startNum ; gradeNo <= endNum ; gradeNo++){
					//获取指定年级下的真实班级列表
					List<ClassInfo> cList = cm.listClassInfoByOption(gradeNo, currentTime, schoolId, "");
					if(cList.size() > 0){
						Map<String,Object> map_d = new HashMap<String,Object>();
						String gradeName_tmp = Convert.NunberConvertChinese(gradeNo);
						map_d.put("gradeNo", gradeNo);
						map_d.put("gradeName", gradeName_tmp);
						map_d.put("selFlag", gradeName_tmp.equals(gradeName) ? true : false);
						list_d.add(map_d);
					}
				}
				map.put("gradeList", list_d);
			}
		}else{//要是该老师没有任何任教班级（被转让完了）
			List<User> uList = um.listEntityById(currUserId);
			if(uList.size() > 0){
				User user = uList.get(0);
				Integer schoolId = user.getSchoolId();
				List<School> sList = sm.listInfoById(schoolId);
				if(sList.size() > 0){
					msg = "success";
					School sch = sList.get(0);
					Integer schoolType = sch.getSchoolType();//小学(1),初中(2),高中(3)
					Integer yearSystem = sch.getYearSystem();//3,4,5,6
					List<Object> list_d = new ArrayList<Object>();
					String currentTime = CurrentTime.getStringDate();
					Integer startNum = 1;
					Integer endNum = 1;
					if(schoolType.equals(1)){//5,6
						endNum = yearSystem;
					}else if(schoolType.equals(2)){//3,4
						if(yearSystem.equals(3)){
							startNum = 7;
						}else{
							startNum = 6;
						}
						endNum = 10;
					}else if(schoolType.equals(3)){
						startNum = 10;
						endNum = 12;
					}
					for(int gradeNo = startNum ; gradeNo <= endNum ; gradeNo++){
						//获取指定年级下的真实班级列表
						List<ClassInfo> cList = cm.listClassInfoByOption(gradeNo, currentTime, schoolId, "");
						if(cList.size() > 0){
							Map<String,Object> map_d = new HashMap<String,Object>();
							String gradeName_tmp = Convert.NunberConvertChinese(gradeNo);
							map_d.put("gradeNo", gradeNo);
							map_d.put("gradeName", gradeName_tmp);
							list_d.add(map_d);
						}
					}
					map.put("gradeList", list_d);
				}
			}
			
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 获取指定学校指定年级下的有效班级列表（已被创建的班级）--申请接管时用
	 * @author wm
	 * @date 2019-8-28 下午05:21:53
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public ActionForward getValidClassData(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		UserClassInfoManager ucm = (UserClassInfoManager) AppFactory.instance(null).getApp(Constants.WEB_USER_CLASS_INFO);
		UserManager um = (UserManager) AppFactory.instance(null).getApp(Constants.WEB_USER_INFO);
		ClassInfoManager cm = (ClassInfoManager) AppFactory.instance(null).getApp(Constants.WEB_CLASS_INFO);
		Integer currUserId = CommonTools.getLoginUserId(request);
		Integer gradeNo = CommonTools.getFinalInteger("gradeNo", request);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "noInfo";
		String currentTime = CurrentTime.getStringDate();
		if(gradeNo > 0){
			List<User> uList = um.listEntityById(currUserId);
			if(uList.size() > 0){
				Integer schoolId = uList.get(0).getSchoolId();
				//获取指定年级下的真实班级列表
				List<ClassInfo> cList = cm.listClassInfoByOption(gradeNo, currentTime, schoolId, "");
				List<Object> list_d1 = new ArrayList<Object>();
				if(cList.size() > 0){
					List<UserClassInfo> ucList_db = ucm.listTeaInfoByOpt(currUserId, Constants.TEA_ROLE_ID);//查看该老师目前有哪些永久和临时接管的班级
					msg = "success";
					for(ClassInfo c : cList){
						boolean existFlag = false;
						if(ucList_db.size() > 0){
							for(UserClassInfo uc : ucList_db){
								if(uc.getClassInfo().getId().equals(c.getId())){
									existFlag = true;
									break;
								}
							}
						}
						if(!existFlag){
							Map<String,Object> map_d1 = new HashMap<String,Object>();
							map_d1.put("cId", c.getId());
							map_d1.put("cName", c.getClassName());
							List<UserClassInfo> ucList = ucm.listInfoByOpt(c.getId(), Constants.TEA_ROLE_ID);
							if(ucList.size() >  0){
								map_d1.put("teaName", ucList.get(0).getUser().getRealName());
							}else{
								map_d1.put("teaName", "暂无老师");
							}
							list_d1.add(map_d1);
							SortCName sort = new SortCName();
							Collections.sort(list_d1, sort);
						}
					}
					map.put("cList", list_d1);
				}
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 增加申请
	 * @author wm
	 * @date 2019-7-29 上午11:00:20
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addApplyClass(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ApplyClassManager acm = (ApplyClassManager) AppFactory.instance(null).getApp(Constants.WEB_APPLY_CLASS_INFO);
		ClassInfoManager cm = (ClassInfoManager) AppFactory.instance(null).getApp(Constants.WEB_CLASS_INFO);
		UserClassInfoManager ucm = (UserClassInfoManager) AppFactory.instance(null).getApp(Constants.WEB_USER_CLASS_INFO);
		Integer applyOpt = CommonTools.getFinalInteger("applyOpt", request);//1：临时，2：永久
		Integer currUserId = CommonTools.getLoginUserId(request);
		Integer classId = CommonTools.getFinalInteger("classId", request);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "error";
		if(classId > 0){
			//获取该老师存在该班级为审核的接管申请记录
			List<ApplyClassInfo> apList = acm.listInfoByOpt(currUserId, 0, classId, 0);
			if(apList.size() > 0){
				msg = "existInfo";
			}else{
				List<ClassInfo> cList = cm.listClassInfoById(classId);
				if(cList.size() > 0){
					String buildeClassDate = cList.get(0).getBuildeClassDate();
					List<UserClassInfo> ucList = ucm.listInfoByOpt(classId, Constants.TEA_ROLE_ID);//获取班级永久老师
					if(ucList.size() > 0){
						Integer classTeaId = ucList.get(0).getUser().getId();String classDetail = Convert.dateConvertGradeName(buildeClassDate) + cList.get(0).getClassName();//当前所在的年级班级
						Integer acId = acm.addApplyClassInfo(currUserId, classId, classDetail, classTeaId, applyOpt);
						if(acId > 0){
							msg = "success";
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
	 * 取消我的临时被接管班级
	 * @author wm
	 * @date 2019-8-28 下午05:34:17
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cancelMyLsInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ApplyClassManager acm = (ApplyClassManager) AppFactory.instance(null).getApp(Constants.WEB_APPLY_CLASS_INFO);
		UserClassInfoManager ucm = (UserClassInfoManager) AppFactory.instance(null).getApp(Constants.WEB_USER_CLASS_INFO);
		Integer currUserId = CommonTools.getLoginUserId(request);
		Integer classId = CommonTools.getFinalInteger("classId", request);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "error";
		if(currUserId > 0 && classId > 0){
			List<UserClassInfo> ucList = ucm.listInfoByOpt(classId, Constants.TEA_ROLE_ID);
			if(ucList.size() > 0){
				UserClassInfo uc = ucList.get(0);
				if(currUserId.equals(uc.getUser().getId())){
					//只有班级永久老师才能取消
					Integer applyTeaId = uc.getAppUserId();
					if(applyTeaId > 0){
						ApplyClassInfo ac = acm.getEntityByOpt(applyTeaId, classId);
						if(ac != null){
							acm.setCancleInfo(ac.getId(), 2, "原班级任课老师强制取消");
							msg = "success";
						}
					}
				}
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
}