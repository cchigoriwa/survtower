package com.survtower.ws.impl;

import com.survtower.business.common.domain.DataSource;
import com.survtower.business.common.service.DataSourceService;
import com.survtower.ws.api.DataSourceWebservice;
import com.survtower.ws.api.domain.DataSourceCollectionPayload;
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
public class DataSourceWebserviceImpl implements DataSourceWebservice {

    @Autowired
    private DataSourceService dataSourceService;

    public DataSourceCollectionPayload getDataSources(RequestMetaData requestMetaData) {

        List<DataSource> dataSources;
        
        Date startDate = null;
        Date endDate;
        if (requestMetaData != null) {
            if (requestMetaData.getMinimumDate() != null) {
                startDate = requestMetaData.getMinimumDate();
            }
        }

        if (startDate != null) {
            endDate = dataSourceService.findMaximumUpdateDate(startDate);
        }else{
            endDate=dataSourceService.findMaximumUpdateDate();
        }
        
        if(endDate!=null){
            if(startDate==null){
                //inclusive endDate (before or as at)-name is a bit misleading
                dataSources=dataSourceService.findDataSourcesUpdatedBefore(endDate);
            }else{
                //exclusive startDate and inclusive endDate
                dataSources=dataSourceService.findDataSourcesUpdatedAfter(startDate, endDate);
            }
        }else{
            dataSources=new ArrayList<DataSource>();
        }
        
        ResponseMetaData responseMetaData=new ResponseMetaData();
        responseMetaData.setMaximumDate(endDate);
        
        DataSourceCollectionPayload dataSourceCollectionPayload=new DataSourceCollectionPayload();
        dataSourceCollectionPayload.setPayloadMetaData(responseMetaData);
        dataSourceCollectionPayload.setDataSources(dataSources);
        
        return dataSourceCollectionPayload;
        
        
        

    }
}
