package com.survtower.client.member.controller;

import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.domain.Surveillance;
import com.survtower.business.common.service.MemberService;
import com.survtower.business.common.service.PeriodService;
import com.survtower.business.common.service.SurveillanceService;
import com.survtower.business.member.domain.report.AuditItem;
import com.survtower.business.member.service.MemberUserService;
import com.survtower.business.member.service.SurveillanceAuditService;
import com.survtower.client.member.bean.MemberUserUtility;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Takunda Dhlakama
 */
@ManagedBean
@RequestScoped
public class IndexController {

    private List<AuditItem> aduitItems; 
    
    @ManagedProperty(value = "#{periodService}")
    PeriodService periodService;

    @ManagedProperty(value = "#{surveillanceAuditService}")
    private SurveillanceAuditService surveillanceAuditService;

    @ManagedProperty(value = "#{surveillanceService}")
    private SurveillanceService surveillanceService;

    @ManagedProperty(value = "#{memberService}")
    private MemberService memberService;
    
    @ManagedProperty(value = "#{memberUserUtility}")
    private MemberUserUtility memberUserUtility;

   
    public void setSurveillanceAuditService(SurveillanceAuditService surveillanceAuditService) {
        this.surveillanceAuditService = surveillanceAuditService;
    }

    public void setSurveillanceService(SurveillanceService surveillanceService) {
        this.surveillanceService = surveillanceService;
    }

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    public List<AuditItem> getAduitItems() {
        return aduitItems;
    }

    public void setAduitItems(List<AuditItem> aduitItems) {
        this.aduitItems = aduitItems;
    }

    public void setPeriodService(PeriodService periodService) {
        this.periodService = periodService;
    }

    @PostConstruct
    public void init() {
        aduitItems = new ArrayList<>();
        
        for (Program program : memberUserUtility.getCurrentUser().getPrograms()) {
            for (Period p : periodService.fetchActive(program)) {
                AuditItem item = new AuditItem();
                item.setPeriod(p);
                item.setProgram(program);
                item.setSurveillanceAudit(surveillanceAuditService.findByProgramAndPeriod(program, p));
                aduitItems.add(item);
            }
        }
        
        
    }

    public void setMemberUserUtility(MemberUserUtility memberUserUtility) {
        this.memberUserUtility = memberUserUtility;
    }
    
    

    public String surveillanceId(Program program, Period p) {
        Surveillance surveillance = surveillanceService.get(program, p, memberService.getCurrentMember());
        return surveillance == null ? null : surveillance.getUuid();
    }

}
