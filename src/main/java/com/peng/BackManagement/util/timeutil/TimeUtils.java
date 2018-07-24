package com.peng.BackManagement.util.timeutil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author: peng
 * @time: 2018/7/21
 **/
public class TimeUtils {
    public static Date getCurrentDate() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTimeStr = dateFormat.format(new Date());
        Date currentDate;
        try {
            currentDate = dateFormat.parse(currentTimeStr);
        } catch (ParseException e) {
            throw e;
        }

        return currentDate;
    }
}
