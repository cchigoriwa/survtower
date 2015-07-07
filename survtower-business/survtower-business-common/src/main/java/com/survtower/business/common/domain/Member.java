package com.survtower.business.common.domain;

import com.survtower.business.common.NamedBaseEntity;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Charles Chigoriwa
 */
@Entity
@XmlRootElement
public class Member extends NamedBaseEntity{
    
    private static final long serialVersionUID = 1L;
    
    private String code; 
    private String logo;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    
    

   @Override
    public String toString() {
        return getName();
    }
    
}
