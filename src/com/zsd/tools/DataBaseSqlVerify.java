package com.zsd.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataBaseSqlVerify {
	
	/**
	 * 防止用户输入注入sql账号
	 * @description
	 * @author wm
	 * @date 2016-4-30 上午08:54:59
	 * @param sqlStr
	 * @return
	 */
	public static boolean checkSql(String sqlStr){
		boolean flag = false;
		if(sqlStr == null || sqlStr.equals("")){
			flag = false;
		}else{
			String inj_str = "insert|select|delete|update|master|truncate|declare";
			String inj_str_1 = "INSERT|SELECT|DELETE|UPDATE|MASTER|TRUNCATE|DECLARE";
			String[] sqlArray = inj_str.split("\\|");
			String[] sqlArray_1 = inj_str_1.split("\\|");
		    for (int i=0 ; i < sqlArray.length ; i++ ){
		        if (sqlStr.indexOf(sqlArray[i]) >= 0 || sqlStr.indexOf(sqlArray_1[i]) >= 0){
		            flag = true;
		            break;
		        }else{
		        	flag =  false;
		        }
		    }
		}
		return flag;
	}
	
	/**
	 * 防止xss攻击
	 * @description
	 * @author wm
	 * @date 2016-4-30 上午08:55:31
	 * @param src
	 * @return
	 */
	public static boolean checkXss(String src){
		boolean flag = false;
		if(src == null || src.equals("")){
			flag = false;
		}else{
			String temp =src;  
			  
	        src = src.replaceAll("<", "<").replaceAll(">", ">");  
	        src = src.replaceAll("\\(", "(").replaceAll("\\)", ")");  

	        src = src.replaceAll("'", "'");  
	          
	        Pattern pattern=Pattern.compile("(eval\\((.*)\\)|script)",Pattern.CASE_INSENSITIVE);     
	        Matcher matcher=pattern.matcher(src);     
	        src = matcher.replaceAll("");  
	  
	        pattern=Pattern.compile("[\\\"\\'][\\s]*javascript:(.*)[\\\"\\']",Pattern.CASE_INSENSITIVE);   
	        matcher=pattern.matcher(src);  
	        src = matcher.replaceAll("\"\"");  
	          
	        //增加脚本   
	        src = src.replaceAll("script", "").replaceAll(";", "")  
	            .replaceAll("\"", "")  
	            .replaceAll("0x0d", "")  
	            .replaceAll("0x0a", "");  
	  
	        if(!temp.equals(src)){  
//	            System.out.println("输入信息存在xss攻击�?);  
//	            System.out.println("原始输入信息-->"+temp);  
//	            System.out.println("处理后信�?->"+src);  
	            flag = true;
	        }  
		}

		return flag;
	}
	public static void main(String args[]){
		String aa = "abcf,2015-01-01||2016-01-02,0";
		String[] bb = aa.split(",");
		
		System.out.println(bb[1].split("\\|\\|")[0]);
	}
}
