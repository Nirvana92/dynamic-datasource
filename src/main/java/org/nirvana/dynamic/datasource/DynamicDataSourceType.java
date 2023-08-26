package org.nirvana.dynamic.datasource;

import lombok.Getter;

@Getter
public enum DynamicDataSourceType {
    READ(1, "读数据源"),
    WRITE(2, "写数据源");

    private Integer type;

    private String desc;

    DynamicDataSourceType(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
