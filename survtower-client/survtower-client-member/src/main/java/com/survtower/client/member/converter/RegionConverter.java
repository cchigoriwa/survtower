package com.survtower.client.member.converter;

import com.survtower.business.member.domain.Region;
import com.survtower.business.member.service.RegionService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author Charles Chigoriwa
 */
@ManagedBean
@RequestScoped
public class RegionConverter implements Converter {

    @ManagedProperty(value = "#{regionService}")
    private RegionService regionService;

    public RegionService getRegionService() {
        return regionService;
    }

    public void setRegionService(RegionService regionService) {
        this.regionService = regionService;
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.isEmpty()) {
            return null;
        } else {
            return regionService.find(Long.valueOf(string));
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null) {
            return "";
        }
        Region region = (Region) o;
        return region.getId() == null ? "" : region.getId().toString();
    }

}
