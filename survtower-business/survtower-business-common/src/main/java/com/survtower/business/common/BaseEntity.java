
package com.survtower.business.common;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Charles Chigoriwa
 */
@MappedSuperclass
@XmlRootElement
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)  
    protected Long id;
    
    @Column(nullable = false, updatable = false)
    protected String uuid = UUID.randomUUID().toString();
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date createDate=new Date();
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updateDate=new Date();
    
    
    @XmlTransient
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    
    public boolean isNew(){
        return uuid==null;
    }
    
    public boolean identityEquals(BaseEntity entity){
        return Objects.equals(entity.id, this.id) && (entity.uuid == null ? this.uuid == null : entity.uuid.equals(this.uuid));
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BaseEntity other = (BaseEntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
