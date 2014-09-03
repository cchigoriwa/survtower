package com.survtower.business.member.integration;

import com.survtower.ws.api.IndicatorWebservice;
import com.survtower.ws.api.LookupDataWebservice;
import java.io.Serializable;

/**
 *
 * @author Charles Chigoriwa
 */
public interface IntegrationService extends Serializable{
    
    public IndicatorWebservice getIndicatorWebservice();
    
    public LookupDataWebservice getLookupDataWebservice();
    
}
