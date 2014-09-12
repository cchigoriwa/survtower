package com.survtower.client.member.controller.entry;

import com.survtower.business.common.SurvtowerRuntimeException;
import com.survtower.business.common.domain.Indicator;
import com.survtower.business.common.domain.Member;
import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.domain.Surveillance;
import com.survtower.business.common.domain.SurveillanceData;
import com.survtower.business.common.service.IndicatorService;
import com.survtower.business.common.service.MemberService;
import com.survtower.business.common.service.PeriodService;
import com.survtower.business.common.service.ProgramService;
import com.survtower.business.common.service.SurveillanceService;
import com.survtower.client.member.utility.MessageInfor;
import static com.survtower.client.member.utility.MessageInfor.errorMessages;
import static com.survtower.client.member.utility.MessageInfor.inforMessages;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Takund Dhlakama
 */
@ManagedBean
@ViewScoped
public class DataEntryController extends MessageInfor implements Serializable {

    public DataEntryController() {
    }

    @ManagedProperty(value = "#{programService}")
    ProgramService programService;

    @ManagedProperty(value = "#{periodService}")
    PeriodService periodService;

    @ManagedProperty(value = "#{surveillanceService}")
    SurveillanceService surveillanceService;

    @ManagedProperty(value = "#{memberService}")
    private MemberService memberService;

    @ManagedProperty(value = "#{indicatorService}")
    private IndicatorService indicatorService;

    private Boolean submitted = Boolean.FALSE;

    //@ManagedProperty(value = "#{param.programId}")
    private String programId;

    //@ManagedProperty(value = "#{param.periodId}")
    private String periodId;

    public Boolean getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Boolean submitted) {
        this.submitted = submitted;
    }

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getPeriodId() {
        return periodId;
    }

    public void setPeriodId(String periodId) {
        this.periodId = periodId;
    }

    private Period period;
    private Program program;
    private Surveillance surveillance;

    public ProgramService getProgramService() {
        return programService;
    }

    public void setProgramService(ProgramService programService) {
        this.programService = programService;
    }

    public PeriodService getPeriodService() {
        return periodService;
    }

    public void setPeriodService(PeriodService periodService) {
        this.periodService = periodService;
    }

    public SurveillanceService getSurveillanceService() {
        return surveillanceService;
    }

    public void setSurveillanceService(SurveillanceService surveillanceService) {
        this.surveillanceService = surveillanceService;
    }

    public MemberService getMemberService() {
        return memberService;
    }

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    public IndicatorService getIndicatorService() {
        return indicatorService;
    }

    public void setIndicatorService(IndicatorService indicatorService) {
        this.indicatorService = indicatorService;
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

    public String submitSurviellanceForm() {
        try {
            getSurveillance().getSurveillanceDataSet().clear();
            getSurveillance().getSurveillanceDataSet().addAll(getSurveillanceDataList());
            submitted = Boolean.TRUE;
            getSurveillanceDataList().clear();
            getSurveillanceDataList().addAll(getSurveillance().getSurveillanceDataSet());
        } catch (Exception ex) {
            submitted = Boolean.FALSE;
            errorMessages("Surveillance Data Not Processed Succefully");
        }
        return null;
    }

    public String saveSurviellanceForm() {
        try {
            getSurveillance().getSurveillanceDataSet().clear();
            getSurveillance().getSurveillanceDataSet().addAll(getSurveillanceDataList());
            for (SurveillanceData data : getSurveillance().getSurveillanceDataSet()) {
                if (!data.getValid()) {
                    errorMessages("Data Incomplete");
                    submitted = Boolean.TRUE;
                    return null;
                }
            }
            submitted = Boolean.TRUE;
            getSurveillanceDataList().clear();
            getSurveillanceDataList().addAll(getSurveillance().getSurveillanceDataSet());
            surveillanceService.save(surveillance);
            inforMessages("Surviellance Data Saved Successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
            submitted = Boolean.FALSE;
            errorMessages("Surveillance Data Not Processed Succefully");
        }
        return null;
    }

    public String editSurviellanceForm() {
        submitted = Boolean.FALSE;
        return null;
    }

    public String reset() {
        surveillance = null;
        program = null;
        period = null;
        getSurveillanceDataList().clear();
        return null;
    }
    private List<SurveillanceData> surveillanceDataList = new ArrayList<SurveillanceData>();

    public List<SurveillanceData> getSurveillanceDataList() {
        return surveillanceDataList;
    }

    public void setSurveillanceDataList(List<SurveillanceData> surveillanceDataList) {
        this.surveillanceDataList = surveillanceDataList;
    }

    @PostConstruct
    public void loadData() {
        programId = FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("programId");
        periodId = FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("periodId");
        program = programService.findByUuid(programId);
        period = periodService.findByUuid(periodId);
        surveillance = surveillanceService.get(program, period, memberService.getCurrentMember());

        if (getSurveillance() != null) {
            //if surveillance data has been created load file
            inforMessages("Indicators for this period have been created Already");
            getSurveillanceDataList().clear();
            getSurveillanceDataList().addAll(getSurveillance().getSurveillanceDataSet());

        } else {
            //create new surveillance 
            setSurveillance(new Surveillance());
            getSurveillance().setPeriod(getPeriod());
            getSurveillance().setProgram(getProgram());
            getSurveillance().setCreateDate(new Date());
            for (Indicator indicator : indicatorService.findIndicatorsInProgram(getProgram())) {
                SurveillanceData data = new SurveillanceData();
                data.setCreateDate(new Date());
                data.setIndicator(indicator);
                data.setSurveillance(getSurveillance());
                getSurveillance().getSurveillanceDataSet().add(data);
                getSurveillanceDataList().clear();
                getSurveillanceDataList().addAll(getSurveillance().getSurveillanceDataSet());
            }
        }
    }
}
