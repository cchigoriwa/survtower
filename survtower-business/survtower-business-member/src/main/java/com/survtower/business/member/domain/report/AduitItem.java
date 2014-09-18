/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survtower.business.member.domain.report;

import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.member.domain.SurveillanceAudit;
import java.io.Serializable;

/**
 *
 * @author tdhlakama
 */
public class AduitItem implements Serializable {

    private Program program;
    private Period period;
    private SurveillanceAudit surveillanceAudit;

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

    public SurveillanceAudit getSurveillanceAudit() {
        return surveillanceAudit;
    }

    public void setSurveillanceAudit(SurveillanceAudit surveillanceAudit) {
        this.surveillanceAudit = surveillanceAudit;
    }
    
    

    public Boolean getDataEntryDone() {
        if (surveillanceAudit != null) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}
