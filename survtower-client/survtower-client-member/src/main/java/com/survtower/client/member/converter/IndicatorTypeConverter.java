package com.survtower.client.member.converter;

import com.survtower.business.common.domain.IndicatorType;
import com.survtower.business.common.service.IndicatorTypeService;
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
public class IndicatorTypeConverter implements Converter {

    @ManagedProperty(value = "#{indicatorTypeService}")
    private IndicatorTypeService indicatorTypeService;

    public IndicatorTypeService getIndicatorTypeService() {
        return indicatorTypeService;
    }

    public void setIndicatorTypeService(IndicatorTypeService indicatorTypeService) {
        this.indicatorTypeService = indicatorTypeService;
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.isEmpty()) {
            return null;
        } else {
            return indicatorTypeService.find(Long.valueOf(string));
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null) {
            return "";
        }
        IndicatorType indicatorType = (IndicatorType) o;
        return indicatorType.getId() == null ? "" : indicatorType.getId().toString();
    }

}
