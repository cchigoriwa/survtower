package com.survtower.client.central.controller;

import com.survtower.business.common.domain.Frequency;
import com.survtower.business.common.service.FrequencyService;
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
public class FrequencyEditController {

    @ManagedProperty(value = "#{frequencyService}")
    private FrequencyService frequencyService;

    @ManagedProperty(value = "#{param.uuid}")
    private String uuid;

    private Frequency frequency;

    public Frequency getFrequency() {
        return frequency;
    }

    public String save() {
        frequencyService.save(frequency);
        return "frequencyList?faces-redirect=true&src=edit";
    }

    public FrequencyService getFrequencyService() {
        return frequencyService;
    }

    public void setFrequencyService(FrequencyService frequencyService) {
        this.frequencyService = frequencyService;
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
            frequency = new Frequency();
        } else {
            frequency = frequencyService.findByUuid(uuid);
            if (frequency == null) {
                frequency = new Frequency();
            }
        }
    }

}
