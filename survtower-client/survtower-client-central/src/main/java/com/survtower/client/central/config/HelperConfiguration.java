package com.survtower.client.central.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Charles Chigoriwa
 */
@Configuration
@ComponentScan(basePackages = {"com.survtower.client.central.helper", "com.survtower.client.central.bean.impl"})
public class HelperConfiguration {

}
