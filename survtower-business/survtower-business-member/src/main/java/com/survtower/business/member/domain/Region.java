package com.survtower.business.member.domain;

import com.survtower.business.common.NamedBaseEntity;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tdhlakama
 */
@Entity
@XmlRootElement
@Table(name = "region")
public class Region extends NamedBaseEntity {

    @Override
    public String toString() {
        return getName();
    }
}
