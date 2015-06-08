package com.survtower.client.member.controller.entry;

import com.survtower.business.common.domain.Surveillance;
import com.survtower.business.common.domain.SurveillanceData;
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
public class DataValidationController extends MessageInfor implements Serializable {

    private String surveillanceId;
    private Surveillance surveillance;
    private SurveillanceAudit surveillanceAudit;

    @ManagedProperty(value = "#{surveillanceService}")
    private SurveillanceService surveillanceService;

    @ManagedProperty(value = "#{surveillanceAuditService}")
    private SurveillanceAuditService surveillanceAuditService;

    @ManagedProperty(value = "#{memberUserService}")
    private MemberUserService memberUserService;

    @ManagedProperty(value = "#{regionSurveillanceDataService}")
    private RegionSurveillanceDataService regionSurveillanceDataService;

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

    public String submitSurviellanceForm() {
        if (!getSurveillanceAudit().getSubmissionDone()) {
            if (getSurveillanceAudit().getId() == null) {
                getSurveillanceAudit().setPeriod(getSurveillance().getPeriod());
                getSurveillanceAudit().setProgram(getSurveillance().getProgram());
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
        }

        return null;
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
        surveillance = surveillanceService.findByUuid(surveillanceId);
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
