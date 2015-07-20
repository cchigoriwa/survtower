package com.survtower.business.member.domain;

import com.survtower.business.common.BaseEntity;
import com.survtower.business.common.domain.Period;
import com.survtower.business.common.domain.Program;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Takunda Dhlakama
 * @author Charles Chigoriwa
 */
@Entity
@XmlRootElement
@Table(name = "region_surveillance_audit")
public class RegionSurveillanceAudit extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @ManyToOne
    @JoinColumn(name="program_id")
    private Program program;
    @ManyToOne
    @JoinColumn(name="period_id")
    private Period period;
    @ManyToOne
    @JoinColumn(name="region_id")
    private Region region;
    @ManyToOne
    @JoinColumn(name="uploaded_by_id")
    private MemberUser uploadedBy;
    @ManyToOne
    @JoinColumn(name="approved_by_id")
    private MemberUser approvedBy;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="uploaded_on")
    private Date uploadedOn;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="approved_on")
    private Date approvedOn;

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
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

    public Date getApprovedOn() {
        return approvedOn;
    }

    public void setApprovedOn(Date approvedOn) {
        this.approvedOn = approvedOn;
    }

    public Boolean getApproved() {//approval of data upload.
        return approvedBy != null;
    }

    public Boolean getSubmissionDone() {//final submission of data
        return getApproved() && getUploaded();
    }

    public Boolean getUploaded() {//upload of data
        return uploadedBy != null;
    }

    public String getForApproval() {
        return getRegion() + "-" + getProgram();
    }

    public boolean isSubmitted() {
        return id != null;
    }

}
