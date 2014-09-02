package com.survtower.client.central.controller;

import com.survtower.business.common.domain.Indicator;
import com.survtower.business.common.service.IndicatorService;
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
public class EditIndicatorController {

    @ManagedProperty(value = "#{indicatorService}")
    private IndicatorService indicatorService;

    @ManagedProperty(value = "#{param.uuid}")
    private String uuid;

    private Indicator indicator;

    public Indicator getIndicator() {
        return indicator;
    }

    public String save() {
        indicatorService.save(indicator);
        return "listIndicator?faces-redirect=true";
    }

    public IndicatorService getIndicatorService() {
        return indicatorService;
    }

    public void setIndicatorService(IndicatorService indicatorService) {
        this.indicatorService = indicatorService;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    
    
    
    @PostConstruct
    public void postConstruct(){
        if(uuid==null){
            indicator=new Indicator();
        }else{
            indicator=indicatorService.findByUuid(uuid);
            if(indicator==null){
                indicator=new Indicator();
            }
        }
    }

}
