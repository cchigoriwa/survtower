package com.survtower.client.member.utility;

import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.domain.Surveillance;
import com.survtower.business.common.service.MemberService;
import com.survtower.business.common.service.SurveillanceService;
import com.survtower.business.member.domain.Region;
import com.survtower.business.member.domain.RegionSurveillanceAudit;
import com.survtower.business.member.service.MemberUserService;
import com.survtower.business.member.service.RegionSurveillanceAuditService;
import com.survtower.client.member.bean.MemberUserUtility;
import java.io.Serializable;
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
public class RegionAuditListController implements Serializable {

    @ManagedProperty(value = "#{memberUserService}")
    private MemberUserService memberUserService;

    @ManagedProperty(value = "#{surveillanceService}")
    private SurveillanceService surveillanceService;

    @ManagedProperty(value = "#{regionSurveillanceAuditService}")
    private RegionSurveillanceAuditService surveillanceAuditService;

    @ManagedProperty(value = "#{memberService}")
    private MemberService memberService;

    @ManagedProperty(value = "#{memberUserUtility}")
    private MemberUserUtility memberUserUtility;

    private RegionSurveillanceAudit regionSurveillanceAudit;

    private List<RegionSurveillanceAudit> audits;
    private List<RegionSurveillanceAudit> waitingApprovalAudits;

    public void setSurveillanceService(SurveillanceService surveillanceService) {
        this.surveillanceService = surveillanceService;
    }

    public void setSurveillanceAuditService(RegionSurveillanceAuditService surveillanceAuditService) {
        this.surveillanceAuditService = surveillanceAuditService;
    }

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    public void setMemberUserService(MemberUserService memberUserService) {
        this.memberUserService = memberUserService;
    }

    public List<RegionSurveillanceAudit> getAudits() {
        return audits;
    }

    public void setAudits(List<RegionSurveillanceAudit> audits) {
        this.audits = audits;
    }

    public RegionSurveillanceAudit getRegionSurveillanceAudit() {
        return regionSurveillanceAudit;
    }

    public void setRegionSurveillanceAudit(RegionSurveillanceAudit regionSurveillanceAudit) {
        this.regionSurveillanceAudit = regionSurveillanceAudit;
    }

    @PostConstruct
    public void init() {

        audits = new ArrayList<>();
        waitingApprovalAudits = new ArrayList<>();

        List<ProgramRegion> programRegions = new ArrayList<>();

        for (Program program : memberUserUtility.getCurrentUser().getPrograms()) {
            for (Region region : memberUserUtility.getCurrentUser().getRegions()) {
                programRegions.add(new ProgramRegion(region, program));

            }
        }

        for (ProgramRegion pr : programRegions) {
            waitingApprovalAudits.addAll(surveillanceAuditService.findPendingApproval(pr.getProgram(), pr.getRegion()));
        }

        for (ProgramRegion pr : programRegions) {
            audits.addAll(surveillanceAuditService.findApproved(pr.getProgram(), pr.getRegion()));
        }

    }

    public List<RegionSurveillanceAudit> getWaitingApprovalAudits() {
        return waitingApprovalAudits;
    }

    public void setWaitingApprovalAudits(List<RegionSurveillanceAudit> waitingApprovalAudits) {
        this.waitingApprovalAudits = waitingApprovalAudits;
    }

    public String surveillanceId(Program program, Period period) {
        Surveillance surveillance = surveillanceService.get(program, period, memberService.getCurrentMember());
        return surveillance == null ? null : surveillance.getUuid();
    }

    public String viewData() {
        return "region_data_entry_view";
    }

    public void setMemberUserUtility(MemberUserUtility memberUserUtility) {
        this.memberUserUtility = memberUserUtility;
    }

}
