package com.survtower.ws.impl;

import com.survtower.business.common.domain.Lookup;
import com.survtower.business.common.service.IndicatorService;
import com.survtower.ws.api.LookupDataWebservice;
import com.survtower.ws.api.domain.LookupMetaDataCollectionPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Charles Chigoriwa
 */
@Component
public class LookupDataWebserviceImpl implements LookupDataWebservice{
    
    @Autowired
    private IndicatorService indicatorService;

    public LookupMetaDataCollectionPayload getLookupMetaDataList() {
        LookupMetaDataCollectionPayload payload=new LookupMetaDataCollectionPayload();        
        payload.add(Lookup.INDICATOR,indicatorService.findMaximumUpdateDate());
        return payload;
    }
    
}
