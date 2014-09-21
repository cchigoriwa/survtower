package com.survtower.business.central.domain.report;

import com.survtower.business.common.domain.Member;
import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.domain.Surveillance;
import java.io.Serializable;

/**
 *
 * @author Charles Chigoriwa
 */
public class CentralAuditItem implements Serializable{ 
    
    private Member member;
    private Program program;
    private Period period;
    private Surveillance surveillance;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
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

    public Surveillance getSurveillance() {
        return surveillance;
    }

    public void setSurveillance(Surveillance surveillance) {
        this.surveillance = surveillance;
    }
    
    public boolean isSubmitted(){
        return surveillance!=null;
    }
    
}
