package com.tangv.feature.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * author:   tangwei
 * Date:     2020/12/24 17:47
 * Description:
 */

@Slf4j
@Component
public class ScheduleRunner implements ApplicationRunner {

    private ScheduledExecutorService scheduledExecutorService;

    @Autowired
    private GoodsOfflineTask goodsOfflineTask;

    @PostConstruct
    public void init() {
        scheduledExecutorService = Executors.newScheduledThreadPool(10);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        long delay = 0L;
        long period = 10L;

        log.info("商品下线状态轮询定时任务启动...");

        scheduledExecutorService.scheduleAtFixedRate(goodsOfflineTask,delay,period, TimeUnit.SECONDS);
    }
}