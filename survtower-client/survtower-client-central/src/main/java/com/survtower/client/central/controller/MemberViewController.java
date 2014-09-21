package com.survtower.client.central.controller;

import com.survtower.business.central.domain.MemberSecurity;
import com.survtower.business.central.domain.report.CentralAuditItem;
import com.survtower.business.central.service.MemberSecurityService;
import com.survtower.business.common.domain.Member;
import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.service.MemberService;
import com.survtower.business.common.service.PeriodService;
import com.survtower.business.common.service.SurveillanceService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Charles Chigoriwa
 */
@ManagedBean
@RequestScoped
public class MemberViewController {

    @ManagedProperty(value = "#{param.uuid}")
    private String uuid;

    @ManagedProperty(value = "#{memberService}")
    private MemberService memberService;

    @ManagedProperty(value = "#{memberSecurityService}")
    private MemberSecurityService memberSecurityService;

    private Member member;
    private MemberSecurity memberSecurity;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public MemberSecurity getMemberSecurity() {
        return memberSecurity;
    }

    public void setMemberSecurity(MemberSecurity memberSecurity) {
        this.memberSecurity = memberSecurity;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public MemberService getMemberService() {
        return memberService;
    }

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    public MemberSecurityService getMemberSecurityService() {
        return memberSecurityService;
    }

    public void setMemberSecurityService(MemberSecurityService memberSecurityService) {
        this.memberSecurityService = memberSecurityService;
    }

    @PostConstruct
    public void postConstruct() {
        member = this.memberService.findByUuid(uuid);
        memberSecurity = this.memberSecurityService.findByMember(member);
        createAuditItems(member);
    }

    private void createAuditItems(Member member) {
        //Added Later for auditing
        List<Period> activePeriods = new ArrayList<Period>();
        pastDueDatePeriods = new ArrayList<Period>();
        auditItems = new ArrayList<CentralAuditItem>();
        for (Period p : periodService.fetchActive()) {
            if (p.getActive()) {
                activePeriods.add(p);
            }
            if (p.getDueDatePassed()) {
                pastDueDatePeriods.add(p);
            }
        }

        for (Period period : activePeriods) {
            for (Program program : period.getPrograms()) {
                CentralAuditItem item = new CentralAuditItem();
                item.setPeriod(period);
                item.setProgram(program);
                item.setSurveillance(surveillanceService.findByProgramAndPeriodAndMember(program, period, member));
                auditItems.add(item);
            }
        }
    }

    //This is for auditing consider creating it somewhere else
    @ManagedProperty(value = "#{surveillanceService}")
    private SurveillanceService surveillanceService;
    @ManagedProperty(value = "#{periodService}")
    private PeriodService periodService;
    List<Period> pastDueDatePeriods;
    List<CentralAuditItem> auditItems;

    public SurveillanceService getSurveillanceService() {
        return surveillanceService;
    }

    public void setSurveillanceService(SurveillanceService surveillanceService) {
        this.surveillanceService = surveillanceService;
    }

    public PeriodService getPeriodService() {
        return periodService;
    }

    public void setPeriodService(PeriodService periodService) {
        this.periodService = periodService;
    }

    public List<Period> getPastDueDatePeriods() {
        return pastDueDatePeriods;
    }

    public List<CentralAuditItem> getAuditItems() {
        return auditItems;
    }

}
