package com.survtower.business.member.integration.impl;

import com.survtower.business.common.SurvtowerRuntimeException;
import com.survtower.business.common.domain.IndicatorGroup;
import com.survtower.business.common.domain.Lookup;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.service.IndicatorGroupService;
import com.survtower.business.common.service.ProgramService;
import com.survtower.business.member.domain.LookupMeta;
import com.survtower.business.member.integration.IndicatorGroupIntegrator;
import com.survtower.business.member.integration.IntegrationService;
import com.survtower.business.member.service.LookupMetaService;
import com.survtower.ws.api.IndicatorGroupWebService;
import com.survtower.ws.api.domain.IndicatorGroupPayload;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Charles Chigoriwa
 */
@Component("indicatorGroupIntegrator")
public class IndicatorGroupIntegratorImpl implements IndicatorGroupIntegrator {

    @Autowired
    private LookupMetaService lookupMetaService;
    @Autowired
    private IndicatorGroupService indicatorGroupService;
    @Autowired
    private IntegrationService integrationService;
    @Autowired
    private ProgramService programService;

    @Override
    public synchronized LookupMeta pull() {

        LookupMeta indicatorGroupLookupMeta=lookupMetaService.findByLookup(Lookup.INDICATOR_GROUP);

        Long lastUpdateNo = null;

        if (indicatorGroupLookupMeta != null) {
            lastUpdateNo = indicatorGroupLookupMeta.getLastServerUpdateNo();
        }

      
        IndicatorGroupWebService indicatorGroupWebService = integrationService.getIndicatorGroupWebService();

        IndicatorGroupPayload indicatorGroupPayload = indicatorGroupWebService.getData(lastUpdateNo);

        List<IndicatorGroup> indicatorGroups = indicatorGroupPayload.getIndicatorGroupBody().getIndicatorGroups();
        if (indicatorGroups != null && !indicatorGroups.isEmpty()) {
            for (IndicatorGroup indicatorGroup : indicatorGroups) {
                IndicatorGroup existing = indicatorGroupService.findByUuid(indicatorGroup.getUuid());
                if (existing != null) {
                    //ID is not send
                    indicatorGroup.setId(existing.getId());
                }else{
                    //it must be a new object
                    indicatorGroup.setId(null);
                }
                
                Program serverProgram=indicatorGroup.getProgram();
                if(serverProgram!=null){
                    Program localProgram=programService.findByUuid(serverProgram.getUuid());
                    if(localProgram==null){
                        throw new SurvtowerRuntimeException(String.format("localProgram with global uuid %s not found",serverProgram.getUuid()));
                    }else{
                        indicatorGroup.setProgram(localProgram);
                    }
                }
                
                
                indicatorGroupService.save(indicatorGroup);
            }
        }

        if (indicatorGroupLookupMeta == null) {
            indicatorGroupLookupMeta = new LookupMeta(Lookup.INDICATOR_GROUP);
        }

        if (indicatorGroupPayload.getResponseHead() != null && indicatorGroupPayload.getResponseHead().getLastUpdateNo() != null) {
            indicatorGroupLookupMeta.setLastServerUpdateNo(indicatorGroupPayload.getResponseHead().getLastUpdateNo());
        }
        
        indicatorGroupLookupMeta.setUpdateDate(new Date());
        lookupMetaService.save(indicatorGroupLookupMeta);

        return indicatorGroupLookupMeta;
    }
}
