package com.survtower.ws.impl.version2;

import com.survtower.ws.api.domain.version2.DataSourceCategoryBody;
import com.survtower.ws.api.domain.version2.DataSourceCategoryPayload;
import com.survtower.ws.api.domain.version2.ResponseHead;
import com.survtower.ws.api.version2.DataSourceCategoryWebService;
import com.survtower.ws.helper.LookupResult;
import com.survtower.ws.helper.LookupWebServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Daniel Nkhoma
 */
public class DataSourceCategoriesWebserviceImpl implements DataSourceCategoryWebService {
    
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
