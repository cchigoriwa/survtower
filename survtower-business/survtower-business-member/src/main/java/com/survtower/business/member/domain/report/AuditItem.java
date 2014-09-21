package com.survtower.business.member.domain.report;

import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.member.domain.SurveillanceAudit;
import java.io.Serializable;

/**
 *
 * @author Takunda Dhlakama
 */
public class AuditItem implements Serializable {

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
