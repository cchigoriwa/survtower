package com.survtower.client.central.utility;

import com.survtower.business.central.domain.CentralUser;
import com.survtower.business.central.service.CentralUserService;
import java.io.Serializable;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class CheckLoggedOn implements Serializable {

    @ManagedProperty(value = "#{centralUserService}")
    private CentralUserService centralUserService;

    public void setCentralUserService(CentralUserService centralUserService) {
        this.centralUserService = centralUserService;
    }

    public void userNotLoggedOn() {
        //check to see if user is logged off and deny access dash board 
        if (!getLoggedOn()) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) facesContext.getApplication().getNavigationHandler();
            nav.performNavigation("login");

        }
    }

    public void userLoggedOn() {
        //check to see if user is already logged on and redirect to index page
        if (getLoggedOn()) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) facesContext.getApplication().getNavigationHandler();
            nav.performNavigation("index");
        }
    }

    public CentralUser getCurrentUser() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext externalContext = fc.getExternalContext();
        if (externalContext.getUserPrincipal() == null) {
            return null;
        } else {
            String user = externalContext.getUserPrincipal().getName();
            CentralUser centralUser = centralUserService.findByUserName(user);
            if (centralUser != null) {
                return centralUser;
            } else {
                return null;
            }
        }
    }

    public Boolean getLoggedOn() {
        return getCurrentUser() != null;
    }
}
