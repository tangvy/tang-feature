/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.feature.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * webmvc配置类
 *
 * @author jingchun.zhang
 * @version OmsWebMvcConfig.java, v 0.1 2022/10/10 16:34 jingchun.zhang Exp $
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final ObjectMapper objectMapper;

    public WebMvcConfig(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //扩展自定义解析方式UnderScoreConverter
        converters.add(new UnderScoreConverter(objectMapper));
    }

    public class UnderScoreConverter extends AbstractJackson2HttpMessageConverter {
        public UnderScoreConverter(ObjectMapper objectMapper) {
            // 初始化消息转换器，该转换器支持自定义的媒体类型
            super(objectMapper, CustomMediaType.APPLICATION_JSON, CustomMediaType.APPLICATION_UNDERSCORE_UTF8);
        }
    }

}
