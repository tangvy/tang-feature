package com.tangv.feature.annotation;


import com.tangv.feature.config.DataBaseType;

import java.lang.annotation.*;

/**
 * 数据源自定义注解
 */

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {

    DataBaseType value() default DataBaseType.TANG_FEATURE;
}
