package com.survtower.ws.impl;

import com.survtower.business.common.domain.IndicatorType;
import com.survtower.business.common.service.IndicatorTypeService;
import com.survtower.ws.api.IndicatorTypeWebservice;
import com.survtower.ws.api.domain.IndicatorTypeCollectionPayload;
import com.survtower.ws.api.domain.RequestMetaData;
import com.survtower.ws.api.domain.ResponseMetaData;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Takunda Dhlakama
 */
@Component
public class IndicatorTypeWebserviceImpl implements IndicatorTypeWebservice {

    @Autowired
    private IndicatorTypeService indicatorTypeService;

    public IndicatorTypeCollectionPayload getIndicatorTypes(RequestMetaData requestMetaData) {

        List<IndicatorType> indicatorTypes;
        
        Date startDate = null;
        Date endDate;
        if (requestMetaData != null) {
            if (requestMetaData.getMinimumDate() != null) {
                startDate = requestMetaData.getMinimumDate();
            }
        }

        if (startDate != null) {
            endDate = indicatorTypeService.findMaximumUpdateDate(startDate);
        }else{
            endDate=indicatorTypeService.findMaximumUpdateDate();
        }
        
        if(endDate!=null){
            if(startDate==null){
                //inclusive endDate (before or as at)-name is a bit misleading
                indicatorTypes=indicatorTypeService.findIndicatorTypesUpdatedBefore(endDate);
            }else{
                //exclusive startDate and inclusive endDate
                indicatorTypes=indicatorTypeService.findIndicatorTypesUpdatedAfter(startDate, endDate);
            }
        }else{
            indicatorTypes=new ArrayList<IndicatorType>();
        }
        
        ResponseMetaData responseMetaData=new ResponseMetaData();
        responseMetaData.setMaximumDate(endDate);
        
        IndicatorTypeCollectionPayload indicatorTypeCollectionPayload=new IndicatorTypeCollectionPayload();
        indicatorTypeCollectionPayload.setPayloadMetaData(responseMetaData);
        indicatorTypeCollectionPayload.setIndicatorTypes(indicatorTypes);
        
        return indicatorTypeCollectionPayload;
        
        
        

    }
}
