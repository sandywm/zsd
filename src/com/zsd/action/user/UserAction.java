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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.zsd.action.base.Transcode;
import com.zsd.factory.AppFactory;
import com.zsd.module.ClassInfo;
import com.zsd.module.GradeSubject;
import com.zsd.module.InviteCodeInfo;
import com.zsd.module.RoleInfo;
import com.zsd.module.RoleUserInfo;
import com.zsd.module.School;
import com.zsd.module.User;
import com.zsd.page.PageConst;
import com.zsd.service.ClassInfoManager;
import com.zsd.service.GradeSubjectManager;
import com.zsd.service.InviteCodeInfoManager;
import com.zsd.service.NetTeacherInfoManager;
import com.zsd.service.NetTeacherStudentManager;
import com.zsd.service.RoleInfoManager;
import com.zsd.service.RoleUserInfoManager;
import com.zsd.service.SchoolManager;
import com.zsd.service.StudentParentInfoManager;
import com.zsd.service.UserClassInfoManager;
import com.zsd.service.UserManager;
import com.zsd.tools.CommonTools;
import com.zsd.tools.Convert;
import com.zsd.tools.CurrentTime;
import com.zsd.tools.FileOpration;
import com.zsd.tools.InviteCode;
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
		String roleName = Transcode.unescape_new("roleName", request);
		String urlPage = "";
		if(roleName.equals("超级管理员") || roleName.equals("知识点管理员")){
			urlPage = "managerPage";
		}else{
			urlPage = "welcomePage";
		}
		return mapping.findForward(urlPage);
	}
	
	/**
	 * 注册用户信息
	 * @author zong
	 * @date  2019-4-29 上午10:46:12
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward regUserInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String lastLoginIp = CommonTools.getIpAddress(request); //最后登录Ip
		String area = CommonTools.getSelfArea(lastLoginIp);
		String prov = area.split(":")[0];
		String city = area.split(":")[1];
		UserManager uManager = (UserManager) AppFactory.instance(null).getApp(Constants.WEB_USER_INFO);
		SchoolManager scManager = (SchoolManager) AppFactory.instance(null).getApp(Constants.WEB_SCHOOL_INFO);
		RoleUserInfoManager ruManager = (RoleUserInfoManager) AppFactory.instance(null).getApp(Constants.WEB_ROLE_USER_INFO);
		RoleInfoManager rManager = (RoleInfoManager) AppFactory.instance(null).getApp(Constants.WEB_ROLE_INFO);
		UserClassInfoManager ucManager = (UserClassInfoManager) AppFactory.instance(null).getApp(Constants.WEB_USER_CLASS_INFO);
		ClassInfoManager ciManager = (ClassInfoManager) AppFactory.instance(null).getApp(Constants.WEB_CLASS_INFO);
		NetTeacherStudentManager ntsManager = (NetTeacherStudentManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_STUDENT);
		InviteCodeInfoManager icManager = (InviteCodeInfoManager) AppFactory.instance(null).getApp(Constants.WEB_INVITE_CODE_INFO);
		StudentParentInfoManager spManager = (StudentParentInfoManager) AppFactory.instance(null).getApp(Constants.WEB_STUDENT_PARENT_INFO);
		NetTeacherInfoManager ntManager = (NetTeacherInfoManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_INFO);
		GradeSubjectManager gsManager = (GradeSubjectManager) AppFactory.instance(null).getApp(Constants.WEB_GRADE_SUBJECT_INFO);
		Map<String,Object> map = new HashMap<String,Object>();
		String userAccount =CommonTools.getFinalStr("userAccount",request);
		String roleName =Transcode.unescape_new("roleName",request);
		String realName=Transcode.unescape_new("realName",request);
		String className=Transcode.unescape_new("className",request);
		String inviteCode=CommonTools.getFinalStr("inviteCode",request);
		String password=new MD5().calcMD5(CommonTools.getFinalStr("password",request));
		String mobile=CommonTools.getFinalStr("mobile",request);
		String lastLoginDate=CurrentTime.getCurrentTime();
		String signDate=CurrentTime.getCurrentTime();
		Integer schoolId=CommonTools.getFinalInteger("schoolId", request);
		Integer gradeNo=CommonTools.getFinalInteger("gradeNo", request);
		Integer subId=CommonTools.getFinalInteger("subId", request);
		Integer schoolType=CommonTools.getFinalInteger("schoolType", request);
		String msg ="";
		Integer userId =0;
		List<School> scList = scManager.listInfoById(schoolId);
		Integer yearSystem=0;
		if(scList.size()>0){
			yearSystem = scList.get(0).getYearSystem();
		}
		List<User> uList = uManager.listInfoByAccount(userAccount);//判断账户是否存在
		msg ="fail";//注册用户失败
		if(uList.size()>0){
			msg="exist"; //用户名存在
		}else{
			//1.用户注册
			userId=uManager.addUser(userAccount, realName, password, mobile, lastLoginDate, lastLoginIp, signDate, schoolId, CurrentTime.getFinalDateTime(30), yearSystem, prov, city);
		}
		if(roleName.equals("学生")){
				if(userId>0){
					List<RoleInfo> rList = rManager.listRoleInfo(roleName);
					Integer roleId = 0;
					if(rList.size() > 0){
						roleId = rList.get(0).getId();
						//2 绑定角色
						Integer ruId=ruManager.addRoleUserInfo(userId, roleId, "", "", "", "", 0, 0, 0, 0);
						msg = "success";//注册用户成功
						if(ruId>0){//绑定角色成功
							List<ClassInfo> ciList = ciManager.listClassInfoByOption(gradeNo, CurrentTime.getCurrentTime(), schoolId, className);
							if(ciList.size()>0){
								Integer classId = ciList.get(0).getId();
								ucManager.addUcInfo(userId, classId, roleId); //3 绑定用户班级
								List<InviteCodeInfo> icList = icManager.listIcInfoByicCode(inviteCode);
								if(icList.size()>0){
									Integer teaId=icList.get(0).getInviteId();
									ntsManager.addNTS(userId, teaId, CurrentTime.getCurrentTime(), -1, CurrentTime.getFinalDateTime(7), 0, "", "", 0);//4 缃戠粶瀵煎笀瀛︾敓缁戝畾
									//5 生成家长账户
									Integer upId = uManager.addUser(userAccount+"_jz", "", new MD5().calcMD5("123456"), "", lastLoginDate, lastLoginIp, signDate, schoolId, CurrentTime.getFinalDateTime(30), yearSystem, prov, city);
									//6 家长绑定角色
									List<RoleInfo> jzlist = rManager.listRoleInfo("家长");
									if(jzlist.size() > 0){
										Integer jzRoleId = jzlist.get(0).getId();
										ruManager.addRoleUserInfo(upId, jzRoleId, "", "", "", "", 0, 0, 0, 0);
									}
									// 7 学生家长绑定
									spManager.addSpInfo(upId, userId);
								}
							}else{//班级不存在
								Integer mRoId =0;
								List<RoleInfo> mRList = rManager.listRoleInfo("管理员");
								if(mRList.size()>0){
								    mRoId = mRList.get(0).getId();
								}
								List<RoleUserInfo> rulist = ruManager.listUserRoleInfoBySchId(schoolId);
								String pr="";
								String ct="";
								String county="";
								Integer schType=0;
								if(!rulist.isEmpty()){
									pr= rulist.get(0).getProv();
									ct= rulist.get(0).getCity();
									county=rulist.get(0).getCounty();
									schType=rulist.get(0).getSchoolType();
								}
								Integer ciId = ciManager.addClassInfo(schoolId,className,Convert.gradeNoToBuildeClassDate(gradeNo));//创建班级
								String currTime=CurrentTime.getCurrentTime();
								Integer cMid=uManager.addUser("c"+ciId, "", new MD5().calcMD5("123456"), "",currTime, lastLoginIp, currTime, schoolId, "", yearSystem, prov, city);
								
								//绑定班级管理员角色
								Integer ruNo =ruManager.addRoleUserInfo(cMid, mRoId, pr, ct, county, "", schType, schoolId, gradeNo, ciId);
									
								
								if(ruNo>0){//班内学科老师
									String gName = Convert.NunberConvertChinese(gradeNo);//年级名
									List<GradeSubject> gslist = gsManager.listSpecInfoByGname(gName);//根据年级名获取学科列表
									for (GradeSubject gs : gslist) {
										Integer sId = gs.getSubject().getId();
										//生成班内老师账户
										Integer teaId=uManager.addUser("t"+schoolId+sId+ciId, "", new MD5().calcMD5("123456"), "",currTime, lastLoginIp, currTime, schoolId, "", yearSystem, prov, city);
										//老师绑定角色
										List<RoleInfo> rlist = rManager.listRoleInfo("老师");
										if(rlist.size() > 0){
											Integer teaRoId = rlist.get(0).getId();
											ruManager.addRoleUserInfo(teaId, teaRoId, "", "", "", "", 0, 0, 0, 0);
											ucManager.addUcInfo(teaId, ciId, teaRoId); //绑定班级
										}
										
									}
									           
								}
							}
						}
					}
				}else{
					msg ="fail";//注册用户失败
				}
		}else if(roleName.equals("网络导师")){ //网络导师注册
			//网络导师生成自己的邀请码
			String ivCode = InviteCode.getRandomCode();
			Integer icId=icManager.addInviteCodeInfo(userId, "导师邀请码", ivCode, CurrentTime.getCurrentTime1());
			if(icId>0){
				Integer baseMoney = 0;
				if(schoolType.equals(1)){
					baseMoney = (int) Constants.NET_TEACHER_SERVICE_FEE_XX;
				}else if(schoolType.equals(2)){
					baseMoney = (int) Constants.NET_TEACHER_SERVICE_FEE_CZ;
				}else{
					baseMoney = (int) Constants.NET_TEACHER_SERVICE_FEE_GZ;
				}
			
				List<RoleInfo> ntlist = rManager.listRoleInfo("网络导师");
				if(ntlist.size() > 0){
					Integer ntRoleId = ntlist.get(0).getId();
					ruManager.addRoleUserInfo(userId, ntRoleId, "", "", "", "", 0, 0, 0, 0);
				}
				ntManager.addNtInfo(userId, subId, schoolType, baseMoney, "", "", "", "", "", "", "", "", 0, 0, 0); //添加网络导师基本信息
				msg = "success";//注册用户成功
			}
		}
		
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	/**
	 * 
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
		String accName =CommonTools.getFinalStr("accName",request);
		Integer roleId =CommonTools.getFinalInteger("roleId",request);
		String realName=Transcode.unescape_new1("realName",request);
		Integer schoolId=CommonTools.getFinalInteger("schoolId",request);
		String prov=Transcode.unescape_new1("prov",request);
		String city=Transcode.unescape_new1("city",request);
		String county=Transcode.unescape_new1("county",request);
		Integer schoolType=CommonTools.getFinalInteger("schoolType", request);
		Integer gradeNo=CommonTools.getFinalInteger("gradeNo", request);
		Integer classId=CommonTools.getFinalInteger("classId", request);
		Integer count = uManager.getUserByoptionCount(accName, realName, schoolId, roleId, prov, city, county, schoolType, gradeNo, classId);
		String msg = "暂无记录";
		Map<String,Object> map = new HashMap<String,Object>();
		if(count>0){
			Integer pageSize = PageConst.getPageSize(String.valueOf(request.getParameter("limit")), 10);//等同于pageSize
			Integer pageNo = CommonTools.getFinalInteger("page", request);//等同于pageNo
			List<User> uList = uManager.listUserInfoByoption(accName, realName, schoolId, roleId, prov, city, county, schoolType, gradeNo, classId, pageNo, pageSize);
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
				Integer accSta = user.getAccountStatus();
				String accStr = "";
				if(accSta.equals(0)){
					accStr="无效";
				}else if(accSta.equals(1)){
					accStr="有效";
				}
				map_u.put("accStatus",accStr);
				map_u.put("QQ", user.getQq());
				map_u.put("birthday", user.getBirthday());
				map_u.put("endDate",user.getEndDate());
				map_u.put("prov", ruInfo.getProv());
				map_u.put("city",ruInfo.getCity());
				map_u.put("county", ruInfo.getCounty());
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
				map_u.put("schoolName", schList.get(0).getSchoolName());
				map_u.put("gradeName", Convert.NunberConvertChinese(ruInfo.getGradeNo()));
				List<ClassInfo> cInfo = cManager.listClassInfoById(ruInfo.getClassId());
				map_u.put("className", cInfo.get(0).getClassName());
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
		Integer userId=CommonTools.getFinalInteger("userId", request);
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
		Integer userId=(Integer) request.getSession().getAttribute("userId");
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
		Integer userId=(Integer) request.getSession().getAttribute("userId");
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
		Integer userId=(Integer) request.getSession().getAttribute("userId");
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
		Integer userId=(Integer) request.getSession().getAttribute("userId");
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
		String portrait=CommonTools.getFinalStr("portrait",request);
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
		CommonTools.getJsonPkg(map, response);
		return null;	
	}
	/**
	 * 基本资料(学生个人中心)
	 * @author zong
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
		map.put("id", user.getId());
		map.put("account", user.getUserAccount());
		map.put("nickName", user.getNickName());
		map.put("realName", user.getRealName());
		map.put("birthDay", user.getBirthday());
		map.put("sex", user.getSex());
		map.put("email",user.getEmail());
		map.put("mobile", user.getMobile());
		map.put("qq", user.getQq());
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
	 * 检查手机号码是否注册
	 * @author zong
	 * 2019-5-14下午05:18:29
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkMobile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserManager uManager = (UserManager) AppFactory.instance(null).getApp(Constants.WEB_USER_INFO);
		Map<String,Object> map = new HashMap<String,Object>();
		String mobile=CommonTools.getFinalStr("mobile",request);
		boolean flag = uManager.checkUserMobile(mobile);
		map.put("msg", flag);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
}
