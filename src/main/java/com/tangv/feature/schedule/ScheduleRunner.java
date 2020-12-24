package com.tangv.feature.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

/**
 * author:   tangwei
 * Date:     2020/12/24 17:47
 * Description:
 */

@Slf4j
@Component
public class ScheduleRunner implements ApplicationRunner {

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}