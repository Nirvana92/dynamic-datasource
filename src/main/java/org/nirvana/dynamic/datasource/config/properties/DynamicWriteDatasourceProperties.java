package org.nirvana.dynamic.datasource.config.properties;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ToString
@Configuration
@ConfigurationProperties(prefix = "spring.datasource.write")
public class DynamicWriteDatasourceProperties {

    private String driverClassName;

    private String url;

    private String name;

    private String password;
}
