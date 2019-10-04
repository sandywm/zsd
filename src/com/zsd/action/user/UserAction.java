/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.zsd.action.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.zsd.action.base.Transcode;
import com.zsd.factory.AppFactory;
import com.zsd.module.ClassInfo;
import com.zsd.module.NetTeacherStudent;
import com.zsd.module.RoleInfo;
import com.zsd.module.RoleUserInfo;
import com.zsd.module.School;
import com.zsd.module.User;
import com.zsd.module.UserClassInfo;
import com.zsd.page.PageConst;
import com.zsd.service.ClassInfoManager;
import com.zsd.service.EmailManager;
import com.zsd.service.NetTeacherStudentManager;
import com.zsd.service.RoleInfoManager;
import com.zsd.service.RoleUserInfoManager;
import com.zsd.service.SchoolManager;
import com.zsd.service.UserClassInfoManager;
import com.zsd.service.UserManager;
import com.zsd.tools.CommonTools;
import com.zsd.tools.Convert;
import com.zsd.tools.FileOpration;
import com.zsd.tools.MD5;
import com.zsd.util.Constants;
import com.zsd.util.WebUrl;


/** 
 * MyEclipse Struts
 * Creation date: 04-24-2019
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class UserAction extends DispatchAction {
	
	/**
	 * 根据用户身份导向不同界面
	 * @description
	 * @author Administrator
	 * @date 2019-4-24 下午04:31:43
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		RoleInfoManager rm = (RoleInfoManager) AppFactory.instance(null).getApp(Constants.WEB_ROLE_INFO);
		Integer roleId = CommonTools.getFinalInteger("roleId", request);
		String roleName = "";
		String urlPage = "";
		if(roleId > 0){
			RoleInfo role = rm.getEntityById(roleId);
			roleName = role.getRoleName();
			if(roleName.equals("超级管理员") || roleName.equals("知识点管理员")){
				urlPage = "managerPage";
			}else{
				urlPage = "welcomePage";
			}
			HttpSession session = request.getSession(false);
			session.setAttribute(Constants.LOGIN_USER_ROLE_ID, roleId);
			session.setAttribute(Constants.LOGIN_USER_ROLE_NAME, roleName);
		}
		return mapping.findForward(urlPage);
	}
	
	/**
	 * 导向用户管理页面
	 * @author wm
	 * @date 2019-10-2 上午08:53:12
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goUserPage (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("userPage");
	}
	
	/**
	 * 根据条件查看用户信息(用户管理)
	 * @author zong
	 * 2019-6-11上午11:28:14
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getUserByOption (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserManager uManager = (UserManager) AppFactory.instance(null).getApp(Constants.WEB_USER_INFO);
		RoleUserInfoManager ruManager = (RoleUserInfoManager) AppFactory.instance(null).getApp(Constants.WEB_ROLE_USER_INFO);
		SchoolManager schManager = (SchoolManager) AppFactory.instance(null).getApp(Constants.WEB_SCHOOL_INFO);
		ClassInfoManager  cManager= (ClassInfoManager) AppFactory.instance(null).getApp(Constants.WEB_CLASS_INFO);
		String accName =CommonTools.getFinalStr("accName",request);//默认""
		Integer roleId =CommonTools.getFinalInteger("roleId",request);//默认0
		String realName=Transcode.unescape_new1("realName",request);//默认""
		Integer schoolId=CommonTools.getFinalInteger("schoolId",request);//默认0
		String prov=Transcode.unescape_new1("prov",request);//默认""
		String city=Transcode.unescape_new1("city",request);//默认""
		String county=Transcode.unescape_new1("county",request);//默认""
		String town = Transcode.unescape_new1("town", request);//默认""
		Integer schoolType=CommonTools.getFinalInteger("schoolType", request);//默认0
		Integer gradeNo=CommonTools.getFinalInteger("gradeNo", request);//默认0
		Integer classId=CommonTools.getFinalInteger("classId", request);//默认0
		Integer count = uManager.getUserByoptionCount(accName, realName, schoolId, roleId, prov, city, county, town, schoolType, gradeNo, classId);
		String msg = "暂无记录";
		Map<String,Object> map = new HashMap<String,Object>();
		if(count>0){
			Integer pageSize = PageConst.getPageSize(String.valueOf(request.getParameter("limit")), 10);//等同于pageSize
			Integer pageNo = CommonTools.getFinalInteger("page", request);//等同于pageNo
			List<User> uList = uManager.listUserInfoByoption(accName, realName, schoolId, roleId, prov, city, county, town, schoolType, gradeNo, classId, pageNo, pageSize);
			List<Object> list = new ArrayList<Object>();
			for(Iterator<User> it = uList.iterator() ; it.hasNext();){
				User user = it.next();
				Map<String,Object> map_u = new HashMap<String,Object>();
				map_u.put("id", user.getId());
				map_u.put("accName", user.getUserAccount());
				map_u.put("realName", user.getRealName());
				map_u.put("nickName", user.getNickName());
				List<RoleUserInfo> ruList  = ruManager.listUserRoleInfoByuserId(user.getId());
				RoleUserInfo ruInfo = ruList.get(0);
				map_u.put("roleName", ruInfo.getRoleInfo().getRoleName());
				String sexStr = user.getSex();
				String sex ="";
				if(sexStr.equalsIgnoreCase("m")){
					sex="男";
				}else if(sexStr.equalsIgnoreCase("f")){
					sex="女";
				}
				map_u.put("sex", sex);
				map_u.put("accStatus", user.getAccountStatus());
				map_u.put("QQ", user.getQq());
				map_u.put("birthday", user.getBirthday());
				map_u.put("endDate",user.getEndDate());
				map_u.put("prov", ruInfo.getProv());
				map_u.put("city",ruInfo.getCity());
				map_u.put("county", ruInfo.getCounty());
				map_u.put("town", ruInfo.getTown());
				map_u.put("freeStatus", user.getFreeStatus());
				Integer schType = ruInfo.getSchoolType();
				String schTypeStr = "";
				if(schType.equals(1)){
					schTypeStr = "小学";
				}else if(schType.equals(2)){
					schTypeStr = "初中";
				}else if(schType.equals(3)){
					schTypeStr = "高中";
				}
				map_u.put("schoolType", schTypeStr);
				List<School> schList = schManager.listInfoById(ruInfo.getSchoolId());
				if(schList.size() > 0){
					map_u.put("schoolName", schList.get(0).getSchoolName());
				}else{
					map_u.put("schoolName", "");
				}
				map_u.put("gradeName", Convert.NunberConvertChinese(ruInfo.getGradeNo()));
				List<ClassInfo> cInfo = cManager.listClassInfoById(ruInfo.getClassId());
				if(cInfo.size() > 0){
					map_u.put("className", cInfo.get(0).getClassName());
				}else{
					map_u.put("className", "");
				}
				
				list.add(map_u);
			}
			map.put("data", list);
			map.put("count", count);
			map.put("code", 0);
			msg = "success";
		}
		map.put("msg", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 获取用户明细
	 * @author wm
	 * @date 2019-10-4 下午02:21:58
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getUserDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserManager um = (UserManager) AppFactory.instance(null).getApp(Constants.WEB_USER_INFO);
		Integer userId = CommonTools.getFinalInteger("userId", request);
		String msg = "error";
		Map<String,Object> map = new HashMap<String,Object>();
		if(userId > 0){
			List<User> uList = um.listEntityById(userId);
			if(uList.size() > 0){
				User user = uList.get(0);
				msg = "success";
				map.put("userId", userId);
				map.put("accStatus", user.getAccountStatus());
				map.put("endDate",user.getEndDate());
				map.put("freeStatus", user.getFreeStatus());
			}else{
				msg = "noInfo";
			}
		}
		map.put("msg", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 修改用户的截止时间或者账号状态
	 * @author zong
	 * 2019-5-11下午03:44:20
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateUserBydateOraccSta(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserManager uManager = (UserManager) AppFactory.instance(null).getApp(Constants.WEB_USER_INFO);
		Integer userId=CommonTools.getLoginUserId(request);
		String endDate=CommonTools.getFinalStr("endDate",request);
		Integer accStatus=CommonTools.getFinalInteger("accStatus", request);
		Integer freeSta=CommonTools.getFinalInteger("freeSta", request);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "fail";
		boolean uflag = uManager.updateUser(userId, accStatus, freeSta, endDate);
		if(uflag){
			msg ="success";
		}
		map.put("msg", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
		
	}
	/**
	 * 修改用户的电子邮箱地址
	 * @author zong
	 * 2019-5-13上午10:51:56
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateUserByEmail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserManager uManager = (UserManager) AppFactory.instance(null).getApp(Constants.WEB_USER_INFO);
		Integer userId=CommonTools.getLoginUserId(request);
		String email=CommonTools.getFinalStr("email",request);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "fail";
		boolean uflag = uManager.updateUserByEmail(userId, email);
		if(uflag){
			msg ="success";
		}
		map.put("msg", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
		
	}
	/**
	 * 修改用户的电话号码
	 * @author zong
	 * 2019-5-13上午10:53:23
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateUserByMobile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserManager uManager = (UserManager) AppFactory.instance(null).getApp(Constants.WEB_USER_INFO);
		Integer userId=CommonTools.getLoginUserId(request);
		String mobile=CommonTools.getFinalStr("mobile",request);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "fail";
		boolean uflag = uManager.updateUserByMobile(userId, mobile);
		if(uflag){
			msg ="success";
		}
		map.put("msg", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
		
	}
	/**
	 * 修改用户密码
	 * @author zong
	 * 2019-5-13上午10:55:30
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateUserByPwd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserManager uManager = (UserManager) AppFactory.instance(null).getApp(Constants.WEB_USER_INFO);
		Integer userId=CommonTools.getLoginUserId(request);
		String password=new MD5().calcMD5(CommonTools.getFinalStr("password", request));
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "fail";
		
		boolean uflag = uManager.updateUserBypwd(userId, password);
		if(uflag){
			msg ="success";
		}
		map.put("msg", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
		
	}
	/**
	 * 检查是否为当前的用户密码
	 * @author zong
	 * 2019-5-14下午05:18:29
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkUserPwd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserManager uManager = (UserManager) AppFactory.instance(null).getApp(Constants.WEB_USER_INFO);
		Map<String,Object> map = new HashMap<String,Object>();
		Integer userId=CommonTools.getLoginUserId(request);
		String password=new MD5().calcMD5(CommonTools.getFinalStr("password", request));
		boolean flag = uManager.checkCurrpwd(userId, password);
		map.put("msg", flag);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	/**
	 * 修改学生用户个人中心基本信息
	 * @author zong
	 * 2019-6-3上午10:21:14
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateUserByStuInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserManager uManager = (UserManager) AppFactory.instance(null).getApp(Constants.WEB_USER_INFO);
		Integer userId=CommonTools.getLoginUserId(request);
		String sex=CommonTools.getFinalStr("sex",request);
		String birthday=CommonTools.getFinalStr("birthday",request);
		String qq=CommonTools.getFinalStr("qq",request);
		Map<String,Object> map = new HashMap<String,Object>();
		String nickName = Transcode.unescape_new1("nickName",request);
		String realName = Transcode.unescape_new1("realName",request);
		String msg = "fail";
		boolean uflag = uManager.updateUserStu(userId, nickName, realName, sex, birthday, qq);
		if(uflag){
			msg ="success";
		}
		map.put("msg", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
		
	}
	/**
	 * 保存用户头像
	 * @author zong
	 * 2019-6-3下午04:48:32
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateUserByPortrait(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserManager uManager = (UserManager) AppFactory.instance(null).getApp(Constants.WEB_USER_INFO);
		Integer userId=CommonTools.getLoginUserId(request);
		String base64=CommonTools.getFinalStr("portrait",request);
		String portrait = Base64Utils.Base64ToImage(base64, userId);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "fail";
		
		List<User> uList = uManager.listEntityById(userId);
		String portrait1 = uList.get(0).getPortrait();
		
		boolean uflag = uManager.updateUserByHead(userId, portrait);
		//删除更新前的原头像文件 
	    if(portrait1!=""){
	    	portrait1 = WebUrl.DATA_URL +"\\"+portrait1.replace("Module/commonJs/ueditor/jsp/head/", "");
	    	FileOpration.deleteFile(portrait1);
	    }
		if(uflag){
			msg ="success";
		}
		map.put("msg", msg);
		map.put("imgUrl", portrait);
		CommonTools.getJsonPkg(map, response);
		return null;	
	}
	/**
	 * 基本资料(学生个人中心)
	 * @author zong
	 * @modify by wm
	 * 2019-6-4下午04:43:25
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getStuInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserManager uManager = (UserManager) AppFactory.instance(null).getApp(Constants.WEB_USER_INFO);
		Map<String,Object> map = new HashMap<String,Object>();
		Integer userId=CommonTools.getLoginUserId(request);
		List<User> uList = uManager.listEntityById(userId);
		User user = uList.get(0);
		map.put("account", user.getUserAccount());
		map.put("nickName", user.getNickName());
		map.put("realName", user.getRealName());
		map.put("birthDay", user.getBirthday());
		String sex="女";
		if(user.getSex().equals("m")){
			sex="男";
		}
		map.put("sex", sex);
		map.put("email",user.getEmail());
		map.put("mobile", user.getMobile());
		map.put("portrait", user.getPortrait());
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	/**
	 * 头像信息(学生个人中心)
	 * @author zong
	 * 2019-6-4下午05:01:41
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getStuPortrait(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserManager uManager = (UserManager) AppFactory.instance(null).getApp(Constants.WEB_USER_INFO);
		Map<String,Object> map = new HashMap<String,Object>();
		Integer userId=CommonTools.getLoginUserId(request);
		List<User> uList = uManager.listEntityById(userId);
		User user = uList.get(0);
		map.put("id", user.getId());
		map.put("portrait", user.getPortrait());
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	/**
	 * 安全设置(学生个人中心)
	 * @author zong
	 * 2019-6-4下午05:16:09
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getStuScy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserManager uManager = (UserManager) AppFactory.instance(null).getApp(Constants.WEB_USER_INFO);
		Map<String,Object> map = new HashMap<String,Object>();
		Integer userId=CommonTools.getLoginUserId(request);
		List<User> uList = uManager.listEntityById(userId);
		User user = uList.get(0);
		map.put("id", user.getId());
		map.put("lastLoginIp", user.getLastLoginIp());
		map.put("email",user.getEmail());
		map.put("mobile", user.getMobile());
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	/**
	 * 学生家长账户信息(学生个人中心)
	 * @author zong
	 * 2019-6-4下午05:20:20
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getStuParentaccInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserManager uManager = (UserManager) AppFactory.instance(null).getApp(Constants.WEB_USER_INFO);
		Map<String,Object> map = new HashMap<String,Object>();
		Integer userId=CommonTools.getLoginUserId(request);
		List<User> uList = uManager.listEntityById(userId);
		User user = uList.get(0);
		map.put("parentAcc", user.getUserAccount()+"_jz");
		map.put("password",123456);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 我的导师(学生)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getNtByStuId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		NetTeacherStudentManager ntsManager = (NetTeacherStudentManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_STUDENT);
		Map<String,Object> map = new HashMap<String,Object>();
		Integer userId=CommonTools.getLoginUserId(request);
		List<NetTeacherStudent> ntslist =ntsManager.listByStuId(userId);
		List<Object> list_d = new ArrayList<Object>();
		for (Iterator<NetTeacherStudent> itr = ntslist.iterator(); itr.hasNext();) {
			NetTeacherStudent nts = (NetTeacherStudent) itr.next();
			Map<String,Object> map_d = new HashMap<String,Object>();
			map_d.put("realName", nts.getNetTeacherInfo().getUser().getRealName());
			map_d.put("subName", nts.getNetTeacherInfo().getSubject().getSubName());
			if(nts.getNetTeacherInfo().getSchoolType().equals(1)){
				map_d.put("schType", "小学");
			}else if(nts.getNetTeacherInfo().getSchoolType().equals(2)){
				map_d.put("schType", "初中");
			}else if(nts.getNetTeacherInfo().getSchoolType().equals(3)){
				map_d.put("schType", "高中");
			}
			map_d.put("portrait", nts.getNetTeacherInfo().getUser().getPortrait());
			map_d.put("bindStatus", nts.getBindStatus());
			map_d.put("bindDate", nts.getBindDate());
			map_d.put("clearDate", nts.getClearDate());
			map_d.put("clearStatus", nts.getClearStatus());
			list_d.add(map_d);
		}
		map.put("ntlist", list_d);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 获取学生当前学籍信息,是否升学
	 * @author wm
	 * @date 2019-8-30 上午09:57:33
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getCurrStuByInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserClassInfoManager ucm = (UserClassInfoManager) AppFactory.instance(null).getApp(Constants.WEB_USER_CLASS_INFO);
		SchoolManager sm = (SchoolManager) AppFactory.instance(null).getApp(Constants.WEB_SCHOOL_INFO);
		NetTeacherStudentManager ntsm = (NetTeacherStudentManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_STUDENT);
		EmailManager em = (EmailManager) AppFactory.instance(null).getApp(Constants.WEB_EMAIL_INFO);
		Integer userId = CommonTools.getLoginUserId(request);
		Integer roleId = CommonTools.getLoginRoleId(request);
		Integer graduationStatus = 0;//表示是否升学【0未升学，1升学】
		String currYearSystem = "";//表示学生的学年制
		String currPara = "";//当前所在学段
		Integer currUserGradeNumber = 0;//当前学生年级号
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "error";
		User user = null;
		if(userId > 0 && roleId.equals(Constants.STU_ROLE_ID)){
			List<UserClassInfo> uList = ucm.listInfoByOpt_1(userId, roleId);
			if(uList.size() > 0){
				UserClassInfo uc = uList.get(0);
				user = uc.getUser();
				ClassInfo c = uc.getClassInfo();
				String buildClassDate = c.getBuildeClassDate();
				//计算出当前学生今天所在的年级
				currUserGradeNumber = Convert.dateConvertGradeNumber(buildClassDate);
				Integer yearSystem = user.getYearSystem();//学年制6,5,4,3
				Integer schoolId = user.getSchoolId();
				if(schoolId > 0){
					List<School> sList = sm.listInfoById(schoolId);
					if(sList.size() > 0){
						msg = "success";
						Integer schoolType = sList.get(0).getSchoolType();
						if(schoolType.equals(1)){//小学
							if(yearSystem.equals(6)){
								if(currUserGradeNumber <= 6){//未升学
									
								}else if(currUserGradeNumber > 6 && currUserGradeNumber <= 9){//初中
									graduationStatus = 1;
									currPara = "初中";
									currYearSystem = "3";
								}else{//高中
									graduationStatus = 1;
									currPara = "高中";
									currYearSystem = "3";
								}
							}else if(yearSystem.equals(5)){
								if(currUserGradeNumber <= 5){//未升学
									
								}else if(currUserGradeNumber > 5 && currUserGradeNumber <= 9){//初中
									graduationStatus = 1;
									currPara = "初中";
									currYearSystem = "4";
								}else{//高中
									graduationStatus = 1;
									currPara = "高中";
									currYearSystem = "3";
								}
							}
						}else if(schoolType.equals(2)){//初中
							if(yearSystem.equals(4)){
								if(currUserGradeNumber > 5 && currUserGradeNumber <= 9){//未升学
									
								}else{//高中
									graduationStatus = 1;
									currPara = "高中";
									currYearSystem = "3";
								}
							}else if(yearSystem.equals(3)){
								if(currUserGradeNumber > 6 && currUserGradeNumber <= 9){//未升学
									
								}else{//高中
									graduationStatus = 1;
									currPara = "高中";
									currYearSystem = "3";
								}
							}
						}
					}
				}
			}
		}
		if(msg.equals("success")){
			map.put("gduStatus", graduationStatus);//升学状态【0未升学，1升学】
			if(graduationStatus.equals(1)){//升学，需要取消绑定的网络导师
				map.put("currPara", currPara);//当前学段
				map.put("currYearSystem", currYearSystem);//当前学年制
				map.put("currUserGradeNumber", currUserGradeNumber);//当前年级号
				map.put("currUserGradeName", Convert.NunberConvertChinese(currUserGradeNumber));//当前年级名称
				//查看绑定日期没结束且未取消未清除的信息列表
				List<NetTeacherStudent> ntsList = ntsm.listValidInfoByOpt(userId);
				if(ntsList.size() > 0){
					for(NetTeacherStudent nts : ntsList){
						Integer ntsId = nts.getId();
						boolean flag = ntsm.clearUserNetTeacher(ntsId);
						if(flag){
							String title = "升学信息";
							User ntUser = nts.getNetTeacherInfo().getUser();
							Integer ntUserId = ntUser.getId();
							String ntName = ntUser.getRealName();
							String stuName = user.getRealName();
							String content_nt=""+ntName+"导师，您好，"+stuName+"学生已升学，系统强制取消你们之间的绑定关系。";
							String content_stu=""+stuName+"同学，您好，由于升学，系统已取消您之前绑定的"+ntName+"网络导师。";
							em.addEmail(1, title, content_stu, "sys", userId);//专门生成一个id=1的账号来发送系统邮件
							em.addEmail(1, title, content_nt, "sys", ntUserId);
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
	 * 因为升学，需要重置学生的学校，年级，班级信息
	 * @author wm
	 * @date 2019-8-30 上午11:04:38
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateStuClassInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserClassInfoManager ucm = (UserClassInfoManager) AppFactory.instance(null).getApp(Constants.WEB_USER_CLASS_INFO);
		UserManager um = (UserManager) AppFactory.instance(null).getApp(Constants.WEB_USER_INFO);
		Integer userId = CommonTools.getLoginUserId(request);
		Integer roleId = CommonTools.getLoginRoleId(request);
		Integer classId = CommonTools.getFinalInteger("classId", request);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "error";
		if(roleId.equals(Constants.STU_ROLE_ID) && userId > 0 && classId > 0){
			List<UserClassInfo> ucList = ucm.listInfoByOpt_1(userId, roleId);
			if(ucList.size() > 0){
				UserClassInfo uc = ucList.get(0);
				Integer ucId = uc.getId();
				Integer schoolId = uc.getClassInfo().getSchool().getId();
				Integer yearSystem = uc.getClassInfo().getSchool().getYearSystem();
				//修改学校信息
				um.updateSchoolInfo(userId, schoolId, yearSystem);
				//修改学生班级信息
				ucm.updateStuClassInfoById(ucId, classId);
				msg = "success";
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
}
