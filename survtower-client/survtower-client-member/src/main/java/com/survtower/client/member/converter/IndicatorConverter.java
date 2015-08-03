package com.survtower.client.member.converter;

import com.survtower.business.common.domain.Indicator;
import com.survtower.business.common.service.IndicatorService;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 *
 * @author Takunda Dhlakama
 */
@ManagedBean
@RequestScoped
public class IndicatorConverter implements Converter {

    @ManagedProperty(value = "#{indicatorService}")
    private IndicatorService indicatorService;

    public IndicatorService getIndicatorService() {
        return indicatorService;
    }

    public void setIndicatorService(IndicatorService indicatorService) {
        this.indicatorService = indicatorService;
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        string = string.trim();
        if (string == null || string.isEmpty()) {
            return null;
        } else {
            try {
                return indicatorService.find(Long.valueOf(string));
            } catch (NumberFormatException ex) {
                FacesMessage msg = new FacesMessage("Indicator Conversion error.",
                        "Invalid Indicator Name.");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ConverterException(msg);
            }
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null) {
            return "";
        }
        Indicator indicator = (Indicator) o;
        return indicator.getId() == null ? "" : indicator.getId().toString();
    }

}
