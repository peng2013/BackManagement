package com.peng.BackManagement.util.stringutil;

/**
 * @author: peng
 * @time: 2018/7/21
 **/
public class StringUtils {

    public static boolean isNotNull(Object object) {
        return  !isNull(object);
    }

    public static boolean isNull(Object object) {
        return object == null;
    }
}
