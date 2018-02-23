package com.abin.lee.spring.boot.hive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by abin on 2018/2/22 20:59.
 * spring-boot-hive1
 * com.abin.lee.spring.boot.hive
 */
@SpringBootApplication
public class SpringBootHiveApplication extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootHiveApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootHiveApplication.class, args);
    }


}