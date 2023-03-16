package com.tangv.feature.config.interceptors; /**
 * shipper.id Inc.
 * Copyright (c) 2018-2022 All Rights Reserved.
 */

import com.tangv.feature.consts.CommonConstants;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Proxy;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @author tang wei
 * @version MybatisSqlLogInterceptor.java, v 0.1 2023/2/15 15:59 tang wei Exp $
 * SQL拦截器，美化SQL日志打印，打印SQL执行时间
 */

@Intercepts({@Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
        @Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
        @Signature(type = StatementHandler.class, method = "batch", args = {Statement.class})})
public class MybatisSqlLogInterceptor implements Interceptor {
    private final Pattern selectPattern =
            Pattern.compile("select(\\s.*)from(\\s.*(where\\s.*)?)", Pattern.CASE_INSENSITIVE);
    private final int SQL_SPLIT_LENGTH = 30;
    private final int MAX_RESULT_LENGTH = 20;
    private final int ZERO = 0;
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.profiles.active}")
    private String env;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (CommonConstants.ENV_PROD.equals(env)) {
            return invocation.proceed();
        }
        StopWatch timeWatch = new StopWatch();
        timeWatch.start();

        Statement statement;
        Object[] args = invocation.getArgs();
        Object firstArg = args[0];
        if (Proxy.isProxyClass(firstArg.getClass())) {
            statement = (Statement) SystemMetaObject.forObject(firstArg).getValue("h.statement");
        } else {
            statement = (Statement) firstArg;
        }

        MetaObject stmtMetaObj = SystemMetaObject.forObject(statement);
        try {
            statement = (Statement) stmtMetaObj.getValue("stmt.statement");
        } catch (Exception e) {

        }

        if (stmtMetaObj.hasGetter("delegate")) {
            try {
                statement = (Statement) stmtMetaObj.getValue("delegate");
            } catch (Exception e) {

            }
        }

        String sql = statement.toString();
        sql = sql.replaceAll("[\\s]+", " ");
        int index = indexOfSqlStart(sql);
        if (index > 0) {
            sql = sql.substring(index);
        }

        Object result = invocation.proceed();
        timeWatch.stop();

        String resultStr = result == null ? "" : result.toString();

        this.log.info(
                "    [sql]: ==> {} <==   |   [time]: ==> {} ms <==   |   [size]: ==> {} <==   |   [result]: ==> {}  <==",
                this.formatSql(sql),
                timeWatch.getTotalTimeMillis()
                , result instanceof Collection ? ((Collection) result).size() : ZERO,
                resultStr.length() > MAX_RESULT_LENGTH ?
                        resultStr.substring(0, MAX_RESULT_LENGTH) + " ..." : result);


        return result;
    }

    /**
     * 获取sql语句开头部分
     *
     * @param sql
     * @return
     */
    private int indexOfSqlStart(String sql) {
        String upperCaseSql = sql.toUpperCase();
        Set<Integer> set = new HashSet<>();
        set.add(upperCaseSql.indexOf("SELECT "));
        set.add(upperCaseSql.indexOf("UPDATE "));
        set.add(upperCaseSql.indexOf("INSERT "));
        set.add(upperCaseSql.indexOf("DELETE "));
        set.remove(-1);
        if (set.isEmpty()) {
            return -1;
        }
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list, Integer::compareTo);
        return list.get(0);
    }

    /**
     * 格式化sql
     *
     * @param sql
     * @return
     */
    private String formatSql(String sql) {
        return beautifySql(sql);
    }


    private String beautifySql(String sql) {
        sql = sql.replaceAll("[\\s\n ]+", " ");
        return sql;
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        }
        return target;
    }
}

