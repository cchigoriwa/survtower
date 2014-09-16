package com.survtower.client.member.controller;

import com.survtower.business.common.domain.Program;
import com.survtower.business.member.domain.MemberUser;
import com.survtower.business.member.domain.MemberUserRole;
import com.survtower.business.member.service.MemberUserService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Takunda Dhlakama
 */
@ManagedBean
@RequestScoped
public class MemberUserEditController {

    @ManagedProperty(value = "#{memberUserService}")
    private MemberUserService memberUserService;

    @ManagedProperty(value = "#{param.uuid}")
    private String uuid;

    private MemberUser memberUser;

    private List<String> roles = new ArrayList<>();
    private List<Program> programs = new ArrayList<>();
    
    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    public List<String> getMemberRoles() {
        List<String> list = new ArrayList<>();
        list.add(MemberUser.ROLE_COUNTRY_ADMINISTRATOR);
        list.add(MemberUser.ROLE_HEALTH_INFORMATION_OFFICER);
        return list;
    }

    public MemberUser getMemberUser() {
        return memberUser;
    }

    public String save() {
        memberUser.setPrograms(new HashSet<Program>(programs));
        Set<MemberUserRole> memberUserRoles = new HashSet<>();
        for (String role : getRoles()) {
            MemberUserRole memberUserRole = new MemberUserRole();
            memberUserRole.setMemberRole(role);
            memberUserRole.setDeactivated(Boolean.FALSE);
            memberUserRoles.add(memberUserRole);
        }
        memberUser.setMemberUserRoles(memberUserRoles);
        memberUserService.save(memberUser);
        return "memberUserList?faces-redirect=true&src=edit";
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

    @PostConstruct
    public void postConstruct() {
        if (uuid == null) {
            memberUser = new MemberUser();
            programs = new ArrayList<>();
            roles = new ArrayList<>();
        } else {
            memberUser = memberUserService.findByUuid(uuid);
            if (memberUser != null) {
                setPrograms(new ArrayList<Program>(memberUser.getPrograms()));
                setRoles(new ArrayList<String>(memberUser.getRoles()));
            }
            if (memberUser == null) {
                memberUser = new MemberUser();
                roles = new ArrayList<>();
                programs = new ArrayList<>();
            }
        }
    }

}
