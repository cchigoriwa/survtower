package com.survtower.business.member.integration;

import com.survtower.ws.api.IndicatorWebservice;
import com.survtower.ws.api.LookupDataWebservice;
import com.survtower.ws.api.MemberWebservice;
import java.io.Serializable;

/**
 *
 * @author Charles Chigoriwa
 */
public interface IntegrationService extends Serializable{
    
    public IndicatorWebservice getIndicatorWebservice();
    
    public MemberWebservice getMemberWebservice();
    
    public LookupDataWebservice getLookupDataWebservice();
    
}
