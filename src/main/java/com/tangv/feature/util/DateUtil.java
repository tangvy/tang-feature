package com.tangv.feature.util;

import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * author:   tangwei
 * Date:     2020/12/25 10:34
 * Description:
 */
public class DateUtil {

    public static long getTimeStemp(LocalDateTime dateTime) {
        return dateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public static long getNowStemp() {
        return LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

}