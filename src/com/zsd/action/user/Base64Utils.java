package com.zsd.action.user;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.zsd.tools.CurrentTime;
import com.zsd.util.WebUrl;
import sun.misc.BASE64Decoder;

public class Base64Utils   {
	  /**
	   * 对字节数组字符串进行Base64解码并生成图片
	   * @param base64
	   * @param userId
	   * @return
	   */
	public static String Base64ToImage(String base64,Integer userId) { 
		String fileUrl="";
		 
		try {
		  String[] baseStrs = base64.split(",");
		  String fileName = CurrentTime.getRadomTime()+"."+getSuffix(baseStrs[0]);
		  BASE64Decoder decoder = new BASE64Decoder();
		  String imgFilePath = WebUrl.DATA_URL+"\\"+userId;
			// Base64解码
			byte[] b = decoder.decodeBuffer(baseStrs[1]);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			//没有该文件夹先创建文件夹
    		File file = new File(imgFilePath);
    		if(!file.exists()){
    			file.mkdirs();
    		}
			OutputStream out = new FileOutputStream(imgFilePath+"/"+fileName);
			out.write(b);
			out.flush();
			out.close();
			fileUrl = WebUrl.NEW_DATA_URL  + userId+"/" + fileName ;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileUrl;
	}

	private static String getSuffix(String baseStrs) {
		String suffix = baseStrs.split(";")[0].split("/")[1];
		return suffix;
	}


}

