/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.zsd.action.school;

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
import com.zsd.exception.WEBException;
import com.zsd.factory.AppFactory;
import com.zsd.module.RoleInfo;
import com.zsd.module.RoleUserInfo;
import com.zsd.module.School;
import com.zsd.page.PageConst;
import com.zsd.service.RoleInfoManager;
import com.zsd.service.RoleUserInfoManager;
import com.zsd.service.SchoolManager;
import com.zsd.service.UserManager;
import com.zsd.tools.CommonTools;
import com.zsd.tools.Convert;
import com.zsd.tools.CurrentTime;
import com.zsd.tools.MD5;
import com.zsd.util.Constants;

/** 
 * MyEclipse Struts
 * Creation date: 04-29-2019
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class SchoolAction extends DispatchAction {
	
	/**
	 * 导向学校管理模块
	 * @author  Administrator
	 * @ModifiedBy  
	 * @date  2019-5-1 下午04:31:05
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward goSchoolPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		return mapping.findForward("schoolPage");
	}
	
	/**
	 * 根据条件分页获取学校列表
	 * @author Administrator
	 * @date 2019-4-29 下午03:56:37
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getPageSchoolData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		SchoolManager sm = (SchoolManager) AppFactory.instance(null).getApp(Constants.WEB_SCHOOL_INFO);
		String schoolName = Transcode.unescape_new("schoolName", request);
		String prov = Transcode.unescape_new("prov", request);
		String city = Transcode.unescape_new("city", request);
		String county = Transcode.unescape_new("county", request);
		String town = Transcode.unescape_new("town", request);
		Integer schoolType = CommonTools.getFinalInteger("schoolType", request);
		Integer count = sm.getCountByOpt(schoolName, prov, city, county, town, schoolType, -1);
		String msg = "暂无记录";
		Map<String,Object> map = new HashMap<String,Object>();
		if(count > 0){
			Integer pageSize = PageConst.getPageSize(String.valueOf(request.getParameter("limit")), 10);//等同于pageSize
			Integer pageNo = CommonTools.getFinalInteger("page", request);//等同于pageNo
			List<School> sList = sm.listPageInfoByOpt(schoolName, prov, city, county, town, schoolType, -1, pageNo, pageSize);
			List<Object> list_d = new ArrayList<Object>();
			for(Iterator<School> it = sList.iterator() ; it.hasNext();){
				School sch = it.next();
				Map<String,Object> map_d = new HashMap<String,Object>();
				map_d.put("id", sch.getId());
				map_d.put("schoolName", sch.getSchoolName());
				map_d.put("prov", sch.getProv());
				map_d.put("city", sch.getCity());
				map_d.put("county", sch.getCounty());
				map_d.put("town", sch.getTown());
				Integer schoolType_db = sch.getSchoolType();
				String schoolTypeChi = "";
				if(schoolType_db.equals(1)){
					schoolTypeChi = "小学";
				}else if(schoolType_db.equals(2)){
					schoolTypeChi = "初中";
				}else if(schoolType_db.equals(3)){
					schoolTypeChi = "高中";
				}
				map_d.put("schoolType", schoolTypeChi);
				map_d.put("yearSystem", sch.getYearSystem()+"年制");
				if(sch.getShowStatus().equals(0)){
					map_d.put("showStatus", "显示");
				}else{
					map_d.put("showStatus", "隐藏");
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
	 * 检查学校名称是否存在
	 * @author Administrator
	 * @date 2019-4-29 下午04:02:54
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkExistSchoolName(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		SchoolManager sm = (SchoolManager) AppFactory.instance(null).getApp(Constants.WEB_SCHOOL_INFO);
		String schoolName = Transcode.unescape_new("schoolName", request);
		String msg = "error";
		Map<String,String> map = new HashMap<String,String>();
		if(!schoolName.equals("")){
			List<School> sList = sm.listInfoBySName(schoolName);
			if(sList.size() > 0){
				msg = "exist";
			}else{
				msg = "noInfo";
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	
	/**
	 * 根据学校编号获取学校详情
	 * @author Administrator
	 * @date 2019-4-29 下午03:57:33
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getSchoolDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		SchoolManager sm = (SchoolManager) AppFactory.instance(null).getApp(Constants.WEB_SCHOOL_INFO);
		Integer schoolId = CommonTools.getFinalInteger("schoolId", request);
		List<School> sList = sm.listInfoById(schoolId);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "noInfo";
		if(sList.size() > 0){
			msg = "success";
			School sch = sList.get(0);
			map.put("id", sch.getId());
			map.put("schoolName", sch.getSchoolName());
			map.put("prov", sch.getProv());
			map.put("city", sch.getCity());
			map.put("county", sch.getCounty());
			map.put("town", sch.getTown());
			map.put("schoolType", sch.getSchoolType());
			map.put("yearSystem", sch.getYearSystem());
			map.put("showStatus", sch.getShowStatus());
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 修改指定学校详情
	 * @author Administrator
	 * @date 2019-4-29 下午04:00:37
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateSchool(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		SchoolManager sm = (SchoolManager) AppFactory.instance(null).getApp(Constants.WEB_SCHOOL_INFO);
		Integer schoolId = CommonTools.getFinalInteger("schoolId", request);
		String schoolName = Transcode.unescape_new("schoolName", request);
		String prov = Transcode.unescape_new("prov", request);
		String city = Transcode.unescape_new("city", request);
		String county = Transcode.unescape_new("county", request);
		String town = Transcode.unescape_new("town", request);
		Integer schoolType = CommonTools.getFinalInteger("schoolType", request);
		Integer yearSystem = CommonTools.getFinalInteger("yearSystem", request);
		Integer showStatus = CommonTools.getFinalInteger("showStatus", request);
		List<School> sList = sm.listInfoById(schoolId);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "fail";
		if(sList.size() > 0){
			String schoolName_db = sList.get(0).getSchoolName();
			if(!schoolName_db.equals(schoolName)){
				//不相同，需要检查学校名是否存在
				List<School> sList_1 = sm.listInfoBySName(schoolName);
				if(sList_1.size() > 0){
					//存在，不能修改
					msg = "exist";
				}else{
					sm.updateSchoolInfoById(schoolId, schoolName, prov, city, county, town, schoolType, yearSystem, showStatus);
					msg = "success";
				}
			}else{
				sm.updateSchoolInfoById(schoolId, schoolName, prov, city, county, town, schoolType, yearSystem, showStatus);
				msg = "success";
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	/**
	 * 增加学校信息
	 * @author zong
	 * 2019-5-7上午09:53:46
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addSchool(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		SchoolManager sManager = (SchoolManager) AppFactory.instance(null).getApp(Constants.WEB_SCHOOL_INFO);
		RoleUserInfoManager ruManager = (RoleUserInfoManager) AppFactory.instance(null).getApp(Constants.WEB_ROLE_USER_INFO);
		UserManager uManager = (UserManager) AppFactory.instance(null).getApp(Constants.WEB_USER_INFO);
		RoleInfoManager rManager = (RoleInfoManager) AppFactory.instance(null).getApp(Constants.WEB_ROLE_INFO);
		String schoolName = Transcode.unescape_new("schoolName", request);
		String prov = Transcode.unescape_new("prov", request);
		String city = Transcode.unescape_new("city", request);
		String county = Transcode.unescape_new("county", request);
		String town = Transcode.unescape_new("town", request);
		Integer schoolType = CommonTools.getFinalInteger("schoolType", request);//学段(小学 初中 高中）
		Integer yearSystem = CommonTools.getFinalInteger("yearSystem", request); //学年制
		String lastLoginIp = CommonTools.getIpAddress(request); //最后登录Ip
		String currTime = CurrentTime.getCurrentTime();
		Integer scId = sManager.addSchool(schoolName, prov, city, county, town, schoolType, yearSystem);
		String msg = "fail";
		if(scId>0){ //添加学校成功
			Integer mRId =0;
			List<RoleInfo> mRList = rManager.listRoleInfo("管理员");
			if(mRList.size()>0){
			    mRId = mRList.get(0).getId();
			}
			if(prov!=""){
				List<RoleUserInfo> provRu = ruManager.listUserRoleInfoByPosition(prov, "", "", 0, 0, 0, 0);
				//创建省管理员
				if(provRu.isEmpty()){
					//生成省管理员账户
					Integer provMid=uManager.addUser("prov"+Convert.getFirstSpell(prov), "", new MD5().calcMD5("123456"), "",currTime, lastLoginIp, currTime, scId, "", yearSystem, prov, city);
					//绑定省管理员角色
					ruManager.addRoleUserInfo(provMid, mRId, prov, "", "", "", 0, 0, 0, 0);
				}
			}
			if(prov!=""&&city !=""){
				List<RoleUserInfo> cityRu = ruManager.listUserRoleInfoByPosition(prov, city, "", 0, 0, 0, 0);
				//创建市管理员
				if(cityRu.isEmpty()){
					//生成市管理员账户
					Integer cityMid=uManager.addUser("city"+Convert.getFirstSpell(city), "", new MD5().calcMD5("123456"), "",currTime, lastLoginIp, currTime, scId, "", yearSystem, prov, city);
					//绑定市管理员角色
					ruManager.addRoleUserInfo(cityMid, mRId, prov, city, "", "", 0, 0, 0, 0);
				}
			}
			if(prov!=""&&city !=""&&county!=""){
				List<RoleUserInfo> cnyRu = ruManager.listUserRoleInfoByPosition(prov, city, county, 0, 0, 0, 0);
				//创建县管理员
				if(cnyRu.isEmpty()){
					//生成县管理员账户
					Integer cnyMid=uManager.addUser("cny"+Convert.getFirstSpell(county), "", new MD5().calcMD5("123456"), "",currTime, lastLoginIp, currTime, scId, "", yearSystem, prov, city);
					//绑定县管理员角色
					ruManager.addRoleUserInfo(cnyMid, mRId, prov, city, county, "", 0, 0, 0, 0);
				}
			}
			if(prov!=""&&city !=""&&county!=""&&schoolType!=0){
				List<RoleUserInfo> sctRu = ruManager.listUserRoleInfoByPosition(prov, city, county, schoolType, 0, 0, 0);
				//创建学段管理员
				if(sctRu.isEmpty()){
					//生成学段管理员账户
					Integer sctMid=uManager.addUser(Convert.getFirstSpell(city)+Convert.getFirstSpell(county)+"0"+schoolType, "", new MD5().calcMD5("123456"), "",currTime, lastLoginIp, currTime, scId, "", yearSystem, prov, city);
					//绑定学段管理员角色
					ruManager.addRoleUserInfo(sctMid, mRId, prov, city, county, "", schoolType, 0, 0, 0);
				}
			}
			if(prov!=""&&city !=""&&county!=""&&schoolType!=0 && scId !=0){
				List<RoleUserInfo> schRu = ruManager.listUserRoleInfoByPosition(prov, city, county, schoolType, scId, 0, 0);
				//创建学校管理员
				if(schRu.isEmpty()){
					//生成学校管理员账户
					Integer schMid=uManager.addUser("sch"+scId, "", new MD5().calcMD5("123456"), "",currTime, lastLoginIp, currTime, scId, "", yearSystem, prov, city);
					//绑定学校管理员角色
					ruManager.addRoleUserInfo(schMid, mRId, prov, city, county, "", schoolType, scId, 0, 0);
				}
			}
			if(schoolType==1){
				for (int g = 1; g <=yearSystem; g++) {//年级
					getGenGraManager(ruManager, uManager, prov, city, county,
							schoolType, yearSystem, lastLoginIp, currTime,
							scId, mRId, g);
					//getGenRela(ruManager,uManager,rManager,prov, city, yearSystem, lastLoginIp, currTime,scId, g,county,schoolType,mRId);//生成班内老师
				}
			}else if(schoolType==2&&yearSystem==3){//年级
				for(int g =7;g<=9;g++){
					getGenGraManager(ruManager, uManager, prov, city, county,
							schoolType, yearSystem, lastLoginIp, currTime,
							scId, mRId, g);
					//getGenRela(ruManager,uManager,rManager,prov, city, yearSystem, lastLoginIp, currTime,scId, g,county,schoolType,mRId);//生成班内老师
				}
				
			}else if(schoolType==2&&yearSystem==4){//年级
				for(int g=6;g<=9;g++){
					getGenGraManager(ruManager, uManager, prov, city, county,
							schoolType, yearSystem, lastLoginIp, currTime,
							scId, mRId, g);
					//getGenRela(ruManager,uManager,rManager,prov, city, yearSystem, lastLoginIp, currTime,scId, g,county,schoolType,mRId);//生成班内老师
				}
			}else{
				for(int g=10;g<=12;g++){//年级
					List<RoleUserInfo> gRu = ruManager.listUserRoleInfoByPosition(prov, city, county, schoolType, scId, g, 0);
					//创建年级管理员
					if(gRu.isEmpty()){
						//生成年级管理员账户
						Integer gMid=uManager.addUser("g"+scId+g, "", new MD5().calcMD5("123456"), "",currTime, lastLoginIp, currTime, scId, "", yearSystem, prov, city);
						//绑定年级管理员角色
						ruManager.addRoleUserInfo(gMid, mRId, prov, city, county, "", schoolType, scId, g, 0);
					}
					//getGenRela(ruManager,uManager,rManager,prov, city, yearSystem, lastLoginIp, currTime,scId, g,county,schoolType,mRId);//生成班内老师
				}
			}
			msg = "success";
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
		
	}
	/**
	 * 生成年级管理员
	 * @author zong
	 * 2019-5-8下午04:58:04
	 * @param ruManager
	 * @param uManager
	 * @param prov 省
	 * @param city 市
	 * @param county 县
	 * @param schoolType 学段
	 * @param yearSystem 学年制
	 * @param lastLoginIp 最后登录IP
	 * @param currTime 当前时间
	 * @param scId 学校编号
	 * @param mRId 角色编号
	 * @param g 年级
	 * @throws WEBException
	 */
	private void getGenGraManager(RoleUserInfoManager ruManager,
			UserManager uManager, String prov, String city, String county,
			Integer schoolType, Integer yearSystem, String lastLoginIp,
			String currTime, Integer scId, Integer mRId, int g)
			throws WEBException {
		List<RoleUserInfo> gRu = ruManager.listUserRoleInfoByPosition(prov, city, county, schoolType, scId, g, 0);
		//创建年级管理员
		if(gRu.isEmpty()){
			//生成年级管理员账户
			Integer gMid=uManager.addUser("g"+scId+"0"+g, "", new MD5().calcMD5("123456"), "",currTime, lastLoginIp, currTime, scId, "", yearSystem, prov, city);
			//绑定年级管理员角色
			ruManager.addRoleUserInfo(gMid, mRId, prov, city, county, "", schoolType, scId, g, 0);
		}
	}
	/**
	 * 添加学校后生成班级,班内老师账户,绑定角色，绑定班级
	 * @author zong
	 * 2019-5-7下午05:22:54
	 * @param prov 省
	 * @param city 市
	 * @param yearSystem 学年制
	 * @param lastLoginIp 最后登录iP
	 * @param currTime 当前时间
	 * @param scId 学校编号
	 * @param g 年级编号（1-12 小学一年级--高中三年级）
	 * @param county 
	 * @param schoolType 
	 * @param mRId 
	 * @throws Exception
	 * @throws WEBException
	 */
