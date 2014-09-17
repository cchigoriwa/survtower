package com.survtower.client.member.controller;

import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.service.PeriodService;
import com.survtower.business.member.domain.report.AduitItem;
import com.survtower.business.member.service.SurveillanceAuditService;
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

    @ManagedProperty(value = "#{surveillanceAuditService}")
    private SurveillanceAuditService surveillanceAuditService;

    public void setSurveillanceAuditService(SurveillanceAuditService surveillanceAuditService) {
        this.surveillanceAuditService = surveillanceAuditService;
    }

    private List<AduitItem> aduitItems;

    private List<Period> activePeriods;

    private List<Period> pastDueDatePeriods;

    public List<AduitItem> getAduitItems() {
        return aduitItems;
    }

    public void setAduitItems(List<AduitItem> aduitItems) {
        this.aduitItems = aduitItems;
    }

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
        aduitItems = new ArrayList<>();
        for (Period p : periodService.fetchActive()) {
            if (p.getActive()) {
                activePeriods.add(p);
            }
            if (p.getDueDatePassed()) {
                pastDueDatePeriods.add(p);
            }
        }

        for (Period period : activePeriods) {
            for (Program program : period.getPrograms()) {
                AduitItem item = new AduitItem();
                item.setPeriod(period);
                item.setProgram(program);
                item.setSurveillanceAudit(surveillanceAuditService.findByProgramAndPeriod(program, period));
                aduitItems.add(item);
            }
        }
    }

    //Create Custom Class Period and Program (Use Audit to Find Out Submitted and Approved)
}
