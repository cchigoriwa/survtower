package com.survtower.business.common.domain;

import com.survtower.business.common.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author Charles Chigoriwa
 */
@Entity
public class UserRole extends BaseEntity{
    
    private String name;
    private String description;
    @Column(unique=true)
    private String role;

    public UserRole() {
    }

    public UserRole(String name, String role) {
        this.name = name;
        this.role = role;
        this.description=name;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
