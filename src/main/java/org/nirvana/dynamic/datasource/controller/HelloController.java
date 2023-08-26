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

@Slf4j
@RestController
public class HelloController {

    @Autowired
    DynamicWriteDatasourceProperties dynamicWriteDatasourceProperties;

    @Autowired
    DynamicReadDatasourceProperties dynamicReadDatasourceProperties;

    @Autowired
    private ReservationMapper reservationMapper;

    @GetMapping("/hello")
    public String hello() {
        log.info("write-data-source: {}", dynamicWriteDatasourceProperties);
        log.info("read-data-source: {}", dynamicReadDatasourceProperties);

        Reservation reservation = reservationMapper.selectById(1);

        return "success";
    }
}
