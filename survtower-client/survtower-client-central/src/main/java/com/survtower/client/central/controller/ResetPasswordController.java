package com.survtower.client.central.controller;

import com.survtower.business.central.domain.CentralUser;
import com.survtower.business.central.domain.ResetCentralUserPasswordRequest;
import com.survtower.business.central.service.CentralUserService;
import com.survtower.business.central.service.ResetCentralUserPasswordRequestService;
import com.survtower.client.central.utility.MessageInfor;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.encoding.PasswordEncoder;

/**
 *
 * @author Daniel Nkhoma
 */
@ManagedBean
@RequestScoped
public class ResetPasswordController {

    @ManagedProperty(value = "#{centralUserService}")
    private CentralUserService centralUserService;

    @ManagedProperty(value = "#{resetPasswordRequestService}")
    private ResetCentralUserPasswordRequestService resetPasswordRequestService;

    @ManagedProperty(value = "#{passwordEncoder}")
    private PasswordEncoder passwordEncoder;

    private CentralUser centralUser;

    private ResetCentralUserPasswordRequest resetPasswordRequest;

    @ManagedProperty(value = "#{param.reset}")
    private String reset;

    @ManagedProperty(value = "#{param.tok}")
    private String tok;

    public CentralUser getCentralUser() {
        return centralUser;
    }

    private String confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public ResetCentralUserPasswordRequestService getResetPasswordRequestService() {
        return resetPasswordRequestService;
    }

    public void setResetPasswordRequestService(ResetCentralUserPasswordRequestService resetPasswordRequestService) {
        this.resetPasswordRequestService = resetPasswordRequestService;
    }

    public ResetCentralUserPasswordRequest getResetPasswordRequest() {
        return resetPasswordRequest;
    }

    public void setResetPasswordRequest(ResetCentralUserPasswordRequest resetPasswordRequest) {
        this.resetPasswordRequest = resetPasswordRequest;
    }

    public String save() {

        if (StringUtils.isEmpty(centralUser.getPassword())) {
            MessageInfor.errorMessages("Enter password to Continue");
            return null;
        }

        if (!confirmPassword.equals(centralUser.getPassword())) {
            MessageInfor.errorMessages("Password do not match");
            return null;
        }
        centralUserService.updatePassword(centralUser.getPassword(), centralUser.getUsername());
        MessageInfor.inforMessages("Password Changed Successfully");
        return "resetPasswordResult?faces-redirect=true";
    }

    public CentralUserService getCentralUserService() {
        return centralUserService;
    }

    public void setCentralUserService(CentralUserService centralUserService) {
        this.centralUserService = centralUserService;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String getReset() {
        return reset;
    }

    public void setReset(String reset) {
        this.reset = reset;
    }

    public String getTok() {
        return tok;
    }

    public void setTok(String tok) {
        this.tok = tok;
    }


    @PostConstruct
    public void postConstruct() {
        resetPasswordRequest = resetPasswordRequestService.findByFirstTag(reset);
        centralUser = resetPasswordRequest.getCentralUser();
        centralUser.setPassword("");
        confirmPassword = "";
    }

}
