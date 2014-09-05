package com.survtower.client.central.controller;

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
public class MemberEditController {

    @ManagedProperty(value = "#{memberService}")
    private MemberService memberService;

    @ManagedProperty(value = "#{param.uuid}")
    private String uuid;

    private Member member;

    public Member getMember() {
        return member;
    }

    public String save() {
        memberService.save(member);
        return "memberList?faces-redirect=true&src=edit";
    }

    public MemberService getMemberService() {
        return memberService;
    }

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    
    
    
    @PostConstruct
    public void postConstruct(){
        if(uuid==null){
            member=new Member();
        }else{
            member=memberService.findByUuid(uuid);
            if(member==null){
                member=new Member();
            }
        }
    }

}
