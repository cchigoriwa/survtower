package com.survtower.ws.impl.version2;

import com.survtower.ws.api.domain.version2.IndicatorBody;
import com.survtower.ws.api.domain.version2.IndicatorPayload;
import com.survtower.ws.api.domain.version2.ResponseHead;
import com.survtower.ws.api.version2.IndicatorWebService;
import com.survtower.ws.helper.LookupResult;
import com.survtower.ws.helper.LookupWebServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Charles Chigoriwa
 */
@Service
public class IndicatorWebServiceImpl implements IndicatorWebService {

    @Autowired
    private LookupWebServiceHelper lookupWebServiceHelper;

    public IndicatorPayload getData(Long lastUpdatedNo) {

        IndicatorPayload responsePayload = new IndicatorPayload();

        LookupResult lookupResult = lookupWebServiceHelper.findLookupResult("Indicator", lastUpdatedNo);

        ResponseHead head = new ResponseHead();
        head.setLastUpdateNo(lookupResult.getServerLastUpdateNo());
        responsePayload.setResponseHead(head);

        IndicatorBody body = new IndicatorBody();
        body.setIndicators(lookupResult.getResultList());
        responsePayload.setIndicatorBody(body);

        return responsePayload;

    }

}
