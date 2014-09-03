package com.survtower.client.member.controller;

import com.survtower.business.member.integration.IndicatorIntegrator;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Charles Chigoriwa
 */
@ManagedBean
@RequestScoped
public class IntegrateIndicatorController {
    
    @ManagedProperty(value = "#{indicatorIntegrator}")
    private IndicatorIntegrator indicatorIntegrator;
    
    
    public String pull(){
        indicatorIntegrator.pull();
        return "listIndicator?faces-redirect=true";
    }

    public IndicatorIntegrator getIndicatorIntegrator() {
        return indicatorIntegrator;
    }

    public void setIndicatorIntegrator(IndicatorIntegrator indicatorIntegrator) {
        this.indicatorIntegrator = indicatorIntegrator;
    }
    
    
    
}
