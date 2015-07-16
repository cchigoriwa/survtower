package com.survtower.ws.impl;

import com.survtower.ws.api.domain.FrequencyBody;
import com.survtower.ws.api.domain.FrequencyPayload;
import com.survtower.ws.api.domain.ResponseHead;
import com.survtower.ws.api.FrequencyWebService;
import com.survtower.ws.helper.LookupResult;
import com.survtower.ws.helper.LookupWebServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Charles Chigoriwa
 */
@Service
public class FrequencyWebServiceImpl implements FrequencyWebService {

    @Autowired
    private LookupWebServiceHelper lookupWebServiceHelper;

    public FrequencyPayload getData(Long lastUpdatedNo) {

        FrequencyPayload responsePayload = new FrequencyPayload();

        LookupResult lookupResult = lookupWebServiceHelper.findLookupResult("Frequency", lastUpdatedNo);

        ResponseHead head = new ResponseHead();
        head.setLastUpdateNo(lookupResult.getServerLastUpdateNo());
        responsePayload.setResponseHead(head);

        FrequencyBody body = new FrequencyBody();
        body.setFrequencies(lookupResult.getResultList());
        responsePayload.setFrequencyBody(body);

        return responsePayload;

    }

}
