/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survtower.client.member.controller.entry;

import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.domain.Surveillance;
import com.survtower.business.common.domain.SurveillanceData;
import com.survtower.business.common.service.IndicatorService;
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
public class RegionDataEntryController extends MessageInfor implements Serializable {

    public RegionDataEntryController() {
    }

    private Boolean submitted = Boolean.FALSE;
    private Period period;
    private Program program;
    private Region region;
    private Surveillance surveillance;
    private RegionSurveillanceAudit surveillanceAudit;

    @ManagedProperty(value = "#{regionService}")
    RegionService regionService;

    @ManagedProperty(value = "#{regionSurveillanceDataService}")
    RegionSurveillanceDataService regionSurveillanceDataService;

    @ManagedProperty(value = "#{programService}")
    ProgramService programService;

    @ManagedProperty(value = "#{periodService}")
    PeriodService periodService;

    @ManagedProperty(value = "#{surveillanceService}")
    SurveillanceService surveillanceService;

    @ManagedProperty(value = "#{memberService}")
    private MemberService memberService;

    @ManagedProperty(value = "#{memberUserService}")
    private MemberUserService memberUserService;

    @ManagedProperty(value = "#{regionSurveillanceAuditService}")
    private RegionSurveillanceAuditService surveillanceAuditService;

    public void setSurveillanceAuditService(RegionSurveillanceAuditService surveillanceAuditService) {
        this.surveillanceAuditService = surveillanceAuditService;
    }

    public void setMemberUserService(MemberUserService memberUserService) {
        this.memberUserService = memberUserService;
    }

    public void setRegionSurveillanceDataService(RegionSurveillanceDataService regionSurveillanceDataService) {
        this.regionSurveillanceDataService = regionSurveillanceDataService;
    }

    public RegionSurveillanceAudit getSurveillanceAudit() {
        return surveillanceAudit;
    }

    public void setSurveillanceAudit(RegionSurveillanceAudit surveillanceAudit) {
        this.surveillanceAudit = surveillanceAudit;
    }

    public Boolean getSubmitted() {
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

    public void setSurveillance(Surveillance surveillance) {
        this.surveillance = surveillance;
    }

    public String submitSurviellanceForm() {
        try {
            submitted = Boolean.TRUE;
        } catch (Exception ex) {
            submitted = Boolean.FALSE;
            errorMessages("Surveillance Data Not Processed Succefully");
        }
        return null;
    }

    public String saveSurviellanceForm() {
        if (getCurrentUser() == null) {
            errorMessages("User needs to login to continue");
            return null;
        }
        try {
            for (RegionSurveillanceData data : getSurveillanceDataList()) {
                if (!data.getValid()) {
                    errorMessages("Data Incomplete");
                    submitted = Boolean.TRUE;
                    return null;
                }
            }

            if (!getCurrentUser().getRoles().contains(MemberUser.ROLE_COUNTRY_DISEASE_MANAGER)) {
                if (getSurveillanceAudit().getSubmissionDone()) {//Check for Final Submission
                    errorMessages("Data Upload Has Already been Approved,Changes Permitted");
                    return null;
                }
            }

            submitted = Boolean.TRUE;
            for (RegionSurveillanceData data : getSurveillanceDataList()) {
                if (data.getValid()) {
                    regionSurveillanceDataService.save(data);
                }
            }

            if (getSurveillanceAudit().getId() == null) {
                getSurveillanceAudit().setPeriod(period);
                getSurveillanceAudit().setProgram(program);
                getSurveillanceAudit().setUploadedBy(getCurrentUser());
                getSurveillanceAudit().setUploadedOn(new Date());
                getSurveillanceAudit().setRegion(region);
            } else {
                getSurveillanceAudit().setUploadedOn(new Date());
            }
            if (getSurveillanceAudit().getUploadedBy() == null) {
                errorMessages("Audit Trail - Not Working");
                return null;
            }
            surveillanceAuditService.save(surveillanceAudit);
            inforMessages("Surviellance Data Saved Successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
            errorMessages("Surveillance Data Not Processed Succefully");
        }
        return null;
    }

    public String editSurviellanceForm() {
        submitted = Boolean.FALSE;
        return null;
    }

    public String reset() {
        surveillance = null;
        program = null;
        period = null;
        region = null;
        return null;
    }
    private List<RegionSurveillanceData> surveillanceDataList = new ArrayList<RegionSurveillanceData>();

    public RegionService getRegionService() {
        return regionService;
    }

    public void setRegionService(RegionService regionService) {
        this.regionService = regionService;
    }

    public List<RegionSurveillanceData> getSurveillanceDataList() {
        return surveillanceDataList;
    }

    public void setSurveillanceDataList(List<RegionSurveillanceData> surveillanceDataList) {
        this.surveillanceDataList = surveillanceDataList;
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

        surveillanceAudit = surveillanceAuditService.get(program, period, region);

        if (surveillanceAudit == null) {
            surveillanceAudit = new RegionSurveillanceAudit();
            for (SurveillanceData surveillanceData : getSurveillance().getSurveillanceDataSet()) {
                RegionSurveillanceData data = null;
                data = regionSurveillanceDataService.find(surveillanceData, region);
                if (data == null) {
                    data = new RegionSurveillanceData();
                    data.setSurveillanceData(surveillanceData);
                    data.setCreateDate(new Date());
                    data.setRegion(region);
                }
                surveillanceDataList.add(data);
            }
        } else {
            surveillanceDataList.addAll(regionSurveillanceDataService.findAll(surveillance, region));
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
