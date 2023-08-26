package org.nirvana.dynamic.datasource.config;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class DynamicDatasource {

    @Autowired
    private DynamicWriteDatasourceProperties writeDatasourceProperties;

    @Autowired
    private DynamicReadDatasourceProperties readDatasourceProperties;

    @Bean(name = "writeDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.write")
    public DataSource writeDatasource() {
        return DataSourceBuilder.create()
                .driverClassName(writeDatasourceProperties.getDriverClassName())
                .url(writeDatasourceProperties.getUrl())
                .username(writeDatasourceProperties.getName())
                .password(writeDatasourceProperties.getPassword())
                .build();
    }

    @Bean(name = "writeSqlSessionFactory")
    public SqlSessionFactory writeSqlSessionFactory(@Qualifier("writeDatasource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean(name = "writeSqlSessionTemplate")
    public SqlSessionTemplate writeSqlSessionTemplate(@Qualifier("writeSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * {@link ConfigurationProperties} 这个注解貌似没有起到作用
     * @return
     */
//    @Bean(name = "readDatasource")
//    @ConfigurationProperties(prefix = "spring.datasource.read")
//    public DataSource readDatasource() {
//        return DataSourceBuilder.create()
//                .driverClassName(readDatasourceProperties.getDriverClassName())
//                .url(readDatasourceProperties.getUrl())
//                .username(readDatasourceProperties.getName())
//                .password(readDatasourceProperties.getPassword())
//                .build();
//    }
//
//    @Bean(name = "readSqlSessionFactory")
//    public SqlSessionFactory readSqlSessionFactory(@Qualifier("readDatasource") DataSource dataSource) throws Exception {
//        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        return bean.getObject();
//    }
//
//    @Bean(name = "readSqlSessionTemplate")
//    public SqlSessionTemplate readSqlSessionTemplate(@Qualifier("readSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
}
