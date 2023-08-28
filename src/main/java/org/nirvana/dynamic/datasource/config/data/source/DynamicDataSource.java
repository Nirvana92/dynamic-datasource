package org.nirvana.dynamic.datasource.config.data.source;

import lombok.extern.slf4j.Slf4j;
import org.nirvana.dynamic.datasource.DynamicDataSourceType;
import org.nirvana.dynamic.datasource.config.properties.DataSourceProperties;
import org.nirvana.dynamic.datasource.config.properties.DynamicDatasourceProperties;
import org.nirvana.dynamic.datasource.config.selector.DataSourceSelectorType;
import org.nirvana.dynamic.datasource.config.selector.DynamicDataSourceSelector;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class DynamicDataSource extends AbstractDynamicDataSource implements InitializingBean {

    @Autowired
    private DynamicDatasourceProperties datasourceProperties;

    /**
     * key 是数据类型, 参考{@link DynamicDataSourceType}
     */
    Map<Integer, DataSource> dataSourceMap = new HashMap<>();

    @Override
    public Connection getConnection() throws SQLException {
        Integer dataSourceType = DynamicDataSourceSelector.getDataSourceType();
        log.info("get-connection-data-source-type: {}", dataSourceType);
        return dataSourceMap.get(dataSourceType).getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        Integer dataSourceType = DynamicDataSourceSelector.getDataSourceType();
        log.info("get-connection-with-param-data-source-type: {}", dataSourceType);
        return dataSourceMap.get(dataSourceType).getConnection(username, password);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        DynamicDataSourceSelector dataSourceSelector
                = DynamicDataSourceSelector.chooseDataSourceSelector(DataSourceSelectorType.HIKARI);

        log.info("datasource-selector: {}", datasourceProperties);

        dataSourceMap.put(DynamicDataSourceType.READ.getType(), dataSourceSelector.newInstance(datasourceProperties.getReadDataSource()));
        dataSourceMap.put(DynamicDataSourceType.WRITE.getType(), dataSourceSelector.newInstance(datasourceProperties.getWriteDataSource()));
    }
}
