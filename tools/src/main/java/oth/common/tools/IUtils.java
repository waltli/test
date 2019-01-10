package oth.common.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qzsoft.common.inner.Utils1;

import oth.common.constants.TimeDirectEnum;

public class IUtils {
	private static final Logger LOG = LoggerFactory.getLogger(IUtils.class);
	
	public static boolean isMinimumType(Class clazz) {
		return Utils1.isMinimumType(clazz);
	}
	
	public static byte[] objectToByte(Object obj) throws IOException {  
		return Utils1.objectToByte(obj);
	}
	
	public static Object byteToObject(byte[] bytes) {
		return Utils1.byteToObject(bytes);
    }
	
	public static String getServerName(){
		return Utils1.getServerName();
	}
    public static String getUUID(){
        return Utils1.getUUID();
    }
	
	public static String unicode2Chinese(String unicode){
		return Utils1.unicode2Chinese(unicode);
	}
    public static String int2ChineseNumber(String i){
    	return Utils1.int2ChineseNumber(i);  
    } 
	
	public static int chineseNumber2Int(String chineseNumber){
		return Utils1.chineseNumber2Int(chineseNumber);
	}
	
	public static String encode(String str, String charset) {
		return Utils1.encode(str, charset);
	}
	
	public static String date2Str(Date time, String format){
		return Utils1.date2Str(time, format);
	}
	
	public static String date2Str(Date time){
		return Utils1.date2Str(time);
	}
	
	public static Date str2Date(String dateStr, String format) throws ParseException{
		return Utils1.str2Date(dateStr, format);
	}
	
	public static Date str2Date(String dateStr) throws ParseException{
		return Utils1.str2Date(dateStr);
	}
	
	public static Date str2Date(String dateStr, TimeDirectEnum direct) throws ParseException{
		return Utils1.str2Date(dateStr, direct);
	}
	
	public static Date str2Date(String dateStr, String format, TimeDirectEnum direct) throws ParseException{
		return Utils1.str2Date(dateStr, format, direct);
	}
	
	public static Date str2Date(String dateStr, TimeDirectEnum direct, Boolean isRape) throws ParseException{
		return Utils1.str2Date(dateStr, direct, isRape);
	}
	public static Date str2Date(String dateStr, String format, TimeDirectEnum direct, Boolean isRape) throws ParseException{
		return Utils1.str2Date(dateStr, format, direct, isRape);
	}
	
	public static String getFormat(String dateStr){
		return Utils1.getFormat(dateStr);
	}
	
	public static String dateWipe(String dateStr, TimeDirectEnum direct, Boolean isRape){
		return Utils1.dateWipe(dateStr, direct, isRape);
		
	}
	
	public static boolean containsOne(List<String> all, List<String> sub){
		return Utils1.containsOne(all, sub);
	}
	
	public static String getHostUrl(String host, String uri){
		return Utils1.getHostUrl(host, uri);
	}
	
	public static void uploadPic(InputStream picStream, String targetPathStr, String fileName, String suffix, int width, int height) throws IOException{
		Utils1.uploadPic(picStream, targetPathStr, fileName, suffix, width, height);
	}
	public static String base64(String str, String charset) throws UnsupportedEncodingException{
		return Utils1.base64(str, charset); 
	}
	public static String sendPost(String url, Map<String, String> params)  throws Exception{
		return Utils1.sendPost(url, params);
    }
	public static String sendPost2(String url, Map<String, Object> params,String authorization)  throws Exception{
       return Utils1.sendPost2(url, params, authorization);
   }
	
	public static String sendPost(String url, Map<String, Object> params,String authorization) throws Exception{
        return Utils1.sendPost(url, params, authorization);
    }
	
	public static String sendPost2(String url, String jsonString,String authorization)  throws Exception{
        return Utils1.sendPost2(url, jsonString, authorization);
    }
	
	public static String sendPut(String url, Map<String, Object> params,String authorization)  throws Exception{
        return Utils1.sendPut(url, params, authorization);
    }
   public static String sendGet(String path,String authorization)  throws Exception{
   	return Utils1.sendGet(path, authorization);
   }

	public static String sendDelete(String httpUrl, String authorization)  throws Exception{
		return Utils1.sendDelete(httpUrl, authorization);
	}

	public static String sendJsonPost(String httpUrl, String jsonString, String authorization) throws Exception{
		 return Utils1.sendJsonPost(httpUrl, jsonString, authorization);
	}
	
	public static String sendPutJson(String url, String jsonString,String authorization) throws Exception{
        return Utils1.sendPutJson(url, jsonString, authorization);
    }
}
