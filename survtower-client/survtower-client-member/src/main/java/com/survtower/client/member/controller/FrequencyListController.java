package com.survtower.client.member.controller;

import com.survtower.business.common.domain.Frequency;
import com.survtower.business.common.service.FrequencyService;
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
public class FrequencyListController {

    @ManagedProperty(value = "#{frequencyService}")
    private FrequencyService frequencyService;

    public List<Frequency> getFrequencys() {
        return frequencyService.findAll();
    }

    public FrequencyService getFrequencyService() {
        return frequencyService;
    }

    public void setFrequencyService(FrequencyService frequencyService) {
        this.frequencyService = frequencyService;
    }

}
