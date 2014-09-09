package com.survtower.business.common.domain;

import com.survtower.business.common.NamedBaseEntity;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Charles Chigoriwa
 */
@Entity
@XmlRootElement
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"uuid"})})
public class Program extends NamedBaseEntity {

    private static final long serialVersionUID = 1L;
    @XmlTransient
    @OneToMany
    private Set<IndicatorGroup> indicatorGroups = new HashSet<IndicatorGroup>();

    public Set<IndicatorGroup> getIndicatorGroups() {
        return indicatorGroups;
    }

    public void setIndicatorGroups(Set<IndicatorGroup> indicatorGroups) {
        this.indicatorGroups = indicatorGroups;
    }

    @Override
    public String toString() {
        return getName();
    }

}
