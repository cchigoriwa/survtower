package com.survtower.client.central.controller;

import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.service.PeriodService;
import java.util.ArrayList;
import java.util.HashSet;
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
public class PeriodEditController {

    @ManagedProperty(value = "#{periodService}")
    private PeriodService periodService;

    @ManagedProperty(value = "#{param.uuid}")
    private String uuid;

    private List<Program> programs = new ArrayList<Program>();

    private Period period;

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    public Period getPeriod() {
        return period;
    }

    public String save() {
        period.setPrograms(new HashSet<Program>(programs));
        periodService.save(period);
        return "periodList?faces-redirect=true&src=edit";
    }

    public PeriodService getPeriodService() {
        return periodService;
    }

    public void setPeriodService(PeriodService periodService) {
        this.periodService = periodService;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @PostConstruct
    public void postConstruct() {
        if (uuid == null) {
            period = new Period();
            programs = new ArrayList<Program>();
        } else {
            period = periodService.findByUuid(uuid);
            if (period == null) {
                period = new Period();
                programs = new ArrayList<Program>();
            }else{
                programs=new ArrayList<Program>(period.getPrograms());
            }
        }        
        
        setPrograms(programs);
    }

}
