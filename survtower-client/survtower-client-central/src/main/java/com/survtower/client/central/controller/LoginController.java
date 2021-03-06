package com.survtower.client.central.controller;

import com.survtower.business.central.domain.CentralUser;
import com.survtower.client.central.bean.CentralUserUtility;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author Takunda Dhlakama
 */
@ManagedBean
@RequestScoped
public class LoginController {

    @ManagedProperty(value = "#{centralUserUtility}")
    private CentralUserUtility centralUserUtility;

    public CentralUserUtility getCentralUserUtility() {
        return centralUserUtility;
    }

    public void setCentralUserUtility(CentralUserUtility centralUserUtility) {
        this.centralUserUtility = centralUserUtility;
    }

    /**
     * @return @throws IOException
     * @throws ServletException
     */
    public String doLogin() throws IOException, ServletException {
        ExternalContext context = FacesContext.getCurrentInstance()
                .getExternalContext();

        RequestDispatcher dispatcher
                = ((ServletRequest) context
                .getRequest())
                .getRequestDispatcher("/j_spring_security_check");

        dispatcher.forward((ServletRequest) context.getRequest(),
                (ServletResponse) context.getResponse());

        FacesContext.getCurrentInstance().responseComplete();

        // Faces are going to exit, sot it'sok to return null
        return null;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }

    public String back() {
        return "/index?faces-redirect=true";
    }

    public CentralUser getCurrentUser() {
        return centralUserUtility.getCurrentUser();
    }
}
