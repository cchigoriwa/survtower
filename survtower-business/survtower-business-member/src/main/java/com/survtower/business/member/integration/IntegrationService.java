package com.survtower.business.member.integration;

import com.survtower.ws.api.DataElementWebservice;
import com.survtower.ws.api.DataSourceCategoryWebservice;
import com.survtower.ws.api.DataSourceWebservice;
import com.survtower.ws.api.FrequencyWebservice;
import com.survtower.ws.api.IndicatorGroupWebservice;
import com.survtower.ws.api.IndicatorTypeWebservice;
import com.survtower.ws.api.IndicatorWebservice;
import com.survtower.ws.api.LookupDataWebservice;
import com.survtower.ws.api.MemberWebservice;
import com.survtower.ws.api.PeriodWebservice;
import com.survtower.ws.api.ProgramWebservice;
import java.io.Serializable;

/**
 *
 * @author Charles Chigoriwa
 */
public interface IntegrationService extends Serializable{
    
    public IndicatorWebservice getIndicatorWebservice();
    
    public MemberWebservice getMemberWebservice();
    
    public LookupDataWebservice getLookupDataWebservice();
    
    public DataSourceCategoryWebservice getDataSourceCategoryWebservice();
    
    public PeriodWebservice getPeriodWebservice();
    
    public FrequencyWebservice getFrequencyWebservice();
    
    public IndicatorTypeWebservice getIndicatorTypeWebservice();
    
    public ProgramWebservice getProgramWebservice();
    
    public IndicatorGroupWebservice getIndicatorGroupWebservice();
    
    public DataSourceWebservice getDataSourceWebservice();
    
    public DataElementWebservice getDataElementWebservice();
    
}
