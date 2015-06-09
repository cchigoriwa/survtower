package com.survtower.client.member.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author charlesc
 */
@Configuration
public class DatabaseConfiguration {
    
    @Configuration
    @Profile("development")
    @PropertySource("classpath:com/survtower/client/member/config/jdbc_dev.properties")
    static class Development {
    }

    @Configuration
    @Profile("test")
    @PropertySource("classpath:com/survtower/client/member/config/jdbc_test.properties")
    static class Test {
    }

    @Configuration
    @Profile("production")
    @PropertySource("classpath:com/survtower/client/member/config/jdbc_prod.properties")
    static class Production {
    }
}
