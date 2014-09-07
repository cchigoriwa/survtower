package com.survtower.ws.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 *
 * @author Charles Chigoriwa
 */
@Configuration
@ImportResource("classpath:com/survtower/ws/config/appSecurityContext.xml")
public class AppSecurityConfiguration {    
}
