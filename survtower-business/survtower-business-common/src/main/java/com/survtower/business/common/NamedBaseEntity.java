package com.survtower.business.common;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author Charles Chigoriwa
 */
@MappedSuperclass
public abstract class NamedBaseEntity extends BaseEntity {

    @Column(name="name",unique = true)
    protected String name;
    @Column(name="description")
    protected String description;
    @Column(name="deleted")
    protected Boolean deleted = Boolean.FALSE;

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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

}
