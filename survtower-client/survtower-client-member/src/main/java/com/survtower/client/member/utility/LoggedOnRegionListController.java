package com.survtower.client.member.utility;

import com.survtower.business.member.domain.Region;
import com.survtower.business.member.service.MemberUserService;
import com.survtower.client.member.bean.MemberUserUtility;
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
public class LoggedOnRegionListController {

    @ManagedProperty(value = "#{memberUserService}")
    private MemberUserService memberUserService;
    
    @ManagedProperty(value = "#{memberUserUtility}")
    private MemberUserUtility memberUserUtility;

    public void setMemberUserService(MemberUserService memberUserService) {
        this.memberUserService = memberUserService;
    }

    public List<Region> getRegions() {
        return memberUserUtility.getCurrentUser().getRegions();
    }

    public void setMemberUserUtility(MemberUserUtility memberUserUtility) {
        this.memberUserUtility = memberUserUtility;
    }
    
    

}
