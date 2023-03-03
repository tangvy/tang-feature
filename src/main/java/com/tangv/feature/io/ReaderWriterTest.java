/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.feature.io;

import com.tangv.feature.model.entity.BaseTestEntity;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author tang wei
 * @version ReaderWriterTest.java, v 0.1 2023/2/23 15:25 tang wei Exp $
 */
public class ReaderWriterTest extends BaseTestEntity {

    @Test
    public void read() throws IOException {

        FileInputStream fi = new FileInputStream("//Users//tangwei//Downloads//岳阳楼记.rtf");
        int len1 = 0;
        byte[] buf = new byte[209];
        try (fi) {
            while ((len1 = fi.read(buf)) != -1) {
                //System.out.print(new String(buf, 0, len1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(split);
        //FileReader fileReader = new FileReader("//Users//tangwei//Downloads//岳阳楼记.rtf");
        FileReader fileReader = new FileReader("//Users//tangwei//Downloads//岳阳楼记文本.txt");
        FileWriter fileWriter = new FileWriter("岳阳楼记.txt");
        //FileReader fileReader = new FileReader("//Users//tangwei//Downloads//2019-2021美团技术年货-后端篇.pdf");
        int len2 = 0;
        char[] buf2 = new char[1024];
        StringBuffer yueYangLou = new StringBuffer();
        try {
            stopWatch.start();
            while ((len2 = fileReader.read(buf2)) != -1) {
                String s = new String(buf2, 0, len2);
                yueYangLou.append(s);
                //System.out.print(new String(buf2, 0, len2));
            }
            stopWatch.stop();
            System.out.println("批量读取花费：" + stopWatch.getTotalTimeNanos() + "ns");

            int len3 = 0;
            stopWatch.start();
            while ((len3 = fileReader.read()) != -1) {
                //System.out.print((char) len3);
            }
            stopWatch.stop();
            System.out.println("单个字符读取花费：" + stopWatch.getTotalTimeNanos() + "ns");

            String yueStr = yueYangLou.toString();
            System.out.println(yueStr.length());
            System.out.println(yueStr.toCharArray());
            fileWriter.write(yueStr.toCharArray(), 3, 10);
            fileWriter.write(30000);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
