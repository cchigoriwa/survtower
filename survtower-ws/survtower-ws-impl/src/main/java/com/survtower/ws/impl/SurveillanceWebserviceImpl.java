package com.survtower.ws.impl;

import com.survtower.business.central.service.MemberSecurityService;
import com.survtower.business.common.domain.Indicator;
import com.survtower.business.common.domain.Member;
import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.domain.Surveillance;
import com.survtower.business.common.domain.SurveillanceData;
import com.survtower.business.common.service.IndicatorService;
import com.survtower.business.common.service.MemberService;
import com.survtower.business.common.service.PeriodService;
import com.survtower.business.common.service.ProgramService;
import com.survtower.business.common.service.SurveillanceService;
import com.survtower.ws.api.SurveillanceWebservice;
import com.survtower.ws.api.domain.SurveillancePayload;
import java.util.LinkedHashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Charles Chigoriwa
 */
@Component
public class SurveillanceWebserviceImpl implements SurveillanceWebservice {

    @Autowired
    private SurveillanceService surveillanceService;
    @Autowired
    private MemberSecurityService memberSecurityService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private PeriodService periodService;
    @Autowired
    private ProgramService programService;
    @Autowired
    private IndicatorService indicatorService;

    public synchronized void processData(SurveillancePayload surveillancePayload) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String memberID = auth.getName();
        Member serverMember = memberSecurityService.findByMemberID(memberID).getMember();

        if (surveillancePayload != null && surveillancePayload.getSurveillances() != null && !surveillancePayload.getSurveillances().isEmpty()) {
            for (Surveillance surveillance : surveillancePayload.getSurveillances()) {
                if (serverMember.equals(memberService.findByUuid(surveillance.getMember().getUuid()))) {
                    Program serverProgram = programService.findByUuid(surveillance.getProgram().getUuid());
                    Period serverPeriod = periodService.findByUuid(surveillance.getPeriod().getUuid());
                    Surveillance existing = surveillanceService.findByProgramAndPeriodAndMember(serverProgram, serverPeriod, serverMember);
                    if (existing == null) {
                        Set<SurveillanceData> clientSurveillanceDatas = surveillance.getSurveillanceDataSet();
                        Set<SurveillanceData> serverSurveillanceDatas = new LinkedHashSet<SurveillanceData>();

                        for (SurveillanceData surveillanceData : clientSurveillanceDatas) {
                            Indicator serverIndicator = indicatorService.findByUuid(surveillanceData.getIndicator().getUuid());
                            if (serverIndicator != null) {
                                surveillanceData.setId(null);
                                surveillanceData.setSurveillance(surveillance);
                                surveillanceData.setIndicator(serverIndicator);
                                serverSurveillanceDatas.add(surveillanceData);
                            }
                        }

                        if (!serverSurveillanceDatas.isEmpty()) {
                            surveillance.setId(null);
                            surveillance.setSurveillanceDataSet(serverSurveillanceDatas);
                            surveillance.setMember(serverMember);
                            surveillance.setPeriod(serverPeriod);
                            surveillance.setProgram(serverProgram);
                            surveillanceService.save(surveillance);
                        }

                    }
                }
            }

        }

    }

}
