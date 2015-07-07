package com.survtower.business.common.domain;

import java.io.Serializable;

/**
 *
 * @author charlesc
 */
public class AppUserRole implements Serializable{
    
    private String name;
    private String description;

    public AppUserRole(String name, String description) {
        this.name = name;
        this.description = description;
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
    
    @Override
    public String toString() {
        return description;
    }
    
}
