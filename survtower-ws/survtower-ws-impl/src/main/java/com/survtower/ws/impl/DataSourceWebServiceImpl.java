package com.survtower.ws.impl;

import com.survtower.ws.api.domain.DataSourceBody;
import com.survtower.ws.api.domain.DataSourcePayload;
import com.survtower.ws.api.domain.ResponseHead;
import com.survtower.ws.api.DataSourceWebService;
import com.survtower.ws.helper.LookupResult;
import com.survtower.ws.helper.LookupWebServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Charles Chigoriwa
 */
@Service
public class DataSourceWebServiceImpl implements DataSourceWebService {

    @Autowired
    private LookupWebServiceHelper lookupWebServiceHelper;

    public DataSourcePayload getData(Long lastUpdatedNo) {

        DataSourcePayload responsePayload = new DataSourcePayload();

        LookupResult lookupResult = lookupWebServiceHelper.findLookupResult("DataSource", lastUpdatedNo);

        ResponseHead head = new ResponseHead();
        head.setLastUpdateNo(lookupResult.getServerLastUpdateNo());
        responsePayload.setResponseHead(head);

        DataSourceBody body = new DataSourceBody();
        body.setDataSources(lookupResult.getResultList());
        responsePayload.setDataSourceBody(body);

        return responsePayload;

    }

}
