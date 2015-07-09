package com.survtower.client.central.controller;

import com.survtower.business.common.domain.IndicatorType;
import com.survtower.business.common.service.IndicatorTypeService;
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
public class IndicatorTypeListController {

    @ManagedProperty(value = "#{indicatorTypeService}")
    private IndicatorTypeService indicatorTypeService;

    public List<IndicatorType> getIndicatorTypes() {
        return indicatorTypeService.findAll();
    }

    public IndicatorTypeService getIndicatorTypeService() {
        return indicatorTypeService;
    }

    public void setIndicatorTypeService(IndicatorTypeService indicatorTypeService) {
        this.indicatorTypeService = indicatorTypeService;
    }
    
    public String createNewIndicatorType() {
        return "indicatorTypeEdit?faces-redirect=true";
    }

}
