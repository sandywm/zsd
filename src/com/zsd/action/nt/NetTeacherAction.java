/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.zsd.action.nt;

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
import com.zsd.service.NetTeacherBasicInfoManager;
import com.zsd.service.NetTeacherInfoManager;
import com.zsd.service.NtCertificateInfoManager;
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
		Integer ntId = (Integer) request.getSession().getAttribute("userId");// 网络导师编号
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
		String addData = Transcode.unescape("addData", request); //添加时间
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
		Integer uId=(Integer) request.getSession().getAttribute("userId");
		List<NetTeacherInfo> ntList = ntManager.listntInfoByuserId(uId);
		Integer ntId = ntList.get(0).getId();
		Integer id = CommonTools.getFinalInteger("ntcId", request);// 主键
		String icardName = Transcode.unescape_new("icardName", request);//身份证姓名
		String icardNum = Transcode.unescape_new("icardNum", request);//身份证号
		String icardImgFrontBig = Transcode.unescape_new("icardImgFrontBig", request); //身份证正面大
		String icardImgBackBig = Transcode.unescape("icardImgBackBig", request); //身份正背面大
		String icardImgFrontSmall = Transcode.unescape("icardImgFrontSmall", request); //身份证正面小
		String icardImgBackSmall = Transcode.unescape("icardImgBackSmall", request); //身份正背面小
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
		Integer uId=(Integer) request.getSession().getAttribute("userId");
		List<NetTeacherInfo> ntList = ntManager.listntInfoByuserId(uId);
		Integer ntId = ntList.get(0).getId();
		Integer id = CommonTools.getFinalInteger("ntcId", request);// 主键
		String xlzImgBig = Transcode.unescape("xlzImgBig", request); //学历证大
		String xlzImgSmall = Transcode.unescape("xlzImgSmall", request); //学历证小
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
		Integer uId=(Integer) request.getSession().getAttribute("userId");
		List<NetTeacherInfo> ntList = ntManager.listntInfoByuserId(uId);
		Integer ntId = ntList.get(0).getId();
		Integer id = CommonTools.getFinalInteger("ntcId", request);// 主键
		String zgzImgBig = Transcode.unescape("zgzImgBig", request); //学历证大
		String zgzImgSmall = Transcode.unescape("zgzImgSmall", request); //学历证小
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
	
}