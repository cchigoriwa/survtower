package com.survtower.ws.impl;

import com.survtower.ws.api.domain.PeriodBody;
import com.survtower.ws.api.domain.PeriodPayload;
import com.survtower.ws.api.domain.ResponseHead;
import com.survtower.ws.api.PeriodWebService;
import com.survtower.ws.helper.LookupResult;
import com.survtower.ws.helper.LookupWebServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Daniel Nkhoma
 */
public class PeriodWebServiceImpl implements PeriodWebService {

    @Autowired
    private LookupWebServiceHelper lookupWebServiceHelper;

    public PeriodPayload getData(Long lastUpdatedNo) {

        PeriodPayload responsePayload = new PeriodPayload();

        LookupResult lookupResult = lookupWebServiceHelper.findLookupResult("Period", lastUpdatedNo);

        ResponseHead head = new ResponseHead();
        head.setLastUpdateNo(lookupResult.getServerLastUpdateNo());
        responsePayload.setResponseHead(head);

        PeriodBody body = new PeriodBody();
        body.setPeriods(lookupResult.getResultList());
        responsePayload.setPeriodBody(body);

        return responsePayload;

    }

}
