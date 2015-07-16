package com.survtower.ws.impl.version2;

import com.survtower.ws.api.domain.version2.IndicatorGroupBody;
import com.survtower.ws.api.domain.version2.IndicatorGroupPayload;
import com.survtower.ws.api.domain.version2.ResponseHead;
import com.survtower.ws.api.version2.IndicatorGroupWebService;
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
