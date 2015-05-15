package com.survtower.client.central.controller.report;

import com.survtower.business.common.domain.Indicator;
import com.survtower.business.common.domain.Member;
import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.SurveillanceData;
import com.survtower.business.common.service.IndicatorService;
import com.survtower.business.common.service.MemberService;
import com.survtower.business.common.service.SurveillanceDataService;
import com.survtower.client.central.utility.MessageInfor;
import static com.survtower.client.central.utility.MessageInfor.inforMessages;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.LineChartModel;

/**
 *
 * @author Takund Dhlakama
 */
@ManagedBean
@ViewScoped
public class IndicatorViewController extends MessageInfor implements Serializable {

    @ManagedProperty(value = "#{surveillanceDataService}")
    private SurveillanceDataService surveillanceDataService;

    @ManagedProperty(value = "#{indicatorService}")
    private IndicatorService indicatorService;

    @ManagedProperty(value = "#{memberService}")
    private MemberService memberService;

    private Indicator indicator;
    private LineChartModel lineChartModel = new LineChartModel();
    private BarChartModel barChartModel = new BarChartModel();
    private HorizontalBarChartModel horizontalBarChartModel = new HorizontalBarChartModel();

    public LineChartModel getLineChartModel() {
        return lineChartModel;
    }

    public void setLineChartModel(LineChartModel lineChartModel) {
        this.lineChartModel = lineChartModel;
    }

    public BarChartModel getBarChartModel() {
        return barChartModel;
    }

    public void setBarChartModel(BarChartModel barChartModel) {
        this.barChartModel = barChartModel;
    }

    public HorizontalBarChartModel getHorizontalBarChartModel() {
        return horizontalBarChartModel;
    }

    public void setHorizontalBarChartModel(HorizontalBarChartModel horizontalBarChartModel) {
        this.horizontalBarChartModel = horizontalBarChartModel;
    }

    public SurveillanceDataService getSurveillanceDataService() {
        return surveillanceDataService;
    }

    public void setSurveillanceDataService(SurveillanceDataService surveillanceDataService) {
        this.surveillanceDataService = surveillanceDataService;
    }

    public IndicatorService getIndicatorService() {
        return indicatorService;
    }

    public void setIndicatorService(IndicatorService indicatorService) {
        this.indicatorService = indicatorService;
    }

    public MemberService getMemberService() {
        return memberService;
    }

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    private List<Member> members = new ArrayList<>();
    private List<Period> periods = new ArrayList<>();

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }

    public Indicator getIndicator() {
        return indicator;
    }

    public void setIndicator(Indicator indicator) {
        this.indicator = indicator;
    }

    public void createMuptiplePeriodIndicatorChart() {
        lineChartModel = new LineChartModel();
        barChartModel = new BarChartModel();
        horizontalBarChartModel = new HorizontalBarChartModel();

        ChartSeries series = new ChartSeries();
        ChartSeries numerator = new ChartSeries();
        ChartSeries denominator = new ChartSeries();

        series.setLabel(getIndicator().getName());
        numerator.setLabel(getIndicator().getNumerator().getName());
        denominator.setLabel(getIndicator().getDenominator().getName());
        getSurveillanceDataList().clear();
        for (Member member : members) {
            for (Period period : periods) {
                getSurveillanceDataList().addAll(surveillanceDataService.findSurveillanceDataItems(period, member, getIndicator()));
            }
        }
        for (SurveillanceData data : getSurveillanceDataList()) {
            if (data.getIndicator().equals(getIndicator())) {
                series.set(data.getSurveillance().getPeriod().getName(), data.getCalculatedValue());
            }
            numerator.set(data.getSurveillance().getPeriod().getName(), data.getNumeratorValue());
            denominator.set(data.getSurveillance().getPeriod().getName(), data.getDenominatorValue());
        }

        lineChartModel.addSeries(series);
        barChartModel.addSeries(series);

        horizontalBarChartModel.addSeries(numerator);
        horizontalBarChartModel.addSeries(denominator);

    }

    private List<SurveillanceData> surveillanceDataList = new ArrayList<SurveillanceData>();

    public List<SurveillanceData> getSurveillanceDataList() {
        return surveillanceDataList;
    }

    public void setSurveillanceDataList(List<SurveillanceData> surveillanceDataList) {
        this.surveillanceDataList = surveillanceDataList;
    }

    public String loadMuptiplePeriodIndicator() {
        getSurveillanceDataList().clear();
        createMuptiplePeriodIndicatorChart();
        if (lineChartModel.getSeries().isEmpty()) {
            inforMessages("No data has been loaded for the selected criteria.");
            return null;
        } else {
            return null;

        }
    }

    public String reset() {
        return "indicator_view?faces-redirect=true";
    }

    public BarChartModel createModel(BarChartModel model) {

        model.setTitle(indicator.getName());
        model.setLegendPosition("ne");

        Axis xAxis = model.getAxis(AxisType.X);
        xAxis.setLabel("Members");

        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Value");
        yAxis.setMin(0);
        yAxis.setMax(100);
        return model;
    }

    public LineChartModel createModel(LineChartModel model) {

        model.setTitle(indicator.getName());
        model.setLegendPosition("e");

        Axis xAxis = model.getAxis(AxisType.X);
        xAxis.setLabel("Members");

        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Value");
        yAxis.setMin(0);
        yAxis.setMax(100);
        return model;
    }

    public HorizontalBarChartModel createModel(HorizontalBarChartModel model) {

        model.setTitle(indicator.getName());
        model.setLegendPosition("ne");

        Axis xAxis = model.getAxis(AxisType.X);
        xAxis.setLabel("Members");

        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Value");
        yAxis.setMin(0);
        return model;
    }

}
