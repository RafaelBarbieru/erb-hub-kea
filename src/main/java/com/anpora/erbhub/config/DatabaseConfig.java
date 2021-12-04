package com.anpora.erbhub.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author Rafael Barbieru, Popular Belbase, Anton Kamenov
 */
@Configuration
public class DatabaseConfig {


    /**
     * MYSQL CONNECTION
     * @return
     */
    /*@Bean
    @Primary
    @ConfigurationProperties(prefix="spring.jpa.datasource")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();

    }*/

    /**
     * MONGODB CONNECTION
     * @return
     */
    /*@Bean
    @ConfigurationProperties(prefix="spring.mongodb-datasource")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }*/

}
