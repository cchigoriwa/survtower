package com.survtower.client.member.controller.entry;

import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.domain.Surveillance;
import com.survtower.business.common.domain.SurveillanceData;
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
 * @author Takunda Dhlakama
 * @author Charles Chigoriwa
 */
@ManagedBean
@ViewScoped
public class RegionDataEntryViewController extends MessageInfor implements Serializable {

    public RegionDataEntryViewController() {
    }

    private Boolean submitted = Boolean.FALSE;
    private Period period;
    private Program program;
    private Region region;
    private Surveillance surveillance;
    private RegionSurveillanceData regionSurveillanceData;
    private RegionSurveillanceAudit regionSurveillanceAudit;

    @ManagedProperty(value = "#{regionService}")
    private RegionService regionService;

    @ManagedProperty(value = "#{regionSurveillanceDataService}")
    private RegionSurveillanceDataService regionSurveillanceDataService;

    @ManagedProperty(value = "#{programService}")
    private ProgramService programService;

    @ManagedProperty(value = "#{periodService}")
    private PeriodService periodService;

    @ManagedProperty(value = "#{surveillanceService}")
    private SurveillanceService surveillanceService;

    @ManagedProperty(value = "#{memberService}")
    private MemberService memberService;

    @ManagedProperty(value = "#{memberUserService}")
    private MemberUserService memberUserService;

    @ManagedProperty(value = "#{regionSurveillanceAuditService}")
    private RegionSurveillanceAuditService regionSurveillanceAuditService;

    public void setSurveillanceAuditService(RegionSurveillanceAuditService surveillanceAuditService) {
        this.regionSurveillanceAuditService = surveillanceAuditService;
    }

    public void setMemberUserService(MemberUserService memberUserService) {
        this.memberUserService = memberUserService;
    }

    public void setRegionSurveillanceDataService(RegionSurveillanceDataService regionSurveillanceDataService) {
        this.regionSurveillanceDataService = regionSurveillanceDataService;
    }

    public RegionSurveillanceAudit getSurveillanceAudit() {
        return regionSurveillanceAudit;
    }

    public void setSurveillanceAudit(RegionSurveillanceAudit surveillanceAudit) {
        this.regionSurveillanceAudit = surveillanceAudit;
    }

    public Boolean getSubmitted() {
        regionSurveillanceAudit = regionSurveillanceAuditService.findByProgramAndPeriodAndRegion(program, period, region);
        if (regionSurveillanceAudit != null) {
            submitted = Boolean.TRUE;
        }
        return submitted;
    }

    public void setSubmitted(Boolean submitted) {
        this.submitted = submitted;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
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

    public Surveillance getSurveillance() {
        return surveillance;
    }

    public void setRegionSurveillanceAuditService(RegionSurveillanceAuditService regionSurveillanceAuditService) {
        this.regionSurveillanceAuditService = regionSurveillanceAuditService;
    }

    public void setSurveillance(Surveillance surveillance) {
        this.surveillance = surveillance;
    }

    public RegionSurveillanceData getRegionSurveillanceData() {
        return regionSurveillanceData;
    }

    public void setRegionSurveillanceData(RegionSurveillanceData regionSurveillanceData) {
        this.regionSurveillanceData = regionSurveillanceData;
    }

    public RegionSurveillanceAudit getRegionSurveillanceAudit() {
        return regionSurveillanceAudit;
    }

    public void setRegionSurveillanceAudit(RegionSurveillanceAudit regionSurveillanceAudit) {
        this.regionSurveillanceAudit = regionSurveillanceAudit;
    }

    public String saveSurveillanceForm() {
        if (getCurrentUser() == null) {
            errorMessages("User needs to login to continue");
            return null;
        }
        try {
            for (RegionSurveillanceData data : getRegionSurveillanceDataList()) {
                if (!data.getValid()) {
                    errorMessages("Data Incomplete");
                    return null;
                }
            }

            if (getSurveillanceAudit().getSubmissionDone()) {//Check for Final Submission
                errorMessages("Data Upload Has Already been Approved,Changes Permitted");
                return null;
            }

            for (RegionSurveillanceData data : getRegionSurveillanceDataList()) {
                if (data.getValid()) {
                    regionSurveillanceDataService.save(data);
                }
            }

            getSurveillanceAudit().setApprovedBy(getCurrentUser());
            getSurveillanceAudit().setApprovedOn(new Date());
            regionSurveillanceAuditService.save(regionSurveillanceAudit);
            inforMessages("Surveillance Data Saved Successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
            errorMessages("Surveillance Data Not Processed Succefully");
        }
        return null;
    }

    public String reset() {
        surveillance = null;
        program = null;
        period = null;
        region = null;
        return null;
    }
    private List<RegionSurveillanceData> regionSurveillanceDataList = new ArrayList<>();

    public RegionService getRegionService() {
        return regionService;
    }

    public void setRegionService(RegionService regionService) {
        this.regionService = regionService;
    }

    public List<RegionSurveillanceData> getRegionSurveillanceDataList() {
        return regionSurveillanceDataList;
    }

    public void setSurveillanceDataList(List<RegionSurveillanceData> regionSurveillanceDataList) {
        this.regionSurveillanceDataList = regionSurveillanceDataList;
    }

    @PostConstruct
    public void loadData() {
        String programId = FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("programId");
        String periodId = FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("periodId");
        String regionId = FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("regionId");
        program = programService.findByUuid(programId);
        period = periodService.findByUuid(periodId);
        region = regionService.findByUuid(regionId);

        surveillance = surveillanceService.get(program, period, memberService.getCurrentMember());

        if (surveillance == null) {
            surveillance = surveillanceService.createSurveillanceData(program, period, memberService.getCurrentMember());
        }

        regionSurveillanceAudit = regionSurveillanceAuditService.get(program, period, region);

        if (regionSurveillanceAudit == null) {
            regionSurveillanceAudit = new RegionSurveillanceAudit();
            for (SurveillanceData surveillanceData : getSurveillance().getSurveillanceDataSet()) {
                RegionSurveillanceData regionSurveillanceData;
                regionSurveillanceData = regionSurveillanceDataService.find(surveillanceData, region);
                if (regionSurveillanceData == null) {
                    regionSurveillanceData = new RegionSurveillanceData();
                    regionSurveillanceData.setSurveillanceData(surveillanceData);
                    regionSurveillanceData.setCreateDate(new Date());
                    regionSurveillanceData.setRegion(region);
                }
                regionSurveillanceDataList.add(regionSurveillanceData);
            }
        } else {
            regionSurveillanceDataList.addAll(regionSurveillanceDataService.findAll(surveillance, region));
        }

    }

    public String backToEdit() {
        return "region_data_entry_edit?faces-redirect=true&programId=" + program.getUuid() + "&periodId=" + period.getUuid() + "&regionId=" + region.getUuid();
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
