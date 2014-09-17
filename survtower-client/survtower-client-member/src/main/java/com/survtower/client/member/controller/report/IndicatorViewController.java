package com.survtower.client.member.controller.report;

import com.survtower.business.common.domain.Indicator;
import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.SurveillanceData;
import com.survtower.business.common.service.IndicatorService;
import com.survtower.business.common.service.MemberService;
import com.survtower.business.common.service.SurveillanceDataService;
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
public class IndicatorViewController extends MessageInfor implements Serializable {

    @ManagedProperty(value = "#{surveillanceDataService}")
    private SurveillanceDataService surveillanceDataService;

    @ManagedProperty(value = "#{indicatorService}")
    private IndicatorService indicatorService;

    @ManagedProperty(value = "#{memberService}")
    private MemberService memberService;

    private Indicator indicator;
    private Period period;
    private CartesianChartModel linearModel = new CartesianChartModel();
    private CartesianChartModel dataElementsModel = new CartesianChartModel();
    private List<Period> periods = new ArrayList<Period>();
    private List<SurveillanceData> surveillanceDataList = new ArrayList<SurveillanceData>();

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

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
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

    public void createSinglePeriodIndicatorChart() {
        linearModel = new CartesianChartModel();
        ChartSeries series = new ChartSeries();
        ChartSeries numerator = new ChartSeries();
        ChartSeries denominator = new ChartSeries();
        dataElementsModel = new CartesianChartModel();
        series.setLabel(getIndicator().getName());
        numerator.setLabel(getIndicator().getNumerator().getName());
        denominator.setLabel(getIndicator().getDenominator().getName());
        getSurveillanceDataList().clear();
        getSurveillanceDataList().addAll(surveillanceDataService.findSurveillanceDataItems(getPeriod(), memberService.getCurrentMember(), getIndicator()));
        for (SurveillanceData data : getSurveillanceDataList()) {
            if (data.getIndicator().equals(getIndicator())) {
                series.set(data.getSurveillance().getPeriod().getName(), data.getCalculatedValue());
            }
            numerator.set(data.getSurveillance().getPeriod().getName(), data.getNumeratorValue());
            denominator.set(data.getSurveillance().getPeriod().getName(), data.getDenominatorValue());
        }

        linearModel.addSeries(series);
        dataElementsModel.addSeries(numerator);
        dataElementsModel.addSeries(denominator);

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
        for (Period p : periods) {
            getSurveillanceDataList().addAll(surveillanceDataService.findSurveillanceDataItems(p, memberService.getCurrentMember(), getIndicator()));
        }
        for (SurveillanceData data : getSurveillanceDataList()) {
            if (data.getIndicator().equals(getIndicator())) {
                series.set(data.getSurveillance().getPeriod().getName(), data.getCalculatedValue());
            }
            numerator.set(data.getSurveillance().getPeriod().getName(), data.getNumeratorValue());
            denominator.set(data.getSurveillance().getPeriod().getName(), data.getDenominatorValue());
        }

        linearModel.addSeries(series);
        dataElementsModel.addSeries(numerator);
        dataElementsModel.addSeries(denominator);

    }

    public List<SurveillanceData> getSurveillanceDataList() {
        return surveillanceDataList;
    }

    public void setSurveillanceDataList(List<SurveillanceData> surveillanceDataList) {
        this.surveillanceDataList = surveillanceDataList;
    }

    public String loadSinglePeriodIndicator() {
        System.out.println("------------------------" + indicator.getName());
        getSurveillanceDataList().clear();
        createSinglePeriodIndicatorChart();
        if (linearModel.getSeries().isEmpty()) {
            inforMessages("No data has been loaded for the selected criteria.");
            return null;
        } else {
            return null;
        }
    }

    public String loadMuptiplePeriodIndicator() {
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
        indicator = null;
        getSurveillanceDataList().clear();
        getPeriods().clear();
        return null;
    }

}
