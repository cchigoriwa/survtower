package com.survtower.client.member.controller;

import com.survtower.business.member.integration.DataElementIntegrator;
import com.survtower.business.member.integration.DataSourceCategoryIntegrator;
import com.survtower.business.member.integration.DataSourceIntegrator;
import com.survtower.business.member.integration.FrequencyIntegrator;
import com.survtower.business.member.integration.IndicatorGroupIntegrator;
import com.survtower.business.member.integration.IndicatorIntegrator;
import com.survtower.business.member.integration.IndicatorTypeIntegrator;
import com.survtower.business.member.integration.PeriodIntegrator;
import com.survtower.business.member.integration.ProgramIntegrator;
import com.survtower.client.member.utility.MessageInfor;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Charles Chigoriwa
 */
@ManagedBean
@RequestScoped
public class IntegratorController {

    @ManagedProperty(value = "#{programIntegrator}")
    private ProgramIntegrator programIntegrator;

    @ManagedProperty(value = "#{indicatorGroupIntegrator}")
    private IndicatorGroupIntegrator indicatorGroupIntegrator;

    @ManagedProperty(value = "#{indicatorIntegrator}")
    private IndicatorIntegrator indicatorIntegrator;

    @ManagedProperty(value = "#{dataElementIntegrator}")
    private DataElementIntegrator dataElementIntegrator;

    @ManagedProperty(value = "#{dataSourceCategoryIntegrator}")
    private DataSourceCategoryIntegrator dataSourceCategoryIntegrator;

    @ManagedProperty(value = "#{dataSourceIntegrator}")
    private DataSourceIntegrator dataSourceIntegrator;

    @ManagedProperty(value = "#{frequencyIntegrator}")
    private FrequencyIntegrator frequencyIntegrator;

    @ManagedProperty(value = "#{periodIntegrator}")
    private PeriodIntegrator periodIntegrator;

    @ManagedProperty(value = "#{indicatorTypeIntegrator}")
    private IndicatorTypeIntegrator indicatorTypeIntegrator;

    public String pullDataElements() {
        try {
            dataElementIntegrator.pull();
        } catch (Exception e) {
            MessageInfor.errorMessages("Could not update from surveillance server");
            return null;
        }
        
        return "dataElementList?faces-redirect=true";
    }

    public String pullIndicators() {
        try {
            indicatorIntegrator.pull();
        } catch (Exception e) {
            MessageInfor.errorMessages("Could not update from surveillance server");
            return null;
        }

        return "indicatorList?faces-redirect=true";
    }

    public String pullDataSources() {
        try {
            dataSourceIntegrator.pull();
        } catch (Exception e) {
            MessageInfor.errorMessages("Could not update from surveillance server");
            return null;
        }
        
        return "dataSourceList?faces-redirect=true";
    }

    public String pullDataSourceCategorys() {
        try {
            dataSourceCategoryIntegrator.pull();
        } catch (Exception e) {
            MessageInfor.errorMessages("Could not update from surveillance server");
            return null;
        }
        
        return "dataSourceList?faces-redirect=true";
    }

    public String pull() {
        try {
            frequencyIntegrator.pull();
        } catch (Exception e) {
            MessageInfor.errorMessages("Could not update from surveillance server");
            return null;
        }

        return "frequencyList?faces-redirect=true";
    }

    public String pullIndicatorGroups() {
        try {
            indicatorGroupIntegrator.pull();
        } catch (Exception e) {
            MessageInfor.errorMessages("Could not update from surveillance server");
            return null;
        }

        return "indicatorGroupList?faces-redirect=true";
    }

    public String pullIndicatorTypes() {
        try {
            indicatorTypeIntegrator.pull();
        } catch (Exception e) {
            MessageInfor.errorMessages("Could not update from surveillance server");
            return null;
        }
        
        return "indicatorTypeList?faces-redirect=true";
    }

    public String pullPeriods() {
        try {
            periodIntegrator.pull();
        } catch (Exception e) {
            MessageInfor.errorMessages("Could not update from surveillance server");
            return null;
        }
        
        return "periodList?faces-redirect=true";
    }

    public String pullPrograms() {
        try {
            programIntegrator.pull();
        } catch (Exception e) {
            MessageInfor.errorMessages("Could not update from surveillance server");
            return null;
        }
        
        return "programList?faces-redirect=true";
    }

    public String pullFrequencys() {
        try {
            frequencyIntegrator.pull();
        } catch (Exception e) {
            MessageInfor.errorMessages("Could not update from surveillance server");
            return null;
        }
        
        return "frequencyList?faces-redirect=true";
    }

    public ProgramIntegrator getProgramIntegrator() {
        return programIntegrator;
    }

    public void setProgramIntegrator(ProgramIntegrator programIntegrator) {
        this.programIntegrator = programIntegrator;
    }

    public IndicatorGroupIntegrator getIndicatorGroupIntegrator() {
        return indicatorGroupIntegrator;
    }

    public void setIndicatorGroupIntegrator(IndicatorGroupIntegrator indicatorGroupIntegrator) {
        this.indicatorGroupIntegrator = indicatorGroupIntegrator;
    }

    public IndicatorIntegrator getIndicatorIntegrator() {
        return indicatorIntegrator;
    }

    public void setIndicatorIntegrator(IndicatorIntegrator indicatorIntegrator) {
        this.indicatorIntegrator = indicatorIntegrator;
    }

    public DataElementIntegrator getDataElementIntegrator() {
        return dataElementIntegrator;
    }

    public void setDataElementIntegrator(DataElementIntegrator dataElementIntegrator) {
        this.dataElementIntegrator = dataElementIntegrator;
    }

    public DataSourceCategoryIntegrator getDataSourceCategoryIntegrator() {
        return dataSourceCategoryIntegrator;
    }

    public void setDataSourceCategoryIntegrator(DataSourceCategoryIntegrator dataSourceCategoryIntegrator) {
        this.dataSourceCategoryIntegrator = dataSourceCategoryIntegrator;
    }

    public DataSourceIntegrator getDataSourceIntegrator() {
        return dataSourceIntegrator;
    }

    public void setDataSourceIntegrator(DataSourceIntegrator dataSourceIntegrator) {
        this.dataSourceIntegrator = dataSourceIntegrator;
    }

    public FrequencyIntegrator getFrequencyIntegrator() {
        return frequencyIntegrator;
    }

    public void setFrequencyIntegrator(FrequencyIntegrator frequencyIntegrator) {
        this.frequencyIntegrator = frequencyIntegrator;
    }

    public PeriodIntegrator getPeriodIntegrator() {
        return periodIntegrator;
    }

    public void setPeriodIntegrator(PeriodIntegrator periodIntegrator) {
        this.periodIntegrator = periodIntegrator;
    }

    public IndicatorTypeIntegrator getIndicatorTypeIntegrator() {
        return indicatorTypeIntegrator;
    }

    public void setIndicatorTypeIntegrator(IndicatorTypeIntegrator indicatorTypeIntegrator) {
        this.indicatorTypeIntegrator = indicatorTypeIntegrator;
    }

}
