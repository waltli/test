package com.sbolo.syk.common.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.sbolo.syk.common.exception.BusinessException;

import oth.common.tools.IConfigUtil;

/**
 * 
 * @author lihaopeng
 * @date 2015年10月8日
 * @version 1.0.0
 * @description desc
 */
public class ConfigUtils {
	
	/**
	 * 获取配置文件的值
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getPropertyValue(String key,String defaultValue){
		return IConfigUtil.getPropertyValue(key, defaultValue);
	}

	/**
	 * 获取配置文件的值
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getPropertyValue(String key){
		return IConfigUtil.getPropertyValue(key);
	}
}
