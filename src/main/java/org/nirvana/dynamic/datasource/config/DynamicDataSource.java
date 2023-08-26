package org.nirvana.dynamic.datasource.config;

import lombok.extern.slf4j.Slf4j;
import org.nirvana.dynamic.datasource.DynamicDataSourceType;
import org.nirvana.dynamic.datasource.config.properties.DynamicReadDatasourceProperties;
import org.nirvana.dynamic.datasource.config.properties.DynamicWriteDatasourceProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
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
    private DynamicWriteDatasourceProperties writeDatasourceProperties;

    @Autowired
    private DynamicReadDatasourceProperties readDatasourceProperties;

    /**
     * key 是数据类型, 参考{@link DynamicDataSourceType}
     */
    Map<Integer, DataSource> dataSourceMap = new HashMap<>();

    @Override
    public Connection getConnection() throws SQLException {
        Integer dataSourceType = DynamicSelector.getDataSourceType();
        log.info("get-connection-data-source-type: {}", dataSourceType);
        return dataSourceMap.get(dataSourceType).getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        Integer dataSourceType = DynamicSelector.getDataSourceType();
        log.info("get-connection-with-param-data-source-type: {}", dataSourceType);
        return dataSourceMap.get(dataSourceType).getConnection();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        dataSourceMap.put(DynamicDataSourceType.READ.getType(), DataSourceBuilder.create()
                .driverClassName(readDatasourceProperties.getDriverClassName())
                .url(readDatasourceProperties.getUrl())
                .username(readDatasourceProperties.getName())
                .password(readDatasourceProperties.getPassword())
                .build());

        dataSourceMap.put(DynamicDataSourceType.WRITE.getType(), DataSourceBuilder.create()
                .driverClassName(writeDatasourceProperties.getDriverClassName())
                .url(writeDatasourceProperties.getUrl())
                .username(writeDatasourceProperties.getName())
                .password(writeDatasourceProperties.getPassword())
                .build());
    }
}
