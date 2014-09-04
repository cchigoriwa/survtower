package com.survtower.business.common;

/**
 *
 * @author Charles Chigoriwa
 */
public abstract class NamedBaseEntity extends BaseEntity{
    
    protected String name;
    protected String description;
    protected Boolean deleted=Boolean.FALSE;

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

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
    
    
    
}
