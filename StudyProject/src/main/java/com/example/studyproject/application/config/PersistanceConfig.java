package com.example.studyproject.application.config;

import com.zaxxer.hikari.HikariConfig;
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
        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");
        config.setUsername(dataBaseConfiguration.getUser());
        config.setPassword(dataBaseConfiguration.getPassword());
        config.addDataSourceProperty("databaseName", dataBaseConfiguration.getDatabase());
        config.setJdbcUrl(dataBaseConfiguration.getJdbcUrl());
        HikariDataSource ds = new HikariDataSource(config);
        return ds;
    }
}
