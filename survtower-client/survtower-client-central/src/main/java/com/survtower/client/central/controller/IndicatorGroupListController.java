package com.survtower.client.central.controller;

import com.survtower.business.common.domain.IndicatorGroup;
import com.survtower.business.common.service.IndicatorGroupService;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Charles Chigoriwa
 */
@ManagedBean
@RequestScoped
public class IndicatorGroupListController {
    
    @ManagedProperty(value = "#{indicatorGroupService}")
    private IndicatorGroupService indicatorGroupService;
    
    public List<IndicatorGroup> getIndicatorGroups(){
        return indicatorGroupService.findAll();
    }

    public IndicatorGroupService getIndicatorGroupService() {
        return indicatorGroupService;
    }
    
    public void setIndicatorGroupService(IndicatorGroupService indicatorGroupService) {
        this.indicatorGroupService = indicatorGroupService;
    }
    
}
