package com.tangv.feature.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.Serializable;

/**
 * @author: tangwei
 * @Date: 2021/6/26 10:36
 * @Description:
 */
@SpringBootTest
@WebAppConfiguration
//@RunWith(SpringRunner.class)
@Slf4j
public class BaseTestEntity {

    @Before
    public void init() {
        log.debug("=====开始测试=====");
    }
}