package org.nirvana.dynamic.datasource.config.properties;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
@ToString
@Configuration
@ConfigurationProperties(prefix = "spring.datasource.dynamic")
public class DynamicDatasourceProperties {

    private String READ_DATA_SOURCE_KEY = "read";
    private String WRITE_DATA_SOURCE_KEY = "write";

    private Map<String, DataSourceProperties> datasource = new LinkedHashMap<>();

    public DataSourceProperties getReadDataSource() {
        return datasource.get(READ_DATA_SOURCE_KEY);
    }

    public DataSourceProperties getWriteDataSource() {
        return datasource.get(WRITE_DATA_SOURCE_KEY);
    }
}
