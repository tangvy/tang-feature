/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.feature.annotations;

import com.tangv.feature.config.DataBaseType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tang wei
 * @version DataBase.java, v 0.1 2023/3/6 20:49 tang wei Exp $
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface DataBase {


        DataBaseType value() default DataBaseType.CANAL_TANGV;

}
