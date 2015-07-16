package com.survtower.ws.impl.version2;

import com.survtower.ws.api.domain.version2.ProgramBody;
import com.survtower.ws.api.domain.version2.ProgramPayload;
import com.survtower.ws.api.domain.version2.ResponseHead;
import com.survtower.ws.api.version2.ProgramWebService;
import com.survtower.ws.helper.LookupResult;
import com.survtower.ws.helper.LookupWebServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Charles Chigoriwa
 */
@Service
public class ProgramWebServiceImpl implements ProgramWebService {

    @Autowired
    private LookupWebServiceHelper lookupWebServiceHelper;

    public ProgramPayload getData(Long lastUpdatedNo) {

        ProgramPayload responsePayload = new ProgramPayload();

        LookupResult lookupResult = lookupWebServiceHelper.findLookupResult("Program", lastUpdatedNo);

        ResponseHead head = new ResponseHead();
        head.setLastUpdateNo(lookupResult.getServerLastUpdateNo());
        responsePayload.setResponseHead(head);

        ProgramBody body = new ProgramBody();
        body.setPrograms(lookupResult.getResultList());
        responsePayload.setProgramBody(body);

        return responsePayload;

    }

}
