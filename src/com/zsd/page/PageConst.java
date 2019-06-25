package com.zsd.page;



public class PageConst
{

    public PageConst()
    {
    }

    public static int getPageNo(Integer pageNo, int pageCount){
    	if(pageNo.equals(0)){
    		pageNo = 1;
    	}else if(pageNo > pageCount)
            pageNo = pageCount;
        return pageNo;
    }
    
    public static int getPageCount(int count, int pageSize){
		return (count + pageSize - 1) / pageSize;
	}
    
    /**
     * 获取每页记录条数
     * @description
     * @author wm
     * @date 2017-6-1 上午11:00:02
     * @param pageSizeStr 每页记录条数
     * @param pageSizeInit 当不存在每页记录条数时初始赋值
     * @return
     */
    public static int getPageSize(String pageSizeStr,Integer pageSizeInit){
    	Integer pageSize = 10;
    	if(pageSizeStr.equals("") || pageSizeStr.equals("null")){
    		pageSize = pageSizeInit;
    	}else{
    		pageSize = Integer.parseInt(pageSizeStr.trim());
    	}
    	return pageSize;
    }
}
