package com.zsd.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.zsd.factory.AppFactory;
import com.zsd.module.BuffetQueInfo;
import com.zsd.module.LoreInfo;
import com.zsd.module.User;
import com.zsd.module.json.LoreBuffetTreeMenuJson;
import com.zsd.module.json.LoreTreeMenuJson;
import com.zsd.module.json.MyTreeNode;
import com.zsd.service.BuffetQueInfoManager;
import com.zsd.service.LoreInfoManager;
import com.zsd.service.UserManager;
import com.zsd.util.Constants;

public class CommonTools {
	
	/**
	 * 获取session/页面传递过来的用户ID
	 * PC端通过session获取，移动端通过页面获取
	 * @author wm
	 * @date 2019-6-14 下午05:54:38
	 * @param request
	 * @return
	 */
	public static Integer getLoginUserId(HttpServletRequest request){
		String cilentInfo = CommonTools.getCilentInfo_new(request);
		Integer userId = 0;
		if(cilentInfo.equals("pc")){
			userId = (Integer)request.getSession(false).getAttribute(Constants.LOGIN_USER_ID);
	        if(userId == null){
	        	return 0;
	        }
		}else{
			userId = CommonTools.getFinalInteger("userId", request);
		}
        return userId;
	}
	
	/**
	 * 获取session中用户的登陆次数
	 * @author wm
	 * @date 2019-6-12 上午10:07:55
	 * @param request
	 * @return
	 */
	public static Integer getLoginStatus(HttpServletRequest request){
		String cilentInfo = CommonTools.getCilentInfo_new(request);
		Integer loginStatus = 0;
		if(cilentInfo.equals("pc")){
			loginStatus = (Integer)request.getSession(false).getAttribute(Constants.LOGIN_STATUS);
	        if(loginStatus == null){
	        	return 0;
	        }
		}else{
			loginStatus = CommonTools.getFinalInteger("loginStatus", request);
		}
        return loginStatus;
	}
	
	/**
	 * 获取session中的用户账号
	 * @author wm
	 * @date 2019-5-10 上午11:19:52
	 * @param request
	 * @return
	 */
	public static String getLoginAccount(HttpServletRequest request){
		String cilentInfo = CommonTools.getCilentInfo_new(request);
		String account = "";
		if(cilentInfo.equals("pc")){
			account = String.valueOf(request.getSession(false).getAttribute(Constants.LOGIN_ACCOUNT));
	        if(account.equals("null")){
	        	return "";
	        }
		}else{
			account = CommonTools.getFinalStr("account", request);
		}
        return account;
	}
	
	/**
	 * 获取session中的用户角色编号
	 * @param request
	 * @return
	 */
	public static Integer getLoginRoleId(HttpServletRequest request){
		String cilentInfo = CommonTools.getCilentInfo_new(request);
		Integer roleId = 0;
		if(cilentInfo.equals("pc") ){
			roleId = (Integer)request.getSession(false).getAttribute(Constants.LOGIN_USER_ROLE_ID);
	        if(roleId == null){
	        	return 0;
	        }
		}else{
			roleId = CommonTools.getFinalInteger("roleId", request);
		}
        return roleId;
	}
	
	/**
	 * 获取session中的用户角色名称(先不启用)
	 * @param request
	 * @return
	 */
	public static String getLoginRoleName(HttpServletRequest request){
        String roleName = String.valueOf(request.getSession(false).getAttribute(Constants.LOGIN_USER_ROLE_NAME));
        if(roleName.equals("null")){
        	return "";
        }
        return roleName;
	}
	
	/**
	 * 封装json
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午04:05:08
	 * @param obj
	 * @param response
	 * @throws IOException
	 */
	public static void getJsonPkg(Object obj,HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
	    response.setCharacterEncoding("UTF-8");
		String json = JSON.toJSONString(obj);
        PrintWriter pw = response.getWriter();  
        pw.write(json); 
        pw.flush();  
        pw.close();
	}
	
	/**
	 * null转换为""
	 * @description
	 * @author wm
	 * @date 2018-8-25 下午06:01:28
	 * @param inputStr
	 * @param request
	 * @return
	 */
	public static String getFinalStr(String inputStr,HttpServletRequest request){
		inputStr = String.valueOf(request.getParameter(inputStr));
		if(inputStr == "null"){
			return "";
		}else{
			return inputStr;
		}
	}
	
