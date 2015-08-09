package com.survtower.client.member.controller.entry;

import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import com.survtower.business.common.domain.Surveillance;
import com.survtower.business.common.domain.SurveillanceData;
import com.survtower.business.common.service.MemberService;
import com.survtower.business.common.service.PeriodService;
import com.survtower.business.common.service.ProgramService;
import com.survtower.business.common.service.SurveillanceService;
import com.survtower.business.member.domain.MemberUser;
import com.survtower.business.member.domain.Region;
import com.survtower.business.member.domain.RegionSurveillanceAudit;
import com.survtower.business.member.domain.SurveillanceAudit;
import com.survtower.business.member.service.MemberUserService;
import com.survtower.business.member.service.RegionSurveillanceAuditService;
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
public class DataValidationController extends MessageInfor implements Serializable {

    private String surveillanceId;
    private String programId;
    private String periodId;
    private Surveillance surveillance;
    private SurveillanceAudit surveillanceAudit;
    private Program program;
    private Period period;

    @ManagedProperty(value = "#{surveillanceService}")
    private SurveillanceService surveillanceService;

    @ManagedProperty(value = "#{surveillanceAuditService}")
    private SurveillanceAuditService surveillanceAuditService;

    @ManagedProperty(value = "#{memberUserService}")
    private MemberUserService memberUserService;

    @ManagedProperty(value = "#{programService}")
    private ProgramService programService;

    @ManagedProperty(value = "#{periodService}")
    private PeriodService periodService;

    @ManagedProperty(value = "#{memberService}")
    private MemberService memberService;

    @ManagedProperty(value = "#{regionSurveillanceDataService}")
    private RegionSurveillanceDataService regionSurveillanceDataService;

    @ManagedProperty(value = "#{regionSurveillanceAuditService}")
    private RegionSurveillanceAuditService regionSurveillanceAuditService;

    public void setRegionSurveillanceDataService(RegionSurveillanceDataService regionSurveillanceDataService) {
        this.regionSurveillanceDataService = regionSurveillanceDataService;
    }

    public void setMemberUserService(MemberUserService memberUserService) {
        this.memberUserService = memberUserService;
    }

    public String getSurveillanceId() {
        return surveillanceId;
    }

    public void setSurveillanceId(String surveillanceId) {
        this.surveillanceId = surveillanceId;
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

    public SurveillanceAudit getSurveillanceAudit() {
        return surveillanceAudit;
    }

    public void setSurveillanceAudit(SurveillanceAudit surveillanceAudit) {
        this.surveillanceAudit = surveillanceAudit;
    }

    public Surveillance getSurveillance() {
        return surveillance;
    }

    public void setSurveillance(Surveillance surveillance) {
        this.surveillance = surveillance;
    }

    public void setSurveillanceService(SurveillanceService surveillanceService) {
        this.surveillanceService = surveillanceService;
    }

    public void setSurveillanceAuditService(SurveillanceAuditService surveillanceAuditService) {
        this.surveillanceAuditService = surveillanceAuditService;
    }

    public RegionSurveillanceAuditService getRegionSurveillanceAuditService() {
        return regionSurveillanceAuditService;
    }

    public void setRegionSurveillanceAuditService(RegionSurveillanceAuditService regionSurveillanceAuditService) {
        this.regionSurveillanceAuditService = regionSurveillanceAuditService;
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

    public MemberService getMemberService() {
        return memberService;
    }

    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
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

    public String finalSaveSurviellanceForm() {

        if (surveillanceAudit == null) {
            errorMessages("Surveillance Audit Error - Data Entry Not Done");
            return null;
        }

        if (getCurrentUser() == null) {
            errorMessages("User needs to login to continue");
            return null;
        }

        for (SurveillanceData surveillanceData : surveillance.getSurveillanceDataSet()) {
            if (!surveillanceData.getValid()) {
                errorMessages("Surveillance data is not valid, please verify the figures entered");
                return null;
            }
        }

        MemberUser memberUser = getCurrentUser();
        List<Region> regions = memberUser.getRegions();
        for (Region region : regions) {
            RegionSurveillanceAudit regionSurveillanceAudit = regionSurveillanceAuditService.findByProgramAndPeriodAndRegion(surveillance.getProgram(), surveillance.getPeriod(), region);
            if (regionSurveillanceAudit == null) {
                errorMessages("Surveillance Audit Error - Data Entry for some regions not Done");
                return null;
            }

            if (!regionSurveillanceAudit.getApproved()) {
                errorMessages("Surveillance data for some regions not approved, please approve these first");
                return null;
            }

        }

        try {
            surveillanceService.save(surveillance);
            surveillanceAudit.setApprovedBy(getCurrentUser());
            surveillanceAudit.setApprovedOn(new Date());
            //Added by Charles
            surveillanceAudit.setUploadedOn(new Date());
            surveillanceAudit.setUploadedBy(getCurrentUser());
            surveillanceAuditService.save(surveillanceAudit);
            inforMessages("Surveillance Data Saved Successfully");
        } catch (Exception ex) {
            errorMessages("Surveillance Data Not Processed Succefully");
        }
        return null;
    }

    private List<SurveillanceData> surveillanceDataList = new ArrayList<>();

    public List<SurveillanceData> getSurveillanceDataList() {
        return surveillanceDataList;
    }

    public void setSurveillanceDataList(List<SurveillanceData> surveillanceDataList) {
        this.surveillanceDataList = surveillanceDataList;
    }

    @PostConstruct
    public void loadData() {
        //get surveillance parameter form External Context
        surveillanceId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("surveillanceId");

        if (surveillanceId == null) {
            programId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("programId");
            periodId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("periodId");
            program = programService.findByUuid(programId);
            period = periodService.findByUuid(periodId);
            surveillance = surveillanceService.get(program, period, memberService.getCurrentMember());
        } else {
            surveillance = surveillanceService.findByUuid(surveillanceId);
        }

        surveillanceAudit = surveillanceAuditService.get(surveillance.getProgram(), surveillance.getPeriod());

        for (SurveillanceData surveillanceData : surveillance.getSurveillanceDataSet()) {
            surveillanceData.setNumeratorValue(regionSurveillanceDataService.getNumeratorCalculatedValue(surveillanceData));
            surveillanceData.setDenominatorValue(regionSurveillanceDataService.getDenominatedCalculatedValue(surveillanceData));
        }

        getSurveillanceDataList().clear();
        getSurveillanceDataList().addAll(surveillance.getSurveillanceDataSet());

        if (surveillanceAudit == null) {
            surveillanceAudit = new SurveillanceAudit();
            //Added by Charles
            surveillanceAudit.setProgram(surveillance.getProgram());
            surveillanceAudit.setPeriod(surveillance.getPeriod());

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
