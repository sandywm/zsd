/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.zsd.action.login;

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
import com.zsd.module.GradeSubject;
import com.zsd.module.InviteCodeInfo;
import com.zsd.module.NetTeacherInfo;
import com.zsd.module.NetTeacherStudent;
import com.zsd.module.RoleInfo;
import com.zsd.module.RoleUserInfo;
import com.zsd.module.School;
import com.zsd.module.User;
import com.zsd.module.UserClassInfo;
import com.zsd.service.ClassInfoManager;
import com.zsd.service.EmailManager;
import com.zsd.service.GradeSubjectManager;
import com.zsd.service.InviteCodeInfoManager;
import com.zsd.service.NetTeacherInfoManager;
import com.zsd.service.NetTeacherStudentManager;
import com.zsd.service.NetTeacherStudioManager;
import com.zsd.service.ParentClubManager;
import com.zsd.service.RoleInfoManager;
import com.zsd.service.RoleUserInfoManager;
import com.zsd.service.SchoolManager;
import com.zsd.service.StudentParentInfoManager;
import com.zsd.service.UserClassInfoManager;
import com.zsd.service.UserManager;
import com.zsd.tools.CommonTools;
import com.zsd.tools.Convert;
import com.zsd.tools.CurrentTime;
import com.zsd.tools.DataBaseSqlVerify;
import com.zsd.tools.InviteCode;
import com.zsd.tools.MD5;
import com.zsd.util.Constants;

