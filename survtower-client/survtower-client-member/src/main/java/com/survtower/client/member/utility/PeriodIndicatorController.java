/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survtower.client.member.utility;

import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.member.domain.Region;
import com.survtower.business.member.domain.RegionSurveillanceData;
import com.survtower.business.member.service.MemberUserService;
import com.survtower.business.member.service.RegionSurveillanceDataService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author tdhlakama
 */
@ManagedBean
@ViewScoped
public class PeriodIndicatorController implements Serializable{

    private Period period;
    private List<RegionSurveillanceData> surveillanceDataList;
    private List<ProgramRegion> programRegions;

    @ManagedProperty(value = "#{memberUserService}")
    private MemberUserService memberUserService;

    @ManagedProperty(value = "#{regionSurveillanceDataService}")
    private RegionSurveillanceDataService surveillanceDataService;

    public void setMemberUserService(MemberUserService memberUserService) {
        this.memberUserService = memberUserService;
    }

    public void setSurveillanceDataService(RegionSurveillanceDataService surveillanceDataService) {
        this.surveillanceDataService = surveillanceDataService;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public List<RegionSurveillanceData> getSurveillanceDataList() {
        return surveillanceDataList;
    }

    public void setSurveillanceDataList(List<RegionSurveillanceData> surveillanceDataList) {
        this.surveillanceDataList = surveillanceDataList;
    }

    public void onProgramChange() {

        surveillanceDataList = new ArrayList<>();
        programRegions = new ArrayList<>();

        for (ProgramRegion pr : programRegions) {
            surveillanceDataList.addAll(surveillanceDataService.findAll(period, pr.getProgram(), pr.getRegion()));
        }

        ChartSeries numerator = new ChartSeries();
        ChartSeries denominator = new ChartSeries();

        for (RegionSurveillanceData data : getSurveillanceDataList()) {
            numerator.setLabel(data.getSurveillanceData().getIndicator().getNumerator().getName());
            denominator.setLabel(data.getSurveillanceData().getIndicator().getDenominator().getName());
            numerator.set(data.getSurveillanceData().getSurveillance().getPeriod().getName(), data.getNumeratorValue());
            denominator.set(data.getSurveillanceData().getSurveillance().getPeriod().getName(), data.getDenominatorValue());
        }

    }

    @PostConstruct
    public void init() {
        surveillanceDataList = new ArrayList<>();
        programRegions = new ArrayList<>();
        for (Program program : memberUserService.getCurrentUserPrograms()) {
            for (Region region : memberUserService.getCurrentUserRegions()) {
                programRegions.add(new ProgramRegion(region, program));
            }
        }

        for (ProgramRegion pr : programRegions) {
            surveillanceDataList.addAll(surveillanceDataService.findAll(pr.getProgram(), pr.getRegion()));
        }

        ChartSeries numerator = new ChartSeries();
        ChartSeries denominator = new ChartSeries();

        for (RegionSurveillanceData data : getSurveillanceDataList()) {
            numerator.setLabel(data.getSurveillanceData().getIndicator().getNumerator().getName());
            denominator.setLabel(data.getSurveillanceData().getIndicator().getDenominator().getName());
            numerator.set(data.getSurveillanceData().getSurveillance().getPeriod().getName(), data.getNumeratorValue());
            denominator.set(data.getSurveillanceData().getSurveillance().getPeriod().getName(), data.getDenominatorValue());
        }

    }

    public CartesianChartModel itemChangeModel(RegionSurveillanceData data) {
        CartesianChartModel model = new CartesianChartModel();
        ChartSeries numerator = new ChartSeries();
        ChartSeries denominator = new ChartSeries();
        numerator.setLabel(data.getSurveillanceData().getIndicator().getNumerator().getName());
        denominator.setLabel(data.getSurveillanceData().getIndicator().getDenominator().getName());
        numerator.set(data.getSurveillanceData().getSurveillance().getPeriod().getName(), data.getNumeratorValue());
        denominator.set(data.getSurveillanceData().getSurveillance().getPeriod().getName(), data.getDenominatorValue());
        model.addSeries(numerator);
        model.addSeries(denominator);
        return model;
    }

}
