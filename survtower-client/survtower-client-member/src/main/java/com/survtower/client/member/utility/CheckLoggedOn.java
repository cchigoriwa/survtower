package com.survtower.client.member.utility;

import com.survtower.business.member.service.MemberUserService;
import java.io.Serializable;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
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
        if (memberUserService.getCurrentUser() == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) facesContext.getApplication().getNavigationHandler();
            nav.performNavigation("access_denied");

        }
    }

    public void userLoggedOn() {
        //check to see if user is already logged on and redirect to index page
        if (memberUserService.getCurrentUser() == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ConfigurableNavigationHandler nav = (ConfigurableNavigationHandler) facesContext.getApplication().getNavigationHandler();
            nav.performNavigation("index");
        }
    }
}
