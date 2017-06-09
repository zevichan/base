package com.czw.function.sso.middleware.cas.client;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.swing.*;
import java.io.IOException;
import java.util.Properties;

/**
 * application.properties only can put in resouces/ or resources/config/ or src/ or src/config/
 *
 * @author ZeviChen , 2017/6/8 10:00
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HazelcastAutoConfiguration.class,
        MongoAutoConfiguration.class})
@EnableAdminServer
public class CASClient {

    public static void main(String[] args) throws IOException {
        SpringApplication app = new SpringApplication();
        app.run(args);

    }
}
