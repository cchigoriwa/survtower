package com.survtower.client.member.converter;

import com.survtower.business.common.domain.IndicatorGroup;
import com.survtower.business.common.service.IndicatorGroupService;
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
public class IndicatorGroupConverter implements Converter {

    @ManagedProperty(value = "#{indicatorGroupService}")
    private IndicatorGroupService indicatorGroupService;

    public IndicatorGroupService getIndicatorGroupService() {
        return indicatorGroupService;
    }

    public void setIndicatorGroupService(IndicatorGroupService indicatorGroupService) {
        this.indicatorGroupService = indicatorGroupService;
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.isEmpty()) {
            return null;
        } else {
            return indicatorGroupService.find(Long.valueOf(string));
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null) {
            return "";
        }
        IndicatorGroup indicatorGroup = (IndicatorGroup) o;
        return indicatorGroup.getId() == null ? "" : indicatorGroup.getId().toString();
    }

}