/** 
 * MyEclipse Struts
 * Creation date: 04-24-2019
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class LoginAction extends DispatchAction {
	
	/**
	 * 登录动作
	 * @description
	 * @author Administrator
	 * @date 2019-4-24 下午04:19:09
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return mapping.findForward("loginOut");
	}
	
	/**
	 * 登出系统
	 * @description
	 * @author Administrator
	 * @date 2019-4-24 下午04:19:21
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward loginOut(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession(false).invalidate();
		return mapping.findForward("loginOut");
	}
	
	/**
	 * 导向注册页面
	 * @description
	 * @author Administrator
	 * @date 2019-4-24 下午04:20:12
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goSignPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return mapping.findForward("signPage");
	}
	/**
	 * 用户登录
	 * @author zong
	 * @date  2019-4-29 下午04:21:29
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward userLogin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserManager uManager = (UserManager) AppFactory.instance(null).getApp(Constants.WEB_USER_INFO);
		RoleUserInfoManager ruManager = (RoleUserInfoManager) AppFactory.instance(null).getApp(Constants.WEB_ROLE_USER_INFO);
		UserClassInfoManager ucm = (UserClassInfoManager) AppFactory.instance(null).getApp(Constants.WEB_USER_CLASS_INFO);
		NetTeacherInfoManager ntm = (NetTeacherInfoManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_INFO);
		/**
		 * true:若存在会话则返回该会话，否则新建一个会话。
		 * false:若存在会话则返回该会话，否则返回NULL
		 */
		HttpSession session = request.getSession(true);
		MD5 md5 = new MD5();
		Map<String,Object> map = new HashMap<String,Object>();
		String account =CommonTools.getFinalStr("account",request);
		String pwd = CommonTools.getFinalStr("password",request);
		String vercode = CommonTools.getFinalStr("vercode",request);//验证码
		//获取图片中的随机数字
		String vercode2 = (String)session.getAttribute("rand");
		String password=md5.calcMD5(pwd);
		String msg = "error";
		//客户端信息
		String clientInfo = CommonTools.getCilentInfo_new(request);
		boolean uFlag= false;
		if(account!=""&& password!=""){
			if(clientInfo.equals("pc")){//电脑端需要匹配验证码
				if(!vercode.equals(vercode2)){
					msg = "vercodeFail";//验证码不匹配 
					uFlag = false;
				}else{
					uFlag = true;
				}
			}else{//app端不需要
				uFlag = true;
			}
			if(uFlag){
				uFlag = DataBaseSqlVerify.checkSql(account);
				if(!uFlag){
					uFlag = uManager.userLogin(account, password);
				}
			}
		}
		if(uFlag){
			//登录成功
			String currdate = CurrentTime.getCurrentTime();
			List<User> uList = uManager.listInfoByAccount(account);
			Integer uid = uList.get(0).getId();
			String  userAcc = uList.get(0).getUserAccount();
			String portrait = uList.get(0).getPortrait();
			//判断用户账号有效状态
			Integer status = uList.get(0).getAccountStatus();
			List<RoleUserInfo> ruList = ruManager.listUserRoleInfoByuserId(uid);
			Integer roleId =0;
			if(!ruList.isEmpty()){
				if(clientInfo.equals("pc")){
					List<Object> list_d = new ArrayList<Object>();
					for(RoleUserInfo ru : ruList){
						Map<String,Object> map_d = new HashMap<String,Object>();
						map_d.put("roleId", ru.getRoleInfo().getId());
						map_d.put("roleName", ru.getRoleInfo().getRoleName());
						list_d.add(map_d);
					}
					map.put("roleList", list_d);
				}else{
					roleId = ruList.get(0).getRoleInfo().getId();
				}
				if(status.equals(1)){//状态 0:无效,1:有效
					Integer loginStatus = uList.get(0).getLoginStatus();//每次登陆，loginStatus自动加1，满50时恢复0状态
					if(loginStatus < 50){
						loginStatus++;
					}else{
						loginStatus = 0;
					}
					//修改用户的登录IP、登录时间、登录次数
					uManager.updateUserLogin(uid, currdate, CommonTools.getIpAddress(request), uList.get(0).getLoginTimes() + 1, loginStatus);
					if(portrait.equals("")){
						portrait="Module/commonJs/ueditor/jsp/head/defaultHead.jpg";
					}
					msg = "success";
					if(clientInfo.equals("pc")){
						session.setAttribute(Constants.LOGIN_USER_ID, uid);
						session.setAttribute(Constants.LOGIN_ACCOUNT, userAcc);
						session.setAttribute(Constants.LOGIN_STATUS, loginStatus);
					}else{
						map.put("loginStatus", loginStatus);
						map.put("roleId", roleId);
						map.put("userAcc", userAcc);
						map.put("password", pwd);
						map.put("portrait", portrait);
						map.put("userId", uid);
						if(roleId.equals(Constants.TEA_ROLE_ID)){//班内老师
							List<UserClassInfo> ucList = ucm.listTeaInfoByOpt(uid, roleId);
							if(ucList.size() > 0){
								map.put("subName",ucList.get(0).getSubjectName());
							}else{
								map.put("subName","暂无");
							}
						}else if(roleId.equals(Constants.NET_TEA_ROLE_ID)){//网络导师
							InviteCodeInfoManager icManager = (InviteCodeInfoManager) AppFactory.instance(null).getApp(Constants.WEB_INVITE_CODE_INFO);
							List<InviteCodeInfo> icList =icManager.listIcInfoByOption(uid, "导师邀请码");
							List<NetTeacherInfo> ntList = ntm.listntInfoByuserId(uid);
							if(ntList.size() > 0){
								map.put("subName",ntList.get(0).getSubject().getSubName());
							}else{
								map.put("subName","暂无");
							}
							if(icList.size()>0){
								map.put("ntInviteCode", icList.get(0).getInviteCode());
							}else{
								map.put("ntInviteCode", "暂无");
							}
						}
					}
				}else{//账号无效
					msg = "lock";
				}
			}else{
				msg = "roleErr";//无身份
			}
		}else{
			msg = "fail";//用户名密码不匹配
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
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
		NetTeacherStudioManager ntStudioManager = (NetTeacherStudioManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_STUDIO);
		ParentClubManager pcManager = (ParentClubManager) AppFactory.instance(null).getApp(Constants.WEB_PARENT_CLUB);
		Map<String,Object> map = new HashMap<String,Object>();
		String userAccount =CommonTools.getFinalStr("userAccount",request);
		String xsAccount = CommonTools.getFinalStr("xsAccount",request);
		String roleName =Transcode.unescape_new("roleName",request);
		String realName=Transcode.unescape_new("realName",request);
		String className=Transcode.unescape_new("className",request);
		String inviteCode=CommonTools.getFinalStr("inviteCode",request);
		String  pwd =CommonTools.getFinalStr("password",request);
		String password=new MD5().calcMD5(pwd);
		String mobile=CommonTools.getFinalStr("mobile",request);
		String lastLoginDate=CurrentTime.getCurrentTime();
		String signDate=CurrentTime.getStringDate();
		Integer schoolId=CommonTools.getFinalInteger("schoolId", request);
		Integer gradeNo=CommonTools.getFinalInteger("gradeNo", request);
		Integer subId=CommonTools.getFinalInteger("subId", request);
		Integer schoolType=CommonTools.getFinalInteger("schoolType", request);
		String msg ="";
		Integer userId =0;
		Integer  xsId =0;
		Integer ntUserId = 0;
		Integer ntId = 0;
		String password_base = new MD5().calcMD5("123456");
		String currDate = CurrentTime.getStringDate();
		String endDate = CurrentTime.getFinalDate(30);
		List<School> scList = scManager.listInfoById(schoolId);
		Integer yearSystem=0;
		if(scList.size()>0){
			yearSystem = scList.get(0).getYearSystem();
		}
		List<User> uList = uManager.listInfoByAccount(userAccount);//判断账户是否存在
		List<User> xsulist =null; 
		if(!xsAccount.equals("")){
			xsulist = uManager.listInfoByAccount(xsAccount);//学生账户是否存在
		}
		msg ="fail";//注册用户失败
		if(roleName.equals("家长")){
		   String xsName=Transcode.unescape_new("stuName",request);
		   String  xsPwd =CommonTools.getFinalStr("xspassword",request);
		   String xsPassword=new MD5().calcMD5(xsPwd);
			if(xsAccount==userAccount){
				msg ="identical";//学生家长账户一样
			}else{
				if(!xsulist.isEmpty()){
					msg="xs_exist"; //学生账户已存在
				}else if(!uList.isEmpty()){
					msg="jz_exist"; 
				}else{
					//1.用户注册
					if(!inviteCode.equals("")){//当存在邀请码时
						List<InviteCodeInfo> icList = icManager.listIcInfoByOpt(inviteCode.toUpperCase(),"导师邀请码");//导师邀请码
						if(icList.size()>0){
							ntUserId = icList.get(0).getInviteId();
							List<NetTeacherInfo> ntlist=ntManager.listntInfoByuserId(ntUserId);
							if(ntlist.get(0).getCheckStatus().equals(2)){//审核通过的导师
								List<School> schList = scManager.listInfoById(schoolId);
						    	Integer stuSchType =0;
						    	Integer ntSchType = ntlist.get(0).getSchoolType();
						    	ntId = ntlist.get(0).getId();
						    	if(!schList.isEmpty()){
						    		stuSchType=schList.get(0).getSchoolType();
						    		if(ntSchType.equals(stuSchType)){
						    			userId=uManager.addUser(userAccount, realName, password, mobile, lastLoginDate, lastLoginIp, 
												signDate, schoolId, endDate, yearSystem, prov, city);
										if(userId>0){//学生账户
											xsId = uManager.addUser(xsAccount, xsName, xsPassword, "", lastLoginDate, lastLoginIp, 
													signDate, schoolId, endDate, yearSystem, prov, city);
											if(xsId > 0){
												ntsManager.addNTS(xsId, ntId, currDate, -1, CurrentTime.getFinalDate(7), 0, "", "", 0);
												msg = "success";
											}else{
												msg = "fail";
											}
										}else{
											msg = "fail";
										}
							    	}else{
							    		msg = "paraDiff";//学段不一致
							    	}
						    	}
							}else{
								msg = "checkFail";//该导师没有审核通过
							}
						}else{
							msg = "noInfo";
						}
					}else{
						uManager.addUser(userAccount, realName, password, mobile, lastLoginDate, lastLoginIp, 
								signDate, schoolId, endDate, yearSystem, prov, city);
						msg = "success";
					}
				}
			}
		}else if(roleName.equals("学生")){
			if(!uList.isEmpty()){
				msg = "xs_exist"; //学生账户已存在
			}else{
				if(!inviteCode.equals("")){//当存在邀请码时
					List<InviteCodeInfo> icList = icManager.listIcInfoByOpt(inviteCode.toUpperCase(),"导师邀请码");//导师邀请码
					if(icList.size()>0){
						ntUserId = icList.get(0).getInviteId();
						List<NetTeacherInfo> ntlist=ntManager.listntInfoByuserId(ntUserId);
						if(ntlist.get(0).getCheckStatus().equals(2)){//审核通过的导师
							List<School> schList = scManager.listInfoById(schoolId);
					    	Integer stuSchType =0;
					    	Integer ntSchType = ntlist.get(0).getSchoolType();
					    	ntId = ntlist.get(0).getId();
					    	if(!schList.isEmpty()){
					    		stuSchType=schList.get(0).getSchoolType();
					    		if(ntSchType.equals(stuSchType)){
					    			userId=uManager.addUser(userAccount, realName, password, mobile, lastLoginDate, lastLoginIp, 
											signDate, schoolId, endDate, yearSystem, prov, city);
									if(userId > 0){
										ntsManager.addNTS(userId, ntId, currDate, -1, CurrentTime.getFinalDate(7), 0, "", "", 0);
									}
						    		msg = "success";
						    	}else{
						    		msg = "paraDiff";//学段不一致
						    	}
					    	}
						}else{
							msg = "checkFail";//该导师没有审核通过
						}
					}else{
						msg = "noInfo";
					}
				}else{
					userId = uManager.addUser(userAccount, realName, password, mobile, lastLoginDate, lastLoginIp, 
							signDate, schoolId, endDate, yearSystem, prov, city);
					msg = "success";
				}
			}
		}else{
			if(uList.size()>0){
				msg="exist"; //用户名存在
			}else{
				//1.用户注册
				userId=uManager.addUser(userAccount, realName, password, mobile, lastLoginDate, lastLoginIp, 
						signDate, schoolId, endDate, yearSystem, prov, city);
				if(userId > 0){
					msg = "success";
				}
			}
		}
		if(msg.equals("success")){
			if(roleName.equals("学生")){
					if(userId>0){
						List<RoleInfo> rList = rManager.listRoleInfo(roleName);
						Integer roleId = 0;
						if(rList.size() > 0){
							roleId = rList.get(0).getId();
							//2学生 绑定角色
							Integer ruId=ruManager.addRoleUserInfo(userId, roleId, "", "", "", "", 0, 0, 0, 0);
							//5 生成家长账户
							Integer upId = uManager.addUser(userAccount+"_jz", "", password_base, "", lastLoginDate, lastLoginIp, signDate, schoolId, CurrentTime.getFinalDateTime(30), yearSystem, prov, city);
							//6 家长绑定角色
							List<RoleInfo> jzlist = rManager.listRoleInfo("家长");
							if(jzlist.size() > 0){
								Integer jzRoleId = jzlist.get(0).getId();
								ruManager.addRoleUserInfo(upId, jzRoleId, "", "", "", "", 0, 0, 0, 0);
							}
							// 7 学生家长绑定
							spManager.addSpInfo(upId, userId);
							pcManager.addParentClub(upId, userAccount+"_jz"+"的家长群", InviteCode.getRandomAllStr(6), 100, "");
							msg = "success";//注册用户成功
							if(ruId>0){//绑定角色成功
								List<ClassInfo> ciList = ciManager.listClassInfoByOption(gradeNo, CurrentTime.getCurrentTime(), schoolId, className);
								if(ciList.size()>0){
									Integer classId = ciList.get(0).getId();
									ucManager.addUcInfo(userId, classId, roleId); //3 绑定用户班级
								}else{//班级不存在
									List<School> scLists = scManager.listInfoById(schoolId);
									String pr="";
									String ct="";
									String county="";
									String town = "";
									Integer schType=0;
									String schoolName="";
									if(!scLists.isEmpty()){
										School sch = scLists.get(0);
										pr= sch.getProv();
										ct= sch.getCity();
										county=sch.getCounty();
										town = sch.getTown();
										schType=sch.getSchoolType();
										schoolName= sch.getSchoolName();
									}

									Integer ciId = ciManager.addClassInfo(schoolId,className,Convert.gradeNoToBuildeClassDate(gradeNo));//创建班级
									
									if(!schoolName.contains("其他学校")){
										Integer mRoId =0;
										List<RoleInfo> mRList = rManager.listRoleInfo("管理员");
										if(mRList.size()>0){
										    mRoId = mRList.get(0).getId();
										}
										
										String currTime=CurrentTime.getCurrentTime();
										Integer cMid=uManager.addUser("c"+ciId, "", password_base, "",currTime, lastLoginIp, currTime, schoolId, "", yearSystem, prov, city);
										
										//绑定班级管理员角色
										Integer ruNo =ruManager.addRoleUserInfo(cMid, mRoId, pr, ct, county, town, schType, schoolId, gradeNo, ciId);
											
										if(ruNo>0){//班内学科老师
											String gName = Convert.NunberConvertChinese(gradeNo);//年级名
											List<GradeSubject> gslist = gsManager.listSpecInfoByGname(gName);//根据年级名获取学科列表
											for (Iterator<GradeSubject> itr = gslist.iterator(); itr.hasNext();) {
												GradeSubject gs = (GradeSubject) itr.next();
												Integer sId = gs.getSubject().getId();//学科编号
												String subName = gs.getSubject().getSubName(); //学科名称
												//生成班内老师账户
												Integer teaId=uManager.addUser("t"+schoolId+sId+ciId, "", password_base, "",currTime, lastLoginIp, currTime, schoolId, "", yearSystem, prov, city);
												//老师绑定角色
												List<RoleInfo> rlist = rManager.listRoleInfo("老师");
												if(rlist.size() > 0){
													Integer teaRoId = rlist.get(0).getId();
													ruManager.addRoleUserInfo(teaId, teaRoId, "", "", "", "", 0, 0, 0, 0);
													ucManager.addUcInfo(teaId, ciId, teaRoId,sId,subName); //绑定班级
												}
											}
										}
									}
									ucManager.addUcInfo(userId, ciId, roleId); //学生用户 绑定班级	
								}
							}
						}
					}else{
						msg ="fail";//注册用户失败
					}
			}else if(roleName.equals("网络导师")){ //网络导师注册
				if(userId>0){
				//网络导师生成自己的邀请码
				String ivCode = InviteCode.getRandomCode();
				Integer icId=icManager.addInviteCodeInfo(userId, "导师邀请码", ivCode.toUpperCase(), CurrentTime.getCurrentTime1());
				if(icId>0){
					Integer baseMoney = 0;
					List<RoleInfo> ntlist = rManager.listRoleInfo("网络导师");
					if(ntlist.size() > 0){
						Integer ntRoleId = ntlist.get(0).getId();
						ruManager.addRoleUserInfo(userId, ntRoleId, "", "", "", "", 0, 0, 0, 0);
					}
					Integer teaId = ntManager.addNtInfo(userId, subId, schoolType, baseMoney, "", "", "", "", "", "", "", "", 0, 0, 0); //添加网络导师基本信息
					
					ntStudioManager.addNTStudio(teaId, userAccount+"的工作室", InviteCode.getRandomAllStr(6), 100, "");
					msg = "success";//注册用户成功
				}
			  }else{
					msg ="fail";//注册用户失败
				}
			}else if(roleName.equals("家长")){
				if(userId>0){
					List<RoleInfo> jzlist = rManager.listRoleInfo(roleName);
					Integer roleId = 0;
					if(jzlist.size() > 0){
						roleId = jzlist.get(0).getId();
						//家长 绑定角色
						ruManager.addRoleUserInfo(userId, roleId, "", "", "", "", 0, 0, 0, 0);
						//获取学生角色信息
						List<RoleInfo> xslist = rManager.listRoleInfo("学生");
						Integer xsRoleId=0;
						if(xslist.size() > 0){
							 xsRoleId = xslist.get(0).getId();
							//学生绑定角色
							ruManager.addRoleUserInfo(xsId, xsRoleId, "", "", "", "", 0, 0, 0, 0);
						}
						//学生家长绑定
						spManager.addSpInfo(userId, xsId);
						Integer pcId=pcManager.addParentClub(userId, userAccount+"的家长群", InviteCode.getRandomAllStr(6), 100, "");
						msg = "success";//注册用户成功
						if(pcId>0){//家长建群成功
							List<ClassInfo> ciList = ciManager.listClassInfoByOption(gradeNo, CurrentTime.getCurrentTime(), schoolId, className);
							if(ciList.size()>0){
								Integer classId = ciList.get(0).getId();
								ucManager.addUcInfo(xsId, classId, xsRoleId); //3 绑定用户班级
							}else{//班级不存在
								List<School> scLists = scManager.listInfoById(schoolId);
								String pr="";
								String ct="";
								String county="";
								String town = "";
								Integer schType=0;
								String schoolName="";
								if(!scLists.isEmpty()){
									School sch = scLists.get(0);
									pr= sch.getProv();
									ct= sch.getCity();
									county=sch.getCounty();
									town = sch.getTown();
									schType=sch.getSchoolType();
									schoolName= sch.getSchoolName();
								}
								Integer ciId = ciManager.addClassInfo(schoolId,className,Convert.gradeNoToBuildeClassDate(gradeNo));//创建班级
								if(!schoolName.contains("其他学校")){
									Integer mRoId =0;
									List<RoleInfo> mRList = rManager.listRoleInfo("管理员");
									if(mRList.size()>0){
									    mRoId = mRList.get(0).getId();
									}
									String currTime=CurrentTime.getCurrentTime();
									Integer cMid=uManager.addUser("c"+ciId, "", password_base, "",currTime, lastLoginIp, currTime, schoolId, "", yearSystem, prov, city);
									
									//绑定班级管理员角色
									Integer ruNo =ruManager.addRoleUserInfo(cMid, mRoId, pr, ct, county, town, schType, schoolId, gradeNo, ciId);
										
									if(ruNo>0){//班内学科老师
										String gName = Convert.NunberConvertChinese(gradeNo);//年级名
										List<GradeSubject> gslist = gsManager.listSpecInfoByGname(gName);//根据年级名获取学科列表
										for (Iterator<GradeSubject> itr = gslist.iterator(); itr.hasNext();) {
											GradeSubject gs = (GradeSubject) itr.next();
											Integer sId = gs.getSubject().getId();
											String subName = gs.getSubject().getSubName(); //学科名称
											//生成班内老师账户
											Integer teaId=uManager.addUser("t"+schoolId+sId+ciId, "", password_base, "",currTime, lastLoginIp, currTime, schoolId, "", yearSystem, prov, city);
											//老师绑定角色
											List<RoleInfo> rlist = rManager.listRoleInfo("老师");
											if(rlist.size() > 0){
												Integer teaRoId = rlist.get(0).getId();
												ruManager.addRoleUserInfo(teaId, teaRoId, "", "", "", "", 0, 0, 0, 0);
												ucManager.addUcInfo(teaId, ciId, teaRoId,sId,subName); //绑定班级
											}
										}
									}
								}
								
								ucManager.addUcInfo(xsId, ciId, xsRoleId); //学生用户 绑定班级	
							}
						}
					}
				}else{
					msg ="fail";//注册用户失败
				}
			}
		}
		if(msg.equals("success")){
			//直接执行登录
			boolean uFlag = uManager.userLogin(userAccount, password);
			if(uFlag){
				//登录成功
				String currdate = CurrentTime.getCurrentTime();
				List<User> uLists = uManager.listInfoByAccount(userAccount);
				User user = uLists.get(0);
				Integer uid = user.getId();
				String  userAcc = user.getUserAccount();
				String portrait = user.getPortrait();
				//判断用户账号有效状态
				Integer status = user.getAccountStatus();
				List<RoleUserInfo> ruList = ruManager.listUserRoleInfoByuserId(uid);
				Integer roleId =0;
				if(!ruList.isEmpty()){
					roleId = ruList.get(0).getRoleInfo().getId();
				}
				if(status.equals(1)){//状态 0:无效,1:有效
					Integer loginStatus = uLists.get(0).getLoginStatus();//每次登陆，loginStatus自动加1，满50时恢复0状态
					if(loginStatus < 50){
						loginStatus++;
					}else{
						loginStatus = 0;
					}
					//修改用户的登录IP、登录时间、登录次数
					uManager.updateUserLogin(uid, currdate, CommonTools.getIpAddress(request), uLists.get(0).getLoginTimes() + 1, loginStatus);
					map.put("roleId", roleId);
					map.put("loginStatus", loginStatus);
					map.put("userAcc", userAcc);
					map.put("password", pwd);
					map.put("portrait", portrait);
					map.put("userId", uid);
					if(roleId.equals(Constants.TEA_ROLE_ID)){//班内老师
						UserClassInfoManager ucm = (UserClassInfoManager) AppFactory.instance(null).getApp(Constants.WEB_USER_CLASS_INFO);
						List<UserClassInfo> ucList = ucm.listTeaInfoByOpt(uid, roleId);
						if(ucList.size() > 0){
							map.put("subName",ucList.get(0).getSubjectName());
						}else{
							map.put("subName","暂无");
						}
					}else if(roleId.equals(Constants.NET_TEA_ROLE_ID)){//网络导师
						NetTeacherInfoManager ntm = (NetTeacherInfoManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_INFO);
						List<InviteCodeInfo> icList =icManager.listIcInfoByOption(uid, "导师邀请码");
						List<NetTeacherInfo> ntList = ntm.listntInfoByuserId(uid);
						if(ntList.size() > 0){
							map.put("subName",ntList.get(0).getSubject().getSubName());
						}else{
							map.put("subName","暂无");
						}
						if(icList.size()>0){
							map.put("ntInviteCode", icList.get(0).getInviteCode());
						}else{
							map.put("ntInviteCode", "暂无");
						}
					}
					msg = "success";
		
				}else{//账号无效
					msg = "lock";
				}
				msg = "success";
			}else{
				msg = "fail";
			}
		}
		map.put("result", msg);
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
	/**
	 * 检查用户名是否已存在
	 * @author zong
	 * 2019年6月13日上午8:28:34
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws ExceptionlistByStuId
	 */
	public ActionForward checkUserName(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserManager uManager = (UserManager) AppFactory.instance(null).getApp(Constants.WEB_USER_INFO);
		Map<String,Object> map = new HashMap<String,Object>();
		String userAccount=CommonTools.getFinalStr("userAccount",request);
		List<User> uList = uManager.listInfoByAccount(userAccount);//判断账户是否存在
		boolean flag = false;
		if(uList.isEmpty()){
			flag = true;
		}
		map.put("msg", flag);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 通过导师邀请码绑定导师
	 * @author zdf
	 * 2019-8-30 下午05:15:40
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bindTeacherByCode(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserManager um = (UserManager) AppFactory.instance(null).getApp(Constants.WEB_USER_INFO);
		InviteCodeInfoManager icManager = (InviteCodeInfoManager) AppFactory.instance(null).getApp(Constants.WEB_INVITE_CODE_INFO);
		NetTeacherStudentManager ntsManager = (NetTeacherStudentManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_STUDENT);
		NetTeacherInfoManager ntManager  = (NetTeacherInfoManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_INFO);
		EmailManager em = (EmailManager) AppFactory.instance(null).getApp(Constants.WEB_EMAIL_INFO);
		SchoolManager schManger = (SchoolManager) AppFactory.instance(null).getApp(Constants.WEB_SCHOOL_INFO);
		String inviteCode=CommonTools.getFinalStr("inviteCode",request);
		Integer userId = CommonTools.getLoginUserId(request);
		List<InviteCodeInfo> icList = null;
		if (!inviteCode.equals("")) {
		   icList = icManager.listIcInfoByOpt(inviteCode.toUpperCase(),"导师邀请码");//导师邀请码 
		}
		Map<String,Object> map = new HashMap<String,Object>();
		if(icList.isEmpty()){
			map.put("msg","noInfo");
		}else{
			List<User> uList = um.listEntityById(userId);
			if(uList.size() > 0){
				String userName_stu = uList.get(0).getRealName();
				Integer teaId=icList.get(0).getInviteId();	
			    List<NetTeacherInfo> ntlist=ntManager.listntInfoByuserId(teaId);
			    if(ntlist.get(0).getCheckStatus().equals(2)){
			    	NetTeacherInfo nt = ntlist.get(0);
			    	Integer userId_tea = nt.getUser().getId();
				    String userName_tea = nt.getUser().getRealName();
				    Integer schType = nt.getSchoolType();
				    Integer  subId=nt.getSubject().getId();
				    boolean flag = ntsManager.isBindTeaBySubIdAndSchType(userId, subId, schType);
				    if(flag){
				    	map.put("msg","binded");
				    }else{
				    	List<School> schList = schManger.listInfoById(uList.get(0).getSchoolId());
				    	Integer stuSchType =0;
				    	if(!schList.isEmpty()){
				    		stuSchType=schList.get(0).getSchoolType();
				    	}
				    	if(schType.equals(stuSchType)){
				    		Integer ntsId =  ntsManager.addNTS(userId, teaId, CurrentTime.getCurrentTime(), -1, CurrentTime.getFinalDateTime(7), 0, "", "", 0);//4 缃戠粶瀵煎笀瀛︾敓缁戝畾
							if(ntsId>0){
								String content_stu = userName_stu+"同学,你好，你已成功绑定"+userName_tea+"老师为你的网络导师，如遇到学习上有什么问题，请及时向导师提出，谢谢。[知识典]";
								String content_tea = userName_tea+"导师，您好，"+userName_stu+"学生已成功绑定您为他的网络导师，请全程为学生做好答疑引导服务，谢谢。[知识典]";
								em.addEmail(1, "绑定导师成功", content_stu, "sys", userId);
								em.addEmail(1, "绑定导师", content_tea, "sys", userId_tea);
								map.put("msg","success");
							}
				    	}else{
				    		map.put("msg", "schTypeError");
				    	}
				    	
				    }
			    }else{
			    	map.put("msg","noInfo");
			    }
			}
		}
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	/**
	 * 取消导师绑定
	 * @author zdf
	 * 2019-8-30 下午05:25:07
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward unBindTeacher(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		EmailManager em = (EmailManager) AppFactory.instance(null).getApp(Constants.WEB_EMAIL_INFO);
		NetTeacherStudentManager ntsManager = (NetTeacherStudentManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_STUDENT);
		Map<String,Object> map = new HashMap<String,Object>();
		Integer ntsId=CommonTools.getFinalInteger("ntsId", request); //绑定导师主键
		String cancelDate= CurrentTime.getStringDate();
		NetTeacherStudent nts = ntsManager.getEntityById(ntsId);
		String msg ="error";
		if(nts != null){
			boolean flag =	ntsManager.updateNTS(ntsId, 0, cancelDate);
			if(flag){
				msg="success";
				String userName_stu = nts.getUser().getRealName();
				Integer userId_stu = nts.getUser().getId();
				String userName_nt = nts.getNetTeacherInfo().getUser().getRealName();
				Integer userId_nt = nts.getNetTeacherInfo().getUser().getId();
				String content_stu = userName_stu+"同学,你好，你已取消和"+userName_nt+"老师的绑定关系。[知识典]";
				String content_tea = userName_nt+"导师，您好，"+userName_stu+"学生已取消和您的绑定关系。[知识典]";
				em.addEmail(1, "取消绑定导师", content_stu, "sys", userId_stu);
				em.addEmail(1, "绑定取消", content_tea, "sys", userId_nt);
			}else{
				msg="fail";
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
}