/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survtower.client.central.utility;

import com.survtower.business.central.domain.MemberSecurity;
import com.survtower.business.central.domain.report.CentralAuditItem;
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
 * @author tdhlakama
 */
@ManagedBean
@RequestScoped
public class MemberAuditController {

    private MemberSecurity memberSecurity;
    private List<CentralAuditItem> auditItems;

    @ManagedProperty(value = "#{memberService}")
    private MemberService memberService;

    @ManagedProperty(value = "#{surveillanceService}")
    private SurveillanceService surveillanceService;

    @ManagedProperty(value = "#{periodService}")
    private PeriodService periodService;

    public void setPeriodService(PeriodService periodService) {
        this.periodService = periodService;
    }

    public List<CentralAuditItem> getAuditItems() {
        return auditItems;
    }

    public MemberSecurity getMemberSecurity() {
        return memberSecurity;
    }

    public void setMemberSecurity(MemberSecurity memberSecurity) {
        this.memberSecurity = memberSecurity;
    }

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    public void setSurveillanceService(SurveillanceService surveillanceService) {
        this.surveillanceService = surveillanceService;
    }

    public MemberService getMemberService() {
        return memberService;
    }

    public SurveillanceService getSurveillanceService() {
        return surveillanceService;
    }

    public PeriodService getPeriodService() {
        return periodService;
    }

    @PostConstruct
    public void postConstruct() {
        auditItems = new ArrayList<>();
        for (Period period : periodService.fetchActive()) {
            for (Program program : period.getPrograms()) {
                for (Member member : memberService.findAll()) {
                    CentralAuditItem item = new CentralAuditItem();
                    item.setMember(member);
                    item.setPeriod(period);
                    item.setProgram(program);
                    item.setSurveillance(surveillanceService.findByProgramAndPeriodAndMember(program, period, member));
                    auditItems.add(item);
                }

            }
        }

    }

}
