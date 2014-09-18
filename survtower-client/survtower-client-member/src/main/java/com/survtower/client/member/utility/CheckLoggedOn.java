package com.survtower.client.member.utility;

import com.survtower.business.member.domain.MemberUser;
import com.survtower.business.member.service.MemberUserService;
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

    @ManagedProperty(value = "#{memberUserService}")
    private MemberUserService memberUserService;

    public void setMemberUserService(MemberUserService memberUserService) {
        this.memberUserService = memberUserService;
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

    public MemberUser getCurrentUser() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext externalContext = fc.getExternalContext();
        if (externalContext.getUserPrincipal() == null) {
            return null;
        } else {
            String user = externalContext.getUserPrincipal().getName();
            MemberUser memberUser = memberUserService.findByUserName(user);
            if (memberUser != null) {
                return memberUser;
            } else {
                return null;
            }
        }
    }

    public Boolean getLoggedOn() {
        return getCurrentUser() != null;
    }
}
