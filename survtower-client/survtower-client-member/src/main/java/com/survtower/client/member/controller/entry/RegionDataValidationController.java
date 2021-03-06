package com.survtower.client.member.controller.entry;

import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.domain.Surveillance;
import com.survtower.business.common.service.MemberService;
import com.survtower.business.common.service.PeriodService;
import com.survtower.business.common.service.ProgramService;
import com.survtower.business.common.service.SurveillanceService;
import com.survtower.business.member.domain.MemberUser;
import com.survtower.business.member.domain.Region;
import com.survtower.business.member.domain.RegionSurveillanceAudit;
import com.survtower.business.member.domain.RegionSurveillanceData;
import com.survtower.business.member.service.MemberUserService;
import com.survtower.business.member.service.RegionService;
import com.survtower.business.member.service.RegionSurveillanceAuditService;
import com.survtower.business.member.service.RegionSurveillanceDataService;
import com.survtower.client.member.utility.MessageInfor;
import static com.survtower.client.member.utility.MessageInfor.errorMessages;
import static com.survtower.client.member.utility.MessageInfor.inforMessages;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author tdhlakama
 */
@ManagedBean
@ViewScoped
public class RegionDataValidationController extends MessageInfor implements Serializable {

    private List<RegionSurveillanceData> regionSurveillanceDataList = new ArrayList<>();
    private RegionSurveillanceAudit regionSurveillanceAudit;
    private Boolean submitted = Boolean.FALSE;
    private Surveillance surveillance;
    private Region region;
    private Program program;
    private Period period;
    private String programId;
    private String periodId;

    @ManagedProperty(value = "#{regionService}")
    private RegionService regionService;

    @ManagedProperty(value = "#{regionSurveillanceDataService}")
    private RegionSurveillanceDataService regionSurveillanceDataService;

    @ManagedProperty(value = "#{regionSurveillanceAuditService}")
    private RegionSurveillanceAuditService regionSurveillanceAuditService;

    @ManagedProperty(value = "#{surveillanceService}")
    private SurveillanceService surveillanceService;

    @ManagedProperty(value = "#{memberUserService}")
    private MemberUserService memberUserService;

    @ManagedProperty(value = "#{programService}")
    private ProgramService programService;

    @ManagedProperty(value = "#{periodService}")
    private PeriodService periodService;

    @ManagedProperty(value = "#{memberService}")
    private MemberService memberService;

    public void setMemberUserService(MemberUserService memberUserService) {
        this.memberUserService = memberUserService;
    }

    public Boolean getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Boolean submitted) {
        this.submitted = submitted;
    }

    public RegionSurveillanceAudit getRegionSurveillanceAudit() {
        return regionSurveillanceAudit;
    }

    public void setRegionSurveillanceAudit(RegionSurveillanceAudit surveillanceAudit) {
        this.regionSurveillanceAudit = surveillanceAudit;
    }

    public Surveillance getSurveillance() {
        return surveillance;
    }

    public void setSurveillance(Surveillance surveillance) {
        this.surveillance = surveillance;
    }

    public void setSurveillanceService(SurveillanceService surveillanceService) {
        this.surveillanceService = surveillanceService;
    }

    public RegionSurveillanceAuditService getRegionSurveillanceAuditService() {
        return regionSurveillanceAuditService;
    }

    public void setRegionSurveillanceAuditService(RegionSurveillanceAuditService regionSurveillanceAuditService) {
        this.regionSurveillanceAuditService = regionSurveillanceAuditService;
    }

    public void setSurveillanceAuditService(RegionSurveillanceAuditService surveillanceAuditService) {
        this.regionSurveillanceAuditService = surveillanceAuditService;
    }

    public List<RegionSurveillanceData> getRegionSurveillanceDataList() {
        return regionSurveillanceDataList;
    }

    public void setRegionSurveillanceDataList(List<RegionSurveillanceData> surveillanceDataList) {
        this.regionSurveillanceDataList = surveillanceDataList;
    }

    public void setRegionService(RegionService regionService) {
        this.regionService = regionService;
    }

    public void setRegionSurveillanceDataService(RegionSurveillanceDataService regionSurveillanceDataService) {
        this.regionSurveillanceDataService = regionSurveillanceDataService;
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

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getPeriodId() {
        return periodId;
    }

    public void setPeriodId(String periodId) {
        this.periodId = periodId;
    }

    public ProgramService getProgramService() {
        return programService;
    }

    public void setProgramService(ProgramService programService) {
        this.programService = programService;
    }

    public PeriodService getPeriodService() {
        return periodService;
    }

    public void setPeriodService(PeriodService periodService) {
        this.periodService = periodService;
    }

    public MemberService getMemberService() {
        return memberService;
    }

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String submitSurviellanceForm() {
        submitted = Boolean.TRUE;
        return null;
    }

    public String editSurveillanceForm() {
        return "region_data_entry?faces-redirect=true&programId=" + getSurveillance().getProgram().getUuid() + "&periodId=" + getSurveillance().getPeriod().getUuid() + "&regionId=" + getRegion().getUuid();
    }

    public String finalSaveSurveillanceForm() {
        if (regionSurveillanceAudit == null) {
            errorMessages("Surveillance Audit Error - Data Entry Not Done");
            return null;
        }
        if (getCurrentUser() == null) {
            errorMessages("User needs to login to continue");
            return null;
        }

        try {
            submitted = Boolean.TRUE;
            regionSurveillanceAudit.setApprovedBy(getCurrentUser());
            regionSurveillanceAudit.setApprovedOn(new Date());
            regionSurveillanceAuditService.save(regionSurveillanceAudit);
            inforMessages(region + " Regional Surveillance Data Saved Successfully");
        } catch (Exception ex) {
            submitted = Boolean.FALSE;
            errorMessages("Surveillance Data Not Processed Succefully");
        }
        return null;
    }

    @PostConstruct
    public void loadData() {
        //get surveillance parameter form External Context
        String surveillanceId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("surveillanceId");

        if (surveillanceId == null) {
            programId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("programId");
            periodId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("periodId");
            program = programService.findByUuid(programId);
            period = periodService.findByUuid(periodId);
            surveillance = surveillanceService.get(program, period, memberService.getCurrentMember());
        } else {

            surveillance = surveillanceService.findByUuid(surveillanceId);
        }
        //get regional parameter form External Context
        String regionId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("regionId");
        getRegionSurveillanceDataList().clear();
        region = regionService.findByUuid(regionId);
        regionSurveillanceDataList.addAll(regionSurveillanceDataService.findAll(surveillance, region));
        regionSurveillanceAudit = regionSurveillanceAuditService.get(surveillance.getProgram(), surveillance.getPeriod(), region);
        if (regionSurveillanceAudit.getApproved()) {
            submitted = Boolean.TRUE;
        }
    }

    public MemberUser getCurrentUser() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext externalContext = fc.getExternalContext();
        if (externalContext.getUserPrincipal() == null) {
            return null;
        } else {
            String user = externalContext.getUserPrincipal().getName();
            MemberUser memberUser = memberUserService.findByUserName(user);
            if (memberUser != null) {
                return memberUser;
            } else {
                return null;
            }
        }
    }
}
