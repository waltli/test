package oth.common.tools;

import com.sbolo.syk.common.inner.ConfigUtil1;

public class IConfigUtil {
	public static String getPropertyValue(String key,String defaultValue){
		return ConfigUtil1.getPropertyValue(key, defaultValue);
	}

	public static String getPropertyValue(String key){
		return ConfigUtil1.getPropertyValue(key);
	}
	
}
