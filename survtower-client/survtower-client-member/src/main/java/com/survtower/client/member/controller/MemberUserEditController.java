package com.survtower.client.member.controller;

import com.survtower.business.common.EmailExistException;
import com.survtower.business.common.service.ProgramService;
import com.survtower.business.common.service.UserRoleService;
import com.survtower.business.member.domain.MemberUser;
import com.survtower.business.member.service.MemberUserService;
import com.survtower.business.member.service.RegionService;
import com.survtower.client.member.utility.MessageInfor;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Takunda Dhlakama
 * @author Charles Chigoriwa
 *
 */
@ManagedBean
@RequestScoped
public class MemberUserEditController {

    @ManagedProperty(value = "#{memberUserService}")
    private MemberUserService memberUserService;
    @ManagedProperty(value = "#{param.uuid}")
    private String uuid;
    private MemberUser memberUser;
    @ManagedProperty(value = "#{programService}")
    private ProgramService programService;
    @ManagedProperty(value = "#{regionService}")
    private RegionService regionService;
    @ManagedProperty(value = "#{userRoleService}")
    private UserRoleService userRoleService;

    public MemberUser getMemberUser() {
        return memberUser;
    }

    public String save() {
        try {

            if (memberUser.getUserRoles().isEmpty()) {
                MessageInfor.errorMessages("Select User Roles");
                return null;
            }

            memberUserService.save(memberUser);
            return "memberUserList?faces-redirect=true&src=edit";
        } catch (EmailExistException ex) {
            MessageInfor.errorMessages("Email is already registered with another user");
        }
        return null;
    }

    public MemberUserService getMemberUserService() {
        return memberUserService;
    }

    public void setMemberUserService(MemberUserService memberUserService) {
        this.memberUserService = memberUserService;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public ProgramService getProgramService() {
        return programService;
    }

    public void setProgramService(ProgramService programService) {
        this.programService = programService;
    }

    public RegionService getRegionService() {
        return regionService;
    }

    public void setRegionService(RegionService regionService) {
        this.regionService = regionService;
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
            memberUser = new MemberUser();
        } else {
            memberUser = memberUserService.findByUuid(uuid);
        }
    }

}
