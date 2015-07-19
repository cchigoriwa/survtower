package com.survtower.client.central.controller;

import com.survtower.business.central.domain.CentralUser;
import com.survtower.business.central.service.CentralUserService;
import com.survtower.client.central.bean.CentralUserUtility;
import com.survtower.client.central.utility.MessageInfor;
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

    @ManagedProperty(value = "#{centralUserService}")
    private CentralUserService centralUserService;

    @ManagedProperty(value = "#{passwordEncoder}")
    private PasswordEncoder passwordEncoder;

    @ManagedProperty(value = "#{centralUserUtility}")
    private CentralUserUtility centralUserUtility;

    private CentralUser centralUser;

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
        return null;
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

    public CentralUserUtility getCentralUserUtility() {
        return centralUserUtility;
    }

    public void setCentralUserUtility(CentralUserUtility centralUserUtility) {
        this.centralUserUtility = centralUserUtility;
    }

    @PostConstruct
    public void postConstruct() {
        centralUser = centralUserUtility.getCurrentUser();
        centralUser.setPassword("");
        confirmPassword = "";
    }

}
