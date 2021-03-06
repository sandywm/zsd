/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.zsd.action.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zsd.factory.AppFactory;
import com.zsd.module.ClassInfo;
import com.zsd.module.GradeSubject;
import com.zsd.module.School;
import com.zsd.module.StudentParentInfo;
import com.zsd.module.TownInfo;
import com.zsd.module.User;
import com.zsd.module.UserClassInfo;
import com.zsd.service.ClassInfoManager;
import com.zsd.service.GradeSubjectManager;
import com.zsd.service.SchoolManager;
import com.zsd.service.StudentParentInfoManager;
import com.zsd.service.TownManager;
import com.zsd.service.UserClassInfoManager;
import com.zsd.service.UserManager;
import com.zsd.tools.CommonTools;
import com.zsd.tools.Convert;
import com.zsd.tools.CurrentTime;
import com.zsd.util.Constants;
import com.zsd.util.WebUrl;

/** 
 * MyEclipse Struts
 * Creation date: 04-28-2019
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class BaseInfoAction extends DispatchAction {
	
	
	/**
	 * 根据省、市、县、乡、学段获取学校的列表（下拉列表用）
	 * @author Administrator
	 * @date 2019-4-29 下午03:08:03
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getSchoolData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		SchoolManager sm = (SchoolManager) AppFactory.instance(null).getApp(Constants.WEB_SCHOOL_INFO);
		String prov = Transcode.unescape_new("prov", request);
		String city = Transcode.unescape_new("city", request);
		String county = Transcode.unescape_new("county", request);
		String town = Transcode.unescape_new("town", request);
		Integer schoolType = CommonTools.getFinalInteger("schoolType", request);
		Integer yearSystem = CommonTools.getFinalInteger("yearSystem", request);
		Integer opt = CommonTools.getFinalInteger("opt", request);//0:学生注册时加载学校列表用（其他学校）,1:不显示其他学校
		List<School> sList = sm.listInfoByOpt(prov, city, county, town, schoolType,yearSystem);
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> map_other = new HashMap<String,Object>();
		String msg = "noInfo";
		if(opt.equals(0)){
			Integer schoolId = 0;
			String schoolName = "其他学校";
			if(schoolType.equals(1)){
				schoolId = -1;
				schoolName += "(小学)";
			}else if(schoolType.equals(2)){
				schoolId = -2;
				schoolName += "(初中)";
			}else if(schoolType.equals(3)){
				schoolId = -3;
				schoolName += "(高中)";
			}
			map_other.put("schoolId", schoolId);
			map_other.put("schoolName", schoolName);
		}
		if(sList.size() > 0){
			msg = "success";
			List<Object> list_d = new ArrayList<Object>();
			if(opt.equals(0)){
				list_d.add(map_other);
			}
			for(Iterator<School> it = sList.iterator() ; it.hasNext();){
				School sch = it.next();
				Map<String,Object> map_d = new HashMap<String,Object>();
				map_d.put("schoolId", sch.getId());
				map_d.put("schoolName", sch.getSchoolName());
				map_d.put("yearSystem", sch.getYearSystem());
				map_d.put("schoolType", sch.getSchoolType());
				list_d.add(map_d);
			}
			map.put("schList", list_d);
		}else{
			if(opt.equals(0)){
				msg = "success";
				List<Object> list_d = new ArrayList<Object>();
				list_d.add(map_other);
				map.put("schList", list_d);
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 定位当前IP省市
	 * @author wm
	 * @date 2019-6-10 上午11:27:40
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getAreaJson(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<String,String>();
//		String address = CommonTools.getSelfArea_taobao("123.52.203.75");
////		String address = CommonTools.getSelfArea_taobao(CommonTools.getIpAddress(request));
//		JSONObject jsonResult = JSON.parseObject(address);
//		System.out.println(JSON.toJSONString(jsonResult, true));
//		JSONObject dataJson = jsonResult.getJSONObject("data");
//		String provName = dataJson.getString("region");
//		String cityName = dataJson.getString("city");
//		String provNo = dataJson.getString("region_id");
//		String cityNo = dataJson.getString("city_id");
//		map.put("provName", provName);
//		map.put("cityName", cityName);
//		map.put("provNo", provNo);
//		map.put("cityNo", cityNo);
		String address = CommonTools.getSelfArea(CommonTools.getIpAddress(request));
		
		String prov = "";
		String city = "";
		if(address.split(":").length == 2){
			prov = address.split(":")[0];
			city = address.split(":")[1];
		}
		map.put("provName", prov);
		map.put("cityName", city);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 获取用户账号登录状态
	 * success:账号正常，loginOut：账号别处登录,sessionLose:超过3天未登陆,accountInvalid(账号无效)
	 * @author wm
	 * @date 2019-6-12 上午10:09:40
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkUserLoginStatus(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
//		 long systime = new Date().getTime();//当前系统时间
		UserManager um = (UserManager)AppFactory.instance(null).getApp(Constants.WEB_USER_INFO);
		Integer login_status_dataBase = -1;
		String result = "accountError";//用户账号状态--账号错误(默认)
		Integer userId_local = CommonTools.getFinalInteger("userId", request);
		Integer loginStatus_local = CommonTools.getFinalInteger("loginStatus", request);
		String lastLoginDate_db = "";
		String currDate = CurrentTime.getStringDate();
		if(userId_local > 0 && loginStatus_local > 0){
			List<User> uList = um.listEntityById(userId_local);
			if(uList.size() > 0){
				if(uList.get(0).getAccountStatus().equals(1)){
					lastLoginDate_db = uList.get(0).getLastLoginDate().substring(0, 10);
					Integer diffDays = CurrentTime.compareDate(lastLoginDate_db, currDate);
					if(diffDays > 3){
						result = "sessionLose";//3天无操作
					}else{
						login_status_dataBase = uList.get(0).getLoginStatus();
						if(login_status_dataBase.equals(loginStatus_local)){
							result = "success";//账号正常
						}else{
							result = "loginOut";//账号别处登录
						}
					}
				}else{
					result = "acountLock";//账号被锁定
				}
			}
		}
		Map<String,String> map = new HashMap<String,String>();
		map.put("result", result);
		CommonTools.getJsonPkg(map, response);
//		long oldtime = new Date().getTime();
//		System.out.println(oldtime - systime);
		return null;
	}
	
	/**
	 * 初始化增加乡镇数据
	 * @author wm
	 * @date 2019-6-21 下午04:45:26
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward initTownData(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		TownManager tm = (TownManager)AppFactory.instance(null).getApp(Constants.WEB_TOWN_INFO);
		File file = new File("d:/new4.json");
		InputStreamReader br = new InputStreamReader(new FileInputStream(file),"utf-8");//读取文件,同时指定编码
		StringBuffer sb = new StringBuffer();
        char[] ch = new char[128];  //一次读取128个字符
        int len = 0;
        while((len = br.read(ch,0, ch.length)) != -1){
            sb.append(ch, 0, len);
        }
        String s = sb.toString();
        if(!s.equals("")){
        	JSONObject dataJson = JSON.parseObject(s); 
            JSONArray features = dataJson.getJSONArray("areaList");// 找到features json数组
            //第一级
            for(int i = 0 ; i < features.size() ; i++){
            	JSONArray features1 = features.getJSONObject(i).getJSONArray("children");
                //第二级
            	for(int j = 0 ; j < features1.size() ; j++){
            		JSONArray features2 = features1.getJSONObject(j).getJSONArray("children");
            		for(int k = 0 ; k < features2.size() ; k++){
            			 //第三级
            			JSONObject obj2 = features2.getJSONObject(k);
            			String countyCode = obj2.getString("code");
            			String countyName = obj2.getString("name");
                        JSONArray features3 = obj2.getJSONArray("children");
                        for(Integer num = 0 ; num < features3.size() ; num++){
                        	JSONObject obj3 = features3.getJSONObject(num);
                        	tm.addInfo(countyCode, countyName, obj3.getString("code"), obj3.getString("name"));
                        }
            		}
            	}
            }
        }
		Map<String,String> map = new HashMap<String,String>();
		map.put("result", "success");
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 根据县编码获取乡镇数据列表
	 * @author wm
	 * @date 2019-6-21 下午05:13:38
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getSpecTownData(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		TownManager tm = (TownManager)AppFactory.instance(null).getApp(Constants.WEB_TOWN_INFO);
		String countyCode = CommonTools.getFinalStr("countyCode", request);
		String msg = "noInfo";
		Map<String,Object> map = new HashMap<String,Object>();
		List<TownInfo> tList = tm.listInfoByCountyCode(countyCode);
		if(tList.size() > 0){
			msg = "success";
			List<Object> list_d = new ArrayList<Object>();
			for(TownInfo t : tList){
				Map<String,String> map_d = new HashMap<String,String>();
				map_d.put("townCode", t.getTownCode());
				map_d.put("townName", t.getTownName());
				list_d.add(map_d);
			}
			map.put("townList", list_d);
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 获取学生所在年级的学科列表
	 * @author wm
	 * @date 2019-6-25 下午04:40:30
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getSelfSubjectData(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		UserClassInfoManager ucm = (UserClassInfoManager)AppFactory.instance(null).getApp(Constants.WEB_USER_CLASS_INFO);
		GradeSubjectManager gsm = (GradeSubjectManager)AppFactory.instance(null).getApp(Constants.WEB_GRADE_SUBJECT_INFO);
		StudentParentInfoManager spm = (StudentParentInfoManager)AppFactory.instance(null).getApp(Constants.WEB_STUDENT_PARENT_INFO);
		//获取我所在年级的学科
		Integer userId = CommonTools.getLoginUserId(request);
		Integer roleId = CommonTools.getLoginRoleId(request);
		Integer subId = CommonTools.getFinalInteger("subId", request);
		String msg = "noInfo";
		Map<String,Object> map = new HashMap<String,Object>();
		if(userId > 0){
			if(subId.equals(0)){
				subId = 2;
			}
			if(roleId.equals(Constants.PATENT_ROLE_ID)){//家长角色需要获取自己孩子的userId
				StudentParentInfo sp = spm.getEntityByParId(userId);
				if(sp != null){
					userId = sp.getStu().getId();//孩子的Id
				}
			}
			List<UserClassInfo> ucList = ucm.listInfoByOpt_1(userId, Constants.STU_ROLE_ID);//获取学生所在班级信息
			if(ucList.size() > 0){
				Integer gradeNumber = Convert.dateConvertGradeNumber(ucList.get(0).getClassInfo().getBuildeClassDate());
				if(gradeNumber > 12){
					gradeNumber = 12;
				}
				String gradeName = Convert.NunberConvertChinese(gradeNumber);
				//获取当前年级的学科列表
				List<GradeSubject>  gsList = gsm.listSpecInfoByGname(gradeName);
				if(gsList.size() > 0){
					msg = "success";
					List<Object> list_g = new ArrayList<Object>();
					for(GradeSubject gs : gsList){
						Map<String,Object> map_g = new HashMap<String,Object>();
						map_g.put("subId", gs.getSubject().getId());
						map_g.put("subName", gs.getSubject().getSubName());
						if(gs.getSubject().getId().equals(subId)){
							map_g.put("selFlag", true);
						}else{
							map_g.put("selFlag", false);
						}
						list_g.add(map_g);
					}
					map.put("subList", list_g);
				}else{
					msg = "noInfo";
				}
			}
		}
		map.put("result", msg);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	/**
	 * 根据学生编号获取学生家长信息
	 * @author zdf
	 * 2019-9-26 下午03:38:01
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getParentInfo(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		StudentParentInfoManager spManager = (StudentParentInfoManager) AppFactory.instance(null).getApp(Constants.WEB_STUDENT_PARENT_INFO);
		Integer stuId = CommonTools.getLoginUserId(request);
		
		StudentParentInfo spInfo =  spManager.getEntityBystuId(stuId);
		Map<String,Object> map = new HashMap<String,Object>();
		if(spInfo!= null){
			map.put("portrait", spInfo.getParent().getPortrait());
			map.put("realName", spInfo.getParent().getRealName());
			map.put("nickName", spInfo.getParent().getNickName());
			map.put("userAcc", spInfo.getParent().getUserAccount());
			String pass ="E10ADC3949BA59ABBE56E057F20F883E";
			if(pass.equalsIgnoreCase(spInfo.getParent().getPassword())){
				map.put("password", 123456);
			}else{
				map.put("password", "******");
			}
			map.put("mobile", spInfo.getParent().getMobile());
			map.put("email", spInfo.getParent().getEmail());
			map.put("birthday", spInfo.getParent().getBirthday());
		}
		CommonTools.getJsonPkg(map, response);
		return null;
		
	}
	
	/**
	 * 获取我的金币数量
	 * @author wm
	 * @date 2019-10-2 下午03:26:09
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getSelfCoin(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		UserManager um = (UserManager) AppFactory.instance(null).getApp(Constants.WEB_USER_INFO);
		Integer userId = CommonTools.getLoginUserId(request);
		Map<String,Object> map = new HashMap<String,Object>();
		String msg = "error";
		Integer coin = 0;
		Integer zsdCoin = 0;
		if(userId > 0){
			List<User> uList = um.listEntityById(userId);
			if(uList.size() > 0){
				msg = "success";
				coin = uList.get(0).getCoin();
				zsdCoin = uList.get(0).getCoinZsd();
			}
		}
		map.put("result", msg);
		if(msg.equals("success")){
			map.put("coin", coin);
			map.put("zsdCoin", zsdCoin);
		}
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 进入下载app页面
	 * @author wm
	 * @date 2019-9-26 上午08:34:18
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  downApp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("downAppPage");
	}
	
	/**
	 * 获取版本号
	 * @author wm
	 * @date 2019-10-8 上午08:34:01
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 * @throws Exception
	 */
	public ActionForward getNewAppVersion(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws IOException{
		String s = null;
		Map<String,Object> map = new HashMap<String,Object>();
        String msg = "error";
		File file = new File(WebUrl.APP_VERSION);
		if(file.exists()){
			InputStreamReader br = new InputStreamReader(new FileInputStream(file),"utf-8");//读取文件,同时指定编码
			String opt = CommonTools.getFinalStr("opt", request);//new或者不传时为获取最新版本，all时表示获取全部版本
			if(opt.equals("")){
				opt = "new";
			}
			StringBuffer sb = new StringBuffer();
	        char[] ch = new char[128];  //一次读取128个字符
	        int len = 0;
	        while((len = br.read(ch,0, ch.length)) != -1){
	            sb.append(ch, 0, len);
	        }
	        s = sb.toString();
	        JSONObject dataJson = JSON.parseObject(s); 
	        JSONArray features = dataJson.getJSONArray("versionList");// 找到features json数组
	        Integer length = features.size();
	        if(length > 0){
	        	if(opt.equals("new")){
	        		msg = "success";
	        		map.put("version",features.getJSONObject(length - 1).getString("version"));
	        		map.put("appSize",features.getJSONObject(length - 1).getString("appSize"));
	        		map.put("date",features.getJSONObject(length - 1).getString("date"));
	        		map.put("upLog", features.getJSONObject(length - 1).getString("upLog"));
	        	}else if(opt.equals("all")){
	        		msg = "success";
	        		List<Object> list_d = new ArrayList<Object>();
	        		for(Integer i = 0 ; i < length ; i++){
	        			 Map<String,Object> map_d = new HashMap<String,Object>();
	        			 map_d.put("version", features.getJSONObject(i).getString("version"));
	        			 map_d.put("appSize", features.getJSONObject(i).getString("appSize"));
	        			 map_d.put("date", features.getJSONObject(i).getString("date"));
	        			 map_d.put("upLog", features.getJSONObject(i).getString("upLog"));
	        			 list_d.add(map_d);
	        		}
	        		map.put("versionList", list_d);
	        	}
	        }
		}
        map.put("result", msg);
        CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 获取当前设备信息
	 * @author wm
	 * @date 2019-10-8 上午09:48:05
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public ActionForward getCilentInfo(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws IOException{
		String cilentInfo = CommonTools.getCilentInfo_new(request);
		Map<String,String> map = new HashMap<String,String>();
		map.put("result", cilentInfo);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
	
	/**
	 * 获取指定指定学校、年级下有效的班级列表
	 * @author wm
	 * @date 2019-12-12 下午07:56:08
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getValidClassData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		SchoolManager sm = (SchoolManager) AppFactory.instance(null).getApp(Constants.WEB_SCHOOL_INFO);
		ClassInfoManager cm = (ClassInfoManager) AppFactory.instance(null).getApp(Constants.WEB_CLASS_INFO);
		Integer schoolId = CommonTools.getFinalInteger("schoolId", request);
		String gradeName = Transcode.unescape_new1("gradeName", request);
		Integer gradeNo = 0;
		if(!gradeName.equals("")){
			gradeNo = Integer.parseInt(Convert.ChineseConvertNumber(gradeName));
		}
		String msg = "noInfo";
		Map<String,Object> map = new HashMap<String,Object>();
		List<Object> list_d = new ArrayList<Object>();
		if(schoolId > 0 && gradeNo > 0){
			List<School> sList = sm.listInfoById(schoolId);
			if(sList.size() > 0){
//				Integer yearSystem = sList.get(0).getYearSystem();
//				Integer schoolType = sList.get(0).getSchoolType();
//				String buildeClassDate = Convert.gradeNoToBuildeClassDate(gradeNo);
				List<ClassInfo> cList = cm.listClassInfoByOption(gradeNo, CurrentTime.getStringDate(), schoolId, "");
				if(cList.size() > 0){
					msg = "success";
					for(ClassInfo c : cList){
						Map<String,Object> map_d = new HashMap<String,Object>();
						map_d.put("classId", c.getId());
						map_d.put("className", c.getClassName());
						list_d.add(map_d);
					}
				}
			}
		}
		map.put("msg", msg);
		map.put("classList", list_d);
		CommonTools.getJsonPkg(map, response);
		return null;
	}
}