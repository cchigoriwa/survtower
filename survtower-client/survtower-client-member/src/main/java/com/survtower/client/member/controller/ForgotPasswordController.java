package com.survtower.client.member.controller;

import com.survtower.business.common.ForgotPassword;
import com.survtower.business.member.domain.MemberUser;
import com.survtower.business.member.service.MemberUserService;
import com.survtower.business.member.service.ResetPasswordRequestService;
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

    @ManagedProperty(value = "#{memberUserService}")
    private MemberUserService memberUserService;

    @ManagedProperty(value = "#{resetPasswordRequestService}")
    private ResetPasswordRequestService resetPasswordRequestService;

    private ForgotPassword forgotPassword;

    public MemberUserService getMemberUserService() {
        return memberUserService;
    }

    public void setMemberUserService(MemberUserService memberUserService) {
        this.memberUserService = memberUserService;
    }

    public ResetPasswordRequestService getResetPasswordRequestService() {
        return resetPasswordRequestService;
    }

    public void setResetPasswordRequestService(ResetPasswordRequestService resetPasswordRequestService) {
        this.resetPasswordRequestService = resetPasswordRequestService;
    }

    public ForgotPassword getForgotPassword() {
        return forgotPassword;
    }

    public void setForgotPassword(ForgotPassword forgotPassword) {
        this.forgotPassword = forgotPassword;
    }

    private boolean getMemberUser(ForgotPassword forgotPassword) {
        MemberUser subscriber = memberUserService.findByUserName(forgotPassword.getUsername());;
        if (subscriber == null) {
            return false;
        }
        return true;
    }

    public String submit() {
        if (getMemberUser(forgotPassword)) {
            resetPasswordRequestService.createNewPasswordRequest(forgotPassword.getEmail());
            return "login";
        } else {
            return "login";
        }

    }
    
    @PostConstruct
    public void postConstruct(){
        
        forgotPassword=forgotPassword==null?new ForgotPassword():forgotPassword;
    }

}
