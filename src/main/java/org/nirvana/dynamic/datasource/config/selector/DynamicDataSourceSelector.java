package org.nirvana.dynamic.datasource.config.selector;

import cn.hutool.extra.spring.SpringUtil;
import org.nirvana.dynamic.datasource.DynamicDataSourceType;
import org.nirvana.dynamic.datasource.config.properties.DataSourceProperties;

import javax.sql.DataSource;
import java.util.Objects;

public interface DynamicDataSourceSelector {

    ThreadLocal<Integer> dataSourceThreadLocal = new ThreadLocal<>();

    /**
     * 设置数据源类型
     * @param dataSourceType
     */
    static void setDataSourceType(DynamicDataSourceType dataSourceType) {
        dataSourceThreadLocal.set(dataSourceType.getType());
    }

    static Integer getDataSourceType() {
        Integer type = dataSourceThreadLocal.get();
        if(Objects.isNull(type)) {
            dataSourceThreadLocal.remove();
            return DynamicDataSourceType.WRITE.getType();
        }
        return type;
    }

    /**
     * 选择数据源选择器
     * @param selectorType 选择器类型
     * @return 返回动态数据源选择器
     */
    static DynamicDataSourceSelector chooseDataSourceSelector(DataSourceSelectorType selectorType) {
        if(Objects.equals(selectorType, DataSourceSelectorType.HIKARI)) {
            return SpringUtil.getBean(HikariDataSourceSelector.class);
        }

        return SpringUtil.getBean(HikariDataSourceSelector.class);
    }

    DataSource newInstance(DataSourceProperties dataSourceProperties);
}
