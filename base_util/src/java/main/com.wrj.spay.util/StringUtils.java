package com.wrj.spay.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理Util类
 */
public class StringUtils {
    private static final Pattern pattern = Pattern.compile("^[0-9]+$");

    public final static String EMPTY = "";

    /**
     * toString()方法后是否相等,不可用于判断两个Object是否相等<br />
     * 基本用于基本类型的判断，如判断Long和Int对应的数值是否相等
     *
     * @param arg1 判断参数1
     * @param arg2 判断参数2
     * @return true-相等 false-不相等
     */
    public static boolean equals(Object arg1, Object arg2) {
        if (arg1 == null && arg2 == null) {
            return true;
        }
        if (arg1 == null || arg2 == null) {
            return false;
        }
        return arg1.toString().equals(arg2.toString());
    }

    /**
     * Object的toString方法
     *
     * @param obj 对象
     * @return null则返回"",否则返回对象的toString
     */
    public static String toString(Object obj) {
        if (obj == null) {
            return EMPTY;
        } else {
            return String.valueOf(obj);
        }
    }

    /**
     * 字符串是否未空或字符串内容为空，满足下列条件之一则返回true<br />
     * <li>字符串是null</li>
     * <li>字符串全部是空</li>
     *
     * @param str 字符串
     * @return
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static boolean isAllNotBlank(String... strs) {
        for (String str : strs) {
            if (isBlank(str)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 字符串是否为空<br />
     * <li>字符串是空对象</li>
     * <li>字符串长度是0</li>
     *
     * @param str 字符串
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 是否都是数字
     *
     * @param param 字符串
     * @return
     */
    public static boolean isNumber(String param) {
        int strLen;
        if (param == null || (strLen = param.length()) == 0) {
            return false;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isDigit(param.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 字符串是否都是数字
     *
     * @param params 字符串
     * @return
     */
    public static Boolean isAllNumeric(String... params) {

        for (String param : params) {
            if (StringUtils.isEmpty(param)) {
                return false;
            }
            Matcher matcher = pattern.matcher(param);
            if (!matcher.matches()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 字符串是否都是空或字符串内容为空
     *
     * @param params 字符串
     * @return
     */
    public static boolean isAllBlank(String... params) {
        for (String param : params) {
            if (!StringUtils.isBlank(param)) {
                return false;
            }
        }
        return true;
    }


    public static String join(Collection collection, String seperator) {
        if (collection == null || collection.size() == 0) {
            return "";
        }
        return join(seperator, collection.iterator());
    }

    public static String join(String seperator, Iterator objects) {
        StringBuffer buf = new StringBuffer();
        if (objects.hasNext()) buf.append(objects.next());
        while (objects.hasNext()) {
            buf.append(seperator).append(objects.next());
        }
        return buf.toString();
    }

    public static String checkNull(String str) {
        if (isEmpty(str)) {
            return "";
        }
        return str;
    }
}
