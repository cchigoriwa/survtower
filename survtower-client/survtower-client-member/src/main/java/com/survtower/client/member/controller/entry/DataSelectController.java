package com.survtower.client.member.controller.entry;

import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.domain.Surveillance;
import com.survtower.business.common.service.MemberService;
import com.survtower.business.common.service.PeriodService;
import com.survtower.business.common.service.ProgramService;
import com.survtower.business.common.service.SurveillanceService;
import com.survtower.business.member.domain.SurveillanceAudit;
import com.survtower.business.member.service.SurveillanceAuditService;
import com.survtower.client.member.utility.MessageInfor;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Takund Dhlakama
 */
@ManagedBean
@RequestScoped
public class DataSelectController extends MessageInfor implements Serializable {

    public DataSelectController() {
    }

    @ManagedProperty(value = "#{programService}")
    ProgramService programService;

    @ManagedProperty(value = "#{periodService}")
    PeriodService periodService;

    @ManagedProperty(value = "#{surveillanceService}")
    SurveillanceService surveillanceService;

    @ManagedProperty(value = "#{surveillanceAuditService}")
    private SurveillanceAuditService surveillanceAuditService;

    @ManagedProperty(value = "#{memberService}")
    private MemberService memberService;

    private Period period;
    private Program program;

    public void setProgramService(ProgramService programService) {
        this.programService = programService;
    }

    public void setPeriodService(PeriodService periodService) {
        this.periodService = periodService;
    }

    public void setSurveillanceService(SurveillanceService surveillanceService) {
        this.surveillanceService = surveillanceService;
    }

    public void setSurveillanceAuditService(SurveillanceAuditService surveillanceAuditService) {
        this.surveillanceAuditService = surveillanceAuditService;
    }

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    public List<Period> getActivePeriods() {
        return periodService.fetchActive();
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public String dataValidationSelection() {
        Surveillance surveillance = surveillanceService.get(program, period, memberService.getCurrentMember());
        if (surveillance != null) {
            return "data_validation?faces-redirect=true&surveillanceId=" + surveillance.getUuid();
        } else {
            errorMessages("Surveillance Not Found");
            return null;
        }
    }

}
