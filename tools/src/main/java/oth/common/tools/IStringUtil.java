package oth.common.tools;

import java.util.List;

import com.qzsoft.common.inner.StringUtil1;

public class IStringUtil {
	public static String toString(Object o) {
		return StringUtil1.toString(o);
	}
	public static String trimAll(String str) {
		return StringUtil1.trimAll(str);
	}
	public static String trim(String str) {
		return StringUtil1.trim(str);
	}
	public static String replaceNomalBlank(String str) {
		return StringUtil1.replaceNomalBlank(str);
	}
	public static String jointDoubanUrl(String doubanId) {
		return StringUtil1.jointDoubanUrl(doubanId);
	}
	public static String replaceBlank2(String str) {
		return StringUtil1.replaceBlank2(str);
	}
	public static String randomString(int len) {
		return StringUtil1.randomString(len);
	}
	public static String StringLimit(String str, int limit) {
		return StringUtil1.StringLimit(str, limit);
	}
	public static Double toDouble(Object val) {
		return StringUtil1.toDouble(val);
	}
	public static Long toLong(Object val) {
		return StringUtil1.toLong(val);
	}
	public static String[] toArr(String sendee) {
		return StringUtil1.toArr(sendee);
	}
	public static String longListToStr(List<Long> list, String sp) {
		return StringUtil1.longListToStr(list, sp);
	}
	public static String strListToStr(List<String> list, String sp) {
		return StringUtil1.strListToStr(list, sp);
	}
	public static boolean isNum(Object obj) {
		return StringUtil1.isNum(obj);
	}
	public static int toInt(Object obj) {
		return StringUtil1.toInt(obj);
	}
	public static String removeSpacesAndSymbols(String str) {
		return StringUtil1.removeSpacesAndSymbols(str);
	}
	public static String toLowerCaseFirstOne(String s){
		return StringUtil1.toLowerCaseFirstOne(s);
	}
	public static Boolean checkNormalText(String s){
		return StringUtil1.checkNormalText(s);
	}
	public static Boolean checkNormalText(String s,String regex){
		return StringUtil1.checkNormalText(s, regex);
	}
	public static final String getStrByObj(Object obj){
		return StringUtil1.getStrByObj(obj);
	}
    public static String underline2Camel(String line, boolean ... smallCamel) {
        return StringUtil1.underline2Camel(line, smallCamel);
    }
    public static String camel2Underline(String line,Boolean isUpperCase) {
        return StringUtil1.camel2Underline(line, isUpperCase);
    }

}

