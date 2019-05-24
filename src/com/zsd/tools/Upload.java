package com.zsd.tools;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.fileupload.FileItem;
import org.apache.struts.upload.FormFile;

public class Upload
{
	private String url;

	private String fileName;
    public Upload()
    {
    }
    
    /**
     * 手机移动端上传
     * @description
     * @author wm
     * @date 2016-8-22 下午05:30:47
     * @param path 工作路径
     * @param firstName 文件名
     * @param suffix 文件类型
     * @param userIdStr 用户编号
     * @return 成功返回succ
     */
    public String uploadApp(String path,String firstName,String suffix,String userIdStr){
    	String url = null;
    	 FileOutputStream fos = null;
         FileInputStream fis = null;
        try{
            //以账号+时间格式生成文件名
            url = makeUrl(path, suffix);
            //用输出流保存文件
            File file = new File(url);
            fos = new FileOutputStream(file);
            fis = new FileInputStream(file);
            //写入时间
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            System.out.println("文件上传成功");
            fos.close();
        	fis.close();
        	return "succ";
        }catch(Exception e){
            e.printStackTrace();
        }
		return "fail";
    }
    
    /**
     * 上传文件方法
     * @param file 选定文件的实体
     * @param path  服务器的绝对路径
     * @param firstName 传递的用户账号
     * @param suffix 传入文件的后缀
     * @return
     */
    public String upload(FormFile file, String path, String firstName, String suffix)
    {
        String url = null;
        FileOutputStream fileOutput;
        try{
            //以账号+时间格式生成文件名
            url = makeUrl(path, suffix);
            this.url = url;
            //用输出流保存文件
            fileOutput = new FileOutputStream(url);
            //写入时间
            fileOutput.write(file.getFileData());
			fileOutput.flush();
			fileOutput.close();
            
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        return fileName + suffix;
    }
    /**
     * 上传文件方法【FileItem】
     * @param fileItem 文件项
       * @param path  服务器的绝对路径
     * @param firstName 传递的用户账号
     * @param suffix 传入文件的后缀
     * @return 新生产的文件名
     */
    public String uploadItem(FileItem fileItem ,String path, String suffix){
    	String filePath = null;
    	//以账号+时间格式生成文件名
        filePath = makeUrl(path, suffix);
        File file = new File(filePath);
        String url = "";
        //写入
        try {
			fileItem.write(file);
			url =  fileName + suffix;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
    }
    
    private String getRadom(){
    	int radom = (int) (Math.random()*(999999-100000)+100000);
		String radomStr = String.valueOf(radom);
		return radomStr;
    }
    private String makeUrl(String path, String suffix)
    {
        this.fileName = CurrentTime.getStringTime() +this.getRadom();
        return path + "\\" + this.fileName + suffix;
    }

    public String makeNewUrl(String path, String suffix, String other)
    {
        return path + "\\" + this.fileName + other + suffix;
    }
    
    public String makeImgUrl(String suffix, String other)
    {
        return this.fileName + other + suffix;
    }
    //返回上传后文件的绝对URL
    public String getUrl()
    {
        return url;
    }

    public String getFileName()
    {
        return fileName;
    }
    
    /**
     * 用于生成上传完后的图片的副本
     * @param url 原图的绝对URL
     * @param newWidth 生成副本的新宽度
     * @param newHeight 生成副本的新高度
     * @param newUrl 生成副本的地址
     * @param formatName 生成图片的格式
     * @throws Exception
     */
    public String makeImage(String url, int newWidth, int newHeight, String newUrl, String formatName)
        throws Exception{
    	//读取图片
        BufferedImage bi = ImageIO.read(new File(url));
        //用Image里的方法对图片进行等比压缩,只要宽和高其一值为负,则以正的那个值为最大边进行等比压缩
        Image image = bi.getScaledInstance(newWidth, newHeight,Image.SCALE_AREA_AVERAGING);
        int height = image.getHeight(null);
        int width = image.getWidth(null);
        //以新的高和宽构造一个新的缓存图片
        BufferedImage bi1 = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
        Graphics g = bi1.getGraphics();
        //在新的缓存图片中画图
        g.drawImage(image, 0, 0, null);
        //构造IO流输出到文件
        FileOutputStream fos = new FileOutputStream(new File(newUrl));
        ImageIO.write(bi1, formatName, fos);
        fos.flush();
        fos.close();
        return this.fileName;
    }
    
    /** 该方法是删除图片
	  * @param url 删除图片的绝对地址
	  * @param bigUrl 数据库中大图片的URL
	  * @param smallUrl 数据库小图片的URL
	  * @param realPath 服务器中的绝对路径
	  */
    public void deleteImage(String url, String bigUrl, String smallUrl, String realPath){
    	//构造三个文件对象,调用方法把所有对应的图片删除
		File fUrl = new File(realPath + "/" + url);
		File fBigUrl = new File(realPath + "/" + bigUrl);
		File fSmallUrl = new File(realPath + "/" + smallUrl);
		fUrl.delete();
		fBigUrl.delete();
		fSmallUrl.delete();
    }
}
