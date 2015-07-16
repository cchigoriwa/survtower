package com.survtower.ws.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Charles Chigoriwa
 */
@Configuration
@ComponentScan(basePackages = {"com.survtower.ws.impl","com.survtower.ws.impl.version2"})
public class CentralWebserviceConfiguration {
    
}
