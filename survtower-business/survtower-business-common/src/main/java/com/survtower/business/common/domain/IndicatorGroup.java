package com.survtower.business.common.domain;

import com.survtower.business.common.NamedBaseEntity;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Charles Chigoriwa
 */
@Entity
@XmlRootElement
@Table(name = "indicator_group")
public class IndicatorGroup extends NamedBaseEntity {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    private Program program;
    @XmlTransient
    @OneToMany
    private Set<Indicator> indicators = new HashSet<Indicator>();

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    @XmlTransient
    public Set<Indicator> getIndicators() {
        return indicators;
    }

    public void setIndicators(Set<Indicator> indicators) {
        this.indicators = indicators;
    }

    @Override
    public String toString() {
        return getName();
    }

}
