package com.zsd.tools;

import java.text.DecimalFormat;

import com.zsd.tools.Convert;
import com.zsd.tools.CurrentTime;

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
	
	public static void main(String[] args){
		System.out.println(Convert.MoneyToCNFormat(157894.26));
	}
}
