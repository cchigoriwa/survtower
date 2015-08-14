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
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
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

    private Set<RegionSurveillanceData> regionSurveillanceDataList;

    private Set<ProgramRegion> programRegions;

    @ManagedProperty(value = "#{memberUserService}")
    private MemberUserService memberUserService;

    @ManagedProperty(value = "#{regionSurveillanceDataService}")
    private RegionSurveillanceDataService regionSurveillanceDataService;

    @ManagedProperty(value = "#{memberUserUtility}")
    private MemberUserUtility memberUserUtility;

    public void setMemberUserService(MemberUserService memberUserService) {
        this.memberUserService = memberUserService;
    }

    public void setSurveillanceDataService(RegionSurveillanceDataService surveillanceDataService) {
        this.regionSurveillanceDataService = surveillanceDataService;
    }

    public Set<RegionSurveillanceData> getRegionSurveillanceDataList() {
        return regionSurveillanceDataList;
    }

    public void setRegionSurveillanceDataList(Set<RegionSurveillanceData> regionSurveillanceDataList) {
        this.regionSurveillanceDataList = regionSurveillanceDataList;
    }

    public RegionSurveillanceDataService getRegionSurveillanceDataService() {
        return regionSurveillanceDataService;
    }

    public void setRegionSurveillanceDataService(RegionSurveillanceDataService regionSurveillanceDataService) {
        this.regionSurveillanceDataService = regionSurveillanceDataService;
    }

    public MemberUserUtility getMemberUserUtility() {
        return memberUserUtility;
    }

    public void setMemberUserUtility(MemberUserUtility memberUserUtility) {
        this.memberUserUtility = memberUserUtility;
    }

    @PostConstruct
    public void init() {

        regionSurveillanceDataList = new HashSet<>();
        programRegions = new HashSet<>();
        for (Program program : memberUserUtility.getCurrentUser().getPrograms()) {
            for (Region region : memberUserUtility.getCurrentUser().getRegions()) {
                programRegions.add(new ProgramRegion(region, program));
            }
        }

        for (ProgramRegion pr : programRegions) {
            regionSurveillanceDataList.addAll(regionSurveillanceDataService.findAll(pr.getProgram(), pr.getRegion()));
        }
    }

    public void testJfreChartComparisonBar(List<RegionSurveillanceData> regionSurveillanceDataList) {

// Create a simple Bar chart
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (RegionSurveillanceData regionSurveillanceData : regionSurveillanceDataList) {
            dataset.setValue(regionSurveillanceData.getCalculatedValue(), "Indicator Value", regionSurveillanceData.getRegion().getName());
        }

        JFreeChart chart = ChartFactory.createBarChart("Comparison between Salesman",
                "Salesman", "Profit", dataset, PlotOrientation.VERTICAL,
                false, true, false);
        try {
            ChartUtilities.saveChartAsJPEG(new File("/home/hitrac/chart.jpg"), chart, 500, 300);
        } catch (IOException e) {
            System.err.println("Problem occurred creating chart.");
        }

    }

    public BarChartModel regionSurveillanceDataChangeModel(RegionSurveillanceData regionSurveillanceData) {

        BarChartModel model = new BarChartModel();
        ChartSeries numerator = new ChartSeries();
        ChartSeries denominator = new ChartSeries();

        numerator.setLabel(regionSurveillanceData.getSurveillanceData().getIndicator().getNumeratorDataElement().getName());
        denominator.setLabel(regionSurveillanceData.getSurveillanceData().getIndicator().getDenominatorDataElement().getName());
        numerator.set(regionSurveillanceData.getSurveillanceData().getSurveillance().getPeriod().getName(), regionSurveillanceData.getNumeratorValue());
        denominator.set(regionSurveillanceData.getSurveillanceData().getSurveillance().getPeriod().getName(), regionSurveillanceData.getDenominatorValue());

        model.addSeries(numerator);
        model.addSeries(denominator);

        model.setTitle(regionSurveillanceData.getSurveillanceData().getIndicator().getName());

        model.setLegendPosition("ne");
        model.setLegendCols(5);

        Axis xAxis = model.getAxis(AxisType.X);
        xAxis.setLabel(regionSurveillanceData.getRegion().getName());

        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Value");
        yAxis.setMin(0);
        return model;
    }

}
