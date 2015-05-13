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
import com.survtower.business.common.service.MemberService;
import com.survtower.business.common.service.PeriodService;
import com.survtower.business.common.service.ProgramService;
import com.survtower.business.common.service.SurveillanceService;
import com.survtower.business.member.domain.Region;
import com.survtower.business.member.domain.RegionSurveillanceData;
import com.survtower.business.member.service.RegionService;
import com.survtower.business.member.service.RegionSurveillanceDataService;
import com.survtower.client.member.utility.MessageInfor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author tdhlakama
 */
@ManagedBean
@ViewScoped
public class RegionDataValidationController extends MessageInfor implements Serializable {

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

    private Surveillance surveillance;
    private Region region;

    public Surveillance getSurveillance() {
        return surveillance;
    }

    public void setSurveillance(Surveillance surveillance) {
        this.surveillance = surveillance;
    }

    public SurveillanceService getSurveillanceService() {
        return surveillanceService;
    }

    public void setSurveillanceService(SurveillanceService surveillanceService) {
        this.surveillanceService = surveillanceService;
    }

    private List<RegionSurveillanceData> surveillanceDataList = new ArrayList<RegionSurveillanceData>();

    public List<RegionSurveillanceData> getSurveillanceDataList() {
        return surveillanceDataList;
    }

    public void setSurveillanceDataList(List<RegionSurveillanceData> surveillanceDataList) {
        this.surveillanceDataList = surveillanceDataList;
    }

    public RegionService getRegionService() {
        return regionService;
    }

    public void setRegionService(RegionService regionService) {
        this.regionService = regionService;
    }

    public RegionSurveillanceDataService getRegionSurveillanceDataService() {
        return regionSurveillanceDataService;
    }

    public void setRegionSurveillanceDataService(RegionSurveillanceDataService regionSurveillanceDataService) {
        this.regionSurveillanceDataService = regionSurveillanceDataService;
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

    @PostConstruct
    public void loadData() {
        String programId = FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("programId");
        String periodId = FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("periodId");
        String regionId = FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("regionId");
        Program program = programService.findByUuid(programId);
        Period period = periodService.findByUuid(periodId);
        region = regionService.findByUuid(regionId);

        surveillance = surveillanceService.get(program, period, memberService.getCurrentMember());
        if (surveillance != null) {
            for (SurveillanceData surveillanceData : surveillance.getSurveillanceDataSet()) {
                RegionSurveillanceData data = regionSurveillanceDataService.find(surveillanceData, region);
                if (data != null) {
                    surveillanceDataList.add(data);
                }
            }
        }

    }

}
