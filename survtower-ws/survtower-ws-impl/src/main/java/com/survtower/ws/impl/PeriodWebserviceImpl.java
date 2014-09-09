package com.survtower.ws.impl;

import com.survtower.business.common.domain.Period;
import com.survtower.business.common.service.PeriodService;
import com.survtower.ws.api.PeriodWebservice;
import com.survtower.ws.api.domain.PeriodCollectionPayload;
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
public class PeriodWebserviceImpl implements PeriodWebservice {

    @Autowired
    private PeriodService periodService;

    public PeriodCollectionPayload getPeriods(RequestMetaData requestMetaData) {

        List<Period> periods;
        
        Date startDate = null;
        Date endDate;
        if (requestMetaData != null) {
            if (requestMetaData.getMinimumDate() != null) {
                startDate = requestMetaData.getMinimumDate();
            }
        }

        if (startDate != null) {
            endDate = periodService.findMaximumUpdateDate(startDate);
        }else{
            endDate=periodService.findMaximumUpdateDate();
        }
        
        if(endDate!=null){
            if(startDate==null){
                //inclusive endDate (before or as at)-name is a bit misleading
                periods=periodService.findPeriodsUpdatedBefore(endDate);
            }else{
                //exclusive startDate and inclusive endDate
                periods=periodService.findPeriodsUpdatedAfter(startDate, endDate);
            }
        }else{
            periods=new ArrayList<Period>();
        }
        
        ResponseMetaData responseMetaData=new ResponseMetaData();
        responseMetaData.setMaximumDate(endDate);
        
        PeriodCollectionPayload periodCollectionPayload=new PeriodCollectionPayload();
        periodCollectionPayload.setPayloadMetaData(responseMetaData);
        periodCollectionPayload.setPeriods(periods);
        
        return periodCollectionPayload;
        
        
        

    }
}
