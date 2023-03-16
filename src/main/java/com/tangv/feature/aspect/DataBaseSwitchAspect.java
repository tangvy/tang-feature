/**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */
package com.tangv.feature.aspect;

import com.tangv.common.annotations.DataBase;
import com.tangv.feature.config.DataSourceHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author tang wei
 * @version DataBaseSwitchAspect.java, v 0.1 2023/3/7 10:39 tang wei Exp $
 */
@Aspect
@Component
public class DataBaseSwitchAspect {

    @Pointcut("@within(com.tangv.common.annotations.DataBase) || @annotation(com.tangv.common.annotations.DataBase)")
    public void aspect() {};

    @Before("aspect()")
    public void beforeExcute(JoinPoint joinPoint) {
        DataBase dataBase = getDataBase(joinPoint);
        if (dataBase != null) {
            DataSourceHolder.setDatasources(dataBase.value());
        }
    }

    @AfterThrowing("aspect()")
    public void ExcuteThrowable(JoinPoint joinPoint) {
        DataBase dataBase = getDataBase(joinPoint);
        if (dataBase != null) {
            DataSourceHolder.remove();
        }
    }

    @After("aspect()")
    public void afterExcute(JoinPoint joinPoint) {
        DataBase dataBase = getDataBase(joinPoint);
        if (dataBase != null) {
            DataSourceHolder.remove();
        }
    }

    private DataBase getDataBase(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DataBase dataBase = method.getAnnotation(DataBase.class);
        if (dataBase == null) {
            Class<?> clazz = joinPoint.getTarget().getClass();
            dataBase = clazz.getDeclaredAnnotation(DataBase.class);
        }
        return dataBase;
    }
}
