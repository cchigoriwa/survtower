/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survtower.client.member.controller.entry;

import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.domain.Surveillance;
import com.survtower.business.common.service.MemberService;
import com.survtower.business.common.service.PeriodService;
import com.survtower.business.common.service.ProgramService;
import com.survtower.business.common.service.SurveillanceService;
import com.survtower.business.member.domain.Region;
import com.survtower.business.member.domain.RegionSurveillanceAudit;
import com.survtower.business.member.service.RegionService;
import com.survtower.business.member.service.RegionSurveillanceAuditService;
import com.survtower.client.member.utility.MessageInfor;
import static com.survtower.client.member.utility.MessageInfor.errorMessages;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author tdhlakama
 */
@ManagedBean
@RequestScoped
public class RegionDataSelectController extends MessageInfor implements Serializable {

    private Period period;
    private Program program;
    private Region region;

    public RegionDataSelectController() {
    }

    @ManagedProperty(value = "#{regionService}")
    RegionService regionService;

    @ManagedProperty(value = "#{programService}")
    ProgramService programService;

    @ManagedProperty(value = "#{periodService}")
    PeriodService periodService;

    @ManagedProperty(value = "#{surveillanceService}")
    SurveillanceService surveillanceService;

    @ManagedProperty(value = "#{memberService}")
    private MemberService memberService;

    @ManagedProperty(value = "#{regionSurveillanceAuditService}")
    private RegionSurveillanceAuditService surveillanceAuditService;

    public void setSurveillanceAuditService(RegionSurveillanceAuditService surveillanceAuditService) {
        this.surveillanceAuditService = surveillanceAuditService;
    }

    public void setProgramService(ProgramService programService) {
        this.programService = programService;
    }

    public void setPeriodService(PeriodService periodService) {
        this.periodService = periodService;
    }

    public void setSurveillanceService(SurveillanceService surveillanceService) {
        this.surveillanceService = surveillanceService;
    }

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    public List<Period> getActivePeriods() {
        return periodService.fetchActive();
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public void setRegionService(RegionService regionService) {
        this.regionService = regionService;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String dataEntrySelection() {
        return "region_data_entry?faces-redirect=true&programId=" + program.getUuid() + "&periodId=" + period.getUuid() + "&regionId=" + region.getUuid();
    }

    public String dataValidationSelection() {
        Surveillance surveillance = surveillanceService.get(program, period, memberService.getCurrentMember());
        RegionSurveillanceAudit audit = surveillanceAuditService.get(program, period, region);
        if (surveillance != null && audit != null) {
            return "region_data_validation?faces-redirect=true&surveillanceId=" + surveillance.getUuid() + "&regionId=" + region.getUuid();
        } else {
            errorMessages("Region Surveillance Data Not Uploaded - Redirect to Regional Data Entry");
            return "region_data_entry?faces-redirect=true&programId=" + program.getUuid() + "&periodId=" + period.getUuid() + "&regionId=" + region.getUuid();
        }
    }

}
