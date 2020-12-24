package com.tangv.feature.util;

import com.tangv.feature.enums.CodeType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * author:   tangwei
 * Date:     2020/12/24 13:58
 * Description:
 */
public class NumUtil {

    public static String getCode(CodeType codeType) {
        StringBuffer resultCode = new StringBuffer();
        switch (codeType) {
            case GOODS_CODE:
                String currentTime = NumUtil.currentTime();
                String sixNum = NumUtil.getSixNum();
                resultCode.append(currentTime).append(sixNum);
                break;
            default:
        }
        return resultCode.toString();
    }

    public static String currentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        String currentTime = now.format(formatter);
        return currentTime;
    }

    public static String getSixNum() {
        Random random = new Random();
        String num = "";
        for (int i = 0;i<6;i++) {
            num += random.nextInt(10);
        }
        return num;
    }

}