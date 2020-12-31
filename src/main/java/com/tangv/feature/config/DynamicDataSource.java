package com.tangv.feature.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * author:   tangwei
 * Date:     2020/12/30 16:27
 * Description:
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceHolder.getDataSources();
    }
}