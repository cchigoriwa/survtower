package com.survtower.business.member.integration.impl;

import com.survtower.business.common.domain.Program;
import com.survtower.business.common.domain.Lookup;
import com.survtower.business.common.service.ProgramService;
import com.survtower.business.member.domain.LookupMeta;
import com.survtower.business.member.integration.ProgramIntegrator;
import com.survtower.business.member.integration.IntegrationService;
import com.survtower.business.member.service.LookupMetaService;
import com.survtower.ws.api.ProgramWebservice;
import com.survtower.ws.api.domain.ProgramCollectionPayload;
import com.survtower.ws.api.domain.RequestMetaData;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Charles Chigoriwa
 */
@Component("programIntegrator")
public class ProgramIntegratorImpl implements ProgramIntegrator {

    @Autowired
    private LookupMetaService lookupMetaService;
    @Autowired
    private ProgramService programService;
    @Autowired
    private IntegrationService integrationService;

    @Override
    public synchronized LookupMeta pull() {

        LookupMeta programLookupMeta=lookupMetaService.findByLookup(Lookup.PROGRAM);

        Date startDate = null;

        if (programLookupMeta != null) {
            startDate = programLookupMeta.getLastServerTimestamp();
        }

        RequestMetaData requestMetaData = new RequestMetaData();
        requestMetaData.setMinimumDate(startDate);
        ProgramWebservice programWebservice = integrationService.getProgramWebservice();

        ProgramCollectionPayload programCollectionPayload = programWebservice.getPrograms(requestMetaData);

        List<Program> programs = programCollectionPayload.getPrograms();
        if (programs != null && !programs.isEmpty()) {
            for (Program program : programs) {
                Program existing = programService.findByUuid(program.getUuid());
                if (existing != null) {
                    //ID is not send
                    program.setId(existing.getId());
                }else{
                    //it must be a new object
                    program.setId(null);
                }
                programService.save(program);
            }
        }

        if (programLookupMeta == null) {
            programLookupMeta = new LookupMeta(Lookup.PROGRAM);
        }

        if (programCollectionPayload.getPayloadMetaData() != null && programCollectionPayload.getPayloadMetaData().getMaximumDate() != null) {
            programLookupMeta.setLastServerTimestamp(programCollectionPayload.getPayloadMetaData().getMaximumDate());
        }
        
        programLookupMeta.setUpdateDate(new Date());
        lookupMetaService.save(programLookupMeta);

        return programLookupMeta;
    }
}
