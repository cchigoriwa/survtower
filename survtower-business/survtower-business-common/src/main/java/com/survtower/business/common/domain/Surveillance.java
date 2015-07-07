package com.survtower.business.common.domain;

import com.survtower.business.common.BaseEntity;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Takunda Dhlakama
 * @author Charles Chigoriwa
 */
@Entity
@XmlRootElement
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"program_id", "period_id", "member_id"})})
public class Surveillance extends BaseEntity {

    @ManyToOne
    private Program program;
    @ManyToOne
    private Period period;
    @ManyToOne
    private Member member;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<SurveillanceData> surveillanceDataSet = new HashSet<SurveillanceData>();
    @Transient
    private Boolean errorMarked;

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

    public Boolean getErrorMarked() {
        for (SurveillanceData data : getSurveillanceDataSet()) {
            if (data.getErrorMarked()) {
                return Boolean.TRUE;
            }
        }
        return errorMarked;
    }

}
