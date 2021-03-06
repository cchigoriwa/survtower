package com.survtower.client.member.utility;

import com.survtower.business.common.domain.Program;
import com.survtower.business.member.domain.MemberUser;
import com.survtower.business.member.service.MemberUserService;
import com.survtower.client.member.bean.MemberUserUtility;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Takunda Dhlakama
 */
@ManagedBean
@RequestScoped
public class LoggedOnRoleListController {

    @ManagedProperty(value = "#{memberUserService}")
    private MemberUserService memberUserService;
    
    @ManagedProperty(value = "#{memberUserUtility}")
    private MemberUserUtility memberUserUtility;

    public void setMemberUserService(MemberUserService memberUserService) {
        this.memberUserService = memberUserService;
    }

    /*public List<String> getRoles() {
        return memberUserService.getCurrentUserRoles();
    }*/

    public void setMemberUserUtility(MemberUserUtility memberUserUtility) {
        this.memberUserUtility = memberUserUtility;
    }

    
}
