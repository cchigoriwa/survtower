/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.survtower.business.common.domain;

import com.survtower.business.common.BaseEntity;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Takunda Dhlakama
 */
@Entity
@XmlRootElement
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"uuid"})})
public class Surveillance extends BaseEntity {

    @ManyToOne
    private Program program;
    @ManyToOne
    private Period period;
    @ManyToOne
    private Member member;
    @OneToMany
    private Set<SurveillanceData> surveillanceDataSet = new HashSet<SurveillanceData>();
    private Boolean uploaded = Boolean.FALSE;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date uploadedOn;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date approvedOn;
    @ManyToOne
    private User uploadedBy;
    @ManyToOne
    private User approvedBy;

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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Set<SurveillanceData> getSurveillanceDataSet() {
        return surveillanceDataSet;
    }

    public void setSurveillanceDataSet(Set<SurveillanceData> surveillanceDataSet) {
        this.surveillanceDataSet = surveillanceDataSet;
    }

    public Boolean isUploaded() {
        return uploaded;
    }

    public void setUploaded(Boolean uploaded) {
        this.uploaded = uploaded;
    }

    public Date getUploadedOn() {
        return uploadedOn;
    }

    public void setUploadedOn(Date uploadedOn) {
        this.uploadedOn = uploadedOn;
    }

    public Date getApprovedOn() {
        return approvedOn;
    }

    public void setApprovedOn(Date approvedOn) {
        this.approvedOn = approvedOn;
    }

    public User getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(User uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public User getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(User approvedBy) {
        this.approvedBy = approvedBy;
    }

}
