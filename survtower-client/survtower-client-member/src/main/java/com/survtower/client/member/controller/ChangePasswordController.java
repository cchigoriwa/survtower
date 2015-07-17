package com.survtower.client.member.controller;

import com.survtower.business.common.service.impl.PasswordEncoderImpl;
import com.survtower.business.member.domain.MemberUser;
import com.survtower.business.member.service.MemberUserService;
import com.survtower.client.member.bean.MemberUserUtility;
import com.survtower.client.member.utility.MessageInfor;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.encoding.PasswordEncoder;

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
    private PasswordEncoder passwordEncoder;
    
    @ManagedProperty(value = "#{memberUserUtility}")
    private MemberUserUtility memberUserUtility;

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
        memberUserService.updatePassword(memberUser.getPassword(), memberUser.getUsername());
        MessageInfor.inforMessages("Password Changed Successfully");
        return null;
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

    public void setMemberUserUtility(MemberUserUtility memberUserUtility) {
        this.memberUserUtility = memberUserUtility;
    }
    
    


    @PostConstruct
    public void postConstruct() {
        memberUser = memberUserUtility.getCurrentUser();
        memberUser.setPassword("");
        confirmPassword = "";
    }

}
