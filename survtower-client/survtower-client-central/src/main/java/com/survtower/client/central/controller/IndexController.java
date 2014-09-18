package com.survtower.client.central.controller;

import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.domain.Surveillance;
import com.survtower.business.common.service.PeriodService;
import com.survtower.business.common.service.SurveillanceService;
import static com.survtower.client.central.utility.MessageInfor.errorMessages;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Charles Chigoriwa
 */
@ManagedBean
@RequestScoped
public class IndexController {

    @ManagedProperty(value = "#{surveillanceService}")
    private SurveillanceService surveillanceService;

    @ManagedProperty(value = "#{periodService}")
    private PeriodService periodService;

    public void setSurveillanceService(SurveillanceService surveillanceService) {
        this.surveillanceService = surveillanceService;
    }

    public void setPeriodService(PeriodService periodService) {
        this.periodService = periodService;
    }

    private List<Surveillance> surveillances = new ArrayList<Surveillance>();

    public List<Surveillance> getSurveillances() {
        return surveillances;
    }

    public void setSurveillances(List<Surveillance> surveillances) {
        this.surveillances = surveillances;
    }

    @PostConstruct
    public void LoadData() {
        surveillances = new ArrayList<Surveillance>();
        for (Period period : periodService.fetchActive()) {
            for (Program program : period.getPrograms()) {
                surveillances.add(surveillanceService.findByProgramAndPeriod(program, period));
            }
        }
    }
}
