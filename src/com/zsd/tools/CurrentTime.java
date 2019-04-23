package com.zsd.tools;

import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CurrentTime {
	/**
	 * 获取当前日期（格式yyyy-MM-dd HH:mm:ss�?
	 * @description
	 * @author wm
	 * @date 2015-10-17 上午10:49:02
	 * @return
	 */
	public static  String getCurrentTime(){
		return getFormat("yyyy-MM-dd HH:mm:ss");
	}
	//返回字符串作为文件名
	public static  String getStringTime(){
		return getFormat("yyyyMMddHHmmss");
	}
	public static String getStringTime1(){
		return getFormat("yyyyMMddHHmmssSSS");
	}
	/**
	 * 获取当前日期（格式yyyy-MM-dd�?
	 * @description
	 * @author wm
	 * @date 2015-10-17 上午10:49:24
	 * @return
	 */
	public static String getStringDate(){
		return getFormat("yyyy-MM-dd");
	}
	public static String getYear(){
		return getFormat("yyyy");
	}
	public static String getMonth(){
		return getFormat("MM");
	}
	public static String getSimpleTime(){
		return getFormat("yyyyMMdd");
	}
	//数字左补0
	public static String getNumberFormat(double d){
		NumberFormat nf = NumberFormat.getInstance();
		// 设置是否使用分组
		nf.setGroupingUsed(false);
		// 设置�?��整数位数
		nf.setMaximumIntegerDigits(12);
		// 设置�?��整数位数
		nf.setMinimumIntegerDigits(12);
		return nf.format(d);
	}
	public static Timestamp getCurrentTime1(){
		java.util.Date date=new java.util.Date();
		Timestamp time = new Timestamp(date.getTime());
		return time;
	}
	//timstamp转换成string
	public static String convertTimestampToString(Timestamp ts){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String tsStr = "";
		try{
	        tsStr = formatter.format(ts);   
	    } catch (Exception e) {   
	        e.printStackTrace();   
	    }  
	    return tsStr;
	}
	/**
	 * timstamp转换成string(yyyy-MM-dd)
	 * @description
	 * @author wm
	 * @date 2015-10-17 上午09:22:14
	 * @param ts
	 * @return
	 */
	public static String convertTimestampToString_1(Timestamp ts){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		String tsStr = "";
		try{
	        tsStr = formatter.format(ts);   
	    } catch (Exception e) {   
	        e.printStackTrace();   
	    }  
	    return tsStr;
	}
	//string转换成timestamp
	public static Timestamp stringConvertToTimestamp(String value){
		Timestamp ts = null;
		try {  
            ts = Timestamp.valueOf(value);   
        } catch (Exception e) {  
            e.printStackTrace();  
        }
		return ts;  
	}
	/**
	 * string转换成date(java.sql.Date)
	 * @description
	 * @author wm
	 * @date 2018-6-4 上午08:25:52
	 * @param value
	 * @return
	 */
	public static java.sql.Date stringToDate(String value){
		java.util.Date d = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		try {  
            d = formatter.parse(value);
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        java.sql.Date date = new java.sql.Date(d.getTime());
        return date;
	}
	
	/**
	 * string转换成date（java.util.Date�?
	 * @description
	 * @author wm
	 * @date 2018-7-25 上午09:12:58
	 * @param value
	 * @return
	 */
	public static Date stringToDate_1(String value){
		java.util.Date d = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		try {  
            d = formatter.parse(value);
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        Date date = new Date(d.getTime());
        return date;
	}
	
	/**
	 * date转换成string
	 * @description
	 * @author wm
	 * @date 2018-6-13 下午03:40:37
	 * @param date
	 * @return
	 */
	public static String dateConvertToString(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		String tsStr = "";
		if(date != null){
			try{
		        tsStr = formatter.format(date);   
		    } catch (Exception e) {   
		        e.printStackTrace();   
		    }  
		}
	    return tsStr;
	}
	
	//系统日期+天数
	public static String getFinalDate(int days){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DAY_OF_YEAR, day + days);
        Date date = calendar.getTime();
        return formatter.format(date);
	}
	//系统日期+天数
	public static String getFinalDateTime(int days){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DAY_OF_YEAR, day + days);
        Date date = calendar.getTime();
        return formatter.format(date);
	}
	//工具方法
	private static String getFormat(String format)
	{
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(currentTime);
	}
	
	/**
	 * 指定日期+/-天数
	 * @description
	 * @author wm
	 * @date 2018-9-12 上午11:30:01
	 * @param specifiedDate
	 * @param days
	 * @return
	 */
	public static String getFinalDate(String specifiedDate,int days){
		String finalDate = "";
        try {
        	Calendar calendar = Calendar.getInstance();
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDate);
			calendar.setTime(date);
			int day = calendar.get(Calendar.DAY_OF_YEAR);
			calendar.set(Calendar.DAY_OF_YEAR, day + days);
			finalDate =  new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return finalDate;
	}
	
	/**
	 * 指定日期加月�?
	 * @description
	 * @author Administrator
	 * @date 2018-10-12 上午10:08:22
	 * @param specifiedDate
	 * @param month
	 * @return
	 */
	public static String getFinalDate_1(String specifiedDate,int addMonthes){
		String finalDate = "";
		Calendar calendar = Calendar.getInstance();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(specifiedDate);
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, addMonthes);
			finalDate =  sdf.format(calendar.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return finalDate;
	}
	
	/**
	 * 指定日期+年数
	 * @description
	 * @author Administrator
	 * @date 2018-10-25 上午09:13:28
	 * @param specifiedDate
	 * @param addMonthes
	 * @return
	 */
	public static String getFinalDate_2(String specifiedDate,int addYears){
		String finalDate = "";
		Calendar calendar = Calendar.getInstance();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(specifiedDate);
			calendar.setTime(date);
			calendar.add(Calendar.YEAR, addYears);
			finalDate =  sdf.format(calendar.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return finalDate;
	}
	
	/**
	 * 日期相减相差天数
	 * @description
	 * @author wm
	 * @date 2016-8-4 下午02:12:56
	 * @param oldDate
	 * @param newDate
	 * @return
	 */
	public static int compareDate(String oldDate,String newDate){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date benginDate;
		Date currentDate;
		int diffDays = 0;
		try {
			benginDate = formatter.parse(oldDate);
			currentDate = formatter.parse(newDate);
			long difference = currentDate.getTime() - benginDate.getTime();
	  		diffDays =  Math.round(difference / (1000 * 60 * 60 * 24));
	  		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return diffDays;
	}
	//日期相减相差毫秒�?
	public static long compareDateTime(String currentTime,String addTime){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date addDate;
		Date currentDate;
		long difference = 0;
		try {
			addDate = formatter.parse(addTime);
			currentDate = formatter.parse(currentTime);
			difference = currentDate.getTime() - addDate.getTime();
	  		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return difference;
	}
	//日期相减相差�?月数
	public static int comparaDate(String currentDate,String oldDate,String dateType){
		int result = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar bef = Calendar.getInstance();		
		Calendar aft = Calendar.getInstance();
		try {
			bef.setTime(sdf.parse(oldDate));
			aft.setTime(sdf.parse(currentDate));
			if(dateType.equals("year")){
				result = aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR);	
			}else{
				result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return result;
	}
    

    /**
     * 本月的第�?��
     * @param sYear 年份
     * @param sMonth 月份
     * @return
     */
    public static  String getFirstDayofMonth(int sYear, int sMonth) {
    	 Calendar c = Calendar.getInstance();
        String tStartdate = "";
        c.set(c.YEAR, sYear);
        c.set(c.MONTH, sMonth);
        tStartdate = String.valueOf(sYear) + "-" + String.valueOf(sMonth) + "-" + c.getActualMinimum(c.DAY_OF_MONTH);
        return tStartdate;
    }

    /**
     * 本月的最后一�?
     * @param sYear 年份
     * @param sMonth 月份
     * @return
     */
    public static String getEndDayofMonth(int sYear, int sMonth) {
    	Calendar c = Calendar.getInstance();
        String tEnddate = "";
        c.set(c.YEAR, sYear);
        c.set(c.MONTH, sMonth);
        tEnddate = String.valueOf(sYear) + "-" + String.valueOf(sMonth) + "-" + c.getActualMaximum(c.DAY_OF_MONTH);
        return tEnddate;
    }

    public static String getNewTime(int i){  

    	String newDateTime = null;
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        GregorianCalendar cal = new GregorianCalendar();  
        Calendar calendar = Calendar.getInstance();

        Date date = calendar.getTime();

        cal.setTime(date);  

        cal.add(12, i);  

        
        newDateTime = formatter.format(cal.getTime());

        return newDateTime;  

    }  
    /**
     * 
     * @description 获取上个月的第一�?
     * @author zong
     * @date 2015-8-27 上午10:13:17
     * @return 返回上个月的第一�?
     */
    public static String getMonthOrOne(){
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
	    return format.format(calendar.getTime());
    }
    /**
     * @description 获取上一个月的最后一�?
     * @author zong
     * @date 2015-8-27 下午04:08:50
     * @return返回上个月的�?���?��
     */
    public static String getMonthOrLast(){
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1); 
		calendar.add(Calendar.DATE, -1);
	    return format.format(calendar.getTime());
    }
    /**
     * @author zong
     * @return 本月的第�?��
     */
    public static String getBeginDate(){
        Calendar begin = Calendar.getInstance(); 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        begin.set(Calendar.DAY_OF_MONTH, begin.getActualMinimum(Calendar.DAY_OF_MONTH));
        begin.set(Calendar.SECOND, 0);
        begin.set(Calendar.HOUR_OF_DAY, 0);
        begin.set(Calendar.MINUTE, 0);
        
        return sdf.format(begin.getTime());
    }
    /**
     * @author zong
     * @return 本月的最后一�?
     */
    public static String getEndDate(){
        Calendar end = Calendar.getInstance(); 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        end.set(Calendar.DAY_OF_MONTH, end.getActualMaximum(Calendar.DAY_OF_MONTH));
        end.set(Calendar.SECOND, 59);
        end.set(Calendar.HOUR_OF_DAY, 23);
        end.set(Calendar.MINUTE, 59);
        
        return sdf.format(end.getTime());
    }
    
    /**
     * 获取本月的第�?��
     * @description
     * @author wm
     * @date 2018-7-13 上午10:42:40
     * @return
     */
    public static String getBeginDate_1(){
        Calendar begin = Calendar.getInstance(); 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        begin.set(Calendar.DAY_OF_MONTH, begin.getActualMinimum(Calendar.DAY_OF_MONTH));
        begin.set(Calendar.SECOND, 0);
        begin.set(Calendar.HOUR_OF_DAY, 0);
        begin.set(Calendar.MINUTE, 0);
        
        return sdf.format(begin.getTime());
    }
    
    /**
     * 获取本月的最后一�?
     * @description
     * @author wm
     * @date 2018-7-13 上午10:43:06
     * @return
     */
    public static String getEndDate_1(){
        Calendar end = Calendar.getInstance(); 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        end.set(Calendar.DAY_OF_MONTH, end.getActualMaximum(Calendar.DAY_OF_MONTH));
        end.set(Calendar.SECOND, 59);
        end.set(Calendar.HOUR_OF_DAY, 23);
        end.set(Calendar.MINUTE, 59);
        
        return sdf.format(end.getTime());
    }
    
    /**
     * 根据指定日期获取每隔3个月的时�?
     * 2015-12-11�?016-03-10:2016-03-11�?016-06-10:2016-06-11�?016-09-10:2016-09-11�?016-12-10
     * @description
     * @author wm
     * @date 2015-12-11 下午04:59:15
     * @return
     * @throws Exception
     */
    public static String getStepSpecialDate(String spec) throws Exception{
    	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		String dateArray = "";
		Date now = format1.parse(spec);
		calendar.setTime(now);
		for(int i = 1; i < 5 ; i++){
			if(i == 1){
				calendar.add(Calendar.MONTH, 3);
				calendar.add(Calendar.DAY_OF_YEAR,-1);
				String dateStr2 = format1.format(calendar.getTime());
				dateArray = spec + "~"+ dateStr2;
			}else{
				calendar.add(Calendar.DAY_OF_YEAR,+1);
				String dateStr3 = format1.format(calendar.getTime());
				calendar.add(Calendar.MONTH, 3);
				calendar.add(Calendar.DAY_OF_YEAR,-1);
				String dateStr4 = format1.format(calendar.getTime());
				dateArray += ":"+dateStr3 + "~"+ dateStr4;
			}
		}
		return dateArray;
    }
    
    /**
     * 获取随机数（�?�?毫秒�?
     * @description
     * @author Administrator
     * @date 2018-10-9 下午04:42:50
     * @return
     */
    public static String getRadomTime(){
    	Integer max = 100,min = 10;
    	String radomStr = String.valueOf(Math.round(Math.random()*(max-min)+min));//两位随机�?
    	return getFormat("mm")+getFormat("ss")+getFormat("SSS")+radomStr;
    }
    
    /**
     * �?#######日期转换�?###-##-##
     * @description
     * @author Administrator
     * @date 2018-9-22 上午09:56:01
     * @param specStr
     * @return
     */
    public static String convertFormatDate(String specStr){
    	if(!specStr.equals("") && specStr.length() == 8){//########
    		return specStr.substring(0, 4) + "-" + specStr.substring(4, 6) + "-" + specStr.substring(6, 8);
    	}
    	return "";
    }
    
	public static void main(String args[]) throws Exception{
//		System.out.println(CurrentTime.compareDate("2014-01-30", "2014-02-01"));
//		float outPenalty = Float.parseFloat("0.1");
//		int differenceDays = -3;
//		if(differenceDays < 0){
//			differenceDays = 0;
//		}
//		float fine = outPenalty * differenceDays;
//		String fineStr = String.format("%2.2f", fine);
//		System.out.println(fineStr);
		
		
//		Random random = new Random();
//		int[] array = {0,1,2,3,4,5,6,7,8,9}; 
//		for (int i = 10; i > 1; i--){
//			int index = random.nextInt(i);     
//			int tmp = array[index];     
//			array[index] = array[i - 1];    
//			array[i - 1] = tmp; 
//		}
//		int result = 0; 
//		for(int i = 0; i < 6; i++)     
//			result = result * 10 + array[i]; 
//		System.out.println(result);
//		System.out.println(CurrentTime.getNewTime(7));

//		String newDate = CurrentTime.convertTimestampToString(CurrentTime.getCurrentTime1());
//		System.out.println(newDate);
//		System.out.println(CurrentTime.compareDateTime(CurrentTime.getCurrentTime(),newDate));
//		System.out.println(CurrentTime.getEndDate());
		//System.out.println(CurrentTime.comparaDate(CurrentTime.getCurrentTime(), "2012-7-2 00:00:00","month"));
		
		//System.out.println(Integer.parseInt(CurrentTime.getYear()));
		
		
		
//		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
//		Calendar calendar = Calendar.getInstance();
//		String dateArray = "";
//		for(int i = 1; i < 5 ; i++){
//			if(i == 1){
//				String dateStr1 = CurrentTime.getStringDate();
//				Date now = format1.parse(dateStr1);
//				calendar.setTime(now);
//				calendar.add(Calendar.MONTH, 3);
//				calendar.add(Calendar.DAY_OF_YEAR,-1);
//				String dateStr2 = format1.format(calendar.getTime());
//				dateArray = dateStr1 + "�?+ dateStr2;
//			}else{
//				calendar.add(Calendar.DAY_OF_YEAR,+1);
//				String dateStr3 = format1.format(calendar.getTime());
//				calendar.add(Calendar.MONTH, 3);
//				calendar.add(Calendar.DAY_OF_YEAR,-1);
//				String dateStr4 = format1.format(calendar.getTime());
//				dateArray += ":"+dateStr3 + "�?+ dateStr4;
//			}
//		}
//		//System.out.println(dateArray);
//		System.out.println(CurrentTime.getStepSpecialDate("2015-01-05"));
//		System.out.println(CurrentTime.compareDateTime("2016-04-08 10:55:20", "2016-04-08 10:55:10"));
//		System.out.println(CurrentTime.getFinalDate(2));
//		System.out.println(CurrentTime.getMonthOrOne());
//		System.out.println(Math.round(2.51));
//		System.out.println(CurrentTime.getMonthOrLast());
//		System.out.println(CurrentTime.getEndDayofMonth(2016,8));
//		for(int i = 1 ; i <= 20 ; i++){
//			System.out.println("time_"+i+": "+CurrentTime.getRadomTime());
//		}
		
		
	}
}
