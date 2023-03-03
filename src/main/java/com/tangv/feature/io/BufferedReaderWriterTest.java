/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.feature.io;

import com.tangv.feature.model.entity.BaseTestEntity;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author tang wei
 * @version BufferedReaderWriterTest.java, v 0.1 2023/2/24 09:57 tang wei Exp $
 */
public class BufferedReaderWriterTest extends BaseTestEntity {

    @Test
    public void test() throws FileNotFoundException {
        /*stopWatch.start();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("//Users//tangwei//Downloads//Navicat Premium 16.1.5 macOS.dmg"));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("navicat.dmg"))) {
            int len;
            while ((len = bufferedReader.read()) != -1) {
                bufferedWriter.write(len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        System.out.println(stopWatch.getLastTaskTimeMillis());

        stopWatch.start();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("//Users//tangwei//Downloads//Navicat Premium 16.1.5 macOS.dmg"));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("navicat1.dmg"))) {
            int len;
            char[] buf = new char[1024*1024*10];
            while ((len = bufferedReader.read(buf)) != -1) {
                bufferedWriter.write(buf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        stopWatch.stop();
        System.out.println(stopWatch.getLastTaskTimeMillis());*/
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("//Users//tangwei//Downloads//岳阳楼记文本.txt"))) {
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {

        }
    }

}
