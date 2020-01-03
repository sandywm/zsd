package com.zsd.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class Convert {
	
	/**
	 * 数字转换成年级
	 * @author Administrator
	 * @date 2019-5-5 下午05:42:52
	 * @param number
	 * @return
	 */
	public static String  NunberConvertChinese(int number){
		String convertStr = "";
		if(number > 0){
			String[] chineseStr = {"一年级","二年级","三年级","四年级","五年级","六年级","七年级","八年级","九年级","高一","高二","高三"};
			if(number <= chineseStr.length){
				convertStr = chineseStr[number-1];
			}else{//超过年级数组，按最后一个年级数据取值
				convertStr = "高三";
			}
		}
		return convertStr;
	}
	
	/**
	 * 中文年级名称转换成年级号
	 * @author wm
	 * @date 2019-5-6 上午10:15:45
	 * @param gradeName
	 * @return
	 */
	public static String  ChineseConvertNumber(String gradeName){
		String gradeNo = "";
		if(gradeName.equals("一年级")){
			gradeNo = "01";
		}else if(gradeName.equals("二年级")){
			gradeNo = "02";
		}else if(gradeName.equals("三年级")){
			gradeNo = "03";
		}else if(gradeName.equals("四年级")){
			gradeNo = "04";
		}else if(gradeName.equals("五年级")){
			gradeNo = "05";
		}else if(gradeName.equals("六年级")){
			gradeNo = "06";
		}else if(gradeName.equals("七年级")){
			gradeNo = "07";
		}else if(gradeName.equals("八年级")){
			gradeNo = "08";
		}else if(gradeName.equals("九年级")){
			gradeNo = "09";
		}else if(gradeName.equals("高一")){
			gradeNo = "10";
		}else if(gradeName.equals("高二")){
			gradeNo = "11";
		}else if(gradeName.equals("高三")){
			gradeNo = "12";
		}
		return gradeNo;
	}
	/**
	 * 0-5转换成大写字母A-F
	 * @author wm
	 * @date 2019-6-13 上午11:58:21
	 * @param number
	 * @return
	 */
	public static String NumberConvertBigChar(int number){
		String convertStr = "";
		String[] chineseStr = {"A","B","C","D","E","F"};
		if(number <= chineseStr.length){
			convertStr = chineseStr[number];
		}
		return convertStr;
	}
	/**
	 * 0-5转换成小写字母a-f
	 * @author wm
	 * @date 2019-6-13 上午11:59:03
	 * @param number
	 * @return
	 */
	public static String NumberConvertSmallChar(int number){
		String convertStr = "";
		String[] chineseStr = {"a","b","c","e","d","f"};
		if(number <= chineseStr.length){
			convertStr = chineseStr[number];
		}
		return convertStr;
	}
	
	/**
	 * 班级创建日期转成年级数字（1,2,3）
	 * @author Administrator
	 * @date 2019-5-5 下午05:43:05
	 * @param buildClassDate
	 * @return
	 */
	public static int dateConvertGradeNumber(String buildClassDate){
		int gradeNumber = 0;
		String currentTime = CurrentTime.getCurrentTime();
		int diffYear = CurrentTime.comparaDate(currentTime, buildClassDate, "year");
		int diffMonth = CurrentTime.comparaDate(currentTime, buildClassDate, "month");
		if(diffYear == 0){
			if(diffMonth < 0){
				gradeNumber = 0;
			}else{
				gradeNumber = 1;
			}
		}else{
			if(diffMonth < 0){
				gradeNumber = diffYear;
			}else{
				gradeNumber = diffYear + 1;
			}
		}
		return gradeNumber;
	}
	

	/**
	 * 根据年级号获取年级下班级的创建日期
	 * @author wm
	 * @date 2019-12-12 下午08:12:59
	 * @param gradeNo
	 * @return
	 */
	public static String numberConvertBuildClassDate(Integer gradeNo){
		String currentDate = CurrentTime.getStringDate();
		Integer month =  Integer.parseInt(currentDate.substring(5,6));
		Integer year = Integer.parseInt(currentDate.substring(0,4));
		if(month >= 9){
			return (year-gradeNo+1)+"-09-01";
		}else{
			return (year-gradeNo)+"-09-01";
		}
	}
	
	/**
	 * 班级创建日期转换成年级名称
	 * @author wm
	 * @date 2019-5-6 上午08:38:23
	 * @param buildClassDate
	 * @return
	 */
	public static String dateConvertGradeName(String buildClassDate){
		String gradeName = "";
		int gradeNumber = Convert.dateConvertGradeNumber(buildClassDate);
		gradeName = Convert.NunberConvertChinese(gradeNumber);
		return gradeName;
	}
	
	/**
	 * 数字转换成章节
	 * @author Administrator
	 * @date 2019-5-5 下午05:42:58
	 * @param number
	 * @return
	 */
	public static String  NunberConvertChapterName(int number){
		String convertStr = "";
		String[] chineseStr = {"第一单元","第二单元","第三单元","第四单元","第五单元",
							   "第六单元","第七单元","第八单元","第九单元","第第十单元",
							   "第十一单元","第十二单元","第十三单元","第十四单元","第十五单元",
							   "第十六单元","第十七单元","第十八单元","第十九单元","第二十单元"};
		if(number <= chineseStr.length){
			convertStr = chineseStr[number-1];
		}
		return convertStr;
	}
	
	
	/**
	 * 账号失效日期转成年级数字
	 * @author Administrator
	 * @date 2019-5-5 下午05:43:13
	 * @param endDate
	 * @param buildClassDate
	 * @return
	 */
	public static int dateConvertGradeNumber(String endDate,String buildClassDate){
		int gradeNumber = 0;
		int diffYear = CurrentTime.comparaDate(endDate, buildClassDate, "year");
		int diffMonth = CurrentTime.comparaDate(endDate, buildClassDate, "month");
		if(diffYear == 0){
			if(diffMonth < 0){
				gradeNumber = 0;
			}else{
				gradeNumber = 1;
			}
		}else{
			if(diffMonth < 0){
				gradeNumber = diffYear;
			}else{
				gradeNumber = diffYear + 1;
			}
		}
		return gradeNumber;
	}
	
	/**
	 * 获取中文全拼
	 * @description
	 * @author wm
	 * @date 2017-3-7 上午09:46:40
	 * @param inputStr
	 * @return
	 */
	public static String getFullPY(String inputStr){
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
 
        char[] input = inputStr.trim().toCharArray();
        String output = "";
        try {
            for (int i = 0; i < input.length; i++) {
                if (java.lang.Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
                    output += temp[0];
                } else
                    output += java.lang.Character.toString(input[i]);
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return output;
	}
	
	/**
	 * 获取中文首字母拼音
	 * @description
	 * @author wm
	 * @date 2017-3-7 上午09:48:39
	 * @param inputStr_chi
	 * @return
	 */
	public static String getFirstSpell(String inputStr_chi){
		StringBuffer pybf = new StringBuffer();   
        char[] arr = inputStr_chi.toCharArray();   
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();   
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);   
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
        for (int i = 0; i < arr.length; i++) {   
            if (arr[i] > 128) {   
                    try {   
                            String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);   
                            if (temp != null) {   
                                    pybf.append(temp[0].charAt(0));   
                            }   
                    } catch (BadHanyuPinyinOutputFormatCombination e) {   
                            e.printStackTrace();   
                    }   
            } else {   
                    pybf.append(arr[i]);   
            }   
        }   
        return pybf.toString().replaceAll("\\W", "").trim(); 
	}
	
	/**
	 * 保留2位小数的四舍五入
	 * @description
	 * @author wm
	 * @date 2017-5-5 下午03:59:39
	 * @param inputF
	 * @return
	 */
	public static float convertInputNumber(float inputF){
		return (float)(Math.round(inputF * 100)) / 100;
	}
	
	/**
	 * 保留2位小数的四舍五入
	 * @description
	 * @author wm
	 * @date 2017-6-21 下午05:19:40
	 * @param inputD
	 * @return
	 */
	public static String convertInputNumber_1(double inputD){
		DecimalFormat    df   = new DecimalFormat("######0.00");   
		return df.format(inputD);
	}
	
	/**
	 * 保留2位小数的四舍五入(格式化金额)
	 * @description
	 * @author wm
	 * @date 2017-11-20 上午09:09:59
	 * @param inputD
	 * @return
	 */
	public static String convertInputNumber_3(double inputD){
		DecimalFormat    df   = new DecimalFormat("￥,###.00");   
		return df.format(inputD);
	}
	
	public static String convertMoney(double inputMoney){
		DecimalFormat    df   = new DecimalFormat("￥,###.##");
		return df.format(inputMoney);
	}
	
	public static String convertMoney_1(double inputMoney){
		DecimalFormat    df   = new DecimalFormat(",###.##");
		return df.format(inputMoney);
	}
	/**
	 * 保留2位小数的四舍五入
	 * @description
	 * @author wm
	 * @date 2017-11-15 上午10:53:26
	 * @param inputD
	 * @return
	 */
	public static Double convertInputNumber_2(double inputD){
		DecimalFormat    df   = new DecimalFormat("######0.00");   
		return Double.parseDouble(df.format(inputD));
	}
	
	/**
	 * 保留1位小数的四舍五入
	 * @author wm
	 * @date 2019-8-16 下午05:28:07
	 * @param inputD
	 * @return
	 */
	public static Double convertInputNumber_5(double inputD){
		DecimalFormat    df   = new DecimalFormat("######0.0");   
		return Double.parseDouble(df.format(inputD));
	}
	
	/**
	 * 保留4位小数的四舍五入
	 * @description
	 * @author wm
	 * @date 2017-11-15 上午10:53:26
	 * @param inputD
	 * @return
	 */
	public static Double convertInputNumber_4(double inputD){
		DecimalFormat    df   = new DecimalFormat("######0.0000");   
		return Double.parseDouble(df.format(inputD));
	}
	
	/**
	 * 不保留小数的四舍五入
	 * @author wm
	 * @date 2019-8-17 上午10:15:44
	 * @param inputD
	 * @return
	 */
	public static Integer convertInputNumber_6(double inputD){
		DecimalFormat    df   = new DecimalFormat("######0");
		return Integer.parseInt(df.format(inputD));
	}
	
	/**
	 * 输入数字金额转换成中文大写，精确到分
	 * @description
	 * @author wm
	 * @date 2018-7-16 上午08:58:18
	 * @param inputMoney
	 * @return
	 */
	public static String MoneyToCNFormat(Double inputMoney){
		final String[] CN_NUMBERS = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
		final String[] CN_MONETETARY_UNIT = { "分", "角", "元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾","佰", "仟", "兆", "拾", "佰", "仟" };
	    final String CN_FULL = "整";
	    final String CN_NEGATIVE = "负";
	    final String CN_ZERO = "零角零分";
	    final long MAX_NUMBER = 10000000000000000l;//最大16位整数
	    final String INVALID_AMOUNT = "金额超出最大金额千兆亿(16位整数)";
	    if(Math.abs(inputMoney) >= MAX_NUMBER) return INVALID_AMOUNT;
	    StringBuilder result = new StringBuilder();
        long value = Math.abs(Math.round(inputMoney*100));//四舍五入到小数点后2位
        int i = 0;
        while(true){
        	int temp = (int) (value % 10);
            result.insert(0, CN_MONETETARY_UNIT[i]);
            result.insert(0, CN_NUMBERS[temp]);
            value = value / 10;
            if (value < 1)
                break;
            i++;
        }
        //"零角零分" 转换成 "整"
        int index = result.indexOf(CN_ZERO);
        if(index > -1) {
            result.replace(index, index + 4, CN_FULL);
        }
        //负数
        if(inputMoney < 0) {
            result.insert(0, CN_NEGATIVE);
        }
		return result.toString();
	}
	/**
	 * 根据年级编号获取年级创建时间；
	 * @author zong
	 * 2019-5-7上午11:35:22
	 * @param gradeID 年级编号
	 * @return
	 */
	public static String gradeNoToBuildeClassDate(Integer gradeNo) {
		String buildeClassDate;
		Integer currentYear = Integer.parseInt(CurrentTime.getYear());
		Integer currentMonth = Integer.parseInt(CurrentTime.getMonth());
		if(currentMonth >= 9){
			buildeClassDate = (currentYear - gradeNo + 1) + "-09-01";
		}else{
			buildeClassDate = currentYear - gradeNo + "-09-01";
		}
		return buildeClassDate;
	}
	
	/**
	 * 将转义字符转换成原始字符（&#39;转成英文的单引号,&quot;转成英文的双引号）
	 * @author wm
	 * @date 2019-7-2 上午11:01:09
	 * @param specChar
	 */
	public static String replaceSpecChar(String specChar){
		return specChar.replaceAll("&#39;", "'").replaceAll("&quot;", "\"");
	}
	
	/**
	 * 按照班级名次升序排列
	 * @author Administrator
	 * @createDate 2019-8-4
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static class SortCName implements Comparator {
		@Override
	    public int compare(Object obj0, Object obj1) {
	      Map<String, String> map0 = (Map) obj0;
	      Map<String, String> map1 = (Map) obj1;
	      int flag = map0.get("cName").toString().compareTo(map1.get("cName").toString());
	      return flag; // 不取反，则按正序排列
	    }
	 }
	
	/**
	 * 按照班级号升序排列
	 * @author Administrator
	 * @createDate 2019-8-4
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static class SortGName implements Comparator {
		@Override
	    public int compare(Object obj0, Object obj1) {
	      Map<String, String> map0 = (Map) obj0;
	      Map<String, String> map1 = (Map) obj1;
	      int flag = map0.get("gradeNo").toString().compareTo(map1.get("gradeNo").toString());
	      return flag; // 不取反，则按正序排列
	    }
	 }
	
	public static void main(String[] args) throws IOException, FileNotFoundException{
		
		
		String currentDate = CurrentTime.getStringDate();
		String year = currentDate.substring(0,4);
		String month = currentDate.substring(5, 7);
		for(int i = 1 ; i <= 12 ; i++){
			System.out.println((Integer.parseInt(year)+i-1)+"-09-01");
		}
		System.out.println(year);
		System.out.println(month);
//		String aa = "1233:11|12:12";
//		String[] array = aa.split(":");
//		String[] array_1 = array[1].split("\\|");
//		System.out.println(array_1[0]);
//		long start = System.currentTimeMillis();
//		System.out.println("读取开始"+start);
//		String s = null;
//		File file = new File("E:/appVersion.json");
//		if(file.exists()){
//			InputStreamReader br = new InputStreamReader(new FileInputStream(new File("E:/appVersion.json")),"utf-8");//读取文件,同时指定编码
//			StringBuffer sb = new StringBuffer();
//	        char[] ch = new char[128];  //一次读取128个字符
//	        int len = 0;
//	        while((len = br.read(ch,0, ch.length)) != -1){
//	            sb.append(ch, 0, len);
//	        }
//	        s = sb.toString();
//	        String newVersion = "";
//	        String remark = "";
//	        JSONObject dataJson = JSON.parseObject(s); 
//	        JSONArray features = dataJson.getJSONArray("versionList");// 找到features json数组
//	        for(Integer i = 0 ; i < features.size() ; i++){
//	        	remark = features.getJSONObject(i).getString("remark");
//	        	newVersion = features.getJSONObject(i).getString("version");
//	        }
//	        if(features.size() > 0){
//	        	newVersion = features.getJSONObject(1).getString("version");
//	        }
//			System.out.println(newVersion);
//			long end = System.currentTimeMillis();
//			System.out.println("耗时"+(end-start));
//		}
		
		String path = "6472:7444|7446:7433:7432|7431:7407|7406:7405|7397:7393|7390|7396|7394:7392:7389";
		path = path.replaceAll(":", ",").replaceAll("\\|", ",");
		StringBuffer studyPath = new StringBuffer();
		for(int i = path.split(",").length - 1 ; i >= 0 ; i--){
			studyPath.append(path.split(",")[i]).append(",");
		}
		studyPath = studyPath.delete(studyPath.length() - 1, studyPath.length());
		System.out.println(studyPath);
		System.out.println(studyPath.substring(studyPath.indexOf(",")+1));
//		System.out.println(Convert.MoneyToCNFormat(157894.26));
//		Map<String,Object> map = new HashMap<String,Object>();
//		List<Object> list_d1 = new ArrayList<Object>();
//		for(int j = 1 ; j <= 2 ; j++){
//			Map<String,Object> map_1 = new HashMap<String,Object>();
//			map_1.put("gradeName", j+"年级");
//			List<Object> list_d2 = new ArrayList<Object>();
//			for(int i = 1 ; i <= 2 ; i++){
//				Map<String,Object> map_2 = new HashMap<String,Object>();
//				map_2.put("classId", i);
//				map_2.put("className", i+"班");
//				list_d2.add(map_2);
//			}
//			map_1.put("cList", list_d2);
//			list_d1.add(map_1);
//		}
//		map.put("cInfo", list_d1);
//		System.out.println(map);
//		String gradeClassStr = "二年级:3班:1,一年级:5班:5,三年级:1班:1,二年级:2班:2,一年级:2班:2,三年级:2班:2";
//		String gradeInfo = "";//二年级,一年级,三年级
//		String classInfo = "";//1班:2班,1班,1班
//		String classIdInfo = "";//1:2,1,1
//		String[] gradeClassArr = gradeClassStr.split(",");
//		for(int i = 0 ; i < gradeClassArr.length ; i++){
//			String gradeName = gradeClassArr[i].split(":")[0];
//			String className = gradeClassArr[i].split(":")[1];
//			String classIdStr = gradeClassArr[i].split(":")[2];
//			String[] classInfoArr = classInfo.split(",");
//			String[] classIdInfoArr = classIdInfo.split(",");
//			if(gradeInfo.contains(gradeName)){
//				String[] gradeInfoArr = gradeInfo.split(",");
//				for(int j = 0 ; j < gradeInfoArr.length ; j++){
//					if(gradeInfoArr[j].equals(gradeName)){
//						classInfoArr[j] = classInfoArr[j]+":"+className;
//						classInfo = "";
//						for(int k = 0 ; k < classInfoArr.length ; k++){
//							classInfo += classInfoArr[k] + ",";
//						}
//						
//						classIdInfoArr[j] = classIdInfoArr[j]+":"+classIdStr;
//						classIdInfo = "";
//						for(int k = 0 ; k < classIdInfoArr.length ; k++){
//							classIdInfo += classIdInfoArr[k] + ",";
//						}
//						
//						break;
//					}
//				}
//			}else{
//				gradeInfo += gradeName + ",";
//				classInfo += className + ",";
//				classIdInfo += classIdStr + ",";
//			}
//		}
//		gradeInfo = gradeInfo.substring(0,gradeInfo.length() - 1);
//		classInfo = classInfo.substring(0,classInfo.length() - 1);
//		classIdInfo = classIdInfo.substring(0, classIdInfo.length() - 1);
//		String[] gradeArr = gradeInfo.split(",");
//		List<Object> list_a = new ArrayList<Object>();
//		for(int i = 0 ; i < gradeArr.length ; i++){
//			Map<String,Object> map_d = new HashMap<String,Object>();
//			map_d.put("gradeName", gradeArr[i]);
//			map_d.put("gradeNo", Convert.ChineseConvertNumber(gradeArr[i]));
//			String[] classArr = classInfo.split(",")[i].split(":");
//			String[] classIdArr = classIdInfo.split(",")[i].split(":");
//			List<Object> list_a1 = new ArrayList<Object>();
//			for(int j = 0 ; j < classArr.length ; j++){
//				Map<String,Object> map_d1 = new HashMap<String,Object>();
//				map_d1.put("cName", classArr[j]);
//				map_d1.put("classId", classIdArr[j]);
//				list_a1.add(map_d1);
//			}
//			SortCName sort = new SortCName();
//			Collections.sort(list_a1, sort);
//			map_d.put("cList", list_a1);
//			list_a.add(map_d);
//		}
//		SortGName sort = new SortGName();
//		Collections.sort(list_a, sort);
//		map.put("gName", list_a);
//		System.out.println(map);
//		System.out.println(gradeInfo);
//		System.out.println(classInfo);
//		
//		
//		
//		for(Map.Entry<String, Object> entry : map.entrySet()){
////			String key = entry.getKey();
////			if(key.equals("gradeName")){
////				String mapValue = (String) entry.getValue();
////				for(int i = 0 ; i < gradeNameStr.split(",").length ; i++){
////					String[] classArr = gradeNameStr.split(",")[i].split(":");
////					System.out.println(classArr[0]);
////					if(mapValue.equals(classArr[0])){
////						System.out.println("same");
////					}
////				}
////			}
////			
//		}
	}
}
