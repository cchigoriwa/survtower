package com.survtower.client.member.utility;

import com.survtower.business.common.domain.Program;
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
public class LoggedOnProgramListController {

    @ManagedProperty(value = "#{memberUserService}")
    private MemberUserService memberUserService;

    public void setMemberUserService(MemberUserService memberUserService) {
        this.memberUserService = memberUserService;
    }

    public List<Program> getPrograms() {
        return memberUserService.getCurrentUserPrograms();
    }

}
