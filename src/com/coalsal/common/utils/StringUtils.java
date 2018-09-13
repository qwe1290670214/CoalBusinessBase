package com.coalsal.common.utils;


 

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by li on 2017/5/2.
 */
public class StringUtils {
	
	public static boolean isNotNull(String str){
		if(str!=null&&!str.trim().equals("")){
			return true;
		}
		return false;
	}
	
	
    /**
     * 功能描述：根据指定的字符串，分割并生成新的字符串
     *
     * @param str       String 原始字符串
     * @param splitsign String 分隔符
     * @return String 分割后并重新生成的字符串
     */
    public static String splitString(String str, String splitsign) {
        String[] stringArr = split(str, splitsign);
        StringBuffer string = new StringBuffer();
        for (int i = 0; i < stringArr.length; i++) {
            string.append(stringArr[i]);
        }
        return string.toString();
    }

    /**
     * 功能描述：人民币转成大写
     *
     * @param str String 数字字符串
     * @return String 人民币转换成大写后的字符串。此方法暂时只能转换到999999999999.99。达到1万亿时会转换出错。
     */
    public static String hangeToBig(String str) {
        double value;
        try {
            value = Double.parseDouble(str.trim());
        } catch (Exception e) {
            return null;
        }
        char[] hunit = {'拾', '佰', '仟'}; // 段内位置表示
        char[] vunit = {'万', '亿'}; // 段名表示
        char[] digit = {'零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'}; // 数字表示
        long midVal = (long) (value * 100); // 转化成整形
        String valStr = String.valueOf(midVal); // 转化成字符串

        String head = valStr.substring(0, valStr.length() - 2); // 取整数部分
        String rail = valStr.substring(valStr.length() - 2); // 取小数部分

        StringBuffer prefix = new StringBuffer(); // 整数部分转化的结果
        StringBuffer suffix = new StringBuffer(); // 小数部分转化的结果
        // 处理小数点后面的数
        if (rail.equals("00")) { // 如果小数部分为0
            suffix.append("整");
        } else {
            suffix.append(digit[rail.charAt(0) - '0']).append("角").append(digit[rail.charAt(1) - '0']).append("分"); // 否则把角分转化出来
        }
        // 处理小数点前面的数
        char[] chDig = head.toCharArray(); // 把整数部分转化成字符数组
        char zero = '0'; // 标志'0'表示出现过0
        byte zeroSerNum = 0; // 连续出现0的次数
        for (int i = 0; i < chDig.length; i++) { // 循环处理每个数字
            int idx = (chDig.length - i - 1) % 4; // 取段内位置
            int vidx = (chDig.length - i - 1) / 4; // 取段位置
            if (chDig[i] == '0') { // 如果当前字符是0
                zeroSerNum++; // 连续0次数递增
                if (zero == '0') { // 标志
                    zero = digit[0];
                } else if (idx == 0 && vidx > 0 && zeroSerNum < 4) {
                    prefix.append(vunit[vidx - 1]);
                    zero = '0';
                }
                continue;
            }
            zeroSerNum = 0; // 连续0次数清零
            if (zero != '0') { // 如果标志不为0,则加上,例如万,亿什么的
                prefix.append(zero);
                zero = '0';
            }
            prefix.append(digit[chDig[i] - '0']); // 转化该数字表示
            if (idx > 0)
                prefix.append(hunit[idx - 1]);
            if (idx == 0 && vidx > 0) {
                prefix.append(vunit[vidx - 1]); // 段结束位置应该加上段名如万,亿
            }
        }

        if (prefix.length() > 0)
            prefix.append('圆'); // 如果整数部分存在,则有圆的字样
        return prefix.toString() + suffix.toString(); // 返回正确表示
    }

    /**
     * 判断一个字符串中是否包含另一个字符串。
     *
     * @param souString 源字符串。
     * @param tarString 目标字符串。
     * @return boolean 如果包含，则返回true，否则返回false。
     */
    public static boolean isInclude(String souString, String tarString) {
        boolean flag = false;
        int i = souString.indexOf(tarString);
        if (i != -1) {
            flag = true;
        }
        return flag;
    }

    /**
     * 功能描述：判断是否为英文
     *
     * @param string String 传入的字符串
     * @return boolean 是英文返回true,否则返回false
     */
    public static boolean isEnglish(String string) {
        return relace(string, RegexEnum.IS_ENGLISH.getValue());
    }

