package com.czw.function.sso.middleware.cas.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * application.properties only can put in resouces/ or resources/config/ or src/ or src/config/
 *
 * @author ZeviChen , 2017/6/8 10:00
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HazelcastAutoConfiguration.class,
        MongoAutoConfiguration.class})
public class CASClient {

    public static void main(String[] args) {
        SpringApplication.run(CASClient.class, args);
    }
}
