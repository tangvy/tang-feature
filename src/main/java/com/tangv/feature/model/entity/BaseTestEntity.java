package com.tangv.feature.model.entity;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.StopWatch;

/**
 * @author: tangwei
 * @Date: 2021/6/26 10:36
 * @Description:
 */
@SpringBootTest
@WebAppConfiguration
//@RunWith(SpringRunner.class)
@Slf4j
public abstract class BaseTestEntity {

    public static final String split = "=======================================";

    public static final StopWatch stopWatch = new StopWatch("unit-test");

    @Before
    public void init() {
        System.out.println("=====开始测试=====");
    }

    public void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + "  ");
        }
        System.out.println();
    }
}