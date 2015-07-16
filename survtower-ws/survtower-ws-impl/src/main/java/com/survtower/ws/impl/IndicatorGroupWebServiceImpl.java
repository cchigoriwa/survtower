package com.survtower.ws.impl;

import com.survtower.ws.api.domain.IndicatorGroupBody;
import com.survtower.ws.api.domain.IndicatorGroupPayload;
import com.survtower.ws.api.domain.ResponseHead;
import com.survtower.ws.api.IndicatorGroupWebService;
import com.survtower.ws.helper.LookupResult;
import com.survtower.ws.helper.LookupWebServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Charles Chigoriwa
 */
@Service
public class IndicatorGroupWebServiceImpl implements IndicatorGroupWebService {

    @Autowired
    private LookupWebServiceHelper lookupWebServiceHelper;

    public IndicatorGroupPayload getData(Long lastUpdatedNo) {

        IndicatorGroupPayload responsePayload = new IndicatorGroupPayload();

        LookupResult lookupResult = lookupWebServiceHelper.findLookupResult("IndicatorGroup", lastUpdatedNo);

        ResponseHead head = new ResponseHead();
        head.setLastUpdateNo(lookupResult.getServerLastUpdateNo());
        responsePayload.setResponseHead(head);

        IndicatorGroupBody body = new IndicatorGroupBody();
        body.setIndicatorGroups(lookupResult.getResultList());
        responsePayload.setIndicatorGroupBody(body);

        return responsePayload;

    }

}
