package org.nirvana.dynamic.datasource.config;

import org.nirvana.dynamic.datasource.DynamicDataSourceType;

import java.util.Objects;

public class DynamicSelector {

    private static ThreadLocal<Integer> dataSourceThreadLocal = new ThreadLocal<>();

    /**
     * 设置数据源类型
     * @param dataSourceType
     */
    public static void setDataSourceType(DynamicDataSourceType dataSourceType) {
        dataSourceThreadLocal.set(dataSourceType.getType());
    }

    public static Integer getDataSourceType() {
        Integer type = dataSourceThreadLocal.get();
        if(Objects.isNull(type)) {
            return DynamicDataSourceType.WRITE.getType();
        }
        return type;
    }
}
