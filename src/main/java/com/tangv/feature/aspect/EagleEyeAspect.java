package com.tangv.feature.aspect;

import com.alibaba.fastjson.JSONObject;
import com.tangv.feature.annotation.EagleEye;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * author:   tangwei
 * Date:     2021/3/12 10:11
 * Description: 方法请求切面
 */
@Aspect
@Component
public class EagleEyeAspect {

    private static final Logger log = LoggerFactory.getLogger(EagleEyeAspect.class);

    @Pointcut("@annotation(com.tangv.feature.annotation.EagleEye)")
    public void eagle() {}

    @Around("eagle()")
    public Object printRequest(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.currentTimeMillis();
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        EagleEye eagleEye = method.getAnnotation(EagleEye.class);
        String value = eagleEye.value();
        log.debug("======请求开始=====");
        log.debug("请求链接：{}", request.getRequestURI().toString());
        log.debug("接口描述：{}", value);
        log.debug("请求类型：{}", request.getMethod());
        log.debug("请求方法：{}.{}",signature.getDeclaringTypeName(), signature.getName());
        log.debug("请求IP：{}", request.getRemoteAddr());
        log.debug("请求入参：{}", JSONObject.toJSONString(pjp.getArgs()));
        Object result = pjp.proceed();
        long end = System.currentTimeMillis();
        log.debug("请求耗时：{}", end - begin);
        log.debug("请求返回：{}", JSONObject.toJSONString(result));
        log.debug("=====请求结束=====");
        return result;
    }
}