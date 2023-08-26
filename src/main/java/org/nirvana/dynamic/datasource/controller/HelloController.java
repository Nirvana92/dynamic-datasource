package org.nirvana.dynamic.datasource.controller;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.nirvana.dynamic.datasource.config.DynamicReadDatasourceProperties;
import org.nirvana.dynamic.datasource.config.DynamicWriteDatasourceProperties;
import org.nirvana.dynamic.datasource.entity.Reservation;
import org.nirvana.dynamic.datasource.mapper.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@RestController
public class HelloController {

    @Autowired
    DynamicWriteDatasourceProperties dynamicWriteDatasourceProperties;

    @Autowired
    DynamicReadDatasourceProperties dynamicReadDatasourceProperties;

    @Autowired
    private ReservationMapper reservationMapper;

    @Autowired
    private DataSource dataSource;

    @GetMapping("/hello")
    public String hello() throws SQLException {
//        log.info("write-data-source: {}", dynamicWriteDatasourceProperties);
//        log.info("read-data-source: {}", dynamicReadDatasourceProperties);

        log.info("data-source: {}", dataSource.getConnection());

        return "success";
    }
}
