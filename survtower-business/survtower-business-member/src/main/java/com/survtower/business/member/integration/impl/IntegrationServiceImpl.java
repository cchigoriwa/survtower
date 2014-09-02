package com.survtower.business.member.integration.impl;

import com.survtower.business.member.integration.IntegrationService;
import com.survtower.ws.api.IndicatorWebservice;
import javax.annotation.Resource;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 *
 * @author Charles Chigoriwa
 */
@Component
public class IntegrationServiceImpl implements IntegrationService{
    
    @Resource
    protected Environment environment;

    @Override
    public IndicatorWebservice getIndicatorWebservice() {
        return getWebserviceTarget().proxy(IndicatorWebservice.class);
        
    }
     protected ResteasyWebTarget getWebserviceTarget() {
        ResteasyClient client = new ResteasyClientBuilder().build();
        //client.register(new BasicAuthentication("user", "pass"));
        ResteasyWebTarget target = client.target(environment.getRequiredProperty("central.webservice.url"));
        return target;
    }
    
}
