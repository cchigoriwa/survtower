package com.survtower.business.member.domain;

import com.survtower.business.common.BaseEntity;
import com.survtower.business.common.domain.Program;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Takunda Dhlakama
 */
@Entity
@XmlRootElement
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"uuid"})})
public class MemberUser extends BaseEntity {

    private String username;
    private static final long serialVersionUID = 1L;
    @ElementCollection(targetClass=MemberRole.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "userrole")
    private Set<MemberRole> userRoles = new HashSet<MemberRole>();
    @OneToMany
    private Set<Program> programs = new HashSet<Program>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<MemberRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<MemberRole> userRoles) {
        this.userRoles = userRoles;
    }

    public Set<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(Set<Program> programs) {
        this.programs = programs;
    }

    @Override
    public String toString() {
        return getUsername();
    }

}
