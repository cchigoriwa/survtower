package com.survtower.client.member.controller;

import com.survtower.business.common.domain.Program;
import com.survtower.business.member.domain.MemberRole;
import com.survtower.business.member.domain.MemberUser;
import com.survtower.business.member.service.MemberUserService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    private List<MemberRole> roles = new ArrayList<>();
    private List<Program> programs = new ArrayList<>();

    public List<MemberRole> getRoles() {
        return roles;
    }

    public void setRoles(List<MemberRole> roles) {
        this.roles = roles;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    public MemberRole[] getMemberRoles() {
        return MemberRole.values();
    }

    public MemberUser getMemberUser() {
        return memberUser;
    }

    public String save() {
        memberUser.setPrograms(new HashSet<Program>(programs));
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
            }
            if (memberUser == null) {
                memberUser = new MemberUser();
                roles = new ArrayList<>();
                programs = new ArrayList<>();
            }
        }
    }

}
