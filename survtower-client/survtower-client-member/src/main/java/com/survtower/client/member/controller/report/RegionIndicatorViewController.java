package com.survtower.client.member.controller.report;

import com.survtower.business.common.domain.Indicator;
import com.survtower.business.common.domain.Period;
import com.survtower.business.member.domain.Region;
import com.survtower.business.member.domain.RegionSurveillanceData;
import com.survtower.business.member.service.RegionService;
import com.survtower.business.member.service.RegionSurveillanceDataService;
import com.survtower.client.member.utility.MessageInfor;
import static com.survtower.client.member.utility.MessageInfor.inforMessages;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author Takund Dhlakama
 */
@ManagedBean
@ViewScoped
public class RegionIndicatorViewController extends MessageInfor implements Serializable {

    @ManagedProperty(value = "#{regionSurveillanceDataService}")
    private RegionSurveillanceDataService regionSurveillanceDataService;

    @ManagedProperty(value = "#{regionService}")
    private RegionService regionService;

    public void setRegionSurveillanceDataService(RegionSurveillanceDataService regionSurveillanceDataService) {
        this.regionSurveillanceDataService = regionSurveillanceDataService;
    }

    public void setRegionService(RegionService regionService) {
        this.regionService = regionService;
    }

    private Indicator indicator;
    private CartesianChartModel linearModel = new CartesianChartModel();
    private CartesianChartModel dataElementsModel = new CartesianChartModel();
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

    public CartesianChartModel getLinearModel() {
        return linearModel;
    }

    public void setLinearModel(CartesianChartModel linearModel) {
        this.linearModel = linearModel;
    }

    public CartesianChartModel getDataElementsModel() {
        return dataElementsModel;
    }

    public void setDataElementsModel(CartesianChartModel dataElementsModel) {
        this.dataElementsModel = dataElementsModel;
    }

    public Indicator getIndicator() {
        return indicator;
    }

    public void setIndicator(Indicator indicator) {
        this.indicator = indicator;
    }

    public void createMuptiplePeriodIndicatorChart() {
        linearModel = new CartesianChartModel();
        ChartSeries series = new ChartSeries();
        ChartSeries numerator = new ChartSeries();
        ChartSeries denominator = new ChartSeries();
        dataElementsModel = new CartesianChartModel();
        series.setLabel(getIndicator().getName());
        numerator.setLabel(getIndicator().getNumerator().getName());
        denominator.setLabel(getIndicator().getDenominator().getName());
        getSurveillanceDataList().clear();
        for (Period period : periods) {
            getSurveillanceDataList().addAll(regionSurveillanceDataService.findAll(period, getIndicator(), region));
        }

        for (RegionSurveillanceData data : getSurveillanceDataList()) {
            if (data.getSurveillanceData().getIndicator().equals(getIndicator())) {
                series.set(data.getSurveillanceData().getSurveillance().getPeriod().getName(), data.getCalculatedValue());
            }
            numerator.set(data.getSurveillanceData().getSurveillance().getPeriod().getName(), data.getNumeratorValue());
            denominator.set(data.getSurveillanceData().getSurveillance().getPeriod().getName(), data.getDenominatorValue());
        }

        linearModel.addSeries(series);
        dataElementsModel.addSeries(numerator);
        dataElementsModel.addSeries(denominator);

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
        if (linearModel.getSeries().isEmpty()) {
            inforMessages("No data has been loaded for the selected criteria.");
            return null;
        } else {
            return null;

        }
    }

    public String reset() {
        return "region_indicator_view?faces-redirect=true";
    }

}
