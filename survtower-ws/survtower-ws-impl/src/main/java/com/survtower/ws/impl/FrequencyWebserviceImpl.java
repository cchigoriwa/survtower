package com.survtower.ws.impl;

import com.survtower.business.common.domain.Frequency;
import com.survtower.business.common.service.FrequencyService;
import com.survtower.ws.api.FrequencyWebservice;
import com.survtower.ws.api.domain.FrequencyCollectionPayload;
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
public class FrequencyWebserviceImpl implements FrequencyWebservice {

    @Autowired
    private FrequencyService frequencyService;

    public FrequencyCollectionPayload getFrequencys(RequestMetaData requestMetaData) {

        List<Frequency> frequencys;
        
        Date startDate = null;
        Date endDate;
        if (requestMetaData != null) {
            if (requestMetaData.getMinimumDate() != null) {
                startDate = requestMetaData.getMinimumDate();
            }
        }

        if (startDate != null) {
            endDate = frequencyService.findMaximumUpdateDate(startDate);
        }else{
            endDate=frequencyService.findMaximumUpdateDate();
        }
        
        if(endDate!=null){
            if(startDate==null){
                //inclusive endDate (before or as at)-name is a bit misleading
                frequencys=frequencyService.findFrequencysUpdatedBefore(endDate);
            }else{
                //exclusive startDate and inclusive endDate
                frequencys=frequencyService.findFrequencysUpdatedAfter(startDate, endDate);
            }
        }else{
            frequencys=new ArrayList<Frequency>();
        }
        
        ResponseMetaData responseMetaData=new ResponseMetaData();
        responseMetaData.setMaximumDate(endDate);
        
        FrequencyCollectionPayload frequencyCollectionPayload=new FrequencyCollectionPayload();
        frequencyCollectionPayload.setPayloadMetaData(responseMetaData);
        frequencyCollectionPayload.setFrequencys(frequencys);
        
        return frequencyCollectionPayload;
        
        
        

    }
}