    /**
     * 功能描述：判断是否为邮箱
     *
     * @param string String 传入的字符串
     * @return boolean 是邮箱返回true,否则返回false
     */
    public static boolean isEmail(String string) {
        return relace(string, RegexEnum.IS_EMAIL.getValue());
    }

    /**
     * 功能描述：判断是否为数字和英文
     *
     * @param string String 传入的字符串
     * @return boolean 是数字和英文返回true,否则返回false
     */
    public static boolean isEnglishNumber(String string) {
        return relace(string, RegexEnum.IS_ENGLISH_NUMBER.getValue());
    }

    /**
     * 功能描述：判断是不是合法的手机号码
     *
     * @param string String 传入的字符串
     * @return boolean 是否是手机号码。是则返回true，否则返回false
     */
    public static boolean isPhone(String string) {

        return relace(string, RegexEnum.IS_PHONE.getValue());
    }

    /**
     * <pre>
     * 功能描述：是否包含特殊字符
     * 字符串中不能包含除数字，大小写字母，汉字外，其它的字符
     * </pre>
     *
     * @param string String 传入的字符串
     * @return boolean 是否是手机号码。是则返回true，否则返回false
     */
    public static boolean notInSpecial(String string) {
        return relace(string, RegexEnum.NOT_IN_SPECIAL_CHAR.getValue());
    }

    /**
     * <pre>
     * 功能描述：使字符串用于模糊查询</br>
     * 将一个字符串转化成    '%字符串%'  形式。
     * </pre>
     *
     * @param str 需要转化的字符串
     * @return String 转化后的字符串
     */
    public static String toSQLLikeString(String str) {
        StringBuffer sb = new StringBuffer("'%");
        if (null == str || "".equals(str)) {
            return sb.append(" ").append("%'").toString();
        } else {
            return sb.append(str).append("%'").toString();
        }
    }

     

     

   

     

    /**
     * <pre>
     * 功能描述：将一个字符串转换成能被SQLQUERY对象中，LIKE查询使用的字符串 </br>
     * 在SQLQUERY中，like查询字符串要转成：%要查询的参数%
     * </pre>
     *
     * @param str 要查询的参数字符串
     * @return String 返回的参数字符串
     */
    public static String toSQLQueryString(String str) {
        if (!isEmptyString(str)) {
            return "%" + str + "%";
        }
        return "";
    }

    /**
     * <pre>
     * 功能描述：将一个字符串转换成能被SQLQUERY对象中，调用存储过程的字符串。 </br>
     * 在SQLQUERY中，like查询字符串要转成：%要查询的参数%
     * </pre>
     *
     * @param str 要查询的参数字符串
     * @return String 返回的参数字符串
     */
    public static String toCallString(String str) {
        if (!isEmptyString(str)) {
            return "{" + str + "}";
        }
        return "";
    }

