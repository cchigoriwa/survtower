package com.survtower.client.member.controller;

import com.survtower.business.common.domain.Member;
import com.survtower.business.common.service.MemberService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Charles Chigoriwa
 * @author Takunda Dhlakama
 */
@ManagedBean
@RequestScoped
public class GeneralController {

    @ManagedProperty(value = "#{memberService}")
    private MemberService memberService;

    public Member getMember() {
        return memberService.getCurrentMember();
    }

    public MemberService getMemberService() {
        return memberService;
    }

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

}
