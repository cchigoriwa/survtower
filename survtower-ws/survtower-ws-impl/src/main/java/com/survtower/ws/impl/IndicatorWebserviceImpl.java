package com.survtower.ws.impl;

import com.survtower.business.common.domain.Indicator;
import com.survtower.business.common.service.IndicatorService;
import com.survtower.ws.api.IndicatorWebservice;
import com.survtower.ws.api.domain.IndicatorCollectionPayload;
import com.survtower.ws.api.domain.RequestMetaData;
import com.survtower.ws.api.domain.ResponseMetaData;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Charles Chigoriwa
 */
@Component
public class IndicatorWebserviceImpl implements IndicatorWebservice {

    @Autowired
    private IndicatorService indicatorService;

    public IndicatorCollectionPayload getIndicators(RequestMetaData requestMetaData) {

        List<Indicator> indicators;
        
        Date startDate = null;
        Date endDate;
        if (requestMetaData != null) {
            if (requestMetaData.getMinimumDate() != null) {
                startDate = requestMetaData.getMinimumDate();
            }
        }

        if (startDate != null) {
            endDate = indicatorService.findMaximumUpdateDate(startDate);
        }else{
            endDate=indicatorService.findMaximumUpdateDate();
        }
        
        if(endDate!=null){
            if(startDate==null){
                //inclusive endDate (before or as at)-name is a bit misleading
                indicators=indicatorService.findIndicatorsUpdatedBefore(endDate);
            }else{
                //exclusive startDate and inclusive endDate
                indicators=indicatorService.findIndicatorsUpdatedAfter(startDate, endDate);
            }
        }else{
            indicators=new ArrayList<Indicator>();
        }
        
        ResponseMetaData responseMetaData=new ResponseMetaData();
        responseMetaData.setMaximumDate(endDate);
        
        IndicatorCollectionPayload indicatorCollectionPayload=new IndicatorCollectionPayload();
        indicatorCollectionPayload.setPayloadMetaData(responseMetaData);
        indicatorCollectionPayload.setIndicators(indicators);
        
        return indicatorCollectionPayload;
        
        
        

    }
}
