package com.survtower.client.member.controller;

import com.survtower.business.common.domain.Period;
import com.survtower.business.common.service.PeriodService;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Takunda Dhlakama
 */
@ManagedBean
@RequestScoped
public class PeriodListController {
    
    @ManagedProperty(value = "#{periodService}")
    private PeriodService periodService;
    
    public List<Period> getPeriods(){
        return periodService.findAll();
    }

    public PeriodService getPeriodService() {
        return periodService;
    }
    
    public void setPeriodService(PeriodService periodService) {
        this.periodService = periodService;
    }
    
}
