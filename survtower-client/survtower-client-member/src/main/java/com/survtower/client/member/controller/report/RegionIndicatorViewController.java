package com.survtower.client.member.controller.report;

import com.survtower.business.common.domain.Indicator;
import com.survtower.business.common.domain.Period;
import com.survtower.business.member.domain.Region;
import com.survtower.business.member.domain.RegionSurveillanceData;
import com.survtower.business.member.service.RegionSurveillanceDataService;
import com.survtower.client.member.utility.MessageInfor;
import static com.survtower.client.member.utility.MessageInfor.inforMessages;
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
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author Takund Dhlakama
 */
@ManagedBean
@ViewScoped
public class RegionIndicatorViewController extends MessageInfor implements Serializable {

    @ManagedProperty(value = "#{regionSurveillanceDataService}")
    private RegionSurveillanceDataService regionSurveillanceDataService;

    public void setRegionSurveillanceDataService(RegionSurveillanceDataService regionSurveillanceDataService) {
        this.regionSurveillanceDataService = regionSurveillanceDataService;
    }

    private Indicator indicator;
    private LineChartModel lineChartModel = new LineChartModel();
    private BarChartModel barChartModel = new BarChartModel();
    private HorizontalBarChartModel horizontalBarChartModel = new HorizontalBarChartModel();

    private List<Period> periods = new ArrayList<>();
    private Region region;

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }

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

    public Indicator getIndicator() {
        return indicator;
    }

    public void setIndicator(Indicator indicator) {
        this.indicator = indicator;
    }

    public void createMuptiplePeriodIndicatorChart() {

        lineChartModel = createModel(new LineChartModel());
        barChartModel = createModel(new BarChartModel());
        horizontalBarChartModel = createModel(new HorizontalBarChartModel());

        ChartSeries series = new ChartSeries();
        LineChartSeries lineChartSeries = new LineChartSeries();
        ChartSeries numerator = new ChartSeries();
        ChartSeries denominator = new ChartSeries();

        series.setLabel(getIndicator().getName());
        lineChartSeries.setLabel(getIndicator().getName());

        numerator.setLabel(getIndicator().getNumeratorDataElement().getName());
        denominator.setLabel(getIndicator().getDenominatorDataElement().getName());
        getSurveillanceDataList().clear();
        for (Period period : periods) {
            getSurveillanceDataList().addAll(regionSurveillanceDataService.findAll(period, getIndicator(), region));
        }

        for (RegionSurveillanceData data : getSurveillanceDataList()) {
            if (data.getSurveillanceData().getIndicator().equals(getIndicator())) {
                series.set(data.getSurveillanceData().getSurveillance().getPeriod().getName(), data.getCalculatedValue());
                lineChartSeries.set(data.getSurveillanceData().getSurveillance().getPeriod().getName(), data.getCalculatedValue());
            }
            numerator.set(data.getSurveillanceData().getSurveillance().getPeriod().getName(), data.getNumeratorValue());
            denominator.set(data.getSurveillanceData().getSurveillance().getPeriod().getName(), data.getDenominatorValue());
        }
        barChartModel.addSeries(series);
        lineChartModel.addSeries(lineChartSeries);
        horizontalBarChartModel.addSeries(numerator);
        horizontalBarChartModel.addSeries(denominator);

    }

    private List<RegionSurveillanceData> surveillanceDataList = new ArrayList<>();

    public List<RegionSurveillanceData> getSurveillanceDataList() {
        return surveillanceDataList;
    }

    public void setSurveillanceDataList(List<RegionSurveillanceData> surveillanceDataList) {
        this.surveillanceDataList = surveillanceDataList;
    }

    public String loadMuptiplePeriodIndicator() {
        if (indicator == null) {
            inforMessages("Select Indicator to Continue.");
            return null;
        }

        if (periods.isEmpty()) {
            inforMessages("Select Periods to Continue.");
            return null;
        }

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
        return "region_indicator_view?faces-redirect=true";
    }

    public BarChartModel createModel(BarChartModel model) {

        model.setTitle(indicator.getName());
        model.setLegendPosition("ne");

        Axis xAxis = model.getAxis(AxisType.X);
        xAxis.setLabel(region.getName());

        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Value");
        yAxis.setMin(0);
        yAxis.setMax(100);
        return model;
    }

    public LineChartModel createModel(LineChartModel model) {

        model.setTitle(indicator.getName());
        model.setLegendPosition("e");
        model.setShowPointLabels(true);

        Axis xAxis = model.getAxis(AxisType.X);
        xAxis.setLabel(region.getName());

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
        xAxis.setLabel(region.getName());

        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setLabel("Value");
        yAxis.setMin(0);
        return model;
    }
}
