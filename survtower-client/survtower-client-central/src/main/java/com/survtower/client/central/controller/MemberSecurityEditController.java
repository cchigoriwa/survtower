package com.survtower.client.central.controller;

import com.survtower.business.central.domain.MemberSecurity;
import com.survtower.business.central.domain.MemberUserRole;
import com.survtower.business.central.service.MemberSecurityService;
import com.survtower.business.common.domain.Member;
import com.survtower.business.common.service.MemberService;
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
 * @author Charles Chigoriwa
 */
@ManagedBean
@RequestScoped
public class MemberSecurityEditController {

    @ManagedProperty(value = "#{memberService}")
    private MemberService memberService;

    @ManagedProperty(value = "#{param.memberUuid}")
    private String memberUuid;

    @ManagedProperty(value = "#{memberSecurityService}")
    private MemberSecurityService memberSecurityService;

    private MemberSecurity memberSecurity;

    public MemberService getMemberService() {
        return memberService;
    }

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    public String getMemberUuid() {
        return memberUuid;
    }

    public void setMemberUuid(String memberUuid) {
        this.memberUuid = memberUuid;
    }

    public MemberSecurityService getMemberSecurityService() {
        return memberSecurityService;
    }

    public void setMemberSecurityService(MemberSecurityService memberSecurityService) {
        this.memberSecurityService = memberSecurityService;
    }

    public MemberSecurity getMemberSecurity() {
        return memberSecurity;
    }

    public void setMemberSecurity(MemberSecurity memberSecurity) {
        this.memberSecurity = memberSecurity;
    }

    private List<String> roles = new ArrayList<String>();

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getMemberRoles() {
        List<String> list = new ArrayList<String>();
        list.add(MemberSecurity.ROLE_GLOBAL_ADMINISTRATOR);
        list.add(MemberSecurity.ROLE_SADC_DATA_MANAGER);
        return list;
    }

    public String save() {
        Set<MemberUserRole> memberUserRoles = new HashSet<MemberUserRole>();
        for (String role : getRoles()) {
            MemberUserRole memberUserRole = new MemberUserRole();
            memberUserRole.setMemberRole(role);
            memberUserRole.setDeactivated(Boolean.FALSE);
            memberUserRoles.add(memberUserRole);
        }
        memberSecurity.setMemberUserRoles(memberUserRoles);
        memberSecurityService.save(memberSecurity);
        return "memberView?faces-redirect=true&amp;uuid=" + memberSecurity.getMember().getUuid();
    }

    @PostConstruct
    public void postConstruct() {
        Member member = memberService.findByUuid(memberUuid);
        memberSecurity = memberSecurityService.findByMember(member);
        roles = new ArrayList<String>();
        if (memberSecurity != null) {
            setRoles(new ArrayList<String>(memberSecurity.getRoles()));
        }
        if (memberSecurity == null) {
            memberSecurity = new MemberSecurity();
            memberSecurity.setMember(member);
            roles = new ArrayList<String>();
        }
    }

}
