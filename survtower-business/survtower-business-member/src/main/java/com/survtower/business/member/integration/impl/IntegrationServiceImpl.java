package com.survtower.business.member.integration.impl;

import com.survtower.business.member.domain.CentralSecurity;
import com.survtower.business.member.integration.IntegrationService;
import com.survtower.business.member.service.CentralSecurityService;
import com.survtower.ws.api.DataElementWebService;
import com.survtower.ws.api.DataSourceCategoryWebService;
import com.survtower.ws.api.DataSourceWebService;
import com.survtower.ws.api.FrequencyWebService;
import com.survtower.ws.api.IndicatorGroupWebService;
import com.survtower.ws.api.IndicatorTypeWebService;
import com.survtower.ws.api.IndicatorWebService;
import com.survtower.ws.api.LookupDataWebService;
import com.survtower.ws.api.MemberWebService;
import com.survtower.ws.api.PeriodWebService;
import com.survtower.ws.api.ProgramWebService;
import com.survtower.ws.api.SurveillanceWebService;
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
    public IndicatorWebService getIndicatorWebService() {
        return getWebServiceTarget().proxy(IndicatorWebService.class);
    }

    @Override
    public LookupDataWebService getLookupDataWebService() {
        return getWebServiceTarget().proxy(LookupDataWebService.class);
    }

    @Override
    public MemberWebService getMemberWebService() {
        return getWebServiceTarget().proxy(MemberWebService.class);
    }

    protected ResteasyWebTarget getWebServiceTarget() {
        ResteasyClient client = new ResteasyClientBuilder().build();
        CentralSecurity centralSecurity = centralSecurityService.find();
        String centralServerUrl = null;
        if (centralSecurity != null) {
            //Will consider encryption and decryption of member key
            client.register(new BasicAuthentication(centralSecurity.getMemberID(), centralSecurity.getMemberKey()));
            centralServerUrl = centralSecurity.getCentralServerUrl();
        }

        if (centralServerUrl == null) {
            centralServerUrl = environment.getRequiredProperty("central.webservice.url");
        }

        ResteasyWebTarget target = client.target(centralServerUrl);
        return target;
    }

    @Override
    public DataSourceCategoryWebService getDataSourceCategoryWebService() {
        return getWebServiceTarget().proxy(DataSourceCategoryWebService.class);
    }

    @Override
    public PeriodWebService getPeriodWebService() {
        return getWebServiceTarget().proxy(PeriodWebService.class);
    }

    @Override
    public FrequencyWebService getFrequencyWebService() {
        return getWebServiceTarget().proxy(FrequencyWebService.class);
    }

    @Override
    public IndicatorTypeWebService getIndicatorTypeWebService() {
        return getWebServiceTarget().proxy(IndicatorTypeWebService.class);
    }

    @Override
    public ProgramWebService getProgramWebService() {
        return getWebServiceTarget().proxy(ProgramWebService.class);
    }

    @Override
    public IndicatorGroupWebService getIndicatorGroupWebService() {
        return getWebServiceTarget().proxy(IndicatorGroupWebService.class);
    }

    @Override
    public DataSourceWebService getDataSourceWebService() {
        return getWebServiceTarget().proxy(DataSourceWebService.class);
    }

    @Override
    public DataElementWebService getDataElementWebService() {
        return getWebServiceTarget().proxy(DataElementWebService.class);
    }

    @Override
    public SurveillanceWebService getSurveillanceWebService() {
        return getWebServiceTarget().proxy(SurveillanceWebService.class);
    }

}
