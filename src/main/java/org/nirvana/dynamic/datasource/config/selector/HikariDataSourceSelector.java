package org.nirvana.dynamic.datasource.config.selector;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.nirvana.dynamic.datasource.config.properties.DataSourceProperties;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Slf4j
@Component
public class HikariDataSourceSelector implements DynamicDataSourceSelector {

    @Override
    public DataSource newInstance(DataSourceProperties dataSourceProperties) {
        log.info("new-data-source-instance, properties: {}", dataSourceProperties);

        return new HikariDataSource(convertConfig(dataSourceProperties));
    }

    HikariConfig convertConfig(DataSourceProperties dataSourceProperties) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(dataSourceProperties.getDriverClassName());
        hikariConfig.setJdbcUrl(dataSourceProperties.getUrl());
        hikariConfig.setUsername(dataSourceProperties.getName());
        hikariConfig.setPassword(dataSourceProperties.getPassword());

        return hikariConfig;
    }
}
