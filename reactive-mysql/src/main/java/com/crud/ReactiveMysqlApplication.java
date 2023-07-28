package com.crud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import java.sql.Connection;
@SpringBootApplication
//@EnableAutoConfiguration(exclude = {DBconfig.class})
//@EnableConfigurationProperties
//@EnableR2dbcRepositories(basePackages = {"com.crud.repo"})
//@EnableR2dbcRepositories
public class ReactiveMysqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveMysqlApplication.class, args);
        System.out.println("hello sameer");
        System.out.println("/..............");

    }

}
