/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.feature.io;

import com.tangv.feature.model.entity.BaseTestEntity;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author tang wei
 * @version ByteInputOutputTest.java, v 0.1 2023/2/23 18:14 tang wei Exp $
 */
public class ByteInputOutputTest extends BaseTestEntity {

    @Test
    public void test() throws FileNotFoundException {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        StopWatch stopWatch = new StopWatch("io-test");
        try {
            stopWatch.start();
            fis = new FileInputStream("/Users/tangwei/Downloads/2022年美团技术年货 - 后端系列.pdf");
            fos = new FileOutputStream("classpath: meituan.pdf");
            int len = 0;
            byte[] bytes = new byte[1024*1024*5];
            while ((len = fis.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }
            stopWatch.stop();
            System.out.println("毫秒：" + stopWatch.getTotalTimeMillis() + "ms，" + "秒：" +stopWatch.getTotalTimeSeconds() + "s");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        FileInputStream fis2 = new FileInputStream("/Users/tangwei/Downloads/2022年美团技术年货 - 后端系列.pdf");
        FileOutputStream fos2 = new FileOutputStream("classpath: meituan.pdf");
        StopWatch stopWatch2 = new StopWatch("io-test");
        try (fis2;fos2) {
            stopWatch.start();
            int len = 0;
            byte[] bytes = new byte[1024*1024*5];
            while ((len = fis2.read(bytes)) != -1) {
                fos2.write(bytes, 0, len);
            }
            stopWatch.stop();
            System.out.println("毫秒：" + stopWatch.getTotalTimeMillis() + "ms，" + "秒：" +stopWatch.getTotalTimeSeconds() + "s");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
