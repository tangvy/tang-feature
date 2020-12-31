package com.tangv.feature.aspect;

import com.tangv.feature.annotation.TargetDataSource;
import com.tangv.feature.config.DataSourceHolder;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * author:   tangwei
 * Date:     2020/12/31 13:57
 * Description: 设置数据源切面bean 未使用
 */
public class DataSourceExchange implements MethodBeforeAdvice, AfterReturningAdvice {

    @Override
    public void before(Method method, Object[] objects, Object target) throws Throwable {
        if (method.isAnnotationPresent(TargetDataSource.class)) {
            TargetDataSource targetDataSource = method.getAnnotation(TargetDataSource.class);
            DataSourceHolder.setDatasources(targetDataSource.value());
        } else {
            if (target.getClass().isAnnotationPresent(TargetDataSource.class)) {
                TargetDataSource targetDataSource = target.getClass().getAnnotation(TargetDataSource.class);
                DataSourceHolder.setDatasources(targetDataSource.value());
            }
        }
    }

    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        DataSourceHolder.remove();
    }
}