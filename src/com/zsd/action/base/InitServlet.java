package com.zsd.action.base;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.zsd.util.WebUrl;

public class InitServlet extends HttpServlet
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InitServlet()
    {
    }

    public void init()throws ServletException{
    	WebUrl.DATA_URL_PRO = getServletContext().getRealPath("/");
    	
    	WebUrl.DATA_URL_WEB_INFO = getServletContext().getRealPath("/WEB-INF/");
    	
    	WebUrl.DATA_URL_YYZZ_UPLOAD = getServletContext().getRealPath("/Module/uploadFile/yyzz");
    	
    	WebUrl.LOG_URL = getServletContext().getRealPath("/Module/sendLog");
    	
    	WebUrl.DATA_URL_UP_FILE_UPLOAD = getServletContext().getRealPath("/Module/uploadFile");
    	
    	WebUrl.NEW_DATA_URL_UP_FILE_UPLOAD = "Module/uploadFile/";
    	
        WebUrl.PERSONAL_HONOR = getServletContext().getRealPath("/Module/uploadFile/cert");
        
        WebUrl.NEW_PERSONAL_HONOR = "Module/uploadFile/cert/";
    	
    	WebUrl.DIAGNOSIS_DATA_URL = getServletContext().getRealPath("/Module/commonJs/ueditor/jsp/video");
    	
        WebUrl.NEW_DIAGNOSIS_DATA_URL = "Module/commonJs/ueditor/jsp/video/";
        
        WebUrl.NEW_LORE_DATA_URL = "Module/commonJs/ueditor/jsp/lore/";
        
        WebUrl.LORE_DATA_URL = getServletContext().getRealPath("Module/commonJs/ueditor/jsp/lore/");
        
        WebUrl.DATA_URL = getServletContext().getRealPath("Module/commonJs/ueditor/jsp/head/");
        
        WebUrl.NEW_DATA_URL = "Module/commonJs/ueditor/jsp/head/";
        
        WebUrl.DATA_URL_QUE_FILE_UPLOAD = getServletContext().getRealPath("/Module/uploadFile/queFile");
        
        WebUrl.NEW_DATA_URL_QUE_FILE_UPLOAD = "/Module/uploadFile/queFile/";
    }
}
