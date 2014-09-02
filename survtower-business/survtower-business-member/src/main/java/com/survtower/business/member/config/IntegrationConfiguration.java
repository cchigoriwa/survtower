package com.survtower.business.member.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author Charles Chigoriwa
 */
@Configuration
public class IntegrationConfiguration {

    @Configuration
    @Profile("development")
    @PropertySource(value = {"classpath:com/survtower/business/member/config/app-config_dev.properties"})
    static class Development {
    }

    @Configuration
    @Profile("test")
    @PropertySource(value = {"classpath:com/survtower/business/member/config/app-config_test.properties"})
    static class Test {
    }

    @Configuration
    @Profile("production")
    @PropertySource(value = {"classpath:com/survtower/business/member/config/app-config_prod.properties"})
    static class Production {
    }
}
