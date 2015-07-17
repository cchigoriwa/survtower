package com.survtower.client.member.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Charles Chigoriwa
 */
@Configuration
@ComponentScan(basePackages = {"com.survtower.client.member.helper","com.survtower.client.member.bean.impl"})
public class HelperConfiguration {
    
}
