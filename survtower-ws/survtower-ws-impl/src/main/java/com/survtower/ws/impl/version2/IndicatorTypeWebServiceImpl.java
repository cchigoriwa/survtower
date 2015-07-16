package com.survtower.ws.impl.version2;

import com.survtower.ws.api.domain.version2.IndicatorTypeBody;
import com.survtower.ws.api.domain.version2.IndicatorTypePayload;
import com.survtower.ws.api.domain.version2.ResponseHead;
import com.survtower.ws.api.version2.IndicatorTypeWebService;
import com.survtower.ws.helper.LookupResult;
import com.survtower.ws.helper.LookupWebServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Charles Chigoriwa
 */
@Service
public class IndicatorTypeWebServiceImpl implements IndicatorTypeWebService {

    @Autowired
    private LookupWebServiceHelper lookupWebServiceHelper;

    public IndicatorTypePayload getData(Long lastUpdatedNo) {

        IndicatorTypePayload responsePayload = new IndicatorTypePayload();

        LookupResult lookupResult = lookupWebServiceHelper.findLookupResult("IndicatorType", lastUpdatedNo);

        ResponseHead head = new ResponseHead();
        head.setLastUpdateNo(lookupResult.getServerLastUpdateNo());
        responsePayload.setResponseHead(head);

        IndicatorTypeBody body = new IndicatorTypeBody();
        body.setIndicatorTypes(lookupResult.getResultList());
        responsePayload.setIndicatorTypeBody(body);

        return responsePayload;

    }

}
