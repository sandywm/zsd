/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.zsd.action.user;

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
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.zsd.tools.CheckImage;
import com.zsd.tools.CommonTools;
import com.zsd.tools.CurrentTime;
import com.zsd.tools.FileOpration;
import com.zsd.util.WebUrl;

/** 
 * MyEclipse Struts
 * Creation date: 06-03-2019
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class UploadAction extends DispatchAction {
		
		/**
		 * 上传图片
		 * @author wm
		 * @date 2019-9-10 下午04:46:51
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 */
		public ActionForward upImg(ActionMapping mapping,ActionForm form,
				HttpServletRequest request,HttpServletResponse response)throws Exception{
			String opt = CommonTools.getFinalStr("opt", request);//不传默认为上传头像用,queImg时为学生提问，老师答疑时上传图像用
			Map<String,Object> map = new HashMap<String,Object>();
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(2048*1024);
			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			List<FileItem> filelist = fileUpload.parseRequest(request);
			ListIterator<FileItem> iterator = filelist.listIterator();
			String userPath = WebUrl.DATA_URL;
			Integer userId = CommonTools.getLoginUserId(request);
			if(opt.equals("queImg")){
				userPath = WebUrl.DATA_URL_QUE_FILE_UPLOAD;
			}
			boolean upFlag = false;
			String msg ="";
			String fileUrl="";
			while(iterator.hasNext()){
				FileItem item = (FileItem)iterator.next();
				// 处理文件上传
				String filename = item.getName();// 获取名字
				if(filename == null){
					continue;
				}
				Integer lastIndex = filename.lastIndexOf(".");
				String suffix = filename.substring(lastIndex+1);
//				String filePre = filename.substring(0, lastIndex);
				String fileNamePre = CurrentTime.getRadomTime() + "_" + userId;
				filename = fileNamePre + "." + suffix;
//				filename = filePre + "_" + CurrentTime.getRadomTime() + "." + suffix;
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
					msg = "success";
					if(opt.equals("queImg")){
						//获取缩略图
						String formatName = "JPEG";
						if(suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("jpeg")){
							formatName = "JPEG";
						}else if(suffix.equalsIgnoreCase("png")){
							formatName = "PNG";
						}else if(suffix.equalsIgnoreCase("bmp")){
							formatName = "BMP";
						}else if(suffix.equalsIgnoreCase("gif")){
							formatName = "GIF";
						}
						String smallImgPath = userPath  + "/" + fileNamePre + "_small." + suffix;
						FileOpration.makeImage(userPath  + "/" + filename, 0.3, smallImgPath, formatName);
						fileUrl +=  WebUrl.NEW_DATA_URL_QUE_FILE_UPLOAD  + fileNamePre + "_small." + suffix + ",";
					}else{
						fileUrl +=  WebUrl.NEW_DATA_URL + filename + ",";
					}
					fileOutputStream.close();// 关闭文件流
				}
			}
			if(!fileUrl.equals("")){
				fileUrl = fileUrl.substring(0,fileUrl.length() - 1);
			}
			map.put("result", msg);
			map.put("imgSrc",fileUrl);
			CommonTools.getJsonPkg(map, response);
			return null;
		}
		
		/**
		 * 上传网络导师身份证、资格证、学历证用
		 * @author wm
		 * @date 2019-9-22 下午05:10:39
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 * @throws Exception
		 */
		public ActionForward upCert(ActionMapping mapping,ActionForm form,
				HttpServletRequest request,HttpServletResponse response)throws Exception{
			Integer userId = CommonTools.getLoginUserId(request);
			Integer loginStatus_local = CommonTools.getFinalInteger("loginStatus", request);
			String checkLoginStatus = CommonTools.checkUserLoginStatus(request,userId,loginStatus_local);
			Map<String,Object> map = new HashMap<String,Object>();
			if(checkLoginStatus.equals("success")){
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(2048*1024);
				ServletFileUpload fileUpload = new ServletFileUpload(factory);
				List<FileItem> filelist = fileUpload.parseRequest(request);
				ListIterator<FileItem> iterator = filelist.listIterator();
				String userPath = WebUrl.PERSONAL_HONOR;
				String smallUrl = "";
				boolean upFlag = false;
				String msg = "error";
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
//						fileUrl +=  WebUrl.NEW_PERSONAL_HONOR  + "\\" + filename ;
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
			}else{
				map.put("result", checkLoginStatus);
			}
			CommonTools.getJsonPkg(map, response);
			return null;
		}
}