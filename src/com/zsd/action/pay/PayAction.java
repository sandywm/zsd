/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.zsd.action.pay;

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
import com.zsd.module.School;
import com.zsd.module.SysFeeInfo;
import com.zsd.module.User;
import com.zsd.module.UserClassInfo;
import com.zsd.service.SchoolManager;
import com.zsd.service.SysFeeManager;
import com.zsd.service.UserClassInfoManager;
import com.zsd.tools.CommonTools;
import com.zsd.tools.Convert;
import com.zsd.tools.CurrentTime;
import com.zsd.util.Constants;

/** 
 * MyEclipse Struts
 * Creation date: 09-10-2019
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class PayAction extends DispatchAction {
	
	/**
	 * 导向 在线购买服务费页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward goOnlineFeePage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return mapping.findForward("onlineFeePage");
	}
	
	/**
	 * 获取在线购买 会员时的费用 
	 * 每月按照30天计算
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward getOnlineFee(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		SysFeeManager sfm = (SysFeeManager) AppFactory.instance(null).getApp(Constants.WEB_SYS_FEE_INFO);
		UserClassInfoManager ucm = (UserClassInfoManager) AppFactory.instance(null).getApp(Constants.WEB_USER_CLASS_INFO);
		SchoolManager sm = (SchoolManager) AppFactory.instance(null).getApp(Constants.WEB_SCHOOL_INFO);
		Integer stuId = CommonTools.getLoginUserId(request);
		Integer roleId  = CommonTools.getLoginRoleId(request);
		Integer selMonth = CommonTools.getFinalInteger("selMonth", request);
		Integer feeType = CommonTools.getFinalInteger("feeType", request);//费用类型(1:导师费,2:会员费)
		Integer fee = 0;//购买所需费用
		String msg = "error";
		String feeOpt = "sameFee";
		Map<String,Object> map = new HashMap<String,Object>();
		if(stuId > 0 && roleId.equals(Constants.STU_ROLE_ID) && selMonth > 0 && selMonth <= 12 && feeType > 0){
			//获取当前学生能购买的的时长（最大到升学日期）不足一月按一月计算
			List<UserClassInfo> uList = ucm.listInfoByOpt_1(stuId, roleId);
			if(uList.size() > 0){
				UserClassInfo uc = uList.get(0);
				User user = uc.getUser();
				ClassInfo c = uc.getClassInfo();
				String buildClassDate = c.getBuildeClassDate();
				String currDate = CurrentTime.getStringDate();
				String endDate = user.getEndDate();
				String endDate_fee = CurrentTime.getStringDate();//会员到期日，已到期时默认为当前日期
				Integer currUserGradeNumber_curr = 0;//当前会员到期日所在的年级
				//判断会员有无过期
				if(CurrentTime.compareDate(currDate, endDate) > 0){//会员没过期
					//计算endDate时的所在年级
					currUserGradeNumber_curr = Convert.dateConvertGradeNumber(endDate,buildClassDate);
					endDate_fee = endDate;
				}else{
					//计算出当前学生今天所在的年级
					currUserGradeNumber_curr = Convert.dateConvertGradeNumber(buildClassDate);
				}
				//会员到期日+购买时长后的日期
				String endDate_new = CurrentTime.getFinalDate_1(endDate_fee, selMonth);
				//获取购买会员后新的会员到期日后所在的年级
				Integer currUserGradeNumber_new = Convert.dateConvertGradeNumber(endDate_new,buildClassDate);//购买会员费后到期日所在的年级
				Integer yearSystem = user.getYearSystem();//学年制6,5,4,3
				Integer schoolId = user.getSchoolId();
				List<School> sList = sm.listInfoById(schoolId);
				if(sList.size() > 0){
					Integer schoolType = sList.get(0).getSchoolType();
					if(schoolType.equals(1)){//小学
						if(yearSystem.equals(6)){//六年制
							if(currUserGradeNumber_new < 7){//不涉及升学
								//获取小学费用
								List<SysFeeInfo> sfList = sfm.listInfoByopt(feeType, schoolType, 1);
								if(sfList.size() > 0){
									//可以购买--获取费用
									msg = "success";
									fee = sfList.get(0).getFee() * selMonth;
								}
							}else{//涉及升学
								if(feeType.equals(2)){//会员费可购买
									
								}else{
									
								}
								//涉及到升学，当前月份时长不能购买
								msg = "noBuy";
							}
						}else{//五年制
							if(currUserGradeNumber_new < 6){//不涉及升学
								//获取小学费用
								List<SysFeeInfo> sfList = sfm.listInfoByopt(feeType, schoolType, 1);
								if(sfList.size() > 0){
									//可以购买--获取费用
									msg = "success";
									fee = sfList.get(0).getFee() * selMonth;
								}
							}else{
								//涉及到升学，当前月份时长不能购买
								msg = "noBuy";
							}
						}
					}else if(schoolType.equals(2)){//初中（9年级可进行切换8、7、[6]）
						if(currUserGradeNumber_new < 10){
							//可以购买
							if(currUserGradeNumber_curr.equals(8) && currUserGradeNumber_new >= 9){
								//8年级购买会员时包括了9年级部分。需要把8年级部分时间的费用和9年级部分时间的费用结合形成新的费用
								//获取有多少天在8年级，有多少天在9年级
								Integer diffDays = CurrentTime.compareDate(endDate_fee, Convert.gradeNoToBuildeClassDate(9));//8年级天数
								Integer remainDays = (selMonth * 30) - diffDays;//9年级天数
								List<SysFeeInfo> sfList = sfm.listInfoByopt(feeType, schoolType, 1);
								if(sfList.size() > 0){
									List<SysFeeInfo> sfList_1 = sfm.listInfoByopt(feeType, 21, 1);//9年级费用
									if(sfList_1.size() > 0){
										//可以购买--获取费用
										msg = "success";
										feeOpt = "diffFee";//存在不同费用
										Integer fee_base = sfList.get(0).getFee();//6,7,8年级费用
										Integer fee_base_1 = sfList_1.get(0).getFee();//9年级费用
										fee = fee_base * diffDays + fee_base_1 * remainDays;
										map.put("fee_1", fee_base);
										map.put("days_1", diffDays);
										map.put("gradeName_1", "八年级");
										map.put("fee_2", fee_base_1);
										map.put("days_2", remainDays);
										map.put("gradeName_2", "九年级");
									}
								}
							}else{
								//获取初中费用
								List<SysFeeInfo> sfList = sfm.listInfoByopt(feeType, schoolType, 1);
								if(sfList.size() > 0){
									//可以购买--获取费用
									msg = "success";
									fee = sfList.get(0).getFee() * selMonth;
								}
							}
						}else{
							//涉及到升学，当前月份时长不能购买
							msg = "noBuy";
						}
					}else{//高中(高三可进行切换高一、二)
						//无封顶，可以购买
						msg = "success";
						if(currUserGradeNumber_curr.equals(11) && currUserGradeNumber_new >= 12){
							//高二购买会员时包括了高三部分。需要把高二部分时间的费用和高三部分时间的费用结合形成新的费用
							//获取有多少天在8年级，有多少天在9年级
							Integer diffDays = CurrentTime.compareDate(endDate_fee, Convert.gradeNoToBuildeClassDate(12));//高二年级天数
							Integer remainDays = (selMonth * 30) - diffDays;//高二年级天数
							List<SysFeeInfo> sfList = sfm.listInfoByopt(feeType, schoolType, 1);
							if(sfList.size() > 0){
								List<SysFeeInfo> sfList_1 = sfm.listInfoByopt(feeType, 31, 1);//高三年级费用
								if(sfList_1.size() > 0){
									//可以购买--获取费用
									msg = "success";
									feeOpt = "diffFee";//存在不同费用
									Integer fee_base = sfList.get(0).getFee();//高一、二年级费用
									Integer fee_base_1 = sfList_1.get(0).getFee();//高三年级费用
									fee = fee_base * diffDays + fee_base_1 * remainDays;
									map.put("fee_1", fee_base);
									map.put("days_1", diffDays);
									map.put("gradeName_1", "高二");
									map.put("fee_2", fee_base_1);
									map.put("days_2", remainDays);
									map.put("gradeName_2", "高三");
								}
							}
						}else if(currUserGradeNumber_curr >= 12){//当前年级为高三
							List<SysFeeInfo> sfList = sfm.listInfoByopt(feeType, 31, 1);
							if(sfList.size() > 0){
								//可以购买--获取费用
								msg = "success";
								fee = sfList.get(0).getFee() * selMonth;
							}
						}else{
							//获取高一、二费用
							List<SysFeeInfo> sfList = sfm.listInfoByopt(feeType, 3, 1);
							if(sfList.size() > 0){
								//可以购买--获取费用
								msg = "success";
								fee = sfList.get(0).getFee() * selMonth;
							}
						}
					}
				}
			}
		}
		if(msg.equals("success")){
			map.put("feeOpt", feeOpt);//diffFee时在存在2种费用，才会存在fee_1,days_1,gradeName_1和2
			map.put("fee", fee);
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
}