package com.tangv.feature.aspect;

import com.tangv.common.aspect.declare.IPojo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import java.util.Iterator;
import java.util.List;

/**
 * Description:
 * Author:      TangWei
 * Date:        2020/9/17 10:26 上午
 */
@Configuration
@Aspect
public class InsertUpdateAspect {

    public InsertUpdateAspect(){}

    //@Pointcut("execution(* tk.mybatis.mapper.common.Mapper.update*(..))")
    @Pointcut("execution(* com.tangv.feature.service.GoodsService.test(..))")
    public void aroundUpdate() {}

    @Pointcut("execution(* tk.mybatis.mapper.common.Mapper.insert*(..))")
    public void aroundInsert() {}

    @Pointcut("execution(* tk.mybatis.mapper.common.special.InsertListMapper.insertList(..))")
    public void aroundInsertList() {}

    @Around("aroundUpdate()")
    public Object aroundUpdateAspect(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();
        if (args.length == 0) {
            return joinPoint.proceed(args);
        }else{
            Object entry = args[0];
            if (entry != null && entry instanceof IPojo) {
                IPojo iPojo = (IPojo) entry;
                iPojo.prepareUpdate();
            }
            return joinPoint.proceed(args);
        }
    }

    @Around("aroundInsert()")
    public Object aroundInsertAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (args.length == 0) {
            return joinPoint.proceed(args);
        }else{
            Object entry = args[0];
            if (entry != null && entry instanceof IPojo) {
                IPojo iPojo = (IPojo) entry;
                iPojo.prepareInsert();
            }
            return joinPoint.proceed(args);
        }
    }

    @Around("aroundInsertList()")
    public Object aroundInsertListAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (args.length == 0) {
            return joinPoint.proceed(args);
        }else{
            Object entrys = args[0];
            if (!(entrys instanceof List)) {
                return joinPoint.proceed(args);
            }else {
                List entryList = (List) entrys;
                Iterator iterator = entryList.iterator();
                while (iterator.hasNext()) {
                    Object next = iterator.next();
                    if(next instanceof IPojo) {
                        IPojo iPojo = (IPojo) next;
                        iPojo.prepareInsert();
                    }
                }
                return joinPoint.proceed(args);
            }
        }
    }

}
