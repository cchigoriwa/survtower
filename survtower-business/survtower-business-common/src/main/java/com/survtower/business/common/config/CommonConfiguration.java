/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survtower.business.common.config;

import com.survtower.business.common.service.impl.DoNothingPasswordEncoderImpl;
import com.survtower.business.common.service.impl.PasswordEncoderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.encoding.PasswordEncoder;

/**
 *
 * @author charlesc
 */
@Configuration
public class CommonConfiguration {

    @Configuration
    @Profile("development")
    static class Development {
        @Bean(name = "passwordEncoder")
        public PasswordEncoder getPasswordEncoder() {
            return new DoNothingPasswordEncoderImpl();
        }
    }

    @Configuration
    @Profile("test")
    static class Test {
        @Bean(name = "passwordEncoder")
        public PasswordEncoder getPasswordEncoder() {
            return new PasswordEncoderImpl();
        }
    }

    @Configuration
    @Profile("production")
    static class Production {

        @Bean(name = "passwordEncoder")
        public PasswordEncoder getPasswordEncoder() {
            return new PasswordEncoderImpl();
        }

    }
}
