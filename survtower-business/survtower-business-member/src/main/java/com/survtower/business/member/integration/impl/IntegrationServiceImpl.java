package com.survtower.business.member.integration.impl;

import com.survtower.business.member.domain.CentralSecurity;
import com.survtower.business.member.integration.IntegrationService;
import com.survtower.business.member.service.CentralSecurityService;
import com.survtower.ws.api.DataSourceCategoryWebservice;
import com.survtower.ws.api.FrequencyWebservice;
import com.survtower.ws.api.IndicatorGroupWebservice;
import com.survtower.ws.api.IndicatorTypeWebservice;
import com.survtower.ws.api.IndicatorWebservice;
import com.survtower.ws.api.LookupDataWebservice;
import com.survtower.ws.api.MemberWebservice;
import com.survtower.ws.api.PeriodWebservice;
import com.survtower.ws.api.ProgramWebservice;
import javax.annotation.Resource;
import org.jboss.resteasy.client.jaxrs.BasicAuthentication;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 *
 * @author Charles Chigoriwa
 */
@Component
public class IntegrationServiceImpl implements IntegrationService {

    @Resource
    protected Environment environment;

    @Autowired
    protected CentralSecurityService centralSecurityService;

    @Override
    public IndicatorWebservice getIndicatorWebservice() {
        return getWebserviceTarget().proxy(IndicatorWebservice.class);
    }

    @Override
    public LookupDataWebservice getLookupDataWebservice() {
        return getWebserviceTarget().proxy(LookupDataWebservice.class);
    }
    
    @Override
    public MemberWebservice getMemberWebservice() {
        return getWebserviceTarget().proxy(MemberWebservice.class);
    }
    
    

    protected ResteasyWebTarget getWebserviceTarget() {
        ResteasyClient client = new ResteasyClientBuilder().build();
        CentralSecurity centralSecurity=centralSecurityService.find();
        if(centralSecurity!=null){
            //Will consider encryption and decryption of member key
           client.register(new BasicAuthentication(centralSecurity.getMemberID(), centralSecurity.getMemberKey()));
        }
        ResteasyWebTarget target = client.target(environment.getRequiredProperty("central.webservice.url"));
        return target;
    }

    @Override
    public DataSourceCategoryWebservice getDataSourceCategoryWebservice() {
         return getWebserviceTarget().proxy(DataSourceCategoryWebservice.class);
    }

    @Override
    public PeriodWebservice getPeriodWebservice() {
        return getWebserviceTarget().proxy(PeriodWebservice.class);
    }

    @Override
    public FrequencyWebservice getFrequencyWebservice() {
        return getWebserviceTarget().proxy(FrequencyWebservice.class);
    }

    @Override
    public IndicatorTypeWebservice getIndicatorTypeWebservice() {
        return getWebserviceTarget().proxy(IndicatorTypeWebservice.class);
    }

    @Override
    public ProgramWebservice getProgramWebservice() {
        return getWebserviceTarget().proxy(ProgramWebservice.class);
    }

    @Override
    public IndicatorGroupWebservice getIndicatorGroupWebservice() {
        return getWebserviceTarget().proxy(IndicatorGroupWebservice.class);
    }

    

}
