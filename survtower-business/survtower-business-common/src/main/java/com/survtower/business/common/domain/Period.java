package com.survtower.business.common.domain;

import com.survtower.business.common.NamedBaseEntity;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
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
public class Period extends NamedBaseEntity {

    private static final long serialVersionUID = 1L;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date validFrom;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date validTo;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dueDate;
    private Boolean active = Boolean.FALSE;
    @ManyToMany
    @JoinTable(name = "Period_Program", joinColumns = {
        @JoinColumn(name = "period_id")}, inverseJoinColumns = {
        @JoinColumn(name = "program_id")})
    private Set<Program> programs = new HashSet<Program>();
    @Transient
    private Boolean dueDatePassed;
    @Transient
    private Integer numberOfDaysLeftToDueDate;

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(Set<Program> programs) {
        this.programs = programs;
    }

    @Override
    public String toString() {
        return getName();
    }

    public Integer getNumberOfDaysLeftToDueDate() {
        if (getDueDate() == null) {
            return 0;
        }
        Calendar startCal;
        Calendar endCal;
        startCal = Calendar.getInstance();
        startCal.setTime(getDueDate());
        endCal = Calendar.getInstance();
        endCal.setTime(new Date());
        int daysLeft = 0;

        if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
            return 0;
        }
        //Return 0 if start and end are the same  
        if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
            return 0;
        }

        if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
            startCal.setTime(getDueDate());
            endCal.setTime(new Date());
        }

        do {
            startCal.add(Calendar.DAY_OF_MONTH, 1);
            ++daysLeft;

        } while (startCal.getTimeInMillis() < endCal.getTimeInMillis());

        return daysLeft;
    }

    public Boolean getDueDatePassed() {
        if (getDueDate() == null) {
            return Boolean.FALSE;
        }
        if (new Date().after(getDueDate())) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }

    }

    public String getDueDateStatus() {
        return getDueDatePassed() ? "-" + getNumberOfDaysLeftToDueDate() +" Days" : "+" + getNumberOfDaysLeftToDueDate() + " Days";
    }

}
