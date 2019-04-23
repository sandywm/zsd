package com.zsd.tools;

import java.security.MessageDigest;

public class MD5{
  /** 
   * 获得MD5加密密码的方�?
   */  
  public String calcMD5(String origString) {  
      String origMD5 = null;  
      try {  
          MessageDigest md5 = MessageDigest.getInstance("MD5");  
          byte[] result = md5.digest(origString.getBytes());  
          origMD5 = byteArray2HexStr(result);  
      } catch (Exception e) {  
          e.printStackTrace();  
      }  
      return origMD5;  
  }  
  /** 
   * 处理字节数组得到MD5密码的方�?
   */  
  private static String byteArray2HexStr(byte[] bs) {  
      StringBuffer sb = new StringBuffer();  
      for (byte b : bs) {  
          sb.append(byte2HexStr(b));  
      }  
      return sb.toString();  
  }  
  /** 
   * 字节标准移位转十六进制方�?
   */  
  private static String byte2HexStr(byte b) {  
      String hexStr = null;  
      int n = b;  
      if (n < 0) {  
          //若需要自定义加密,请修改这个移位算法即�? 
          n = b & 0x7F + 128;  
      }  
      hexStr = Integer.toHexString(n / 16) + Integer.toHexString(n % 16);  
      return hexStr.toUpperCase();  
  }  
  /** 
   * 提供�?��MD5多次加密方法 
   */  
  public String calcMD5(String origString, int times) {  
	  MD5 md5 = new MD5();
      String md5Str = md5.calcMD5(origString);  
      for (int i = 0; i < times - 1; i++) {  
    	  md5Str = md5.calcMD5(md5Str);  
      }  
      return md5Str;  
  }  

  /** 
   * 重载�?��多次(也可�?�?加密时的密码验证方法 
   */  
  public static boolean verifyPassword(String inputStr, String MD5Code, int times) {  
	  MD5 md5 = new MD5();
	  if(times == 0){
		  return md5.calcMD5(inputStr).equalsIgnoreCase(MD5Code);  
	  }else{
		  return md5.calcMD5(inputStr, times).equalsIgnoreCase(MD5Code);  
	  }
  }
  

  public static void main(String args[]){
	  MD5 md5 = new MD5();
	  System.out.println(md5.calcMD5("519421"));
	  //System.out.println(verifyPassword("123456wmk","E84C77D2584C289EA93175460D7DC1F6",0));
  }
}
