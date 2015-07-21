/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survtower.client.central.utility;

import com.survtower.business.common.domain.Member;
import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.domain.SurveillanceData;
import com.survtower.business.common.service.MemberService;
import com.survtower.business.common.service.PeriodService;
import com.survtower.business.common.service.ProgramService;
import com.survtower.business.common.service.SurveillanceDataService;
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

    private List<SurveillanceData> surveillanceDataList;
    private List<MemberProgram> memberPrograms;

    @ManagedProperty(value = "#{memberService}")
    private MemberService memberService;

    @ManagedProperty(value = "#{surveillanceDataService}")
    private SurveillanceDataService surveillanceDataService;

    @ManagedProperty(value = "#{programService}")
    private ProgramService programService;

    @ManagedProperty(value = "#{periodService}")
    private PeriodService periodService;

    public List<MemberProgram> getMemberPrograms() {
        return memberPrograms;
    }

    public void setMemberPrograms(List<MemberProgram> memberPrograms) {
        this.memberPrograms = memberPrograms;
    }

    public void setProgramService(ProgramService programService) {
        this.programService = programService;
    }

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    public void setSurveillanceDataService(SurveillanceDataService surveillanceDataService) {
        this.surveillanceDataService = surveillanceDataService;
    }

    public void setPeriodService(PeriodService periodService) {
        this.periodService = periodService;
    }

    public List<SurveillanceData> getSurveillanceDataList() {
        return surveillanceDataList;
    }

    public void setSurveillanceDataList(List<SurveillanceData> surveillanceDataList) {
        this.surveillanceDataList = surveillanceDataList;
    }

    @PostConstruct
    public void init() {

        surveillanceDataList = new ArrayList<>();
        memberPrograms = new ArrayList<>();
        for (Program program : programService.findAll()) {
            for (Member member : memberService.findAll()) {
                memberPrograms.add(new MemberProgram(program, member));
            }
        }

        for (MemberProgram pr : memberPrograms) {
            for (Period period : periodService.fetchActive()) {
                surveillanceDataList.addAll(surveillanceDataService.findSurveillanceDataItems(pr.getProrgam(), period, pr.getMember()));
            }
        }

    }

    public BarChartModel surveillanceDataChangeModel(SurveillanceData surveillanceData) {
        
        BarChartModel model = new BarChartModel();
        ChartSeries indicator = new ChartSeries();
        indicator.setLabel(surveillanceData.getIndicator().getName());
        indicator.set(surveillanceData.getSurveillance().getPeriod().getName(), surveillanceData.getCalculatedValue());
        model.addSeries(indicator);        
        model.setTitle(surveillanceData.getIndicator().getName());
        model.setLegendPosition("ne");
        
        Axis xAxis = model.getAxis(AxisType.X);
        xAxis.setLabel(surveillanceData.getSurveillance().getMember().getName());
        
        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Percentage");
        yAxis.setMin(0);
        yAxis.setMax(100);
        
        return model;
        
    }
   
}
