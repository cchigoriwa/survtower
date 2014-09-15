package com.survtower.client.member.controller;

import com.survtower.business.member.domain.MemberUser;
import com.survtower.business.member.service.MemberUserService;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.common.util.impl.Log;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.WebAttributes;

/**
 *
 * @author Takunda Dhlakama
 */
@ManagedBean
@RequestScoped
public class LoginController implements PhaseListener {

    @ManagedProperty(value = "#{memberUserService}")
    private MemberUserService memberUserService;

    private MemberUser memberUser;

    public MemberUser getMemberUser() {
        return memberUser;
    }

    public void setMemberUser(MemberUser memberUser) {
        this.memberUser = memberUser;
    }

    public MemberUserService getMemberUserService() {
        return memberUserService;
    }

    public void setMemberUserService(MemberUserService memberUserService) {
        this.memberUserService = memberUserService;
    }

    public String dologin() {
        return "index";
    }

    /**
     *
     * Redirects the login request directly to spring security check. Leave this
     * method as it is to properly support spring security.
     *
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String doLogin() throws ServletException, IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest())
                .getRequestDispatcher("/j_spring_security_check");

        dispatcher.forward((ServletRequest) context.getRequest(),
                (ServletResponse) context.getResponse());

        FacesContext.getCurrentInstance().responseComplete();

        return null;
    }

    @Override
    public void afterPhase(PhaseEvent event) {
    }

    /* (non-Javadoc)
     * @see javax.faces.event.PhaseListener#beforePhase(javax.faces.event.PhaseEvent)
     *
     * Do something before rendering phase.
     */
    @Override
    public void beforePhase(PhaseEvent event) {
        Exception e = (Exception) FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap().get(WebAttributes.AUTHENTICATION_EXCEPTION);

        if (e instanceof BadCredentialsException) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(
                    WebAttributes.AUTHENTICATION_EXCEPTION, null);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Username or password not valid.", "Username or password not valid"));
        }
    }

    /* (non-Javadoc)
     * @see javax.faces.event.PhaseListener#getPhaseId()
     *
     * In which phase you want to interfere?
     */
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }

}
