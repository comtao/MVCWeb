package com.cn.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	/** 年月日时分秒(无下划线) yyyyMMddHHmmss */
    public static final String dtLong                  = "yyyyMMddHHmmss";

    /** 完整时间 yyyy-MM-dd HH:mm:ss */
    public static final String simple                  = "yyyy-MM-dd HH:mm:ss";

    /** 年月日(无下划线) yyyyMMdd */
    public static final String dtShort                 = "yyyyMMdd";
    /** 完整时间 yyyy-MM-dd */
    public static final String shortSimple                  = "yyyy-MM-dd";
    /**
     * 获取对应日期格式的 字符串
     * @param style
     * @return
     */
    public static String getDateStr(String style){
    	Date date = new Date();
    	DateFormat df = new SimpleDateFormat(style);
    	return df.format(date);
    }
    
    public static String getDateStr() {
		Calendar cal = Calendar.getInstance();// 使用日历类
		int year = cal.get(Calendar.YEAR);// 得到年
		int month = cal.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
		int day = cal.get(Calendar.DAY_OF_MONTH);// 得到天
		StringBuffer sb = new StringBuffer();
		sb.append(year).append("年").append(month).append("月").append(day).append("日");
		return sb.toString();
	}
    
    public static String getMyDateStr(String style,Date date){
    	if(date == null){
    		date = new Date();
    	}
    	DateFormat df = new SimpleDateFormat(style);
    	return df.format(date);
    }
    
    public static int getDateInt(Date date){
    	if(date == null){
    		date = new Date();
    	}
    	DateFormat df = new SimpleDateFormat(dtShort);
    	return Integer.parseInt(df.format(date));
    }
    
    public static String getDateStr(String style,Date date){
    	DateFormat df = new SimpleDateFormat(style);
    	return df.format(date);
    }
    
    /**
     * 字符串 转日期 根据 依相应格式
     * @param dateStr
     * @param style
     * @return
     * @throws ParseException
     */
    public static Date str2Date(String dateStr,String style) throws ParseException{
    	if(StringUtil.isEmpty(dateStr)){
    		return new Date();
    	}
    	DateFormat df = new SimpleDateFormat(style);
    	return df.parse(dateStr);
    }
    
    /**
     * 获得当期日期 前/后 n 个月的日期
     * @param i
     * @return
     */
    public static Date getMonth(int n){
    	Calendar calendar = Calendar.getInstance(); 
    	calendar.add(Calendar.MONTH, n);
    	return calendar.getTime();
    }
    
    /**
     * 获得当期日期 前/后 n 天的日期
     * @param i
     * @return
     */
    public static Date getDay(int n){
    	Calendar calendar = Calendar.getInstance(); 
    	calendar.add(Calendar.DATE, n);
    	return calendar.getTime();
    }
    
    public static int getDateInt(String str,String style , int n) throws ParseException{
    	Date date = str2Date(str ,style);
    	Calendar calendar = Calendar.getInstance(); 
    	calendar.setTime(date);
    	calendar.add(Calendar.DATE, n);
    	return getDateInt(calendar.getTime());
    }
}
