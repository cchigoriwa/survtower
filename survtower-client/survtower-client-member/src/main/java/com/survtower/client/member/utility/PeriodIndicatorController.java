/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survtower.client.member.utility;

import com.survtower.business.common.domain.Program;
import com.survtower.business.member.domain.Region;
import com.survtower.business.member.domain.RegionSurveillanceData;
import com.survtower.business.member.service.MemberUserService;
import com.survtower.business.member.service.RegionSurveillanceDataService;
import com.survtower.client.member.bean.MemberUserUtility;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author tdhlakama
 */
@ManagedBean
@RequestScoped
public class PeriodIndicatorController implements Serializable {

    private List<RegionSurveillanceData> surveillanceDataList;
    private List<ProgramRegion> programRegions;

    @ManagedProperty(value = "#{memberUserService}")
    private MemberUserService memberUserService;

    @ManagedProperty(value = "#{regionSurveillanceDataService}")
    private RegionSurveillanceDataService surveillanceDataService;

    @ManagedProperty(value = "#{memberUserUtility}")
    private MemberUserUtility memberUserUtility;

    public void setMemberUserService(MemberUserService memberUserService) {
        this.memberUserService = memberUserService;
    }

    public void setSurveillanceDataService(RegionSurveillanceDataService surveillanceDataService) {
        this.surveillanceDataService = surveillanceDataService;
    }

    public List<RegionSurveillanceData> getSurveillanceDataList() {
        return surveillanceDataList;
    }

    public void setSurveillanceDataList(List<RegionSurveillanceData> surveillanceDataList) {
        this.surveillanceDataList = surveillanceDataList;
    }

    public MemberUserUtility getMemberUserUtility() {
        return memberUserUtility;
    }

    public void setMemberUserUtility(MemberUserUtility memberUserUtility) {
        this.memberUserUtility = memberUserUtility;
    }

    @PostConstruct
    public void init() {

        surveillanceDataList = new ArrayList<>();
        programRegions = new ArrayList<>();
        for (Program program : memberUserUtility.getCurrentUser().getPrograms()) {
            for (Region region : memberUserUtility.getCurrentUser().getRegions()) {
                programRegions.add(new ProgramRegion(region, program));
            }
        }

        for (ProgramRegion pr : programRegions) {
            surveillanceDataList.addAll(surveillanceDataService.findAll(pr.getProgram(), pr.getRegion()));
        }

    }

    public BarChartModel regionSurveillanceDataChangeModel(RegionSurveillanceData regionSurveillanceData) {

        BarChartModel model = new BarChartModel();
        ChartSeries numerator = new ChartSeries();
        ChartSeries denominator = new ChartSeries();

        numerator.setLabel(regionSurveillanceData.getSurveillanceData().getIndicator().getName());
        denominator.setLabel(regionSurveillanceData.getSurveillanceData().getIndicator().getDenominatorDataElement().getName());
        numerator.set(regionSurveillanceData.getSurveillanceData().getSurveillance().getPeriod().getName(), regionSurveillanceData.getNumeratorValue());
        denominator.set(regionSurveillanceData.getSurveillanceData().getSurveillance().getPeriod().getName(), regionSurveillanceData.getDenominatorValue());
        model.addSeries(numerator);
        model.addSeries(denominator);

        model.setTitle(regionSurveillanceData.getSurveillanceData().getIndicator().getName());
        model.setLegendPosition("ne");

        Axis xAxis = model.getAxis(AxisType.X);
        xAxis.setLabel(regionSurveillanceData.getRegion().getName());

        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Value");
        yAxis.setMin(0);
        return model;
    }

}
