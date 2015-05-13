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
import com.survtower.business.member.domain.RegionSurveillanceData;
import com.survtower.business.member.service.MemberUserService;
import com.survtower.business.member.service.RegionService;
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

    @ManagedProperty(value = "#{indicatorService}")
    private IndicatorService indicatorService;

    @ManagedProperty(value = "#{memberUserService}")
    private MemberUserService memberUserService;

    public void setMemberUserService(MemberUserService memberUserService) {
        this.memberUserService = memberUserService;
    }

    public RegionSurveillanceDataService getRegionSurveillanceDataService() {
        return regionSurveillanceDataService;
    }

    public void setRegionSurveillanceDataService(RegionSurveillanceDataService regionSurveillanceDataService) {
        this.regionSurveillanceDataService = regionSurveillanceDataService;
    }

    private Boolean submitted = Boolean.FALSE;

    private String programId;

    private String periodId;

    private String regionId;

    public Boolean getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Boolean submitted) {
        this.submitted = submitted;
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

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    private Period period;
    private Program program;
    private Region region;
    private Surveillance surveillance;

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
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

    public SurveillanceService getSurveillanceService() {
        return surveillanceService;
    }

    public void setSurveillanceService(SurveillanceService surveillanceService) {
        this.surveillanceService = surveillanceService;
    }

    public MemberService getMemberService() {
        return memberService;
    }

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    public IndicatorService getIndicatorService() {
        return indicatorService;
    }

    public void setIndicatorService(IndicatorService indicatorService) {
        this.indicatorService = indicatorService;
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
            submitted = Boolean.TRUE;
            for (RegionSurveillanceData data : getSurveillanceDataList()) {
                if (data.getValid()) {
                    regionSurveillanceDataService.save(data);
                }
            }
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
        programId = FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("programId");
        periodId = FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("periodId");
        regionId = FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("regionId");
        program = programService.findByUuid(programId);
        period = periodService.findByUuid(periodId);
        region = regionService.findByUuid(regionId);

        surveillance = surveillanceService.get(program, period, memberService.getCurrentMember());
        if (getSurveillance() != null) {
            for (SurveillanceData surveillanceData : getSurveillance().getSurveillanceDataSet()) {
                RegionSurveillanceData data = null;
                data = regionSurveillanceDataService.find(surveillanceData, region);
                if (data == null) {
                    data = new RegionSurveillanceData();
                    data.setSurveillanceData(surveillanceData);
                    data.setCreateDate(new Date());
                    data.setRegion(region);
                } else {
                    submitted = Boolean.TRUE;
                }
                surveillanceDataList.add(data);
            }
        }

    }

    public String dataValidationSelection() {
        return "";
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
