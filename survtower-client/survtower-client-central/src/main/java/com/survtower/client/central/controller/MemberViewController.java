package com.survtower.client.central.controller;

import com.survtower.business.central.domain.MemberSecurity;
import com.survtower.business.central.service.MemberSecurityService;
import com.survtower.business.common.domain.Member;
import com.survtower.business.common.service.MemberService;
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
public class MemberViewController {
    
    @ManagedProperty(value = "#{param.uuid}")
    private String uuid;
    
    @ManagedProperty(value = "#{memberService}")
    private MemberService memberService;
    
    @ManagedProperty(value = "#{memberSecurityService}")
    private MemberSecurityService memberSecurityService;
    
    private Member member;
    private MemberSecurity memberSecurity;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public MemberSecurity getMemberSecurity() {
        return memberSecurity;
    }

    public void setMemberSecurity(MemberSecurity memberSecurity) {
        this.memberSecurity = memberSecurity;
    }
    
    

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public MemberService getMemberService() {
        return memberService;
    }

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    public MemberSecurityService getMemberSecurityService() {
        return memberSecurityService;
    }

    public void setMemberSecurityService(MemberSecurityService memberSecurityService) {
        this.memberSecurityService = memberSecurityService;
    }
    
    
    
    @PostConstruct
    public void postConstruct(){
        member=this.memberService.findByUuid(uuid);
        memberSecurity=this.memberSecurityService.findByMember(member);
    }
}