    /**
     * 功能描述：字符串编码转换的实现方法
     *
     * @param str        待转换编码的字符串
     * @param oldCharset 原编码
     * @param newCharset 目标编码
     * @return String 返回编码后的字符串。
     */
    public static String changeCharset(String str, String oldCharset, String newCharset) {
        if (!isEmptyString(str)) {
            // 用旧的字符编码解码字符串。解码可能会出现异常。
            byte[] bs;
            try {
                bs = str.getBytes(oldCharset);
                // 用新的字符编码生成字符串
                return new String(bs, newCharset);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * <pre>
     * 功能描述：将字符串中的占位符进行替换。
     * </pre>
     *
     * @param str   需要替换的原始字符串。
     * @param value 替换的内容。
     * @return 返回一个替换后的字符串。
     */
    public static String StringFormat(String str, String... value) {
        return MessageFormat.format(str, value);
    }

    /**
     * <pre>
     * 功能描述：使用正则表达式替换字符串。
     * </pre>
     *
     * @param souStr 需要替换的原始字符串。
     * @param tarStr 替换的字符串。
     * @param regStr 正则表达式
     * @return 返回一个替换后的字符串。
     */
    public static String StringFormat(String souStr, String tarStr, String regStr) {
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(souStr);
        return matcher.replaceAll(tarStr);
    }

    /**
     * <pre>
     * 首字母转小写
     * </pre>
     *
     * @param str 需要将首字母改为小写的字符串
     * @return 返回转换后的字符串。
     */
    public static String toLowerCaseFirstOne(String str) {
        if (Character.isLowerCase(str.charAt(0))) {
            return str;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString();
        }
    }

    /**
     * <pre>
     * 将数据库表名转换成类名
     * </pre>
     *
     * @param tableName 表名
     * @return 返回转换后的类名
     */
    public static String converTableName(String tableName) {
        if (StringUtils.isInclude(tableName, "_")) {
            String[] str = tableName.split("_");
            StringBuffer classString = new StringBuffer();
            for (String string : str) {
                classString.append(StringUtils.toUpperCaseFirstOne(string));
            }
            return classString.toString();
        } else {
            return StringUtils.toUpperCaseFirstOne(tableName);
        }
    }

    /**
     * <pre>
     * 首字母转大写
     * </pre>
     *
     * @param str 需要将首字母改为大写的字符串
     * @return 返回转换后的字符串。
     */
    public static String toUpperCaseFirstOne(String str) {
        if (Character.isUpperCase(str.charAt(0))) {
            return str;
        } else {
            return (new StringBuilder()).append(Character.toUpperCase(str.charAt(0))).append(str.substring(1)).toString();
        }
    }

    /**
     * 功能描述：分割字符串
     *
     * @param str       String 原始字符串
     * @param splitsign String 分隔符
     * @return String[] 分割后的字符串数组
     */
    protected static String[] split(String str, String splitsign) {
        if (isEmpty(str) || isEmpty(splitsign)) {
            return null;
        }
        return str.split(splitsign);
    }

    /**
     * 功能描述：对象是否为空
     *
     * @param str String 需要判断的对象
     * @return boolean 判断对象是否为空。当对象为空时返回true，为假返回false
     */
    public static boolean isEmptyString(String str) {
        if (isEmpty(str) || "".equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 功能描述：替换字符串
     *
     * @param source String 母字符串
     * @param from   String 原始字符串
     * @param to     String 目标字符串
     * @return String 替换后的字符串
     */
    public static String replace(String source, String from, String to) {
        if (isEmpty(source) || isEmpty(from) || isEmpty(to)) {
            return null;
        }
        return source.replace(from, to);
    }

    /**
     * 功能描述：对象是否为空
     *
     * @param object Object 需要判断的对象
     * @return boolean 判断对象是否为空。当对象为空时返回true，为假返回false
     */
    public static boolean isEmpty(Object object) {
        if (null == object) {
            return true;
        }
        return false;
    }

    /**
     * 功能描述：根据正则表达式进行验证
     *
     * @param object Object 传入的对象
     * @param regex  String 传入的正则表达式
     * @return boolean 为真返回true,否则返回false
     */
    public static boolean relace(Object object, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(toString(object)).matches();
    }

    /**
     * 功能描述：对象转换为字符串
     *
     * @param object Object 需要转换的对象
     * @return String 转换结果
     */
    public static String toString(Object object) {
        if (isEmpty(object)) {
            return "";
        }
        return object.toString();
    }


    /**
     * 实现用通配符验证字符串
     *
     * @param pattern
     * @param content
     * @param p
     * @param c
     * @return
     */
    public static boolean match(String pattern, String content, int p, int c) {
        // if we reatch both end of two string, we are done
        if (pattern.length() == p && content.length() == c)
            return true;
        /* make sure that the characters after '*' are present in second string.
          this function assumes that the first string will not contain two
           consecutive '*'*/
        if (pattern.length() > p && '*' == pattern.charAt(p) && pattern.length() > p + 1 && content.length() == c)
            return false;
        // if the first string contains '?', or current characters of both
        // strings match
        if (pattern.length() > p && content.length() > c && ('?' == pattern.charAt(p) || pattern.charAt(p) == content.charAt(c)))
            return match(pattern, content, p + 1, c + 1);
        /* if there is *, then there are two possibilities
           a) We consider current character of second string
           b) We ignore current character of second string.*/
        if (pattern.length() > p && '*' == pattern.charAt(p))
            return match(pattern, content, p + 1, c) || match(pattern, content, p, c + 1);
        return false;
    }


     
}
