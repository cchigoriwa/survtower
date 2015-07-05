package com.survtower.client.member.controller;

import com.survtower.business.member.domain.MemberUser;
import com.survtower.business.member.domain.ResetPasswordRequest;
import com.survtower.business.member.service.MemberUserService;
import com.survtower.business.member.service.ResetPasswordRequestService;
import com.survtower.client.member.utility.MessageInfor;
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

    @ManagedProperty(value = "#{memberUserService}")
    private MemberUserService memberUserService;

    @ManagedProperty(value = "#{resetPasswordRequestService}")
    private ResetPasswordRequestService resetPasswordRequestService;

    @ManagedProperty(value = "#{passwordEncoder}")
    private PasswordEncoder passwordEncoder;

    private MemberUser memberUser;

    private ResetPasswordRequest resetPasswordRequest;

    @ManagedProperty(value = "#{param.reset}")
    private String reset;

    @ManagedProperty(value = "#{param.tok}")
    private String tok;

    public MemberUser getMemberUser() {
        return memberUser;
    }

    private String confirmPassword;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public ResetPasswordRequestService getResetPasswordRequestService() {
        return resetPasswordRequestService;
    }

    public void setResetPasswordRequestService(ResetPasswordRequestService resetPasswordRequestService) {
        this.resetPasswordRequestService = resetPasswordRequestService;
    }

    public ResetPasswordRequest getResetPasswordRequest() {
        return resetPasswordRequest;
    }

    public void setResetPasswordRequest(ResetPasswordRequest resetPasswordRequest) {
        this.resetPasswordRequest = resetPasswordRequest;
    }

    public String save() {

        if (StringUtils.isEmpty(memberUser.getPassword())) {
            MessageInfor.errorMessages("Enter password to Continue");
            return null;
        }

        if (!confirmPassword.equals(memberUser.getPassword())) {
            MessageInfor.errorMessages("Password do not match");
            return null;
        }
        memberUser.setPassword(passwordEncoder.encodePassword(memberUser.getPassword(), memberUser.getUuid()));
        memberUserService.save(memberUser);
        MessageInfor.inforMessages("Password Changed Successfully");
        return "resetPasswordResult?faces-redirect=true";
    }

    public MemberUserService getMemberUserService() {
        return memberUserService;
    }

    public void setMemberUserService(MemberUserService memberUserService) {
        this.memberUserService = memberUserService;
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
        memberUser = resetPasswordRequest.getMemberUser();
        memberUser.setPassword("");
        confirmPassword = "";
    }

}
