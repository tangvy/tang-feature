package com.tangv.feature.aspect;

import com.tangv.feature.annotation.TargetDataSource;
import com.tangv.feature.config.DataSourceHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AdviceName;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * author:   tangwei
 * Date:     2020/12/31 14:06
 * Description: 拦截数据源自定义注解切面
 */
@Order(-1)
@Aspect
@Configuration
public class DataSourceAspect {

    /**
     * 拦截类上加数据源注解
     */
    @Around("@within(com.tangv.feature.annotation.TargetDataSource)")
    public Object AroundDataSourceAspectClass(ProceedingJoinPoint joinPoint) throws Throwable {
        if (joinPoint.getTarget().getClass().isAnnotationPresent(TargetDataSource.class)) {
            TargetDataSource targetDataSource = joinPoint.getTarget().getClass().getAnnotation(TargetDataSource.class);
            DataSourceHolder.setDatasources(targetDataSource.value());
        }
        Object proceed = joinPoint.proceed();
        DataSourceHolder.remove();
        return proceed;
    }

    /**
     * 拦截方法上加数据源注解
     */
    @Around("@annotation(com.tangv.feature.annotation.TargetDataSource)")
    public Object AroundDataSourceAspectMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        if (signature.getMethod().isAnnotationPresent(TargetDataSource.class)) {
            TargetDataSource targetDataSource = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(TargetDataSource.class);
            DataSourceHolder.setDatasources(targetDataSource.value());
        }
        Object proceed = joinPoint.proceed();
        DataSourceHolder.remove();
        return proceed;
    }
}