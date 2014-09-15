package com.survtower.business.member.integration.impl;

import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Lookup;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.service.PeriodService;
import com.survtower.business.common.service.ProgramService;
import com.survtower.business.member.domain.LookupMeta;
import com.survtower.business.member.integration.PeriodIntegrator;
import com.survtower.business.member.integration.IntegrationService;
import com.survtower.business.member.service.LookupMetaService;
import com.survtower.ws.api.PeriodWebservice;
import com.survtower.ws.api.domain.PeriodCollectionPayload;
import com.survtower.ws.api.domain.RequestMetaData;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Charles Chigoriwa
 */
@Component("periodIntegrator")
public class PeriodIntegratorImpl implements PeriodIntegrator {

    @Autowired
    private LookupMetaService lookupMetaService;
    @Autowired
    private PeriodService periodService;
    @Autowired
    private IntegrationService integrationService;
    @Autowired
    private ProgramService programService;

    @Override
    public synchronized LookupMeta pull() {

        LookupMeta periodLookupMeta=lookupMetaService.findByLookup(Lookup.PERIOD);

        Date startDate = null;

        if (periodLookupMeta != null) {
            startDate = periodLookupMeta.getLastServerTimestamp();
        }

        RequestMetaData requestMetaData = new RequestMetaData();
        requestMetaData.setMinimumDate(startDate);
        PeriodWebservice periodWebservice = integrationService.getPeriodWebservice();

        PeriodCollectionPayload periodCollectionPayload = periodWebservice.getPeriods(requestMetaData);

        List<Period> periods = periodCollectionPayload.getPeriods();
        if (periods != null && !periods.isEmpty()) {
            for (Period period : periods) {
                Period existing = periodService.findByUuid(period.getUuid());
                if (existing != null) {
                    //ID is not send
                    period.setId(existing.getId());
                }else{
                    //it must be a new object
                    period.setId(null);
                }
                
                //Reset @OneToMany programs before saving
                if(period.getPrograms()!=null && !period.getPrograms().isEmpty()){
                    Set<Program> serverPrograms=period.getPrograms();
                    Set<Program> localPrograms=new LinkedHashSet<>();
                    for(Program serverProgram:serverPrograms){
                        Program localProgram=programService.findByUuid(serverProgram.getUuid());
                        localPrograms.add(localProgram);
                    }
                    period.setPrograms(localPrograms);                    
                }
                
                
                periodService.save(period);
            }
        }

        if (periodLookupMeta == null) {
            periodLookupMeta = new LookupMeta(Lookup.PERIOD);
        }

        if (periodCollectionPayload.getPayloadMetaData() != null && periodCollectionPayload.getPayloadMetaData().getMaximumDate() != null) {
            periodLookupMeta.setLastServerTimestamp(periodCollectionPayload.getPayloadMetaData().getMaximumDate());
        }
        
        periodLookupMeta.setUpdateDate(new Date());
        lookupMetaService.save(periodLookupMeta);

        return periodLookupMeta;
    }
}
