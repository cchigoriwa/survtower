package com.survtower.client.member.controller.entry;

import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.domain.Surveillance;
import com.survtower.business.common.domain.SurveillanceData;
import com.survtower.business.common.service.IndicatorService;
import com.survtower.business.common.service.MemberService;
import com.survtower.business.common.service.PeriodService;
import com.survtower.business.common.service.ProgramService;
import com.survtower.business.common.service.SurveillanceService;
import com.survtower.business.member.domain.MemberUser;
import com.survtower.business.member.domain.SurveillanceAudit;
import com.survtower.business.member.service.MemberUserService;
import com.survtower.business.member.service.RegionSurveillanceDataService;
import com.survtower.business.member.service.SurveillanceAuditService;
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
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Takund Dhlakama
 */
@ManagedBean
@ViewScoped
public class DataEntryController extends MessageInfor implements Serializable {

    private Boolean submitted = Boolean.FALSE;
    private Period period;
    private Program program;
    private Surveillance surveillance;
    private SurveillanceAudit surveillanceAudit;

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

    @ManagedProperty(value = "#{surveillanceAuditService}")
    private SurveillanceAuditService surveillanceAuditService;

    @ManagedProperty(value = "#{memberUserService}")
    private MemberUserService memberUserService;

    @ManagedProperty(value = "#{regionSurveillanceDataService}")
    RegionSurveillanceDataService regionSurveillanceDataService;

    public RegionSurveillanceDataService getRegionSurveillanceDataService() {
        return regionSurveillanceDataService;
    }

    public void setRegionSurveillanceDataService(RegionSurveillanceDataService regionSurveillanceDataService) {
        this.regionSurveillanceDataService = regionSurveillanceDataService;
    }

    public void setMemberUserService(MemberUserService memberUserService) {
        this.memberUserService = memberUserService;
    }

    public SurveillanceAuditService getSurveillanceAuditService() {
        return surveillanceAuditService;
    }

    public void setSurveillanceAuditService(SurveillanceAuditService surveillanceAuditService) {
        this.surveillanceAuditService = surveillanceAuditService;
    }

    public Boolean getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Boolean submitted) {
        this.submitted = submitted;
    }

    public SurveillanceAudit getSurveillanceAudit() {
        return surveillanceAudit;
    }

    public void setSurveillanceAudit(SurveillanceAudit surveillanceAudit) {
        this.surveillanceAudit = surveillanceAudit;
    }

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
            for (SurveillanceData data : getSurveillance().getSurveillanceDataSet()) {
                if (!data.getValid()) {
                    errorMessages("Data Incomplete");
                    submitted = Boolean.FALSE;
                    return null;
                }
            }
            submitted = Boolean.TRUE;
        } catch (Exception ex) {
            submitted = Boolean.FALSE;
            errorMessages("Surveillance Data Not Processed Succefully");
        }
        return null;
    }

    public String saveSurviellanceForm() {
        if (getCurrentUser() == null) {
            errorMessages("User needs to login to continue");
            return null;
        }
        try {
            for (SurveillanceData data : getSurveillance().getSurveillanceDataSet()) {
                if (!data.getValid()) {
                    errorMessages("Data Incomplete");
                    submitted = Boolean.FALSE;
                    return null;
                }
            }

            for (SurveillanceData data : getSurveillance().getSurveillanceDataSet()) {
                data.setNumeratorValue(regionSurveillanceDataService.getNumeratorCalculatedValue(data));
                data.setDenominatorValue(regionSurveillanceDataService.getDenominatedCalculatedValue(data));
            }

            if (getSurveillanceAudit().getSubmissionDone()) {//Check for Final Submission
                errorMessages("Data Upload Has Already bee Approved,Changes Permitted");
                return null;
            }

            surveillanceService.save(surveillance);
            if (getSurveillanceAudit().getId() == null) {
                getSurveillanceAudit().setPeriod(period);
                getSurveillanceAudit().setProgram(program);
                getSurveillanceAudit().setUploadedBy(getCurrentUser());
                getSurveillanceAudit().setUploadedOn(new Date());
            } else {
                getSurveillanceAudit().setUploadedOn(new Date());
            }
            if (getSurveillanceAudit().getUploadedBy() == null) {
                errorMessages("Audit Trail - Not Working");
                return null;
            }
            surveillanceAuditService.save(surveillanceAudit);
            inforMessages("Surviellance Data Saved Successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
            errorMessages("Surveillance Data Not Processed Succefully");
        }
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
        String programId = FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("programId");
        String periodId = FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("periodId");
        program = programService.findByUuid(programId);
        period = periodService.findByUuid(periodId);

        surveillance = surveillanceService.get(program, period, memberService.getCurrentMember());

        if (surveillance == null) {
            surveillance = surveillanceService.createSurveillanceData(program, period, memberService.getCurrentMember());
        } else {
            for (SurveillanceData data : surveillance.getSurveillanceDataSet()) {
                data.setNumeratorValue(regionSurveillanceDataService.getNumeratorCalculatedValue(data));
                data.setDenominatorValue(regionSurveillanceDataService.getDenominatedCalculatedValue(data));
            }
        }

        getSurveillanceDataList().clear();
        getSurveillanceDataList().addAll(surveillance.getSurveillanceDataSet());
        surveillanceAudit = surveillanceAuditService.get(program, period);

        if (surveillanceAudit == null) {
            setSurveillanceAudit(new SurveillanceAudit());
        } 
    }

    public MemberUser getCurrentUser() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext externalContext = fc.getExternalContext();
        if (externalContext.getUserPrincipal() == null) {
            return null;
        } else {
            String user = externalContext.getUserPrincipal().getName();
            MemberUser memberUser = memberUserService.findByUserName(user);
            if (memberUser != null) {
                return memberUser;
            } else {
                return null;
            }
        }
    }
}
