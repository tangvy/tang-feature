package com.tangv.feature.config;

import com.tangv.common.enums.DataBaseType;

/**
 * author:   tangwei
 * Date:     2020/12/30 17:31
 * Description:
 */
public class DataSourceHolder {

    private static final ThreadLocal<DataBaseType> datasources = ThreadLocal.withInitial(() ->DataBaseType.CANAL_TANGV);

    public static void setDatasources(DataBaseType dataBaseType) {
        datasources.set(dataBaseType);
    }

    public static DataBaseType getDataSources() {
        return datasources.get();
    }

    public static void remove() {
        datasources.remove();
    }

}