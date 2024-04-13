package com.vector.common.redis.util;

/**
 * Created by Zorg
 * 2020/6/10 09:24
 */
public class StringUtil {

    public static String convertToCamelCase(String str) {
        if (str == null || str.isEmpty())
            return "";
        String[] strArr = str.split("_");
        StringBuilder sb = new StringBuilder();
        for (String s : strArr) {
            if (!s.isEmpty()) {
                sb.append(s.substring(0,1).toUpperCase())
                        .append(s.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }
}
