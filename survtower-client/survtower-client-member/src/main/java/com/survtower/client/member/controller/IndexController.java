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

    @ManagedProperty(value = "#{memberUserService}")
    private MemberUserService memberUserService;

    public void setMemberUserService(MemberUserService memberUserService) {
        this.memberUserService = memberUserService;
    }

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

    public PeriodService getPeriodService() {
        return periodService;
    }

    public void setPeriodService(PeriodService periodService) {
        this.periodService = periodService;
    }

    @PostConstruct
    public void init() {
        aduitItems = new ArrayList<>();
        for (Program program : getPrograms()) {
            for (Period period : periodService.fetchActive(program)) {
                AuditItem item = new AuditItem();
                item.setPeriod(period);
                item.setProgram(program);
                item.setSurveillanceAudit(surveillanceAuditService.findByProgramAndPeriod(program, period));
                aduitItems.add(item);
            }
        }
    }

    public String surveillanceId(Program program, Period period) {
        Surveillance surveillance = surveillanceService.get(program, period, memberService.getCurrentMember());
        return surveillance == null ? null : surveillance.getUuid();
    }

    public List<Program> getPrograms() {
        return memberUserService.getCurrentUserPrograms();
    }
}
