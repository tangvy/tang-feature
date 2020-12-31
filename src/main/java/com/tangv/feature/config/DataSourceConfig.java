package com.tangv.feature.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.ExecutorType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * author:   tangwei
 * Date:     2020/12/30 14:52
 * Description: 数据源配置
 */

@Configuration
/**
 * 关闭springboot数据源自动配置，否则出现异常The dependencies of some of the beans in the application context form a cycle:
 */
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@MapperScan(value = "com.tangv.**.dao.**", sqlSessionFactoryRef = "sqlSessionFactory")
public class DataSourceConfig {

    /**
     * 数据源:mysql数据库feature
     */
    @Bean(name = "feature")
    @ConfigurationProperties(prefix = "spring.datasource.druid.tang-feature")
    public DataSource feature() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 数据源:mysql数据库feature1
     */
    @Bean(name = "feature1")
    @ConfigurationProperties(prefix = "spring.datasource.druid.tang-feature1")
    public DataSource feature1() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 自定义数据源DynamicDataSource，继承AbstractRoutingDataSource，把多个数据源管理起来
     */
    @Bean
    @Primary
    public DynamicDataSource dataSource(@Qualifier("feature") DataSource feature, @Qualifier(value = "feature1") DataSource feature1) {
        Map<Object, Object> targetDataSources = new HashMap<>(2);
        targetDataSources.put(DataBaseType.TANG_FEATURE,feature);
        targetDataSources.put(DataBaseType.TANG_FEATURE1,feature1);
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(feature);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        return dynamicDataSource;
    }

    /**
     * SQLSession工厂
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DynamicDataSource dynamicDataSource) throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/*.xml"));
        return sqlSessionFactoryBean;
    }

    /**
     * 配置一个可以进行批量执行的sqlSession
     */
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactoryBean.getObject(), ExecutorType.BATCH);
        return sqlSessionTemplate;
    }

    /**
     * spring事务管理
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DynamicDataSource dynamicDataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dynamicDataSource);
        return transactionManager;
    }
}