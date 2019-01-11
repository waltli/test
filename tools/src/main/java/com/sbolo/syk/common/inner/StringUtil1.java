package com.sbolo.syk.common.inner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class StringUtil1 {
	public static String toString(Object o) {
		if (o == null)
			return "";
		else
			return o.toString();
	}

	/**
	 * 去除各式各样的空白串
	 * 
	 * @param str
	 * @return
	 */
	public static String trimAll(String str) {
		String dest = "";
		if (str != null) {
			// 倒数第二个空格与空格键打出来的空格不一样！
			Pattern p = Pattern.compile("\\s|\\t|\\r|\\n| |　|(&nbsp;)");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	/**
	 * 去除头尾的空白串加强
	 * 
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		return Pattern.compile("^(\\s\\\t|\\r|\\n| |　|(&nbsp;))|(\\s|\\t|\\r|\\n| |　|(&nbsp;))$").matcher(str)
				.replaceAll("");
	}

	/**
	 * 所有空格替换为标准空格
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceNomalBlank(String str) {
		String dest = "";
		if (str != null) {
			// 倒数第二个空格与空格键打出来的空格不一样！
			Pattern p = Pattern.compile("\\s|\\t|\\r|\\n| |　|(&nbsp;)");
			Matcher m = p.matcher(str);
			dest = m.replaceAll(" ");
		}
		return dest;
	}

	public static String jointDoubanUrl(String doubanId) {
		return "https://movie.douban.com/subject/" + doubanId + "/";
	}

	/**
	 * 去除字符串的“-”和空串
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceBlank2(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("-|\\s");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	public static String randomString(int len) {
		String randomSource = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			int number = new Random().nextInt(62);
			sb.append(randomSource.charAt(number));
		}
		return sb.toString();
	}

	public static String StringLimit(String str, int limit) {
		int strLen = str.length();

		double count = 0.0d;
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < strLen; i++) {
			int asciicode = str.codePointAt(i);
			if (asciicode > 255) {
				count = count + 1.0d;
			} else {
				count = count + 0.5d;
			}
			if (count > limit) {
				sb.append("...");
				break;
			}
			sb.append(str.charAt(i));
		}
		return sb.toString();
	}

	/**
	 * 转换为Double类型
	 */
	public static Double toDouble(Object val) {
		if (val == null) {
			return 0D;
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}

	/**
	 * 转换为Long类型
	 */
	public static Long toLong(Object val) {
		return toDouble(val).longValue();
	}

	/**
	 * 字符串转换为字符串数组
	 */
	public static String[] toArr(String sendee) {
		String[] toArr = null;
		if (!StringUtils.isEmpty(sendee)) {
			if (sendee.contains(",")) {
				toArr = sendee.split(",");
			} else {
				toArr = new String[] { sendee };
			}
		}
		return toArr;
	}

	/**
	 * 将long集合转换为字符串
	 * 
	 * @param list
	 * @param sp
	 *            拆分符
	 * @return
	 */
	public static String longListToStr(List<Long> list, String sp) {
		StringBuffer sb = new StringBuffer("");
		for (Long temp : list) {
			sb.append(temp + sp);
		}
		if (sb.length() > 0) {
			return sb.substring(0, sb.length() - 1);
		}
		return sb.toString();
	}
	
	/**
	 * 将Str集合转换为字符串
	 * 
	 * @param list
	 * @param sp
	 *            拆分符
	 * @return
	 */
	public static String strListToStr(List<String> list, String sp) {
		StringBuffer sb = new StringBuffer("");
		for (String temp : list) {
			sb.append(temp + sp);
		}
		if (sb.length() > 0) {
			return sb.substring(0, sb.length() - 1);
		}
		return sb.toString();
	}

	/**
	 * 是否可转化为数字
	 * 
	 * @param o
	 * @return
	 */
	public static boolean isNum(Object obj) {
		if (null == obj) {
			return false;
		}
		try {
			new BigDecimal(obj.toString());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 转化为int型数字, 不可转化时返回0
	 * 
	 * @param o
	 * @return
	 */
	public static int toInt(Object obj) {
		if (isNum(obj)) {
			return new Integer(obj.toString());
		} else {
			return 0;
		}
	}
	
	/**
	 * 去除所有标点符号和空格符
	 * @param str
	 * @return
	 */
	public static String removeSpacesAndSymbols(String str) {
		//1.清除所有标点 符号,只留下字母 数字  汉字  共3类
		String str2 = str.replaceAll("[\\pP\\p{Punct}]","");
		
		//2.去除空格
		//可以替换大部分空白字符， 不限于空格 . 说明:\s 可以匹配空格、制表符、换页符等空白字符的其中任意一个
		String str3 = str2.replaceAll("\\s*",""); 
		
		return str3;
	}
	
	/**
	 * 首字母转小写
	 * @param s
	 * @return
	 */
	public static String toLowerCaseFirstOne(String s){
	  if(Character.isLowerCase(s.charAt(0)))
	    return s;
	  else
	    return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	}
	
	/**
	 * 判断字符串格式是否正确
	 * @param s
	 * @return
	 */
	public static Boolean checkNormalText(String s){
		Pattern pattern = Pattern.compile("^[\u4E00-\u9FA5a-zA-Z0-9_${}，。（）[-]\\s]+$");
		return pattern.matcher(s).matches();
	}
	/**
	 * 判断字符串格式是否正确
	 * @param s 要验证的数据
	 * @param regex 正则表达式
	 * @return
	 */
	public static Boolean checkNormalText(String s,String regex){
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(s).matches();
	}
	
	/**
	 * 将对象转成字符串
	 * @param obj
	 * @return
	 */
	public static final String getStrByObj(Object obj){
		if(obj == null){
			return null;
		}else{
			return obj.toString();
		}
		
	}
	
	/**
     * 下划线转驼峰法(默认小驼峰)
     *
     * @param line
     *            源字符串
     * @param smallCamel
     *            大小驼峰,是否为小驼峰(驼峰，第一个字符是大写还是小写)
     * @return 转换后的字符串
     */
    public static String underline2Camel(String line, boolean ... smallCamel) {
        if (line == null || "".equals(line)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile("([A-Za-z\\d]+)(_)?");
        Matcher matcher = pattern.matcher(line);
        //匹配正则表达式
        while (matcher.find()) {
            String word = matcher.group();
            //当是true 或则是空的情况
            if((smallCamel.length ==0 || smallCamel[0] ) && matcher.start()==0){
                sb.append(Character.toLowerCase(word.charAt(0)));
            }else{
                sb.append(Character.toUpperCase(word.charAt(0)));
            }

            int index = word.lastIndexOf('_');
            if (index > 0) {
                sb.append(word.substring(1, index).toLowerCase());
            } else {
                sb.append(word.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }

    /**
     * 驼峰法转下划线
     *
     * @param line
     *            源字符串
     * @return 转换后的字符串
     */
    public static String camel2Underline(String line,Boolean isUpperCase) {
        if (line == null || "".equals(line)) {
            return "";
        }
        line = String.valueOf(line.charAt(0)).toUpperCase()
                .concat(line.substring(1));
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile("[A-Z]([a-z\\d]+)?");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String word = matcher.group();
            if(isUpperCase == true){
            	sb.append(word.toUpperCase());
            }else{
            	sb.append(word.toLowerCase());
            }
            sb.append(matcher.end() == line.length() ? "" : "_");
        }
        return sb.toString();
    }

}

