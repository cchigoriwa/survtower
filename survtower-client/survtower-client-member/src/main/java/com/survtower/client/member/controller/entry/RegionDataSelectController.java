package com.survtower.client.member.controller.entry;

import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.service.PeriodService;
import com.survtower.business.member.domain.Region;
import com.survtower.client.member.utility.MessageInfor;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Takunda Dhlakama
 * @author Daniel Nkhoma
 */
@ManagedBean
@RequestScoped
public class RegionDataSelectController extends MessageInfor implements Serializable {

    @ManagedProperty(value = "#{periodService}")
    private PeriodService periodService;
    private Period period;
    private Program program;
    private Region region;

    public RegionDataSelectController() {
    }

    public void setPeriodService(PeriodService periodService) {
        this.periodService = periodService;
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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String dataEntrySelection() {
        return "region_data_entry_edit?faces-redirect=true&programId=" + program.getUuid() + "&periodId=" + period.getUuid() + "&regionId=" + region.getUuid();
    }
}
