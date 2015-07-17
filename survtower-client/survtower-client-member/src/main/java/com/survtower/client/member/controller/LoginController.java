package com.survtower.client.member.controller;

import com.survtower.business.member.domain.MemberUser;
import com.survtower.business.member.service.MemberUserService;
import com.survtower.client.member.bean.MemberUserUtility;
import com.survtower.client.member.utility.WebUtilityImpl;
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

    @ManagedProperty(value = "#{memberUserService}")
    private MemberUserService memberUserService;
    
    @ManagedProperty(value = "#{memberUserUtility}")
    private MemberUserUtility memberUserUtility;

    public void setMemberUserService(MemberUserService memberUserService) {
        this.memberUserService = memberUserService;
    }

    public void setMemberUserUtility(MemberUserUtility memberUserUtility) {
        this.memberUserUtility = memberUserUtility;
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

    public MemberUser getCurrentUser() {
        return memberUserUtility.getCurrentUser();
    }
}
