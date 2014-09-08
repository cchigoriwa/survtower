package com.survtower.business.common.domain;

import com.survtower.business.common.NamedBaseEntity;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Charles Chigoriwa
 */
@Entity
@XmlRootElement
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"uuid"})})
public class Member extends NamedBaseEntity{
    
    private static final long serialVersionUID = 1L;
    
    private String code; 

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

   @Override
    public String toString() {
        return getName();
    }
    
}
