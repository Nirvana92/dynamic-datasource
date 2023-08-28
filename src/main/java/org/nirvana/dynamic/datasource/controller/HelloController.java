package org.nirvana.dynamic.datasource.controller;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.nirvana.dynamic.datasource.config.properties.DynamicDatasourceProperties;
import org.nirvana.dynamic.datasource.entity.Reservation;
import org.nirvana.dynamic.datasource.mapper.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@Slf4j
@RestController
public class HelloController {

    @Autowired
    DynamicDatasourceProperties dynamicWriteDatasourceProperties;

    @Autowired
    private ReservationMapper reservationMapper;

    @GetMapping("/hello")
    public String hello() throws SQLException {
        Reservation reservation = reservationMapper.selectById(1692353826595885058L);
        log.info("reservation: {}", JSONUtil.toJsonStr(reservation));

        return "success";
    }
}
