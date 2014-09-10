package com.survtower.ws.impl;

import com.survtower.business.common.domain.IndicatorGroup;
import com.survtower.business.common.service.IndicatorGroupService;
import com.survtower.ws.api.IndicatorGroupWebservice;
import com.survtower.ws.api.domain.IndicatorGroupCollectionPayload;
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
public class IndicatorGroupWebserviceImpl implements IndicatorGroupWebservice {

    @Autowired
    private IndicatorGroupService indicatorGroupService;

    public IndicatorGroupCollectionPayload getIndicatorGroups(RequestMetaData requestMetaData) {

        List<IndicatorGroup> indicatorGroups;
        
        Date startDate = null;
        Date endDate;
        if (requestMetaData != null) {
            if (requestMetaData.getMinimumDate() != null) {
                startDate = requestMetaData.getMinimumDate();
            }
        }

        if (startDate != null) {
            endDate = indicatorGroupService.findMaximumUpdateDate(startDate);
        }else{
            endDate=indicatorGroupService.findMaximumUpdateDate();
        }
        
        if(endDate!=null){
            if(startDate==null){
                //inclusive endDate (before or as at)-name is a bit misleading
                indicatorGroups=indicatorGroupService.findIndicatorGroupsUpdatedBefore(endDate);
            }else{
                //exclusive startDate and inclusive endDate
                indicatorGroups=indicatorGroupService.findIndicatorGroupsUpdatedAfter(startDate, endDate);
            }
        }else{
            indicatorGroups=new ArrayList<IndicatorGroup>();
        }
        
        ResponseMetaData responseMetaData=new ResponseMetaData();
        responseMetaData.setMaximumDate(endDate);
        
        IndicatorGroupCollectionPayload indicatorGroupCollectionPayload=new IndicatorGroupCollectionPayload();
        indicatorGroupCollectionPayload.setPayloadMetaData(responseMetaData);
        indicatorGroupCollectionPayload.setIndicatorGroups(indicatorGroups);
        
        return indicatorGroupCollectionPayload;
        
        
        

    }
}
