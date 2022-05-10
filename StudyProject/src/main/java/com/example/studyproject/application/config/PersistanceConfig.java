package com.example.studyproject.application.config;

import com.zaxxer.hikari.HikariDataSource;
import io.r2dbc.spi.ConnectionFactories;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.postgresql.core.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class PersistanceConfig {

    @Bean
    public DataSource postgresDataSource(DataBaseConfiguration dataBaseConfiguration){
        HikariDataSource ds = new HikariDataSource();
        ds.setPassword(dataBaseConfiguration.getPassword());
        ds.setUsername(dataBaseConfiguration.getUsername());
        ds.setJdbcUrl(dataBaseConfiguration.getJdbcUrl());
        return ds;
    }
    @Bean
    public DSLContext dslContext(DataSource postgresDataSource) {
        DSLContext ctx = DSL.using(postgresDataSource, SQLDialect.POSTGRES);
        return  ctx;
    }
}