	/**
	 * 输入的数字类型转换成数据类型
	 * @description
	 * @author wm
	 * @date 2016-9-6 下午03:09:46
	 * @param inputData
	 * @return
	 */
	public static Integer getFinalInteger(String inputData,HttpServletRequest request){
		inputData = String.valueOf(request.getParameter(inputData));
		if(inputData.equals("") || inputData.equals("null")  || inputData.equals("undefined")){
			return 0;
		}else{
			return Integer.parseInt(inputData);
		}
	}
	/**
	 * 输入的数字类型转换成数据类型
	 * @description
	 * @author wm
	 * @date 2017-4-21 上午11:16:34
	 * @param inputData
	 * @return
	 */
	public static Float getFinalFloat(String inputData,HttpServletRequest request){
		inputData = String.valueOf(request.getParameter(inputData));
		if(inputData.equals("") || inputData.equals("null")  || inputData.equals("undefined")){
			return 0f;
		}else{
			return Float.parseFloat(inputData);
		}
	}
	
	/**
	 * 输入的数字类型转换成数据类型
	 * @description
	 * @author wm
	 * @date 2017-4-21 上午11:16:34
	 * @param inputData
	 * @return
	 */
	public static Double getFinalDouble(String inputData,HttpServletRequest request){
		inputData = String.valueOf(request.getParameter(inputData));
		if(inputData.equals("") || inputData.equals("null")  || inputData.equals("undefined")){
			return 0.0;
		}else{
			return Double.parseDouble(inputData);
		}
	}
	
	/**
     * 写入xss/sql注入攻击报告
     * @description
     * @author wm
     * @date 2016-4-29 上午08:34:23
     * @param subject
     * @param request
     */
    public static void sendAttackReport(String filePath,String content,HttpServletRequest request,String attackUrl){
		String attackInfo = "      攻击时间："+CurrentTime.getCurrentTime() + "      攻击IP："+CommonTools.getIpAddress(request) + "     攻击URL："+attackUrl;
		File oldfile = new File(filePath);  
		if(!oldfile.exists()){
			 try {
				oldfile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileWriter fw;
		try {
			fw = new FileWriter(oldfile, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println("  "+content+attackInfo);
			pw.flush();
			fw.flush();
			pw.close();
			fw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
  //获取真实IP地址
	public static String getIpAddress(HttpServletRequest request){
		String ipAddress = "";
		ipAddress = request.getHeader("x-forwarded-for");    
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {    
			ipAddress = request.getHeader("Proxy-Client-IP");    
	    }
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {    
	        ipAddress = request.getHeader("WL-Proxy-Client-IP");    
	    }  
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
	        ipAddress = request.getHeader("HTTP_CLIENT_IP");  
	    }  
	    if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
	        ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");  
	    }  
		if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {    
			ipAddress = request.getRemoteAddr();    
			if(ipAddress.equals("127.0.0.1") ||ipAddress.equals("0:0:0:0:0:0:0:1")){    
				//根据网卡取本机配置的IP    
				InetAddress inet=null;    
			    try {    
			    	inet = InetAddress.getLocalHost();    
			    } catch (UnknownHostException e) {    
			    	e.printStackTrace();    
			    }    
			    ipAddress= inet.getHostAddress();
			}          
	    }   
		//对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割    
	    if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15    
	    	if(ipAddress.indexOf(",")>0){    
	    		ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));    
	    	}    
	    }    
		return ipAddress;
	}
	
