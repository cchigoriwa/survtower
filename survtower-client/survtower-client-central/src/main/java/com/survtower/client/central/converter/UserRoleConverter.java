package com.survtower.client.central.converter;

import com.survtower.business.common.domain.UserRole;
import com.survtower.business.common.service.UserRoleService;
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
public class UserRoleConverter implements Converter {

    @ManagedProperty(value = "#{userRoleService}")
    private UserRoleService userRoleService;

    public UserRoleService getUserRoleService() {
        return userRoleService;
    }

    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.isEmpty()) {
            return null;
        } else {
            return userRoleService.find(Long.valueOf(string));
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null) {
            return "";
        }
        UserRole userRole = (UserRole) o;
        return userRole.getId() == null ? "" : userRole.getId().toString();
    }

}
