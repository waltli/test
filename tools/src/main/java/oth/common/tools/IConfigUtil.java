package oth.common.tools;

import com.qzsoft.common.inner.ConfigUtil1;

public class IConfigUtil {
	public static String getPropertyValue(String key,String defaultValue){
		return ConfigUtil1.getPropertyValue(key, defaultValue);
	}

	public static String getPropertyValue(String key){
		return ConfigUtil1.getPropertyValue(key);
	}
	
	public static String getMessage(Object key, Object...objects){
		return ConfigUtil1.getMessage(key, objects);
	}
	
}
