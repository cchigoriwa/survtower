package com.survtower.business.member.integration;


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
import java.io.Serializable;

/**
 *
 * @author Charles Chigoriwa
 */
public interface IntegrationService extends Serializable{
    
    public IndicatorWebService getIndicatorWebService();
    
    public MemberWebService getMemberWebService();
    
    public LookupDataWebService getLookupDataWebService();
    
    public DataSourceCategoryWebService getDataSourceCategoryWebService();
    
    public PeriodWebService getPeriodWebService();
    
    public FrequencyWebService getFrequencyWebService();
    
    public IndicatorTypeWebService getIndicatorTypeWebService();
    
    public ProgramWebService getProgramWebService();
    
    public IndicatorGroupWebService getIndicatorGroupWebService();
    
    public DataSourceWebService getDataSourceWebService();
    
    public DataElementWebService getDataElementWebService();
    
    public SurveillanceWebService getSurveillanceWebService();
    
}
