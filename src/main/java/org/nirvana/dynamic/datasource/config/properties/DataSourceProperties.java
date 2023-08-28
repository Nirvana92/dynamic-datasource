package org.nirvana.dynamic.datasource.config.properties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class DataSourceProperties {

    private String driverClassName;

    private String url;

    private String name;

    private String password;
}
