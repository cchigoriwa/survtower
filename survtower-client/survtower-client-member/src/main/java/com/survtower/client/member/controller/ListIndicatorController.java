package com.survtower.client.member.controller;

import com.survtower.business.common.domain.Indicator;
import com.survtower.business.common.service.IndicatorService;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Charles Chigoriwa
 */
@ManagedBean
@RequestScoped
public class ListIndicatorController {
    
    @ManagedProperty(value = "#{indicatorService}")
    private IndicatorService indicatorService;
    
    public List<Indicator> getIndicators(){
        return indicatorService.findAll();
    }

    public IndicatorService getIndicatorService() {
        return indicatorService;
    }
    
    public void setIndicatorService(IndicatorService indicatorService) {
        this.indicatorService = indicatorService;
    }
    
    
}
