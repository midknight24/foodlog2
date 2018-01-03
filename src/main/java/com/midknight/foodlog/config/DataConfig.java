package com.midknight.foodlog.config;

//import liquibase.integration.spring.SpringLiquibase;
import liquibase.integration.spring.SpringLiquibase;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.management.MXBean;
import javax.sql.DataSource;

/**
 * Created by Onlyme on 9/27/2017.
 */

@Configuration
@PropertySource("app.properties")
public class DataConfig {
    @Autowired
    private Environment env;

    @Bean
    public LocalSessionFactoryBean sessioinFactory(){
        Resource config = new ClassPathResource("hibernate.cfg.xml");
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setConfigLocation(config);
        sessionFactory.setPackagesToScan(env.getProperty("foodlog.entity.package"));
        sessionFactory.setDataSource(dataSource());
        return sessionFactory;
    }

    @Bean
    @LiquibaseDataSource
    public DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        //Driver class name
        ds.setDriverClassName(env.getProperty("foodlog.db.driver"));
        //Set URL
        ds.setUrl(env.getProperty("foodlog.db.url"));
        //Set username & password
        ds.setUsername(env.getProperty("foodlog.db.username"));
        ds.setPassword(env.getProperty("foodlog.db.password"));
        return ds;
    }

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:liquibase-changeLog.xml");
        liquibase.setDataSource(dataSource());
        return liquibase;
    }




}