	/**
	 * 根据IP地址获取当前省、市
	 * @description
	 * @author wm
	 * @date 2018-8-2 下午04:18:06
	 * @param ip
	 * @return
	 */
	public static String getSelfArea(String ip) {
		String address="";
		String prov = "",city = "";
		String strUrl="https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?query="+ip+"&co=&resource_id=6006&t=1444747793291&ie=utf8&oe=utf8&cb=op_aladdin_callback&format=json&tn=baidu&cb=jQuery110207215902183078953_1444747767470&_=1444747767472";
		try { 
			URL url = new URL(strUrl);
			
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
	        //add reuqest header
	        con.setRequestMethod("GET");
	        // Send post request
	        con.setDoOutput(true);
	        BufferedReader in = new BufferedReader(
	                new InputStreamReader(con.getInputStream(),Charset.forName("UTF-8")));
	        String inputLine;
	        StringBuffer response = new StringBuffer();
	 
	        while ((inputLine = in.readLine()) != null) {
	            response.append(inputLine);
	        }
	        in.close();
	 
	        int idx=response.indexOf("{\"location\":\"");
	        if(idx != -1){
		        String response2 = response.substring(response.indexOf("{\"location\":\"")+13,response.length());
		        address = response2.substring(0,response2.indexOf("\""));
		        String[] zzq = {"内蒙古","新疆","西藏","广西","宁夏"};
				String[] zxs = {"北京市","天津市","上海市","重庆市"};
				String[] xzq = {"香港","澳门"};
				if(address.contains("自治区")){//自治区
					for(Integer i = 0 ; i < zzq.length ; i++){
						if(address.contains(zzq[i])){
							prov = zzq[i]+"自治区";
							Integer startIndex = address.indexOf("自治区");
							city = address.substring(startIndex+3, address.indexOf("市"))+"市";
							break;
						}
					}
				}else if(address.contains("省")){//省、市
					Integer startIndex = address.indexOf("省");
					Integer endIndex = address.indexOf("市");
					prov = address.substring(0,startIndex)+"省";
					city = address.substring(startIndex+1, endIndex)+"市";
				}else if(address.contains("行政区")){//特别行政区
//					for(Integer i = 0 ; i < xzq.length ; i++){
//						if(address.contains(xzq[i])){
//							prov = city = xzq[i];
//							break;
//						}
//					}
					prov = address;
					city = "none";
				}else if(address.equals("本地局域网")){
					prov = "河南省";
					city = "濮阳市";
				}else{//直辖市
					for(Integer i = 0 ; i < zxs.length ; i++){
						if(address.contains(zxs[i])){
							prov = zxs[i];
							city = "none";
							break;
						}
					}
				}
				address = prov + ":" + city;
	        }
	        else
	        	address = "un-know";
		} catch (Exception e) {
			e.printStackTrace();
			address = "un-know";
		}
		return address;
	}
	
	/**
	 * 根据IP地址获取当前省、市
	 * @author wm
	 * @date 2019-6-12 下午02:06:36
	 * @param ip
	 * @return
	 */
	public static String getSelfArea_taobao(String ip) {
		String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = "http://ip.taobao.com/service/getIpInfo.php?ip="+ip;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("Charsert", "UTF-8");
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //-------------post----------------//
//            connection.setRequestProperty("Content-Type", "application/json");
//            // 发送POST请求必须设置如下两行
//            connection.setDoOutput(true);
//            connection.setDoInput(true);
            
            //----------------get-----------------//
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
	}
	
	/**
	 * 获取客户端信息（上述2种方法的整合）分清安卓、ios、pc、移动浏览器
	 * @description
	 * @author wm
	 * @date 2016-7-14 上午08:38:13
	 * @param request
	 * @return
	 */
	public static String getCilentInfo_new(HttpServletRequest request){
		String clientInfo = request.getHeader("User-agent");
		String cilentQuip = "";
		if(clientInfo != null){
			if(clientInfo.indexOf("Android") >= 0 || clientInfo.indexOf("iPad") >= 0 || clientInfo.indexOf("iPhone") >= 0){
				if(clientInfo.indexOf("AppleWebKit") > 0){//手机浏览器，手机app封装的html页面
					if(clientInfo.indexOf("Html5") > 0){
						cilentQuip = "commonApp";////手机app封装html页面
					}else{
						//cilentQuip = "pc";//移动端浏览器效果同于PC
						if(clientInfo.indexOf("Android") >= 0){//安卓手机浏览器
							cilentQuip = "andriodWeb";
						}else if(clientInfo.indexOf("iPad") >= 0 || clientInfo.indexOf("iPhone") >= 0){//苹果手机浏览器
							cilentQuip = "iphoneWeb";
						}
					}
				}else{
					if(clientInfo.indexOf("Android") >= 0){//移动端APP
						cilentQuip = "andriodApp";
					}else if(clientInfo.indexOf("iPad") >= 0 || clientInfo.indexOf("iPhone") >= 0){
						cilentQuip = "iphoneApp";
					}
				}
			}else if(clientInfo.indexOf("Lavf") >= 0){//手机端播放视频时
				cilentQuip = "commonApp";////手机app封装html页面
			}else{
				cilentQuip = "pc";//PC端
			}
		}else{
			cilentQuip = "";//无法获取客户端信息
		}
		return cilentQuip;
	}
	
