package com.survtower.client.member.controller.entry;

import com.survtower.business.common.domain.Surveillance;
import com.survtower.business.common.domain.SurveillanceData;
import com.survtower.business.common.service.SurveillanceService;
import com.survtower.client.member.utility.MessageInfor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Takund Dhlakama
 */
@ManagedBean
@ViewScoped
public class DataViewController extends MessageInfor implements Serializable {

    @ManagedProperty(value = "#{surveillanceService}")
    private SurveillanceService surveillanceService;

    private String surveillanceId;

    private Surveillance surveillance;

    public String getSurveillanceId() {
        return surveillanceId;
    }

    public void setSurveillanceId(String surveillanceId) {
        this.surveillanceId = surveillanceId;
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
        getSurveillanceDataList().clear();
        getSurveillanceDataList().addAll(getSurveillance().getSurveillanceDataSet());
    }
}
