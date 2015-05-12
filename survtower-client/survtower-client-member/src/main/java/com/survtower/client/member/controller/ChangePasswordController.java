package com.survtower.client.member.controller;

import com.survtower.business.common.service.impl.PasswordEncoderImpl;
import com.survtower.business.member.domain.MemberUser;
import com.survtower.business.member.service.MemberUserService;
import com.survtower.client.member.utility.MessageInfor;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Takunda Dhlakama
 */
@ManagedBean
@RequestScoped
public class ChangePasswordController {

    @ManagedProperty(value = "#{memberUserService}")
    private MemberUserService memberUserService;

    @ManagedProperty(value = "#{passwordEncoder}")
    private PasswordEncoderImpl passwordEncoder;

    private MemberUser memberUser;

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
        return null;
    }

    public MemberUserService getMemberUserService() {
        return memberUserService;
    }

    public void setMemberUserService(MemberUserService memberUserService) {
        this.memberUserService = memberUserService;
    }

    public PasswordEncoderImpl getPasswordEncoder() {
        return passwordEncoder;
    }

    public void setPasswordEncoder(PasswordEncoderImpl passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void postConstruct() {
        memberUser = memberUserService.getCurrentUser();
        memberUser.setPassword("");
        confirmPassword = "";
    }

}
