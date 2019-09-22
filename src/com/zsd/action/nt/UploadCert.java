/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.zsd.action.nt;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zsd.tools.CheckImage;
import com.zsd.tools.CommonTools;
import com.zsd.tools.CurrentTime;
import com.zsd.tools.FileOpration;
import com.zsd.util.WebUrl;

/** 
 * MyEclipse Struts
 * Creation date: 05-15-2019
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class UploadCert extends Action {
	
	/** 
	 * 网络导师个人中心图片上传
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(2048*1024);
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		List<FileItem> filelist = fileUpload.parseRequest(request);
		ListIterator<FileItem> iterator = filelist.listIterator();
		String userPath = WebUrl.PERSONAL_HONOR;
		String smallUrl = "";
		boolean upFlag = false;
		String msg = "error";
		String fileUrl="";
		while(iterator.hasNext()){
			FileItem item = (FileItem)iterator.next();
			// 处理文件上传
			String filename = item.getName();// 获取名字
			Integer lastIndex = filename.lastIndexOf(".");
			String suffix = filename.substring(lastIndex+1);
			String fileNamePre = CurrentTime.getRadomTime();
			filename = fileNamePre + "." + suffix;
			CheckImage ci = new CheckImage();
			//doc,docx,wps,xls,xlsx,txt,pdf,pptx,ppt,zip,rar,dwg,eml,jpg,png,bmp,gif,vsd,vsdx如果文件格式不在上述范围内请压缩成zip格式后上传
			String checkFileSuffixInfo = ci.getUpFileStuffix(suffix);
			if(checkFileSuffixInfo.equals("img")){//图片限制5M
				upFlag = ci.checkItemSize(item, 5 * 1024 * 1024);
				if(!upFlag){
					msg = "outSize";
				}
			}else{
				msg = "suffixError";
			}
			if(upFlag){
				byte[] data = item.get();// 获取数据
				//没有该文件夹先创建文件夹
	    		File file = new File(userPath);
	    		if(!file.exists()){
	    			file.mkdirs();
	    		}
	    		FileOutputStream fileOutputStream = new FileOutputStream(userPath + "/" + filename);
				fileOutputStream.write(data);// 写入文件
				fileOutputStream.close();// 关闭文件流
				msg = "success";
//				fileUrl +=  WebUrl.NEW_PERSONAL_HONOR  + "\\" + filename ;
				//生成小图
				String smallImgPath = userPath  + "/" + fileNamePre + "_small." + suffix;
				FileOpration.makeImage(userPath  + "/" + filename, 0.3, smallImgPath, suffix.toUpperCase());
				smallUrl = WebUrl.NEW_PERSONAL_HONOR + "\\" + fileNamePre + "_small." + suffix;
			}
		}
		map.put("result", msg);
		if(msg.equals("success")){
			map.put("smallUrl", smallUrl);
		}
		CommonTools.getJsonPkg(map, response);
		return null;
	}
}