/**************************************
 * Copyright (C), Navinfo
 * Package: utils
 * Author: wulongyue06158
 * Date: Created in 2019/1/7 11:46
 **************************************/
package utils;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: Sunmeina
 * @Date: 10/17/2018 10:10
 */
@Slf4j
public class DateUtils {

    /**
     * 获取现在时间
     *
     * @return 返回时间类型 Date yyyy-MM-dd HH:mm:ss
     */
    public static Date getNowDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        Date currentTime_2 = null;
        try {
            currentTime_2 = formatter.parse(dateString);
            log.info("DateStr： " + dateString + " " + currentTime_2);
        } catch (ParseException e) {
            log.error("getNowDate 日期格式转换错误" + dateString);
        }
        return currentTime_2;
    }

    /**
     * 获取现在时间
     *
     * @return 返回时间类型 String yyyy-MM-dd HH:mm:ss
     */
    public static String getNowDateStr() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return formatter.format(currentTime);
    }

}
