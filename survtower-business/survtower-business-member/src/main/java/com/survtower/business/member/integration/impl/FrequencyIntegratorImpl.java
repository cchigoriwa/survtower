package com.survtower.business.member.integration.impl;

import com.survtower.business.common.domain.Frequency;
import com.survtower.business.common.domain.Lookup;
import com.survtower.business.common.service.FrequencyService;
import com.survtower.business.member.domain.LookupMeta;
import com.survtower.business.member.integration.FrequencyIntegrator;
import com.survtower.business.member.integration.IntegrationService;
import com.survtower.business.member.service.LookupMetaService;
import com.survtower.ws.api.FrequencyWebService;
import com.survtower.ws.api.domain.FrequencyPayload;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Charles Chigoriwa
 */
@Component("frequencyIntegrator")
public class FrequencyIntegratorImpl implements FrequencyIntegrator {

    @Autowired
    private LookupMetaService lookupMetaService;
    @Autowired
    private FrequencyService frequencyService;
    @Autowired
    private IntegrationService integrationService;

    @Override
    public synchronized LookupMeta pull() {

        LookupMeta frequencyLookupMeta = lookupMetaService.findByLookup(Lookup.FREQUENCY);

        Long lastUpdateNo = null;

        if (frequencyLookupMeta != null) {
            lastUpdateNo = frequencyLookupMeta.getLastServerUpdateNo();
        }

        FrequencyWebService frequencyWebService = integrationService.getFrequencyWebService();

        FrequencyPayload frequencyPayload = frequencyWebService.getData(lastUpdateNo);

        List<Frequency> frequencys = frequencyPayload.getFrequencyBody().getFrequencies();
        if (frequencys != null && !frequencys.isEmpty()) {
            for (Frequency frequency : frequencys) {
                Frequency existing = frequencyService.findByUuid(frequency.getUuid());
                if (existing != null) {
                    //ID is not send
                    frequency.setId(existing.getId());
                } else {
                    //it must be a new object
                    frequency.setId(null);
                }
                frequencyService.save(frequency);
            }
        }

        if (frequencyLookupMeta == null) {
            frequencyLookupMeta = new LookupMeta(Lookup.FREQUENCY);
        }

        if (frequencyPayload.getResponseHead() != null && frequencyPayload.getResponseHead().getLastUpdateNo() != null) {
            frequencyLookupMeta.setLastServerUpdateNo(frequencyPayload.getResponseHead().getLastUpdateNo());
        }

        frequencyLookupMeta.setUpdateDate(new Date());
        lookupMetaService.save(frequencyLookupMeta);

        return frequencyLookupMeta;
    }
}
