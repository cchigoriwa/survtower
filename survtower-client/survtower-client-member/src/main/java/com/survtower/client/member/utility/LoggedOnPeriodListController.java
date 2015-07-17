package com.survtower.client.member.utility;

import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.service.PeriodService;
import com.survtower.business.member.service.MemberUserService;
import com.survtower.client.member.bean.MemberUserUtility;
import java.util.ArrayList;
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
public class LoggedOnPeriodListController {

    @ManagedProperty(value = "#{periodService}")
    private PeriodService periodService;
    
    @ManagedProperty(value = "#{memberUserUtility}")
    private MemberUserUtility memberUserUtility;

    public void setPeriodService(PeriodService periodService) {
        this.periodService = periodService;
    }

    public void setMemberUserUtility(MemberUserUtility memberUserUtility) {
        this.memberUserUtility = memberUserUtility;
    }
    
    

    public List<Period> getPeriods() {
        List<Period> list = new ArrayList<>();
        for (Program program : memberUserUtility.getCurrentUser().getPrograms()) {
            list.addAll(periodService.fetchAll(program));
        }
        return list;
    }
}