	/**
	 * 根据传递的入库单编号自动获取下一个
	 * @description
	 * @author wm
	 * @date 2017-5-11 上午09:30:26
	 * @param insNoStr 数据库中最后一个入库单编号
	 * @param preSuffix 前缀字母代号
	 * @return
	 */
	public static String getInStoreNo(String insNoStr){
		String insNo_base = insNoStr.split("_")[1];
		String preStr = insNoStr.split("_")[0];
		Integer insNoLength = insNo_base.length();
		String insNo_curr_str = String.valueOf((Integer.parseInt(insNo_base) + 1));
		Integer insNo_curr_length = insNo_curr_str.length();
		Integer diff = insNoLength - insNo_curr_length;
		preStr += "_"; 
		for(Integer i = 0 ; i < diff ; i++){
			preStr += "0";
		}
		return preStr + insNo_curr_str;
	}
	
	public static boolean checkMobile(String mobile) {
		if (mobile.equals("")) {
			return false;
		} else {
			String regex = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(mobile);

			return m.find();
		}

	}

	/**
	 * 长整形对象转成整形（用于获取记录数时使用）
	 * @description
	 * @author wm
	 * @date 2018-7-25 上午10:07:53
	 * @param count_obj
	 * @return
	 */
	public static Integer longToInt(Object count_obj){
		long count = 0;
		if(count_obj != null){
			count =  Long.parseLong(String.valueOf( count_obj));
		}
		return (int)count;
	}
	
