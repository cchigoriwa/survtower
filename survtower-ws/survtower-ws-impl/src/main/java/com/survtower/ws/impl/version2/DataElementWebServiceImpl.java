package com.survtower.ws.impl.version2;

import com.survtower.ws.api.domain.version2.DataElementBody;
import com.survtower.ws.api.domain.version2.DataElementPayload;
import com.survtower.ws.api.domain.version2.ResponseHead;
import com.survtower.ws.api.version2.DataElementWebService;
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
