package com.survtower.client.central.controller;

import com.survtower.business.central.domain.CentralUser;
import com.survtower.business.central.service.CentralUserService;
import com.survtower.business.common.EmailExistException;
import com.survtower.business.common.service.UserRoleService;
import com.survtower.client.central.utility.MessageInfor;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Takunda Dhlakama
 * @author Daniel Nkhoma
 */
@ManagedBean
@RequestScoped
public class CentralUserEditController {

    @ManagedProperty(value = "#{centralUserService}")
    private CentralUserService centralUserService;

    @ManagedProperty(value = "#{param.uuid}")
    private String uuid;

    @ManagedProperty(value = "#{userRoleService}")
    private UserRoleService userRoleService;

    private CentralUser centralUser;

    public CentralUser getCentralUser() {
        return centralUser;
    }

    public String save() {
        try {
            if (centralUser.getUserRoles().isEmpty()) {
                MessageInfor.errorMessages("Select User Roles");
                return null;
            }
            centralUserService.save(centralUser);
            return "centralUserList?faces-redirect=true&src=edit";

        } catch (EmailExistException ex) {
            MessageInfor.errorMessages("Email is already registered with another user");
        }
        return null;
    }

    public CentralUserService getCentralUserService() {
        return centralUserService;
    }

    public void setCentralUserService(CentralUserService centralUserService) {
        this.centralUserService = centralUserService;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public UserRoleService getUserRoleService() {
        return userRoleService;
    }

    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PostConstruct
    public void postConstruct() {
        if (uuid == null) {
            centralUser = new CentralUser();
        } else {
            centralUser = centralUserService.findByUuid(uuid);
        }
    }

}
