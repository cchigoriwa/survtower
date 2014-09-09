package com.survtower.ws.impl;

import com.survtower.business.common.domain.DataElement;
import com.survtower.business.common.service.DataElementService;
import com.survtower.ws.api.DataElementWebservice;
import com.survtower.ws.api.domain.DataElementCollectionPayload;
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
public class DataElementWebserviceImpl implements DataElementWebservice {

    @Autowired
    private DataElementService dataElementService;

    public DataElementCollectionPayload getDataElements(RequestMetaData requestMetaData) {

        List<DataElement> dataElements;
        
        Date startDate = null;
        Date endDate;
        if (requestMetaData != null) {
            if (requestMetaData.getMinimumDate() != null) {
                startDate = requestMetaData.getMinimumDate();
            }
        }

        if (startDate != null) {
            endDate = dataElementService.findMaximumUpdateDate(startDate);
        }else{
            endDate=dataElementService.findMaximumUpdateDate();
        }
        
        if(endDate!=null){
            if(startDate==null){
                //inclusive endDate (before or as at)-name is a bit misleading
                dataElements=dataElementService.findDataElementsUpdatedBefore(endDate);
            }else{
                //exclusive startDate and inclusive endDate
                dataElements=dataElementService.findDataElementsUpdatedAfter(startDate, endDate);
            }
        }else{
            dataElements=new ArrayList<DataElement>();
        }
        
        ResponseMetaData responseMetaData=new ResponseMetaData();
        responseMetaData.setMaximumDate(endDate);
        
        DataElementCollectionPayload dataElementCollectionPayload=new DataElementCollectionPayload();
        dataElementCollectionPayload.setPayloadMetaData(responseMetaData);
        dataElementCollectionPayload.setDataElements(dataElements);
        
        return dataElementCollectionPayload;
        
        
        

    }
}