	/**
	 * 检查邮箱格式
	 * @description
	 * @author wm
	 * @date 2018-7-30 下午04:14:40
	 * @param inputEmail
	 * @return 证成功返回true，验证失败返回false 
	 */
	public static boolean checkEmail(String inputEmail){
		String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        return Pattern.matches(regex, inputEmail);   
	}

	
	/**
	 * 获取当前知识点编号的所在级数
	 * @author wm
	 * @date 2019-6-2 下午04:24:39
	 * @param pathArray
	 * @param currentLoreId
	 * @return
	 */
	public static Integer getCurrentStep(String[] pathArray,Integer currentLoreId){
		Integer currentI = 0;
		boolean flag = false;
		for(Integer i = 0 ; i < pathArray.length ; i++){
			String[] currentPathArray = pathArray[i].split("\\|");
			for(Integer j = 0 ; j < currentPathArray.length ; j++){
				if(currentPathArray[j].equals(String.valueOf(currentLoreId))){
					flag = true;
					currentI = i;
					break;
				}
			}
			if(flag){
				break;
			}
		}
		return currentI;
	}
	
	
	/**
	 * 获取本知识点对应的被引用的知识点编号
	 * @author wm
	 * @date 2019-6-2 下午04:56:50
	 * @param loreId
	 * @return
	 */
	public static Integer getQuoteLoreId(Integer loreId){
		Integer realLoreId = 0;
		try {
			LoreInfoManager lm = (LoreInfoManager)AppFactory.instance(null).getApp(Constants.WEB_LORE_INFO);
			LoreInfo lore = lm.getEntityById(loreId);
			Integer quoteLoreId = lore.getMainLoreId();
			if(quoteLoreId == 0){
				realLoreId = loreId;
			}else{
				realLoreId = quoteLoreId;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return realLoreId;
	}
	
	/**
	 * 根据通用知识点编号、学习知识点编号获取当前和学习知识点相同出版社的真实编号
	 * @author wm
	 * @date 2019-6-12 上午11:52:13
	 * @param quoteLoreId 题库知识点编号--通用版
	 * @param loreId 其他版本下的知识点编号(最开始学习的知识点)--学习知识点
	 * @return
	 */
	public static String[] getRealLoreInfo(Integer quoteLoreId,Integer loreId){
		String[] loreInfoArr = new String[2];
		try {
			LoreInfoManager lm = (LoreInfoManager)AppFactory.instance(null).getApp(Constants.WEB_LORE_INFO);
			LoreInfo lore_bb = lm.getEntityById(loreId);
			if(lore_bb != null){
				Integer ediId = lore_bb.getChapter().getEducation().getEdition().getId();//学习知识点所属出版社
				List<LoreInfo> loreList = lm.listInfoByMainLoreId(quoteLoreId);
				for(LoreInfo lore : loreList){
					if(lore.getChapter().getEducation().getEdition().getId().equals(ediId)){
						loreInfoArr[0] = lore.getId().toString();
						loreInfoArr[1] = lore.getLoreName();
						break;
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loreInfoArr;
	}
	
	/**
	 * 根据当前currentLoreId截取studyPath(当前知识典除去)获取当前知识点以后的知识点
	 * @author wm
	 * @date 2019-6-3 下午04:38:35
	 * @param studyPath 学习路线
	 * @param currentLoreId 当前知识点
	 * @return
	 */
	public static String getCurrentStudyPath_new(String studyPath,Integer currentLoreId){
		String studyPath_new = "";
		boolean flag = false;
		for(int i = 0 ; i < studyPath.split(":").length; i++){
			String[] path1 = studyPath.split(":")[i].split("\\|");
			for(int j = 0 ; j < path1.length ; j++){
				if(path1[j].equals(String.valueOf(currentLoreId))){
					flag = true;
				}else{
					if(flag == true){
						studyPath_new += path1[j] + "|";
					}
				}
			}
			if(studyPath_new.length() > 0 ){
				studyPath_new = studyPath_new.substring(0, studyPath_new.length() - 1);
				studyPath_new += ":";
			}
		}
		if(studyPath_new.length() > 0 ){
			studyPath_new = studyPath_new.substring(0, studyPath_new.length() - 1);
		}
		return studyPath_new;
	}
	
	/**
	 * 根据当前currentLoreId截取studyPath(包含当前知识点所在的)
	 * @author wm
	 * @date 2019-6-4 上午11:24:07
	 * @param studyPath
	 * @param currentLoreId
	 * @return
	 */
	public static String[] getStudyPath_new(String studyPath,String studyPathChi,Integer currentLoreId){
		String studyPath_new = "";
		String studyPathChi_new = "";
		String[] studyPath_new_arr = new String[2];
		Integer currentI = 0;
		boolean flag = false;
		if(!studyPath.equals("") && currentLoreId > 0){
			String[] studyPath_array = studyPath.split(":");
			String[] studyPathChi_array = studyPathChi.split(":");
			for(Integer i = 0 ; i < studyPath_array.length ; i++){
				String[] currentPathArray = studyPath_array[i].split("\\|"); 
				for(Integer j = 0 ; j < currentPathArray.length ; j++){
					if(currentPathArray[j].equals(String.valueOf(currentLoreId))){
						flag = true;
						currentI = i;
						break;
					}
				}
				if(flag){
					break;
				}
			}
			for(Integer i = currentI ; i < studyPath_array.length ; i++){
				studyPath_new += studyPath_array[i] + ":";
				studyPathChi_new += studyPathChi_array[i] + ":";
			}
			if(!studyPath_new.equals("")){
				studyPath_new = studyPath_new.substring(0, studyPath_new.length() - 1);
				studyPathChi_new = studyPathChi_new.substring(0, studyPathChi_new.length() - 1);
			}
			studyPath_new_arr[0] = studyPath_new;
			studyPath_new_arr[1] = studyPathChi_new;
		}
		return studyPath_new_arr;
		
	}
	
	/**
	 * 获取知识点全部路径(诊断/学习时)
	 * @author wm
	 * @date 2019-6-6 上午09:28:20
	 * @param loreId
	 * @param pathType diagnosis/study
	 * @return loreId组合,loreName组合
	 * @throws Exception
	 */
	public static String[] getLorePath(Integer loreId,String pathType) throws Exception{
		LoreInfoManager lm = (LoreInfoManager) AppFactory.instance(null).getApp(Constants.WEB_LORE_INFO);
		String[] pathArr = new String[2];
		String path = "";
		LoreTreeMenuJson ltmj = new LoreTreeMenuJson();
		List<MyTreeNode> ltList = ltmj.showTree(loreId, 0,"desc");
		StringBuilder buff = new StringBuilder();
		StringBuilder buffChi = new StringBuilder();
		ltmj.getPath(ltList, buff,buffChi);
		path = buff.delete(buff.length() - 1, buff.length()).toString();
		String pathChi = buffChi.delete(buffChi.length() - 1, buffChi.length()).toString();
		pathArr[0] = path;
		pathArr[1] = pathChi;
		if(pathType.equals("diagnosis")){//诊断
//			return pathArr;
			//新版本--start
			String loreIdStr = "";
			String loreNameStr = "";
			path = path.replaceAll(":", ",").replaceAll("\\|", ",");
//			Integer length = path.indexOf(",");
//			path = path.substring(length+1);
			List<LoreInfo> loreList = lm.listInfoInLoreId(path,"desc");
			if(loreList.size() > 0){
				for(LoreInfo lore : loreList){
					loreIdStr += lore.getId() + ":";
					loreNameStr += lore.getLoreName() + ":";
				}
				loreIdStr = loreIdStr.substring(0, loreIdStr.length() - 1);
				loreNameStr = loreNameStr.substring(0, loreNameStr.length() - 1);
			}
			pathArr[0] = loreIdStr;
			pathArr[1] = loreNameStr;
			return pathArr;
			//新版本--end
		}else{//学习
			return ltmj.getStudyPath(path,pathChi,"onlineStudy");
		}
	}
	
	/**
	 * 学习自助餐时获取关联知识点的全部路径(诊断/学习时)
	 * @author wm
	 * @date 2019-6-27 下午04:36:08
	 * @param buffetId 自助餐编号
	 * @param buffetName 自助餐名称
	 * @param loreId 知识点编号
	 * @param pathType diagnosis/study
	 * @return loreId组合,loreName组合
	 * @throws Exception
	 */
	public static String[] getBuffetLorePath(Integer buffetId,String buffetName,Integer loreId,String pathType) throws Exception{
		LoreInfoManager lm = (LoreInfoManager) AppFactory.instance(null).getApp(Constants.WEB_LORE_INFO);
		BuffetQueInfoManager bqm = (BuffetQueInfoManager) AppFactory.instance(null).getApp(Constants.WEB_BUFFET_QUE_INFO);
		String[] pathArr = new String[2];
		String path = "";
		LoreBuffetTreeMenuJson lbtmj = new LoreBuffetTreeMenuJson();
		List<MyTreeNode> ltList = lbtmj.showBuffetTree_2(buffetId, buffetName, loreId, 0);
		StringBuilder buff = new StringBuilder();
		StringBuilder buffChi = new StringBuilder();
		LoreTreeMenuJson ltmj = new LoreTreeMenuJson();
		ltmj.getPath(ltList, buff,buffChi);
		path = buff.delete(buff.length() - 1, buff.length()).toString();
		String pathChi = buffChi.delete(buffChi.length() - 1, buffChi.length()).toString();
		pathArr[0] = path;
		pathArr[1] = pathChi;
		if(pathType.equals("diagnosis")){//诊断
//			return pathArr;
			//新版本--start
			String loreIdStr = "";
			String loreNameStr = "";
			path = path.replaceAll(":", ",").replaceAll("\\|", ",");
			//去掉第一个--自助餐编号
			Integer length = path.indexOf(",");
			path = path.substring(length+1);
			List<LoreInfo> loreList = lm.listInfoInLoreId(path,"desc");
			BuffetQueInfo bq = bqm.getEntityById(buffetId);
			if(bq != null && loreList.size() > 0){
				loreIdStr += bq.getId() + ":";
				loreNameStr += bq.getTitle() + ":";
				for(LoreInfo lore : loreList){
					loreIdStr += lore.getId() + ":";
					loreNameStr += lore.getLoreName() + ":";
				}
				loreIdStr = loreIdStr.substring(0, loreIdStr.length() - 1);
				loreNameStr = loreNameStr.substring(0, loreNameStr.length() - 1);
			}
			pathArr[0] = loreIdStr;
			pathArr[1] = loreNameStr;
			return pathArr;
		}else{//学习
			return ltmj.getStudyPath(path,pathChi,"buffet");
		}
	}
	
	/**
	 * 对数组进行排序（数组必须是a-z或者A-Z）
	 * @author wm
	 * @date 2019-6-13 下午04:06:20
	 * @param array
	 * @return
	 */
	public static String arraySort(String[] array){
		String newSortStr = "";
		Arrays.sort(array);
		for(int i = 0 ; i <array.length ; i++){
			if(i < array.length - 1){
				newSortStr += array[i] + ",";
			}else{
				newSortStr += array[i];
			}
		}
		return newSortStr;
	}
	
	/**
	 * 计算自己到期日期和当前日期相差天数(免费学生不计算)
	 * @author wm
	 * @date 2019-8-23 上午10:15:10
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public static Integer getDiffDays(Integer userId) throws Exception{
		Integer diffDays = 0;
		UserManager um = (UserManager)AppFactory.instance(null).getApp(Constants.WEB_USER_INFO);
		//先判断有没有到期，通过用户编号获取endDate
		List<User> uList = um.listEntityById(userId);
		if(uList.size() > 0){
			User user = uList.get(0);
			Integer freeStatus= user.getFreeStatus();//0：收费，1：免费
			if(freeStatus.equals(0)){
				String myendDate = user.getEndDate();
				diffDays = CurrentTime.compareDate(CurrentTime.getStringDate(),myendDate);
			}else{//免费
				diffDays = 10000;
			}
		}
		return diffDays;
	}
	
	/**
	 * 获取购买会员费、导师绑定费的折扣费
	 * @author wm
	 * @date 2019-9-16 下午05:47:47
	 * @param selMonth
	 * @return
	 */
	public static Double getZkRate(Integer selMonth){
		Double zkRate = 1.0;
		if(selMonth.equals(1)){
			
		}else if(selMonth.equals(2)){
			zkRate = 0.99;
		}else if(selMonth.equals(3)){
			zkRate = 0.98;
		}else if(selMonth.equals(4)){
			zkRate = 0.97;
		}else if(selMonth.equals(5)){
			zkRate = 0.96;
		}else if(selMonth.equals(6)){
			zkRate = 0.95;
		}else if(selMonth.equals(7)){
			zkRate = 0.94;
		}else if(selMonth.equals(8)){
			zkRate = 0.93;
		}else if(selMonth.equals(9)){
			zkRate = 0.92;
		}else if(selMonth.equals(10)){
			zkRate = 0.91;
		}else if(selMonth.equals(11)){
			zkRate = 0.90;
		}else if(selMonth.equals(12)){
			zkRate = 0.85;
		}
		return zkRate;
	}
	
	/**
	 * 获取用户账号登录状态
	 * success:账号正常，loginOut：账号别处登录,sessionLose:超过3天未登陆,accountInvalid(账号无效)
	 * @author wm
	 * @date 2019-9-22 下午05:47:19
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static String checkUserLoginStatus(HttpServletRequest request,Integer userId_local,Integer loginStatus_local) throws Exception{
		//客户端信息
		String clientInfo = CommonTools.getCilentInfo_new(request);
		String result = "accountError";//用户账号状态--账号错误(默认)
		if(!clientInfo.equals("pc")){
			UserManager um = (UserManager)AppFactory.instance(null).getApp(Constants.WEB_USER_INFO);
			Integer login_status_dataBase = -1;
//			Integer userId_local = CommonTools.getFinalInteger("userId", request);
//			Integer loginStatus_local = CommonTools.getFinalInteger("loginStatus", request);
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
		}else{
			result = "success";
		}
		return result;
	}
	
	public static String getWebAddress(ServletRequest request){
		String xyType = request.getScheme();
		String ym = request.getServerName();
		Integer dkh = request.getServerPort();
		String dkhChi = dkh.equals(80) ? "" : ":" + dkh;
		return xyType + "://" +  ym + dkhChi;
	}
	
	public static void main(String[] args) throws Exception, FileNotFoundException{
//		System.out.println(System.currentTimeMillis());
//		Integer items[] = {1,2,3,4,5,11,12,21};
//		Integer[] need_del_items =  {2,11,4};
//	    List<Integer> list1=Arrays.asList(items);
//	    List<Integer> arrList = new ArrayList<Integer>(list1); //这句话的重要性在上一节blog中有讲到
//	    for(Integer c : need_del_items){
//	    	Integer b = c;
//	        arrList.remove(b);
//	    }
//	    arrList.toArray();
	    System.out.println(CurrentTime.getCurrentTime().substring(0,10));
	    List<String> list = new ArrayList<String>();
	    list.add("测试一");
	    list.add("测试二");
	    list.add("测试三");
	    list.add("测试四");
	    System.out.println(list);
	    
//	    String bb = "7389:7392:7394|7396|7390|7393:7397|7405:7406|7407:7431|7432:7433:7436|7446:7448";
//	    System.out.println(CommonTools.getCurrentStudyPath_new(bb, 7397));
//	    System.out.println(CommonTools.getSelfArea_taobao("123.52.203.75"));
//	    System.out.println(CurrentTime.convertTimestampToString_1(CurrentTime.getCurrentTime1()));
//		File file = new File("d:/new4.json");
//		InputStreamReader br = new InputStreamReader(new FileInputStream(file),"utf-8");//读取文件,同时指定编码
//		StringBuffer sb = new StringBuffer();
//        char[] ch = new char[128];  //一次读取128个字符
//        int len = 0;
//        while((len = br.read(ch,0, ch.length)) != -1){
//            sb.append(ch, 0, len);
//        }
//        String s = sb.toString();
//        if(!s.equals("")){
//        	JSONObject dataJson = JSON.parseObject(s); 
//            JSONArray features = dataJson.getJSONArray("areaList");// 找到features json数组
//            //第一级
//            for(int i = 0 ; i < features.size() ; i++){
//            	JSONArray features1 = features.getJSONObject(i).getJSONArray("children");
//                //第二级
//            	for(int j = 0 ; j < features1.size() ; j++){
//            		JSONArray features2 = features1.getJSONObject(j).getJSONArray("children");
//            		for(int k = 0 ; k < features2.size() ; k++){
//            			 //第三级
//            			JSONObject obj2 = features2.getJSONObject(k);
//            			String countyCode = obj2.getString("code");
//            			String countyName = obj2.getString("name");
//                        JSONArray features3 = obj2.getJSONArray("children");
//                        for(Integer num = 0 ; num < features3.size() ; num++){
//                        	JSONObject obj3 = features3.getJSONObject(num);
//                        	System.out.println(countyCode+"--"+ countyName +"--" + obj3.getString("code") + "   " + obj3.getString("name"));
//                        }
//            		}
//            	}
//            }
//        }
//		double aa = 7 * 10.0 / 9;
//		System.out.println(Convert.convertInputNumber_6(aa));
//		String realAnswer = "9厘米,1厘米";
//		String myAnswer = "A,B";
//		String answerOpt = "1厘米,,4厘米,9厘米";
//		String realAnswerChi = "";
//		String[] answerOptArr = answerOpt.split(",");
//		String[] realAnswerArr = realAnswer.split(",");
//		for(int i = 0 ; i < realAnswerArr.length ; i++){
//			for(int j = 0 ; j < answerOptArr.length ; j++)
//			if(realAnswerArr[i].equals(answerOptArr[j])){
//				realAnswerChi += Convert.NumberConvertBigChar(j)+",";
//				break;
//			}
//		}
//		if(realAnswerChi.length() > 0){
//			realAnswerChi = realAnswerChi.substring(0, realAnswerChi.length() - 1);
//		}
//		System.out.println(realAnswerChi);
		
//		String str = "\ud83d\ude04正常中文\ud83d\ude04";
//        System.out.println("原始字符为：\n" + str);
//
//        System.out.println("to html：");
//        System.out.println(EmojiParser.parseToHtmlDecimal(str));
//        System.out.println(EmojiParser.parseToHtmlDecimal(str, EmojiParser.FitzpatrickAction.PARSE));
//        System.out.println(EmojiParser.parseToHtmlDecimal(str, EmojiParser.FitzpatrickAction.REMOVE));
//        System.out.println(EmojiParser.parseToHtmlDecimal(str, EmojiParser.FitzpatrickAction.IGNORE));
//
//        System.out.println("to html(hex)：");
//        System.out.println(EmojiParser.parseToHtmlHexadecimal(str));
//        System.out.println(EmojiParser.parseToUnicode(EmojiParser.parseToHtmlDecimal(str)));
	}
}