/*	private void getGenRela(RoleUserInfoManager ruManager,UserManager uManager,RoleInfoManager rManager,
			String prov, String city, Integer yearSystem,
			String lastLoginIp, String currTime, Integer scId, int g, String county, Integer schoolType, Integer mRId)
			throws Exception, WEBException {
		ClassInfoManager cInfoManager = (ClassInfoManager) AppFactory.instance(null).getApp(Constants.WEB_CLASS_INFO);
		GradeSubjectManager gsManager = (GradeSubjectManager) AppFactory.instance(null).getApp(Constants.WEB_GRADE_SUBJECT_INFO);
	
		UserClassInfoManager ucManager = (UserClassInfoManager) AppFactory.instance(null).getApp(Constants.WEB_USER_CLASS_INFO);
		for(int c =1;c<=20;c++){
			Integer ciId = cInfoManager.addClassInfo(scId, c+"班", Convert.gradeNoToBuildeClassDate(g));//创建班级
			if(prov!=""&&city !=""&&county!=""&&schoolType!=0 && scId !=0&&g!=0){
				List<RoleUserInfo> cRu = ruManager.listUserRoleInfoByPosition(prov, city, county, schoolType, scId, g, c);
				//创建班级管理员
				if(cRu.isEmpty()){
					//生成班级管理员账户
					Integer cMid=uManager.addUser("c"+ciId, "", new MD5().calcMD5("123456"), "",currTime, lastLoginIp, currTime, scId, "", yearSystem, prov, city);
					//绑定班级管理员角色
					ruManager.addRoleUserInfo(cMid, mRId, prov, city, county, "", schoolType, scId, g, ciId);
				}
			}
			if(ciId>0){//班内学科老师
				String gName = Convert.NunberConvertChinese(g);//年级名
				List<GradeSubject> gslist = gsManager.listSpecInfoByGname(gName);//根据年级名获取学科列表
				for (GradeSubject gs : gslist) {
					Integer subId = gs.getSubject().getId();
					//生成班内老师账户
					Integer teaId=uManager.addUser("t"+scId+subId+ciId, "", new MD5().calcMD5("123456"), "",currTime, lastLoginIp, currTime, scId, "", yearSystem, prov, city);
					//老师绑定角色
					List<RoleInfo> rlist = rManager.listRoleInfo("老师");
					if(rlist.size() > 0){
						Integer roleId = rlist.get(0).getId();
						ruManager.addRoleUserInfo(teaId, roleId, "", "", "", "", 0, 0, 0, 0);
						ucManager.addUcInfo(teaId, ciId, roleId); //绑定班级
					}
					
				}
				           
			}
		}
	}*/
}