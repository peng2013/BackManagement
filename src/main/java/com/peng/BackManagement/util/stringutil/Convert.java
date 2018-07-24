package com.peng.BackManagement.util.stringutil;

import org.springframework.util.StringUtils;

/**
 * @author: peng
 * @time: 2018/7/21
 **/
public class Convert {

    public static Long[] toLongArray(String str) {
        return toLongArray(",",str);
    }


    public static Long[] toLongArray(String split,String str) {
        if (StringUtils.isEmpty(str)) {
            return new Long[] {};
        }

        String[] arr = str.split(split);
        final Long[] longs = new Long[arr.length];
        for (int i = 0;i < arr.length;i++) {
            final Long v = toLong(arr[i],null);
            longs[i] = v;
        }

        return  longs;
    }

    public static Long toLong(Object value,Long defaultValue) {
        if (value == null) {
            return defaultValue;
        }

        if (value instanceof Long) {
            return (Long)value;
        }

        if (value instanceof Number) {
            return ((Number)value).longValue();
        }

        return defaultValue;
    }
}
