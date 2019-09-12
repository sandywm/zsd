/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.zsd.action.nt;

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
import com.zsd.module.NetTeacherBasicInfo;
import com.zsd.module.NetTeacherCertificateInfo;
import com.zsd.module.NetTeacherInfo;
import com.zsd.module.NetTeacherStudent;
import com.zsd.module.NetTeacherTxRecord;
import com.zsd.module.StudentPayOrderInfo;
import com.zsd.module.User;
import com.zsd.page.PageConst;
import com.zsd.service.NetTeacherBasicInfoManager;
import com.zsd.service.NetTeacherInfoManager;
import com.zsd.service.NetTeacherStudentManager;
import com.zsd.service.NetTeacherTxRecordManager;
import com.zsd.service.NtCertificateInfoManager;
import com.zsd.service.StudentPayOrderInfoManager;
import com.zsd.service.UserManager;
import com.zsd.tools.CommonTools;
import com.zsd.util.Constants;

/**
 * MyEclipse Struts Creation date: 05-12-2019
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class NetTeacherAction extends DispatchAction {
	/**
	 * 网络导师的基本信息修改
	 * 
	 * @author zong 2019-5-12上午11:13:36
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateNtInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NetTeacherInfoManager ntInfoManager = (NetTeacherInfoManager) AppFactory
				.instance(null).getApp(Constants.WEB_NET_TEACHER_INFO);
		Integer ntId = CommonTools.getFinalInteger("ntId", request);// 网络导师编号
		String realName = Transcode.unescape_new("realName", request);
		String nickName = Transcode.unescape_new("nickName", request);
		String teaSign = Transcode.unescape_new("teaSign", request);
		String teaEdu = Transcode.unescape_new("teaEdu", request);
		String graduateSchool = Transcode.unescape_new("graduateSchool",
				request);
		String major = Transcode.unescape_new("major", request);
		Integer schoolAge = CommonTools.getFinalInteger("schoolAge", request);
		String sex = CommonTools.getFinalStr("sex", request);
		String birthday = CommonTools.getFinalStr("birthday", request);
		boolean ntFlag = ntInfoManager.updateNtBybasicInfo(ntId, realName,
				nickName, teaSign, teaEdu, graduateSchool, major, schoolAge,
				sex, birthday);
		Map<String, String> map = new HashMap<String, String>();
		if (ntFlag) {
			map.put("result", "success");
		} else {
			map.put("result", "fail");
		}
		CommonTools.getJsonPkg(map, response);
		return null;
	}

	/**
	 * 添加网络老师背景资料信息
	 * 
	 * @author zong 2019-5-13上午09:01:55
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addNtbInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NetTeacherBasicInfoManager ntbManager = (NetTeacherBasicInfoManager) AppFactory
				.instance(null).getApp(Constants.WEB_NET_TEACHER_BASIC_INFO);
		Integer ntId = CommonTools.getFinalInteger("ntId", request);// 网络导师主键
		String title = Transcode.unescape_new("title", request);//标题
		String dataRange = Transcode.unescape_new("dataRan", request);//时间范围
		String description = Transcode.unescape_new("desc", request); //描述
		Integer type = CommonTools.getFinalInteger("type", request); //类型
		String addData = Transcode.unescape_new1("addData", request); //添加时间
		Integer ntbId = ntbManager.addNtbInfo(ntId, title, dataRange, description, type, addData);
		Map<String, String> map = new HashMap<String, String>();
		if(ntbId>0){
			map.put("result", "success");
		}else{
			map.put("result", "fail");
		}
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	/**
	 * 保存网络导师的身份信息(姓名,身份证号码,正反面)
	 * @author zong
	 * 2019-5-13下午05:18:30
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveICard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NtCertificateInfoManager ntcManager = (NtCertificateInfoManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_CERTIFICATE_INFO);
		NetTeacherInfoManager ntManager = (NetTeacherInfoManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_INFO);
		Map<String, String> map = new HashMap<String, String>();
		Integer userId = CommonTools.getLoginUserId(request);
		List<NetTeacherInfo> ntList = ntManager.listntInfoByuserId(userId);
		Integer ntId = ntList.get(0).getId();
		Integer id = CommonTools.getFinalInteger("ntcId", request);// 主键
		String icardName = Transcode.unescape_new("icardName", request);//身份证姓名
		String icardNum = Transcode.unescape_new("icardNum", request);//身份证号
		String icardImgFrontBig = Transcode.unescape_new("icardImgFrontBig", request); //身份证正面大
		String icardImgBackBig = Transcode.unescape_new("icardImgBackBig", request); //身份正背面大
		String icardImgFrontSmall = Transcode.unescape_new("icardImgFrontSmall", request); //身份证正面小
		String icardImgBackSmall = Transcode.unescape_new("icardImgBackSmall", request); //身份正背面小
		if(id>0){
			boolean ntcFlag = ntcManager.updateNtcInfo(id, icardImgFrontBig, icardImgBackBig, icardImgFrontSmall, icardImgBackSmall, icardName, icardNum, "", "", "", "");
			if(ntcFlag){
				map.put("result", "success");
			}else{
				map.put("result", "fail");
			}
		}else{
			Integer ntcId = ntcManager.addNtcInfo(ntId, icardImgFrontBig, icardImgBackBig, icardImgFrontSmall, icardImgBackSmall, icardName, icardNum, "", "", "", "", 0, "", 0, "", "", "", "");
			if(ntcId>0){
				map.put("result", "success");
			}else{
				map.put("result", "fail");
			}
		}
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	/**
	 * 保存学历证
	 * @author zong
	 * 2019-5-14上午11:47:52
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveEduCert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		NtCertificateInfoManager ntcManager = (NtCertificateInfoManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_CERTIFICATE_INFO);
		NetTeacherInfoManager ntManager = (NetTeacherInfoManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_INFO);
		Map<String, String> map = new HashMap<String, String>();
		Integer userId = CommonTools.getLoginUserId(request);
		List<NetTeacherInfo> ntList = ntManager.listntInfoByuserId(userId);
		Integer ntId = ntList.get(0).getId();
		Integer id = CommonTools.getFinalInteger("ntcId", request);// 主键
		String xlzImgBig = Transcode.unescape_new("xlzImgBig", request); //学历证大
		String xlzImgSmall = Transcode.unescape_new("xlzImgSmall", request); //学历证小
		if(id>0){
			boolean   ntcFlag = ntcManager.updateNtcInfo(id, "", "", "", "", "", "", "", "", xlzImgBig, xlzImgSmall);
			if(ntcFlag){
				map.put("result", "success");
			}else{
				map.put("result", "fail");
			}
		}else{
			Integer  ntcId = ntcManager.addNtcInfo(ntId, "", "", "", "", "", "", "", "", xlzImgBig, xlzImgSmall,0, "", 0, "", "", "", "");
			if(ntcId>0){
				map.put("result", "success");
			}else{
				map.put("result", "fail");
			}	
		}
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	/**
	 * 保存教师资格证
	 * @author zong
	 * 2019-5-14上午11:48:13
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveNtCert(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		NtCertificateInfoManager ntcManager = (NtCertificateInfoManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_CERTIFICATE_INFO);
		NetTeacherInfoManager ntManager = (NetTeacherInfoManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_INFO);
		Map<String, String> map = new HashMap<String, String>();
		Integer userId = CommonTools.getLoginUserId(request);
		List<NetTeacherInfo> ntList = ntManager.listntInfoByuserId(userId);
		Integer ntId = ntList.get(0).getId();
		Integer id = CommonTools.getFinalInteger("ntcId", request);// 主键
		String zgzImgBig = Transcode.unescape_new("zgzImgBig", request); //学历证大
		String zgzImgSmall = Transcode.unescape_new("zgzImgSmall", request); //学历证小
		if(id>0){
			boolean   ntcFlag = ntcManager.updateNtcInfo(id, "", "", "", "", "", "", zgzImgBig, zgzImgSmall, "", "");
			if(ntcFlag){
				map.put("result", "success");
			}else{
				map.put("result", "fail");
			}
		}else{
			Integer  ntcId = ntcManager.addNtcInfo(ntId, "", "", "", "", "", "",zgzImgBig, zgzImgSmall, "", "",0, "", 0, "", "", "", "");
			if(ntcId>0){
				map.put("result", "success");
			}else{
				map.put("result", "fail");
			}	
		}
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	/**
	 * 我的银行卡(网络导师)
	 * @author zong
	 * 2019-5-26上午10:18:46
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward myBankCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		NetTeacherInfoManager ntManager = (NetTeacherInfoManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_INFO);
		Integer userId=CommonTools.getLoginUserId(request);
		Map<String, Object> map = new HashMap<String, Object>();
		List<NetTeacherInfo> ntlist = ntManager.listntInfoByuserId(userId);
		String bName =ntlist.get(0).getBankName();
		String bNum = ntlist.get(0).getBankNumber();
		map.put("bankName", bName);//银行名称
		map.put("bankNum", bNum);//银行账号
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	/**
	 * 我的账户信息
	 * @author zdf
	 * 2019-9-11 下午05:27:33
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward myAccount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		UserManager uManager = (UserManager) AppFactory.instance(null).getApp(Constants.WEB_USER_INFO);
		NetTeacherTxRecordManager ntxManager = (NetTeacherTxRecordManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_TX_RECORD);
		NetTeacherInfoManager ntManager = (NetTeacherInfoManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_INFO);
		Integer userId=CommonTools.getLoginUserId(request);
		Map<String, Object> map = new HashMap<String, Object>();
		List<User> ulist = uManager.listEntityById(userId);
		List<NetTeacherInfo> ntlist = ntManager.listntInfoByuserId(userId);
		Integer ntId = ntlist.get(0).getId();
		List<NetTeacherTxRecord> ntxlist =ntxManager.findnTxReCordByNtId(ntId,0);//等待到账
		List<NetTeacherTxRecord> ntxs =ntxManager.findnTxReCordByNtId(ntId,1);//已到账
		Integer ytxCash=0;
		Integer txCash =0;
		for (Iterator<NetTeacherTxRecord> it = ntxlist.iterator(); it.hasNext();) {
			NetTeacherTxRecord ntx = (NetTeacherTxRecord) it.next();
			 ytxCash+=ntx.getTxMoney();
		}
		for (Iterator<NetTeacherTxRecord> itr = ntxs.iterator(); itr.hasNext();) {
			NetTeacherTxRecord ntxRe = (NetTeacherTxRecord) itr.next();
			txCash+=ntxRe.getTxMoney();
		}
		map.put("ytxCash", ytxCash);//等待到账金额
		map.put("txCash", txCash);//已到账金额
		map.put("accMoney", ulist.get(0).getAccountMoney());//账户余额(可提现)
		CommonTools.getJsonPkg(map, response);
		return null;
		
	}
	/**
	 * 根据网络导师主键修改网络导师银行卡信息(添加更新银行卡)
	 * @author zong
	 * 2019-5-27上午10:34:03
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addOrUpdateBankCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		NetTeacherInfoManager ntManager = (NetTeacherInfoManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_INFO);
		Integer userId=CommonTools.getLoginUserId(request);
		String bName = Transcode.unescape_new("bankName", request);
		String bNum = CommonTools.getFinalStr("bankNum",request);
		boolean ntFlag=ntManager.updateNtByBankCard(userId, bName, bNum);
		Map<String, String> map = new HashMap<String, String>();
		if(ntFlag){
			map.put("result", "success");
		}else{
			map.put("result", "fail");
		}
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	/**
	 * 获取网络导师用户编号的提现记录信息(账单信息)
	 * @author zong
	 * 2019-5-27下午03:40:02
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listnTxReCord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		NetTeacherTxRecordManager ntxManager = (NetTeacherTxRecordManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_TX_RECORD);
		Integer userId=CommonTools.getLoginUserId(request);
		Integer count = ntxManager.getnTxReCordCount(userId);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg ="暂无记录";
		if(count>0){
			Integer pageSize = PageConst.getPageSize(String.valueOf(request.getParameter("limit")), 10);//等同于pageSize
			Integer pageNo = CommonTools.getFinalInteger("page", request);//等同于pageNo
			List<NetTeacherTxRecord> ntxlist= ntxManager.listnTxReCordByNtId(userId, pageNo, pageSize);
			List<Object> list_d = new ArrayList<Object>();
			for (Iterator<NetTeacherTxRecord> itr = ntxlist.iterator(); itr.hasNext();) {
				NetTeacherTxRecord ntx = (NetTeacherTxRecord) itr.next();
				Map<String,Object> map_d = new HashMap<String,Object>();
				map_d.put("id", ntx.getId());
				if(ntx.getOperateUserId().equals(-1)){
					map_d.put("ntxName", "收入");
				}else{
					map_d.put("ntxName", "支出");
				}
				map_d.put("txMoney", ntx.getTxMoney());
				map_d.put("txDate", ntx.getTxDate());
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
	 * 账单信息详情
	 * @author zdf
	 * 2019-9-11 下午03:56:54
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward nTxRecordDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		NetTeacherTxRecordManager ntxManager = (NetTeacherTxRecordManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_TX_RECORD);
		UserManager uManager = (UserManager) AppFactory.instance(null).getApp(Constants.WEB_USER_INFO);
		Integer Id = CommonTools.getFinalInteger("ntxId", request);
		List<NetTeacherTxRecord> ntxList = ntxManager.listnTxReCordById(Id);
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> list_d = new ArrayList<Object>();
		for (Iterator<NetTeacherTxRecord> iterator = ntxList.iterator(); iterator.hasNext();) {
			NetTeacherTxRecord ntx = (NetTeacherTxRecord) iterator.next();
			Map<String, Object> map_d = new HashMap<String, Object>();
			if(ntx.getOperateUserId().equals(-1)){
				 List<User> ulist = uManager.listEntityById(ntx.getStuId());
				 String reMark = "学生"+ulist.get(0).getRealName()+"绑定费返现";
				 map_d.put("ntxTitle", "收入");	
				 map_d.put("txMoney", ntx.getTxMoney());
				 map_d.put("txDate", ntx.getTxDate());
				 map_d.put("reMark", reMark);
			}else if(ntx.getOperateUserId()>=0){
				 map_d.put("ntxTitle", "支出");	
				 map_d.put("txMoney", ntx.getTxMoney());
				 map_d.put("txDate", ntx.getTxDate());
				 map_d.put("bankName", ntx.getBankName());
				 map_d.put("bankNo", ntx.getBankNo());
				 map_d.put("operDate", ntx.getOperateDate());
				 String operUser="";
				 if(ntx.getOperateUserId()>0){
					 List<User> ulist = uManager.listEntityById(ntx.getOperateUserId());
					 operUser =ulist.get(0).getRealName() ;
					 map_d.put("operUser", operUser); 
				 }else{
					 map_d.put("operUser", operUser);  
				 }
				
			}
			list_d.add(map_d);
		}
		map.put("data", list_d);
		CommonTools.getJsonPkg(map, response);
		return null;
		
	}
	
	/**
	 * 获取学生的购买订单信息
	 * @author zong
	 * 2019-5-27下午04:53:21
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listStuPayOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		NetTeacherInfoManager ntManager = (NetTeacherInfoManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_INFO);
		StudentPayOrderInfoManager sOrdeManager = (StudentPayOrderInfoManager) AppFactory.instance(null).getApp(Constants.WEB_STUDENT_PAY_ORDER_INFO);
		NetTeacherStudentManager ntsManager = (NetTeacherStudentManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_STUDENT);
		Integer userId=CommonTools.getLoginUserId(request);
		Map<String, Object> map = new HashMap<String, Object>();
		List<NetTeacherInfo> ntlist = ntManager.listntInfoByuserId(userId);
		Integer ntId = ntlist.get(0).getId();
		List<NetTeacherStudent> ntslist=ntsManager.listNTByntId(ntId, 1);
		List<Object> list_d = new ArrayList<Object>();
		String  msg = "暂无记录";
		Integer count =0;
		if(!ntslist.isEmpty()){
			for (Iterator<NetTeacherStudent> iter = ntslist.iterator(); iter.hasNext();) {
				NetTeacherStudent nts = (NetTeacherStudent) iter.next();
				 Integer ntsId = nts.getId();
				 count = sOrdeManager.getspOrderInfoCount(ntsId);
				 if(count>0){
					 msg="success";
					 Integer pageSize = PageConst.getPageSize(String.valueOf(request.getParameter("limit")), 10);//等同于pageSize
					 Integer pageNo = CommonTools.getFinalInteger("page", request);//等同于pageNo
					 List<StudentPayOrderInfo> sordeList = sOrdeManager.listSpayOrderInfoByOpt(ntsId, pageNo, pageSize);
						for (Iterator<StudentPayOrderInfo> itrs = sordeList.iterator(); itrs.hasNext();) {
							StudentPayOrderInfo sorder = (StudentPayOrderInfo) itrs.next();
							Map<String,Object> map_d = new HashMap<String,Object>();
							map_d.put("stuName", sorder.getUser().getRealName());
							map_d.put("payDate", sorder.getAddDate());
							map_d.put("payMoney", sorder.getPayMoney());
							map_d.put("endDate", sorder.getEndDate());
							list_d.add(map_d);
						}
				 }
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
	 * 获取班级成员列表(我的班级)
	 * @author zong
	 * 2019-5-31下午03:59:27
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward listUserClass(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		NetTeacherStudentManager ntsManager = (NetTeacherStudentManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_STUDENT);
		NetTeacherInfoManager ntManager = (NetTeacherInfoManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_INFO);
		Integer userId=CommonTools.getLoginUserId(request);
		List<NetTeacherInfo> ntlist = ntManager.listntInfoByuserId(userId);
		Integer ntId = ntlist.get(0).getId();
		Integer paySta=CommonTools.getFinalInteger("paySta", request);
		Integer bindFlag= CommonTools.getFinalInteger("bindFlag", request);
		String stuName=Transcode.unescape_new("stuName", request);
		Integer count = ntsManager.getNtsBystunameOrBindSta(ntId, paySta, bindFlag, stuName);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg ="暂无记录";
		if(count>0){
			Integer pageSize = PageConst.getPageSize(String.valueOf(request.getParameter("limit")), 10);//等同于pageSize
			Integer pageNo = CommonTools.getFinalInteger("page", request);//等同于pageNo
			List<NetTeacherStudent> ntslist = ntsManager.listNTByStuNameOrBindSta(ntId, paySta, bindFlag, stuName, pageNo, pageSize);
			List<Object> list_d = new ArrayList<Object>();
			for (Iterator<NetTeacherStudent> itr = ntslist.iterator(); itr.hasNext();) {
				NetTeacherStudent nts = (NetTeacherStudent) itr.next();
				Map<String,Object> map_d = new HashMap<String,Object>();
				map_d.put("id", nts.getId());
				map_d.put("stuNmae", nts.getUser().getRealName());
				map_d.put("sectDate", nts.getBindDate()+"至"+nts.getEndDate());
				Integer paysta = nts.getPayStatus();
				if(paysta.equals(-1)){
					map_d.put("payVal", "免费试用");
				}else if(paysta.equals(1)){
					map_d.put("payVal", "付费");
				}else if(paysta.equals(0)){
					map_d.put("payVal", "取消");
				}else{
					map_d.put("payVal", "免费");
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
	 * 获取班级基本信息(我的班级)
	 * @author zong
	 * 2019-5-31下午05:03:05
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getClassInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		NetTeacherStudentManager ntsManager = (NetTeacherStudentManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_STUDENT);
		NetTeacherInfoManager ntManager = (NetTeacherInfoManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_INFO);
		Integer userId=CommonTools.getLoginUserId(request);
		Map<String,Object> map = new HashMap<String,Object>();
		List<NetTeacherInfo> ntlist = ntManager.listntInfoByuserId(userId);
		NetTeacherInfo ntInfo = ntlist.get(0);
		Integer ntId = ntInfo.getId();
		Integer trialNum = ntsManager.getByStuNum(ntId, -1);//免费试用人员
		Integer freeNum = ntsManager.getByStuNum(ntId, 2);//免费人员
		Integer payNum = ntsManager.getByStuNum(ntId, 1);//付费人员
		String schType ="";
		if(ntInfo.getSchoolType()==1){
			schType="小学";
		}else if(ntInfo.getSchoolType()==2){
			schType="初中";
		}else if(ntInfo.getSchoolType()==3){
			schType="高中";
		}
		map.put("subName", schType+ntInfo.getSubject().getSubName());
		map.put("bmoney", ntInfo.getBaseMoney());
		map.put("trialNum", trialNum);
		map.put("freeNum", freeNum);
		map.put("payNum", payNum);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	/**
	 * 网络导师个人中心
	 * @author zong
	 * 2019-6-5下午03:39:21
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getNtperCenter(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception {
		NetTeacherInfoManager ntManager = (NetTeacherInfoManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_INFO);
		NetTeacherBasicInfoManager ntbManager = (NetTeacherBasicInfoManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_BASIC_INFO);
		NtCertificateInfoManager ntcManager = (NtCertificateInfoManager) AppFactory.instance(null).getApp(Constants.WEB_NET_TEACHER_CERTIFICATE_INFO);
		Integer userId=CommonTools.getLoginUserId(request);
		Map<String,Object> map = new HashMap<String,Object>();
		List<NetTeacherInfo> ntlist = ntManager.listntInfoByuserId(userId);
		NetTeacherInfo ntInfo = ntlist.get(0);
		Integer ntId = ntInfo.getId();
		map.put("ntId", ntId);
		map.put("realName", ntInfo.getUser().getRealName());
		map.put("nickName", ntInfo.getUser().getNickName());
		map.put("teaSign", ntInfo.getTeaSign());
		map.put("teaEdu", ntInfo.getTeaEdu());
		map.put("graduateSchool", ntInfo.getGraduateSchool());
		map.put("major", ntInfo.getMajor());
		map.put("schoolAge", ntInfo.getSchoolAge());
		map.put("sex", ntInfo.getUser().getSex());
		map.put("birtthday", ntInfo.getUser().getBirthday());
		map.put("lastLoginDate", ntInfo.getUser().getLastLoginDate());
		map.put("lastLoginIp", ntInfo.getUser().getLastLoginIp());
		map.put("email", ntInfo.getUser().getEmail());
		map.put("mobile", ntInfo.getUser().getMobile());
		
		List<Object> list_ntb = new ArrayList<Object>();
		List<NetTeacherBasicInfo> ntblist = ntbManager.listNtbByTeaId(ntId);
		for (Iterator<NetTeacherBasicInfo> itr = ntblist.iterator(); itr.hasNext();) {
			NetTeacherBasicInfo ntbInfo = (NetTeacherBasicInfo) itr.next();
			Map<String,Object> map_ntb = new HashMap<String,Object>();
			map_ntb.put("ntbId", ntbInfo.getId());
			map_ntb.put("title", ntbInfo.getTitle());
			map_ntb.put("dataRange", ntbInfo.getDataRange());
			map_ntb.put("description", ntbInfo.getDescription());
			map_ntb.put("type", ntbInfo.getType());
			map_ntb.put("addDate", ntbInfo.getAddData());
			
			list_ntb.add(map_ntb);
		}
		map.put("list_ntb", list_ntb);
		
		List<Object> list_ntc = new ArrayList<Object>();
		List<NetTeacherCertificateInfo> ntclist = ntcManager.getNtcByTeaId(ntId);
		for (Iterator<NetTeacherCertificateInfo> ittr = ntclist.iterator(); ittr.hasNext();) {
			NetTeacherCertificateInfo ntcInfo = (NetTeacherCertificateInfo) ittr.next();
			Map<String,Object> map_ntc = new HashMap<String,Object>();
			map_ntc.put("ntcId", ntcInfo.getId());
			map_ntc.put("icName", ntcInfo.getIcardName());
			map_ntc.put("icNum", ntcInfo.getIcardNum());
			map_ntc.put("icImgFrontBig", ntcInfo.getIcardImgFrontBig());
			map_ntc.put("icImgFrontSmall", ntcInfo.getIcardImgFrontSmall());
			map_ntc.put("icImgBackBig", ntcInfo.getIcardImgBackBig());
			map_ntc.put("icImgBackSmall", ntcInfo.getIcardImgBackSmall());
			map_ntc.put("zgzImgBig", ntcInfo.getZgzImgBig());
			map_ntc.put("zgzImgSmall", ntcInfo.getZgzImgSmall());
			map_ntc.put("xlzImgBig", ntcInfo.getXlzImgBig());
			map_ntc.put("xlzImgSmall", ntcInfo.getXlzImgSmall());
			
			list_ntc.add(map_ntc);
		}
		map.put("list_ntc", list_ntc);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
}