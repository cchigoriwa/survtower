package com.survtower.business.member.integration.impl;

import com.survtower.business.common.domain.Indicator;
import com.survtower.business.common.service.IndicatorService;
import com.survtower.business.member.domain.IndicatorMetaData;
import com.survtower.business.member.integration.IndicatorIntegrator;
import com.survtower.business.member.integration.IntegrationService;
import com.survtower.business.member.service.IndicatorMetaDataService;
import com.survtower.ws.api.IndicatorWebservice;
import com.survtower.ws.api.domain.IndicatorCollectionPayload;
import com.survtower.ws.api.domain.RequestMetaData;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Charles Chigoriwa
 */
@Component
public class IndicatorIntegratorImpl implements IndicatorIntegrator {

    @Autowired
    private IndicatorMetaDataService indicatorMetaDataService;
    @Autowired
    private IndicatorService indicatorService;
    @Autowired
    private IntegrationService integrationService;

    public synchronized boolean pull() {

        IndicatorMetaData indicatorMetaData = indicatorMetaDataService.find();

        Date startDate = null;

        if (indicatorMetaData != null) {
            startDate = indicatorMetaData.getMaximumDate();
        }

        RequestMetaData requestMetaData = new RequestMetaData();
        requestMetaData.setMinimumDate(startDate);

        IndicatorWebservice indicatorWebservice = integrationService.getIndicatorWebservice();

        IndicatorCollectionPayload indicatorCollectionPayload = indicatorWebservice.getIndicators(requestMetaData);


        List<Indicator> indicators = indicatorCollectionPayload.getIndicators();
        if (indicators != null && !indicators.isEmpty()) {
            for (Indicator indicator : indicators) {
                Indicator existing = indicatorService.findByUuid(indicator.getUuid());
                if (existing != null) {
                    //ID is not send
                    indicator.setId(existing.getId());
                }else{
                    //it must be a new object
                    indicator.setId(null);
                }
                indicatorService.save(indicator);
            }
        }

        if (indicatorMetaData == null) {
            indicatorMetaData = new IndicatorMetaData();
        }

        if (indicatorCollectionPayload.getPayloadMetaData() != null && indicatorCollectionPayload.getPayloadMetaData().getMaximumDate() != null) {
            indicatorMetaData.setMaximumDate(indicatorCollectionPayload.getPayloadMetaData().getMaximumDate());
        }
        indicatorMetaData.setUpdateDate(new Date());
        indicatorMetaDataService.save(indicatorMetaData);

        return true;
    }
}
