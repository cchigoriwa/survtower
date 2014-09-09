package com.survtower.client.central.controller;

import com.survtower.business.common.domain.IndicatorType;
import com.survtower.business.common.service.IndicatorTypeService;
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
public class IndicatorTypeEditController {

    @ManagedProperty(value = "#{indicatorTypeService}")
    private IndicatorTypeService indicatorTypeService;

    @ManagedProperty(value = "#{param.uuid}")
    private String uuid;

    private IndicatorType indicatorType;

    public IndicatorType getIndicatorType() {
        return indicatorType;
    }

    public String save() {
        indicatorTypeService.save(indicatorType);
        return "indicatorTypeList?faces-redirect=true";
    }

    public IndicatorTypeService getIndicatorTypeService() {
        return indicatorTypeService;
    }

    public void setIndicatorTypeService(IndicatorTypeService indicatorTypeService) {
        this.indicatorTypeService = indicatorTypeService;
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
            indicatorType = new IndicatorType();
        } else {
            indicatorType = indicatorTypeService.findByUuid(uuid);
            if (indicatorType == null) {
                indicatorType = new IndicatorType();
            }
        }
    }

}
