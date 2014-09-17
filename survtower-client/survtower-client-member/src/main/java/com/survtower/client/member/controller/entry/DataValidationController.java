package com.survtower.client.member.controller.entry;

import com.survtower.business.common.domain.Surveillance;
import com.survtower.business.common.domain.SurveillanceData;
import com.survtower.business.common.service.SurveillanceService;
import com.survtower.business.member.domain.MemberUser;
import com.survtower.business.member.domain.SurveillanceAudit;
import com.survtower.business.member.service.MemberUserService;
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

    @ManagedProperty(value = "#{surveillanceService}")
    private SurveillanceService surveillanceService;

    @ManagedProperty(value = "#{surveillanceAuditService}")
    private SurveillanceAuditService surveillanceAuditService;

    @ManagedProperty(value = "#{memberUserService}")
    private MemberUserService memberUserService;

    private Boolean submitted = Boolean.FALSE;

    private String surveillanceId;

    private Surveillance surveillance;
    private SurveillanceAudit surveillanceAudit;

    public void setMemberUserService(MemberUserService memberUserService) {
        this.memberUserService = memberUserService;
    }

    public Boolean getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Boolean submitted) {
        this.submitted = submitted;
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

    public SurveillanceService getSurveillanceService() {
        return surveillanceService;
    }

    public void setSurveillanceService(SurveillanceService surveillanceService) {
        this.surveillanceService = surveillanceService;
    }

    public SurveillanceAuditService getSurveillanceAuditService() {
        return surveillanceAuditService;
    }

    public void setSurveillanceAuditService(SurveillanceAuditService surveillanceAuditService) {
        this.surveillanceAuditService = surveillanceAuditService;
    }

    public String submitSurviellanceForm() {
        submitted = Boolean.TRUE;
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
            submitted = Boolean.TRUE;
            getSurveillanceDataList().clear();
            getSurveillanceDataList().addAll(getSurveillance().getSurveillanceDataSet());
            surveillanceAudit.setApprovedBy(getCurrentUser());
            surveillanceAudit.setApprovedOn(new Date());
            surveillanceAuditService.save(surveillanceAudit);
            inforMessages("Surviellance Data Saved Successfully");
        } catch (Exception ex) {
            submitted = Boolean.FALSE;
            errorMessages("Surveillance Data Not Processed Succefully");
        }
        return null;
    }

    public String editSurviellanceForm() {
        return "data_entry?faces-redirect=true&programId=" + getSurveillance().getProgram().getUuid() + "&periodId=" + getSurveillance().getPeriod().getUuid();
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
        //get surveillance parameter form External Context
        surveillanceId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("surveillanceId");
        surveillance = surveillanceService.findByUuid(surveillanceId);
        surveillanceAudit = surveillanceAuditService.get(surveillance.getProgram(), surveillance.getPeriod());
        getSurveillanceDataList().clear();
        getSurveillanceDataList().addAll(getSurveillance().getSurveillanceDataSet());
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
