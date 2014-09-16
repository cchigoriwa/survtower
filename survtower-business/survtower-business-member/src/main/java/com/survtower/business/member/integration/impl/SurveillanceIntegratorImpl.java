package com.survtower.business.member.integration.impl;

import com.survtower.business.common.domain.Dynamic;
import com.survtower.business.common.domain.Surveillance;
import com.survtower.business.common.service.SurveillanceService;
import com.survtower.business.member.domain.DynamicMeta;
import com.survtower.business.member.domain.SurveillanceAudit;
import com.survtower.business.member.integration.IntegrationService;
import com.survtower.business.member.integration.SurveillanceIntegrator;
import com.survtower.business.member.service.DynamicMetaService;
import com.survtower.business.member.service.SurveillanceAuditService;
import com.survtower.ws.api.SurveillanceWebservice;
import com.survtower.ws.api.domain.SurveillancePayload;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Charles Chigoriwa
 */
@Service("surveillanceIntegrator")
@Transactional(readOnly = true)
public class SurveillanceIntegratorImpl implements SurveillanceIntegrator {

    @Autowired
    private SurveillanceAuditService surveillanceAuditService;
    @Autowired
    private DynamicMetaService dynamicMetaService;
    @Autowired
    private IntegrationService integrationService;
    @Autowired
    private SurveillanceService surveillanceService;

    @Override
    public synchronized void push() {
        System.out.println("GLOBAL SURVEILLANCE PUSH");
        List<SurveillanceAudit> surveillanceAudits;

        Date startDate = null;
        Date endDate;
        DynamicMeta surveillanceAuditDynamicMeta = dynamicMetaService.findByDynamic(Dynamic.SURVEILLANCE);

        if (surveillanceAuditDynamicMeta != null) {
            startDate = surveillanceAuditDynamicMeta.getLastLocalTimestamp();
        }

        if (startDate != null) {
            endDate = surveillanceAuditService.findMaximumUpdateDate(startDate);
        } else {
            endDate = surveillanceAuditService.findMaximumUpdateDate();
        }

        if (endDate != null) {
            if (startDate == null) {
                //inclusive endDate (before or as at)-name is a bit misleading
                surveillanceAudits = surveillanceAuditService.findSurveillanceAuditsUpdatedBefore(endDate);
            } else {
                //exclusive startDate and inclusive endDate
                surveillanceAudits = surveillanceAuditService.findSurveillanceAuditsUpdatedAfter(startDate, endDate);
            }

            if (surveillanceAudits != null && !surveillanceAudits.isEmpty()) {

                List<Surveillance> surveillances = new ArrayList<>();
                for (SurveillanceAudit surveillanceAudit : surveillanceAudits) {
                    if (surveillanceAudit.approved()) {
                        Surveillance surveillance = surveillanceService.findByProgramAndPeriod(surveillanceAudit.getProgram(), surveillanceAudit.getPeriod());
                        surveillances.add(surveillance);
                    }
                }

                if (!surveillances.isEmpty()) {                    
                    SurveillanceWebservice surveillanceWebservice=integrationService.getSurveillanceWebservice();
                    surveillanceWebservice.processData(new SurveillancePayload(surveillances));
                    
                    //now Update or create local meta for surveillance
                    if (surveillanceAuditDynamicMeta == null) {
                        surveillanceAuditDynamicMeta = new DynamicMeta(Dynamic.SURVEILLANCE);
                    }
                    surveillanceAuditDynamicMeta.setLastLocalTimestamp(endDate);
                    dynamicMetaService.save(surveillanceAuditDynamicMeta);
                }
            }

        }

    }

}
