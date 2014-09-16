package com.survtower.ws.impl;

import com.survtower.business.central.service.MemberSecurityService;
import com.survtower.business.common.domain.Indicator;
import com.survtower.business.common.domain.Member;
import com.survtower.business.common.domain.Surveillance;
import com.survtower.business.common.domain.SurveillanceData;
import com.survtower.business.common.service.DataElementService;
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
import org.springframework.stereotype.Service;

/**
 *
 * @author Charles Chigoriwa
 */
@Service
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

    public synchronized boolean processData(SurveillancePayload surveillancePayload) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String memberID = auth.getName();
        Member member = memberSecurityService.findByMemberID(memberID).getMember();


            if (surveillancePayload != null && surveillancePayload.getSurveillances() != null && !surveillancePayload.getSurveillances().isEmpty()) {
                for (Surveillance surveillance : surveillancePayload.getSurveillances()) {
                    if (member.equals(memberService.findByUuid(surveillance.getMember().getUuid()))) {
                        Surveillance existing = surveillanceService.findByProgramAndPeriodAndMember(surveillance.getProgram(), surveillance.getPeriod(), member);
                        if (existing == null) {
                            Set<SurveillanceData> clientSurveillanceDatas = surveillance.getSurveillanceDataSet();
                            Set<SurveillanceData> serverSurveillanceDatas = new LinkedHashSet<SurveillanceData>();
                            for (SurveillanceData surveillanceData : clientSurveillanceDatas) {
                                Indicator serverIndicator = indicatorService.findByUuid(surveillanceData.getIndicator().getUuid());
                                if (serverIndicator != null) {
                                    surveillanceData.setId(null);
                                    surveillanceData.setIndicator(serverIndicator);
                                    serverSurveillanceDatas.add(surveillanceData);
                                }
                            }

                            if (!serverSurveillanceDatas.isEmpty()) {
                                surveillance.setId(null);
                                surveillance.setSurveillanceDataSet(serverSurveillanceDatas);
                                surveillance.setMember(member);
                                surveillance.setPeriod(periodService.findByUuid(surveillance.getPeriod().getUuid()));
                                surveillance.setProgram(programService.findByUuid(surveillance.getProgram().getUuid()));
                                surveillanceService.save(surveillance);
                            }

                        }
                    }
                }
            
        }
        return true;

    }

}
