package com.survtower.client.central.controller;

import com.survtower.business.central.domain.CentralUser;
import com.survtower.business.central.service.CentralUserService;
import com.survtower.business.central.service.ResetCentralUserPasswordRequestService;
import com.survtower.business.central.service.ResetPasswordProcess;
import com.survtower.business.common.ForgotPassword;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Daniel Nkhoma
 */
@ManagedBean
@RequestScoped
public class ForgotPasswordController {

    @ManagedProperty(value = "#{centralUserService}")
    private CentralUserService centralUserService;

    @ManagedProperty(value = "#{resetCentralUserPasswordRequestService}")
    private ResetCentralUserPasswordRequestService resetCentralUserPasswordRequestService;

    @ManagedProperty(value = "#{resetPasswordProcess}")
    private ResetPasswordProcess resetPasswordProcess;

    @ManagedProperty(value = "#{param.email}")
    private String email;

    private ForgotPassword forgotPassword;

    public CentralUserService getCentralUserService() {
        return centralUserService;
    }

    public void setCentralUserService(CentralUserService centralUserService) {
        this.centralUserService = centralUserService;
    }

    public ResetCentralUserPasswordRequestService getResetCentralUserPasswordRequestService() {
        return resetCentralUserPasswordRequestService;
    }

    public void setResetCentralUserPasswordRequestService(ResetCentralUserPasswordRequestService resetCentralUserPasswordRequestService) {
        this.resetCentralUserPasswordRequestService = resetCentralUserPasswordRequestService;
    }

    public ResetPasswordProcess getResetPasswordProcess() {
        return resetPasswordProcess;
    }

    public void setResetPasswordProcess(ResetPasswordProcess resetPasswordProcess) {
        this.resetPasswordProcess = resetPasswordProcess;
    }

    public ForgotPassword getForgotPassword() {
        return forgotPassword;
    }

    public void setForgotPassword(ForgotPassword forgotPassword) {
        this.forgotPassword = forgotPassword;
    }

    private boolean getCentralUser(ForgotPassword forgotPassword) {
        CentralUser centralUser = centralUserService.findByEmail(forgotPassword.getEmail());
        if (centralUser == null) {
            return false;
        }
        return true;
    }

    public String submit() {
        if (getCentralUser(forgotPassword)) {
            resetPasswordProcess.createNewCentralUserPasswordRequest(forgotPassword.getEmail());
            return "forgotPasswordEmailSentSuccessful";
        } else {
            return "forgotPasswordEmailSentFailure";
        }

    }

    @PostConstruct
    public void postConstruct() {
        forgotPassword = forgotPassword == null ? new ForgotPassword() : forgotPassword;
        forgotPassword.setEmail(email);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
