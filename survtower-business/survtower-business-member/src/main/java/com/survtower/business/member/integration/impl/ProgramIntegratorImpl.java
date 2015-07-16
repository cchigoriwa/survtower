package com.survtower.business.member.integration.impl;

import com.survtower.business.common.domain.Program;
import com.survtower.business.common.domain.Lookup;
import com.survtower.business.common.service.ProgramService;
import com.survtower.business.member.domain.LookupMeta;
import com.survtower.business.member.integration.ProgramIntegrator;
import com.survtower.business.member.integration.IntegrationService;
import com.survtower.business.member.service.LookupMetaService;
import com.survtower.ws.api.ProgramWebService;
import com.survtower.ws.api.domain.ProgramPayload;
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

        LookupMeta programLookupMeta = lookupMetaService.findByLookup(Lookup.PROGRAM);

        Long lastUpdateNo = null;

        if (programLookupMeta != null) {
            lastUpdateNo = programLookupMeta.getLastServerUpdateNo();
        }

        ProgramWebService programWebService = integrationService.getProgramWebService();

        ProgramPayload programPayload = programWebService.getData(lastUpdateNo);

        List<Program> programs = programPayload.getProgramBody().getPrograms();
        if (programs != null && !programs.isEmpty()) {
            for (Program program : programs) {
                Program existing = programService.findByUuid(program.getUuid());
                if (existing != null) {
                    //ID is not send
                    program.setId(existing.getId());
                } else {
                    //it must be a new object
                    program.setId(null);
                }
                programService.save(program);
            }
        }

        if (programLookupMeta == null) {
            programLookupMeta = new LookupMeta(Lookup.PROGRAM);
        }

        if (programPayload.getResponseHead() != null && programPayload.getResponseHead().getLastUpdateNo() != null) {
            programLookupMeta.setLastServerUpdateNo(programPayload.getResponseHead().getLastUpdateNo());
        }

        programLookupMeta.setUpdateDate(new Date());
        lookupMetaService.save(programLookupMeta);

        return programLookupMeta;
    }
}
