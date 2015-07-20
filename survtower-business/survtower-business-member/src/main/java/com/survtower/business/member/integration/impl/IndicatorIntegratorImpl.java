package com.survtower.business.member.integration.impl;

import com.survtower.business.common.SurvtowerRuntimeException;
import com.survtower.business.common.domain.DataElement;
import com.survtower.business.common.domain.Indicator;
import com.survtower.business.common.domain.IndicatorGroup;
import com.survtower.business.common.domain.IndicatorType;
import com.survtower.business.common.domain.Lookup;
import com.survtower.business.common.service.DataElementService;
import com.survtower.business.common.service.IndicatorGroupService;
import com.survtower.business.common.service.IndicatorService;
import com.survtower.business.common.service.IndicatorTypeService;
import com.survtower.business.member.domain.LookupMeta;
import com.survtower.business.member.integration.IndicatorIntegrator;
import com.survtower.business.member.integration.IntegrationService;
import com.survtower.business.member.service.LookupMetaService;
import com.survtower.ws.api.IndicatorWebService;
import com.survtower.ws.api.domain.IndicatorPayload;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Charles Chigoriwa
 */
@Component("indicatorIntegrator")
public class IndicatorIntegratorImpl implements IndicatorIntegrator {

    @Autowired
    private LookupMetaService lookupMetaService;
    @Autowired
    private IndicatorService indicatorService;
    @Autowired
    private IntegrationService integrationService;
    @Autowired
    private IndicatorGroupService indicatorGroupService;
    @Autowired
    private DataElementService dataElementService;
    @Autowired
    private IndicatorTypeService indicatorTypeService;

    @Override
    public synchronized LookupMeta pull() {

        LookupMeta indicatorLookupMeta=lookupMetaService.findByLookup(Lookup.INDICATOR);

        Long lastUpdateNo = null;

        if (indicatorLookupMeta != null) {
            lastUpdateNo = indicatorLookupMeta.getLastServerUpdateNo();
        }

        
        IndicatorWebService indicatorWebService = integrationService.getIndicatorWebService();

        IndicatorPayload indicatorPayload = indicatorWebService.getData(lastUpdateNo);

        List<Indicator> indicators = indicatorPayload.getIndicatorBody().getIndicators();
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
                
                IndicatorGroup serverIndicatorGroup=indicator.getIndicatorGroup();
                if(serverIndicatorGroup!=null){
                    IndicatorGroup localIndicatorGroup=indicatorGroupService.findByUuid(serverIndicatorGroup.getUuid());
                    if(localIndicatorGroup==null){
                        throw new SurvtowerRuntimeException(String.format("localIndicatorGroup with global uuid %s not found",serverIndicatorGroup.getUuid()));
                    }else{
                        indicator.setIndicatorGroup(localIndicatorGroup);
                    }
                }
                
                IndicatorType serverIndicatorType=indicator.getIndicatorType();
                if(serverIndicatorType!=null){
                    IndicatorType localIndicatorType=indicatorTypeService.findByUuid(serverIndicatorType.getUuid());
                    if(localIndicatorType==null){
                        throw new SurvtowerRuntimeException(String.format("localIndicatorType with global uuid %s not found",serverIndicatorType.getUuid()));
                    }else{
                        indicator.setIndicatorType(localIndicatorType);
                    }
                }
                
                DataElement serverNumeratorDataElement=indicator.getNumeratorDataElement();
                if(serverNumeratorDataElement!=null){
                    DataElement localNumeratorDataElement=dataElementService.findByUuid(serverNumeratorDataElement.getUuid());
                    if(localNumeratorDataElement==null){
                        throw new SurvtowerRuntimeException(String.format("localDataElement with global uuid %s not found",serverNumeratorDataElement.getUuid()));
                    }else{
                        indicator.setNumeratorDataElement(localNumeratorDataElement);
                    }
                }
                
                DataElement serverDenominatorDataElement=indicator.getDenominatorDataElement();
                if(serverDenominatorDataElement!=null){
                    DataElement localDenominatorDataElement=dataElementService.findByUuid(serverDenominatorDataElement.getUuid());
                    if(localDenominatorDataElement==null){
                        throw new SurvtowerRuntimeException(String.format("localDataElement with global uuid %s not found",serverDenominatorDataElement.getUuid()));
                    }else{
                        indicator.setDenominatorDataElement(localDenominatorDataElement);
                    }
                }
                
                indicatorService.save(indicator);
            }
        }

        if (indicatorLookupMeta == null) {
            indicatorLookupMeta = new LookupMeta(Lookup.INDICATOR);
        }

        if (indicatorPayload.getResponseHead() != null && indicatorPayload.getResponseHead().getLastUpdateNo() != null) {
            indicatorLookupMeta.setLastServerUpdateNo(indicatorPayload.getResponseHead().getLastUpdateNo());
        }
        
        indicatorLookupMeta.setUpdateDate(new Date());
        lookupMetaService.save(indicatorLookupMeta);

        return indicatorLookupMeta;
    }
}
