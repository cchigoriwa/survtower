/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survtower.business.member.domain;

import com.survtower.business.common.BaseEntity;
import com.survtower.business.common.domain.Surveillance;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Takunda Dhlakama
 */
@Entity
public class SurveillanceAudit extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @ManyToOne
    private Surveillance surveillance;
    @ManyToOne
    private MemberUser uploadedBy;
    @ManyToOne
    private MemberUser approvedBy;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date uploadedOn;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date approvedByOn;

    public Surveillance getSurveillance() {
        return surveillance;
    }

    public void setSurveillance(Surveillance surveillance) {
        this.surveillance = surveillance;
    }

    public MemberUser getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(MemberUser uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public MemberUser getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(MemberUser approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Date getUploadedOn() {
        return uploadedOn;
    }

    public void setUploadedOn(Date uploadedOn) {
        this.uploadedOn = uploadedOn;
    }

    public Date getApprovedByOn() {
        return approvedByOn;
    }

    public void setApprovedByOn(Date approvedByOn) {
        this.approvedByOn = approvedByOn;
    }

}
