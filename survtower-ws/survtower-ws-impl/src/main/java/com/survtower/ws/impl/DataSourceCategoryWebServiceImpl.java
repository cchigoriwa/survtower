package com.survtower.ws.impl;

import com.survtower.ws.api.domain.DataSourceCategoryBody;
import com.survtower.ws.api.domain.DataSourceCategoryPayload;
import com.survtower.ws.api.domain.ResponseHead;
import com.survtower.ws.api.DataSourceCategoryWebService;
import com.survtower.ws.helper.LookupResult;
import com.survtower.ws.helper.LookupWebServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Daniel Nkhoma
 */
public class DataSourceCategoryWebServiceImpl implements DataSourceCategoryWebService {
    
    @Autowired
    private LookupWebServiceHelper lookupWebServiceHelper;
    
    public DataSourceCategoryPayload getData(Long lastUpdateNo) {
        DataSourceCategoryPayload responsePayload = new DataSourceCategoryPayload();
        
        LookupResult lookupResult = lookupWebServiceHelper.findLookupResult("DataSourceCategory", lastUpdateNo);
        
        ResponseHead head = new ResponseHead();
        head.setLastUpdateNo(lookupResult.getServerLastUpdateNo());
        responsePayload.setResponseHead(head);
        
        DataSourceCategoryBody body = new DataSourceCategoryBody();
        body.setDataSourceCategories(lookupResult.getResultList());
        responsePayload.setDataSourceCategoryBody(body);
        
        return responsePayload;
        
    }
    
}
