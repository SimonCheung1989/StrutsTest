package com.simon.swsxtzs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日期转换为大写工具类
 * <p>Company: 税友软件集团有限公司</p>
 * <p>Copyright: Copyright(c) 2009-2018</p>
 * 
 * @author	zhangww
 * @date 	2015年8月12日
 * @version 1.0
 * @description
 */
public class DateToUpperChinese {

    private static final String[] NUMBERS = { "0", "一", "二", "三", "四", "五",
        "六", "七", "八", "九" };
    
    public static synchronized String toChinese(String str, boolean isAllUpper) {
    	Pattern pattern = Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2}");
    	Matcher matcher = pattern.matcher(str);
    	if(!matcher.find()){
    		return "";
    	}
        StringBuffer sb = new StringBuffer();
        sb.append(getSplitDateStr(str, 0, isAllUpper)).append("年").append(
                getSplitDateStr(str, 1, isAllUpper)).append("月").append(
                getSplitDateStr(str, 2, isAllUpper)).append("日");
        return sb.toString();
    }

    public static String getSplitDateStr(String str, int unit, boolean isAllUpper) {
        String[] DateStr = str.split("-");
        if (unit > DateStr.length)
            unit = 0;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < DateStr[unit].length(); i++) {
            if ((unit == 1 || unit == 2) && Integer.valueOf(DateStr[unit]) > 9) {
            	if(isAllUpper){
            		if(!"1".equals(DateStr[unit].substring(0, 1))){
            			sb.append(convertNum(DateStr[unit].substring(0, 1)));
            		}
                    sb.append("ʮ").append(
                            convertNum(DateStr[unit].substring(1, 2)));
            	}else{
            		sb.append(DateStr[unit].substring(0, 2));
            	}
                break;
            } else {
            	if(isAllUpper){
            		sb.append(convertNum(DateStr[unit].substring(i, i + 1)));
            	}else{
            		sb.append(DateStr[unit].substring(i, i + 1));
            	}
            }
        }
        if (unit == 1 || unit == 2) {
        	if(isAllUpper){
        		return sb.toString().replaceAll("^1", "").replace("0", "");
        	}else{
        		return sb.toString().replace("0", "");
        	}
        }
        return sb.toString();

    }
 
    private static String convertNum(String str) {
        return NUMBERS[Integer.valueOf(str)];
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
    
    public static void main(String[] args){
    	System.out.println(toChinese("2015-02-28", false));
    }

}
