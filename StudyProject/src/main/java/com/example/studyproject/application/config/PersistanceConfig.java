package com.example.studyproject.application.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class PersistanceConfig {

    private final DataBaseConfiguration dataBaseConfiguration;

    public PersistanceConfig(DataBaseConfiguration dataBaseConfiguration) {
        this.dataBaseConfiguration = dataBaseConfiguration;
    }

    @Bean
    public DataSource postgresDataSource(){
        HikariDataSource ds = new HikariDataSource();
        ds.setPassword(dataBaseConfiguration.getPassword());
        ds.setUsername(dataBaseConfiguration.getUsername());
        ds.setJdbcUrl(dataBaseConfiguration.getJdbcUrl());
        return ds;
    }
}
