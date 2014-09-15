package com.survtower.client.member.controller;

import com.survtower.business.member.domain.MemberUser;
import com.survtower.business.member.service.MemberUserService;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Takunda Dhlakama
 */
@ManagedBean
@RequestScoped
public class MemberUserListController {
    
    @ManagedProperty(value = "#{memberUserService}")
    private MemberUserService memberUserService;
    
    public List<MemberUser> getMemberUsers(){
        return memberUserService.findAll();
    }

    public MemberUserService getMemberUserService() {
        return memberUserService;
    }
    
    public void setMemberUserService(MemberUserService memberUserService) {
        this.memberUserService = memberUserService;
    }
    
}
