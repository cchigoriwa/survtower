package com.survtower.ws.impl;

import com.survtower.ws.api.domain.DataElementBody;
import com.survtower.ws.api.domain.DataElementPayload;
import com.survtower.ws.api.domain.ResponseHead;
import com.survtower.ws.api.DataElementWebService;
import com.survtower.ws.helper.LookupResult;
import com.survtower.ws.helper.LookupWebServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Charles Chigoriwa
 */
@Service
public class DataElementWebServiceImpl implements DataElementWebService {

    @Autowired
    private LookupWebServiceHelper lookupWebServiceHelper;

    public DataElementPayload getData(Long lastUpdatedNo) {

        DataElementPayload responsePayload = new DataElementPayload();

        LookupResult lookupResult = lookupWebServiceHelper.findLookupResult("DataElement", lastUpdatedNo);

        ResponseHead head = new ResponseHead();
        head.setLastUpdateNo(lookupResult.getServerLastUpdateNo());
        responsePayload.setResponseHead(head);

        DataElementBody body = new DataElementBody();
        body.setDataElements(lookupResult.getResultList());
        responsePayload.setDataElementBody(body);

        return responsePayload;

    }

}
