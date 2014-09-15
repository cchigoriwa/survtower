package com.survtower.client.member.controller;

import com.survtower.business.common.domain.Period;
import com.survtower.business.common.service.PeriodService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Takunda Dhlakama
 */
@ManagedBean
@RequestScoped
public class IndexController {

    @ManagedProperty(value = "#{periodService}")
    PeriodService periodService;

    private List<Period> activePeriods;

    private List<Period> pastDueDatePeriods;

    public PeriodService getPeriodService() {
        return periodService;
    }

    public void setPeriodService(PeriodService periodService) {
        this.periodService = periodService;
    }

    public List<Period> getActivePeriods() {
        return activePeriods;
    }

    public void setActivePeriods(List<Period> activePeriods) {
        this.activePeriods = activePeriods;
    }

    public List<Period> getPastDueDatePeriods() {
        return pastDueDatePeriods;
    }

    public void setPastDueDatePeriods(List<Period> pastDueDatePeriods) {
        this.pastDueDatePeriods = pastDueDatePeriods;
    }

    @PostConstruct
    public void init() {
        activePeriods = new ArrayList<>();
        pastDueDatePeriods = new ArrayList<>();
        for (Period p : periodService.fetchActive()) {
            if (p.getActive()) {
                activePeriods.add(p);
            }
            if (p.getDueDatePassed()) {
                pastDueDatePeriods.add(p);
            }
        }
    }

}
