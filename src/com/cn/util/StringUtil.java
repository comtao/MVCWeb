package com.cn.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.id.Hex;
import org.apache.commons.id.uuid.UUID;

public class StringUtil {
	
	public static String subGameName(String gameName,int length,int i){
		String sub = gameName.substring(0, i);
		int j = length-sub.getBytes().length;
		if(j == 0){
			return sub;
		}
		if(j ==1){
			String a = gameName.substring(i, i+1);
			if(a.getBytes().length ==1){
				sub = gameName.substring(0, i+1);
			}
			return sub;
		}
		if(j>=2){
			int k = j/2;
			i = i+k;
			sub =  subGameName(gameName,length,i);
			return sub;
		}
		return "";
	}
	
	/**
	 * 字符串是否为空 或 null
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(str == null || "".equals(str.trim())){
			return true;
		}
		return false ;
	}
	
	public static boolean isEmpty(Object str){
		if(str == null || "".equals(str.toString().trim())){
			return true;
		}
		return false ;
	}
	
	
	/**
	 * map 2 String
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String map2String(Map map){
		StringBuffer sb = new StringBuffer("[");
		if(map != null){
			Iterator it = map.keySet().iterator() ;
			while(it.hasNext()){
				String key = getString(it.next()) ;
				sb.append(key).append(":").append(getString(map.get(key))).append(",");
			}
			sb.setLength(sb.length()-1);
		}
		sb.append("]");
		return sb.toString() ;
	}
	
	/**
	 * 数组转字符串
	 * @param objs
	 * @return
	 */
	public static String Array2Str(Object[] objs){
		StringBuffer sb = new StringBuffer("[");
		for(Object obj:objs){
			if(sb.length()>1){
				sb.append(",");
			}
			if(obj != null){
				sb.append(obj.toString());
			}else{
				sb.append("null");
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
	/**
	 * 数组转字符串
	 * @param objs
	 * @return
	 */
	public static String ArrayString(Object[] objs){
		StringBuffer sb = new StringBuffer();
		for(Object obj:objs){
			if(sb.length()>0){
				sb.append(",");
			}
			if(obj != null){
				sb.append(obj.toString());
			}else{
				sb.append("null");
			}
		}
		sb.append("");
		return sb.toString();
	}
	/**
	 * 自动生成32位的随机数作为id  
	 * @return
	 */
	public static String getCode() {
		  return new String(Hex.encodeHex(UUID
		    .randomUUID().getRawBytes()));
	}
	
	/**
	 * 获取文件后缀 包含. exp: .apk
	 * @param str
	 * @return
	 */
	public static String getSuffix(String str){
		return str.substring(str.lastIndexOf("."), str.length());
	}
	
	/**
	 * 取得整型值，带默认
	 */
	public static int getInt(String stringValue,int initValue){
		int intValue=initValue;
		if(stringValue!=null){
			try{
				intValue=Integer.parseInt(stringValue);
			}catch(NumberFormatException ne){}
		}
		return intValue;
	}
	
	/**
	 * obj 2 String
	 * @param obj
	 * @return
	 */
	public static String getString(Object obj){
		if(obj == null){
			return "" ;
		}
		return (String)obj;
	}
	
	public static String list2Str(List<String> list){
		StringBuffer sb = new StringBuffer();
		for(String str:list){
			sb.append(str).append(",");
		}
		if(sb.length()>1){
			sb.setLength(sb.length()-1);
		}
		return sb.toString() ;
	}
	
	/**
	 * Print SQL with param
	 * 
	 * @param sql
	 * @param params
	 * @param seperator
	 * @return
	 */
	public static String assembleSQL(String sql, Object[] params) {
		String seperator = "?";
		StringBuffer retValue = new StringBuffer();
		try {
			if ((sql != null) && (sql.length() > 0)) {
				retValue.append(sql);
				if ((params != null) && (params.length > 0)) {
					for (int i = 0; i < params.length; i++) {
						int pos1 = retValue.indexOf(seperator);
						if (params[i] == null) {
							retValue.replace(pos1, pos1 + seperator.length(), "");
						} else if (params[i] instanceof Date) {
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							Date date = (Date) params[i];
							retValue.replace(pos1, pos1 + seperator.length(), "to_date('" + sdf.format(date)
									+ "', 'yyyy-mm-dd hh24:mi:ss')");
						} else if (params[i] instanceof String)
							retValue.replace(pos1, pos1 + seperator.length(), "'" + params[i].toString() + "'");
						else
							retValue.replace(pos1, pos1 + seperator.length(), params[i].toString());
					}
				}
			}
		} catch (Exception ex) {
		}
		return retValue.toString();
	}
 
	
	/**
	 * 获取IP 地址
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {  
	    String ip = request.getHeader("x-forwarded-for");  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("Proxy-Client-IP");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getRemoteAddr();  
	    } 
	    if(ip != null && ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) 
        { 
            ip = "127.0.0.1"; 
        } 
	    return ip;  
	}  
	
	
	/**
	 * 随机数字 字母
	 * @param length
	 * @return
	 */
	public static String getCharAndNumr(int length)     
	{     
	    String val = "";     
	    Random random = new Random();     
	    for(int i = 0; i < length; i++){     
	        String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字     
	        if("char".equalsIgnoreCase(charOrNum)) // 字符串     
	        {     
	            int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; //取得大写字母还是小写字母     
	            val += (char) (choice + random.nextInt(26));     
	        }     
	        else if("num".equalsIgnoreCase(charOrNum)) // 数字     
	        {     
	            val += String.valueOf(random.nextInt(10));     
	        }     
	    }     
	    return val;     
	}   
	/**
     * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
     * @return
     *      以yyyyMMddHHmmss为格式的当前系统时间
     */
	public  static String getNum(){
		SimpleDateFormat format = new SimpleDateFormat(DateUtil.dtLong);
        Date date = new Date();
        String strKey = format.format(date);
        java.util.Random r = new java.util.Random();
        strKey = strKey + r.nextInt();
        strKey = strKey.substring(0, 19);
        
        return strKey;
	}
 
}
