package com.survtower.business.member.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

/**
 *
 * @author Charles Chigoriwa
 */
@Configuration
public class EmailConfiguration {

    @Configuration
    @Profile("development")
    @ImportResource("classpath:com/survtower/business/member/config/mail_config_dev.xml")
    static class Development {
    }

    @Configuration
    @Profile("test")
    @ImportResource("classpath:com/survtower/business/member/config/mail_config_test.xml")
    static class Test {
    }

    @Configuration
    @Profile("production")
    @ImportResource("classpath:com/survtower/business/member/config/mail_config_prod.xml")
    static class Production {
    }
}
