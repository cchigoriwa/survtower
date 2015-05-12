package com.survtower.client.central.controller;

import com.survtower.business.central.domain.CentralUser;
import com.survtower.business.central.domain.CentralUserRole;
import com.survtower.business.central.service.CentralUserService;
import com.survtower.business.common.service.impl.PasswordEncoderImpl;
import com.survtower.client.central.utility.MessageInfor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class CentralUserEditController {

    @ManagedProperty(value = "#{centralUserService}")
    private CentralUserService centralUserService;

    @ManagedProperty(value = "#{param.uuid}")
    private String uuid;

    @ManagedProperty(value = "#{passwordEncoder}")
    private PasswordEncoderImpl passwordEncoder;

    private CentralUser centralUser;

    private List<String> roles = new ArrayList<>();

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getMemberRoles() {
        List<String> list = new ArrayList<>();
        list.add(CentralUser.ROLE_GLOBAL_ADMINISTRATOR);
        list.add(CentralUser.ROLE_SADC_DATA_MANAGER);
        return list;
    }

    public CentralUser getCentralUser() {
        return centralUser;
    }

    public String save() {
        if (StringUtils.isEmpty(centralUser.getPassword())) {
            MessageInfor.errorMessages("Enter Password to Continue");
            return null;
        }
        Set<CentralUserRole> centralUserRoles = new HashSet<>();
        for (String role : getRoles()) {
            CentralUserRole centralUserRole = new CentralUserRole();
            centralUserRole.setMemberRole(role);
            centralUserRole.setDeactivated(Boolean.TRUE);
            centralUserRoles.add(centralUserRole);
        }
        centralUser.setPassword(passwordEncoder.encodePassword(centralUser.getPassword(), centralUser.getUuid()));
        centralUser.setCentralUserRoles(centralUserRoles);
        centralUserService.save(centralUser);
        return "centralUserList?faces-redirect=true&src=edit";
    }

    public PasswordEncoderImpl getPasswordEncoder() {
        return passwordEncoder;
    }

    public void setPasswordEncoder(PasswordEncoderImpl passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
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

    @PostConstruct
    public void postConstruct() {
        if (uuid == null) {
            centralUser = new CentralUser();
            roles = new ArrayList<>();
        } else {
            centralUser = centralUserService.findByUuid(uuid);
            if (centralUser != null) {
                setRoles(new ArrayList<>(centralUser.getRoles()));
            }
            if (centralUser == null) {
                centralUser = new CentralUser();
                roles = new ArrayList<>();
            }
        }
    }

}
