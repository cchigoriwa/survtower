package com.survtower.client.member.converter;

import com.survtower.business.common.domain.Frequency;
import com.survtower.business.common.service.FrequencyService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Takunda Dhlakama
 */
@ManagedBean
@RequestScoped
public class FrequencyConverter implements Converter {

    @ManagedProperty(value = "#{frequencyService}")
    private FrequencyService frequencyService;

    public FrequencyService getFrequencyService() {
        return frequencyService;
    }

    public void setFrequencyService(FrequencyService frequencyService) {
        this.frequencyService = frequencyService;
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.isEmpty()) {
            return null;
        } else {
            return frequencyService.find(Long.valueOf(string));
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null) {
            return "";
        }
        Frequency frequency = (Frequency) o;
        return frequency.getId() == null ? "" : frequency.getId().toString();
    }

}
