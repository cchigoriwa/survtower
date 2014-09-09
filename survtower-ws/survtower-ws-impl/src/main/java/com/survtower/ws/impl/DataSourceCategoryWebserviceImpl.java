package com.survtower.ws.impl;

import com.survtower.business.common.domain.DataSourceCategory;
import com.survtower.business.common.service.DataSourceCategoryService;
import com.survtower.ws.api.DataSourceCategoryWebservice;
import com.survtower.ws.api.domain.DataSourceCategoryCollectionPayload;
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
public class DataSourceCategoryWebserviceImpl implements DataSourceCategoryWebservice {

    @Autowired
    private DataSourceCategoryService dataSourceCategoryService;

    public DataSourceCategoryCollectionPayload getDataSourceCategorys(RequestMetaData requestMetaData) {

        List<DataSourceCategory> dataSourceCategorys;
        
        Date startDate = null;
        Date endDate;
        if (requestMetaData != null) {
            if (requestMetaData.getMinimumDate() != null) {
                startDate = requestMetaData.getMinimumDate();
            }
        }

        if (startDate != null) {
            endDate = dataSourceCategoryService.findMaximumUpdateDate(startDate);
        }else{
            endDate=dataSourceCategoryService.findMaximumUpdateDate();
        }
        
        if(endDate!=null){
            if(startDate==null){
                //inclusive endDate (before or as at)-name is a bit misleading
                dataSourceCategorys=dataSourceCategoryService.findDataSourceCategorysUpdatedBefore(endDate);
            }else{
                //exclusive startDate and inclusive endDate
                dataSourceCategorys=dataSourceCategoryService.findDataSourceCategorysUpdatedAfter(startDate, endDate);
            }
        }else{
            dataSourceCategorys=new ArrayList<DataSourceCategory>();
        }
        
        ResponseMetaData responseMetaData=new ResponseMetaData();
        responseMetaData.setMaximumDate(endDate);
        
        DataSourceCategoryCollectionPayload dataSourceCategoryCollectionPayload=new DataSourceCategoryCollectionPayload();
        dataSourceCategoryCollectionPayload.setPayloadMetaData(responseMetaData);
        dataSourceCategoryCollectionPayload.setDataSourceCategorys(dataSourceCategorys);
        
        return dataSourceCategoryCollectionPayload;
        
        
        

    }
}
