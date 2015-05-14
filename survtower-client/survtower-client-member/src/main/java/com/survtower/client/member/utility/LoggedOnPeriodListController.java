package com.survtower.client.member.utility;

import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.service.PeriodService;
import com.survtower.business.member.service.MemberUserService;
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

    @ManagedProperty(value = "#{memberUserService}")
    private MemberUserService memberUserService;

    @ManagedProperty(value = "#{periodService}")
    private PeriodService periodService;

    public void setMemberUserService(MemberUserService memberUserService) {
        this.memberUserService = memberUserService;
    }

    public void setPeriodService(PeriodService periodService) {
        this.periodService = periodService;
    }

    public List<Period> getPeriods() {
        List<Period> list = new ArrayList<>();
        for (Program program : memberUserService.getCurrentUserPrograms()) {
            list.addAll(periodService.fetchAll(program));
        }
        return list;
    }
}
