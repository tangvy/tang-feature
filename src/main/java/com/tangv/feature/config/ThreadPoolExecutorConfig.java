package com.tangv.feature.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * author:   tangwei
 * Date:     2020/12/22 17:57
 * Description: 线程池配置
 */

@Configuration
public class ThreadPoolExecutorConfig {

    @Bean
    public ThreadPoolExecutor executor() {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                availableProcessors,
                availableProcessors*2,
                1, TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(5),
                new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

}