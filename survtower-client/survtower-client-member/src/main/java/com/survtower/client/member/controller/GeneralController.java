package com.survtower.client.member.controller;

import com.survtower.business.common.SurvtowerRuntimeException;
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
public class GeneralController {

    @ManagedProperty(value = "#{memberService}")
    private MemberService memberService;

    public Member getMember() {
        Member member = null;
        List<Member> list = memberService.findAll();
        if (list.size() > 1) {
            throw new SurvtowerRuntimeException("Members in a member state app should not exceed 1");
        } else if (list.size() == 1) {
            member=list.get(0);
        }

        return member;
    }

    public MemberService getMemberService() {
        return memberService;
    }

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

}
