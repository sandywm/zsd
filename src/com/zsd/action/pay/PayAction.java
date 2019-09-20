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
		Integer stuId = CommonTools.getLoginUserId(request);
		Integer roleId  = CommonTools.getLoginRoleId(request);
		Integer selMonth = CommonTools.getFinalInteger("selMonth", request);//默认进来为一个月
		Integer feeType = 2;//费用类型(1:导师费,2:会员费)
		Integer fee = 0;//购买所需费用
		String msg = "error";
		String feeOpt = "sameFee";
		Double zkRate = 0.0;
		Map<String,Object> map = new HashMap<String,Object>();
		if(selMonth.equals(0)){
			selMonth = 1;
		}
		stuId = CommonTools.getFinalInteger("userId", request);
		roleId = CommonTools.getFinalInteger("roleId", request);
		if(stuId > 0 && roleId.equals(Constants.STU_ROLE_ID) && selMonth > 0 && selMonth <= 12){
			//获取当前学生能购买的的时长（最大到升学日期）不足一月按一月计算
			List<UserClassInfo> uList = ucm.listInfoByOpt_1(stuId, roleId);
			if(uList.size() > 0){
				zkRate = CommonTools.getZkRate(selMonth);
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
				String gradeName_curr = Convert.NunberConvertChinese(currUserGradeNumber_curr);;
				//会员到期日+购买时长后的日期
				String endDate_new = CurrentTime.getFinalDate(endDate_fee, selMonth * 30);//购买会员后的新的到期日
				//获取购买会员后新的会员到期日后所在的年级
				Integer currUserGradeNumber_new = Convert.dateConvertGradeNumber(endDate_new,buildClassDate);//购买会员费后到期日所在的年级号
				String gradeName_new = Convert.NunberConvertChinese(currUserGradeNumber_new);//购买会员后到期日所在的年级名称
				map.put("gradeName_curr", gradeName_curr);//当前会员到期日所在的年级
				map.put("endDate_curr", endDate_fee);//会员到期日
				map.put("gradeName_new", gradeName_new);//购买会员后到期日所在的年级名称
				map.put("endDate_new", endDate_new);//购买会员后的新的到期日
				if(currUserGradeNumber_curr < 6){
					//获取小学费用
					List<SysFeeInfo> sfList = sfm.listInfoByopt(feeType, 1, 1);
					if(sfList.size() > 0){
						//可以购买--获取费用
						msg = "success";
						fee = (int)(sfList.get(0).getFee() * selMonth * zkRate);
					}
				}else if(currUserGradeNumber_curr.equals(6)){
					if(currUserGradeNumber_new.equals(6)){//从6年级买到6年级
						//获取小学费用
						List<SysFeeInfo> sfList = sfm.listInfoByopt(feeType, 1, 1);
						if(sfList.size() > 0){
							//可以购买--获取费用
							msg = "success";
							fee = (int)(sfList.get(0).getFee() * selMonth * zkRate);
						}
					}else{//从6年级买到7年级
						Integer diffDays = CurrentTime.compareDate(endDate_fee, (Integer.parseInt(endDate_fee.substring(0,4)) + 1)+"-09-01");//6年级天数
						Integer remainDays = (selMonth * 30) - diffDays;//7年级天数
						List<SysFeeInfo> sfList = sfm.listInfoByopt(feeType, 1, 1);//小学费用
						if(sfList.size() > 0){
							List<SysFeeInfo> sfList_1 = sfm.listInfoByopt(feeType, 2, 1);//7年级费用
							if(sfList_1.size() > 0){
								//可以购买--获取费用
								msg = "success";
								feeOpt = "diffFee";//存在不同费用
								Integer fee_base = sfList.get(0).getFee();//6年级费用
								Integer fee_base_1 = sfList_1.get(0).getFee();//7年级费用
								Double month_1 = Convert.convertInputNumber_2(diffDays / 30.0);
								Double month_2 = Convert.convertInputNumber_2(remainDays / 30.0);
								fee = (int)(fee_base * month_1 * zkRate + fee_base_1 * month_2 * zkRate);
								map.put("fee_1", fee_base);//第一阶段费用标准
								map.put("days_1", diffDays);//第一阶段购买时间
								map.put("gradeName_1", "六年级");//第一阶段所在年级
								map.put("fee_2", fee_base_1);
								map.put("days_2", remainDays);
								map.put("gradeName_2", "七年级");
							}
						}
					}
				}else if(currUserGradeNumber_curr.equals(7)){
					//获取初中费用
					List<SysFeeInfo> sfList = sfm.listInfoByopt(feeType, 2, 1);
					if(sfList.size() > 0){
						//可以购买--获取费用
						msg = "success";
						fee = (int)(sfList.get(0).getFee() * selMonth * zkRate);
					}
				}else if(currUserGradeNumber_curr.equals(8)){
					if(currUserGradeNumber_new.equals(8)){//从8年级买到8年级
						//获取初中费用
						List<SysFeeInfo> sfList = sfm.listInfoByopt(feeType, 2, 1);
						if(sfList.size() > 0){
							//可以购买--获取费用
							msg = "success";
							fee = (int)(sfList.get(0).getFee() * selMonth * zkRate);
						}
					}else{//从8年级买到9年级
						Integer diffDays = CurrentTime.compareDate(endDate_fee, (Integer.parseInt(endDate_fee.substring(0,4)) + 1)+"-09-01");//8年级天数
						Integer remainDays = (selMonth * 30) - diffDays;//9年级天数
						List<SysFeeInfo> sfList = sfm.listInfoByopt(feeType, 2, 1);//初中费用
						if(sfList.size() > 0){
							List<SysFeeInfo> sfList_1 = sfm.listInfoByopt(feeType, 21, 1);//9年级费用
							if(sfList_1.size() > 0){
								//可以购买--获取费用
								msg = "success";
								feeOpt = "diffFee";//存在不同费用
								Integer fee_base = sfList.get(0).getFee();//6年级费用
								Integer fee_base_1 = sfList_1.get(0).getFee();//7年级费用
								Double month_1 = Convert.convertInputNumber_2(diffDays / 30.0);
								Double month_2 = Convert.convertInputNumber_2(remainDays / 30.0);
								fee = (int)(fee_base * month_1 * zkRate + fee_base_1 * month_2 * zkRate);
								map.put("fee_1", fee_base);
								map.put("days_1", diffDays);
								map.put("gradeName_1", "八年级");
								map.put("fee_2", fee_base_1);
								map.put("days_2", remainDays);
								map.put("gradeName_2", "九年级");
							}
						}
					}
				}else if(currUserGradeNumber_curr.equals(9)){
					if(currUserGradeNumber_new.equals(9)){//从9年级买到9年级
						//获取9年级费用
						List<SysFeeInfo> sfList = sfm.listInfoByopt(feeType, 21, 1);
						if(sfList.size() > 0){
							//可以购买--获取费用
							msg = "success";
							fee = (int)(sfList.get(0).getFee() * selMonth * zkRate);
						}
					}else{//从9年级买到高一
						Integer diffDays = CurrentTime.compareDate(endDate_fee, (Integer.parseInt(endDate_fee.substring(0,4)) + 1)+"-09-01");//9年级天数
						Integer remainDays = (selMonth * 30) - diffDays;//高一天数
						List<SysFeeInfo> sfList = sfm.listInfoByopt(feeType, 21, 1);//9年级费用
						if(sfList.size() > 0){
							List<SysFeeInfo> sfList_1 = sfm.listInfoByopt(feeType, 3, 1);//高中费用
							if(sfList_1.size() > 0){
								//可以购买--获取费用
								msg = "success";
								feeOpt = "diffFee";//存在不同费用
								Integer fee_base = sfList.get(0).getFee();//6年级费用
								Integer fee_base_1 = sfList_1.get(0).getFee();//7年级费用
								Double month_1 = Convert.convertInputNumber_2(diffDays / 30.0);
								Double month_2 = Convert.convertInputNumber_2(remainDays / 30.0);
								fee = (int)(fee_base * month_1 * zkRate + fee_base_1 * month_2 * zkRate);
								map.put("fee_1", fee_base);
								map.put("days_1", diffDays);
								map.put("gradeName_1", "九年级");
								map.put("fee_2", fee_base_1);
								map.put("days_2", remainDays);
								map.put("gradeName_2", "高一");
							}
						}
					}
				}else if(currUserGradeNumber_curr.equals(10)){
					//获取高中费用
					List<SysFeeInfo> sfList = sfm.listInfoByopt(feeType, 3, 1);
					if(sfList.size() > 0){
						//可以购买--获取费用
						msg = "success";
						fee = (int)(sfList.get(0).getFee() * selMonth * zkRate);
					}
				}else if(currUserGradeNumber_curr.equals(11)){
					if(currUserGradeNumber_new.equals(11)){//从高二买到高二
						//获取高中费用
						List<SysFeeInfo> sfList = sfm.listInfoByopt(feeType, 3, 1);
						if(sfList.size() > 0){
							//可以购买--获取费用
							msg = "success";
							fee = (int)(sfList.get(0).getFee() * selMonth * zkRate);
						}
					}else{//从高二买到高三
						Integer diffDays = CurrentTime.compareDate(endDate_fee, (Integer.parseInt(endDate_fee.substring(0,4)) + 1)+"-09-01");//高二天数
						Integer remainDays = (selMonth * 30) - diffDays;//高三天数
						List<SysFeeInfo> sfList = sfm.listInfoByopt(feeType, 3, 1);//高一、二费用
						if(sfList.size() > 0){
							List<SysFeeInfo> sfList_1 = sfm.listInfoByopt(feeType, 31, 1);//高三费用
							if(sfList_1.size() > 0){
								//可以购买--获取费用
								msg = "success";
								feeOpt = "diffFee";//存在不同费用
								Integer fee_base = sfList.get(0).getFee();//6年级费用
								Integer fee_base_1 = sfList_1.get(0).getFee();//7年级费用
								Double month_1 = Convert.convertInputNumber_2(diffDays / 30.0);
								Double month_2 = Convert.convertInputNumber_2(remainDays / 30.0);
								fee = (int)(fee_base * month_1 * zkRate + fee_base_1 * month_2 * zkRate);
								map.put("fee_1", fee_base);
								map.put("days_1", diffDays);
								map.put("gradeName_1", "高二");
								map.put("fee_2", fee_base_1);
								map.put("days_2", remainDays);
								map.put("gradeName_2", "高三");
							}
						}
					}
				}else if(currUserGradeNumber_curr.equals(12)){
					//获取高中费用
					List<SysFeeInfo> sfList = sfm.listInfoByopt(feeType, 31, 1);
					if(sfList.size() > 0){
						//可以购买--获取费用
						msg = "success";
						fee = (int)(sfList.get(0).getFee() * selMonth * zkRate);
					}
				}
			}
		}
		if(msg.equals("success")){
			map.put("feeOpt", feeOpt);//diffFee时在存在2种费用，才会存在fee_1,days_1,gradeName_1和2
			map.put("fee", fee);//购买会员总费用
			map.put("zkRate", zkRate * 100 + "%");
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 获取购买网络导师服务费
	 * @author wm
	 * @date 2019-9-19 上午10:10:45
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getNtFee(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		SysFeeManager sfm = (SysFeeManager) AppFactory.instance(null).getApp(Constants.WEB_SYS_FEE_INFO);
		UserClassInfoManager ucm = (UserClassInfoManager) AppFactory.instance(null).getApp(Constants.WEB_USER_CLASS_INFO);
		Integer stuId = CommonTools.getLoginUserId(request);
		Integer roleId  = CommonTools.getLoginRoleId(request);
		Integer selMonth = CommonTools.getFinalInteger("selMonth", request);//默认进来为一个月
		Integer feeType = 1;//费用类型(1:导师费,2:会员费)
		Integer fee = 0;//购买所需费用
		String buyDate = "";//获取指定购买月份后的日期
		String currDate = CurrentTime.getStringDate();
		Integer currUserGradeNumber_curr = 0;//当前所在年级
		Integer currUserGradeNumber_new = 0;//选取购买月份后所在年级
		String gradeName_curr = "";
		String gradeName_new = "";
		String msg = "error";
		Double zkRate = 0.0;
		Map<String,Object> map = new HashMap<String,Object>();
		if(selMonth.equals(0)){
			selMonth = 1;
		}
		stuId = CommonTools.getFinalInteger("userId", request);
		roleId = CommonTools.getFinalInteger("roleId", request);
		if(stuId > 0 && roleId.equals(Constants.STU_ROLE_ID) && selMonth > 0 && selMonth <= 12){
			//获取当前学生能购买的的时长（最大到升学日期）不足一月按一月计算
			List<UserClassInfo> uList = ucm.listInfoByOpt_1(stuId, roleId);
			if(uList.size() > 0){
				zkRate = CommonTools.getZkRate(selMonth);
				UserClassInfo uc = uList.get(0);
				ClassInfo c = uc.getClassInfo();
				Integer schoolType = c.getSchool().getSchoolType();//当前学生所处的学段
				Integer yearSystem = c.getSchool().getYearSystem();//当前学生的学年制
				String buildClassDate = c.getBuildeClassDate();
				Integer buildClassYear = Integer.parseInt(buildClassDate.substring(0, 4));
				String byDate = "";//毕业时间
				currUserGradeNumber_curr = Convert.dateConvertGradeNumber(buildClassDate);//当前会员到期日所在的年级
				gradeName_curr = Convert.NunberConvertChinese(currUserGradeNumber_curr);
				buyDate = CurrentTime.getFinalDate(currDate, selMonth * 30);//购买导师服务费的日期
				//获取购买导师服务费的日期后所在的年级
				currUserGradeNumber_new = Convert.dateConvertGradeNumber(buyDate,buildClassDate);
				if(currUserGradeNumber_new > 12){
					currUserGradeNumber_new = 12;
				}
				gradeName_new = Convert.NunberConvertChinese(currUserGradeNumber_new);//购买会员后到期日所在的年级名称
				map.put("gradeName_curr", gradeName_curr);//当前用户所在的年级
				map.put("gradeName_new", gradeName_new);//购买导师服务费的日期后所在的年级名称
				map.put("buyDate_end", buyDate);//购买导师到期日
				Integer gradeNumber = 0;
				if(schoolType.equals(1)){//小学
					gradeNumber = yearSystem;
					if(yearSystem.equals(6)){//6+3+3年制
						//计算升学时间
						byDate = (buildClassYear + 8) + "-09-01";
					}else{//5+4+3年制
						byDate = (buildClassYear + 7) + "-09-01";
					}
				}else if(schoolType.equals(2)){//初中
					byDate = (buildClassYear + 11) + "-09-01";
					gradeNumber = 10;
				}else if(schoolType.equals(3)){//高中
					//不区分
					byDate = (buildClassYear + 14) + "-09-01";
					gradeNumber = 12;
				}
				if(currUserGradeNumber_new <= gradeNumber){
					//不涉及升学
					List<SysFeeInfo> sfList = sfm.listInfoByopt(feeType, 1, 1);
					if(sfList.size() > 0){
						//可以购买--获取费用
						msg = "success";
						fee = (int)(sfList.get(0).getFee() * selMonth * zkRate);
					}
				}else{
					//涉及升学，不能进行购买
					msg = "noBuy";
				}
			}
		}
		map.put("result", msg);
		if(msg.equals("success")){
			map.put("gradeName_curr", gradeName_curr);//当前所在年级
			map.put("gradeName_new", gradeName_new);//选取购买时长后所在的年级
			map.put("buyDate", buyDate);//绑定截止日期
			map.put("fee", fee);//费用
			map.put("zkRate", zkRate * 100 + "%");
		}
		CommonTools.getJsonPkg(map, response);
		return null;
	}
}