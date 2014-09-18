package com.survtower.client.member.controller;

import com.survtower.business.common.domain.Member;
import com.survtower.business.common.service.MemberService;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Charles Chigoriwa
 */
@ManagedBean
@RequestScoped
public class MemberListController {
    
    @ManagedProperty(value = "#{memberService}")
    private MemberService memberService;
    
    public List<Member> getMembers(){
        return memberService.findAll();
    }

    public MemberService getMemberService() {
        return memberService;
    }
    
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }
    
}
